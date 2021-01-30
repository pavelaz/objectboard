package com.psg.objectboard.model.service.Other;

import com.psg.objectboard.batch.App;
import com.psg.objectboard.model.own.ownsEntity.classVO.HeadersSurveyVO;
import com.psg.objectboard.model.own.ownsEntity.classVO.MasterUserVO;

import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

public class OtherFunctions {
    private String answer="";
    public String linkMedio="";

    public String buscaExtencionFiles(String nombre,String unidad) throws IOException {
        String unit_img_format;
        OtherFunctions of = new OtherFunctions();
        if (Integer.parseInt(unidad) == 1){
            unit_img_format = "png";
        }else{
            if (nombre.equals(of.searchLink("7"))){
                unit_img_format = "png";
            }else {
                String buscar = ".";
                if (!nombre.contains(buscar)) {
                    unit_img_format = "png";
                }else{
                    int position = nombre.indexOf(buscar) + 1;
                    int largo = nombre.length();
                    unit_img_format = nombre.substring(position,largo);
                }
            }
        }
        return unit_img_format;
    }

    public String buscaRetornoUserProfile(String companyNumber,String viene){
        String none = null;
        if (companyNumber.equals("1")){
            if (viene.equals("P")){
                none = "userprofile1";
            }else{
                none = "userprofile";
            }
        }else{
            none = "userprofile2";
        }
        return none;
    }

    public void searchLink() throws IOException {
        Properties vProp = new Properties();
        InputStream vInputStream = null;
        try {
            vInputStream = App.class.getResourceAsStream("/app.properties");
            vProp.load(vInputStream);
        } finally {
            if (vInputStream != null){
                vInputStream.close();
            }
        }
        this.linkMedio = vProp.getProperty("propert-ipapachetomcat");
        System.out.println(" ######## - >" + this.linkMedio);
    }

