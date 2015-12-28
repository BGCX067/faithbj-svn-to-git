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
# Dumping data for table members
#

LOCK TABLES `members` WRITE;
/*!40000 ALTER TABLE `members` DISABLE KEYS */;
INSERT INTO `members` (`id`,`username`,`password`,`member_rank_id`,`create_date`,`modify_date`,`account_non_locked`,`credentials_non_expired`,`email`,`enabled`,`locked_date`,`login_date`,`login_failure_count`,`login_ip`,`password_recover_key`,`point`,`register_ip`,`safe_answer`,`safe_question`,`telephone`) VALUES ('402881862be164ae012be16ab6fd0004','feifei','feifei','402881862be164ae012be16ab6fd0004','2012-03-08 08:30:37','2012-03-28 08:30:37',1,1,'1',1,NULL,'2012-03-08 08:30:37',0,'127.0.0.1','1',1,'127.0.0.1','1','1','1');
/*!40000 ALTER TABLE `members` ENABLE KEYS */;
UNLOCK TABLES;

/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
