package com.psg.objectboard.controller;
import com.psg.objectboard.model.EntityManagerHolder;
import com.psg.objectboard.model.datatransferobject.LanguageDto;
import com.psg.objectboard.model.entity.LanguageEntity;
import com.psg.objectboard.model.service.LanguageService;

import javax.persistence.EntityManager;
import java.util.Scanner;

public class LanguageController {

    private LanguageService languageService;

    public LanguageController(){this.languageService = new LanguageService();}


    /**
     * 2) Consulta detallada del lenguaje
     */
    public void languageDetailPorte(){
        // 2) Solicitamos campo indice del lenguaje a detallar
        Scanner scanner=new Scanner(System.in);
        System.out.println("Entre lenguage code which you want to view?");
        long identifier=scanner.nextLong();

        // 4 y Fin) Instanciamos objeto Dto y Valorizamos desde la clase Servicio
        LanguageDto languageDto= languageService.getLanguage(identifier);
        System.out.println("The lenguage consulted is called = "+ languageDto.getName());
    }
    /**
     * 2) Capturamos por consola parametros a crear
     * realizamos pase de parametros a variable de objeto DTO
     * Construimos() objeto de tipo Service e implementamos metodo createLanguage() enviado parametro DTO
     */
    public void createLanguage(){
        // Solicitamos lenguaje a crear
        Scanner scanner = new Scanner(System.in);
        System.out.println("Language name to create?");
        String nameLanguage = scanner.nextLine();

        // Instanciamos variable de objeto de tipo DTO clase hija indentidad
        LanguageDto newLanguageDto = new LanguageDto();

        // Valorizamos sus parametro con los que recibimos por consola
        newLanguageDto.setName(nameLanguage);

        // Construimos() objeto de tipo Service e implementamos metodo createLanguage() enviado parametro DTO
        languageService.createLanguage(newLanguageDto);
    }
    /**
     * 2) Actualizar nombre de lenguaje
     */
    public void updateLanguage(){

        // 2) Solicitamos campo indice y newName lenguaje a modificar
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entre lenguage code which you want modify?");
        long identifier = scanner.nextLong();
        scanner.nextLine();//despues de haber solicitado un long con scanner, debemos repetir la Line() para nuevamente solicitar otro dato.

        System.out.println("Entre lenguage code which you want modify?");
        String newName = scanner.nextLine();

        languageService.updateLanguage(identifier, newName);


    }
    /**
     * 2) Capturamos por consola parametros a crear
     * realizamos pase de parametros a variable de objeto DTO
     * Construimos() objeto de tipo Service e implementamos metodo createLanguage() enviado parametro DTO
     */
    public void deleteLanguage(){
        // Solicitamos campo indice del lenguaje
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entre lenguage code which you want delete?");
        long identifier = scanner.nextLong();

        // Construimos() objeto de tipo Service e implementamos metodo createLanguage() enviado parametro DTO
        languageService.deleteLanguage(identifier);

    }
}
