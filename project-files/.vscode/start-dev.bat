@echo off
REM =============================================================================
REM start-dev.bat - PJe (TJPE) - JBoss EAP 7.0
REM =============================================================================
REM Uso:
REM   start-dev.bat        -> Inicia apenas 1G (porta 8080, debug 8787)
REM   start-dev.bat 2g     -> Inicia apenas 2G (porta 8081, debug 8888)
REM   start-dev.bat all    -> Inicia 1G e 2G simultaneamente
REM =============================================================================

REM ---------------------------------------------------------------------------
REM Limpar variaveis que o standalone.bat/standalone2.bat "vazam" para o
REM ambiente do terminal apos executar. Se o terminal for reaproveitado
REM (ex: VS Code reusa o mesmo shell entre tasks), essas variaveis ficam
REM com o valor da instancia anterior (1G) e fazem o 2G carregar o
REM standalone.xml errado (do diretorio "standalone" em vez de "standalone2").
REM ---------------------------------------------------------------------------
set "JBOSS_BASE_DIR="
set "JBOSS_CONFIG_DIR="
set "JBOSS_LOG_DIR="
set "JBOSS_MODULEPATH="

REM ---------------------------------------------------------------------------
REM Caminhos do ambiente
REM ---------------------------------------------------------------------------
set "JBOSS_HOME=%USERPROFILE%\jboss-eap-7.0"
set "JAVA_HOME=%USERPROFILE%\jdk8u232-b09"
set "MAVEN_HOME=%USERPROFILE%\apache-maven-3.6.3"
set "PATH=%JAVA_HOME%\bin;%MAVEN_HOME%\bin;%PATH%"

REM ---------------------------------------------------------------------------
REM Raiz do repositorio (calculada a partir da localizacao deste script).
REM Este .bat sempre fica em "<raiz-do-repo>\.vscode\start-dev.bat", entao
REM subir um nivel a partir de %~dp0 da a raiz do projeto, independente de
REM onde cada usuario clonou o repositorio.
REM ---------------------------------------------------------------------------
for %%I in ("%~dp0..") do set "REPO_ROOT=%%~fI"

REM ---------------------------------------------------------------------------
REM Hostname da maquina
REM ---------------------------------------------------------------------------
set "HOSTNAME_FQDN=%COMPUTERNAME%"
if defined USERDNSDOMAIN set "HOSTNAME_FQDN=%COMPUTERNAME%.%USERDNSDOMAIN%"

REM ---------------------------------------------------------------------------
REM Selecionar instancia
REM ---------------------------------------------------------------------------
echo [DEBUG] Argumento recebido: "%~1"
if /i "%~1"=="2g" goto SETUP_2G
if /i "%~1"=="all" goto START_ALL
goto SETUP_1G

REM ===========================================================================
:SETUP_1G
set "INSTANCE_NAME=1G"
set "DEBUG_PORT=8787"
set "HTTP_PORT=8080"
set "APP_NAME=pje-tjpe-legacy-1g"
set "STANDALONE_BAT=%JBOSS_HOME%\bin\standalone.bat"
set "SERVER_BASE=%JBOSS_HOME%\standalone"
set "WAR_DEST=%SERVER_BASE%\deployments\pje.war"
goto CONFIGURE_ENV

REM ===========================================================================
:SETUP_2G
set "INSTANCE_NAME=2G"
set "DEBUG_PORT=8888"
set "HTTP_PORT=8081"
set "APP_NAME=pje-tjpe-legacy-2g"
set "STANDALONE_BAT=%JBOSS_HOME%\bin\standalone2.bat"
set "SERVER_BASE=%JBOSS_HOME%\standalone2"
set "WAR_DEST=%SERVER_BASE%\deployments\pje.war"
goto CONFIGURE_ENV

REM ===========================================================================
:START_ALL
echo [INFO] Iniciando 1G e 2G simultaneamente...
start "PJe 1G" cmd /c "%~f0"
start "PJe 2G" cmd /c "%~f0" 2g
echo.
echo [INFO] 1G e 2G iniciados em janelas separadas.
echo        1G: http://%HOSTNAME_FQDN%:8080/pje (debug 8787)
echo        2G: http://%HOSTNAME_FQDN%:8081/pje (debug 8888)
exit /b 0

REM ===========================================================================
REM Variaveis de ambiente da aplicacao
REM ===========================================================================
:CONFIGURE_ENV

