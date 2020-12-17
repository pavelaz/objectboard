package com.psg.objectboard.model.own.ownsEntity.classDAO;

import com.psg.objectboard.model.service.Other.OtherConexion;
import com.psg.objectboard.model.service.Other.SqlFunctions;
import com.psg.objectboard.model.own.ownsEntity.classVO.StateVO;

import java.sql.*;
import java.util.ArrayList;

public class StateDAO {
    private static OtherConexion cc = null;
    private static StateVO sovo = null;
    private static Connection cn = null;
    private static SqlFunctions sqls = null;
    private static ResultSet rs = null;
    private static Statement cmd = null;
    private static PreparedStatement pst = null;
    private static String dataUser;
    private static String dataPassword;

    public static void setDataUser(String dataUser) {
        StateDAO.dataUser = dataUser;
    }

    public static void setDataPassword(String dataPassword) {
        StateDAO.dataPassword = dataPassword;
    }

    public static void insertStateDAO(StateVO sovo, Connection cone){
        String sql = "INSERT INTO states values (null,?,?)";
        sovo.setResult(false);
        try{
            pst = cone.prepareStatement(sql);
            pst.setLong(1,sovo.getCountryCoCountryCode());
            pst.setString(2,sovo.getStName());
            pst.executeUpdate();
            System.out.println("Operacion de Insert state Exitosa.");
            sovo.setResult(true);
        }catch (SQLException ex){
            sovo.setResult(false);
            //sovo.setResultMsg("Error en la consulta: "+ex.getMessage());
            System.out.println("Error en la consulta de insert state: "+ex.getMessage());
        }
    }

    public static Boolean deleteStateDAO(Integer clave1,Integer clave2,Connection cone){
        Boolean result = null;
        String sql = "DELETE FROM states WHERE st_state_code=? AND country_co_country_code=?";
        try{
            pst = cone.prepareStatement(sql);
            pst.setInt(1,clave1);
            pst.setInt(2,clave2);
            pst.executeUpdate();
            System.out.println("Estado "+clave1+"," +clave2+ " eliminado con exito");
            result = true;
        }catch (SQLException ex){
            result = false;
            System.out.println("Error en la eliminacion: "+ex.getMessage());
        }
        return result;
    }

    public static void updateStatetDAO(StateVO cov, Connection cone) {
        String sql = "UPDATE states SET st_name=?  WHERE (st_state_code=? AND country_co_country_code=?)";
        try{
            pst = cone.prepareStatement(sql);
            pst.setString(1, cov.getStName());
            pst.setLong(2, cov.getStStateCode());
            pst.setLong(3, cov.getCountryCoCountryCode());
            pst.execute();
            System.out.println("state actualizado con exito, ID: "+ cov.getStName());
            cov.setResult(true);
        }catch (SQLException ex){
            System.out.println("Error en la actualizacion: "+ex.getMessage());
            cov.setResult(false);
        }
    }

    public StateVO serchStateDAO(Integer clave1, Integer clave2){
        StateVO com = new StateVO();
        cc = new OtherConexion();
        cn = cc.conectarse(dataUser,dataPassword);
        String sql = "SELECT st_name FROM states WHERE st_state_code=? AND country_co_country_code=?";
        try{
            pst = cn.prepareStatement(sql);
            pst.setInt(1,clave1);
            pst.setInt(2,clave2);
            rs = pst.executeQuery();
            com.setResult(false);
            if (rs.next()){ // valida si trae algun registro
                com.setStName(rs.getString(1));
                com.setResult(true);
            }
            //com.setResultMsg("Busqueda exitosa");
            System.out.println("Busqueda de estado exitosa");
        }catch (SQLException ex){
            com.setResult(false);
            //com.setResultMsg("Error en la consulta: "+ex.getMessage());
            System.out.println("Error en la consulta de state: "+ex.getMessage());
        }finally {
            try{
                if (cn != null){
                    cn.close();
                    pst.close();
                    System.out.println("Conexion cerrada");
                }
            }catch (Exception e){
                //com.setResultMsg("Error "+e);
                System.out.println("Error "+e);
            }
        }
        return com;
    }

    public ArrayList<StateVO> getListStates(String condi){
        ArrayList<StateVO> arrcom = new ArrayList<StateVO>();
        cc = new OtherConexion();
        cn = cc.conectarse(dataUser,dataPassword);
        sqls = new SqlFunctions();
        String sql = null;

        if(!condi.equals("")){
            sql = sqls.get_select("states", "*",condi,"st_name","","");
        }else{
            sql = sqls.get_select("states", "*","","st_name","","");
        }

        try{
            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
                sovo = new StateVO();
                sovo.setStStateCode(rs.getLong(1));
                sovo.setCountryCoCountryCode(rs.getLong(2));
                sovo.setStName(rs.getString(3));
                if (arrcom.isEmpty()){
                    arrcom.add(0,sovo);
                }else{
                    arrcom.add(sovo);
                }
            }
            System.out.println("Consulta array exitosa: ");
        }catch (SQLException ex){
            System.out.println("Error en la consulta array: "+ex.getMessage());
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
        return arrcom;
    }
}
