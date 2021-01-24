package com.psg.objectboard.controller.common;

import com.psg.objectboard.controller.servlet.MasterUserProfileServlet;
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
import java.nio.file.Paths;

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

    public void writerFileInFolder(BufferedImage FileByte, String PathFile, String extension){
        try {

            File outputfile = new File(PathFile); // PathImage tiene que ser igual = "/web/temporaryfile/saved.png"
            ImageIO.write(FileByte, extension, outputfile);

        } catch (IOException e) {
            System.out.println("Foto no existe en base de datos " + e);
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

    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        MasterUserProfileServlet masterUserProfileServlet = new MasterUserProfileServlet();
        masterUserProfileServlet.doOptions(request, response);

    }



}
