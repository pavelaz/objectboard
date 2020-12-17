package com.psg.objectboard.model.service;

import com.psg.objectboard.model.EntityManagerHolder;
import com.psg.objectboard.model.datatransferobject.CommentsGetDto;
import com.psg.objectboard.model.datatransferobject.CommentsInsertUpdateDto;
import com.psg.objectboard.model.entity.CommentsEntity;
import com.psg.objectboard.model.repository.CommentsRepositoryImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.HashSet;
import java.util.List;

public class CommentsService {

    private CommentsRepositoryImpl commentsRepository;

    public CommentsService() {
        this.commentsRepository = new CommentsRepositoryImpl();
    }

    public CommentsService(CommentsRepositoryImpl commentsRepository) {
        this.commentsRepository = commentsRepository;
    }

    public CommentsGetDto getListComments(){
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        CommentsGetDto commentsGetDto = new CommentsGetDto();

        try{
            entityManager = new EntityManagerHolder().getCurrentEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();

            List<CommentsEntity> commentsEntityList = commentsRepository.getCommentsList(entityManager);

            commentsGetDto.setCommentsDtoSet(new HashSet<>());
            for (CommentsEntity commentsEntity: commentsEntityList){
                final CommentsGetDto valorizationDto = new CommentsGetDto();
                valorizationDto.setCommentCode(commentsEntity.getCommentCode());
                valorizationDto.setBussinessUnitBuBisCode(commentsEntity.getBussinessUnitBuBisCode());
                valorizationDto.setCommentDescription(commentsEntity.getCommentDescription());
                valorizationDto.setCommentType(commentsEntity.getCommentType());

                commentsGetDto.getCommentsDtoSet().add(valorizationDto);
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
        return commentsGetDto;
    }

    public void insertComments(CommentsInsertUpdateDto commentsInsertUpdateDto){
        EntityManager entityManager = null;
        EntityTransaction entityTransaction = null;

        try{
            //Solicitamos una seccion en hibernate para la insercion
            entityManager = new EntityManagerHolder().getCurrentEntityManager();
            //Iniciamos un recurso de transaccion.
            entityTransaction = entityManager.getTransaction();

            for(CommentsInsertUpdateDto valorizationDTO: commentsInsertUpdateDto.getCommentsInsertDtoSet()){
                entityTransaction.begin();
                // Instanciamos variable de objeto de tipo entidad
                CommentsEntity commentsEntity = new CommentsEntity();
                // Valorizamos con parametros de objetoDTO a Identidad.
                commentsEntity.setBussinessUnitBuBisCode(valorizationDTO.getBussinessUnitBuBisCode());
                commentsEntity.setCommentDescription(valorizationDTO.getCommentDescription());
                commentsEntity.setCommentType(valorizationDTO.getCommentType());
                // Construimos() objeto de tipo RepositoryImpl y implementamos metodo create() enviando parametros a seccion de persistencia
                commentsRepository.createComments(entityManager, commentsEntity);
                // Confirmamos la transacción del recursos actual y escribimos los cambios no vaciados en la base de datos.
                entityTransaction.commit();
            }

            System.out.println("Create to Comments");
        }
        catch (Exception e){
            if (entityTransaction!=null)
                entityTransaction.rollback();
            e.printStackTrace();
            System.err.println("Comments no funciona");
        }
        finally {
            if (entityManager!=null)
                entityManager.close();
        }

    }

    public void updateBussinnessType(CommentsInsertUpdateDto commentsInsertUpdateDto){

        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            entityManager = new EntityManagerHolder().getCurrentEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();

            for (CommentsInsertUpdateDto valorizationDto: commentsInsertUpdateDto.getCommentsUpdateDtoSet()) {
                CommentsEntity commentsEntity = commentsRepository.getById(entityManager, valorizationDto.getCommentCode());
                commentsEntity.setBussinessUnitBuBisCode(valorizationDto.getBussinessUnitBuBisCode());
                commentsEntity.setCommentDescription(valorizationDto.getCommentDescription());
                commentsEntity.setCommentType(valorizationDto.getCommentType());
            }

            transaction.commit();
            System.out.println("Leido update CommentsService");

        } catch (Exception e){
            if (transaction!=null)
                transaction.rollback();
            e.printStackTrace();
            System.err.println("Modificar Comments Service no funciona");
        }
        finally {
            if (entityManager!=null)
                entityManager.close();
        }
    }

    public void deleteComments(CommentsGetDto commentsGetDto){
        //Variables de objeto
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        CommentsEntity commentsEntity = null;

        try{
            // Solicitamos una seccion en hibernate para la lectura
            entityManager = new EntityManagerHolder().getCurrentEntityManager();
            // Iniciamos un recurso de transaccion.
            transaction = entityManager.getTransaction();

            for (CommentsGetDto commentsDtoTable: commentsGetDto.getCommentsDtoSet()) {
                transaction.begin();
                // Valorizamos con parametros de objetoDTO a Identidad.
                commentsEntity = new CommentsEntity();
                commentsEntity = commentsRepository.getById(entityManager, commentsDtoTable.getCommentCode());
                commentsRepository.delete(entityManager,commentsEntity);
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