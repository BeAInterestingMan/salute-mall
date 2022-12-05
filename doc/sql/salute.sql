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

 Date: 05/12/2022 22:11:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for delivery_order
-- ----------------------------
DROP TABLE IF EXISTS `delivery_order`;
CREATE TABLE `delivery_order`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `sale_order_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '订单编号',
  `delivery_order_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '发货单号',
  `shop_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '店铺名称',
  `shop_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '店铺名称',
  `status` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '发订单状态  待发货 待收货 已完成 已取消',
  `pay_mode` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '支付方式  线上  线下',
  `pay_type` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '支付类型  微信 支付宝  现金 银行卡',
  `pay_status` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'WAIT_PAY' COMMENT '支付状态 待支付 支付成功 支付失败',
  `pay_time` datetime(0) NULL DEFAULT NULL COMMENT '支付时间',
  `pay_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '支付编号',
  `total_cost_amount` decimal(10, 0) NOT NULL COMMENT '成本价总额',
  `total_sale_amount` decimal(10, 0) NOT NULL COMMENT '未参与营销的总额',
  `total_payable_amount` decimal(10, 0) NOT NULL COMMENT '应付金额',
  `total_coupon_amount` decimal(10, 0) NOT NULL COMMENT '使用优惠券金额',
  `total_payment_amount` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '实付金额',
  `total_delivery_payment_amount` decimal(10, 0) NOT NULL COMMENT '实际发货应付金额',
  `total_delivery_payable_amount` decimal(10, 0) NOT NULL COMMENT '实际发货应付金额',
  `total_delivery_coupon_amount` decimal(10, 0) NOT NULL COMMENT '实际发货使用优惠券金额',
  `total_delivery_cost_amount` decimal(10, 0) NOT NULL COMMENT '成本价总额',
  `total_delivery_sale_amount` decimal(10, 0) NOT NULL COMMENT '未参与营销的总额',
  `order_remark` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '订单备注',
  `sort` int(0) NOT NULL DEFAULT 0 COMMENT '排序',
  `version` int(0) NOT NULL DEFAULT 1 COMMENT '乐观锁',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建人',
  `creator_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建人编号',
  `created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modifier` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '更新人',
  `modifier_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '更新人编号',
  `modified_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '发货单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of delivery_order
-- ----------------------------

-- ----------------------------
-- Table structure for delivery_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `delivery_order_detail`;
CREATE TABLE `delivery_order_detail`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `sale_order_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '订单编号',
  `delivery_order_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '发货单编号',
  `sku_code` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '商品编码',
  `sku_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '商品sku名称',
  `product_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '商品编码',
  `product_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '商品名称',
  `main_image` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '商品主图',
  `shop_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '店铺名称',
  `shop_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '店铺名称',
  `buy_qty` decimal(10, 0) NOT NULL COMMENT '购买数量',
  `order_sku_type` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'COMMON' COMMENT '赠品   正常购买商品',
  `remark` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '备注',
  `delivery_sku_qty` int(0) NOT NULL DEFAULT 0 COMMENT '发货数量',
  `cost_price` decimal(10, 0) NOT NULL COMMENT '成本价总额',
  `sale_price` decimal(10, 0) NOT NULL COMMENT '销售金额（未参与营销）',
  `transaction_price` decimal(10, 0) NOT NULL COMMENT '成交价(扣除各种优惠后的)',
  `marketing_price` decimal(10, 0) NOT NULL COMMENT '市场价',
  `payable_amount` decimal(10, 0) NOT NULL COMMENT '应付金额',
  `payment_amount` decimal(10, 0) NOT NULL COMMENT '实付金额',
  `coupon_amount` decimal(10, 0) NOT NULL COMMENT '使用优惠券金额',
  `delivery_payable_amount` decimal(10, 0) NOT NULL COMMENT '发货应付金额',
  `delivery_payment_amount` decimal(10, 0) NOT NULL COMMENT '发货实付金额',
  `delivery_coupon_amount` decimal(10, 0) NOT NULL COMMENT '发货使用优惠券金额',
  `sort` int(0) NOT NULL DEFAULT 0 COMMENT '排序',
  `version` int(0) NOT NULL DEFAULT 1 COMMENT '乐观锁',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建人',
  `creator_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建人编号',
  `created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modifier` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '更新人',
  `modifier_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '更新人编号',
  `modified_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '发货单详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of delivery_order_detail
-- ----------------------------

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '营销-优惠券发放规则明细表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of marketing_coupon_send_rule_detail
-- ----------------------------

