package com.psg.objectboard.model.own.ownsEntity.classVO;

import java.util.Date;

public class DischargeVO {
    private static Long diLicenseNum;
    private static String diStartDate;
    private static String diEndDate;
    private static Integer diNumberUser;
    private static String diPermanent;
    private static Integer diSalesCode;
    private static String diLicenseCode;
    private static Long bussinessUnitBuBisCode;
    private static Long projectPrIdProject;
    private Boolean result;

    public static Long getDiLicenseNum() {
        return diLicenseNum;
    }

    public static void setDiLicenseNum(Long diLicenseNum) {
        DischargeVO.diLicenseNum = diLicenseNum;
    }

    public static String getDiStartDate() {
        return diStartDate;
    }

    public static void setDiStartDate(String diStartDate) {
        DischargeVO.diStartDate = diStartDate;
    }

    public static String getDiEndDate() {
        return diEndDate;
    }

    public static void setDiEndDate(String diEndDate) {
        DischargeVO.diEndDate = diEndDate;
    }

    public static Integer getDiNumberUser() {
        return diNumberUser;
    }

    public static void setDiNumberUser(Integer diNumberUser) {
        DischargeVO.diNumberUser = diNumberUser;
    }

    public static String getDiPermanent() {
        return diPermanent;
    }

    public static void setDiPermanent(String diPermanent) {
        DischargeVO.diPermanent = diPermanent;
    }

    public static Integer getDiSalesCode() {
        return diSalesCode;
    }

    public static void setDiSalesCode(Integer diSalesCode) {
        DischargeVO.diSalesCode = diSalesCode;
    }

    public static String getDiLicenseCode() {
        return diLicenseCode;
    }

    public static void setDiLicenseCode(String diLicenseCode) {
        DischargeVO.diLicenseCode = diLicenseCode;
    }

    public static Long getBussinessUnitBuBisCode() {
        return bussinessUnitBuBisCode;
    }

    public static void setBussinessUnitBuBisCode(Long bussinessUnitBuBisCode) {
        DischargeVO.bussinessUnitBuBisCode = bussinessUnitBuBisCode;
    }

    public static Long getProjectPrIdProject() {
        return projectPrIdProject;
    }

    public static void setProjectPrIdProject(Long projectPrIdProject) {
        DischargeVO.projectPrIdProject = projectPrIdProject;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }
}
