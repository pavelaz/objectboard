package com.psg.objectboard.model.own.ownsEntity.classVO;

public class ProjectVO {
    private long prIdProject;
    private String prName;
    private String prNote;
    private Boolean result;

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public long getPrIdProject() {
        return prIdProject;
    }

    public void setPrIdProject(long prIdProject) {
        this.prIdProject = prIdProject;
    }

    public String getPrName() {
        return prName;
    }

    public void setPrName(String prName) {
        this.prName = prName;
    }

    public String getPrNote() {
        return prNote;
    }

    public void setPrNote(String prNote) {
        this.prNote = prNote;
    }
}
