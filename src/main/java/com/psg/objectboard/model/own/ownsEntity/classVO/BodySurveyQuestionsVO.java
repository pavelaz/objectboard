package com.psg.objectboard.model.own.ownsEntity.classVO;

import java.io.InputStream;
import java.sql.Blob;

public class BodySurveyQuestionsVO {
    private Long questionCode;
    private Long headersSurveySurveyCode;
    private Long headersSurveyBussinessUnitBuBisCode;
    private Integer typeRequest;
    private String mainRequest;
    private String AnnexType;
    private String bodyAnnexDoc;
    private String bodyAnnexPhoto;
    private String comment;
    private Integer answerNumber;
    private Integer solutionNumber;
    private String auditableSolution;
    private String auditableAnswerSolution;
    private Double questionPoints;
    private String statusRank;
    private Blob questionImageFile;
    private byte[] questionImageFileByte;
    private InputStream questionImageFileInput;
    private String questionImageName;
    private String ruta_image;
    private Boolean result;

    public String getRuta_image() {
        return ruta_image;
    }

    public void setRuta_image(String ruta_image) {
        this.ruta_image = ruta_image;
    }

    public String getQuestionImageName() {
        return questionImageName;
    }

    public void setQuestionImageName(String questionImageName) {
        this.questionImageName = questionImageName;
    }

    public Long getQuestionCode() {
        return questionCode;
    }

    public void setQuestionCode(Long questionCode) {
        this.questionCode = questionCode;
    }

    public Long getHeadersSurveySurveyCode() {
        return headersSurveySurveyCode;
    }

    public void setHeadersSurveySurveyCode(Long headersSurveySurveyCode) {
        this.headersSurveySurveyCode = headersSurveySurveyCode;
    }

    public Long getHeadersSurveyBussinessUnitBuBisCode() {
        return headersSurveyBussinessUnitBuBisCode;
    }

    public void setHeadersSurveyBussinessUnitBuBisCode(Long headersSurveyBussinessUnitBuBisCode) {
        this.headersSurveyBussinessUnitBuBisCode = headersSurveyBussinessUnitBuBisCode;
    }

    public Integer getTypeRequest() {
        return typeRequest;
    }

    public void setTypeRequest(Integer typeRequest) {
        this.typeRequest = typeRequest;
    }

    public String getMainRequest() {
        return mainRequest;
    }

    public void setMainRequest(String mainRequest) {
        this.mainRequest = mainRequest;
    }

    public String getAnnexType() {
        return AnnexType;
    }

    public void setAnnexType(String annexType) {
        AnnexType = annexType;
    }

    public String getBodyAnnexDoc() {
        return bodyAnnexDoc;
    }

    public void setBodyAnnexDoc(String bodyAnnexDoc) {
        this.bodyAnnexDoc = bodyAnnexDoc;
    }

    public String getBodyAnnexPhoto() {
        return bodyAnnexPhoto;
    }

    public void setBodyAnnexPhoto(String bodyAnnexPhoto) {
        this.bodyAnnexPhoto = bodyAnnexPhoto;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getAnswerNumber() {
        return answerNumber;
    }

    public void setAnswerNumber(Integer answerNumber) {
        this.answerNumber = answerNumber;
    }

    public Integer getSolutionNumber() {
        return solutionNumber;
    }

    public void setSolutionNumber(Integer solutionNumber) {
        this.solutionNumber = solutionNumber;
    }

    public String getAuditableSolution() {
        return auditableSolution;
    }

    public void setAuditableSolution(String auditableSolution) {
        this.auditableSolution = auditableSolution;
    }

    public String getAuditableAnswerSolution() {
        return auditableAnswerSolution;
    }

    public void setAuditableAnswerSolution(String auditableAnswerSolution) {
        this.auditableAnswerSolution = auditableAnswerSolution;
    }

    public Double getQuestionPoints() {
        return questionPoints;
    }

    public void setQuestionPoints(Double questionPoints) {
        this.questionPoints = questionPoints;
    }

    public String getStatusRank() {
        return statusRank;
    }

    public void setStatusRank(String statusRank) {
        this.statusRank = statusRank;
    }

    public Blob getQuestionImageFile() {
        return questionImageFile;
    }

    public void setQuestionImageFile(Blob questionImageFile) {
        this.questionImageFile = questionImageFile;
    }

    public byte[] getQuestionImageFileByte() {
        return questionImageFileByte;
    }

    public void setQuestionImageFileByte(byte[] questionImageFileByte) {
        this.questionImageFileByte = questionImageFileByte;
    }

    public InputStream getQuestionImageFileInput() {
        return questionImageFileInput;
    }

    public void setQuestionImageFileInput(InputStream questionImageFileInput) {
        this.questionImageFileInput = questionImageFileInput;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }
}
