-- MySQL dump 10.16  Distrib 10.1.34-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: tltvbqn
-- ------------------------------------------------------
-- Server version	10.1.34-MariaDB-0ubuntu0.18.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bando`
--

DROP TABLE IF EXISTS `bando`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bando` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `daXoa` bit(1) NOT NULL,
  `ngaySua` datetime DEFAULT NULL,
  `ngayTao` datetime DEFAULT NULL,
  `trangThai` varchar(255) DEFAULT NULL,
  `lat` double NOT NULL,
  `lng` double NOT NULL,
  `nguoiSua_id` bigint(20) DEFAULT NULL,
  `nguoiTao_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKh495o542bjvgcwn6ux9wi6eus` (`nguoiSua_id`),
  KEY `FKpu2u1pd23rhwwcnvffpb6kj74` (`nguoiTao_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bando`
--

LOCK TABLES `bando` WRITE;
/*!40000 ALTER TABLE `bando` DISABLE KEYS */;
/*!40000 ALTER TABLE `bando` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coquantochuc`
--

DROP TABLE IF EXISTS `coquantochuc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coquantochuc` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `daXoa` bit(1) NOT NULL,
  `ngaySua` datetime DEFAULT NULL,
  `ngayTao` datetime DEFAULT NULL,
  `trangThai` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fax` varchar(255) DEFAULT NULL,
  `noiBo` bit(1) NOT NULL,
  `organAdd` varchar(255) DEFAULT NULL,
  `organId` varchar(255) DEFAULT NULL,
  `organName` varchar(255) DEFAULT NULL,
  `organizationCharge` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `website` varchar(255) DEFAULT NULL,
  `nguoiSua_id` bigint(20) DEFAULT NULL,
  `nguoiTao_id` bigint(20) DEFAULT NULL,
  `cha_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKa6u4t8f44jmolgh8vjmnbmi92` (`nguoiSua_id`),
  KEY `FKl2pvxxw6wam1mam98f2pi833m` (`nguoiTao_id`),
  KEY `FK9hhap5v67si62qmnre89q1ply` (`cha_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coquantochuc`
--

LOCK TABLES `coquantochuc` WRITE;
/*!40000 ALTER TABLE `coquantochuc` DISABLE KEYS */;
/*!40000 ALTER TABLE `coquantochuc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `image`
--

DROP TABLE IF EXISTS `image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `image` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `daXoa` bit(1) NOT NULL,
  `ngaySua` datetime DEFAULT NULL,
  `ngayTao` datetime DEFAULT NULL,
  `trangThai` varchar(255) DEFAULT NULL,
  `bannerImage` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `detailImage` varchar(255) DEFAULT NULL,
  `extension` varchar(255) DEFAULT NULL,
  `imageUrl` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `nameFileHash` varchar(255) DEFAULT NULL,
  `smallImage` varchar(255) DEFAULT NULL,
  `videoImage` varchar(255) DEFAULT NULL,
  `nguoiSua_id` bigint(20) DEFAULT NULL,
  `nguoiTao_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKou77algsm9v32c5q7ix04jxgl` (`nguoiSua_id`),
  KEY `FK33foc3k9ct7i13wy28myg3f0g` (`nguoiTao_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image`
--

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `language`
--

DROP TABLE IF EXISTS `language`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `language` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `daXoa` bit(1) NOT NULL,
  `ngaySua` datetime DEFAULT NULL,
  `ngayTao` datetime DEFAULT NULL,
  `trangThai` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `nguoiSua_id` bigint(20) DEFAULT NULL,
  `nguoiTao_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqe2qrfo14h35y71xo32jrm36t` (`nguoiSua_id`),
  KEY `FKq8mmr7f05cve848nfxl98tkao` (`nguoiTao_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `language`
--

LOCK TABLES `language` WRITE;
/*!40000 ALTER TABLE `language` DISABLE KEYS */;
/*!40000 ALTER TABLE `language` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhanvien`
--

DROP TABLE IF EXISTS `nhanvien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nhanvien` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `daXoa` bit(1) NOT NULL,
  `ngaySua` datetime DEFAULT NULL,
  `ngayTao` datetime DEFAULT NULL,
  `trangThai` varchar(255) DEFAULT NULL,
  `checkKichHoat` bit(1) NOT NULL,
  `chucVu` varchar(255) DEFAULT NULL,
  `diaChi` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `hoVaTen` varchar(255) DEFAULT NULL,
  `matKhau` varchar(255) DEFAULT NULL,
  `ngaySinh` datetime DEFAULT NULL,
  `pathAvatar` varchar(255) DEFAULT NULL,
  `salkey` varchar(255) DEFAULT NULL,
  `selectedDV` bit(1) NOT NULL,
  `soDienThoai` varchar(255) DEFAULT NULL,
  `nguoiSua_id` bigint(20) DEFAULT NULL,
  `nguoiTao_id` bigint(20) DEFAULT NULL,
  `phongBan_id` bigint(20) DEFAULT NULL,
  `vaiTro_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKea6is3f6do1ybghqu5uo8xiap` (`nguoiSua_id`),
  KEY `FKlyyrr2uas0f50iupka9ergm8x` (`nguoiTao_id`),
  KEY `FK3qvwtuxp7rmr2kvksmb0bh34g` (`phongBan_id`),
  KEY `FKj6192ntptfiiw2ie88p77jb5n` (`vaiTro_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhanvien`
--

LOCK TABLES `nhanvien` WRITE;
/*!40000 ALTER TABLE `nhanvien` DISABLE KEYS */;
INSERT INTO `nhanvien` VALUES (1,'\0','2019-01-31 10:50:09','2019-01-31 10:50:09','ap_dung','\0','','','admin@greenglobal.vn','Super Admin','QpifZYlm8tV1pSh4kpz+Le1Wx3v4zSKU',NULL,'','wy2e96nGN2cDqlLvplqEWT7fbZ/mete1','\0','',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `nhanvien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhanvien_quyens`
--

DROP TABLE IF EXISTS `nhanvien_quyens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nhanvien_quyens` (
  `nhanVien_id` bigint(20) NOT NULL,
  `quyens` varchar(255) DEFAULT NULL,
  KEY `FKnlof2gbqm97pmg0mpfr2ytmui` (`nhanVien_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhanvien_quyens`
--

LOCK TABLES `nhanvien_quyens` WRITE;
/*!40000 ALTER TABLE `nhanvien_quyens` DISABLE KEYS */;
INSERT INTO `nhanvien_quyens` VALUES (1,'*');
/*!40000 ALTER TABLE `nhanvien_quyens` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhanvien_vaitro`
--

DROP TABLE IF EXISTS `nhanvien_vaitro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nhanvien_vaitro` (
  `nhanvien_id` bigint(20) NOT NULL,
  `vaitros_id` bigint(20) NOT NULL,
  PRIMARY KEY (`nhanvien_id`,`vaitros_id`),
  KEY `FKidefm6rhsejb07ce6hcdlecg` (`vaitros_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhanvien_vaitro`
--

LOCK TABLES `nhanvien_vaitro` WRITE;
/*!40000 ALTER TABLE `nhanvien_vaitro` DISABLE KEYS */;
/*!40000 ALTER TABLE `nhanvien_vaitro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phongban`
--

DROP TABLE IF EXISTS `phongban`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `phongban` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `daXoa` bit(1) NOT NULL,
  `ngaySua` datetime DEFAULT NULL,
  `ngayTao` datetime DEFAULT NULL,
  `trangThai` varchar(255) DEFAULT NULL,
  `moTa` varchar(255) DEFAULT NULL,
  `ten` varchar(255) DEFAULT NULL,
  `nguoiSua_id` bigint(20) DEFAULT NULL,
  `nguoiTao_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKn1jfu4n83q1rny3eqlxx7q2j6` (`nguoiSua_id`),
  KEY `FKfobc77hmqd3hx4kgmoc2fnmyn` (`nguoiTao_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phongban`
--

LOCK TABLES `phongban` WRITE;
/*!40000 ALTER TABLE `phongban` DISABLE KEYS */;
/*!40000 ALTER TABLE `phongban` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sessioncount`
--

DROP TABLE IF EXISTS `sessioncount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sessioncount` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `daXoa` bit(1) NOT NULL,
  `ngaySua` datetime DEFAULT NULL,
  `ngayTao` datetime DEFAULT NULL,
  `trangThai` varchar(255) DEFAULT NULL,
  `hostName` varchar(255) DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `sessionId` varchar(255) DEFAULT NULL,
  `nguoiSua_id` bigint(20) DEFAULT NULL,
  `nguoiTao_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK33u1wllpubpeaor6et0hy6j1c` (`nguoiSua_id`),
  KEY `FKgw3b3qkj5ylnajbjir8dq6bx1` (`nguoiTao_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sessioncount`
--

LOCK TABLES `sessioncount` WRITE;
/*!40000 ALTER TABLE `sessioncount` DISABLE KEYS */;
/*!40000 ALTER TABLE `sessioncount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `settings`
--

DROP TABLE IF EXISTS `settings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `settings` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `daXoa` bit(1) NOT NULL,
  `ngaySua` datetime DEFAULT NULL,
  `ngayTao` datetime DEFAULT NULL,
  `trangThai` varchar(255) DEFAULT NULL,
  `canBoQuanLy` bit(1) NOT NULL,
  `dacDiemNhanDang` bit(1) NOT NULL,
  `dem` bigint(20) NOT NULL,
  `diaChiHienNay` bit(1) NOT NULL,
  `diaChiHienNayHuyen` bit(1) NOT NULL,
  `diaChiHienNayTinh` bit(1) NOT NULL,
  `diaChiHienNayToDanPho` bit(1) NOT NULL,
  `diaChiHienNayXa` bit(1) NOT NULL,
  `diaChiThuongTru` bit(1) NOT NULL,
  `diaChiThuongTruHuyen` bit(1) NOT NULL,
  `diaChiThuongTruTinh` bit(1) NOT NULL,
  `diaChiThuongTruToDanPho` bit(1) NOT NULL,
  `diaChiThuongTruXa` bit(1) NOT NULL,
  `donViCanBoQuanLy` bit(1) NOT NULL,
  `email` bit(1) NOT NULL,
  `ngayCapCMND` bit(1) NOT NULL,
  `ngayQuanLySauKhiRaTrungTam` int(11) NOT NULL,
  `ngheNghiep` bit(1) NOT NULL,
  `noiCapCMND` bit(1) NOT NULL,
  `soCMND` bit(1) NOT NULL,
  `soDTCanBoQuanLy` bit(1) NOT NULL,
  `soDienThoai` bit(1) NOT NULL,
  `soDinhDanh` bit(1) NOT NULL,
  `tenKhac` bit(1) NOT NULL,
  `thangQuanLySauCai` int(11) NOT NULL,
  `thangQuanLySauViPham` int(11) NOT NULL,
  `thanhPhanDoiTuong` bit(1) NOT NULL,
  `tinhTrangViecLam` bit(1) NOT NULL,
  `trinhDoHocVan` bit(1) NOT NULL,
  `widthMedium` int(11) NOT NULL,
  `widthSmall` int(11) NOT NULL,
  `nguoiSua_id` bigint(20) DEFAULT NULL,
  `nguoiTao_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKp204jwnv8cjfxoni9c3s81ap1` (`nguoiSua_id`),
  KEY `FK3u6flv7dh5v0048tsnv4jtefd` (`nguoiTao_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `settings`
--

LOCK TABLES `settings` WRITE;
/*!40000 ALTER TABLE `settings` DISABLE KEYS */;
/*!40000 ALTER TABLE `settings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teptin`
--

DROP TABLE IF EXISTS `teptin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teptin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `daXoa` bit(1) NOT NULL,
  `ngaySua` datetime DEFAULT NULL,
  `ngayTao` datetime DEFAULT NULL,
  `trangThai` varchar(255) DEFAULT NULL,
  `nameHash` varchar(255) DEFAULT NULL,
  `pathFile` varchar(255) DEFAULT NULL,
  `tenFile` varchar(255) DEFAULT NULL,
  `tenTaiLieu` varchar(255) DEFAULT NULL,
  `typeFile` varchar(255) DEFAULT NULL,
  `nguoiSua_id` bigint(20) DEFAULT NULL,
  `nguoiTao_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrrosiyu0g66a2g269ubsin7mh` (`nguoiSua_id`),
  KEY `FKrcexf7uj6tqr098i97larre3x` (`nguoiTao_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teptin`
--

LOCK TABLES `teptin` WRITE;
/*!40000 ALTER TABLE `teptin` DISABLE KEYS */;
/*!40000 ALTER TABLE `teptin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thamso`
--

DROP TABLE IF EXISTS `thamso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `thamso` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `daXoa` bit(1) NOT NULL,
  `ngaySua` datetime DEFAULT NULL,
  `ngayTao` datetime DEFAULT NULL,
  `trangThai` varchar(255) DEFAULT NULL,
  `ma` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `nguoiSua_id` bigint(20) DEFAULT NULL,
  `nguoiTao_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKlaliea6x5rnn8bn2jt6kehqun` (`nguoiSua_id`),
  KEY `FKtnyvrvdhkcwpuj70pfhn42yy5` (`nguoiTao_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thamso`
--

LOCK TABLES `thamso` WRITE;
/*!40000 ALTER TABLE `thamso` DISABLE KEYS */;
/*!40000 ALTER TABLE `thamso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thongbao`
--

DROP TABLE IF EXISTS `thongbao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `thongbao` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `daXoa` bit(1) NOT NULL,
  `ngaySua` datetime DEFAULT NULL,
  `ngayTao` datetime DEFAULT NULL,
  `trangThai` varchar(255) DEFAULT NULL,
  `daXem` bit(1) NOT NULL,
  `idObject` bigint(20) DEFAULT NULL,
  `kieuThongBao` varchar(255) DEFAULT NULL,
  `loaiThongBao` int(11) DEFAULT NULL,
  `noiDung` varchar(255) DEFAULT NULL,
  `xemChiTiet` bit(1) NOT NULL,
  `nguoiSua_id` bigint(20) DEFAULT NULL,
  `nguoiTao_id` bigint(20) DEFAULT NULL,
  `nguoiGui_id` bigint(20) DEFAULT NULL,
  `nguoiNhan_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpieyg7mkasgkf1jl4gxj3qsvw` (`nguoiSua_id`),
  KEY `FK83hv7q7ahv7mbktsy8tn4mwb7` (`nguoiTao_id`),
  KEY `FK3kymlanbdgk6u8f9kwxbeqa58` (`nguoiGui_id`),
  KEY `FK4ua79gs8n01q2v8nblg9bccls` (`nguoiNhan_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thongbao`
--

LOCK TABLES `thongbao` WRITE;
/*!40000 ALTER TABLE `thongbao` DISABLE KEYS */;
/*!40000 ALTER TABLE `thongbao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vaitro`
--

DROP TABLE IF EXISTS `vaitro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vaitro` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `daXoa` bit(1) NOT NULL,
  `ngaySua` datetime DEFAULT NULL,
  `ngayTao` datetime DEFAULT NULL,
  `trangThai` varchar(255) DEFAULT NULL,
  `alias` varchar(255) DEFAULT NULL,
  `checkKichHoat` bit(1) NOT NULL,
  `loaiVaiTro` varchar(255) DEFAULT NULL,
  `soThuTu` int(11) NOT NULL,
  `tenVaiTro` varchar(255) DEFAULT NULL,
  `nguoiSua_id` bigint(20) DEFAULT NULL,
  `nguoiTao_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5qixi7fouimmv3r9fau58tk2q` (`nguoiSua_id`),
  KEY `FK9b0cfnlecgosjvuq6lkxe8fy9` (`nguoiTao_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vaitro`
--

LOCK TABLES `vaitro` WRITE;
/*!40000 ALTER TABLE `vaitro` DISABLE KEYS */;
/*!40000 ALTER TABLE `vaitro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vaitro_quyens`
--

DROP TABLE IF EXISTS `vaitro_quyens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vaitro_quyens` (
  `vaitro_id` bigint(20) NOT NULL,
  `quyens` varchar(255) DEFAULT NULL,
  KEY `FKqldf0fggg0f8sc37im018c5ti` (`vaitro_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vaitro_quyens`
--

LOCK TABLES `vaitro_quyens` WRITE;
/*!40000 ALTER TABLE `vaitro_quyens` DISABLE KEYS */;
/*!40000 ALTER TABLE `vaitro_quyens` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vanban`
--

DROP TABLE IF EXISTS `vanban`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vanban` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `daXoa` bit(1) NOT NULL,
  `ngaySua` datetime DEFAULT NULL,
  `ngayTao` datetime DEFAULT NULL,
  `trangThai` varchar(255) DEFAULT NULL,
  `checkSum` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `docId` varchar(255) DEFAULT NULL,
  `documentId` varchar(255) DEFAULT NULL,
  `fileUrl` varchar(255) DEFAULT NULL,
  `loaiLienThong` varchar(255) DEFAULT NULL,
  `ngayPhatHanh` datetime DEFAULT NULL,
  `soFileDinhKem` int(11) NOT NULL,
  `soKyhieu` varchar(255) DEFAULT NULL,
  `subject` varchar(500) DEFAULT NULL,
  `nguoiSua_id` bigint(20) DEFAULT NULL,
  `nguoiTao_id` bigint(20) DEFAULT NULL,
  `coQuanToChuc_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmnsq1go24lav91wrjjf673dl9` (`nguoiSua_id`),
  KEY `FKhd04sbooj5j19r2n7o3ky999n` (`nguoiTao_id`),
  KEY `FKfe3gpxs8jtg2vy8xbooh4fobt` (`coQuanToChuc_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vanban`
--

LOCK TABLES `vanban` WRITE;
/*!40000 ALTER TABLE `vanban` DISABLE KEYS */;
/*!40000 ALTER TABLE `vanban` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vanbanden`
--

DROP TABLE IF EXISTS `vanbanden`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vanbanden` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `daXoa` bit(1) NOT NULL,
  `ngaySua` datetime DEFAULT NULL,
  `ngayTao` datetime DEFAULT NULL,
  `trangThai` varchar(255) DEFAULT NULL,
  `daNhan` bit(1) NOT NULL,
  `nguoiSua_id` bigint(20) DEFAULT NULL,
  `nguoiTao_id` bigint(20) DEFAULT NULL,
  `coQuanToChuc_id` bigint(20) DEFAULT NULL,
  `vanBan_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9y2m0g25nvyyqtr4yws1mxvga` (`nguoiSua_id`),
  KEY `FKpwg2m4tdk6w3df7shskd88uu4` (`nguoiTao_id`),
  KEY `FKtevvwppvdiksq01tods9wt49` (`coQuanToChuc_id`),
  KEY `FKg17t2s8wu8ibecj8dmddkdu1i` (`vanBan_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vanbanden`
--

LOCK TABLES `vanbanden` WRITE;
/*!40000 ALTER TABLE `vanbanden` DISABLE KEYS */;
/*!40000 ALTER TABLE `vanbanden` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-31 10:56:41
