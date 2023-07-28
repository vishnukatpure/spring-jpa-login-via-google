
-- Dumping structure for table demo.social_media_credentials
DROP TABLE IF EXISTS `social_media_credentials`;
CREATE TABLE IF NOT EXISTS `social_media_credentials` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createBy` bigint(20) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `updatedBy` bigint(20) DEFAULT NULL,
  `updatedDate` datetime DEFAULT NULL,
  `accessRequestAPI` varchar(255) DEFAULT NULL,
  `accessTokenAPI` varchar(255) DEFAULT NULL,
  `appId` text,
  `authorizedRedirectURL` varchar(255) DEFAULT NULL,
  `clientId` text,
  `clientSecret` text,
  `emailAddressAPI` varchar(255) DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT '1',
  `scopes` varchar(255) DEFAULT NULL,
  `socialMediaProvider` varchar(255) DEFAULT NULL,
  `userCancelApi` varchar(255) DEFAULT NULL,
  `userDetailAPI` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
-- Dumping structure for table demo.social_user
DROP TABLE IF EXISTS `social_user`;
CREATE TABLE IF NOT EXISTS `social_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createBy` bigint(20) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `updatedBy` bigint(20) DEFAULT NULL,
  `updatedDate` datetime DEFAULT NULL,
  `accessToken` text,
  `contactNumber` varchar(255) DEFAULT NULL,
  `contactNumberCountryCode` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `middleName` varchar(255) DEFAULT NULL,
  `pictureUrl` varchar(255) DEFAULT NULL,
  `socialMediaProvider` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `userSocialId` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `social_media_credentials` (`id`, `createBy`, `createDate`, `updatedBy`, `updatedDate`, `accessRequestAPI`, `accessTokenAPI`, `appId`, `authorizedRedirectURL`, `clientId`, `clientSecret`, `emailAddressAPI`, `enabled`, `scopes`, `socialMediaProvider`, `userCancelApi`, `userDetailAPI`) VALUES (1, NULL, NULL, NULL, NULL, 'https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email+https://www.googleapis.com/auth/userinfo.profile&google_client.authorization.scope=[https://www.googleapis.com/auth/userinfo.email+https://www.googleapis.com', NULL, NULL, 'oauth-login/google/complete', '988107224706-2k0b72bgubaav86vskmpu3fre91utbfh.apps.googleusercontent.com', '989mabKkOEM1wnt0CRothxii', NULL, 1, NULL, 'GOOGLE', NULL, 'https://www.googleapis.com/oauth2/v3/userinfo?access_token=');

