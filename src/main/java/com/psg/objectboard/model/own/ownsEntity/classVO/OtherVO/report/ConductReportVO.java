package com.psg.objectboard.model.own.ownsEntity.classVO.OtherVO.report;

import com.psg.objectboard.model.own.ownsEntity.classVO.OtherVO.AssignmentsConsultVO;

import java.util.ArrayList;

public class ConductReportVO {
    private AdditionalDataReportVO additionalDataReport;
    private ArrayList<AssignmentsConsultVO> assignmentsConductReport;

    public ConductReportVO() {
    }

    public ConductReportVO(AdditionalDataReportVO additionalDataReport, ArrayList<AssignmentsConsultVO> assignmentsConductReport) {
        this.additionalDataReport = additionalDataReport;
        this.assignmentsConductReport = assignmentsConductReport;
    }

    public AdditionalDataReportVO getAdditionalDataReport() {
        return additionalDataReport;
    }

    public void setAdditionalDataReport(AdditionalDataReportVO additionalDataReport) {
        this.additionalDataReport = additionalDataReport;
    }

    public ArrayList<AssignmentsConsultVO> getAssignmentsConductReport() {
        return assignmentsConductReport;
    }

    public void setAssignmentsConductReport(ArrayList<AssignmentsConsultVO> assignmentsConductReport) {
        this.assignmentsConductReport = assignmentsConductReport;
    }
}
