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
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `personaplay`.`roles` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(45)  NOT NULL UNIQUE;
) ENGINE = InnoDB;

CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `email` varchar(64) NOT NULL,
  `content_filter` tinyint(1) NOT NULL DEFAULT '0',
  `role_id` int NOT NULL,
  `icon_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`),
  KEY `fk_users_roles` (`role_id`),
  KEY `fk_users_icon_idx` (`icon_id`),
  CONSTRAINT `fk_users_roles` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `icon` (
  `id` int NOT NULL AUTO_INCREMENT,
  `icon` int NOT NULL DEFAULT '0',
  `variant` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  CONSTRAINT `users_id` FOREIGN KEY (`id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--user-medias
CREATE TABLE `personaplay`.`users_medias` (
  `users_id` INT NOT NULL,
  `medias_id` INT NOT NULL,
  PRIMARY KEY (`users_id`, `medias_id`),
  `rating` INT NOT NULL,
  `watch_status` VARCHAR(45) NOT NULL,
  `favorite` TINYINT(1) NOT NULL DEFAULT 0,
  FOREIGN KEY (`users_id`) REFERENCES `personaplay`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  FOREIGN KEY (`medias_id`) REFERENCES `personaplay`.`medias` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB;

-- ratings
CREATE TABLE `personaplay`.`ratings` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `rating` INT NOT NULL,
  `rating_count` INT NOT NULL,
  `medias_id` INT NOT NULL,
    FOREIGN KEY (`medias_id`) REFERENCES `personaplay`.`medias` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
) ENGINE = InnoDB;

-- stats
CREATE TABLE `personaplay`.`stats` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `watch_time` INT NOT NULL DEFAULT 0
) ENGINE = InnoDB;

-- medias_stats
CREATE TABLE `personaplay`.`medias_stats` (
  `medias_id` INT NOT NULL,
  `stats_id` INT NOT NULL,
  PRIMARY KEY (`medias_id`, `stats_id`),
  FOREIGN KEY (`medias_id`) REFERENCES `personaplay`.`medias` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  FOREIGN KEY (`stats_id`) REFERENCES `personaplay`.`stats` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB;

-- watchlist, there's going to be multiple types of watchlists
CREATE TABLE `personaplay`.`watchlist` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `type` VARCHAR(45) NOT NULL,
  `users_id` INT NOT NULL,
    FOREIGN KEY (`users_id`) REFERENCES `personaplay`.`users` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
) ENGINE = InnoDB;

-- tags
CREATE TABLE `personaplay`.`tags` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(45) NOT NULL UNIQUE
) ENGINE = InnoDB;

-- watchlist_tags
CREATE TABLE `personaplay`.`watchlist_tags` (
  `watchlist_id` INT NOT NULL,
  `tags_id` INT NOT NULL,
  PRIMARY KEY (`watchlist_id`, `tags_id`),
  FOREIGN KEY (`watchlist_id`) REFERENCES `personaplay`.`watchlist` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  FOREIGN KEY (`tags_id`) REFERENCES `personaplay`.`tags` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB;

-- watchlist_medias_stats, because there's going to be multiple watchlists
CREATE TABLE `personaplay`.`watchlist_medias_stats` (
  `watchlist_id` INT NOT NULL,
  `medias_id` INT NOT NULL,
  `stats_id` INT NOT NULL,
  PRIMARY KEY (`watchlist_id`, `medias_id`, `stats_id`),
  FOREIGN KEY (`watchlist_id`) REFERENCES `personaplay`.`watchlist` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  FOREIGN KEY (`medias_id`, `stats_id`) REFERENCES `personaplay`.`medias_stats` (`medias_id`, `stats_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB;
