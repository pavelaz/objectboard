package com.psg.objectboard.model.service;

import com.psg.objectboard.model.own.ownsEntity.classDAO.AssignmentsDAO;
import com.psg.objectboard.model.own.ownsEntity.classVO.OtherVO.report.AdditionalDataReportVO;
import com.psg.objectboard.model.own.ownsEntity.classVO.OtherVO.AssignmentsConsultVO;
import com.psg.objectboard.model.own.ownsEntity.classVO.OtherVO.report.ConductReportVO;

import java.util.ArrayList;

public class ConductService {

    public ConductReportVO getAllConductReport(String dataUser, String dataPassword, String companyNumber) {


        String title_report = "";
        String caption_table = "";
        String footer_table = "";
        String path_logo = "";
        String data_logo = path_logo + "";
        String company_name = "";
        String user_name = "";
        String user_email = "";

        AdditionalDataReportVO additionalData = new AdditionalDataReportVO(company_name, user_name, user_email ,data_logo, title_report, caption_table, footer_table);

        AssignmentsDAO cod = new AssignmentsDAO();
        ArrayList<AssignmentsConsultVO> polls = null;
        cod.setDataUser(dataUser);
        cod.setDataPassword(dataPassword);

        ArrayList<AssignmentsConsultVO> surveysAssign = null;
        surveysAssign = cod.getListAssignmentsConsult("masterUser_bussinessUnit_bu_bis_code = " + Long.parseLong(companyNumber) );

        ConductReportVO conductReportVO = new ConductReportVO(additionalData, surveysAssign);

        return conductReportVO;
    }
}
