/*
 Navicat Premium Data Transfer

 Source Server         : Docker-MySQL
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : 127.0.0.1:3306
 Source Schema         : seata

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 22/01/2021 16:56:14
*/

SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for branch_table
-- ----------------------------
DROP TABLE IF EXISTS `branch_table`;
CREATE TABLE `branch_table`
(
    `branch_id`         bigint       NOT NULL,
    `xid`               varchar(128) NOT NULL,
    `transaction_id`    bigint        DEFAULT NULL,
    `resource_group_id` varchar(32)   DEFAULT NULL,
    `resource_id`       varchar(256)  DEFAULT NULL,
    `lock_key`          varchar(128)  DEFAULT NULL,
    `branch_type`       varchar(8)    DEFAULT NULL,
    `status`            tinyint       DEFAULT NULL,
    `client_id`         varchar(64)   DEFAULT NULL,
    `application_data`  varchar(2000) DEFAULT NULL,
    `gmt_create`        datetime      DEFAULT NULL,
    `gmt_modified`      datetime      DEFAULT NULL,
    PRIMARY KEY (`branch_id`),
    KEY                 `idx_xid` (`xid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of branch_table
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for global_table
-- ----------------------------
DROP TABLE IF EXISTS `global_table`;
CREATE TABLE `global_table`
(
    `xid`                       varchar(128) NOT NULL,
    `transaction_id`            bigint        DEFAULT NULL,
    `status`                    tinyint      NOT NULL,
    `application_id`            varchar(32)   DEFAULT NULL,
    `transaction_service_group` varchar(32)   DEFAULT NULL,
    `transaction_name`          varchar(128)  DEFAULT NULL,
    `timeout`                   int           DEFAULT NULL,
    `begin_time`                bigint        DEFAULT NULL,
    `application_data`          varchar(2000) DEFAULT NULL,
    `gmt_create`                datetime      DEFAULT NULL,
    `gmt_modified`              datetime      DEFAULT NULL,
    PRIMARY KEY (`xid`),
    KEY                         `idx_gmt_modified_status` (`gmt_modified`,`status`),
    KEY                         `idx_transaction_id` (`transaction_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of global_table
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for lock_table
-- ----------------------------
DROP TABLE IF EXISTS `lock_table`;
CREATE TABLE `lock_table`
(
    `row_key`        varchar(128) NOT NULL,
    `xid`            varchar(96)  DEFAULT NULL,
    `transaction_id` mediumtext,
    `branch_id`      mediumtext,
    `resource_id`    varchar(256) DEFAULT NULL,
    `table_name`     varchar(32)  DEFAULT NULL,
    `pk`             varchar(36)  DEFAULT NULL,
    `gmt_create`     datetime     DEFAULT NULL,
    `gmt_modified`   datetime     DEFAULT NULL,
    PRIMARY KEY (`row_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of lock_table
-- ----------------------------
BEGIN;
COMMIT;

SET
FOREIGN_KEY_CHECKS = 1;
