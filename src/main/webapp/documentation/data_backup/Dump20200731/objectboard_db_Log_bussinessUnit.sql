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
-- Table structure for table `Log_bussinessUnit`
--

DROP TABLE IF EXISTS `Log_bussinessUnit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Log_bussinessUnit` (
  `bu_bis_code` bigint(4) DEFAULT NULL COMMENT 'Clavede la tabla - numero de la empresa',
  `bu_federal_number` varchar(10) DEFAULT NULL,
  `bu_provincial_number` varchar(16) DEFAULT NULL,
  `bu_email` varchar(60) DEFAULT NULL,
  `bu_status` varchar(1) DEFAULT NULL COMMENT '1 ACTIVE 0 INACTIVE',
  `bu_super_code` varchar(12) DEFAULT NULL COMMENT 'COMPANY CODE TO REGISTER ADMINISTRATORS',
  `bu_phone` varchar(13) DEFAULT NULL,
  `bu_zipcode` varchar(6) DEFAULT NULL,
  `bu_name` varchar(35) DEFAULT NULL,
  `bu_address` varchar(45) DEFAULT NULL,
  `bu_webpage` varchar(50) DEFAULT NULL,
  `bu_admin_code` varchar(12) DEFAULT NULL,
  `bu_user1_code` varchar(12) DEFAULT NULL,
  `bu_user2_code` varchar(12) DEFAULT NULL,
  `city_ci_city_code` bigint(3) DEFAULT NULL,
  `city_states_st_state_code` bigint(2) DEFAULT NULL,
  `city_states_country_co_country_code` bigint(3) DEFAULT NULL,
  `bussinessType_bt_code_type` bigint(2) DEFAULT NULL,
  `bu_federal_number_old` varchar(10) DEFAULT NULL,
  `bu_provincial_number_old` varchar(16) DEFAULT NULL,
  `bu_email_old` varchar(60) DEFAULT NULL,
  `bu_status_old` varchar(1) DEFAULT NULL COMMENT '1 ACTIVE 0 INACTIVE',
  `bu_super_code_old` varchar(12) DEFAULT NULL COMMENT 'COMPANY CODE TO REGISTER ADMINISTRATORS',
  `bu_phone_old` varchar(13) DEFAULT NULL,
  `bu_zipcode_old` varchar(6) DEFAULT NULL,
  `bu_name_old` varchar(35) DEFAULT NULL,
  `bu_address_old` varchar(45) DEFAULT NULL,
  `bu_webpage_old` varchar(50) DEFAULT NULL,
  `bu_admin_code_old` varchar(12) DEFAULT NULL,
  `bu_user1_code_old` varchar(12) DEFAULT NULL,
  `bu_user2_code_old` varchar(12) DEFAULT NULL,
  `city_ci_city_code_old` bigint(3) DEFAULT NULL,
  `city_states_st_state_code_old` bigint(2) DEFAULT NULL,
  `city_states_country_co_country_code_old` bigint(3) DEFAULT NULL,
  `bussinessType_bt_code_type_old` bigint(2) DEFAULT NULL,
  `action_type` varchar(8) DEFAULT NULL,
  `action_who` varchar(12) DEFAULT NULL,
  `action_time` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Log_bussinessUnit`
--

LOCK TABLES `Log_bussinessUnit` WRITE;
/*!40000 ALTER TABLE `Log_bussinessUnit` DISABLE KEYS */;
/*!40000 ALTER TABLE `Log_bussinessUnit` ENABLE KEYS */;
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
