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
  `tenLoai` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loaisanpham`
--

LOCK TABLES `loaisanpham` WRITE;
/*!40000 ALTER TABLE `loaisanpham` DISABLE KEYS */;
INSERT INTO `loaisanpham` VALUES (1,'Scale Figure'),(2,'Nendoroid'),(3,'gameprize');
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
INSERT INTO `nguoidungs` VALUES (1,'admin','$2a$10$Af7DHT4moV4hfhQk.Y7DFu6ACjD5mgzXPMgs3Bjf0qKUqtx41yOhO','admin@gmail.com','Quản Trị Viên','0123456789','TP.HCM','ROLE_ADMIN',NULL,'2025-08-03 03:41:02','DUOC_DUYET'),(3,'khoi','$2a$10$33jZmElH.cRopR0sFmhU4uXJGxKaHIW4uBTBdGtDZ0geDv68U6Qti','nxk02032004@gmail.com','Nguyễn Đăng Khôi','0785643424','32 Trần Hưng Đạo, phường Bến Thành, TP.HCM','ROLE_NGUOIBAN','https://res.cloudinary.com/dp4fipzce/image/upload/v1754194091/dzbkdao29lrw4safwvox.jpg','2025-08-03 04:08:07','DUOC_DUYET'),(4,'phong','$2a$10$LsraHMvQR9I9KS5Uo9zkM.qXEyX9jmyb9qxZ7M3EkAmFOSMqnrUEq','tqphong2004@gmail.com','Trần Quốc Phong','0785643476','228 Phan Văn Trị, Phường Bình Lợi Trung, TP.HCM','ROLE_NGUOIMUA','https://res.cloudinary.com/dp4fipzce/image/upload/v1754231030/grjgh2c8tb3srczu7vrm.jpg','2025-08-03 14:23:33','DUOC_DUYET');
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
INSERT INTO `phiendaugia` VALUES (1,2,'2025-08-03 16:01:14','2025-08-03 21:00:00','dang_dien_ra',NULL,NULL),(2,3,'2025-08-03 17:36:47','2025-08-03 22:00:00','dang_dien_ra',NULL,NULL);
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phiendaugia_nguoidung`
--

LOCK TABLES `phiendaugia_nguoidung` WRITE;
/*!40000 ALTER TABLE `phiendaugia_nguoidung` DISABLE KEYS */;
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
  `giaBua` decimal(10,2) DEFAULT NULL,
  `ngayDang` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `thoiGianKetThuc` datetime NOT NULL,
  `trangThai` enum('CHO_DUYET','DUYET','KHONG_DUYET') DEFAULT 'CHO_DUYET',
  PRIMARY KEY (`id`),
  KEY `nguoiDung_id` (`nguoiDung_id`),
  KEY `loaiSanPham_id` (`loaiSanPham_id`),
  CONSTRAINT `sanphams_ibfk_1` FOREIGN KEY (`nguoiDung_id`) REFERENCES `nguoidungs` (`id`),
  CONSTRAINT `sanphams_ibfk_2` FOREIGN KEY (`loaiSanPham_id`) REFERENCES `loaisanpham` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sanphams`
--

LOCK TABLES `sanphams` WRITE;
/*!40000 ALTER TABLE `sanphams` DISABLE KEYS */;
INSERT INTO `sanphams` VALUES (2,3,3,'Marin','real, new seal','https://res.cloudinary.com/dp4fipzce/image/upload/v1754211683/vwjuwho3d36praelujaa.jpg',200000.00,20000.00,400000.00,'2025-08-03 09:01:14','2025-08-03 21:00:00','DUYET'),(3,3,2,'Sakura Miku Bloomed in Japan','real, 2nd, đủ phụ kiện, box tàn.','https://res.cloudinary.com/dp4fipzce/image/upload/v1754217414/ml5lou8txsgt8mf3crza.jpg',700000.00,50000.00,1000000.00,'2025-08-03 10:36:47','2025-08-03 22:00:00','DUYET');
/*!40000 ALTER TABLE `sanphams` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `theodoisanpham`
--

DROP TABLE IF EXISTS `theodoisanpham`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `theodoisanpham` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nguoiDung_id` int DEFAULT NULL,
  `sanPham_id` int DEFAULT NULL,
  `ngayTheoDoi` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nguoiDung_id` (`nguoiDung_id`,`sanPham_id`),
  KEY `sanPham_id` (`sanPham_id`),
  CONSTRAINT `theodoisanpham_ibfk_1` FOREIGN KEY (`nguoiDung_id`) REFERENCES `nguoidungs` (`id`) ON DELETE CASCADE,
  CONSTRAINT `theodoisanpham_ibfk_2` FOREIGN KEY (`sanPham_id`) REFERENCES `sanphams` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `theodoisanpham`
--

LOCK TABLES `theodoisanpham` WRITE;
/*!40000 ALTER TABLE `theodoisanpham` DISABLE KEYS */;
/*!40000 ALTER TABLE `theodoisanpham` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-08-03 21:25:24
