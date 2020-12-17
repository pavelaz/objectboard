package com.psg.objectboard.model.own.ownsEntity.classVO;

public class CountryVO {
    private long coCountryCode;
    private String coName;
    private Boolean result;
    private String resultMsg;

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public long getCoCountryCode() {
        return coCountryCode;
    }

    public void setCoCountryCode(long coCountryCode) {
        this.coCountryCode = coCountryCode;
    }

    public String getCoName() {
        return coName;
    }

    public void setCoName(String coName) {
        this.coName = coName;
    }
}
