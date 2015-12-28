# MySQL-Front 5.0  (Build 1.0)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;


# Host: localhost    Database: shop
# ------------------------------------------------------
# Server version 5.1.55-community

#
# Table structure for table product
#

DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` varchar(32) NOT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifyDate` datetime DEFAULT NULL,
  `description` text,
  `freezeStore` int(11) NOT NULL,
  `htmlFilePath` varchar(255) NOT NULL,
  `isBest` bit(1) NOT NULL,
  `isHot` bit(1) NOT NULL,
  `isMarketable` bit(1) NOT NULL,
  `isNew` bit(1) NOT NULL,
  `marketPrice` decimal(15,5) NOT NULL,
  `metaDescription` text,
  `metaKeywords` text,
  `name` varchar(255) NOT NULL,
  `point` int(11) NOT NULL,
  `price` decimal(15,5) NOT NULL,
  `productImageListStore` text,
  `productSn` varchar(255) NOT NULL,
  `store` int(11) DEFAULT NULL,
  `weight` double NOT NULL,
  `weightUnit` int(11) NOT NULL,
  `brand_id` varchar(32) DEFAULT NULL,
  `productCategory_id` varchar(32) NOT NULL,
  `productType_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `productSn` (`productSn`),
  KEY `FK50C664CF738B3AF6` (`productType_id`),
  KEY `FK50C664CF59CF1676` (`productCategory_id`),
  KEY `FK50C664CFF378EF16` (`brand_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `product` VALUES ('8ac7440334cf97190134cfd30caa0009','2012-01-12 10:50:42','2012-01-12 10:50:42','',0,'/html/product_content/201201/026752eb7502484f854e749dd45c6ab0.html',b'1',b'1',b'1',b'1',1,'白萝卜','白萝卜','白萝卜',0,0.8,'[{\"id\":\"b17df768c3184f6aaf0e90e254bf3dbf\",\"thumbnailProductImagePath\":\"/upload/image/201201/b17df768c3184f6aaf0e90e254bf3dbf_thumbnail.jpg\",\"smallProductImagePath\":\"/upload/image/201201/b17df768c3184f6aaf0e90e254bf3dbf_small.jpg\",\"bigProductImagePath\":\"/upload/image/201201/b17df768c3184f6aaf0e90e254bf3dbf_big.jpg\",\"sourceProductImagePath\":\"/upload/image/201201/b17df768c3184f6aaf0e90e254bf3dbf.jpeg\"}]','SN_41E0B455A0C9',NULL,0,0,NULL,'402881882ba8753a012ba95b38d001ca',NULL);
