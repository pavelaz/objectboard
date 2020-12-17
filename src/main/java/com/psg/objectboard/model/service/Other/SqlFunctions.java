package com.psg.objectboard.model.service.Other;

public class SqlFunctions {
    private String answer="";

    // SENTENCIAS SQL
    // CONSULTAS SELECT
    public String get_select(String table, String columns,String where,String order,String limit,String agrupa){
        answer = "SELECT " + columns + " FROM " + table;
        if(!where.equals("") && where!=null){
            answer = answer + " WHERE " + where;
        }
        if(!order.equals("") && order!=null){
            answer = answer + " ORDER BY " + order;
        }
        if(!limit.equals("") && limit!=null){
            answer = answer + " LIMIT " + limit;
        }
        if(!agrupa.equals("") && agrupa!=null){
            answer = answer + " GROUP BY " + agrupa;
        }
        return answer;
    }

    //-SELECT- entre 2 tablas por 2 indices comunes
    public String get_select_join(String table_a,String table_b, String id_a, String id_b, String columns, String where, String order){
        String table = table_a + " a, " + table_b + " b";
        String w = "a." + id_a + "=b." + id_b;
        if(!where.equals("") && where!=null) {
            w = w + "AND (" + where + ")";
        }
        if(!order.equals("") && order!=null) {
            answer=get_select(table,columns,w,order,"","");
        }else{
            answer=get_select(table,columns,w,"","","");
        }
        return answer;
    }

    //  SENTENCIAS INSERT
    public String get_insert(String table, String columns, String values, String duplicado){
        answer = "INSERT INTO " + table + " (" + columns + ") VALUES (" + values + ")";
        if(!duplicado.equals("") && duplicado!=null){
            answer = answer + " ON DUPLICATE KEY UPDATE " + duplicado;
        }
        return answer;
    }

    // SENTENCIA  COMUN UPDATE- $values=get_mult_set(...)   $where=get_mult_set(...) o get_simp_set(...)
    public String get_update(String table,String values,String where){
        answer = "UPDATE " + table + " SET " + values + " WHERE " + where;
        return answer;
    }

    //-UPDATE- actualiza una estilos con valores de otra
    public String get_update_join(String table_a,String table_b,String id_a,String id_b,String values,String where){
        if(!where.equals("") && where!=null){
            where = "AND (" + where + ")";
        }
        answer = "UPDATE " + table_a + " a, " + table_b + " b SET " + values +
                 " WHERE a." + id_a + "=b." + id_b + " " + where;
        return answer;
    }

    //-DELETE-  $where=get_mult_set(...) o get_simp_set(...)
    public String get_delete(String table, String where){
        answer = "DELETE FROM " + table;
        if(!where.equals("") && where!=null){
            answer = answer + " WHERE " + where;
        }
        return answer;
    }

    public String get_between(String col, Integer min, Integer max){
        answer = "(" + col + " BETWEEN " + min + " AND " + max;
        return answer;
    }
}
