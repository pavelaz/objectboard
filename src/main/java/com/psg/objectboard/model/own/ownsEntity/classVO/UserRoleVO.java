package com.psg.objectboard.model.own.ownsEntity.classVO;

public class UserRoleVO {
    private String masterUserMuEmail;
    private long masterUserBussinessUnitBuBisCode;
    private long projectPrIdProject;
    private int umRole;
    private String umStatus;
    private Boolean result;

    public String getMasterUserMuEmail() {
        return masterUserMuEmail;
    }

    public void setMasterUserMuEmail(String masterUserMuEmail) {
        this.masterUserMuEmail = masterUserMuEmail;
    }

    public long getMasterUserBussinessUnitBuBisCode() {
        return masterUserBussinessUnitBuBisCode;
    }

    public void setMasterUserBussinessUnitBuBisCode(long masterUserBussinessUnitBuBisCode) {
        this.masterUserBussinessUnitBuBisCode = masterUserBussinessUnitBuBisCode;
    }

    public long getProjectPrIdProject() {
        return projectPrIdProject;
    }

    public void setProjectPrIdProject(long projectPrIdProject) {
        this.projectPrIdProject = projectPrIdProject;
    }

    public int getUmRole() {
        return umRole;
    }

    public void setUmRole(int umRole) {
        this.umRole = umRole;
    }

    public String getUmStatus() {
        return umStatus;
    }

    public void setUmStatus(String umStatus) {
        this.umStatus = umStatus;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }
}