    public String searchLink(String valor) throws IOException {
        String none="";
        Properties vProp = new Properties();
        InputStream vInputStream = null;
        try {
            vInputStream = App.class.getResourceAsStream("/app.properties");
            vProp.load(vInputStream);
        } finally {
            if (vInputStream != null){
                vInputStream.close();
            }
        }
        if (valor.equals("")){
            none = vProp.getProperty("propert-pathcontainerfiles");
            return none;
        }
        if (valor.equals("0")){
            none = vProp.getProperty("propert-pathcomplements");
                //none = none.substring(0,none.length()-4);
            return none;
        }
        if (valor.equals("1")){
            none = vProp.getProperty("propert-persistencemysqldatabase");
            return none;
        }
        if (valor.equals("2")){
            none = vProp.getProperty("propert-ipmysqldatabase");
            return none;
        }
        if (valor.equals("3")){
            none = vProp.getProperty("propert-complements");
            return none;
        }
        if (valor.equals("4")){
            none = vProp.getProperty("propert-pathfiles");
            return none;
        }
        if (valor.equals("5")){
            none = vProp.getProperty("propert-pathimglogos");
            return none;
        }
        if (valor.equals("6")){
            none = vProp.getProperty("propert-environment");
            return none;
        }
        // nombre del logo por defecto del sistemas
        if (valor.equals("7")){
            none = vProp.getProperty("propert-defaultlogoname");
            return none;
        }
        return vProp.getProperty("propert-pathuserhome");
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

    // Subir archivo forma alex (funciona y coloca el archivo en carpeta temporal fuera del proyecto)

    /*public String subirArchivo(int codigo, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Part filePart = request.getPart("archivo"); // Obtiene el archivo
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.

        //InputStream fileContent = filePart.getInputStream(); //Lo transforma en InputStream

        String path="/archivos/";
        File uploads = new File(path); //Carpeta donde se guardan los archivos
        uploads.mkdirs(); //Crea los directorios necesarios
        File file = File.createTempFile("cod"+codigo+"-", "-"+fileName, uploads); //Evita que hayan dos archivos con el mismo nombre

        try (InputStream input = filePart.getInputStream()){
            Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }

        return file.getPath();
    }*/

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
    public Boolean borrarDirectorio (File directorio){
        File[] ficheros = directorio.listFiles();

        for (int x=0;x < ficheros.length;x++){
            if (ficheros[x].isDirectory()) {
                borrarDirectorio(ficheros[x]);
            }
            ficheros[x].delete();
        }
        if (directorio.delete()) {
            System.out.println("El directorio " + directorio + " ha sido borrado correctamente");
            return true;
        }else {
            System.out.println("El directorio " + directorio + " no se ha podido borrar");
            return false;
        }
    }
    //

    // genera cuerpos de email predeterminados
    public String bodyConfirmRegister(MasterUserVO muv,String company) throws IOException{
        String link;
            this.searchLink();
            //link = "http://localhost:8084" // esta parte debe sustituirse al poner el sistema en produccion
            //link = "http://35.202.62.183:8080" // esta parte debe sustituirse al poner el sistema en produccion
            link = "http://" + this.linkMedio
                 + "/objectboard"
                 + "/confirmemail?pu="
                 + muv.getMuEmail()+ "&ps="+ muv.getMuConfirmCode()+"&pc="+muv.getBussinessUnitBuBisCode();
            answer = "<h1>Our warmest regards</h1><p><br>"
                    + "It is a pleasure to inform you that you have successfully registered on our portal, so we have sent you "
                    + "in this email a link to activate your account, after this you can already use the portal with the modules "
                    + "which are activated by default. Please keep this email, which could be useful in case you forget all "
                    + "or part of the information with which you registered, which we attach below:"
                    + "</p>"
                    + "<p>Company: "+ company.toUpperCase() + "</p>"
                    + "<p>User Name: "+muv.getMuName()+"</p>"
                    + "<p>Login Email: "+muv.getMuEmail()+"</p>"
                    + "<p>Password: "+muv.getMuPassword()+"</p>"
                    + "<p>&nbsp;</p>"
                    + "<p>Email Confirmation Link:</p>"
                    + "<a href='" + link + "'>Confirm email address</a>";
            return answer;
    }

    public String bodyResetPassword(MasterUserVO muv,String company) throws IOException {
        String link,newPassword;
        LocalDateFunctions locDat = new LocalDateFunctions();
        LocalDate fecha = locDat.obtenerFechaActual();
        newPassword= generateRandomString(8);
        this.searchLink();
        link = "http://" + this.linkMedio // esta parte debe sustituirse al poner el sistema en produccion
               // + "/pages/jsp/process/confirm_reset.jsp?pu="
                + "/objectboard"
                + "/confirmreset?pu="
                + muv.getMuEmail() + "&ps="
                + newPassword + "&pc="
                + muv.getBussinessUnitBuBisCode() + "&pf="
                + fecha;
        answer = "<h1>Our warmest regards</h1><p><br>"
                + "It is with pleasure to inform you that after you click on the link that appears in this email, the new \n"
                + "password for your account will be as follows: "
                + "</p>"
                + "<p>Company: "+ company.toUpperCase() + "</p>"
                + "<p>Valid Date: "+ fecha + "</p>"
                + "<p>Login Email: "+muv.getMuEmail()+"</p>"
                + "<p>New Password: "+newPassword+"</p>"
                + "<p>&nbsp;</p>"
                + "<p>Email reset Link:</p>"
                + "<a href='" + link + "'>Reset Password System --></a>"
                + "<p>&nbsp;</p>"
                + "<p>This email will only be valid within the validity date that appears in the email. After this time,\n"
                + "if the link has not been followed, you must request the reset of your password again.</p>";
        return answer;
    }

    public String bodyInformationRequest(String nombre,String correo,String telefono,String mensaje, String fecha) throws IOException {
        answer = "<h1>Our warmest regards</h1><p><br>"
                + "We have received the following request for information under the following parameters: \n"
                + "</p>"
                + "<p>&nbsp;</p>"
                + "<p>Name: "+ nombre.toUpperCase() + "</p>"
                + "<p>Email: "+ correo + "</p>"
                + "<p>Phone: "+ telefono +"</p>"
                + "<p>Date  Request: "+fecha+"</p>"
                + "<p>&nbsp;</p>"
                + "<p>Message:</p>"
                + "<p>Message: "+mensaje+"</p>"
                + "<p>&nbsp;</p>"
                + "<p>In a short period of time, our staff will contact you by any of the available channels.</p>";
        return answer;
    }

    //  Devulve un campo string con caracteres random del largo solicitado
    public String generateRandomString(long largo){
        int cont=1;
        String texto = "";
        String [] mazo= {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
                "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
                "U", "V", "W", "X", "Y", "Z", "1", "2", "3", "4",
                "5", "6", "7", "8", "9", "0"};
        //contador para hacer el procedimiento la misma cantidad de veces que la cantidad de cartas que hay
        while (cont<=largo){
            //se aplica el random para desordenar el mazo genera numero del 1 al 36 sin incluir el 36
            int numRandon = (int) Math.round(Math.random() *35 ) ;

            //para que los valores vallan concatenados
            texto=texto+mazo[numRandon];

            //contador del while
            cont++;
        }
        //System.out.println("bloque ramdom: " + texto);
        return  texto;
    }

    public String generateRandomComplex(long largo){
        int cont=1;
        String texto = "";
        String [] mazo= {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
                "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
                "U", "V", "W", "X", "Y", "Z", "1", "2", "3", "4",
                "5", "6", "7", "8", "9", "0",
                "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
                "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
                "u", "v", "w", "x", "y", "z","*","%","#","@","!"};
        //contador para hacer el procedimiento la misma cantidad de veces que la cantidad de cartas que hay
        while (cont<=largo){
            //se aplica el random para desordenar el mazo genera numero del 1 al 36 sin incluir el 36
            int numRandon = (int) Math.round(Math.random() *66 ) ;

            //para que los valores vallan concatenados
            texto=texto+mazo[numRandon];

            //contador del while
            cont++;
        }
        //System.out.println("bloque ramdom: " + texto);
        return  texto;
    }

    // Resta dos fechas tipo date formato yyyy-MM-dd recibidas como string
    // fecha1 debe ser la mayor y 2 la menor
    public Integer restaFechasTDate(String fecha1, String fecha2) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date fechaInicial=dateFormat.parse(fecha1);

        Date fechaFinal=dateFormat.parse(fecha2);

        int dias=(int) ((fechaFinal.getTime()-fechaInicial.getTime())/86400000);

        System.out.println("Hay "+dias+" dias de diferencia");
        return dias;
    }

    public String sacaFecha(String fe){
        answer =  fe.substring(0,10);
        answer = answer.replace("/","-");
        return answer;
    }

    // convertir de hexagecimal a string
    public String hexToString(String hex) {
        return new String(new BigInteger(hex, 16).toByteArray());
    }

