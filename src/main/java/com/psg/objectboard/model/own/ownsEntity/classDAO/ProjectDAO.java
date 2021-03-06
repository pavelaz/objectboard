package com.psg.objectboard.model.own.ownsEntity.classDAO;

import com.psg.objectboard.model.own.ownsEntity.classVO.CertificationsVO;
import com.psg.objectboard.model.service.Other.OtherConexion;
import com.psg.objectboard.model.service.Other.SqlFunctions;
import com.psg.objectboard.model.own.ownsEntity.classVO.ProjectVO;

import java.sql.*;
import java.util.ArrayList;

public class ProjectDAO {
    private static OtherConexion cc = null;
    private static ProjectVO covo = null;
    private static Connection cn = null;
    private static SqlFunctions sqls = null;
    private static ResultSet rs = null;
    private static Statement cmd = null;
    private static PreparedStatement pst = null;
    private static String dataUser;
    private static String dataPassword;

    public static void setDataUser(String dataUser) {
        ProjectDAO.dataUser = dataUser;
    }

    public static void setDataPassword(String dataPassword) {
        ProjectDAO.dataPassword = dataPassword;
    }

    public ArrayList<ProjectVO> getListProjects(String condi){
        ArrayList<ProjectVO> arrcom = new ArrayList<ProjectVO>();
        cc = new OtherConexion();
        cn = cc.conectarse(dataUser,dataPassword);
        sqls = new SqlFunctions();
        String sql = null;
        if(!condi.equals("")){
            sql = sqls.get_select("project", "*",condi,"pr_id_project","","");
        }else{
            sql = sqls.get_select("project", "*","","pr_id_project","","");
        }
        try{
            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
                covo = new ProjectVO();
                covo.setPrIdProject(rs.getLong(1));
                covo.setPrName(rs.getString(2));
                covo.setPrNote(rs.getString(3));
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

    public static void insertProjectsDAO(ProjectVO covo, Connection cone){
        String sql = "INSERT INTO project values (null,?,?)";
        covo.setResult(false);
        try{
            pst = cone.prepareStatement(sql);
            pst.setString(1,covo.getPrName());
            pst.setString(2,covo.getPrNote());
            pst.executeUpdate();
            System.out.println("Operacion de Insert Project Exitosa.");
            covo.setResult(true);
        }catch (SQLException ex){
            covo.setResult(false);
            System.out.println("Error en la consulta de insert Project: "+ex.getMessage());
        }
    }

    public static Boolean deleteProjectsDAO(Integer clave,Connection cone){
        Boolean result = null;
        String sql = "DELETE FROM project WHERE pr_id_project=?";
        try{
            pst = cone.prepareStatement(sql);
            pst.setInt(1,clave);
            pst.executeUpdate();
            System.out.println("project "+clave+" "+ "eliminado con exito");
            result = true;
        }catch (SQLException ex){
            result = false;
            System.out.println("Error en la eliminacion: "+ex.getMessage());
        }
        return result;
    }

    public static void updateProjectsDAO(ProjectVO cov, Connection cone) {
        String sql = "UPDATE project SET " +
                "pr_name=?, " +
                "pr_note=? " +
                " WHERE pr_id_project=?";
        try{
            pst = cone.prepareStatement(sql);
            pst.setString(1,cov.getPrName());
            pst.setString(2,cov.getPrNote());
            pst.setLong(3,cov.getPrIdProject());
            pst.execute();
            System.out.println("projects actualizado con exito, ID: "+
                    cov.getPrIdProject());
            cov.setResult(true);
        }catch (SQLException ex){
            System.out.println("Error en la actualizacion: "+ex.getMessage());
            cov.setResult(false);
        }
    }
}
