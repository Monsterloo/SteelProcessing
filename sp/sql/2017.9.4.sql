/*
Navicat MySQL Data Transfer

Source Server         : lulu
Source Server Version : 50636
Source Host           : localhost:3306
Source Database       : db_productorder

Target Server Type    : MYSQL
Target Server Version : 50636
File Encoding         : 65001

Date: 2017-09-04 16:10:01
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
INSERT INTO `tb_admin` VALUES ('64c7f5eb87e311e7b5cec85b76aeb963', 'lulu', '123456', '1', 'IT', 'lulu', '18000000000', '1');
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
INSERT INTO `tb_client` VALUES ('27faf4ea8c8311e7b5cec85b76aeb963', 'test', '020-123321123', 'where is it', 'ttttt', '1');
INSERT INTO `tb_client` VALUES ('3ea284418c6e11e7b5cec85b76aeb963', 'winna公司', '020-123321123', '我是哪儿', 'winniw', '1');

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
-- Table structure for tb_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order` (
  `O_ID` varchar(32) NOT NULL COMMENT '订单ID',
  `PROJECT_NAME` varchar(100) DEFAULT NULL COMMENT '项目名字',
  `C_ID` varchar(32) NOT NULL COMMENT '公司主键',
  `P_ID` varchar(32) NOT NULL COMMENT '产品主键',
  PRIMARY KEY (`O_ID`)
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
INSERT INTO `tb_product` VALUES ('0394c2c6912511e7a3ecc85b76aeb963', '成品1比5', 'www.qq.com', '88', '940a0242914111e7a3ecc85b76aeb963', '5', '1');
INSERT INTO `tb_product` VALUES ('330fc8b9914211e7a3ecc85b76aeb963', '成品测试3', 'www.jq22.com', '233', '05840891914211e7a3ecc85b76aeb963', '3', '1');
INSERT INTO `tb_product` VALUES ('b42da9ff914111e7a3ecc85b76aeb963', '成品测试3', 'www.github.com', '199', 'f3cad0698d6311e7b5cec85b76aeb963', '3', '1');
INSERT INTO `tb_product` VALUES ('c38226ba914111e7a3ecc85b76aeb963', '成品测试2', 'www.google.com', '123', 'f3cad0698d6311e7b5cec85b76aeb963', '3', '1');
INSERT INTO `tb_product` VALUES ('da44a3ce912411e7a3ecc85b76aeb963', '测试成品', 'www.baidu.com', '111', '940a0242914111e7a3ecc85b76aeb963', '10', '1');

-- ----------------------------
-- Table structure for tb_warehouse_record
-- ----------------------------
DROP TABLE IF EXISTS `tb_warehouse_record`;
CREATE TABLE `tb_warehouse_record` (
  `W_ID` varchar(32) NOT NULL COMMENT '入库主键',
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
INSERT INTO `tb_warehouse_record` VALUES ('a39f5ff88ebe11e7b5cec85b76aeb963', 'f3cad0698d6311e7b5cec85b76aeb963', '888', '1', '1', '2017-09-01 10:35:03', '2017-09-01 13:28:53', '1');
INSERT INTO `tb_warehouse_record` VALUES ('d87710a48ebd11e7b5cec85b76aeb963', 'f3cad0698d6311e7b5cec85b76aeb963', '888', '1', '1', '2017-09-01 10:29:22', '2017-09-01 11:28:59', '1');
INSERT INTO `tb_warehouse_record` VALUES ('fa659bea8ec111e7b5cec85b76aeb963', 'f3cad0698d6311e7b5cec85b76aeb963', '888', '1', '0', '2017-09-01 10:58:57', '2017-09-01 15:25:39', '1');
