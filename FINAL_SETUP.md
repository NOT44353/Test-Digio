# 🎮 XO Game - การตั้งค่าและรันโปรแกรม

## ✅ สถานะปัจจุบัน
- **Frontend**: กำลังรันอยู่ที่ http://localhost:3000
- **Backend**: ต้องติดตั้ง Java + Maven ก่อน

## 🚀 วิธีรันทั้งหมด

### วิธีที่ 1: รันทีละส่วน
```bash
# Terminal 1: Frontend
start-frontend.bat

# Terminal 2: Backend (ต้องมี Java + Maven)
start-backend.bat
```

### วิธีที่ 2: รันทั้งหมดพร้อมกัน
```bash
start-all.bat
```

### วิธีที่ 3: รันด้วย IDE
- **IntelliJ IDEA**: เปิดโฟลเดอร์ → รัน XoGameApplication.java
- **VS Code**: ติดตั้ง Java Extension Pack → รัน
- **Eclipse**: Import Maven Project → รัน

## 📋 ไฟล์ที่สร้างให้

### สคริปต์รัน
- `start-frontend.bat` - รัน Frontend เท่านั้น
- `start-backend.bat` - รัน Backend (ต้องมี Java + Maven)
- `start-all.bat` - รันทั้งหมดพร้อมกัน
- `run-without-maven.bat` - คำแนะนำวิธีรันโดยไม่ใช้ Maven

### เอกสาร
- `README.md` - เอกสารหลัก
- `QUICK_START.md` - วิธีรันแบบเร็ว
- `INSTALL_REQUIREMENTS.md` - วิธีติดตั้ง Java + Maven
- `GITHUB_SETUP.md` - วิธีอัปโหลดไป GitHub

## 🎯 ฟีเจอร์ที่พร้อมใช้

### Frontend (รันอยู่แล้ว)
- ✅ UI สวยงามแบบ Glassmorphism
- ✅ Responsive Design
- ✅ เลือกขนาดกระดาน (3x3, 4x4, 5x5, 6x6)
- ✅ เลือก Human/AI สำหรับแต่ละฝ่าย
- ✅ หน้าตั้งค่าเกม

### Backend (ต้องติดตั้ง Java + Maven)
- ✅ REST API สำหรับเกม
- ✅ AI ฝ่ายตรงข้าม (Minimax Algorithm)
- ✅ ฐานข้อมูล H2
- ✅ ประวัติเกมและ Replay
- ✅ เกมขนาดกระดานใดก็ได้

## 🔧 การแก้ไขปัญหา

### Frontend ไม่รัน
```bash
cd frontend
npm install
npm start
```

### Backend ไม่รัน
1. ติดตั้ง Java 17+: https://adoptium.net/
2. ติดตั้ง Maven: https://maven.apache.org/download.cgi
3. รัน: `mvn spring-boot:run`

### Port ซ้ำ
- Frontend: เปลี่ยนใน `frontend/package.json`
- Backend: เปลี่ยนใน `src/main/resources/application.properties`

## 🌐 URL ที่สำคัญ
- **Frontend**: http://localhost:3000
- **Backend**: http://localhost:8080
- **Database**: http://localhost:8080/h2-console
- **GitHub**: https://github.com/NOT44353/Test-Digio

## 🎮 วิธีใช้งาน
1. เปิด http://localhost:3000
2. เลือกขนาดกระดาน
3. เลือก Human/AI
4. กด "Start Game"
5. คลิกช่องเพื่อเดินหมาก
6. AI จะเดินอัตโนมัติ (ถ้าเลือก AI)

**Frontend พร้อมใช้งานแล้ว!** 🎉
