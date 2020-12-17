package com.psg.objectboard.model.own.ownsEntity.classVO;

public class ProcessVO {
    private Long processCode;
    private Long certificationsCertificationCode;
    private Long certificationsBussinessUnitBuBisCode;
    private Long headersSurveySurveyCode;
    private String status;
    private Boolean result;

    public Long getProcessCode() {
        return processCode;
    }

    public void setProcessCode(Long processCode) {
        this.processCode = processCode;
    }

    public Long getCertificationsCertificationCode() {
        return certificationsCertificationCode;
    }

    public void setCertificationsCertificationCode(Long certificationsCertificationCode) {
        this.certificationsCertificationCode = certificationsCertificationCode;
    }

    public Long getCertificationsBussinessUnitBuBisCode() {
        return certificationsBussinessUnitBuBisCode;
    }

    public void setCertificationsBussinessUnitBuBisCode(Long certificationsBussinessUnitBuBisCode) {
        this.certificationsBussinessUnitBuBisCode = certificationsBussinessUnitBuBisCode;
    }

    public Long getHeadersSurveySurveyCode() {
        return headersSurveySurveyCode;
    }

    public void setHeadersSurveySurveyCode(Long headersSurveySurveyCode) {
        this.headersSurveySurveyCode = headersSurveySurveyCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }
}
