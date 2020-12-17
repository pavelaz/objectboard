package com.psg.objectboard.model.own.ownsEntity.classVO;

public class BodySurveyAnswersVO {
    private Long answerCode;
    private Long bodySurveyQuestionsQuestionCode;
    private Long bodySurveyQuestionsHeadersSurveySurveyCode;
    private Long bodySurveyQuestionsHeadersSurveyBussinessUnitBuBisCode;
    private String answer;
    private String answerSolution;
    private String auditableSolution;
    private String answerOnlyText;
    private String answerOnlyDate;
    private String answerOnlyTime;
    private Double answerOnlyNumber;
    private String statusRank;
    private Double rankMin;
    private Double rankMax;
    private Boolean result;

    public Long getAnswerCode() {
        return answerCode;
    }

    public void setAnswerCode(Long answerCode) {
        this.answerCode = answerCode;
    }

    public Long getBodySurveyQuestionsQuestionCode() {
        return bodySurveyQuestionsQuestionCode;
    }

    public void setBodySurveyQuestionsQuestionCode(Long bodySurveyQuestionsQuestionCode) {
        this.bodySurveyQuestionsQuestionCode = bodySurveyQuestionsQuestionCode;
    }

    public Long getBodySurveyQuestionsHeadersSurveySurveyCode() {
        return bodySurveyQuestionsHeadersSurveySurveyCode;
    }

    public void setBodySurveyQuestionsHeadersSurveySurveyCode(Long bodySurveyQuestionsHeadersSurveySurveyCode) {
        this.bodySurveyQuestionsHeadersSurveySurveyCode = bodySurveyQuestionsHeadersSurveySurveyCode;
    }

    public Long getBodySurveyQuestionsHeadersSurveyBussinessUnitBuBisCode() {
        return bodySurveyQuestionsHeadersSurveyBussinessUnitBuBisCode;
    }

    public void setBodySurveyQuestionsHeadersSurveyBussinessUnitBuBisCode(Long bodySurveyQuestionsHeadersSurveyBussinessUnitBuBisCode) {
        this.bodySurveyQuestionsHeadersSurveyBussinessUnitBuBisCode = bodySurveyQuestionsHeadersSurveyBussinessUnitBuBisCode;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswerSolution() {
        return answerSolution;
    }

    public void setAnswerSolution(String answerSolution) {
        this.answerSolution = answerSolution;
    }

    public String getAuditableSolution() {
        return auditableSolution;
    }

    public void setAuditableSolution(String auditableSolution) {
        this.auditableSolution = auditableSolution;
    }

    public String getAnswerOnlyText() {
        return answerOnlyText;
    }

    public void setAnswerOnlyText(String answerOnlyText) {
        this.answerOnlyText = answerOnlyText;
    }

    public String getAnswerOnlyDate() {
        return answerOnlyDate;
    }

    public void setAnswerOnlyDate(String answerOnlyDate) {
        this.answerOnlyDate = answerOnlyDate;
    }

    public String getAnswerOnlyTime() {
        return answerOnlyTime;
    }

    public void setAnswerOnlyTime(String answerOnlyTime) {
        this.answerOnlyTime = answerOnlyTime;
    }

    public Double getAnswerOnlyNumber() {
        return answerOnlyNumber;
    }

    public void setAnswerOnlyNumber(Double answerOnlyNumber) {
        this.answerOnlyNumber = answerOnlyNumber;
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

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }
}
