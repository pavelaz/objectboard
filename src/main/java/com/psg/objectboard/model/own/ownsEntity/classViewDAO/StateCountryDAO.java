package com.psg.objectboard.model.own.ownsEntity.classViewDAO;

import com.psg.objectboard.model.own.ownsEntity.classViewVO.StateCountryVO;
import com.psg.objectboard.model.service.Other.OtherConexion;

import java.sql.*;
import java.util.ArrayList;

public class StateCountryDAO {
    private static OtherConexion cc = null;
    private static StateCountryVO sovo = null;
    private static Connection cn = null;
    private static ResultSet rs = null;
    private static Statement cmd = null;
    private static PreparedStatement pst = null;
    private static String dataUser;
    private static String dataPassword;

    public static void setDataUser(String dataUser) {
        StateCountryDAO.dataUser = dataUser;
    }

    public static void setDataPassword(String dataPassword) {
        StateCountryDAO.dataPassword = dataPassword;
    }

    public static ArrayList<StateCountryVO> getListStateCountry(){
        ArrayList<StateCountryVO> arrcom = new ArrayList<StateCountryVO>();
        cc = new OtherConexion();
        cn = cc.conectarse(dataUser,dataPassword);
        //sqls = new SqlFunctions();
        String sql = null;

        sql = "SELECT * FROM StateCountry;";

        try{
            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
                sovo = new StateCountryVO();
                sovo.setId_state(rs.getLong(1));
                sovo.setState_name(rs.getString(2));
                sovo.setId_country(rs.getLong(3));
                sovo.setCountry_name(rs.getString(4));
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
