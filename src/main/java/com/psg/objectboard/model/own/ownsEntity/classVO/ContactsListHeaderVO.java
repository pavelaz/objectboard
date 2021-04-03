package com.psg.objectboard.model.own.ownsEntity.classVO;

public class ContactsListHeaderVO {
    private Integer list_id;
    private Long contacts_masterUser_bussinessUnit_bu_bis_code;
    private String contacts_masterUser_mu_email;
    private Long list_project;
    private String list_name;
    private Integer list_count_directions;
    private Integer masMenos;
    private Boolean result;

    public void setMasMenos(Integer masMenos) {
        this.masMenos = masMenos;
    }

    public Integer getList_count_directions() {
        return list_count_directions;
    }

    public void setList_count_directions(Integer list_count_directions) {
        this.list_count_directions = list_count_directions;
    }

    public String getList_name() {
        return list_name;
    }

    public void setList_name(String list_name) {
        this.list_name = list_name;
    }

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
