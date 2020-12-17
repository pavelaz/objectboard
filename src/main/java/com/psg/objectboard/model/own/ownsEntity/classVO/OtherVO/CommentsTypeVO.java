package com.psg.objectboard.model.own.ownsEntity.classVO.OtherVO;

public class CommentsTypeVO {
    private Integer ct_id;
    private String ct_description;

    public Integer getCt_id() {
        return ct_id;
    }

    public void setCt_id(Integer ct_id) {
        this.ct_id = ct_id;
    }

    public String getCt_description() {
        return ct_description;
    }

    public void setCt_description(String ct_description) {
        this.ct_description = ct_description;
    }

    public CommentsTypeVO(Integer ct_id, String ct_description) {
        this.ct_id = ct_id;
        this.ct_description = ct_description;
    }
}
