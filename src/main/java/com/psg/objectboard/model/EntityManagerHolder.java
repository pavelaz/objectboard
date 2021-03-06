package com.psg.objectboard.model;
import com.psg.objectboard.model.service.Other.OtherFunctions;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;

/*Creamos el Contexte de persistance*/
public class EntityManagerHolder {
    private final ThreadLocal<EntityManager> entityManagerThreadLocal = new ThreadLocal<>();
    private static EntityManagerFactory entityManagerFactory;

    static {
        try {
            entityManagerFactory = buildEntityManagerFactory();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static EntityManagerFactory buildEntityManagerFactory() throws IOException {
        OtherFunctions otherFunctions = new OtherFunctions();
        String x = otherFunctions.searchLink("1");
        return Persistence.createEntityManagerFactory(x);
    }

    /**
     * @return The {@link EntityManager} linked to this thread
     */
    public EntityManager getCurrentEntityManager() {
        EntityManager entityManager = entityManagerThreadLocal.get();

        if (entityManager == null) {

            // Start the conversation by creating the EntityManager for this thread
            entityManager = entityManagerFactory.createEntityManager();
            entityManagerThreadLocal.set(entityManager);

        }
        return entityManager;
    }

}