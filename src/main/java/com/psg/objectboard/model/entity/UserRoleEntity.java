package com.psg.objectboard.model.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "userRole", schema = "objectboard_db", catalog = "")
public class UserRoleEntity {
    private String masterUserMuEmail;
    private long masterUserBussinessUnitBuBisCode;
    private long projectPrIdProject;
    private int umRole;
    private String umStatus;

    @Id
    @Column(name = "masterUser_mu_email")
    public String getMasterUserMuEmail() {
        return masterUserMuEmail;
    }

    public void setMasterUserMuEmail(String masterUserMuEmail) {
        this.masterUserMuEmail = masterUserMuEmail;
    }

    @Basic
    @Column(name = "masterUser_bussinessUnit_bu_bis_code")
    public long getMasterUserBussinessUnitBuBisCode() {
        return masterUserBussinessUnitBuBisCode;
    }

    public void setMasterUserBussinessUnitBuBisCode(long masterUserBussinessUnitBuBisCode) {
        this.masterUserBussinessUnitBuBisCode = masterUserBussinessUnitBuBisCode;
    }

    @Basic
    @Column(name = "project_pr_id_project")
    public long getProjectPrIdProject() {
        return projectPrIdProject;
    }

    public void setProjectPrIdProject(long projectPrIdProject) {
        this.projectPrIdProject = projectPrIdProject;
    }

    @Basic
    @Column(name = "um_role")
    public int getUmRole() {
        return umRole;
    }

    public void setUmRole(int umRole) {
        this.umRole = umRole;
    }

    @Basic
    @Column(name = "um_status")
    public String getUmStatus() {
        return umStatus;
    }

    public void setUmStatus(String umStatus) {
        this.umStatus = umStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRoleEntity that = (UserRoleEntity) o;
        return masterUserBussinessUnitBuBisCode == that.masterUserBussinessUnitBuBisCode && projectPrIdProject == that.projectPrIdProject && umRole == that.umRole && Objects.equals(masterUserMuEmail, that.masterUserMuEmail) && Objects.equals(umStatus, that.umStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(masterUserMuEmail, masterUserBussinessUnitBuBisCode, projectPrIdProject, umRole, umStatus);
    }
}
