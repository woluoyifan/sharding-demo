SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for order_record_2017
-- ----------------------------
DROP TABLE IF EXISTS `order_record_2017`;
CREATE TABLE `order_record_2017`  (
  `id` bigint(20) UNSIGNED NOT NULL COMMENT 'id',
  `product_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '商品名称',
  `order_time` datetime(0) NOT NULL COMMENT '下单时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for order_record_2018
-- ----------------------------
DROP TABLE IF EXISTS `order_record_2018`;
CREATE TABLE `order_record_2018`  (
  `id` bigint(20) UNSIGNED NOT NULL COMMENT 'id',
  `product_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '商品名称',
  `order_time` datetime(0) NOT NULL COMMENT '下单时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for order_record_2019
-- ----------------------------
DROP TABLE IF EXISTS `order_record_2019`;
CREATE TABLE `order_record_2019`  (
  `id` bigint(20) UNSIGNED NOT NULL COMMENT 'id',
  `product_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '商品名称',
  `order_time` datetime(0) NOT NULL COMMENT '下单时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
