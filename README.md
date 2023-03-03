# 数据库导入

```
/*
SQLyog Trial v13.1.8 (64 bit)
MySQL - 8.0.27 : Database - sentinel-test-db
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`sentinel-test-db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `sentinel-test-db`;

/*Table structure for table `sentinel_metric` */

CREATE TABLE `sentinel_metric` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id，主键',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `app` varchar(100) DEFAULT NULL COMMENT '应用名称',
  `timestamp` datetime DEFAULT NULL COMMENT '统计时间',
  `resource` varchar(500) DEFAULT NULL COMMENT '资源名称',
  `pass_qps` int DEFAULT NULL COMMENT '通过qps',
  `success_qps` int DEFAULT NULL COMMENT '成功qps',
  `block_qps` int DEFAULT NULL COMMENT '限流qps',
  `exception_qps` int DEFAULT NULL COMMENT '发送异常的次数',
  `rt` double DEFAULT NULL COMMENT '所有successQps的rt的和',
  `_count` int DEFAULT NULL COMMENT '本次聚合的总条数',
  `resource_code` int DEFAULT NULL COMMENT '资源的hashCode',
  PRIMARY KEY (`id`),
  KEY `app_idx` (`app`) USING BTREE,
  KEY `resource_idx` (`resource`) USING BTREE,
  KEY `timestamp_idx` (`timestamp`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*Data for the table `sentinel_metric` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

```

<br/>

<br/>

# 修改配置文件

修改 src/main/resources/application.properties

**将 数据库 、Nacos 、Bootadmin 地址修改**

```
#spring settings
spring.http.encoding.force=true
spring.http.encoding.charset=UTF-8
spring.http.encoding.enabled=true
#server.port=9000

#cookie name setting
server.servlet.session.cookie.name=sentinel_dashboard_cookie

#logging settings
logging.level.org.springframework.web=INFO
logging.file=${user.home}/logs/csp/sentinel-dashboard.log
logging.pattern.file= %d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n
#logging.pattern.console= %d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n

#auth settings
auth.filter.exclude-urls=/,/auth/login,/auth/logout,/registry/machine,/version,/actuator,/actuator/**
auth.filter.exclude-url-suffixes=htm,html,js,css,map,ico,ttf,woff,png
# If auth.enabled=false, Sentinel console disable login
auth.username=sentinel
auth.password=sentinel

# Inject the dashboard version. It's required to enable
# filtering in pom.xml for this resource file.
sentinel.dashboard.version=${project.version}



spring.application.name=maozi-cloud-sentinel




spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/sentinel-test-db?characterEncoding=utf8
spring.datasource.username=root
spring.datasource.password=password

# spring data jpa
spring.jpa.hibernate.ddl-auto=none
spring.jpa.hibernate.use-new-id-generator-mappings=false
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
spring.jpa.show-sql=false




nacos.address=localhost:8848
nacos.namespace=
nacos.username=nacos
nacos.password=nacos













spring.boot.admin.discovery.services=maozi-cloud-*
spring.boot.admin.client.url=http://localhost:8000
spring.boot.admin.client.username=admin
spring.boot.admin.client.password=admin

management.info.env.enabled=true
management.health.dubbo.status.defaults=memory
management.health.dubbo.status.extras=load,threadpool
management.endpoints.web.exposure.include=*

management.endpoint.dubbo.enabled=true
management.endpoint.health.show-details=ALWAYS





```