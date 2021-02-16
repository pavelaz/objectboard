package com.psg.objectboard.model.service;

import com.psg.objectboard.model.EntityManagerHolder;
import com.psg.objectboard.model.datatransferobject.ProjectGetDto;
import com.psg.objectboard.model.datatransferobject.ProjectInsertUpdateDto;
import com.psg.objectboard.model.entity.ProjectEntity;
import com.psg.objectboard.model.own.por_borrar.ProjectRepositoryImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.HashSet;
import java.util.List;

public class ProjectService {

    private ProjectRepositoryImpl projectRepository;

    public ProjectService() {
        this.projectRepository = new ProjectRepositoryImpl();
    }

    public ProjectService(ProjectRepositoryImpl projectRepository) {
        this.projectRepository = projectRepository;
    }

    public ProjectGetDto getListProject(){
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        ProjectGetDto projectGetDto = new ProjectGetDto();

        try{
            entityManager = new EntityManagerHolder().getCurrentEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();

            List<ProjectEntity> projectEntityList = projectRepository.getProjectList(entityManager);

            projectGetDto.setProjectDtoSet(new HashSet<>());
            for (ProjectEntity projectEntity: projectEntityList){
                final ProjectGetDto valorizationDto = new ProjectGetDto();
                valorizationDto.setPrIdProject(projectEntity.getPrIdProject());
                valorizationDto.setPrName(projectEntity.getPrName());
                valorizationDto.setPrNote(projectEntity.getPrNote());

                projectGetDto.getProjectDtoSet().add(valorizationDto);
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
        return projectGetDto;
    }

    public void insertProject(ProjectInsertUpdateDto projectInsertUpdateDto){
        EntityManager entityManager = null;
        EntityTransaction entityTransaction = null;

        try{
            //Solicitamos una seccion en hibernate para la insercion
            entityManager = new EntityManagerHolder().getCurrentEntityManager();
            //Iniciamos un recurso de transaccion.
            entityTransaction = entityManager.getTransaction();

            for(ProjectInsertUpdateDto valorizationDTO: projectInsertUpdateDto.getProjectInsertDtoSet()){
                entityTransaction.begin();
                // Instanciamos variable de objeto de tipo entidad
                ProjectEntity projectEntity = new ProjectEntity();
                // Valorizamos con parametros de objetoDTO a Identidad.
                projectEntity.setPrName(valorizationDTO.getPrName());
                projectEntity.setPrNote(valorizationDTO.getPrNote());
                // Construimos() objeto de tipo RepositoryImpl y implementamos metodo create() enviando parametros a seccion de persistencia
                projectRepository.createProject(entityManager, projectEntity);
                // Confirmamos la transacción del recursos actual y escribimos los cambios no vaciados en la base de datos.
                entityTransaction.commit();
            }

            System.out.println("Create to Project");
        }
        catch (Exception e){
            if (entityTransaction!=null)
                entityTransaction.rollback();
            e.printStackTrace();
            System.err.println("Project no funciona");
        }
        finally {
            if (entityManager!=null)
                entityManager.close();
        }

    }

    public void updateBussinnessType(ProjectInsertUpdateDto projectInsertUpdateDto){

        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            entityManager = new EntityManagerHolder().getCurrentEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();

            for (ProjectInsertUpdateDto valorizationDto: projectInsertUpdateDto.getProjectUpdateDtoSet()) {
                ProjectEntity projectEntity = projectRepository.getById(entityManager, valorizationDto.getPrIdProject());
                projectEntity.setPrName(valorizationDto.getPrName());
                projectEntity.setPrNote(valorizationDto.getPrNote());
            }

            transaction.commit();
            System.out.println("Leido update ProjectService");

        } catch (Exception e){
            if (transaction!=null)
                transaction.rollback();
            e.printStackTrace();
            System.err.println("Modificar Project Service no funciona");
        }
        finally {
            if (entityManager!=null)
                entityManager.close();
        }
    }

    public void deleteProject(ProjectGetDto projectGetDto){
        //Variables de objeto
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        ProjectEntity projectEntity = null;

        try{
            // Solicitamos una seccion en hibernate para la lectura
            entityManager = new EntityManagerHolder().getCurrentEntityManager();
            // Iniciamos un recurso de transaccion.
            transaction = entityManager.getTransaction();

            for (ProjectGetDto projectDtoTable: projectGetDto.getProjectDtoSet()) {
                transaction.begin();
                // Valorizamos con parametros de objetoDTO a Identidad.
                projectEntity = new ProjectEntity();
                projectEntity = projectRepository.getById(entityManager, projectDtoTable.getPrIdProject());
                projectRepository.delete(entityManager,projectEntity);
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