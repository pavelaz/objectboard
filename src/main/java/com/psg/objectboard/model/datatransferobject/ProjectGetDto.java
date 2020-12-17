package com.psg.objectboard.model.datatransferobject;

import java.util.Set;

public class ProjectGetDto {
    private long prIdProject;
    private String actions;
    private String prName;
    private String prNote;
    private Set<ProjectGetDto> projectDtoSet;

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

    public String getActions() {
        return actions;
    }

    public void setActions(String actions) {
        this.actions = actions;
    }

    public Set<ProjectGetDto> getProjectDtoSet() {
        return projectDtoSet;
    }

    public void setProjectDtoSet(Set<ProjectGetDto> projectDtoSet) {
        this.projectDtoSet = projectDtoSet;
    }
}
