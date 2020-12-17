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
-- Table structure for table `Log_discharge`
--

DROP TABLE IF EXISTS `Log_discharge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Log_discharge` (
  `di_license_num` bigint(8) DEFAULT NULL,
  `di_start_date` varchar(19) DEFAULT NULL,
  `di_end_date` varchar(19) DEFAULT NULL,
  `di_number_user` int(3) DEFAULT NULL,
  `di_permanent` varchar(1) DEFAULT NULL,
  `di_sales_code` int(2) DEFAULT NULL,
  `di_license_code` varchar(16) DEFAULT NULL,
  `bussinessUnit_bu_bis_code` bigint(4) DEFAULT NULL,
  `project_pr_id_project` bigint(2) DEFAULT NULL,
  `di_start_date_old` varchar(19) DEFAULT NULL,
  `di_end_date_old` varchar(19) DEFAULT NULL,
  `di_number_user_old` int(3) DEFAULT NULL,
  `di_permanent_old` varchar(1) DEFAULT NULL,
  `di_sales_code_old` int(2) DEFAULT '0',
  `di_license_code_old` varchar(16) DEFAULT NULL,
  `bussinessUnit_bu_bis_code_old` bigint(4) DEFAULT NULL,
  `project_pr_id_project_old` bigint(2) DEFAULT NULL,
  `action_type` varchar(8) DEFAULT NULL,
  `action_who` varchar(12) DEFAULT NULL,
  `action_time` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Log_discharge`
--

LOCK TABLES `Log_discharge` WRITE;
/*!40000 ALTER TABLE `Log_discharge` DISABLE KEYS */;
/*!40000 ALTER TABLE `Log_discharge` ENABLE KEYS */;
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
