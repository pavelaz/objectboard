package com.psg.objectboard.model.own.ownsEntity.classViewDAO;

import com.psg.objectboard.model.own.ownsEntity.classViewVO.UserRoleViewVO;
import com.psg.objectboard.model.service.Other.OtherConexion;

import java.sql.*;
import java.util.ArrayList;

public class UserRoleViewDAO {
    private static OtherConexion cc = null;
    private static UserRoleViewVO sovo = null;
    private static Connection cn = null;
    private static ResultSet rs = null;
    private static Statement cmd = null;
    private static PreparedStatement pst = null;
    private static String dataUser;
    private static String dataPassword;

    public static void setDataUser(String dataUser) {
        UserRoleViewDAO.dataUser = dataUser;
    }

    public static void setDataPassword(String dataPassword) {
        UserRoleViewDAO.dataPassword = dataPassword;
    }

    public static ArrayList<UserRoleViewVO> getListUserRoleView(){
        ArrayList<UserRoleViewVO> arrcom = new ArrayList<UserRoleViewVO>();
        cc = new OtherConexion();
        cn = cc.conectarse(dataUser,dataPassword);

        String sql = null;

        sql = "SELECT * FROM UserRoleView;";

        try{
            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
                sovo = new UserRoleViewVO();
                sovo.setMasterUserMuEmail(rs.getString(1));
                sovo.setMasterUserBussinessUnitBuBisCode(rs.getLong(2));
                sovo.setProjectPrIdProject(rs.getLong(3));
                sovo.setUmRole(rs.getInt(4));
                sovo.setUmStatus(rs.getString(5));
                sovo.setUserName(rs.getString(6));
                sovo.setUnitName(rs.getString(7));
                sovo.setProjName(rs.getString(8));
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
