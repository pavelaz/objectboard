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
-- Temporary table structure for view `BussinessUnitCoStCi`
--

DROP TABLE IF EXISTS `BussinessUnitCoStCi`;
/*!50001 DROP VIEW IF EXISTS `BussinessUnitCoStCi`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `BussinessUnitCoStCi` AS SELECT 
 1 AS `bu_bis_code`,
 1 AS `bu_federal_number`,
 1 AS `bu_provincial_number`,
 1 AS `bu_email`,
 1 AS `bu_status`,
 1 AS `bu_super_code`,
 1 AS `bu_phone`,
 1 AS `bu_zipcode`,
 1 AS `bu_name`,
 1 AS `bu_address`,
 1 AS `bu_webpage`,
 1 AS `bu_admin_code`,
 1 AS `bu_user1_code`,
 1 AS `bu_user2_code`,
 1 AS `city_ci_city_code`,
 1 AS `city_states_st_state_code`,
 1 AS `city_states_country_co_country_code`,
 1 AS `bussinessType_bt_code_type`,
 1 AS `city_name`,
 1 AS `state_name`,
 1 AS `country_name`,
 1 AS `type_name`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `StateCountry`
--

DROP TABLE IF EXISTS `StateCountry`;
/*!50001 DROP VIEW IF EXISTS `StateCountry`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `StateCountry` AS SELECT 
 1 AS `id_state`,
 1 AS `state_name`,
 1 AS `id_country`,
 1 AS `country_name`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `DischargeView`
--

DROP TABLE IF EXISTS `DischargeView`;
/*!50001 DROP VIEW IF EXISTS `DischargeView`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `DischargeView` AS SELECT 
 1 AS `di_license_num`,
 1 AS `di_start_date`,
 1 AS `di_end_date`,
 1 AS `di_number_user`,
 1 AS `di_permanent`,
 1 AS `di_sales_code`,
 1 AS `di_license_code`,
 1 AS `bussinessUnit_bu_bis_code`,
 1 AS `project_pr_id_project`,
 1 AS `bu_name`,
 1 AS `pr_name`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `Profiles`
--

DROP TABLE IF EXISTS `Profiles`;
/*!50001 DROP VIEW IF EXISTS `Profiles`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `Profiles` AS SELECT 
 1 AS `mu_email`,
 1 AS `bussinessUnit_bu_bis_code`,
 1 AS `mu_password`,
 1 AS `mu_password_old`,
 1 AS `mu_name`,
 1 AS `mu_section_time`,
 1 AS `mu_question`,
 1 AS `mu_start_date`,
 1 AS `mu_answer`,
 1 AS `mu_status`,
 1 AS `mu_date`,
 1 AS `mu_effective_days`,
 1 AS `mu_confirm_code`,
 1 AS `mu_email_confirm`,
 1 AS `mu_date_reset_pwd`,
 1 AS `mu_gender`,
 1 AS `mu_photo`,
 1 AS `mu_data_user`,
 1 AS `mu_data_password`,
 1 AS `mu_expires`,
 1 AS `mu_date_expires`,
 1 AS `city_ci_city_code`,
 1 AS `city_states_st_state_code`,
 1 AS `city_states_country_co_country_code`,
 1 AS `contry_name`,
 1 AS `state_name`,
 1 AS `city_name`,
 1 AS `bu_name`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `CityStateCountry`
--

DROP TABLE IF EXISTS `CityStateCountry`;
/*!50001 DROP VIEW IF EXISTS `CityStateCountry`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `CityStateCountry` AS SELECT 
 1 AS `id_city`,
 1 AS `id_state`,
 1 AS `id_country`,
 1 AS `city_name`,
 1 AS `state_name`,
 1 AS `country_name`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `UserRoleView`
--

DROP TABLE IF EXISTS `UserRoleView`;
/*!50001 DROP VIEW IF EXISTS `UserRoleView`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `UserRoleView` AS SELECT 
 1 AS `masterUser_mu_email`,
 1 AS `masterUser_bussinessUnit_bu_bis_code`,
 1 AS `project_pr_id_project`,
 1 AS `um_role`,
 1 AS `um_status`,
 1 AS `user_name`,
 1 AS `unit_name`,
 1 AS `proj_name`*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `BussinessUnitCoStCi`
--

/*!50001 DROP VIEW IF EXISTS `BussinessUnitCoStCi`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`boarduser`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `BussinessUnitCoStCi` AS select `bb`.`bu_bis_code` AS `bu_bis_code`,`bb`.`bu_federal_number` AS `bu_federal_number`,`bb`.`bu_provincial_number` AS `bu_provincial_number`,`bb`.`bu_email` AS `bu_email`,`bb`.`bu_status` AS `bu_status`,`bb`.`bu_super_code` AS `bu_super_code`,`bb`.`bu_phone` AS `bu_phone`,`bb`.`bu_zipcode` AS `bu_zipcode`,`bb`.`bu_name` AS `bu_name`,`bb`.`bu_address` AS `bu_address`,`bb`.`bu_webpage` AS `bu_webpage`,`bb`.`bu_admin_code` AS `bu_admin_code`,`bb`.`bu_user1_code` AS `bu_user1_code`,`bb`.`bu_user2_code` AS `bu_user2_code`,`bb`.`city_ci_city_code` AS `city_ci_city_code`,`bb`.`city_states_st_state_code` AS `city_states_st_state_code`,`bb`.`city_states_country_co_country_code` AS `city_states_country_co_country_code`,`bb`.`bussinessType_bt_code_type` AS `bussinessType_bt_code_type`,`ct`.`ci_name` AS `city_name`,`st`.`st_name` AS `state_name`,`co`.`co_name` AS `country_name`,`bt`.`bt_description` AS `type_name` from ((((`bussinessUnit` `bb` join `states` `st`) join `country` `co`) join `city` `ct`) join `bussinessType` `bt`) where ((`bb`.`city_ci_city_code` = `ct`.`ci_city_code`) and (`bb`.`city_states_st_state_code` = `st`.`st_state_code`) and (`bb`.`city_states_country_co_country_code` = `co`.`co_country_code`) and (`bb`.`bussinessType_bt_code_type` = `bt`.`bt_code_type`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `StateCountry`
--

/*!50001 DROP VIEW IF EXISTS `StateCountry`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`boarduser`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `StateCountry` AS select `states`.`st_state_code` AS `id_state`,`states`.`st_name` AS `state_name`,`country`.`co_country_code` AS `id_country`,`country`.`co_name` AS `country_name` from (`states` join `country`) where (`states`.`country_co_country_code` = `country`.`co_country_code`) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `DischargeView`
--

/*!50001 DROP VIEW IF EXISTS `DischargeView`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`boarduser`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `DischargeView` AS select `D`.`di_license_num` AS `di_license_num`,`D`.`di_start_date` AS `di_start_date`,`D`.`di_end_date` AS `di_end_date`,`D`.`di_number_user` AS `di_number_user`,`D`.`di_permanent` AS `di_permanent`,`D`.`di_sales_code` AS `di_sales_code`,`D`.`di_license_code` AS `di_license_code`,`D`.`bussinessUnit_bu_bis_code` AS `bussinessUnit_bu_bis_code`,`D`.`project_pr_id_project` AS `project_pr_id_project`,`B`.`bu_name` AS `bu_name`,`P`.`pr_name` AS `pr_name` from ((`discharge` `D` join `bussinessUnit` `B`) join `project` `P`) where ((`B`.`bu_bis_code` = `D`.`bussinessUnit_bu_bis_code`) and (`P`.`pr_id_project` = `D`.`project_pr_id_project`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `Profiles`
--

/*!50001 DROP VIEW IF EXISTS `Profiles`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`boarduser`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `Profiles` AS select `M`.`mu_email` AS `mu_email`,`M`.`bussinessUnit_bu_bis_code` AS `bussinessUnit_bu_bis_code`,`M`.`mu_password` AS `mu_password`,`M`.`mu_password_old` AS `mu_password_old`,`M`.`mu_name` AS `mu_name`,`M`.`mu_section_time` AS `mu_section_time`,`M`.`mu_question` AS `mu_question`,`M`.`mu_start_date` AS `mu_start_date`,`M`.`mu_answer` AS `mu_answer`,`M`.`mu_status` AS `mu_status`,`M`.`mu_date` AS `mu_date`,`M`.`mu_effective_days` AS `mu_effective_days`,`M`.`mu_confirm_code` AS `mu_confirm_code`,`M`.`mu_email_confirm` AS `mu_email_confirm`,`M`.`mu_date_reset_pwd` AS `mu_date_reset_pwd`,`M`.`mu_gender` AS `mu_gender`,`M`.`mu_photo` AS `mu_photo`,`M`.`mu_data_user` AS `mu_data_user`,`M`.`mu_data_password` AS `mu_data_password`,`M`.`mu_expires` AS `mu_expires`,`M`.`mu_date_expires` AS `mu_date_expires`,`M`.`city_ci_city_code` AS `city_ci_city_code`,`M`.`city_states_st_state_code` AS `city_states_st_state_code`,`M`.`city_states_country_co_country_code` AS `city_states_country_co_country_code`,`P`.`co_name` AS `contry_name`,`S`.`st_name` AS `state_name`,`C`.`ci_name` AS `city_name`,`B`.`bu_name` AS `bu_name` from ((((`masterUser` `M` join `country` `P`) join `states` `S`) join `city` `C`) join `bussinessUnit` `B`) where ((`M`.`city_states_country_co_country_code` = `P`.`co_country_code`) and (`M`.`city_states_st_state_code` = `S`.`st_state_code`) and (`M`.`city_ci_city_code` = `C`.`ci_city_code`) and (`M`.`bussinessUnit_bu_bis_code` = `B`.`bu_bis_code`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `CityStateCountry`
--

/*!50001 DROP VIEW IF EXISTS `CityStateCountry`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`boarduser`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `CityStateCountry` AS select `ct`.`ci_city_code` AS `id_city`,`ct`.`states_st_state_code` AS `id_state`,`ct`.`states_country_co_country_code` AS `id_country`,`ct`.`ci_name` AS `city_name`,`st`.`st_name` AS `state_name`,`co`.`co_name` AS `country_name` from ((`city` `ct` join `states` `st`) join `country` `co`) where ((`ct`.`states_st_state_code` = `st`.`st_state_code`) and (`ct`.`states_country_co_country_code` = `co`.`co_country_code`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `UserRoleView`
--

/*!50001 DROP VIEW IF EXISTS `UserRoleView`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`boarduser`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `UserRoleView` AS select `UR`.`masterUser_mu_email` AS `masterUser_mu_email`,`UR`.`masterUser_bussinessUnit_bu_bis_code` AS `masterUser_bussinessUnit_bu_bis_code`,`UR`.`project_pr_id_project` AS `project_pr_id_project`,`UR`.`um_role` AS `um_role`,`UR`.`um_status` AS `um_status`,`MU`.`mu_name` AS `user_name`,`BU`.`bu_name` AS `unit_name`,`PR`.`pr_name` AS `proj_name` from (((`userRole` `UR` join `masterUser` `MU`) join `bussinessUnit` `BU`) join `project` `PR`) where ((`UR`.`masterUser_mu_email` = `MU`.`mu_email`) and (`UR`.`masterUser_bussinessUnit_bu_bis_code` = `MU`.`bussinessUnit_bu_bis_code`) and (`UR`.`masterUser_bussinessUnit_bu_bis_code` = `BU`.`bu_bis_code`) and (`UR`.`project_pr_id_project` = `PR`.`pr_id_project`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Dumping routines for database 'objectboard_db'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-07-31 12:30:12
