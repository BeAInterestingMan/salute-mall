/*
 Navicat Premium Data Transfer

 Source Server         : 华为云
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : 114.116.105.15:3306
 Source Schema         : salute

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 30/11/2022 22:21:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for marketing_coupon_activity
-- ----------------------------
DROP TABLE IF EXISTS `marketing_coupon_activity`;
CREATE TABLE `marketing_coupon_activity`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `coupon_activity_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '优惠券活动编号',
  `coupon_activity_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '优惠券活动名称',
  `start_time` datetime(0) NOT NULL COMMENT '开始时间',
  `end_time` datetime(0) NOT NULL COMMENT '结束时间',
  `coupon_amount` decimal(12, 4) NOT NULL COMMENT '优惠券面值',
  `send_type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '1.主动领取 2.订单满赠 3.新用户发放 4.指定用户发放',
  `use_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '1.指定商品可用 2.指定分类可用 3.订单满减',
  `coupn_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '使用类型  满减券  无门槛',
  `status` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'DOWN' COMMENT '状态 ON上架 DOWN下架',
  `delete_flag` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'NO' COMMENT '删除标志;YES-已删除 NO-正常',
  `sort` int(0) NOT NULL DEFAULT 0 COMMENT '排序',
  `version` int(0) NOT NULL DEFAULT 1 COMMENT '乐观锁',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建人',
  `creator_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建人编号',
  `created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modifier` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '更新人',
  `modifier_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '更新人编号',
  `modified_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of marketing_coupon_activity
-- ----------------------------

-- ----------------------------
-- Table structure for marketing_coupon_send_rule
-- ----------------------------
DROP TABLE IF EXISTS `marketing_coupon_send_rule`;
CREATE TABLE `marketing_coupon_send_rule`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `coupon_activity_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '优惠券活动编号',
  `send_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '1.主动领取 2.订单满赠 3.新用户发放 4.指定用户发放5.指定用户类型发放',
  `send_qty` int(0) NOT NULL DEFAULT 0 COMMENT '单次发放数量',
  `participate_limit_qty` int(0) NULL DEFAULT -1 COMMENT '限制参与次数 -1-表示无限制',
  `order_full_amount` decimal(12, 4) NOT NULL COMMENT '订单满赠限制金额-发放方式为订单满赠时必填',
  `send_sence` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '发放场景  订单满赠为 指定分类商品满足  指定商品满足   ',
  `sort` int(0) NOT NULL DEFAULT 0 COMMENT '排序',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建人',
  `creator_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建人编号',
  `created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modifier` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '更新人',
  `modifier_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '更新人编号',
  `modified_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '营销优惠券发放规则表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of marketing_coupon_send_rule
-- ----------------------------

-- ----------------------------
-- Table structure for marketing_coupon_send_rule_detail
-- ----------------------------
DROP TABLE IF EXISTS `marketing_coupon_send_rule_detail`;
CREATE TABLE `marketing_coupon_send_rule_detail`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `coupon_activity_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '优惠券活动编号',
  `specific_type` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '指定用户',
  `specific_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '特定编号 1-指定用户 则为用户编号',
  `specific_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '特定名称',
  `sort` int(0) NOT NULL DEFAULT 0 COMMENT '排序',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建人',
  `creator_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建人编号',
  `created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modifier` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '更新人',
  `modifier_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '更新人编号',
  `modified_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '营销-优惠券发放规则明细表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of marketing_coupon_send_rule_detail
-- ----------------------------

-- ----------------------------
-- Table structure for marketing_coupon_use_rule
-- ----------------------------
DROP TABLE IF EXISTS `marketing_coupon_use_rule`;
CREATE TABLE `marketing_coupon_use_rule`  (
  `id` int(0) NOT NULL,
  `coupon_activity_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '优惠券活动编号',
  `use_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '1.指定商品可用 2.指定分类可用 3.订单满减',
  `order_full_amount` decimal(12, 4) NULL DEFAULT NULL COMMENT '订单满减金额',
  `mutex_flag` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '是否与其他活动互斥',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建人',
  `creator_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建人编号',
  `created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modifier` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '更新人',
  `modifier_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '更新人编号',
  `modified_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '营销优惠券使用规则表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of marketing_coupon_use_rule
-- ----------------------------

-- ----------------------------
-- Table structure for marketing_coupon_use_rule_detail
-- ----------------------------
DROP TABLE IF EXISTS `marketing_coupon_use_rule_detail`;
CREATE TABLE `marketing_coupon_use_rule_detail`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `coupon_activity_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '优惠券活动编号',
  `specific_type` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '指定分类  指定商品',
  `specific_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '特定编号 1-指定分类 则为分类编号',
  `specific_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '特定名称',
  `sort` int(0) NOT NULL DEFAULT 0 COMMENT '排序',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建人',
  `creator_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建人编号',
  `created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modifier` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '更新人',
  `modifier_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '更新人编号',
  `modified_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '营销优惠券使用规则明细表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of marketing_coupon_use_rule_detail
-- ----------------------------

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `shop_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '店铺编号',
  `product_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '商品名称',
  `product_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '商品编码',
  `keyword` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '关键词',
  `title` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '标题',
  `sell_point` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '卖点',
  `supplier_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '供应商编码',
  `supplier_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '供应商名称',
  `category_code_first` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '一级分类编号',
  `category_code_second` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '二级分类编号',
  `category_code_third` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '三级分类编号',
  `barcode` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '商品条形码',
  `brand_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '品牌编号',
  `sale_num` int(0) NOT NULL DEFAULT 0 COMMENT '销量',
  `status` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'DOWN' COMMENT '商品状态;ON上架 DOWN下架',
  `sort` int(0) NOT NULL DEFAULT 0 COMMENT '排序',
  `version` int(0) NOT NULL DEFAULT 1 COMMENT '乐观锁',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建人',
  `creator_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建人编号',
  `created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modifier` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '更新人',
  `modifier_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '更新人编号',
  `modified_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `main_image` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '商品主图',
  `delete_flag` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'NO' COMMENT '删除标志;YES-已删除 NO-正常',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_product_code`(`product_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品spu表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, 'sasqqq', '美的自行车店铺', '12545544787551', '自行车，实心，复古', '自行车女通勤轻便单车', '', '', '', '1121545455', '2sa3s21545455', '4546546466', 'saxaxqxqxq', '美的', 0, 'ON', 0, 1, '', '', '2022-11-29 09:57:20', '', '', '2022-11-29 10:13:17', '', 'NO');

-- ----------------------------
-- Table structure for product_category
-- ----------------------------
DROP TABLE IF EXISTS `product_category`;
CREATE TABLE `product_category`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `tenant_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '租户号',
  `category_level` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '分类级别',
  `parent_category_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '父级分类编号',
  `category_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '分类编号',
  `category_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '分类名称',
  `category_desc` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '分类描述',
  `icon` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '分类图标',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '状态',
  `delete_flag` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'NO' COMMENT '删除标志;YES-已删除  NO-正常',
  `sort` int(0) NOT NULL DEFAULT 0 COMMENT '排序',
  `version` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '乐观锁',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建人',
  `creator_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建人编号',
  `created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modifier` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '更新人',
  `modifier_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '更新人编号',
  `modified_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `uk_category_code`(`category_code`) USING BTREE COMMENT '分类编号',
  INDEX `idx_parent_category_code`(`parent_category_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品spu数据库' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_category
-- ----------------------------
INSERT INTO `product_category` VALUES (1, 'SALUTE', '1', '0', 'FL111', '母婴', '', '', 'ENABLE', 'NO', 0, '', '', '', '2022-11-23 06:11:04', '', '', '2022-11-23 06:11:04');
INSERT INTO `product_category` VALUES (2, 'SALUTE', '2', 'FL111', 'FL222', '亲子', '', '', 'ENABLE', 'NO', 0, '', '', '', '2022-11-23 06:12:12', '', '', '2022-11-23 06:13:43');
INSERT INTO `product_category` VALUES (3, 'SALUTE', '3', 'FL111', 'FL333', '幼儿', '', '', 'ENABLE', 'NO', 0, '', '', '', '2022-11-23 06:12:13', '', '', '2022-11-23 06:13:43');
INSERT INTO `product_category` VALUES (4, 'SALUTE', '4', '0', 'FL444', '玩具', '', '', 'ENABLE', 'NO', 0, '', '', '', '2022-11-23 06:12:42', '', '', '2022-11-23 06:12:42');
INSERT INTO `product_category` VALUES (5, 'SALUTE', '2', 'FL555', 'FL555', '玩具枪', '', '', 'ENABLE', 'NO', 0, '', '', '', '2022-11-23 06:13:25', '', '', '2022-11-23 06:13:43');

-- ----------------------------
-- Table structure for product_detail
-- ----------------------------
DROP TABLE IF EXISTS `product_detail`;
CREATE TABLE `product_detail`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `product_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '商品编号',
  `product_time` datetime(0) NULL DEFAULT NULL COMMENT '生成日期',
  `shelf_life_unit` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '商品保质期单位',
  `shelf_life` int(0) NOT NULL DEFAULT 0 COMMENT '商品保质期',
  `detail_html` varchar(900) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '产品详情网页内容',
  `detail_mobile_html` varchar(900) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '移动端网页详情',
  `main_image` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '商品主图',
  `images` varchar(900) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '图片详情',
  `origin_place` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '产地',
  `weight` int(0) NOT NULL DEFAULT 0 COMMENT '重量（g）',
  `delete_flag` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'NO' COMMENT '删除标志;YES-已删除 NO-正常',
  `sort` int(0) NOT NULL DEFAULT 0 COMMENT '排序',
  `version` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '1' COMMENT '乐观锁',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建人',
  `creator_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建人编号',
  `created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modifier` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '更新人',
  `modifier_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '更新人编号',
  `modified_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品spu详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_detail
-- ----------------------------
INSERT INTO `product_detail` VALUES (1, '12545544787551', '2022-11-29 20:56:16', '1', 10, '', '', '', '', '', 0, 'NO', 0, '1', '', '', '2022-11-29 12:56:29', '', '', '2022-11-29 12:56:29');

-- ----------------------------
-- Table structure for product_sku
-- ----------------------------
DROP TABLE IF EXISTS `product_sku`;
CREATE TABLE `product_sku`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `tenant_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '租户编号',
  `shop_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '32' COMMENT '店铺编号',
  `product_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品编码',
  `sku_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT 'sku名称',
  `sku_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT 'sku编码',
  `sale_price` decimal(12, 4) NOT NULL COMMENT '售卖价',
  `market_price` decimal(12, 4) NOT NULL COMMENT '市场价',
  `cost_price` decimal(12, 4) NOT NULL COMMENT '成本价',
  `specification_json` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '规格属性josn',
  `status` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'DOWN' COMMENT '状态 ON上架 DOWN下架',
  `delete_flag` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'NO' COMMENT '删除标志;YES-已删除 NO-正常',
  `sort` int(0) NOT NULL DEFAULT 0 COMMENT '排序',
  `version` int(0) NOT NULL DEFAULT 1 COMMENT '乐观锁',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建人',
  `creator_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建人编号',
  `created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modifier` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '更新人',
  `modifier_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '更新人编号',
  `modified_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `default_sku_flag` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'NO' COMMENT '是否是默认sku',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_sku_code`(`sku_code`) USING BTREE,
  INDEX `idx_product_code`(`product_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品sku表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_sku
-- ----------------------------
INSERT INTO `product_sku` VALUES (1, 'SALUTE', 'sasqqq', '12545544787551', '山地自行车sqsqsq', '122343545544787551', 200.3600, 300.2500, 110.3600, '[{\"productCode\":\"12545544787551\",\"specificationCode\":\"sdfdsfs\",\"specificationName\":\"颜色\",\"specificationValue\":\"绿色\"},{\"productCode\":\"12545544787551\",\"specificationCode\":\"sdfdsfs\",\"specificationName\":\"内存\",\"specificationValue\":\"128G\"}]', 'ON', 'NO', 0, 1, '', '', '2022-11-29 10:10:02', '', '', '2022-11-29 13:09:21', 'NO');
INSERT INTO `product_sku` VALUES (2, 'SALUTE', 'sasqqq', '12545544787551', '学生自行车', '55641115', 100.0000, 200.0000, 30.0000, '[{\"productCode\":\"12545544787551\",\"specificationCode\":\"sdfdsfs\",\"specificationName\":\"颜色\",\"specificationValue\":\"绿色\"},{\"productCode\":\"12545544787551\",\"specificationCode\":\"sdfdsfs\",\"specificationName\":\"内存\",\"specificationValue\":\"256G\"}]', 'ON', 'NO', 0, 1, '', '', '2022-11-29 12:57:05', '', '', '2022-11-29 13:09:35', 'NO');

-- ----------------------------
-- Table structure for product_specification
-- ----------------------------
DROP TABLE IF EXISTS `product_specification`;
CREATE TABLE `product_specification`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `product_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '商品编号',
  `specification_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '规格项编号',
  `specification_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '规格项名称',
  `specification_value` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '规格项值',
  `status` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'ENABLE' COMMENT '状态 ENABLE-启用  DISABLE-禁用',
  `delete_flag` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'NO' COMMENT '删除标志;YES-已删除 NO-正常',
  `sort` int(0) NOT NULL DEFAULT 0 COMMENT '排序',
  `version` int(0) NOT NULL DEFAULT 1 COMMENT '乐观锁',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建人',
  `creator_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建人编号',
  `created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modifier` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '更新人',
  `modifier_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '更新人编号',
  `modified_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_product_code_specification_code`(`product_code`, `specification_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '规格表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_specification
-- ----------------------------
INSERT INTO `product_specification` VALUES (1, '12545544787551', '2sdsdsd', '颜色', '黄色', 'ENABLE', 'NO', 0, 1, '', '', '2022-11-29 12:54:54', '', '', '2022-11-29 12:59:13');
INSERT INTO `product_specification` VALUES (2, '12545544787551', '2sdsdsd', '颜色', '绿色', 'ENABLE', 'NO', 0, 1, '', '', '2022-11-29 12:55:12', '', '', '2022-11-29 12:55:12');
INSERT INTO `product_specification` VALUES (3, '12545544787551', 'sdfdsfs', '内存', '128G', 'ENABLE', 'NO', 0, 1, '', '', '2022-11-29 12:55:28', '', '', '2022-11-29 12:59:16');
INSERT INTO `product_specification` VALUES (4, '12545544787551', 'sdfdsfs', '内存', '256G', 'ENABLE', 'NO', 0, 1, '', '', '2022-11-29 12:55:45', '', '', '2022-11-29 12:55:45');

-- ----------------------------
-- Table structure for product_stock
-- ----------------------------
DROP TABLE IF EXISTS `product_stock`;
CREATE TABLE `product_stock`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `tenant_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '租户号',
  `stock_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '库存编号',
  `product_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '商品编码',
  `sku_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT 'sku编码',
  `real_stock` int(0) NOT NULL DEFAULT 0 COMMENT '真实库存',
  `available_stock` int(0) NOT NULL DEFAULT 0 COMMENT '可用库存',
  `freeze_stock` int(0) NOT NULL DEFAULT 0 COMMENT '冻结库存',
  `delete_flag` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'NO' COMMENT '删除标志;YES-已删除 NO-正常',
  `sort` int(0) NOT NULL DEFAULT 0 COMMENT '排序',
  `version` int(0) NOT NULL DEFAULT 1 COMMENT '乐观锁',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建人',
  `creator_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建人编号',
  `created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modifier` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '更新人',
  `modifier_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '更新人编号',
  `modified_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_sku_code`(`sku_code`) USING BTREE,
  INDEX `uk_stock_code`(`stock_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品库存表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_stock
-- ----------------------------
INSERT INTO `product_stock` VALUES (1, 'SALUTE', '5544adas', '12545544787551', '122343545544787551', 100, 50, 50, 'NO', 0, 1, '', '', '2022-11-29 13:11:54', '', '', '2022-11-29 13:26:38');
INSERT INTO `product_stock` VALUES (2, 'SALUTE', 'asdasdsadsa', '12545544787551', '55641115', 500, 400, 100, 'NO', 0, 1, '', '', '2022-11-29 13:12:09', '', '', '2022-11-29 13:26:40');

-- ----------------------------
-- Table structure for product_stock_transaction
-- ----------------------------
DROP TABLE IF EXISTS `product_stock_transaction`;
CREATE TABLE `product_stock_transaction`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `tenant_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '租户号',
  `operate_type` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '操作类型',
  `operate_time` datetime(0) NOT NULL COMMENT '操作时间',
  `biz_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '业务单号',
  `before_real_stock` int(0) NOT NULL COMMENT '操作前真实库存',
  `after_real_stock` int(0) NOT NULL COMMENT '操作后真实库存',
  `before_freeze_stock` int(0) NOT NULL COMMENT '操作前冻结库存',
  `after_freeze_stock` int(0) NOT NULL COMMENT '操作后冻结库存',
  `operate_stock` int(0) NOT NULL COMMENT '操作库存数量',
  `delete_flag` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'NO' COMMENT '删除标志;YES-已删除 NO-正常',
  `sort` int(0) NOT NULL DEFAULT 0 COMMENT '排序',
  `version` int(0) NOT NULL DEFAULT 1 COMMENT '乐观锁',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建人',
  `creator_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建人编号',
  `created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modifier` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '更新人',
  `modifier_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '更新人编号',
  `modified_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '库存流水表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_stock_transaction
-- ----------------------------

-- ----------------------------
-- Table structure for product_tag
-- ----------------------------
DROP TABLE IF EXISTS `product_tag`;
CREATE TABLE `product_tag`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `product_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品编码',
  `tag_desc` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '标签描述',
  `tag_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '标签code',
  `tag_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '标签名称',
  `status` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'DOWN' COMMENT '状态 ON上架 DOWN下架',
  `delete_flag` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'NO' COMMENT '删除标志;YES-已删除 NO-正常',
  `sort` int(0) NOT NULL DEFAULT 0 COMMENT '排序',
  `version` int(0) NOT NULL DEFAULT 1 COMMENT '乐观锁',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建人',
  `creator_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建人编号',
  `created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modifier` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '更新人',
  `modifier_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '更新人编号',
  `modified_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_product_code`(`product_code`) USING BTREE,
  INDEX `uk_tag_code`(`tag_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品标签表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_tag
-- ----------------------------

-- ----------------------------
-- Table structure for shopping_cart
-- ----------------------------
DROP TABLE IF EXISTS `shopping_cart`;
CREATE TABLE `shopping_cart`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `shop_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '店铺编号',
  `product_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '商品编码',
  `add_cart_price` decimal(12, 4) NOT NULL COMMENT '加入购物车时价格',
  `status` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'DOWN' COMMENT '商品状态;ON上架 DOWN下架',
  `sort` int(0) NOT NULL DEFAULT 0 COMMENT '排序',
  `version` int(0) NOT NULL DEFAULT 1 COMMENT '乐观锁',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建人',
  `creator_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建人编号',
  `created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modifier` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '更新人',
  `modifier_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '更新人编号',
  `modified_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '购物车' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shopping_cart
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
