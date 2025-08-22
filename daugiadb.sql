-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: daugiadb
-- ------------------------------------------------------
-- Server version	9.2.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `donthanhtoan_daugia`
--

DROP TABLE IF EXISTS `donthanhtoan_daugia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `donthanhtoan_daugia` (
  `id` int NOT NULL AUTO_INCREMENT,
  `phienDauGia_id` int NOT NULL,
  `nguoiMua_id` int NOT NULL,
  `soTien` decimal(10,2) NOT NULL,
  `trangThai` enum('PENDING','SELLER_REVIEW','PAID','CANCELLED') DEFAULT 'PENDING',
  `phuongThuc` enum('COD','BANK') DEFAULT 'COD',
  `hoTenNhan` varchar(100) DEFAULT NULL,
  `soDienThoai` varchar(20) DEFAULT NULL,
  `diaChiNhan` text,
  `ghiChu` text,
  `ngayTao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `ngayThanhToan` timestamp NULL DEFAULT NULL,
  `ngaySellerDuyet` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `phienDauGia_id` (`phienDauGia_id`),
  KEY `nguoiMua_id` (`nguoiMua_id`),
  CONSTRAINT `donthanhtoan_daugia_ibfk_1` FOREIGN KEY (`phienDauGia_id`) REFERENCES `phiendaugia` (`id`),
  CONSTRAINT `donthanhtoan_daugia_ibfk_2` FOREIGN KEY (`nguoiMua_id`) REFERENCES `nguoidungs` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `donthanhtoan_daugia`
--

