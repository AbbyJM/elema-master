-- MySQL dump 10.13  Distrib 8.0.12, for Linux (x86_64)
--
-- Host: localhost    Database: elema_uaac
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `persistent_logins`
--

DROP TABLE IF EXISTS `persistent_logins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persistent_logins`
--

LOCK TABLES `persistent_logins` WRITE;
/*!40000 ALTER TABLE `persistent_logins` DISABLE KEYS */;
INSERT INTO `persistent_logins` VALUES ('abby','3R06+xfEqAMi19ue6kV8+A==','HgCs3wD6DYw9QooxdYz03g==','2018-10-11 14:07:56'),('abby','foI4hqcmGgg9S5fw6HX2jg==','sWnKYV3T7oWQsv7RWnkAoA==','2018-10-11 16:13:39'),('abby','jWSyzlJxwi1alRBG1z//iw==','16je/abwXGkzGUNjBtG4og==','2018-10-11 14:51:47'),('abby','wwV2m5wjtTIFny2no5HZ+Q==','XFCngmhIhrVmjhKtHI7oyg==','2018-10-11 16:15:50');
/*!40000 ALTER TABLE `persistent_logins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uaac_log`
--

DROP TABLE IF EXISTS `uaac_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `uaac_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `operator_id` int(11) NOT NULL,
  `operator_name` varchar(20) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `operation_type` varchar(20) DEFAULT NULL,
  `time_begin` timestamp NULL DEFAULT NULL,
  `class_name` varchar(255) DEFAULT NULL,
  `method_name` varchar(255) DEFAULT NULL,
  `target_os` varchar(20) DEFAULT NULL,
  `ip_address` varchar(20) DEFAULT NULL,
  `browser_name` varchar(50) DEFAULT NULL,
  `status` varchar(25) DEFAULT NULL,
  `time_used` int(11) DEFAULT NULL,
  `request_url` varchar(255) DEFAULT NULL,
  `location_name` varchar(20) DEFAULT NULL,
  `request_params` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=117 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uaac_log`
--

LOCK TABLES `uaac_log` WRITE;
/*!40000 ALTER TABLE `uaac_log` DISABLE KEYS */;
INSERT INTO `uaac_log` VALUES (32,-1,'ANONYMOUS','generated validator image successfully','SEND_EMAIL','2018-11-08 11:16:59','com.abby.elema.web.fronted.ValidateCodeController','getImageValidateCode','Unknown','0:0:0:0:0:0:0:1','Unknown','success',210,'http://10.5.120.169:7070/api/getvalidate/image','localhost','testparam: zhagn;  test key: fdsf;  '),(33,-1,'ANONYMOUS','registered successfully','UNKNOWN','2018-11-09 18:40:05','com.sun.proxy.$Proxy156','registerUser','Unknown','0:0:0:0:0:0:0:1','Unknown','success',1585,'http://localhost:8888/test','localhost',''),(34,-1,'ANONYMOUS','registered successfully','UNKNOWN','2018-11-09 19:12:45','com.sun.proxy.$Proxy156','registerUser','Unknown','0:0:0:0:0:0:0:1','Unknown','success',425,'http://localhost:8888/test','localhost',''),(35,-1,'ANONYMOUS','registered successfully','UNKNOWN','2018-11-09 21:49:14','com.sun.proxy.$Proxy156','registerUser','Unknown','0:0:0:0:0:0:0:1','Unknown','success',344,'http://localhost:8888/test','localhost',''),(36,-1,'ANONYMOUS','registered successfully','UNKNOWN','2018-11-09 21:56:09','com.sun.proxy.$Proxy156','registerUser','Unknown','0:0:0:0:0:0:0:1','Unknown','success',1197,'http://localhost:8888/test','localhost',''),(37,-1,'ANONYMOUS','registered successfully','UNKNOWN','2018-11-09 22:01:17','com.sun.proxy.$Proxy156','registerUser','Unknown','0:0:0:0:0:0:0:1','Unknown','success',365,'http://localhost:8888/test','localhost',''),(38,-1,'ANONYMOUS','registered successfully','UNKNOWN','2018-11-09 22:07:51','com.sun.proxy.$Proxy156','registerUser','Unknown','0:0:0:0:0:0:0:1','Unknown','success',62837,'http://localhost:8888/test','localhost',''),(39,-1,'ANONYMOUS','registered successfully','UNKNOWN','2018-11-09 22:27:27','com.sun.proxy.$Proxy157','registerUser','Unknown','0:0:0:0:0:0:0:1','Unknown','success',1173,'http://localhost:8888/test','localhost',''),(40,-1,'ANONYMOUS','registered successfully','UNKNOWN','2018-11-09 22:49:35','com.sun.proxy.$Proxy157','registerUser','Unknown','0:0:0:0:0:0:0:1','Unknown','success',1179,'http://localhost:8888/test','localhost',''),(41,-1,'ANONYMOUS','registered successfully','UNKNOWN','2018-11-10 11:02:58','com.sun.proxy.$Proxy157','registerUser','Unknown','0:0:0:0:0:0:0:1','Unknown','success',1234,'http://localhost:8888/test','localhost',''),(42,-1,'ANONYMOUS','registered successfully','UNKNOWN','2018-11-10 11:09:17','com.sun.proxy.$Proxy157','registerUser','Unknown','0:0:0:0:0:0:0:1','Unknown','success',1182,'http://localhost:8888/test','localhost',''),(43,-1,'ANONYMOUS','registered successfully','UNKNOWN','2018-11-10 11:19:43','com.sun.proxy.$Proxy157','registerUser','Unknown','0:0:0:0:0:0:0:1','Unknown','success',1181,'http://localhost:8888/test','localhost',''),(44,-1,'ANONYMOUS','registered successfully','UNKNOWN','2018-11-10 11:52:13','com.sun.proxy.$Proxy157','registerUser','Unknown','0:0:0:0:0:0:0:1','Unknown','success',1063,'http://localhost:8888/test','localhost',''),(45,-1,'ANONYMOUS','registered successfully','UNKNOWN','2018-11-10 11:56:28','com.sun.proxy.$Proxy157','registerUser','Unknown','0:0:0:0:0:0:0:1','Unknown','success',1076,'http://localhost:8888/test','localhost',''),(46,-1,'ANONYMOUS','registered successfully','UNKNOWN','2018-11-10 12:38:32','com.sun.proxy.$Proxy157','registerUser','Unknown','0:0:0:0:0:0:0:1','Unknown','success',364,'http://localhost:8888/test','localhost',''),(47,-1,'ANONYMOUS','registered successfully','UNKNOWN','2018-11-10 16:10:52','com.sun.proxy.$Proxy157','registerUser','Unknown','10.5.120.169','Robot/Spider','success',1150,'http://10.5.120.169:8888/admin/mqproducer/send','localhost',''),(48,-1,'ANONYMOUS','registered successfully','UNKNOWN','2018-11-10 17:59:13','com.sun.proxy.$Proxy157','registerUser','Unknown','10.5.120.169','Robot/Spider','success',328,'http://10.5.120.169:8888/admin/mqproducer/send','localhost',''),(49,-1,'ANONYMOUS','registered successfully','UNKNOWN','2018-11-10 20:08:21','com.sun.proxy.$Proxy157','registerUser','Unknown','172.20.10.2','Robot/Spider','success',1272,'http://172.20.10.2:8888/admin/mqproducer/send','localhost',''),(50,-1,'ANONYMOUS','success','REGISTER_USER','2018-11-10 20:08:20','com.abby.elema.web.frontend.UserController','registerUser','Unknown','0:0:0:0:0:0:0:1','Unknown','success',3191,'http://172.20.10.2:7070/api/user/register','localhost','username: jianming;  password: 8915890;  email: 1358890401@qq.com;  '),(51,-1,'ANONYMOUS','mq send error','REGISTER_USER','2018-11-10 22:26:32','com.abby.elema.web.frontend.UserController','registerUser','Unknown','0:0:0:0:0:0:0:1','Unknown','mq send error',2206,'http://172.20.10.2:7070/api/user/register','localhost','username: jianming;  password: 8915890;  email: 1358890401@qq.com;  '),(52,-1,'ANONYMOUS','registered successfully','UNKNOWN','2018-11-10 22:27:29','com.sun.proxy.$Proxy157','registerUser','Unknown','172.20.10.2','Robot/Spider','success',365,'http://172.20.10.2:8888/admin/mqproducer/send','localhost',''),(53,-1,'ANONYMOUS','success','REGISTER_USER','2018-11-10 22:27:28','com.abby.elema.web.frontend.UserController','registerUser','Unknown','0:0:0:0:0:0:0:1','Unknown','success',1066,'http://172.20.10.2:7070/api/user/register','localhost','username: jianming;  password: 8915890;  email: 1358890401@qq.com;  '),(54,-1,'ANONYMOUS','registered successfully','UNKNOWN','2018-11-11 21:12:42','com.sun.proxy.$Proxy157','registerUser','Unknown','172.20.10.2','Robot/Spider','success',1392,'http://172.20.10.2:8888/admin/mqproducer/send','localhost',''),(55,-1,'ANONYMOUS','success','REGISTER_USER','2018-11-11 21:12:41','com.abby.elema.web.frontend.UserController','registerUser','Unknown','0:0:0:0:0:0:0:1','Unknown','success',3155,'http://172.20.10.2:7070/api/user/register','localhost','username: jianming;  password: 8915890;  email: 1358890401@qq.com;  '),(56,-1,'ANONYMOUS','generated validator image successfully','SEND_EMAIL','2018-11-11 21:14:24','com.abby.elema.web.frontend.ValidateCodeController','getImageValidateCode','Unknown','0:0:0:0:0:0:0:1','Unknown','success',1525,'http://172.20.10.2:7070/api/getvalidate/image','localhost','testparam: zhagn;  test key: fdsf;  '),(57,-1,'ANONYMOUS','generated validator image successfully','SEND_EMAIL','2018-11-11 21:25:10','com.abby.elema.web.frontend.ValidateCodeController','getImageValidateCode','Unknown','0:0:0:0:0:0:0:1','Unknown','success',21,'http://172.20.10.2:7070/api/getvalidate/image','localhost','testparam: zhagn;  test key: fdsf;  '),(58,-1,'ANONYMOUS','generated validator image successfully','SEND_EMAIL','2018-11-11 21:33:38','com.abby.elema.web.frontend.ValidateCodeController','getImageValidateCode','Unknown','0:0:0:0:0:0:0:1','Unknown','success',191,'http://10.5.120.169:7070/api/getvalidate/image','localhost','testparam: zhagn;  test key: fdsf;  '),(59,-1,'ANONYMOUS','generated validator image successfully','SEND_EMAIL','2018-11-11 21:33:52','com.abby.elema.web.frontend.ValidateCodeController','getImageValidateCode','Unknown','0:0:0:0:0:0:0:1','Unknown','success',25,'http://10.5.120.169:7070/api/getvalidate/image','localhost','testparam: zhagn;  test key: fdsf;  '),(60,-1,'ANONYMOUS','generated validator image successfully','SEND_EMAIL','2018-11-11 21:34:07','com.abby.elema.web.frontend.ValidateCodeController','getImageValidateCode','Unknown','0:0:0:0:0:0:0:1','Unknown','success',21,'http://10.5.120.169:7070/api/getvalidate/image','localhost','testparam: zhagn;  test key: fdsf;  '),(61,-1,'ANONYMOUS','registered successfully','UNKNOWN','2018-11-11 21:44:29','com.sun.proxy.$Proxy157','registerUser','Unknown','10.5.120.169','Robot/Spider','success',1176,'http://10.5.120.169:8888/admin/mqproducer/send','localhost',''),(62,-1,'ANONYMOUS','success','REGISTER_USER','2018-11-11 21:44:29','com.abby.elema.web.frontend.UserController','registerUser','Unknown','0:0:0:0:0:0:0:1','Unknown','success',1906,'http://10.5.120.169:7070/api/user/register','localhost','username: jianming;  password: 8915890;  email: 1358890401@qq.com;  '),(63,-1,'ANONYMOUS','generated validator image successfully','SEND_EMAIL','2018-11-11 21:45:11','com.abby.elema.web.frontend.ValidateCodeController','getImageValidateCode','Unknown','0:0:0:0:0:0:0:1','Unknown','success',188,'http://10.5.120.169:7070/api/getvalidate/image','localhost','testparam: zhagn;  test key: fdsf;  '),(64,-1,'ANONYMOUS','generated validator image successfully','SEND_EMAIL','2018-11-11 22:02:01','com.abby.elema.web.frontend.ValidateCodeController','getImageValidateCode','Unknown','0:0:0:0:0:0:0:1','Unknown','success',218,'http://172.20.10.2:7070/api/getvalidate/image','localhost','testparam: zhagn;  test key: fdsf;  '),(65,-1,'ANONYMOUS','registered successfully','UNKNOWN','2018-11-11 22:10:42','com.sun.proxy.$Proxy157','registerUser','Unknown','172.20.10.2','Robot/Spider','success',1228,'http://172.20.10.2:8888/admin/mqproducer/send','localhost',''),(66,-1,'ANONYMOUS','success','REGISTER_USER','2018-11-11 22:10:42','com.abby.elema.web.frontend.UserController','registerUser','Unknown','0:0:0:0:0:0:0:1','Unknown','success',2249,'http://172.20.10.2:7070/api/user/register','localhost','username: jianming;  password: 8915890;  email: 1358890401@qq.com;  '),(67,-1,'ANONYMOUS','generated validator image successfully','SEND_EMAIL','2018-11-11 22:10:59','com.abby.elema.web.frontend.ValidateCodeController','getImageValidateCode','Unknown','0:0:0:0:0:0:0:1','Unknown','success',178,'http://172.20.10.2:7070/api/getvalidate/image','localhost','testparam: zhagn;  test key: fdsf;  '),(68,-1,'ANONYMOUS','generated validator image successfully','SEND_EMAIL','2018-11-11 22:35:39','com.abby.elema.web.frontend.ValidateCodeController','getImageValidateCode','Unknown','0:0:0:0:0:0:0:1','Unknown','success',338,'http://172.20.10.2:7070/api/getvalidate/image','localhost','testparam: zhagn;  test key: fdsf;  '),(69,-1,'ANONYMOUS','registered successfully','UNKNOWN','2018-11-11 22:36:53','com.sun.proxy.$Proxy157','registerUser','Unknown','172.20.10.2','Robot/Spider','success',1127,'http://172.20.10.2:8888/admin/mqproducer/send','localhost',''),(70,-1,'ANONYMOUS','success','REGISTER_USER','2018-11-11 22:36:52','com.abby.elema.web.frontend.UserController','registerUser','Unknown','0:0:0:0:0:0:0:1','Unknown','success',2863,'http://172.20.10.2:7070/api/user/register','localhost','username: jianming;  password: 8915890;  email: 1358890401@qq.com;  '),(71,-1,'ANONYMOUS','registered successfully','UNKNOWN','2018-11-11 22:43:16','com.sun.proxy.$Proxy157','registerUser','Unknown','172.20.10.2','Robot/Spider','success',372,'http://172.20.10.2:8888/admin/mqproducer/send','localhost',''),(72,-1,'ANONYMOUS','success','REGISTER_USER','2018-11-11 22:43:15','com.abby.elema.web.frontend.UserController','registerUser','Unknown','0:0:0:0:0:0:0:1','Unknown','success',1959,'http://172.20.10.2:7070/api/user/register','localhost','username: jianming;  password: 8915890;  email: 1358890401@qq.com;  '),(73,-1,'ANONYMOUS','registered successfully','UNKNOWN','2018-11-11 22:47:54','com.sun.proxy.$Proxy157','registerUser','Unknown','172.20.10.2','Robot/Spider','success',1060,'http://172.20.10.2:8888/admin/mqproducer/send','localhost',''),(74,-1,'ANONYMOUS','registered successfully','UNKNOWN','2018-11-11 22:55:31','com.sun.proxy.$Proxy157','registerUser','Unknown','172.20.10.2','Robot/Spider','success',439,'http://172.20.10.2:8888/admin/mqproducer/send','localhost',''),(75,-1,'ANONYMOUS','success','REGISTER_USER','2018-11-11 22:55:30','com.abby.elema.web.frontend.UserController','registerUser','Unknown','0:0:0:0:0:0:0:1','Unknown','success',2098,'http://172.20.10.2:7070/api/user/register','localhost','username: jianming;  password: 8915890;  email: 1358890401@qq.com;  '),(76,-1,'ANONYMOUS','generated validator image successfully','SEND_EMAIL','2018-11-11 22:55:48','com.abby.elema.web.frontend.ValidateCodeController','getImageValidateCode','Unknown','0:0:0:0:0:0:0:1','Unknown','success',207,'http://172.20.10.2:7070/api/getvalidate/image','localhost','testparam: zhagn;  test key: fdsf;  '),(77,-1,'ANONYMOUS','registered successfully','UNKNOWN','2018-11-11 23:02:38','com.sun.proxy.$Proxy157','registerUser','Unknown','172.20.10.2','Robot/Spider','success',408,'http://172.20.10.2:8888/admin/mqproducer/send','localhost',''),(78,-1,'ANONYMOUS','success','REGISTER_USER','2018-11-11 23:02:37','com.abby.elema.web.frontend.UserController','registerUser','Unknown','0:0:0:0:0:0:0:1','Unknown','success',2031,'http://172.20.10.2:7070/api/user/register','localhost','username: jianming;  password: 8915890;  email: 1358890401@qq.com;  '),(79,-1,'ANONYMOUS','generated validator image successfully','SEND_EMAIL','2018-11-11 23:03:41','com.abby.elema.web.frontend.ValidateCodeController','getImageValidateCode','Unknown','0:0:0:0:0:0:0:1','Unknown','success',197,'http://172.20.10.2:7070/api/getvalidate/image','localhost','testparam: zhagn;  test key: fdsf;  '),(80,-1,'ANONYMOUS',NULL,'REGISTER_USER','2018-11-11 23:11:57','com.abby.elema.web.frontend.UserController','registerUser','Unknown','0:0:0:0:0:0:0:1','Unknown',NULL,576,'http://172.20.10.2:7070/api/user/register','localhost','username: jianming;  password: 8915890;  email: 1358890401@qq.com;  '),(81,-1,'ANONYMOUS','generated validator image successfully','SEND_EMAIL','2018-11-11 23:12:21','com.abby.elema.web.frontend.ValidateCodeController','getImageValidateCode','Unknown','0:0:0:0:0:0:0:1','Unknown','success',206,'http://172.20.10.2:7070/api/getvalidate/image','localhost','testparam: zhagn;  test key: fdsf;  '),(82,-1,'ANONYMOUS','registered successfully','UNKNOWN','2018-11-11 23:30:33','com.sun.proxy.$Proxy157','registerUser','Unknown','172.20.10.2','Robot/Spider','success',382,'http://172.20.10.2:8888/admin/mqproducer/send','localhost',''),(83,-1,'ANONYMOUS','success','REGISTER_USER','2018-11-11 23:30:32','com.abby.elema.web.frontend.UserController','registerUser','Unknown','0:0:0:0:0:0:0:1','Unknown','success',1999,'http://172.20.10.2:7070/api/user/register','localhost','username: jianming;  password: 8915890;  email: 1358890401@qq.com;  '),(84,-1,'ANONYMOUS','registered successfully','UNKNOWN','2018-11-11 23:40:24','com.sun.proxy.$Proxy157','registerUser','Unknown','172.20.10.2','Robot/Spider','success',419,'http://172.20.10.2:8888/admin/mqproducer/send','localhost',''),(85,-1,'ANONYMOUS','success','REGISTER_USER','2018-11-11 23:40:23','com.abby.elema.web.frontend.UserController','registerUser','Unknown','0:0:0:0:0:0:0:1','Unknown','success',1883,'http://172.20.10.2:7070/api/user/register','localhost','username: jianming;  password: 8915890;  email: 1358890401@qq.com;  '),(86,-1,'ANONYMOUS','generated validator image successfully','SEND_EMAIL','2018-11-11 23:40:42','com.abby.elema.web.frontend.ValidateCodeController','getImageValidateCode','Unknown','0:0:0:0:0:0:0:1','Unknown','success',206,'http://172.20.10.2:7070/api/getvalidate/image','localhost','testparam: zhagn;  test key: fdsf;  '),(87,-1,'ANONYMOUS','registered successfully','UNKNOWN','2018-11-13 11:44:51','com.sun.proxy.$Proxy158','registerUser','Unknown','172.20.10.2','Robot/Spider','success',1105,'http://172.20.10.2:8888/admin/mqproducer/send','localhost',''),(88,-1,'ANONYMOUS','success','REGISTER_USER','2018-11-13 11:44:51','com.abby.elema.web.frontend.UserController','registerUser','Unknown','0:0:0:0:0:0:0:1','Unknown','success',1894,'http://172.20.10.2:7070/api/user/register','localhost','username: jianming;  password: 8915890;  email: 1358890401@qq.com;  '),(89,-1,'ANONYMOUS','generated validator image successfully','SEND_EMAIL','2018-11-13 12:39:19','com.abby.elema.web.frontend.ValidateCodeController','getImageValidateCode','Unknown','0:0:0:0:0:0:0:1','Unknown','success',194,'http://172.20.10.2:7070/api/getvalidate/image','localhost','testparam: zhagn;  test key: fdsf;  '),(90,-1,'ANONYMOUS','mq send error','REGISTER_USER','2018-11-13 14:20:56','com.abby.elema.web.frontend.UserController','registerUser','Unknown','0:0:0:0:0:0:0:1','Unknown','mq send error',2028,'http://172.20.10.2:7070/api/user/register','localhost','username: zhangsan;  password: 8915890;  email: 2627372340@qq.com;  '),(91,-1,'ANONYMOUS','registered successfully','UNKNOWN','2018-11-13 17:20:02','com.sun.proxy.$Proxy158','registerUser','Unknown','10.5.120.169','Robot/Spider','success',1283,'http://10.5.120.169:8888/admin/mqproducer/send','localhost',''),(92,-1,'ANONYMOUS','success','REGISTER_USER','2018-11-13 17:20:02','com.abby.elema.web.frontend.UserController','registerUser','Unknown','0:0:0:0:0:0:0:1','Unknown','success',2005,'http://10.5.120.169:7070/api/user/register','localhost','username: zhangsan;  password: 8915890;  email: 2627372340@qq.com;  '),(93,-1,'ANONYMOUS','registered successfully','UNKNOWN','2018-11-13 20:26:19','com.sun.proxy.$Proxy160','registerUser','Unknown','172.20.10.2','Robot/Spider','success',371,'http://172.20.10.2:8888/admin/mqproducer/send','localhost',''),(94,-1,'ANONYMOUS','success','REGISTER_USER','2018-11-13 20:26:18','com.abby.elema.web.frontend.UserController','registerUser','Unknown','0:0:0:0:0:0:0:1','Unknown','success',1811,'http://172.20.10.2:7070/api/user/register','localhost','username: chester;  password: 8915890;  email: 2627372340@qq.com;  '),(95,-1,'ANONYMOUS','registered successfully','UNKNOWN','2018-11-13 20:29:36','com.sun.proxy.$Proxy160','registerUser','Unknown','172.20.10.2','Robot/Spider','success',1072,'http://172.20.10.2:8888/admin/mqproducer/send','localhost',''),(96,-1,'ANONYMOUS','success','REGISTER_USER','2018-11-13 20:29:36','com.abby.elema.web.frontend.UserController','registerUser','Unknown','0:0:0:0:0:0:0:1','Unknown','success',1915,'http://172.20.10.2:7070/api/user/register','localhost','username: chester;  password: 8915890;  email: 2627372340@qq.com;  '),(97,-1,'ANONYMOUS','registered successfully','UNKNOWN','2018-11-13 20:36:31','com.sun.proxy.$Proxy160','registerUser','Unknown','172.20.10.2','Robot/Spider','success',1172,'http://172.20.10.2:8888/admin/mqproducer/send','localhost',''),(98,-1,'ANONYMOUS','success','REGISTER_USER','2018-11-13 20:36:31','com.abby.elema.web.frontend.UserController','registerUser','Unknown','0:0:0:0:0:0:0:1','Unknown','success',1924,'http://172.20.10.2:7070/api/user/register','localhost','username: chester;  password: 8915890;  email: 2627372340@qq.com;  '),(99,-1,'ANONYMOUS','registered successfully','UNKNOWN','2018-11-13 20:40:20','com.sun.proxy.$Proxy159','registerUser','Unknown','172.20.10.2','Robot/Spider','success',74379,'http://172.20.10.2:8888/admin/mqproducer/send','localhost',''),(100,-1,'ANONYMOUS','registered successfully','UNKNOWN','2018-11-13 20:47:13','com.sun.proxy.$Proxy160','registerUser','Unknown','172.20.10.2','Robot/Spider','success',501,'http://172.20.10.2:8888/admin/mqproducer/send','localhost',''),(101,-1,'ANONYMOUS','success','REGISTER_USER','2018-11-13 20:47:12','com.abby.elema.web.frontend.UserController','registerUser','Unknown','0:0:0:0:0:0:0:1','Unknown','success',2330,'http://172.20.10.2:7070/api/user/register','localhost','username: chester;  password: 8915890;  email: 2627372340@qq.com;  '),(102,-1,'ANONYMOUS','registered successfully','UNKNOWN','2018-11-13 20:48:58','com.sun.proxy.$Proxy160','registerUser','Unknown','172.20.10.2','Robot/Spider','success',304,'http://172.20.10.2:8888/admin/mqproducer/send','localhost',''),(103,-1,'ANONYMOUS','success','REGISTER_USER','2018-11-13 20:48:58','com.abby.elema.web.frontend.UserController','registerUser','Unknown','0:0:0:0:0:0:0:1','Unknown','success',780,'http://172.20.10.2:7070/api/user/register','localhost','username: chester;  password: 8915890;  email: 2627372340@qq.com;  '),(104,-1,'ANONYMOUS','mq send error','REGISTER_USER','2018-11-13 20:53:25','com.abby.elema.web.frontend.UserController','registerUser','Unknown','0:0:0:0:0:0:0:1','Unknown','mq send error',28180,'http://172.20.10.2:7070/api/user/register','localhost','username: chester;  password: 8915890;  email: 2627372340@qq.com;  '),(105,-1,'ANONYMOUS','registered successfully','UNKNOWN','2018-11-13 20:57:56','com.sun.proxy.$Proxy160','registerUser','Unknown','172.20.10.2','Robot/Spider','success',399,'http://172.20.10.2:8888/admin/mqproducer/send','localhost',''),(106,-1,'ANONYMOUS','success','REGISTER_USER','2018-11-13 20:57:55','com.abby.elema.web.frontend.UserController','registerUser','Unknown','0:0:0:0:0:0:0:1','Unknown','success',1842,'http://172.20.10.2:7070/api/user/register','localhost','username: chester;  password: 8915890;  email: 2627372340@qq.com;  '),(107,-1,'ANONYMOUS','registered successfully','UNKNOWN','2018-11-13 20:59:53','com.sun.proxy.$Proxy160','registerUser','Unknown','172.20.10.2','Robot/Spider','success',340,'http://172.20.10.2:8888/admin/mqproducer/send','localhost',''),(108,-1,'ANONYMOUS','success','REGISTER_USER','2018-11-13 20:59:53','com.abby.elema.web.frontend.UserController','registerUser','Unknown','0:0:0:0:0:0:0:1','Unknown','success',819,'http://172.20.10.2:7070/api/user/register','localhost','username: jianming;  password: 8915890;  email: 1358890401@qq.com;  '),(109,-1,'ANONYMOUS','registered successfully','UNKNOWN','2018-11-13 21:01:38','com.sun.proxy.$Proxy160','registerUser','Unknown','172.20.10.2','Robot/Spider','success',366,'http://172.20.10.2:8888/admin/mqproducer/send','localhost',''),(110,-1,'ANONYMOUS','success','REGISTER_USER','2018-11-13 21:01:38','com.abby.elema.web.frontend.UserController','registerUser','Unknown','0:0:0:0:0:0:0:1','Unknown','success',965,'http://172.20.10.2:7070/api/user/register','localhost','username: jianming;  password: 8915890;  email: 1358890401@qq.com;  '),(111,-1,'ANONYMOUS','registered successfully','UNKNOWN','2018-11-13 21:05:13','com.sun.proxy.$Proxy160','registerUser','Unknown','172.20.10.2','Robot/Spider','success',378,'http://172.20.10.2:8888/admin/mqproducer/send','localhost',''),(112,-1,'ANONYMOUS','success','REGISTER_USER','2018-11-13 21:05:13','com.abby.elema.web.frontend.UserController','registerUser','Unknown','0:0:0:0:0:0:0:1','Unknown','success',864,'http://172.20.10.2:7070/api/user/register','localhost','username: jianming;  password: 8915890;  email: 1358890401@qq.com;  '),(113,-1,'ANONYMOUS','generated validator image successfully','SEND_EMAIL','2018-11-13 21:06:31','com.abby.elema.web.frontend.ValidateCodeController','getImageValidateCode','Unknown','0:0:0:0:0:0:0:1','Unknown','success',398,'http://172.20.10.2:7070/api/getvalidate/image','localhost','testparam: zhagn;  test key: fdsf;  '),(114,-1,'ANONYMOUS','test success','TEST','2018-11-24 00:08:05','com.abby.elema.web.TestController','test','Unknown','0:0:0:0:0:0:0:1','Unknown','success',4,'http://172.20.10.2:7070/test/','localhost','username: abby;  '),(115,-1,'ANONYMOUS','generated validator image successfully','SEND_EMAIL','2018-12-20 22:51:13','com.abby.elema.web.frontend.ValidateCodeController','getImageValidateCode','Unknown','0:0:0:0:0:0:0:1','Unknown','success',1185,'http://10.5.89.187:7070/api/getvalidate/image','localhost','testparam: zhagn;  test key: fdsf;  '),(116,-1,'ANONYMOUS','generated validator image successfully','SEND_EMAIL','2019-01-03 22:23:04','com.abby.elema.web.frontend.ValidateCodeController','getImageValidateCode','Unknown','0:0:0:0:0:0:0:1','Unknown','success',546,'http://10.5.66.240:7070/api/getvalidate/image','localhost','');
/*!40000 ALTER TABLE `uaac_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uaac_user`
--

DROP TABLE IF EXISTS `uaac_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `uaac_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nickname` varchar(20) DEFAULT NULL,
  `sex` varchar(8) DEFAULT NULL,
  `region` varchar(20) DEFAULT NULL,
  `created_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `last_login_time` timestamp NULL DEFAULT NULL,
  `user_status` varchar(20) DEFAULT NULL,
  `user_group` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uaac_user`
--

LOCK TABLES `uaac_user` WRITE;
/*!40000 ALTER TABLE `uaac_user` DISABLE KEYS */;
INSERT INTO `uaac_user` VALUES (1,'abby','$2a$10$vJAQZb42mgwiCMhxyl4U1O6gH41GPBaiQqFQ18qppJLfw9vuGStnG',NULL,NULL,NULL,NULL,NULL,'2018-10-07 05:25:49','2018-10-07 05:25:49',NULL,'NORMAL','DEVELOPMENT'),(2,'guest','$2a$10$pIHInLW4/ITkkV9oA5./7O0zxoPvGHmX9xFOTakM8TuhUhII6j9nG',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'NORMAL','USER'),(53,'jianming','$2a$10$WJ4cdPIgVONB9wRTI2Fj.OMekBSWpcBMUQREY09./8zgjKa1UnfGa',NULL,'1358890401@qq.com',NULL,NULL,NULL,'2018-11-13 21:05:13',NULL,NULL,'NORMAL','');
/*!40000 ALTER TABLE `uaac_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uaac_user_role`
--

DROP TABLE IF EXISTS `uaac_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `uaac_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `user_role` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uaac_user_role`
--

LOCK TABLES `uaac_user_role` WRITE;
/*!40000 ALTER TABLE `uaac_user_role` DISABLE KEYS */;
INSERT INTO `uaac_user_role` VALUES (1,1,'SUPER_ADMIN'),(2,1,'DEVELOPER'),(3,2,'USER'),(4,53,'SELLER');
/*!40000 ALTER TABLE `uaac_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uaac_user_token`
--

DROP TABLE IF EXISTS `uaac_user_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `uaac_user_token` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `user_name` varchar(20) NOT NULL,
  `user_status` varchar(15) NOT NULL,
  `access_token` varchar(255) NOT NULL,
  `target_os` varchar(20) DEFAULT NULL,
  `target_browser` varchar(255) DEFAULT NULL,
  `target_ip` varchar(20) DEFAULT NULL,
  `login_time` timestamp NULL DEFAULT NULL,
  `login_location` varchar(100) DEFAULT NULL,
  `is_online` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uaac_user_token`
--

LOCK TABLES `uaac_user_token` WRITE;
/*!40000 ALTER TABLE `uaac_user_token` DISABLE KEYS */;
INSERT INTO `uaac_user_token` VALUES (77,53,'jianming','NORMAL','eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzY29wZSI6WyJhbGwiXSwiZXhwIjoxNTQ1NDMyMjk0LCJqdGkiOiI4N2M0ZWQzYy1kY2RlLTQwOTAtYmRmMC1kOWY3MzE2MjU2ZWIiLCJjbGllbnRfaWQiOiJlbGVtYS11YWFjIn0.Z8GbImto3D0YJbWkLe6ibBKJns7yt9BN6dkZeX0Oc8s',NULL,NULL,NULL,'2018-12-20 22:53:41',NULL,0),(78,1,'abby','NORMAL','eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzY29wZSI6WyJhbGwiXSwiZXhwIjoxNTQ2NjQwNTU3LCJqdGkiOiI4NjJhMTQ1Yi02NDgwLTQ4NzQtOTMwZS0xMmQ2NjU1MDcyYzYiLCJjbGllbnRfaWQiOiJlbGVtYS11YWFjIn0.MiJ-6d-MWnlpOsVbhb8xsfWvxqOCemVRdltzJM29NqQ',NULL,NULL,NULL,'2019-01-03 22:24:12',NULL,1);
/*!40000 ALTER TABLE `uaac_user_token` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-04 20:25:32
