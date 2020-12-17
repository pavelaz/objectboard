package com.psg.objectboard.model.datatransferobject;

import java.util.Set;

public class CommentsGetDto {
    private long commentCode;
    private String actions;
    private long bussinessUnitBuBisCode;
    private String commentDescription;
    private String commentType;
    private Set<CommentsGetDto> commentsDtoSet;

    public long getCommentCode() {
        return commentCode;
    }

    public void setCommentCode(long commentCode) {
        this.commentCode = commentCode;
    }

    public String getActions() {
        return actions;
    }

    public void setActions(String actions) {
        this.actions = actions;
    }

    public long getBussinessUnitBuBisCode() {
        return bussinessUnitBuBisCode;
    }

    public void setBussinessUnitBuBisCode(long bussinessUnitBuBisCode) {
        this.bussinessUnitBuBisCode = bussinessUnitBuBisCode;
    }

    public String getCommentDescription() {
        return commentDescription;
    }

    public void setCommentDescription(String commentDescription) {
        this.commentDescription = commentDescription;
    }

    public String getCommentType() {
        return commentType;
    }

    public void setCommentType(String commentType) {
        this.commentType = commentType;
    }

    public Set<CommentsGetDto> getCommentsDtoSet() {
        return commentsDtoSet;
    }

    public void setCommentsDtoSet(Set<CommentsGetDto> commentsDtoSet) {
        this.commentsDtoSet = commentsDtoSet;
    }
}
