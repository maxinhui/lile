/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50611
Source Host           : localhost:3306
Source Database       : lile

Target Server Type    : MYSQL
Target Server Version : 50611
File Encoding         : 65001

Date: 2017-04-14 09:26:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `member_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `open_id` varchar(100) DEFAULT NULL,
  `is_li` varchar(2) DEFAULT NULL,
  `is_le` varchar(2) DEFAULT NULL,
  `is_all` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of member
-- ----------------------------
INSERT INTO `member` VALUES ('8', 'ohYjowtUOhTaX_OUJhNg5L74UHSE', 'Y', 'Y', 'Y');

-- ----------------------------
-- Table structure for member_ac
-- ----------------------------
DROP TABLE IF EXISTS `member_ac`;
CREATE TABLE `member_ac` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `open_id` varchar(100) DEFAULT NULL COMMENT '微信openId',
  `sanc_time` datetime DEFAULT NULL COMMENT '扫码时间',
  `rite_id` bigint(20) DEFAULT NULL COMMENT '小姐识别id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of member_ac
-- ----------------------------
INSERT INTO `member_ac` VALUES ('8', 'ohYjowtUOhTaX_OUJhNg5L74UHSE', '2017-03-22 11:41:40', '1');
INSERT INTO `member_ac` VALUES ('9', 'ohYjowtUOhTaX_OUJhNg5L74UHSE', '2017-03-22 11:46:45', '8');

-- ----------------------------
-- Table structure for member_draw
-- ----------------------------
DROP TABLE IF EXISTS `member_draw`;
CREATE TABLE `member_draw` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `open_id` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `rite_code` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of member_draw
-- ----------------------------
INSERT INTO `member_draw` VALUES ('1', 'ohYjowtUOhTaX_OUJhNg5L74UHSE', '2017-03-22 11:47:01', 'Y008');

-- ----------------------------
-- Table structure for rite
-- ----------------------------
DROP TABLE IF EXISTS `rite`;
CREATE TABLE `rite` (
  `rite_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `rite_code` varchar(255) DEFAULT NULL COMMENT '识别码',
  `address` varchar(255) DEFAULT NULL COMMENT '所在区域',
  `count` bigint(11) DEFAULT '0' COMMENT '访问流量',
  `draw_count` bigint(11) DEFAULT '0' COMMENT '各点兑换奖品数量',
  PRIMARY KEY (`rite_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rite
-- ----------------------------
INSERT INTO `rite` VALUES ('1', 'Y001', 'mapA', '16', '0');
INSERT INTO `rite` VALUES ('2', 'Y002', 'mapB', '4', '0');
INSERT INTO `rite` VALUES ('3', 'Y003', 'mapC', '0', '0');
INSERT INTO `rite` VALUES ('4', 'Y004', 'mapD', '4', '0');
INSERT INTO `rite` VALUES ('5', 'Y005', 'mapE', '0', '0');
INSERT INTO `rite` VALUES ('6', 'Y006', 'mapF', '0', '0');
INSERT INTO `rite` VALUES ('7', 'Y007', 'mapG', '0', '0');
INSERT INTO `rite` VALUES ('8', 'Y008', 'mapH', '1', '1');
INSERT INTO `rite` VALUES ('9', '0000', 'end', '1', '0');

-- ----------------------------
-- Table structure for wx_config
-- ----------------------------
DROP TABLE IF EXISTS `wx_config`;
CREATE TABLE `wx_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `access_token` varchar(255) DEFAULT NULL COMMENT '微信AccessToken',
  `js_api_ticket` varchar(255) DEFAULT NULL COMMENT '微信JsApiTicket',
  `expires_in` bigint(11) DEFAULT NULL COMMENT '刷新时间',
  `refresh_time` bigint(20) DEFAULT NULL COMMENT '刷新时间',
  `corp_id` varchar(50) DEFAULT NULL COMMENT 'appId',
  `corp_secret` varchar(100) DEFAULT NULL COMMENT 'appSecret',
  `pram_token` varchar(255) DEFAULT NULL COMMENT '标示',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wx_config
-- ----------------------------
INSERT INTO `wx_config` VALUES ('1', '_Zgk36GCXf4hiXMli6M3ybM4Hk8vLJM55AnO6kyOHn0j2NchfJI4WVM8_ncgrldgqe5VcvdRJCsVORRR9GQnhNSDlbM3l0qmatiaWKHOgp5fwg6IJVO9FRwAdtwDsLtoRPKiADAZXS', 'kgt8ON7yVITDhtdwci0qeZpOd3Yf5wWpz03vI4skAZRQbySCyo1_pPhaWr4W8eHO-OCDtXjpJ9q7u--33-1i7g', '7000', '1490153865', 'wx35eed173d89a147d', '7d843ccad16898bea30809a71ae69f70', 'lile');
