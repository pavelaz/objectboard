package com.psg.objectboard.model.entity;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "bussinessUnit", schema = "objectboard_db", catalog = "")
public class BussinessUnitEntity {
    private long buBisCode;
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
    private long cityCiCityCode;
    private long cityStatesStStateCode;
    private long cityStatesCountryCoCountryCode;
    private long bussinessTypeBtCodeType;
    private String buLogoName;
    /*private MasterUserEntity masterUser;

    @OneToMany
    public MasterUserEntity getMasterUser() {
        return masterUser;
    }

    public void setMasterUser(MasterUserEntity masterUser) {
        this.masterUser = masterUser;
    }*/

    @Basic
    @Column(name = "bu_logo_name", nullable = true, length = 45)
    public String getBuLogoName() {
        return buLogoName;
    }

    public void setBuLogoName(String buLogoName) {
        this.buLogoName = buLogoName;
    }

    @Transient
    @Column(name = "bussinessType_bt_code_type", nullable = true, length = 20)
    public long getBussinessTypeBtCodeType() {
        return bussinessTypeBtCodeType;
    }

    public void setBussinessTypeBtCodeType(long bussinessTypeBtCodeType) {
        this.bussinessTypeBtCodeType = bussinessTypeBtCodeType;
    }

    @Transient
    @Basic
    @Column(name = "city_ci_city_code", nullable= true, length = 20)
    public long getCityCiCityCode() {
        return cityCiCityCode;
    }

    public void setCityCiCityCode(long cityCiCityCode) {
        this.cityCiCityCode = cityCiCityCode;
    }

    @Transient
    @Basic
    @Column(name = "city_states_st_state_code", nullable= true, length = 20)
    public long getCityStatesStStateCode() {
        return cityStatesStStateCode;
    }

    public void setCityStatesStStateCode(long cityStatesStStateCode) {
        this.cityStatesStStateCode = cityStatesStStateCode;
    }

    @Transient
    @Basic
    @Column(name = "city_states_country_co_country_code", nullable= true, length = 20)
    public long getCityStatesCountryCoCountryCode() {
        return cityStatesCountryCoCountryCode;
    }

    public void setCityStatesCountryCoCountryCode(long cityStatesCountryCoCountryCode) {
        this.cityStatesCountryCoCountryCode = cityStatesCountryCoCountryCode;
    }

    @Basic
    @Column(name="bu_user2_code", nullable = false, length = 12)
    public String getBuUser2Code() {
        return buUser2Code;
    }

    public void setBuUser2Code(String buUser2Code) {
        this.buUser2Code = buUser2Code;
    }

    @Basic
    @Column(name = "bu_user1_code", nullable = false, length = 12)
    public String getBuUser1Code() {
        return buUser1Code;
    }

    public void setBuUser1Code(String buUser1Code) {
        this.buUser1Code = buUser1Code;
    }

    @Basic
    @Column(name ="bu_admin_code", nullable = false, length = 12)
    public String getBuAdminCode() {
        return buAdminCode;
    }

    public void setBuAdminCode(String buAdminCode) {
        this.buAdminCode = buAdminCode;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bu_bis_code", nullable = true, length = 20)
    public long getBuBisCode() {
        return buBisCode;
    }
    public void setBuBisCode(long buBisCode) {
        this.buBisCode = buBisCode;
    }

    @Basic
    @Column(name = "bu_federal_number", nullable = false, length = 10)
    public String getBuFederalNumber() {
        return buFederalNumber;
    }

    public void setBuFederalNumber(String buFederalNumber) {
        this.buFederalNumber = buFederalNumber;
    }

    @Basic
    @Column(name = "bu_provincial_number", nullable = false, length = 16)
    public String getBuProvincialNumber() {
        return buProvincialNumber;
    }

    public void setBuProvincialNumber(String buProvincialNumber) {
        this.buProvincialNumber = buProvincialNumber;
    }

    @Basic
    @Column(name = "bu_email", nullable = false, length = 60)
    public String getBuEmail() {
        return buEmail;
    }

    public void setBuEmail(String buEmail) {
        this.buEmail = buEmail;
    }

    @Basic
    @Column(name = "bu_status", nullable = true, length = 1)
    public String getBuStatus() {
        return buStatus;
    }

    public void setBuStatus(String buStatus) {
        this.buStatus = buStatus;
    }

    @Basic
    @Column(name = "bu_super_code", nullable = false, length = 12)
    public String getBuSuperCode() {
        return buSuperCode;
    }

    public void setBuSuperCode(String buSuperCode) {
        this.buSuperCode = buSuperCode;
    }

    @Basic
    @Column(name = "bu_phone", nullable = false, length = 13)
    public String getBuPhone() {
        return buPhone;
    }

    public void setBuPhone(String buPhone) {
        this.buPhone = buPhone;
    }

    @Basic
    @Column(name = "bu_zipcode", nullable = false, length = 6)
    public String getBuZipcode() {
        return buZipcode;
    }

    public void setBuZipcode(String buZipcode) {
        this.buZipcode = buZipcode;
    }

    @Basic
    @Column(name = "bu_name", nullable = false, length = 35)
    public String getBuName() {
        return buName;
    }

    public void setBuName(String buName) {
        this.buName = buName;
    }

    @Basic
    @Column(name = "bu_address", nullable = false, length = 45)
    public String getBuAddress() {
        return buAddress;
    }

    public void setBuAddress(String buAddress) {
        this.buAddress = buAddress;
    }

    @Basic
    @Column(name = "bu_webpage", nullable = false, length = 50)
    public String getBuWebpage() {
        return buWebpage;
    }

    public void setBuWebpage(String buWebpage) {
        this.buWebpage = buWebpage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BussinessUnitEntity that = (BussinessUnitEntity) o;
        return buBisCode == that.buBisCode &&
                Objects.equals(buFederalNumber, that.buFederalNumber) &&
                Objects.equals(buProvincialNumber, that.buProvincialNumber) &&
                Objects.equals(buEmail, that.buEmail) &&
                Objects.equals(buStatus, that.buStatus) &&
                Objects.equals(buSuperCode, that.buSuperCode) &&
                Objects.equals(buPhone, that.buPhone) &&
                Objects.equals(buZipcode, that.buZipcode) &&
                Objects.equals(buName, that.buName) &&
                Objects.equals(buAddress, that.buAddress) &&
                Objects.equals(buWebpage, that.buWebpage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buBisCode, buFederalNumber, buProvincialNumber, buEmail, buStatus, buSuperCode, buPhone, buZipcode, buName, buAddress, buWebpage);
    }
}
