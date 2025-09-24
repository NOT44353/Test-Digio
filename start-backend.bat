@echo off
echo Starting XO Game Backend...
echo.

echo Checking Java installation...
java -version
if %errorlevel% neq 0 (
    echo.
    echo ERROR: Java not found!
    echo Please install Java 17+ from: https://adoptium.net/
    echo.
    pause
    exit /b 1
)

echo.
echo Checking Maven installation...
mvn -version
if %errorlevel% neq 0 (
    echo.
    echo ERROR: Maven not found!
    echo Please install Maven from: https://maven.apache.org/download.cgi
    echo.
    pause
    exit /b 1
)

echo.
echo Building and starting Spring Boot application...
echo Backend will be available at: http://localhost:8080
echo H2 Console: http://localhost:8080/h2-console
echo.

call mvn clean install
call mvn spring-boot:run
