package com.psg.objectboard.model.datatransferobject;

import java.util.Set;

public class ProjectInsertUpdateDto {
    private long prIdProject;
    private String prName;
    private String prNote;
    private Set<ProjectInsertUpdateDto> projectUpdateDtoSet;
    private Set<ProjectInsertUpdateDto> projectInsertDtoSet;

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

    public Set<ProjectInsertUpdateDto> getProjectUpdateDtoSet() {
        return projectUpdateDtoSet;
    }

    public void setProjectUpdateDtoSet(Set<ProjectInsertUpdateDto> projectUpdateDtoSet) {
        this.projectUpdateDtoSet = projectUpdateDtoSet;
    }

    public Set<ProjectInsertUpdateDto> getProjectInsertDtoSet() {
        return projectInsertDtoSet;
    }

    public void setProjectInsertDtoSet(Set<ProjectInsertUpdateDto> projectInsertDtoSet) {
        this.projectInsertDtoSet = projectInsertDtoSet;
    }

}
