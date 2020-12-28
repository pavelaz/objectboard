package com.psg.objectboard.model.service;

import com.psg.objectboard.controller.common.FilesController;
import com.psg.objectboard.model.EntityManagerHolder;
import com.psg.objectboard.model.datatransferobject.MasterUserDto;
import com.psg.objectboard.model.datatransferobject.PhotoDto;
import com.psg.objectboard.model.entity.MasterUserEntity;
import com.psg.objectboard.model.repository.MasterUserProfileRepositoryImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class MasterUserProfileService {


    private MasterUserProfileRepositoryImpl masterUserRepository;

    public MasterUserProfileService() {
        this.masterUserRepository = new MasterUserProfileRepositoryImpl();
    }

    public MasterUserProfileService(MasterUserProfileRepositoryImpl masterUserRepository) {
        this.masterUserRepository = masterUserRepository;
    }

    public MasterUserDto getMasterUser(long identifier, String email){
        System.out.println("************** dentro getMasterUser");
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        MasterUserDto masterUserDto = null;
        try{
            entityManager = new EntityManagerHolder().getCurrentEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();

            MasterUserEntity masterUserEntity = masterUserRepository.getByIds(entityManager, identifier, email, '0');
            masterUserDto = new MasterUserDto();

            masterUserDto.setMuEmail(masterUserEntity.getMuEmail());
            masterUserDto.setMuPassword(masterUserEntity.getMuPassword());
            masterUserDto.setMuName(masterUserEntity.getMuName());
            masterUserDto.setMuSectionTime(masterUserEntity.getMuSectionTime());
            masterUserDto.setMuQuestion(masterUserEntity.getMuQuestion());
            masterUserDto.setMuStartDate(masterUserEntity.getMuStartDate());
            masterUserDto.setMuAnswer(masterUserEntity.getMuAnswer());
            masterUserDto.setMuStatus(masterUserEntity.getMuStatus());
            masterUserDto.setMuDate(masterUserEntity.getMuDate());
            masterUserDto.setMuEffectiveDays(masterUserEntity.getMuEffectiveDays());
            masterUserDto.setMuEmailConfirm(masterUserEntity.getMuEmailConfirm());
            masterUserDto.setMuGender(masterUserEntity.getMuGender());
            masterUserDto.setMuDataUser(masterUserEntity.getMuDataUser());
            masterUserDto.setMuDataPassword(masterUserEntity.getMuDataPassword());
            masterUserDto.setMuExpires(masterUserEntity.getMuExpires());
            masterUserDto.setMuDateExpires(masterUserEntity.getMuDateExpires());
            masterUserDto.setCityCiCityCode(masterUserEntity.getCityCiCityCode());
            masterUserDto.setCityStatesStStateCode(masterUserEntity.getCityStatesStStateCode());
            masterUserDto.setCityStatesCountryCoCountryCode(masterUserEntity.getCityStatesCountryCoCountryCode());

            /*LocalDateTime localDateTime = LocalDateTime.parse(masterUserEntity.getMuDate());
            DateTimeFormatter newFormatterDate = DateTimeFormatter.ofPattern("yyyy-mm-dd");
            masterUserDto.setMuDate(Date.valueOf(newFormatterDate.format(localDateTime)));
            System.out.println(masterUserDto.getMuDate());*/

            transaction.commit();
            System.out.println("Leido getMasterUserService");
        }
        catch (Exception e){
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
            System.err.println("Servicio MasterUser no funciona");
        }
        finally {
            if (entityManager != null)
                entityManager.close();
        }
        return masterUserDto;
    }

    public PhotoDto getShowPhoto(long identifier, String email){
        System.out.println("##########  dentro getShowPhoto");
        EntityManager entityManager = null;
        PhotoDto photoDto = null;
        EntityTransaction transaction = null;

        try {
            entityManager = new EntityManagerHolder().getCurrentEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();

            MasterUserEntity masterUserEntity = masterUserRepository.getByIds(entityManager, identifier, email, '1');
            photoDto = new PhotoDto();
            photoDto.setMuPhoto(masterUserEntity.getMuPhoto());

            transaction.commit();
            System.out.println("Leido MasterUserService Photo");
        }
        catch (Exception e){
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
            System.err.println("Servicio MasterUser Photo no funciona");
        }
        finally {
            if (entityManager != null)
                entityManager.close();
        }
        return photoDto;
    }

    public void updateMasterUser(MasterUserDto masterUserDto){

        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        FilesController filesController = null;

        try {
            entityManager = new EntityManagerHolder().getCurrentEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();

            System.out.println("datos para consulta: " + masterUserDto.getBussinessUnitBuBisCode() + " " + masterUserDto.getMuEmail());

            MasterUserEntity masterUserEntity = masterUserRepository.getByIds(entityManager,masterUserDto.getBussinessUnitBuBisCode(), masterUserDto.getMuEmail(), '2');
            System.out.println("dentro del cuerpo del servicio justo a invocar el update");
            if (masterUserDto.getBussinessUnitBuBisCode() == '1') {
                masterUserEntity.setBussinessUnitBuBisCode(masterUserDto.getBussinessUnitBuBisCode());
                masterUserEntity.setMuName(masterUserDto.getMuName());
                masterUserEntity.setMuPassword(masterUserDto.getMuPassword());
                masterUserEntity.setMuQuestion(masterUserDto.getMuQuestion());
                masterUserEntity.setMuAnswer(masterUserDto.getMuAnswer());
                masterUserEntity.setMuGender(masterUserDto.getMuGender());
                masterUserEntity.setMuSectionTime((masterUserDto.getMuSectionTime()) * 60);
                masterUserEntity.setMuStatus(masterUserDto.getMuStatus());
                masterUserEntity.setMuEffectiveDays(masterUserDto.getMuEffectiveDays());
                masterUserEntity.setMuEmailConfirm(masterUserDto.getMuEmailConfirm());
            } else{
                masterUserEntity.setBussinessUnitBuBisCode(masterUserDto.getBussinessUnitBuBisCode());
                masterUserEntity.setMuName(masterUserDto.getMuName());
                masterUserEntity.setMuPassword(masterUserDto.getMuPassword());
                masterUserEntity.setMuQuestion(masterUserDto.getMuQuestion());
                masterUserEntity.setMuAnswer(masterUserDto.getMuAnswer());
                masterUserEntity.setMuGender(masterUserDto.getMuGender());
            }

            /*Tranformation of byte[] to Blob of Photo*/
            if (masterUserDto.getRoutePhoto()!= null){
                filesController = new FilesController();
                byte[] blobPhoto = filesController.byteToBlobTransformation(masterUserDto.getRoutePhoto());
                masterUserEntity.setMuPhoto(blobPhoto);
            }

            transaction.commit();
            System.out.println("Leido update MasterUserService");

            /*Delete Photo to Server*/
            filesController.deleteFile(masterUserDto.getRoutePhoto());

        } catch (Exception e){
            if (transaction!=null)
                transaction.rollback();
            e.printStackTrace();
            System.err.println("Modificar MasterUser Service no funciona");
        }
        finally {
            if (entityManager!=null)
                entityManager.close();
        }
    }
}