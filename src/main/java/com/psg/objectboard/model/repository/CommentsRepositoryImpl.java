package com.psg.objectboard.model.repository;

import com.psg.objectboard.model.entity.CommentsEntity;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class CommentsRepositoryImpl {

    public List<CommentsEntity> getCommentsList (EntityManager entityManager){
        TypedQuery<CommentsEntity> query = entityManager.createNamedQuery("ListComments", CommentsEntity.class);
        List<CommentsEntity> commentsEntityList = query.getResultList();
        System.out.println("Leido CommentsRepositoryImpl ListComments");
    return commentsEntityList;
    }

    public CommentsEntity getById(EntityManager entityManager,Long id){

        CommentsEntity commentsEntity = entityManager.find(CommentsEntity.class, id);
        System.out.println("Leido CommentsRepositoryImpl  getById");

        return commentsEntity;
    }

    public void createComments(EntityManager entityManager, CommentsEntity commentsEntity){

        entityManager.persist(commentsEntity);
        System.out.println("Leido CommentsRepositoryImpl  create");

    }

    public void delete(EntityManager entityManager, CommentsEntity commentsEntity){

        entityManager.remove(commentsEntity);
        System.out.println("Leido CommentsRepositoryImpl  Delete");
    }
}
