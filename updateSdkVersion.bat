@echo off

REM ===== QuoteX Install ====
REM This script checks the version of the model skd and public API before bootstrapping a model

:switchTo
if "%1%" == "" (
	goto :checkVersion
) else if "%1%" == "true" (
    goto :updateVersion
) else (
    goto :checkVersion
)

:checkVersion
call gradlew.bat checkSdkVersion
exit /B 0

:updateVersion
call gradlew.bat updateSdkVersion
call gradlew.bat eclipse
exit /B 0
