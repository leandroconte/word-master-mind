@rem
@rem Copyright 2015 the original author or authors.
@rem
@rem Licensed under the Apache License, Version 2.0 (the "License");
@rem you may not use this file except in compliance with the License.
@rem You may obtain a copy of the License at
@rem
@rem      https://www.apache.org/licenses/LICENSE-2.0
@rem
@rem Unless required by applicable law or agreed to in writing, software
@rem distributed under the License is distributed on an "AS IS" BASIS,
@rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@rem See the License for the specific language governing permissions and
@rem limitations under the License.
@rem

@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  word-master-mind startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Resolve any "." and ".." in APP_HOME to make it shorter.
for %%i in ("%APP_HOME%") do set APP_HOME=%%~fi

@rem Add default JVM options here. You can also use JAVA_OPTS and WORD_MASTER_MIND_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS="-Dio.ktor.development=false"

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto execute

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto execute

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\word-master-mind-0.0.1.jar;%APP_HOME%\lib\ktor-gson-jvm-1.6.8.jar;%APP_HOME%\lib\ktor-server-cio-jvm-1.6.8.jar;%APP_HOME%\lib\ktor-server-host-common-jvm-1.6.8.jar;%APP_HOME%\lib\ktor-server-core-jvm-1.6.8.jar;%APP_HOME%\lib\exposed-dao-0.37.3.jar;%APP_HOME%\lib\exposed-jdbc-0.37.3.jar;%APP_HOME%\lib\exposed-java-time-0.37.3.jar;%APP_HOME%\lib\kodein-di-jvm-7.11.0.jar;%APP_HOME%\lib\exposed-core-0.37.3.jar;%APP_HOME%\lib\ktor-http-cio-jvm-1.6.8.jar;%APP_HOME%\lib\ktor-http-jvm-1.6.8.jar;%APP_HOME%\lib\ktor-network-jvm-1.6.8.jar;%APP_HOME%\lib\ktor-utils-jvm-1.6.8.jar;%APP_HOME%\lib\kotlinx-coroutines-jdk8-1.6.0.jar;%APP_HOME%\lib\ktor-io-jvm-1.6.8.jar;%APP_HOME%\lib\kotlinx-coroutines-core-jvm-1.6.0.jar;%APP_HOME%\lib\kodein-type-jvm-1.12.0.jar;%APP_HOME%\lib\kotlin-stdlib-jdk8-1.6.10.jar;%APP_HOME%\lib\logback-classic-1.2.11.jar;%APP_HOME%\lib\HikariCP-5.0.1.jar;%APP_HOME%\lib\postgresql-42.2.2.jar;%APP_HOME%\lib\kotlin-stdlib-jdk7-1.6.10.jar;%APP_HOME%\lib\kotlin-reflect-1.6.10.jar;%APP_HOME%\lib\kotlin-stdlib-1.6.10.jar;%APP_HOME%\lib\slf4j-api-1.7.36.jar;%APP_HOME%\lib\config-1.4.1.jar;%APP_HOME%\lib\jansi-2.4.0.jar;%APP_HOME%\lib\gson-2.9.0.jar;%APP_HOME%\lib\logback-core-1.2.11.jar;%APP_HOME%\lib\annotations-13.0.jar;%APP_HOME%\lib\kotlin-stdlib-common-1.6.10.jar


@rem Execute word-master-mind
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %WORD_MASTER_MIND_OPTS%  -classpath "%CLASSPATH%" br.com.leandro.mastermind.ApplicationKt %*

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable WORD_MASTER_MIND_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%WORD_MASTER_MIND_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
