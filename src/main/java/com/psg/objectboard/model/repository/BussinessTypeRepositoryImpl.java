package com.psg.objectboard.model.repository;

import com.psg.objectboard.model.entity.BussinessTypeEntity;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class BussinessTypeRepositoryImpl {

    public List<BussinessTypeEntity> getBussinessTypeList (EntityManager entityManager){
        TypedQuery<BussinessTypeEntity> query = entityManager.createNamedQuery("ListBussinessType", BussinessTypeEntity.class);
        List<BussinessTypeEntity> bussinessTypeEntityList = query.getResultList();
        System.out.println("Leido BussinessTypeRepositoryImpl ListBussinessType");
    return bussinessTypeEntityList;
    }

    public BussinessTypeEntity getById(EntityManager entityManager,Long id){

        BussinessTypeEntity bussinessTypeEntity = entityManager.find(BussinessTypeEntity.class, id);
        System.out.println("Leido BussinessTypeRepositoryImpl  getById");

        return bussinessTypeEntity;
    }

    public void createBussinessType(EntityManager entityManager, BussinessTypeEntity bussinessTypeEntity){

        entityManager.persist(bussinessTypeEntity);
        System.out.println("Leido BussinessTypeRepositoryImpl  create");

    }

    public void delete(EntityManager entityManager, BussinessTypeEntity bussinessTypeEntity){

        entityManager.remove(bussinessTypeEntity);
        System.out.println("Leido BussinessTypeRepositoryImpl  Delete");
    }
}
