--
-- Table structure for table `apps`
--
CREATE TABLE `apps` (
    `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `name` varchar(1000) NOT NULL,
    `genre` varchar(1000) NOT NULL,
    `rating` float NOT NULL,
    `version` varchar(255) NOT NULL,
    `size_bytes` int(11) NOT NULL,
    `is_awesome` tinyint(4) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=136377 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `movies`
--
CREATE TABLE `movies` (
    `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `original_title` varchar(1000) NOT NULL,
    `original_language` varchar(2) NOT NULL,
    `budget` int(11) NOT NULL,
    `is_adult` tinyint(4) NOT NULL,
    `release_date` date NOT NULL,
    `original_title_normalized` varchar(1000) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=154777 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `songs`
--
CREATE TABLE `songs` (
    `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `artist_name` varchar(1000) NOT NULL,
    `title` varchar(1000) NOT NULL,
    `year` int(11) NOT NULL,
    `release` varchar(1000) NOT NULL,
    `ingestion_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=168368 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `processed_files`
--
CREATE TABLE `processed_files` (
    `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `name` varchar(50) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=309 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
