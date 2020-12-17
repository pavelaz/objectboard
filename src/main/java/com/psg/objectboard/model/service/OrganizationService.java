package com.psg.objectboard.model.service;

import com.psg.objectboard.model.own.ownsEntity.classDAO.OrganizationDAO;
import com.psg.objectboard.model.own.ownsEntity.classVO.OrganizationVO;
import com.psg.objectboard.model.own.ownsEntity.classVO.OtherVO.report.AdditionalDataReportVO;
import com.psg.objectboard.model.own.ownsEntity.classVO.OtherVO.report.OrganizationReportVO;

import java.util.ArrayList;

public class OrganizationService {

    public OrganizationReportVO getAllOrganizationsReport(String dataUser, String dataPassword, String companyNumber) {


        String title_report = "";
        String caption_table = "";
        String footer_table = "";
        String path_logo = "";
        String data_logo = path_logo + "";
        String company_name = "";
        String user_name = "";
        String user_email = "";

        AdditionalDataReportVO additionalData = new AdditionalDataReportVO(company_name, user_name, user_email ,data_logo, title_report, caption_table, footer_table);

        OrganizationDAO cod = new OrganizationDAO();
        ArrayList<OrganizationVO> orga = null;
        cod.setDataUser(dataUser);
        cod.setDataPassword(dataPassword);

        orga = cod.getListOrganization("bussinessUnit_bu_bis_code = "+companyNumber);

        OrganizationReportVO organizationReport = new OrganizationReportVO(additionalData, orga);

        return organizationReport;
    }
}
