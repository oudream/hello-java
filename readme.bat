@echo off

set JAVA_HOME=F:\Java\jdk1.8

set CLASSPATH=%~dp0;%JAVA_HOME%\lib\tools.jar;%JAVA_HOME%\lib\dt.jar;
set PATH=%JAVA_HOME%\bin;%path%

cd /d %~dp0
