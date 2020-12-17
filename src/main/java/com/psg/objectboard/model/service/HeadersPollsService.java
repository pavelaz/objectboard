package com.psg.objectboard.model.service;

import com.psg.objectboard.model.own.ownsEntity.classDAO.HeadersSurveyDAO;
import com.psg.objectboard.model.own.ownsEntity.classVO.HeadersSurveyVO;
import com.psg.objectboard.model.own.ownsEntity.classVO.OtherVO.report.AdditionalDataReportVO;
import com.psg.objectboard.model.own.ownsEntity.classVO.OtherVO.report.HeaderPollReportVO;
import com.psg.objectboard.model.service.Other.DateFunctions;

import java.util.ArrayList;

public class HeadersPollsService {

    public HeaderPollReportVO getAllHeadersPollsReport(String dataUser, String dataPassword, String companyNumber) {


        String title_report = "";
        String caption_table = "";
        String footer_table = "";
        String path_logo = "";
        String data_logo = path_logo + "";
        String company_name = "";
        String user_name = "";
        String user_email = "";

        AdditionalDataReportVO additionalData = new AdditionalDataReportVO(company_name, user_name, user_email ,data_logo, title_report, caption_table, footer_table);

        HeadersSurveyDAO cod = new HeadersSurveyDAO();
        ArrayList<HeadersSurveyVO> polls = null;
        cod.setDataUser(dataUser);
        cod.setDataPassword(dataPassword);

        polls = cod.getListHeadersSurvey("bussinessUnit_bu_bis_code = " + Integer.parseInt(companyNumber));
        if (polls.size() !=0){
            DateFunctions df = new DateFunctions();
            for (int x=0; x < polls.size(); x++){
                polls.get(x).setDateCreation(df.parseFecha_2(polls.get(x).getDateCreation()));
                polls.get(x).setDateLastModification(df.parseFecha_2(polls.get(x).getDateLastModification()));
            }
        }

        HeaderPollReportVO headersPollsReport = new HeaderPollReportVO(additionalData, polls);

        return headersPollsReport;
    }
}
