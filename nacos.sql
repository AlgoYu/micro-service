/*
 Navicat Premium Data Transfer

 Source Server         : Dev-MySQL
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : localhost:3306
 Source Schema         : nacos

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 14/04/2021 20:13:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for config_info
-- ----------------------------
DROP TABLE IF EXISTS `config_info`;
CREATE TABLE `config_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin COMMENT 'source user',
  `src_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'source ip',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  `c_desc` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `c_use` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `effect` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `type` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `c_schema` text CHARACTER SET utf8 COLLATE utf8_bin,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfo_datagrouptenant` (`data_id`,`group_id`,`tenant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=427 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info';

-- ----------------------------
-- Records of config_info
-- ----------------------------
BEGIN;
INSERT INTO `config_info` VALUES (1, 'MICRO-GENERATOR-SERVICE.yaml', 'DEV', 'spring:\n  # 允许bean覆盖\n  main:\n    allow-bean-definition-overriding: true\n  # 链路追踪\n  zipkin:\n    base-url: http://localhost:9411\n    sleuth:\n      sampler:\n        probability: 1\n  cloud:\n    # 注册中心\n    nacos:\n      discovery:\n        server-addr: 127.0.0.1:8848\n    # 熔断限流降级\n    sentinel:\n      transport:\n        dashboard: 127.0.0.1:8858\n        port: 8719\n    # 消息驱动\n    stream:\n      # 配置RabbitMQ 绑定器\n      binders:\n        rabbit:\n          type: rabbit\n          # RabbitMQ地址\n          environment:\n            spring:\n              rabbitmq:\n                host: localhost\n                port: 5672\n                username: guest\n                password: guest\n      # 配置输出通道\n      bindings:\n        output:\n          # 主题\n          destination: dev-message\n          # Binder\n          binder: rabbit\n          # 内容格式JSON\n          content-type: application/json\n  # 数据源\n  datasource:\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://127.0.0.1:3306/micro_service?useUnicode=true&useSSL=false&characterEncoding=utf8&allowPublicKeyRetrieval=true\n    username: root\n    password: 123456\n    # 连接池\n    druid:\n      # 最小空闲\n      min-idle: 5\n      # 最大存活连接\n      max-active: 20\n      # 初始化连接数\n      initial-size: 5\n      # 配置监控服务器\n      stat-view-servlet:\n        #是否允许开启监控\n        enabled: true\n        #是否允许重置监控数据\n        reset-enable: true\n        #druid访问路径\n        url-pattern: /druid/*\n      # 监控统计过滤器\n      filters: stat,wall,log4j2\n      # 检查关闭空闲连接的时间周期\n      time-between-eviction-runs-millis: 6000\n      # mergeSql，慢SQL记录\n      connect-properties: druid.state.mergeSql=true\n# 暴露端点\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n# Mybatis Plus\nmybatis-plus:\n  mapper-locations: classpath*:/mapper/*.xml\n  type-enums-package: cn.machine.geek.enums\n  configuration:\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\n# 代码生成器生成路径\n#generate-path: /Users/machine/Desktop/生成\n# 分布式事务配置\nseata:\n  tx-service-group: machine_geek\n  enable-auto-data-source-proxy: true\n  registry:\n    type: nacos\n    nacos:\n      server-addr: 127.0.0.1:8848\n      namespace:\n      group: SEATA_GROUP\n      application: SEATA-SERVER\n  config:\n    type: nacos\n    nacos:\n      server-addr: 127.0.0.1:8848\n      namespace:\n      group: SEATA_GROUP\n# 日志级别\nlogging:\n  level:\n    org.springframework.boot.autoconfigure.security: WARN', 'd1b61491ec89ab0c7ba8607be2894804', '2021-01-18 02:51:23', '2021-04-14 12:02:49', NULL, '172.100.0.1', '', '', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (4, 'MICRO-GATEWAY-SERVICE.yaml', 'DEV', 'spring:\n  # 允许bean覆盖\n  main:\n    allow-bean-definition-overriding: true\n  # 链路追踪\n  zipkin:\n    base-url: http://localhost:9411\n    sleuth:\n      sampler:\n        probability: 1\n  cloud:\n    # 注册中心\n    nacos:\n      discovery:\n        server-addr: 127.0.0.1:8848\n    # 熔断限流降级\n    sentinel:\n      transport:\n        dashboard: 127.0.0.1:8858\n        port: 8719\n    # 网关配置\n    gateway:\n      #解决跨域问题\n      globalcors:\n        corsConfigurations:\n          \'[/**]\':\n            # 允许携带认证信息\n            allow-credentials: true\n            # 允许跨域的源(网站域名/ip)，设置*为全部\n            allowedOrigins: \"*\"\n            # 允许跨域的method， 默认为GET和OPTIONS，设置*为全部\n            allowedMethods: \"*\"\n            # 允许跨域请求里的head字段，设置*为全部\n            allowedHeaders: \"*\"\n      #解决双重跨域 RETAIN_FIRST RETAIN_LAST RETAIN_UNIQUE\n      default-filters:\n        - DedupeResponseHeader=Access-Control-Allow-Origin Access-Control-Allow-Credentials Vary, RETAIN_UNIQUE\n      # 路由配置\n      routes:\n      # 网关转发测试\n      - id: Test\n        uri: https://weibo.com/\n        predicates:\n          - Path=/gateway\n      # 认证中心\n      - id: Auth\n        uri: lb://MICRO-AUTH-SERVICE\n        predicates:\n          - Path=/oauth/**\n      # 账号中心\n      - id: Center\n        uri: lb://MICRO-CENTER-SERVICE\n        predicates:\n          - Path=/account/**,/role/**,/authority/**\n      # 代码生成器\n      - id: CodeGenerator\n        uri: lb://MICRO-GENERATOR-SERVICE\n        predicates:\n          - Path=/generator/**\n      # 系统异常\n      - id: Auth\n        uri: lb://MICRO-EXCEPTION-SERVICE\n        predicates:\n          - Path=/systemException/**\n      # Nacos\n      - id: Nacos\n        uri: http://localhost:8848\n        predicates:\n          - Path=/nacos/**\n      # Sentinel\n      - id: Sentinel\n        uri: http://localhost:8858\n        predicates:\n          - Path=/sentinel/**\n        filters:\n          - StripPrefix=1\n      # Zipkin\n      - id: Zipkin\n        uri: http://localhost:9411\n        predicates:\n          - Path=/zipkin/**\n# 日志\nlogging:\n  level:\n    org.springframework.cloud.gateway: trace\n# 暴露端点\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n# 忽略URIS\nignoreUris:\n  - /oauth/', '9066b05b00073645165c0a107b6175b2', '2021-01-18 03:05:16', '2021-02-22 06:49:51', NULL, '172.100.0.1', '', '', 'Gateway网关配置。', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (15, 'MICRO-AUTH-SERVICE.yaml', 'DEV', '\nspring:\n  # 允许bean覆盖\n  main:\n    allow-bean-definition-overriding: true\n  # 链路追踪\n  zipkin:\n    base-url: http://localhost:9411\n    sleuth:\n      sampler:\n        probability: 1\n  cloud:\n    # 注册中心\n    nacos:\n      discovery:\n        server-addr: 127.0.0.1:8848\n    # 熔断限流降级\n    sentinel:\n      transport:\n        dashboard: 127.0.0.1:8858\n        port: 8719\n  # 数据源\n  datasource:\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://127.0.0.1:3306/micro_service?useUnicode=true&useSSL=false&characterEncoding=utf8&allowPublicKeyRetrieval=true\n    username: root\n    password: 123456\n    # 连接池\n    druid:\n      # 最小空闲\n      min-idle: 5\n      # 最大存活连接\n      max-active: 20\n      # 初始化连接数\n      initial-size: 5\n      # 配置监控服务器\n      stat-view-servlet:\n        #是否允许开启监控\n        enabled: true\n        #是否允许重置监控数据\n        reset-enable: true\n        #druid访问路径\n        url-pattern: /druid/*\n      # 监控统计过滤器\n      filters: stat,wall,log4j2\n      # 检查关闭空闲连接的时间周期\n      time-between-eviction-runs-millis: 6000\n      # mergeSql，慢SQL记录\n      connect-properties: druid.state.mergeSql=true\n# 暴露端点\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n# Mybatis Plus\nmybatis-plus:\n  mapper-locations: classpath*:/mapper/*.xml\n  type-enums-package: cn.machine.geek.enums\n  configuration:\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl', 'ee4d73e45b6df5b38ea107d87358c928', '2021-01-18 09:41:05', '2021-01-31 08:42:03', NULL, '172.100.0.1', '', '', '认证中心配置', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (27, 'MICRO-CENTER-SERVICE.yaml', 'DEV', 'spring:\n  # 排除自动配置\n  autoconfigure:\n    exclude: org.springframework.cloud.security.oauth2.SpringCloudSecurityAutoConfiguration\n  # 允许bean覆盖\n  main:\n    allow-bean-definition-overriding: true\n  # 链路追踪\n  zipkin:\n    base-url: http://localhost:9411\n    sleuth:\n      sampler:\n        probability: 1\n  cloud:\n    # 注册中心\n    nacos:\n      discovery:\n        server-addr: 127.0.0.1:8848\n    # 熔断限流降级\n    sentinel:\n      transport:\n        dashboard: 127.0.0.1:8858\n        port: 8719\n    # 消息驱动\n    stream:\n      # 配置RabbitMQ 绑定器\n      binders:\n        rabbit:\n          type: rabbit\n          # RabbitMQ地址\n          environment:\n            spring:\n              rabbitmq:\n                host: localhost\n                port: 5672\n                username: guest\n                password: guest\n      # 配置输出通道\n      bindings:\n        output:\n          # 主题\n          destination: dev-message\n          # Binder\n          binder: rabbit\n          # 内容格式JSON\n          content-type: application/json\n  # 数据源\n  datasource:\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://127.0.0.1:3306/micro_service?useUnicode=true&useSSL=false&characterEncoding=utf8&allowPublicKeyRetrieval=true\n    username: root\n    password: 123456\n    # 连接池\n    druid:\n      # 最小空闲\n      min-idle: 5\n      # 最大存活连接\n      max-active: 20\n      # 初始化连接数\n      initial-size: 5\n      # 配置监控服务器\n      stat-view-servlet:\n        #是否允许开启监控\n        enabled: true\n        #是否允许重置监控数据\n        reset-enable: true\n        #druid访问路径\n        url-pattern: /druid/*\n      # 监控统计过滤器\n      filters: stat,wall,log4j2\n      # 检查关闭空闲连接的时间周期\n      time-between-eviction-runs-millis: 6000\n      # mergeSql，慢SQL记录\n      connect-properties: druid.state.mergeSql=true\n# 暴露端点\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n# Mybatis Plus\nmybatis-plus:\n  mapper-locations: classpath*:/mapper/*.xml\n  type-enums-package: cn.machine.geek.enums\n  configuration:\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\n# 分布式事务配置\nseata:\n  tx-service-group: machine_geek\n  enable-auto-data-source-proxy: true\n  registry:\n    type: nacos\n    nacos:\n      server-addr: 127.0.0.1:8848\n      namespace:\n      group: SEATA_GROUP\n      application: SEATA-SERVER\n  config:\n    type: nacos\n    nacos:\n      server-addr: 127.0.0.1:8848\n      namespace:\n      group: SEATA_GROUP\n# 日志级别\nlogging:\n  level:\n    org.springframework.boot.autoconfigure.security: WARN', '88c33cde30389823e3c3cf2c32985832', '2021-01-20 06:57:35', '2021-01-31 09:11:14', NULL, '172.100.0.1', '', '', '中心服务配置', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (306, 'transport.type', 'SEATA_GROUP', 'TCP', 'b136ef5f6a01d816991fe3cf7a6ac763', '2021-01-21 09:18:04', '2021-01-21 09:18:04', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (307, 'transport.server', 'SEATA_GROUP', 'NIO', 'b6d9dfc0fb54277321cebc0fff55df2f', '2021-01-21 09:18:04', '2021-01-21 09:18:04', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (308, 'transport.heartbeat', 'SEATA_GROUP', 'true', 'b326b5062b2f0e69046810717534cb09', '2021-01-21 09:18:04', '2021-01-21 09:18:04', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (309, 'transport.enableClientBatchSendRequest', 'SEATA_GROUP', 'false', '68934a3e9455fa72420237eb05902327', '2021-01-21 09:18:04', '2021-01-21 09:18:04', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (310, 'transport.threadFactory.bossThreadPrefix', 'SEATA_GROUP', 'NettyBoss', '0f8db59a3b7f2823f38a70c308361836', '2021-01-21 09:18:04', '2021-01-21 09:18:04', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (311, 'transport.threadFactory.workerThreadPrefix', 'SEATA_GROUP', 'NettyServerNIOWorker', 'a78ec7ef5d1631754c4e72ae8a3e9205', '2021-01-21 09:18:04', '2021-01-21 09:18:04', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (312, 'transport.threadFactory.serverExecutorThreadPrefix', 'SEATA_GROUP', 'NettyServerBizHandler', '11a36309f3d9df84fa8b59cf071fa2da', '2021-01-21 09:18:04', '2021-01-21 09:18:04', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (313, 'transport.threadFactory.shareBossWorker', 'SEATA_GROUP', 'false', '68934a3e9455fa72420237eb05902327', '2021-01-21 09:18:04', '2021-01-21 09:18:04', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (314, 'transport.threadFactory.clientSelectorThreadPrefix', 'SEATA_GROUP', 'NettyClientSelector', 'cd7ec5a06541e75f5a7913752322c3af', '2021-01-21 09:18:04', '2021-01-21 09:18:04', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (315, 'transport.threadFactory.clientSelectorThreadSize', 'SEATA_GROUP', '1', 'c4ca4238a0b923820dcc509a6f75849b', '2021-01-21 09:18:04', '2021-01-21 09:18:04', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (316, 'transport.threadFactory.clientWorkerThreadPrefix', 'SEATA_GROUP', 'NettyClientWorkerThread', '61cf4e69a56354cf72f46dc86414a57e', '2021-01-21 09:18:04', '2021-01-21 09:18:04', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (317, 'transport.threadFactory.bossThreadSize', 'SEATA_GROUP', '1', 'c4ca4238a0b923820dcc509a6f75849b', '2021-01-21 09:18:04', '2021-01-21 09:18:04', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (318, 'transport.threadFactory.workerThreadSize', 'SEATA_GROUP', 'default', 'c21f969b5f03d33d43e04f8f136e7682', '2021-01-21 09:18:04', '2021-01-21 09:18:04', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (319, 'transport.shutdown.wait', 'SEATA_GROUP', '3', 'eccbc87e4b5ce2fe28308fd9f2a7baf3', '2021-01-21 09:18:04', '2021-01-21 09:18:04', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (320, 'service.vgroupMapping.machine_geek', 'SEATA_GROUP', 'default', 'c21f969b5f03d33d43e04f8f136e7682', '2021-01-21 09:18:04', '2021-01-21 09:18:04', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (321, 'service.default.grouplist', 'SEATA_GROUP', '127.0.0.1:8091', 'c32ce0d3e264525dcdada751f98143a3', '2021-01-21 09:18:04', '2021-01-22 06:30:36', NULL, '172.100.0.1', '', '', '', '', '', '', '');
INSERT INTO `config_info` VALUES (322, 'service.enableDegrade', 'SEATA_GROUP', 'false', '68934a3e9455fa72420237eb05902327', '2021-01-21 09:18:04', '2021-01-21 09:18:04', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (323, 'service.disableGlobalTransaction', 'SEATA_GROUP', 'false', '68934a3e9455fa72420237eb05902327', '2021-01-21 09:18:04', '2021-01-21 09:18:04', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (324, 'client.rm.asyncCommitBufferLimit', 'SEATA_GROUP', '10000', 'b7a782741f667201b54880c925faec4b', '2021-01-21 09:18:04', '2021-01-21 09:18:04', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (325, 'client.rm.lock.retryInterval', 'SEATA_GROUP', '10', 'd3d9446802a44259755d38e6d163e820', '2021-01-21 09:18:04', '2021-01-21 09:18:04', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (326, 'client.rm.lock.retryTimes', 'SEATA_GROUP', '30', '34173cb38f07f89ddbebc2ac9128303f', '2021-01-21 09:18:04', '2021-01-21 09:18:04', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (327, 'client.rm.lock.retryPolicyBranchRollbackOnConflict', 'SEATA_GROUP', 'true', 'b326b5062b2f0e69046810717534cb09', '2021-01-21 09:18:04', '2021-01-21 09:18:04', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (328, 'client.rm.reportRetryCount', 'SEATA_GROUP', '5', 'e4da3b7fbbce2345d7772b0674a318d5', '2021-01-21 09:18:04', '2021-01-21 09:18:04', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (329, 'client.rm.tableMetaCheckEnable', 'SEATA_GROUP', 'false', '68934a3e9455fa72420237eb05902327', '2021-01-21 09:18:04', '2021-01-21 09:18:04', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (330, 'client.rm.tableMetaCheckerInterval', 'SEATA_GROUP', '60000', '2b4226dd7ed6eb2d419b881f3ae9c97c', '2021-01-21 09:18:04', '2021-01-21 09:18:04', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (331, 'client.rm.sqlParserType', 'SEATA_GROUP', 'druid', '3d650fb8a5df01600281d48c47c9fa60', '2021-01-21 09:18:04', '2021-01-21 09:18:04', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (332, 'client.rm.reportSuccessEnable', 'SEATA_GROUP', 'false', '68934a3e9455fa72420237eb05902327', '2021-01-21 09:18:04', '2021-01-21 09:18:04', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (333, 'client.rm.sagaBranchRegisterEnable', 'SEATA_GROUP', 'false', '68934a3e9455fa72420237eb05902327', '2021-01-21 09:18:04', '2021-01-21 09:18:04', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (334, 'client.tm.commitRetryCount', 'SEATA_GROUP', '5', 'e4da3b7fbbce2345d7772b0674a318d5', '2021-01-21 09:18:04', '2021-01-21 09:18:04', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (335, 'client.tm.rollbackRetryCount', 'SEATA_GROUP', '5', 'e4da3b7fbbce2345d7772b0674a318d5', '2021-01-21 09:18:04', '2021-01-21 09:18:04', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (336, 'client.tm.defaultGlobalTransactionTimeout', 'SEATA_GROUP', '60000', '2b4226dd7ed6eb2d419b881f3ae9c97c', '2021-01-21 09:18:04', '2021-01-21 09:18:04', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (337, 'client.tm.degradeCheck', 'SEATA_GROUP', 'false', '68934a3e9455fa72420237eb05902327', '2021-01-21 09:18:05', '2021-01-21 09:18:05', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (338, 'client.tm.degradeCheckAllowTimes', 'SEATA_GROUP', '10', 'd3d9446802a44259755d38e6d163e820', '2021-01-21 09:18:05', '2021-01-21 09:18:05', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (339, 'client.tm.degradeCheckPeriod', 'SEATA_GROUP', '2000', '08f90c1a417155361a5c4b8d297e0d78', '2021-01-21 09:18:05', '2021-01-21 09:18:05', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (340, 'store.mode', 'SEATA_GROUP', 'db', 'd77d5e503ad1439f585ac494268b351b', '2021-01-21 09:18:05', '2021-01-21 09:18:05', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (341, 'store.file.dir', 'SEATA_GROUP', 'file_store/data', '6a8dec07c44c33a8a9247cba5710bbb2', '2021-01-21 09:18:05', '2021-01-21 09:18:05', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (342, 'store.file.maxBranchSessionSize', 'SEATA_GROUP', '16384', 'c76fe1d8e08462434d800487585be217', '2021-01-21 09:18:05', '2021-01-21 09:18:05', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (343, 'store.file.maxGlobalSessionSize', 'SEATA_GROUP', '512', '10a7cdd970fe135cf4f7bb55c0e3b59f', '2021-01-21 09:18:05', '2021-01-21 09:18:05', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (344, 'store.file.fileWriteBufferCacheSize', 'SEATA_GROUP', '16384', 'c76fe1d8e08462434d800487585be217', '2021-01-21 09:18:05', '2021-01-21 09:18:05', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (345, 'store.file.flushDiskMode', 'SEATA_GROUP', 'async', '0df93e34273b367bb63bad28c94c78d5', '2021-01-21 09:18:05', '2021-01-21 09:18:05', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (346, 'store.file.sessionReloadReadSize', 'SEATA_GROUP', '100', 'f899139df5e1059396431415e770c6dd', '2021-01-21 09:18:05', '2021-01-21 09:18:05', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (347, 'store.db.datasource', 'SEATA_GROUP', 'druid', '3d650fb8a5df01600281d48c47c9fa60', '2021-01-21 09:18:05', '2021-01-21 09:18:05', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (348, 'store.db.dbType', 'SEATA_GROUP', 'mysql', '81c3b080dad537de7e10e0987a4bf52e', '2021-01-21 09:18:05', '2021-01-21 09:18:05', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (349, 'store.db.driverClassName', 'SEATA_GROUP', 'com.mysql.cj.jdbc.Driver', '33763409bb7f4838bde4fae9540433e4', '2021-01-21 09:18:05', '2021-01-21 09:21:39', NULL, '172.100.0.1', '', '', '', '', '', '', '');
INSERT INTO `config_info` VALUES (350, 'store.db.url', 'SEATA_GROUP', 'jdbc:mysql://127.0.0.1:3306/seata?useUnicode=true&rewriteBatchedStatements=true', '030ea5ff5c2ef043adf9826c70570b0b', '2021-01-21 09:18:05', '2021-01-22 06:56:05', NULL, '172.100.0.1', '', '', '', '', '', '', '');
INSERT INTO `config_info` VALUES (351, 'store.db.user', 'SEATA_GROUP', 'root', '63a9f0ea7bb98050796b649e85481845', '2021-01-21 09:18:05', '2021-01-21 09:18:05', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (352, 'store.db.password', 'SEATA_GROUP', '123456', 'e10adc3949ba59abbe56e057f20f883e', '2021-01-21 09:18:05', '2021-01-21 09:18:05', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (353, 'store.db.minConn', 'SEATA_GROUP', '5', 'e4da3b7fbbce2345d7772b0674a318d5', '2021-01-21 09:18:05', '2021-01-21 09:18:05', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (354, 'store.db.maxConn', 'SEATA_GROUP', '30', '34173cb38f07f89ddbebc2ac9128303f', '2021-01-21 09:18:05', '2021-01-21 09:18:05', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (355, 'store.db.globalTable', 'SEATA_GROUP', 'global_table', '8b28fb6bb4c4f984df2709381f8eba2b', '2021-01-21 09:18:05', '2021-01-21 09:18:05', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (356, 'store.db.branchTable', 'SEATA_GROUP', 'branch_table', '54bcdac38cf62e103fe115bcf46a660c', '2021-01-21 09:18:05', '2021-01-21 09:18:05', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (357, 'store.db.queryLimit', 'SEATA_GROUP', '100', 'f899139df5e1059396431415e770c6dd', '2021-01-21 09:18:05', '2021-01-21 09:18:05', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (358, 'store.db.lockTable', 'SEATA_GROUP', 'lock_table', '55e0cae3b6dc6696b768db90098b8f2f', '2021-01-21 09:18:05', '2021-01-21 09:18:05', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (359, 'store.db.maxWait', 'SEATA_GROUP', '5000', 'a35fe7f7fe8217b4369a0af4244d1fca', '2021-01-21 09:18:05', '2021-01-21 09:18:05', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (360, 'store.redis.mode', 'SEATA_GROUP', 'single', 'dd5c07036f2975ff4bce568b6511d3bc', '2021-01-21 09:18:05', '2021-01-21 09:18:05', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (361, 'store.redis.single.host', 'SEATA_GROUP', '127.0.0.1', 'f528764d624db129b32c21fbca0cb8d6', '2021-01-21 09:18:05', '2021-01-21 09:18:05', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (362, 'store.redis.single.port', 'SEATA_GROUP', '6379', '92c3b916311a5517d9290576e3ea37ad', '2021-01-21 09:18:05', '2021-01-21 09:18:05', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (363, 'store.redis.maxConn', 'SEATA_GROUP', '10', 'd3d9446802a44259755d38e6d163e820', '2021-01-21 09:18:05', '2021-01-21 09:18:05', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (364, 'store.redis.minConn', 'SEATA_GROUP', '1', 'c4ca4238a0b923820dcc509a6f75849b', '2021-01-21 09:18:05', '2021-01-21 09:18:05', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (365, 'store.redis.maxTotal', 'SEATA_GROUP', '100', 'f899139df5e1059396431415e770c6dd', '2021-01-21 09:18:05', '2021-01-21 09:18:05', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (366, 'store.redis.database', 'SEATA_GROUP', '0', 'cfcd208495d565ef66e7dff9f98764da', '2021-01-21 09:18:05', '2021-01-21 09:18:05', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (367, 'store.redis.queryLimit', 'SEATA_GROUP', '100', 'f899139df5e1059396431415e770c6dd', '2021-01-21 09:18:05', '2021-01-21 09:18:05', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (368, 'server.recovery.committingRetryPeriod', 'SEATA_GROUP', '1000', 'a9b7ba70783b617e9998dc4dd82eb3c5', '2021-01-21 09:18:05', '2021-01-21 09:18:05', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (369, 'server.recovery.asynCommittingRetryPeriod', 'SEATA_GROUP', '1000', 'a9b7ba70783b617e9998dc4dd82eb3c5', '2021-01-21 09:18:05', '2021-01-21 09:18:05', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (370, 'server.recovery.rollbackingRetryPeriod', 'SEATA_GROUP', '1000', 'a9b7ba70783b617e9998dc4dd82eb3c5', '2021-01-21 09:18:05', '2021-01-21 09:18:05', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (371, 'server.recovery.timeoutRetryPeriod', 'SEATA_GROUP', '1000', 'a9b7ba70783b617e9998dc4dd82eb3c5', '2021-01-21 09:18:05', '2021-01-21 09:18:05', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (372, 'server.maxCommitRetryTimeout', 'SEATA_GROUP', '-1', '6bb61e3b7bce0931da574d19d1d82c88', '2021-01-21 09:18:05', '2021-01-21 09:18:05', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (373, 'server.maxRollbackRetryTimeout', 'SEATA_GROUP', '-1', '6bb61e3b7bce0931da574d19d1d82c88', '2021-01-21 09:18:05', '2021-01-21 09:18:05', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (374, 'server.rollbackRetryTimeoutUnlockEnable', 'SEATA_GROUP', 'false', '68934a3e9455fa72420237eb05902327', '2021-01-21 09:18:06', '2021-01-21 09:18:06', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (375, 'client.undo.dataValidation', 'SEATA_GROUP', 'true', 'b326b5062b2f0e69046810717534cb09', '2021-01-21 09:18:06', '2021-01-21 09:18:06', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (376, 'client.undo.logSerialization', 'SEATA_GROUP', 'jackson', 'b41779690b83f182acc67d6388c7bac9', '2021-01-21 09:18:06', '2021-01-21 09:18:06', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (377, 'client.undo.onlyCareUpdateColumns', 'SEATA_GROUP', 'true', 'b326b5062b2f0e69046810717534cb09', '2021-01-21 09:18:06', '2021-01-21 09:18:06', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (378, 'server.undo.logSaveDays', 'SEATA_GROUP', '7', '8f14e45fceea167a5a36dedd4bea2543', '2021-01-21 09:18:06', '2021-01-21 09:18:06', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (379, 'server.undo.logDeletePeriod', 'SEATA_GROUP', '86400000', 'f4c122804fe9076cb2710f55c3c6e346', '2021-01-21 09:18:06', '2021-01-21 09:18:06', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (380, 'client.undo.logTable', 'SEATA_GROUP', 'undo_log', '2842d229c24afe9e61437135e8306614', '2021-01-21 09:18:06', '2021-01-21 09:18:06', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (381, 'client.undo.compress.enable', 'SEATA_GROUP', 'true', 'b326b5062b2f0e69046810717534cb09', '2021-01-21 09:18:06', '2021-01-21 09:18:06', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (382, 'client.undo.compress.type', 'SEATA_GROUP', 'zip', 'adcdbd79a8d84175c229b192aadc02f2', '2021-01-21 09:18:06', '2021-01-21 09:18:06', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (383, 'client.undo.compress.threshold', 'SEATA_GROUP', '64k', 'bd44a6458bdbff0b5cac721ba361f035', '2021-01-21 09:18:06', '2021-01-21 09:18:06', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (384, 'log.exceptionRate', 'SEATA_GROUP', '100', 'f899139df5e1059396431415e770c6dd', '2021-01-21 09:18:06', '2021-01-21 09:18:06', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (385, 'transport.serialization', 'SEATA_GROUP', 'seata', 'b943081c423b9a5416a706524ee05d40', '2021-01-21 09:18:06', '2021-01-21 09:18:06', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (386, 'transport.compressor', 'SEATA_GROUP', 'none', '334c4a4c42fdb79d7ebc3e73b517e6f8', '2021-01-21 09:18:06', '2021-01-21 09:18:06', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (387, 'metrics.enabled', 'SEATA_GROUP', 'false', '68934a3e9455fa72420237eb05902327', '2021-01-21 09:18:06', '2021-01-21 09:18:06', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (388, 'metrics.registryType', 'SEATA_GROUP', 'compact', '7cf74ca49c304df8150205fc915cd465', '2021-01-21 09:18:06', '2021-01-21 09:18:06', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (389, 'metrics.exporterList', 'SEATA_GROUP', 'prometheus', 'e4f00638b8a10e6994e67af2f832d51c', '2021-01-21 09:18:06', '2021-01-21 09:18:06', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (390, 'metrics.exporterPrometheusPort', 'SEATA_GROUP', '9898', '7b9dc501afe4ee11c56a4831e20cee71', '2021-01-21 09:18:06', '2021-01-21 09:18:06', NULL, '172.100.0.1', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (419, 'MICRO-EXCEPTION-SERVICE.yaml', 'DEV', 'spring:\n  # 排除自动配置\n  autoconfigure:\n    exclude: org.springframework.cloud.security.oauth2.SpringCloudSecurityAutoConfiguration\n  # 允许bean覆盖\n  main:\n    allow-bean-definition-overriding: true\n  # 链路追踪\n  zipkin:\n    base-url: http://localhost:9411\n    sleuth:\n      sampler:\n        probability: 1\n  cloud:\n    # 注册中心\n    nacos:\n      discovery:\n        server-addr: 127.0.0.1:8848\n    # 熔断限流降级\n    sentinel:\n      transport:\n        dashboard: 127.0.0.1:8858\n        port: 8719\n    # 消息驱动\n    stream:\n      # 配置RabbitMQ 绑定器\n      binders:\n        rabbit:\n          type: rabbit\n          # RabbitMQ地址\n          environment:\n            spring:\n              rabbitmq:\n                host: localhost\n                port: 5672\n                username: guest\n                password: guest\n      # 配置输出通道\n      bindings:\n        input:\n          # 主题\n          destination: dev-message\n          # Binder\n          binder: rabbit\n          # 内容格式JSON\n          content-type: application/json\n        output:\n          # 主题\n          destination: dev-message\n          # Binder\n          binder: rabbit\n          # 内容格式JSON\n          content-type: application/json\n  # 数据源\n  datasource:\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://127.0.0.1:3306/micro_service?useUnicode=true&useSSL=false&characterEncoding=utf8&allowPublicKeyRetrieval=true\n    username: root\n    password: 123456\n    # 连接池\n    druid:\n      # 最小空闲\n      min-idle: 5\n      # 最大存活连接\n      max-active: 20\n      # 初始化连接数\n      initial-size: 5\n      # 配置监控服务器\n      stat-view-servlet:\n        #是否允许开启监控\n        enabled: true\n        #是否允许重置监控数据\n        reset-enable: true\n        #druid访问路径\n        url-pattern: /druid/*\n      # 监控统计过滤器\n      filters: stat,wall,log4j2\n      # 检查关闭空闲连接的时间周期\n      time-between-eviction-runs-millis: 6000\n      # mergeSql，慢SQL记录\n      connect-properties: druid.state.mergeSql=true\n# 暴露端点\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n# Mybatis Plus\nmybatis-plus:\n  mapper-locations: classpath*:/mapper/*.xml\n  type-enums-package: cn.machine.geek.enums\n  configuration:\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\n# 分布式事务配置\nseata:\n  tx-service-group: machine_geek\n  enable-auto-data-source-proxy: true\n  registry:\n    type: nacos\n    nacos:\n      server-addr: 127.0.0.1:8848\n      namespace:\n      group: SEATA_GROUP\n      application: SEATA-SERVER\n  config:\n    type: nacos\n    nacos:\n      server-addr: 127.0.0.1:8848\n      namespace:\n      group: SEATA_GROUP\n# 日志级别\nlogging:\n  level:\n    org.springframework.boot.autoconfigure.security: WARN', 'e190ec3a96dfe93f69ad75885671f34b', '2021-01-31 08:59:40', '2021-01-31 09:11:54', NULL, '172.100.0.1', '', '', '异常服务', '', '', 'yaml', '');
COMMIT;

-- ----------------------------
-- Table structure for config_info_aggr
-- ----------------------------
DROP TABLE IF EXISTS `config_info_aggr`;
CREATE TABLE `config_info_aggr` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `datum_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'datum_id',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '内容',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfoaggr_datagrouptenantdatum` (`data_id`,`group_id`,`tenant_id`,`datum_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='增加租户字段';

-- ----------------------------
-- Records of config_info_aggr
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for config_info_beta
-- ----------------------------
DROP TABLE IF EXISTS `config_info_beta`;
CREATE TABLE `config_info_beta` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `beta_ips` varchar(1024) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'betaIps',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin COMMENT 'source user',
  `src_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'source ip',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfobeta_datagrouptenant` (`data_id`,`group_id`,`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info_beta';

-- ----------------------------
-- Records of config_info_beta
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for config_info_tag
-- ----------------------------
DROP TABLE IF EXISTS `config_info_tag`;
CREATE TABLE `config_info_tag` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_id',
  `tag_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'tag_id',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin COMMENT 'source user',
  `src_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'source ip',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfotag_datagrouptenanttag` (`data_id`,`group_id`,`tenant_id`,`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info_tag';

-- ----------------------------
-- Records of config_info_tag
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for config_tags_relation
-- ----------------------------
DROP TABLE IF EXISTS `config_tags_relation`;
CREATE TABLE `config_tags_relation` (
  `id` bigint NOT NULL COMMENT 'id',
  `tag_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'tag_name',
  `tag_type` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'tag_type',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_id',
  `nid` bigint NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`nid`),
  UNIQUE KEY `uk_configtagrelation_configidtag` (`id`,`tag_name`,`tag_type`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_tag_relation';

-- ----------------------------
-- Records of config_tags_relation
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for group_capacity
-- ----------------------------
DROP TABLE IF EXISTS `group_capacity`;
CREATE TABLE `group_capacity` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Group ID，空字符表示整个集群',
  `quota` int unsigned NOT NULL DEFAULT '0' COMMENT '配额，0表示使用默认值',
  `usage` int unsigned NOT NULL DEFAULT '0' COMMENT '使用量',
  `max_size` int unsigned NOT NULL DEFAULT '0' COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int unsigned NOT NULL DEFAULT '0' COMMENT '聚合子配置最大个数，，0表示使用默认值',
  `max_aggr_size` int unsigned NOT NULL DEFAULT '0' COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int unsigned NOT NULL DEFAULT '0' COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_group_id` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='集群、各Group容量信息表';

-- ----------------------------
-- Records of group_capacity
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for his_config_info
-- ----------------------------
DROP TABLE IF EXISTS `his_config_info`;
CREATE TABLE `his_config_info` (
  `id` bigint unsigned NOT NULL,
  `nid` bigint unsigned NOT NULL AUTO_INCREMENT,
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin,
  `src_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `op_type` char(10) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`nid`),
  KEY `idx_gmt_create` (`gmt_create`),
  KEY `idx_gmt_modified` (`gmt_modified`),
  KEY `idx_did` (`data_id`)
) ENGINE=InnoDB AUTO_INCREMENT=697 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='多租户改造';

-- ----------------------------
-- Records of his_config_info
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for permissions
-- ----------------------------
DROP TABLE IF EXISTS `permissions`;
CREATE TABLE `permissions` (
  `role` varchar(50) NOT NULL,
  `resource` varchar(255) NOT NULL,
  `action` varchar(8) NOT NULL,
  UNIQUE KEY `uk_role_permission` (`role`,`resource`,`action`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of permissions
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `username` varchar(50) NOT NULL,
  `role` varchar(50) NOT NULL,
  UNIQUE KEY `idx_user_role` (`username`,`role`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of roles
-- ----------------------------
BEGIN;
INSERT INTO `roles` VALUES ('nacos', 'ROLE_ADMIN');
COMMIT;

-- ----------------------------
-- Table structure for tenant_capacity
-- ----------------------------
DROP TABLE IF EXISTS `tenant_capacity`;
CREATE TABLE `tenant_capacity` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Tenant ID',
  `quota` int unsigned NOT NULL DEFAULT '0' COMMENT '配额，0表示使用默认值',
  `usage` int unsigned NOT NULL DEFAULT '0' COMMENT '使用量',
  `max_size` int unsigned NOT NULL DEFAULT '0' COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int unsigned NOT NULL DEFAULT '0' COMMENT '聚合子配置最大个数',
  `max_aggr_size` int unsigned NOT NULL DEFAULT '0' COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int unsigned NOT NULL DEFAULT '0' COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='租户容量信息表';

-- ----------------------------
-- Records of tenant_capacity
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for tenant_info
-- ----------------------------
DROP TABLE IF EXISTS `tenant_info`;
CREATE TABLE `tenant_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `kp` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'kp',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_id',
  `tenant_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_name',
  `tenant_desc` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'tenant_desc',
  `create_source` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'create_source',
  `gmt_create` bigint NOT NULL COMMENT '创建时间',
  `gmt_modified` bigint NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tenant_info_kptenantid` (`kp`,`tenant_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='tenant_info';

-- ----------------------------
-- Records of tenant_info
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(500) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of users
-- ----------------------------
BEGIN;
INSERT INTO `users` VALUES ('nacos', '$2a$10$EuWPZHzz32dJN7jexM34MOeYirDdFAZm2kuWj7VEOJhhZkDrxfvUu', 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
