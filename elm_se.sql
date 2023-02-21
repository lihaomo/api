/*
 Navicat Premium Data Transfer

 Source Server         : java5mysql
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : localhost:3306
 Source Schema         : elm_se

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 21/02/2023 22:56:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for table_order
-- ----------------------------
DROP TABLE IF EXISTS `table_order`;
CREATE TABLE `table_order`  (
  `id` int(10) NOT NULL,
  `price` decimal(10, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of table_order
-- ----------------------------
INSERT INTO `table_order` VALUES (3, 50.00);

-- ----------------------------
-- Table structure for table_waller
-- ----------------------------
DROP TABLE IF EXISTS `table_waller`;
CREATE TABLE `table_waller`  (
  `waller` decimal(11, 2) NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of table_waller
-- ----------------------------
INSERT INTO `table_waller` VALUES (960.00, 'zhangsan');
INSERT INTO `table_waller` VALUES (30.00, 'wangwu');

SET FOREIGN_KEY_CHECKS = 1;
