package com.psg.objectboard.model.own.ownsEntity.classVO.OtherVO;

public class AnnexTypeVO {
    private Integer at_id;
    private String at_description;

    public AnnexTypeVO(Integer at_id, String at_description) {
        this.at_id = at_id;
        this.at_description = at_description;
    }

    public Integer getAt_id() {
        return at_id;
    }

    public void setAt_id(Integer at_id) {
        this.at_id = at_id;
    }

    public String getAt_description() {
        return at_description;
    }

    public void setAt_description(String at_description) {
        this.at_description = at_description;
    }
}
