package com.psg.objectboard.model.own.ownsEntity.classVO.OtherVO;

public class MailSendVO {
    private String SMTP_SERVER = null;
    private String USERNAME = null;
    private String PASSWORD = null;

    private String EMAIL_FROM = null;
    private String EMAIL_TO = null;
    private String EMAIL_TO_CC = null;
    private String EMAIL_TO_BCC = null;

    private String EMAIL_SUBJECT = null;
    private String EMAIL_TEXT = null;
    private String EMAIL_RUTARCH = null;

    public String getEMAIL_RUTARCH() {
        return EMAIL_RUTARCH;
    }

    public void setEMAIL_RUTARCH(String EMAIL_RUTARCH) {
        this.EMAIL_RUTARCH = EMAIL_RUTARCH;
    }

    public String getEMAIL_TO_BCC() {
        return EMAIL_TO_BCC;
    }

    public void setEMAIL_TO_BCC(String EMAIL_TO_BCC) {
        this.EMAIL_TO_BCC = EMAIL_TO_BCC;
    }

    public String getSMTP_SERVER() {
        return SMTP_SERVER;
    }

    public void setSMTP_SERVER(String SMTP_SERVER) {
        this.SMTP_SERVER = SMTP_SERVER;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public String getEMAIL_FROM() {
        return EMAIL_FROM;
    }

    public void setEMAIL_FROM(String EMAIL_FROM) {
        this.EMAIL_FROM = EMAIL_FROM;
    }

    public String getEMAIL_TO() {
        return EMAIL_TO;
    }

    public void setEMAIL_TO(String EMAIL_TO) {
        this.EMAIL_TO = EMAIL_TO;
    }

    public String getEMAIL_TO_CC() {
        return EMAIL_TO_CC;
    }

    public void setEMAIL_TO_CC(String EMAIL_TO_CC) {
        this.EMAIL_TO_CC = EMAIL_TO_CC;
    }

    public String getEMAIL_SUBJECT() {
        return EMAIL_SUBJECT;
    }

    public void setEMAIL_SUBJECT(String EMAIL_SUBJECT) {
        this.EMAIL_SUBJECT = EMAIL_SUBJECT;
    }

    public String getEMAIL_TEXT() {
        return EMAIL_TEXT;
    }

    public void setEMAIL_TEXT(String EMAIL_TEXT) {
        this.EMAIL_TEXT = EMAIL_TEXT;
    }

}