set "ENV_EUREKA_CLIENT_HOSTNAME=%HOSTNAME_FQDN%"
set "ENV_EUREKA_CLIENT_NONSECURE_PORT=%HTTP_PORT%"
set "ENV_EUREKA_CLIENT_NONSECURE_PORT_ENABLED=true"
set "ENV_EUREKA_CLIENT_SECURE_PORT=8443"
set "ENV_EUREKA_CLIENT_SECURE_PORT_ENABLED=false"
set "ENV_EUREKA_SERVER_URL=http://%HOSTNAME_FQDN%:8761/eureka"
set "ENV_EUREKA_SHOULD_USE_DNS=true"
set "ENV_PJE2_AUDITORIA_TIPO_PERSISTENCIA=DB"
set "ENV_PJE2_CLIENTE_URL=http://%HOSTNAME_FQDN%:4200"
set "ENV_PJE2_CLOUD_APP_NAME=%APP_NAME%"
set "ENV_PJE2_CLOUD_RABBIT_AUTORIDADES_CERTIFICADORAS_CONSUMER=false"
set "ENV_PJE2_CLOUD_RABBIT_CONNECTION_REQUIRED=true"
set "ENV_PJE2_CLOUD_RABBIT_EXCHANGENAME=pje.legacy"
set "ENV_PJE2_CLOUD_RABBIT_HOST=localhost"
set "ENV_PJE2_CLOUD_RABBIT_JOB_DJE_ENABLED=false"
set "ENV_PJE2_CLOUD_RABBIT_JOBS_CONSUMER=false"
set "ENV_PJE2_CLOUD_RABBIT_JOBS_PUBLISHER=false"
set "ENV_PJE2_CLOUD_RABBIT_PASSWORD=guest"
set "ENV_PJE2_CLOUD_RABBIT_PORT=5672"
set "ENV_PJE2_CLOUD_RABBIT_PUBLISH_MESSAGES=false"
set "ENV_PJE2_CLOUD_RABBIT_QUEUENAME=pje.exchange"
set "ENV_PJE2_CLOUD_RABBIT_USERNAME=guest"
set "ENV_PJE2_CLOUD_RABBIT_VIRTUALHOST=/"
set "ENV_PJE2_CLOUD_REGISTRA_COM_IP=true"
set "ENV_PJE2_CLOUD_REGISTRAR=true"
set "ENV_PJE2_CLOUD_URL_GATEWAY=http://%HOSTNAME_FQDN%:8180"
set "ENV_PJE2_CORS_ENABLED=true"
set "ENV_PJE_UPDATE_AUTORIDADES_CERTIFICADORAS_ONSTARTUP=false"
set "ENV_SSO_AUTHENTICATION_ENABLED=false"
set "ENV_SSO_AUTHORIZATION_ENABLED=false"
set "ENV_SSO_AUTHSERVER_URL=https://sso.stg.cloud.pje.jus.br/auth"
set "ENV_SSO_CLIENT_ID=pje-tjpe-1g"
set "ENV_SSO_CLIENT_SECRET=b9bbd9b7-c832-4e8f-a2a7-e211a973b80b"
set "ENV_SSO_CONFIDENTIAL_PORT=443"
set "ENV_SSO_REALM=pje"
set "ENV_SSO_SSL_REQUIRED=ALL"
set "ENV_SSO_URL=https://sso.stg.cloud.pje.jus.br"
set "ENV_URL_API_ASSINADOR=https://teste-pje-assinador.app.tjpe.jus.br/assinador-a1/api/v1/assinar/"
set "pje.contexto.aplicacao=pje"

REM ---------------------------------------------------------------------------
REM Deploy do artefato
REM ---------------------------------------------------------------------------
set "WAR_SOURCE=%REPO_ROOT%\pje-web\target\pje"

if not exist "%WAR_SOURCE%" goto NO_SOURCE
goto HAS_SOURCE

:NO_SOURCE
if not exist "%WAR_DEST%" goto NO_WAR_AT_ALL
echo [DEPLOY] Usando WAR existente em %WAR_DEST%
echo.> "%SERVER_BASE%\deployments\pje.war.dodeploy"
goto CHECK_RUNNING

:NO_WAR_AT_ALL
echo [ERRO] WAR nao encontrado em %WAR_SOURCE% nem em %WAR_DEST%
echo        Execute o build antes: mvn clean package -DskipTests
pause
exit /b 1

:HAS_SOURCE
set "NEED_COPY=true"
if not exist "%WAR_DEST%" goto DO_COPY_CHECK
powershell -Command "if ((Get-Item '%WAR_SOURCE%').LastWriteTime -le (Get-Item '%WAR_DEST%').LastWriteTime) { exit 1 } else { exit 0 }" >nul 2>&1
if %ERRORLEVEL% neq 0 (
    set "NEED_COPY=false"
    echo [DEPLOY] WAR no JBoss ja esta atualizado. Pulando copia.
)

:DO_COPY_CHECK
if not "%NEED_COPY%"=="true" goto SKIP_COPY
echo [DEPLOY] Copiando artefato para o JBoss...
if exist "%WAR_DEST%" rmdir /s /q "%WAR_DEST%"
xcopy /E /I /Y /Q "%WAR_SOURCE%" "%WAR_DEST%\"

:SKIP_COPY
echo.> "%SERVER_BASE%\deployments\pje.war.dodeploy"

