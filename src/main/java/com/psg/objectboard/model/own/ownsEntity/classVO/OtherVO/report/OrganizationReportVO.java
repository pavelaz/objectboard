package com.psg.objectboard.model.own.ownsEntity.classVO.OtherVO.report;

import com.psg.objectboard.model.own.ownsEntity.classVO.OrganizationVO;

import java.util.ArrayList;

public class OrganizationReportVO {
    private AdditionalDataReportVO additionalDataReport;
    private ArrayList<OrganizationVO> organizationsReport;

    public OrganizationReportVO() {
    }

    public OrganizationReportVO(AdditionalDataReportVO additionalDataReport, ArrayList<OrganizationVO> organizationsReport) {
        this.additionalDataReport = additionalDataReport;
        this.organizationsReport = organizationsReport;
    }

    public AdditionalDataReportVO getAdditionalDataReport() {
        return additionalDataReport;
    }

    public void setAdditionalDataReport(AdditionalDataReportVO additionalDataReport) {
        this.additionalDataReport = additionalDataReport;
    }

    public ArrayList<OrganizationVO> getOrganizationsReport() {
        return organizationsReport;
    }

    public void setOrganizationsReport(ArrayList<OrganizationVO> organizationsReport) {
        this.organizationsReport = organizationsReport;
    }
}
