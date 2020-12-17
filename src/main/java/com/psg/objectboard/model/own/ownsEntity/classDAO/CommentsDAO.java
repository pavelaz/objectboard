package com.psg.objectboard.model.own.ownsEntity.classDAO;

import com.psg.objectboard.model.service.Other.OtherConexion;
import com.psg.objectboard.model.service.Other.SqlFunctions;
import com.psg.objectboard.model.own.ownsEntity.classVO.CommentsVO;

import java.sql.*;
import java.util.ArrayList;

public class CommentsDAO {
    private static OtherConexion cc = null;
    private static CommentsVO covo = null;
    private static Connection cn = null;
    private static SqlFunctions sqls = null;
    private static ResultSet rs = null;
    private static Statement cmd = null;
    private static PreparedStatement pst = null;
    private static String dataUser;
    private static String dataPassword;

    public static void setDataUser(String dataUser) {
        CommentsDAO.dataUser = dataUser;
    }

    public static void setDataPassword(String dataPassword) {
        CommentsDAO.dataPassword = dataPassword;
    }

    public ArrayList<CommentsVO> getListComments(String condi){
        ArrayList<CommentsVO> arrcom = new ArrayList<CommentsVO>();
        cc = new OtherConexion();
        cn = cc.conectarse(dataUser,dataPassword);
        sqls = new SqlFunctions();
        String sql = null;
        if(!condi.equals("")){
            sql = sqls.get_select("comments", "*",condi,"","","");
        }else{
            sql = sqls.get_select("comments", "*","","","","");
        }
        try{
            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
                covo = new CommentsVO();
                covo.setCommentCode(rs.getLong(1));
                covo.setBussinessUnitBuBisCode(rs.getLong(2));
                covo.setCommentType(rs.getString(3));
                covo.setCommentDescription(rs.getString(4));
                covo.setCommentReference(rs.getString(5));

                if (arrcom.isEmpty()){
                    arrcom.add(0,covo);
                }else{
                    arrcom.add(covo);
                }
                //System.out.println(rs.getLong(1) + ", "+rs.getString(5));
            }
            /*System.out.println("-----------");
            for (Integer x=0; x < arrcom.size();x++){
                System.out.println(arrcom.get(x).getCommentCode() + ", "+arrcom.get(x).getCommentReference());
            }*/
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

    public static void insertCommentsDAO(CommentsVO covo, Connection cone){
        String sql = "INSERT INTO comments values (null,?,?,?,?)";
        covo.setResult(false);
        try{
            pst = cone.prepareStatement(sql);
            pst.setLong(1,covo.getBussinessUnitBuBisCode());
            pst.setString(2,covo.getCommentType());
            pst.setString(3,covo.getCommentDescription());
            pst.setString(4,covo.getCommentReference());
            pst.executeUpdate();
            System.out.println("Operacion de Insert Comments Exitosa.");
            covo.setResult(true);
        }catch (SQLException ex){
            covo.setResult(false);
            System.out.println("Error en la consulta de insert Comments: "+ex.getMessage());
        }
    }

    public static Boolean deleteCommentsDAO(Integer cia,Integer clave0, Connection cone){
        Boolean result = null;
        String sql = "DELETE FROM comments WHERE bussinessUnit_bu_bis_code=? AND comment_code=?";
        try{
            pst = cone.prepareStatement(sql);
            pst.setInt(1,cia);
            pst.setInt(2,clave0);
            pst.executeUpdate();
            System.out.println("Comments "+clave0+" eliminado con exito");
            result = true;
        }catch (SQLException ex){
            result = false;
            System.out.println("Error en la eliminacion: "+ex.getMessage());
        }
        return result;
    }

    public static void updateCommentsDAO(CommentsVO cov, Connection cone) {
        String sql = "UPDATE comments SET " +
                "comment_type = ?, " +
                "comment_description =?, " +
                "comment_reference =? " +
                " WHERE (bussinessUnit_bu_bis_code=? AND comment_code=?)";
        try{
            pst = cone.prepareStatement(sql);
            pst.setString(1,cov.getCommentType());
            pst.setString(2,cov.getCommentDescription());
            pst.setString(3,cov.getCommentReference());
            pst.setLong(4,cov.getBussinessUnitBuBisCode());
            pst.setLong(5,cov.getCommentCode());
            pst.execute();
            System.out.println("comments actualizado con exito, ID: "+
                    cov.getBussinessUnitBuBisCode()+" "+
                    cov.getCommentCode());
            cov.setResult(true);
        }catch (SQLException ex){
            System.out.println("Error en la actualizacion: "+ex.getMessage());
            cov.setResult(false);
        }
    }

}
