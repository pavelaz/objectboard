package com.psg.objectboard.model.own.ownsEntity.classVO;

public class CityVO {
    private long ciCityCode;
    private long statesStStateCode;
    private long statesCountryCoCountryCode;
    private String ciName;
    private Boolean result;

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public long getCiCityCode() {
        return ciCityCode;
    }

    public void setCiCityCode(long ciCityCode) {
        this.ciCityCode = ciCityCode;
    }

    public long getStatesStStateCode() {
        return statesStStateCode;
    }

    public void setStatesStStateCode(long statesStStateCode) {
        this.statesStStateCode = statesStStateCode;
    }

    public long getStatesCountryCoCountryCode() {
        return statesCountryCoCountryCode;
    }

    public void setStatesCountryCoCountryCode(long statesCountryCoCountryCode) {
        this.statesCountryCoCountryCode = statesCountryCoCountryCode;
    }

    public String getCiName() {
        return ciName;
    }

    public void setCiName(String ciName) {
        this.ciName = ciName;
    }
}
