package com.psg.objectboard.model.service.Other;

import java.time.*;
import java.util.Date;

public class LocalDateFunctions {
    private String answer="";

    // utilizado la libreria LocalDate y Localtime
    // Trae la fecha del dia
    public LocalDate obtenerFechaActual(){
        LocalDate d = LocalDate.now();
        return d;
    }
    public LocalTime obtenerHoraActual(){
        LocalTime t = LocalTime.now();
        return t;
    }
    // combierte en una fecha
    public LocalDate ponerFecha(Integer anno,Integer mes,Integer dia) {
        LocalDate e = LocalDate.of(anno, mes, dia);
        return e;
    }
    // combierte a hora, minutos, segundos y nanosegundos
    public LocalTime poneHora(Integer hora, Integer minu,Integer segu,Integer nano){
        LocalTime t = LocalTime.of(hora,minu,segu,nano);
        return t;
    }
    // trae hora segun zona horaria
    public LocalTime traeHora(String zona){
        zona = "Asia/Kuwait";  // sustituir por la deseada
        LocalTime g = LocalTime.now(ZoneId.of(zona));
        return g;
    }
    // trae hora tipo GMT
    public LocalTime traeHora(){
        LocalTime h = LocalTime.now(ZoneId.of("GMT"));
        return h;
    }
    // muestra por consola todas las zonas horarias disponibles
    public static void verZonasHorarias(){
        for(String s : ZoneId.getAvailableZoneIds()) {
            System.out.println(s);
        }
    }
    // trabajar con fecha  utilizando el tipo LocalDate
    // convierte de tipo LocalDate a java.util.date
    public Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public  Date asDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate asLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDateTime asLocalDateTime(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
    //
}
