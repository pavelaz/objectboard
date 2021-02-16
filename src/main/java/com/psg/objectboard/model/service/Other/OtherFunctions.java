package com.psg.objectboard.model.service.Other;

import com.psg.objectboard.batch.App;
import com.psg.objectboard.model.own.ownsEntity.classVO.HeadersSurveyVO;

import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

public class OtherFunctions {
    private String answer="";
    public String linkMedio="";

    public String buscaPrefijoToFiles(String company){
        DateFunctions df = new DateFunctions();
        answer =  df.fechaFull(9).replace("/","");
        answer =  answer.replace(" ","");
        answer =  answer.replace(":","");
        answer =  answer + "" + company + "_";
        return answer;
    }

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
        // Servidor Angular
        if (valor.equals("8")){
            none = vProp.getProperty("propert-ipnodejsangular");
            return none;
        }
        return vProp.getProperty("propert-pathuserhome");
    }

    //
    public byte[] toSaveDocumentInFile(String rutaYArchivo) throws IOException {
        //File aSelected = new File("/ruta/del/fichero/a/subir");
        File aSelected = new File(rutaYArchivo);

        long length= aSelected.length();
        InputStream is = new FileInputStream(aSelected);

        byte[] data1 = new byte[(int)length];

        int offset = 0;
        int numRead = 0;
        while (offset < data1.length
                && (numRead=is.read(data1, offset, data1.length-offset)) >= 0) {
            offset += numRead;
        }

        if (offset < data1.length) {
            throw new IOException("No se pudo completar la lectura del fichero "+aSelected.getName());
        }

        //En este punto asignas la variable data1 al campo serializable (el que mapea con blob) de tu objeto a persistir.

        is.close();

        return data1;
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

    public Boolean evaluaRank(Double maximun,Double minimun,Double respuesta){
        return true;
    }
}
