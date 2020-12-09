/*
 Navicat Premium Data Transfer

 Source Server         : docker_3339
 Source Server Type    : MySQL
 Source Server Version : 50732
 Source Host           : localhost:3339
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50732
 File Encoding         : 65001

 Date: 09/12/2020 02:30:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, '小葵');
INSERT INTO `user` VALUES (2, '小葵');
INSERT INTO `user` VALUES (4, '小葵');
INSERT INTO `user` VALUES (5, '小葵');
INSERT INTO `user` VALUES (6, '小葵');
INSERT INTO `user` VALUES (7, '小葵');
INSERT INTO `user` VALUES (8, '小葵');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
