@echo off
REM =============================================================================
REM check-encoding.bat - PJe (TJPE)
REM =============================================================================
REM Verifica se os arquivos .sql de src/main/resources/migrations estao em
REM UTF-8 valido. O maven-resources-plugin (pje-comum/pom.xml) aplica
REM filtering nesses arquivos usando UTF-8 (project.build.sourceEncoding).
REM Se algum arquivo estiver salvo em outro encoding (ex: Windows-1252, com
REM bytes de acentuacao invalidos em UTF-8), o build falha com:
REM   MalformedInputException: Input length = 1
REM
REM Uso:
REM   check-encoding.bat          -> apenas verifica e lista os arquivos com problema
REM   check-encoding.bat /fix     -> alem de verificar, converte automaticamente
REM                                  os arquivos invalidos de Windows-1252 para UTF-8
REM                                  (mantem um backup .bak do original)
REM
REM NOTA: a logica fica embutida no proprio .bat (via powershell -Command)
REM em vez de um .ps1 separado, pois a politica de execucao de scripts
REM PowerShell pode estar restrita na maquina do desenvolvedor
REM (Restricted/AllSigned), o que bloquearia um arquivo .ps1 mesmo com
REM -ExecutionPolicy Bypass em alguns ambientes corporativos.
REM =============================================================================

setlocal

set "MIGRATIONS_DIR=%~dp0..\pje-comum\src\main\resources\migrations"
set "FIX_MODE=false"
if /i "%~1"=="/fix" set "FIX_MODE=true"

if not exist "%MIGRATIONS_DIR%" (
    echo [ERRO] Diretorio de migrations nao encontrado em:
    echo        %MIGRATIONS_DIR%
    exit /b 1
)

echo =========================================
echo  Verificando encoding dos arquivos .sql
echo  Diretorio: %MIGRATIONS_DIR%
echo =========================================
echo.

powershell -NoProfile -Command "$dir = '%MIGRATIONS_DIR%'; $fix = ('%FIX_MODE%' -eq 'true'); $bad = @(); $utf8 = New-Object System.Text.UTF8Encoding($false, $true); Get-ChildItem -Path $dir -Recurse -Filter *.sql | ForEach-Object { $file = $_.FullName; $name = $_.Name; $bytes = [System.IO.File]::ReadAllBytes($file); try { $utf8.GetString($bytes) | Out-Null } catch { $bad += $file; Write-Host ('[INVALIDO] ' + $file) -ForegroundColor Yellow; if ($fix) { Copy-Item $file ($file + '.bak') -Force; $content = [System.IO.File]::ReadAllText($file, [System.Text.Encoding]::GetEncoding(1252)); $utf8nobom = New-Object System.Text.UTF8Encoding($false); [System.IO.File]::WriteAllText($file, $content, $utf8nobom); Write-Host ('  -> Convertido para UTF-8 (backup em ' + $name + '.bak)') -ForegroundColor Green } } }; Write-Host ''; if ($bad.Count -eq 0) { Write-Host 'Nenhum arquivo com problema de encoding encontrado.' -ForegroundColor Green } else { Write-Host ($bad.Count.ToString() + ' arquivo(s) com problema de encoding.') -ForegroundColor Red; if (-not $fix) { Write-Host 'Execute check-encoding.bat /fix para corrigir automaticamente.' -ForegroundColor Red } }; exit $bad.Count"

set "PS_EXIT=%ERRORLEVEL%"
exit /b %PS_EXIT%
