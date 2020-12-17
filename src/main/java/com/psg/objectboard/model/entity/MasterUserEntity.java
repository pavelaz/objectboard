package com.psg.objectboard.model.entity;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name="MasterUserDetails.byId", query="select j from MasterUserEntity  j where j.bussinessUnitBuBisCode=:BusBisCode and j.muEmail=:muEmail"),
        @NamedQuery(name="MasterUserPhoto.byId", query="select j.muPhoto from MasterUserEntity  j where j.bussinessUnitBuBisCode=:BusBisCode and j.muEmail=:muEmail")
})
@Table(name = "masterUser", schema = "objectboard_db", catalog = "")
public class MasterUserEntity {
    private String muEmail;
    private long bussinessUnitBuBisCode;//@Transient
    private String muPassword;
    private String muPasswordOld;//@Transient
    private String muName;
    private Integer muSectionTime;
    private String muQuestion;
    private String muStartDate;
    private String muAnswer;
    private String muStatus;
    private String muDate;
    private int muEffectiveDays;
    private String muConfirmCode;
    private String muEmailConfirm;
    private String muDateResetPwd;
    private String muGender;
    private byte[] muPhoto;
    private String muDataUser;
    private String muDataPassword;
    private String muExpires;
    private String muDateExpires;
    private long cityCiCityCode;
    private long cityStatesStStateCode;
    private long cityStatesCountryCoCountryCode;

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mu_email")
    public String getMuEmail() {
        return muEmail;
    }

    public void setMuEmail(String muEmail) {
        this.muEmail = muEmail;
    }

    @Basic
    @Column(name = "bussinessUnit_bu_bis_code")
    public long getBussinessUnitBuBisCode() {
        return bussinessUnitBuBisCode;
    }

    public void setBussinessUnitBuBisCode(long bussinessUnitBuBisCode) {
        this.bussinessUnitBuBisCode = bussinessUnitBuBisCode;
    }

    @Basic
    @Column(name = "mu_password")
    public String getMuPassword() {
        return muPassword;
    }

    public void setMuPassword(String muPassword) {
        this.muPassword = muPassword;
    }


    /*@Basic
    @Column(name = "mu_password_old")*/
    @Transient
    public String getMuPasswordOld() {
        return muPasswordOld;
    }

    public void setMuPasswordOld(String muPasswordOld) {
        this.muPasswordOld = muPasswordOld;
    }

    @Basic
    @Column(name = "mu_name")
    public String getMuName() {
        return muName;
    }

    public void setMuName(String muName) {
        this.muName = muName;
    }

    @Basic
    @Column(name = "mu_section_time")
    public Integer getMuSectionTime() {
        return muSectionTime;
    }

    public void setMuSectionTime(Integer muSectionTime) {
        this.muSectionTime = muSectionTime;
    }

    @Basic
    @Column(name = "mu_question")
    public String getMuQuestion() {
        return muQuestion;
    }

    public void setMuQuestion(String muQuestion) {
        this.muQuestion = muQuestion;
    }

    @Basic
    @Column(name = "mu_start_date")
    public String getMuStartDate() {
        return muStartDate;
    }

    public void setMuStartDate(String muStartDate) {
        this.muStartDate = muStartDate;
    }

    @Basic
    @Column(name = "mu_answer")
    public String getMuAnswer() {
        return muAnswer;
    }

    public void setMuAnswer(String muAnswer) {
        this.muAnswer = muAnswer;
    }

    @Basic
    @Column(name = "mu_status")
    public String getMuStatus() {
        return muStatus;
    }

    public void setMuStatus(String muStatus) {
        this.muStatus = muStatus;
    }

    @Basic
    @Column(name = "mu_date")
    public String getMuDate() {
        return muDate;
    }

    public void setMuDate(String muDate) {
        this.muDate = muDate;
    }

    @Basic
    @Column(name = "mu_effective_days")
    public int getMuEffectiveDays() {
        return muEffectiveDays;
    }

    public void setMuEffectiveDays(int muEffectiveDays) {
        this.muEffectiveDays = muEffectiveDays;
    }

    @Basic
    @Column(name = "mu_confirm_code")
    public String getMuConfirmCode() {
        return muConfirmCode;
    }

    public void setMuConfirmCode(String muConfirmCode) {
        this.muConfirmCode = muConfirmCode;
    }

    @Basic
    @Column(name = "mu_email_confirm")
    public String getMuEmailConfirm() {
        return muEmailConfirm;
    }

    public void setMuEmailConfirm(String muEmailConfirm) {
        this.muEmailConfirm = muEmailConfirm;
    }

