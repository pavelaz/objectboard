package com.psg.objectboard.model.own.ownsEntity.classDAO;

import com.psg.objectboard.model.service.Other.OtherConexion;
import com.psg.objectboard.model.service.Other.SqlFunctions;
import com.psg.objectboard.model.own.ownsEntity.classVO.UserRoleVO;

import java.sql.*;
import java.util.ArrayList;

public class UserRoleDAO {
    private static OtherConexion cc = null;
    private static UserRoleVO covo = null;
    private static Connection cn = null;
    private static SqlFunctions sqls = null;
    private static ResultSet rs = null;
    private static Statement cmd = null;
    private static PreparedStatement pst = null;
    private static String dataUser;
    private static String dataPassword;

    public static void setDataUser(String dataUser) {
        UserRoleDAO.dataUser = dataUser;
    }

    public static void setDataPassword(String dataPassword) {
        UserRoleDAO.dataPassword = dataPassword;
    }

    public ArrayList<UserRoleVO> getListUserRoles(String condi){
        ArrayList<UserRoleVO> arrcom = new ArrayList<UserRoleVO>();
        cc = new OtherConexion();
        cn = cc.conectarse(dataUser,dataPassword);
        sqls = new SqlFunctions();
        String sql = null;
        if(!condi.equals("")){
            sql = sqls.get_select("userRole", "*",condi,"masterUser_mu_email","","");
        }else{
            sql = sqls.get_select("userRole", "*","","masterUser_mu_email","","");
        }
        try{
            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
                covo = new UserRoleVO();
                covo.setMasterUserMuEmail(rs.getString(1));
                covo.setMasterUserBussinessUnitBuBisCode(rs.getLong(2));
                covo.setProjectPrIdProject(rs.getLong(3));
                covo.setUmRole(rs.getInt(4));
                covo.setUmStatus(rs.getString(5));
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
    //
    public static void insertUserRolesDAO(UserRoleVO sovo, Connection cone){
        String sql = "INSERT INTO userRole values (?,?,?,?,?)";
        sovo.setResult(false);
        try{
            pst = cone.prepareStatement(sql);
            pst.setString(1,sovo.getMasterUserMuEmail());
            pst.setLong(2,sovo.getMasterUserBussinessUnitBuBisCode());
            pst.setLong(3,sovo.getProjectPrIdProject());
            pst.setInt(4,sovo.getUmRole());
            pst.setString(5,sovo.getUmStatus());
            pst.executeUpdate();
            System.out.println("Operacion de Insert Roll Exitosa.");
            sovo.setResult(true);
        }catch (SQLException ex){
            sovo.setResult(false);
            System.out.println("Error en la consulta de insert Roll: "+ex.getMessage());
        }
    }

    public static Boolean deleteUserRoleDAO(String clave1,Integer clave2,Integer clave3,Connection cone){
        Boolean result = null;
        String sql = "DELETE FROM userRole WHERE masterUser_mu_email=? and " +
                "masterUser_bussinessUnit_bu_bis_code = ? and " +
                "project_pr_id_project = ?";
        try{
            pst = cone.prepareStatement(sql);
            pst.setString(1,clave1);
            pst.setInt(2,clave2);
            pst.setInt(3,clave3);
            pst.executeUpdate();
            System.out.println("User Role "+clave1+" "+clave2+" "+clave3+"eliminado con exito");
            result = true;
        }catch (SQLException ex){
            result = false;
            System.out.println("Error en la eliminacion: "+ex.getMessage());
        }
        return result;
    }

    public static void updateUserRoleDAO(UserRoleVO cov, Connection cone) {
        String sql = "UPDATE userRole SET " +
                "um_role=?, " +
                "um_status=? " +
                " WHERE (masterUser_mu_email=? and masterUser_bussinessUnit_bu_bis_code=? and " +
                "project_pr_id_project=?)";
        try{
            pst = cone.prepareStatement(sql);
            pst.setInt(1,cov.getUmRole());
            pst.setString(2,cov.getUmStatus());
            pst.setString(3,cov.getMasterUserMuEmail());
            pst.setLong(4,cov.getMasterUserBussinessUnitBuBisCode());
            pst.setLong(5,cov.getProjectPrIdProject());
            pst.execute();
            System.out.println("User Role actualizado con exito, ID: "+ cov.getMasterUserMuEmail());
            cov.setResult(true);
        }catch (SQLException ex){
            System.out.println("Error en la actualizacion: "+ex.getMessage());
            cov.setResult(false);
        }
    }
}
