/*
 Navicat Premium Data Transfer

 Source Server         : gantch
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : nbiot

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 02/01/2020 18:06:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for nbiot_alarm_log
-- ----------------------------
DROP TABLE IF EXISTS `nbiot_alarm_log`;
CREATE TABLE `nbiot_alarm_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tenant_id` int(11) NULL DEFAULT NULL,
  `deviceId` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `timestamp` datetime(0) NULL DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `latitude` decimal(10, 6) NULL DEFAULT NULL,
  `longitude` decimal(10, 6) NULL DEFAULT NULL,
  `alarmType` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3673 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of nbiot_alarm_log
-- ----------------------------
INSERT INTO `nbiot_alarm_log` VALUES (3684, 1, '553a4c1d-939c-4073-92e7-8443dd6f0487', '2020-01-02 09:28:42', NULL, 0.000000, 0.000000, '烟雾报警');
INSERT INTO `nbiot_alarm_log` VALUES (3685, 1, '553a4c1d-939c-4073-92e7-8443dd6f0487', '2020-01-02 09:28:50', NULL, 0.000000, 0.000000, '烟雾报警');
INSERT INTO `nbiot_alarm_log` VALUES (3686, 1, '553a4c1d-939c-4073-92e7-8443dd6f0487', '2020-01-02 09:30:45', '烟雾报请问测试警', 0.000000, 0.000000, '烟雾报警');
INSERT INTO `nbiot_alarm_log` VALUES (3687, 1, '553a4c1d-939c-4073-92e7-8443dd6f0487', '2020-01-02 11:29:38', '测试哈', 0.000000, 0.000000, '烟雾报警');
INSERT INTO `nbiot_alarm_log` VALUES (3688, 1, '553a4c1d-939c-4073-92e7-8443dd6f0487', '2020-01-02 11:29:44', '测试哈', 0.000000, 0.000000, '烟雾报警');
INSERT INTO `nbiot_alarm_log` VALUES (3689, 1, '553a4c1d-939c-4073-92e7-8443dd6f0487', '2020-01-02 11:32:54', '测试哈', 0.000000, 0.000000, '烟雾报警');
INSERT INTO `nbiot_alarm_log` VALUES (3690, 1, '553a4c1d-939c-4073-92e7-8443dd6f0487', '2020-01-02 11:33:24', '测试哈', 0.000000, 0.000000, '烟雾报警');

-- ----------------------------
-- Table structure for nbiot_device
-- ----------------------------
DROP TABLE IF EXISTS `nbiot_device`;
CREATE TABLE `nbiot_device`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mac` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `deviceId` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `deviceType` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `model` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '出厂时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of nbiot_device
-- ----------------------------
INSERT INTO `nbiot_device` VALUES (24, '8677260327673220', 'smoke_detector', '553a4c1d-939c-4073-92e7-8443dd6f0487', '20', 'BC28', '2019-12-31 07:22:03');
INSERT INTO `nbiot_device` VALUES (25, '8677260317673220', 'smoke_detector', 'c99540d6-1d5c-4a4d-b3ca-cd28a433082c', '20', 'BC28', '2019-12-31 15:54:54');

-- ----------------------------
-- Table structure for nbiot_device_message
-- ----------------------------
DROP TABLE IF EXISTS `nbiot_device_message`;
CREATE TABLE `nbiot_device_message`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `device_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `customer_id` int(11) NULL DEFAULT NULL,
  `phone` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `status` int(3) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of nbiot_device_message
-- ----------------------------
INSERT INTO `nbiot_device_message` VALUES (23, '553a4c1d-939c-4073-92e7-8443dd6f0487', 100042, '18651470593', 3);
INSERT INTO `nbiot_device_message` VALUES (24, 'c99540d6-1d5c-4a4d-b3ca-cd28a433082c', 100042, '18651470593', 3);
INSERT INTO `nbiot_device_message` VALUES (25, '553a4c1d-939c-4073-92e7-8443dd6f0487', 100041, '13558695773', 3);

-- ----------------------------
-- Table structure for nbiot_device_relation
-- ----------------------------
DROP TABLE IF EXISTS `nbiot_device_relation`;
CREATE TABLE `nbiot_device_relation`  (
  `id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '从nbiot_device传来的设备ID',
  `mac` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '设备mac地址',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '设备名',
  `nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '设备昵称',
  `tenant_id` int(11) NULL DEFAULT NULL COMMENT '租户Id',
  `customer_id` int(11) NULL DEFAULT NULL COMMENT '绑定设备的用户ID',
  `device_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '设备类型',
  `model` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '模组信息',
  `device_group_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '设备组Id\r\n',
  `latitude` decimal(10, 0) NULL DEFAULT NULL COMMENT '经度',
  `longitude` decimal(10, 0) NULL DEFAULT NULL COMMENT '维度',
  `district` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '设备所在/区/县',
  `location` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '设备详细地址',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '用户设备创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of nbiot_device_relation
-- ----------------------------
INSERT INTO `nbiot_device_relation` VALUES ('553a4c1d-939c-4073-92e7-8443dd6f0487', '8677260327673220', 'smoke_detector', '测试哈', 1, 100040, '20', 'BC28', NULL, 0, 0, '顺义区', '北京市', '2020-01-02 11:20:17');

-- ----------------------------
-- Table structure for nbiot_group
-- ----------------------------
DROP TABLE IF EXISTS `nbiot_group`;
CREATE TABLE `nbiot_group`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `tenant_id` int(11) NULL DEFAULT NULL,
  `customer_id` int(11) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of nbiot_group
-- ----------------------------
INSERT INTO `nbiot_group` VALUES ('eb7449f3-4fa2-42be-ad16-2079272d0166', 1, 100042, '测试哈');

-- ----------------------------
-- Table structure for nbiot_group_relation
-- ----------------------------
DROP TABLE IF EXISTS `nbiot_group_relation`;
CREATE TABLE `nbiot_group_relation`  (
  `group_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `device_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_admin
-- ----------------------------
DROP TABLE IF EXISTS `user_admin`;
CREATE TABLE `user_admin`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tenant_id` int(20) NULL DEFAULT NULL COMMENT '租户ID',
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `icon` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` int(11) NULL DEFAULT NULL COMMENT '手机号码',
  `nick_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `note` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注信息',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `login_time` datetime(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  `status` int(1) NULL DEFAULT 1 COMMENT '帐号启用状态：0->禁用；1->启用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_admin
-- ----------------------------
INSERT INTO `user_admin` VALUES (1, 1, 'admin', '$2a$10$NZ5o7r2E.ayT2ZoxgjlI.eJ6OEYqjH7INR/F.mXDbjZJi9HF0YCVG', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO `user_admin` VALUES (2, 2, 'test', '$2a$10$n64SavPKVr38Lf2aByr.JuDgt4jIC9SfKQHOziooDkPjD0ZFwlnd2', NULL, NULL, NULL, '系统管理员', NULL, NULL, NULL, 1);
INSERT INTO `user_admin` VALUES (3, 2, 'admin22', '$2a$10$BL3bE64TeHM.MnaQSrRdS.2kV8WV/GGn5HC9BBagigK41EKe03tva', NULL, NULL, NULL, '系统管理员', NULL, NULL, NULL, 1);
INSERT INTO `user_admin` VALUES (4, 2, 'admin223', '$2a$10$cL.KcZydCGTqhim3wCd8MemgVn/zl1Akzd9zfxJABmMlMffHb/Poa', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO `user_admin` VALUES (5, 2, 'admin2223', '$2a$10$ZIGQ6lRuY83tKkhqwL7pq.jHqvy3GcRHDi7EVFCX7RWl.04l0YgoC', NULL, NULL, NULL, NULL, NULL, '2019-12-19 16:34:24', NULL, 1);
INSERT INTO `user_admin` VALUES (6, 2, 'admin22222', '$2a$10$Nqe/WRqtTfel3D0KJw2DBuecvP.ByRT4hA/kqxm/fHKY4Mf0NF2DO', NULL, NULL, NULL, '系统管理员', NULL, '2019-12-20 08:55:47', NULL, 1);

-- ----------------------------
-- Table structure for user_admin_login_log
-- ----------------------------
DROP TABLE IF EXISTS `user_admin_login_log`;
CREATE TABLE `user_admin_login_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin_id` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `ip` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `user_agent` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '浏览器登录类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 72 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '后台用户登录日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_admin_login_log
-- ----------------------------
INSERT INTO `user_admin_login_log` VALUES (18, 2, '2019-12-19 15:03:28', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `user_admin_login_log` VALUES (19, 2, '2019-12-19 15:04:27', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `user_admin_login_log` VALUES (20, 2, '2019-12-19 15:08:03', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `user_admin_login_log` VALUES (21, 2, '2019-12-19 15:32:43', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `user_admin_login_log` VALUES (22, 2, '2019-12-19 15:36:01', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `user_admin_login_log` VALUES (23, 2, '2019-12-19 15:39:08', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `user_admin_login_log` VALUES (24, 2, '2019-12-19 15:40:24', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `user_admin_login_log` VALUES (25, 2, '2019-12-19 15:40:24', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `user_admin_login_log` VALUES (26, 2, '2019-12-19 15:40:26', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `user_admin_login_log` VALUES (27, 2, '2019-12-19 15:41:10', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `user_admin_login_log` VALUES (28, 2, '2019-12-19 15:41:28', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `user_admin_login_log` VALUES (29, 4, '2019-12-19 16:19:44', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `user_admin_login_log` VALUES (30, 4, '2019-12-19 16:19:56', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `user_admin_login_log` VALUES (31, 4, '2019-12-19 16:21:16', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `user_admin_login_log` VALUES (32, 4, '2019-12-19 16:21:35', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `user_admin_login_log` VALUES (33, 5, '2019-12-19 16:34:57', '127.0.0.1', NULL, NULL);
INSERT INTO `user_admin_login_log` VALUES (34, 5, '2019-12-19 16:47:36', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `user_admin_login_log` VALUES (35, 5, '2019-12-19 16:53:00', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `user_admin_login_log` VALUES (36, 5, '2019-12-19 16:54:37', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `user_admin_login_log` VALUES (37, 5, '2019-12-19 17:01:11', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `user_admin_login_log` VALUES (38, 5, '2019-12-19 17:26:18', '127.0.0.1', NULL, NULL);
INSERT INTO `user_admin_login_log` VALUES (39, 4, '2019-12-20 08:36:26', '127.0.0.1', NULL, NULL);
INSERT INTO `user_admin_login_log` VALUES (40, 3, '2019-12-20 08:40:49', '127.0.0.1', NULL, NULL);
INSERT INTO `user_admin_login_log` VALUES (41, 3, '2019-12-20 08:41:51', '127.0.0.1', NULL, NULL);
INSERT INTO `user_admin_login_log` VALUES (42, 3, '2019-12-20 08:52:25', '127.0.0.1', NULL, NULL);
INSERT INTO `user_admin_login_log` VALUES (43, 3, '2019-12-20 08:54:25', '127.0.0.1', NULL, NULL);
INSERT INTO `user_admin_login_log` VALUES (44, 3, '2019-12-20 09:31:23', '127.0.0.1', NULL, NULL);
INSERT INTO `user_admin_login_log` VALUES (45, 3, '2019-12-20 10:02:41', '127.0.0.1', NULL, NULL);
INSERT INTO `user_admin_login_log` VALUES (46, 3, '2019-12-20 11:46:40', '127.0.0.1', NULL, NULL);
INSERT INTO `user_admin_login_log` VALUES (47, 6, '2019-12-21 10:34:04', '127.0.0.1', NULL, NULL);
INSERT INTO `user_admin_login_log` VALUES (48, 3, '2019-12-26 09:22:26', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `user_admin_login_log` VALUES (49, 3, '2019-12-26 09:23:06', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `user_admin_login_log` VALUES (50, 3, '2019-12-26 12:27:00', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `user_admin_login_log` VALUES (51, 3, '2019-12-26 12:28:54', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `user_admin_login_log` VALUES (52, 3, '2019-12-26 12:30:55', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `user_admin_login_log` VALUES (53, 3, '2019-12-26 12:30:55', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `user_admin_login_log` VALUES (54, 3, '2019-12-26 12:30:56', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `user_admin_login_log` VALUES (55, 3, '2019-12-26 12:30:57', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `user_admin_login_log` VALUES (56, 3, '2019-12-26 12:30:57', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `user_admin_login_log` VALUES (57, 3, '2019-12-26 12:30:58', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `user_admin_login_log` VALUES (58, 3, '2019-12-26 12:31:55', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `user_admin_login_log` VALUES (59, 3, '2019-12-26 13:49:00', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `user_admin_login_log` VALUES (60, 3, '2019-12-27 13:18:32', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `user_admin_login_log` VALUES (61, 3, '2019-12-27 14:07:34', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `user_admin_login_log` VALUES (62, 3, '2019-12-28 11:52:04', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `user_admin_login_log` VALUES (63, 3, '2019-12-28 15:56:58', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `user_admin_login_log` VALUES (64, 3, '2019-12-28 15:57:01', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `user_admin_login_log` VALUES (65, 3, '2019-12-28 15:57:02', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `user_admin_login_log` VALUES (66, 3, '2019-12-28 15:57:03', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `user_admin_login_log` VALUES (67, 3, '2019-12-28 15:57:04', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `user_admin_login_log` VALUES (68, 3, '2019-12-28 15:57:04', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `user_admin_login_log` VALUES (69, 3, '2019-12-30 10:02:20', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `user_admin_login_log` VALUES (70, 3, '2019-12-30 10:02:25', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `user_admin_login_log` VALUES (71, 3, '2019-12-30 10:02:26', '0:0:0:0:0:0:0:1', NULL, NULL);

-- ----------------------------
-- Table structure for user_admin_permission_relation
-- ----------------------------
DROP TABLE IF EXISTS `user_admin_permission_relation`;
CREATE TABLE `user_admin_permission_relation`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin_id` bigint(20) NULL DEFAULT NULL,
  `permission_id` bigint(20) NULL DEFAULT NULL,
  `type` int(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '后台用户和权限关系表(除角色中定义的权限以外的加减权限)' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_admin_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `user_admin_role_relation`;
CREATE TABLE `user_admin_role_relation`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin_id` bigint(20) NULL DEFAULT NULL,
  `role_id` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '后台用户和角色关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_admin_role_relation
-- ----------------------------
INSERT INTO `user_admin_role_relation` VALUES (13, 3, 1);
INSERT INTO `user_admin_role_relation` VALUES (15, 3, 2);
INSERT INTO `user_admin_role_relation` VALUES (16, 3, 4);

-- ----------------------------
-- Table structure for user_device_relation
-- ----------------------------
DROP TABLE IF EXISTS `user_device_relation`;
CREATE TABLE `user_device_relation`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `binder` int(11) NULL DEFAULT NULL COMMENT '被分享的人',
  `binded` int(11) NULL DEFAULT NULL COMMENT '设备组拥有者',
  `group_id` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_member
-- ----------------------------
DROP TABLE IF EXISTS `user_member`;
CREATE TABLE `user_member`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tenant_id` int(11) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `openid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100043 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_member
-- ----------------------------
INSERT INTO `user_member` VALUES (100039, 1, 'lcw33332', '123435678910', 'DE28EC0F41A43D19635D74250074403A', '123435678910', 'lcw33332@outlook.com', '12333', '2019-12-30 14:03:28');
INSERT INTO `user_member` VALUES (100040, 1, 'www', '18581599773', 'DE28EC0F41A43D19635D74250074403A', '18581599773', '123@outlook.com', '12333333', '2019-12-30 14:18:33');
INSERT INTO `user_member` VALUES (100041, 2, 'lcw333332', '13558695773', '202CB962AC59075B964B07152D234B70', '13558695773', 'lcw333332@outlook.com', '123333', '2019-12-30 14:34:41');
INSERT INTO `user_member` VALUES (100042, 2, 'lcw333332', '231', '202CB962AC59075B964B07152D234B70', '18651470593', 'lcw333332@outlook.com', '1233333', '2019-12-30 14:40:44');

-- ----------------------------
-- Table structure for user_permission
-- ----------------------------
DROP TABLE IF EXISTS `user_permission`;
CREATE TABLE `user_permission`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pid` bigint(20) NULL DEFAULT NULL COMMENT '父级权限id',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `value` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限值',
  `icon` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标',
  `uri` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '前端资源路径',
  `status` int(1) NULL DEFAULT NULL COMMENT '启用状态；0->禁用；1->启用',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '后台用户权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_permission
-- ----------------------------
INSERT INTO `user_permission` VALUES (1, 0, '商品', NULL, NULL, NULL, 1, '2018-09-29 16:15:14', 0);
INSERT INTO `user_permission` VALUES (2, 1, '商品列表', 'pms:product:read', NULL, '/pms/product/index', 1, '2018-09-29 16:17:01', 0);
INSERT INTO `user_permission` VALUES (3, 1, '添加商品', 'pms:product:create', NULL, '/pms/product/add', 1, '2018-09-29 16:18:51', 0);
INSERT INTO `user_permission` VALUES (4, 1, '商品分类', 'pms:productCategory:read', NULL, '/pms/productCate/index', 1, '2018-09-29 16:23:07', 0);
INSERT INTO `user_permission` VALUES (5, 1, '商品类型', 'pms:productAttribute:read', NULL, '/pms/productAttr/index', 1, '2018-09-29 16:24:43', 0);
INSERT INTO `user_permission` VALUES (6, 1, '品牌管理', 'pms:brand:read', NULL, '/pms/brand/index', 1, '2018-09-29 16:25:45', 0);
INSERT INTO `user_permission` VALUES (7, 2, '编辑商品', 'pms:product:update', NULL, '/pms/product/updateProduct', 1, '2018-09-29 16:34:23', 0);
INSERT INTO `user_permission` VALUES (8, 2, '删除商品', 'pms:product:delete', NULL, '/pms/product/delete', 1, '2018-09-29 16:38:33', 0);
INSERT INTO `user_permission` VALUES (9, 4, '添加商品分类', 'pms:productCategory:create', NULL, '/pms/productCate/create', 1, '2018-09-29 16:43:23', 0);
INSERT INTO `user_permission` VALUES (10, 4, '修改商品分类', 'pms:productCategory:update', NULL, '/pms/productCate/update', 1, '2018-09-29 16:43:55', 0);
INSERT INTO `user_permission` VALUES (11, 4, '删除商品分类', 'pms:productCategory:delete', NULL, '/pms/productAttr/delete', 1, '2018-09-29 16:44:38', 0);
INSERT INTO `user_permission` VALUES (12, 5, '添加商品类型', 'pms:productAttribute:create', NULL, '/pms/productAttr/create', 1, '2018-09-29 16:45:25', 0);
INSERT INTO `user_permission` VALUES (13, 5, '修改商品类型', 'pms:productAttribute:update', NULL, '/pms/productAttr/update', 1, '2018-09-29 16:48:08', 0);
INSERT INTO `user_permission` VALUES (14, 5, '删除商品类型', 'pms:productAttribute:delete', NULL, '/pms/productAttr/delete', 1, '2018-09-29 16:48:44', 0);
INSERT INTO `user_permission` VALUES (15, 6, '添加品牌', 'pms:brand:create', NULL, '/pms/brand/add', 1, '2018-09-29 16:49:34', 0);
INSERT INTO `user_permission` VALUES (16, 6, '修改品牌', 'pms:brand:update', NULL, '/pms/brand/update', 1, '2018-09-29 16:50:55', 0);
INSERT INTO `user_permission` VALUES (17, 6, '删除品牌', 'pms:brand:delete', NULL, '/pms/brand/delete', 1, '2018-09-29 16:50:59', 0);
INSERT INTO `user_permission` VALUES (18, 0, '首页', NULL, NULL, NULL, 1, '2018-09-29 16:51:57', 0);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `description` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `admin_count` int(11) NULL DEFAULT NULL COMMENT '后台用户数量',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `status` int(1) NULL DEFAULT 1 COMMENT '启用状态：0->禁用；1->启用',
  `sort` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '后台用户角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, '超级管理员', '超级管理员', 0, '2018-09-30 15:46:11', 1, 0);
INSERT INTO `user_role` VALUES (2, '租户管理员', '租户管理员', 0, '2018-09-30 15:53:45', 1, 0);
INSERT INTO `user_role` VALUES (3, '商品类型管理员', '商品类型管理员', 0, '2018-09-30 15:53:56', 1, 0);
INSERT INTO `user_role` VALUES (4, '品牌管理员', '品牌管理员', 0, '2018-09-30 15:54:12', 1, 0);

-- ----------------------------
-- Table structure for user_role_permission_relation
-- ----------------------------
DROP TABLE IF EXISTS `user_role_permission_relation`;
CREATE TABLE `user_role_permission_relation`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NULL DEFAULT NULL,
  `permission_id` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '后台用户角色和权限关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role_permission_relation
-- ----------------------------
INSERT INTO `user_role_permission_relation` VALUES (1, 1, 1);
INSERT INTO `user_role_permission_relation` VALUES (2, 1, 2);
INSERT INTO `user_role_permission_relation` VALUES (3, 1, 3);
INSERT INTO `user_role_permission_relation` VALUES (4, 1, 7);
INSERT INTO `user_role_permission_relation` VALUES (5, 1, 8);
INSERT INTO `user_role_permission_relation` VALUES (6, 2, 4);
INSERT INTO `user_role_permission_relation` VALUES (7, 2, 9);
INSERT INTO `user_role_permission_relation` VALUES (8, 2, 10);
INSERT INTO `user_role_permission_relation` VALUES (9, 2, 11);
INSERT INTO `user_role_permission_relation` VALUES (10, 3, 5);
INSERT INTO `user_role_permission_relation` VALUES (11, 3, 12);
INSERT INTO `user_role_permission_relation` VALUES (12, 3, 13);
INSERT INTO `user_role_permission_relation` VALUES (13, 3, 14);
INSERT INTO `user_role_permission_relation` VALUES (14, 4, 6);
INSERT INTO `user_role_permission_relation` VALUES (15, 4, 15);
INSERT INTO `user_role_permission_relation` VALUES (16, 4, 16);
INSERT INTO `user_role_permission_relation` VALUES (17, 4, 17);

-- ----------------------------
-- Table structure for user_tenant_relation
-- ----------------------------
DROP TABLE IF EXISTS `user_tenant_relation`;
CREATE TABLE `user_tenant_relation`  (
  `tenant_id` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `phone` int(11) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`tenant_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_tenant_relation
-- ----------------------------
INSERT INTO `user_tenant_relation` VALUES (1, '北京冠川', 'gckj2008@126.com', NULL, '公司主账号');

SET FOREIGN_KEY_CHECKS = 1;
