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

DROP TABLE IF EXISTS `authorities`;
CREATE TABLE `authorities` (
    `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
    `username` varchar(30) NOT NULL DEFAULT '' COMMENT '注册名',
    `authority` varchar(255) NOT NULL DEFAULT '' COMMENT '权限 TALLYER--记账人，ROOT--超级权限',
    PRIMARY KEY (`id`),
    KEY `uniq_idx_username` (`username`)
) ENGINE=InnoDB CHARSET=utf8mb4;

DROP TABLE IF EXISTS `security`;
CREATE TABLE `security` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL DEFAULT '' COMMENT '注册名',
  `password` varchar(30) NOT NULL DEFAULT '' COMMENT '密码',
  `enable` tinyint(4) NOT NULL DEFAULT '1' COMMENT '用户可用与否，1可用',
  PRIMARY KEY (`id`),
  KEY `uniq_idx_username` (`username`)
) ENGINE=InnoDB CHARSET=utf8mb4;