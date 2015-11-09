/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : aiaole

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2015-01-11 23:17:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `apppush_list`
-- ----------------------------
DROP TABLE IF EXISTS `apppush_list`;
CREATE TABLE `apppush_list` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `userid` varchar(32) DEFAULT NULL,
  `title` varchar(200) DEFAULT NULL,
  `text` varchar(500) DEFAULT NULL,
  `ts` datetime DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `sendtime` datetime DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of apppush_list
-- ----------------------------
INSERT INTO `apppush_list` VALUES ('1', 'f9aa885b483e3a4001483f6f2fce0003', '数据库测试', '数据库测试11', '2015-01-11 21:07:40', '1', '2015-01-11 21:08:02', null);

-- ----------------------------
-- Table structure for `sms_list`
-- ----------------------------
DROP TABLE IF EXISTS `sms_list`;
CREATE TABLE `sms_list` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `content` varchar(500) DEFAULT NULL,
  `telephone` varchar(50) DEFAULT NULL,
  `send_time` datetime DEFAULT NULL,
  `ts` datetime DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sms_list
-- ----------------------------
INSERT INTO `sms_list` VALUES ('1', '测试', '18502713158', '2015-01-11 15:10:09', '2015-01-11 15:04:49', '1');
