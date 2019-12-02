-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: sneaklifedb
-- ------------------------------------------------------
-- Server version	5.7.21-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `columns`
--

DROP TABLE IF EXISTS `columns`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `columns` (
  `id` char(32) NOT NULL COMMENT 'ID,32位字符串',
  `field` varchar(50) DEFAULT NULL COMMENT '字段名',
  `title` varchar(50) DEFAULT NULL COMMENT '标题',
  `is_del` int(1) NOT NULL DEFAULT '0' COMMENT '0：未删除 | 1：已删除',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `align` varchar(50) NOT NULL COMMENT '对齐方式',
  `menu_id` char(32) NOT NULL COMMENT 'system_menu中的id',
  `is_show` int(1) NOT NULL DEFAULT '1' COMMENT '0：显示 | 1：不显示',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='配置页面需要展示的字段';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `columns`
--

LOCK TABLES `columns` WRITE;
/*!40000 ALTER TABLE `columns` DISABLE KEYS */;
INSERT INTO `columns` VALUES ('0157b9bdc62111e9bd4f80fa5b3a283a','id','键值',0,'2019-08-24 11:40:58','2019-08-24 11:40:58','center','a6d31fd1c25211e98f5680fa5b3a283a',1),('0170d8e2c62111e9bd4f80fa5b3a283a','createDate','创建时间',0,'2019-08-24 11:40:58','2019-08-24 11:40:58','center','a6d31fd1c25211e98f5680fa5b3a283a',1),('017a18fac62111e9bd4f80fa5b3a283a','isDel','是否删除',0,'2019-08-24 11:40:58','2019-08-24 11:40:58','center','a6d31fd1c25211e98f5680fa5b3a283a',1),('01849d65c62111e9bd4f80fa5b3a283a','textName','文本名称',0,'2019-08-24 11:40:58','2019-08-24 11:40:58','center','a6d31fd1c25211e98f5680fa5b3a283a',0),('0195711bc62111e9bd4f80fa5b3a283a','updateDate','更新时间',0,'2019-08-24 11:40:58','2019-08-24 11:40:58','center','a6d31fd1c25211e98f5680fa5b3a283a',1),('019f5cb0c62111e9bd4f80fa5b3a283a','htmlTypeName','HTML类型',0,'2019-08-24 11:40:58','2019-08-24 11:40:58','center','a6d31fd1c25211e98f5680fa5b3a283a',0),('01ac801fc62111e9bd4f80fa5b3a283a','field','匹配关键字',0,'2019-08-24 11:40:58','2019-08-24 11:40:58','center','a6d31fd1c25211e98f5680fa5b3a283a',0),('01b66134c62111e9bd4f80fa5b3a283a','isShow','是否显示',0,'2019-08-24 11:40:58','2019-08-24 11:40:58','center','a6d31fd1c25211e98f5680fa5b3a283a',1),('01bf39cec62111e9bd4f80fa5b3a283a','menuId','归属菜单',0,'2019-08-24 11:40:58','2019-08-24 11:40:58','center','a6d31fd1c25211e98f5680fa5b3a283a',1),('1465898a133911eaa3ed80fa5b3a283a','tab','功能菜单名称',0,'2019-11-30 14:17:03','2019-11-30 14:17:03','center','1d7e7233132a11eaa3ed80fa5b3a283a',0),('1685cd54c57811e9bd4f80fa5b3a283a','id','键值',0,'2019-08-23 15:31:51','2019-08-23 15:31:51','center','493b7b82c25211e98f5680fa5b3a283a',1),('16962ab4c57811e9bd4f80fa5b3a283a','typeName','功能操作类型',0,'2019-08-23 15:31:51','2019-08-23 15:31:51','center','493b7b82c25211e98f5680fa5b3a283a',0),('16a88d56c57811e9bd4f80fa5b3a283a','codeName','功能操作',0,'2019-08-23 15:31:51','2019-08-23 15:31:51','center','493b7b82c25211e98f5680fa5b3a283a',0),('16b34d00c57811e9bd4f80fa5b3a283a','iconName','功能操作图标',0,'2019-08-23 15:31:52','2019-08-23 15:31:52','center','493b7b82c25211e98f5680fa5b3a283a',0),('16bf4cc1c57811e9bd4f80fa5b3a283a','updateDate','更新时间',0,'2019-08-23 15:31:52','2019-08-23 15:31:52','center','493b7b82c25211e98f5680fa5b3a283a',1),('16c9a9ddc57811e9bd4f80fa5b3a283a','createDate','创建时间',0,'2019-08-23 15:31:52','2019-08-23 15:31:52','center','493b7b82c25211e98f5680fa5b3a283a',1),('16d1cc47c57811e9bd4f80fa5b3a283a','isDel','是否删除',0,'2019-08-23 15:31:52','2019-08-23 15:31:52','center','493b7b82c25211e98f5680fa5b3a283a',1),('16d8e5d0c57811e9bd4f80fa5b3a283a','url','功能操作地址',0,'2019-08-23 15:31:52','2019-08-23 15:31:52','center','493b7b82c25211e98f5680fa5b3a283a',0),('16df4cf9c57811e9bd4f80fa5b3a283a','menuId','归属菜单',0,'2019-08-23 15:31:52','2019-08-23 15:31:52','center','493b7b82c25211e98f5680fa5b3a283a',1),('16e93bc6c57811e9bd4f80fa5b3a283a','isShow','是否显示',0,'2019-08-23 15:31:52','2019-08-23 15:31:52','center','493b7b82c25211e98f5680fa5b3a283a',1),('4aea37db91bc11e99d0480fa5b3a283a','id','键值',0,'2019-06-18 19:29:02','2019-06-18 19:29:02','center','99a6c4b38b4711e99d7380fa5b3a283a',1),('4af3d9a691bc11e99d0480fa5b3a283a','name','数据字典名称',0,'2019-06-18 19:29:02','2019-06-18 19:29:02','center','99a6c4b38b4711e99d7380fa5b3a283a',0),('4b02ecba91bc11e99d0480fa5b3a283a','typeName','数据字典类型',0,'2019-06-18 19:29:02','2019-06-18 19:29:02','center','99a6c4b38b4711e99d7380fa5b3a283a',0),('4b0d10d891bc11e99d0480fa5b3a283a','isDel','是否删除',0,'2019-06-18 19:29:02','2019-06-18 19:29:02','center','99a6c4b38b4711e99d7380fa5b3a283a',1),('4b19001b91bc11e99d0480fa5b3a283a','value','数据字典值',0,'2019-06-18 19:29:02','2019-06-18 19:29:02','center','99a6c4b38b4711e99d7380fa5b3a283a',0),('4b229af391bc11e99d0480fa5b3a283a','createDate','创建时间',0,'2019-06-18 19:29:02','2019-06-18 19:29:02','center','99a6c4b38b4711e99d7380fa5b3a283a',1),('4b2c736b91bc11e99d0480fa5b3a283a','updateDate','更新时间',0,'2019-06-18 19:29:02','2019-06-18 19:29:02','center','99a6c4b38b4711e99d7380fa5b3a283a',1),('596dfc1714cd11eaa3ed80fa5b3a283a','id','数据字典类型值',0,'2019-12-02 14:30:48','2019-12-02 14:30:48','center','ea32d48b132a11eaa3ed80fa5b3a283a',0),('7168f1ecc62211e9bd4f80fa5b3a283a','id','键值',0,'2019-08-24 11:51:15','2019-08-24 11:51:15','center','a6c593c5c25211e98f5680fa5b3a283a',1),('71713d44c62211e9bd4f80fa5b3a283a','createDate','创建时间',0,'2019-08-24 11:51:15','2019-08-24 11:51:15','center','a6c593c5c25211e98f5680fa5b3a283a',1),('717a234ac62211e9bd4f80fa5b3a283a','isDel','是否删除',0,'2019-08-24 11:51:15','2019-08-24 11:51:15','center','a6c593c5c25211e98f5680fa5b3a283a',1),('7182ddbec62211e9bd4f80fa5b3a283a','title','字段标题',0,'2019-08-24 11:51:15','2019-08-24 11:51:15','center','a6c593c5c25211e98f5680fa5b3a283a',0),('718d537fc62211e9bd4f80fa5b3a283a','field','匹配关键字',0,'2019-08-24 11:51:15','2019-08-24 11:51:15','center','a6c593c5c25211e98f5680fa5b3a283a',0),('719aeb33c62211e9bd4f80fa5b3a283a','updateDate','更新时间',0,'2019-08-24 11:51:16','2019-08-24 11:51:16','center','a6c593c5c25211e98f5680fa5b3a283a',1),('71a340a6c62211e9bd4f80fa5b3a283a','alignName','对齐方式',0,'2019-08-24 11:51:16','2019-08-24 11:51:16','center','a6c593c5c25211e98f5680fa5b3a283a',0),('71ac19adc62211e9bd4f80fa5b3a283a','menuId','归属菜单',0,'2019-08-24 11:51:16','2019-08-24 11:51:16','center','a6c593c5c25211e98f5680fa5b3a283a',1),('71b41b24c62211e9bd4f80fa5b3a283a','isShow','是否显示',0,'2019-08-24 11:51:16','2019-08-24 11:51:16','center','a6c593c5c25211e98f5680fa5b3a283a',0),('758e264e14cd11eaa3ed80fa5b3a283a','name','数据字典类型名称',0,'2019-12-02 14:31:35','2019-12-02 14:31:35','center','ea32d48b132a11eaa3ed80fa5b3a283a',0),('7698aa3c133911eaa3ed80fa5b3a283a','name','功能菜单键值',0,'2019-11-30 14:19:47','2019-11-30 14:19:47','center','1d7e7233132a11eaa3ed80fa5b3a283a',0),('7be853db133a11eaa3ed80fa5b3a283a','pageUrl','构造页面地址',0,'2019-11-30 14:27:06','2019-11-30 14:27:06','center','1d7e7233132a11eaa3ed80fa5b3a283a',0),('847580f2b8e611e9806380fa5b3a283a','status','状态',0,'2019-08-07 15:39:41','2019-08-07 15:39:41','left','b83fc0ba8b3411e99d7380fa5b3a283a',0),('85f671c214cd11eaa3ed80fa5b3a283a','createDate','创建时间',0,'2019-12-02 14:32:03','2019-12-02 14:32:03','center','ea32d48b132a11eaa3ed80fa5b3a283a',1),('860bbd0cb66011e985a680fa5b3a283a','id','键值',0,'2019-08-04 10:35:29','2019-08-04 10:35:29','center','b82b1c928b3411e99d7380fa5b3a283a',1),('8616fdbab66011e985a680fa5b3a283a','createDate','创建时间',0,'2019-08-04 10:35:29','2019-08-04 10:35:29','center','b82b1c928b3411e99d7380fa5b3a283a',1),('861ff808b66011e985a680fa5b3a283a','isDel','是否删除',0,'2019-08-04 10:35:29','2019-08-04 10:35:29','center','b82b1c928b3411e99d7380fa5b3a283a',1),('8628ab95b66011e985a680fa5b3a283a','name','角色名称',0,'2019-08-04 10:35:29','2019-08-04 10:35:29','center','b82b1c928b3411e99d7380fa5b3a283a',0),('8632eeb1b66011e985a680fa5b3a283a','typeName','角色类型',0,'2019-08-04 10:35:29','2019-08-04 10:35:29','center','b82b1c928b3411e99d7380fa5b3a283a',0),('863dacedb66011e985a680fa5b3a283a','updateDate','更新时间',0,'2019-08-04 10:35:29','2019-08-04 10:35:29','center','b82b1c928b3411e99d7380fa5b3a283a',1),('968aefde133a11eaa3ed80fa5b3a283a','createDate','创建时间',0,'2019-11-30 14:27:50','2019-11-30 14:27:50','center','1d7e7233132a11eaa3ed80fa5b3a283a',1),('9ab8744614cd11eaa3ed80fa5b3a283a','updateDate','更新时间',0,'2019-12-02 14:32:37','2019-12-02 14:32:37','center','ea32d48b132a11eaa3ed80fa5b3a283a',1),('9e7bdec8133a11eaa3ed80fa5b3a283a','updateDate','更新时间',0,'2019-11-30 14:28:04','2019-11-30 14:28:04','center','1d7e7233132a11eaa3ed80fa5b3a283a',1),('a4348cf514cd11eaa3ed80fa5b3a283a','isDel','是否删除',0,'2019-12-02 14:32:53','2019-12-02 14:32:53','center','ea32d48b132a11eaa3ed80fa5b3a283a',1),('a71bd91e133a11eaa3ed80fa5b3a283a','isDel','是否删除',0,'2019-11-30 14:28:18','2019-11-30 14:28:18','center','1d7e7233132a11eaa3ed80fa5b3a283a',1),('bec89965133a11eaa3ed80fa5b3a283a','pidName','父功能菜单键值',0,'2019-11-30 14:28:58','2019-11-30 14:28:58','center','1d7e7233132a11eaa3ed80fa5b3a283a',0),('d258a640bbed11e9878380fa5b3a283a','userName','用户名称',0,'2019-08-11 12:09:25','2019-08-11 12:09:25','center','b83782508b3411e99d7380fa5b3a283a',0),('d2690a4ebbed11e9878380fa5b3a283a','roleName','角色名称',0,'2019-08-11 12:09:25','2019-08-11 12:09:25','center','b83782508b3411e99d7380fa5b3a283a',0),('d2a38c65133a11eaa3ed80fa5b3a283a','dataUrl','页面数据地址',0,'2019-11-30 14:29:31','2019-11-30 14:29:31','center','1d7e7233132a11eaa3ed80fa5b3a283a',0),('dd9e35c1b8e411e9806380fa5b3a283a','id','键值',0,'2019-08-07 15:27:52','2019-08-07 15:27:52','center','b83fc0ba8b3411e99d7380fa5b3a283a',1),('ddbac7d2b8e411e9806380fa5b3a283a','createDate','创建时间',0,'2019-08-07 15:27:52','2019-08-07 15:27:52','center','b83fc0ba8b3411e99d7380fa5b3a283a',1),('ddca4564b8e411e9806380fa5b3a283a','isDel','是否删除',0,'2019-08-07 15:27:52','2019-08-07 15:27:52','center','b83fc0ba8b3411e99d7380fa5b3a283a',1),('ddd913dcb8e411e9806380fa5b3a283a','menuId','归属菜单',0,'2019-08-07 15:27:52','2019-08-07 15:27:52','center','b83fc0ba8b3411e99d7380fa5b3a283a',1),('dde8d8efb8e411e9806380fa5b3a283a','roleId','角色键值',0,'2019-08-07 15:27:52','2019-08-07 15:27:52','center','b83fc0ba8b3411e99d7380fa5b3a283a',1),('ddfb56deb8e411e9806380fa5b3a283a','updateDate','更新时间',0,'2019-08-07 15:27:52','2019-08-07 15:27:52','center','b83fc0ba8b3411e99d7380fa5b3a283a',1),('de110600b8e411e9806380fa5b3a283a','menuName','标签名称',0,'2019-08-07 15:27:52','2019-08-07 15:27:52','center','b83fc0ba8b3411e99d7380fa5b3a283a',1),('de1cf85cb8e411e9806380fa5b3a283a','name','功能名称',0,'2019-08-07 15:27:52','2019-08-07 15:27:52','left','b83fc0ba8b3411e99d7380fa5b3a283a',0),('f9fa994a133a11eaa3ed80fa5b3a283a','itemUrl','二次页面数据地址',0,'2019-11-30 14:30:37','2019-11-30 14:30:37','center','1d7e7233132a11eaa3ed80fa5b3a283a',0),('fe34d888133811eaa3ed80fa5b3a283a','id','键值',0,'2019-11-30 14:16:25','2019-11-30 14:16:25','center','1d7e7233132a11eaa3ed80fa5b3a283a',1);
/*!40000 ALTER TABLE `columns` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `data_dictionary`
--

DROP TABLE IF EXISTS `data_dictionary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `data_dictionary` (
  `id` char(32) NOT NULL,
  `name` varchar(100) NOT NULL COMMENT '名称',
  `type_id` bigint(20) NOT NULL COMMENT '类型',
  `value` varchar(50) NOT NULL COMMENT '值',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `is_del` int(1) NOT NULL DEFAULT '0' COMMENT '是否删除：0 未删除 | 1 已删除',
  PRIMARY KEY (`id`),
  KEY `DATA_DICTIONARY_BY_ISDEL_INDEX` (`is_del`) USING BTREE,
  KEY `FKg4kmvyh71vlmpmfi9klq78bu5` (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='配置数据字典';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `data_dictionary`
--

LOCK TABLES `data_dictionary` WRITE;
/*!40000 ALTER TABLE `data_dictionary` DISABLE KEYS */;
INSERT INTO `data_dictionary` VALUES ('0690fb8713f011eaa3ed80fa5b3a283a','下拉列表树选择',5,'selectsTree','2019-12-01 12:06:34','2019-12-01 12:06:34',0),('26bdda6c100911eaa3ed80fa5b3a283a','系统角色',7,'xtjs','2019-11-26 12:55:49','2019-11-26 12:55:51',0),('302401d1cba511e9ad5680fa5b3a283a','标题输入框',5,'inputLable','2019-08-31 12:09:54','2019-08-31 12:09:54',0),('33c9667f100911eaa3ed80fa5b3a283a','系统建设',7,'xtjis','2019-11-26 12:57:25','2019-11-26 12:57:28',0),('377307b9cba511e9ad5680fa5b3a283a','下拉列表选择',5,'selects','2019-08-31 12:10:07','2019-08-31 12:10:07',0),('400631aac96711e9968180fa5b3a283a','glyphicon-pencil',1,'391744999','2019-08-28 15:41:27','2019-08-28 15:41:58',0),('5f733c09c96711e9968180fa5b3a283a','glyphicon-trash',1,'391744535','2019-08-28 15:42:20','2019-08-28 15:42:20',0),('778de2efcacd11e9968180fa5b3a283a','修改',2,'1','2019-08-30 10:25:36','2019-08-30 10:25:36',0),('7b55247dcacd11e9968180fa5b3a283a','删除',2,'2','2019-08-30 10:25:42','2019-08-30 10:25:42',0),('84346dafcacd11e9968180fa5b3a283a','添加',2,'0','2019-08-30 10:25:57','2019-08-30 10:25:57',0),('c7fc32d4cba311e9ad5680fa5b3a283a','居中对齐',4,'center','2019-08-31 11:59:50','2019-08-31 11:59:50',0),('ddc05effcba411e9ad5680fa5b3a283a','text',5,'1567224456697','2019-08-31 12:07:36','2019-08-31 12:46:20',0),('e8814218cace11e9968180fa5b3a283a','button',6,'1567132555','2019-08-30 10:35:55','2019-08-30 10:35:55',0),('fb542eacc96511e9968180fa5b3a283a','glyphicon-plus',1,'391744385','2019-08-28 15:32:22','2019-08-28 15:32:22',0);
/*!40000 ALTER TABLE `data_dictionary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (5);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log`
--

DROP TABLE IF EXISTS `log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `is_del` int(1) DEFAULT NULL,
  `log_ex` varchar(255) DEFAULT NULL,
  `log_in` text,
  `log_out` text,
  `log_text` varchar(500) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `me_modifier` varchar(255) DEFAULT NULL,
  `me_return_type` varchar(255) DEFAULT NULL,
  `me_declaring` varchar(255) DEFAULT NULL,
  `me_name` varchar(255) DEFAULT NULL,
  `me_parameter_type` varchar(255) DEFAULT NULL,
  `me_exception_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='日志';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log`
--

LOCK TABLES `log` WRITE;
/*!40000 ALTER TABLE `log` DISABLE KEYS */;
/*!40000 ALTER TABLE `log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `opera_in`
--

DROP TABLE IF EXISTS `opera_in`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `opera_in` (
  `id` char(32) NOT NULL COMMENT 'ID,32位字符串',
  `text_name` varchar(100) NOT NULL COMMENT '字段标题',
  `html_type` varchar(50) NOT NULL COMMENT 'html类型',
  `field` varchar(50) NOT NULL COMMENT '字段标志',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `is_del` int(1) NOT NULL COMMENT '0：未删除 | 1：已删除',
  `is_show` int(1) NOT NULL DEFAULT '1' COMMENT '0：显示 | 1：不显示',
  `menu_id` char(32) DEFAULT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字段输入';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `opera_in`
--

LOCK TABLES `opera_in` WRITE;
/*!40000 ALTER TABLE `opera_in` DISABLE KEYS */;
INSERT INTO `opera_in` VALUES ('0806ade314ce11eaa3ed80fa5b3a283a','数据字典类型值','inputLable','id','2019-12-02 14:35:41','2019-12-02 14:35:41',0,1,'ea32d48b132a11eaa3ed80fa5b3a283a'),('0a933692133d11eaa3ed80fa5b3a283a','父功能菜单键值','selectsTree','pid','2019-11-30 14:45:24','2019-11-30 14:45:24',0,0,'1d7e7233132a11eaa3ed80fa5b3a283a'),('11ab9b0414ce11eaa3ed80fa5b3a283a','数据字典类型名称','inputLable','name','2019-12-02 14:35:57','2019-12-02 14:35:57',0,0,'ea32d48b132a11eaa3ed80fa5b3a283a'),('159c2d39133d11eaa3ed80fa5b3a283a','页面数据地址','inputLable','dataUrl','2019-11-30 14:45:43','2019-11-30 14:45:43',0,0,'1d7e7233132a11eaa3ed80fa5b3a283a'),('16b612d7956911e9914980fa5b3a283a','键值','inputLable','id','2019-06-23 11:43:39','2019-06-23 11:43:39',0,1,'99a6c4b38b4711e99d7380fa5b3a283a'),('16ebd302956911e9914980fa5b3a283a','数据字典名称','inputLable','name','2019-06-23 11:43:40','2019-06-23 11:43:40',0,0,'99a6c4b38b4711e99d7380fa5b3a283a'),('16f8029c956911e9914980fa5b3a283a','数据字典类型','selects','typeId','2019-06-23 11:43:40','2019-06-23 11:43:40',0,0,'99a6c4b38b4711e99d7380fa5b3a283a'),('1704cf33956911e9914980fa5b3a283a','是否删除','inputLable','isDel','2019-06-23 11:43:40','2019-06-23 11:43:40',0,1,'99a6c4b38b4711e99d7380fa5b3a283a'),('170d0d5c956911e9914980fa5b3a283a','数据字典值','inputLable','value','2019-06-23 11:43:40','2019-06-23 11:43:40',0,1,'99a6c4b38b4711e99d7380fa5b3a283a'),('1715eb3a956911e9914980fa5b3a283a','创建时间','inputLable','createDate','2019-06-23 11:43:40','2019-06-23 11:43:40',0,1,'99a6c4b38b4711e99d7380fa5b3a283a'),('171ea786956911e9914980fa5b3a283a','更新时间','inputLable','updateDate','2019-06-23 11:43:40','2019-06-23 11:43:40',0,1,'99a6c4b38b4711e99d7380fa5b3a283a'),('1750c28bc57911e9bd4f80fa5b3a283a','键值','inputLable','id','2019-08-23 15:39:02','2019-08-23 15:39:02',0,1,'493b7b82c25211e98f5680fa5b3a283a'),('1762f627c57911e9bd4f80fa5b3a283a','创建时间','inputLable','createDate','2019-08-23 15:39:02','2019-08-23 15:39:02',0,1,'493b7b82c25211e98f5680fa5b3a283a'),('176bcfdcc57911e9bd4f80fa5b3a283a','是否删除','inputLable','isDel','2019-08-23 15:39:02','2019-08-23 15:39:02',0,1,'493b7b82c25211e98f5680fa5b3a283a'),('178b8e17c57911e9bd4f80fa5b3a283a','功能操作类型','selects','type','2019-08-23 15:39:02','2019-08-23 15:39:02',0,0,'493b7b82c25211e98f5680fa5b3a283a'),('17932552c57911e9bd4f80fa5b3a283a','更新时间','inputLable','updateDate','2019-08-23 15:39:02','2019-08-23 15:39:02',0,1,'493b7b82c25211e98f5680fa5b3a283a'),('179c7a6fc57911e9bd4f80fa5b3a283a','功能操作','selects','code','2019-08-23 15:39:03','2019-08-23 15:39:03',0,0,'493b7b82c25211e98f5680fa5b3a283a'),('17a428fec57911e9bd4f80fa5b3a283a','功能操作图标','selects','icon','2019-08-23 15:39:03','2019-08-23 15:39:03',0,0,'493b7b82c25211e98f5680fa5b3a283a'),('17b090ebc57911e9bd4f80fa5b3a283a','功能操作地址','inputLable','url','2019-08-23 15:39:03','2019-08-23 15:39:03',0,0,'493b7b82c25211e98f5680fa5b3a283a'),('17b836bcc57911e9bd4f80fa5b3a283a','归属菜单','inputLable','menuId','2019-08-23 15:39:03','2019-08-23 15:39:03',0,1,'493b7b82c25211e98f5680fa5b3a283a'),('17bf5c43c57911e9bd4f80fa5b3a283a','是否显示','inputLable','isShow','2019-08-23 15:39:03','2019-08-23 15:39:03',0,1,'493b7b82c25211e98f5680fa5b3a283a'),('2072fc1e133d11eaa3ed80fa5b3a283a','二次页面数据地址','inputLable','itemUrl','2019-11-30 14:46:01','2019-11-30 14:46:01',0,0,'1d7e7233132a11eaa3ed80fa5b3a283a'),('2acd34bbb81911e9806380fa5b3a283a','角色键值','selects','roleId','2019-08-06 15:09:44','2019-08-06 15:09:44',0,0,'b83fc0ba8b3411e99d7380fa5b3a283a'),('2ad8136ab81911e9806380fa5b3a283a','功能列表','treeView','menuId','2019-08-06 15:09:44','2019-08-06 15:09:44',0,0,'b83fc0ba8b3411e99d7380fa5b3a283a'),('2efa889414ce11eaa3ed80fa5b3a283a','创建时间','inputLable','createDate','2019-12-02 14:36:46','2019-12-02 14:36:46',0,1,'ea32d48b132a11eaa3ed80fa5b3a283a'),('35d52b9d133c11eaa3ed80fa5b3a283a','键值','inputLable','id','2019-11-30 14:39:27','2019-11-30 14:39:27',1,0,'1d7e7233132a11eaa3ed80fa5b3a283a'),('39268e7f14ce11eaa3ed80fa5b3a283a','更新时间','inputLable','updateDate','2019-12-02 14:37:03','2019-12-02 14:37:03',0,1,'ea32d48b132a11eaa3ed80fa5b3a283a'),('5854077db66111e985a680fa5b3a283a','键值','inputLable','id','2019-08-04 10:41:22','2019-08-04 10:41:22',0,1,'b82b1c928b3411e99d7380fa5b3a283a'),('58602095b66111e985a680fa5b3a283a','创建时间','inputLable','createDate','2019-08-04 10:41:22','2019-08-04 10:41:22',0,1,'b82b1c928b3411e99d7380fa5b3a283a'),('5869a1f2b66111e985a680fa5b3a283a','是否删除','inputLable','isDel','2019-08-04 10:41:22','2019-08-04 10:41:22',0,1,'b82b1c928b3411e99d7380fa5b3a283a'),('5875e37bb66111e985a680fa5b3a283a','角色名称','inputLable','name','2019-08-04 10:41:22','2019-08-04 10:41:22',0,0,'b82b1c928b3411e99d7380fa5b3a283a'),('58837670b66111e985a680fa5b3a283a','角色类型','selects','type','2019-08-04 10:41:22','2019-08-04 10:41:22',0,0,'b82b1c928b3411e99d7380fa5b3a283a'),('588d5153b66111e985a680fa5b3a283a','更新时间','inputLable','updateDate','2019-08-04 10:41:22','2019-08-04 10:41:22',0,1,'b82b1c928b3411e99d7380fa5b3a283a'),('8fe86e24c62111e9bd4f80fa5b3a283a','键值','inputLable','id','2019-08-24 11:44:57','2019-08-24 11:44:57',0,1,'a6d31fd1c25211e98f5680fa5b3a283a'),('8ff308c1c62111e9bd4f80fa5b3a283a','创建时间','inputLable','createDate','2019-08-24 11:44:57','2019-08-24 11:44:57',0,1,'a6d31fd1c25211e98f5680fa5b3a283a'),('8ffa18a5c62111e9bd4f80fa5b3a283a','是否删除','inputLable','isDel','2019-08-24 11:44:57','2019-08-24 11:44:57',0,1,'a6d31fd1c25211e98f5680fa5b3a283a'),('900699cec62111e9bd4f80fa5b3a283a','文本名称','inputLable','textName','2019-08-24 11:44:57','2019-08-24 11:44:57',0,0,'a6d31fd1c25211e98f5680fa5b3a283a'),('901e293bc62111e9bd4f80fa5b3a283a','更新时间','inputLable','updateDate','2019-08-24 11:44:57','2019-08-24 11:44:57',0,1,'a6d31fd1c25211e98f5680fa5b3a283a'),('9029ac4bc62111e9bd4f80fa5b3a283a','HTML类型','selects','htmlType','2019-08-24 11:44:57','2019-08-24 11:44:57',0,0,'a6d31fd1c25211e98f5680fa5b3a283a'),('90361526c62111e9bd4f80fa5b3a283a','匹配关键字','inputLable','field','2019-08-24 11:44:57','2019-08-24 11:44:57',0,0,'a6d31fd1c25211e98f5680fa5b3a283a'),('90414e42c62111e9bd4f80fa5b3a283a','是否显示','inputLable','isShow','2019-08-24 11:44:57','2019-08-24 11:44:57',0,1,'a6d31fd1c25211e98f5680fa5b3a283a'),('9048e36cc62111e9bd4f80fa5b3a283a','归属菜单','inputLable','menuId','2019-08-24 11:44:58','2019-08-24 11:44:58',0,1,'a6d31fd1c25211e98f5680fa5b3a283a'),('cf5f95dd133c11eaa3ed80fa5b3a283a','功能菜单名称','inputLable','tab','2019-11-30 14:43:45','2019-11-30 14:43:45',0,0,'1d7e7233132a11eaa3ed80fa5b3a283a'),('d80101cc133c11eaa3ed80fa5b3a283a','功能菜单键值','inputLable','name','2019-11-30 14:43:59','2019-11-30 14:43:59',0,0,'1d7e7233132a11eaa3ed80fa5b3a283a'),('e44ee748133c11eaa3ed80fa5b3a283a','构造页面地址','inputLable','pageUrl','2019-11-30 14:44:20','2019-11-30 14:44:20',0,0,'1d7e7233132a11eaa3ed80fa5b3a283a'),('ed6266aa133c11eaa3ed80fa5b3a283a','创建时间','inputLable','createDate','2019-11-30 14:44:35','2019-11-30 14:44:35',1,0,'1d7e7233132a11eaa3ed80fa5b3a283a'),('ed9b46dbc62111e9bd4f80fa5b3a283a','键值','inputLable','id','2019-08-24 11:47:34','2019-08-24 11:47:34',0,1,'a6c593c5c25211e98f5680fa5b3a283a'),('eda62322c62111e9bd4f80fa5b3a283a','创建时间','inputLable','createDate','2019-08-24 11:47:34','2019-08-24 11:47:34',0,1,'a6c593c5c25211e98f5680fa5b3a283a'),('edaed48fc62111e9bd4f80fa5b3a283a','是否删除','inputLable','isDel','2019-08-24 11:47:34','2019-08-24 11:47:34',0,1,'a6c593c5c25211e98f5680fa5b3a283a'),('edc3ad1ec62111e9bd4f80fa5b3a283a','字段标题','inputLable','title','2019-08-24 11:47:34','2019-08-24 11:47:34',0,0,'a6c593c5c25211e98f5680fa5b3a283a'),('edca09efc62111e9bd4f80fa5b3a283a','对齐方式','selects','align','2019-08-24 11:47:34','2019-08-24 11:47:34',0,0,'a6c593c5c25211e98f5680fa5b3a283a'),('edd4cbd7c62111e9bd4f80fa5b3a283a','更新时间','inputLable','updateDate','2019-08-24 11:47:34','2019-08-24 11:47:34',0,1,'a6c593c5c25211e98f5680fa5b3a283a'),('eddec8b2c62111e9bd4f80fa5b3a283a','是否显示','inputLable','isShow','2019-08-24 11:47:35','2019-08-24 11:47:35',0,1,'a6c593c5c25211e98f5680fa5b3a283a'),('ede92596c62111e9bd4f80fa5b3a283a','归属菜单','inputLable','menuId','2019-08-24 11:47:35','2019-08-24 11:47:35',0,1,'a6c593c5c25211e98f5680fa5b3a283a'),('edefeb6bc62111e9bd4f80fa5b3a283a','匹配关键字','inputLable','field','2019-08-24 11:47:35','2019-08-24 11:47:35',0,0,'a6c593c5c25211e98f5680fa5b3a283a'),('f5914c4d133c11eaa3ed80fa5b3a283a','更新时间','inputLable','updateDate','2019-11-30 14:44:49','2019-11-30 14:44:49',1,0,'1d7e7233132a11eaa3ed80fa5b3a283a'),('fcb47f29133c11eaa3ed80fa5b3a283a','是否删除','inputLable','isDel','2019-11-30 14:45:01','2019-11-30 14:45:01',1,0,'1d7e7233132a11eaa3ed80fa5b3a283a');
/*!40000 ALTER TABLE `opera_in` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `opera_sb`
--

DROP TABLE IF EXISTS `opera_sb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `opera_sb` (
  `id` char(32) NOT NULL COMMENT 'ID,32位字符串',
  `type` varchar(50) NOT NULL DEFAULT '0' COMMENT '操作按钮类型：0：add | 1：update | 2 ：delete',
  `code` varchar(11) NOT NULL COMMENT '逻辑处理：0：add | 1：update | 2 ：delete',
  `icon` varchar(50) DEFAULT '' COMMENT '图标',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `is_del` int(1) NOT NULL DEFAULT '0' COMMENT '0；未删除 | 1：已删除',
  `url` varchar(50) NOT NULL COMMENT '执行动作的请求地址',
  `menu_id` char(32) NOT NULL COMMENT '菜单id',
  `is_show` int(1) NOT NULL DEFAULT '0' COMMENT '0：显示 | 1：不显示',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='功能按钮';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `opera_sb`
--

LOCK TABLES `opera_sb` WRITE;
/*!40000 ALTER TABLE `opera_sb` DISABLE KEYS */;
INSERT INTO `opera_sb` VALUES ('005a46a7c61c11e9bd4f80fa5b3a283a','1567132555','0','391744385','2019-08-24 11:05:09','2019-08-24 11:05:09',0,'insertFunctionColumns','a6c593c5c25211e98f5680fa5b3a283a',0),('134dfeacb81711e9806380fa5b3a283a','1567132555','0','391744385','2019-08-06 14:54:45','2019-08-06 14:54:45',0,'insertRoleFunction','b83fc0ba8b3411e99d7380fa5b3a283a',1),('136d2cebb81711e9806380fa5b3a283a','1567132555','1','391744999','2019-08-06 14:54:46','2019-08-06 14:54:46',0,'updateRoleFunction','b83fc0ba8b3411e99d7380fa5b3a283a',0),('1376b1d2b81711e9806380fa5b3a283a','1567132555','2','391744535','2019-08-06 14:54:46','2019-08-06 14:54:46',0,'deleteRoleFunction','b83fc0ba8b3411e99d7380fa5b3a283a',1),('1a83150ab66311e985a680fa5b3a283a','1567132555','0','391744385','2019-08-04 10:53:57','2019-08-04 10:53:57',0,'insertRoleConfig','b82b1c928b3411e99d7380fa5b3a283a',0),('1a8e40efb66311e985a680fa5b3a283a','1567132555','1','391744999','2019-08-04 10:53:57','2019-08-04 10:53:57',0,'updateRoleConfig','b82b1c928b3411e99d7380fa5b3a283a',0),('1a96f17eb66311e985a680fa5b3a283a','1567132555','2','391744535','2019-08-04 10:53:57','2019-08-04 10:53:57',0,'deleteRoleConfig','b82b1c928b3411e99d7380fa5b3a283a',0),('27b783e9c61c11e9bd4f80fa5b3a283a','1567132555','1','391744999','2019-08-24 11:06:15','2019-08-24 11:06:15',0,'updateFunctionColumns','a6c593c5c25211e98f5680fa5b3a283a',0),('35199651be3911e98f5680fa5b3a283a','1567132555','0','391744385','2019-08-14 10:14:08','2019-08-14 10:14:08',0,'insertUserRole','b83782508b3411e99d7380fa5b3a283a',1),('353339a1be3911e98f5680fa5b3a283a','1567132555','1','391744999','2019-08-14 10:14:08','2019-08-14 10:14:08',0,'updateUserRole','b83782508b3411e99d7380fa5b3a283a',0),('353f47ffbe3911e98f5680fa5b3a283a','1567132555','2','391744535','2019-08-14 10:14:08','2019-08-14 10:14:08',0,'deleteUserRole','b83782508b3411e99d7380fa5b3a283a',1),('40f9821c133711eaa3ed80fa5b3a283a','1567132555','2','391744535','2019-11-30 14:03:58','2019-11-30 14:03:58',0,'deleteSystemFunctionMenu','1d7e7233132a11eaa3ed80fa5b3a283a',0),('4b7dc1c114c611eaa3ed80fa5b3a283a','1567132555','1','391744999','2019-12-02 13:40:18','2019-12-02 13:40:18',0,'updateTypeDictionary','ea32d48b132a11eaa3ed80fa5b3a283a',0),('4db8c8e0c61d11e9bd4f80fa5b3a283a','1567132555','0','391744385','2019-08-24 11:14:28','2019-08-24 11:14:28',0,'insertFunctionInput','a6d31fd1c25211e98f5680fa5b3a283a',0),('5db5823cc61d11e9bd4f80fa5b3a283a','1567132555','1','391744999','2019-08-24 11:14:55','2019-08-24 11:14:55',0,'updateFunctionInput','a6d31fd1c25211e98f5680fa5b3a283a',0),('605490e914c611eaa3ed80fa5b3a283a','1567132555','0','391744385','2019-12-02 13:40:53','2019-12-02 13:40:53',0,'insertTypeDictionary','ea32d48b132a11eaa3ed80fa5b3a283a',0),('60d42824956711e9914980fa5b3a283a','1567132555','0','391744385','2019-06-23 11:31:25','2019-06-23 11:31:25',0,'insertDataDictionary','99a6c4b38b4711e99d7380fa5b3a283a',0),('610a7c28956711e9914980fa5b3a283a','1567132555','1','391744999','2019-06-23 11:31:25','2019-06-23 11:31:25',0,'updateDataDictionary','99a6c4b38b4711e99d7380fa5b3a283a',0),('610fcf14956711e9914980fa5b3a283a','1567132555','2','391744535','2019-06-23 11:31:25','2019-06-23 11:31:25',0,'deleteDataDictionary','99a6c4b38b4711e99d7380fa5b3a283a',0),('65957244c61d11e9bd4f80fa5b3a283a','1567132555','2','391744535','2019-08-24 11:15:08','2019-08-24 11:15:08',0,'deleteFunctionInput','a6d31fd1c25211e98f5680fa5b3a283a',0),('708a5eb514c611eaa3ed80fa5b3a283a','1567132555','2','391744535','2019-12-02 13:41:20','2019-12-02 13:41:20',0,'deleteTypeDictionary','ea32d48b132a11eaa3ed80fa5b3a283a',0),('7a26328dc61c11e9bd4f80fa5b3a283a','1567132555','2','391744535','2019-08-24 11:08:33','2019-08-24 11:08:33',0,'deleteFunctionColumns','a6c593c5c25211e98f5680fa5b3a283a',0),('8aaa1453c57911e9bd4f80fa5b3a283a','1567132555','0','391744385','2019-08-23 15:42:16','2019-08-23 15:42:16',0,'insertFunctionButton','493b7b82c25211e98f5680fa5b3a283a',0),('8ab783acc57911e9bd4f80fa5b3a283a','1567132555','1','391744999','2019-08-23 15:42:16','2019-08-23 15:42:16',0,'updateFunctionButton','493b7b82c25211e98f5680fa5b3a283a',0),('8ac4e09dc57911e9bd4f80fa5b3a283a','1567132555','2','391744535','2019-08-23 15:42:16','2019-08-23 15:42:16',0,'deleteFunctionButton','493b7b82c25211e98f5680fa5b3a283a',0),('bbe24483133611eaa3ed80fa5b3a283a','1567132555','0','391744385','2019-11-30 14:00:15','2019-11-30 14:00:15',0,'insertSystemFunctionMenu','1d7e7233132a11eaa3ed80fa5b3a283a',0),('ce246164133611eaa3ed80fa5b3a283a','1567132555','1','391744999','2019-11-30 14:00:46','2019-11-30 14:00:46',0,'updateSystemFunctionMenu','1d7e7233132a11eaa3ed80fa5b3a283a',0);
/*!40000 ALTER TABLE `opera_sb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_config`
--

DROP TABLE IF EXISTS `role_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_config` (
  `id` varchar(32) NOT NULL COMMENT 'ID,32位字符串',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `is_del` int(1) NOT NULL COMMENT '0：未删除 | 1：已删除',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `type` varchar(50) NOT NULL COMMENT '类型',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_config`
--

LOCK TABLES `role_config` WRITE;
/*!40000 ALTER TABLE `role_config` DISABLE KEYS */;
INSERT INTO `role_config` VALUES ('492daab1b66411e985a680fa5b3a283a','2019-08-04 11:02:25',1,'admin','xtjs','2019-08-04 11:02:25'),('4b7affe614dc11eaa3ed80fa5b3a283a','2019-12-02 16:17:47',0,'yyy','xtjis','2019-12-02 16:17:47'),('5a76e6b5b66511e985a680fa5b3a283a','2019-08-04 11:10:03',0,'admin','xtjs','2019-08-04 11:10:03'),('5b16105fb66411e985a680fa5b3a283a','2019-08-04 11:02:55',1,'admin','xtjs','2019-08-04 11:09:39'),('bb6ec9de100a11eaa3ed80fa5b3a283a','2019-11-26 13:07:52',0,'1','xtjs','2019-11-26 13:07:52'),('e7c8354cbbde11e9878380fa5b3a283a','2019-08-11 10:22:38',0,'user','xtjis','2019-08-11 10:22:38');
/*!40000 ALTER TABLE `role_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_function`
--

DROP TABLE IF EXISTS `role_function`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_function` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `is_del` int(1) DEFAULT NULL,
  `menu_id` char(32) NOT NULL,
  `role_id` char(32) NOT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=526 DEFAULT CHARSET=utf8 COMMENT='角色功能';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_function`
--

LOCK TABLES `role_function` WRITE;
/*!40000 ALTER TABLE `role_function` DISABLE KEYS */;
INSERT INTO `role_function` VALUES (61,'2019-08-14 10:44:47',0,'99a6c4b38b4711e99d7380fa5b3a283a','5a76e6b5b66511e985a680fa5b3a283a','2019-08-14 10:44:47'),(62,'2019-08-14 10:44:47',0,'4af3d9a691bc11e99d0480fa5b3a283a','5a76e6b5b66511e985a680fa5b3a283a','2019-08-14 10:44:47'),(63,'2019-08-14 10:44:47',0,'4b02ecba91bc11e99d0480fa5b3a283a','5a76e6b5b66511e985a680fa5b3a283a','2019-08-14 10:44:47'),(64,'2019-08-14 10:44:47',0,'4b19001b91bc11e99d0480fa5b3a283a','5a76e6b5b66511e985a680fa5b3a283a','2019-08-14 10:44:47'),(65,'2019-08-14 10:44:47',0,'60d42824956711e9914980fa5b3a283a','5a76e6b5b66511e985a680fa5b3a283a','2019-08-14 10:44:47'),(66,'2019-08-14 10:44:47',0,'610a7c28956711e9914980fa5b3a283a','5a76e6b5b66511e985a680fa5b3a283a','2019-08-14 10:44:47'),(67,'2019-08-14 10:44:47',0,'610fcf14956711e9914980fa5b3a283a','5a76e6b5b66511e985a680fa5b3a283a','2019-08-14 10:44:47'),(68,'2019-08-14 10:44:47',0,'16ebd302956911e9914980fa5b3a283a','5a76e6b5b66511e985a680fa5b3a283a','2019-08-14 10:44:47'),(69,'2019-08-14 10:44:47',0,'16f8029c956911e9914980fa5b3a283a','5a76e6b5b66511e985a680fa5b3a283a','2019-08-14 10:44:47'),(70,'2019-08-14 10:44:47',0,'170d0d5c956911e9914980fa5b3a283a','5a76e6b5b66511e985a680fa5b3a283a','2019-08-14 10:44:47'),(71,'2019-08-14 10:44:47',0,'99b7ec5c8b4711e99d7380fa5b3a283a','5a76e6b5b66511e985a680fa5b3a283a','2019-08-14 10:44:47'),(72,'2019-08-14 10:44:47',0,'b8148d088b3411e99d7380fa5b3a283a','5a76e6b5b66511e985a680fa5b3a283a','2019-08-14 10:44:47'),(73,'2019-08-14 10:44:47',0,'b8209ee58b3411e99d7380fa5b3a283a','5a76e6b5b66511e985a680fa5b3a283a','2019-08-14 10:44:47'),(74,'2019-08-14 10:44:47',0,'3b4bf5ca9caa11e9be7d80fa5b3a283a','5a76e6b5b66511e985a680fa5b3a283a','2019-08-14 10:44:47'),(75,'2019-08-14 10:44:47',0,'3b7260d59caa11e9be7d80fa5b3a283a','5a76e6b5b66511e985a680fa5b3a283a','2019-08-14 10:44:47'),(76,'2019-08-14 10:44:47',0,'3e3b4f919efe11e9be7d80fa5b3a283a','5a76e6b5b66511e985a680fa5b3a283a','2019-08-14 10:44:47'),(77,'2019-08-14 10:44:47',0,'3e49c7489efe11e9be7d80fa5b3a283a','5a76e6b5b66511e985a680fa5b3a283a','2019-08-14 10:44:47'),(78,'2019-08-14 10:44:47',0,'3e50247c9efe11e9be7d80fa5b3a283a','5a76e6b5b66511e985a680fa5b3a283a','2019-08-14 10:44:47'),(79,'2019-08-14 10:44:47',0,'8caa5a4da38011e9b79180fa5b3a283a','5a76e6b5b66511e985a680fa5b3a283a','2019-08-14 10:44:47'),(80,'2019-08-14 10:44:47',0,'e76d1bb2a38111e9b79180fa5b3a283a','5a76e6b5b66511e985a680fa5b3a283a','2019-08-14 10:44:47'),(81,'2019-08-14 10:44:47',0,'8628ab95b66011e985a680fa5b3a283a','5a76e6b5b66511e985a680fa5b3a283a','2019-08-14 10:44:47'),(82,'2019-08-14 10:44:47',0,'8632eeb1b66011e985a680fa5b3a283a','5a76e6b5b66511e985a680fa5b3a283a','2019-08-14 10:44:47'),(83,'2019-08-14 10:44:47',0,'1a83150ab66311e985a680fa5b3a283a','5a76e6b5b66511e985a680fa5b3a283a','2019-08-14 10:44:47'),(84,'2019-08-14 10:44:47',0,'1a8e40efb66311e985a680fa5b3a283a','5a76e6b5b66511e985a680fa5b3a283a','2019-08-14 10:44:47'),(85,'2019-08-14 10:44:47',0,'1a96f17eb66311e985a680fa5b3a283a','5a76e6b5b66511e985a680fa5b3a283a','2019-08-14 10:44:47'),(86,'2019-08-14 10:44:47',0,'5875e37bb66111e985a680fa5b3a283a','5a76e6b5b66511e985a680fa5b3a283a','2019-08-14 10:44:47'),(87,'2019-08-14 10:44:47',0,'58837670b66111e985a680fa5b3a283a','5a76e6b5b66511e985a680fa5b3a283a','2019-08-14 10:44:47'),(88,'2019-08-14 10:44:47',0,'d258a640bbed11e9878380fa5b3a283a','5a76e6b5b66511e985a680fa5b3a283a','2019-08-14 10:44:47'),(89,'2019-08-14 10:44:47',0,'d2690a4ebbed11e9878380fa5b3a283a','5a76e6b5b66511e985a680fa5b3a283a','2019-08-14 10:44:47'),(90,'2019-08-14 10:44:47',0,'35199651be3911e98f5680fa5b3a283a','5a76e6b5b66511e985a680fa5b3a283a','2019-08-14 10:44:47'),(91,'2019-08-14 10:44:47',0,'353339a1be3911e98f5680fa5b3a283a','5a76e6b5b66511e985a680fa5b3a283a','2019-08-14 10:44:47'),(92,'2019-08-14 10:44:47',0,'353f47ffbe3911e98f5680fa5b3a283a','5a76e6b5b66511e985a680fa5b3a283a','2019-08-14 10:44:47'),(93,'2019-08-14 10:44:47',0,'847580f2b8e611e9806380fa5b3a283a','5a76e6b5b66511e985a680fa5b3a283a','2019-08-14 10:44:47'),(94,'2019-08-14 10:44:47',0,'de1cf85cb8e411e9806380fa5b3a283a','5a76e6b5b66511e985a680fa5b3a283a','2019-08-14 10:44:47'),(95,'2019-08-14 10:44:47',0,'136d2cebb81711e9806380fa5b3a283a','5a76e6b5b66511e985a680fa5b3a283a','2019-08-14 10:44:47'),(96,'2019-08-14 10:44:47',0,'2acd34bbb81911e9806380fa5b3a283a','5a76e6b5b66511e985a680fa5b3a283a','2019-08-14 10:44:47'),(97,'2019-08-14 10:44:47',0,'2ad8136ab81911e9806380fa5b3a283a','5a76e6b5b66511e985a680fa5b3a283a','2019-08-14 10:44:47'),(98,'2019-11-28 14:46:33',0,'493b7b82c25211e98f5680fa5b3a283a','5a76e6b5b66511e985a680fa5b3a283a','2019-11-28 14:46:44');
/*!40000 ALTER TABLE `role_function` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_menu`
--

DROP TABLE IF EXISTS `system_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `system_menu` (
  `id` char(32) NOT NULL,
  `tab` varchar(100) NOT NULL DEFAULT '' COMMENT '标签页唯一标识',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT 'vue组件的name',
  `page_url` varchar(100) DEFAULT '' COMMENT '页面构造请求url',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `is_del` int(1) NOT NULL DEFAULT '0' COMMENT '是否删除：0 未删除 | 1 已删除',
  `pid` char(32) DEFAULT '' COMMENT '父id,一般是本表id',
  `data_url` varchar(100) DEFAULT '' COMMENT '数据请求url',
  `item_url` varchar(100) DEFAULT '' COMMENT '临时请求地址，改请求地址可以构建下拉列表树在页面上显示',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='配置系统菜单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_menu`
--

LOCK TABLES `system_menu` WRITE;
/*!40000 ALTER TABLE `system_menu` DISABLE KEYS */;
INSERT INTO `system_menu` VALUES ('1d7e7233132a11eaa3ed80fa5b3a283a','系统功能菜单','system-function','systemFunctionMenu','2019-11-30 12:31:16','2019-12-02 12:07:51',0,'','getSystemFunctionMenu',''),('493b7b82c25211e98f5680fa5b3a283a','功能操作配置','function-button-config','functionButton','2019-08-19 15:23:33','2019-12-02 12:48:44',0,'b8148d088b3411e99d7380fa5b3a283a','getFunctionButton','functionButtonTableView'),('99a6c4b38b4711e99d7380fa5b3a283a','数据字典详细配置','data-dictionary','dataDictionary','2019-06-10 14:18:43','2019-12-02 12:07:51',0,'c32e594b132a11eaa3ed80fa5b3a283a','getDataDictionary',''),('99b7ec5c8b4711e99d7380fa5b3a283a','系统监控','system-monitoring','systemMonitoring','2019-06-10 14:18:43','2019-12-02 12:07:51',0,'','',''),('a6c593c5c25211e98f5680fa5b3a283a','功能字段配置','function-columns-config','functionColumns','2019-08-19 15:26:10','2019-12-02 12:07:51',0,'b8148d088b3411e99d7380fa5b3a283a','getFunctionColumns','functionColumnsTableView'),('a6d31fd1c25211e98f5680fa5b3a283a','功能输入配置','function-input-config','functionInput','2019-08-19 15:26:10','2019-12-02 12:07:51',0,'b8148d088b3411e99d7380fa5b3a283a','getFunctionInput','functionInputTableView'),('b8148d088b3411e99d7380fa5b3a283a','权限功能管理','','','2019-06-10 12:03:33','2019-12-02 12:07:51',0,'','',''),('b82b1c928b3411e99d7380fa5b3a283a','角色配置','role-config','roleConfig','2019-06-10 12:03:34','2019-12-02 12:07:51',0,'b8148d088b3411e99d7380fa5b3a283a','getRoleConfig',''),('b83782508b3411e99d7380fa5b3a283a','用户角色配置','user-role-config','userRole','2019-06-10 12:03:34','2019-12-02 12:07:51',0,'b8148d088b3411e99d7380fa5b3a283a','getUserRole',''),('b83fc0ba8b3411e99d7380fa5b3a283a','角色功能配置','role-function-config','roleFunction','2019-06-10 12:03:34','2019-12-02 12:07:51',0,'b8148d088b3411e99d7380fa5b3a283a','getRoleFunction',''),('c32e594b132a11eaa3ed80fa5b3a283a','数据字典配置','','','2019-11-30 12:34:55','2019-12-02 12:07:51',0,'','',''),('ea32d48b132a11eaa3ed80fa5b3a283a','数据字典类型配置','type-dictionary','typeDictionary','2019-11-30 12:36:29','2019-12-02 14:16:49',0,'c32e594b132a11eaa3ed80fa5b3a283a','getTypeDictionary','');
/*!40000 ALTER TABLE `system_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type_dictionary`
--

DROP TABLE IF EXISTS `type_dictionary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `type_dictionary` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  `is_del` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type_dictionary`
--

LOCK TABLES `type_dictionary` WRITE;
/*!40000 ALTER TABLE `type_dictionary` DISABLE KEYS */;
INSERT INTO `type_dictionary` VALUES (1,'按钮样式','2019-08-30 11:14:10','2019-08-30 11:14:10',0),(2,'功能操作按钮','2019-08-30 11:25:39','2019-08-30 11:25:39',0),(3,'动作类型','2019-08-30 11:26:13','2019-08-30 11:26:13',0),(4,'对齐方式','2019-08-31 11:58:51','2019-12-02 15:14:08',0),(5,'输入类型','2019-08-31 12:05:44','2019-08-31 12:05:44',0),(6,'HTML类型','2019-08-31 12:06:09','2019-08-31 12:06:09',0),(7,'角色类型','2019-11-26 12:54:53','2019-11-26 12:54:56',0);
/*!40000 ALTER TABLE `type_dictionary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `is_del` int(1) NOT NULL DEFAULT '0',
  `name` varchar(100) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `uuid` char(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='用户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'2019-08-11 10:01:46',0,'user01','2019-08-11 10:01:46','fd8e53f0bbdb11e9878380fa5b3a283a'),(2,'2019-08-11 10:02:36',0,'user02','2019-08-11 10:02:36','1aec2b4abbdc11e9878380fa5b3a283a'),(3,'2019-08-11 10:02:36',0,'user03','2019-08-11 10:02:36','1afb960dbbdc11e9878380fa5b3a283a'),(4,'2019-08-11 10:02:36',0,'user04','2019-08-11 10:02:36','1b185cb6bbdc11e9878380fa5b3a283a'),(5,'2019-08-11 10:02:36',0,'user05','2019-08-11 10:02:36','1b24eaafbbdc11e9878380fa5b3a283a'),(6,'2019-08-11 10:02:36',0,'user06','2019-08-11 10:02:36','1b2f3989bbdc11e9878380fa5b3a283a'),(7,'2019-08-11 10:02:36',0,'user07','2019-08-11 10:02:36','1b362f40bbdc11e9878380fa5b3a283a'),(8,'2019-08-11 10:02:36',0,'user08','2019-08-11 10:02:36','1b3cb66bbbdc11e9878380fa5b3a283a'),(9,'2019-08-11 10:02:36',0,'user09','2019-08-11 10:02:36','1b4364ecbbdc11e9878380fa5b3a283a'),(10,'2019-08-11 10:02:36',0,'user10','2019-08-11 10:02:36','1b4a6620bbdc11e9878380fa5b3a283a'),(11,'2019-08-11 10:02:36',0,'user11','2019-08-11 10:02:36','1b5149ccbbdc11e9878380fa5b3a283a'),(12,'2019-08-11 10:02:36',0,'user12','2019-08-11 10:02:36','1b57dc38bbdc11e9878380fa5b3a283a'),(13,'2019-08-11 10:02:36',0,'user13','2019-08-11 10:02:36','1b5eb096bbdc11e9878380fa5b3a283a'),(14,'2019-08-11 10:02:36',0,'user14','2019-08-11 10:02:36','1b658841bbdc11e9878380fa5b3a283a'),(15,'2019-08-11 10:02:37',0,'user15','2019-08-11 10:02:37','1b6c2a93bbdc11e9878380fa5b3a283a');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `is_del` int(1) NOT NULL DEFAULT '0',
  `role_id` char(32) NOT NULL,
  `update_date` datetime DEFAULT NULL,
  `user_id` char(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='用户角色';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,'2019-08-11 11:13:16',0,'5a76e6b5b66511e985a680fa5b3a283a','2019-11-28 15:26:40','1aec2b4abbdc11e9878380fa5b3a283a'),(2,'2019-08-11 11:13:17',0,'e7c8354cbbde11e9878380fa5b3a283a','2019-12-02 13:50:46','1b362f40bbdc11e9878380fa5b3a283a'),(3,'2019-08-11 11:13:17',0,'bb6ec9de100a11eaa3ed80fa5b3a283a','2019-12-02 16:00:45','1b6c2a93bbdc11e9878380fa5b3a283a'),(4,'2019-08-11 11:13:17',0,'e7c8354cbbde11e9878380fa5b3a283a','2019-08-11 11:13:17','1b4a6620bbdc11e9878380fa5b3a283a'),(5,'2019-08-11 11:13:17',0,'e7c8354cbbde11e9878380fa5b3a283a','2019-08-11 11:13:17','1b24eaafbbdc11e9878380fa5b3a283a');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'sneaklifedb'
--

--
-- Dumping routines for database 'sneaklifedb'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-02 16:24:03
