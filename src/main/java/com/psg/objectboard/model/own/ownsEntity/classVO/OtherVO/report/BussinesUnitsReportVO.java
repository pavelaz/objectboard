package com.psg.objectboard.model.own.ownsEntity.classVO.OtherVO.report;

import com.psg.objectboard.model.own.ownsEntity.classViewVO.BussinessUnitCoStCiVO;

import java.util.ArrayList;

public class BussinesUnitsReportVO {
    private AdditionalDataReportVO additionalDataReport;
    private ArrayList<BussinessUnitCoStCiVO> bussinessUnitCoStCiVOS;

    public BussinesUnitsReportVO() {
    }

    public BussinesUnitsReportVO(AdditionalDataReportVO additionalDataReport, ArrayList<BussinessUnitCoStCiVO> bussinessUnitCoStCiVOS) {
        this.additionalDataReport = additionalDataReport;
        this.bussinessUnitCoStCiVOS = bussinessUnitCoStCiVOS;
    }

    public AdditionalDataReportVO getAdditionalDataReport() {
        return additionalDataReport;
    }

    public void setAdditionalDataReport(AdditionalDataReportVO additionalDataReport) {
        this.additionalDataReport = additionalDataReport;
    }

    public ArrayList<BussinessUnitCoStCiVO> getBussinessUnitCoStCiVOS() {
        return bussinessUnitCoStCiVOS;
    }

    public void setBussinessUnitCoStCiVOS(ArrayList<BussinessUnitCoStCiVO> bussinessUnitCoStCiVOS) {
        this.bussinessUnitCoStCiVOS = bussinessUnitCoStCiVOS;
    }
}
