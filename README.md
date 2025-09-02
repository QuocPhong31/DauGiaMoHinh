DauGiaMoHinh - Website đấu giá mô hình trực tuyến
---
📚 Giới thiệu
---
DauGiaMoHinh là hệ thống giúp người dùng tham gia vào các phiên đấu giá, nơi họ có thể đặt giá cho các mô hình sưu tầm. Người tham gia có thể xem các phiên đấu giá, theo dõi tình trạng đấu giá, và thanh toán khi chiến thắng.
***
✨ Đây là dự án nhằm rèn luyện kỹ năng **Fullstack với Spring MVC & ReactJS**.
---

## ✨ Tính năng nổi bật

### 👤 Người dùng
- Đăng ký / Đăng nhập tài khoản
- Tham gia các phiên đấu giá
- Đặt giá cho sản phẩm
- Xem lịch sử đấu giá và kết quả
- Theo dõi trạng thái thanh toán

### 🔑 Quản trị (Admin)
- Quản lý người dùng và phiên đấu giá
- Duyệt và quản lý các sản phẩm trong đấu giá
- Quản lý lịch sử và trạng thái thanh toán

---

## ⚙️ Công nghệ sử dụng
- **Frontend:** ReactJS
- **Backend:** Spring MVC, Spring Security (JWT)
- **Database:** MySQL
- **Khác:** Cloudinary (lưu trữ hình ảnh), Firebase (chat với mọi người)

---

## 📂 Cấu trúc dự án
```
DauGiaMoHinh/
│── backend/              # Spring MVC(REST API, Security, MySQL)
│   ├── src/main/java/    # Source code Java
│   ├── src/main/resources/
│   │   └── application.properties
│   └── pom.xml           # Maven build file
│
│── frontend/             # ReactJS (UI cho người dùng & admin)
│   ├── public/
│   ├── src/
│   └── package.json
│
│── database/             # File SQL khởi tạo CSDL
│   └── daugia.sql
│
└── README.md             # Tài liệu dự án
```
---

## 🚀 Hướng dẫn cài đặt

### 🔹 Backend (Spring Boot)

**1. Clone project:**
```bash
git clone https://github.com/QuocPhong31/DauGiaMoHinh.git
```

**2. Tạo database trong MySQL:**
```sql
CREATE DATABASE daugiadb;
```

**3. Cấu hình MySQL trong `src/main/resources/application.properties`:**
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/daugiadb
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

**4. Chạy project Spring Boot:**
```bash
mvn spring-boot:run
```

👉 **Server chạy tại:** http://localhost:8080

### 🔹 Frontend (ReactJS)

**1. Vào thư mục frontend:**
```bash
cd ../daugiaweb
```

**2. Cài đặt dependencies:**
```bash
npm install
```

**3. Chạy ứng dụng React:**
```bash
npm start
```

👉 **Giao diện chạy tại:** http://localhost:3000

---

## 👤 Tài khoản mẫu

Để test các tính năng của hệ thống, bạn có thể sử dụng tài khoản mẫu sau:

### 🔑 Admin
- **Tài khoản:** `admin`
- **Mật khẩu:** `123`

*Lưu ý: Đây là tài khoản demo, trong môi trường thực tế nên thay đổi mật khẩu mạnh hơn.*

---

## 🎯 Demo

### Giao diện người dùng
- Trang chủ đấu giá
- Đặt giá cho sản phẩm
- Theo dõi kết quả đấu giá

### Giao diện quản trị
- Dashboard quản lý phiên đấu giá
- Quản lý người dùng và sản phẩm
- Quản lý lịch sử và trạng thái thanh toán

---

## 📞 Liên hệ
👨‍💻 **Tác giả:** tranquocphong  
🔗 **GitHub:** https://github.com/QuocPhong31
📧 **Email:** tqphong2004@gmail.com

---

⭐ **Nếu bạn thấy dự án hữu ích, hãy cho một star nhé!** ⭐
