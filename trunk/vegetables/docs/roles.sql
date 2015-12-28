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
# Dumping data for table roles
#

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` (`id`,`create_date`,`modify_date`,`description`,`is_system`,`name`,`value`) VALUES ('402881862bec2a21012bec2b70510002','2010-10-27 13:31:31','2010-10-27 13:31:31','拥有最高后台管理权限',1,'管理员','ROLE_ADMIN');
INSERT INTO `roles` (`id`,`create_date`,`modify_date`,`description`,`is_system`,`name`,`value`) VALUES ('402881862bec2a21012bec2b70510003','2012-02-29 13:31:31','2012-02-29 13:31:31','商品管理',1,'商品管理','ROLE_GOODS');
INSERT INTO `roles` (`id`,`create_date`,`modify_date`,`description`,`is_system`,`name`,`value`) VALUES ('402881862bec2a21012bec2b70510004','2012-02-29 13:31:31','2012-02-29 13:31:31','到货通知',1,'到货通知','ROLE_GOODS_NOTIFY');
INSERT INTO `roles` (`id`,`create_date`,`modify_date`,`description`,`is_system`,`name`,`value`) VALUES ('402881862bec2a21012bec2b70510005','2012-02-29 13:31:31','2012-02-29 13:31:31','商品分类管理',1,'商品分类管理','ROLE_GOODS_CATEGORY');
INSERT INTO `roles` (`id`,`create_date`,`modify_date`,`description`,`is_system`,`name`,`value`) VALUES ('402881862bec2a21012bec2b70510006','2012-02-29 13:31:31','2012-02-29 13:31:31','商品类型管理',1,'商品类型管理','ROLE_GOODS_TYPE');
INSERT INTO `roles` (`id`,`create_date`,`modify_date`,`description`,`is_system`,`name`,`value`) VALUES ('402881862bec2a21012bec2b70510007','2012-02-29 13:31:31','2012-02-29 13:31:31','商品规格管理',1,'商品规格管理','ROLE_SPECIFICATION');
INSERT INTO `roles` (`id`,`create_date`,`modify_date`,`description`,`is_system`,`name`,`value`) VALUES ('402881862bec2a21012bec2b70510008','2012-02-29 13:31:31','2012-02-29 13:31:31','商品品牌管理',1,'商品品牌管理','ROLE_BRAND');
INSERT INTO `roles` (`id`,`create_date`,`modify_date`,`description`,`is_system`,`name`,`value`) VALUES ('402881862bec2a21012bec2b70510009','2012-02-29 13:31:31','2012-02-29 13:31:31','订单管理',1,'订单管理','ROLE_ORDER');
INSERT INTO `roles` (`id`,`create_date`,`modify_date`,`description`,`is_system`,`name`,`value`) VALUES ('402881862bec2a21012bec2b70510010','2012-02-29 13:31:31','2012-02-29 13:31:31','收款管理',1,'收款管理','ROLE_PAYMENT');
INSERT INTO `roles` (`id`,`create_date`,`modify_date`,`description`,`is_system`,`name`,`value`) VALUES ('402881862bec2a21012bec2b70510011','2012-02-29 13:31:31','2012-02-29 13:31:31','退款管理',1,'退款管理','ROLE_REFUND');
INSERT INTO `roles` (`id`,`create_date`,`modify_date`,`description`,`is_system`,`name`,`value`) VALUES ('402881862bec2a21012bec2b70510012','2012-02-29 13:31:31','2012-02-29 13:31:31','发货管理',1,'发货管理','ROLE_SHIPPING');
INSERT INTO `roles` (`id`,`create_date`,`modify_date`,`description`,`is_system`,`name`,`value`) VALUES ('402881862bec2a21012bec2b70510013','2012-02-29 13:31:31','2012-02-29 13:31:31','退货管理',1,'退货管理','ROLE_RESHIP');
INSERT INTO `roles` (`id`,`create_date`,`modify_date`,`description`,`is_system`,`name`,`value`) VALUES ('402881862bec2a21012bec2b70510014','2012-02-29 13:31:31','2012-02-29 13:31:31','发货点管理',1,'发货点管理','ROLE_DELIVERY_CENTER');
INSERT INTO `roles` (`id`,`create_date`,`modify_date`,`description`,`is_system`,`name`,`value`) VALUES ('402881862bec2a21012bec2b70510016','2012-02-29 13:31:31','2012-02-29 13:31:31','会员等级管理',1,'会员等级管理','ROLE_MEMBER_RANK');
INSERT INTO `roles` (`id`,`create_date`,`modify_date`,`description`,`is_system`,`name`,`value`) VALUES ('402881862bec2a21012bec2b70510017','2012-02-29 13:31:31','2012-02-29 13:31:31','会员注册项管理',1,'会员注册项管理','ROLE_MEMBER_ATTRIBUTE');
INSERT INTO `roles` (`id`,`create_date`,`modify_date`,`description`,`is_system`,`name`,`value`) VALUES ('402881862bec2a21012bec2b70510018','2012-02-29 13:31:31','2012-02-29 13:31:31','商品评论管理',1,'商品评论管理','ROLE_COMMENT');
INSERT INTO `roles` (`id`,`create_date`,`modify_date`,`description`,`is_system`,`name`,`value`) VALUES ('402881862bec2a21012bec2b70510019','2012-02-29 13:31:31','2012-02-29 13:31:31','在线留言管理',1,'在线留言管理','ROLE_LEAVE_MESSAGE');
INSERT INTO `roles` (`id`,`create_date`,`modify_date`,`description`,`is_system`,`name`,`value`) VALUES ('402881862bec2a21012bec2b70510020','2012-02-29 13:31:31','2012-02-29 13:31:31','导航管理',1,'导航管理','ROLE_NAVIGATION');
INSERT INTO `roles` (`id`,`create_date`,`modify_date`,`description`,`is_system`,`name`,`value`) VALUES ('402881862bec2a21012bec2b70510021','2012-02-29 13:31:31','2012-02-29 13:31:31','文章管理',1,'文章管理','ROLE_ARTICLEE');
INSERT INTO `roles` (`id`,`create_date`,`modify_date`,`description`,`is_system`,`name`,`value`) VALUES ('402881862bec2a21012bec2b70510022','2012-02-29 13:31:31','2012-02-29 13:31:31','文章分类管理',1,'文章分类管理','ROLE_ARTICLE_CATEGORY');
INSERT INTO `roles` (`id`,`create_date`,`modify_date`,`description`,`is_system`,`name`,`value`) VALUES ('402881862bec2a21012bec2b70510023','2012-02-29 13:31:31','2012-02-29 13:31:31','友情链接管理',1,'友情链接管理','ROLE_FRIEND_LINK');
INSERT INTO `roles` (`id`,`create_date`,`modify_date`,`description`,`is_system`,`name`,`value`) VALUES ('402881862bec2a21012bec2b70510024','2012-02-29 13:31:31','2012-02-29 13:31:31','页面模板管理',1,'页面模板管理','ROLE_PAGE_TEMPLATE');
INSERT INTO `roles` (`id`,`create_date`,`modify_date`,`description`,`is_system`,`name`,`value`) VALUES ('402881862bec2a21012bec2b70510025','2012-02-29 13:31:31','2012-02-29 13:31:31','邮件模板管理',1,'邮件模板管理','ROLE_MAIL_TEMPLATE');
INSERT INTO `roles` (`id`,`create_date`,`modify_date`,`description`,`is_system`,`name`,`value`) VALUES ('402881862bec2a21012bec2b70510026','2012-02-29 13:31:31','2012-02-29 13:31:31','打印模板管理',1,'打印模板管理','ROLE_PRINT_TEMPLATE');
INSERT INTO `roles` (`id`,`create_date`,`modify_date`,`description`,`is_system`,`name`,`value`) VALUES ('402881862bec2a21012bec2b70510027','2012-02-29 13:31:31','2012-02-29 13:31:31','缓存管理',1,'缓存管理','ROLE_CACHE');
INSERT INTO `roles` (`id`,`create_date`,`modify_date`,`description`,`is_system`,`name`,`value`) VALUES ('402881862bec2a21012bec2b70510028','2012-02-29 13:31:31','2012-02-29 13:31:31','生成静态管理',1,'生成静态管理','ROLE_BUILD_HTML');
INSERT INTO `roles` (`id`,`create_date`,`modify_date`,`description`,`is_system`,`name`,`value`) VALUES ('402881862bec2a21012bec2b70510030','2012-02-29 13:31:31','2012-02-29 13:31:31','角色管理',1,'角色管理','ROLE_ROLE');
INSERT INTO `roles` (`id`,`create_date`,`modify_date`,`description`,`is_system`,`name`,`value`) VALUES ('402881862bec2a21012bec2b70510031','2012-02-29 13:31:31','2012-02-29 13:31:31','站内消息管理',1,'站内消息管理','ROLE_MESSAGE');
INSERT INTO `roles` (`id`,`create_date`,`modify_date`,`description`,`is_system`,`name`,`value`) VALUES ('402881862bec2a21012bec2b70510032','2012-02-29 13:31:31','2012-02-29 13:31:31','日志管理',1,'日志管理','ROLE_LOG');
INSERT INTO `roles` (`id`,`create_date`,`modify_date`,`description`,`is_system`,`name`,`value`) VALUES ('402881862bec2a21012bec2b70510033','2012-02-29 13:31:31','2012-02-29 13:31:31','系统设置',1,'系统设置','ROLE_SETTING');
INSERT INTO `roles` (`id`,`create_date`,`modify_date`,`description`,`is_system`,`name`,`value`) VALUES ('402881862bec2a21012bec2b70510034','2012-02-29 13:31:31','2012-02-29 13:31:31','在线客服',1,'在线客服','ROLE_INSTANT_MESSAGING');
INSERT INTO `roles` (`id`,`create_date`,`modify_date`,`description`,`is_system`,`name`,`value`) VALUES ('402881862bec2a21012bec2b70510035','2012-02-29 13:31:31','2012-02-29 13:31:31','支付方式管理',1,'支付方式管理','ROLE_PAYMENT_CONFIG');
INSERT INTO `roles` (`id`,`create_date`,`modify_date`,`description`,`is_system`,`name`,`value`) VALUES ('402881862bec2a21012bec2b70510036','2012-02-29 13:31:31','2012-02-29 13:31:31','配送方式管理',1,'配送方式管理','ROLE_DELIVERY_TYPE');
INSERT INTO `roles` (`id`,`create_date`,`modify_date`,`description`,`is_system`,`name`,`value`) VALUES ('402881862bec2a21012bec2b70510037','2012-02-29 13:31:31','2012-02-29 13:31:31','地区管理',1,'地区管理','ROLE_AREA');
INSERT INTO `roles` (`id`,`create_date`,`modify_date`,`description`,`is_system`,`name`,`value`) VALUES ('402881862bec2a21012bec2b70510038','2012-02-29 13:31:31','2012-02-29 13:31:31','物流公司管理',1,'物流公司管理','ROLE_DELIVERY_CORP');
INSERT INTO `roles` (`id`,`create_date`,`modify_date`,`description`,`is_system`,`name`,`value`) VALUES ('402881862bec2a21012bec2b70510039','2012-03-25 13:31:31','2012-03-25 13:31:31','土地租赁用户',1,'土地租赁','ROLE_RENT');
INSERT INTO `roles` (`id`,`create_date`,`modify_date`,`description`,`is_system`,`name`,`value`) VALUES ('402881862bec2a21012bec2b70510040','2012-03-25 13:31:31','2012-03-25 13:31:31','土地配送',1,'土地配送','ROLE_DELIVERY');
INSERT INTO `roles` (`id`,`create_date`,`modify_date`,`description`,`is_system`,`name`,`value`) VALUES ('402881862bec2a21012bec2b70510041','2012-03-25 13:31:31','2012-03-25 13:31:31','超级管理员',1,'超级管理员','ROLE_SUPERVISOR');
INSERT INTO `roles` (`id`,`create_date`,`modify_date`,`description`,`is_system`,`name`,`value`) VALUES ('402881862bec2b21012bec2b70510015','2012-02-29 13:31:31','2012-02-29 13:31:31','会员管理',1,'会员管理','ROLE_MEMBER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
