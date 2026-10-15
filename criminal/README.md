
# PJe Criminal

Serviço de negócio do ecossistema PJe responsável por manter dados específicos de processos judiciais criminais fora do monólito. O serviço oferece APIs REST para processos, partes, eventos criminais, informações de prisão/soltura, penas, medidas socioeducativas, normas e cadastros de apoio, além de consumir eventos do PJe por RabbitMQ.

Esta documentação descreve o estado observado no código e nas configurações deste repositório (`3.0.7-SNAPSHOT`).

## Arquitetura em alto nível

```text
Clientes / PJe legado
        │ REST + Bearer token + X-pje-legacy-app
        ▼
┌───────────────────────────────────────────────┐
│ Criminal (Spring Boot)                        │
│ Controllers v1/v2 → Services → Repositories   │
│       │                         │             │
│       ├─ Feign → PJe legado/gateway           │
│       ├─ SOAP/MNI → SEEU                      │
│       └─ RabbitListener → handlers de eventos │
└───────────────────────────────────────────────┘
        │ JPA/Hibernate + Flyway + Envers
        ▼
 PostgreSQL (schemas core e audit)

 RabbitMQ ◄── eventos do ecossistema
 Eureka    ◄── descoberta de serviços
 Keycloak  ◄── autenticação/autorização
```

O processo HTTP passa pelo `TribunalInterceptor`, que resolve o tribunal usando o cabeçalho `X-pje-legacy-app`, valida a permissão do usuário de sistema e coloca o tribunal em `TribunalContext`. 

Entidades multi-tenant recebem `cd_tribunal` na persistência e usam filtros Hibernate para evitar acesso cruzado entre tribunais. 

O contexto é limpo ao final da requisição.

O fluxo assíncrono é iniciado por `CriminalMessageConsumer`: a fila `pje.criminal` recebe eventos do exchange topic `pje.exchange`; o `appName` do envelope identifica o tribunal e eventos `ProcessoParte` são delegados a `ParteEventHandler`. A implementação faz verificação de existência e fallback de atualização para criação, favorecendo idempotência em reentregas. Embora exista a routing key `ProcessoEvento`, ela não está associada a um listener no estado atual.

## Componente central: informação criminal

O agregado central do domínio é organizado assim:

```text
Processo (tb_processo)
  └── Parte (tb_parte)                    1 processo → várias partes
        └── InformacaoCriminal             processo + parte
              └── ConteudoInformacaoCriminalBean
                    ├── identificação da pessoa
                    └── listas de eventos criminais por categoria
```

`InformacaoCriminal` é uma entidade JPA (`tb_informacao_criminal`) auditada pelo Envers. Ela possui FKs para `Processo` e `Parte`, mas não contém uma coleção relacional de eventos. O campo `ds_conteudo` é `jsonb` e armazena todo o `ConteudoInformacaoCriminalBean`; a tabela de auditoria `audit.tb_informacao_criminal_aud` guarda os snapshots desse JSON. O bean mantém dados de identificação (`nomePrincipal`, `nomes`, `filiacoes`, documentos e características físicas) e listas independentes para os fatos do processo daquela parte.

### Modelo de evento

`EventoCriminalBean` é a classe base dos eventos. Seus campos comuns são `id`, `data`, `dataCadastro`, `tipoInformacaoCriminal`, `tipificacoes`, `processoMovimentoLegacy`, `observacao`, `resumo` e `ativo`. As subclasses acrescentam dados específicos do fato, como datas e prazos de prisão, unidade prisional e motivo de soltura, trânsito em julgado, decisão, pena, internação, evasão ou remissão. As subclasses estão em `model/beans` e incluem `PrisaoBean`, `SolturaBean`, `FugaBean`, `SentencaCondenatoriaProcessoBean`, `DecisaoInstanciaSuperiorBean`, `InternacaoBean`, `EvasaoBean` e `RemissaoBean`.

O tipo persistido em `tipoInformacaoCriminal.codTipoIc` é o discriminador funcional. `ConteudoInformacaoCriminalBean.addEventoCriminal`, `removeEventoCriminal` e `getEventosCriminaisPorTipo` usam esse código para escolher a lista. Há agrupamentos relevantes: `OFD/OFR` usam oferecimento de denúncia/representação; `RCD/RCR`, recebimento; `ADD/ADR`, aditamento; `NRD/NRR`, não recebimento; e os demais códigos selecionam listas de subclasses próprias. A enumeração completa está em `TipoEventoCriminalEnum` e o catálogo/associação com movimentos legados fica em `TipoEventoCriminal` e `TipoEventoCriminalMovimento`.

As principais famílias representadas no conteúdo são:

