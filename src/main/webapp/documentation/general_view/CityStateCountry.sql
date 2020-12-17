CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `boarduser`@`%` 
    SQL SECURITY DEFINER
VIEW `objectboard_db`.`CityStateCountry` AS
  select ct.ci_city_code AS id_city,
       ct.states_st_state_code AS id_state,
       ct.states_country_co_country_code AS id_country,
       ct.ci_name AS city_name,
       st.st_name AS state_name,
       co.co_name AS contry_name
       FROM objectboard_db.city ct, objectboard_db.states st,  objectboard_db.country co
       WHERE (ct.ci_city_code=st.st_state_code AND ct.states_country_co_country_code=co.co_country_code);