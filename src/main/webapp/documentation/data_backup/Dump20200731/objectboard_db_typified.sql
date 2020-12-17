-- MySQL dump 10.13  Distrib 5.7.31, for Linux (x86_64)
--
-- Host: localhost    Database: objectboard_db
-- ------------------------------------------------------
-- Server version	5.7.31-0ubuntu0.18.04.1

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
-- Table structure for table `typified`
--

DROP TABLE IF EXISTS `typified`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `typified` (
  `bussinessUnit_bu_bis_code` bigint(4) NOT NULL,
  `ctypified_code1` varchar(30) NOT NULL,
  `ctypified_code2` varchar(30) NOT NULL,
  `ctypified_code3` varchar(30) NOT NULL,
  PRIMARY KEY (`bussinessUnit_bu_bis_code`,`ctypified_code1`,`ctypified_code2`,`ctypified_code3`),
  KEY `fk_typified1_bussinessUnit1_idx` (`bussinessUnit_bu_bis_code`),
  CONSTRAINT `fk_typified1_bussinessUnit1` FOREIGN KEY (`bussinessUnit_bu_bis_code`) REFERENCES `bussinessUnit` (`bu_bis_code`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `typified`
--

LOCK TABLES `typified` WRITE;
/*!40000 ALTER TABLE `typified` DISABLE KEYS */;
INSERT INTO `typified` VALUES (2,'Dos','Tres','Cuatro'),(2,'Dos 2','Tres 3','Cuatro 4'),(2,'Uno','Dos','Cinco'),(2,'Uno','Dos','Seis'),(2,'Uno','Dos','Tres');
/*!40000 ALTER TABLE `typified` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-07-31 12:30:12
