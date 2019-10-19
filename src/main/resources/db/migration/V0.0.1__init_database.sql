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


-- Dumping database structure for apps
CREATE DATABASE IF NOT EXISTS `db` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;
USE `db`;

-- Dumping structure for table db.user
CREATE TABLE IF NOT EXISTS `user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'primary key',
  `firstname` varchar(255) NOT NULL COMMENT 'firstname',
  `lastname` varchar(255) NOT NULL COMMENT 'lastname',
  `email` varchar(255) NOT NULL COMMENT 'email is also the username',
  `password` varchar(255) NOT NULL COMMENT 'password is hashed in bcrypt',
  `organization_id` bigint(20) DEFAULT NULL COMMENT 'association to organization',
  `status` enum('ACTIVE','INACTIVE') NOT NULL DEFAULT 'INACTIVE' COMMENT 'status',
  `creation_date` datetime NOT NULL DEFAULT current_timestamp() COMMENT 'audit data',
  `created_by` bigint(20) DEFAULT NULL COMMENT 'audit data',
  `last_modified_date` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT 'audit data',
  `last_modified_by` bigint(20) DEFAULT NULL COMMENT 'audit data',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email` (`email`),
  KEY `FK_user_user` (`created_by`),
  KEY `FK_user_user_2` (`last_modified_by`),
  KEY `FK_user_organization` (`organization_id`),
  CONSTRAINT `FK_user_organization` FOREIGN KEY (`organization_id`) REFERENCES `organization` (`organization_id`),
  CONSTRAINT `FK_user_user` FOREIGN KEY (`created_by`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FK_user_user_2` FOREIGN KEY (`last_modified_by`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='user informations';

-- Data exporting was unselected.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
