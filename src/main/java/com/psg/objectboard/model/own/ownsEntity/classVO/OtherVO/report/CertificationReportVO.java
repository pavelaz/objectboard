package com.psg.objectboard.model.own.ownsEntity.classVO.OtherVO.report;

import com.psg.objectboard.model.own.ownsEntity.classVO.CertificationsVO;
import com.psg.objectboard.model.own.ownsEntity.classVO.TypifiedVO;

import java.util.ArrayList;

public class CertificationReportVO {
    private AdditionalDataReportVO additionalDataReport;
    private ArrayList<CertificationsVO> certificationsReport;

    public CertificationReportVO() {
    }

    public CertificationReportVO(AdditionalDataReportVO additionalDataReport, ArrayList<CertificationsVO> certificationsReport) {
        this.additionalDataReport = additionalDataReport;
        this.certificationsReport = certificationsReport;
    }

    public AdditionalDataReportVO getAdditionalDataReport() {
        return additionalDataReport;
    }

    public void setAdditionalDataReport(AdditionalDataReportVO additionalDataReport) {
        this.additionalDataReport = additionalDataReport;
    }

    public ArrayList<CertificationsVO> getCertificationsReport() {
        return certificationsReport;
    }

    public void setCertificationsReport(ArrayList<CertificationsVO> certificationsReport) {
        this.certificationsReport = certificationsReport;
    }
}