INSERT INTO `product` VALUES ('8ac7440334cf97190134cfd51ad9000a','2012-01-12 10:52:56','2012-01-12 10:52:56','',0,'/html/product_content/201201/aab35d0327a24d4786685c8480441ee6.html',b'1',b'1',b'1',b'1',1,'青萝卜','青萝卜','青萝卜',0,0.8,'[{\"id\":\"f77864f7ba63441e939faab6f87289ca\",\"thumbnailProductImagePath\":\"/upload/image/201201/f77864f7ba63441e939faab6f87289ca_thumbnail.jpg\",\"smallProductImagePath\":\"/upload/image/201201/f77864f7ba63441e939faab6f87289ca_small.jpg\",\"bigProductImagePath\":\"/upload/image/201201/f77864f7ba63441e939faab6f87289ca_big.jpg\",\"sourceProductImagePath\":\"/upload/image/201201/f77864f7ba63441e939faab6f87289ca.jpeg\"}]','SN_30488CF7DEDC',NULL,0,0,NULL,'402881882ba8753a012ba95b38d001ca',NULL);
INSERT INTO `product` VALUES ('8ac7440334cfdbf80134cfdfda020000','2012-01-12 11:04:41','2012-01-12 11:04:41','',0,'/html/product_content/201201/c95c6ccdd2854d1288059ad9f02249a8.html',b'1',b'1',b'1',b'1',0,'胡萝卜','胡萝卜','胡萝卜',0,0,'[{\"id\":\"17f305d5f4864e2cb393360c087d7eab\",\"thumbnailProductImagePath\":\"/upload/image/201201/17f305d5f4864e2cb393360c087d7eab_thumbnail.jpg\",\"smallProductImagePath\":\"/upload/image/201201/17f305d5f4864e2cb393360c087d7eab_small.jpg\",\"bigProductImagePath\":\"/upload/image/201201/17f305d5f4864e2cb393360c087d7eab_big.jpg\",\"sourceProductImagePath\":\"/upload/image/201201/17f305d5f4864e2cb393360c087d7eab.jpeg\"}]','SN_394671D81932',NULL,0,0,NULL,'402881882ba8753a012ba95b38d001ca',NULL);
INSERT INTO `product` VALUES ('8ac7440334cfdbf80134cfe073d10001','2012-01-12 11:05:20','2012-01-12 11:05:20','',0,'/html/product_content/201201/d552051a4f494ebf939ff9e44d68eb0c.html',b'1',b'1',b'1',b'1',0,'韭菜','韭菜','韭菜',0,0,NULL,'SN_46BE33BC9EEE',NULL,0,0,NULL,'402881882bb2c00c012bb2ddb237000e',NULL);
INSERT INTO `product` VALUES ('8ac7440334cfdbf80134cfe0e3c20002','2012-01-12 11:05:49','2012-01-12 11:05:49','',0,'/html/product_content/201201/6248315122ff4a81b7b23422f5c0bdcf.html',b'1',b'1',b'1',b'1',0,'大葱','大葱','大葱',0,0,NULL,'SN_3E892484C04B',NULL,0,0,NULL,'402881882bb2c00c012bb2ddb237000e',NULL);
INSERT INTO `product` VALUES ('8ac7440334cfdbf80134cfe155e40003','2012-01-12 11:06:18','2012-01-12 11:06:18','',0,'/html/product_content/201201/8670c2616fa8413b8f7cfcfaa8ed200c.html',b'1',b'1',b'1',b'1',0,'洋葱','洋葱','洋葱',0,0,NULL,'SN_479546DFEDAB',NULL,0,0,NULL,'402881882bb2c00c012bb2ddb237000e',NULL);
INSERT INTO `product` VALUES ('8ac7440334cfdbf80134cfe1db8f0004','2012-01-12 11:06:52','2012-01-12 11:06:52','',0,'/html/product_content/201201/4d623dc6f5744a2a8723db2fc3d120f5.html',b'1',b'1',b'1',b'1',0,'大白菜','大白菜','大白菜',0,0,NULL,'SN_EAF5E0EC7268',NULL,0,0,NULL,'8ac7440334cf97190134cfcb882a0002',NULL);
INSERT INTO `product` VALUES ('8ac7440334cfdbf80134cfe2415e0005','2012-01-12 11:07:18','2012-01-12 11:07:18','',0,'/html/product_content/201201/5a65d714fc2e42798ece3915aeb2fdde.html',b'1',b'1',b'1',b'1',0,'小白菜','小白菜','小白菜',0,0,NULL,'SN_B609AFCA1C6C',NULL,0,0,NULL,'8ac7440334cf97190134cfcb882a0002',NULL);
INSERT INTO `product` VALUES ('8ac7440334cfdbf80134d1617e120006','2012-01-12 18:05:54','2012-01-12 18:05:54','',0,'/html/product_content/201201/f24f7442dcc34e91938efbbdd0824b5e.html',b'1',b'1',b'1',b'1',0,'黄瓜','黄瓜','黄瓜',0,0,NULL,'SN_6A72E4A7B1D0',NULL,0,0,NULL,'8ac7440334cf97190134cfcbd7350003',NULL);
INSERT INTO `product` VALUES ('8ac7440334cfdbf80134d161cd5b0007','2012-01-12 18:06:14','2012-01-12 18:06:14','',0,'/html/product_content/201201/aaea8f6ee835432d8c4517c223ce8f2a.html',b'1',b'1',b'1',b'1',0,'冬瓜','冬瓜','冬瓜',0,0,NULL,'SN_4513A12DEE8D',NULL,0,0,NULL,'8ac7440334cf97190134cfcbd7350003',NULL);
INSERT INTO `product` VALUES ('8ac7440334cfdbf80134d16225cb0008','2012-01-12 18:06:37','2012-01-12 18:06:37','',0,'/html/product_content/201201/e85e65459e504efca27fe9ccece88d66.html',b'1',b'1',b'1',b'1',0,'南瓜','南瓜','南瓜',0,0,NULL,'SN_7C36EA4F3A3B',NULL,0,0,NULL,'8ac7440334cf97190134cfcbd7350003',NULL);
INSERT INTO `product` VALUES ('8ac7440334cfdbf80134d162db110009','2012-01-12 18:07:23','2012-01-12 18:07:23','',0,'/html/product_content/201201/cd8cfe4ea2404424a2de3b7dc5f1464c.html',b'1',b'1',b'1',b'1',0,'西葫芦','西葫芦','西葫芦',0,0,NULL,'SN_2BA87F39C243',NULL,0,0,NULL,'8ac7440334cf97190134cfcbd7350003',NULL);
INSERT INTO `product` VALUES ('8ac7440334cfdbf80134d1636e87000a','2012-01-12 18:08:01','2012-01-12 18:08:01','',0,'/html/product_content/201201/dfb896976d6f41e882b94dcf0f0dac59.html',b'1',b'1',b'1',b'1',0,'丝瓜','丝瓜','丝瓜',0,0,NULL,'SN_BDA9A05EDDEC',NULL,0,0,NULL,'8ac7440334cf97190134cfcbd7350003',NULL);
INSERT INTO `product` VALUES ('8ac7440334cfdbf80134d163bd63000b','2012-01-12 18:08:21','2012-01-12 18:08:21','',0,'/html/product_content/201201/2b622e0c272d4e98b39b92527222c766.html',b'1',b'1',b'1',b'1',0,'苦瓜','苦瓜','苦瓜',0,0,NULL,'SN_ABFB3105DAEE',NULL,0,0,NULL,'8ac7440334cf97190134cfcbd7350003',NULL);
INSERT INTO `product` VALUES ('8ac7440334cfdbf80134d16421ca000c','2012-01-12 18:08:47','2012-01-12 18:10:30','',0,'/html/product_content/201201/28d607b379424a488d766d597c09890e.html',b'1',b'1',b'1',b'1',0,'菜瓜','菜瓜','菜瓜',0,0,'[{\"id\":\"b5afb9abb49c422c957c29473644bb9f\",\"thumbnailProductImagePath\":\"/upload/image/201201/b5afb9abb49c422c957c29473644bb9f_thumbnail.jpg\",\"smallProductImagePath\":\"/upload/image/201201/b5afb9abb49c422c957c29473644bb9f_small.jpg\",\"bigProductImagePath\":\"/upload/image/201201/b5afb9abb49c422c957c29473644bb9f_big.jpg\",\"sourceProductImagePath\":\"/upload/image/201201/b5afb9abb49c422c957c29473644bb9f.jpeg\"}]','SN_A710D4A4311E',NULL,0,0,NULL,'8ac7440334cf97190134cfcbd7350003',NULL);
INSERT INTO `product` VALUES ('8ac7440334cfdbf80134d169856e000e','2012-01-12 18:14:40','2012-01-12 18:14:40','',0,'/html/product_content/201201/45bc6968adf64acebf005d564a117524.html',b'1',b'1',b'1',b'1',0,'番茄','番茄','番茄',0,0,NULL,'SN_6288B4F857DD',NULL,0,0,NULL,'8ac7440334cfdbf80134d166a5d5000d',NULL);
INSERT INTO `product` VALUES ('8ac7440334cfdbf80134d169d727000f','2012-01-12 18:15:01','2012-01-12 18:15:01','',0,'/html/product_content/201201/69f50f76d9044c38894c8c2bff8e1968.html',b'1',b'1',b'1',b'1',0,'茄子','茄子','茄子',0,0,NULL,'SN_8D3135340ACC',NULL,0,0,NULL,'8ac7440334cfdbf80134d166a5d5000d',NULL);
INSERT INTO `product` VALUES ('8ac7440334cfdbf80134d16bbdca0010','2012-01-12 18:17:06','2012-01-12 18:17:06','',0,'/html/product_content/201201/ff6bf469aedb49d380b200099bf473f5.html',b'1',b'1',b'1',b'1',0,'辣椒','辣椒','辣椒',0,0,NULL,'SN_1C078440BD0D',NULL,0,0,NULL,'8ac7440334cfdbf80134d166a5d5000d',NULL);
INSERT INTO `product` VALUES ('8ac7440334cfdbf80134d1722d4f0011','2012-01-12 18:24:08','2012-01-12 18:24:08','',0,'/html/product_content/201201/aa56325d59d74bc499111a07e1fcbd7e.html',b'1',b'1',b'1',b'1',0,'青椒','青椒','青椒',0,0,NULL,'SN_125BDAD83C66',NULL,0,0,NULL,'8ac7440334cfdbf80134d166a5d5000d',NULL);
INSERT INTO `product` VALUES ('8ac7440334cfdbf80134d1737ce10012','2012-01-12 18:25:33','2012-01-12 18:25:33','',0,'/html/product_content/201201/98213a7fe3cb4ccbae5b89bf3943e880.html',b'1',b'1',b'1',b'1',0,'绿豆芽','绿豆芽','绿豆芽',0,0,NULL,'SN_287C3C3610A9',NULL,0,0,NULL,'8ac7440334cf97190134cfcc88c20005',NULL);
INSERT INTO `product` VALUES ('8ac7440334cfdbf80134d17429bb0013','2012-01-12 18:26:18','2012-01-12 18:26:18','',0,'/html/product_content/201201/44ea28c273754c8da3a57b6cbd8f8873.html',b'1',b'1',b'1',b'1',0,'黄豆芽','黄豆芽','黄豆芽',0,0,NULL,'SN_39565C745A9E',NULL,0,0,NULL,'8ac7440334cf97190134cfcc88c20005',NULL);
INSERT INTO `product` VALUES ('8ac7440334cfdbf80134d1797ab20015','2012-01-12 18:32:06','2012-01-12 18:32:06','',0,'/html/product_content/201201/c2d01bfa721440e08708bb6183921097.html',b'1',b'1',b'1',b'1',0,'香椿芽','香椿芽','香椿芽',0,0,NULL,'SN_8CF6D06A4863',NULL,0,0,NULL,'8ac7440334cf97190134cfcc88c20005',NULL);
INSERT INTO `product` VALUES ('8ac7440334cfdbf80134d17a62530016','2012-01-12 18:33:05','2012-01-12 18:33:05','',0,'/html/product_content/201201/93d23133f29a4576aacbe26ae030acd6.html',b'1',b'1',b'1',b'1',0,'四季豆','四季豆','四季豆',0,0,NULL,'SN_72C58DA26CE7',NULL,0,0,NULL,'8ac7440334cf97190134cfcd2dbc0007',NULL);
INSERT INTO `product` VALUES ('8ac7440334cfdbf80134d17b08080017','2012-01-12 18:33:48','2012-01-12 18:33:48','',0,'/html/product_content/201201/1b2b2caf53964df58c7a0d0fe2b08223.html',b'1',b'1',b'1',b'1',0,'长豇豆','长豇豆','长豇豆',0,0,NULL,'SN_191893458374',NULL,0,0,NULL,'8ac7440334cf97190134cfcd2dbc0007',NULL);
INSERT INTO `product` VALUES ('8ac7440334cfdbf80134d17ba32f0018','2012-01-12 18:34:28','2012-01-12 18:34:28','',0,'/html/product_content/201201/c7e2a83cb50d40c8bbc776dddded25ba.html',b'1',b'1',b'1',b'1',0,'毛豆','毛豆','毛豆',0,0,NULL,'SN_CEE3A151A2AF',NULL,0,0,NULL,'8ac7440334cf97190134cfcd2dbc0007',NULL);
INSERT INTO `product` VALUES ('8ac7440334cfdbf80134d17d545a001a','2012-01-12 18:36:18','2012-01-12 18:36:18','',0,'/html/product_content/201201/fe0ed12ba2234665af30cb679a39a998.html',b'1',b'1',b'1',b'1',0,'木耳','木耳','木耳',0,0,NULL,'SN_2A993EB0059C',NULL,0,0,NULL,'8ac7440334cfdbf80134d17cb51c0019',NULL);
INSERT INTO `product` VALUES ('8ac7440334cfdbf80134d17e1885001b','2012-01-12 18:37:09','2012-01-12 18:37:09','',0,'/html/product_content/201201/865c24fac0ae442997f3e30ad5178e50.html',b'1',b'1',b'1',b'1',0,'金针菇','金针菇','金针菇',0,0,NULL,'SN_4653F057A6A5',NULL,0,0,NULL,'8ac7440334cfdbf80134d17cb51c0019',NULL);
INSERT INTO `product` VALUES ('8ac7440334cfdbf80134d17ea246001c','2012-01-12 18:37:44','2012-01-12 18:37:44','',0,'/html/product_content/201201/1baa80d5932b4a69a1db425c8f4f45ff.html',b'1',b'1',b'1',b'1',0,'香菇','香菇','香菇',0,0,NULL,'SN_9F744C36B87A',NULL,0,0,NULL,'8ac7440334cfdbf80134d17cb51c0019',NULL);
INSERT INTO `product` VALUES ('8ac7440334d1ea180134d1f4cea80000','2012-01-12 20:46:49','2012-01-12 20:46:49','',0,'/html/product_content/201201/05e935be0ddb4449b316fd3e14fac19e.html',b'0',b'0',b'1',b'1',0,'水萝卜','水萝卜','水萝卜',0,0,NULL,'SN_55B114DF9AF6',NULL,0,0,NULL,'402881882ba8753a012ba95b38d001ca',NULL);
INSERT INTO `product` VALUES ('8ac7440334d1ea180134d1f55de70001','2012-01-12 20:47:25','2012-01-12 20:47:25','',0,'/html/product_content/201201/e55e51e03eee4b3aa2361584a9498311.html',b'1',b'1',b'1',b'1',0,'甘蓝','甘蓝','甘蓝',0,0,NULL,'SN_3E42E8F41D57',NULL,0,0,NULL,'402881882ba8753a012ba95b38d001ca',NULL);
INSERT INTO `product` VALUES ('8ac7440334d1ea180134d1f5ed470002','2012-01-12 20:48:02','2012-01-12 20:48:02','',0,'/html/product_content/201201/1504775c86a740eeb7f4e319e383c234.html',b'1',b'1',b'1',b'1',0,'马铃薯','马铃薯','马铃薯',0,0,NULL,'SN_EAA07493D5F9',NULL,0,0,NULL,'402881882ba8753a012ba95b38d001ca',NULL);
INSERT INTO `product` VALUES ('8ac7440334d1ea180134d1f71b760003','2012-01-12 20:49:19','2012-01-12 20:49:19','',0,'/html/product_content/201201/74b138d706f6496b94e4856d6cadbf55.html',b'1',b'1',b'1',b'1',0,'姜','姜','姜',0,0,NULL,'SN_D9E0FD3B4A8A',NULL,0,0,NULL,'402881882ba8753a012ba95b38d001ca',NULL);
INSERT INTO `product` VALUES ('8ac7440334d1ea180134d1f8cb680004','2012-01-12 20:51:10','2012-01-12 20:51:10','',0,'/html/product_content/201201/3fa5222875064659aed98879aae860f8.html',b'0',b'0',b'1',b'0',0,'芋头','芋头','芋头',0,0,NULL,'SN_9CDB5194134C',NULL,0,0,NULL,'402881882ba8753a012ba95b38d001ca',NULL);
INSERT INTO `product` VALUES ('8ac7440334d1ea180134d1f9ebdc0005','2012-01-12 20:52:24','2012-01-12 20:52:24','',0,'/html/product_content/201201/993203b89d2147c99d94429e31e1cbd5.html',b'1',b'1',b'1',b'1',0,'山药','山药','山药',0,0,NULL,'SN_71F0CC0A355A',NULL,0,0,NULL,'402881882ba8753a012ba95b38d001ca',NULL);
INSERT INTO `product` VALUES ('8ac7440334d1ea180134d1fa46dd0006','2012-01-12 20:52:47','2012-01-12 20:52:47','',0,'/html/product_content/201201/da83cfc341cb4fa5870f737d5594883d.html',b'0',b'0',b'1',b'0',0,'甘薯','甘薯','甘薯',0,0,NULL,'SN_2B186DFB2E48',NULL,0,0,NULL,'402881882ba8753a012ba95b38d001ca',NULL);
INSERT INTO `product` VALUES ('8ac7440334d1ea180134d1fad64b0007','2012-01-12 20:53:24','2012-01-12 20:53:24','',0,'/html/product_content/201201/9f5fa7965cdd41279a18d066aca27eb8.html',b'0',b'0',b'1',b'0',0,'大蒜','大蒜','大蒜',0,0,NULL,'SN_3931633B8216',NULL,0,0,NULL,'402881882bb2c00c012bb2ddb237000e',NULL);
INSERT INTO `product` VALUES ('8ac7440334d1ea180134d1fb5f420008','2012-01-12 20:53:59','2012-01-12 20:53:59','',0,'/html/product_content/201201/89db6477476d4893b1df53d73507e538.html',b'0',b'0',b'1',b'0',0,'蒜苔','蒜苔','蒜苔',0,0,NULL,'SN_2CE8D248F9E2',NULL,0,0,NULL,'402881882bb2c00c012bb2ddb237000e',NULL);
INSERT INTO `product` VALUES ('8ac7440334d1ea180134d1fbd4050009','2012-01-12 20:54:29','2012-01-12 20:54:29','',0,'/html/product_content/201201/52dcaf2448ad4bd4a9970f40d2fd3a42.html',b'0',b'0',b'1',b'0',0,'蒜苗','蒜苗','蒜苗',0,0,NULL,'SN_50730900F48F',NULL,0,0,NULL,'402881882bb2c00c012bb2ddb237000e',NULL);
INSERT INTO `product` VALUES ('8ac7440334d1ea180134d1fc4397000a','2012-01-12 20:54:57','2012-01-12 20:54:57','',0,'/html/product_content/201201/368cd2371a874b21a26a71865e587887.html',b'0',b'0',b'1',b'0',0,'香葱','香葱','香葱',0,0,NULL,'SN_3A3FE3EB9799',NULL,0,0,NULL,'402881882bb2c00c012bb2ddb237000e',NULL);
INSERT INTO `product` VALUES ('8ac7440334d1ea180134d1fcd596000b','2012-01-12 20:55:35','2012-01-12 20:55:35','',0,'/html/product_content/201201/027852eb14394acd854a519c9732aa8e.html',b'0',b'0',b'1',b'0',0,'菠菜','菠菜','菠菜',0,0,NULL,'SN_096906515FB3',NULL,0,0,NULL,'8ac7440334cf97190134cfcb882a0002',NULL);
INSERT INTO `product` VALUES ('8ac7440334d1ea180134d1fd1629000c','2012-01-12 20:55:51','2012-01-12 20:55:51','',0,'/html/product_content/201201/061fb212f83c440a8b465516911dd45d.html',b'0',b'0',b'1',b'0',0,'莴苣','莴苣','莴苣',0,0,NULL,'SN_32AEF3FB9C13',NULL,0,0,NULL,'8ac7440334cf97190134cfcb882a0002',NULL);
INSERT INTO `product` VALUES ('8ac7440334d1ea180134d1fd79c5000d','2012-01-12 20:56:17','2012-01-12 20:56:17','',0,'/html/product_content/201201/166fe345aacb4b9e8c97e3e5ac16644b.html',b'0',b'0',b'1',b'0',0,'芹菜','芹菜','芹菜',0,0,NULL,'SN_95F57B28AAFE',NULL,0,0,NULL,'8ac7440334cf97190134cfcb882a0002',NULL);
INSERT INTO `product` VALUES ('8ac7440334d1ea180134d1fde1e6000e','2012-01-12 20:56:43','2012-01-12 20:56:43','',0,'/html/product_content/201201/2ebf0d9e88884fb29a0008c75fe442ce.html',b'0',b'0',b'1',b'0',0,'空心菜','空心菜','空心菜',0,0,NULL,'SN_4E21B044413A',NULL,0,0,NULL,'8ac7440334cf97190134cfcb882a0002',NULL);
INSERT INTO `product` VALUES ('8ac7440334d1ea180134d1ff140d000f','2012-01-12 20:58:02','2012-01-12 20:58:02','',0,'/html/product_content/201201/820e22ee35d342e7b627ee4f970827bc.html',b'0',b'0',b'1',b'0',0,'苋菜','苋菜','苋菜',0,0,NULL,'SN_AB9074ABE5F1',NULL,0,0,NULL,'8ac7440334cf97190134cfcb882a0002',NULL);
INSERT INTO `product` VALUES ('8ac7440334d1ea180134d1ff56550010','2012-01-12 20:58:19','2012-01-12 20:58:19','',0,'/html/product_content/201201/1e94bdc5600b4cd386116e71ed54ffd6.html',b'0',b'0',b'1',b'0',0,'茼蒿','茼蒿','茼蒿',0,0,NULL,'SN_05EC44884CA2',NULL,0,0,NULL,'8ac7440334cf97190134cfcb882a0002',NULL);
INSERT INTO `product` VALUES ('8ac7440334d1ea180134d1ffaeb60011','2012-01-12 20:58:41','2012-01-12 20:58:41','',0,'/html/product_content/201201/9f4195c07e1e499db67153c2c79c67e8.html',b'0',b'0',b'1',b'0',0,'香菜','香菜','香菜',0,0,NULL,'SN_7E46637F403C',NULL,0,0,NULL,'8ac7440334cf97190134cfcb882a0002',NULL);
INSERT INTO `product` VALUES ('8ac7440334d1ea180134d1fff0150012','2012-01-12 20:58:58','2012-01-12 20:58:58','',0,'/html/product_content/201201/7b6cf1b5608e4679af78f604d385df0f.html',b'0',b'0',b'1',b'0',0,'茴香','茴香','茴香',0,0,NULL,'SN_E2B0026BF9FB',NULL,0,0,NULL,'8ac7440334cf97190134cfcb882a0002',NULL);
INSERT INTO `product` VALUES ('8ac7440334d1ea180134d2002f410013','2012-01-12 20:59:14','2012-01-12 20:59:14','',0,'/html/product_content/201201/28543c9ceba14b3eac9e54f688e0182a.html',b'0',b'0',b'1',b'0',0,'荠菜','荠菜','荠菜',0,0,NULL,'SN_B482863F3530',NULL,0,0,NULL,'8ac7440334cf97190134cfcb882a0002',NULL);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

#
#  Foreign keys for table product
#

ALTER TABLE `product`
ADD CONSTRAINT `FK50C664CF59CF1676` FOREIGN KEY (`productCategory_id`) REFERENCES `productcategory` (`id`),
  ADD CONSTRAINT `FK50C664CF738B3AF6` FOREIGN KEY (`productType_id`) REFERENCES `producttype` (`id`),
  ADD CONSTRAINT `FK50C664CFF378EF16` FOREIGN KEY (`brand_id`) REFERENCES `brand` (`id`);


/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
