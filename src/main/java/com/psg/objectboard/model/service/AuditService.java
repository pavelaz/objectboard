package com.psg.objectboard.model.service;

import com.psg.objectboard.model.own.ownsEntity.classDAO.HeaderConductSurveyDAO;
import com.psg.objectboard.model.own.ownsEntity.classVO.HeaderConductSurveyVO;
import com.psg.objectboard.model.own.ownsEntity.classVO.OtherVO.report.AdditionalDataReportVO;
import com.psg.objectboard.model.own.ownsEntity.classVO.OtherVO.report.AuditReportVO;
import com.psg.objectboard.model.service.Other.DateFunctions;

import java.util.ArrayList;

public class AuditService {

    public AuditReportVO getAllAuditReport(String dataUser, String dataPassword, String companyNumber) {


        String title_report = "";
        String caption_table = "";
        String footer_table = "";
        String path_logo = "";
        String data_logo = path_logo + "";
        String company_name = "";
        String user_name = "";
        String user_email = "";

        AdditionalDataReportVO additionalData = new AdditionalDataReportVO(company_name, user_name, user_email ,data_logo, title_report, caption_table, footer_table);

        HeaderConductSurveyDAO cod = new HeaderConductSurveyDAO();
        ArrayList<HeaderConductSurveyVO> polls = null;
        cod.setDataUser(dataUser);
        cod.setDataPassword(dataPassword);

        polls = cod.getListHeaderConductSurvey("headersSurvey_bussinessUnit_bu_bis_code = " + Integer.parseInt(companyNumber) +
                " AND user_auditor = '" + user_email + "' AND auditable = 'T' AND to_audited = 'T'");
        if (polls.size() !=0){
            DateFunctions df = new DateFunctions();
            for (int x=0; x < polls.size(); x++){
                polls.get(x).setConduct_date(df.parseFecha_2(polls.get(x).getConduct_date()) + polls.get(x).getConduct_date().substring(10,19));
            }
        }

        AuditReportVO auditReportVO = new AuditReportVO(additionalData, polls);

        return auditReportVO;
    }
}
