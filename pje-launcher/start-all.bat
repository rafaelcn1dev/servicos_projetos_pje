@echo off
setlocal enabledelayedexpansion
set "SCRIPT_DIR=%~dp0"
set "WT="

call :ask "JCR" jcr-start.bat
call :ask "Gateway" Gateway.bat
call :ask "Discovery" Discovery.bat
call :ask "PJE Front" pje-front.bat

choice /c SN /n /m "Iniciar RabbitMQ? [S/N]: "
set "RABBIT=%errorlevel%"
choice /c SN /n /m "Iniciar Criminal? [S/N]: "
set "CRIMINAL=%errorlevel%"

if %CRIMINAL%==1 if %RABBIT%==2 (
  echo Criminal depende do RabbitMQ, iniciando-o tambem.
  set "RABBIT=1"
)
if %RABBIT%==1 call :add "RabbitMQ" rabbitmq-start.bat
if %CRIMINAL%==1 call :add "Criminal" criminal-start.bat

if not defined WT (
  echo Nada selecionado.
  exit /b
)
start "" wt -w "MinhaJanela" !WT!
exit /b

:ask
choice /c SN /n /m "Iniciar %~1? [S/N]: "
if errorlevel 2 exit /b
call :add %1 %2
exit /b

:add
if defined WT set "WT=!WT! ; "
set "WT=!WT!nt --title "%~1" -p "Prompt de Comando" cmd /k "%SCRIPT_DIR%%~2""
exit /b
