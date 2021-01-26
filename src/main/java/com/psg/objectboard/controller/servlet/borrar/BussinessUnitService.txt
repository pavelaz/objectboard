package com.psg.objectboard.model.service;
import com.psg.objectboard.model.EntityManagerHolder;
import com.psg.objectboard.model.datatransferobject.BussinessUnitFullDto;
import com.psg.objectboard.model.entity.BussinessUnitEntity;
import com.psg.objectboard.model.repository.BussinessUnitRepositoryImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;

public class BussinessUnitService {
    private BussinessUnitRepositoryImpl bussinessUnitRepository;

    public BussinessUnitService(BussinessUnitRepositoryImpl bussinessUnitRepository) {
        this.bussinessUnitRepository = bussinessUnitRepository;
    }
    public BussinessUnitService() {
        this.bussinessUnitRepository = new BussinessUnitRepositoryImpl();
    }

    public List<BussinessUnitFullDto> getListBussinessUnit(){
        List<BussinessUnitFullDto> listBussinessUnit = new ArrayList<>();

        EntityManager entityManager=null;
        EntityTransaction transaction = null;

        try{

            entityManager = new EntityManagerHolder().getCurrentEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();

            for(BussinessUnitEntity bussinessUnitEntity: listBussinessUnit){
                final BussinessUnitFullDto bussinessUnitDto = new BussinessUnitFullDto();
                bussinessUnitDto.setBuBisCode(bussinessUnitEntity.getBuBisCode());
                bussinessUnitDto.setBuName(bussinessUnitEntity.getBuName());
                bussinessUnitDto.setBuSuperCode(bussinessUnitEntity.getBuSuperCode());
                listBussinessUnit.add(bussinessUnitDto);
            }

            transaction.commit();
            System.out.println("Leido Servicio BussinessUnit");
        }catch (Exception e){
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
            System.err.println("Servicio BussinessUnit no funciona");
        }
        finally {
            if (entityManager!=null)
                entityManager.close();
        }
        return listBussinessUnit;
    }

    /*public MasterUserDto getMasterUser(long identifier){
        EntityManager entityManager=null;
        EntityTransaction transaction = null;
        MasterUserEntity masterUserEntity = null;
        MasterUserDto masterUserDto = null;
        try{
            entityManager = new EntityManagerHolder().getCurrentEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();

            masterUserEntity = masterUserRepository.getById(identifier);
            masterUserDto = new MasterUserDto();

            masterUserDto.setMuEmail(masterUserEntity.getMuEmail());
            masterUserDto.setBussinessUnitBuBisCode(masterUserEntity.getBussinessUnitBuBisCode());
            masterUserDto.setMuPassword(masterUserEntity.getMuPassword());
            masterUserDto.setMuPasswordOld(masterUserEntity.getMuPasswordOld());
            masterUserDto.setMuName(masterUserEntity.getMuName());
            masterUserDto.setMuSectionTime(masterUserEntity.getMuSectionTime());
            masterUserDto.setMuQuestion(masterUserEntity.getMuQuestion());
            masterUserDto.setMuStartDate(masterUserEntity.getMuStartDate());
            masterUserDto.setMuAnswer(masterUserEntity.getMuAnswer());
            masterUserDto.setMuStatus(masterUserEntity.getMuStatus());
            masterUserDto.setMuDate(masterUserEntity.getMuDate());
            masterUserDto.setMuEffectiveDays(masterUserEntity.getMuEffectiveDays());
            masterUserDto.setMuConfirmCode(masterUserEntity.getMuConfirmCode());
            masterUserDto.setMuEmailConfirm(masterUserEntity.getMuEmailConfirm());
            masterUserDto.setMuDateResetPwd(masterUserEntity.getMuDateResetPwd());
            masterUserDto.setMuPhoto(masterUserEntity.getMuPhoto());

            transaction.commit();
            System.out.println("Leido Servicio MasterUser");
        }
        catch (Exception e){
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
            System.err.println("Servicio MasterUser no funciona");
        }
        finally {
            if (entityManager!=null)
                entityManager.close();
        }
        return masterUserDto;
    }*/
}
