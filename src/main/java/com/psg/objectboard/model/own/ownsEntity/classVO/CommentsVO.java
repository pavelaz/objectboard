package com.psg.objectboard.model.own.ownsEntity.classVO;

public class CommentsVO {
    public static Long commentCode;
    public static Long bussinessUnitBuBisCode;
    public static String commentType;
    public static String commentDescription;
    public static String commentReference;
    public static Boolean result;

    public static Long getCommentCode() {
        return commentCode;
    }

    public static void setCommentCode(Long commentCode) {
        CommentsVO.commentCode = commentCode;
    }

    public static Long getBussinessUnitBuBisCode() {
        return bussinessUnitBuBisCode;
    }

    public static void setBussinessUnitBuBisCode(Long bussinessUnitBuBisCode) {
        CommentsVO.bussinessUnitBuBisCode = bussinessUnitBuBisCode;
    }

    public static String getCommentType() {
        return commentType;
    }

    public static void setCommentType(String commentType) {
        CommentsVO.commentType = commentType;
    }

    public static String getCommentDescription() {
        return commentDescription;
    }

    public static void setCommentDescription(String commentDescription) {
        CommentsVO.commentDescription = commentDescription;
    }

    public static String getCommentReference() {
        return commentReference;
    }

    public static void setCommentReference(String commentReference) {
        CommentsVO.commentReference = commentReference;
    }

    public static Boolean getResult() {
        return result;
    }

    public static void setResult(Boolean result) {
        CommentsVO.result = result;
    }
}
