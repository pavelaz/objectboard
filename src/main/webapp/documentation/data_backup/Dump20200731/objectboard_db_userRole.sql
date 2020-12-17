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
-- Table structure for table `userRole`
--

DROP TABLE IF EXISTS `userRole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userRole` (
  `masterUser_mu_email` varchar(40) NOT NULL,
  `masterUser_bussinessUnit_bu_bis_code` bigint(4) NOT NULL,
  `project_pr_id_project` bigint(2) NOT NULL,
  `um_role` int(2) NOT NULL DEFAULT '0' COMMENT 'INTRANET USERS',
  `um_status` varchar(1) DEFAULT 'T' COMMENT 'active or not',
  PRIMARY KEY (`masterUser_mu_email`,`masterUser_bussinessUnit_bu_bis_code`,`project_pr_id_project`),
  KEY `fk_userRole_project1_idx` (`project_pr_id_project`),
  CONSTRAINT `fk_userRole_masterUser1` FOREIGN KEY (`masterUser_mu_email`, `masterUser_bussinessUnit_bu_bis_code`) REFERENCES `masterUser` (`mu_email`, `bussinessUnit_bu_bis_code`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_userRole_project1` FOREIGN KEY (`project_pr_id_project`) REFERENCES `project` (`pr_id_project`) ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='modules to which each user has access by business unit';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userRole`
--

LOCK TABLES `userRole` WRITE;
/*!40000 ALTER TABLE `userRole` DISABLE KEYS */;
INSERT INTO `userRole` VALUES ('pavelaz1@gmail.com',1,1,1,'T');
/*!40000 ALTER TABLE `userRole` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-07-31 12:30:10
