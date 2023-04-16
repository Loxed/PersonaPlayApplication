CREATE TABLE `medias` (
  `id` int NOT NULL AUTO_INCREMENT,
  `imdb_id` varchar(9) DEFAULT NULL,
  `title` varchar(45) DEFAULT NULL,
  `poster` varchar(255) DEFAULT NULL,
  `genre` varchar(50) DEFAULT NULL,
  `year` int DEFAULT NULL,
  `actors` varchar(100) DEFAULT NULL,
  `director` varchar(50) DEFAULT NULL,
  `plot` mediumtext,
  `visible` tinyint(1) DEFAULT '1',
  `available` tinyint(1) DEFAULT '0',
  `media_location` varchar(255) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=734 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `icon` (
  `id` int NOT NULL AUTO_INCREMENT,
  `icon` int NOT NULL DEFAULT '0',
  `variant` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `email` varchar(64) NOT NULL,
  `content_filter` tinyint(1) NOT NULL DEFAULT '0',
  `role_id` int NOT NULL,
  `creation_date` timestamp NOT NULL DEFAULT '2022-04-16 15:30:00',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`),
  KEY `fk_users_roles` (`role_id`),
  CONSTRAINT `fk_users_roles` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `users_medias` (
  `users_id` int NOT NULL,
  `medias_id` varchar(9) NOT NULL,
  `rating` int NOT NULL,
  `watch_status` varchar(45) NOT NULL,
  `favorite` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`users_id`,`medias_id`),
  KEY `medias_id` (`medias_id`),
  CONSTRAINT `users_medias_ibfk_1` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
