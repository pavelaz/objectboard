package com.psg.objectboard.model.own.ownsEntity.classVO;

public class Contacts_listVO {
    private Integer list_id;
    private Long contacts_masterUser_bussinessUnit_bu_bis_code;
    private String contacts_masterUser_mu_email;
    private String contacts_cto_email_direction;
    private Long list_project;
    private Boolean result;

    public Integer getList_id() {
        return list_id;
    }

    public void setList_id(Integer list_id) {
        this.list_id = list_id;
    }

    public Long getContacts_masterUser_bussinessUnit_bu_bis_code() {
        return contacts_masterUser_bussinessUnit_bu_bis_code;
    }

    public void setContacts_masterUser_bussinessUnit_bu_bis_code(Long contacts_masterUser_bussinessUnit_bu_bis_code) {
        this.contacts_masterUser_bussinessUnit_bu_bis_code = contacts_masterUser_bussinessUnit_bu_bis_code;
    }

    public String getContacts_masterUser_mu_email() {
        return contacts_masterUser_mu_email;
    }

    public void setContacts_masterUser_mu_email(String contacts_masterUser_mu_email) {
        this.contacts_masterUser_mu_email = contacts_masterUser_mu_email;
    }

    public String getContacts_cto_email_direction() {
        return contacts_cto_email_direction;
    }

    public void setContacts_cto_email_direction(String contacts_cto_email_direction) {
        this.contacts_cto_email_direction = contacts_cto_email_direction;
    }

    public Long getList_project() {
        return list_project;
    }

    public void setList_project(Long list_project) {
        this.list_project = list_project;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }
}
