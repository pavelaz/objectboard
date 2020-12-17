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
-- Table structure for table `bodySurveyAnswers`
--

DROP TABLE IF EXISTS `bodySurveyAnswers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bodySurveyAnswers` (
  `answer_code` bigint(4) NOT NULL AUTO_INCREMENT,
  `bodySurveyQuestions_question_code` bigint(4) NOT NULL,
  `bodySurveyQuestions_headersSurvey_survey_code` bigint(8) NOT NULL,
  `bodySurveyQuestions_headersSurvey_bussinessUnit_bu_bis_code` bigint(4) NOT NULL,
  `answer` varchar(65) NOT NULL,
  `answer_solution` varchar(1) NOT NULL DEFAULT 'F',
  `auditable_solution` varchar(1) NOT NULL DEFAULT 'F',
  `answer_only_text` varchar(45) DEFAULT NULL,
  `answer_only_number` double DEFAULT '0',
  `answer_only_date` varchar(10) DEFAULT NULL,
  `answer_only_time` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`answer_code`,`bodySurveyQuestions_question_code`,`bodySurveyQuestions_headersSurvey_survey_code`,`bodySurveyQuestions_headersSurvey_bussinessUnit_bu_bis_code`),
  KEY `fk_bodySurveyAnswers_bodySurveyQuestions1_idx` (`bodySurveyQuestions_question_code`,`bodySurveyQuestions_headersSurvey_survey_code`,`bodySurveyQuestions_headersSurvey_bussinessUnit_bu_bis_code`),
  CONSTRAINT `fk_bodySurveyAnswers_bodySurveyQuestions1` FOREIGN KEY (`bodySurveyQuestions_question_code`, `bodySurveyQuestions_headersSurvey_survey_code`, `bodySurveyQuestions_headersSurvey_bussinessUnit_bu_bis_code`) REFERENCES `bodySurveyQuestions` (`question_code`, `headersSurvey_survey_code`, `headersSurvey_bussinessUnit_bu_bis_code`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=99 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bodySurveyAnswers`
--

LOCK TABLES `bodySurveyAnswers` WRITE;
/*!40000 ALTER TABLE `bodySurveyAnswers` DISABLE KEYS */;
INSERT INTO `bodySurveyAnswers` VALUES (73,63,9,2,'Conform','T','F',NULL,0,NULL,NULL),(75,63,9,2,'Not conform','F','',NULL,0,NULL,NULL),(76,65,9,2,'yes','T','',NULL,0,NULL,NULL),(77,65,9,2,'no','F','',NULL,0,NULL,NULL),(78,66,9,2,'xcxcxcxxcxc','F','',NULL,0,NULL,NULL),(79,66,9,2,'mmnmnm, jjjk  jlkjlkj','T','',NULL,0,NULL,NULL),(80,66,9,2,'dddddddddd','F','',NULL,0,NULL,NULL),(81,67,9,2,'mmmmmmmmm','F','',NULL,0,NULL,NULL),(82,67,9,2,'ooooo ooooo','F','',NULL,0,NULL,NULL),(83,67,9,2,'pppppppp uuuuu','T','F',NULL,0,NULL,NULL),(84,67,9,2,'tttttttt','F','',NULL,0,NULL,NULL),(85,68,9,2,'45  kjhkj','F','',NULL,0,NULL,NULL),(86,68,9,2,'548 gyggj','T','F',NULL,0,NULL,NULL),(87,68,9,2,'hhgj 4646','T','F',NULL,0,NULL,NULL),(88,68,9,2,'nbmnm','F','',NULL,0,NULL,NULL),(89,69,9,2,'mmnnn','F','',NULL,0,NULL,NULL),(90,69,9,2,'jeremias','T','',NULL,0,NULL,NULL),(91,69,9,2,'licy','T','',NULL,0,NULL,NULL),(92,69,9,2,'Luego de iniciar su jornada de trabajo','T','',NULL,0,NULL,NULL),(96,79,9,2,'','T','F','papi',0,NULL,NULL),(97,80,9,2,'','T','T',NULL,0,NULL,NULL),(98,81,9,2,'','T','F',NULL,0,NULL,'13:30:25');
/*!40000 ALTER TABLE `bodySurveyAnswers` ENABLE KEYS */;
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
