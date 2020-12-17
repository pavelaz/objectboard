package com.psg.objectboard.model.own.ownsEntity.classVO;

public class AssignmentsVO {
    private String masterUserMuEmail;
    private Long masterUserBussinessUnitBuBisCode;
    private Long headersSurveySurveyCode;
    private String dateStart;
    private Integer frecuency;
    private Integer timeByFrecuency;
    private Long notes;
    private Long legends;
    private Long exceptions;
    private Long terms;
    private String reference;
    private Boolean result;

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getMasterUserMuEmail() {
        return masterUserMuEmail;
    }

    public void setMasterUserMuEmail(String masterUserMuEmail) {
        this.masterUserMuEmail = masterUserMuEmail;
    }

    public Long getMasterUserBussinessUnitBuBisCode() {
        return masterUserBussinessUnitBuBisCode;
    }

    public void setMasterUserBussinessUnitBuBisCode(Long masterUserBussinessUnitBuBisCode) {
        this.masterUserBussinessUnitBuBisCode = masterUserBussinessUnitBuBisCode;
    }

    public Long getHeadersSurveySurveyCode() {
        return headersSurveySurveyCode;
    }

    public void setHeadersSurveySurveyCode(Long headersSurveySurveyCode) {
        this.headersSurveySurveyCode = headersSurveySurveyCode;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public Integer getFrecuency() {
        return frecuency;
    }

    public void setFrecuency(Integer frecuency) {
        this.frecuency = frecuency;
    }

    public Integer getTimeByFrecuency() {
        return timeByFrecuency;
    }

    public void setTimeByFrecuency(Integer timeByFrecuency) {
        this.timeByFrecuency = timeByFrecuency;
    }

    public Long getNotes() {
        return notes;
    }

    public void setNotes(Long notes) {
        this.notes = notes;
    }

    public Long getLegends() {
        return legends;
    }

    public void setLegends(Long legends) {
        this.legends = legends;
    }

    public Long getExceptions() {
        return exceptions;
    }

    public void setExceptions(Long exceptions) {
        this.exceptions = exceptions;
    }

    public Long getTerms() {
        return terms;
    }

    public void setTerms(Long terms) {
        this.terms = terms;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }
}
