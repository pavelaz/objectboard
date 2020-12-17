package com.psg.objectboard.model.service;

import com.psg.objectboard.model.EntityManagerHolder;
import com.psg.objectboard.model.datatransferobject.BussinessTypeBasicDto;
import com.psg.objectboard.model.datatransferobject.BussinessTypeDto;
import com.psg.objectboard.model.entity.BussinessTypeEntity;
import com.psg.objectboard.model.repository.BussinessTypeRepositoryImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.HashSet;
import java.util.List;

public class BussinessTypeService {

    private BussinessTypeRepositoryImpl bussinessTypeRepository;

    public BussinessTypeService() {
        this.bussinessTypeRepository = new BussinessTypeRepositoryImpl();
    }

    public BussinessTypeService(BussinessTypeRepositoryImpl bussinessTypeRepository) {
        this.bussinessTypeRepository = bussinessTypeRepository;
    }

    public BussinessTypeDto getListBussinessType(){
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        BussinessTypeDto bussinessTypeDto = new BussinessTypeDto();

        try{
            entityManager = new EntityManagerHolder().getCurrentEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();

            List<BussinessTypeEntity> bussinessTypeEntityList = bussinessTypeRepository.getBussinessTypeList(entityManager);

            bussinessTypeDto.setBussinessTypeDtoSet(new HashSet<>());
            for (BussinessTypeEntity bussinessTypeEntity: bussinessTypeEntityList){
                final BussinessTypeDto valorizationDto = new BussinessTypeDto();
                valorizationDto.setBtCodeType(bussinessTypeEntity.getBtCodeType());
                valorizationDto.setBtDescription(bussinessTypeEntity.getBtDescription());
                valorizationDto.setBtNote(bussinessTypeEntity.getBtNote());

                bussinessTypeDto.getBussinessTypeDtoSet().add(valorizationDto);
            }
            transaction.commit();
            System.out.println("Leido getMasterUserService Valorizado ObjetoDto con Coleccion");
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
        return bussinessTypeDto;
    }

    public void insertBussinessType(BussinessTypeBasicDto bussinessTypeBasicDto){
        EntityManager entityManager = null;
        EntityTransaction entityTransaction = null;

        try{
            //Solicitamos una seccion en hibernate para la insercion
            entityManager = new EntityManagerHolder().getCurrentEntityManager();
            //Iniciamos un recurso de transaccion.
            entityTransaction = entityManager.getTransaction();

            for(BussinessTypeBasicDto valorizationDTO: bussinessTypeBasicDto.getBussinessTypeInsertDtoSet()){
                entityTransaction.begin();
                // Instanciamos variable de objeto de tipo entidad
                BussinessTypeEntity bussinessTypeEntity = new BussinessTypeEntity();
                // Valorizamos con parametros de objetoDTO a Identidad.
                bussinessTypeEntity.setBtDescription(valorizationDTO.getBtDescription());
                bussinessTypeEntity.setBtNote(valorizationDTO.getBtNote());
                // Construimos() objeto de tipo RepositoryImpl y implementamos metodo create() enviando parametros a seccion de persistencia
                bussinessTypeRepository.createBussinessType(entityManager, bussinessTypeEntity);
                // Confirmamos la transacción del recursos actual y escribimos los cambios no vaciados en la base de datos.
                entityTransaction.commit();
            }

            System.out.println("Create to BussinessType");
        }
        catch (Exception e){
            if (entityTransaction!=null)
                entityTransaction.rollback();
            e.printStackTrace();
            System.err.println("BussinessType no funciona");
        }
        finally {
            if (entityManager!=null)
                entityManager.close();
        }

    }

    public void updateBussinnessType(BussinessTypeBasicDto bussinessTypeBasicDto){

        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            entityManager = new EntityManagerHolder().getCurrentEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();

            for (BussinessTypeBasicDto valorizationDto: bussinessTypeBasicDto.getBussinessTypeUpdateDtoSet()) {
                BussinessTypeEntity bussinessTypeEntity = bussinessTypeRepository.getById(entityManager, valorizationDto.getBtCodeType());
                bussinessTypeEntity.setBtDescription(valorizationDto.getBtDescription());
                bussinessTypeEntity.setBtNote(valorizationDto.getBtNote());
            }

            transaction.commit();
            System.out.println("Leido update BussinessTypeService");

        } catch (Exception e){
            if (transaction!=null)
                transaction.rollback();
            e.printStackTrace();
            System.err.println("Modificar BussinessType Service no funciona");
        }
        finally {
            if (entityManager!=null)
                entityManager.close();
        }
    }

    public void deleteBussinessType(BussinessTypeDto bussinessTypeDto){
        //Variables de objeto
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        BussinessTypeEntity bussinessTypeEntity = null;
        /*LanguageDto languageDto = null;*/

        try{
            // Solicitamos una seccion en hibernate para la lectura
            entityManager = new EntityManagerHolder().getCurrentEntityManager();
            // Iniciamos un recurso de transaccion.
            transaction = entityManager.getTransaction();

            for (BussinessTypeDto bussinessTypeDtoTable: bussinessTypeDto.getBussinessTypeDtoSet()) {
                transaction.begin();
                // Valorizamos con parametros de objetoDTO a Identidad.
                bussinessTypeEntity = new BussinessTypeEntity();
                bussinessTypeEntity = bussinessTypeRepository.getById(entityManager, bussinessTypeDtoTable.getBtCodeType());
                bussinessTypeRepository.delete(entityManager,bussinessTypeEntity);
                // Confirmamos la transacción del recursos actual y escribimos los cambios no vaciados en la base de datos.
                transaction.commit();
            }

        } catch (Exception e){
            if (transaction!=null)
                transaction.rollback();
            e.printStackTrace();
            System.err.println("Borrar Service no funciona");
        }
        finally {
            if (entityManager!=null)
                entityManager.close();
        }
    }
}