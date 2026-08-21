@echo off
REM =============================================================================
REM install.bat - Instalador do ambiente de desenvolvimento PJe (TJPE)
REM =============================================================================
REM 1. Verifica se Java 8, Maven e JBoss EAP 7.0 estao instalados. Quando
REM    ausentes, baixa e instala automaticamente (Java e Maven do mirror
REM    oficial; JBoss EAP de um mirror interno da equipe no GitHub, ja que
REM    o download oficial exige login no Red Hat Customer Portal).
REM    As configuracoes de JCR (jcr / pje-binarios) vem junto neste .zip e
REM    sao copiadas automaticamente se ainda nao existirem na maquina.
REM 2. Copia os arquivos de configuracao do JBoss (1G e 2G) e os arquivos do
REM    projeto (start-dev.bat, tasks.json, launch.json) para os diretorios
REM    corretos.
REM
REM Uso: execute este .bat dentro da pasta onde ele foi extraido do .zip.
REM =============================================================================

setlocal EnableDelayedExpansion

set "SCRIPT_DIR=%~dp0"
set "JAVA_DIR=%USERPROFILE%\jdk8u232-b09"
set "MAVEN_DIR=%USERPROFILE%\apache-maven-3.6.3"
set "JBOSS_HOME=%USERPROFILE%\jboss-eap-7.0"
set "JCR_DIR=%USERPROFILE%\jcr"
set "PJE_BINARIOS_DIR=%USERPROFILE%\pje-binarios"
set "TEMP_DOWNLOAD=%TEMP%\pje-env-setup"
set "MISSING_CRITICAL=false"

echo =========================================
echo  Instalador do Ambiente PJe - TJPE
echo =========================================
echo.

REM =============================================================================
REM ETAPA 1 - Verificar/preparar Java, Maven, JBoss e configuracoes de JCR
REM =============================================================================
echo [ETAPA 1/2] Verificando ferramentas necessarias...
echo.

REM ---------------------------------------------------------------------------
REM 1.1 Java 8 (jdk8u232-b09) - download automatico (AdoptOpenJDK, publico)
REM ---------------------------------------------------------------------------
if exist "%JAVA_DIR%\bin\java.exe" (
    echo [OK] Java 8 encontrado em %JAVA_DIR%
) else (
    echo [FALTA] Java 8 nao encontrado em %JAVA_DIR%
    echo         Baixando automaticamente ^(AdoptOpenJDK 8u232-b09^)...
    call :DOWNLOAD_AND_EXTRACT ^
        "https://github.com/AdoptOpenJDK/openjdk8-binaries/releases/download/jdk8u232-b09/OpenJDK8U-jdk_x64_windows_hotspot_8u232b09.zip" ^
        "%TEMP_DOWNLOAD%\java.zip" ^
        "%USERPROFILE%" ^
        "jdk8u232-b09" ^
        "%JAVA_DIR%"
    if exist "%JAVA_DIR%\bin\java.exe" (
        echo [OK] Java 8 instalado em %JAVA_DIR%
    ) else (
        echo [ERRO] Falha ao baixar/instalar o Java automaticamente.
        echo        Baixe manualmente em:
        echo        https://github.com/AdoptOpenJDK/openjdk8-binaries/releases/tag/jdk8u232-b09
        echo        Extraia o conteudo para: %JAVA_DIR%
        set "MISSING_CRITICAL=true"
    )
)

REM ---------------------------------------------------------------------------
REM 1.2 Maven 3.6.3 - download automatico (Apache, publico)
REM ---------------------------------------------------------------------------
if exist "%MAVEN_DIR%\bin\mvn.cmd" (
    echo [OK] Maven encontrado em %MAVEN_DIR%
) else (
    echo [FALTA] Maven nao encontrado em %MAVEN_DIR%
    echo         Baixando automaticamente ^(Apache Maven 3.6.3^)...
    call :DOWNLOAD_AND_EXTRACT ^
        "https://archive.apache.org/dist/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.zip" ^
        "%TEMP_DOWNLOAD%\maven.zip" ^
        "%USERPROFILE%" ^
        "apache-maven-3.6.3" ^
        "%MAVEN_DIR%"
    if exist "%MAVEN_DIR%\bin\mvn.cmd" (
        echo [OK] Maven instalado em %MAVEN_DIR%
    ) else (
        echo [ERRO] Falha ao baixar/instalar o Maven automaticamente.
        echo        Baixe manualmente em:
        echo        https://archive.apache.org/dist/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.zip
        echo        Extraia o conteudo para: %MAVEN_DIR%
        set "MISSING_CRITICAL=true"
    )
)

