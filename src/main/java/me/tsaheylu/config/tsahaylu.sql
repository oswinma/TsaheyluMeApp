-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        10.3.11-MariaDB - mariadb.org binary distribution
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 tsahaylu 的数据库结构
CREATE DATABASE IF NOT EXISTS `tsahaylu_com` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `tsahaylu_com`;

-- 导出  表 tsahaylu.comment 结构
CREATE TABLE IF NOT EXISTS `comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fromid` bigint(20) DEFAULT NULL,
  `toid` bigint(20) DEFAULT NULL,
  `urlid` bigint(20) DEFAULT NULL,
  `favurlid` bigint(20) DEFAULT NULL,
  `sendtime` datetime DEFAULT NULL,
  `content` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。
-- 导出  表 tsahaylu.favurl 结构
CREATE TABLE IF NOT EXISTS `favurl` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fromid` bigint(20) DEFAULT NULL,
  `toid` bigint(20) DEFAULT NULL,
  `urlid` bigint(20) DEFAULT NULL,
  `status` int(1) unsigned zerofill DEFAULT NULL,
  `fstatus` int(1) unsigned zerofill DEFAULT NULL,
  `sendtime` datetime DEFAULT NULL,
  `serial` bigint(20) DEFAULT NULL,
  `readtime` datetime DEFAULT NULL,
  `comment` varchar(500) DEFAULT NULL,
  `channel` varchar(10) DEFAULT NULL,
  `fav` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。
-- 导出  表 tsahaylu.friend 结构
CREATE TABLE IF NOT EXISTS `friend` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fromid` bigint(20) DEFAULT NULL,
  `toid` bigint(20) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `popup` int(1) DEFAULT NULL,
  `bondtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。
-- 导出  表 tsahaylu.group 结构
CREATE TABLE IF NOT EXISTS `group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fromid` bigint(20) DEFAULT NULL,
  `status` int(1) unsigned zerofill DEFAULT NULL,
  `des` varchar(100) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。
-- 导出  表 tsahaylu.groupdata 结构
CREATE TABLE IF NOT EXISTS `groupdata` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `toid` bigint(20) DEFAULT NULL,
  `groupid` bigint(20) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `addtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。
-- 导出  表 tsahaylu.message 结构
CREATE TABLE IF NOT EXISTS `message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fromid` bigint(20) DEFAULT NULL,
  `toid` bigint(20) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `status` int(1) unsigned zerofill DEFAULT NULL,
  `content` varchar(500) DEFAULT NULL,
  `sendtime` datetime DEFAULT NULL,
  `readtime` datetime DEFAULT NULL,
  `refid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。
-- 导出  表 tsahaylu.urlinfo 结构
CREATE TABLE IF NOT EXISTS `urlinfo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url` varchar(1000) DEFAULT NULL,
  `title` varchar(200) DEFAULT NULL,
  `icon` varchar(200) DEFAULT NULL,
  `host` varchar(100) DEFAULT NULL,
  `status` int(1) unsigned zerofill DEFAULT NULL,
  `share` int(10) DEFAULT NULL,
  `favs` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。
-- 导出  表 tsahaylu.user 结构
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `nickname` varchar(100) DEFAULT NULL,
  `country` varchar(100) DEFAULT NULL,
  `language` varchar(100) DEFAULT NULL,
  `avatarKey` varchar(100) DEFAULT NULL,
  `avatarURL` varchar(100) DEFAULT NULL,
  `code` varchar(100) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `signuptime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。
-- 导出  表 tsahaylu.useropenid 结构
CREATE TABLE IF NOT EXISTS `useropenid` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `openid_url` varchar(1000) DEFAULT NULL,
  `user_id` varchar(100) DEFAULT NULL,
  `provider_id` varchar(100) DEFAULT NULL,
  `openid_provider` varchar(10) DEFAULT NULL,
  `attachtime` varchar(0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
