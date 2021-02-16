package com.psg.objectboard.controller.common;

import com.psg.objectboard.model.service.Other.OtherFunctions;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Blob;
import java.sql.SQLException;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class FilesController extends HttpServlet {
    public static final long serialVersionUID = 1L;
    //private String CHEMIN_FICHIERS = System.getProperty("user.home") + "src/main/webapp/complements/temporaryfiles";
    private String CHEMIN_FICHIERS = "";

    public FilesController() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getNameFile(Part part) {
        for ( String contentDisposition : part.getHeader( "content-disposition" ).split( ";" ) ) {
            if ( contentDisposition.trim().startsWith( "filename" ) ) {
                return contentDisposition.substring( contentDisposition.indexOf( '=' ) + 1 ).trim().replace( "\"", "" );
            }
        }
        return null;
    }

    private void writerFile( Part part, String nomFichier, String chemin, int TAILLE_TAMPON ) throws IOException {
        BufferedInputStream entree = null;
        BufferedOutputStream sortie = null;
        try {
            entree = new BufferedInputStream(part.getInputStream(), TAILLE_TAMPON);
            System.out.println("chemin write " + chemin + nomFichier);
            sortie = new BufferedOutputStream(new FileOutputStream(new File(chemin + nomFichier)), TAILLE_TAMPON);

            byte[] tampon = new byte[TAILLE_TAMPON];
            int longueur;
            while ((longueur = entree.read(tampon)) > 0) {
                sortie.write(tampon, 0, longueur);
            }
        } finally {
            try {
                sortie.close();
            } catch (IOException ignore) {
            }
            try {
                entree.close();
            } catch (IOException ignore) {
            }
        }
    }

    /**
     * Metodo para escribir un archivo apartir de un campo tipo blob de base dato
     * @param blob
     * @param PathImage
     * @param extension
    **/
    public void writerImageInFolder(Blob blob, String PathImage, String extension){
        try {

            InputStream inputStream = null;
            try {
                inputStream = blob.getBinaryStream();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            BufferedImage image = ImageIO.read(inputStream);

            File outputfile = new File(PathImage); //
            ImageIO.write(image, extension, outputfile);

        } catch (IOException e) {
            System.out.println("Foto no existe en base de datos " + e);
        }
    }

    /**
     * Metodo para escribir un archivo apartir de un campo tipo byte de base dato
     * @param getFileByte
     * @param PathAndFile
     */
    public void writerFileInFolder(byte getFileByte[], String PathAndFile){
        String s = "Hello World! ";
        byte data[] = getFileByte;//s.getBytes();
        Path p = Paths.get(PathAndFile);

        try (OutputStream out = new BufferedOutputStream(
                Files.newOutputStream(p, CREATE, APPEND))) {
            out.write(data, 0, data.length);
        } catch (IOException x) {
            System.err.println(x);
        }
    }

    public String updateFile (HttpServletRequest request, int TAILLE_TAMPON, String field) {

        String route_file = null;

        try {
            if (request.getPart(field) != null) {
                // On récupère le champ du fichier
                Part part = request.getPart(field);
                // On vérifie qu'on a bien reçu un fichier
                String profile_photo = getNameFile(part);

                // Si on a bien un fichier
                if (profile_photo != null && !profile_photo.isEmpty()) {
                    //String nomChamp = part.getName(); Si queremos enviar el nombre del archivo a la JSP
                    // Corrige un bug du fonctionnement d'Internet Explorer
                    profile_photo = profile_photo.substring(profile_photo.lastIndexOf('/') + 1)
                            .substring(profile_photo.lastIndexOf('\\') + 1);
                    OtherFunctions of = new OtherFunctions();
                    this.CHEMIN_FICHIERS = of.searchLink("");

                    if (Files.exists(Paths.get(CHEMIN_FICHIERS))) {
                        // On écrit définitivement le fichier sur le disque
                        writerFile(part, profile_photo,CHEMIN_FICHIERS,TAILLE_TAMPON);
                        route_file = (CHEMIN_FICHIERS.concat(profile_photo));
                        System.out.println("On écrit définitivement le fichier sur le disque");
                    } else //if the folder not exists, we are creation a new
                    {
                        System.out.println("Folder does not exist");
                        try{
                            File file = new File(CHEMIN_FICHIERS);
                            file.mkdir();
                            writerFile(part, profile_photo, CHEMIN_FICHIERS, TAILLE_TAMPON);
                            route_file = (CHEMIN_FICHIERS.concat(profile_photo));
                            System.out.println("Folder created");
                        } catch(SecurityException se){
                            System.err.println(se);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
        return route_file;
    }

    public byte[] byteToBlobTransformation(String pathAndFile) {
        File file = new File(pathAndFile);
        byte[] blobFile = new byte[(int) file.length()];
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(blobFile);
            fileInputStream.close();
            System.out.println("File transformed into blob.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return blobFile;
    }

    public ServletOutputStream blobToDifferentFormats(HttpServletResponse response, byte[] blobFilDto, String formatFile) {
        byte[] file = blobFilDto;
        ServletOutputStream outputStream = null;
        try {
            response.setContentType(formatFile);
            outputStream = response.getOutputStream();
            outputStream.write(file);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outputStream;
    }

    public void deleteFile(String pathFile) throws IOException {
        File file = new File(pathFile);
        file.delete();
        System.out.println("Photo Delete the Server");
    }

    // Relacionados a la subida de archivos, creacion de directorios y movidas de archivos
    // Subir archivo forma paul (funciona)
    public static void subirArchivos(InputStream is, File f) throws IOException {
        FileOutputStream out = new FileOutputStream(f);
        int dato = is.read();
        while (dato != -1){
            out.write(dato);
            dato = is.read();
        }
        out.close();
        is.close();
    }

    // crear un directorio o carpeta si este no existe
    // recibe ruta completa con el nombre de la carpeta a crear
    public void CrearDirectorio (String rutaYCarpeta){
        File directorio = new File(rutaYCarpeta);
        if (!directorio.exists()){
            if(directorio.mkdirs()){
                System.out.println("Directorio creado");
            }else{
                System.out.println("Error al crear directorio");
            }
        }
    }

    // Copiar un archivo de una carpeta a otra dejando el archivo original en su sitio
    public void copiarArchivos(String pathOrigen,String pathDestino,String fichero){
        try{
            File ficheroCopiar = new File(pathOrigen, fichero);
            File ficheroDestino = new File(pathDestino, fichero);
            if (ficheroCopiar.exists()) {
                Files.copy(Paths.get(ficheroCopiar.getAbsolutePath()), Paths.get(ficheroDestino.getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);
            } else {
                System.out.println("El fichero " + fichero + " no existe en el directorio " + pathOrigen);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Mover un archivo de una carpeta a otra eliminando el original
    public void moverArchivos(String pathOrigen,String pathDestino,String fichero){
        try{
            File ficheroCopiar = new File(pathOrigen, fichero);
            File ficheroDestino = new File(pathDestino, fichero);
            if (ficheroCopiar.exists()) {
                Files.copy(Paths.get(ficheroCopiar.getAbsolutePath()), Paths.get(ficheroDestino.getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);
                eliminarFichero(ficheroCopiar);
            } else {
                System.out.println("El fichero " + fichero + " no existe en el directorio " + pathOrigen);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // eliminar archivos dentro de algun directorio
    // si el fichero a borrar es un directorio solo lo borra si esta vacio
    // en caso contrario devuelve true pero no lo borra
    public Boolean eliminarFichero(File fichero){
        if (!fichero.exists()){
            System.out.println("El fichero por eliminar no existe");
            return false;
        }else{
            fichero.delete(); // da mensaje de error cuendo el fichero no existe previamente
            System.out.println("El fichero original fue eliminado");
            return true;
        }
    }

    // borrar carpeta de archivos de forma recursiva
    // eliminando primero los ficheros internos
    public void borrarDirectorio (File directorio){

        File[] ficheros = directorio.listFiles();

        for (int x=0;x < ficheros.length;x++){
            if (ficheros[x].isDirectory()) {
                borrarDirectorio(ficheros[x]);
            }
            ficheros[x].delete();
        }
        if (directorio.delete()) {
            System.out.println("El directorio " + directorio + " ha sido borrado correctamente");
        }else {
            System.out.println("El directorio " + directorio + " no se ha podido borrar");
        }
    }

    public void writerInFolderFiles(String rutayarchivo,byte archivo[]) throws Exception {
        InputStream input = null;
        FileOutputStream output = null;

        try {

            File theFile = new File(rutayarchivo);
            output = new FileOutputStream(theFile);


            input = new ByteArrayInputStream(archivo);

            byte[] buffer = new byte[1024];
            while (input.read(buffer) > 0) {
                output.write(buffer);
            }

            System.out.println("\nSaved to file: " + theFile.getAbsolutePath());

            System.out.println("\nCompleted successfully!");

        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            if (input != null) {
                input.close();
            }

            if (output != null) {
                output.close();
            }
        }
    }

 }