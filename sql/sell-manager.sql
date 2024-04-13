/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50556
 Source Host           : localhost:3306
 Source Schema         : sell-manager

 Target Server Type    : MySQL
 Target Server Version : 50556
 File Encoding         : 65001

 
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名字',
  `company_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司地址',
  `person` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机',
  `profession` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '行业',
  `province` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省份',
  `city` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '城市',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '负责人id',
  `customer_state` int(11) NULL DEFAULT NULL COMMENT '客户状态  0:新建完成   1:跟进中  2:即将成交   3:成交过的',
  `competitor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '竞争对手',
  `note` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户备注',
  `bank_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开户行',
  `bank_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开户账号',
  `bill_address` varchar(400) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发票地址',
  `bill_person` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发票人',
  `bill_phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发票手机号',
  `bill_note` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开票的备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '客户表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for customer_follow
-- ----------------------------
DROP TABLE IF EXISTS `customer_follow`;
CREATE TABLE `customer_follow`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `customer_id` bigint(20) NULL DEFAULT NULL COMMENT '跟进客户id',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建用户id',
  `create_time` datetime NULL DEFAULT NULL COMMENT '跟进时间',
  `subject` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主题',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '跟进内容',
  `next_time` date NULL DEFAULT NULL COMMENT '下次跟进时间',
  `next_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '下次跟进内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '创建用户id',
  `customer_id` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '客户id',
  `create_date` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `total_money` decimal(10, 2) NULL DEFAULT NULL COMMENT '总金额',
  `already_pay` decimal(10, 2) NULL DEFAULT NULL COMMENT '已经支付的钱数',
  `next_pay_time` date NULL DEFAULT NULL COMMENT '下次的支付时间',
  `discount` decimal(10, 2) NULL DEFAULT NULL COMMENT '回扣',
  `product_state` tinyint(2) UNSIGNED NULL DEFAULT NULL COMMENT '0未发货   1发货了部分产品   2全部发货完成',
  `pay_state` tinyint(2) UNSIGNED NULL DEFAULT NULL COMMENT '订单支付状态: 0未支付   1 支付部分   2支付完成',
  `send_time` date NULL DEFAULT NULL COMMENT '发货时间',
  `send_address` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `send_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '运费',
  `send_way` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发货方式',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `order_id` bigint(20) NULL DEFAULT NULL COMMENT '订单的id',
  `product_id` bigint(20) NULL DEFAULT NULL COMMENT '产品id',
  `product_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品编码',
  `product_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品名字',
  `amount` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '数量',
  `univalent` decimal(10, 2) NULL DEFAULT NULL COMMENT '单价',
  `total_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '总价',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_order_product_id`(`order_id`, `product_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单明细order_detail' ROW_FORMAT = Compact;


-- ----------------------------
-- Table structure for order_target
-- ----------------------------
DROP TABLE IF EXISTS `order_target`;
CREATE TABLE `order_target`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `start_time` date NULL DEFAULT NULL COMMENT '目标开始时间',
  `end_time` date NULL DEFAULT NULL COMMENT '目标结束时间',
  `target` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '目标',
  `has_done` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '已经完成',
  `target_state` tinyint(1) NULL DEFAULT NULL COMMENT '目标状态 0 未完成   1已经完成',
  `user_id` bigint(20) UNSIGNED NOT NULL COMMENT '关联到的用户, 公司整体的为0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '目标记录' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名字',
  `code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品编码',
  `create_time` date NULL DEFAULT NULL COMMENT '创建时间',
  `supplier` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供应商',
  `note` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注',
  `product_unit` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品单位',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 43 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '产品表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for to_do_list
-- ----------------------------
DROP TABLE IF EXISTS `to_do_list`;
CREATE TABLE `to_do_list`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) UNSIGNED NOT NULL COMMENT '创建者用户id',
  `content` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '待办事项内容',
  `create_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建日期',
  `do_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '计划完成时间',
  `work_state` tinyint(1) NULL DEFAULT NULL COMMENT '任务状态,0 未完成, 1已完成',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '代办事项表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名字',
  `age` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '年龄',
  `phone` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `role` tinyint(1) UNSIGNED NULL DEFAULT NULL COMMENT '身份:1普通用户   0管理员',
  `user_state` int(11) NULL DEFAULT NULL COMMENT '当前用户状态 0 正常 1 限制登录  2 已经被删除的用户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 130 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
