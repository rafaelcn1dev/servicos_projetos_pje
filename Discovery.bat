@echo off
set "SCRIPT_DIR=%~dp0"

cd %SCRIPT_DIR%

echo "Iniciando o discovery"
call "1.1 run discovery.bat"
