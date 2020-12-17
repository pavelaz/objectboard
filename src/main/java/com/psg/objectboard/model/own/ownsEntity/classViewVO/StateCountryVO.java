package com.psg.objectboard.model.own.ownsEntity.classViewVO;

public class StateCountryVO {
    private long id_state;
    private String state_name;
    private long id_country;
    private String country_name;
    private Boolean result;

    public long getId_state() {
        return id_state;
    }

    public void setId_state(long id_state) {
        this.id_state = id_state;
    }

    public String getState_name() {
        return state_name;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }

    public long getId_country() {
        return id_country;
    }

    public void setId_country(long id_country) {
        this.id_country = id_country;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }
}