REM ---------------------------------------------------------------------------
REM 1.3 JBoss EAP 7.0 - download automatico (mirror interno da equipe no
REM     GitHub, pois o download oficial exige login no Red Hat Customer
REM     Portal). O .zip baixado extrai para a pasta "jboss7.0-main", que e
REM     renomeada automaticamente para jboss-eap-7.0.
REM ---------------------------------------------------------------------------
if exist "%JBOSS_HOME%\bin\standalone.bat" (
    echo [OK] JBoss EAP 7.0 encontrado em %JBOSS_HOME%
) else (
    echo [FALTA] JBoss EAP 7.0 nao encontrado em %JBOSS_HOME%
    echo         Baixando automaticamente ^(mirror interno da equipe^)...
    call :DOWNLOAD_AND_EXTRACT ^
        "https://codeload.github.com/rafaelcn1dev/jboss7.0/zip/refs/heads/main" ^
        "%TEMP_DOWNLOAD%\jboss.zip" ^
        "%USERPROFILE%" ^
        "jboss7.0-main" ^
        "%JBOSS_HOME%"
    if exist "%JBOSS_HOME%\bin\standalone.bat" (
        echo [OK] JBoss EAP 7.0 instalado em %JBOSS_HOME%
    ) else (
        echo [ERRO] Falha ao baixar/instalar o JBoss automaticamente.
        echo        Baixe manualmente em:
        echo        https://codeload.github.com/rafaelcn1dev/jboss7.0/zip/refs/heads/main
        echo        Extraia o conteudo e renomeie a pasta para: %JBOSS_HOME%
        set "MISSING_CRITICAL=true"
    )
)

