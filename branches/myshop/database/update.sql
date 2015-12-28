


----------------------------------------------------------------------------
--2012-02-06 10:26:00
--barney
--重新初始化土地模块相关表
----------------------------------------------------------------------------

drop table farminglog;
drop table rendlog;
drop table farmingblockdefaultimage;
drop table harvestrecord;
drop table farmingblock;
drop table rendblock_member;
drop table cartitem;
drop table rendblock;
drop table fieldblockcartitem;
drop table fieldblockorderitem;
drop table member_fieldblock;
drop table fieldblock;




----------------------------------------------------------------------------
--2012-01-27 23:53:00 
--barney
--导航
----------------------------------------------------------------------------

delete from navigation where position = 1;
insert  into `navigation`(`id`,`createDate`,`modifyDate`,`isBlankTarget`,`isVisible`,`name`,`orderList`,`position`,`url`) 
values 
('402881882badb4ed012badc775b70012','2010-10-15 10:45:51','2010-10-27 22:05:11','\0','','首页',0,1,'/'),
('402881882ba98cce012ba9d5a2460018','2010-10-14 16:22:51','2010-10-27 22:05:45','\0','','新闻',1,1,'/shop/article!list.action?id=402881882ba8455f012ba86db8560006'),
('402881882ba98cce012ba9d5cd0f0019','2010-10-14 16:23:02','2010-10-27 22:05:35','\0','','土地承包',2,1,'/fieldmember/field_manage!index.action'),
('402881882ba98cce012ba9d5eb07001a','2010-10-14 16:23:10','2010-10-27 22:06:09','\0','','定期配送',3,1,'/shop/product!list.action?id=402881882ba8753a012ba95d56ae01cc'),
('402881882ba98cce012ba9d60b03001b','2010-10-14 16:23:18','2010-10-27 22:06:02','\0','','零售用户',4,1,'/shop/product!list.action?id=402881882ba8753a012ba955ca2001c4'),
('402881882ba98cce012ba9d62419001c','2010-10-14 16:23:24','2010-10-27 22:05:55','\0','','礼品卡',5,1,'/shop/product!list.action?id=402881882ba8753a012ba954f95301c2');




