CREATE TABLE `categories` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `questions` (
  `id` int NOT NULL AUTO_INCREMENT,
  `testId` int NOT NULL,
  `title` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Questions_Tests1_idx` (`testId`),
  CONSTRAINT `fk_Questions_Tests1` FOREIGN KEY (`testId`) REFERENCES `tests` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `results` (
  `userId` int NOT NULL,
  `testId` int NOT NULL,
  `result` int NOT NULL,
  `passingDate` date NOT NULL,
  PRIMARY KEY (`userId`,`testId`),
  KEY `fk_results_tests1_idx` (`testId`),
  CONSTRAINT `fk_results_tests1` FOREIGN KEY (`testId`) REFERENCES `tests` (`id`),
  CONSTRAINT `fk_results_users1` FOREIGN KEY (`userId`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `statements` (
  `id` int NOT NULL AUTO_INCREMENT,
  `questionId` int NOT NULL,
  `text` varchar(50) NOT NULL,
  `isCorrect` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Statments_Questions1_idx` (`questionId`),
  CONSTRAINT `fk_Statments_Questions1` FOREIGN KEY (`questionId`) REFERENCES `questions` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tests` (
  `id` int NOT NULL AUTO_INCREMENT,
  `creatorId` int NOT NULL,
  `categoryId` int NOT NULL,
  `name` varchar(40) NOT NULL,
  `creationDate` date NOT NULL,
  `removed` bit(1) NOT NULL DEFAULT b'0',
  `available` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`),
  KEY `fk_Tests_Users1_idx` (`creatorId`),
  KEY `fk_tests_categories1_idx` (`categoryId`),
  CONSTRAINT `fk_tests_categories1` FOREIGN KEY (`categoryId`) REFERENCES `categories` (`id`),
  CONSTRAINT `fk_Tests_Users1` FOREIGN KEY (`creatorId`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `roleId` int NOT NULL,
  `name` varchar(20) NOT NULL,
  `email` varchar(30) NOT NULL,
  `password` varchar(60) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_UNIQUE` (`email`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  KEY `fk_users_roles1_idx` (`roleId`),
  CONSTRAINT `fk_users_roles1` FOREIGN KEY (`roleId`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;