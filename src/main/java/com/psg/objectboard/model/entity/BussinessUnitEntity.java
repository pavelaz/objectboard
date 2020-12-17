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

    @Id
    @Column(name = "bu_bis_code")
    public long getBuBisCode() {
        return buBisCode;
    }
    public void setBuBisCode(long buBisCode) {
        this.buBisCode = buBisCode;
    }

    @Basic
    @Column(name = "bu_federal_number")
    public String getBuFederalNumber() {
        return buFederalNumber;
    }

    public void setBuFederalNumber(String buFederalNumber) {
        this.buFederalNumber = buFederalNumber;
    }

    @Basic
    @Column(name = "bu_provincial_number")
    public String getBuProvincialNumber() {
        return buProvincialNumber;
    }

    public void setBuProvincialNumber(String buProvincialNumber) {
        this.buProvincialNumber = buProvincialNumber;
    }

    @Basic
    @Column(name = "bu_email")
    public String getBuEmail() {
        return buEmail;
    }

    public void setBuEmail(String buEmail) {
        this.buEmail = buEmail;
    }

    @Basic
    @Column(name = "bu_status")
    public String getBuStatus() {
        return buStatus;
    }

    public void setBuStatus(String buStatus) {
        this.buStatus = buStatus;
    }

    @Basic
    @Column(name = "bu_super_code")
    public String getBuSuperCode() {
        return buSuperCode;
    }

    public void setBuSuperCode(String buSuperCode) {
        this.buSuperCode = buSuperCode;
    }

    @Basic
    @Column(name = "bu_phone")
    public String getBuPhone() {
        return buPhone;
    }

    public void setBuPhone(String buPhone) {
        this.buPhone = buPhone;
    }

    @Basic
    @Column(name = "bu_zipcode")
    public String getBuZipcode() {
        return buZipcode;
    }

    public void setBuZipcode(String buZipcode) {
        this.buZipcode = buZipcode;
    }

    @Basic
    @Column(name = "bu_name")
    public String getBuName() {
        return buName;
    }

    public void setBuName(String buName) {
        this.buName = buName;
    }

    @Basic
    @Column(name = "bu_address")
    public String getBuAddress() {
        return buAddress;
    }

    public void setBuAddress(String buAddress) {
        this.buAddress = buAddress;
    }

    @Basic
    @Column(name = "bu_webpage")
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
