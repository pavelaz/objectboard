package com.psg.objectboard.model.own.ownsEntity.classVO;

public class MessageVO {
    private Long msg_id;
    private String masterUser_mu_email;
    private Long masterUser_bussinessUnit_bu_bis_code;
    private Long msg_project;
    private String msg_send_type;
    private String msg_object_type;
    private String msg_to;
    private String msg_cc;
    private String msg_cco;
    private Integer msg_to_list;
    private Integer msg_cc_list;
    private Integer msg_cco_list;
    private String msg_subject;
    private String msg_message;
    private Long msg_survey_code;
    private Boolean result;

    public Long getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(Long msg_id) {
        this.msg_id = msg_id;
    }

    public String getMasterUser_mu_email() {
        return masterUser_mu_email;
    }

    public void setMasterUser_mu_email(String masterUser_mu_email) {
        this.masterUser_mu_email = masterUser_mu_email;
    }

    public Long getMasterUser_bussinessUnit_bu_bis_code() {
        return masterUser_bussinessUnit_bu_bis_code;
    }

    public void setMasterUser_bussinessUnit_bu_bis_code(Long masterUser_bussinessUnit_bu_bis_code) {
        this.masterUser_bussinessUnit_bu_bis_code = masterUser_bussinessUnit_bu_bis_code;
    }

    public Long getMsg_project() {
        return msg_project;
    }

    public void setMsg_project(Long msg_project) {
        this.msg_project = msg_project;
    }

    public String getMsg_send_type() {
        return msg_send_type;
    }

    public void setMsg_send_type(String msg_send_type) {
        this.msg_send_type = msg_send_type;
    }

    public String getMsg_object_type() {
        return msg_object_type;
    }

    public void setMsg_object_type(String msg_object_type) {
        this.msg_object_type = msg_object_type;
    }

    public String getMsg_to() {
        return msg_to;
    }

    public void setMsg_to(String msg_to) {
        this.msg_to = msg_to;
    }

    public String getMsg_cc() {
        return msg_cc;
    }

    public void setMsg_cc(String msg_cc) {
        this.msg_cc = msg_cc;
    }

    public String getMsg_cco() {
        return msg_cco;
    }

    public void setMsg_cco(String msg_cco) {
        this.msg_cco = msg_cco;
    }

    public Integer getMsg_to_list() {
        return msg_to_list;
    }

    public void setMsg_to_list(Integer msg_to_list) {
        this.msg_to_list = msg_to_list;
    }

    public Integer getMsg_cc_list() {
        return msg_cc_list;
    }

    public void setMsg_cc_list(Integer msg_cc_list) {
        this.msg_cc_list = msg_cc_list;
    }

    public Integer getMsg_cco_list() {
        return msg_cco_list;
    }

    public void setMsg_cco_list(Integer msg_cco_list) {
        this.msg_cco_list = msg_cco_list;
    }

    public String getMsg_subject() {
        return msg_subject;
    }

    public void setMsg_subject(String msg_subject) {
        this.msg_subject = msg_subject;
    }

    public String getMsg_message() {
        return msg_message;
    }

    public void setMsg_message(String msg_message) {
        this.msg_message = msg_message;
    }

    public Long getMsg_survey_code() {
        return msg_survey_code;
    }

    public void setMsg_survey_code(Long msg_survey_code) {
        this.msg_survey_code = msg_survey_code;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }
}
