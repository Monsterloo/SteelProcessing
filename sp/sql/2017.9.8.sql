/*
Navicat MySQL Data Transfer

Source Server         : lulu
Source Server Version : 50636
Source Host           : localhost:3306
Source Database       : db_productorder

Target Server Type    : MYSQL
Target Server Version : 50636
File Encoding         : 65001

Date: 2017-09-08 17:58:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_admin
-- ----------------------------
DROP TABLE IF EXISTS `tb_admin`;
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

-- ----------------------------
-- Records of tb_admin
-- ----------------------------
INSERT INTO `tb_admin` VALUES ('1', 'admin', 'admin', '1', 'it', 'winna', '13600000000', '1');
INSERT INTO `tb_admin` VALUES ('1589027c8a4411e7b5cec85b76aeb963', 'test', '111111', '1', 'test', 'test', '18100000000', '1');
INSERT INTO `tb_admin` VALUES ('316333ad8d5d11e7b5cec85b76aeb963', 'test1', '111111', '1', 'testtt', 'testtt', '18100000123', '1');
INSERT INTO `tb_admin` VALUES ('64c7f5eb87e311e7b5cec85b76aeb963', 'lulu', '123456', '1', 'IT', 'lulu', '18000000001', '1');
INSERT INTO `tb_admin` VALUES ('cabb83b1946111e7afb9c85b76aeb963', 'testAdmin', null, '0', null, 'testAdmin', '18660000000', '1');
INSERT INTO `tb_admin` VALUES ('db493c0a8d5c11e7b5cec85b76aeb963', 'test2', '111111', '1', 'testtt', 'testtt', '18100000123', '1');

-- ----------------------------
-- Table structure for tb_client
-- ----------------------------
DROP TABLE IF EXISTS `tb_client`;
CREATE TABLE `tb_client` (
  `C_ID` varchar(32) NOT NULL,
  `C_NAME` varchar(50) DEFAULT NULL COMMENT '客户公司名字',
  `C_CONTACT` varchar(20) DEFAULT NULL COMMENT '客户联系方式',
  `C_ADDRESS` varchar(100) DEFAULT NULL COMMENT '客户公司地址',
  `C_LEGALPERSON` varchar(10) DEFAULT NULL COMMENT '账户余额',
  `STATE` varchar(2) DEFAULT '1' COMMENT '记录状态',
  PRIMARY KEY (`C_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_client
-- ----------------------------
INSERT INTO `tb_client` VALUES ('15f841ac946611e7afb9c85b76aeb963', 'testClient1', '18000000123', '广东省广州市天河区正佳广场', 'lulu', '1');
INSERT INTO `tb_client` VALUES ('27faf4ea8c8311e7b5cec85b76aeb963', 'test', '020-123321123', 'where is it', 'ttttt', '1');
INSERT INTO `tb_client` VALUES ('3ea284418c6e11e7b5cec85b76aeb963', 'winna公司', '020-123321123', '我是哪儿', 'winniw', '1');
INSERT INTO `tb_client` VALUES ('f18dec03946411e7afb9c85b76aeb963', 'testClient', '18000000123', '广东省广州市天河区正佳广场', 'lululu', '1');

-- ----------------------------
-- Table structure for tb_material
-- ----------------------------
DROP TABLE IF EXISTS `tb_material`;
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

-- ----------------------------
-- Records of tb_material
-- ----------------------------
INSERT INTO `tb_material` VALUES ('05840891914211e7a3ecc85b76aeb963', '测试材料3', '8889', '88', '0', '888', '88', '8', '1');
INSERT INTO `tb_material` VALUES ('940a0242914111e7a3ecc85b76aeb963', '测试材料2', '888', '233', '0', '100', '100', '101', '1');
INSERT INTO `tb_material` VALUES ('f3cad0698d6311e7b5cec85b76aeb963', '测试材料1', '321', '111', '1', '100', '10', '101', '1');

-- ----------------------------
-- Table structure for tb_material_stock_out_record
-- ----------------------------
DROP TABLE IF EXISTS `tb_material_stock_out_record`;
CREATE TABLE `tb_material_stock_out_record` (
  `MR_ID` varchar(36) NOT NULL COMMENT '材料出库记录',
  PRIMARY KEY (`MR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_material_stock_out_record
-- ----------------------------

-- ----------------------------
-- Table structure for tb_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
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

-- ----------------------------
-- Records of tb_order
-- ----------------------------

-- ----------------------------
-- Table structure for tb_product
-- ----------------------------
DROP TABLE IF EXISTS `tb_product`;
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

-- ----------------------------
-- Records of tb_product
-- ----------------------------
INSERT INTO `tb_product` VALUES ('0394c2c6912511e7a3ecc85b76aeb963', '成品1比5', 'www.qq.com', '88', '1', '940a0242914111e7a3ecc85b76aeb963', '5', '1');
INSERT INTO `tb_product` VALUES ('330fc8b9914211e7a3ecc85b76aeb963', '成品测试3', 'www.jq22.com', '233', '3', '05840891914211e7a3ecc85b76aeb963', '3', '1');
INSERT INTO `tb_product` VALUES ('b42da9ff914111e7a3ecc85b76aeb963', '成品测试3', 'www.github.com', '199', '3', 'f3cad0698d6311e7b5cec85b76aeb963', '3', '1');
INSERT INTO `tb_product` VALUES ('c38226ba914111e7a3ecc85b76aeb963', '成品测试2', 'www.google.com', '123', '2', 'f3cad0698d6311e7b5cec85b76aeb963', '3', '1');
INSERT INTO `tb_product` VALUES ('d9d410cd914c11e7a3ecc85b76aeb963', '成品测试3-2', 'www.jq2222.com', '200', '2', '05840891914211e7a3ecc85b76aeb963', '3', '1');
INSERT INTO `tb_product` VALUES ('da44a3ce912411e7a3ecc85b76aeb963', '测试成品', 'www.baidu.com', '111', '1', '940a0242914111e7a3ecc85b76aeb963', '10', '1');

-- ----------------------------
-- Table structure for tb_steel_shape
-- ----------------------------
DROP TABLE IF EXISTS `tb_steel_shape`;
CREATE TABLE `tb_steel_shape` (
  `S_ID` varchar(32) NOT NULL COMMENT '钢筋形状主键',
  `IMAGE_URL` varchar(150) DEFAULT NULL COMMENT '图片路径',
  `FORMULA` varchar(150) DEFAULT NULL COMMENT '公式',
  `STATE` varchar(2) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`S_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_steel_shape
-- ----------------------------
INSERT INTO `tb_steel_shape` VALUES ('000010', '../assets/addimg/000010.jpg', '(A+B+C)*2', '1');
INSERT INTO `tb_steel_shape` VALUES ('000011', '../assets/addimg/000011.jpg', '(A+(B+C)/2)*2+10', '1');
INSERT INTO `tb_steel_shape` VALUES ('000012', '../assets/addimg/000012.jpg', 'A+B+C+D+12', '1');
INSERT INTO `tb_steel_shape` VALUES ('000013', '../assets/addimg/000013.jpg', 'A+B+C+D+10', '1');
INSERT INTO `tb_steel_shape` VALUES ('000020', '../assets/addimg/000020.jpg', 'A', '1');
INSERT INTO `tb_steel_shape` VALUES ('000021', '../assets/addimg/000021.jpg', '(A+B)/2', '1');
INSERT INTO `tb_steel_shape` VALUES ('000030', '../assets/addimg/000030.jpg', 'A+B+C', '1');
INSERT INTO `tb_steel_shape` VALUES ('000031', '../assets/addimg/000031.jpg', 'A+10', '1');
INSERT INTO `tb_steel_shape` VALUES ('000032', '../assets/addimg/000032.jpg', 'A+14', '1');
INSERT INTO `tb_steel_shape` VALUES ('000035', '../assets/addimg/000035.jpg', '(A+B)/2+14', '1');
INSERT INTO `tb_steel_shape` VALUES ('000040', '../assets/addimg/000040.jpg', 'A+B', '1');
INSERT INTO `tb_steel_shape` VALUES ('000041', '../assets/addimg/000041.jpg', 'A+B+C', '1');

-- ----------------------------
-- Table structure for tb_warehouse_record
-- ----------------------------
DROP TABLE IF EXISTS `tb_warehouse_record`;
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

-- ----------------------------
-- Records of tb_warehouse_record
-- ----------------------------
INSERT INTO `tb_warehouse_record` VALUES ('69850fd68ec811e7b5cec85b76aeb963', 'f3cad0698d6311e7b5cec85b76aeb963', '88888', '1', '1', '2017-09-01 11:45:01', '2017-09-01 11:45:01', '1');
INSERT INTO `tb_warehouse_record` VALUES ('a39f5ff88ebe11e7b5cec85b76aeb963', 'f3cad0698d6311e7b5cec85b76aeb963', '888', '1', '0', '2017-09-01 10:35:03', '2017-09-01 13:28:53', '1');
INSERT INTO `tb_warehouse_record` VALUES ('b8b63759-9225-11e7-a3ec-c85b76aeb963', 'f3cad0698d6311e7b5cec85b76aeb963', '123456', '1', '0', '2017-09-05 18:33:37', '2017-09-05 18:33:37', '1');
INSERT INTO `tb_warehouse_record` VALUES ('d87710a48ebd11e7b5cec85b76aeb963', 'f3cad0698d6311e7b5cec85b76aeb963', '888', '1', '1', '2017-09-01 10:29:22', '2017-09-01 11:28:59', '1');
INSERT INTO `tb_warehouse_record` VALUES ('fa659bea8ec111e7b5cec85b76aeb963', 'f3cad0698d6311e7b5cec85b76aeb963', '888', '1', '0', '2017-09-01 10:58:57', '2017-09-01 15:25:39', '1');
