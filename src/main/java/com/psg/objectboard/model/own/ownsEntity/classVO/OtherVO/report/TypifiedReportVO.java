package com.psg.objectboard.model.own.ownsEntity.classVO.OtherVO.report;

import com.psg.objectboard.model.own.ownsEntity.classVO.TypifiedVO;

import java.util.ArrayList;

public class TypifiedReportVO {
    private AdditionalDataReportVO additionalDataReport;
    private ArrayList<TypifiedVO> typifiedsReport;

    public TypifiedReportVO() {
    }

    public TypifiedReportVO(AdditionalDataReportVO additionalDataReport, ArrayList<TypifiedVO> typifiedsReport) {
        this.additionalDataReport = additionalDataReport;
        this.typifiedsReport = typifiedsReport;
    }

    public AdditionalDataReportVO getAdditionalDataReport() {
        return additionalDataReport;
    }

    public void setAdditionalDataReport(AdditionalDataReportVO additionalDataReport) {
        this.additionalDataReport = additionalDataReport;
    }

    public ArrayList<TypifiedVO> getTypifiedsReport() {
        return typifiedsReport;
    }

    public void setTypifiedsReport(ArrayList<TypifiedVO> typifiedsReport) {
        this.typifiedsReport = typifiedsReport;
    }
}
