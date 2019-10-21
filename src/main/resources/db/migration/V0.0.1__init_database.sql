-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.4.8-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping structure for table db.user
CREATE TABLE IF NOT EXISTS `user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'primary key',
  `firstname` varchar(255) NOT NULL COMMENT 'firstname',
  `lastname` varchar(255) NOT NULL COMMENT 'lastname',
  `email` varchar(255) NOT NULL COMMENT 'email is also the username',
  `password` varchar(255) NOT NULL COMMENT 'password is hashed in bcrypt',
  `account_non_expired` tinyint(1) DEFAULT 1 COMMENT 'account expiration status',
  `account_non_locked` tinyint(1) DEFAULT 1 COMMENT 'account lock status',
  `credential_non_expired` tinyint(1) DEFAULT 1 COMMENT 'account credential expiration status',
  `enabled` tinyint(1) DEFAULT 1 COMMENT 'account is enabled or not',
  `created_date` datetime NOT NULL DEFAULT current_timestamp() COMMENT 'audit data',
  `created_by` bigint(20) DEFAULT NULL COMMENT 'audit data',
  `last_modified_date` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT 'audit data',
  `last_modified_by` bigint(20) DEFAULT NULL COMMENT 'audit data',
  `version` bigint(20) NOT NULL DEFAULT 1 COMMENT 'versioning',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email` (`email`),
  KEY `FK_user_user` (`created_by`),
  KEY `FK_user_user_2` (`last_modified_by`),
  CONSTRAINT `FK_user_user` FOREIGN KEY (`created_by`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FK_user_user_2` FOREIGN KEY (`last_modified_by`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='user informations';

-- Data exporting was unselected.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
