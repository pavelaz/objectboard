package com.psg.objectboard.model.service.Other;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFunctions {
    private String answer="";

    //  --  Otra(s funciones NO relacionadas a sentencias sql --
    // devuelvo un campo tipo string con la fecha en el formato seleccionado por el campo nu
    public String fechaFull(Integer nu) {
        Date date = new Date();
        String fecha = new String();
        //Caso 1: obtener la hora y salida por pantalla con formato:
        if (nu==1) {
            DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
            //System.out.println("Hora: " + hourFormat.format(date));
            fecha = hourFormat.format(date);
        }
        //Caso 2: obtener la fecha y salida por pantalla con formato: separador /
        if (nu==2) {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            //System.out.println("Fecha: " + dateFormat.format(date));
            fecha =  dateFormat.format(date);
        }
        //Caso 3: obtenerhora y fecha y salida por pantalla con formato: separador /
        if (nu==3) {
            DateFormat hourdateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
            //System.out.println("Hora y fecha: " + hourdateFormat.format(date));
            fecha =  hourdateFormat.format(date);
        }
        //Caso 4: obtener la fecha y salida por pantalla con formato: separador /
        if (nu==4) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/dd/MM");
            //System.out.println("Fecha: " + dateFormat.format(date));
            fecha =  dateFormat.format(date);
        }
        //Caso 5: obtener la fecha y salida por pantalla con formato: separador -
        if (nu==5) {
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            //System.out.println("Fecha: " + dateFormat.format(date));
            fecha =  dateFormat.format(date);
        }
        //Caso 6: obtenerhora y fecha y salida por pantalla con formato: separador -
        if (nu==6) {
            DateFormat hourdateFormat = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
            //System.out.println("Hora y fecha: " + hourdateFormat.format(date));
            fecha =  hourdateFormat.format(date);
        }
        //Caso 7: obtener la fecha y salida por pantalla con formato: separador -
        if (nu==7) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-dd-MM");
            //System.out.println("Fecha: " + dateFormat.format(date));
            fecha =  dateFormat.format(date);
        }
        //Caso 8: obtener la fecha y salida por pantalla con formato: separador -
        // retorna formador yyyy-MM-dd
        if (nu==8) {
            String fe;
            DateFormat dateFormat = new SimpleDateFormat("yyyy-dd-MM");
            fe =  dateFormat.format(date);
            fecha = fe.substring(0,4)+"-"+fe.substring(8,10)+"-"+fe.substring(5,7);
            //System.out.println("Fecha: " + fecha);
        }
        //Caso 9: obtenerhora y fecha y salida por pantalla con formato: separador -
        if (nu==9) {
            DateFormat hourdateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss" );
            //System.out.println("Hora y fecha: " + hourdateFormat.format(date));
            fecha =  hourdateFormat.format(date);
        }
        //Caso 10: obtener la fecha y salida por pantalla con formato: separador /
        // retorna formador MM/dd/yyyy
        if (nu==10) {
            String fe;
            DateFormat dateFormat = new SimpleDateFormat("yyyy-dd-MM");
            fe =  dateFormat.format(date);
            //fecha = fe.substring(0,4)+"-"+fe.substring(8,10)+"-"+fe.substring(5,7);
            fecha = fe.substring(8,10)+"/"+fe.substring(5,7)+"/"+fe.substring(0,4);
            //System.out.println("Fecha: " + fecha);
        }
        return fecha;
    }

    /**
     * Permite convertir un String con la fecha en formato mm/dd/aaaa a formato aaaa/mm/dd.
     */
    public String parseFecha_1(String fecha)    {
        String fecha_new = null;
        fecha_new = fecha.substring(6,10)+"/"+fecha.substring(0,2)+"/"+fecha.substring(3,5);
        return fecha_new;
    }

    /**
     * Permite convertir un String con la fecha en formato aaaa/mm/dd a formato mm/dd/aaaa.
     */
    public String parseFecha_2(String fecha)    {
        String fecha_new = null;
        fecha_new = fecha.substring(5,7)+"/"+fecha.substring(8,10)+"/"+fecha.substring(0,4);
        return fecha_new;
    }

    /**
     * Permite convertir un Objeto tipo  String en tipo fecha (Date).
     * @param fecha Cadena de fecha yyyy-dd-MM
     * @return Objeto Date
     */
    public Date parseFecha(String fecha)    {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-dd-MM");
        Date fechaDate = null;
        try {
            fechaDate = (Date) formato.parse(fecha);
        }
        catch (ParseException ex)
        {
            System.out.println(ex);
        }
        return fechaDate;
    }

    /**
     * compara dos fechas recibidas como string en formato aaaa/mm/dd ambas.
     * devuelve 0 si son iguales, 1 si la primera es mayor y 2 si la segunda es mayor
     */
    public Integer ComparaFechas(String fecha1,String fecha2) {
        //Integer valor1 = Integer.parseInt(fecha1.substring(5,7)+fecha1.substring(8,10)+fecha1.substring(0,4));
        //Integer valor2 = Integer.parseInt(fecha2.substring(5,7)+fecha2.substring(8,10)+fecha2.substring(0,4));
        Integer valor1 = Integer.parseInt(fecha1.substring(0,4)+fecha1.substring(5,7)+fecha1.substring(8,10));
        Integer valor2 = Integer.parseInt(fecha2.substring(0,4)+fecha2.substring(5,7)+fecha2.substring(8,10));
        if (valor1 == valor2){
            return 0;
        }else{
            if (valor1 > valor2)
                return 1;
            else
                return 2;
        }
    }

    // obtienne la fecha en formato yyyy-dd-MM de una cadena string
    // formato 2020/04/23 12:57:37
    public Date sacaFecha(String texto){
        Date fechaDate = null;
        String new_texto = texto.substring(0,4) + "-" + texto.substring(8,10) + "-" + texto.substring(5,7);
        fechaDate = this.parseFecha(new_texto);

        return fechaDate;
    }

    //Saber los dias de diferencia entre dos objetos tipo DATE fechas formato "yyyy-dd-MM"
    // retorno en dias
    public int diasEntreFechas(Date mayor, Date menor){
        int dif=(int) ((mayor.getTime()-menor.getTime())/86400000);
        return dif;
    }

    // Suma los días recibidos a la fecha
    public Date sumarRestarDiasFecha(Date fecha, int dias){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha); // Configuramos la fecha que se recibe
        calendar.add(Calendar.DAY_OF_YEAR, dias);  // numero de días a añadir, o restar en caso de que días<0
        return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos
    }

    // Suma o resta las horas recibidos a la fecha
    public Date sumarRestarHorasFecha(Date fecha, int horas){

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha); // Configuramos la fecha que se recibe
        calendar.add(Calendar.HOUR, horas);  // numero de horas a añadir, o restar en caso de horas<0
        return calendar.getTime(); // Devuelve el objeto Date con las nuevas horas añadidas
    }
}
