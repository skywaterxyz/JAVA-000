CREATE TABLE if not exists `account` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '账号id',
  `account` varchar(64) NOT NULL COMMENT '用户登入账号',
  `password` varchar(150) NOT NULL COMMENT '用户登入密码',
  `user_name` varchar(64) NOT NULL COMMENT '用户自定义名称,用作显示,初始值与account一致',
  `phone` varchar(16) DEFAULT NULL COMMENT '账号电话',
  `email` varchar(64) DEFAULT NULL COMMENT '账号邮箱',
  `wechat_nickname` varchar(150) DEFAULT NULL COMMENT '微信昵称',
  `wechat_unionId` varchar(150) DEFAULT NULL COMMENT '微信unionId',
  `wechat_openid` varchar(64) DEFAULT NULL COMMENT '微信openid',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `state` char(1) NOT NULL DEFAULT 'A' COMMENT '状态，A为效账号，I为无效，P为待验证',
  PRIMARY KEY (`id`),
  KEY `ix_account` (`account`),
  KEY `ix_phone` (`phone`),
  KEY `ix_email` (`email`),
  KEY `ik_wechat_unionId` (`wechat_unionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户账号表';

CREATE TABLE if not exists `seller` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '商户id',
  `name` varchar(64) NOT NULL COMMENT '商户名称',
  `phone` varchar(16) NOT NULL DEFAULT '' COMMENT '联系电话',
  `email` varchar(64) NOT NULL DEFAULT '' COMMENT '邮箱',
  `state` char(1) NOT NULL DEFAULT 'A' COMMENT '状态，A为效账号，I为无效',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商户表';

CREATE TABLE if not exists `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品类目id',
  `category` varchar(64) NOT NULL COMMENT '类目名称',
  `description` varchar(64) DEFAULT NULL COMMENT '类目描述',
  `lft` smallint(6) NOT NULL COMMENT '左子树',
  `rgt` smallint(6) NOT NULL COMMENT '右子树',
  `lvl` tinyint(4) NOT NULL COMMENT '树高，类目级别',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `category` (`category`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品类目表';

CREATE TABLE if not exists `product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品id',  
  `name` varchar(64) NOT NULL COMMENT '商品名称',
  `price` int(10) DEFAULT NULL COMMENT '商品价格，单位：分',
  `description` varchar(64) DEFAULT NULL COMMENT '商品描述',
  `seller_id` int(10) NOT NULL COMMENT '商户id',  
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `name` (`name`),
  KEY `seller_id` (`seller_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品表';

CREATE TABLE if not exists `product_category_map` (
  `product_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品id',  
  `category_id` int(11) NOT NULL COMMENT '商品类目id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`product_id`, `category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品和类目关系映射表';

CREATE TABLE if not exists `product_detail` (
  `product_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品id', 
  `description` varchar(4096) DEFAULT NULL COMMENT '商品描述',  
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品详情表';

CREATE TABLE if not exists `product_picture` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',  
  `product_id` bigint(20) NOT NULL COMMENT '商品id', 
  `picture_src` varchar(64) NOT NULL COMMENT '图片资源',
  `description` varchar(128) DEFAULT NULL COMMENT '商品描述',  
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品图片表';

CREATE TABLE if not exists `order_unfinished` (
  `id` varchar(64) NOT NULL COMMENT '订单编号',  
  `account_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '买家账号id',
  `seller_id` int(10) NOT NULL COMMENT '商户id',  
  `product_id` bigint(20) NOT NULL COMMENT '商品id', 
  `description` varchar(4096) DEFAULT NULL COMMENT '下单时商品详情，包括名称，价格以及规格等',
  `state` char(1) NOT NULL DEFAULT 'A' COMMENT '订单状态，A为未支付，B为已支付,C为已发货，D为已收货待确认，E为已收货，F为退货中，G为已退货',  
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '订单创建时间',
  `pay_time` timestamp DEFAULT '0000-00-00 00:00:00' COMMENT '支付时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `account_id` (`account_id`),
  KEY `seller_id` (`seller_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='未完成订单表';

CREATE TABLE if not exists `order_history` (
  `id` varchar(64) NOT NULL COMMENT '订单编号',  
  `account_id` int(10) NOT NULL COMMENT '买家账号id',
  `seller_id` int(10) NOT NULL COMMENT '商户id',  
  `product_id` bigint(20) NOT NULL COMMENT '商品id', 
  `description` varchar(4096) DEFAULT NULL COMMENT '下单时商品详情，包括名称，价格以及规格等',
  `state` char(1) NOT NULL DEFAULT 'A' COMMENT '订单状态，A为未支付，B为已支付,C为已发货，D为已收货待确认，E为已收货，F为退货中，G为已退货',  
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '订单创建时间',
  `pay_time` timestamp DEFAULT '0000-00-00 00:00:00' COMMENT '支付时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `account_id` (`account_id`),
  KEY `seller_id` (`seller_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='已完成订单表';