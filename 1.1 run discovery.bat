@echo off

call "0.1 init.bat"

echo Executando discovery...
cd .\discovery
%MVN_CMD% exec:java