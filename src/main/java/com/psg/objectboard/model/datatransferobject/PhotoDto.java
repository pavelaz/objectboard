package com.psg.objectboard.model.datatransferobject;

public class PhotoDto {
    private String muEmail;
    private long bussinessUnitBuBisCode;
    private byte[] muPhoto;

    public String getMuEmail() {
        return muEmail;
    }

    public long getBussinessUnitBuBisCode() {
        return bussinessUnitBuBisCode;
    }

    public byte[] getMuPhoto() {
        return muPhoto;
    }

    public void setMuPhoto(byte[] muPhoto) {
        this.muPhoto = muPhoto;
    }

}