REM ---------------------------------------------------------------------------
REM 1.4 Modulos adicionais do JBoss (ex: driver JDBC do PostgreSQL, exigido
REM     pelos datasources XA definidos no standalone.xml). O JBoss EAP nao
REM     vem com o driver PostgreSQL por padrao; ele precisa ser instalado
REM     como MODULO NATIVO em modules\org\postgresql\main. O nome do modulo
REM     "org.postgresql" e resolvido pelo JBoss trocando os pontos por
REM     pastas -- colocar em modules\postgresql\main (sem o "org\") causa
REM     "ModuleNotFoundException: org.postgresql:main" no deploy do pje.war.
REM     Os modulos empacotados dentro deste .zip (pasta jboss-modules\) sao
REM     copiados preservando a MESMA estrutura de pastas usada dentro de
REM     %JBOSS_HOME%\modules (ex: jboss-modules\org\postgresql\main\...).
REM     Para adicionar outros modulos (ex: customizacoes do "jboss"), basta
REM     colocar a pasta correspondente dentro de jboss-modules\ usando o
REM     mesmo caminho relativo que ela teria dentro de %JBOSS_HOME%\modules.
REM ---------------------------------------------------------------------------
REM if exist "%JBOSS_HOME%\modules\org\postgresql\main\module.xml" (
REM echo [OK] Modulo org.postgresql encontrado em %JBOSS_HOME%\modules\org\postgresql\main
REM ) else (
REM     echo [FALTA] Modulo org.postgresql nao encontrado em %JBOSS_HOME%\modules\org\postgresql\main
REM     if exist "%SCRIPT_DIR%jboss-modules" (
REM         echo         Copiando modulos adicionais de dentro do pacote para %JBOSS_HOME%\modules...
REM xcopy /E /I /Y /Q "%SCRIPT_DIR%jboss-modules" "%JBOSS_HOME%\modules\" >nul
REM         if exist "%JBOSS_HOME%\modules\org\postgresql\main\module.xml" (
REM             echo [OK] Modulo org.postgresql instalado em %JBOSS_HOME%\modules\org\postgresql\main
REM         ) else (
REM             echo [ERRO] Falha ao copiar o modulo org.postgresql para %JBOSS_HOME%\modules
REM             set "MISSING_CRITICAL=true"
REM         )
REM     ) else (
REM         echo         Pasta "jboss-modules" nao encontrada dentro deste pacote.
REM         echo         Sem o driver JDBC do PostgreSQL instalado como modulo, o
REM         echo         deploy do pje.war falha com:
REM         echo         ModuleNotFoundException: org.postgresql:main
REM         set "MISSING_CRITICAL=true"
REM     )
REM )

REM ---------------------------------------------------------------------------
REM 1.5 Configuracoes de JCR (armazenamento de documentos/binarios) - arquivos
REM     internos do TJPE. Vem empacotados dentro deste .zip (pastas
REM     jcr-config/ e pje-binarios-config/), entao sao copiados
REM     automaticamente se ainda nao existirem na maquina.
REM ---------------------------------------------------------------------------
if exist "%JCR_DIR%\jcr-storage.local.properties" (
    echo [OK] Configuracao JCR ^(1G^) encontrada em %JCR_DIR%
) else (
    echo [FALTA] Configuracao JCR ^(1G^) nao encontrada em %JCR_DIR%
    if exist "%SCRIPT_DIR%jcr-config" (
        echo         Copiando de dentro do pacote para %JCR_DIR%...
        xcopy /E /I /Y /Q "%SCRIPT_DIR%jcr-config" "%JCR_DIR%\" >nul
        if exist "%JCR_DIR%\jcr-storage.local.properties" (
            echo [OK] Configuracao JCR ^(1G^) copiada para %JCR_DIR%
        ) else (
            echo [ERRO] Falha ao copiar a configuracao JCR ^(1G^) para %JCR_DIR%
            set "MISSING_CRITICAL=true"
        )
    ) else (
        echo         Pasta "jcr-config" nao encontrada dentro deste pacote.
        echo         Solicite o conteudo/pasta "jcr" a algum colega da equipe
        echo         e copie para: %JCR_DIR%
        set "MISSING_CRITICAL=true"
    )
)

if exist "%PJE_BINARIOS_DIR%\pjebinarios-storage.properties" (
    echo [OK] Configuracao JCR ^(2G^) encontrada em %PJE_BINARIOS_DIR%
) else (
    echo [FALTA] Configuracao JCR ^(2G^) nao encontrada em %PJE_BINARIOS_DIR%
    if exist "%SCRIPT_DIR%pje-binarios-config" (
        echo         Copiando de dentro do pacote para %PJE_BINARIOS_DIR%...
        xcopy /E /I /Y /Q "%SCRIPT_DIR%pje-binarios-config" "%PJE_BINARIOS_DIR%\" >nul
        if exist "%PJE_BINARIOS_DIR%\pjebinarios-storage.properties" (
            echo [OK] Configuracao JCR ^(2G^) copiada para %PJE_BINARIOS_DIR%
        ) else (
            echo [ERRO] Falha ao copiar a configuracao JCR ^(2G^) para %PJE_BINARIOS_DIR%
            set "MISSING_CRITICAL=true"
        )
    ) else (
        echo         Pasta "pje-binarios-config" nao encontrada dentro deste pacote.
        echo         Solicite o conteudo/pasta "pje-binarios" a algum colega da
        echo         equipe e copie para: %PJE_BINARIOS_DIR%
        set "MISSING_CRITICAL=true"
    )
)

if exist "%TEMP_DOWNLOAD%" rmdir /s /q "%TEMP_DOWNLOAD%" 2>nul

echo.
if "%MISSING_CRITICAL%"=="true" (
    echo =========================================
    echo  Ambiente incompleto. Resolva os itens [FALTA]/[ERRO] acima
    echo  e execute este instalador novamente.
    echo =========================================
    pause
    exit /b 1
)

echo [OK] Todas as ferramentas necessarias estao presentes.
echo.

REM =============================================================================
REM ETAPA 2 - Copiar configuracoes do JBoss e arquivos do projeto
REM =============================================================================
echo [ETAPA 2/2] Copiando configuracoes...
echo.

REM ---------------------------------------------------------------------------
REM Perguntar o caminho do repositorio do projeto PJe
REM ---------------------------------------------------------------------------
set "PROJECT_DIR="
set /p PROJECT_DIR="Informe o caminho completo do repositorio do PJe (ex: C:\dev\pje): "

REM Remove aspas e espacos acidentais (inicio/fim) do caminho informado
set "PROJECT_DIR=%PROJECT_DIR:"=%"
for /f "tokens=* delims= " %%A in ("%PROJECT_DIR%") do set "PROJECT_DIR=%%A"
:TRIM_TRAILING_SPACE
if "%PROJECT_DIR:~-1%"==" " (
    set "PROJECT_DIR=%PROJECT_DIR:~0,-1%"
    goto :TRIM_TRAILING_SPACE
)

if not exist "%PROJECT_DIR%" (
    echo [ERRO] Diretorio do projeto nao encontrado: %PROJECT_DIR%
    pause
    exit /b 1
)

echo.
echo  Repositorio do projeto: %PROJECT_DIR%
echo =========================================
echo.

REM ---------------------------------------------------------------------------
REM 2.1 Copiar arquivos de configuracao do JBoss (1G)
REM ---------------------------------------------------------------------------
echo [1/4] Copiando configuracao do JBoss 1G...

if not exist "%JBOSS_HOME%\standalone\configuration" mkdir "%JBOSS_HOME%\standalone\configuration"

copy /y "%SCRIPT_DIR%jboss-config\bin\standalone.bat" "%JBOSS_HOME%\bin\standalone.bat" >nul
copy /y "%SCRIPT_DIR%jboss-config\bin\standalone.conf.bat" "%JBOSS_HOME%\bin\standalone.conf.bat" >nul
copy /y "%SCRIPT_DIR%jboss-config\standalone\configuration\standalone.xml" "%JBOSS_HOME%\standalone\configuration\standalone.xml" >nul
copy /y "%SCRIPT_DIR%jboss-config\standalone\configuration\logging.properties" "%JBOSS_HOME%\standalone\configuration\logging.properties" >nul

echo       OK - 1G configurado.

REM ---------------------------------------------------------------------------
REM 2.2 Copiar arquivos de configuracao do JBoss (2G)
REM ---------------------------------------------------------------------------
echo [2/4] Copiando configuracao do JBoss 2G...

if not exist "%JBOSS_HOME%\standalone2\configuration" mkdir "%JBOSS_HOME%\standalone2\configuration"

copy /y "%SCRIPT_DIR%jboss-config\bin\standalone2.bat" "%JBOSS_HOME%\bin\standalone2.bat" >nul
copy /y "%SCRIPT_DIR%jboss-config\bin\standalone2.conf.bat" "%JBOSS_HOME%\bin\standalone2.conf.bat" >nul
copy /y "%SCRIPT_DIR%jboss-config\standalone2\configuration\standalone.xml" "%JBOSS_HOME%\standalone2\configuration\standalone.xml" >nul
copy /y "%SCRIPT_DIR%jboss-config\standalone2\configuration\logging.properties" "%JBOSS_HOME%\standalone2\configuration\logging.properties" >nul

echo       OK - 2G configurado.

REM ---------------------------------------------------------------------------
REM 2.3 Copiar documentacao do projeto
REM ---------------------------------------------------------------------------
echo [3/4] Copiando documentacao...

copy /y "%SCRIPT_DIR%project-files\SETUP-AMBIENTE-DEV.md" "%PROJECT_DIR%\SETUP-AMBIENTE-DEV.md" >nul

echo       OK - documentacao copiada.

REM ---------------------------------------------------------------------------
REM 2.4 Copiar configuracoes do VS Code / Kiro (.vscode), incluindo o
REM     start-dev.bat e seu script auxiliar fix-logging-abspath.vbs
REM ---------------------------------------------------------------------------
echo [4/4] Copiando configuracoes do Kiro/VS Code (.vscode)...

if not exist "%PROJECT_DIR%\.vscode" mkdir "%PROJECT_DIR%\.vscode"

copy /y "%SCRIPT_DIR%project-files\.vscode\start-dev.bat" "%PROJECT_DIR%\.vscode\start-dev.bat" >nul
copy /y "%SCRIPT_DIR%project-files\.vscode\fix-logging-abspath.vbs" "%PROJECT_DIR%\.vscode\fix-logging-abspath.vbs" >nul
copy /y "%SCRIPT_DIR%project-files\.vscode\tasks.json" "%PROJECT_DIR%\.vscode\tasks.json" >nul
copy /y "%SCRIPT_DIR%project-files\.vscode\launch.json" "%PROJECT_DIR%\.vscode\launch.json" >nul

echo       OK - start-dev.bat, fix-logging-abspath.vbs, tasks.json e launch.json copiados.

echo.
echo =========================================
echo  Instalacao concluida!
echo =========================================
echo.
echo  Proximos passos:
echo   1. Compile o projeto:
echo      cd %PROJECT_DIR%
echo      mvn clean package -DskipTests
echo   2. No Kiro, abra o painel Run and Debug (Ctrl+Shift+D) e escolha
echo      "PJe Debug 1G" ou "PJe Debug 2G".
echo.
echo  Leia %PROJECT_DIR%\SETUP-AMBIENTE-DEV.md para mais detalhes.
echo =========================================

pause
exit /b 0

REM =============================================================================
REM :DOWNLOAD_AND_EXTRACT <url> <zip_dest> <extract_root> <expected_folder_name> <final_dir>
REM Baixa um .zip via PowerShell, extrai em extract_root e garante que o
REM resultado final fique em final_dir (renomeia se o nome dentro do zip for
REM diferente do esperado).
REM =============================================================================
:DOWNLOAD_AND_EXTRACT
set "DL_URL=%~1"
set "DL_ZIP=%~2"
set "DL_ROOT=%~3"
set "DL_EXPECTED=%~4"
set "DL_FINAL=%~5"

if not exist "%TEMP_DOWNLOAD%" mkdir "%TEMP_DOWNLOAD%"

REM Pasta de extracao temporaria dedicada e limpa (evita conflito com
REM Expand-Archive -Force, que tem bug conhecido de "Remove-Item" falhar
REM quando o antivirus trava arquivos recem-extraidos).
set "DL_EXTRACT_TMP=%TEMP_DOWNLOAD%\extract_%DL_EXPECTED%"
if exist "%DL_EXTRACT_TMP%" rmdir /s /q "%DL_EXTRACT_TMP%" 2>nul
mkdir "%DL_EXTRACT_TMP%"

powershell -NoProfile -Command ^
    "try { Invoke-WebRequest -Uri '%DL_URL%' -OutFile '%DL_ZIP%' -UseBasicParsing; exit 0 } catch { Write-Output ('[ERRO] Download falhou: ' + $_.Exception.Message); exit 1 }"

if not exist "%DL_ZIP%" (
    echo [ERRO] Arquivo nao foi baixado: %DL_ZIP%
    goto :EOF
)

REM Extracao via .NET ZipFile (mais confiavel que Expand-Archive -Force,
REM que costuma falhar com "Remove-Item: Nao e possivel localizar o
REM caminho" quando o antivirus bloqueia temporariamente algum arquivo).
powershell -NoProfile -Command ^
    "try { Add-Type -AssemblyName System.IO.Compression.FileSystem; [System.IO.Compression.ZipFile]::ExtractToDirectory('%DL_ZIP%', '%DL_EXTRACT_TMP%'); exit 0 } catch { Write-Output ('[ERRO] Extracao falhou: ' + $_.Exception.Message); exit 1 }"

if /i not "%DL_EXTRACT_TMP%\%DL_EXPECTED%"=="%DL_FINAL%" (
    if exist "%DL_EXTRACT_TMP%\%DL_EXPECTED%" (
        if exist "%DL_FINAL%" rmdir /s /q "%DL_FINAL%"
        move "%DL_EXTRACT_TMP%\%DL_EXPECTED%" "%DL_FINAL%" >nul
    ) else (
        REM Alguns zips nao tem uma pasta-raiz com o nome esperado
        REM (o conteudo fica direto na raiz do zip). Nesse caso, move a
        REM propria pasta de extracao para o destino final.
        if exist "%DL_FINAL%" rmdir /s /q "%DL_FINAL%"
        move "%DL_EXTRACT_TMP%" "%DL_FINAL%" >nul
    )
)

rmdir /s /q "%DL_EXTRACT_TMP%" 2>nul
del "%DL_ZIP%" 2>nul
goto :EOF
