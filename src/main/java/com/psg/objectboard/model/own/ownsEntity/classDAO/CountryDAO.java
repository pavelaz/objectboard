package com.psg.objectboard.model.own.ownsEntity.classDAO;

import com.psg.objectboard.model.service.Other.OtherConexion;
import com.psg.objectboard.model.service.Other.SqlFunctions;
import com.psg.objectboard.model.own.ownsEntity.classVO.CountryVO;
import java.sql.*;
import java.util.ArrayList;

public class CountryDAO {
    private static OtherConexion cc = null;
    private static CountryVO covo = null;
    private static Connection cn = null;
    private static SqlFunctions sqls = null;
    private static ResultSet rs = null;
    private static Statement cmd = null;
    private static PreparedStatement pst = null;
    private static String dataUser;
    private static String dataPassword;

    public static void setDataUser(String dataUser) {
        CountryDAO.dataUser = dataUser;
    }

    public static void setDataPassword(String dataPassword) {
        CountryDAO.dataPassword = dataPassword;
    }

    public ArrayList<CountryVO> getListCountries(String condi){
        ArrayList<CountryVO> arrcom = new ArrayList<CountryVO>();
        cc = new OtherConexion();
        cn = cc.conectarse(dataUser,dataPassword);
        sqls = new SqlFunctions();
        String sql = null;
        if(!condi.equals("")){
            sql = sqls.get_select("country", "*",condi,"co_name","","");
        }else{
            sql = sqls.get_select("country", "*","","co_name","","");
        }
        try{
            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
                covo = new CountryVO();
                covo.setCoCountryCode(rs.getLong(1));
                covo.setCoName(rs.getString(2));
                if (arrcom.isEmpty()){
                    arrcom.add(0,covo);
                }else{
                    arrcom.add(covo);
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

    public static void insertCountryDAO(CountryVO covo, Connection cone){
        String sql = "INSERT INTO country values (null,?)";
        covo.setResult(false);
        try{
            pst = cone.prepareStatement(sql);
            pst.setString(1,covo.getCoName());
            pst.executeUpdate();
            System.out.println("Operacion de Insert country Exitosa.");
            covo.setResult(true);
        }catch (SQLException ex){
            covo.setResult(false);
            covo.setResultMsg("Error en la consulta: "+ex.getMessage());
            System.out.println("Error en la consulta de insert country: "+ex.getMessage());
        }
    }

    public static Boolean deleteCountryDAO(Integer clave1,Connection cone){
        Boolean result = null;
        String sql = "DELETE FROM country WHERE co_country_code=?";
        try{
            pst = cone.prepareStatement(sql);
            pst.setInt(1,clave1);
            pst.executeUpdate();
            System.out.println("Pais "+clave1+" eliminado con exito");
            result = true;
        }catch (SQLException ex){
            result = false;
            System.out.println("Error en la eliminacion: "+ex.getMessage());
        }
        return result;
    }

    public CountryVO serchCountryDAO(Integer clave1){
        CountryVO com = new CountryVO();
        cc = new OtherConexion();
        cn = cc.conectarse(dataUser,dataPassword);
        String sql = "SELECT co_name FROM country WHERE co_country_code=?";
        try{
            pst = cn.prepareStatement(sql);
            pst.setInt(1,clave1);
            rs = pst.executeQuery();
            com.setResult(false);
            if (rs.next()){ // valida si trae algun registro
                com.setCoName(rs.getString(1));
                com.setResult(true);
            }
            com.setResultMsg("Busqueda exitosa");
            System.out.println("Busqueda de pais exitosa");
        }catch (SQLException ex){
            com.setResult(false);
            com.setResultMsg("Error en la consulta: "+ex.getMessage());
            System.out.println("Error en la consulta de pais: "+ex.getMessage());
        }finally {
            try{
                if (cn != null){
                    cn.close();
                    pst.close();
                    System.out.println("Conexion cerrada");
                }
            }catch (Exception e){
                com.setResultMsg("Error "+e);
                System.out.println("Error "+e);
            }
        }
        return com;
    }

    public static void updateCountrytDAO(CountryVO cov, Connection cone) {
        String sql = "UPDATE country SET co_name=?  WHERE (co_country_code=?)";
        try{
            pst = cone.prepareStatement(sql);
            pst.setString(1, cov.getCoName());
            pst.setLong(2, cov.getCoCountryCode());
            pst.execute();
            System.out.println("Country actualizado con exito, ID: "+ cov.getCoName());
            cov.setResult(true);
        }catch (SQLException ex){
            System.out.println("Error en la actualizacion: "+ex.getMessage());
            cov.setResult(false);
        }
    }
}
