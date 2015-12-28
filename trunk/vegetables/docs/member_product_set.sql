//喜好商品
CREATE TABLE `members_favor_product_set` (
  `favor_member_set` varchar(32) NOT NULL,
  `favor_product_set` varchar(32) NOT NULL,
  PRIMARY KEY (`favor_member_set`,`favor_product_set`),
  KEY `fk_product_favor_member_set` (`favor_product_set`),
  KEY `fk_member_favor_goods_set` (`favor_member_set`),
  CONSTRAINT `fk_member_favor_goods_set` FOREIGN KEY (`favor_member_set`) REFERENCES `members` (`id`),
  CONSTRAINT `fk_product_favor_member_set` FOREIGN KEY (`favor_product_set`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

//忌讳商品
CREATE TABLE `members_hate_product_set` (
  `hate_member_set` varchar(32) NOT NULL,
  `hate_product_set` varchar(32) NOT NULL,
  PRIMARY KEY (`hate_member_set`,`hate_product_set`),
  KEY `fk_product_hate_member_set` (`hate_product_set`),
  KEY `fk_member_hate_goods_set` (`hate_member_set`),
  CONSTRAINT `fk_member_hate_goods_set` FOREIGN KEY (`hate_member_set`) REFERENCES `members` (`id`),
  CONSTRAINT `fk_product_hate_member_set` FOREIGN KEY (`hate_product_set`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;