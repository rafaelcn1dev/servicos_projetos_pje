# Setup do Ambiente de Desenvolvimento - PJe TJPE

Guia rápido para configurar o ambiente local e rodar o projeto com debug (1G e/ou 2G) direto pelo Kiro/VS Code.

## 1. Onde instalar as ferramentas

Tudo deve ficar na pasta do seu usuário (`%USERPROFILE%`), ou seja, `C:\Users\SEU_USUARIO\`. Os scripts detectam esse caminho automaticamente, então funciona para qualquer pessoa sem editar nada.

| Ferramenta | Onde instalar | Exemplo |
|---|---|---|
| **Java (JDK 8)** | `%USERPROFILE%\jdk8u232-b09` | `C:\Users\rafacTJ\jdk8u232-b09` |
| **Maven 3.6.3** | `%USERPROFILE%\apache-maven-3.6.3` | `C:\Users\rafacTJ\apache-maven-3.6.3` |
| **JBoss EAP 7.0** | `%USERPROFILE%\jboss-eap-7.0` | `C:\Users\rafacTJ\jboss-eap-7.0` |

Dentro do JBoss, é necessário ter as duas instâncias configuradas:
- `jboss-eap-7.0\standalone` → **1G** (1º grau)
- `jboss-eap-7.0\standalone2` → **2G** (2º grau), com `bin\standalone2.bat` e `bin\standalone2.conf.bat`

> Se os nomes das pastas do Java/Maven forem diferentes na sua máquina, ajuste as 3 linhas no topo do `.vscode/start-dev.bat` (seção "Caminhos do ambiente").

## 2. Arquivos do projeto (já vêm prontos no repositório)

| Arquivo | Para que serve |
|---|---|
| `.vscode/start-dev.bat` | Script principal. Faz o deploy do WAR e inicia o JBoss (1G, 2G ou os dois). |
| `.vscode/fix-logging-abspath.vbs` | Usado internamente pelo `start-dev.bat` para gravar o caminho absoluto do log no `logging.properties` a cada inicialização. |
| `.vscode/tasks.json` | Define as tasks "Start JBoss 1G" e "Start JBoss 2G" que o `start-dev.bat` usa. |
| `.vscode/launch.json` | Define as configurações de debug ("PJe Debug 1G" / "PJe Debug 2G") que aparecem no painel Run and Debug. |

## 3. Como rodar (fluxo do dia a dia)

1. Compile o projeto (fora do Kiro, como você já faz hoje):
   ```
   mvn clean package -DskipTests
   ```

2. No Kiro, abra o painel **Run and Debug** (`Ctrl+Shift+D`).

3. Escolha uma opção e clique em play:
   - **PJe Debug 1G** → sobe o JBoss 1G (porta 8080) e conecta o debug (porta 8787)
   - **PJe Debug 2G** → sobe o JBoss 2G (porta 8081) e conecta o debug (porta 8888)

4. Acesse no navegador: `http://localhost:8080/pje` (1G) ou `http://localhost:8081/pje` (2G).

Cada instância roda de forma independente — pode subir só uma, ou as duas juntas ao mesmo tempo.

## 4. Primeira vez configurando numa máquina nova

Use o instalador `pje-dev-setup.zip` (veja com quem já tem o ambiente configurado). Ele:

1. Verifica se Java 8, Maven e JBoss EAP 7.0 estão instalados (baixa Java e Maven automaticamente se faltarem).
2. Copia as configurações do JBoss (1G e 2G) já ajustadas para funcionar com qualquer usuário/máquina (usam `%USERPROFILE%` em vez de caminho fixo).
3. Copia `start-dev.bat`, `fix-logging-abspath.vbs`, `tasks.json` e `launch.json` para a pasta `.vscode` do repositório.

Basta extrair o `.zip` e executar `install.bat`.
