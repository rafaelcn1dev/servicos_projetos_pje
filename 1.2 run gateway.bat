@echo off

call "0.1 init.bat"

echo Executando gateway...
cd .\gateway
%MVN_CMD% exec:java