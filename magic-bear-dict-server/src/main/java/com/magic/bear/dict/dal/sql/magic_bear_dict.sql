-- ----------------------------
-- Table structure for sys_app_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_app_info`;
CREATE TABLE `sys_app_info`  (
  `app_id` varchar(32) NOT NULL COMMENT 'app唯一标识',
  `app_description` varchar(255) NULL DEFAULT NULL COMMENT 'app描述',
  `remark` varchar(255) NULL DEFAULT NULL COMMENT '备注',
  `creator` varchar(32) NULL DEFAULT NULL COMMENT '创建者',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modifier` varchar(32) NULL DEFAULT NULL COMMENT '更新者',
  `modify_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`app_id`) USING BTREE
) COMMENT = 'app信息';

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data`  (
  `dict_data_id` int(11) NOT NULL,
  `dict_label` varchar(64) NOT NULL COMMENT '字典标签',
  `dict_value` varchar(64) NOT NULL COMMENT '字典键值',
  `dict_type` varchar(64) NOT NULL COMMENT '字典类型',
  `app_id` varchar(32) NOT NULL COMMENT '系统',
  `dict_sort` int(11) NULL DEFAULT NULL COMMENT '字典排序',
  `is_default` tinyint(4) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '是否默认（1-是 0-否）',
  `status` tinyint(4) NULL DEFAULT 1 COMMENT '状态（1-正常 0-停用）',
  `remark` varchar(255) NULL DEFAULT NULL COMMENT '备注',
  `creator` varchar(32) NULL DEFAULT NULL COMMENT '创建者',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modifier` varchar(32) NULL DEFAULT NULL COMMENT '更新者',
  `modify_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`dict_data_id`) USING BTREE
) COMMENT = '字典数据表' ;

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type`  (
  `dict_type_id` int(11) NOT NULL,
  `dict_type` varchar(64) NOT NULL COMMENT '字典类型',
  `dict_type_name` varchar(64) NOT NULL COMMENT '字典名称',
  `app_id` varchar(32) NOT NULL COMMENT '系统',
  `remark` varchar(255) NULL DEFAULT NULL COMMENT '备注',
  `creator` varchar(32) NULL DEFAULT NULL COMMENT '创建者',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modifier` varchar(32) NULL DEFAULT NULL COMMENT '更新者',
  `modify_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`dict_type_id`) USING BTREE
) COMMENT = '字典类型表' ;

-- ----------------------------
-- Table structure for sys_user_app
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_app`;
CREATE TABLE `sys_user_app`  (
  `id` int(11) NOT NULL,
  `app_id` varchar(32) NOT NULL COMMENT '系统',
  `user_id` int(11) NOT NULL COMMENT '用户',
  PRIMARY KEY (`id`) USING BTREE
) COMMENT = '用户app关联关系';

-- ----------------------------
-- Table structure for sys_user_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_info`;
CREATE TABLE `sys_user_info`  (
  `user_id` int(11) NOT NULL,
  `username` varchar(32) NOT NULL COMMENT '用户名',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `realName` varchar(32) NULL DEFAULT NULL COMMENT '真实姓名',
  `status` tinyint(4) NULL DEFAULT 1 COMMENT ' 状态（1-正常 0-停用）',
  `remark` varchar(255) NULL DEFAULT NULL COMMENT '备注',
  `creator` varchar(32) NULL DEFAULT NULL COMMENT '创建者',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modifier` varchar(32) NULL DEFAULT NULL COMMENT '更新者',
  `modify_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`user_id`) USING BTREE
) COMMENT = '用户信息';

