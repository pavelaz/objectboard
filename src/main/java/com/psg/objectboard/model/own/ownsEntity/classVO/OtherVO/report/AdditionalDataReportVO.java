package com.psg.objectboard.model.own.ownsEntity.classVO.OtherVO.report;

public class AdditionalDataReportVO {
    public String company_name;
    public String user_name;
    public String user_email;
    public String data_logo;
    public String title_report;
    public String caption_table;
    public String footer_table;

    public AdditionalDataReportVO() {
    }

    public AdditionalDataReportVO(String company_name, String user_name, String user_email, String data_logo, String title_report, String caption_table, String footer_table) {
        this.company_name = company_name;
        this.user_name = user_name;
        this.user_email = user_email;
        this.data_logo = data_logo;
        this.title_report = title_report;
        this.caption_table = caption_table;
        this.footer_table = footer_table;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getData_logo() {
        return data_logo;
    }

    public void setData_logo(String data_logo) {
        this.data_logo = data_logo;
    }

    public String getTitle_report() {
        return title_report;
    }

    public void setTitle_report(String title_report) {
        this.title_report = title_report;
    }

    public String getCaption_table() {
        return caption_table;
    }

    public void setCaption_table(String caption_table) {
        this.caption_table = caption_table;
    }

    public String getFooter_table() {
        return footer_table;
    }

    public void setFooter_table(String footer_table) {
        this.footer_table = footer_table;
    }
}
