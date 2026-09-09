@echo off
set "SCRIPT_DIR=%~dp0"

REM Garante que a pasta temp do Tomcat exista (evita o erro
REM "Cannot find specified temporary folder ...\temp" no startup).
if not exist "%USERPROFILE%\jcr\apache-tomcat-7.0.59\temp" mkdir "%USERPROFILE%\jcr\apache-tomcat-7.0.59\temp"

cd /d "%USERPROFILE%\jcr\apache-tomcat-7.0.59\bin"
catalina run
pause