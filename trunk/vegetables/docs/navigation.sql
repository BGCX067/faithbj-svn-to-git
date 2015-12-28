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
# Dumping data for table navigation
#

LOCK TABLES `navigation` WRITE;
/*!40000 ALTER TABLE `navigation` DISABLE KEYS */;
INSERT INTO `navigation` VALUES ('4028818330556c7c013055a9da3c0072','2012-03-21','2012-03-21',0,1,'常见问题',2,0,'${base}/shop/article/questions');
INSERT INTO `navigation` VALUES ('4028818330556c7c013055aa11a80073','2012-03-21','2012-03-21',0,1,'隐私申明',2,0,'${base}/shop/article/privacy');
INSERT INTO `navigation` VALUES ('4028818330556c7c013055aa57d90074','2012-03-21','2012-03-21',0,1,'交易条款',2,0,'${base}/shop/article/terms');
INSERT INTO `navigation` VALUES ('4028818330556c7c013055aa83da0075','2012-03-21','2012-03-21',0,1,'新手上路',2,0,'${base}/shop/article/newbie');
INSERT INTO `navigation` VALUES ('4028818330556c7c013055aaec390077','2012-03-21','2012-03-21',0,1,'支付方式',2,0,'${base}/shop/article/payment');
INSERT INTO `navigation` VALUES ('4028818330556c7c013055ab1f6e0078','2012-03-21','2012-03-21',0,1,'配送方式',2,0,'${base}/shop/article/shipping_method');
INSERT INTO `navigation` VALUES ('4028818330556c7c013055ab4c7a007a','2012-03-21','2012-03-21',0,1,'售后服务',2,0,'${base}/shop/article/service');
INSERT INTO `navigation` VALUES ('4028818330556c7c013055ab8e26007b','2012-03-21','2012-03-21',0,1,'服务保证',2,0,'${base}/shop/article/service_guarantee');
INSERT INTO `navigation` VALUES ('4028818330556c7c013055abd34d007c','2012-03-21','2012-03-21',0,1,'网站制度',2,0,'${base}/shop/article/website_system');
INSERT INTO `navigation` VALUES ('4028818330556c7c013055ac1509007d','2012-03-21','2012-03-21',0,1,'关于我们',2,0,'${base}/shop/article/about_us');
INSERT INTO `navigation` VALUES ('4028818330556c7c013055ad1090007e','2012-03-21','2012-03-21',0,1,'帮助中心',2,0,'${base}/shop/article/help');
INSERT INTO `navigation` VALUES ('4028818330556c7c013055ad500a007f','2012-03-21','2012-03-21',0,1,'购物指南',2,0,'${base}/shop/article/shopping_guide');
INSERT INTO `navigation` VALUES ('402881863058a9e301305934f02a0001','2012-03-21','2012-03-21',0,1,'收藏本站',0,1,'javascript: addFavorite(\'${setting.shopUrl}\',\'${setting.shopnavigation_name}\')');
INSERT INTO `navigation` VALUES ('40289181303478af013036a0f05d007f','2012-03-21','2012-03-21',0,1,'帮助中心',0,3,'${base}/shop/article/list');
INSERT INTO `navigation` VALUES ('40289181303478af013036a4b97c0080','2012-03-21','2012-03-21',0,1,'首页',1,1,'${base}/index');
INSERT INTO `navigation` VALUES ('40289181303478af013036e02f200095','2012-03-21','2012-03-21',0,1,'土地承包',1,2,'${base}/land');
INSERT INTO `navigation` VALUES ('40289181303478af013036e075190096','2012-03-21','2012-03-21',0,1,'定期配送',1,3,'${base}/shop/goods/mobile_digita');
INSERT INTO `navigation` VALUES ('40289181303478af013036e0f80e0097','2012-03-21','2012-03-21',0,0,'时尚影音',1,4,'${base}/shop/goods/fashion_video');
INSERT INTO `navigation` VALUES ('40289181303478af013036e1261c0098','2012-03-21','2012-03-21',0,1,'新闻中心',1,6,'${base}/article');
INSERT INTO `navigation` VALUES ('40289181303478af013036e164350099','2012-03-21','2012-03-21',0,0,'在线留言',1,7,'${base}/shop/leavemessage');
INSERT INTO `navigation` VALUES ('40289181303478af013036e2e4e9009b','2012-03-21','2012-03-21',0,1,'关于我们',0,2,'${base}/shop/article/about_us');
INSERT INTO `navigation` VALUES ('40289181303478af013036febc2600a3','2012-03-21','2012-03-21',0,1,'零售蔬菜',1,5,'${base}/shop/article/help');
/*!40000 ALTER TABLE `navigation` ENABLE KEYS */;
UNLOCK TABLES;

/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;


update navigation set url='${base}/product/productCategory' where id = '40289181303478af013036febc2600a3'