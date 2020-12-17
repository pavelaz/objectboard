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
-- Table structure for table `bodySurveyQuestions`
--

DROP TABLE IF EXISTS `bodySurveyQuestions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bodySurveyQuestions` (
  `question_code` bigint(4) NOT NULL AUTO_INCREMENT,
  `headersSurvey_survey_code` bigint(8) NOT NULL,
  `headersSurvey_bussinessUnit_bu_bis_code` bigint(4) NOT NULL,
  `type_request` int(2) NOT NULL DEFAULT '0',
  `main_request` varchar(160) DEFAULT NULL,
  `Annex_type` varchar(1) DEFAULT NULL,
  `body_annex_doc` varchar(45) DEFAULT NULL,
  `body_annex_photo` varchar(45) DEFAULT NULL,
  `comment` varchar(1) DEFAULT 'F',
  `answer_number` int(2) NOT NULL DEFAULT '0',
  `solution_number` int(2) NOT NULL DEFAULT '0',
  `auditable_solution` varchar(1) NOT NULL DEFAULT 'F',
  `auditable_answer_solution` varchar(1) NOT NULL DEFAULT 'F',
  PRIMARY KEY (`question_code`,`headersSurvey_survey_code`,`headersSurvey_bussinessUnit_bu_bis_code`),
  KEY `fk_bodySurvey_headersSurvey1_idx` (`headersSurvey_survey_code`,`headersSurvey_bussinessUnit_bu_bis_code`),
  CONSTRAINT `fk_bodySurvey_headersSurvey1` FOREIGN KEY (`headersSurvey_survey_code`, `headersSurvey_bussinessUnit_bu_bis_code`) REFERENCES `headersSurvey` (`survey_code`, `bussinessUnit_bu_bis_code`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bodySurveyQuestions`
--

LOCK TABLES `bodySurveyQuestions` WRITE;
/*!40000 ALTER TABLE `bodySurveyQuestions` DISABLE KEYS */;
INSERT INTO `bodySurveyQuestions` VALUES (63,9,2,1,'as dffasdad ee fad fa','0','','','T',2,1,'F','F'),(65,9,2,1,'fdfa','2','','dfasdfas','F',2,1,'T','F'),(66,9,2,2,'fffff ffdfaf','1','fd fdgdf','','F',3,1,'T','F'),(67,9,2,2,'rrrr ddd','0','','','F',4,0,'F','F'),(68,9,2,3,'fsgf kjlhl k l ljklj','0','','','T',4,2,'F','F'),(69,9,2,3,'bbbbbb','1','xddddd','','F',4,3,'T','F'),(70,9,2,4,'jlaksfdl fjaldkjflsak','1','uoiuoiuowq sds','','F',1,1,'T','F'),(71,9,2,5,'eqerqwc eqe','2','','iiii uuu','F',1,1,'T','F'),(77,9,2,1,'hhhhhh','0','','','F',0,0,'F','F'),(79,9,2,6,'asdfa','1','fhgfhf','','T',1,1,'T','F'),(80,9,2,7,'fffff','0','','','T',1,1,'F','T'),(81,9,2,8,'ggggg','2','','iiii','F',1,1,'T','F');
/*!40000 ALTER TABLE `bodySurveyQuestions` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-07-31 12:30:09