-- ----------------------------
-- Table structure for marketing_coupon_stock
-- ----------------------------
DROP TABLE IF EXISTS `marketing_coupon_stock`;
CREATE TABLE `marketing_coupon_stock`  (
  `id` int(0) NOT NULL,
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建人',
  `creator_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建人编号',
  `created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modifier` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '更新人',
  `modifier_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '更新人编号',
  `modified_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `coupon_activity_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '优惠券活动编号',
  `real_stock` int(0) NOT NULL DEFAULT 0 COMMENT '真实库存',
  `send_stock` int(0) NOT NULL DEFAULT 0 COMMENT '已发放库存',
  `available_stock` int(0) NOT NULL DEFAULT 0 COMMENT '可用库存'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '优惠券库存表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of marketing_coupon_stock
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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '营销优惠券使用规则明细表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of marketing_coupon_use_rule_detail
-- ----------------------------

-- ----------------------------
-- Table structure for marketing_coupon_user_record
-- ----------------------------
DROP TABLE IF EXISTS `marketing_coupon_user_record`;
CREATE TABLE `marketing_coupon_user_record`  (
  `id` int(0) NOT NULL,
  `coupon_activity_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '优惠券活动编号',
  `coupon_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '优惠券实例标号',
  `status` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '已使用  未使用  已撤销 已过期',
  `using_time` datetime(0) NULL DEFAULT NULL COMMENT '使用时间',
  `relation_biz_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '关联业务单号',
  `startTime` datetime(0) NOT NULL COMMENT '优惠券生效开始时间',
  `endTime` datetime(0) NOT NULL COMMENT '优惠券生效结束时间',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建人',
  `creator_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建人编号',
  `created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modifier` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '更新人',
  `modifier_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '更新人编号',
  `modified_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '优惠券领用记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of marketing_coupon_user_record
-- ----------------------------

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `shop_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '店铺编号',
  `shop_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '店铺名称',
  `product_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '商品名称',
  `main_image` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '商品主图',
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
  `delete_flag` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'NO' COMMENT '删除标志;YES-已删除 NO-正常',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_product_code`(`product_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品spu表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, '1376433565247471616', 'salute自营店', '一加 OnePlus 9 ', 'https://lilishop-oss.oss-cn-beijing.aliyuncs.com/c4be072d4fdf408d8b599db8328f5a1d.jpg?x-oss-process=style/400X400', '1376373278360207360', '手机、一加', ' 8GB+128GB 5G手机', ' 8GB+128GB 5G手机', '', '', '1348576427264204941', '1348576427264204942', '1348576427264204943', 'saxaxqxqxq', '一加', 1000, 'ON', 0, 1, '', '', '2022-11-29 09:57:20', '', '', '2022-12-05 12:03:55', 'NO');
INSERT INTO `product` VALUES (2, '1376433565247471616', 'salute自营店', 'Apple iPhone 12', 'https://lilishop-oss.oss-cn-beijing.aliyuncs.com/f728a555128947c59554c74b5c5740d0.jpg?x-oss-process=style/400X400', '1376529925690884096', 'Apple、iPhone、苹果', 'Apple iPhone 12', ' 移动联通电信5G全网通手机', '', '', '1348576427264204941', '1348576427264204942', '1348576427264204943', '', '一加', 2565, 'ON', 0, 1, '', '', '2022-12-04 10:23:54', '', '', '2022-12-05 12:05:51', 'NO');

-- ----------------------------
-- Table structure for product_category
-- ----------------------------
DROP TABLE IF EXISTS `product_category`;
CREATE TABLE `product_category`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `shop_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '租户号',
  `category_level` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '分类级别',
  `parent_category_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '父级分类编号',
  `category_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '分类编号',
  `category_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '分类名称',
  `category_image` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '分类图片',
  `category_desc` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '分类描述',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'ENABLE' COMMENT '状态',
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
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品spu数据库' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_category
-- ----------------------------
INSERT INTO `product_category` VALUES (1, 'SALUTE', '1', '0', '1348576427260010496', '服装鞋帽', 'https://lilishop-oss.oss-cn-beijing.aliyuncs.com/baab654a10654d509aaf94565e5f2a79.png', '', 'ENABLE', 'NO', 0, '', '', '', '2022-11-23 06:11:04', '', '', '2022-12-03 07:39:23');
INSERT INTO `product_category` VALUES (2, 'SALUTE', '2', '1348576427260010496', '1348576427264204800', '女装', '', '', 'ENABLE', 'NO', 0, '', '', '', '2022-11-23 06:12:12', '', '', '2022-12-03 07:41:13');
INSERT INTO `product_category` VALUES (3, 'SALUTE', '2', '1348576427260010496', '1348576427264204821', '男装', '', '', 'ENABLE', 'NO', 0, '', '', '', '2022-11-23 06:12:13', '', '', '2022-12-04 09:26:36');
INSERT INTO `product_category` VALUES (4, 'SALUTE', '3', '1348576427264204820', '1348576427264204842', '内衣', 'https://lilishop-oss.oss-cn-beijing.aliyuncs.com/1348576427264204804.png', '', 'ENABLE', 'NO', 0, '', '', '', '2022-11-23 06:12:42', '', '', '2022-12-03 08:09:54');
INSERT INTO `product_category` VALUES (5, 'SALUTE', '3', '1348576427264204800', '1348576427264204943', 'T恤', 'https://lilishop-oss.oss-cn-beijing.aliyuncs.com/1348576427264204801.png', '', 'ENABLE', 'NO', 0, '', '', '', '2022-11-23 06:13:25', '', '', '2022-12-05 14:09:59');
INSERT INTO `product_category` VALUES (6, 'SALUTE', '3', '1348576427264204802', '1348576427264204822', '衬衫', 'https://lilishop-oss.oss-cn-beijing.aliyuncs.com/1348576427264204802.png', '', 'ENABLE', 'NO', 0, '', '', '', '2022-12-03 07:42:32', '', '', '2022-12-04 09:26:56');
INSERT INTO `product_category` VALUES (7, 'SALUTE', '2', '1348576427264204941', '1348576427264204942', '手机通讯', '', '', 'ENABLE', 'NO', 0, '', '', '', '2022-12-03 07:44:34', '', '', '2022-12-03 08:05:11');
INSERT INTO `product_category` VALUES (8, 'SALUTE', '1', '0', '1348576427264204941', '数码办公', 'https://lilishop-oss.oss-cn-beijing.aliyuncs.com/3f26aec6ce4b4b2f9201b5f56111cafe.png', '', 'ENABLE', 'NO', 0, '', '', '', '2022-12-03 07:44:58', '', '', '2022-12-03 08:05:12');
INSERT INTO `product_category` VALUES (9, 'SALUTE', '3', '1348576427264204942', '1348576427264204943', '手机', 'https://lilishop-oss.oss-cn-beijing.aliyuncs.com/1272b051537a4ee19f12791eb9da60f9.jpg', '', 'ENABLE', 'NO', 0, '', '', '', '2022-12-03 07:46:04', '', '', '2022-12-03 08:05:13');
INSERT INTO `product_category` VALUES (10, 'SALUTE', '3', '1348576427264204800', '1348576427264204803', '雪纺衫', 'https://lilishop-oss.oss-cn-beijing.aliyuncs.com/1348576427264204803.png', '', 'ENABLE', 'NO', 0, '', '', '', '2022-12-03 08:41:58', '', '', '2022-12-03 08:42:13');

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
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品spu详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_detail
-- ----------------------------
INSERT INTO `product_detail` VALUES (1, '1376373278360207360', '2022-11-29 20:56:16', '1', 10, '<p><img src=\"https://lilishop-oss.oss-cn-beijing.aliyuncs.com/e9f010bdc5f448c0807ea0fb51897db0.jpg\" style=\"max-width:100%;\"/><br/></p>', '<p><img src=\"https://lilishop-oss.oss-cn-beijing.aliyuncs.com/e9f010bdc5f448c0807ea0fb51897db0.jpg\"/><br/></p>', '', '', '', 0, 'NO', 0, '1', '', '', '2022-11-29 12:56:29', '', '', '2022-12-05 13:29:46');
INSERT INTO `product_detail` VALUES (2, '1376529925690884096', '2022-12-04 18:25:16', '1', 10, '<p><img src=\"https://lilishop-oss.oss-cn-beijing.aliyuncs.com/370283fdba55436c81d7a0a61911d88c.jpg\" style=\"max-width:100%;\"/><br/></p>', '<p><img src=\"https://lilishop-oss.oss-cn-beijing.aliyuncs.com/370283fdba55436c81d7a0a61911d88c.jpg\"/><br/></p>', '', '', '', 0, 'NO', 0, '1', '', '', '2022-12-04 10:25:22', '', '', '2022-12-05 13:29:36');

-- ----------------------------
-- Table structure for product_sku
-- ----------------------------
DROP TABLE IF EXISTS `product_sku`;
CREATE TABLE `product_sku`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `shop_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '32' COMMENT '店铺编号',
  `shop_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '店铺名称',
  `product_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品编码',
  `sku_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT 'sku名称',
  `sku_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT 'sku编码',
  `main_image` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '商品主图',
  `default_sku_flag` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'NO' COMMENT '是否是默认sku',
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
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_sku_code`(`sku_code`) USING BTREE,
  INDEX `idx_product_code`(`product_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品sku表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_sku
-- ----------------------------
INSERT INTO `product_sku` VALUES (2, 'sasqqq', '', '1376373278360207360', '一加 OnePlus 9 ', '1387977447487569920', 'https://lilishop-oss.oss-cn-beijing.aliyuncs.com/df6377650f94456087316c1c9a381952.jpeg?x-oss-process=style/400X400', 'NO', 3999.0000, 3500.0000, 3500.0000, '[{\"productCode\":\"12545544787551\",\"specificationCode\":\"sdfdsfs\",\"specificationName\":\"颜色\",\"specificationValue\":\"绿色\"},{\"productCode\":\"1376373278360207360\",\"specificationCode\":\"sdfdsfs\",\"specificationName\":\"内存\",\"specificationValue\":\"256G\"}]', 'ON', 'NO', 0, 1, '', '', '2022-11-29 12:57:05', '', '', '2022-12-05 13:27:02');
INSERT INTO `product_sku` VALUES (3, 'SASA', '', '1376529925690884096', 'Apple iPhone 12', '1387977574864388096', 'https://lilishop-oss.oss-cn-beijing.aliyuncs.com/af4e8d5341b1433c8d23d44dbf9bf37f.jpg?x-oss-process=style/400X400', 'NO', 5999.0000, 5000.0000, 4500.0000, '[{\"productCode\":\"12545544787551\",\"specificationCode\":\"sdfdsfs\",\"specificationName\":\"颜色\",\"specificationValue\":\"绿色\"},{\"productCode\":\"1376529925690884096\",\"specificationCode\":\"sdfdsfs\",\"specificationName\":\"内存\",\"specificationValue\":\"256G\"}]', 'ON', 'NO', 0, 1, '', '', '2022-12-04 10:25:55', '', '', '2022-12-05 13:27:14');

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
INSERT INTO `product_specification` VALUES (1, '1376373278360207360', '2sdsdsd', '颜色', '黄色', 'ENABLE', 'NO', 0, 1, '', '', '2022-11-29 12:54:54', '', '', '2022-12-05 13:31:15');
INSERT INTO `product_specification` VALUES (2, '1376373278360207360', '2sdsdsd', '颜色', '绿色', 'ENABLE', 'NO', 0, 1, '', '', '2022-11-29 12:55:12', '', '', '2022-12-05 13:31:24');
INSERT INTO `product_specification` VALUES (3, '1376529925690884096', 'sdfdsfs', '内存', '128G', 'ENABLE', 'NO', 0, 1, '', '', '2022-11-29 12:55:28', '', '', '2022-12-05 13:31:24');
INSERT INTO `product_specification` VALUES (4, '1376529925690884096', 'sdfdsfs', '内存', '256G', 'ENABLE', 'NO', 0, 1, '', '', '2022-11-29 12:55:45', '', '', '2022-12-05 13:31:26');

-- ----------------------------
-- Table structure for product_stock
-- ----------------------------
DROP TABLE IF EXISTS `product_stock`;
CREATE TABLE `product_stock`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
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
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品库存表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_stock
-- ----------------------------
INSERT INTO `product_stock` VALUES (1, '5544adas', '1376373278360207360', '1387977447487569920', 100, 50, 50, 'NO', 0, 1, '', '', '2022-11-29 13:11:54', '', '', '2022-12-05 13:30:42');
INSERT INTO `product_stock` VALUES (2, 'asdasdsadsa', '1376529925690884096', '1387977574864388096', 500, 400, 100, 'NO', 0, 1, '', '', '2022-11-29 13:12:09', '', '', '2022-12-05 13:30:51');

-- ----------------------------
-- Table structure for product_stock_transaction
-- ----------------------------
DROP TABLE IF EXISTS `product_stock_transaction`;
CREATE TABLE `product_stock_transaction`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '库存流水表' ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品标签表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_tag
-- ----------------------------
INSERT INTO `product_tag` VALUES (1, '1376373278360207360', '', '12134243423', '潮流好货', 'ON', 'NO', 0, 1, '', '', '2022-12-04 05:51:00', '', '', '2022-12-05 13:35:49');
INSERT INTO `product_tag` VALUES (2, '1376529925690884096', '', 's212133', '当季新品', 'ON', 'NO', 0, 1, '', '', '2022-12-05 13:36:10', '', '', '2022-12-05 14:08:49');

-- ----------------------------
-- Table structure for sale_order
-- ----------------------------
DROP TABLE IF EXISTS `sale_order`;
CREATE TABLE `sale_order`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `shop_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '店铺名称',
  `sale_order_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '订单编号',
  `source` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '订单来源',
  `status` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '订单状态 已创建  待审核  审核通过 审核拒绝  待发货  部分发货  待收货  部分收货  已完成  已取消',
  `pay_type` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '支付类型  微信 支付宝  现金 银行卡',
  `shop_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '店铺名称',
  `pay_mode` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '支付方式  线上  线下',
  `pay_time` datetime(0) NULL DEFAULT NULL COMMENT '支付时间',
  `pay_status` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'WAIT_PAY' COMMENT '支付状态 待支付 支付成功 支付失败',
  `pay_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '支付编号',
  `total_payable_amount` decimal(10, 0) NOT NULL COMMENT '应付金额',
  `total_payment_amount` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '实付金额',
  `total_coupon_amount` decimal(10, 0) NOT NULL COMMENT '使用优惠券金额',
  `total_cost_amount` decimal(10, 0) NOT NULL COMMENT '成本价总额',
  `total_sale_amount` decimal(10, 0) NOT NULL COMMENT '未参与营销的总额',
  `cancel_type` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '取消类型',
  `cancel_reason` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '取消原因',
  `order_remark` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '订单备注',
  `sort` int(0) NOT NULL DEFAULT 0 COMMENT '排序',
  `version` int(0) NOT NULL DEFAULT 1 COMMENT '乐观锁',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建人',
  `creator_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建人编号',
  `created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modifier` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '更新人',
  `modifier_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '更新人编号',
  `modified_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '订单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sale_order
-- ----------------------------

-- ----------------------------
-- Table structure for sale_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `sale_order_detail`;
CREATE TABLE `sale_order_detail`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `sale_order_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '订单编号',
  `product_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '商品编码',
  `product_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '商品名称',
  `sku_code` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '商品编码',
  `sku_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '商品sku名称',
  `shop_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '店铺名称',
  `shop_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '店铺名称',
  `buy_qty` decimal(10, 0) NOT NULL COMMENT '购买数量',
  `main_image` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '商品主图',
  `order_sku_type` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'COMMON' COMMENT '赠品   正常购买商品',
  `cost_price` decimal(10, 0) NOT NULL COMMENT '成本价总额',
  `sale_price` decimal(10, 0) NOT NULL COMMENT '销售金额（未参与营销）',
  `transaction_price` decimal(10, 0) NOT NULL COMMENT '成交价(扣除各种优惠后的)',
  `marketing_price` decimal(10, 0) NOT NULL COMMENT '市场价',
  `payable_amount` decimal(10, 0) NOT NULL COMMENT '应付金额',
  `payment_amount` decimal(10, 0) NOT NULL COMMENT '实付金额',
  `coupon_amount` decimal(10, 0) NOT NULL COMMENT '使用优惠券金额',
  `remark` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '备注',
  `sort` int(0) NOT NULL DEFAULT 0 COMMENT '排序',
  `version` int(0) NOT NULL DEFAULT 1 COMMENT '乐观锁',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建人',
  `creator_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建人编号',
  `created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modifier` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '更新人',
  `modifier_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '更新人编号',
  `modified_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '订单详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sale_order_detail
-- ----------------------------

-- ----------------------------
-- Table structure for shopping_cart
-- ----------------------------
DROP TABLE IF EXISTS `shopping_cart`;
CREATE TABLE `shopping_cart`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `sku_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '商品编码',
  `sku_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '商品sku名称',
  `product_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '商品编码',
  `buy_qty` int(0) NOT NULL DEFAULT 0 COMMENT '购买数量',
  `shop_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '店铺编号',
  `shop_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '店铺名称',
  `add_cart_price` decimal(12, 4) NOT NULL COMMENT '加入购物车时价格',
  `main_image` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT 'sku主图',
  `sort` int(0) NOT NULL DEFAULT 0 COMMENT '排序',
  `version` int(0) NOT NULL DEFAULT 1 COMMENT '乐观锁',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建人',
  `creator_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建人编号',
  `created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modifier` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '更新人',
  `modifier_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '更新人编号',
  `modified_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '购物车' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shopping_cart
-- ----------------------------
INSERT INTO `shopping_cart` VALUES (1, '122343545544787551', '山地自行车sqsqsq', '12545544787551', 10, 'sasqqq', '测试店铺', 20.0000, '', 0, 1, '', 'wang', '2022-12-01 13:47:53', '', '', '2022-12-01 13:50:36');

SET FOREIGN_KEY_CHECKS = 1;
