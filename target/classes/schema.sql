DROP TABLE IF EXISTS `activity_participants`;
DROP TABLE IF EXISTS `activities`;
DROP TABLE IF EXISTS `club_members`;
DROP TABLE IF EXISTS `transactions`;
DROP TABLE IF EXISTS `files`;
DROP TABLE IF EXISTS `news`;
DROP TABLE IF EXISTS `messages`;
DROP TABLE IF EXISTS `clubs`;
DROP TABLE IF EXISTS `users`;

-- Users Table
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `role` varchar(20) NOT NULL DEFAULT 'USER' COMMENT 'USER, ADMIN, SUPER_ADMIN',
  `email` varchar(100) DEFAULT NULL,
  `introduction` text,
  `avatar` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Clubs Table
CREATE TABLE `clubs` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` text,
  `category` varchar(50) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `creator_id` bigint(20) NOT NULL,
  `status` varchar(20) DEFAULT 'PENDING' COMMENT 'PENDING, APPROVED, REJECTED',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_creator` (`creator_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Club Members Table
CREATE TABLE `club_members` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `club_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `role` varchar(20) DEFAULT 'MEMBER' COMMENT 'MEMBER, ADMIN, PRESIDENT',
  `status` varchar(20) DEFAULT 'PENDING' COMMENT 'PENDING, APPROVED',
  `join_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_club_user` (`club_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Activities Table
CREATE TABLE `activities` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `club_id` bigint(20) NOT NULL,
  `title` varchar(100) NOT NULL,
  `description` text,
  `start_time` datetime NOT NULL,
  `end_time` datetime NOT NULL,
  `location` varchar(100) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_club` (`club_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Activity Participants Table
CREATE TABLE `activity_participants` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `activity_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `status` varchar(20) DEFAULT 'SIGNED_UP' COMMENT 'SIGNED_UP, CHECKED_IN',
  `signup_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_activity_user` (`activity_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- News/Announcements Table
CREATE TABLE `news` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `content` text NOT NULL,
  `author_id` bigint(20) NOT NULL,
  `type` varchar(20) DEFAULT 'NEWS' COMMENT 'NEWS, ANNOUNCEMENT',
  `target_club_id` bigint(20) DEFAULT NULL COMMENT 'If null, global announcement',
  `publish_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Transactions Table
CREATE TABLE `transactions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `club_id` bigint(20) NOT NULL,
  `type` varchar(20) NOT NULL COMMENT 'INCOME, EXPENSE',
  `amount` decimal(10,2) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `creator_id` bigint(20) NOT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_club` (`club_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Messages Table
CREATE TABLE `messages` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sender_id` bigint(20) NOT NULL,
  `receiver_id` bigint(20) NOT NULL,
  `content` text NOT NULL,
  `is_read` tinyint(1) DEFAULT 0,
  `send_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_sender` (`sender_id`),
  KEY `idx_receiver` (`receiver_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Files Table
CREATE TABLE `files` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uploader_id` bigint(20) NOT NULL,
  `club_id` bigint(20) DEFAULT NULL,
  `filename` varchar(255) NOT NULL,
  `file_path` varchar(255) NOT NULL,
  `file_type` varchar(50) DEFAULT NULL,
  `upload_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Initial Data (Super Admin)
INSERT INTO `users` (`username`, `password`, `role`, `email`, `introduction`) VALUES ('admin', 'admin123', 'SUPER_ADMIN', 'admin@cms.com', 'System Administrator');
