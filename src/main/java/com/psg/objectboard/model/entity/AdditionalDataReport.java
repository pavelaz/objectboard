package com.psg.objectboard.model.entity;

public class AdditionalDataReport {
    public String company_name;
    public String company_number;
    public String user_name;
    public String data_logo;
    public String title_report;
    public String caption_table;
    public String footer_table;
    public String data_user;
    public String data_password;

    public AdditionalDataReport() {
    }

    public AdditionalDataReport(String company_name, String company_number, String user_name, String data_logo, String title_report, String caption_table, String footer_table, String data_user, String data_password) {
        this.company_name = company_name;
        this.company_number = company_number;
        this.user_name = user_name;
        this.data_logo = data_logo;
        this.title_report = title_report;
        this.caption_table = caption_table;
        this.footer_table = footer_table;
        this.data_user = data_user;
        this.data_password = data_password;
    }

    public String getData_user() {
        return data_user;
    }

    public void setData_user(String data_user) {
        this.data_user = data_user;
    }

    public String getData_password() {
        return data_password;
    }

    public void setData_password(String data_password) {
        this.data_password = data_password;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_number() {
        return company_number;
    }

    public void setCompany_number(String company_number) {
        this.company_number = company_number;
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
