package com.psg.objectboard.model.own.ownsEntity.classViewVO;

public class UserRoleViewVO {
    private String masterUserMuEmail;
    private Long masterUserBussinessUnitBuBisCode;
    private Long projectPrIdProject;
    private Integer umRole;
    private String umStatus;
    private String userName;
    private String unitName;
    private String projName;

    public String getMasterUserMuEmail() {
        return masterUserMuEmail;
    }

    public void setMasterUserMuEmail(String masterUserMuEmail) {
        this.masterUserMuEmail = masterUserMuEmail;
    }

    public Long getMasterUserBussinessUnitBuBisCode() {
        return masterUserBussinessUnitBuBisCode;
    }

    public void setMasterUserBussinessUnitBuBisCode(Long masterUserBussinessUnitBuBisCode) {
        this.masterUserBussinessUnitBuBisCode = masterUserBussinessUnitBuBisCode;
    }

    public Long getProjectPrIdProject() {
        return projectPrIdProject;
    }

    public void setProjectPrIdProject(Long projectPrIdProject) {
        this.projectPrIdProject = projectPrIdProject;
    }

    public Integer getUmRole() {
        return umRole;
    }

    public void setUmRole(Integer umRole) {
        this.umRole = umRole;
    }

    public String getUmStatus() {
        return umStatus;
    }

    public void setUmStatus(String umStatus) {
        this.umStatus = umStatus;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getProjName() {
        return projName;
    }

    public void setProjName(String projName) {
        this.projName = projName;
    }
}