| Família | Listas/categorias no JSON |
| --- | --- |
| Formação da acusação | `indiciamento`, `oferecimentoDenuncia`, `recebimentoDenuncia`, `aditamentoDenuncia`, `naoRecebimentoDenuncia`, `citacoes`, `recebimentosAditamentoDenuncia` |
| Custódia e liberdade | `prisoes`, `solturas`, `fugas`, `presosOutroProcesso` |
| Suspensão e decisões | `suspensoesProcesso`, `decisoesInstanciaSuperior`, `decisoes`, `revogacoesSuspensaoCondicionalProcesso` |
| Sentenças criminais | `sentencasCondenatorias`, `sentencasAbsolutorias`, `sentencasAbsolutoriasImproprias`, `sentencasAbsolvicaoSumaria`, `sentencasPronuncia`, `sentencasImpronuncia`, `sentencasExtincaoPunibilidade` |
| Desfechos processuais | `desclassificacoesProcesso`, `desmembramentos`, `trancamentos`, `nulidadesProcessos`, `litispendenciasCoisaJulgada`, `ausenciasCondicoesAcao`, `decliniosCompetencia`, `arquivamentosIncidencias`, `homologacoesComposicoesDanosCivis`, `transacoesPenais` |
| Socioeducativo | `internacoes`, `sentencasInfracionais`, `sentencasExtincaoMedidaSocioeducativa`, `semiliberdades`, `suspensoesMedidasSocioeducativas`, `remissoes` |

### Persistência e ciclo de vida

O fluxo usual de gravação é read-modify-write: localizar por `idProcesso` e `idProcessoParteLegacy`, criar `InformacaoCriminal(processo, parte)` se necessário, normalizar referências de domínio, preencher `data`/`dataCadastro`, adicionar ou substituir o evento e salvar a entidade. O documento inteiro é então serializado novamente para `ds_conteudo`.

O `ConteudoInformacaoCriminalConverter` usa Jackson e ignora propriedades desconhecidas na leitura. Isso permite evolução compatível, mas renomear uma propriedade ou alterar a classe de uma lista exige avaliar registros existentes e, possivelmente, uma migração JSONB. As migrações já transformam JSONB para preencher `dataCadastro` e compatibilizar campos de data legados (`data`, `dtPrisao`, `dtSoltura`, `dtFuga` etc.).

O modelo pretendido é uma informação por parte dentro do processo, porém o schema atual não possui `UNIQUE` sobre `(id_processo, id_parte)` e o repositório retorna `List`, usando o primeiro item em várias operações. Não se deve assumir unicidade física sem diagnóstico e correção compatível.

### APIs do componente

O controller principal é `controllers/v1/InformacaoCriminalRestController`. Ele expõe endpoints legados sem prefixo `/api`, protegidos por `@InboundRequest`, para consultar tipos e movimentos, recuperar informação por processo/parte, criar/alterar/remover eventos, listar eventos e incidências penais, consultar pendências e operar famílias específicas como prisões, solturas, fugas, sentenças, internações, decisões e remissões.

As rotas específicas seguem `/{recurso}/processos/{idProcesso}/partes/{idProcessoParteLegacy}`. `getEventosCriminais()` concatena listas em ordem fixa de implementação; não ordena por `data` ou `dataCadastro`. Consumidores que precisam de linha do tempo devem ordenar explicitamente. Eventos importados podem ser associados a `ProcessoMovimentoLegacyBean`; a checagem de pendências usa `(idProcessoEvento, tipoInformacaoCriminal)` para detectar materialização anterior.

## Tecnologias e integrações

- Java 8, Maven e Spring Boot `2.0.3.RELEASE`.
- Spring MVC, Spring Data JPA/Hibernate, AOP, Actuator e Spring Cloud Finchley.
- PostgreSQL; migrações Flyway em `src/main/resources/db/migration`; auditoria Hibernate Envers no schema `audit`.
- RabbitMQ/AMQP com conversão JSON via Jackson.
- Eureka para descoberta e OpenFeign/Ribbon para chamadas ao PJe legado.
- Keycloak `9.0.2`, com suporte a Bearer token e segurança em nível de método.
- Spring-WS/JAXB, MNI e clientes SOAP para integração com o SEEU.
- Swagger 2/Springfox para APIs v1 e v2; Micrometer/Prometheus para métricas.
- `cnj-interop`, `pje-commons`, `pje-interfaces`, geração JAXB, iText e XHTMLRenderer para recursos específicos do domínio.

## Estrutura do código