REM ---------------------------------------------------------------------------
REM Verificar se ja esta rodando
REM ---------------------------------------------------------------------------
:CHECK_RUNNING
powershell -Command "try { $c = New-Object Net.Sockets.TcpClient('localhost', %HTTP_PORT%); $c.Close(); exit 0 } catch { exit 1 }" >nul 2>&1
if %ERRORLEVEL% neq 0 goto DO_START
echo.
echo [INFO] JBoss %INSTANCE_NAME% ja esta rodando na porta %HTTP_PORT%.
echo        App: http://%HOSTNAME_FQDN%:%HTTP_PORT%/pje
echo        Debug: porta %DEBUG_PORT%
echo.
exit /b 0

:DO_START

REM Limpar JAVA_OPTS para que o .conf.bat do JBoss configure corretamente
set "JAVA_OPTS="

REM Port-offset para 2G: standalone2.xml usa as MESMAS portas padrao (8080/9990)
REM do 1G. Sem o offset, os dois JBoss brigam pelas mesmas portas ao rodar juntos.
set "PORT_OFFSET_ARG="
if "%INSTANCE_NAME%"=="2G" set "PORT_OFFSET_ARG=-Djboss.socket.binding.port-offset=1"

REM ---------------------------------------------------------------------------
REM IMPORTANTE: standalone.bat/standalone2.bat calculam um JBOSS_BASE_DIR
REM interno (para paths de log), mas isso NAO se torna a propriedade JVM
REM "-Djboss.server.base.dir" automaticamente. Sem esse -D explicito,
REM org.jboss.as.server.Main usa o DEFAULT hardcoded do proprio JBoss:
REM "%JBOSS_HOME%\standalone" (sem "2"), independente de qual .bat foi
REM chamado. Por isso o 2G sempre carregava o standalone.xml errado.
REM Forcamos aqui via argumento de linha de comando (SERVER_OPTS).
REM ---------------------------------------------------------------------------
set "SERVER_BASE_ARGS=-Djboss.server.base.dir=%SERVER_BASE% -Djboss.server.config.dir=%SERVER_BASE%\configuration -Djboss.server.log.dir=%SERVER_BASE%\log"

REM ---------------------------------------------------------------------------
REM Resolver e validar o caminho REAL do standalone.xml que sera usado
REM (standalone.bat/standalone2.bat resolvem -c standalone.xml relativo ao
REM  JBOSS_BASE_DIR, que e definido internamente como %JBOSS_HOME%\standalone
REM  ou %JBOSS_HOME%\standalone2 dependendo do script chamado)
REM ---------------------------------------------------------------------------
set "CONFIG_XML_REAL=%SERVER_BASE%\configuration\standalone.xml"

echo.
echo =========================================
echo  PJe TJPE - JBoss EAP 7.0 (%INSTANCE_NAME% - Debug)
echo =========================================
echo  JBOSS_HOME:      %JBOSS_HOME%
echo  Script:          %STANDALONE_BAT%
echo  Server base:     %SERVER_BASE%
echo  Config (XML):    %CONFIG_XML_REAL%
echo  JAVA_HOME:       %JAVA_HOME%
echo  Hostname:        %HOSTNAME_FQDN%
echo  HTTP:            porta %HTTP_PORT%
echo  Debug:           porta %DEBUG_PORT%
echo  App:             http://%HOSTNAME_FQDN%:%HTTP_PORT%/pje
echo =========================================

if not exist "%CONFIG_XML_REAL%" (
    echo.
    echo [ERRO] Arquivo de configuracao NAO encontrado em:
    echo        %CONFIG_XML_REAL%
    pause
    exit /b 1
)

for %%F in ("%CONFIG_XML_REAL%") do (
    echo  Tamanho do XML:  %%~zF bytes
    echo  Modificado em:   %%~tF
)
echo =========================================
echo.

REM ---------------------------------------------------------------------------
REM Corrigir logging.properties com o caminho ABSOLUTO real do log desta
REM instancia. O placeholder ${jboss.server.log.dir} nao e resolvido pelo
REM bootstrap de logging (carregado pela JVM antes do JBoss processar os
REM argumentos -D), entao gravamos o caminho expandido diretamente a cada
REM inicializacao (funciona para qualquer usuario/maquina, pois SERVER_BASE
REM ja foi resolvido a partir de %%USERPROFILE%%).
REM ---------------------------------------------------------------------------
cscript //nologo "%~dp0fix-logging-abspath.vbs" "%SERVER_BASE%\configuration\logging.properties" "%SERVER_BASE%\log\server.log" >nul

REM ---------------------------------------------------------------------------
REM Mudar o diretorio de trabalho para JBOSS_HOME\bin antes de iniciar o
REM JBoss (mesmo comportamento do IntelliJ). Isso evita que caminhos
REM relativos do projeto (ex: hibernate.search.index_base=./indices/) criem
REM arquivos dentro do repositorio do PJe.
REM ---------------------------------------------------------------------------
pushd "%JBOSS_HOME%\bin"
call "%STANDALONE_BAT%" -c standalone.xml -b 0.0.0.0 -bmanagement 0.0.0.0 %SERVER_BASE_ARGS% %PORT_OFFSET_ARG%
popd
