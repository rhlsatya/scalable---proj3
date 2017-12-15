CREATE DATABASE  IF NOT EXISTS `scalable` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `scalable`;
-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: localhost    Database: scalable
-- ------------------------------------------------------
-- Server version	5.7.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `file_directory`
--

DROP TABLE IF EXISTS `file_directory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `file_directory` (
  `file_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `location` varchar(100) NOT NULL,
  `isLocked` varchar(1) CHARACTER SET latin1 COLLATE latin1_bin NOT NULL,
  PRIMARY KEY (`file_id`),
  UNIQUE KEY `id_UNIQUE` (`file_id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=1010 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `file_directory`
--

LOCK TABLES `file_directory` WRITE;
/*!40000 ALTER TABLE `file_directory` DISABLE KEYS */;
INSERT INTO `file_directory` VALUES (1001,'research','/Users/rahulsatya/Desktop/','n'),(1002,'README','/Users/rahulsatya/Desktop/','n'),(1003,'random_1','/Users/rahulsatya/Downloads/','n'),(1004,'random_2','/Users/rahulsatya/Downloads/','n'),(1005,'random_3','/Users/rahulsatya/Downloads/','n'),(1006,'random_4','/Users/rahulsatya/Documents/','n'),(1007,'random_5','/Users/rahulsatya/Documents/','n'),(1008,'random_6','/Users/rahulsatya/Documents/','n'),(1009,'abc','/Users/rahulsatya/eclipse-workspace/project 3/','n');
/*!40000 ALTER TABLE `file_directory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `read_permissions`
--

DROP TABLE IF EXISTS `read_permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `read_permissions` (
  `user_directory_user_id` int(11) NOT NULL,
  `file_directory_file_id` int(11) NOT NULL,
  PRIMARY KEY (`user_directory_user_id`,`file_directory_file_id`),
  KEY `fk_user_directory_has_file_directory_file_directory1_idx` (`file_directory_file_id`),
  KEY `fk_user_directory_has_file_directory_user_directory_idx` (`user_directory_user_id`),
  CONSTRAINT `fk_user_directory_has_file_directory_file_directory1` FOREIGN KEY (`file_directory_file_id`) REFERENCES `file_directory` (`file_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_directory_has_file_directory_user_directory` FOREIGN KEY (`user_directory_user_id`) REFERENCES `user_directory` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `read_permissions`
--

LOCK TABLES `read_permissions` WRITE;
/*!40000 ALTER TABLE `read_permissions` DISABLE KEYS */;
INSERT INTO `read_permissions` VALUES (101,1001),(104,1001),(101,1002),(102,1002),(101,1003),(104,1003),(101,1004),(102,1004),(103,1005),(104,1005),(102,1006),(103,1006),(102,1007),(103,1007),(103,1008),(105,1008),(101,1009);
/*!40000 ALTER TABLE `read_permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_directory`
--

DROP TABLE IF EXISTS `user_directory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_directory` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `file_in_use` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  UNIQUE KEY `name_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_directory`
--

LOCK TABLES `user_directory` WRITE;
/*!40000 ALTER TABLE `user_directory` DISABLE KEYS */;
INSERT INTO `user_directory` VALUES (101,'rahul','satya',NULL),(102,'lulu','alcoholic','random_2'),(103,'mini','on',NULL),(104,'kungfu','panda',NULL),(105,'shi','fu',NULL),(106,'sponge','bob',NULL),(107,'karate','kid',NULL),(108,'han','solo',NULL),(109,'bay','max',NULL),(110,'darth','vader',NULL);
/*!40000 ALTER TABLE `user_directory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `write_permissions`
--

DROP TABLE IF EXISTS `write_permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `write_permissions` (
  `user_directory_user_id` int(11) NOT NULL,
  `file_directory_file_id` int(11) NOT NULL,
  PRIMARY KEY (`user_directory_user_id`,`file_directory_file_id`),
  KEY `fk_user_directory_has_file_directory_file_directory2_idx` (`file_directory_file_id`),
  KEY `fk_user_directory_has_file_directory_user_directory1_idx` (`user_directory_user_id`),
  CONSTRAINT `fk_user_directory_has_file_directory_file_directory2` FOREIGN KEY (`file_directory_file_id`) REFERENCES `file_directory` (`file_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_directory_has_file_directory_user_directory1` FOREIGN KEY (`user_directory_user_id`) REFERENCES `user_directory` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `write_permissions`
--

LOCK TABLES `write_permissions` WRITE;
/*!40000 ALTER TABLE `write_permissions` DISABLE KEYS */;
INSERT INTO `write_permissions` VALUES (102,1001),(101,1003),(106,1003),(102,1004),(101,1005),(101,1006),(102,1006),(104,1006),(101,1007),(107,1007),(105,1008),(106,1008),(101,1009),(105,1009);
/*!40000 ALTER TABLE `write_permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'scalable'
--

--
-- Dumping routines for database 'scalable'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-15 23:38:33
