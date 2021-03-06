package com.psg.objectboard.model.own.ownsEntity.classViewVO;

import java.io.InputStream;
import java.sql.Blob;

public class BussinessUnitCoStCiVO {
    private Long buBisCode;
    private String buFederalNumber;
    private String buProvincialNumber;
    private String buEmail;
    private String buStatus;
    private String buSuperCode;
    private String buPhone;
    private String buZipcode;
    private String buName;
    private String buAddress;
    private String buWebpage;
    private String buAdminCode;
    private String buUser1Code;
    private String buUser2Code;
    private Integer cityCiCityCode;
    private Integer cityStatesStStateCode;
    private Integer cityStatesCountryCoCountryCode;
    private Integer bussinessTypeBtCodeType;
    private String cityName;
    private String stateName;
    private String countryName;
    private String typeName;
    private String buLogoName;
    private Blob buLogoImage;
    private String ruta_imagen;
    private byte[] buLogoImageByte;
    private InputStream buLogoImageInput;

    public Blob getBuLogoImage() {
        return buLogoImage;
    }

    public void setBuLogoImage(Blob buLogoImage) {
        this.buLogoImage = buLogoImage;
    }

    public String getRuta_imagen() {
        return ruta_imagen;
    }

    public void setRuta_imagen(String ruta_imagen) {
        this.ruta_imagen = ruta_imagen;
    }

    public byte[] getBuLogoImageByte() {
        return buLogoImageByte;
    }

    public void setBuLogoImageByte(byte[] buLogoImageByte) {
        this.buLogoImageByte = buLogoImageByte;
    }

    public InputStream getBuLogoImageInput() {
        return buLogoImageInput;
    }

    public void setBuLogoImageInput(InputStream buLogoImageInput) {
        this.buLogoImageInput = buLogoImageInput;
    }

    public String getBuLogoName() {
        return buLogoName;
    }

    public void setBuLogoName(String buLogoName) {
        this.buLogoName = buLogoName;
    }

    public Long getBuBisCode() {
        return buBisCode;
    }

    public void setBuBisCode(Long buBisCode) {
        this.buBisCode = buBisCode;
    }

    public String getBuFederalNumber() {
        return buFederalNumber;
    }

    public void setBuFederalNumber(String buFederalNumber) {
        this.buFederalNumber = buFederalNumber;
    }

    public String getBuProvincialNumber() {
        return buProvincialNumber;
    }

    public void setBuProvincialNumber(String buProvincialNumber) {
        this.buProvincialNumber = buProvincialNumber;
    }

    public String getBuEmail() {
        return buEmail;
    }

    public void setBuEmail(String buEmail) {
        this.buEmail = buEmail;
    }

    public String getBuStatus() {
        return buStatus;
    }

    public void setBuStatus(String buStatus) {
        this.buStatus = buStatus;
    }

    public String getBuSuperCode() {
        return buSuperCode;
    }

    public void setBuSuperCode(String buSuperCode) {
        this.buSuperCode = buSuperCode;
    }

    public String getBuPhone() {
        return buPhone;
    }

    public void setBuPhone(String buPhone) {
        this.buPhone = buPhone;
    }

    public String getBuZipcode() {
        return buZipcode;
    }

    public void setBuZipcode(String buZipcode) {
        this.buZipcode = buZipcode;
    }

    public String getBuName() {
        return buName;
    }

    public void setBuName(String buName) {
        this.buName = buName;
    }

    public String getBuAddress() {
        return buAddress;
    }

    public void setBuAddress(String buAddress) {
        this.buAddress = buAddress;
    }

    public String getBuWebpage() {
        return buWebpage;
    }

    public void setBuWebpage(String buWebpage) {
        this.buWebpage = buWebpage;
    }

    public String getBuAdminCode() {
        return buAdminCode;
    }

    public void setBuAdminCode(String buAdminCode) {
        this.buAdminCode = buAdminCode;
    }

    public String getBuUser1Code() {
        return buUser1Code;
    }

    public void setBuUser1Code(String buUser1Code) {
        this.buUser1Code = buUser1Code;
    }

    public String getBuUser2Code() {
        return buUser2Code;
    }

    public void setBuUser2Code(String buUser2Code) {
        this.buUser2Code = buUser2Code;
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

    public Integer getBussinessTypeBtCodeType() {
        return bussinessTypeBtCodeType;
    }

    public void setBussinessTypeBtCodeType(Integer bussinessTypeBtCodeType) {
        this.bussinessTypeBtCodeType = bussinessTypeBtCodeType;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
