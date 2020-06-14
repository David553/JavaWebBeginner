SET MODE MYSQL;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `gender` tinyint(4) NOT NULL DEFAULT '1' COMMENT '性别：1.男 2.女',
  `age` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '年龄',
  `name` varchar(30) NOT NULL DEFAULT '' COMMENT '注册名',
  `isDeleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '软删除',
  `createdTime` bigint(20) NOT NULL,
  `updatedTime` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB CHARSET=utf8mb4;

