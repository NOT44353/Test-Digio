@echo off
echo ========================================
echo    XO Game - Starting All Services
echo ========================================
echo.

echo Starting Frontend (React)...
start "XO Game Frontend" cmd /k "cd frontend && npm install && npm start"

echo.
echo Waiting 5 seconds before starting backend...
timeout /t 5 /nobreak > nul

echo.
echo Starting Backend (Spring Boot)...
echo Note: Backend requires Java 17+ and Maven
echo If you don't have them installed, please install from:
echo - Java: https://adoptium.net/
echo - Maven: https://maven.apache.org/download.cgi
echo.

start "XO Game Backend" cmd /k "mvn clean install && mvn spring-boot:run"

echo.
echo ========================================
echo    Services Starting...
echo ========================================
echo.
echo Frontend: http://localhost:3000
echo Backend:  http://localhost:8080
echo Database: http://localhost:8080/h2-console
echo.
echo If backend fails to start, please install Java and Maven first.
echo.
pause
