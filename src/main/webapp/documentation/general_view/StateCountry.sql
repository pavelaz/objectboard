CREATE VIEW `StateCountry` AS
    SELECT 
        st_state_code AS id_state,
        st_name AS state_name,
        co_country_code AS id_country,
        co_name AS country_name
    FROM
        states,
        country
    WHERE
        country_co_country_code = co_country_code;