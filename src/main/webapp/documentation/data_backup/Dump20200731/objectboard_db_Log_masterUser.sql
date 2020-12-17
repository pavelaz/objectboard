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
-- Table structure for table `Log_masterUser`
--

DROP TABLE IF EXISTS `Log_masterUser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Log_masterUser` (
  `mu_email` varchar(40) DEFAULT NULL,
  `bussinessUnit_bu_bis_code` bigint(4) DEFAULT NULL,
  `mu_password` varchar(20) DEFAULT NULL,
  `mu_password_ant` varchar(20) DEFAULT NULL,
  `mu_password_old` varchar(20) DEFAULT NULL,
  `mu_password_old_ant` varchar(20) DEFAULT NULL,
  `mu_name` varchar(45) DEFAULT NULL,
  `mu_name_ant` varchar(45) DEFAULT NULL,
  `mu_section_time` int(11) DEFAULT '1200' COMMENT 'TIEMPO QUE PERMANECE ABIERTA LA SECCION EN DESUSO',
  `mu_section_time_ant` int(11) DEFAULT NULL,
  `mu_question` varchar(60) DEFAULT NULL,
  `mu_question_ant` varchar(60) DEFAULT NULL,
  `mu_start_date` varchar(19) DEFAULT NULL COMMENT 'start date ',
  `mu_start_date_ant` varchar(19) DEFAULT NULL COMMENT 'start date ',
  `mu_answer` varchar(35) DEFAULT NULL,
  `mu_answer_ant` varchar(35) DEFAULT NULL,
  `mu_status` varchar(1) DEFAULT 'F' COMMENT '0 INACTIVE 1 ACTIVE',
  `mu_status_ant` varchar(1) DEFAULT 'F' COMMENT '0 INACTIVE 1 ACTIVE',
  `mu_date` varchar(19) DEFAULT NULL COMMENT 'REGISTER DATE',
  `mu_date_ant` varchar(19) DEFAULT NULL COMMENT 'REGISTER DATE',
  `mu_effective_days` int(4) DEFAULT '90' COMMENT 'renovation password days',
  `mu_effective_days_ant` int(4) DEFAULT '90' COMMENT 'renovation password days',
  `mu_confirm_code` varchar(45) DEFAULT NULL,
  `mu_confirm_code_ant` varchar(45) DEFAULT NULL,
  `mu_email_confirm` varchar(1) DEFAULT 'F' COMMENT '0 UNCONFIRM 1 CONFIRM',
  `mu_email_confirm_ant` varchar(1) DEFAULT 'F' COMMENT '0 UNCONFIRM 1 CONFIRM',
  `mu_date_reset_pwd` varchar(19) DEFAULT NULL,
  `mu_date_reset_pwd_ant` varchar(19) DEFAULT NULL,
  `mu_gender` varchar(1) DEFAULT 'M',
  `mu_gender_ant` varchar(1) DEFAULT 'M',
  `mu_photo` blob,
  `mu_photo_ant` blob,
  `mu_data_user` varchar(12) DEFAULT NULL COMMENT 'long para accesar la base de datos',
  `mu_data_user_ant` varchar(12) DEFAULT NULL COMMENT 'long para accesar la base de datos',
  `mu_data_password` varchar(16) DEFAULT NULL,
  `mu_data_password_ant` varchar(16) DEFAULT NULL,
  `mu_expires` varchar(1) DEFAULT 'F',
  `mu_expires_ant` varchar(1) DEFAULT 'F',
  `mu_date_expires` varchar(19) DEFAULT '90',
  `mu_date_expires_ant` varchar(19) DEFAULT '90',
  `city_ci_city_code` bigint(3) DEFAULT NULL,
  `city_ci_city_code_ant` bigint(3) DEFAULT NULL,
  `city_states_st_state_code` bigint(2) DEFAULT NULL COMMENT 'EL ACCESO AL SISTEMA CADUCA O NO',
  `city_states_st_state_code_ant` bigint(2) DEFAULT NULL COMMENT 'EL ACCESO AL SISTEMA CADUCA O NO',
  `city_states_country_co_country_code` bigint(3) DEFAULT NULL,
  `city_states_country_co_country_code_ant` bigint(3) DEFAULT NULL,
  `action_type` varchar(8) DEFAULT NULL,
  `action_who` varchar(12) DEFAULT NULL,
  `action_time` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Log_masterUser`
--

LOCK TABLES `Log_masterUser` WRITE;
/*!40000 ALTER TABLE `Log_masterUser` DISABLE KEYS */;
/*!40000 ALTER TABLE `Log_masterUser` ENABLE KEYS */;
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
