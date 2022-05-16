-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: market2021
-- ------------------------------------------------------
-- Server version	8.0.23

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
-- Table structure for table `order_details`
--

DROP TABLE IF EXISTS `order_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_details` (
  `id_order` int NOT NULL AUTO_INCREMENT,
  `ordertime` datetime DEFAULT NULL,
  `purchase_price` int DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `id_commodity` int DEFAULT NULL,
  `name_commodity` varchar(45) DEFAULT NULL,
  `id_supplier` int DEFAULT NULL,
  `name_supplier` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `postcode` varchar(45) DEFAULT NULL,
  `purchaser_id` varchar(45) NOT NULL,
  `purchaser_name` varchar(45) DEFAULT NULL,
  `purchasetime` datetime DEFAULT NULL,
  `orderstate` varchar(45) DEFAULT NULL,
  `manage_id` varchar(45) DEFAULT NULL,
  `manage_name` varchar(45) DEFAULT NULL,
  `manage_time` datetime DEFAULT NULL,
  `manage_opinion` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id_order`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_details`
--

LOCK TABLES `order_details` WRITE;
/*!40000 ALTER TABLE `order_details` DISABLE KEYS */;
INSERT INTO `order_details` VALUES (1,'2021-08-23 00:00:00',12,200,1,'狗屎糖',1,'赵无极','甘井子区','116000','1','张三','2021-06-19 00:00:00','approved','2','李四','2021-06-21 00:00:00',NULL),(2,'2021-09-11 00:00:00',13,100,2,'六个核弹',2,'夏鲜生','流沙河区','116000','1','张三','2021-06-20 00:00:00','approved','2','李四','2021-06-21 00:00:00',NULL),(3,'2021-09-01 00:00:00',8,233,3,'乐糕',3,'吴作为','熊安心区','116000','1','张三','2021-06-18 00:00:00','unapproved',NULL,NULL,NULL,NULL),(5,NULL,12,133,8,'asdasdfasfdca',3,'','','','1','张三','2021-07-02 02:30:34','unapproved','','',NULL,''),(7,NULL,12,21,1,'狗屎糖',1,'','','','1','张三','2022-05-11 22:10:03','unapproved','','',NULL,'');
/*!40000 ALTER TABLE `order_details` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-16 18:53:36
