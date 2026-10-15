@echo off
title Criminal
cd /d C:\dev\criminal
call mvn clean package -DskipTests || (echo Build falhou. & exit /b 1)

set /a n=0
:wait
set /a n+=1
if %n% gtr 60 (echo RabbitMQ nao ficou pronto. & exit /b 1)
curl -s -o NUL -w "%%{http_code}" -u pje:pje http://localhost:15672/api/vhosts/pje-dev | findstr /b 200 >nul || (timeout /t 3 /nobreak >nul & goto wait)

bash run.sh
