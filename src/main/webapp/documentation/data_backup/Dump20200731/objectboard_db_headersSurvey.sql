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
-- Table structure for table `headersSurvey`
--

DROP TABLE IF EXISTS `headersSurvey`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `headersSurvey` (
  `survey_code` bigint(8) NOT NULL AUTO_INCREMENT,
  `bussinessUnit_bu_bis_code` bigint(4) NOT NULL,
  `hs_name` varchar(45) NOT NULL DEFAULT 'NA',
  `hs_references` varchar(8) DEFAULT NULL,
  `total_questions` int(3) DEFAULT '0',
  `surveyStatus` varchar(1) DEFAULT NULL,
  `surveyAnswerStatus` varchar(1) DEFAULT NULL,
  `version` int(3) NOT NULL DEFAULT '1',
  `date_creation` varchar(19) NOT NULL,
  `typified_bussinessUnit_bu_bis_code` bigint(4) NOT NULL,
  `typified_ctypified_code1` varchar(30) NOT NULL,
  `typified_ctypified_code2` varchar(30) NOT NULL,
  `typified_ctypified_code3` varchar(30) NOT NULL,
  `organization_bussinessUnit_bu_bis_code` bigint(4) NOT NULL,
  `organization_level1` varchar(45) NOT NULL,
  `organization_level2` varchar(45) NOT NULL,
  `organization_level3` varchar(45) NOT NULL,
  `organization_level4` varchar(45) NOT NULL,
  `date_last_modification` varchar(19) DEFAULT NULL,
  PRIMARY KEY (`survey_code`,`bussinessUnit_bu_bis_code`),
  KEY `fk_headersQuestions_bussinessUnit1_idx` (`bussinessUnit_bu_bis_code`),
  KEY `fk_headersSurvey_typified1_idx` (`typified_bussinessUnit_bu_bis_code`,`typified_ctypified_code1`,`typified_ctypified_code2`,`typified_ctypified_code3`),
  KEY `fk_headersSurvey_organization1_idx` (`organization_bussinessUnit_bu_bis_code`,`organization_level1`,`organization_level2`,`organization_level3`,`organization_level4`),
  CONSTRAINT `fk_headersQuestions_bussinessUnit1` FOREIGN KEY (`bussinessUnit_bu_bis_code`) REFERENCES `bussinessUnit` (`bu_bis_code`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_headersSurvey_organization1` FOREIGN KEY (`organization_bussinessUnit_bu_bis_code`, `organization_level1`, `organization_level2`, `organization_level3`, `organization_level4`) REFERENCES `organization` (`bussinessUnit_bu_bis_code`, `level1`, `level2`, `level3`, `level4`) ON UPDATE NO ACTION,
  CONSTRAINT `fk_headersSurvey_typified1` FOREIGN KEY (`typified_bussinessUnit_bu_bis_code`, `typified_ctypified_code1`, `typified_ctypified_code2`, `typified_ctypified_code3`) REFERENCES `typified` (`bussinessUnit_bu_bis_code`, `ctypified_code1`, `ctypified_code2`, `ctypified_code3`) ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `headersSurvey`
--

LOCK TABLES `headersSurvey` WRITE;
/*!40000 ALTER TABLE `headersSurvey` DISABLE KEYS */;
INSERT INTO `headersSurvey` VALUES (9,2,'Primera encuesta','0HJHWGIQ',12,'T','T',1,'2020/07/30 12:28:25',2,'Uno','Dos','Tres',2,'Uno','Dos','Tres','Cuatro','2020/07/31 12:26:06');
/*!40000 ALTER TABLE `headersSurvey` ENABLE KEYS */;
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
