package com.psg.objectboard.model.own.ownsEntity.classVO;

import java.io.InputStream;
import java.sql.Blob;

public class MasterUserVO {
    private String muEmail;
    private long bussinessUnitBuBisCode;
    private String muPassword;
    private String muPasswordOld;
    private String muName;
    private Integer muSectionTime;
    private String muQuestion;
    private String muAnswer;
    private String muStartDate;
    private String muStatus;
    private String muDate;
    private Integer muEffectiveDays;
    private String muConfirmCode;
    private String muEmailConfirm;
    private String muDateResetPwd;
    private String muGender;
    private Blob muPhoto;
    private String muDataUser;
    private String muDataPassword;
    private String muExpires;
    private String muDateExpires;
    private Integer cityCiCityCode;
    private Integer cityStatesStStateCode;
    private Integer cityStatesCountryCoCountryCode;

    private Boolean result;
    private String resultMsg;
    private String ruta_imagen;
    private byte[] muPhotoByte;
    private InputStream muPhotoInput;

    public InputStream getMuPhotoInput() {
        return muPhotoInput;
    }

    public void setMuPhotoInput(InputStream muPhotoInput) {
        this.muPhotoInput = muPhotoInput;
    }

    public byte[] getMuPhotoByte() {
        return muPhotoByte;
    }

    public void setMuPhotoByte(byte[] muPhotoByte) {
        this.muPhotoByte = muPhotoByte;
    }

    public String getMuEmail() {
        return muEmail;
    }

    public void setMuEmail(String muEmail) {
        this.muEmail = muEmail;
    }

    public long getBussinessUnitBuBisCode() {
        return bussinessUnitBuBisCode;
    }

    public void setBussinessUnitBuBisCode(long bussinessUnitBuBisCode) {
        this.bussinessUnitBuBisCode = bussinessUnitBuBisCode;
    }

    public String getMuPassword() {
        return muPassword;
    }

    public void setMuPassword(String muPassword) {
        this.muPassword = muPassword;
    }

    public String getMuPasswordOld() {
        return muPasswordOld;
    }

    public void setMuPasswordOld(String muPasswordOld) {
        this.muPasswordOld = muPasswordOld;
    }

    public String getMuName() {
        return muName;
    }

    public void setMuName(String muName) {
        this.muName = muName;
    }

    public Integer getMuSectionTime() {
        return muSectionTime;
    }

    public void setMuSectionTime(Integer muSectionTime) {
        this.muSectionTime = muSectionTime;
    }

    public String getMuQuestion() {
        return muQuestion;
    }

    public void setMuQuestion(String muQuestion) {
        this.muQuestion = muQuestion;
    }

    public String getMuAnswer() {
        return muAnswer;
    }

    public void setMuAnswer(String muAnswer) {
        this.muAnswer = muAnswer;
    }

    public String getMuStartDate() {
        return muStartDate;
    }

    public void setMuStartDate(String muStartDate) {
        this.muStartDate = muStartDate;
    }

    public String getMuStatus() {
        return muStatus;
    }

    public void setMuStatus(String muStatus) {
        this.muStatus = muStatus;
    }

    public String getMuDate() {
        return muDate;
    }

    public void setMuDate(String muDate) {
        this.muDate = muDate;
    }

    public Integer getMuEffectiveDays() {
        return muEffectiveDays;
    }

    public void setMuEffectiveDays(Integer muEffectiveDays) {
        this.muEffectiveDays = muEffectiveDays;
    }

    public String getMuConfirmCode() {
        return muConfirmCode;
    }

    public void setMuConfirmCode(String muConfirmCode) {
        this.muConfirmCode = muConfirmCode;
    }

    public String getMuEmailConfirm() {
        return muEmailConfirm;
    }

    public void setMuEmailConfirm(String muEmailConfirm) {
        this.muEmailConfirm = muEmailConfirm;
    }

    public String getMuDateResetPwd() {
        return muDateResetPwd;
    }

    public void setMuDateResetPwd(String muDateResetPwd) {
        this.muDateResetPwd = muDateResetPwd;
    }

    public String getMuGender() {
        return muGender;
    }

    public void setMuGender(String muGender) {
        this.muGender = muGender;
    }

    public Blob getMuPhoto() {
        return muPhoto;
    }

    public void setMuPhoto(Blob muPhoto) {
        this.muPhoto = muPhoto;
    }

    public String getMuDataUser() {
        return muDataUser;
    }

    public void setMuDataUser(String muDataUser) {
        this.muDataUser = muDataUser;
    }

    public String getMuDataPassword() {
        return muDataPassword;
    }

    public void setMuDataPassword(String muDataPassword) {
        this.muDataPassword = muDataPassword;
    }

    public String getMuExpires() {
        return muExpires;
    }

    public void setMuExpires(String muExpires) {
        this.muExpires = muExpires;
    }

    public String getMuDateExpires() {
        return muDateExpires;
    }

    public void setMuDateExpires(String muDateExpires) {
        this.muDateExpires = muDateExpires;
    }

    public Integer getCityCiCityCode() {
        return cityCiCityCode;
    }

    public void setCityCiCityCode(Integer cityCiCityCode) {
        this.cityCiCityCode = cityCiCityCode;
    }

    public Integer getCityStatesStStateCode() {
        return cityStatesStStateCode;
    }

    public void setCityStatesStStateCode(Integer cityStatesStStateCode) {
        this.cityStatesStStateCode = cityStatesStStateCode;
    }

    public Integer getCityStatesCountryCoCountryCode() {
        return cityStatesCountryCoCountryCode;
    }

    public void setCityStatesCountryCoCountryCode(Integer cityStatesCountryCoCountryCode) {
        this.cityStatesCountryCoCountryCode = cityStatesCountryCoCountryCode;
    }

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

    public String getRuta_imagen() {
        return ruta_imagen;
    }

    public void setRuta_imagen(String ruta_imagen) {
        this.ruta_imagen = ruta_imagen;
    }
}
