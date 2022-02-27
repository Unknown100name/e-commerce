-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        8.0.22 - MySQL Community Server - GPL
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  11.2.0.6213
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- 导出 ecommerce 的数据库结构
CREATE DATABASE IF NOT EXISTS `ecommerce` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `ecommerce`;

-- 导出  表 ecommerce.category_one 结构
CREATE TABLE IF NOT EXISTS `category_one` (
  `category_one_id` int NOT NULL COMMENT '目录 ID',
  `category_one_name` bigint DEFAULT NULL COMMENT '目录名称',
  `category_one_record` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`category_one_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='一级类目';

-- 数据导出被取消选择。

-- 导出  表 ecommerce.category_two 结构
CREATE TABLE IF NOT EXISTS `category_two` (
  `category_two_id` int NOT NULL COMMENT '目录 ID',
  `category_two_name` bigint DEFAULT NULL COMMENT '目录名称',
  `category_two_record` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`category_two_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='二级类目';

-- 数据导出被取消选择。

-- 导出  表 ecommerce.contact 结构
CREATE TABLE IF NOT EXISTS `contact` (
  `id` int NOT NULL,
  `user_id` int DEFAULT NULL COMMENT '所属人 ID',
  `address` varchar(50) DEFAULT NULL COMMENT '联系地址',
  `name` varchar(50) DEFAULT NULL COMMENT '联系人',
  `phone` varchar(50) DEFAULT NULL COMMENT '联系电话',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `user_id_link` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='联系地址 用于快递等信息';

-- 数据导出被取消选择。

-- 导出  表 ecommerce.dictionary 结构
CREATE TABLE IF NOT EXISTS `dictionary` (
  `id` int NOT NULL,
  `code` varchar(50) DEFAULT NULL,
  `parent_code` int NOT NULL,
  `content` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `code` (`code`),
  KEY `parentCode` (`parent_code`) USING BTREE,
  CONSTRAINT `parent_check` FOREIGN KEY (`parent_code`) REFERENCES `dictionary` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='字典';

-- 数据导出被取消选择。

-- 导出  表 ecommerce.evaluate 结构
CREATE TABLE IF NOT EXISTS `evaluate` (
  `id` int NOT NULL,
  `inner_item_id` int NOT NULL COMMENT '评价商品 ID',
  `time` int NOT NULL COMMENT '评论时间',
  `type` int NOT NULL COMMENT '评论分数\r\n0-10\r\n0-1:非常差\r\n2-3:差\r\n4-6:可以\r\n7-8:优秀\r\n9-10:完美\r\n0-3: 差评\r\n4-6: 中评\r\n7-10: 好评',
  `content` varchar(50) DEFAULT NULL COMMENT '评论内容',
  PRIMARY KEY (`id`),
  KEY `inner_item_id_with_time` (`inner_item_id`,`time`),
  KEY `inner_item_id_with_type_and_time` (`inner_item_id`,`type`,`time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='评价';

-- 数据导出被取消选择。

-- 导出  表 ecommerce.inner_item 结构
CREATE TABLE IF NOT EXISTS `inner_item` (
  `id` int NOT NULL,
  `item_id` int DEFAULT NULL COMMENT '商品 id',
  `type_name` varchar(50) DEFAULT NULL COMMENT '种类名称',
  `price` decimal(20,6) DEFAULT NULL COMMENT '单价',
  `inventory` int DEFAULT NULL COMMENT '库存\r\n-1 为无限量',
  PRIMARY KEY (`id`),
  KEY `item_id` (`item_id`,`id`),
  CONSTRAINT `item_id_link_two` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='细分商品';

-- 数据导出被取消选择。

-- 导出  表 ecommerce.inner_order 结构
CREATE TABLE IF NOT EXISTS `inner_order` (
  `id` int NOT NULL,
  `order_id` int NOT NULL COMMENT '大订单 Id',
  `state` int NOT NULL COMMENT '-1: 订单已取消\r\n0: 待付款\r\n1: 已付款 (商家待确认)\r\n2: 已发货 (买家待确认)\r\n3: 已收货 (买家已确认)\r\n * 5: 待退款 (买家申请退款)\r\n * 6: 待发货 (商家确认退款)\r\n * 7: 已发货 (商家待确认)\r\n * 8: 已确认 (商家已确认)',
  `inner_item_id` int NOT NULL COMMENT '细分商品 Id',
  `number` int NOT NULL COMMENT '购买数量',
  `single_price` decimal(20,6) NOT NULL COMMENT '购买单价',
  `express_code` varchar(50) DEFAULT NULL,
  `back_express_code` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `order_id` (`order_id`,`inner_item_id`),
  KEY `inner_item_id_link` (`inner_item_id`),
  CONSTRAINT `inner_item_id_link` FOREIGN KEY (`inner_item_id`) REFERENCES `inner_item` (`id`),
  CONSTRAINT `order_id_link` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='子订单 用于一个大订单{@link Order}中包含每个商品的子订单';

-- 数据导出被取消选择。

-- 导出  表 ecommerce.item 结构
CREATE TABLE IF NOT EXISTS `item` (
  `id` int NOT NULL,
  `title` varchar(50) NOT NULL COMMENT '标题',
  `sub_title` varchar(50) DEFAULT NULL COMMENT '副标题',
  `h5_base64` varchar(50) DEFAULT NULL COMMENT '详情页',
  `state` int NOT NULL COMMENT '商品状态\r\n0: 已暂存\r\n1: 待审核\r\n2: 已审核\r\n3: 已上线\r\n4: 待下线(已下线, 但还有订单未完成)\r\n5: 已下线(权限等同于已审核)\r\n6: 已删除',
  `shop_id` int NOT NULL COMMENT '商家 ID',
  `sell_count` int NOT NULL DEFAULT '0' COMMENT '销量',
  `category_one_id` int NOT NULL COMMENT '大目录分类',
  `category_two_id` int NOT NULL COMMENT '小目录分类',
  `hits` int NOT NULL DEFAULT '0' COMMENT '商品点击量',
  PRIMARY KEY (`id`),
  KEY `shop_id_with_title` (`shop_id`,`title`),
  KEY `shop_id_with_sell` (`shop_id`,`sell_count`),
  KEY `shop_id_with_hits` (`shop_id`,`hits`),
  KEY `category_one_link` (`category_one_id`),
  KEY `category_two_link` (`category_two_id`),
  CONSTRAINT `category_one_link` FOREIGN KEY (`category_one_id`) REFERENCES `category_one` (`category_one_id`),
  CONSTRAINT `category_two_link` FOREIGN KEY (`category_two_id`) REFERENCES `category_two` (`category_two_id`),
  CONSTRAINT `shop_id_link` FOREIGN KEY (`shop_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='大类商品';

-- 数据导出被取消选择。

-- 导出  表 ecommerce.item_image 结构
CREATE TABLE IF NOT EXISTS `item_image` (
  `id` int NOT NULL,
  `image_base64` varchar(50) DEFAULT NULL COMMENT '图片 base64',
  `item_id` int DEFAULT NULL COMMENT '{@link Item#id} 商品图\r\n{@link InnerItem#id} 细分商品图',
  `type` tinyint unsigned DEFAULT NULL COMMENT '类别\r\n0: 商品主图\r\n1: 商品图册\r\n2: InnerItem 主图',
  `order` int unsigned DEFAULT NULL COMMENT '图册 order',
  PRIMARY KEY (`id`),
  KEY `item_id_with_type_and_order` (`item_id`,`type`,`order`),
  CONSTRAINT `item_id_link` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用于商品的图片显示';

-- 数据导出被取消选择。

-- 导出  表 ecommerce.order 结构
CREATE TABLE IF NOT EXISTS `order` (
  `id` int NOT NULL,
  `time` int DEFAULT NULL COMMENT '订单下单时间',
  `contact_id` int DEFAULT NULL COMMENT '联系地址',
  PRIMARY KEY (`id`),
  KEY `contact_id` (`contact_id`),
  CONSTRAINT `contact_id_link` FOREIGN KEY (`contact_id`) REFERENCES `contact` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订单';

-- 数据导出被取消选择。

-- 导出  表 ecommerce.shopping_car 结构
CREATE TABLE IF NOT EXISTS `shopping_car` (
  `id` int NOT NULL,
  `user_id` int NOT NULL COMMENT '所属人',
  `inner_item_id` int NOT NULL COMMENT '商品 ID',
  `number` int NOT NULL COMMENT '数量',
  PRIMARY KEY (`id`),
  KEY `basic_key` (`user_id`,`inner_item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='购物车';

-- 数据导出被取消选择。

-- 导出  表 ecommerce.user 结构
CREATE TABLE IF NOT EXISTS `user` (
  `id` int NOT NULL,
  `nick` int DEFAULT NULL COMMENT 'nickname',
  `password` varchar(50) NOT NULL COMMENT '密码 SHA1',
  `phone` varchar(50) DEFAULT NULL COMMENT '手机号',
  `type` int NOT NULL COMMENT '用户种类\r\n0 = 商家\r\n1 = 买家\r\n2 = 管理员',
  `gender` int DEFAULT NULL COMMENT '性别\r\n0 = male\r\n1 = female',
  `real_name` varchar(50) DEFAULT NULL COMMENT '真实姓名',
  `id_card` varchar(50) DEFAULT NULL COMMENT '身份证号',
  `delete` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否注销\r\nfalse = 未删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户';

-- 数据导出被取消选择。

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
