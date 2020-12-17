package com.psg.objectboard.model.own.ownsEntity.classVO;

public class BussinessTypeVO {
    private long btCodeType;
    private String btDescription;
    private String btNote;
    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

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

    public String toString() {
        return "BussinessTypeVO{" +
                "btNote='" + btNote + '\'' +
                '}';
    }
}