    // busca nomYbre y ubicacion del logo del cliente buscando en archivos -  eliminar este metodo
    public String buscaLogoYDirCliente(String numCia,String dataUser,String dataPassword) throws Exception{
        String info = null;
        String nombre_logo = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        OtherConexion cc = new OtherConexion();
        Connection cn = null;
        cn = cc.conectarse(dataUser,dataPassword);
        String sql = "SELECT bu_logo_name FROM bussinessUnit WHERE bu_bis_code=?";
        try{
            pst = cn.prepareStatement(sql);
            pst.setLong(1,Long.parseLong(numCia));
            rs = pst.executeQuery();
            if (rs.next()){ // valida si trae algun registro
                nombre_logo = rs.getString(1);
            }

            System.out.println("Busqueda exitosa");

            OtherFunctions of = new OtherFunctions();
            if (Long.parseLong(numCia) == 1){
                info =  of.searchLink("0") + "img/" + of.searchLink("7") ;
            }else {
                if (nombre_logo.equals(of.searchLink("7") )){
                    info = of.searchLink("0") + "img/" + of.searchLink("7") ;
                }else {
                    if (Long.parseLong(numCia) != 0) {
                        if (Long.parseLong(numCia) < 10) {
                            info = of.searchLink("0") + "img/logos/"
                                    + "0" + Long.parseLong(numCia) + "/" + nombre_logo;
                        } else {
                            info = of.searchLink("0") + "img/logos/"
                                    + Long.parseLong(numCia) + "/" + nombre_logo;
                        }
                    }
                }
            }
        }catch (SQLException ex){
            System.out.println("Error en la consulta: "+ex.getMessage());
        }finally {
            try{
                if (cn != null){
                    cn.close();
                    pst.close();
                    System.out.println("Conexion cerrada");
                }
            }catch (Exception e){
                System.out.println("Error "+e);
            }
        }

        return info;
    }

