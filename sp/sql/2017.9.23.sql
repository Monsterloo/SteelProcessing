CREATE DATABASE  IF NOT EXISTS `db_productorder` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `db_productorder`;
-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: 127.0.0.1    Database: db_productorder
-- ------------------------------------------------------
-- Server version	5.7.19

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
-- Table structure for table `tb_admin`
--

DROP TABLE IF EXISTS `tb_admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_admin` (
  `A_id` varchar(32) NOT NULL,
  `A_account` varchar(36) DEFAULT NULL COMMENT '账户名',
  `A_pwd` varchar(36) DEFAULT NULL COMMENT '密码',
  `A_role` varchar(5) DEFAULT NULL COMMENT '角色',
  `A_dept` varchar(36) DEFAULT NULL COMMENT '所属部门',
  `A_name` varchar(30) DEFAULT NULL COMMENT '管理员名字',
  `A_contact` varchar(20) DEFAULT NULL COMMENT '联系方式',
  `STATE` varchar(5) DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`A_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_admin`
--

LOCK TABLES `tb_admin` WRITE;
/*!40000 ALTER TABLE `tb_admin` DISABLE KEYS */;
INSERT INTO `tb_admin` VALUES ('1','admin','admin','1','it','winnaå','13600000000','1'),('1589027c8a4411e7b5cec85b76aeb963','test','111111','1','test','test','18100000000','1'),('1819df469aac11e79724966435dcafda','啊啊啊啊啊啊啊12345678','admin123','0',NULL,'å12345678','13610003422','1'),('316333ad8d5d11e7b5cec85b76aeb963','test1','111111','1','testtt','testtt','18100000123','1'),('64c7f5eb87e311e7b5cec85b76aeb963','lulu','123456','1','IT','lulu','18000000001','1'),('8066d30398f311e7acc0c85b76aeb963','testAdmin','admin','1',NULL,'他她','18000000001','1'),('db493c0a8d5c11e7b5cec85b76aeb963','test2','111111','1','testtt','testtt','18100000123','1');
/*!40000 ALTER TABLE `tb_admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_client`
--

DROP TABLE IF EXISTS `tb_client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_client` (
  `C_ID` varchar(32) NOT NULL,
  `C_NAME` varchar(50) DEFAULT NULL COMMENT '客户公司名字',
  `C_CONTACT` varchar(20) DEFAULT NULL COMMENT '客户联系方式',
  `C_ADDRESS` varchar(100) DEFAULT NULL COMMENT '客户公司地址',
  `C_LEGALPERSON` varchar(10) DEFAULT NULL COMMENT '账户余额',
  `STATE` varchar(2) DEFAULT '1' COMMENT '记录状态',
  PRIMARY KEY (`C_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_client`
--

LOCK TABLES `tb_client` WRITE;
/*!40000 ALTER TABLE `tb_client` DISABLE KEYS */;
INSERT INTO `tb_client` VALUES ('15f841ac946611e7afb9c85b76aeb963','testClient1','18000000123','广东省广州市天河区正佳广场','lulu','1'),('27faf4ea8c8311e7b5cec85b76aeb963','test','020-123321123','where is it','ttttt','1'),('3ea284418c6e11e7b5cec85b76aeb963','winna公司','020-123321123','我是哪儿','winniw','1'),('6d080fc29c5611e799f67f4d0226b51d','啊啊啊啊啊12345678','13910002344','天津市-市辖区-河西区-啊啊啊啊','啊啊啊啊','1'),('f18dec03946411e7afb9c85b76aeb963','testClient','18000000123','广东省广州市天河区正佳广场','lululu','1');
/*!40000 ALTER TABLE `tb_client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_material`
--

DROP TABLE IF EXISTS `tb_material`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_material` (
  `M_ID` varchar(32) NOT NULL COMMENT '材料主键',
  `M_NAME` varchar(30) DEFAULT NULL COMMENT '材料名字',
  `M_EXIST_COUNT` int(10) DEFAULT NULL COMMENT '现存数量',
  `M_MIN_COUNT` int(10) DEFAULT NULL COMMENT '最低数量',
  `M_CATEGORY` int(2) DEFAULT NULL COMMENT '材料类别',
  `M_DIAMETER` double(30,0) DEFAULT NULL COMMENT '材料直径',
  `M_LENGTH` double(30,0) DEFAULT NULL COMMENT '材料长度',
  `M_WEIGHT` double(30,0) DEFAULT NULL COMMENT '材料重量',
  `STATE` varchar(2) DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`M_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_material`
--

LOCK TABLES `tb_material` WRITE;
/*!40000 ALTER TABLE `tb_material` DISABLE KEYS */;
INSERT INTO `tb_material` VALUES ('05840891914211e7a3ecc85b76aeb963','测试材料3',9913,23,0,888,88,8,'0'),('07e4c9a7991d11e7acc0c85b76aeb963','新材料',200,66,1,1,1,1,'0'),('6ab5756a9ab311e79724966435dcafda','啊啊啊test',144,155,1,14,14,14,'1'),('940a0242914111e7a3ecc85b76aeb963','测试材料2',888,233,0,100,100,101,'1'),('f3cad0698d6311e7b5cec85b76aeb963','测试材料1',321,111,1,100,10,101,'1');
/*!40000 ALTER TABLE `tb_material` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_material_stock_out_record`
--

DROP TABLE IF EXISTS `tb_material_stock_out_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_material_stock_out_record` (
  `MR_ID` varchar(36) NOT NULL COMMENT '材料出库记录',
  PRIMARY KEY (`MR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_material_stock_out_record`
--

LOCK TABLES `tb_material_stock_out_record` WRITE;
/*!40000 ALTER TABLE `tb_material_stock_out_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_material_stock_out_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_order`
--

DROP TABLE IF EXISTS `tb_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_order` (
  `O_ID` varchar(36) NOT NULL COMMENT '订单ID',
  `PROJECT_NAME` varchar(100) DEFAULT NULL COMMENT '项目名字',
  `C_ID` varchar(32) NOT NULL COMMENT '公司主键',
  `S_ID` varchar(32) NOT NULL COMMENT '钢筋图主键',
  `A_ID` varchar(32) NOT NULL COMMENT '管理员主键',
  `PRICE` double(20,0) DEFAULT NULL COMMENT '单价',
  `COUNT` int(10) DEFAULT NULL COMMENT '数量',
  `DIAMETER` double(20,0) DEFAULT NULL COMMENT '钢筋直径',
  `DUE_DATE` datetime NOT NULL COMMENT '到期日期',
  `TOTAL_LENGTH` double(50,0) DEFAULT NULL COMMENT '总长度',
  `TOTAL_WEIGHT` double(50,0) DEFAULT NULL,
  `TOTAL_PRICE` double(50,0) DEFAULT NULL,
  `CREATETIME` datetime NOT NULL COMMENT '创建时间',
  `MODIFYTIME` datetime DEFAULT NULL COMMENT '修改时间',
  `ORDER_STATE` varchar(2) NOT NULL DEFAULT '1' COMMENT '订单状态',
  `STATE` varchar(2) NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`O_ID`),
  KEY `FK_CLIENT_CID` (`C_ID`),
  KEY `FK_ADMIN_AID` (`A_ID`),
  KEY `FK_STEEL_SHAPE_SID` (`S_ID`),
  CONSTRAINT `FK_ADMIN_AID` FOREIGN KEY (`A_ID`) REFERENCES `tb_admin` (`A_id`),
  CONSTRAINT `FK_CLIENT_CID` FOREIGN KEY (`C_ID`) REFERENCES `tb_client` (`C_ID`),
  CONSTRAINT `FK_STEEL_SHAPE_SID` FOREIGN KEY (`S_ID`) REFERENCES `tb_steel_shape` (`S_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_order`
--

LOCK TABLES `tb_order` WRITE;
/*!40000 ALTER TABLE `tb_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_product`
--

DROP TABLE IF EXISTS `tb_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_product` (
  `P_ID` varchar(32) NOT NULL COMMENT '成品主键',
  `PRODUCT_NAME` varchar(50) DEFAULT NULL COMMENT '成品名称',
  `IMAGE_URL` varchar(255) DEFAULT NULL COMMENT '图片地址',
  `PRICE` double(20,0) DEFAULT NULL COMMENT '单价',
  `PRODUCT_TYPE` int(2) DEFAULT NULL COMMENT '钢筋类型',
  `M_ID` varchar(32) DEFAULT NULL COMMENT '材料主键',
  `MATERIAL_COUNT` int(10) DEFAULT NULL COMMENT '材料数量',
  `STATE` varchar(2) DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`P_ID`),
  KEY `FK_PRODUCT_MID` (`M_ID`),
  CONSTRAINT `FK_PRODUCT_MID` FOREIGN KEY (`M_ID`) REFERENCES `tb_material` (`M_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_product`
--

LOCK TABLES `tb_product` WRITE;
/*!40000 ALTER TABLE `tb_product` DISABLE KEYS */;
INSERT INTO `tb_product` VALUES ('0394c2c6912511e7a3ecc85b76aeb963','成品1比5','www.qq.com',88,1,'940a0242914111e7a3ecc85b76aeb963',5,'1'),('330fc8b9914211e7a3ecc85b76aeb963','成品测试3','www.jq22.com',233,3,'05840891914211e7a3ecc85b76aeb963',3,'1'),('b42da9ff914111e7a3ecc85b76aeb963','成品测试3','www.github.com',199,3,'f3cad0698d6311e7b5cec85b76aeb963',3,'1'),('c38226ba914111e7a3ecc85b76aeb963','成品测试2','www.google.com',123,2,'f3cad0698d6311e7b5cec85b76aeb963',3,'1'),('d9d410cd914c11e7a3ecc85b76aeb963','成品测试3-2','www.jq2222.com',200,2,'05840891914211e7a3ecc85b76aeb963',3,'1'),('da44a3ce912411e7a3ecc85b76aeb963','测试成品','www.baidu.com',111,1,'940a0242914111e7a3ecc85b76aeb963',10,'1');
/*!40000 ALTER TABLE `tb_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_steel_shape`
--

DROP TABLE IF EXISTS `tb_steel_shape`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_steel_shape` (
  `S_ID` varchar(32) NOT NULL COMMENT '钢筋形状主键',
  `IMAGE_URL` varchar(150) DEFAULT NULL COMMENT '图片路径',
  `FORMULA` varchar(150) DEFAULT NULL COMMENT '公式',
  `STATE` varchar(2) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`S_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_steel_shape`
--

LOCK TABLES `tb_steel_shape` WRITE;
/*!40000 ALTER TABLE `tb_steel_shape` DISABLE KEYS */;
INSERT INTO `tb_steel_shape` VALUES ('000010','../assets/addimg/000010.jpg','(A+B+C)*2','1'),('000011','../assets/addimg/000011.jpg','(A+(B+C)/2)*2+10','1'),('000012','../assets/addimg/000012.jpg','A+B+C+D+12','1'),('000013','../assets/addimg/000013.jpg','A+B+C+D+10','1'),('000020','../assets/addimg/000020.jpg','A','1'),('000021','../assets/addimg/000021.jpg','(A+B)/2','1'),('000030','../assets/addimg/000030.jpg','A+B+C','1'),('000031','../assets/addimg/000031.jpg','A+10','1'),('000032','../assets/addimg/000032.jpg','A+14','1'),('000035','../assets/addimg/000035.jpg','(A+B)/2+14','1'),('000040','../assets/addimg/000040.jpg','A+B','1'),('000041','../assets/addimg/000041.jpg','A+B+C','1');
/*!40000 ALTER TABLE `tb_steel_shape` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_warehouse_record`
--

DROP TABLE IF EXISTS `tb_warehouse_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_warehouse_record` (
  `W_ID` varchar(36) NOT NULL COMMENT '入库主键',
  `M_ID` varchar(32) DEFAULT NULL COMMENT '材料主键',
  `PURCHASE_COUNT` int(10) DEFAULT NULL COMMENT '采购数量',
  `MATERIAL_CATEGORY` int(2) DEFAULT NULL COMMENT '材料类型',
  `PURCHASE_STATE` varchar(2) DEFAULT NULL COMMENT '采购状态',
  `CREATETIME` datetime NOT NULL COMMENT '创建时间',
  `MODIFYTIME` datetime NOT NULL COMMENT '修改时间',
  `STATE` varchar(2) DEFAULT '1' COMMENT '记录状态',
  PRIMARY KEY (`W_ID`),
  KEY `FK_WAREHOUSE_RECORD_MID` (`M_ID`),
  CONSTRAINT `FK_WAREHOUSE_RECORD_MID` FOREIGN KEY (`M_ID`) REFERENCES `tb_material` (`M_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_warehouse_record`
--

LOCK TABLES `tb_warehouse_record` WRITE;
/*!40000 ALTER TABLE `tb_warehouse_record` DISABLE KEYS */;
INSERT INTO `tb_warehouse_record` VALUES ('20e689c2-9b00-11e7-9724-966435dcafda','6ab5756a9ab311e79724966435dcafda',4,1,'0','2017-09-17 00:57:31','2017-09-17 00:57:31','1'),('40d82632-9ab0-11e7-9724-966435dcafda','05840891914211e7a3ecc85b76aeb963',1000,0,'1','2017-09-16 15:25:45','2017-09-16 15:26:44','1'),('45ee0cd8-9aae-11e7-9724-966435dcafda','05840891914211e7a3ecc85b76aeb963',12,0,'1','2017-09-16 15:24:12','2017-09-16 15:24:38','1'),('69850fd68ec811e7b5cec85b76aeb963','f3cad0698d6311e7b5cec85b76aeb963',88888,1,'1','2017-09-01 11:45:01','2017-09-01 11:45:01','1'),('91c5f062-9c57-11e7-99f6-7f4d0226b51d','6ab5756a9ab311e79724966435dcafda',5,1,'0','2017-09-18 17:55:57','2017-09-18 17:55:57','1'),('a39f5ff88ebe11e7b5cec85b76aeb963','f3cad0698d6311e7b5cec85b76aeb963',888,1,'0','2017-09-01 10:35:03','2017-09-01 13:28:53','1'),('b8b63759-9225-11e7-a3ec-c85b76aeb963','f3cad0698d6311e7b5cec85b76aeb963',123456,1,'0','2017-09-05 18:33:37','2017-09-05 18:33:37','1'),('d87710a48ebd11e7b5cec85b76aeb963','f3cad0698d6311e7b5cec85b76aeb963',888,1,'1','2017-09-01 10:29:22','2017-09-01 11:28:59','1'),('fa659bea8ec111e7b5cec85b76aeb963','f3cad0698d6311e7b5cec85b76aeb963',888,1,'0','2017-09-01 10:58:57','2017-09-01 15:25:39','1');
/*!40000 ALTER TABLE `tb_warehouse_record` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-09-23 22:02:51
