package com.psg.objectboard.model.repository;

import com.psg.objectboard.model.entity.ProjectEntity;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ProjectRepositoryImpl {

    public List<ProjectEntity> getProjectList (EntityManager entityManager){
        TypedQuery<ProjectEntity> query = entityManager.createNamedQuery("ListProject", ProjectEntity.class);
        List<ProjectEntity> projectEntityList = query.getResultList();
        System.out.println("Leido ProjectRepositoryImpl ListProject");
    return projectEntityList;
    }

    public ProjectEntity getById(EntityManager entityManager,Long id){

        ProjectEntity projectEntity = entityManager.find(ProjectEntity.class, id);
        System.out.println("Leido ProjectRepositoryImpl  getById");

        return projectEntity;
    }

    public void createProject(EntityManager entityManager, ProjectEntity projectEntity){

        entityManager.persist(projectEntity);
        System.out.println("Leido ProjectRepositoryImpl  create");

    }

    public void delete(EntityManager entityManager, ProjectEntity projectEntity){

        entityManager.remove(projectEntity);
        System.out.println("Leido ProjectRepositoryImpl  Delete");
    }
}
