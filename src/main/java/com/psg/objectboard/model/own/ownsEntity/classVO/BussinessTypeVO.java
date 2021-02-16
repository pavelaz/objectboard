package com.psg.objectboard.model.own.ownsEntity.classVO;

public class BussinessTypeVO {
    private long btCodeType;
    private String btDescription;
    private String btNote;
    private Boolean result;

    public long getBtCodeType() {
        return btCodeType;
    }

    public void setBtCodeType(long btCodeType) {
        this.btCodeType = btCodeType;
    }

    public String getBtDescription() {
        return btDescription;
    }

    public void setBtDescription(String btDescription) {
        this.btDescription = btDescription;
    }

    public String getBtNote() {
        return btNote;
    }

    public void setBtNote(String btNote) {
        this.btNote = btNote;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }
}
