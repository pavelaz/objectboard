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
-- Table structure for table `discharge`
--

DROP TABLE IF EXISTS `discharge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `discharge` (
  `di_license_num` bigint(8) NOT NULL AUTO_INCREMENT,
  `di_start_date` varchar(19) NOT NULL,
  `di_end_date` varchar(19) NOT NULL,
  `di_number_user` int(3) DEFAULT NULL,
  `di_permanent` varchar(1) DEFAULT 'F',
  `di_sales_code` int(2) DEFAULT '0',
  `di_license_code` varchar(16) NOT NULL,
  `bussinessUnit_bu_bis_code` bigint(4) NOT NULL,
  `project_pr_id_project` bigint(2) NOT NULL,
  PRIMARY KEY (`di_license_num`),
  KEY `fk_discharge_bussinessUnit1_idx` (`bussinessUnit_bu_bis_code`),
  KEY `fk_discharge_project1_idx` (`project_pr_id_project`),
  CONSTRAINT `fk_discharge_bussinessUnit1` FOREIGN KEY (`bussinessUnit_bu_bis_code`) REFERENCES `bussinessUnit` (`bu_bis_code`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_discharge_project1` FOREIGN KEY (`project_pr_id_project`) REFERENCES `project` (`pr_id_project`) ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discharge`
--

LOCK TABLES `discharge` WRITE;
/*!40000 ALTER TABLE `discharge` DISABLE KEYS */;
INSERT INTO `discharge` VALUES (1,'2020/10/03','2020/10/09',5,'F',1,'ASDFGHJKL9876543',1,1),(2,'2020/07/07','2021/01/08',10,'F',12,'HYPV9MPDNLQFZZL1',2,1);
/*!40000 ALTER TABLE `discharge` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-07-31 12:30:11
