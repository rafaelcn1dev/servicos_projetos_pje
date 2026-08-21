' =============================================================================
' fix-logging-abspath.vbs
' Grava no logging.properties o caminho ABSOLUTO real do arquivo de log.
' Chamado automaticamente pelo start-dev.bat a cada inicializacao, pois o
' placeholder ${jboss.server.log.dir} nao e resolvido pelo bootstrap de
' logging da JVM (carregado antes do JBoss processar os argumentos -D).
'
' Uso: cscript fix-logging-abspath.vbs "<logging.properties>" "<log-file-path>"
'
' NOTA: RegExp.Replace processa escapes de barra invertida na string de
' substituicao (ex: "\r" vira retorno de carro), o que corrompe caminhos
' Windows. Por isso usamos um placeholder sem barras no regex e depois um
' Replace() literal (nao-regex) para inserir o caminho real.
' =============================================================================

Dim targetFile, logPath
targetFile = WScript.Arguments(0)
logPath = WScript.Arguments(1)

Dim fso
Set fso = CreateObject("Scripting.FileSystemObject")

If Not fso.FileExists(targetFile) Then
    WScript.Quit 0
End If

Dim f, content, before
Set f = fso.OpenTextFile(targetFile, 1)
content = f.ReadAll
f.Close
before = content

Const PLACEHOLDER = "__LOGPATH_PLACEHOLDER__"

Dim re1
Set re1 = New RegExp
re1.Pattern = "handler\.FILE\.fileName=.*"
re1.Global = True
content = re1.Replace(content, "handler.FILE.fileName=" & PLACEHOLDER)

content = Replace(content, PLACEHOLDER, logPath)

If content <> before Then
    Set f = fso.OpenTextFile(targetFile, 2)
    f.Write content
    f.Close
End If
