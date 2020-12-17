package com.psg.objectboard.model.datatransferobject;

import java.util.Set;

public class BussinessTypeBasicDto {
    private long btCodeType;
    private String btDescription;
    private String btNote;
    private Set<BussinessTypeBasicDto> bussinessTypeUpdateDtoSet;
    private Set<BussinessTypeBasicDto> bussinessTypeInsertDtoSet;

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

    public Set<BussinessTypeBasicDto> getBussinessTypeUpdateDtoSet() {
        return bussinessTypeUpdateDtoSet;
    }

    public void setBussinessTypeUpdateDtoSet(Set<BussinessTypeBasicDto> bussinessTypeUpdateDtoSet) {
        this.bussinessTypeUpdateDtoSet = bussinessTypeUpdateDtoSet;
    }

    public Set<BussinessTypeBasicDto> getBussinessTypeInsertDtoSet() {
        return bussinessTypeInsertDtoSet;
    }

    public void setBussinessTypeInsertDtoSet(Set<BussinessTypeBasicDto> bussinessTypeInsertDtoSet) {
        this.bussinessTypeInsertDtoSet = bussinessTypeInsertDtoSet;
    }
}