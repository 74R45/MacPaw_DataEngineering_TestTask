--
-- Table structure for table `apps`
--
CREATE TABLE `apps` (
    `id` int unsigned NOT NULL AUTO_INCREMENT,
    `name` varchar(1000) NOT NULL,
    `genre` varchar(1000) NOT NULL,
    `rating` float NOT NULL,
    `version` varchar(255) NOT NULL,
    `size_bytes` int NOT NULL,
    `is_awesome` tinyint NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `movies`
--
CREATE TABLE `movies` (
    `id` int unsigned NOT NULL AUTO_INCREMENT,
    `original_title` varchar(1000) NOT NULL,
    `original_language` varchar(2) NOT NULL,
    `budget` int NOT NULL,
    `is_adult` tinyint NOT NULL,
    `release_date` date NOT NULL,
    `original_title_normalized` varchar(1000) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `songs`
--
CREATE TABLE `songs` (
    `id` int unsigned NOT NULL AUTO_INCREMENT,
    `artist_name` varchar(1000) NOT NULL,
    `title` varchar(1000) NOT NULL,
    `year` int NOT NULL,
    `release` varchar(1000) NOT NULL,
    `ingestion_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `processed_files`
--
CREATE TABLE `processed_files` (
    `id` int unsigned NOT NULL AUTO_INCREMENT,
    `name` varchar(50) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
