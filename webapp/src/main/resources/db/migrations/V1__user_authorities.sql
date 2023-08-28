-- Dumping structure for table demo.users
DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`createBy` BIGINT(20) NULL DEFAULT NULL,
	`createDate` DATETIME NULL DEFAULT NULL,
	`updatedBy` BIGINT(20) NULL DEFAULT NULL,
	`updatedDate` DATETIME NULL DEFAULT NULL,
	`accountNonExpired` TINYINT(1) NULL DEFAULT '1',
	`accountNonLocked` TINYINT(1) NULL DEFAULT '1',
	`credentialsNonExpired` TINYINT(1) NULL DEFAULT '1',
	`email` VARCHAR(255) NULL DEFAULT NULL,
	`enabled` BIT(1) NOT NULL,
	`firstName` VARCHAR(255) NULL DEFAULT NULL,
	`lastName` VARCHAR(255) NULL DEFAULT NULL,
	`password` VARCHAR(255) NULL DEFAULT NULL,
	`sex` VARCHAR(255) NULL DEFAULT NULL,
	`username` VARCHAR(255) NULL DEFAULT NULL,
	PRIMARY KEY (`id`),
	UNIQUE INDEX `UK_r43af9ap4edm43mmtq01oddj6` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--
LOCK TABLES `users` WRITE;
INSERT INTO `users` VALUES (1,1,NULL,NULL,NULL,1,1,1,'admin@demo.in',1,NULL,NULL,'$2a$10$hbxecwitQQ.dDT4JOFzQAulNySFwEpaFLw38jda6Td.Y/cOiRzDFu',NULL,"admin@demo.in");
UNLOCK TABLES;


--
-- Table structure for table `roles`
--
DROP TABLE IF EXISTS `roles`;

CREATE TABLE `roles` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`createBy` BIGINT(20) NULL DEFAULT NULL,
	`createDate` DATETIME NULL DEFAULT NULL,
	`updatedBy` BIGINT(20) NULL DEFAULT NULL,
	`updatedDate` DATETIME NULL DEFAULT NULL,
	`role` VARCHAR(50) NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

INSERT INTO `roles` VALUES (1,1,now(),NULL,NULL,'ROLE_USER');
INSERT INTO `roles` VALUES (2,1,now(),NULL,NULL,'ROLE_ADMIN');

--
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `user_authorities`;

CREATE TABLE `user_authorities` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`createBy` BIGINT(20) NULL DEFAULT NULL,
	`createDate` DATETIME NULL DEFAULT NULL,
	`updatedBy` BIGINT(20) NULL DEFAULT NULL,
	`updatedDate` DATETIME NULL DEFAULT NULL,
	`role_id` BIGINT(20) NULL DEFAULT NULL,
	`username_id` BIGINT(20) NULL DEFAULT NULL,
	PRIMARY KEY (`id`),
	INDEX `FK_go2qs49fy5t2uh3phs93kxj5y` (`username_id`),
	CONSTRAINT `FK_go2qs49fy5t2uh3phs93kxj5y` FOREIGN KEY (`username_id`) REFERENCES `users` (`id`),
	CONSTRAINT `FK_go2qs49fy5t2uh3phs93kxj5s` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

LOCK TABLES `user_authorities` WRITE;
INSERT INTO `user_authorities` VALUES (1,NULL,NULL,NULL,NULL,1,1);
UNLOCK TABLES;
