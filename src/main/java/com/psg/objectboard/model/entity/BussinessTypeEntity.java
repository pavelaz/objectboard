package com.psg.objectboard.model.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@NamedQuery(name="ListBussinessType", query="SELECT DISTINCT(j) FROM BussinessTypeEntity j ORDER BY j.btCodeType ASC")
@Table(name = "bussinessType", schema = "objectboard_db", catalog = "")
public class BussinessTypeEntity {
    private long btCodeType;
    private String btDescription;
    private String btNote;
    private Set<BussinessTypeEntity> bussinessTypeEntitySet;

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bt_code_type")
    public long getBtCodeType() {
        return btCodeType;
    }

    public void setBtCodeType(long btCodeType) {
        this.btCodeType = btCodeType;
    }

    @Basic
    @Column(name = "bt_description")
    public String getBtDescription() {
        return btDescription;
    }

    public void setBtDescription(String btDescription) {
        this.btDescription = btDescription;
    }

    @Basic
    @Column(name = "bt_note")
    public String getBtNote() {
        return btNote;
    }

    public void setBtNote(String btNote) {
        this.btNote = btNote;
    }

    @Transient
    public Set<BussinessTypeEntity> getBussinessTypeEntitySet() {
        return bussinessTypeEntitySet;
    }

    public void setBussinessTypeEntitySet(Set<BussinessTypeEntity> bussinessTypeEntitySet) {
        this.bussinessTypeEntitySet = bussinessTypeEntitySet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BussinessTypeEntity that = (BussinessTypeEntity) o;
        return btCodeType == that.btCodeType &&
                Objects.equals(btDescription, that.btDescription) &&
                Objects.equals(btNote, that.btNote);
    }

    @Override
    public int hashCode() {
        return Objects.hash(btCodeType, btDescription, btNote);
    }
}
