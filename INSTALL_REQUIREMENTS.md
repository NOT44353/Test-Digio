# การติดตั้ง Java และ Maven สำหรับ XO Game

## สถานะปัจจุบัน
- ✅ Node.js v24.6.0 - พร้อมใช้งาน
- ❌ Java - ยังไม่ได้ติดตั้ง
- ❌ Maven - ยังไม่ได้ติดตั้ง

## ขั้นตอนการติดตั้ง

### 1. ติดตั้ง Java 17+
1. ไปที่ https://adoptium.net/
2. ดาวน์โหลด Java 17 LTS หรือใหม่กว่า
3. ติดตั้งและเพิ่มใน PATH
4. ตรวจสอบ: `java -version`

### 2. ติดตั้ง Maven
1. ไปที่ https://maven.apache.org/download.cgi
2. ดาวน์โหลด Binary zip archive
3. แตกไฟล์ไปที่ C:\apache-maven-3.x.x
4. เพิ่ม C:\apache-maven-3.x.x\bin ใน PATH
5. ตรวจสอบ: `mvn -version`

### 3. รันโปรแกรม
หลังจากติดตั้งเสร็จ:

**Backend (Terminal 1):**
```bash
cd "C:\Users\Tanapat\Desktop\digio test"
mvn clean install
mvn spring-boot:run
```

**Frontend (Terminal 2):**
```bash
cd "C:\Users\Tanapat\Desktop\digio test\frontend"
npm start
```

## ทางเลือก: ใช้ IDE
- **IntelliJ IDEA**: เปิดโฟลเดอร์โปรเจกต์ แล้วรัน XoGameApplication.java
- **VS Code**: ติดตั้ง Extension Pack for Java แล้วรัน
- **Eclipse**: Import as Maven project

## ตรวจสอบการติดตั้ง
```bash
java -version    # ควรแสดง Java 17+
mvn -version     # ควรแสดง Maven 3.6+
node -version    # ควรแสดง Node.js 16+
```

## หลังจากติดตั้งเสร็จ
1. Backend: http://localhost:8080
2. Frontend: http://localhost:3000
3. H2 Database: http://localhost:8080/h2-console

## ฟีเจอร์ที่พร้อมใช้งาน
- ✅ กระดานขนาดต่างๆ (3x3, 4x4, 5x5, 6x6)
- ✅ AI ฝ่ายตรงข้าม (Minimax Algorithm)
- ✅ ประวัติเกมและ Replay
- ✅ UI สวยงามแบบ Glassmorphism
- ✅ Responsive Design

**Frontend กำลังรันอยู่แล้ว!** 🎮
