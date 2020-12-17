package com.psg.objectboard.model.service;
import com.psg.objectboard.model.EntityManagerHolder;
import com.psg.objectboard.model.datatransferobject.LanguageDto;
import com.psg.objectboard.model.entity.LanguageEntity;
import com.psg.objectboard.model.repository.LanguageRepositoryImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;

public class LanguageService {
    private LanguageRepositoryImpl languageRepository;

    public LanguageService() {
        this.languageRepository = new LanguageRepositoryImpl();
    }

    public LanguageService(LanguageRepositoryImpl languageRepository) {
        this.languageRepository = languageRepository;
    }

    /**
     * 3) Creamos nuevo lenguaje: createLanguage de Servicio
     * Iniciamos un recurso de transaccion.
     * Instanciamos variable de objeto de tipo entidad
     * Realizamos pase de parametros de objetoDTO a Identidad.
     * Construimos() objeto de tipo RepositoryImpl y implementamos metodo create() enviado parametro de Identidad
     * Confirmamos la transacción del recursos actual y escribimos los cambios no vaciados en la base de datos.
     * @param languageDto
     */
        public void createLanguage(LanguageDto languageDto){
        EntityManager entityManager = null;
        EntityTransaction entityTransaction = null;

        try{
            //Solicitamos una seccion en hibernate para la insercion
            entityManager = new EntityManagerHolder().getCurrentEntityManager();
            //Iniciamos un recurso de transaccion.
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();

            // Instanciamos variable de objeto de tipo entidad
            LanguageEntity languageEntity = new LanguageEntity();

            // Valorizamos con parametros de objetoDTO a Identidad.
            languageEntity.setName(languageDto.getName());

            // Construimos() objeto de tipo RepositoryImpl y implementamos metodo create() enviado parametro de Identidad
            languageRepository.create(entityManager, languageEntity);

            // Confirmamos la transacción del recursos actual y escribimos los cambios no vaciados en la base de datos.
            entityTransaction.commit();

            System.out.println("Create to language");
        }
        catch (Exception e){
            if (entityTransaction!=null)
                entityTransaction.rollback();
            e.printStackTrace();
            System.err.println("LanguageDto no funciona");
        }
        finally {
            if (entityManager!=null)
                entityManager.close();
        }

    }

    /**
     * 3)
     * @param id
     * @return
     */
    public LanguageDto getLanguage(Long id){
        //Variables de objeto
        EntityManager entityManager=null;
        EntityTransaction transaction = null;
        LanguageEntity languageEntity= null;
        LanguageDto languageDto = null;

        try{
            //Solicitamos una seccion en hibernate para la lectura
            entityManager = new EntityManagerHolder().getCurrentEntityManager();
            //Iniciamos un recurso de transaccion.
            transaction = entityManager.getTransaction();
            transaction.begin();

            // Construimos() objeto de tipo RepositoryImpl y implementamos metodo create() enviado parametro de Identidad al fin Valorizamos
            languageEntity=languageRepository.getById(entityManager,id);

            // Instanciamos variable de objeto de tipo Dto
            languageDto = new LanguageDto();

            // Valorizamos con parametros de Dto con Entidad
            languageDto.setId(languageEntity.getId());
            languageDto.setName(languageEntity.getName());

            // Confirmamos la transacción del recursos actual y escribimos los cambios no vaciados en la base de datos.
            transaction.commit();
        }
        catch (Exception e){
            if (transaction!=null)
                transaction.rollback();
            e.printStackTrace();
            System.err.println("LanguageDto no funciona");
        }
        finally {
            if (entityManager!=null)
                entityManager.close();
        }
        return languageDto;
    }

    /**
     * Muestra lista de lenguajes
     * @return
     */
    public List<LanguageDto> list(){
        List<LanguageDto> languagesDto = new ArrayList<>();
        //Variables de objeto
        EntityManager entityManager=null;
        EntityTransaction transaction = null;

        try{
            //Solicitamos una seccion en hibernate para la lectura
            entityManager = new EntityManagerHolder().getCurrentEntityManager();
            //Iniciamos un recurso de transaccion.
            transaction = entityManager.getTransaction();
            transaction.begin();

            for (LanguageEntity languageEntity : languagesDto){
                final LanguageDto languageDto = new LanguageDto();
                languageDto.setId(languageEntity.getId());
                languageDto.setName(languageEntity.getName());
                languagesDto.add(languageDto);
            }

            // Confirmamos la transacción del recursos actual y escribimos los cambios no vaciados en la base de datos.
            transaction.commit();
        } catch (Exception e){
            if (transaction!=null)
                transaction.rollback();
            e.printStackTrace();
            System.err.println("Mostrar lista Service no funciona");
        }
        finally {
            if (entityManager!=null)
                entityManager.close();
        }
        return list();
    }

    /**
     * Metodo de eliminacion
     * @param identifier
     */
    public void deleteLanguage(long identifier){
        //Variables de objeto
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        LanguageEntity languageEntity = null;
        LanguageDto languageDto = null;

        try{
            // Solicitamos una seccion en hibernate para la lectura
            entityManager = new EntityManagerHolder().getCurrentEntityManager();
            // Iniciamos un recurso de transaccion.
            transaction = entityManager.getTransaction();
            transaction.begin();

            // Instanciamos variables de objeto
            languageEntity = new LanguageEntity();
            languageDto = new LanguageDto();

            // Valorizamos con parametros de Dto con Entidad
            languageDto.setId(languageEntity.getId());

            // Valorizamos con parametros de objetoDTO a Identidad.
            languageEntity = languageRepository.getById(entityManager,identifier);

            // Construimos() objeto de tipo RepositoryImpl y implementamos metodo create() enviado parametro de Identidad
            languageRepository.delete(entityManager,languageEntity);

            // Confirmamos la transacción del recursos actual y escribimos los cambios no vaciados en la base de datos.
            transaction.commit();
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
    /**
     * 3) Metodo de actualizacion del nombre
     * @param identifier
     * @param newName
     */
    public void updateLanguage(Long identifier, String newName){

        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        LanguageEntity languageEntity= null;

        try{
            //Solicitamos una seccion en hibernate para la lectura
            entityManager = new EntityManagerHolder().getCurrentEntityManager();
            //Iniciamos un recurso de transaccion.
            transaction = entityManager.getTransaction();
            transaction.begin();

            // Iniciamos consulta del objeto a modificar
            languageEntity = languageRepository.getById(entityManager,identifier);

            // Valorizando el objeto en estado de persistencia y invocamos Roquette en condicion de update
            languageRepository.updateLanguage(languageEntity, newName);

            // Confirmamos la transacción del recursos actual y escribimos los cambios no vaciados en la base de datos.
            transaction.commit();
        } catch (Exception e){
            if (transaction!=null)
                transaction.rollback();
            e.printStackTrace();
            System.err.println("Actualizar Service no funciona");
        }
        finally {
            if (entityManager!=null)
                entityManager.close();
        }


    }
}


