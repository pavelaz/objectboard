package com.psg.objectboard.model.own.ownsEntity.classVO.OtherVO.report;

import com.psg.objectboard.model.own.ownsEntity.classVO.HeaderConductSurveyVO;

import java.util.ArrayList;

public class AuditReportVO {
    private AdditionalDataReportVO additionalDataReport;
    private ArrayList<HeaderConductSurveyVO> auditReport;

    public AuditReportVO() {

    }

    public AuditReportVO(AdditionalDataReportVO additionalDataReport, ArrayList<HeaderConductSurveyVO> auditReport) {
        this.additionalDataReport = additionalDataReport;
        this.auditReport = auditReport;
    }

    public AdditionalDataReportVO getAdditionalDataReport() {
        return additionalDataReport;
    }

    public void setAdditionalDataReport(AdditionalDataReportVO additionalDataReport) {
        this.additionalDataReport = additionalDataReport;
    }

    public ArrayList<HeaderConductSurveyVO> getAuditReport() {
        return auditReport;
    }

    public void setAuditReport(ArrayList<HeaderConductSurveyVO> auditReport) {
        this.auditReport = auditReport;
    }
}
