@echo off
echo Starting XO Game Services...
echo.

echo Starting Backend (Spring Boot)...
start "XO Backend" cmd /k "powershell -ExecutionPolicy Bypass -Command .\scripts\detect_set_java.ps1; .\scripts\install_maven_locally.ps1; mvn spring-boot:run"

echo Waiting 5 seconds...
timeout /t 5 /nobreak > nul

echo Starting Frontend (React)...
start "XO Frontend" cmd /k "cd frontend && npm start"

echo.
echo Services starting...
echo Backend: http://localhost:8080
echo Frontend: http://localhost:3000
echo Database: http://localhost:8080/h2-console
echo.
echo Please wait for both services to fully start.
echo.
pause
