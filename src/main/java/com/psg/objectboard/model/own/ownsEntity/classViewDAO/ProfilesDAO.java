package com.psg.objectboard.model.own.ownsEntity.classViewDAO;

import com.psg.objectboard.model.own.ownsEntity.classViewVO.ProfilesVO;
import com.psg.objectboard.model.service.Other.OtherConexion;

import java.sql.*;
import java.util.ArrayList;

public class ProfilesDAO {
    private static OtherConexion cc = null;
    private static ProfilesVO sovo = null;
    private static Connection cn = null;
    private static ResultSet rs = null;
    private static Statement cmd = null;
    private static PreparedStatement pst = null;
    private static String dataUser;
    private static String dataPassword;

    public static void setDataUser(String dataUser) {
        ProfilesDAO.dataUser = dataUser;
    }

    public static void setDataPassword(String dataPassword) {
        ProfilesDAO.dataPassword = dataPassword;
    }

    public static ArrayList<ProfilesVO> getListProfiles(){
        ArrayList<ProfilesVO> arrcom = new ArrayList<ProfilesVO>();
        cc = new OtherConexion();
        cn = cc.conectarse(dataUser,dataPassword);

        String sql = null;

        sql = "SELECT * FROM Profiles;";

        try{
            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
                sovo = new ProfilesVO();
                sovo.setMuEmail(rs.getString(1));
                sovo.setBussinessUnitBuBisCode(rs.getLong(2));
                sovo.setMuPassword(rs.getString(3));
                sovo.setMuPasswordOld(rs.getString(4));
                sovo.setMuName(rs.getString(5));
                sovo.setMuSectionTime(rs.getInt(6));
                sovo.setMuQuestion(rs.getString(7));
                sovo.setMuStartDate(rs.getString(8));
                sovo.setMuAnswer(rs.getString(9));
                sovo.setMuStatus(rs.getString(10));
                sovo.setMuDate(rs.getString(11));
                sovo.setMuEffectiveDays(rs.getInt(12));
                sovo.setMuConfirmCode(rs.getString(13));
                sovo.setMuEmailConfirm(rs.getString(14));
                sovo.setMuDateResetPwd(rs.getString(15));
                sovo.setMuGender(rs.getString(16));
                sovo.setMuPhoto(rs.getBlob(17));

                sovo.setMuDataUser(rs.getString(18));
                sovo.setMuDataPassword(rs.getString(19));
                sovo.setMuExpires(rs.getString(20));
                sovo.setMuDateExpires(rs.getString(21));
                sovo.setCityCiCityCode(rs.getInt(22));
                sovo.setCityStatesStStateCode(rs.getInt(23));
                sovo.setCityStatesCountryCoCountryCode(rs.getInt(24));

                sovo.setContry_name(rs.getString(25));
                sovo.setState_name(rs.getString(26));
                sovo.setCity_name(rs.getString(27));
                sovo.setBu_name(rs.getString(28));
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
