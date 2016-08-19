# Host: localhost  (Version: 5.7.10-log)
# Date: 2016-07-09 17:17:40
# Generator: MySQL-Front 5.3  (Build 5.16)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "job"
#

DROP TABLE IF EXISTS `job`;
CREATE TABLE `job` (
  `jid` int(11) NOT NULL,
  `uid` int(11) NOT NULL,
  `jar_path` varchar(1024) DEFAULT NULL,
  `in_path` varchar(1024) DEFAULT NULL,
  `out_path` varchar(1024) DEFAULT NULL,
  `main_class` varchar(30) DEFAULT NULL,
  `memory` int(4) DEFAULT NULL,
  `cores` int(4) DEFAULT NULL,
  `status` int(4) DEFAULT NULL,
  `st` varchar(30) DEFAULT NULL,
  `et` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`jid`,`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "job"
#


#
# Structure for table "user"
#

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `uname` varchar(30) NOT NULL,
  `upasswd` varchar(30) NOT NULL,
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uname` (`uname`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

#
# Data for table "user"
#

INSERT INTO `user` VALUES (1,'admin','admin'),(4,'1','1'),(8,'2','2'),(9,'hehe','hehe'),(12,'sdfg','sdfg'),(13,'sdfgfhfdgs','sdfsdfgsdfgg'),(14,'124334','2134344654');
