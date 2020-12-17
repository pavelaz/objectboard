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
-- Table structure for table `bodyAnswerSurvey`
--

DROP TABLE IF EXISTS `bodyAnswerSurvey`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bodyAnswerSurvey` (
  `headerAnswerSurvey_answer_id` bigint(8) NOT NULL,
  `bodySurveyQuestions_headersSurvey_survey_code` bigint(8) NOT NULL,
  `bodySurveyQuestions_headersSurvey_bussinessUnit_bu_bis_code` bigint(4) NOT NULL,
  `bodySurveyQuestions_question_code` bigint(4) NOT NULL,
  `answer` varchar(10) DEFAULT NULL,
  `answer_evaluation` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`bodySurveyQuestions_question_code`,`bodySurveyQuestions_headersSurvey_bussinessUnit_bu_bis_code`,`bodySurveyQuestions_headersSurvey_survey_code`,`headerAnswerSurvey_answer_id`),
  KEY `fk_bodyAnswerSurvey_bodySurveyQuestions1_idx` (`bodySurveyQuestions_question_code`,`bodySurveyQuestions_headersSurvey_survey_code`,`bodySurveyQuestions_headersSurvey_bussinessUnit_bu_bis_code`),
  KEY `fk_bodyAnswerSurvey_headerAnswerSurvey1_idx` (`headerAnswerSurvey_answer_id`),
  CONSTRAINT `fk_bodyAnswerSurvey_bodySurveyQuestions1` FOREIGN KEY (`bodySurveyQuestions_question_code`, `bodySurveyQuestions_headersSurvey_survey_code`, `bodySurveyQuestions_headersSurvey_bussinessUnit_bu_bis_code`) REFERENCES `bodySurveyQuestions` (`question_code`, `headersSurvey_survey_code`, `headersSurvey_bussinessUnit_bu_bis_code`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_bodyAnswerSurvey_headerAnswerSurvey1` FOREIGN KEY (`headerAnswerSurvey_answer_id`) REFERENCES `headerAnswerSurvey` (`answer_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bodyAnswerSurvey`
--

LOCK TABLES `bodyAnswerSurvey` WRITE;
/*!40000 ALTER TABLE `bodyAnswerSurvey` DISABLE KEYS */;
/*!40000 ALTER TABLE `bodyAnswerSurvey` ENABLE KEYS */;
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
