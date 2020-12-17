package com.psg.objectboard.model.own.ownsEntity.classViewDAO;

import com.psg.objectboard.model.own.ownsEntity.classViewVO.DischargeViewVO;
import com.psg.objectboard.model.service.Other.OtherConexion;

import java.sql.*;
import java.util.ArrayList;

public class DischargeViewDAO {
    private static OtherConexion cc = null;
    private static DischargeViewVO sovo = null;
    private static Connection cn = null;
    private static ResultSet rs = null;
    private static Statement cmd = null;
    private static PreparedStatement pst = null;
    private static String dataUser;
    private static String dataPassword;

    public static void setDataUser(String dataUser) {
        DischargeViewDAO.dataUser = dataUser;
    }

    public static void setDataPassword(String dataPassword) {
        DischargeViewDAO.dataPassword = dataPassword;
    }

    public static ArrayList<DischargeViewVO> getListDischargeView(String condi){
        ArrayList<DischargeViewVO> arrcom = new ArrayList<DischargeViewVO>();
        cc = new OtherConexion();
        cn = cc.conectarse(dataUser,dataPassword);
        //sqls = new SqlFunctions();
        String sql = null;

        if (condi != "")
            sql = "SELECT * FROM DischargeView WHERE " + condi +";";
        else
            sql = "SELECT * FROM DischargeView;";

        try{
            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
                sovo = new DischargeViewVO();
                sovo.setDiLicenseNum(rs.getLong(1));
                sovo.setDiStartDate(rs.getString(2));
                sovo.setDiEndDate(rs.getString(3));
                sovo.setDiNumberUser(rs.getInt(4));
                sovo.setDiPermanent(rs.getString(5));
                sovo.setDiSalesCode(rs.getInt(6));
                sovo.setDiLicenseCode(rs.getString(7));
                sovo.setBussinessUnitBuBisCode(rs.getLong(8));
                sovo.setProjectPrIdProject(rs.getLong(9));
                sovo.setBuName(rs.getString(10));
                sovo.setPrName(rs.getString(11));

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
