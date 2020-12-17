package com.psg.objectboard.model.service;

import com.psg.objectboard.model.own.ownsEntity.classDAO.CertificationsDAO;
import com.psg.objectboard.model.own.ownsEntity.classVO.CertificationsVO;
import com.psg.objectboard.model.own.ownsEntity.classVO.OtherVO.report.AdditionalDataReportVO;
import com.psg.objectboard.model.own.ownsEntity.classVO.OtherVO.report.CertificationReportVO;

import java.util.ArrayList;

public class CertificationService {
    public CertificationReportVO getAllCertificationReport(String dataUser, String dataPassword, String companyNumber) {

        String title_report = "";
        String caption_table = "";
        String footer_table = "";
        String path_logo = "";
        String data_logo = path_logo + "";
        String company_name = "";
        String user_name = "";
        String user_email = "";

        AdditionalDataReportVO additionalData = new AdditionalDataReportVO(company_name, user_name, user_email ,data_logo, title_report, caption_table, footer_table);

        CertificationsDAO cod = new CertificationsDAO();
        ArrayList<CertificationsVO> cert = null;
        cod.setDataUser(dataUser);
        cod.setDataPassword(dataPassword);

        cert = cod.getListCertifications("bussinessUnit_bu_bis_code = "+companyNumber);

        CertificationReportVO certificationReport = new CertificationReportVO(additionalData, cert);

        return certificationReport;
    }
}
