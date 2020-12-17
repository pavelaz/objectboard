package com.psg.objectboard.consoleview;

import com.psg.objectboard.controller.LanguageController;

public class main {
    public static void main(String [] args){

        /**
         * 1) Consulta un lenguaje
         */
        LanguageController languageController = new LanguageController();
        languageController.languageDetailPorte();

        /**
         * 1) Creacion de un lenguaje
         */
        /*LanguageController controller = new LanguageController();
        controller.createLanguage();*/

        /**
         * 1) Modificando un lenguaje
         */
        /*LanguageController languageController = new LanguageController();
        languageController.updateLanguage();*/

        /**
         * 1) Metodo eliminacion de un lenguaje
         */
        /*LanguageController controller = new LanguageController();
        controller.deleteLanguage();*/



    }
}
