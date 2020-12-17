package com.psg.objectboard.model.own.ownsEntity.classDAO;

import com.psg.objectboard.model.service.Other.OtherConexion;
import com.psg.objectboard.model.service.Other.SqlFunctions;
import com.psg.objectboard.model.own.ownsEntity.classVO.CityVO;

import java.sql.*;
import java.util.ArrayList;

public class CityDAO {
    private static OtherConexion cc = null;
    private static CityVO sovo = null;
    private static Connection cn = null;
    private static SqlFunctions sqls = null;
    private static ResultSet rs = null;
    private static Statement cmd = null;
    private static PreparedStatement pst = null;
    private static String dataUser;
    private static String dataPassword;

    public static void setDataUser(String dataUser) {
        CityDAO.dataUser = dataUser;
    }

    public static void setDataPassword(String dataPassword) {
        CityDAO.dataPassword = dataPassword;
    }

    public static void insertCityDAO(CityVO sovo, Connection cone){
        String sql = "INSERT INTO city values (null,?,?,?)";
        sovo.setResult(false);
        try{
            pst = cone.prepareStatement(sql);
            pst.setLong(1,sovo.getStatesStStateCode());
            pst.setLong(2,sovo.getStatesCountryCoCountryCode());
            pst.setString(3,sovo.getCiName());
            pst.executeUpdate();
            System.out.println("Operacion de Insert city Exitosa.");
            sovo.setResult(true);
        }catch (SQLException ex){
            sovo.setResult(false);
            //sovo.setResultMsg("Error en la consulta: "+ex.getMessage());
            System.out.println("Error en la consulta de insert city: "+ex.getMessage());
        }
    }

    public static Boolean deleteCityDAO(Integer city,Integer state,Integer contry,Connection cone){
        Boolean result = null;
        String sql = "DELETE FROM city WHERE ci_city_code=? AND states_st_state_code=? AND states_country_co_country_code=?";
        try{
            pst = cone.prepareStatement(sql);
            pst.setInt(1,city);
            pst.setInt(2,state);
            pst.setInt(3,contry);
            pst.executeUpdate();
            System.out.println("City "+"," +city+"," +state+"," +contry +" eliminada con exito");
            result = true;
        }catch (SQLException ex){
            result = false;
            System.out.println("Error en la eliminacion: "+ex.getMessage());
        }
        return result;
    }

    public static void updateCitytDAO(CityVO cov, Connection cone) {
        String sql = "UPDATE city SET ci_name=?  WHERE (ci_city_code=?  AND states_st_state_code=? AND states_country_co_country_code=?)";
        try{
            pst = cone.prepareStatement(sql);
            pst.setString(1, cov.getCiName());
            pst.setLong(2, cov.getCiCityCode());
            pst.setLong(3, cov.getStatesStStateCode());
            pst.setLong(4, cov.getStatesCountryCoCountryCode());
            pst.execute();
            System.out.println("city actualizado con exito, ID: "+ cov.getCiName());
            cov.setResult(true);
        }catch (SQLException ex){
            System.out.println("Error en la actualizacion: "+ex.getMessage());
            cov.setResult(false);
        }
    }

    public static CityVO serchCityDAO(Integer clave1,Integer clave2,Integer clave3){
        CityVO cit = new CityVO();
        cc = new OtherConexion();
        cn = cc.conectarse(dataUser,dataPassword);
        String result = null;
        String sql = "SELECT ci_name FROM city WHERE ci_city_code=? AND states_st_state_code=? AND states_country_co_country_code=?";
        try{
            pst = cn.prepareStatement(sql);
            pst.setInt(1,clave1);
            pst.setInt(2,clave2);
            pst.setInt(3,clave3);
            rs = pst.executeQuery();
            cit.setResult(false);
            if (rs.next()){ // valida si trae algun registro
                cit.setCiName(rs.getString(1));
                cit.setResult(true);
            }
            //com.setResultMsg("Busqueda exitosa");
            System.out.println("Busqueda de city exitosa");
        }catch (SQLException ex){
            cit.setResult(false);
            //com.setResultMsg("Error en la consulta: "+ex.getMessage());
            System.out.println("Error en la consulta de city: "+ex.getMessage());
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
        return cit;
    }

    public ArrayList<CityVO> getListCities(String condi){
        ArrayList<CityVO> arrcom = new ArrayList<CityVO>();
        cc = new OtherConexion();
        cn = cc.conectarse(dataUser,dataPassword);
        sqls = new SqlFunctions();
        String sql = null;

        if(!condi.equals("")){
            sql = sqls.get_select("city", "*",condi,"ci_name","","");
        }else{
            sql = sqls.get_select("city", "*","","ci_name","","");
        }

        try{
            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
                sovo = new CityVO();
                sovo.setCiCityCode(rs.getLong(1));
                sovo.setStatesStStateCode(rs.getLong(2));
                sovo.setStatesCountryCoCountryCode(rs.getLong(3));
                sovo.setCiName(rs.getString(4));
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
