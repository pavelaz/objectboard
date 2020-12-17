package com.psg.objectboard.model.repository;

import com.psg.objectboard.model.entity.MasterUserEntity;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class MasterUserProfileRepositoryImpl {

    public MasterUserEntity getByIds (EntityManager entityManager ,long identifier, String email, char method){
        TypedQuery<MasterUserEntity> query = entityManager.createNamedQuery("MasterUserDetails.byId", MasterUserEntity.class);
            query.setParameter("BusBisCode", identifier);
            query.setParameter("muEmail", email);
        MasterUserEntity masterUserEntity = query.getSingleResult();
        if (method == '0')
            System.out.println("Leido MasterUserRepositoryImpl getMasterUser");
        else if (method == '1')
            System.out.println("Leido MasterUserRepositoryImpl getShowPhoto");
        else
            System.out.println("Leido MasterUserRepositoryImpl updateMasterUser");
    return masterUserEntity;
    }
}
