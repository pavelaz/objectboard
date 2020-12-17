package com.psg.objectboard.model.own.ownsEntity.classVO.OtherVO;

public class CommentsNotesVO {
    public Long commentCode;
    public Long bussinessUnitBuBisCode;
    public String commentType;
    public String commentDescription;
    public String commentReference;
    public Boolean result;

    public Long getCommentCode() {
        return commentCode;
    }

    public void setCommentCode(Long commentCode) {
        this.commentCode = commentCode;
    }

    public Long getBussinessUnitBuBisCode() {
        return bussinessUnitBuBisCode;
    }

    public void setBussinessUnitBuBisCode(Long bussinessUnitBuBisCode) {
        this.bussinessUnitBuBisCode = bussinessUnitBuBisCode;
    }

    public String getCommentType() {
        return commentType;
    }

    public void setCommentType(String commentType) {
        this.commentType = commentType;
    }

    public String getCommentDescription() {
        return commentDescription;
    }

    public void setCommentDescription(String commentDescription) {
        this.commentDescription = commentDescription;
    }

    public String getCommentReference() {
        return commentReference;
    }

    public void setCommentReference(String commentReference) {
        this.commentReference = commentReference;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }
}