LOCK TABLES `donthanhtoan_daugia` WRITE;
/*!40000 ALTER TABLE `donthanhtoan_daugia` DISABLE KEYS */;
INSERT INTO `donthanhtoan_daugia` VALUES (1,4,3,100000.00,'PENDING','COD',NULL,NULL,NULL,NULL,'2025-08-22 14:04:39',NULL,NULL);
/*!40000 ALTER TABLE `donthanhtoan_daugia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loaisanpham`
--

DROP TABLE IF EXISTS `loaisanpham`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `loaisanpham` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nguoiDung_id` int DEFAULT NULL,
  `tenLoai` varchar(50) NOT NULL,
  `trangThai` varchar(20) DEFAULT 'HOAT_DONG',
  PRIMARY KEY (`id`),
  KEY `nguoiDung_id` (`nguoiDung_id`),
  CONSTRAINT `loaisanpham_ibfk_1` FOREIGN KEY (`nguoiDung_id`) REFERENCES `nguoidungs` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loaisanpham`
--

LOCK TABLES `loaisanpham` WRITE;
/*!40000 ALTER TABLE `loaisanpham` DISABLE KEYS */;
INSERT INTO `loaisanpham` VALUES (1,1,'Scale Figure','HOAT_DONG'),(2,1,'gameprize','HOAT_DONG'),(3,1,'Nendoroid','HOAT_DONG');
/*!40000 ALTER TABLE `loaisanpham` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nguoidungs`
--

DROP TABLE IF EXISTS `nguoidungs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nguoidungs` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(100) NOT NULL,
  `hoTen` varchar(100) NOT NULL,
  `soDienThoai` varchar(20) NOT NULL,
  `diaChi` text NOT NULL,
  `vaiTro` enum('ROLE_ADMIN','ROLE_NGUOIBAN','ROLE_NGUOIMUA') DEFAULT 'ROLE_NGUOIMUA',
  `avatar` varchar(255) DEFAULT NULL,
  `ngayTao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `trangThai` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nguoidungs`
--

LOCK TABLES `nguoidungs` WRITE;
/*!40000 ALTER TABLE `nguoidungs` DISABLE KEYS */;
INSERT INTO `nguoidungs` VALUES (1,'admin','$2a$10$Af7DHT4moV4hfhQk.Y7DFu6ACjD5mgzXPMgs3Bjf0qKUqtx41yOhO','admin@gmail.com','Quản Trị Viên','0123456789','TP.HCM','ROLE_ADMIN',NULL,'2025-08-22 13:23:29','DUOC_DUYET'),(2,'phong','$2a$10$zGMbEcE0PM7UGbv7VwxCL.m.lDelZxeF0dBuB9zyEGY2hG4UCZJg.','tqphong2004@gmail.com','Trần Quốc Phong','0785643476','23 Phan Văn Trị, phường Bình Lợi Trung, TP.HCM','ROLE_NGUOIBAN','https://res.cloudinary.com/dp4fipzce/image/upload/v1755869131/vhxctqjo34qef7xhf8vz.jpg','2025-08-22 13:25:23','DUOC_DUYET'),(3,'khoi','$2a$10$O4pAKHfPYF1nmiY5iviSbO/4rndw88zr7FtDimUKKeiimQZmIv1ea','nxk02032004@gmail.com','Nguyễn Đăng Khôi','0785643424','34 Trần hưng Đạo, phường Bến Thành, TP.HCM','ROLE_NGUOIBAN','https://res.cloudinary.com/dp4fipzce/image/upload/v1755869173/kbmefhmlyddkrut2ywa4.jpg','2025-08-22 13:26:06','DUOC_DUYET');
/*!40000 ALTER TABLE `nguoidungs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phiendaugia`
--

DROP TABLE IF EXISTS `phiendaugia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phiendaugia` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nguoiDang_id` int DEFAULT NULL,
  `sanPham_id` int DEFAULT NULL,
  `thoiGianBatDau` datetime DEFAULT NULL,
  `thoiGianKetThuc` datetime DEFAULT NULL,
  `trangThai` enum('dang_dien_ra','da_ket_thuc') DEFAULT 'dang_dien_ra',
  `giaChot` decimal(10,2) DEFAULT NULL,
  `nguoiThangDauGia_id` int DEFAULT NULL,
  `daThongBaoKQ` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `nguoiDang_id` (`nguoiDang_id`),
  KEY `sanPham_id` (`sanPham_id`),
  KEY `nguoiThangDauGia_id` (`nguoiThangDauGia_id`),
  CONSTRAINT `phiendaugia_ibfk_1` FOREIGN KEY (`nguoiDang_id`) REFERENCES `nguoidungs` (`id`),
  CONSTRAINT `phiendaugia_ibfk_2` FOREIGN KEY (`sanPham_id`) REFERENCES `sanphams` (`id`),
  CONSTRAINT `phiendaugia_ibfk_3` FOREIGN KEY (`nguoiThangDauGia_id`) REFERENCES `nguoidungs` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phiendaugia`
--

LOCK TABLES `phiendaugia` WRITE;
/*!40000 ALTER TABLE `phiendaugia` DISABLE KEYS */;
INSERT INTO `phiendaugia` VALUES (1,2,1,'2025-08-22 20:33:43','2025-08-22 20:35:00','da_ket_thuc',550000.00,3,1),(2,2,2,'2025-08-22 20:50:31','2025-08-22 20:52:00','da_ket_thuc',240000.00,3,1),(3,2,3,'2025-08-22 20:58:04','2025-08-22 20:59:00','da_ket_thuc',100000.00,3,1),(4,2,4,'2025-08-22 21:02:25','2025-08-22 21:04:00','da_ket_thuc',100000.00,3,1);
/*!40000 ALTER TABLE `phiendaugia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phiendaugia_nguoidung`
--

DROP TABLE IF EXISTS `phiendaugia_nguoidung`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phiendaugia_nguoidung` (
  `id` int NOT NULL AUTO_INCREMENT,
  `phienDauGia_id` int DEFAULT NULL,
  `nguoiDung_id` int DEFAULT NULL,
  `giaDau` decimal(10,2) DEFAULT NULL,
  `thoiGianDauGia` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `phienDauGia_id` (`phienDauGia_id`),
  KEY `nguoiDung_id` (`nguoiDung_id`),
  CONSTRAINT `phiendaugia_nguoidung_ibfk_1` FOREIGN KEY (`phienDauGia_id`) REFERENCES `phiendaugia` (`id`),
  CONSTRAINT `phiendaugia_nguoidung_ibfk_2` FOREIGN KEY (`nguoiDung_id`) REFERENCES `nguoidungs` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phiendaugia_nguoidung`
--

LOCK TABLES `phiendaugia_nguoidung` WRITE;
/*!40000 ALTER TABLE `phiendaugia_nguoidung` DISABLE KEYS */;
INSERT INTO `phiendaugia_nguoidung` VALUES (1,1,3,550000.00,'2025-08-22 13:34:15'),(2,2,3,240000.00,'2025-08-22 13:50:56'),(3,3,3,100000.00,'2025-08-22 13:58:38'),(4,4,3,100000.00,'2025-08-22 14:03:00');
/*!40000 ALTER TABLE `phiendaugia_nguoidung` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sanphams`
--

DROP TABLE IF EXISTS `sanphams`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sanphams` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nguoiDung_id` int DEFAULT NULL,
  `loaiSanPham_id` int DEFAULT NULL,
  `tenSanPham` varchar(100) NOT NULL,
  `moTa` text NOT NULL,
  `hinhAnh` varchar(255) NOT NULL,
  `giaKhoiDiem` decimal(10,2) NOT NULL,
  `buocNhay` decimal(10,2) NOT NULL,
  `ngayDang` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `thoiGianKetThuc` datetime NOT NULL,
  `trangThai` enum('CHO_DUYET','DUYET','KHONG_DUYET') DEFAULT 'CHO_DUYET',
  PRIMARY KEY (`id`),
  KEY `nguoiDung_id` (`nguoiDung_id`),
  KEY `loaiSanPham_id` (`loaiSanPham_id`),
  CONSTRAINT `sanphams_ibfk_1` FOREIGN KEY (`nguoiDung_id`) REFERENCES `nguoidungs` (`id`),
  CONSTRAINT `sanphams_ibfk_2` FOREIGN KEY (`loaiSanPham_id`) REFERENCES `loaisanpham` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sanphams`
--

LOCK TABLES `sanphams` WRITE;
/*!40000 ALTER TABLE `sanphams` DISABLE KEYS */;
INSERT INTO `sanphams` VALUES (1,2,1,'APEX Azur Lane Prinz Rupprecht Gate Dragon\'s Advent Scale 1/7','real, box đẹp, đủ phụ kiện','https://res.cloudinary.com/dp4fipzce/image/upload/v1755869629/rdplu1q0yizn17nenshm.jpg',500000.00,50000.00,'2025-08-22 13:33:43','2025-08-22 20:35:00','DUYET'),(2,2,2,'marin','dsgdg','https://res.cloudinary.com/dp4fipzce/image/upload/v1755870639/zijebdkorgjp6aomzrnm.jpg',200000.00,20000.00,'2025-08-22 13:50:31','2025-08-22 20:52:00','DUYET'),(3,2,2,'rem','cscsc','https://res.cloudinary.com/dp4fipzce/image/upload/v1755871091/goxwigjc7hb7vmq87vyl.jpg',100000.00,20000.00,'2025-08-22 13:58:04','2025-08-22 20:59:00','DUYET'),(4,2,2,'marin','đẹp','https://res.cloudinary.com/dp4fipzce/image/upload/v1755871354/ckci7ufxwl18nacieur6.jpg',100000.00,20000.00,'2025-08-22 14:02:25','2025-08-22 21:04:00','DUYET');
/*!40000 ALTER TABLE `sanphams` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `theodoisanphams`
--

DROP TABLE IF EXISTS `theodoisanphams`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `theodoisanphams` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nguoiDung_id` int DEFAULT NULL,
  `phienDauGia_id` int DEFAULT NULL,
  `ngayTheoDoi` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nguoiDung_id` (`nguoiDung_id`,`phienDauGia_id`),
  KEY `phienDauGia_id` (`phienDauGia_id`),
  CONSTRAINT `theodoisanphams_ibfk_1` FOREIGN KEY (`nguoiDung_id`) REFERENCES `nguoidungs` (`id`),
  CONSTRAINT `theodoisanphams_ibfk_2` FOREIGN KEY (`phienDauGia_id`) REFERENCES `phiendaugia` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `theodoisanphams`
--

LOCK TABLES `theodoisanphams` WRITE;
/*!40000 ALTER TABLE `theodoisanphams` DISABLE KEYS */;
/*!40000 ALTER TABLE `theodoisanphams` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thongtintaikhoan`
--

DROP TABLE IF EXISTS `thongtintaikhoan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `thongtintaikhoan` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nguoiBan_id` int NOT NULL,
  `tenNguoiNhan` varchar(100) NOT NULL,
  `nganHang` varchar(100) NOT NULL,
  `soTaiKhoan` varchar(40) NOT NULL,
  `qrUrl` varchar(255) NOT NULL,
  `macDinh` tinyint DEFAULT '1',
  `ngayTao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `nguoiBan_id` (`nguoiBan_id`),
  CONSTRAINT `thongtintaikhoan_ibfk_1` FOREIGN KEY (`nguoiBan_id`) REFERENCES `nguoidungs` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thongtintaikhoan`
--

LOCK TABLES `thongtintaikhoan` WRITE;
/*!40000 ALTER TABLE `thongtintaikhoan` DISABLE KEYS */;
INSERT INTO `thongtintaikhoan` VALUES (1,2,'Trần Quốc Phong','VCB - Vietcombank','1051749090','https://res.cloudinary.com/dp4fipzce/image/upload/v1755869583/vhlfadcavwnf0jcuna2l.jpg',1,'2025-08-22 13:32:55');
/*!40000 ALTER TABLE `thongtintaikhoan` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-08-22 21:07:41
