@echo off
set "SCRIPT_DIR=%~dp0"

echo Configurando java, jboss e tomcat...
set PJE_HOME=C:\dev\pje
set JAVA_HOME=%USERPROFILE%\jdk8u232-b09
set JRE_HOME=%JAVA_HOME%\jre
set CLASSPATH=.;
set JBOSS_HOME=%USERPROFILE%\jboss-eap-7.0
set CATALINA_HOME=%SCRIPT_DIR%jcr\apache-tomcat-7.0.59
set MVN_HOME=%USERPROFILE%\apache-maven-3.6.3
set MVN_CMD=%MVN_HOME%\bin\mvn --settings %SCRIPT_DIR%settings.xml -Dsettings.security=%SCRIPT_DIR%settings-security.xml
set path=%JAVA_HOME%\bin;%path%