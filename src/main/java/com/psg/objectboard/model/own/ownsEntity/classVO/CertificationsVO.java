package com.psg.objectboard.model.own.ownsEntity.classVO;

public class CertificationsVO {
    private Long certificationCode;
    private Long bussinessUnitBuBisCode;
    private String name;
    private Boolean result;

    public Long getCertificationCode() {
        return certificationCode;
    }

    public void setCertificationCode(Long certificationCode) {
        this.certificationCode = certificationCode;
    }

    public Long getBussinessUnitBuBisCode() {
        return bussinessUnitBuBisCode;
    }

    public void setBussinessUnitBuBisCode(Long bussinessUnitBuBisCode) {
        this.bussinessUnitBuBisCode = bussinessUnitBuBisCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }
}
