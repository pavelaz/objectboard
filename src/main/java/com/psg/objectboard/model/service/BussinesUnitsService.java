package com.psg.objectboard.model.service;

import com.psg.objectboard.model.own.ownsEntity.classVO.OtherVO.report.AdditionalDataReportVO;
import com.psg.objectboard.model.own.ownsEntity.classVO.OtherVO.report.BussinesUnitsReportVO;
import com.psg.objectboard.model.own.ownsEntity.classViewDAO.BussinessUnitCoStCiDAO;
import com.psg.objectboard.model.own.ownsEntity.classViewVO.BussinessUnitCoStCiVO;

import java.util.ArrayList;

public class BussinesUnitsService {

    public BussinesUnitsReportVO getAllBussinesUnitsReport() {


        String title_report = "";
        String caption_table = "";
        String footer_table = "";
        String path_logo = "";
        String data_logo = path_logo + "";
        String company_name = "";
        String user_name = "";
        String user_email = "";
        String dataUser = "6C0REY2T";
        String dataPassword = "vFpvUVvGPojdI27";

        AdditionalDataReportVO additionalData = new AdditionalDataReportVO(company_name, user_name, user_email ,data_logo, title_report, caption_table, footer_table);

        BussinessUnitCoStCiDAO cod= new BussinessUnitCoStCiDAO();
        ArrayList<BussinessUnitCoStCiVO> unidad = null;
        cod.setDataUser(dataUser);
        cod.setDataPassword(dataPassword);
        unidad = cod.getListBussinessUnitCoStCi();

      BussinesUnitsReportVO bussinesUnitsReport = new BussinesUnitsReportVO(additionalData, unidad);

        return bussinesUnitsReport;
    }
}
