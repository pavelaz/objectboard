package com.psg.objectboard.model.datatransferobject;

import java.util.Set;

public class CommentsInsertUpdateDto {
    private long commentCode;
    private long bussinessUnitBuBisCode;
    private String commentDescription;
    private String commentType;
    private Set<CommentsInsertUpdateDto> commentsUpdateDtoSet;
    private Set<CommentsInsertUpdateDto> commentsInsertDtoSet;

    public long getCommentCode() {
        return commentCode;
    }

    public void setCommentCode(long commentCode) {
        this.commentCode = commentCode;
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

    public Set<CommentsInsertUpdateDto> getCommentsUpdateDtoSet() {
        return commentsUpdateDtoSet;
    }

    public void setCommentsUpdateDtoSet(Set<CommentsInsertUpdateDto> commentsUpdateDtoSet) {
        this.commentsUpdateDtoSet = commentsUpdateDtoSet;
    }

    public Set<CommentsInsertUpdateDto> getCommentsInsertDtoSet() {
        return commentsInsertDtoSet;
    }

    public void setCommentsInsertDtoSet(Set<CommentsInsertUpdateDto> commentsInsertDtoSet) {
        this.commentsInsertDtoSet = commentsInsertDtoSet;
    }
}