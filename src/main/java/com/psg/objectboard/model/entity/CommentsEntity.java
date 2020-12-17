package com.psg.objectboard.model.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@NamedQuery(name="ListComments", query="SELECT DISTINCT(j) FROM CommentsEntity j ORDER BY j.commentCode ASC")
@Table(name = "comments", schema = "objectboard_db")
public class CommentsEntity {
    private long commentCode;
    private long bussinessUnitBuBisCode;
    private String commentDescription;
    private String commentType;
    private Set<CommentsEntity> commentsEntitySet;

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_code")
    public long getCommentCode() {
        return commentCode;
    }

    public void setCommentCode(long commentCode) {
        this.commentCode = commentCode;
    }

    @Basic
    @Column(name = "bussinessUnit_bu_bis_code")
    public long getBussinessUnitBuBisCode() {
        return bussinessUnitBuBisCode;
    }

    public void setBussinessUnitBuBisCode(long bussinessUnitBuBisCode) {
        this.bussinessUnitBuBisCode = bussinessUnitBuBisCode;
    }

    @Basic
    @Column(name = "comment_description")
    public String getCommentDescription() {
        return commentDescription;
    }

    public void setCommentDescription(String commentDescription) {
        this.commentDescription = commentDescription;
    }

    @Basic
    @Column(name = "comment_type")
    public String getCommentType() {
        return commentType;
    }

    public void setCommentType(String commentType) {
        this.commentType = commentType;
    }

    @Transient
    public Set<CommentsEntity> getCommentsEntitySet() {
        return commentsEntitySet;
    }

    public void setCommentsEntitySet(Set<CommentsEntity> commentsEntitySet) {
        this.commentsEntitySet = commentsEntitySet;
    }
}
