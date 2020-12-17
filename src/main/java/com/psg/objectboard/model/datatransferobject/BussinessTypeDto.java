package com.psg.objectboard.model.datatransferobject;

import java.util.Set;

public class BussinessTypeDto {
    private long btCodeType;
    private String actions;
    private String btDescription;
    private String btNote;
    private Set<BussinessTypeDto> bussinessTypeDtoSet;


    public long getBtCodeType() {
        return btCodeType;
    }

    public void setBtCodeType(long btCodeType) {
        this.btCodeType = btCodeType;
    }

    public String getActions() {
        return actions;
    }

    public void setActions(String actions) {
        this.actions = actions;
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

    public void setBussinessTypeDtoSet(Set<BussinessTypeDto> bussinessTypeDtoSet) {
        this.bussinessTypeDtoSet = bussinessTypeDtoSet;
    }

    public Set<BussinessTypeDto> getBussinessTypeDtoSet() {
        return bussinessTypeDtoSet;
    }
}