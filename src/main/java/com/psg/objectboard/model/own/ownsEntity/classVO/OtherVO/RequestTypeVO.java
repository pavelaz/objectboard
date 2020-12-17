package com.psg.objectboard.model.own.ownsEntity.classVO.OtherVO;

public class RequestTypeVO {
    private Integer rt_id;
    private String rt_description;

    public RequestTypeVO(Integer rt_id, String rt_description) {
        this.rt_id = rt_id;
        this.rt_description = rt_description;
    }

    public Integer getRt_id() {
        return rt_id;
    }

    public void setRt_id(Integer rt_id) {
        this.rt_id = rt_id;
    }

    public String getRt_description() {
        return rt_description;
    }

    public void setRt_description(String rt_description) {
        this.rt_description = rt_description;
    }
}
