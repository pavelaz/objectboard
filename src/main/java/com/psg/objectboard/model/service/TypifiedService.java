package com.psg.objectboard.model.service;

import com.psg.objectboard.model.own.ownsEntity.classDAO.TypifiedDAO;
import com.psg.objectboard.model.own.ownsEntity.classVO.OtherVO.report.AdditionalDataReportVO;
import com.psg.objectboard.model.own.ownsEntity.classVO.OtherVO.report.TypifiedReportVO;
import com.psg.objectboard.model.own.ownsEntity.classVO.TypifiedVO;

import java.util.ArrayList;

public class TypifiedService {
    public TypifiedReportVO getAllTypifiedReport(String dataUser, String dataPassword, String companyNumber) {

        String title_report = "";
        String caption_table = "";
        String footer_table = "";
        String path_logo = "";
        String data_logo = path_logo + "";
        String company_name = "";
        String user_name = "";
        String user_email = "";

        AdditionalDataReportVO additionalData = new AdditionalDataReportVO(company_name, user_name, user_email ,data_logo, title_report, caption_table, footer_table);

        TypifiedDAO cod = new TypifiedDAO();
        ArrayList<TypifiedVO> type = null;
        cod.setDataUser(dataUser);
        cod.setDataPassword(dataPassword);

        type = cod.getListTypified("bussinessUnit_bu_bis_code = "+companyNumber);

        TypifiedReportVO typifiedReport = new TypifiedReportVO(additionalData, type);

        return typifiedReport;
    }
}
