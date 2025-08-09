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
-- Table structure for table `loaisanpham`
--

DROP TABLE IF EXISTS `loaisanpham`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `loaisanpham` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nguoiDung_id` int DEFAULT NULL,
  `tenLoai` varchar(50) NOT NULL,
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
INSERT INTO `loaisanpham` VALUES (1,1,'Scale Figure'),(2,1,'Nendoroid'),(3,1,'gameprize');
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nguoidungs`
--

LOCK TABLES `nguoidungs` WRITE;
/*!40000 ALTER TABLE `nguoidungs` DISABLE KEYS */;
INSERT INTO `nguoidungs` VALUES (1,'admin','$2a$10$Af7DHT4moV4hfhQk.Y7DFu6ACjD5mgzXPMgs3Bjf0qKUqtx41yOhO','admin@gmail.com','Quản Trị Viên','0123456789','TP.HCM','ROLE_ADMIN',NULL,'2025-08-09 11:59:04','DUOC_DUYET'),(2,'phong','$2a$10$iaZhijaLC23NNu/VbR6lROtD4pKA2MKqFe2OKkqzERgcZoXXhxGka','tqphong2004@gmail.com','Trần Quốc Phong','0785643424','23 Phan Văn Trị, Phường Bình Lợi Trung, TP.HCM','ROLE_NGUOIBAN','https://res.cloudinary.com/dp4fipzce/image/upload/v1754740916/inbibzpw4mqbfrr01oww.jpg','2025-08-09 12:01:49','DUOC_DUYET'),(3,'khoi','$2a$10$kog/4qhwTsiEQhTa5Nq.AuY4sXHJ3Sx3f.XnhkIg1RA8LxloljmTu','nxk02032004@gmail.com','Nguyễn Đăng Khôi','0785643476','23 Trần Hưng Đạo, Phường Bến Thành, TP.HCM','ROLE_NGUOIBAN','https://res.cloudinary.com/dp4fipzce/image/upload/v1754740943/cqmak60q24g86rk2b8bg.jpg','2025-08-09 12:02:19','DUOC_DUYET'),(4,'sang','$2a$10$.93ZVLi66CQhEBBI6jg/l.x3VHPLfkkQnr6GuavHXTdd3hCRjr3aC','nxk3333333@gmail.com','Trần Huỳnh Sang','0797654222','24/23/1 Lê Văn Lương, Huyện Nhà Bè, TP.HCM','ROLE_NGUOIMUA','https://res.cloudinary.com/dp4fipzce/image/upload/v1754742569/tk3nfwvxwrt2vvujqzsb.jpg','2025-08-09 12:29:08','DUOC_DUYET');
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
  `sanPham_id` int DEFAULT NULL,
  `thoiGianBatDau` datetime DEFAULT NULL,
  `thoiGianKetThuc` datetime DEFAULT NULL,
  `trangThai` enum('dang_dien_ra','da_ket_thuc') DEFAULT 'dang_dien_ra',
  `giaChot` decimal(10,2) DEFAULT NULL,
  `nguoiThangDauGia_id` int DEFAULT NULL,
  `daThongBaoKQ` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `sanPham_id` (`sanPham_id`),
  KEY `nguoiThangDauGia_id` (`nguoiThangDauGia_id`),
  CONSTRAINT `phiendaugia_ibfk_1` FOREIGN KEY (`sanPham_id`) REFERENCES `sanphams` (`id`),
  CONSTRAINT `phiendaugia_ibfk_2` FOREIGN KEY (`nguoiThangDauGia_id`) REFERENCES `nguoidungs` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phiendaugia`
--

LOCK TABLES `phiendaugia` WRITE;
/*!40000 ALTER TABLE `phiendaugia` DISABLE KEYS */;
INSERT INTO `phiendaugia` VALUES (1,1,'2025-08-09 19:06:41','2025-08-09 22:30:00','dang_dien_ra',NULL,NULL,0),(2,2,'2025-08-09 19:15:22','2025-08-09 22:30:00','dang_dien_ra',NULL,NULL,0);
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phiendaugia_nguoidung`
--

LOCK TABLES `phiendaugia_nguoidung` WRITE;
/*!40000 ALTER TABLE `phiendaugia_nguoidung` DISABLE KEYS */;
INSERT INTO `phiendaugia_nguoidung` VALUES (1,1,3,500000.00,'2025-08-09 12:27:34'),(2,1,4,550000.00,'2025-08-09 12:30:11'),(3,2,4,220000.00,'2025-08-09 12:30:41'),(4,1,3,600000.00,'2025-08-09 13:31:02'),(5,1,4,650000.00,'2025-08-09 13:32:15');
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sanphams`
--

LOCK TABLES `sanphams` WRITE;
/*!40000 ALTER TABLE `sanphams` DISABLE KEYS */;
INSERT INTO `sanphams` VALUES (1,2,1,'APEX Azur Lane Prinz Rupprecht Gate Dragon\'s Advent Scale 1/7','newseal to đẹp có face, tay bonus ','https://res.cloudinary.com/dp4fipzce/image/upload/v1754741205/cetgfefv00firzsa6m6d.jpg',500000.00,50000.00,'2025-08-09 12:06:41','2025-08-09 22:30:00','DUYET'),(2,2,3,'Marin','real, new seal, box đẹp','https://res.cloudinary.com/dp4fipzce/image/upload/v1754741730/efdtmoyfiuoxczr67ycc.jpg',200000.00,20000.00,'2025-08-09 12:15:22','2025-08-09 22:30:00','DUYET');
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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-08-09 20:32:43
