package com.psg.objectboard.model.own.ownsEntity.classVO.OtherVO.report;

import com.psg.objectboard.model.own.ownsEntity.classVO.HeadersSurveyVO;

import java.util.ArrayList;

public class HeaderPollReportVO {
    private AdditionalDataReportVO additionalDataReport;
    private ArrayList<HeadersSurveyVO> headersPollsReport;

    public HeaderPollReportVO() {
    }

    public HeaderPollReportVO(AdditionalDataReportVO additionalDataReport, ArrayList<HeadersSurveyVO> headersPollsReport) {
        this.additionalDataReport = additionalDataReport;
        this.headersPollsReport = headersPollsReport;
    }

    public AdditionalDataReportVO getAdditionalDataReport() {
        return additionalDataReport;
    }

    public void setAdditionalDataReport(AdditionalDataReportVO additionalDataReport) {
        this.additionalDataReport = additionalDataReport;
    }

    public ArrayList<HeadersSurveyVO> getHeadersPollsReport() {
        return headersPollsReport;
    }

    public void setHeadersPollsReport(ArrayList<HeadersSurveyVO> headersPollsReport) {
        this.headersPollsReport = headersPollsReport;
    }
}