----------------------------------------------------------------------------
--2012-01-12 02:35:00 
--lacrimosar
--event
----------------------------------------------------------------------------
CREATE TABLE `event` (
  `Id` varchar(32) NOT NULL DEFAULT '',
  `eventname` varchar(255) DEFAULT NULL,
  `account` decimal(15,5) DEFAULT NULL,
  `createdate` datetime DEFAULT NULL,
  `expiredate` datetime DEFAULT NULL,
  `modifyDate` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='活动名称表';


----------------------------------------------------------------------------
--2012-01-12 02:35:00 
--lacrimosar
--presentcard
----------------------------------------------------------------------------
CREATE TABLE `presentcard` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `eventid` varchar(32) NOT NULL DEFAULT '0',
  `memberid` varchar(32) DEFAULT NULL,
  `userNum` varchar(30) DEFAULT NULL,
  `activate` int(1) DEFAULT NULL COMMENT '0未激活，1激活',
  `createDate` datetime DEFAULT NULL,
  `expireDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


----------------------------------------------------------------------------
--2012-01-10 10:26:00 
--xiangchao
--online
----------------------------------------------------------------------------
--留言板
DROP TABLE IF EXISTS `online`;
CREATE TABLE `online` (
  `id` varchar(32) NOT NULL,
  `title` varchar(40) DEFAULT NULL,
  `question` longtext,
  `answer` longtext,
  `status` varchar(50) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifyDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


----------------------------------------------------------------------------
--2012-01-02 09:17:00 
--barney
--土地模块表结构备份
----------------------------------------------------------------------------
--fieldblock 土地
CREATE TABLE `fieldblock` (                       
              `id` varchar(32) NOT NULL,                      
              `createDate` datetime default NULL,             
              `modifyDate` datetime default NULL,             
              `address` longtext NOT NULL,                    
              `area` int(11) NOT NULL,                        
              `code` varchar(16) NOT NULL,                    
              `deliveryFee` decimal(19,2) NOT NULL,           
              `description` varchar(255) default NULL,        
              `fieldType` varchar(255) default NULL,          
              `isMarketable` bit(1) NOT NULL,                 
              `longitudeLatitude` varchar(255) default NULL,  
              `name` varchar(255) NOT NULL,                   
              `point` int(11) NOT NULL,                       
              `productImageListStore` longtext,               
              `rent` decimal(19,2) NOT NULL,                  
              `store` int(11) default NULL,                   
              `trusteeFee` decimal(19,2) NOT NULL,            
              PRIMARY KEY  (`id`)                             
            ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
            
--rendblock 承包地块            
CREATE TABLE `rendblock` (                                                                       
             `id` varchar(32) NOT NULL,                                                                     
             `createDate` datetime default NULL,                                                            
             `modifyDate` datetime default NULL,                                                            
             `area` int(11) NOT NULL,                                                                       
             `code` varchar(16) NOT NULL,                                                                   
             `description` varchar(255) default NULL,                                                       
             `endDate` datetime default NULL,                                                               
             `fieldBlockCode` varchar(255) default NULL,                                                    
             `isDeliveryFeeEnabled` bit(1) NOT NULL,                                                        
             `isMarketable` bit(1) NOT NULL,                                                                
             `isTrusteeFeeEnabled` bit(1) NOT NULL,                                                         
             `name` varchar(255) NOT NULL,                                                                  
             `productImageListStore` longtext,                                                              
             `relativeAddress` longtext NOT NULL,                                                           
             `startDate` datetime default NULL,                                                             
             `username` varchar(255) default NULL,                                                          
             `fieldBlock_id` varchar(32) default NULL,                                                      
             `member_id` varchar(32) default NULL,                                                          
             PRIMARY KEY  (`id`),                                                                           
             KEY `FKE3292AC4A1E00A3E` (`member_id`),                                                        
             KEY `FKE3292AC4E792536C` (`fieldBlock_id`),                                                    
             CONSTRAINT `FKE3292AC4E792536C` FOREIGN KEY (`fieldBlock_id`) REFERENCES `fieldblock` (`id`),  
             CONSTRAINT `FKE3292AC4A1E00A3E` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)           
           ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--farmingblock 耕种地块           
CREATE TABLE `farmingblock` (                                                                  
                `id` varchar(32) NOT NULL,                                                                   
                `createDate` datetime default NULL,                                                          
                `modifyDate` datetime default NULL,                                                          
                `area` int(11) NOT NULL,                                                                     
                `code` varchar(16) NOT NULL,                                                                 
                `operateTime` datetime default NULL,                                                         
                `operateUser` varchar(64) default NULL,                                                      
                `productImageListStore` longtext,                                                            
                `rendBlockCode` varchar(255) default NULL,                                                   
                `seedName` varchar(255) default NULL,                                                        
                `status` varchar(1) default NULL,                                                            
                `username` varchar(255) default NULL,                                                        
                `member_id` varchar(32) default NULL,                                                        
                `rendBlock_id` varchar(32) default NULL,                                                     
                `seed_id` varchar(32) default NULL,                                                          
                PRIMARY KEY  (`id`),                                                                         
                KEY `FK78F37021A1E00A3E` (`member_id`),                                                      
                KEY `FK78F37021E3E5C7DE` (`seed_id`),                                                        
                KEY `FK78F3702172F78E28` (`rendBlock_id`),                                                   
                CONSTRAINT `FK78F3702172F78E28` FOREIGN KEY (`rendBlock_id`) REFERENCES `rendblock` (`id`),  
                CONSTRAINT `FK78F37021A1E00A3E` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`),        
                CONSTRAINT `FK78F37021E3E5C7DE` FOREIGN KEY (`seed_id`) REFERENCES `seed` (`id`)             
              ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--farminglog 耕种日志              
CREATE TABLE `farminglog` (                   
              `id` varchar(32) NOT NULL,                  
              `createDate` datetime default NULL,         
              `modifyDate` datetime default NULL,         
              `area` int(11) NOT NULL,                    
              `code` varchar(16) NOT NULL,                
              `operateTime` datetime default NULL,        
              `operateUser` varchar(64) default NULL,     
              `rendBlockCode` varchar(255) default NULL,  
              `seedName` varchar(255) default NULL,       
              `status` varchar(1) default NULL,           
              `username` varchar(255) default NULL,       
              PRIMARY KEY  (`id`)                         
            ) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;
            
--rendlog 承包日志           
CREATE TABLE `rendlog` (                       
           `id` varchar(32) NOT NULL,                   
           `createDate` datetime default NULL,          
           `modifyDate` datetime default NULL,          
           `area` int(11) NOT NULL,                     
           `code` varchar(16) NOT NULL,                 
           `endDate` datetime default NULL,             
           `fieldBlockCode` varchar(255) default NULL,  
           `isDeliveryFeeEnabled` bit(1) NOT NULL,      
           `isTrusteeFeeEnabled` bit(1) NOT NULL,       
           `name` varchar(255) NOT NULL,                
           `operateTime` datetime default NULL,         
           `operateUser` varchar(64) default NULL,      
           `startDate` datetime default NULL,           
           `username` varchar(255) default NULL,        
           PRIMARY KEY  (`id`)                          
         ) ENGINE=InnoDB DEFAULT CHARSET=utf8;            
          
--farmingblockdefaultimage 耕种地块默认图片
CREATE TABLE `farmingblockdefaultimage` (  
                            `id` varchar(32) NOT NULL,               
                            `createDate` datetime default NULL,      
                            `modifyDate` datetime default NULL,      
                            `productImageListStore` longtext,        
                            `seedName` varchar(255) default NULL,    
                            `status` varchar(255) default NULL,      
                            PRIMARY KEY  (`id`)                      
                          ) ENGINE=InnoDB DEFAULT CHARSET=utf8;            

