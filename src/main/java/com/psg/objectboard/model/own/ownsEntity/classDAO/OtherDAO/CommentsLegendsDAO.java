package com.psg.objectboard.model.own.ownsEntity.classDAO.OtherDAO;

import com.psg.objectboard.model.service.Other.OtherConexion;
import com.psg.objectboard.model.service.Other.SqlFunctions;
import com.psg.objectboard.model.own.ownsEntity.classVO.OtherVO.CommentsLegendsVO;

import java.sql.*;
import java.util.ArrayList;

public class CommentsLegendsDAO {
    private static OtherConexion cc = null;
    private static CommentsLegendsVO covo = null;
    private static Connection cn = null;
    private static SqlFunctions sqls = null;
    private static ResultSet rs = null;
    private static Statement cmd = null;
    private static PreparedStatement pst = null;
    private static String dataUser;
    private static String dataPassword;

    public static void setDataUser(String dataUser) {
        CommentsLegendsDAO.dataUser = dataUser;
    }

    public static void setDataPassword(String dataPassword) {
        CommentsLegendsDAO.dataPassword = dataPassword;
    }

    public ArrayList<CommentsLegendsVO> getListComments(String condi){
        ArrayList<CommentsLegendsVO> arrcom = new ArrayList<CommentsLegendsVO>();
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
                covo = new CommentsLegendsVO();
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

}
