@echo off
set "SCRIPT_DIR=%~dp0"

cd %SCRIPT_DIR%jcr\apache-tomcat-7.0.59\bin
catalina run
pause