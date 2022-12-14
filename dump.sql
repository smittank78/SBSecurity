
CREATE DATABASE `security`;

USE `security`;

--
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
CREATE TABLE `authorities` (
  `username` varchar(128) NOT NULL,
  `authority` varchar(128) NOT NULL,
  UNIQUE KEY `AUTHORITIES_UNIQUE` (`username`,`authority`)
);

--
-- Dumping data for table `authorities`
--

INSERT INTO `authorities` VALUES ('a','ROLE_USER'),('smit','ROLE_DEV'),('vinit','ROLE_USER');

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `USERNAME` varchar(128) NOT NULL,
  `PASSWORD` varchar(128) NOT NULL,
  `ENABLED` tinyint NOT NULL,
  PRIMARY KEY (`USERNAME`)
);

--
-- Dumping data for table `users`
--

INSERT INTO `users` VALUES ('a','$2a$10$RzOSCkclIPeM2HWSnScY3u4phStI6kqvB.CwBvYeilIq8Ljp4Hp7e',1),('smit','$2a$10$SJUhxK/oq6rsU5qLLfHrFuqGNuxebqqK8vhTJCKnQtTKKWA0k9BAC',1),('vinit','$2a$10$Spa1.s/T2NRweCszMYEglOe9Lp/q71AHv9xn5Iz4DP9Al7RTXP5xG',1);
