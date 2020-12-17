package com.psg.objectboard.model.service;

import com.psg.objectboard.model.own.ownsEntity.classDAO.AssignmentsDAO;
import com.psg.objectboard.model.own.ownsEntity.classVO.OtherVO.report.AdditionalDataReportVO;
import com.psg.objectboard.model.own.ownsEntity.classVO.OtherVO.AssignmentsConsultVO;
import com.psg.objectboard.model.own.ownsEntity.classVO.OtherVO.report.AssignmentReportVO;

import java.util.ArrayList;

public class AssignmentService {

    public AssignmentReportVO getAllAssignmentReport(String dataUser, String dataPassword, String companyNumber) {


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
        ArrayList<AssignmentsConsultVO> asigna = null;
        cod.setDataUser(dataUser);
        cod.setDataPassword(dataPassword);

        asigna = cod.getListAssignmentsConsult("masterUser_bussinessUnit_bu_bis_code = " + Integer.parseInt(companyNumber));

        AssignmentReportVO assignmentsReport = new AssignmentReportVO(additionalData, asigna);

        return assignmentsReport;
    }
}
