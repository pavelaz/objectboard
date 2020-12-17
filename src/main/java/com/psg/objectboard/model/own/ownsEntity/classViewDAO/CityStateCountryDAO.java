package com.psg.objectboard.model.own.ownsEntity.classViewDAO;

import com.psg.objectboard.model.own.ownsEntity.classViewVO.CityStateCountryVO;
import com.psg.objectboard.model.service.Other.OtherConexion;

import java.sql.*;
import java.util.ArrayList;

public class CityStateCountryDAO {
    private static OtherConexion cc = null;
    private static CityStateCountryVO sovo = null;
    private static Connection cn = null;
    private static ResultSet rs = null;
    private static Statement cmd = null;
    private static PreparedStatement pst = null;
    private static String dataUser;
    private static String dataPassword;

    public static void setDataUser(String dataUser) {
        CityStateCountryDAO.dataUser = dataUser;
    }

    public static void setDataPassword(String dataPassword) {
        CityStateCountryDAO.dataPassword = dataPassword;
    }

    public static ArrayList<CityStateCountryVO> getListCityStateCountry(){
        ArrayList<CityStateCountryVO> arrcom = new ArrayList<CityStateCountryVO>();
        cc = new OtherConexion();
        cn = cc.conectarse(dataUser,dataPassword);
        String sql = null;

        sql = "SELECT * FROM CityStateCountry;";

        try{
            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
                sovo = new CityStateCountryVO();
                sovo.setId_city(rs.getLong(1));
                sovo.setId_state(rs.getLong(2));
                sovo.setId_country(rs.getLong(3));
                sovo.setCity_name(rs.getString(4));
                sovo.setState_name(rs.getString(5));
                sovo.setCountry_name(rs.getString(6));
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
