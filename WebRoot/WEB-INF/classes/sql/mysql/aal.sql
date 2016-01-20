/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.6.11 : Database - aal
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`aal` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `aal`;

/*Table structure for table `adspublish` */

DROP TABLE IF EXISTS `adspublish`;

CREATE TABLE `adspublish` (
  `adspublishId` varchar(32) NOT NULL,
  `imageadsId` varchar(32) DEFAULT NULL,
  `receiveId` varchar(32) DEFAULT NULL,
  `publishUser` varchar(50) DEFAULT NULL,
  `publishOrg` varchar(50) DEFAULT NULL,
  `publishTime` datetime DEFAULT NULL,
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
  PRIMARY KEY (`adspublishId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `adspublish` */

/*Table structure for table `aolchild_relation` */

DROP TABLE IF EXISTS `aolchild_relation`;

CREATE TABLE `aolchild_relation` (
  `ID` varchar(32) NOT NULL,
  `AOL_USER_ID` varchar(32) DEFAULT '',
  `CHILD_ID` varchar(32) DEFAULT '',
  `FNOTE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `AOL_USER_ID` (`AOL_USER_ID`),
  KEY `CHILD_ID` (`CHILD_ID`),
  CONSTRAINT `aolchild_relation_ibfk_1` FOREIGN KEY (`AOL_USER_ID`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `aolchild_relation` */

/*Table structure for table `apppush_list` */

DROP TABLE IF EXISTS `apppush_list`;

CREATE TABLE `apppush_list` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `userid` varchar(32) DEFAULT NULL,
  `title` varchar(200) DEFAULT NULL,
  `text` text,
  `ts` datetime DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `sendtime` datetime DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `senduserid` varchar(50) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `apppush_list` */

/*Table structure for table `attach` */

DROP TABLE IF EXISTS `attach`;

CREATE TABLE `attach` (
  `attachId` char(32) NOT NULL,
  `attachName` varchar(200) DEFAULT NULL,
  `attachTruename` varchar(200) DEFAULT NULL,
  `attachPath` varchar(200) DEFAULT NULL,
  `attachSize` decimal(18,4) DEFAULT NULL,
  `attachUser` char(32) DEFAULT NULL,
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
  PRIMARY KEY (`attachId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `attach` */

/*Table structure for table `childrenuser` */

DROP TABLE IF EXISTS `childrenuser`;

CREATE TABLE `childrenuser` (
  `id` varchar(255) NOT NULL,
  `nickname` varchar(50) DEFAULT NULL COMMENT '昵称',
  `gender` varchar(11) DEFAULT NULL COMMENT '性别 0：不限 1：男 2：女',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机号',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `childrenuser` */

/*Table structure for table `device` */

DROP TABLE IF EXISTS `device`;

CREATE TABLE `device` (
  `device_id` varchar(32) NOT NULL,
  `user_id` varchar(32) DEFAULT NULL,
  `deviceProYear` varchar(50) DEFAULT NULL,
  `deviceProMonth` varchar(50) DEFAULT NULL,
  `deviceType` varchar(50) DEFAULT NULL,
  `deviceSerial` varchar(50) DEFAULT NULL,
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
  `ts` datetime DEFAULT NULL,
  PRIMARY KEY (`device_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `device` */

/*Table structure for table `device_coordinate` */

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

/*Data for the table `device_coordinate` */

/*Table structure for table `feedback` */

DROP TABLE IF EXISTS `feedback`;

CREATE TABLE `feedback` (
  `ID` varchar(32) NOT NULL,
  `TITLE` varchar(100) DEFAULT NULL,
  `CONTENT` varchar(2000) DEFAULT NULL,
  `CREATE_USER` varchar(50) DEFAULT NULL,
  `CREATE_DATA` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `feedback` */

/*Table structure for table `findknowledge` */

DROP TABLE IF EXISTS `findknowledge`;

CREATE TABLE `findknowledge` (
  `find_id` varchar(32) NOT NULL DEFAULT '',
  `user_id` varchar(50) DEFAULT NULL,
  `title` varchar(500) DEFAULT NULL,
  `title_img` varchar(500) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `content` longtext,
  `create_time` datetime DEFAULT NULL,
  `state` varchar(10) DEFAULT NULL,
  `bak1` varchar(2000) DEFAULT NULL,
  `bak2` varchar(50) DEFAULT NULL,
  `bak3` varchar(50) DEFAULT NULL,
  `bak4` varchar(50) DEFAULT NULL,
  `bak5` timestamp NULL DEFAULT NULL,
  `bak6` timestamp NULL DEFAULT NULL,
  `bak7` double DEFAULT NULL,
  `bak8` double DEFAULT NULL,
  PRIMARY KEY (`find_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `findknowledge` */

/*Table structure for table `imageads` */

DROP TABLE IF EXISTS `imageads`;

CREATE TABLE `imageads` (
  `imageadsId` varchar(32) NOT NULL,
  `title` varchar(200) DEFAULT NULL,
  `adsDesc` varchar(500) DEFAULT NULL,
  `adsType` varchar(10) DEFAULT NULL,
  `imageUrl` varchar(100) DEFAULT NULL,
  `adsLink` varchar(100) DEFAULT NULL,
  `adsState` varchar(10) DEFAULT NULL,
  `createUser` varchar(50) DEFAULT NULL,
  `createOrg` varchar(50) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
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
  PRIMARY KEY (`imageadsId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `imageads` */

/*Table structure for table `loginfo` */

DROP TABLE IF EXISTS `loginfo`;

CREATE TABLE `loginfo` (
  `loginfo_id` varchar(32) NOT NULL,
  `logcontent` varchar(200) DEFAULT NULL,
  `username` varchar(100) DEFAULT NULL,
  `ip_address` varchar(50) DEFAULT NULL,
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
  PRIMARY KEY (`loginfo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `loginfo` */

insert  into `loginfo`(`loginfo_id`,`logcontent`,`username`,`ip_address`,`logtime`,`dr`,`ts`,`bak1`,`bak2`,`bak3`,`bak4`,`bak5`,`bak6`,`bak7`,`bak8`) values ('4028807c523fc5a301523fc61cc00000','总公司管理员登录了系统。','总公司管理员','0:0:0:0:0:0:0:1','2016-01-14 18:54:53',0,'2016-01-14 18:54:53',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('402883b551c84ed50151c85005e70000','null登录了系统。',NULL,'0:0:0:0:0:0:0:1','2015-12-22 14:11:06',0,'2015-12-22 14:11:06',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `logs` */

DROP TABLE IF EXISTS `logs`;

CREATE TABLE `logs` (
  `logs_id` varchar(32) NOT NULL,
  `device_id` varchar(32) DEFAULT NULL,
  `operType` varchar(50) DEFAULT NULL,
  `operTime` varchar(50) DEFAULT NULL,
  `operRemark` varchar(50) DEFAULT NULL,
  `bak1` varchar(50) DEFAULT NULL,
  `bak2` varchar(50) DEFAULT NULL,
  `bak3` varchar(50) DEFAULT NULL,
  `bak4` varchar(50) DEFAULT NULL,
  `bak5` timestamp NULL DEFAULT NULL,
  `bak6` timestamp NULL DEFAULT NULL,
  `bak7` double DEFAULT NULL,
  `bak8` double DEFAULT NULL,
  PRIMARY KEY (`logs_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `logs` */

/*Table structure for table `measure` */

DROP TABLE IF EXISTS `measure`;

CREATE TABLE `measure` (
  `measure_id` varchar(32) NOT NULL,
  `user_id` varchar(32) DEFAULT NULL,
  `device_id` varchar(32) DEFAULT NULL,
  `measureState` varchar(50) DEFAULT NULL,
  `measureType` varchar(50) DEFAULT NULL,
  `result1` varchar(50) DEFAULT NULL,
  `result2` varchar(50) DEFAULT NULL,
  `result3` varchar(50) DEFAULT NULL,
  `result4` varchar(50) DEFAULT NULL,
  `result5` varchar(50) DEFAULT NULL,
  `sendMsg` varchar(50) DEFAULT NULL,
  `sendTime` varchar(50) DEFAULT NULL,
  `bak1` varchar(50) DEFAULT NULL,
  `bak2` varchar(50) DEFAULT NULL,
  `bak3` varchar(50) DEFAULT NULL,
  `bak4` varchar(50) DEFAULT NULL,
  `bak5` timestamp NULL DEFAULT NULL,
  `bak6` timestamp NULL DEFAULT NULL,
  `bak7` double DEFAULT NULL,
  `bak8` double DEFAULT NULL,
  `dr` int(11) DEFAULT NULL,
  `ts` datetime DEFAULT NULL,
  PRIMARY KEY (`measure_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `measure` */

/*Table structure for table `messages` */

DROP TABLE IF EXISTS `messages`;

CREATE TABLE `messages` (
  `messagesId` varchar(32) NOT NULL,
  `messagesTitle` varchar(200) DEFAULT NULL,
  `messagesContent` longtext,
  `messagesTime` datetime DEFAULT NULL,
  `messagesState` int(11) DEFAULT NULL,
  `messagesOwn` varchar(20) DEFAULT NULL,
  `messagesSender` varchar(32) DEFAULT NULL,
  `messagesSendee` text,
  `sendeeType` int(11) DEFAULT NULL,
  `messagesSenderName` varchar(200) DEFAULT NULL,
  `messagesSendeeName` text,
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
  PRIMARY KEY (`messagesId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `messages` */

/*Table structure for table `organise` */

DROP TABLE IF EXISTS `organise`;

CREATE TABLE `organise` (
  `organiseId` varchar(32) NOT NULL,
  `organiseCode` varchar(50) DEFAULT NULL,
  `organiseName` varchar(100) DEFAULT NULL,
  `organiseShortname` varchar(50) DEFAULT NULL,
  `telephone` varchar(100) DEFAULT NULL,
  `fax` varchar(100) DEFAULT NULL,
  `organiseAddress` varchar(200) DEFAULT NULL,
  `storeAddress` varchar(200) DEFAULT NULL,
  `website` varchar(100) DEFAULT NULL,
  `mailbox` varchar(100) DEFAULT NULL,
  `zipcode` varchar(20) DEFAULT NULL,
  `parentId` varchar(32) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `levelType` int(11) DEFAULT NULL,
  `attachId` varchar(32) DEFAULT NULL,
  `userId` varchar(32) DEFAULT NULL,
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
  PRIMARY KEY (`organiseId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `organise` */

/*Table structure for table `organisedevice` */

DROP TABLE IF EXISTS `organisedevice`;

CREATE TABLE `organisedevice` (
  `organisedeviceId` varchar(32) NOT NULL,
  `deviceSerial` varchar(200) DEFAULT NULL,
  `organiseId` varchar(32) DEFAULT NULL,
  `batchNumber` varchar(50) DEFAULT NULL,
  `orderIndex` int(11) DEFAULT NULL,
  `bindTime` timestamp NULL DEFAULT NULL,
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
  PRIMARY KEY (`organisedeviceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `organisedevice` */

/*Table structure for table `security_log_entity` */

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
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

/*Data for the table `security_log_entity` */

insert  into `security_log_entity`(`id`,`create_time`,`ip_address`,`log_level`,`message`,`username`,`createTime`,`ipAddress`,`logLevel`) values (1,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-22 14:11:06',NULL,'TRACE'),(2,NULL,NULL,NULL,'{0}登录了系统。','admin','2015-12-22 14:12:21',NULL,'TRACE'),(3,NULL,NULL,NULL,'{0}登录了系统。','admin','2015-12-22 14:12:38',NULL,'TRACE'),(4,NULL,NULL,NULL,'{0}登录了系统。','admin','2015-12-22 14:15:11',NULL,'TRACE'),(5,NULL,NULL,NULL,'{0}登录了系统。','admin','2015-12-22 14:15:43',NULL,'TRACE'),(6,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-22 14:36:22',NULL,'TRACE'),(7,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-22 14:36:34',NULL,'TRACE'),(8,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-22 14:36:35',NULL,'TRACE'),(9,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-22 14:36:36',NULL,'TRACE'),(10,NULL,NULL,NULL,'{0}登录了系统。','admin','2015-12-30 10:52:37',NULL,'TRACE'),(11,NULL,NULL,NULL,'{0}登录了系统。','admin','2015-12-30 10:53:00',NULL,'TRACE'),(12,NULL,NULL,NULL,'{0}登录了系统。','admin','2016-01-14 18:18:57',NULL,'TRACE'),(13,NULL,NULL,NULL,'{0}登录了系统。','admin','2016-01-14 18:19:08',NULL,'TRACE'),(14,NULL,NULL,NULL,'{0}登录了系统。','admin','2016-01-14 18:25:15',NULL,'TRACE'),(15,NULL,NULL,NULL,'{0}登录了系统。','admin','2016-01-14 18:25:36',NULL,'TRACE'),(16,NULL,NULL,NULL,'{0}登录了系统。','admin','2016-01-14 18:29:20',NULL,'TRACE'),(17,NULL,NULL,NULL,'{0}登录了系统。','admin','2016-01-14 18:32:04',NULL,'TRACE'),(18,NULL,NULL,NULL,'{0}登录了系统。','admin','2016-01-14 18:32:12',NULL,'TRACE'),(19,NULL,NULL,NULL,'{0}登录了系统。','admin','2016-01-14 18:39:44',NULL,'TRACE'),(20,NULL,NULL,NULL,'{0}登录了系统。','admin','2016-01-14 18:41:23',NULL,'TRACE'),(21,NULL,NULL,NULL,'{0}登录了系统。','admin','2016-01-14 18:41:38',NULL,'TRACE'),(22,NULL,NULL,NULL,'{0}登录了系统。','admin','2016-01-14 18:43:53',NULL,'TRACE'),(23,NULL,NULL,NULL,'{0}登录了系统。','admin','2016-01-14 18:45:46',NULL,'TRACE'),(24,NULL,NULL,NULL,'{0}登录了系统。','admin','2016-01-14 18:48:10',NULL,'TRACE'),(25,NULL,NULL,NULL,'{0}登录了系统。','admin','2016-01-14 18:49:05',NULL,'TRACE'),(26,NULL,NULL,NULL,'{0}登录了系统。','admin','2016-01-14 18:52:23',NULL,'TRACE'),(27,NULL,NULL,NULL,'admin登录了系统。','admin','2016-01-14 18:55:01',NULL,'TRACE');

/*Table structure for table `security_module` */

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `security_module` */

/*Table structure for table `security_module_desktop` */

DROP TABLE IF EXISTS `security_module_desktop`;

CREATE TABLE `security_module_desktop` (
  `id` varchar(255) NOT NULL,
  `module_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `security_module_desktop` */

/*Table structure for table `security_organization` */

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `security_organization` */

/*Table structure for table `security_organization_role` */

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

/*Data for the table `security_organization_role` */

/*Table structure for table `security_permission` */

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

/*Data for the table `security_permission` */

/*Table structure for table `security_role` */

DROP TABLE IF EXISTS `security_role`;

CREATE TABLE `security_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `security_role` */

/*Table structure for table `security_role_permission` */

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

/*Data for the table `security_role_permission` */

/*Table structure for table `security_user` */

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `security_user` */

/*Table structure for table `security_user_role` */

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

/*Data for the table `security_user_role` */

/*Table structure for table `sms_list` */

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

/*Data for the table `sms_list` */

/*Table structure for table `syscontent` */

DROP TABLE IF EXISTS `syscontent`;

CREATE TABLE `syscontent` (
  `id` varchar(50) NOT NULL DEFAULT '',
  `ctype` varchar(10) DEFAULT NULL,
  `content` text,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `syscontent` */

/*Table structure for table `systype` */

DROP TABLE IF EXISTS `systype`;

CREATE TABLE `systype` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `type_name` varchar(50) DEFAULT NULL,
  `type_code` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `bak1` varchar(50) DEFAULT NULL,
  `bak2` varchar(50) DEFAULT NULL,
  `bak3` varchar(50) DEFAULT NULL,
  `bak4` varchar(50) DEFAULT NULL,
  `bak5` timestamp NULL DEFAULT NULL,
  `bak6` timestamp NULL DEFAULT NULL,
  `bak7` double DEFAULT NULL,
  `bak8` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `systype` */

/*Table structure for table `sysversion` */

DROP TABLE IF EXISTS `sysversion`;

CREATE TABLE `sysversion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` varchar(50) DEFAULT NULL,
  `versiondesc` text,
  `downloadurl` varchar(500) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `type` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sysversion` */

/*Table structure for table `t_detection_a` */

DROP TABLE IF EXISTS `t_detection_a`;

CREATE TABLE `t_detection_a` (
  `ID` varchar(32) NOT NULL,
  `DETECTION_T_Id` varchar(32) DEFAULT NULL,
  `DETECTION_Q_Id` varchar(32) DEFAULT NULL,
  `ANSWER` varchar(10) DEFAULT NULL,
  `USER_ID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_detection_a` */

/*Table structure for table `t_detection_q` */

DROP TABLE IF EXISTS `t_detection_q`;

CREATE TABLE `t_detection_q` (
  `ID` varchar(32) NOT NULL,
  `QNO` int(32) DEFAULT NULL,
  `TITLE` varchar(100) DEFAULT NULL,
  `T_ID` varchar(32) DEFAULT NULL,
  `A` varchar(50) DEFAULT NULL,
  `B` varchar(50) DEFAULT NULL,
  `C` varchar(50) DEFAULT NULL,
  `D` varchar(50) DEFAULT NULL,
  `ANSWER` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_detection_q` */

/*Table structure for table `t_detection_t` */

DROP TABLE IF EXISTS `t_detection_t`;

CREATE TABLE `t_detection_t` (
  `ID` varchar(32) NOT NULL,
  `TITLE` varchar(100) DEFAULT NULL,
  `IMAGE` varchar(255) DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  `CREATE_USER` varchar(50) DEFAULT NULL,
  `STATUS` int(2) DEFAULT NULL COMMENT '状态 0:题库没有题目，1:题库已有保存的题目列表',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_detection_t` */

/*Table structure for table `t_doctor` */

DROP TABLE IF EXISTS `t_doctor`;

CREATE TABLE `t_doctor` (
  `ID` varchar(32) NOT NULL,
  `NAME` varchar(50) NOT NULL COMMENT '医生名字',
  `DETAIL` varchar(2000) DEFAULT NULL COMMENT '医生简介',
  `DEPART` varchar(100) DEFAULT NULL COMMENT '所在科室',
  `DOMAIN` varchar(100) DEFAULT NULL COMMENT '所在领域',
  `MOBILE` varchar(15) DEFAULT NULL COMMENT '手机号',
  `TEL` varchar(25) DEFAULT NULL COMMENT '座机电话',
  `HEAD` varchar(225) DEFAULT NULL COMMENT '头像地址',
  `GENDER` tinyint(1) DEFAULT NULL COMMENT '性别1:男 2:女',
  `AGE` int(10) DEFAULT NULL COMMENT '年龄',
  `LEVEL` varchar(50) DEFAULT NULL COMMENT '医师等级',
  `BIRTHDAY` datetime DEFAULT NULL COMMENT '出生日期',
  `ONLINE` varchar(255) DEFAULT NULL COMMENT '在诊时间',
  `TYPE` tinyint(1) DEFAULT NULL COMMENT '类型 0:专家 1:家庭医生',
  `REWARD` varchar(2000) DEFAULT NULL COMMENT '获得奖励',
  `HOSPITAL` varchar(2000) DEFAULT NULL COMMENT '所在医院',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_doctor` */

/*Table structure for table `t_pathology` */

DROP TABLE IF EXISTS `t_pathology`;

CREATE TABLE `t_pathology` (
  `ID` varchar(32) NOT NULL,
  `TITLE` varchar(100) DEFAULT NULL,
  `CONTENT` varchar(2000) DEFAULT NULL,
  `IMAGE` varchar(255) DEFAULT NULL,
  `URL` varchar(255) DEFAULT NULL COMMENT '讲座链接地址',
  `TYPE` int(2) DEFAULT NULL COMMENT '类型 0:病理 1:讲座',
  `CREATE_DATE` datetime DEFAULT NULL,
  `CREATE_USER` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_pathology` */

/*Table structure for table `t_relatives` */

DROP TABLE IF EXISTS `t_relatives`;

CREATE TABLE `t_relatives` (
  `ID` varchar(32) NOT NULL,
  `AOL_USER_ID` varchar(32) DEFAULT NULL,
  `REL_USER_ID` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_relatives` */

/*Table structure for table `urgentperson` */

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

/*Data for the table `urgentperson` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` varchar(32) NOT NULL,
  `mobile` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `account` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `headPic` varchar(255) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `sex` varchar(50) DEFAULT NULL,
  `birthday` varchar(50) DEFAULT NULL,
  `height` varchar(50) DEFAULT NULL,
  `weight` varchar(50) DEFAULT NULL,
  `deviceCode` varchar(50) DEFAULT NULL,
  `userToken` varchar(50) DEFAULT NULL,
  `iosToken` varchar(50) DEFAULT NULL,
  `bak1` varchar(50) DEFAULT NULL,
  `bak2` varchar(50) DEFAULT NULL,
  `bak3` varchar(50) DEFAULT NULL,
  `bak4` varchar(50) DEFAULT NULL,
  `bak5` timestamp NULL DEFAULT NULL,
  `bak6` timestamp NULL DEFAULT NULL,
  `bak7` double DEFAULT NULL,
  `bak8` double DEFAULT NULL,
  `regcode` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `dr` int(11) DEFAULT NULL,
  `ts` datetime DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `maritalStatus` varchar(50) DEFAULT NULL,
  `isHighOrSugar` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`user_id`,`mobile`,`email`,`account`,`password`,`headPic`,`name`,`sex`,`birthday`,`height`,`weight`,`deviceCode`,`userToken`,`iosToken`,`bak1`,`bak2`,`bak3`,`bak4`,`bak5`,`bak6`,`bak7`,`bak8`,`regcode`,`remark`,`dr`,`ts`,`city`,`maritalStatus`,`isHighOrSugar`) values ('00000000000000000000000000000000','13523147658','hao123@126.com','admin','123456','http://img.51daifu.com/elder/20101213/elder_20101213_lwj_1.jpg','总公司管理员',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'99','2014-09-22 00:00:00',NULL,NULL,NULL,NULL,'',0,NULL,NULL,NULL,NULL);

/*Table structure for table `userauthorize` */

DROP TABLE IF EXISTS `userauthorize`;

CREATE TABLE `userauthorize` (
  `id` varchar(32) NOT NULL,
  `user_id` varchar(32) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `authorize_user_id` varchar(32) DEFAULT NULL,
  `authName` varchar(50) DEFAULT NULL,
  `authEmail` varchar(50) DEFAULT NULL,
  `authTime` varchar(50) DEFAULT NULL,
  `authState` varchar(50) DEFAULT NULL,
  `bak1` varchar(50) DEFAULT NULL,
  `bak2` varchar(50) DEFAULT NULL,
  `bak3` varchar(50) DEFAULT NULL,
  `bak4` varchar(50) DEFAULT NULL,
  `bak5` timestamp NULL DEFAULT NULL,
  `bak6` timestamp NULL DEFAULT NULL,
  `bak7` double DEFAULT NULL,
  `bak8` double DEFAULT NULL,
  `mobile` varchar(50) DEFAULT NULL,
  `authMobile` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `userauthorize` */

/*Table structure for table `userdevice` */

DROP TABLE IF EXISTS `userdevice`;

CREATE TABLE `userdevice` (
  `id` varchar(32) NOT NULL,
  `device_id` varchar(32) DEFAULT NULL,
  `user_id` varchar(32) DEFAULT NULL,
  `bindTime` varchar(50) DEFAULT NULL,
  `isAdmin` varchar(50) DEFAULT NULL,
  `bindState` varchar(32) DEFAULT NULL,
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
  `ts` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `userdevice` */

/*Table structure for table `userfind` */

DROP TABLE IF EXISTS `userfind`;

CREATE TABLE `userfind` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `find_id` varchar(32) DEFAULT NULL,
  `bindTime` varchar(50) DEFAULT NULL,
  `user_id` varchar(32) DEFAULT NULL,
  `bak1` varchar(50) DEFAULT NULL,
  `bak2` varchar(50) DEFAULT NULL,
  `bak3` varchar(50) DEFAULT NULL,
  `bak4` varchar(50) DEFAULT NULL,
  `bak5` timestamp NULL DEFAULT NULL,
  `bak6` timestamp NULL DEFAULT NULL,
  `bak7` double DEFAULT NULL,
  `bak8` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `userfind` */

/*Table structure for table `validatecode` */

DROP TABLE IF EXISTS `validatecode`;

CREATE TABLE `validatecode` (
  `code_id` varchar(32) NOT NULL,
  `type` varchar(50) DEFAULT NULL,
  `userMark` varchar(50) DEFAULT NULL,
  `code` varchar(32) DEFAULT NULL,
  `sendTime` varchar(50) DEFAULT NULL,
  `expiredTime` varchar(50) DEFAULT NULL,
  `bak1` varchar(50) DEFAULT NULL,
  `bak2` varchar(50) DEFAULT NULL,
  `bak3` varchar(50) DEFAULT NULL,
  `bak4` varchar(50) DEFAULT NULL,
  `bak5` timestamp NULL DEFAULT NULL,
  `bak6` timestamp NULL DEFAULT NULL,
  `bak7` double DEFAULT NULL,
  `bak8` double DEFAULT NULL,
  PRIMARY KEY (`code_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `validatecode` */

/*Table structure for table `weatherinfo` */

DROP TABLE IF EXISTS `weatherinfo`;

CREATE TABLE `weatherinfo` (
  `weatherinfoId` char(32) NOT NULL,
  `cityCode` varchar(50) DEFAULT NULL,
  `dateTime` varchar(50) DEFAULT NULL,
  `weatherInfo` text,
  `dataState` int(11) DEFAULT NULL,
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
  PRIMARY KEY (`weatherinfoId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `weatherinfo` */

insert  into `weatherinfo`(`weatherinfoId`,`cityCode`,`dateTime`,`weatherInfo`,`dataState`,`bak1`,`bak2`,`bak3`,`bak4`,`bak5`,`bak6`,`bak7`,`bak8`,`dr`,`ts`) values ('402883b551c84ed50151c8500e7c0001','101200101','2015-12-22','<?xml version=\"1.0\" encoding=\"utf-8\" ?> <CityWeatherResponse>		  <error>0</error>	    <status>success</status>		    <date>2015-12-22</date>	    <results>              <currentCity>武汉</currentCity>        <weather_data>                            <date>周二 12月22日 (实时：7℃)</date>                <dayPictureUrl>http://api.map.baidu.com/images/weather/day/yin.png</dayPictureUrl>                <nightPictureUrl>http://api.map.baidu.com/images/weather/night/xiaoyu.png</nightPictureUrl>                <weather>阴转小雨</weather>                <wind>微风</wind>                <temperature>9 ~ 4℃</temperature>                            <date>周三</date>                <dayPictureUrl>http://api.map.baidu.com/images/weather/day/xiaoyu.png</dayPictureUrl>                <nightPictureUrl>http://api.map.baidu.com/images/weather/night/xiaoyu.png</nightPictureUrl>                <weather>小雨</weather>                <wind>微风</wind>                <temperature>8 ~ 4℃</temperature>                            <date>周四</date>                <dayPictureUrl>http://api.map.baidu.com/images/weather/day/xiaoyu.png</dayPictureUrl>                <nightPictureUrl>http://api.map.baidu.com/images/weather/night/duoyun.png</nightPictureUrl>                <weather>小雨转多云</weather>                <wind>微风</wind>                <temperature>7 ~ 2℃</temperature>                            <date>周五</date>                <dayPictureUrl>http://api.map.baidu.com/images/weather/day/duoyun.png</dayPictureUrl>                <nightPictureUrl>http://api.map.baidu.com/images/weather/night/duoyun.png</nightPictureUrl>                <weather>多云</weather>                <wind>微风</wind>                <temperature>11 ~ -1℃</temperature>                    </weather_data>        <index>                            <title>穿衣</title>                <zs>较冷</zs>                <tipt>穿衣指数</tipt>                <des>建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。</des>                            <title>洗车</title>                <zs>不宜</zs>                <tipt>洗车指数</tipt>                <des>不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。</des>                            <title>旅游</title>                <zs>适宜</zs>                <tipt>旅游指数</tipt>                <des>天气较好，气温稍低，会感觉稍微有点凉，不过也是个好天气哦。适宜旅游，可不要错过机会呦！</des>                            <title>感冒</title>                <zs>较易发</zs>                <tipt>感冒指数</tipt>                <des>天气较凉，较易发生感冒，请适当增加衣服。体质较弱的朋友尤其应该注意防护。</des>                            <title>运动</title>                <zs>较不宜</zs>                <tipt>运动指数</tipt>                <des>阴天，且天气寒冷，推荐您在室内进行低强度运动；若坚持户外运动，请选择合适的运动并注意保暖。</des>                            <title>紫外线强度</title>                <zs>最弱</zs>                <tipt>紫外线强度指数</tipt>                <des>属弱紫外线辐射天气，无需特别防护。若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。</des>                    </index>        <pm25>146</pm25>        </results></CityWeatherResponse>',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'2015-12-22 14:11:08');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
