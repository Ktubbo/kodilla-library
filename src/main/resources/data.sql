DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS copies;
DROP TABLE IF EXISTS loans;

CREATE TABLE `users` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `firstname` varchar(50) NOT NULL,
  `lastname` varchar(50) NOT NULL,
  `creationdate` date DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `books` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  `author` varchar(50) NOT NULL,
  `publishing_date` int(4) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `copies` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `bookid` int NOT NULL,
  `status` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `loans` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `bookid` int NOT NULL,
  `userid` int NOT NULL,
  `borrowingDate` date DEFAULT NULL,
  `returningDate` date DEFAULT NULL,
  PRIMARY KEY (`id`)
);