--fieldblockcartitem 承包土地购物车项
CREATE TABLE `fieldblockcartitem` (                                                              
                      `id` varchar(32) NOT NULL,                                                                     
                      `createDate` datetime default NULL,                                                            
                      `modifyDate` datetime default NULL,                                                            
                      `isDeliveryFeeEnabled` bit(1) default NULL,                                                    
                      `isTrusteeFeeEnabled` bit(1) default NULL,                                                     
                      `quantity` int(11) default NULL,                                                               
                      `fieldBlock_id` varchar(32) NOT NULL,                                                          
                      `member_id` varchar(32) NOT NULL,                                                              
                      PRIMARY KEY  (`id`),                                                                           
                      KEY `FKB86D5746A1E00A3E` (`member_id`),                                                        
                      KEY `FKB86D5746E792536C` (`fieldBlock_id`),                                                    
                      CONSTRAINT `FKB86D5746E792536C` FOREIGN KEY (`fieldBlock_id`) REFERENCES `fieldblock` (`id`),  
                      CONSTRAINT `FKB86D5746A1E00A3E` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)           
                    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;                          

--fieldblockorderitem 承包土地订单项                    
CREATE TABLE `fieldblockorderitem` (                                                             
                       `id` varchar(32) NOT NULL,                                                                     
                       `createDate` datetime default NULL,                                                            
                       `modifyDate` datetime default NULL,                                                            
                       `deliveryFee` decimal(15,5) NOT NULL,                                                          
                       `fieldBlockCode` varchar(255) NOT NULL,                                                        
                       `fieldBlockName` varchar(255) NOT NULL,                                                        
                       `isDeliveryFeeEnabled` bit(1) default NULL,                                                    
                       `isTrusteeFeeEnabled` bit(1) default NULL,                                                     
                       `quantity` int(11) default NULL,                                                               
                       `rent` decimal(15,5) NOT NULL,                                                                 
                       `trusteeFee` decimal(15,5) NOT NULL,                                                           
                       `fieldBlock_id` varchar(32) default NULL,                                                      
                       `order_id` varchar(32) NOT NULL,                                                               
                       PRIMARY KEY  (`id`),                                                                           
                       KEY `FK326540EEA72EC7B6` (`order_id`),                                                         
                       KEY `FK326540EEE792536C` (`fieldBlock_id`),                                                    
                       CONSTRAINT `FK326540EEE792536C` FOREIGN KEY (`fieldBlock_id`) REFERENCES `fieldblock` (`id`),  
                       CONSTRAINT `FK326540EEA72EC7B6` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)            
                     ) ENGINE=InnoDB DEFAULT CHARSET=utf8;                    
                    
--harvestrecord 收获记录                     
CREATE TABLE `harvestrecord` (                                                                       
                 `id` varchar(32) NOT NULL,                                                                         
                 `createDate` datetime default NULL,                                                                
                 `modifyDate` datetime default NULL,                                                                
                 `amount` int(11) default NULL,                                                                     
                 `farmingBlockCode` varchar(255) default NULL,                                                      
                 `harvestTime` datetime default NULL,                                                               
                 `operateTime` datetime default NULL,                                                               
                 `operateUser` varchar(255) default NULL,                                                           
                 `seedName` varchar(255) default NULL,                                                              
                 `unit` varchar(255) default NULL,                                                                  
                 `username` varchar(255) default NULL,                                                              
                 `farmingBlock_id` varchar(32) default NULL,                                                        
                 `seed_id` varchar(32) default NULL,                                                                
                 PRIMARY KEY  (`id`),                                                                               
                 KEY `FK9A0060FAE3E5C7DE` (`seed_id`),                                                              
                 KEY `FK9A0060FADDD1642C` (`farmingBlock_id`),                                                      
                 CONSTRAINT `FK9A0060FADDD1642C` FOREIGN KEY (`farmingBlock_id`) REFERENCES `farmingblock` (`id`),  
                 CONSTRAINT `FK9A0060FAE3E5C7DE` FOREIGN KEY (`seed_id`) REFERENCES `seed` (`id`)                   
               ) ENGINE=InnoDB DEFAULT CHARSET=utf8;                     
            
--seed 种子
CREATE TABLE `seed` (
  `Id` varchar(32) NOT NULL DEFAULT '',
  `name` varchar(32) DEFAULT NULL,
  `type` tinyint(1) DEFAULT '0',
  `createDate` datetime DEFAULT NULL,
  `modifyDate` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;  

             
--memberauth 会员授权         
CREATE TABLE `memberauth` (                                                             
              `id` varchar(32) NOT NULL,                                                            
              `createDate` datetime default NULL,                                                   
              `modifyDate` datetime default NULL,                                                   
              `type` varchar(1) default NULL,                                                       
              `member_id` varchar(32) default NULL,                                                 
              `endDate` datetime default NULL,                                                      
              `startDate` datetime default NULL,                                                    
              PRIMARY KEY  (`id`),                                                                  
              KEY `FK26D8DB8288694389` (`member_id`),                                               
              CONSTRAINT `FK26D8DB8288694389` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)  
            ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