| Diretório | Responsabilidade |
| --- | --- |
| `controllers/` | Adaptadores HTTP REST; contratos v1 e v2 ficam separados. |
| `services/` | Casos de uso e regras de negócio. |
| `model/entities/` | Entidades JPA e relacionamentos persistidos. |
| `model/beans`, `model/dto`, `api/dto` | Modelos de domínio e transporte. |
| `model/converters/` | Serialização do agregado de informação criminal para JSONB. |
| `repositories/` | Repositórios Spring Data. |
| `amqp/` | Configuração, consumidor, envelope CloudEvent e handlers. |
| `clients/` e `seeu/` | Integrações Feign, SOAP e classes JAXB. |
| `aop/`, `listeners/`, `audit/` | Contexto de tribunal, multi-tenancy, logging e auditoria. |
| `security/` | Keycloak, provedores PJe e autorização por método. |
| `src/main/resources/db/` | Banco base e 53 scripts de migração Flyway. |
| `src/test/` | Testes de contexto, unidade, MVC, repositório e integração. |

## Execução local

Requisitos: JDK 8, Maven, PostgreSQL, RabbitMQ e, para chamadas protegidas, Keycloak. Eureka é necessário quando o cliente de descoberta estiver habilitado.

1. Suba as dependências locais disponíveis:

   ```bash
   docker compose up -d discovery rabbitmq
   ```

2. Crie um banco PostgreSQL chamado `criminal` e configure os schemas/permissões esperados (`core` e `audit`). O Flyway executa as migrações na inicialização; o Hibernate está configurado com `ddl-auto: none`.

3. Compile e execute:

   ```bash
   mvn clean test
   mvn spring-boot:run
   ```

   Alternativamente, gere o JAR e execute `java -jar target/criminal-3.0.7-SNAPSHOT.jar`.

Por padrão, a aplicação escuta em `http://localhost:8480`, conecta ao PostgreSQL `criminal` e registra-se no Eureka em `http://localhost:8761/eureka`. Os valores são substituíveis por variáveis de ambiente, principalmente `CRIMINAL_DB_URL`, `CRIMINAL_DB_USER`, `CRIMINAL_DB_PASSWORD`, `CRIMINAL_RABBITMQ_HOST`, `EUREKA_SERVER_DEFAULT_ZONE`, `SSO_AUTH_SERVER`, `SSO_REALM` e `SSO_RESOURCE`. Os valores padrão de desenvolvimento presentes em `application.yml` não devem ser usados em produção.

O arquivo `docker-compose.yml` fornece Discovery e RabbitMQ com management em `http://localhost:15672`; ele não fornece PostgreSQL, Keycloak nem o gateway. O `Dockerfile` espera que o JAR já esteja em `target/` e expõe as portas 8480/8481.

## APIs e observabilidade

- API versionada: `/api/v1/**` e `/api/v2/**`.
- Há também endpoints legados sem `/api`, especialmente na v1; consulte os controllers e o Swagger antes de criar novos consumidores.
- Swagger UI e especificações são gerados pelos grupos `criminal-v1` e `criminal-v2`.
- Actuator: `/actuator/health`, `/actuator/info` e `/actuator/prometheus` são expostos conforme o profile. A configuração atual permite `/actuator/**` na segurança; revise essa decisão antes de publicar o serviço diretamente.
- As requisições que acessam dados tenant-aware precisam enviar `X-pje-legacy-app` com um `appName` reconhecido. Eventos AMQP usam `appName` no CloudEvent.

## Testes e recursos úteis

Os testes estão em `src/test/java` e cobrem contexto Spring, MVC, AMQP, repositório e uma integração de filtragem por tribunal. Os exemplos HTTP estão em [`http/seeu.http`](http/seeu.http) e a análise detalhada do consumidor AMQP está em [`CriminalMessageConsumer.md`](CriminalMessageConsumer.md).

Ao alterar o modelo persistido, crie uma nova migração Flyway em vez de depender de alteração automática do Hibernate. Ao alterar contratos REST, verifique v1 e v2, segurança e compatibilidade com o PJe legado.

## Execução local em container

- ajuste as configurações da base criminal utilizada em application.yml

- rode os comandos:
```
mvn verify -DskipTests
sudo docker build --network=host -t criminal  .
sudo docker run --rm --network=host criminal
```

- alternativamente, pelo VSCode, suba aplicação pelo Spring Boot Dashboard.

## Acesso SEEU

Para utilizar os serviços do SEEU, na tabela core.tb_seeu_tribunal, verifique se os dados do Tribunal que utiliza a aplicação estão preenchidos.

## Acessando informações criminais via PJe

Para acessar as informações criminais via PJe, nos autos digitais do processo, vá no menu > Informações Criminais.
A fim de que essa tela funciona corretamente, é necessário que tb_processo e tb_parte da base criminal estejam atualizados com os dados do processo em questão.
