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
-- Table structure for table `bussinessUnit`
--

DROP TABLE IF EXISTS `bussinessUnit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bussinessUnit` (
  `bu_bis_code` bigint(4) NOT NULL AUTO_INCREMENT COMMENT 'Clavede la tabla - numero de la empresa',
  `bu_federal_number` varchar(10) DEFAULT NULL,
  `bu_provincial_number` varchar(16) DEFAULT NULL,
  `bu_email` varchar(60) DEFAULT NULL,
  `bu_status` varchar(1) NOT NULL DEFAULT 'T' COMMENT '1 ACTIVE 0 INACTIVE',
  `bu_super_code` varchar(12) DEFAULT NULL COMMENT 'COMPANY CODE TO REGISTER ADMINISTRATORS',
  `bu_phone` varchar(13) DEFAULT NULL,
  `bu_zipcode` varchar(6) DEFAULT NULL,
  `bu_name` varchar(35) DEFAULT NULL,
  `bu_address` varchar(45) DEFAULT NULL,
  `bu_webpage` varchar(50) DEFAULT NULL,
  `bu_admin_code` varchar(12) DEFAULT NULL,
  `bu_user1_code` varchar(12) DEFAULT NULL,
  `bu_user2_code` varchar(12) DEFAULT NULL,
  `city_ci_city_code` bigint(3) NOT NULL,
  `city_states_st_state_code` bigint(2) NOT NULL,
  `city_states_country_co_country_code` bigint(3) NOT NULL,
  `bussinessType_bt_code_type` bigint(2) NOT NULL,
  PRIMARY KEY (`bu_bis_code`),
  KEY `fk_bussinessUnit_city1_idx` (`city_ci_city_code`,`city_states_st_state_code`,`city_states_country_co_country_code`),
  KEY `fk_bussinessUnit_bussinessType1_idx` (`bussinessType_bt_code_type`),
  CONSTRAINT `fk_bussinessUnit_bussinessType1` FOREIGN KEY (`bussinessType_bt_code_type`) REFERENCES `bussinessType` (`bt_code_type`) ON UPDATE NO ACTION,
  CONSTRAINT `fk_bussinessUnit_city1` FOREIGN KEY (`city_ci_city_code`, `city_states_st_state_code`, `city_states_country_co_country_code`) REFERENCES `city` (`ci_city_code`, `states_st_state_code`, `states_country_co_country_code`) ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bussinessUnit`
--

LOCK TABLES `bussinessUnit` WRITE;
/*!40000 ALTER TABLE `bussinessUnit` DISABLE KEYS */;
INSERT INTO `bussinessUnit` VALUES (1,'0123456789','0123456789012345','pepe@gmail.com','T','ASDFJKLNMOPQ','305-258-3689','jkl369','Servicios PVSoft LLc','Case de Carajo','pepe.com','AWQRCFDSHBNF','OPLKIUJHYTGF','UYTREDSWQAZX',1,1,1,1),(2,'9876543210','5432109876543210','pavelaz1@gmail.com','T','DFRSC56780AS','302-258-9874','123456','Test Company LLc','En el patio','luis.com','0987654321QW','GHJKLMNBVCXZ','1Q2W3E4R5T6Y',1,1,1,1);
/*!40000 ALTER TABLE `bussinessUnit` ENABLE KEYS */;
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
