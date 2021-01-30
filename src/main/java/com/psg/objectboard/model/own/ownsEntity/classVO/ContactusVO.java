package com.psg.objectboard.model.own.ownsEntity.classVO;

public class ContactusVO {
    private long ctId;
    private String ctPhone;
    private String ctNombre;
    private String ctMessage;
    private String ctDate;
    private Boolean result;

    public long getCtId() {
        return ctId;
    }

    public void setCtId(long ctId) {
        this.ctId = ctId;
    }

    public String getCtPhone() {
        return ctPhone;
    }

    public void setCtPhone(String ctPhone) {
        this.ctPhone = ctPhone;
    }

    public String getCtNombre() {
        return ctNombre;
    }

    public void setCtNombre(String ctNombre) {
        this.ctNombre = ctNombre;
    }

    public String getCtMessage() {
        return ctMessage;
    }

    public void setCtMessage(String ctMessage) {
        this.ctMessage = ctMessage;
    }

    public String getCtDate() {
        return ctDate;
    }

    public void setCtDate(String ctDate) {
        this.ctDate = ctDate;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }
}
