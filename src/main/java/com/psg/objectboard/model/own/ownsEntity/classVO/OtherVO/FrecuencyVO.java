package com.psg.objectboard.model.own.ownsEntity.classVO.OtherVO;

public class FrecuencyVO {
    private Integer fr_id;
    private String fr_description;
    private Integer fr_days;

    public FrecuencyVO(Integer fr_id, String fr_description, Integer fr_days) {
        this.fr_id = fr_id;
        this.fr_description = fr_description;
        this.fr_days = fr_days;
    }

    public Integer getFr_id() {
        return fr_id;
    }

    public void setFr_id(Integer fr_id) {
        this.fr_id = fr_id;
    }

    public String getFr_description() {
        return fr_description;
    }

    public void setFr_description(String fr_description) {
        this.fr_description = fr_description;
    }

    public Integer getFr_days() {
        return fr_days;
    }

    public void setFr_days(Integer fr_days) {
        this.fr_days = fr_days;
    }
}
