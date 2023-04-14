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
) ENGINE=InnoDB AUTO_INCREMENT=433 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
