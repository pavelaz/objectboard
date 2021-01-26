package com.psg.objectboard.model.own.ownsEntity.classVO;

import java.io.InputStream;
import java.sql.Blob;

public class BodyConductSurveyVO {
    private Long headerConductSurveyConductId;
    private Long  bsaBodySurveyQuestionsQuestionCode;
    private Long  bsaBodySurveyQuestionsHeadersSurveySurveyCode;
    private Long  bsaBodySurveyQuestionsHeadersSurveyBussinessUnitBuBisCode;
    private String bcsAnswerOnlyText;
    private Double bcsAnswerOnlyNumber;
    private String  bcsAnswerOnlyDate;
    private String  bcsAnswerOnlyTime;
    private String  bcsComment;
    private String  bcsAnswer;
    private Integer bcsTypeRequest;
    private String  bcsNameAnnexFile;
    private String bcsAnnexType;
    private String bcsAnswerSolution;
    private String StatusAudit;
    private String ResultAudit;
    private Double points;
    private String auditNote;
    private String statusRank;
    private Double rankMin;
    private Double rankMax;
    private Blob bcsAnnexFile;
    private String rutaAnnex;
    private byte[] bcsAnnexFileByte;
    private InputStream bcsAnnexFileInput;
    private Boolean result;

    public Long getHeaderConductSurveyConductId() {
        return headerConductSurveyConductId;
    }

    public void setHeaderConductSurveyConductId(Long headerConductSurveyConductId) {
        this.headerConductSurveyConductId = headerConductSurveyConductId;
    }

    public Long getBsaBodySurveyQuestionsQuestionCode() {
        return bsaBodySurveyQuestionsQuestionCode;
    }

    public void setBsaBodySurveyQuestionsQuestionCode(Long bsaBodySurveyQuestionsQuestionCode) {
        this.bsaBodySurveyQuestionsQuestionCode = bsaBodySurveyQuestionsQuestionCode;
    }

    public Long getBsaBodySurveyQuestionsHeadersSurveySurveyCode() {
        return bsaBodySurveyQuestionsHeadersSurveySurveyCode;
    }

    public void setBsaBodySurveyQuestionsHeadersSurveySurveyCode(Long bsaBodySurveyQuestionsHeadersSurveySurveyCode) {
        this.bsaBodySurveyQuestionsHeadersSurveySurveyCode = bsaBodySurveyQuestionsHeadersSurveySurveyCode;
    }

    public Long getBsaBodySurveyQuestionsHeadersSurveyBussinessUnitBuBisCode() {
        return bsaBodySurveyQuestionsHeadersSurveyBussinessUnitBuBisCode;
    }

    public void setBsaBodySurveyQuestionsHeadersSurveyBussinessUnitBuBisCode(Long bsaBodySurveyQuestionsHeadersSurveyBussinessUnitBuBisCode) {
        this.bsaBodySurveyQuestionsHeadersSurveyBussinessUnitBuBisCode = bsaBodySurveyQuestionsHeadersSurveyBussinessUnitBuBisCode;
    }

    public String getBcsAnswerOnlyText() {
        return bcsAnswerOnlyText;
    }

    public void setBcsAnswerOnlyText(String bcsAnswerOnlyText) {
        this.bcsAnswerOnlyText = bcsAnswerOnlyText;
    }

    public Double getBcsAnswerOnlyNumber() {
        return bcsAnswerOnlyNumber;
    }

    public void setBcsAnswerOnlyNumber(Double bcsAnswerOnlyNumber) {
        this.bcsAnswerOnlyNumber = bcsAnswerOnlyNumber;
    }

    public String getBcsAnswerOnlyDate() {
        return bcsAnswerOnlyDate;
    }

    public void setBcsAnswerOnlyDate(String bcsAnswerOnlyDate) {
        this.bcsAnswerOnlyDate = bcsAnswerOnlyDate;
    }

    public String getBcsAnswerOnlyTime() {
        return bcsAnswerOnlyTime;
    }

    public void setBcsAnswerOnlyTime(String bcsAnswerOnlyTime) {
        this.bcsAnswerOnlyTime = bcsAnswerOnlyTime;
    }

    public String getBcsComment() {
        return bcsComment;
    }

    public void setBcsComment(String bcsComment) {
        this.bcsComment = bcsComment;
    }

    public String getBcsAnswer() {
        return bcsAnswer;
    }

    public void setBcsAnswer(String bcsAnswer) {
        this.bcsAnswer = bcsAnswer;
    }

    public Integer getBcsTypeRequest() {
        return bcsTypeRequest;
    }

    public void setBcsTypeRequest(Integer bcsTypeRequest) {
        this.bcsTypeRequest = bcsTypeRequest;
    }

    public String getBcsNameAnnexFile() {
        return bcsNameAnnexFile;
    }

    public void setBcsNameAnnexFile(String bcsNameAnnexFile) {
        this.bcsNameAnnexFile = bcsNameAnnexFile;
    }

    public String getBcsAnnexType() {
        return bcsAnnexType;
    }

    public void setBcsAnnexType(String bcsAnnexType) {
        this.bcsAnnexType = bcsAnnexType;
    }

    public String getBcsAnswerSolution() {
        return bcsAnswerSolution;
    }

    public void setBcsAnswerSolution(String bcsAnswerSolution) {
        this.bcsAnswerSolution = bcsAnswerSolution;
    }

    public String getStatusAudit() {
        return StatusAudit;
    }

    public void setStatusAudit(String statusAudit) {
        StatusAudit = statusAudit;
    }

    public String getResultAudit() {
        return ResultAudit;
    }

    public void setResultAudit(String resultAudit) {
        ResultAudit = resultAudit;
    }

    public Double getPoints() {
        return points;
    }

    public void setPoints(Double points) {
        this.points = points;
    }

    public String getAuditNote() {
        return auditNote;
    }

    public void setAuditNote(String auditNote) {
        this.auditNote = auditNote;
    }

    public String getStatusRank() {
        return statusRank;
    }

    public void setStatusRank(String statusRank) {
        this.statusRank = statusRank;
    }

    public Double getRankMin() {
        return rankMin;
    }

    public void setRankMin(Double rankMin) {
        this.rankMin = rankMin;
    }

    public Double getRankMax() {
        return rankMax;
    }

    public void setRankMax(Double rankMax) {
        this.rankMax = rankMax;
    }

    public Blob getBcsAnnexFile() {
        return bcsAnnexFile;
    }

    public void setBcsAnnexFile(Blob bcsAnnexFile) {
        this.bcsAnnexFile = bcsAnnexFile;
    }

    public String getRutaAnnex() {
        return rutaAnnex;
    }

    public void setRutaAnnex(String rutaAnnex) {
        this.rutaAnnex = rutaAnnex;
    }

    public byte[] getBcsAnnexFileByte() {
        return bcsAnnexFileByte;
    }

    public void setBcsAnnexFileByte(byte[] bcsAnnexFileByte) {
        this.bcsAnnexFileByte = bcsAnnexFileByte;
    }

    public InputStream getBcsAnnexFileInput() {
        return bcsAnnexFileInput;
    }

    public void setBcsAnnexFileInput(InputStream bcsAnnexFileInput) {
        this.bcsAnnexFileInput = bcsAnnexFileInput;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }
}