    @Basic
    @Column(name = "mu_date_reset_pwd")
    public String getMuDateResetPwd() {
        return muDateResetPwd;
    }

    public void setMuDateResetPwd(String muDateResetPwd) {
        this.muDateResetPwd = muDateResetPwd;
    }

    @Basic
    @Column(name = "mu_gender")
    public String getMuGender() {
        return muGender;
    }

    public void setMuGender(String muGender) {
        this.muGender = muGender;
    }

    @Basic(fetch=FetchType.LAZY)
    @Column(name="mu_Photo",length = 10000)
    public byte[] getMuPhoto() {
        return muPhoto;
    }

    public void setMuPhoto(byte[] muPhoto) {
        this.muPhoto = muPhoto;
    }

    @Basic
    @Column(name = "mu_data_user")
    public String getMuDataUser() {
        return muDataUser;
    }

    public void setMuDataUser(String muDataUser) {
        this.muDataUser = muDataUser;
    }

    @Basic
    @Column(name = "mu_data_password")
    public String getMuDataPassword() {
        return muDataPassword;
    }

    public void setMuDataPassword(String muDataPassword) {
        this.muDataPassword = muDataPassword;
    }

    @Basic
    @Column(name = "mu_expires")
    public String getMuExpires() {
        return muExpires;
    }

    public void setMuExpires(String muExpires) {
        this.muExpires = muExpires;
    }

    @Basic
    @Column(name = "mu_date_expires")
    public String getMuDateExpires() {
        return muDateExpires;
    }

    public void setMuDateExpires(String muDateExpires) {
        this.muDateExpires = muDateExpires;
    }

    @Basic
    @Column(name = "city_ci_city_code")
    public long getCityCiCityCode() {
        return cityCiCityCode;
    }

    public void setCityCiCityCode(long cityCiCityCode) {
        this.cityCiCityCode = cityCiCityCode;
    }

    @Basic
    @Column(name = "city_states_st_state_code")
    public long getCityStatesStStateCode() {
        return cityStatesStStateCode;
    }

    public void setCityStatesStStateCode(long cityStatesStStateCode) {
        this.cityStatesStStateCode = cityStatesStStateCode;
    }

    @Basic
    @Column(name = "city_states_country_co_country_code")
    public long getCityStatesCountryCoCountryCode() {
        return cityStatesCountryCoCountryCode;
    }

    public void setCityStatesCountryCoCountryCode(long cityStatesCountryCoCountryCode) {
        this.cityStatesCountryCoCountryCode = cityStatesCountryCoCountryCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MasterUserEntity that = (MasterUserEntity) o;
        return bussinessUnitBuBisCode == that.bussinessUnitBuBisCode &&
                muEffectiveDays == that.muEffectiveDays &&
                cityCiCityCode == that.cityCiCityCode &&
                cityStatesStStateCode == that.cityStatesStStateCode &&
                cityStatesCountryCoCountryCode == that.cityStatesCountryCoCountryCode &&
                Objects.equals(muEmail, that.muEmail) &&
                Objects.equals(muPassword, that.muPassword) &&
                Objects.equals(muPasswordOld, that.muPasswordOld) &&
                Objects.equals(muName, that.muName) &&
                Objects.equals(muSectionTime, that.muSectionTime) &&
                Objects.equals(muQuestion, that.muQuestion) &&
                Objects.equals(muStartDate, that.muStartDate) &&
                Objects.equals(muAnswer, that.muAnswer) &&
                Objects.equals(muStatus, that.muStatus) &&
                Objects.equals(muDate, that.muDate) &&
                Objects.equals(muConfirmCode, that.muConfirmCode) &&
                Objects.equals(muEmailConfirm, that.muEmailConfirm) &&
                Objects.equals(muDateResetPwd, that.muDateResetPwd) &&
                Objects.equals(muGender, that.muGender) &&
                Arrays.equals(muPhoto, that.muPhoto) &&
                Objects.equals(muDataUser, that.muDataUser) &&
                Objects.equals(muDataPassword, that.muDataPassword) &&
                Objects.equals(muExpires, that.muExpires) &&
                Objects.equals(muDateExpires, that.muDateExpires);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(muEmail, bussinessUnitBuBisCode, muPassword, muPasswordOld, muName, muSectionTime, muQuestion, muStartDate, muAnswer, muStatus, muDate, muEffectiveDays, muConfirmCode, muEmailConfirm, muDateResetPwd, muGender, muDataUser, muDataPassword, muExpires, muDateExpires, cityCiCityCode, cityStatesStStateCode, cityStatesCountryCoCountryCode);
        result = 31 * result + Arrays.hashCode(muPhoto);
        return result;
    }
}
