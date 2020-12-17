package com.psg.objectboard.model.own.ownsEntity.classVO.OtherVO.report;

import com.psg.objectboard.model.own.ownsEntity.classVO.OtherVO.AssignmentsConsultVO;

import java.util.ArrayList;

public class AssignmentReportVO {
    private AdditionalDataReportVO additionalDataReport;
    private ArrayList<AssignmentsConsultVO> assignmentsConduct;

    public AssignmentReportVO() {
    }

    public AssignmentReportVO(AdditionalDataReportVO additionalDataReport, ArrayList<AssignmentsConsultVO> assignmentsConduct) {
        this.additionalDataReport = additionalDataReport;
        this.assignmentsConduct = assignmentsConduct;
    }

    public AdditionalDataReportVO getAdditionalDataReport() {
        return additionalDataReport;
    }

    public void setAdditionalDataReport(AdditionalDataReportVO additionalDataReport) {
        this.additionalDataReport = additionalDataReport;
    }

    public ArrayList<AssignmentsConsultVO> getAssignmentsConduct() {
        return assignmentsConduct;
    }

    public void setAssignmentsConduct(ArrayList<AssignmentsConsultVO> assignmentsConduct) {
        this.assignmentsConduct = assignmentsConduct;
    }
}
