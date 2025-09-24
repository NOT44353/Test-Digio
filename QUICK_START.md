# 🎮 XO Game - วิธีรันแบบเร็ว

## สถานะปัจจุบัน
- ✅ **Frontend กำลังรันอยู่!** - http://localhost:3000
- ❌ **Backend ยังรันไม่ได้** - ต้องติดตั้ง Java + Maven ก่อน

## วิธีรัน Frontend (รันอยู่แล้ว)
```bash
cd frontend
npm start
```
**เปิดที่:** http://localhost:3000

## วิธีรัน Backend (ต้องติดตั้งก่อน)

### ตัวเลือก 1: ติดตั้ง Java + Maven
1. **Java 17+**: https://adoptium.net/
2. **Maven**: https://maven.apache.org/download.cgi
3. **รัน:**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

### ตัวเลือก 2: ใช้ IDE
- **IntelliJ IDEA**: เปิดโฟลเดอร์ → รัน XoGameApplication.java
- **VS Code**: ติดตั้ง Java Extension Pack → รัน
- **Eclipse**: Import Maven Project → รัน

## ฟีเจอร์ที่พร้อมใช้
- 🎯 กระดานขนาดต่างๆ (3x3, 4x4, 5x5, 6x6)
- 🤖 AI ฝ่ายตรงข้าม (Minimax Algorithm)
- 📚 ประวัติเกมและ Replay
- 🎨 UI สวยงามแบบ Glassmorphism
- 📱 Responsive Design

## หลังจาก Backend รันได้
1. **Backend**: http://localhost:8080
2. **Frontend**: http://localhost:3000
3. **Database**: http://localhost:8080/h2-console

## การใช้งาน
1. เลือกขนาดกระดาน
2. เลือก Human/AI สำหรับแต่ละฝ่าย
3. กด "Start Game"
4. คลิกช่องเพื่อเดินหมาก
5. AI จะเดินอัตโนมัติ (ถ้าเลือก AI)

**Frontend พร้อมใช้งานแล้ว!** 🚀