    // convertir de string a hexagecimal
    public String toHex(String arg) {
        String str = arg;
        char ch[] = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ch.length; i++) {
            sb.append(Integer.toHexString((int) ch[i]));
        }
        return (sb.toString());
    }

    // pasar de dato tipo blob a tipo byte[]
    public byte[] pasarBlobToByte(Blob blob) throws SQLException {
        int blobLength = (int) blob.length();
        byte[] blobAsBytes = blob.getBytes(1, blobLength);
        blob.free();
        return blobAsBytes;
    }

    // Seccion para auditsRevision.jsp
    public String valida_result(Long code,Integer evento,Integer typeRequest,String answer,String answerSolution,
                                String annexType,String onlyText,Double onlyNumber,String onlyTime,String onlyDate,
                                Integer question){
        String none = "";
        if(evento==1) {
            none = none + "<div class='card-footer'>\n";
        }
        none = none + "<hr>\n";
        none = none + "<div class=\"form-group row\">\n";
        if (annexType.equals("0")){
            none = none + "<div class=\"col-md-12\" align=\"right\">\n";
            none = none + "<input type=\"hidden\" id=\"comen\" name=\"p_comen_" + question + "\"  value=\" \">\n";
            none = none + "<label for=\"co\" class=\"col-form-label\">Result of the audit:</label>&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;\n";
            if (typeRequest == 1 || typeRequest==2 || typeRequest==3){
                if (answer.equals(answerSolution)){
                    none = none + "<input type=\"radio\" id=\"codep_" + question + "_1\" name=\"p_audi_" + question + "\" value=\"T\" checked disabled>&nbsp;&nbsp;\n";
                    none = none + "<label for=\"co\" class= \"col-form-label\" >Correct</label>&nbsp;&nbsp; &nbsp; &nbsp;\n";
                    none = none + "<input type=\"radio\" id=\"codep_" + question + "_2\" name=\"p_audi_" + question + "\" value=\"F\" disabled>&nbsp;&nbsp;\n";
                    none = none + "<label for=\"co\" class=\"col-form-label\" >Incorrect</label>\n";
                }else{
                    none = none + "<input type=\"radio\" id=\"codep_" + question + "_1\" name=\"p_audi_" + question + "\" value=\"T\" disabled>&nbsp;&nbsp;\n";
                    none = none + "<label for=\"co\" class= \"col-form-label\" >Correct</label>&nbsp;&nbsp; &nbsp; &nbsp;\n";
                    none = none + "<input type=\"radio\" id=\"codep_" + question + "_2\" name=\"p_audi_" + question + "\" value=\"F\" checked disabled>&nbsp;&nbsp;\n";
                    none = none + "<label for=\"co\" class=\"col-form-label\" >Incorrect</label>\n";
                }
            }
            if (typeRequest == 6){
                if (onlyText.trim().length()==0){
                    none = none + "<input type=\"radio\" id=\"codep_" + question + "_1\" name=\"p_audi_" + question + "\" value=\"T\">&nbsp;&nbsp;\n";
                    none = none + "<label for=\"co\" class= \"col-form-label\" >Correct</label>&nbsp;&nbsp; &nbsp; &nbsp;\n";
                    none = none + "<input type=\"radio\" id=\"codep_" + question + "_2\" name=\"p_audi_" + question + "\" value=\"F\">&nbsp;&nbsp;\n";
                    none = none + "<label for=\"co\" class=\"col-form-label\" >Incorrect</label>\n";
                }else{
                    if (answer.equals(onlyText)){
                        none = none + "<input type=\"radio\" id=\"codep_" + question + "_1\" name=\"p_audi_" + question + "\" value=\"T\" checked disabled>&nbsp;&nbsp;\n";
                        none = none + "<label for=\"co\" class= \"col-form-label\" >Correct</label>&nbsp;&nbsp; &nbsp; &nbsp;\n";
                        none = none + "<input type=\"radio\" id=\"codep_" + question + "_2\" name=\"p_audi_" + question + "\" value=\"F\" disabled>&nbsp;&nbsp;\n";
                        none = none + "<label for=\"co\" class=\"col-form-label\" >Incorrect</label>\n";
                    }else{
                        none = none + "<input type=\"radio\" id=\"codep_" + question + "_1\" name=\"p_audi_" + question + "\" value=\"T\" disabled>&nbsp;&nbsp;\n";
                        none = none + "<label for=\"co\" class= \"col-form-label\" >Correct</label>&nbsp;&nbsp; &nbsp; &nbsp;\n";
                        none = none + "<input type=\"radio\" id=\"codep_" + question + "_2\" name=\"p_audi_" + question + "\" value=\"F\" checked disabled>&nbsp;&nbsp;\n";
                        none = none + "<label for=\"co\" class=\"col-form-label\" >Incorrect</label>\n";
                    }
                }
            }
            if (typeRequest == 7){
                if (onlyNumber==0){
                    none = none + "<input type=\"radio\" id=\"codep_" + question + "_1\" name=\"p_audi_" + question + "\" value=\"T\">&nbsp;&nbsp;\n";
                    none = none + "<label for=\"co\" class= \"col-form-label\" >Correct</label>&nbsp;&nbsp; &nbsp; &nbsp;\n";
                    none = none + "<input type=\"radio\" id=\"codep_" + question + "_2\" name=\"p_audi_" + question + "\" value=\"F\">&nbsp;&nbsp;\n";
                    none = none + "<label for=\"co\" class=\"col-form-label\" >Incorrect</label>\n";
                }else{
                    if (Double.parseDouble(answer) == onlyNumber){
                        none = none + "<input type=\"radio\" id=\"codep_" + question + "_1\" name=\"p_audi_" + question + "\" value=\"T\" checked disabled>&nbsp;&nbsp;\n";
                        none = none + "<label for=\"co\" class= \"col-form-label\" >Correct</label>&nbsp;&nbsp; &nbsp; &nbsp;\n";
                        none = none + "<input type=\"radio\" id=\"codep_" + question + "_2\" name=\"p_audi_" + question + "\" value=\"F\" disabled>&nbsp;&nbsp;\n";
                        none = none + "<label for=\"co\" class=\"col-form-label\" >Incorrect</label>\n";
                    }else{
                        none = none + "<input type=\"radio\" id=\"codep_" + question + "_1\" name=\"p_audi_" + question + "\" value=\"T\" disabled>&nbsp;&nbsp;\n";
                        none = none + "<label for=\"co\" class= \"col-form-label\" >Correct</label>&nbsp;&nbsp; &nbsp; &nbsp;\n";
                        none = none + "<input type=\"radio\" id=\"codep_" + question + "_2\" name=\"p_audi_" + question + "\" value=\"F\" checked disabled>&nbsp;&nbsp;\n";
                        none = none + "<label for=\"co\" class=\"col-form-label\" >Incorrect</label>\n";
                    }
                }
            }
            if (typeRequest==8){
                if (onlyTime.trim().length()==0){
                    none = none + "<input type=\"radio\" id=\"codep_" + question + "_1\" name=\"p_audi_" + question + "\" value=\"T\">&nbsp;&nbsp;\n";
                    none = none + "<label for=\"co\" class= \"col-form-label\" >Correct</label>&nbsp;&nbsp; &nbsp; &nbsp;\n";
                    none = none + "<input type=\"radio\" id=\"codep_" + question + "_2\" name=\"p_audi_" + question + "\" value=\"F\">&nbsp;&nbsp;\n";
                    none = none + "<label for=\"co\" class=\"col-form-label\" >Incorrect</label>\n";
                }else{
                    if (answer.equals(onlyTime)){
                        none = none + "<input type=\"radio\" id=\"codep_" + question + "_1\" name=\"p_audi_" + question + "\" value=\"T\" checked disabled>&nbsp;&nbsp;\n";
                        none = none + "<label for=\"co\" class= \"col-form-label\" >Correct</label>&nbsp;&nbsp; &nbsp; &nbsp;\n";
                        none = none + "<input type=\"radio\" id=\"codep_" + question + "_2\" name=\"p_audi_" + question + "\" value=\"F\" disabled>&nbsp;&nbsp;\n";
                        none = none + "<label for=\"co\" class=\"col-form-label\" >Incorrect</label>\n";
                    }else{
                        none = none + "<input type=\"radio\" id=\"codep_" + question + "_1\" name=\"p_audi_" + question + "\" value=\"T\" disabled>&nbsp;&nbsp;\n";
                        none = none + "<label for=\"co\" class= \"col-form-label\" >Correct</label>&nbsp;&nbsp; &nbsp; &nbsp;\n";
                        none = none + "<input type=\"radio\" id=\"codep_" + question + "_2\" name=\"p_audi_" + question + "\" value=\"F\" checked disabled>&nbsp;&nbsp;\n";
                        none = none + "<label for=\"co\" class=\"col-form-label\" >Incorrect</label>\n";
                    }
                }
            }
            if (typeRequest==9){
                if (onlyDate.trim().length()==0){
                    none = none + "<input type=\"radio\" id=\"codep_" + question + "_1\" name=\"p_audi_" + question + "\" value=\"T\">&nbsp;&nbsp;\n";
                    none = none + "<label for=\"co\" class= \"col-form-label\" >Correct</label>&nbsp;&nbsp; &nbsp; &nbsp;\n";
                    none = none + "<input type=\"radio\" id=\"codep_" + question + "_2\" name=\"p_audi_" + question + "\" value=\"F\">&nbsp;&nbsp;\n";
                    none = none + "<label for=\"co\" class=\"col-form-label\" >Incorrect</label>\n";
                }else{
                    if (answer.equals(onlyDate)){
                        none = none + "<input type=\"radio\" id=\"codep_" + question + "_1\" name=\"p_audi_" + question + "\" value=\"T\" checked disabled>&nbsp;&nbsp;\n";
                        none = none + "<label for=\"co\" class= \"col-form-label\" >Correct</label>&nbsp;&nbsp; &nbsp; &nbsp;\n";
                        none = none + "<input type=\"radio\" id=\"codep_" + question + "_2\" name=\"p_audi_" + question + "\" value=\"F\" disabled>&nbsp;&nbsp;\n";
                        none = none + "<label for=\"co\" class=\"col-form-label\" >Incorrect</label>\n";
                    }else{
                        none = none + "<input type=\"radio\" id=\"codep_" + question + "_1\" name=\"p_audi_" + question + "\" value=\"T\" disabled>&nbsp;&nbsp;\n";
                        none = none + "<label for=\"co\" class= \"col-form-label\" >Correct</label>&nbsp;&nbsp; &nbsp; &nbsp;\n";
                        none = none + "<input type=\"radio\" id=\"codep_" + question + "_2\" name=\"p_audi_" + question + "\" value=\"F\" checked disabled>&nbsp;&nbsp;\n";
                        none = none + "<label for=\"co\" class=\"col-form-label\" >Incorrect</label>\n";
                    }
                }
            }
        }else{
            none = none + "<div class=\"col-md-2\" align=\"left\">\n";
            none = none + "<label for=\"co\" class=\"col-form-label\">Audit Comment:</label>\n";
            none = none + "</div>\n";
            none = none + "<div class=\"col-md-4\" align=\"left\">\n";
            none = none + "<input type=\"text\" id=\"comen\" name=\"p_comen_" + question + "\" class=\"form-control\" maxlength=\"145\" placeholder=\"Comment the result\">\n";
            none = none + "</div>\n";
            none = none + "<div class=\"col-md-6\" align=\"right\">\n";
            none = none + "<label for=\"co\" class=\"col-form-label\">Result of the audit:</label>&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;\n";
            if (typeRequest == 1 || typeRequest==2 || typeRequest==3){
                if (answer.equals(answerSolution)){
                    none = none + "<input type=\"radio\" id=\"codep_" + question + "_1\" name=\"p_audi_" + question + "\" value=\"T\" checked>&nbsp;&nbsp;\n";
                    none = none + "<label for=\"co\" class= \"col-form-label\" >Correct</label>&nbsp;&nbsp; &nbsp; &nbsp;\n";
                    none = none + "<input type=\"radio\" id=\"codep_" + question + "_2\" name=\"p_audi_" + question + "\" value=\"F\" >&nbsp;&nbsp;\n";
                    none = none + "<label for=\"co\" class=\"col-form-label\" >Incorrect</label>\n";
                }else{
                    none = none + "<input type=\"radio\" id=\"codep_" + question + "_1\" name=\"p_audi_" + question + "\" value=\"T\" >&nbsp;&nbsp;\n";
                    none = none + "<label for=\"co\" class= \"col-form-label\" >Correct</label>&nbsp;&nbsp; &nbsp; &nbsp;\n";
                    none = none + "<input type=\"radio\" id=\"codep_" + question + "_2\" name=\"p_audi_" + question + "\" value=\"F\" checked>&nbsp;&nbsp;\n";
                    none = none + "<label for=\"co\" class=\"col-form-label\" >Incorrect</label>\n";
                }
            }
            if (typeRequest == 4 || typeRequest == 5){
                none = none + "<input type=\"radio\" id=\"codep_" + question + "_1\" name=\"p_audi_" + question + "\" value=\"T\">&nbsp;&nbsp;\n";
                none = none + "<label for=\"co\" class= \"col-form-label\" >Correct</label>&nbsp;&nbsp; &nbsp; &nbsp;\n";
                none = none + "<input type=\"radio\" id=\"codep_" + question + "_2\" name=\"p_audi_" + question + "\" value=\"F\">&nbsp;&nbsp;\n";
                none = none + "<label for=\"co\" class=\"col-form-label\" >Incorrect</label>\n";
            }
            if (typeRequest == 6){
                if (onlyText.trim().length()==0){
                    none = none + "<input type=\"radio\" id=\"codep_" + question + "_1\" name=\"p_audi_" + question + "\" value=\"T\">&nbsp;&nbsp;\n";
                    none = none + "<label for=\"co\" class= \"col-form-label\" >Correct</label>&nbsp;&nbsp; &nbsp; &nbsp;\n";
                    none = none + "<input type=\"radio\" id=\"codep_" + question + "_2\" name=\"p_audi_" + question + "\" value=\"F\">&nbsp;&nbsp;\n";
                    none = none + "<label for=\"co\" class=\"col-form-label\" >Incorrect</label>\n";
                }else{
                    if (answer.equals(onlyText)){
                        none = none + "<input type=\"radio\" id=\"codep_" + question + "_1\" name=\"p_audi_" + question + "\" value=\"T\" checked>&nbsp;&nbsp;\n";
                        none = none + "<label for=\"co\" class= \"col-form-label\" >Correct</label>&nbsp;&nbsp; &nbsp; &nbsp;\n";
                        none = none + "<input type=\"radio\" id=\"codep_" + question + "_2\" name=\"p_audi_" + question + "\" value=\"F\" >&nbsp;&nbsp;\n";
                        none = none + "<label for=\"co\" class=\"col-form-label\" >Incorrect</label>\n";
                    }else{
                        none = none + "<input type=\"radio\" id=\"codep_" + question + "_1\" name=\"p_audi_" + question + "\" value=\"T\" >&nbsp;&nbsp;\n";
                        none = none + "<label for=\"co\" class= \"col-form-label\" >Correct</label>&nbsp;&nbsp; &nbsp; &nbsp;\n";
                        none = none + "<input type=\"radio\" id=\"codep_" + question + "_2\" name=\"p_audi_" + question + "\" value=\"F\" checked >&nbsp;&nbsp;\n";
                        none = none + "<label for=\"co\" class=\"col-form-label\" >Incorrect</label>\n";
                    }
                }
            }
            if (typeRequest == 7){
                if (onlyNumber==0){
                    none = none + "<input type=\"radio\" id=\"codep_" + question + "_1\" name=\"p_audi_" + question + "\" value=\"T\">&nbsp;&nbsp;\n";
                    none = none + "<label for=\"co\" class= \"col-form-label\" >Correct</label>&nbsp;&nbsp; &nbsp; &nbsp;\n";
                    none = none + "<input type=\"radio\" id=\"codep_" + question + "_2\" name=\"p_audi_" + question + "\" value=\"F\">&nbsp;&nbsp;\n";
                    none = none + "<label for=\"co\" class=\"col-form-label\" >Incorrect</label>\n";
                }else{
                    if (Double.parseDouble(answer) == onlyNumber){
                        none = none + "<input type=\"radio\" id=\"codep_" + question + "_1\" name=\"p_audi_" + question + "\" value=\"T\" checked>&nbsp;&nbsp;\n";
                        none = none + "<label for=\"co\" class= \"col-form-label\" >Correct</label>&nbsp;&nbsp; &nbsp; &nbsp;\n";
                        none = none + "<input type=\"radio\" id=\"codep_" + question + "_2\" name=\"p_audi_" + question + "\" value=\"F\" >&nbsp;&nbsp;\n";
                        none = none + "<label for=\"co\" class=\"col-form-label\" >Incorrect</label>\n";
                    }else{
                        none = none + "<input type=\"radio\" id=\"codep_" + question + "_1\" name=\"p_audi_" + question + "\" value=\"T\" >&nbsp;&nbsp;\n";
                        none = none + "<label for=\"co\" class= \"col-form-label\" >Correct</label>&nbsp;&nbsp; &nbsp; &nbsp;\n";
                        none = none + "<input type=\"radio\" id=\"codep_" + question + "_2\" name=\"p_audi_" + question + "\" value=\"F\" checked>&nbsp;&nbsp;\n";
                        none = none + "<label for=\"co\" class=\"col-form-label\" >Incorrect</label>\n";
                    }
                }
            }
            if (typeRequest==8){
                if (onlyTime.trim().length()==0){
                    none = none + "<input type=\"radio\" id=\"codep_" + question + "_1\" name=\"p_audi_" + question + "\" value=\"T\">&nbsp;&nbsp;\n";
                    none = none + "<label for=\"co\" class= \"col-form-label\" >Correct</label>&nbsp;&nbsp; &nbsp; &nbsp;\n";
                    none = none + "<input type=\"radio\" id=\"codep_" + question + "_2\" name=\"p_audi_" + question + "\" value=\"F\">&nbsp;&nbsp;\n";
                    none = none + "<label for=\"co\" class=\"col-form-label\" >Incorrect</label>\n";
                }else{
                    if (answer.equals(onlyTime)){
                        none = none + "<input type=\"radio\" id=\"codep_" + question + "_1\" name=\"p_audi_" + question + "\" value=\"T\" checked>&nbsp;&nbsp;\n";
                        none = none + "<label for=\"co\" class= \"col-form-label\" >Correct</label>&nbsp;&nbsp; &nbsp; &nbsp;\n";
                        none = none + "<input type=\"radio\" id=\"codep_" + question + "_2\" name=\"p_audi_" + question + "\" value=\"F\" >&nbsp;&nbsp;\n";
                        none = none + "<label for=\"co\" class=\"col-form-label\" >Incorrect</label>\n";
                    }else{
                        none = none + "<input type=\"radio\" id=\"codep_" + question + "_1\" name=\"p_audi_" + question + "\" value=\"T\" >&nbsp;&nbsp;\n";
                        none = none + "<label for=\"co\" class= \"col-form-label\" >Correct</label>&nbsp;&nbsp; &nbsp; &nbsp;\n";
                        none = none + "<input type=\"radio\" id=\"codep_" + question + "_2\" name=\"p_audi_" + question + "\" value=\"F\" checked>&nbsp;&nbsp;\n";
                        none = none + "<label for=\"co\" class=\"col-form-label\" >Incorrect</label>\n";
                    }
                }
            }
            if (typeRequest==9){
                if (onlyDate.trim().length()==0){
                    none = none + "<input type=\"radio\" id=\"codep_" + question + "_1\" name=\"p_audi_" + question + "\" value=\"T\">&nbsp;&nbsp;\n";
                    none = none + "<label for=\"co\" class= \"col-form-label\" >Correct</label>&nbsp;&nbsp; &nbsp; &nbsp;\n";
                    none = none + "<input type=\"radio\" id=\"codep_" + question + "_2\" name=\"p_audi_" + question + "\" value=\"F\">&nbsp;&nbsp;\n";
                    none = none + "<label for=\"co\" class=\"col-form-label\" >Incorrect</label>\n";
                }else{
                    if (answer.equals(onlyDate)){
                        none = none + "<input type=\"radio\" id=\"codep_" + question + "_1\" name=\"p_audi_" + question + "\" value=\"T\" checked>&nbsp;&nbsp;\n";
                        none = none + "<label for=\"co\" class= \"col-form-label\" >Correct</label>&nbsp;&nbsp; &nbsp; &nbsp;\n";
                        none = none + "<input type=\"radio\" id=\"codep_" + question + "_2\" name=\"p_audi_" + question + "\" value=\"F\" >&nbsp;&nbsp;\n";
                        none = none + "<label for=\"co\" class=\"col-form-label\" >Incorrect</label>\n";
                    }else{
                        none = none + "<input type=\"radio\" id=\"codep_" + question + "_1\" name=\"p_audi_" + question + "\" value=\"T\" >&nbsp;&nbsp;\n";
                        none = none + "<label for=\"co\" class= \"col-form-label\" >Correct</label>&nbsp;&nbsp; &nbsp; &nbsp;\n";
                        none = none + "<input type=\"radio\" id=\"codep_" + question + "_2\" name=\"p_audi_" + question + "\" value=\"F\" checked>&nbsp;&nbsp;\n";
                        none = none + "<label for=\"co\" class=\"col-form-label\" >Incorrect</label>\n";
                    }
                }
            }
        }

        none = none + "</div>\n";
        none = none + "</div>\n";
        if(evento==1) {
            none = none + "</div>\n";
        }
        return none;
    }

    public String valida_comentario(Integer format,Long code,String coment){
        String none = "";
        none = none + "<div class=\"form-group row\">\n";
        none = none + "<div class=\"col-sm-2\">\n";
        none = none + "<label for=\"co\" class=\"col-form-label\">Comment :</label>\n";
        none = none + "</div>\n";
        none = none + "<div class=\"col-sm-10\" >\n";
        if (format == 2) {
            none = none + "<textarea id=\"co\" name=\"p_co_" + code + "\" rows=\"1\" maxlength=\"145\"\n" +
                    "            cols=\"80\" class=\"form-control\" placeholder=\"Write here the comment.\" onfocus=\"selecciona_contenido(this)\"\n" +
                    "                   disabled >\n";
            none = none + coment + "</textarea>";
        }else {
            none = none + "<textarea id=\"co\" name=\"p_co_" + code + "\" rows=\"1\" maxlength=\"145\"\n" +
                    "            cols=\"80\" class=\"form-control\" placeholder=\"Write here the comment.\" onfocus=\"selecciona_contenido(this)\" disabled></textarea>";
        }
        none = none + "</div>\n";
        none = none + "</div>\n";
        return none;
    }

    public String valida_tipoArchivo(Integer ctaLinea,String annexType, String nameAnnexFile,String dir_doc,String dir_img,String directorio_gral,Integer format){
        String none = "";
        if (ctaLinea == 1){
            if (annexType.equals("1")){
                none = none + "Validate the document:\n";
            }else{
                none = none + "Validate the image:\n";
            }
        }
        if (ctaLinea == 2) {
            if (annexType.equals("1")) {
                if(format == 2) {
                    none = none + "<a href='" + dir_doc + nameAnnexFile + "' title=\"View Document\" >\n";
                    none = none + "<img src=\"" + directorio_gral + "document_gral.png\" alt=\"Profile-document\" width=\"100\" height=\"60\" border=\"1\">\n";
                    none = none + "</a>\n";
                }else{
                    none = none + "<a href='#!' title=\"View Document\" >\n";
                    none = none + "<img src=\"" + directorio_gral + "document_gral.png\" alt=\"Profile-document\" width=\"100\" height=\"60\" border=\"1\">\n";
                    none = none + "</a>\n";
                }
            } else {
                if(format == 2) {
                    none = none + "<a href='" + dir_img + nameAnnexFile + "' title=\"View Image\" >\n";
                    none = none + "<img src=\"" + directorio_gral + "image_gral.jpg\" alt=\"Profile-image\" width=\"100\" height=\"60\" border=\"1\">\n";
                    none = none + "</a>\n";
                }else{
                    none = none + "<a href='#!' title=\"View Image\" >\n";
                    none = none + "<img src=\"" + directorio_gral + "image_gral.jpg\" alt=\"Profile-image\" width=\"100\" height=\"60\" border=\"1\">\n";
                    none = none + "</a>\n";
                }
            }
        }
        return none;
    }

    public String valida_mensaFiletype(String annexType){
        String none = "";
        if (!annexType.equals("0")){
            if (annexType.equals("1")){
                none = none + "Validate the document:\n";
            }else{
                none = none + "Validate the image:\n";
            }
        }
        return none;
    }

    public String valida_mensaFile(String annexType,String dir_doc,String nameAnnexFile,String directorio_gral,String dir_img,Integer format){
        String none = "";
        if (!annexType.equals("0")){
            if (annexType.equals("1")){
                if(format == 2) {
                    none = none + "<a href='" + dir_doc + nameAnnexFile + "' title=\"View Document\" >\n";
                    none = none + "<img src=\"" + directorio_gral + "document_gral.png\" alt=\"Profile-document\" width=\"100\" height=\"60\" border=\"1\">\n";
                    none = none + "</a>\n";
                }else{
                    none = none + "<a href='#!' title=\"View Document\" >\n";
                    none = none + "<img src=\"" + directorio_gral + "document_gral.png\" alt=\"Profile-document\" width=\"100\" height=\"60\" border=\"1\">\n";
                    none = none + "</a>\n";
                }
            }else{
                if(format == 2) {
                    none = none + "<a href='" + dir_img + nameAnnexFile + "' title=\"View Image\" >\n";
                    none = none + "<img src=\"" + directorio_gral + "image_gral.jpg\" alt=\"Profile-image\" width=\"100\" height=\"60\" border=\"1\">\n";
                    none = none + "</a>\n";
                }else{
                    none = none + "<a href='#!' title=\"View Document\" >\n";
                    none = none + "<img src=\"" + directorio_gral + "document_gral.png\" alt=\"Profile-document\" width=\"100\" height=\"60\" border=\"1\">\n";
                    none = none + "</a>\n";
                }
            }
        }
        return none;
    }

    // Seccion para header_polls.jsp
    public String pone_pieHeaderPolls(String column) {
        String none = "";
        if (column.equals("0")) {
            none = none + "<td>&nbsp;</td>";
            none = none + "<td>Not Action</td>";
            none = none + "<td>Not data</td>";
            none = none + "<td>Not data</td>";
            none = none + "<td>Not data</td>";
            none = none + "<td>Not data</td>";
            none = none + "<td>Not data</td>";
            none = none + "<td>Not data</td>";
        } else {
            none = none + "<td>&nbsp;</td>";
            none = none + "<td>Not Action</td>";
            none = none + "<td>Not data</td>";
            none = none + "<td>Not data</td>";
            none = none + "<td>Not data</td>";
            none = none + "<td>Not data</td>";
            none = none + "<td>Not data</td>";
            none = none + "<td>Not data</td>";
            none = none + "<td>Not data</td>";
            none = none + "<td>Not data</td>";
            none = none + "<td>Not data</td>";
            none = none + "<td>Not data</td>";
            none = none + "<td>Not data</td>";
            none = none + "<td>Not data</td>";
            none = none + "<td>Not data</td>";
            none = none + "<td>Not data</td>";
            none = none + "<td>Not data</td>";
            none = none + "<td>Not data</td>";
        }
        return none;
    }

    public String pone_encabezadoHeaderPolls(String column){
        String none = "";
        if (column.equals("0")){
            none = none + "<th data-field=\"state\" data-valign=\"middle\" data-align=\"center\" data-sortable=\"false\" >\n";
            none = none + "<input type='checkbox' onclick='marcar(this);' />\n";
            none = none + "</th>\n";
            none = none + "<th data-field=\"ac\" data-sortable=\"false\" data-valign=\"middle\" data-align=\"center\">ACTION</th>\n";
            none = none + "<th data-field=\"l1\" data-sortable=\"true\" data-switchable=\"false\">DESCRIPTION NAME</th>\n";
            none = none + "<th data-field=\"l3\" data-sortable=\"true\" data-switchable=\"true\">STATUS</th>\n";
            none = none + "<th data-field=\"l6\" data-sortable=\"true\" data-switchable=\"true\">DATE CREATION</th>\n";
            none = none + "<th data-field=\"l6\" data-sortable=\"true\" data-switchable=\"true\">No.REQUEST</th>\n";
            none = none + "<th data-field=\"l16\" data-sortable=\"true\" data-switchable=\"true\">AUDITED</th>\n";
            none = none + "<th data-field=\"l16\" data-sortable=\"true\" data-switchable=\"true\">TOTAL POINTS</th>\n";
        }else {
            none = none + "<th data-field=\"state\" data-valign=\"middle\" data-align=\"center\" data-sortable=\"false\" >";
            none = none + "<input type='checkbox' onclick='marcar(this);' />";
            none = none + "</th>";
            none = none + "<th data-field=\"ac\" data-sortable=\"false\" data-valign=\"middle\" data-align=\"center\">ACTION</th>";
            none = none + "<th data-field=\"l1\" data-sortable=\"true\" data-switchable=\"false\">DESCRIPTION NAME</th>";
            none = none + "<th data-field=\"l2\" data-sortable=\"true\" data-switchable=\"true\">REFERENCE</th>";
            none = none + "<th data-field=\"l3\" data-sortable=\"true\" data-switchable=\"true\">STATUS</th>";
            none = none + "<th data-field=\"l5\" data-sortable=\"true\" data-switchable=\"true\">VERSION</th>";
            none = none + "<th data-field=\"l6\" data-sortable=\"true\" data-switchable=\"true\">DATE CREATION</th>";
            none = none + "<th data-field=\"l6\" data-sortable=\"true\" data-switchable=\"true\">No.REQUEST</th>";
            none = none + "<th data-field=\"l16\" data-sortable=\"true\" data-switchable=\"true\">AUDITED</th>";
            none = none + "<th data-field=\"l7\" data-sortable=\"true\" data-switchable=\"false\">ORG LEVEL1</th>";
            none = none + "<th data-field=\"l9\" data-sortable=\"true\" data-switchable=\"true\">ORG LEVEL2</th>";
            none = none + "<th data-field=\"l10\" data-sortable=\"true\" data-switchable=\"true\">ORG LEVEL3</th>";
            none = none + "<th data-field=\"l11\" data-sortable=\"true\" data-switchable=\"true\">ORG LEVEL4</th>";
            none = none + "<th data-field=\"l12\" data-sortable=\"true\" data-switchable=\"false\">TYP LEVEL1</th>";
            none = none + "<th data-field=\"l13\" data-sortable=\"true\" data-switchable=\"true\">TYP LEVEL2</th>";
            none = none + "<th data-field=\"l14\" data-sortable=\"true\" data-switchable=\"true\">TYP LEVEL3</th>";
            none = none + "<th data-field=\"l15\" data-sortable=\"true\" data-switchable=\"true\">LAST MODIF.</th>";
            none = none + "<th data-field=\"l16\" data-sortable=\"true\" data-switchable=\"true\">TOTAL POINTS</th>";
        }
        return none;
    }

    // CREAR OBJETOS JSON PARA REPORTES EN PDF
    public String crearJson(ArrayList<HeadersSurveyVO> pools){
        String none = "";
        //JSONObject myObject = new JSONObject();
        //JSONObject subdata = new JSONObject();

        //subdata.put("Encuestas", pools);
        //myObject.put("extra_data", subdata);
        //myObject.put("Encuestas", pools);

        /* Cadenas de texto básicas
        myObject.put("name", "Carlos");
        myObject.put("last_name", "Carlos");

        // Valores primitivos
        myObject.put("age", new Integer(21));
        myObject.put("bank_account_balance", new Double(20.2));
        myObject.put("is_developer", new Boolean(true));

        // Matrices
        double[] myList = {1.9, 2.9, 3.4, 3.5};
        myObject.put("number_list", myList);

        // Objeto dentro de objeto
        JSONObject subdata = new JSONObject();
        myObject.put("extra_data", subdata);*/

        // Generar cadena de texto JSON
        // none = System.out.print(myObject);
        //none = myObject.toString();
        //
        return none;
    }
}
