@echo off
set "SCRIPT_DIR=%~dp0"

start wt -w "MinhaJanela" ^
  nt --title "JCR" -p "Prompt de Comando" cmd /k "%SCRIPT_DIR%jcr-start.bat" ^
  ; nt --title "Gateway" -p "Prompt de Comando" cmd /k "%SCRIPT_DIR%Gateway.bat" ^
  ; nt --title "Discovery" -p "Prompt de Comando" cmd /k "%SCRIPT_DIR%Discovery.bat" ^
  ; nt --title "PJE Front" -p "Prompt de Comando" cmd /k "cd /d C:\dev\pje2-web && echo 'Nao esquecer de verificar o local onde esta o projeto do front-end! Ex:C:\dev\pje2-web' && ng serve --host=0.0.0.0 --disable-host-check" ^
  ; nt --title "Podman RabbitMQ" -p "Prompt de Comando" cmd /k "timeout /t 60 /nobreak && podman run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 docker.io/library/rabbitmq:3.11-management"
