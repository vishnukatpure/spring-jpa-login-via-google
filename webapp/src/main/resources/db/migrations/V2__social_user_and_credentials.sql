
-- Dumping structure for table demo.social_media_credentials
DROP TABLE IF EXISTS `social_media_credentials`;
CREATE TABLE IF NOT EXISTS `social_media_credentials` (
 	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`createBy` BIGINT(20) NULL DEFAULT NULL,
	`createDate` DATETIME NULL DEFAULT NULL,
	`updatedBy` BIGINT(20) NULL DEFAULT NULL,
	`updatedDate` DATETIME NULL DEFAULT NULL,
	`accessRequestAPI` VARCHAR(255) NULL DEFAULT NULL,
	`accessTokenAPI` VARCHAR(255) NULL DEFAULT NULL,
	`appId` TEXT NULL,
	`authorizedRedirectURL` VARCHAR(255) NULL DEFAULT NULL,
	`clientId` TEXT NULL,
	`clientSecret` TEXT NULL,
	`emailAddressAPI` VARCHAR(255) NULL DEFAULT NULL,
	`enabled` TINYINT(1) NULL DEFAULT '1',
	`scopes` VARCHAR(255) NULL DEFAULT NULL,
	`socialMediaProvider` VARCHAR(255) NULL DEFAULT NULL,
	`userCancelApi` VARCHAR(255) NULL DEFAULT NULL,
	`userDetailAPI` VARCHAR(255) NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
-- Dumping structure for table demo.social_user
DROP TABLE IF EXISTS `social_user`;
CREATE TABLE IF NOT EXISTS `social_user` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`createBy` BIGINT(20) NULL DEFAULT NULL,
	`createDate` DATETIME NULL DEFAULT NULL,
	`updatedBy` BIGINT(20) NULL DEFAULT NULL,
	`updatedDate` DATETIME NULL DEFAULT NULL,
	`accessToken` TEXT NULL,
	`contactNumber` VARCHAR(255) NULL DEFAULT NULL,
	`contactNumberCountryCode` VARCHAR(255) NULL DEFAULT NULL,
	`email` VARCHAR(255) NULL DEFAULT NULL,
	`firstName` VARCHAR(255) NULL DEFAULT NULL,
	`lastName` VARCHAR(255) NULL DEFAULT NULL,
	`middleName` VARCHAR(255) NULL DEFAULT NULL,
	`pictureUrl` VARCHAR(255) NULL DEFAULT NULL,
	`socialMediaProvider` VARCHAR(255) NULL DEFAULT NULL,
	`url` VARCHAR(255) NULL DEFAULT NULL,
	`userSocialId` VARCHAR(255) NULL DEFAULT NULL,
	`user_id` BIGINT(20) NULL DEFAULT NULL,
	PRIMARY KEY (`id`),
	INDEX `FK_num91wn94xxe1onyhwd27skyd` (`user_id`),
	CONSTRAINT `FK_num91wn94xxe1onyhwd27skyd` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `social_media_credentials` (`id`, `createBy`, `createDate`, `updatedBy`, `updatedDate`, `accessRequestAPI`, `accessTokenAPI`, `appId`, `authorizedRedirectURL`, `clientId`, `clientSecret`, `emailAddressAPI`, `enabled`, `scopes`, `socialMediaProvider`, `userCancelApi`, `userDetailAPI`) VALUES (1, NULL, NULL, NULL, NULL, 'https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email+https://www.googleapis.com/auth/userinfo.profile&google_client.authorization.scope=[https://www.googleapis.com/auth/userinfo.email+https://www.googleapis.com', NULL, NULL, 'http://localhost:8080/spring-jpa-login-via-google/oauth-login/google/complete', '988107224706-2k0b72bgubaav86vskmpu3fre91utbfh.apps.googleusercontent.com', '989mabKkOEM1wnt0CRothxii', NULL, 1, NULL, 'GOOGLE', NULL, 'https://www.googleapis.com/oauth2/v3/userinfo?access_token=');

