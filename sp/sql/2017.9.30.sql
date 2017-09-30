/*
Navicat MySQL Data Transfer

Source Server         : lulu
Source Server Version : 50636
Source Host           : localhost:3306
Source Database       : db_productorder

Target Server Type    : MYSQL
Target Server Version : 50636
File Encoding         : 65001

Date: 2017-09-30 16:48:24
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
INSERT INTO `tb_admin` VALUES ('1', 'admin', 'admin', '1', 'it', 'winnaå', '13600000000', '1');
INSERT INTO `tb_admin` VALUES ('1589027c8a4411e7b5cec85b76aeb963', 'test', '111111', '1', 'test', 'test', '18100000000', '1');
INSERT INTO `tb_admin` VALUES ('1819df469aac11e79724966435dcafda', '啊啊啊啊啊啊啊12345678', 'admin123', '0', null, 'å12345678', '13610003422', '1');
INSERT INTO `tb_admin` VALUES ('316333ad8d5d11e7b5cec85b76aeb963', 'test1', '111111', '1', 'testtt', 'testtt', '18100000123', '1');
INSERT INTO `tb_admin` VALUES ('64c7f5eb87e311e7b5cec85b76aeb963', 'lulu', '123456', '1', 'IT', 'lulu', '18000000001', '1');
INSERT INTO `tb_admin` VALUES ('8066d30398f311e7acc0c85b76aeb963', 'testAdmin', 'admin', '1', null, '他她', '18000000001', '1');
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
INSERT INTO `tb_client` VALUES ('6d080fc29c5611e799f67f4d0226b51d', '啊啊啊啊啊12345678', '13910002344', '天津市-市辖区-河西区-啊啊啊啊', '啊啊啊啊', '1');
INSERT INTO `tb_client` VALUES ('f18dec03946411e7afb9c85b76aeb963', 'testClient', '18000000123', '广东省广州市天河区正佳广场', 'lululu', '1');

-- ----------------------------
-- Table structure for tb_material
-- ----------------------------
DROP TABLE IF EXISTS `tb_material`;
CREATE TABLE `tb_material` (
  `M_ID` varchar(32) NOT NULL COMMENT '材料主键',
  `M_NAME` varchar(30) DEFAULT NULL COMMENT '材料名字',
  `M_SOURCE` varchar(80) DEFAULT NULL COMMENT '材料来源',
  `M_PRICE` double(20,2) DEFAULT NULL COMMENT '材料单价',
  `M_EXIST_COUNT` int(10) DEFAULT NULL COMMENT '现存数量',
  `M_MIN_COUNT` int(10) DEFAULT NULL COMMENT '最低数量',
  `M_CATEGORY` int(2) DEFAULT NULL COMMENT '材料类别',
  `M_DIAMETER` double(30,2) DEFAULT NULL COMMENT '材料直径',
  `M_LENGTH` double(30,2) DEFAULT NULL COMMENT '材料长度',
  `M_WEIGHT` double(30,2) DEFAULT NULL COMMENT '材料重量',
  `STATE` varchar(2) DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`M_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_material
-- ----------------------------
INSERT INTO `tb_material` VALUES ('05840891914211e7a3ecc85b76aeb963', '测试材料3', 'source', '21.00', '9913', '23', '0', '888.00', '88.00', '8.00', '1');
INSERT INTO `tb_material` VALUES ('07e4c9a7991d11e7acc0c85b76aeb963', '新材料', '来源地', '12.00', '200', '66', '1', '1.00', '1.00', '1.00', '1');
INSERT INTO `tb_material` VALUES ('6ab5756a9ab311e79724966435dcafda', '啊啊啊test', '来源地1', '43.00', '149', '155', '1', '14.00', '14.00', '14.00', '1');
INSERT INTO `tb_material` VALUES ('940a0242914111e7a3ecc85b76aeb963', '测试材料2', '这是来源地!', '13.00', '888', '233', '0', '100.00', '100.00', '101.00', '1');
INSERT INTO `tb_material` VALUES ('f3cad0698d6311e7b5cec85b76aeb963', '测试材料1', '来源地!!!!', '22.00', '321', '111', '1', '100.00', '10.00', '101.00', '1');

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
  `A_ID` varchar(32) NOT NULL COMMENT '管理员主键',
  `DUE_DATE` datetime NOT NULL COMMENT '到期日期',
  `TOTAL_PRICE` double(50,0) DEFAULT NULL,
  `CREATETIME` datetime NOT NULL COMMENT '创建时间',
  `MODIFYTIME` datetime DEFAULT NULL COMMENT '修改时间',
  `ORDER_STATE` varchar(2) NOT NULL DEFAULT '1' COMMENT '订单状态',
  `STATE` varchar(2) NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`O_ID`),
  KEY `FK_CLIENT_CID` (`C_ID`),
  KEY `FK_ADMIN_AID` (`A_ID`),
  CONSTRAINT `FK_ADMIN_AID` FOREIGN KEY (`A_ID`) REFERENCES `tb_admin` (`A_id`),
  CONSTRAINT `FK_CLIENT_CID` FOREIGN KEY (`C_ID`) REFERENCES `tb_client` (`C_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_order
-- ----------------------------
INSERT INTO `tb_order` VALUES ('47ef542e-a1d7-11e7-9d08-c85b76aeb963', 'test', '3ea284418c6e11e7b5cec85b76aeb963', '1', '2017-09-25 17:52:01', null, '2017-09-25 17:52:02', '2017-09-25 17:52:02', '1', '1');

-- ----------------------------
-- Table structure for tb_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_detail`;
CREATE TABLE `tb_order_detail` (
  `D_ID` varchar(36) NOT NULL COMMENT '订单明细主键',
  `O_ID` varchar(36) DEFAULT NULL COMMENT '订单主键',
  `S_ID` varchar(32) DEFAULT NULL COMMENT '钢筋主键',
  `DIAMETRE` double(20,2) DEFAULT NULL COMMENT '钢筋半径',
  `PRICE` double(20,2) DEFAULT NULL COMMENT '钢筋单价',
  `STEEL_CATEGORY` varchar(2) DEFAULT NULL COMMENT '钢筋种类',
  `PARTS_LENGTH` varchar(50) DEFAULT NULL COMMENT '部件长度',
  `LENGTH` double(20,2) DEFAULT NULL COMMENT '总长度',
  `COUNT` int(10) DEFAULT NULL COMMENT '数量',
  `WEIGHT` double(20,2) DEFAULT NULL COMMENT '重量',
  `TOTAL_PRICE` double(10,2) DEFAULT NULL COMMENT '总价',
  `STATE` varchar(2) DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`D_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_order_detail
-- ----------------------------

-- ----------------------------
-- Table structure for tb_product
-- ----------------------------
DROP TABLE IF EXISTS `tb_product`;
CREATE TABLE `tb_product` (
  `P_ID` varchar(32) NOT NULL COMMENT '成品主键',
  `PRODUCT_NAME` varchar(50) DEFAULT NULL COMMENT '成品名称',
  `IMAGE_URL` varchar(255) DEFAULT NULL COMMENT '图片地址',
  `PRICE` double(20,2) DEFAULT NULL COMMENT '单价',
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
INSERT INTO `tb_product` VALUES ('0394c2c6912511e7a3ecc85b76aeb963', '成品1比5', 'www.qq.com', '88.00', '1', '940a0242914111e7a3ecc85b76aeb963', '5', '1');
INSERT INTO `tb_product` VALUES ('330fc8b9914211e7a3ecc85b76aeb963', '成品测试3', 'www.jq22.com', '233.00', '3', '05840891914211e7a3ecc85b76aeb963', '3', '1');
INSERT INTO `tb_product` VALUES ('b42da9ff914111e7a3ecc85b76aeb963', '成品测试3', 'www.github.com', '199.00', '3', 'f3cad0698d6311e7b5cec85b76aeb963', '3', '1');
INSERT INTO `tb_product` VALUES ('c38226ba914111e7a3ecc85b76aeb963', '成品测试2', 'www.google.com', '123.00', '2', 'f3cad0698d6311e7b5cec85b76aeb963', '3', '1');
INSERT INTO `tb_product` VALUES ('d9d410cd914c11e7a3ecc85b76aeb963', '成品测试3-2', 'www.jq2222.com', '200.00', '2', '05840891914211e7a3ecc85b76aeb963', '3', '1');
INSERT INTO `tb_product` VALUES ('da44a3ce912411e7a3ecc85b76aeb963', '测试成品', 'www.baidu.com', '111.00', '1', '940a0242914111e7a3ecc85b76aeb963', '10', '1');

-- ----------------------------
-- Table structure for tb_steel
-- ----------------------------
DROP TABLE IF EXISTS `tb_steel`;
CREATE TABLE `tb_steel` (
  `S_ID` varchar(32) NOT NULL COMMENT '钢筋形状主键',
  `IMAGE_URL` varchar(150) DEFAULT NULL COMMENT '图片路径',
  `FORMULA` varchar(150) DEFAULT NULL COMMENT '公式',
  `STATE` varchar(2) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`S_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_steel
-- ----------------------------
INSERT INTO `tb_steel` VALUES ('000010', '../assets/addimg/000010.jpg', '(A+B+C)*2', '1');
INSERT INTO `tb_steel` VALUES ('000011', '../assets/addimg/000011.jpg', '(A+(B+C)/2)*2+10', '1');
INSERT INTO `tb_steel` VALUES ('000012', '../assets/addimg/000012.jpg', 'A+B+C+D+12', '1');
INSERT INTO `tb_steel` VALUES ('000013', '../assets/addimg/000013.jpg', 'A+B+C+D+10', '1');
INSERT INTO `tb_steel` VALUES ('000020', '../assets/addimg/000020.jpg', 'A', '1');
INSERT INTO `tb_steel` VALUES ('000021', '../assets/addimg/000021.jpg', '(A+B)/2', '1');
INSERT INTO `tb_steel` VALUES ('000030', '../assets/addimg/000030.jpg', 'A+B+C', '1');
INSERT INTO `tb_steel` VALUES ('000031', '../assets/addimg/000031.jpg', 'A+10', '1');
INSERT INTO `tb_steel` VALUES ('000032', '../assets/addimg/000032.jpg', 'A+14', '1');
INSERT INTO `tb_steel` VALUES ('000035', '../assets/addimg/000035.jpg', '(A+B)/2+14', '1');
INSERT INTO `tb_steel` VALUES ('000040', '../assets/addimg/000040.jpg', 'A+B', '1');
INSERT INTO `tb_steel` VALUES ('000041', '../assets/addimg/000041.jpg', 'A+B+C', '1');

-- ----------------------------
-- Table structure for tb_warehouse_record
-- ----------------------------
DROP TABLE IF EXISTS `tb_warehouse_record`;
CREATE TABLE `tb_warehouse_record` (
  `W_ID` varchar(36) NOT NULL COMMENT '入库主键',
  `M_ID` varchar(32) DEFAULT NULL COMMENT '材料主键',
  `PURCHASE_COUNT` int(10) DEFAULT NULL COMMENT '采购数量',
  `MATERIAL_CATEGORY` int(2) DEFAULT NULL COMMENT '材料类型',
  `PRICE` double(20,2) DEFAULT '0.00' COMMENT '入库单价',
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
INSERT INTO `tb_warehouse_record` VALUES ('20e689c2-9b00-11e7-9724-966435dcafda', '6ab5756a9ab311e79724966435dcafda', '4', '1', '1.00', '0', '2017-09-17 00:57:31', '2017-09-17 00:57:31', '1');
INSERT INTO `tb_warehouse_record` VALUES ('40d82632-9ab0-11e7-9724-966435dcafda', '05840891914211e7a3ecc85b76aeb963', '1000', '0', '1.00', '1', '2017-09-16 15:25:45', '2017-09-16 15:26:44', '1');
INSERT INTO `tb_warehouse_record` VALUES ('45ee0cd8-9aae-11e7-9724-966435dcafda', '05840891914211e7a3ecc85b76aeb963', '12', '0', '1.00', '1', '2017-09-16 15:24:12', '2017-09-16 15:24:38', '1');
INSERT INTO `tb_warehouse_record` VALUES ('69850fd68ec811e7b5cec85b76aeb963', 'f3cad0698d6311e7b5cec85b76aeb963', '88888', '1', '1.00', '1', '2017-09-01 11:45:01', '2017-09-01 11:45:01', '1');
INSERT INTO `tb_warehouse_record` VALUES ('91c5f062-9c57-11e7-99f6-7f4d0226b51d', '6ab5756a9ab311e79724966435dcafda', '5', '1', '1.00', '1', '2017-09-18 17:55:57', '2017-09-24 00:01:53', '1');
INSERT INTO `tb_warehouse_record` VALUES ('a39f5ff88ebe11e7b5cec85b76aeb963', 'f3cad0698d6311e7b5cec85b76aeb963', '888', '1', '1.00', '0', '2017-09-01 10:35:03', '2017-09-01 13:28:53', '1');
INSERT INTO `tb_warehouse_record` VALUES ('b8b63759-9225-11e7-a3ec-c85b76aeb963', 'f3cad0698d6311e7b5cec85b76aeb963', '123456', '1', '1.00', '0', '2017-09-05 18:33:37', '2017-09-05 18:33:37', '1');
INSERT INTO `tb_warehouse_record` VALUES ('d87710a48ebd11e7b5cec85b76aeb963', 'f3cad0698d6311e7b5cec85b76aeb963', '888', '1', '1.00', '1', '2017-09-01 10:29:22', '2017-09-01 11:28:59', '1');
INSERT INTO `tb_warehouse_record` VALUES ('fa659bea8ec111e7b5cec85b76aeb963', 'f3cad0698d6311e7b5cec85b76aeb963', '888', '1', '1.00', '0', '2017-09-01 10:58:57', '2017-09-01 15:25:39', '1');
