@echo off
echo ========================================
echo    XO Game - วิธีรันแบบไม่ต้องใช้ Maven
echo ========================================
echo.

echo 1. Frontend (React) - กำลังรันอยู่
echo    URL: http://localhost:3000
echo.

echo 2. Backend (Spring Boot) - ต้องใช้ Java
echo    วิธีรัน Backend:
echo.

echo    ตัวเลือก A: ติดตั้ง Java + Maven
echo    - Java 17+: https://adoptium.net/
echo    - Maven: https://maven.apache.org/download.cgi
echo    - รัน: mvn spring-boot:run
echo.

echo    ตัวเลือก B: ใช้ IDE
echo    - IntelliJ IDEA: เปิดโฟลเดอร์ → รัน XoGameApplication.java
echo    - VS Code: ติดตั้ง Java Extension Pack → รัน
echo    - Eclipse: Import Maven Project → รัน
echo.

echo    ตัวเลือก C: ใช้ Spring Boot CLI
echo    - ติดตั้ง Spring Boot CLI
echo    - รัน: spring run src/main/java/com/digio/xogame/XoGameApplication.java
echo.

echo 3. หลังจาก Backend รันได้
echo    - Backend: http://localhost:8080
echo    - Frontend: http://localhost:3000
echo    - Database: http://localhost:8080/h2-console
echo.

echo 4. ฟีเจอร์ที่พร้อมใช้
echo    - กระดานขนาดต่างๆ (3x3, 4x4, 5x5, 6x6)
echo    - AI ฝ่ายตรงข้าม (Minimax Algorithm)
echo    - ประวัติเกมและ Replay
echo    - UI สวยงามแบบ Glassmorphism
echo.

echo ========================================
echo    Frontend พร้อมใช้งานแล้ว!
echo    เปิด: http://localhost:3000
echo ========================================
echo.
pause
