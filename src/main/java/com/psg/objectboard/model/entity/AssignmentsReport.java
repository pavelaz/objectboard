package com.psg.objectboard.model.entity;

import com.psg.objectboard.model.own.ownsEntity.classVO.OtherVO.AssignmentsConsultVO;

import java.util.Set;

public class AssignmentsReport {
    private AdditionalDataReport additionalDataReport;
    private Set<AssignmentsConsultVO> assignmentsConsultVOSet;

    public AssignmentsReport() {
    }

    public AssignmentsReport(AdditionalDataReport additionalDataReport, Set<AssignmentsConsultVO> assignmentsConsultVOSet) {
        this.additionalDataReport = additionalDataReport;
        this.assignmentsConsultVOSet = assignmentsConsultVOSet;
    }

    public Set<AssignmentsConsultVO> getAssignmentsConsultVOSet() {
        return assignmentsConsultVOSet;
    }

    public void setAssignmentsConsultVOSet(Set<AssignmentsConsultVO> assignmentsConsultVOSet) {
        this.assignmentsConsultVOSet = assignmentsConsultVOSet;
    }

    public AdditionalDataReport getDataSectionVO() {
        return additionalDataReport;
    }

    public void setDataSectionVO(AdditionalDataReport additionalDataReport) {
        this.additionalDataReport = additionalDataReport;
    }
}
