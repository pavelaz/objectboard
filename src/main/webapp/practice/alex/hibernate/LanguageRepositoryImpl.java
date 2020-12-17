package com.psg.objectboard.model.repository;

import com.psg.objectboard.model.entity.LanguageEntity;
import javax.persistence.EntityManager;

public class LanguageRepositoryImpl {

    /**
     * 3.3) Metodo de consulta individual de la tabla lenguaje
     * @param id
     * @return
     */
    public LanguageEntity getById(EntityManager entityManager,Long id){

        // El tipo de objeto que queremos crear ().class y id que queremos consultar
        LanguageEntity languageEntity = entityManager.find(LanguageEntity.class, id);
        System.out.println("LanguageRepositoryImpl Read");

        return languageEntity;
    }

    /**
     * 4) create() de Repository Implementation
     * Recibimos parametros de identidad y pasamos al estado de persistencia.
     * @param languageEntity
     */
    public void create(EntityManager entityManager, LanguageEntity languageEntity){

        // Pasamos a estado de persistencia
        entityManager.persist(languageEntity);
    }

    public void delete(EntityManager entityManager, LanguageEntity languageEntity){

        entityManager.remove(languageEntity);
        System.out.println("Language delete");
    }

    /**
     * Valorizando el objeto en estado de persistencia y invocamos Roquette en condicion de update
     * @param languageEntity
     * @param newName
     */
    public void updateLanguage(LanguageEntity languageEntity, String newName){

        languageEntity.setName(newName);
    }



}
