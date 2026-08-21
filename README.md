# Configuração do Ambiente de Desenvolvimento

Este repositório contém os projetos necessários para executar os serviços **Gateway, Discovery e JCR** em ambiente de desenvolvimento.

## 1. Clonar o repositório dos serviços

Após clonar este repositório, recomenda-se armazená-lo no diretório do usuário, por exemplo:

`C:\Users\usuario`

## 2. Configurar o projeto PJe

Após a instalação do **Kiru**, clone o repositório principal do PJe:

`https://git.cnj.jus.br/pje/pje.git`

Recomenda-se utilizar o diretório:

`C:\dev`

## 3. Configurar o PJe Web

Clone também o repositório do projeto **PJe Web Front (Angular)**:

`https://git.cnj.jus.br/pje2/pje2-clientes/pje2-web`

Recomenda-se utilizar o diretório:

`C:\dev`

## 4. Instalar o ambiente

Após realizar os clones dos projetos, execute o script:

`install.bat`

Aguarde a conclusão do script antes de prosseguir.

## 5. Inicializar os serviços

Com o ambiente de desenvolvimento configurado e em execução local pelo **Kiru**, execute o script:

`Inicializar serviços Discovery, Gateway e JCR.bat`

Esse script será responsável por inicializar os projetos correspondentes aos serviços:

* **Discovery**
* **Gateway**
* **JCR**

Após a execução, os serviços estarão disponíveis para utilização no ambiente de desenvolvimento local.
