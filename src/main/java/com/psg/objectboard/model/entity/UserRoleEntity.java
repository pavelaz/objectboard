package com.psg.objectboard.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/*@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString*/
@Entity
@Table(name = "userRole", schema = "objectboard_db", catalog = "")
public class UserRoleEntity {
    private String masterUserMuEmail;
    private long masterUserBussinessUnitBuBisCode;
    private long projectPrIdProject;
    private int umRole;
    private String umStatus;
    //private MasterUserEntity masterUser;

    /*@OneToOne(mappedBy = "userRole")
    public MasterUserEntity getMasterUser() {
        return masterUser;
    }

    public void setMasterUser(MasterUserEntity masterUser) {
        this.masterUser = masterUser;
    }*/

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "masterUser_mu_email", updatable = false, nullable = true, length = 40)
    public String getMasterUserMuEmail() {
        return masterUserMuEmail;
    }

    public void setMasterUserMuEmail(String masterUserMuEmail) {
        this.masterUserMuEmail = masterUserMuEmail;
    }

    @Basic
    @Column(name = "masterUser_bussinessUnit_bu_bis_code", nullable = true, length = 20)
    public long getMasterUserBussinessUnitBuBisCode() {
        return masterUserBussinessUnitBuBisCode;
    }

    public void setMasterUserBussinessUnitBuBisCode(long masterUserBussinessUnitBuBisCode) {
        this.masterUserBussinessUnitBuBisCode = masterUserBussinessUnitBuBisCode;
    }

    @Basic
    @Column(name = "project_pr_id_project", nullable = true, length = 20)
    public long getProjectPrIdProject() {
        return projectPrIdProject;
    }

    public void setProjectPrIdProject(long projectPrIdProject) {
        this.projectPrIdProject = projectPrIdProject;
    }

    @Basic
    @Column(name = "um_role", nullable = true, length = 11)
    public int getUmRole() {
        return umRole;
    }

    public void setUmRole(int umRole) {
        this.umRole = umRole;
    }

    @Basic
    @Column(name = "um_status", nullable = false, length = 1)
    public String getUmStatus() {
        return umStatus;
    }

    public void setUmStatus(String umStatus) {
        this.umStatus = umStatus;
    }

}
