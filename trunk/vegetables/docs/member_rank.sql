# SQL-Front 5.1  (Build 4.16)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;


# Host: localhost    Database: vege
# ------------------------------------------------------
# Server version 5.5.16

#
# Dumping data for table member_rank
#

LOCK TABLES `member_rank` WRITE;
/*!40000 ALTER TABLE `member_rank` DISABLE KEYS */;
INSERT INTO `member_rank` (`id`,`create_date`,`modify_date`,`is_default`,`name`,`point`,`preferential_scale`) VALUES ('402881862be164ae012be16ab6fd0004','2010-10-25 11:24:48','2010-10-25 11:25:44',1,'普通会员',0,100);
INSERT INTO `member_rank` (`id`,`create_date`,`modify_date`,`is_default`,`name`,`point`,`preferential_scale`) VALUES ('402881862be164ae012be16affce0005','2010-10-25 11:25:07','2010-10-27 23:00:12',0,'高级会员',5000,96);
INSERT INTO `member_rank` (`id`,`create_date`,`modify_date`,`is_default`,`name`,`point`,`preferential_scale`) VALUES ('402881862bedd9fc012bee0b65170005','2010-10-27 22:15:45','2010-10-27 23:00:35',0,'白金会员',10000,93);
INSERT INTO `member_rank` (`id`,`create_date`,`modify_date`,`is_default`,`name`,`point`,`preferential_scale`) VALUES ('402881862bedd9fc012bee0bb9800006','2010-10-27 22:16:07','2010-10-27 23:00:43',0,'钻石会员',50000,90);
/*!40000 ALTER TABLE `member_rank` ENABLE KEYS */;
UNLOCK TABLES;

/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
