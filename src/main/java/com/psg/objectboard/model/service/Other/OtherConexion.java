package com.psg.objectboard.model.service.Other;

import java.sql.*;

public class OtherConexion {
    private ResultSet rs = null;
    private Statement cmd = null;
    private String sqls = null;
    private static PreparedStatement pst = null;

    public Connection conectarse(String dataUserLogin, String dataUserPassword){
        Connection con = null;
        //driver 5. String resto = "useSSL=false&amp;serverTimezone=Europe/Paris";
        String resto = "serverTimezone=UTC";
        OtherFunctions otherFunctions = new OtherFunctions();

        try{
            String x = otherFunctions.searchLink("2");
            String url = "jdbc:mysql://"+ x +"/objectboard_db"+ "?" + resto;
            //String url = "jdbc:mysql://localhost:3306/objectboard_db"+ "?" + resto;
            //String url = "jdbc:mysql://mysqlcloud:3306/objectboard_db"+ "?" + resto;

            Class.forName("com.mysql.cj.jdbc.Driver");
            //Class.forName("com.mysql.jdbc.Driver"); driver 5.
            con = DriverManager.getConnection(url,dataUserLogin,dataUserPassword);
            System.out.println("Coneccion en Linea.");
            System.out.println("   Usuario: " + dataUserLogin + " Password: " + dataUserPassword.substring(0,4));
        }catch (Exception ex){
            System.out.println("Error de coneccion: "+ex.getMessage());
        }
        return con;
    }

    /*public Connection conectarse(){
        Connection con = null;
        String resto = "serverTimezone=UTC";
        String url = "jdbc:mysql://localhost:3306/objectboard_db"+ "?" + resto;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url,"boarduser","01_Objectuser");
            System.out.println("Coneccion en Linea.");
        }catch (Exception ex){
            System.out.println("Error de coneccion: ");
        }
        return con;
    }*/

    public void init_trans(Connection cone){
        sqls = "BEGIN;";
        //String sqls = "START TRANSACTION;";
        try{
            cmd = cone.createStatement();
            rs = cmd.executeQuery(sqls);
            System.out.println("Inicio Transacion Exitoso ");
        }catch (Exception ex){
            System.out.println("Error de Inicio Transacion");
        }
    }

    public Boolean usersCreations(Connection cone,String data_user,String data_password){
        try{
            // creacion de usuario
            // sqls = "CREATE USER '" + data_user + "'@'localhost' IDENTIFIED BY '"+data_password+"';";
            sqls = "create user " + data_user + " identified by " + "'" + data_password + "'";
            pst = cone.prepareStatement(sqls);
            pst.execute();
            System.out.println("Creacion de usuario Exitoso ");

            // sqls = "GRANT ALL PRIVILEGES ON dashboard_db.* TO '"+data_user+"' WITH GRANT OPTION;";
            sqls = "GRANT ALL PRIVILEGES ON *.* TO '" +data_user+"'@'%' WITH GRANT OPTION;";
            pst = cone.prepareStatement(sqls);
            pst.execute();
            System.out.println("Asignacion de permisos a usuario Exitoso ");

            sqls = "flush privileges;";
            pst = cone.prepareStatement(sqls);
            pst.execute();
            System.out.println("Refrescamiento a usuario Exitoso ");

            return true;
        }catch (Exception ex){
            System.out.println("Error de creacion de usuario sql");
            return false;
        }
    }

    public void valida_trans(Connection cone,Boolean result){
        if(result) {
            sqls = "COMMIT;";
        }else {
            sqls = "ROLLBACK;";
        }
        try{
            cmd = cone.createStatement();
            rs = cmd.executeQuery(sqls);
            System.out.println(sqls+" de Transacion realizado con exito");
        }catch (Exception ex){
            System.out.println("Error en validacion de Transacion :"+sqls);
        }
    }

    public void cierra_coneccion(Connection cone){
        try{
            cone.close();
            System.out.println("Coneccion Cerrada.");
        } catch (Exception ex) {
            System.out.println("Error en cierre de coneccion."+ex.getMessage());
        }
    }
}
