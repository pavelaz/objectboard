package com.psg.objectboard.model.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@NamedQuery(name="ListProject", query="SELECT DISTINCT(j) FROM ProjectEntity j ORDER BY j.prIdProject ASC")
@Table(name = "project", schema = "objectboard_db", catalog = "")
public class ProjectEntity {
    private long prIdProject;
    private String prName;
    private String prNote;
    private Set<ProjectEntity> projectEntitiesSet;

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pr_id_project")
    public long getPrIdProject() {
        return prIdProject;
    }

    public void setPrIdProject(long prIdProject) {
        this.prIdProject = prIdProject;
    }

    @Basic
    @Column(name = "pr_name")
    public String getPrName() {
        return prName;
    }

    public void setPrName(String prName) {
        this.prName = prName;
    }

    @Basic
    @Column(name = "pr_note")
    public String getPrNote() {
        return prNote;
    }

    public void setPrNote(String prNote) {
        this.prNote = prNote;
    }

    @Transient
    public Set<ProjectEntity> getProjectEntitiesSet() {
        return projectEntitiesSet;
    }

    public void setProjectEntitiesSet(Set<ProjectEntity> projectEntitiesSet) {
        this.projectEntitiesSet = projectEntitiesSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectEntity that = (ProjectEntity) o;
        return prIdProject == that.prIdProject &&
                Objects.equals(prName, that.prName) &&
                Objects.equals(prNote, that.prNote);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prIdProject, prName, prNote);
    }
}
