package com.psg.objectboard.model.own.ownsEntity.classViewDAO;

import com.psg.objectboard.model.own.ownsEntity.classViewVO.BussinessUnitCoStCiVO;
import com.psg.objectboard.model.service.Other.OtherConexion;

import java.sql.*;
import java.util.ArrayList;

public class BussinessUnitCoStCiDAO {
    private static OtherConexion cc = null;
    private static BussinessUnitCoStCiVO sovo = null;
    private static Connection cn = null;
    private static ResultSet rs = null;
    private static Statement cmd = null;
    private static PreparedStatement pst = null;
    private static String dataUser;
    private static String dataPassword;

    public static void setDataUser(String dataUser) {
        BussinessUnitCoStCiDAO.dataUser = dataUser;
    }

    public static void setDataPassword(String dataPassword) {
        BussinessUnitCoStCiDAO.dataPassword = dataPassword;
    }

    public static ArrayList<BussinessUnitCoStCiVO> getListBussinessUnitCoStCi(){
        ArrayList<BussinessUnitCoStCiVO> arrcom = new ArrayList<BussinessUnitCoStCiVO>();
        cc = new OtherConexion();
        cn = cc.conectarse(dataUser,dataPassword);
        //sqls = new SqlFunctions();
        String sql = null;

        sql = "SELECT * FROM BussinessUnitCoStCi;";

        try{
            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
                sovo = new BussinessUnitCoStCiVO();
                sovo.setBuBisCode(rs.getLong(1));
                sovo.setBuFederalNumber(rs.getString(2));
                sovo.setBuProvincialNumber(rs.getString(3));
                sovo.setBuEmail(rs.getString(4));
                sovo.setBuStatus(rs.getString(5));
                sovo.setBuSuperCode(rs.getString(6));
                sovo.setBuPhone(rs.getString(7));
                sovo.setBuZipcode(rs.getString(8));
                sovo.setBuName(rs.getString(9));
                sovo.setBuAddress(rs.getString(10));
                sovo.setBuWebpage(rs.getString(11));

                sovo.setBuAdminCode(rs.getString(12));
                sovo.setBuUser1Code(rs.getString(13));
                sovo.setBuUser2Code(rs.getString(14));

                sovo.setCityCiCityCode(rs.getInt(15));
                sovo.setCityStatesStStateCode(rs.getInt(16));
                sovo.setCityStatesCountryCoCountryCode(rs.getInt(17));
                sovo.setBussinessTypeBtCodeType(rs.getInt(18));
                sovo.setBuLogoName(rs.getString(19));

                sovo.setCityName(rs.getString(20));
                sovo.setStateName(rs.getString(21));
                sovo.setCountryName(rs.getString(22));
                sovo.setTypeName(rs.getString(23));

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
