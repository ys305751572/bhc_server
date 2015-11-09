/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50510
Source Host           : 127.0.0.1:3306
Source Database       : biolandhealthycloud

Target Server Type    : MYSQL
Target Server Version : 50510
File Encoding         : 65001

Date: 2014-11-15 00:41:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `adspublish`
-- ----------------------------
DROP TABLE IF EXISTS `adspublish`;
CREATE TABLE `adspublish` (
  `adspublish_id` varchar(32) NOT NULL,
  `imageads_id` varchar(32) DEFAULT NULL,
  `receive_id` varchar(32) DEFAULT NULL,
  `publish_user` varchar(50) DEFAULT NULL,
  `publish_org` varchar(50) DEFAULT NULL,
  `publish_time` datetime DEFAULT NULL,
  `bak1` varchar(20) DEFAULT NULL,
  `bak2` varchar(50) DEFAULT NULL,
  `bak3` varchar(200) DEFAULT NULL,
  `bak4` varchar(500) DEFAULT NULL,
  `bak5` datetime DEFAULT NULL,
  `bak6` datetime DEFAULT NULL,
  `bak7` double DEFAULT NULL,
  `bak8` double DEFAULT NULL,
  `dr` int(11) DEFAULT NULL,
  `ts` datetime DEFAULT NULL,
  PRIMARY KEY (`adspublish_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of adspublish
-- ----------------------------

-- ----------------------------
-- Table structure for `attach`
-- ----------------------------
DROP TABLE IF EXISTS `attach`;
CREATE TABLE `attach` (
  `attach_id` char(32) NOT NULL,
  `attach_name` varchar(200) DEFAULT NULL,
  `attach_truename` varchar(200) DEFAULT NULL,
  `attach_path` varchar(200) DEFAULT NULL,
  `attach_size` decimal(18,4) DEFAULT NULL,
  `attach_user` char(32) DEFAULT NULL,
  `bak1` varchar(20) DEFAULT NULL,
  `bak2` varchar(50) DEFAULT NULL,
  `bak3` varchar(200) DEFAULT NULL,
  `bak4` varchar(500) DEFAULT NULL,
  `bak5` datetime DEFAULT NULL,
  `bak6` datetime DEFAULT NULL,
  `bak7` double DEFAULT NULL,
  `bak8` double DEFAULT NULL,
  `dr` int(11) DEFAULT NULL,
  `ts` datetime DEFAULT NULL,
  PRIMARY KEY (`attach_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of attach
-- ----------------------------

-- ----------------------------
-- Table structure for `device`
-- ----------------------------
DROP TABLE IF EXISTS `device`;
CREATE TABLE `device` (
  `device_id` varchar(32) NOT NULL,
  `user_id` varchar(32) DEFAULT NULL,
  `device_pro_year` varchar(50) DEFAULT NULL,
  `device_pro_month` varchar(50) DEFAULT NULL,
  `device_type` varchar(50) DEFAULT NULL,
  `device_serial` varchar(50) DEFAULT NULL,
  `version` varchar(25) DEFAULT NULL,
  `bak1` varchar(50) DEFAULT NULL,
  `bak2` varchar(50) DEFAULT NULL,
  `bak3` varchar(50) DEFAULT NULL,
  `bak4` varchar(50) DEFAULT NULL,
  `bak5` timestamp NULL DEFAULT NULL,
  `bak6` timestamp NULL DEFAULT NULL,
  `bak7` double DEFAULT NULL,
  `bak8` double DEFAULT NULL,
  `dr` int(11) DEFAULT NULL,
  `ts` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`device_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of device
-- ----------------------------
INSERT INTO `device` VALUES ('f9aa885b48e9c5210148ef6698ef0008', '00000000000000000000000000000012', '2014', '1', '2', '0805FA', '1.0', '0', null, null, null, null, '2014-11-08 11:30:56', null, null, '0', '2014-11-08 11:30:56');
INSERT INTO `device` VALUES ('f9aa885b48e9c5210148ef6698ef2316', '00000000000000000000000000000014', '2014', '2', '2', 'F003156', '1.0', '1', null, null, null, null, '2014-11-08 11:30:56', null, null, '0', '2014-11-08 11:30:56');
INSERT INTO `device` VALUES ('f9aa885b48e9c5210148ef6698ef9251', '00000000000000000000000000000012', '2014', '3', '2', 'F003147', '1.0', '1', null, null, null, null, '2014-11-08 11:30:56', null, null, '0', '2014-11-08 11:30:56');
INSERT INTO `device` VALUES ('f9aa885b48e9c5210148ef6698efs306', '00000000000000000000000000000014', '2014', '4', '2', 'F003118', '1.0', '1', null, null, null, null, '2014-11-08 11:30:56', null, null, '0', '2014-11-08 11:30:56');
INSERT INTO `device` VALUES ('f9aa885b48e9c5210148f7fac1230020', '00000000000000000000000000000012', '2014', '5', '4', 'F81012', '1.0', '0', null, null, null, null, '2014-11-08 11:30:56', null, null, '0', '2014-11-08 11:30:56');
INSERT INTO `device` VALUES ('f9aa885b48e9c5210148f8cf2357008a', '00000000000000000000000000000014', '2014', '6', '1', 'FA516B', '1.0', '0', null, null, null, null, '2014-11-08 11:30:56', null, null, '0', '2014-11-08 11:30:56');
INSERT INTO `device` VALUES ('f9aa885b48e9c5210148f904e93e04c6', '00000000000000000000000000000014', '2014', '7', '2', '34F7E6', '1.0', '0', null, null, null, null, '2014-11-08 11:30:56', null, null, '0', '2014-11-08 11:30:56');
INSERT INTO `device` VALUES ('f9aa885b48e9c5210148f9090bac068b', '00000000000000000000000000000017', '2014', '8', '1', 'A637A6', '1.0', '0', null, null, null, null, '2014-11-08 11:30:56', null, null, '0', '2014-11-08 11:30:56');
INSERT INTO `device` VALUES ('f9aa885b48e9c5210148f90bb479070c', '00000000000000000000000000000017', '2014', '9', '1', 'CA13F3', '1.0', '0', null, null, null, null, '2014-11-08 11:30:56', null, null, '0', '2014-11-08 11:30:56');
INSERT INTO `device` VALUES ('f9aa885b48e9c5210148fdc5ebec0773', '00000000000000000000000000000017', '2014', '10', '1', 'E554D5', '1.0', '0', null, null, null, null, '2014-11-08 11:30:56', null, null, '0', '2014-11-08 11:30:56');
INSERT INTO `device` VALUES ('f9aa885b48e9c5210148fddb0b9f0808', '00000000000000000000000000000017', '2014', '9', '3', '7D7738', '1.0', '0', null, null, null, null, '2014-11-08 11:30:56', null, null, '0', '2014-11-08 11:30:56');
INSERT INTO `device` VALUES ('f9aa885b48e9c52101491dda9fbd0831', '00000000000000000000000000000017', '2014', '8', '4', '3D7C5C', '1.0', '0', null, null, null, null, '2014-11-08 11:30:56', null, null, '0', '2014-11-08 11:30:56');
INSERT INTO `device` VALUES ('f9aa885b48e9c52101492c629eb40869', '00000000000000000000000000000017', '2014', '10', '3', '9B34FB', '1.0', '0', null, null, null, null, '2014-11-08 11:30:56', null, null, '0', '2014-11-08 11:30:56');

-- ----------------------------
-- Table structure for `device_coordinate`
-- ----------------------------
DROP TABLE IF EXISTS `device_coordinate`;
CREATE TABLE `device_coordinate` (
  `device_coordinate_id` varchar(32) NOT NULL,
  `device_id` varchar(32) DEFAULT NULL,
  `x_coordinate` varchar(50) DEFAULT NULL,
  `y_coordinate` varchar(50) DEFAULT NULL,
  `coordinate_time` timestamp NULL DEFAULT NULL,
  `bak1` varchar(255) DEFAULT NULL,
  `bak2` varchar(255) DEFAULT NULL,
  `bak3` varchar(255) DEFAULT NULL,
  `bak4` varchar(255) DEFAULT NULL,
  `bak5` datetime DEFAULT NULL,
  `bak6` datetime DEFAULT NULL,
  `bak7` double DEFAULT NULL,
  `bak8` double DEFAULT NULL,
  `dr` int(11) DEFAULT NULL,
  `ts` datetime DEFAULT NULL,
  PRIMARY KEY (`device_coordinate_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of device_coordinate
-- ----------------------------
INSERT INTO `device_coordinate` VALUES ('111', 'f9aa885b48e9c5210148f904e93e04c6', '114.277296', '30.582164', null, null, null, null, '1', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('222', 'f9aa885b48e9c5210148f90bb479070c', '114.276996', '30.58402', null, null, null, null, '1', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30ced0200130cf1f83390609', 'f9aa885b48e9c5210148f904e93e04c6', '114.266031', '30.584473', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30ced0200130d3f77ea10658', 'f9aa885b48e9c5210148f90bb479070c', '114.270237', '30.586283', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30ced0200130d3fb4a87065b', 'f9aa885b48e9c5210148ef6698ef0008', '114.267147', '30.605492', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30ced0200130d42ca62f066c', 'f9aa885b48e9c5210148ef6698ef2316', '114.25925', '30.584805', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30ced0200130d432a8ed0671', 'f9aa885b48e9c5210148ef6698ef9251', '114.251182', '30.607708', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30ced0200130d444e0900678', 'f9aa885b48e9c5210148ef6698efs306', '114.252555', '30.587613', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30d4d3090130d8daddda001b', 'f9aa885b48e9c5210148f7fac1230020', '114.284999', '30.603424', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30d4d3090130d931ea3b0027', 'f9aa885b48e9c5210148f8cf2357008a', '114.273841', '30.613323', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30d4d3090130d93391140029', 'f9aa885b48e9c5210148f904e93e04c6', '114.240367', '30.620561', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30d4d3090130d94f9c79002d', 'f9aa885b48e9c5210148f9090bac068b', '114.258735', '30.585988', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30d4d3090130d9540682002f', 'f9aa885b48e9c5210148f90bb479070c', '114.273841', '30.608595', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30d4d3090130d966bc780033', 'f9aa885b48e9c5210148fdc5ebec0773', '114.25307', '30.587318', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30d4d3090130d96b08180034', 'f9aa885b48e9c5210148fddb0b9f0808', '114.249122', '30.598105', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30d97b350130da3dbbcc0028', 'f9aa885b48e9c52101491dda9fbd0831', '114.289634', '30.603571', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30d97b350130da627a0d002d', 'f9aa885b48e9c52101492c629eb40869', '114.247577', '30.614948', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30d97b350130da6388db002f', 'f9aa885b48e9c5210148f904e93e04c6', '114.284141', '30.604015', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30d97b350130da7c2b860034', 'f9aa885b48e9c5210148f90bb479070c', '114.309719', '30.621743', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30d97b350130da89f8310038', 'f9aa885b48e9c5210148ef6698ef0008', '114.244144', '30.604458', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30d97b350130da8b20a7003a', 'f9aa885b48e9c5210148ef6698ef2316', '114.249122', '30.597218', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30d97b350130da8c5cbc003c', 'f9aa885b48e9c5210148ef6698ef9251', '114.236248', '30.612141', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30d97b350130da8dbfb2003e', 'f9aa885b48e9c5210148ef6698efs306', '114.283454', '30.578155', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30d97b350130da94637c0040', 'f9aa885b48e9c5210148f7fac1230020', '114.250324', '30.591751', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30de6a750130de8ffba2000f', 'f9aa885b48e9c5210148f8cf2357008a', '114.262683', '30.613323', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30dee4440130df6e051a0014', 'f9aa885b48e9c5210148f904e93e04c6', '114.25101', '30.604162', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30dee4440130df6fcf2c0016', 'f9aa885b48e9c5210148f9090bac068b', '114.255302', '30.592785', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30dee4440130df855c000022', 'f9aa885b48e9c5210148f90bb479070c', '114.255302', '30.592785', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30dee4440130df896ca30024', 'f9aa885b48e9c5210148fdc5ebec0773', '114.265087', '30.582589', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30dee4440130df902f850028', 'f9aa885b48e9c5210148fddb0b9f0808', '114.256847', '30.587022', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30dee4440130df92fa13002a', 'f9aa885b48e9c52101491dda9fbd0831', '114.268863', '30.57446', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30dee4440130dfa99e100031', 'f9aa885b48e9c52101492c629eb40869', '114.291179', '30.586283', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30dee4440130dfb15f1d0035', 'f9aa885b48e9c5210148f904e93e04c6', '114.293239', '30.608447', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30dee4440130dfb6075d0037', 'f9aa885b48e9c5210148f90bb479070c', '114.266288', '30.619675', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30dee4440130dfb9071f003a', 'f9aa885b48e9c5210148ef6698ef0008', '114.256847', '30.591751', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30dee4440130dfba7f3a003c', 'f9aa885b48e9c5210148ef6698ef2316', '114.268863', '30.613323', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30dee4440130dfbb5702003e', 'f9aa885b48e9c5210148ef6698ef9251', '114.291179', '30.604162', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30dee4440130dfe598e30042', 'f9aa885b48e9c5210148ef6698efs306', '114.293239', '30.592785', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30dee4440130e33b44ad005f', 'f9aa885b48e9c5210148f7fac1230020', '114.266288', '30.592785', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30dee4440130e3468a730066', 'f9aa885b48e9c5210148f8cf2357008a', '114.244144', '30.582589', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30dee4440130e34b1ddc0071', 'f9aa885b48e9c5210148f904e93e04c6', '114.249122', '30.586283', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30dee4440130e34d23b60074', 'f9aa885b48e9c5210148f9090bac068b', '114.236248', '30.608447', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30dee4440130e37270020079', 'f9aa885b48e9c5210148f90bb479070c', '114.283454', '30.619675', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30dee4440130e45a49930198', 'f9aa885b48e9c5210148fdc5ebec0773', '114.250324', '30.591751', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30dee4440130e45fd5ea019b', 'f9aa885b48e9c5210148fddb0b9f0808', '114.262683', '30.613323', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30dee4440130e4633b70019e', 'f9aa885b48e9c52101491dda9fbd0831', '114.276019', '30.604162', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30dee4440130e464018c01a1', 'f9aa885b48e9c52101492c629eb40869', '114.277092', '30.592785', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30dee4440130e465865401a3', 'f9aa885b48e9c5210148f904e93e04c6', '114.276357', '30.592785', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30dee4440130e466954201a5', 'f9aa885b48e9c5210148f90bb479070c', '114.277382', '30.582589', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30dee4440130e46796b901a7', 'f9aa885b48e9c5210148ef6698ef0008', '114.275665', '30.586283', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30dee4440130e468c8ef01a9', 'f9aa885b48e9c5210148ef6698ef2316', '114.274823', '30.608447', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30dee4440130e4699f4101ab', 'f9aa885b48e9c5210148ef6698ef9251', '114.277613', '30.58402', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30dee4440130e46a63d701ad', 'f9aa885b48e9c5210148ef6698efs306', '114.277092', '30.583775', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30dee4440130e46b490701af', 'f9aa885b48e9c5210148f7fac1230020', '114.276357', '30.583937', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30dee4440130e46cafb501b1', 'f9aa885b48e9c5210148f8cf2357008a', '114.277382', '30.584413', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30dee4440130e46d7a7201b3', 'f9aa885b48e9c5210148f904e93e04c6', '114.275665', '30.583678', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30dee4440130e46e3d8201b6', 'f9aa885b48e9c5210148f9090bac068b', '114.274823', '30.583337', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30dee4440130e4713c6a01b9', 'f9aa885b48e9c5210148f90bb479070c', '114.277613', '30.582556', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30dee4440130e474191201bc', 'f9aa885b48e9c5210148fdc5ebec0773', '114.266031', '30.582579', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30dee4440130e475018c01bf', 'f9aa885b48e9c5210148fddb0b9f0808', '114.270237', '30.582856', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30dee4440130e47614dd01d9', 'f9aa885b48e9c52101491dda9fbd0831', '114.267147', '30.58251', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30dee4440130e478ff6a01db', 'f9aa885b48e9c52101492c629eb40869', '114.25925', '30.582977', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30dee4440130e48054ca01e0', 'f9aa885b48e9c5210148f904e93e04c6', '114.251182', '30.583415', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30dee4440130e48b3fc401e8', 'f9aa885b48e9c5210148f90bb479070c', '114.252555', '30.583281', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30e4b6d30130e4d8c19205de', 'f9aa885b48e9c5210148ef6698ef0008', '114.284999', '30.582977', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30e4b6d30130e4d97c9705e0', 'f9aa885b48e9c5210148ef6698ef2316', '114.277382', '30.583415', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30e4b6d30130f2c330d10600', 'f9aa885b48e9c5210148ef6698ef9251', '114.275665', '30.583281', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30e4b6d30130f2d509070607', 'f9aa885b48e9c5210148ef6698efs306', '114.274823', '30.583646', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30e4b6d30130f2d91b9d0609', 'f9aa885b48e9c5210148f7fac1230020', '114.277613', '30.584473', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30e4b6d30130f2dbd9ae060c', 'f9aa885b48e9c5210148f8cf2357008a', '114.266031', '30.586283', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30e4b6d30130f2dd9df6060f', 'f9aa885b48e9c5210148f904e93e04c6', '114.270237', '30.605492', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30f3a89b0130f44c69760076', 'f9aa885b48e9c5210148f9090bac068b', '114.267147', '30.584805', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30f3a89b0130f87a43a400a5', 'f9aa885b48e9c5210148f90bb479070c', '114.25925', '30.607708', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30f8d77e0130f99d349d0014', 'f9aa885b48e9c5210148fdc5ebec0773', '114.276905', '30.598105', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30f8d77e0130fcf0f770001e', 'f9aa885b48e9c5210148fddb0b9f0808', '114.276196', '30.603571', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30f8d77e0130fcf1e3250020', 'f9aa885b48e9c52101491dda9fbd0831', '114.276019', '30.614948', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30f8d77e0130fcff53840024', 'f9aa885b48e9c52101492c629eb40869', '114.277092', '30.604015', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30f8d77e0130fd00be370026', 'f9aa885b48e9c5210148f904e93e04c6', '114.276357', '30.621743', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30f8d77e0130fd0dc6ef0028', 'f9aa885b48e9c5210148f9090bac068b', '114.277382', '30.604458', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30f8d77e0130fd0e73c0002b', 'f9aa885b48e9c5210148f90bb479070c', '114.275665', '30.597218', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30f8d77e0131074348d2013b', 'f9aa885b48e9c5210148fdc5ebec0773', '114.274823', '30.612141', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30f8d77e01310769276e014a', 'f9aa885b48e9c5210148fddb0b9f0808', '114.277613', '30.578155', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30f8d77e0131076b2eae014e', 'f9aa885b48e9c52101491dda9fbd0831', '114.266031', '30.591751', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30f8d77e0131076e07030151', 'f9aa885b48e9c52101492c629eb40869', '114.270237', '30.613323', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30f8d77e01310784f789015c', 'f9aa885b48e9c5210148f904e93e04c6', '114.267147', '30.604162', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30f8d77e0131078c99150160', 'f9aa885b48e9c5210148f9090bac068b', '114.25925', '30.592785', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30f8d77e0131078d1ff00162', 'f9aa885b48e9c5210148f90bb479070c', '114.251182', '30.592785', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30f8d77e0131078dde3f0164', 'f9aa885b48e9c5210148fdc5ebec0773', '114.252555', '30.582579', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30f8d77e013107ce826b0196', 'f9aa885b48e9c5210148fddb0b9f0808', '114.247577', '30.582856', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30f8d77e013107d1ec73019d', 'f9aa885b48e9c52101491dda9fbd0831', '114.284141', '30.58251', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c30f8d77e013107e0a47b01a9', 'f9aa885b48e9c52101492c629eb40869', '114.309719', '30.582977', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c310862bb01310862d8e60004', 'f9aa885b48e9c5210148fddb0b9f0808', '114.244144', '30.583415', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c31089228013108b789df0027', 'f9aa885b48e9c52101491dda9fbd0831', '114.249122', '30.583281', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('4028808c31089228013108ccab730033', 'f9aa885b48e9c52101492c629eb40869', '114.236248', '30.598105', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('f9aa885b48e9c5210148ef6698ef0008', 'f9aa885b48e9c5210148ef6698ef0008', '114.278015', '30.583775', null, null, null, null, '1', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('f9aa885b48e9c5210148ef6698ef2316', 'f9aa885b48e9c5210148ef6698ef2316', '114.275349', '30.583937', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('f9aa885b48e9c5210148ef6698ef9251', 'f9aa885b48e9c5210148ef6698ef9251', '114.276186', '30.584413', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('f9aa885b48e9c5210148ef6698efs306', 'f9aa885b48e9c5210148ef6698efs306', '114.2764', '30.583678', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('f9aa885b48e9c5210148f7fac1230020', 'f9aa885b48e9c5210148f7fac1230020', '114.276905', '30.583337', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('f9aa885b48e9c5210148f8cf2357008a', 'f9aa885b48e9c5210148f8cf2357008a', '114.276196', '30.582556', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('f9aa885b48e9c5210148f904e93e04c6', 'f9aa885b48e9c5210148f904e93e04c6', '114.276019', '30.582579', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('f9aa885b48e9c5210148f9090bac068b', 'f9aa885b48e9c5210148f9090bac068b', '114.277092', '30.582856', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('f9aa885b48e9c5210148f90bb479070c', 'f9aa885b48e9c5210148f90bb479070c', '114.276357', '30.58251', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('f9aa885b48e9c5210148fdc5ebec0773', 'f9aa885b48e9c5210148fdc5ebec0773', '114.277382', '30.582977', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('f9aa885b48e9c5210148fddb0b9f0808', 'f9aa885b48e9c5210148fddb0b9f0808', '114.275665', '30.583415', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('f9aa885b48e9c52101491dda9fbd0831', 'f9aa885b48e9c52101491dda9fbd0831', '114.274823', '30.583281', null, null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `device_coordinate` VALUES ('f9aa885b48e9c52101492c629eb40869', 'f9aa885b48e9c52101492c629eb40869', '114.277613', '30.583646', null, null, null, null, '0', null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `imageads`
-- ----------------------------
DROP TABLE IF EXISTS `imageads`;
CREATE TABLE `imageads` (
  `imageads_id` varchar(32) NOT NULL,
  `title` varchar(200) DEFAULT NULL,
  `ads_desc` varchar(500) DEFAULT NULL,
  `ads_type` varchar(10) DEFAULT NULL,
  `image_url` varchar(100) DEFAULT NULL,
  `ads_link` varchar(100) DEFAULT NULL,
  `ads_state` varchar(10) DEFAULT NULL,
  `create_user` varchar(50) DEFAULT NULL,
  `create_org` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `bak1` varchar(20) DEFAULT NULL,
  `bak2` varchar(50) DEFAULT NULL,
  `bak3` varchar(200) DEFAULT NULL,
  `bak4` varchar(500) DEFAULT NULL,
  `bak5` datetime DEFAULT NULL,
  `bak6` datetime DEFAULT NULL,
  `bak7` double DEFAULT NULL,
  `bak8` double DEFAULT NULL,
  `dr` int(11) DEFAULT NULL,
  `ts` datetime DEFAULT NULL,
  PRIMARY KEY (`imageads_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of imageads
-- ----------------------------

-- ----------------------------
-- Table structure for `log`
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `log_id` varchar(32) NOT NULL,
  `logcontent` varchar(200) DEFAULT NULL,
  `userid` varchar(200) DEFAULT NULL,
  `logtime` timestamp NULL DEFAULT NULL,
  `dr` int(11) DEFAULT NULL,
  `ts` timestamp NULL DEFAULT NULL,
  `bak1` varchar(100) DEFAULT NULL,
  `bak2` varchar(100) DEFAULT NULL,
  `bak3` varchar(100) DEFAULT NULL,
  `bak4` varchar(100) DEFAULT NULL,
  `bak5` double DEFAULT NULL,
  `bak6` double DEFAULT NULL,
  `bak7` timestamp NULL DEFAULT NULL,
  `bak8` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of log
-- ----------------------------
INSERT INTO `log` VALUES ('402880ea49aecb4d0149af0b0bff0000', 'viewOwnInfo', '00000000000000000000000000000000', '2014-11-15 00:02:57', '0', '2014-11-15 00:02:57', null, null, null, null, null, null, null, null);
INSERT INTO `log` VALUES ('402880ea49af106e0149af10dcd60000', 'viewOwnInfo', '00000000000000000000000000000000', '2014-11-15 00:09:18', '0', '2014-11-15 00:09:18', null, null, null, null, null, null, null, null);
INSERT INTO `log` VALUES ('402880ea49af106e0149af1187520001', 'viewOwnInfo', '00000000000000000000000000000000', '2014-11-15 00:10:01', '0', '2014-11-15 00:10:01', null, null, null, null, null, null, null, null);
INSERT INTO `log` VALUES ('402880ea49af106e0149af124c140002', 'viewOwnInfo', '00000000000000000000000000000000', '2014-11-15 00:10:52', '0', '2014-11-15 00:10:52', null, null, null, null, null, null, null, null);
INSERT INTO `log` VALUES ('402880ea49af106e0149af13f98f0003', 'viewOwnInfo', '00000000000000000000000000000000', '2014-11-15 00:12:42', '0', '2014-11-15 00:12:42', null, null, null, null, null, null, null, null);
INSERT INTO `log` VALUES ('402880ea49af106e0149af1446d30004', 'viewOwnInfo', '00000000000000000000000000000000', '2014-11-15 00:13:02', '0', '2014-11-15 00:13:02', null, null, null, null, null, null, null, null);
INSERT INTO `log` VALUES ('402880ea49af106e0149af1515290009', 'viewOwnInfo', '00000000000000000000000000000000', '2014-11-15 00:13:54', '0', '2014-11-15 00:13:54', null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `measure`
-- ----------------------------
DROP TABLE IF EXISTS `measure`;
CREATE TABLE `measure` (
  `measure_id` varchar(255) NOT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `device_id` varchar(255) DEFAULT NULL,
  `measure_state` varchar(255) DEFAULT NULL,
  `measure_type` varchar(255) DEFAULT NULL,
  `result1` varchar(255) DEFAULT NULL,
  `result2` varchar(255) DEFAULT NULL,
  `result3` varchar(255) DEFAULT NULL,
  `result4` varchar(255) DEFAULT NULL,
  `result5` varchar(255) DEFAULT NULL,
  `send_msg` varchar(255) DEFAULT NULL,
  `send_time` varchar(255) DEFAULT NULL,
  `bak1` varchar(255) DEFAULT NULL,
  `bak2` varchar(255) DEFAULT NULL,
  `bak3` varchar(255) DEFAULT NULL,
  `bak4` varchar(255) DEFAULT NULL,
  `bak5` datetime DEFAULT NULL,
  `bak6` datetime DEFAULT NULL,
  `bak7` double DEFAULT NULL,
  `dr` int(11) DEFAULT NULL,
  `ts` datetime DEFAULT NULL,
  `bak8` double DEFAULT NULL,
  PRIMARY KEY (`measure_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of measure
-- ----------------------------
INSERT INTO `measure` VALUES ('f9aa885b48e9c5210148ef7308e5000c', 'f9aa885b483bbe7201483bbe72690000', 'f9aa885b48e9c5210148ef6698ef0008', '1', '1', null, '', '', '175', null, null, '2014-10-08 19:09', '1', null, null, null, null, '2014-10-08 19:09:26', null, '0', '2014-10-22 15:46:27', null);
INSERT INTO `measure` VALUES ('f9aa885b48e9c5210148ef80492b000e', 'f9aa885b483bbe7201483bbe72690000', 'f9aa885b48e9c5210148ef6698ef0008', '1', '1', null, '', '', '108', null, null, '2014-10-08 19:23:53', '1', null, null, null, null, '2014-10-08 19:23:55', null, '0', '2014-10-22 15:46:27', null);
INSERT INTO `measure` VALUES ('f9aa885b48e9c5210148ef81a2df0010', 'f9aa885b483bbe7201483bbe72690000', 'f9aa885b48e9c5210148ef6698ef0008', '1', '1', null, '', '', '172', null, null, '2014-10-08 19:25:22', '1', null, null, null, null, '2014-10-08 19:25:23', null, '0', '2014-10-22 15:46:27', null);
INSERT INTO `measure` VALUES ('f9aa885b48e9c5210148ef8db2d30012', '00000000000000000000000000000012', 'f9aa885b48e9c5210148ef6698ef0008', '1', '2', null, '', '', '109', null, null, '2014-10-08 19:38:32', '1', null, null, null, null, '2014-10-08 19:38:34', null, '0', '2014-10-22 15:46:27', null);
INSERT INTO `measure` VALUES ('f9aa885b48e9c5210148efcafc180014', '00000000000000000000000000000012', 'f9aa885b48e9c5210148ef6698ef0008', '1', '2', null, '', '', '175', null, null, '2014-10-08 20:45:29', '1', null, null, null, null, '2014-10-08 20:45:30', null, '0', '2014-10-22 15:46:27', null);
INSERT INTO `measure` VALUES ('f9aa885b48e9c5210148effb937c0016', '00000000000000000000000000000012', 'f9aa885b48e9c5210148ef6698ef0008', '1', '2', null, '', '', '174', null, null, '2014-10-08 21:38:34', '1', null, null, null, null, '2014-10-08 21:38:35', null, '0', '2014-10-22 15:46:27', null);
INSERT INTO `measure` VALUES ('f9aa885b48e9c5210148effdbe3c0018', '00000000000000000000000000000012', 'f9aa885b48e9c5210148ef6698ef0008', '1', '2', null, '', '', '174', null, null, '2014-10-08 21:40:56', '1', null, null, null, null, '2014-10-08 21:40:57', null, '0', '2014-10-22 15:46:27', null);
INSERT INTO `measure` VALUES ('f9aa885b48e9c5210148effee910001a', '00000000000000000000000000000012', 'f9aa885b48e9c5210148ef6698ef0008', '1', '2', null, '', '', '173', null, null, '2014-10-08 21:42:12', '1', null, null, null, null, '2014-10-08 21:42:13', null, '0', '2014-10-22 15:46:27', null);
INSERT INTO `measure` VALUES ('f9aa885b48e9c5210148f011e09f001c', '00000000000000000000000000000012', 'f9aa885b48e9c5210148ef6698ef0008', '1', '2', null, '', '', '174', null, null, '2014-10-08 22:02:55', '1', null, null, null, null, '2014-10-08 22:02:56', null, '0', '2014-10-22 15:46:27', null);
INSERT INTO `measure` VALUES ('f9aa885b48e9c5210148f0216923001e', 'f9aa885b483bbe7201483bbe72690000', 'f9aa885b48e9c5210148ef6698ef0008', '1', '2', null, '', '', '172', null, null, '2014-10-08 22:19:53', '1', null, null, null, null, '2014-10-08 22:19:54', null, '0', '2014-10-22 15:46:27', null);
INSERT INTO `measure` VALUES ('f9aa885b48e9c5210148fabb7f0c0757', '00000000000000000000000000000017', 'f9aa885b48e9c5210148fddb0b9f0808', '1', '3', null, null, null, '172', null, null, '2014-10-10 23:44:23', '1', null, null, null, null, '2014-10-10 23:44:24', null, '0', '2014-10-22 15:46:27', null);
INSERT INTO `measure` VALUES ('f9aa885b48e9c5210148fabe6f4f0759', 'f9aa885b483bbe7201483bbe72690000', 'f9aa885b48e9c5210148ef6698ef0008', '1', '2', null, '', '', '173', null, null, '2014-10-10 23:47:38', '1', null, null, null, null, '2014-10-10 23:47:37', null, '0', '2014-10-22 15:46:27', null);
INSERT INTO `measure` VALUES ('f9aa885b48e9c5210148fac080aa075b', '00000000000000000000000000000017', 'f9aa885b48e9c5210148fddb0b9f0808', '1', '3', null, null, null, '172', null, null, '2014-10-10 23:49:51', '1', null, null, null, null, '2014-10-10 23:49:52', null, '0', '2014-10-22 15:46:27', null);
INSERT INTO `measure` VALUES ('f9aa885b48e9c5210148fac1bf25075d', '00000000000000000000000000000017', 'f9aa885b48e9c5210148fddb0b9f0808', '1', '3', null, null, null, '172', null, null, '2014-10-10 23:51:13', '1', null, null, null, null, '2014-10-10 23:51:14', null, '0', '2014-10-22 15:46:27', null);
INSERT INTO `measure` VALUES ('f9aa885b48e9c5210148fac2ab6a075f', 'f9aa885b483bbe7201483bbe72690000', 'f9aa885b48e9c5210148ef6698ef0008', '1', '2', null, '', '', '10', null, null, '2014-10-10 23:52:16', '1', null, null, null, null, '2014-10-10 23:52:14', null, '0', '2014-10-22 15:46:27', null);
INSERT INTO `measure` VALUES ('f9aa885b48e9c5210148fac3d2270761', 'f9aa885b483bbe7201483bbe72690000', 'f9aa885b48e9c5210148ef6698ef0008', '1', '2', null, '', '', '108', null, null, '2014-10-10 23:53:31', '1', null, null, null, null, '2014-10-10 23:53:30', null, '0', '2014-10-22 15:46:27', null);
INSERT INTO `measure` VALUES ('f9aa885b48e9c5210148fac4be8b0763', 'f9aa885b483bbe7201483bbe72690000', 'f9aa885b48e9c5210148ef6698ef0008', '1', '2', null, '', '', '171', null, null, '2014-10-10 23:54:32', '1', null, null, null, null, '2014-10-10 23:54:30', null, '0', '2014-10-22 15:46:27', null);
INSERT INTO `measure` VALUES ('f9aa885b48e9c5210148fad666220765', 'f9aa885b483bbe7201483bbe72690000', 'f9aa885b48e9c5210148ef6698ef0008', '1', '2', null, '', '', '172', null, null, '2014-10-11 00:13:48', '1', null, null, null, null, '2014-10-11 00:13:47', null, '0', '2014-10-22 15:46:27', null);
INSERT INTO `measure` VALUES ('f9aa885b48e9c5210148fb2c52420768', '00000000000000000000000000000017', 'f9aa885b48e9c5210148fddb0b9f0808', '1', '3', null, null, null, '173', null, null, '2014-10-11 01:47:37', '1', null, null, null, null, '2014-10-11 01:47:38', null, '0', '2014-10-22 15:46:27', null);
INSERT INTO `measure` VALUES ('f9aa885b48e9c5210148fb3cd3a9076a', '00000000000000000000000000000017', 'f9aa885b48e9c5210148fddb0b9f0808', '1', '3', null, null, null, '109', null, null, '2014-10-11 02:05:02', '1', null, null, null, null, '2014-10-11 02:05:40', null, '0', '2014-10-22 15:46:27', null);
INSERT INTO `measure` VALUES ('f9aa885b48e9c5210148fdc911aa0775', '00000000000000000000000000000014', 'f9aa885b48e9c5210148f8cf2357008a', '1', '1', null, '144', '86', '87', null, null, '2014-10-11 13:58:05', '1', null, null, null, null, '2014-10-11 13:58:05', null, '0', '2014-10-22 15:46:27', null);
INSERT INTO `measure` VALUES ('f9aa885b48e9c5210148ffc237180825', '00000000000000000000000000000014', 'f9aa885b48e9c5210148f8cf2357008a', '1', '1', null, '120', '50', '100', null, null, '2014-10-11 23:09:53', '1', null, null, null, null, '2014-10-11 23:09:51', null, '0', '2014-10-22 15:46:27', null);
INSERT INTO `measure` VALUES ('f9aa885b48e9c5210149140ea5d5082f', '00000000000000000000000000000014', 'f9aa885b48e9c5210148f8cf2357008a', '1', '1', null, '150', '80', '120', null, null, '2014-10-15 21:45:44', '1', null, null, null, null, '2014-10-15 21:45:44', null, '0', '2014-10-22 15:46:27', null);
INSERT INTO `measure` VALUES ('f9aa885b48e9c52101491ddb32390833', '00000000000000000000000000000014', 'f9aa885b48e9c5210148f8cf2357008a', '1', '1', null, '84', '40', '50', null, null, '2014-10-17 19:25:46', '1', null, null, null, null, '2014-10-17 19:25:44', null, '0', '2014-10-22 15:46:27', null);
INSERT INTO `measure` VALUES ('f9aa885b48e9c52101491ddb55320835', '00000000000000000000000000000014', 'f9aa885b48e9c5210148f8cf2357008a', '1', '1', null, '133', '76', '79', null, null, '2014-10-17 19:25:55', '1', null, null, null, null, '2014-10-17 19:25:53', null, '0', '2014-10-22 15:46:27', null);
INSERT INTO `measure` VALUES ('f9aa885b48e9c52101491ddb68ba0836', 'f9aa885b483bbe7201483bbe72690000', 'f9aa885b48e9c52101491dda9fbd0831', '1', '5', null, '', '', '36.4', null, null, '2014-10-17 19:26:00', '1', null, null, null, null, '2014-10-17 19:25:58', null, '0', '2014-10-22 15:46:27', null);
INSERT INTO `measure` VALUES ('f9aa885b48e9c52101491ddc49f30838', '00000000000000000000000000000012', 'f9aa885b48e9c52101491dda9fbd0831', '1', '5', null, '', '', '36.5', null, null, '2014-10-17 19:26:58', '1', null, null, null, null, '2014-10-17 19:26:56', null, '0', '2014-10-22 15:46:27', null);
INSERT INTO `measure` VALUES ('f9aa885b48e9c52101491e33fcec083c', '00000000000000000000000000000012', 'f9aa885b48e9c5210148ef6698ef0008', '1', '2', null, '', '', '178', null, null, '2014-10-17 21:02:45', '1', null, null, null, null, '2014-10-17 21:02:43', null, '0', '2014-10-22 15:46:27', null);
INSERT INTO `measure` VALUES ('f9aa885b48e9c52101491e5298cc083e', '00000000000000000000000000000017', 'f9aa885b48e9c5210148fddb0b9f0808', '1', '3', null, null, null, '180', null, null, '2014-10-17 21:36:09', '1', null, null, null, null, '2014-10-17 21:36:09', null, '0', '2014-10-22 15:46:27', null);
INSERT INTO `measure` VALUES ('f9aa885b48e9c52101491e5801cf0840', '00000000000000000000000000000017', 'f9aa885b48e9c5210148fddb0b9f0808', '1', '3', null, null, null, '179', null, null, '2014-10-17 21:42:04', '1', null, null, null, null, '2014-10-17 21:42:04', null, '0', '2014-10-22 15:46:27', null);
INSERT INTO `measure` VALUES ('f9aa885b48e9c52101491e613f590842', 'f9aa885b483e3a4001483f6f2fce0003', 'f9aa885b48e9c5210148ef6698ef0008', '1', '2', null, null, null, '180', null, null, '2014-10-17 21:52:09', '1', null, null, null, null, '2014-10-17 21:52:10', null, '0', '2014-10-22 15:46:27', null);
INSERT INTO `measure` VALUES ('f9aa885b48e9c52101491e67a6050844', 'f9aa885b483bbe7201483bbe72690000', 'f9aa885b48e9c52101491dda9fbd0831', '1', '5', null, '', '', '36.6', null, null, '2014-10-17 21:59:07', '1', null, null, null, null, '2014-10-17 21:59:09', null, '0', '2014-10-22 15:46:27', null);
INSERT INTO `measure` VALUES ('f9aa885b48e9c52101491e67cbbd0845', 'f9aa885b483bbe7201483bbe72690000', 'f9aa885b48e9c52101491dda9fbd0831', '1', '5', null, '', '', '36.5', null, null, '2014-10-17 21:59:20', '1', null, null, null, null, '2014-10-17 21:59:19', null, '0', '2014-10-22 15:46:27', null);
INSERT INTO `measure` VALUES ('f9aa885b48e9c52101491e67efa10846', 'f9aa885b483bbe7201483bbe72690000', 'f9aa885b48e9c52101491dda9fbd0831', '1', '5', null, '', '', '34.0', null, null, '2014-10-17 21:59:29', '1', null, null, null, null, '2014-10-17 21:59:28', null, '0', '2014-10-22 15:46:27', null);
INSERT INTO `measure` VALUES ('f9aa885b48e9c52101491e77188c0849', 'f9aa885b483e3a4001483f6f2fce0003', 'f9aa885b48e9c5210148ef6698ef0008', '1', '2', null, null, null, '113', null, null, '2014-10-17 22:16:01', '1', null, null, null, null, '2014-10-17 22:16:01', null, '0', '2014-10-22 15:46:27', null);
INSERT INTO `measure` VALUES ('f9aa885b48e9c521014920a2d639084d', 'f9aa885b483e3a4001483f6f2fce0003', 'f9aa885b48e9c5210148ef6698ef0008', '1', '2', null, null, null, '180', null, null, '2014-10-18 08:23:01', '1', null, null, null, null, '2014-10-18 08:23:02', null, '0', '2014-10-22 15:46:27', null);
INSERT INTO `measure` VALUES ('f9aa885b48e9c521014920a7b9c0084f', 'f9aa885b483e3a4001483f6f2fce0003', 'f9aa885b48e9c5210148ef6698ef0008', '1', '2', null, null, null, '113', null, null, '2014-10-18 08:28:22', '1', null, null, null, null, '2014-10-18 08:28:23', null, '0', '2014-10-22 15:46:27', null);
INSERT INTO `measure` VALUES ('f9aa885b48e9c521014920abc4d80852', 'f9aa885b483e3a4001483f6f2fce0003', 'f9aa885b48e9c5210148ef6698ef0008', '1', '2', null, null, null, '179', null, null, '2014-10-18 08:32:47', '1', null, null, null, null, '2014-10-18 08:32:48', null, '0', '2014-10-22 15:46:27', null);
INSERT INTO `measure` VALUES ('f9aa885b48e9c521014920cfa3c40857', '00000000000000000000000000000012', 'f9aa885b48e9c5210148ef6698ef0008', '1', '2', null, null, null, '180', null, null, '2014-10-18 09:11:58', '1', null, null, null, null, '2014-10-18 09:11:59', null, '0', '2014-10-22 15:46:27', null);
INSERT INTO `measure` VALUES ('f9aa885b48e9c521014920d13f530859', '00000000000000000000000000000012', 'f9aa885b48e9c5210148ef6698ef0008', '1', '2', null, null, null, '113', null, null, '2014-10-18 09:13:43', '1', null, null, null, null, '2014-10-18 09:13:44', null, '0', '2014-10-22 15:46:27', null);
INSERT INTO `measure` VALUES ('f9aa885b48e9c521014920d74c47085d', '00000000000000000000000000000012', 'f9aa885b48e9c5210148ef6698ef0008', '1', '2', null, null, null, '180', null, null, '2014-10-18 09:20:19', '1', null, null, null, null, '2014-10-18 09:20:21', null, '0', '2014-10-22 15:46:27', null);
INSERT INTO `measure` VALUES ('f9aa885b48e9c521014920df258e0861', 'f9aa885b483e3a4001483f6f2fce0003', 'f9aa885b48e9c5210148ef6698ef0008', '1', '2', null, null, null, '113', null, null, '2014-10-18 09:28:54', '1', null, null, null, null, '2014-10-18 09:28:55', null, '0', '2014-10-22 15:46:27', null);
INSERT INTO `measure` VALUES ('f9aa885b48e9c521014920e0c8810863', 'f9aa885b483e3a4001483f6f2fce0003', 'f9aa885b48e9c5210148ef6698ef0008', '1', '2', null, null, null, '180', null, null, '2014-10-18 09:30:37', '1', null, null, null, null, '2014-10-18 09:30:42', null, '0', '2014-10-22 15:46:27', null);
INSERT INTO `measure` VALUES ('f9aa885b48e9c521014920e598570865', 'f9aa885b483e3a4001483f6f2fce0003', 'f9aa885b48e9c5210148ef6698ef0008', '1', '2', null, null, null, '113', null, null, '2014-10-18 09:35:56', '1', null, null, null, null, '2014-10-18 09:35:58', null, '0', '2014-10-22 15:46:27', null);
INSERT INTO `measure` VALUES ('f9aa885b48e9c52101492106f9cb0867', 'f9aa885b483e3a4001483f6f2fce0003', 'f9aa885b48e9c5210148ef6698ef0008', '1', '2', null, null, null, '180', null, null, '2014-10-18 10:12:23', '1', null, null, null, null, '2014-10-18 10:12:25', null, '0', '2014-10-22 15:46:27', null);
INSERT INTO `measure` VALUES ('f9aa885b48e9c52101492cd43f7b0873', 'f9aa885b483e3a4001483f6f2fce0003', 'f9aa885b48e9c52101492c629eb40869', '1', '5', null, null, null, null, null, null, '2014-10-20 17:12:27', '1', null, null, null, null, '2014-10-20 17:12:27', null, '0', '2014-10-22 15:46:27', null);
INSERT INTO `measure` VALUES ('f9aa885b48e9c52101492cd452b50874', 'f9aa885b483e3a4001483f6f2fce0003', 'f9aa885b48e9c52101492c629eb40869', '1', '5', null, null, null, null, null, null, '2014-10-20 17:12:32', '1', null, null, null, null, '2014-10-20 17:12:32', null, '0', '2014-10-22 15:46:27', null);
INSERT INTO `measure` VALUES ('f9aa885b48e9c52101492cd9432e0876', 'f9aa885b483e3a4001483f6f2fce0003', 'f9aa885b48e9c52101492c629eb40869', '1', '5', null, null, null, '36.6', null, null, '2014-10-20 17:17:55', '1', null, null, null, null, '2014-10-20 17:17:56', null, '0', '2014-10-22 15:46:27', null);
INSERT INTO `measure` VALUES ('f9aa885b48e9c52101492cdb61f80878', 'f9aa885b483e3a4001483f6f2fce0003', 'f9aa885b48e9c52101492c629eb40869', '1', '5', null, null, null, '36.6', null, null, '2014-10-20 17:20:13', '1', null, null, null, null, '2014-10-20 17:20:15', null, '0', '2014-10-22 15:46:27', null);
INSERT INTO `measure` VALUES ('f9aa885b48e9c52101492cdb77e20879', 'f9aa885b483e3a4001483f6f2fce0003', 'f9aa885b48e9c52101492c629eb40869', '1', '5', null, null, null, '36.2', null, null, '2014-10-20 17:20:20', '1', null, null, null, null, '2014-10-20 17:20:20', null, '0', '2014-10-22 15:46:27', null);
INSERT INTO `measure` VALUES ('f9aa885b48e9c52101492cdcb1f9087b', 'f9aa885b483e3a4001483f6f2fce0003', 'f9aa885b48e9c52101492c629eb40869', '1', '5', null, null, null, '36.5', null, null, '2014-10-20 17:21:40', '1', null, null, null, null, '2014-10-20 17:21:41', null, '0', '2014-10-22 15:46:27', null);
INSERT INTO `measure` VALUES ('f9aa885b48e9c52101492d876b2d087d', 'f9aa885b483bbe7201483bbe72690000', 'f9aa885b48e9c52101491dda9fbd0831', '1', '5', null, '', '', '37.2', null, null, '2014-10-20 20:28:12', '1', null, null, null, null, '2014-10-20 20:28:09', null, '0', '2014-10-22 15:46:27', null);
INSERT INTO `measure` VALUES ('f9aa885b48e9c52101492dbd92b80881', 'f9aa885b483bbe7201483bbe72690000', 'f9aa885b48e9c52101491dda9fbd0831', '1', '5', null, '', '', '36.7', null, null, '2014-10-20 21:27:21', '1', null, null, null, null, '2014-10-20 21:27:18', null, '0', '2014-10-22 15:46:27', null);
INSERT INTO `measure` VALUES ('f9aa885b48e9c52101492dbde09a0882', 'f9aa885b483bbe7201483bbe72690000', 'f9aa885b48e9c52101491dda9fbd0831', '1', '5', null, '', '', '36.8', null, null, '2014-10-20 21:27:41', '1', null, null, null, null, '2014-10-20 21:27:38', null, '0', '2014-10-22 15:46:27', null);
INSERT INTO `measure` VALUES ('f9aa885b48e9c52101492dcbb4d40887', 'f9aa885b483e3a4001483f6f2fce0003', 'f9aa885b48e9c52101492c629eb40869', '1', '5', null, null, null, '36.5', null, null, '2014-10-20 21:42:45', '1', null, null, null, null, '2014-10-20 21:42:45', null, '0', '2014-10-22 15:46:27', null);
INSERT INTO `measure` VALUES ('f9aa885b48e9c52101492dd165a4088d', 'f9aa885b483e3a4001483f6f2fce0003', 'f9aa885b48e9c52101492c629eb40869', '1', '5', null, null, null, '34.8', null, null, '2014-10-20 21:48:57', '1', null, null, null, null, '2014-10-20 21:48:58', null, '0', '2014-10-22 15:46:27', null);
INSERT INTO `measure` VALUES ('f9aa885b48e9c52101492dd17ac2088e', 'f9aa885b483e3a4001483f6f2fce0003', 'f9aa885b48e9c52101492c629eb40869', '1', '5', null, null, null, '34.6', null, null, '2014-10-20 21:49:02', '1', null, null, null, null, '2014-10-20 21:49:03', null, '0', '2014-10-22 15:46:27', null);
INSERT INTO `measure` VALUES ('f9aa885b48e9c52101492dd1b2e9088f', 'f9aa885b483e3a4001483f6f2fce0003', 'f9aa885b48e9c52101492c629eb40869', '1', '5', null, null, null, '34.8', null, null, '2014-10-20 21:49:17', '1', null, null, null, null, '2014-10-20 21:49:17', null, '0', '2014-10-22 15:46:27', null);
INSERT INTO `measure` VALUES ('f9aa885b48e9c52101492dd1d8140890', 'f9aa885b483e3a4001483f6f2fce0003', 'f9aa885b48e9c52101492c629eb40869', '1', '5', null, null, null, '34.8', null, null, '2014-10-20 21:49:27', '1', null, null, null, null, '2014-10-20 21:49:27', null, '0', '2014-10-22 15:46:27', null);
INSERT INTO `measure` VALUES ('f9aa885b48e9c52101492dd2f5f80892', 'f9aa885b483bbe7201483bbe72690000', 'f9aa885b48e9c52101491dda9fbd0831', '1', '5', null, '', '', '35.1', null, null, '2014-10-20 21:50:43', '1', null, null, null, null, '2014-10-20 21:50:40', null, '0', '2014-10-22 15:46:27', null);
INSERT INTO `measure` VALUES ('f9aa885b48e9c52101492dd31d850893', 'f9aa885b483bbe7201483bbe72690000', 'f9aa885b48e9c52101491dda9fbd0831', '1', '5', null, '', '', '35.1', null, null, '2014-10-20 21:50:43', '1', null, null, null, null, '2014-10-20 21:50:50', null, '0', '2014-10-22 15:46:27', null);
INSERT INTO `measure` VALUES ('f9aa885b48e9c5210149385d3f9908c0', 'f9aa885b483bbe7201483bbe72690000', 'f9aa885b48e9c5210148ef6698ef0008', '1', '2', null, '', '', '176', null, null, '2014-10-22 22:57:56', '1', null, null, null, null, '2014-10-22 22:57:55', null, '0', '2014-10-22 15:46:27', null);

-- ----------------------------
-- Table structure for `messages`
-- ----------------------------
DROP TABLE IF EXISTS `messages`;
CREATE TABLE `messages` (
  `messages_id` varchar(32) NOT NULL,
  `messages_title` varchar(200) DEFAULT NULL,
  `messages_content` longtext,
  `messages_time` datetime DEFAULT NULL,
  `messages_state` int(11) DEFAULT NULL,
  `messages_own` varchar(20) DEFAULT NULL,
  `messages_sender` varchar(32) DEFAULT NULL,
  `messages_sendee` text,
  `dr` int(11) DEFAULT NULL,
  `ts` datetime DEFAULT NULL,
  `bak1` varchar(10) DEFAULT NULL,
  `bak2` varchar(20) DEFAULT NULL,
  `bak3` varchar(50) DEFAULT NULL,
  `bak4` varchar(100) DEFAULT NULL,
  `bak5` datetime DEFAULT NULL,
  `bak6` datetime DEFAULT NULL,
  `bak7` double DEFAULT NULL,
  `bak8` double DEFAULT NULL,
  `sendee_type` int(11) DEFAULT NULL,
  PRIMARY KEY (`messages_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of messages
-- ----------------------------

-- ----------------------------
-- Table structure for `organise`
-- ----------------------------
DROP TABLE IF EXISTS `organise`;
CREATE TABLE `organise` (
  `organise_id` varchar(32) NOT NULL,
  `organise_code` varchar(50) DEFAULT NULL,
  `organise_name` varchar(100) DEFAULT NULL,
  `organise_shortname` varchar(50) DEFAULT NULL,
  `telephone` varchar(100) DEFAULT NULL,
  `fax` varchar(100) DEFAULT NULL,
  `organise_address` varchar(200) DEFAULT NULL,
  `store_address` varchar(200) DEFAULT NULL,
  `website` varchar(100) DEFAULT NULL,
  `mailbox` varchar(100) DEFAULT NULL,
  `zipcode` varchar(20) DEFAULT NULL,
  `parent_id` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `bak1` varchar(20) DEFAULT NULL,
  `bak2` varchar(50) DEFAULT NULL,
  `bak3` varchar(200) DEFAULT NULL,
  `bak4` varchar(500) DEFAULT NULL,
  `bak5` datetime DEFAULT NULL,
  `bak6` datetime DEFAULT NULL,
  `bak7` double DEFAULT NULL,
  `bak8` double DEFAULT NULL,
  `dr` int(11) DEFAULT NULL,
  `ts` datetime DEFAULT NULL,
  `level_type` int(11) DEFAULT NULL,
  `attach_id` varchar(32) DEFAULT NULL,
  `user_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`organise_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of organise
-- ----------------------------
INSERT INTO `organise` VALUES ('00000000000000000000000000000000', 'aol', '爱奥乐医疗器械（深圳）有限公司', '爱奥乐', '0755-36900999', '0755-33296299', '中国广东省深圳市龙岗区宝龙五路尚荣科技工业园G栋', null, 'http://www.bioland.com.cn', null, '518116', null, '2014-10-31 00:00:00', null, null, null, null, null, null, null, null, '0', '2014-10-31 00:00:00', '0', null, '00000000000000000000000000000000');

-- ----------------------------
-- Table structure for `organisedevice`
-- ----------------------------
DROP TABLE IF EXISTS `organisedevice`;
CREATE TABLE `organisedevice` (
  `organisedevice_id` varchar(32) NOT NULL,
  `device_serial` varchar(200) DEFAULT NULL,
  `organise_id` varchar(32) DEFAULT NULL,
  `batch_number` varchar(50) DEFAULT NULL,
  `order_index` int(11) DEFAULT NULL,
  `bind_time` timestamp NULL DEFAULT NULL,
  `dr` int(11) DEFAULT NULL,
  `ts` datetime DEFAULT NULL,
  `bak1` varchar(50) DEFAULT NULL,
  `bak2` varchar(50) DEFAULT NULL,
  `bak3` varchar(50) DEFAULT NULL,
  `bak4` varchar(50) DEFAULT NULL,
  `bak5` timestamp NULL DEFAULT NULL,
  `bak6` timestamp NULL DEFAULT NULL,
  `bak7` double DEFAULT NULL,
  `bak8` double DEFAULT NULL,
  PRIMARY KEY (`organisedevice_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of organisedevice
-- ----------------------------

-- ----------------------------
-- Table structure for `security_log_entity`
-- ----------------------------
DROP TABLE IF EXISTS `security_log_entity`;
CREATE TABLE `security_log_entity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `ip_address` varchar(16) DEFAULT NULL,
  `log_level` varchar(16) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `username` varchar(32) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `ipAddress` varchar(16) DEFAULT NULL,
  `logLevel` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of security_log_entity
-- ----------------------------
INSERT INTO `security_log_entity` VALUES ('1', '2014-11-14 16:48:36', null, 'TRACE', 'admin登录了系统。', 'admin', null, null, null);
INSERT INTO `security_log_entity` VALUES ('2', '2014-11-14 17:12:35', null, 'TRACE', 'admin登录了系统。', 'admin', null, null, null);
INSERT INTO `security_log_entity` VALUES ('3', '2014-11-14 17:28:20', null, 'TRACE', 'admin登录了系统。', 'admin', null, null, null);
INSERT INTO `security_log_entity` VALUES ('4', '2014-11-14 17:30:14', null, 'TRACE', 'admin登录了系统。', 'admin', null, null, null);
INSERT INTO `security_log_entity` VALUES ('5', '2014-11-14 17:33:32', null, 'TRACE', 'admin登录了系统。', 'admin', null, null, null);
INSERT INTO `security_log_entity` VALUES ('6', '2014-11-14 21:36:29', null, 'TRACE', 'admin登录了系统。', 'admin', null, null, null);
INSERT INTO `security_log_entity` VALUES ('7', '2014-11-14 21:44:09', null, 'TRACE', 'admin登录了系统。', 'admin', null, null, null);
INSERT INTO `security_log_entity` VALUES ('8', '2014-11-14 21:54:53', null, 'TRACE', 'admin登录了系统。', 'admin', null, null, null);
INSERT INTO `security_log_entity` VALUES ('9', '2014-11-14 21:56:09', null, 'TRACE', 'admin登录了系统。', 'admin', null, null, null);
INSERT INTO `security_log_entity` VALUES ('10', '2014-11-14 22:03:52', null, 'TRACE', 'admin登录了系统。', 'admin', null, null, null);
INSERT INTO `security_log_entity` VALUES ('11', '2014-11-14 22:07:43', null, 'TRACE', 'admin登录了系统。', 'admin', null, null, null);
INSERT INTO `security_log_entity` VALUES ('12', '2014-11-14 22:09:29', null, 'TRACE', 'admin登录了系统。', 'admin', null, null, null);
INSERT INTO `security_log_entity` VALUES ('13', '2014-11-14 22:12:13', null, 'TRACE', 'admin登录了系统。', 'admin', null, null, null);
INSERT INTO `security_log_entity` VALUES ('14', '2014-11-14 22:14:06', null, 'TRACE', 'admin登录了系统。', 'admin', null, null, null);
INSERT INTO `security_log_entity` VALUES ('15', '2014-11-14 22:19:18', null, 'TRACE', 'admin登录了系统。', 'admin', null, null, null);
INSERT INTO `security_log_entity` VALUES ('16', '2014-11-14 22:22:32', null, 'TRACE', 'admin登录了系统。', 'admin', null, null, null);
INSERT INTO `security_log_entity` VALUES ('17', '2014-11-14 22:22:48', null, 'TRACE', 'admin登录了系统。', 'admin', null, null, null);
INSERT INTO `security_log_entity` VALUES ('18', '2014-11-14 22:28:11', null, 'TRACE', 'admin登录了系统。', 'admin', null, null, null);
INSERT INTO `security_log_entity` VALUES ('19', '2014-11-14 22:29:33', null, 'TRACE', 'admin登录了系统。', 'admin', null, null, null);
INSERT INTO `security_log_entity` VALUES ('20', '2014-11-14 22:30:38', null, 'TRACE', 'admin登录了系统。', 'admin', null, null, null);
INSERT INTO `security_log_entity` VALUES ('21', '2014-11-14 22:35:58', null, 'TRACE', 'admin登录了系统。', 'admin', null, null, null);
INSERT INTO `security_log_entity` VALUES ('22', '2014-11-14 22:36:45', null, 'TRACE', 'admin登录了系统。', 'admin', null, null, null);
INSERT INTO `security_log_entity` VALUES ('23', '2014-11-14 22:51:30', null, 'TRACE', 'admin登录了系统。', 'admin', null, null, null);
INSERT INTO `security_log_entity` VALUES ('24', '2014-11-14 22:53:42', null, 'TRACE', 'admin登录了系统。', 'admin', null, null, null);
INSERT INTO `security_log_entity` VALUES ('25', '2014-11-14 23:41:12', null, 'TRACE', 'admin登录了系统。', 'admin', null, null, null);
INSERT INTO `security_log_entity` VALUES ('26', '2014-11-14 23:46:06', null, 'TRACE', 'admin登录了系统。', 'admin', null, null, null);
INSERT INTO `security_log_entity` VALUES ('27', '2014-11-14 23:55:39', null, 'TRACE', 'admin登录了系统。', 'admin', null, null, null);
INSERT INTO `security_log_entity` VALUES ('28', '2014-11-14 23:59:20', null, 'TRACE', 'admin登录了系统。', 'admin', null, null, null);
INSERT INTO `security_log_entity` VALUES ('29', '2014-11-15 00:01:00', null, 'TRACE', 'admin登录了系统。', 'admin', null, null, null);
INSERT INTO `security_log_entity` VALUES ('30', '2014-11-15 00:09:12', null, 'TRACE', 'admin登录了系统。', 'admin', null, null, null);
INSERT INTO `security_log_entity` VALUES ('31', '2014-11-15 00:10:48', null, 'TRACE', 'admin登录了系统。', 'admin', null, null, null);
INSERT INTO `security_log_entity` VALUES ('32', '2014-11-15 00:12:39', null, 'TRACE', 'admin登录了系统。', 'admin', null, null, null);
INSERT INTO `security_log_entity` VALUES ('33', '2014-11-15 00:26:29', null, 'TRACE', 'admin登录了系统。', 'admin', null, null, null);
INSERT INTO `security_log_entity` VALUES ('34', '2014-11-15 00:33:39', null, 'TRACE', 'admin登录了系统。', 'admin', null, null, null);
INSERT INTO `security_log_entity` VALUES ('35', '2014-11-15 00:40:25', null, 'TRACE', 'admin登录了系统。', 'admin', null, null, null);

-- ----------------------------
-- Table structure for `security_module`
-- ----------------------------
DROP TABLE IF EXISTS `security_module`;
CREATE TABLE `security_module` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(32) NOT NULL,
  `priority` int(11) NOT NULL,
  `sn` varchar(32) NOT NULL,
  `url` varchar(255) NOT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `icon` varchar(255) DEFAULT '/desktop/icon/readGod.png',
  `parentId` bigint(20) DEFAULT NULL,
  `height` int(11) DEFAULT NULL,
  `ismax` int(11) DEFAULT NULL,
  `width` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6510844BB3395F9` (`parent_id`),
  KEY `FK6510844BF4FB1B54` (`parentId`),
  CONSTRAINT `FK6510844BB3395F9` FOREIGN KEY (`parent_id`) REFERENCES `security_module` (`id`),
  CONSTRAINT `FK6510844BF4FB1B54` FOREIGN KEY (`parentId`) REFERENCES `security_module` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of security_module
-- ----------------------------
INSERT INTO `security_module` VALUES ('1', '所有模块的根节点，不能删除。', '根模块', '1', 'Root', '#', null, '/desktop/icon/readGod.png', null, '0', '0', '0');
INSERT INTO `security_module` VALUES ('2', '安全管理:包含权限管理、模块管理等。', '安全管理', '99', 'Security', '/management/index/sysMgr', '1', '/desktop/icon/app/Board.png', null, '0', '0', '0');
INSERT INTO `security_module` VALUES ('3', '', '用户管理', '99', 'User', '/management/security/user/list', '2', '/desktop/icon/readGod.png', null, '0', '0', '0');
INSERT INTO `security_module` VALUES ('4', '', '角色管理', '99', 'Role', '/management/security/role/list', '2', '/desktop/icon/readGod.png', null, '0', '0', '0');
INSERT INTO `security_module` VALUES ('5', '', '模块管理', '99', 'Module', '/management/security/module/tree_list', '2', '/desktop/icon/readGod.png', null, '0', '0', '0');
INSERT INTO `security_module` VALUES ('18', '', '组织管理', '99', 'Organization', '/management/security/organization/tree_list', '2', '/desktop/icon/readGod.png', null, '0', '0', '0');
INSERT INTO `security_module` VALUES ('24', '', '缓存管理', '99', 'CacheManage', '/management/security/cacheManage/index', '2', '/desktop/icon/readGod.png', null, '0', '0', '0');
INSERT INTO `security_module` VALUES ('59', '', '日志管理', '99', 'logEntity', '/management/security/logEntity/list', '2', '/desktop/icon/readGod.png', null, '0', '0', '0');

-- ----------------------------
-- Table structure for `security_module_desktop`
-- ----------------------------
DROP TABLE IF EXISTS `security_module_desktop`;
CREATE TABLE `security_module_desktop` (
  `id` varchar(32) DEFAULT NULL,
  `module_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `moduleId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of security_module_desktop
-- ----------------------------

-- ----------------------------
-- Table structure for `security_organization`
-- ----------------------------
DROP TABLE IF EXISTS `security_organization`;
CREATE TABLE `security_organization` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(64) NOT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `parentId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1DBDA7D2FCC01B00` (`parent_id`),
  KEY `FK1DBDA7D2BD5F5BDB` (`parentId`),
  CONSTRAINT `FK1DBDA7D2BD5F5BDB` FOREIGN KEY (`parentId`) REFERENCES `security_organization` (`id`),
  CONSTRAINT `FK1DBDA7D2FCC01B00` FOREIGN KEY (`parent_id`) REFERENCES `security_organization` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of security_organization
-- ----------------------------
INSERT INTO `security_organization` VALUES ('1', '不能删除。', '根组织', null, null);

-- ----------------------------
-- Table structure for `security_organization_role`
-- ----------------------------
DROP TABLE IF EXISTS `security_organization_role`;
CREATE TABLE `security_organization_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `priority` int(11) NOT NULL,
  `organization_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  `organizationId` bigint(20) DEFAULT NULL,
  `roleId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK557CA4C3D3465025` (`organization_id`),
  KEY `FK557CA4C36156045` (`role_id`),
  KEY `FK557CA4C340124224` (`organizationId`),
  KEY `FK557CA4C33972B6EA` (`roleId`),
  CONSTRAINT `FK557CA4C33972B6EA` FOREIGN KEY (`roleId`) REFERENCES `security_role` (`id`),
  CONSTRAINT `FK557CA4C340124224` FOREIGN KEY (`organizationId`) REFERENCES `security_organization` (`id`),
  CONSTRAINT `FK557CA4C36156045` FOREIGN KEY (`role_id`) REFERENCES `security_role` (`id`),
  CONSTRAINT `FK557CA4C3D3465025` FOREIGN KEY (`organization_id`) REFERENCES `security_organization` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of security_organization_role
-- ----------------------------

-- ----------------------------
-- Table structure for `security_permission`
-- ----------------------------
DROP TABLE IF EXISTS `security_permission`;
CREATE TABLE `security_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(32) NOT NULL,
  `short_name` varchar(16) NOT NULL,
  `module_id` bigint(20) DEFAULT NULL,
  `shortName` varchar(16) NOT NULL DEFAULT '0',
  `moduleId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKBA7A9C2E334A08F7` (`module_id`),
  KEY `FKBA7A9C2E8AEB5096` (`moduleId`),
  CONSTRAINT `FKBA7A9C2E334A08F7` FOREIGN KEY (`module_id`) REFERENCES `security_module` (`id`),
  CONSTRAINT `FKBA7A9C2E8AEB5096` FOREIGN KEY (`moduleId`) REFERENCES `security_module` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of security_permission
-- ----------------------------

-- ----------------------------
-- Table structure for `security_role`
-- ----------------------------
DROP TABLE IF EXISTS `security_role`;
CREATE TABLE `security_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of security_role
-- ----------------------------

-- ----------------------------
-- Table structure for `security_role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `security_role_permission`;
CREATE TABLE `security_role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `permission_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  `permissionId` bigint(20) DEFAULT NULL,
  `roleId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK679E223926E70397` (`permission_id`),
  KEY `FK679E2239C592DFF7` (`role_id`),
  KEY `FK679E223960B5B91C` (`permissionId`),
  KEY `FK679E22393972B6EA` (`roleId`),
  CONSTRAINT `FK679E223926E70397` FOREIGN KEY (`permission_id`) REFERENCES `security_permission` (`id`),
  CONSTRAINT `FK679E22393972B6EA` FOREIGN KEY (`roleId`) REFERENCES `security_role` (`id`),
  CONSTRAINT `FK679E223960B5B91C` FOREIGN KEY (`permissionId`) REFERENCES `security_permission` (`id`),
  CONSTRAINT `FK679E2239C592DFF7` FOREIGN KEY (`role_id`) REFERENCES `security_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of security_role_permission
-- ----------------------------

-- ----------------------------
-- Table structure for `security_user`
-- ----------------------------
DROP TABLE IF EXISTS `security_user`;
CREATE TABLE `security_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `email` varchar(128) DEFAULT NULL,
  `password` varchar(64) NOT NULL,
  `phone` varchar(32) DEFAULT NULL,
  `realname` varchar(32) NOT NULL,
  `salt` varchar(32) NOT NULL,
  `status` varchar(16) NOT NULL,
  `username` varchar(32) NOT NULL,
  `org_id` bigint(20) DEFAULT NULL,
  `sdeptid` bigint(20) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `orgId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKD607B56A453A1286` (`org_id`),
  KEY `FKD607B56AF80AE8D1` (`sdeptid`),
  KEY `FKD607B56A7DA55B95` (`orgId`),
  CONSTRAINT `FKD607B56A453A1286` FOREIGN KEY (`org_id`) REFERENCES `security_organization` (`id`),
  CONSTRAINT `FKD607B56A7DA55B95` FOREIGN KEY (`orgId`) REFERENCES `security_organization` (`id`),
  CONSTRAINT `FKD607B56AF80AE8D1` FOREIGN KEY (`sdeptid`) REFERENCES `security_organization` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of security_user
-- ----------------------------
INSERT INTO `security_user` VALUES ('1', '2012-08-03 14:58:38', 'ketayao@gmail.com', '7a8f27edd04296d1a2f484cca71c6834a87356b6', '13518109993', '姚强', '9754469b0353a6a7', 'enabled', 'admin', '1', null, null, null);

-- ----------------------------
-- Table structure for `security_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `security_user_role`;
CREATE TABLE `security_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `priority` int(11) NOT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `roleId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6DD3562BC592DFF7` (`role_id`),
  KEY `FK6DD3562B6ABDA3D7` (`user_id`),
  KEY `FK6DD3562B3972B6EA` (`roleId`),
  KEY `FK6DD3562B3EC80C54` (`userId`),
  CONSTRAINT `FK6DD3562B3972B6EA` FOREIGN KEY (`roleId`) REFERENCES `security_role` (`id`),
  CONSTRAINT `FK6DD3562B3EC80C54` FOREIGN KEY (`userId`) REFERENCES `security_user` (`id`),
  CONSTRAINT `FK6DD3562B6ABDA3D7` FOREIGN KEY (`user_id`) REFERENCES `security_user` (`id`),
  CONSTRAINT `FK6DD3562BC592DFF7` FOREIGN KEY (`role_id`) REFERENCES `security_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of security_user_role
-- ----------------------------

-- ----------------------------
-- Table structure for `urgentperson`
-- ----------------------------
DROP TABLE IF EXISTS `urgentperson`;
CREATE TABLE `urgentperson` (
  `urgentperson_id` varchar(32) NOT NULL,
  `user_id` varchar(32) DEFAULT NULL,
  `urgentxm` varchar(100) DEFAULT NULL,
  `telephone` varchar(25) DEFAULT NULL,
  `address` varchar(300) DEFAULT NULL,
  `sffs` varchar(25) DEFAULT NULL,
  `bak1` varchar(100) DEFAULT NULL,
  `bak2` varchar(100) DEFAULT NULL,
  `bak3` varchar(100) DEFAULT NULL,
  `bak4` varchar(100) DEFAULT NULL,
  `bak5` timestamp NULL DEFAULT NULL,
  `bak6` timestamp NULL DEFAULT NULL,
  `bak7` double DEFAULT NULL,
  `bak8` double DEFAULT NULL,
  `gx` varchar(100) DEFAULT NULL,
  `dr` int(11) DEFAULT NULL,
  `ts` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`urgentperson_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of urgentperson
-- ----------------------------
INSERT INTO `urgentperson` VALUES ('402880ea49af106e0149af148ecf0005', '00000000000000000000000000000011', 'aaa', 'vvv', 'xxx', '1', null, null, null, '0', null, null, null, null, null, '0', '2014-11-15 00:13:20');
INSERT INTO `urgentperson` VALUES ('402880ea49af106e0149af14e8740006', '00000000000000000000000000000011', '11', '11', '11', null, null, null, null, '1', null, null, null, null, '11', '0', '2014-11-15 00:13:43');
INSERT INTO `urgentperson` VALUES ('402880ea49af106e0149af14e8790007', '00000000000000000000000000000011', '22', '22', '22', null, null, null, null, '1', null, null, null, null, '22', '0', '2014-11-15 00:13:43');
INSERT INTO `urgentperson` VALUES ('402880ea49af106e0149af14e8800008', '00000000000000000000000000000011', '33', '33', '33', null, null, null, null, '1', null, null, null, null, '33', '0', '2014-11-15 00:13:43');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` varchar(255) NOT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `regcode` varchar(255) DEFAULT NULL,
  `account` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `headpic` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `birthday` varchar(255) DEFAULT NULL,
  `height` varchar(255) DEFAULT NULL,
  `weight` varchar(255) DEFAULT NULL,
  `devicecode` varchar(255) DEFAULT NULL,
  `usertoken` varchar(255) DEFAULT NULL,
  `iostoken` varchar(255) DEFAULT NULL,
  `dr` int(11) DEFAULT NULL,
  `ts` datetime DEFAULT NULL,
  `bak1` varchar(255) DEFAULT NULL,
  `bak2` varchar(255) DEFAULT NULL,
  `bak3` varchar(255) DEFAULT NULL,
  `bak4` varchar(255) DEFAULT NULL,
  `bak5` datetime DEFAULT NULL,
  `bak6` datetime DEFAULT NULL,
  `bak7` double DEFAULT NULL,
  `bak8` double DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('00000000000000000000000000000000', '13523147658', 'hao123@126.com', null, 'admin', '123456', null, '总公司管理员', '男', '11', '22', '111', null, null, null, '0', '2014-10-11 00:00:00', null, null, null, '99', '2014-09-22 00:00:00', null, null, null, '');
INSERT INTO `user` VALUES ('00000000000000000000000000000011', '18761519489', 'zhangsan@126.com', 'FC141A8966', 'zhangsan', '123456', null, '张三', '男', '11-24', '168', '52', null, null, null, '0', '2014-10-11 00:00:00', null, null, null, '0', '2014-09-22 00:00:00', null, null, null, '');
INSERT INTO `user` VALUES ('00000000000000000000000000000012', '18761519489', 'xiao_hong@163.com', 'A4112BF32439', 'xiaohong', '123456', null, '晓红', '女', '4-09', '164', '46', null, null, null, '0', '2014-10-11 00:00:00', null, null, null, '0', '2014-09-22 00:00:00', null, null, null, '');
INSERT INTO `user` VALUES ('00000000000000000000000000000013', '18761519489', '125137253@qq.com', 'E214B74D92', 'wangming', '123456', null, '王明', '男', '7-29', '179', '75', null, null, null, '0', '2014-10-11 00:00:00', null, null, null, '0', '2014-09-22 00:00:00', null, null, null, '');
INSERT INTO `user` VALUES ('00000000000000000000000000000014', '18761519489', '56291699@qq.com', '23C623E0981', 'zhaohui', '123456', null, '赵辉', '男', '11-14', '183', '73', null, null, null, '0', '2014-10-11 00:00:00', null, null, null, '0', '2014-09-22 00:00:00', null, null, null, '');
INSERT INTO `user` VALUES ('00000000000000000000000000000015', '18761519489', '39127491@qq.com', '23C623E0981', 'leihao', '123456', null, '雷浩', '男', '1-27', '165', '64', null, null, null, '0', '2014-10-11 00:00:00', null, null, null, '0', '2014-09-22 00:00:00', null, null, null, '');
INSERT INTO `user` VALUES ('00000000000000000000000000000016', '18761519489', '39127491@qq.com', '23C623E0981', 'lizhong', '123456', null, '李忠', '男', '12-04', '174', '66', null, null, null, '0', '2014-10-11 00:00:00', null, null, null, '0', '2014-09-22 00:00:00', null, null, null, '');
INSERT INTO `user` VALUES ('00000000000000000000000000000017', '18761519489', '39127491@qq.com', '23C623E0981', 'liuxiong', '123456', null, '刘雄', '男', '6-23', '178', '81', null, null, null, '0', '2014-10-11 00:00:00', null, null, null, '0', '2014-09-22 00:00:00', null, null, null, '');
INSERT INTO `user` VALUES ('00000000000000000000000000000018', '18761519489', '39127491@qq.com', '23C623E0981', 'maliu', '123456', null, '马刘', '男', '4-12', '163', '64', null, null, null, '0', '2014-10-11 00:00:00', null, null, null, '0', '2014-09-22 00:00:00', null, null, null, '');
INSERT INTO `user` VALUES ('00000000000000000000000000000019', '18761519489', '39127491@qq.com', '23C623E0981', 'pengbin', '123456', null, '彭斌', '男', '5-5', '169', '57', null, null, null, '0', '2014-10-11 00:00:00', null, null, null, '0', '2014-09-22 00:00:00', null, null, null, '');
INSERT INTO `user` VALUES ('00000000000000000000000000000020', '18761519489', '39127491@qq.com', '23C623E0981', 'humin', '123456', null, '胡敏', '男', '8-20', '175', '62', null, null, null, '0', '2014-10-11 00:00:00', null, null, null, '0', '2014-09-22 00:00:00', null, null, null, '');
INSERT INTO `user` VALUES ('00000000000000000000000000000021', '18761519489', '39127491@qq.com', '23C623E0981', 'zhengyi', '123456', null, '郑毅', '男', '9-14', '189', '83', null, null, null, '0', '2014-10-11 00:00:00', null, null, null, '0', '2014-09-22 00:00:00', null, null, null, '');
INSERT INTO `user` VALUES ('00000000000000000000000000000022', '18761519489', '39127491@qq.com', '23C623E0981', 'jiangxin', '123456', null, '江欣', '女', '3-20', '157', '42', null, null, null, '0', '2014-10-11 00:00:00', null, null, null, '0', '2014-09-22 00:00:00', null, null, null, '');

-- ----------------------------
-- Table structure for `userdevice`
-- ----------------------------
DROP TABLE IF EXISTS `userdevice`;
CREATE TABLE `userdevice` (
  `id` varchar(32) NOT NULL,
  `device_id` varchar(32) DEFAULT NULL,
  `user_id` varchar(32) DEFAULT NULL,
  `bind_time` varchar(50) DEFAULT NULL,
  `is_admin` varchar(50) DEFAULT NULL,
  `bind_state` varchar(32) DEFAULT NULL,
  `_order` int(11) DEFAULT NULL,
  `bak1` varchar(50) DEFAULT NULL,
  `bak2` varchar(50) DEFAULT NULL,
  `bak3` varchar(50) DEFAULT NULL,
  `bak4` varchar(50) DEFAULT NULL,
  `bak5` timestamp NULL DEFAULT NULL,
  `bak6` timestamp NULL DEFAULT NULL,
  `bak7` double DEFAULT NULL,
  `bak8` double DEFAULT NULL,
  `dr` int(11) DEFAULT NULL,
  `ts` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userdevice
-- ----------------------------
INSERT INTO `userdevice` VALUES ('f9aa885b48e9c5210148ef6698e94zq7', 'f9aa885b48e9c5210148f9090bac068b', '00000000000000000000000000000018', '2014-10-16 11:10:15', '0', null, '1', null, null, null, null, null, '2014-11-11 10:32:14', null, null, '0', '2014-11-11 10:32:14');
INSERT INTO `userdevice` VALUES ('f9aa885b48e9c5210148ef6698e98hb4', 'f9aa885b48e9c5210148f904e93e04c6', '00000000000000000000000000000017', '2014-10-16 11:10:15', '0', null, '1', null, null, null, null, null, '2014-11-11 10:32:14', null, null, '0', '2014-11-11 10:32:14');
INSERT INTO `userdevice` VALUES ('f9aa885b48e9c5210148ef6698e9za10', 'f9aa885b48e9c5210148fdc5ebec0773', '00000000000000000000000000000020', '2014-10-16 11:10:15', '0', null, '1', null, null, null, null, null, '2014-11-11 10:32:14', null, null, '0', '2014-11-11 10:32:14');
INSERT INTO `userdevice` VALUES ('f9aa885b48e9c5210148ef6698ef1231', 'f9aa885b48e9c5210148ef6698ef2316', '00000000000000000000000000000012', '2014-10-16 11:10:15', '0', null, '1', null, null, null, null, null, '2014-11-11 10:32:14', null, null, '0', '2014-11-11 10:32:14');
INSERT INTO `userdevice` VALUES ('f9aa885b48e9c5210148ef6698ef5r64', 'f9aa885b48e9c5210148fddb0b9f0808', '00000000000000000000000000000021', '2014-10-16 11:10:15', '0', null, '1', null, null, null, null, null, '2014-11-11 10:32:14', null, null, '0', '2014-11-11 10:32:14');
INSERT INTO `userdevice` VALUES ('f9aa885b48e9c5210148ef6698ef5rqq', 'f9aa885b48e9c52101491dda9fbd0831', '00000000000000000000000000000022', '2014-10-16 11:10:15', '0', null, '1', null, null, null, null, null, '2014-11-11 10:32:14', null, null, '0', '2014-11-11 10:32:14');
INSERT INTO `userdevice` VALUES ('f9aa885b48e9c5210148ef6698ef982s', 'f9aa885b48e9c5210148ef6698ef9251', '00000000000000000000000000000013', '2014-10-16 11:10:15', '0', null, '1', null, null, null, null, null, '2014-11-11 10:32:14', null, null, '0', '2014-11-11 10:32:14');
INSERT INTO `userdevice` VALUES ('f9aa885b48e9c5210148ef6698efas87', 'f9aa885b48e9c5210148ef6698efs306', '00000000000000000000000000000014', '2014-10-16 11:10:15', '0', null, '1', null, null, null, null, null, '2014-11-11 10:32:14', null, null, '0', '2014-11-11 10:32:14');
INSERT INTO `userdevice` VALUES ('f9aa885b48e9c5210148ef6698efaswx', 'f9aa885b48e9c5210148ef6698ef0008', '00000000000000000000000000000011', '2014-10-16 11:10:15', '0', null, '1', null, null, null, null, null, '2014-11-11 10:32:14', null, null, '0', '2014-11-11 10:32:14');
INSERT INTO `userdevice` VALUES ('f9aa885b48e9c5210148ef6698efcc32', 'f9aa885b48e9c5210148f90bb479070c', '00000000000000000000000000000019', '2014-10-16 11:10:15', '0', null, '1', null, null, null, null, null, '2014-11-11 10:32:14', null, null, '0', '2014-11-11 10:32:14');
INSERT INTO `userdevice` VALUES ('f9aa885b48e9c5210148ef6698efi75v', 'f9aa885b48e9c5210148f8cf2357008a', '00000000000000000000000000000016', '2014-10-16 11:10:15', '0', null, '1', null, null, null, null, null, '2014-11-11 10:32:14', null, null, '0', '2014-11-11 10:32:14');
INSERT INTO `userdevice` VALUES ('f9aa885b48e9c5210148ef6698efuw5b', 'f9aa885b48e9c5210148f7fac1230020', '00000000000000000000000000000015', '2014-10-16 11:10:15', '0', null, '1', null, null, null, null, null, '2014-11-11 10:32:14', null, null, '0', '2014-11-11 10:32:14');
