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
  `category_one_id` bigint NOT NULL DEFAULT '0' COMMENT '目录 ID',
  `category_one_name` varchar(50) DEFAULT NULL COMMENT '目录名称',
  PRIMARY KEY (`category_one_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='一级类目';

-- 正在导出表  ecommerce.category_one 的数据：~12 rows (大约)
DELETE FROM `category_one`;
/*!40000 ALTER TABLE `category_one` DISABLE KEYS */;
INSERT INTO `category_one` (`category_one_id`, `category_one_name`) VALUES
	(1, '食品'),
	(2, '教育文娱'),
	(3, '家用电器'),
	(4, '数码产品'),
	(5, '户外'),
	(6, '家居'),
	(7, '服装'),
	(8, '汽车房产'),
	(9, '医药计生'),
	(10, '工业品'),
	(11, '母婴'),
	(12, '电脑办公');
/*!40000 ALTER TABLE `category_one` ENABLE KEYS */;

-- 导出  表 ecommerce.category_two 结构
CREATE TABLE IF NOT EXISTS `category_two` (
  `category_two_id` bigint NOT NULL DEFAULT '0' COMMENT '目录 ID',
  `category_two_name` varchar(50) DEFAULT NULL COMMENT '目录名称',
  `category_one_id` bigint NOT NULL DEFAULT '0' COMMENT '一级目录 ID',
  PRIMARY KEY (`category_two_id`) USING BTREE,
  KEY `FK_category_two_category_one` (`category_one_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='二级类目';

-- 正在导出表  ecommerce.category_two 的数据：~120 rows (大约)
DELETE FROM `category_two`;
/*!40000 ALTER TABLE `category_two` DISABLE KEYS */;
INSERT INTO `category_two` (`category_two_id`, `category_two_name`, `category_one_id`) VALUES
	(1, '海鲜水产', 1),
	(2, '冷饮冻食', 1),
	(3, '休闲食品', 1),
	(4, '新鲜水果', 1),
	(5, '进口食品', 1),
	(6, '地方特产', 1),
	(7, '饮料冲调', 1),
	(8, '精选肉类', 1),
	(9, '中外名酒', 1),
	(10, '粮油调味', 1),
	(11, '蔬菜蛋品', 1),
	(12, '茗茶', 1),
	(13, '教材教辅', 2),
	(14, '生活', 2),
	(15, '文学', 2),
	(16, '人文社科', 2),
	(17, '艺术', 2),
	(18, '经管励志', 2),
	(19, '电子书', 2),
	(20, '报刊', 2),
	(21, '科学', 2),
	(22, '文娱音像', 2),
	(23, '教育培训', 2),
	(24, '童书', 2),
	(25, '视听影音', 3),
	(26, '油烟机', 3),
	(27, '洗衣机', 3),
	(28, '热水器', 3),
	(29, '空调', 3),
	(30, '生活电器', 3),
	(31, '个人护理', 3),
	(32, '冰箱', 3),
	(33, '厨房电器', 3),
	(34, '燃气灶', 3),
	(35, '电视', 3),
	(36, '手机通信', 4),
	(37, '影音娱乐', 4),
	(38, '数码配件', 4),
	(39, '手机配件', 4),
	(40, '运营商', 4),
	(41, '摄影摄像', 4),
	(42, '电子教育', 4),
	(43, '户外装备', 5),
	(44, '游泳用品', 5),
	(45, '骑行运动', 5),
	(46, '户外鞋服', 5),
	(47, '健身训练', 5),
	(48, '体育用品', 5),
	(49, '垂钓用品', 5),
	(50, '厨房卫浴', 6),
	(51, '家装软饰', 6),
	(52, '厨具', 6),
	(53, '灯具', 6),
	(54, '装修设计', 6),
	(55, '五金电工', 6),
	(56, '全屋定制', 6),
	(57, '建筑材料', 6),
	(58, '家具', 6),
	(59, '生活日用', 6),
	(60, '家纺', 6),
	(61, '男装', 7),
	(62, '配饰', 7),
	(63, '女鞋', 7),
	(64, '内衣', 7),
	(65, '女装', 7),
	(66, '童鞋', 7),
	(67, '男鞋', 7),
	(68, '女士箱包', 7),
	(69, '钟表', 7),
	(70, '男士箱包', 7),
	(71, '童装', 7),
	(72, '电动车', 8),
	(73, '车载电器', 8),
	(74, '维修保养', 8),
	(75, '汽车', 8),
	(76, '房产', 8),
	(77, '汽车服务', 8),
	(78, '汽车装饰', 8),
	(79, '美容清洗', 8),
	(80, '安全自驾', 8),
	(81, '计生情趣', 9),
	(82, '营养成分', 9),
	(83, '护理护具', 9),
	(84, '隐形眼镜', 9),
	(85, '营养健康', 9),
	(86, '保健器械', 9),
	(87, '中西药品', 9),
	(88, '滋补养生', 9),
	(89, '健康服务', 9),
	(90, '仪器仪表', 10),
	(91, '安全消防', 10),
	(92, '化学品', 10),
	(93, '焊接紧固', 10),
	(94, '暖通照明', 10),
	(95, '劳动防护', 10),
	(96, '机械配件', 10),
	(97, '工具', 10),
	(98, '仓储包装', 10),
	(99, '工控配电', 10),
	(100, '清洁用品', 10),
	(101, '实验用品', 10),
	(102, '童车童床', 11),
	(103, '玩具', 11),
	(104, '洗护用品', 11),
	(105, '乐器', 11),
	(106, '营养辅食', 11),
	(107, '奶粉', 11),
	(108, '尿裤湿巾', 11),
	(109, '妈妈专区', 11),
	(110, '寝具服饰', 11),
	(111, '喂养用品', 11),
	(112, '网络产品', 12),
	(113, '办公设备', 12),
	(114, '耗材', 12),
	(115, '服务产品', 12),
	(116, '外设产品', 12),
	(117, '电脑配件', 12),
	(118, '电脑整机', 12),
	(119, '游戏设备', 12),
	(120, '文具', 12);
/*!40000 ALTER TABLE `category_two` ENABLE KEYS */;

-- 导出  表 ecommerce.contact 结构
CREATE TABLE IF NOT EXISTS `contact` (
  `id` bigint NOT NULL DEFAULT '0',
  `user_id` bigint DEFAULT NULL COMMENT '所属人 ID',
  `address` varchar(50) DEFAULT NULL COMMENT '联系地址',
  `name` varchar(50) DEFAULT NULL COMMENT '联系人',
  `phone` varchar(50) DEFAULT NULL COMMENT '联系电话',
  `deleted` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='联系地址 用于快递等信息';

-- 正在导出表  ecommerce.contact 的数据：~2 rows (大约)
DELETE FROM `contact`;
/*!40000 ALTER TABLE `contact` DISABLE KEYS */;
INSERT INTO `contact` (`id`, `user_id`, `address`, `name`, `phone`, `deleted`) VALUES
	(0, 21622007846141952, '重庆市渝北区鸳鸯街道壹号湖畔78-602', '陈丽华', '13608382065', 0),
	(1, 21622007846141952, '山东省环翠区哈尔滨工业大学(威海)', '邹皓杰', '18996345736', 0),
	(2, 21622007846141952, '重庆市綦江区古南街道桥河', '陈祎', NULL, 0),
	(22748331188944896, 21622007846141952, '重庆市渝北区鸳鸯街道壹号湖畔78-1202', '邹皓名', NULL, 0),
	(22748435555811328, 21622007846141952, '重庆市渝北区鸳鸯街道壹号湖畔78-1202', '谢怡漫', '000', 0);
/*!40000 ALTER TABLE `contact` ENABLE KEYS */;

-- 导出  表 ecommerce.dictionary 结构
CREATE TABLE IF NOT EXISTS `dictionary` (
  `id` bigint NOT NULL DEFAULT '0',
  `code` varchar(50) DEFAULT NULL,
  `parent_code` bigint NOT NULL DEFAULT '0',
  `content` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `code` (`code`),
  KEY `parentCode` (`parent_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='字典';

-- 正在导出表  ecommerce.dictionary 的数据：~0 rows (大约)
DELETE FROM `dictionary`;
/*!40000 ALTER TABLE `dictionary` DISABLE KEYS */;
/*!40000 ALTER TABLE `dictionary` ENABLE KEYS */;

-- 导出  表 ecommerce.evaluate 结构
CREATE TABLE IF NOT EXISTS `evaluate` (
  `id` bigint NOT NULL DEFAULT '0',
  `inner_item_id` bigint NOT NULL DEFAULT '0' COMMENT '评价商品 ID',
  `time` bigint NOT NULL DEFAULT '0' COMMENT '评论时间',
  `type` int NOT NULL COMMENT '评论分数\r\n0-10\r\n0-1:非常差\r\n2-3:差\r\n4-6:可以\r\n7-8:优秀\r\n9-10:完美\r\n0-3: 差评\r\n4-6: 中评\r\n7-10: 好评',
  `content` varchar(50) DEFAULT NULL COMMENT '评论内容',
  PRIMARY KEY (`id`),
  KEY `inner_item_id_with_time` (`inner_item_id`,`time`),
  KEY `inner_item_id_with_type_and_time` (`inner_item_id`,`type`,`time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='评价';

-- 正在导出表  ecommerce.evaluate 的数据：~0 rows (大约)
DELETE FROM `evaluate`;
/*!40000 ALTER TABLE `evaluate` DISABLE KEYS */;
INSERT INTO `evaluate` (`id`, `inner_item_id`, `time`, `type`, `content`) VALUES
	(22723348546977792, 21985896999944193, 1646384068473, 8, '可以说是非常好了');
/*!40000 ALTER TABLE `evaluate` ENABLE KEYS */;

-- 导出  表 ecommerce.inner_item 结构
CREATE TABLE IF NOT EXISTS `inner_item` (
  `id` bigint NOT NULL DEFAULT '0',
  `item_id` bigint DEFAULT NULL COMMENT '商品 id',
  `type_name` varchar(50) DEFAULT NULL COMMENT '种类名称',
  `price` decimal(20,6) DEFAULT NULL COMMENT '单价',
  `inventory` int DEFAULT NULL COMMENT '库存\r\n-1 为无限量',
  PRIMARY KEY (`id`),
  KEY `item_id` (`item_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='细分商品';

-- 正在导出表  ecommerce.inner_item 的数据：~12 rows (大约)
DELETE FROM `inner_item`;
/*!40000 ALTER TABLE `inner_item` DISABLE KEYS */;
INSERT INTO `inner_item` (`id`, `item_id`, `type_name`, `price`, `inventory`) VALUES
	(21985896999944193, 21985896999944192, '10天大闸蟹', 80.000000, 20),
	(21985896999944194, 21985896999944192, '20天大闸蟹', 100.000000, 100),
	(21985896999944195, 21985896999944192, '终生大闸蟹', 120.000000, 10),
	(21987502478852097, 21987502478852096, '本土鳗鱼', 100.000000, 2100),
	(21987502478852098, 21987502478852096, '东海鳗鱼', 213.500000, 540),
	(21987502478852099, 21987502478852096, '日本鳗鱼', 320.750000, 175),
	(21987742590173185, 21987742590173184, '语文', 100.000000, 100),
	(21987742590173186, 21987742590173184, '数学', 100.000000, 100),
	(21987742590173187, 21987742590173184, '英语', 100.000000, 100),
	(21987742590173188, 21987742590173184, '物理', 50.000000, 100),
	(21987742590173189, 21987742590173184, '化学', 50.000000, 100),
	(21987742590173190, 21987742590173184, '生物', 50.000000, 100),
	(21987994164527105, 21987994164527104, '中餐', 50.500000, 120),
	(21987994164527106, 21987994164527104, '西餐', 32.500000, 80);
/*!40000 ALTER TABLE `inner_item` ENABLE KEYS */;

-- 导出  表 ecommerce.inner_order 结构
CREATE TABLE IF NOT EXISTS `inner_order` (
  `id` bigint NOT NULL DEFAULT '0',
  `order_id` bigint NOT NULL DEFAULT '0' COMMENT '大订单 Id',
  `state` int NOT NULL COMMENT '-1: 订单已取消\r\n0: 待付款\r\n1: 已付款 (商家待确认)\r\n2: 已发货 (买家待确认)\r\n3: 已收货 (买家已确认)\r\n * 5: 待退款 (买家申请退款)\r\n * 6: 待发货 (商家确认退款)\r\n * 7: 已发货 (商家待确认)\r\n * 8: 已确认 (商家已确认)',
  `inner_item_id` bigint NOT NULL DEFAULT '0' COMMENT '细分商品 Id',
  `number` int NOT NULL COMMENT '购买数量',
  `single_price` decimal(20,6) NOT NULL COMMENT '购买单价',
  `express_code` varchar(50) DEFAULT NULL,
  `back_express_code` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `order_id` (`order_id`,`inner_item_id`),
  KEY `inner_item_id_link` (`inner_item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='子订单 用于一个大订单{@link Order}中包含每个商品的子订单';

-- 正在导出表  ecommerce.inner_order 的数据：~2 rows (大约)
DELETE FROM `inner_order`;
/*!40000 ALTER TABLE `inner_order` DISABLE KEYS */;
INSERT INTO `inner_order` (`id`, `order_id`, `state`, `inner_item_id`, `number`, `single_price`, `express_code`, `back_express_code`) VALUES
	(22361324730187776, 22361324713410560, 3, 21985896999944193, 3, 80.000000, NULL, NULL),
	(22361324730187777, 22361324713410560, 3, 21987502478852099, 1, 320.750000, NULL, NULL);
/*!40000 ALTER TABLE `inner_order` ENABLE KEYS */;

-- 导出  表 ecommerce.item 结构
CREATE TABLE IF NOT EXISTS `item` (
  `id` bigint NOT NULL DEFAULT '0',
  `title` varchar(50) NOT NULL COMMENT '标题',
  `sub_title` varchar(50) DEFAULT NULL COMMENT '副标题',
  `h5_base64` varchar(50) DEFAULT NULL COMMENT '详情页',
  `state` int NOT NULL COMMENT '商品状态\r\n0: 已暂存\r\n1: 待审核\r\n2: 已审核\r\n3: 已上线\r\n4: 待下线(已下线, 但还有订单未完成)\r\n5: 已下线(权限等同于已审核)\r\n6: 已删除',
  `shop_id` bigint NOT NULL DEFAULT '0' COMMENT '商家 ID',
  `sell_count` int NOT NULL DEFAULT '0' COMMENT '销量',
  `category_one_id` bigint NOT NULL DEFAULT '0' COMMENT '大目录分类',
  `category_two_id` bigint NOT NULL DEFAULT '0' COMMENT '小目录分类',
  `hits` int NOT NULL DEFAULT '0' COMMENT '商品点击量',
  PRIMARY KEY (`id`),
  KEY `shop_id_with_title` (`shop_id`,`title`),
  KEY `shop_id_with_sell` (`shop_id`,`sell_count`),
  KEY `shop_id_with_hits` (`shop_id`,`hits`),
  KEY `category_one_link` (`category_one_id`),
  KEY `category_two_link` (`category_two_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='大类商品';

-- 正在导出表  ecommerce.item 的数据：~4 rows (大约)
DELETE FROM `item`;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` (`id`, `title`, `sub_title`, `h5_base64`, `state`, `shop_id`, `sell_count`, `category_one_id`, `category_two_id`, `hits`) VALUES
	(21985896999944192, '海鲜测试商品001', '阳澄湖大闸蟹', NULL, 3, 21979855163752448, 0, 1, 1, 0),
	(21987502478852096, '海鲜测试商品002', '鳗鱼', NULL, 3, 21979855163752448, 0, 1, 2, 0),
	(21987742590173184, '教育文娱测试商品003', '五年高考三年模拟套卷', NULL, 3, 21979855163752448, 0, 2, 13, 0),
	(21987994164527104, '教育文娱测试商品004', '30天学会做饭', NULL, 3, 21979855163752448, 0, 2, 14, 0);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;

-- 导出  表 ecommerce.item_image 结构
CREATE TABLE IF NOT EXISTS `item_image` (
  `id` bigint NOT NULL DEFAULT '0',
  `image_base64` varchar(50) DEFAULT NULL COMMENT '图片 base64',
  `item_id` bigint DEFAULT NULL COMMENT '{@link Item#id} 商品图\r\n{@link InnerItem#id} 细分商品图',
  `type` tinyint unsigned DEFAULT NULL COMMENT '类别\r\n0: 商品主图\r\n1: 商品图册\r\n2: InnerItem 主图',
  `order` int unsigned DEFAULT NULL COMMENT '图册 order',
  PRIMARY KEY (`id`),
  KEY `item_id_with_type_and_order` (`item_id`,`type`,`order`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用于商品的图片显示';

-- 正在导出表  ecommerce.item_image 的数据：~0 rows (大约)
DELETE FROM `item_image`;
/*!40000 ALTER TABLE `item_image` DISABLE KEYS */;
/*!40000 ALTER TABLE `item_image` ENABLE KEYS */;

-- 导出  表 ecommerce.order 结构
CREATE TABLE IF NOT EXISTS `order` (
  `id` bigint NOT NULL DEFAULT '0',
  `time` bigint DEFAULT NULL COMMENT '订单下单时间',
  `contact_id` bigint DEFAULT NULL COMMENT '联系地址',
  PRIMARY KEY (`id`),
  KEY `contact_id` (`contact_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订单';

-- 正在导出表  ecommerce.order 的数据：~0 rows (大约)
DELETE FROM `order`;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` (`id`, `time`, `contact_id`) VALUES
	(22361324713410560, 1646297755265, 0);
/*!40000 ALTER TABLE `order` ENABLE KEYS */;

-- 导出  表 ecommerce.shopping_car 结构
CREATE TABLE IF NOT EXISTS `shopping_car` (
  `id` bigint NOT NULL DEFAULT '0',
  `user_id` bigint NOT NULL DEFAULT '0' COMMENT '所属人',
  `inner_item_id` bigint NOT NULL DEFAULT '0' COMMENT '商品 ID',
  `number` int NOT NULL COMMENT '数量',
  PRIMARY KEY (`id`),
  KEY `basic_key` (`user_id`,`inner_item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='购物车';

-- 正在导出表  ecommerce.shopping_car 的数据：~0 rows (大约)
DELETE FROM `shopping_car`;
/*!40000 ALTER TABLE `shopping_car` DISABLE KEYS */;
/*!40000 ALTER TABLE `shopping_car` ENABLE KEYS */;

-- 导出  表 ecommerce.user 结构
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint NOT NULL DEFAULT '0',
  `nick` varchar(50) DEFAULT NULL COMMENT 'nickname',
  `password` varchar(50) NOT NULL COMMENT '密码 SHA1',
  `phone` varchar(50) DEFAULT NULL COMMENT '手机号',
  `type` int NOT NULL COMMENT '用户种类\r\n0 = 商家\r\n1 = 买家\r\n2 = 管理员',
  `gender` int DEFAULT NULL COMMENT '性别\r\n0 = male\r\n1 = female',
  `real_name` varchar(50) DEFAULT NULL COMMENT '真实姓名',
  `id_card` varchar(50) DEFAULT NULL COMMENT '身份证号',
  `deleted` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否注销\r\nfalse = 未删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户';

-- 正在导出表  ecommerce.user 的数据：~2 rows (大约)
DELETE FROM `user`;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `nick`, `password`, `phone`, `type`, `gender`, `real_name`, `id_card`, `deleted`) VALUES
	(21622007846141952, '测试用户001', '7c4a8d09ca3762af61e59520943dc26494f8941b', '18996345736', 1, 0, '邹皓杰', '330324199912170198', 0),
	(21979855163752448, '测试用户002', '7c4a8d09ca3762af61e59520943dc26494f8941b', '18996345736', 0, 0, '邹皓杰', '330324199912170198', 0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- 导出  表 ecommerce.user_activity 结构
CREATE TABLE IF NOT EXISTS `user_activity` (
  `user_id` bigint NOT NULL DEFAULT '0' COMMENT '用户',
  `category_two_id` bigint NOT NULL DEFAULT '0' COMMENT '二级目录 ID',
  `hits` int NOT NULL DEFAULT '0' COMMENT '点击量',
  PRIMARY KEY (`user_id`,`category_two_id`,`hits`),
  KEY `FK_user_activity_category_two` (`category_two_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户行为日志\r\n';

-- 正在导出表  ecommerce.user_activity 的数据：~4 rows (大约)
DELETE FROM `user_activity`;
/*!40000 ALTER TABLE `user_activity` DISABLE KEYS */;
INSERT INTO `user_activity` (`user_id`, `category_two_id`, `hits`) VALUES
	(21622007846141952, 1, 10),
	(21622007846141952, 2, 6),
	(21622007846141952, 13, 1),
	(21622007846141952, 14, 2);
/*!40000 ALTER TABLE `user_activity` ENABLE KEYS */;

-- 导出  表 ecommerce.user_similarity 结构
CREATE TABLE IF NOT EXISTS `user_similarity` (
  `user_id` bigint NOT NULL DEFAULT '0' COMMENT '用户 ID',
  `user_ref_id` bigint NOT NULL DEFAULT '0' COMMENT '关联用户 ID',
  `similarity` double NOT NULL DEFAULT '0' COMMENT '相似度',
  PRIMARY KEY (`user_id`,`user_ref_id`,`similarity`),
  KEY `FK_user_similarity_user_2` (`user_ref_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户相似度表';

-- 正在导出表  ecommerce.user_similarity 的数据：~0 rows (大约)
DELETE FROM `user_similarity`;
/*!40000 ALTER TABLE `user_similarity` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_similarity` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
