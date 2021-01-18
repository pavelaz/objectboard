package com.psg.objectboard.model.own.ownsEntity.classDAO;

import com.psg.objectboard.model.own.ownsEntity.classVO.MasterUserVO;
import com.psg.objectboard.model.service.Other.*;

import java.io.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class MasterUserDAO {
    private static OtherFunctions of = null;
    private static PreparedStatement pst = null;
    private static OtherConexion cc = null;
    private static Connection cn = null;
    private static ResultSet rs = null;
    private static SqlFunctions sqls = null;
    private static String dataUser;
    private static String dataPassword;

    public static void setDataUser(String dataUser) {
        MasterUserDAO.dataUser = dataUser;
    }

    public static void setDataPassword(String dataPassword) {
        MasterUserDAO.dataPassword = dataPassword;
    }

    public static void insertMasterUserDAO(MasterUserVO mus, Connection cone){
        of = new OtherFunctions();
        String last=null;
        FileInputStream fi = null;
        String sql = "INSERT INTO masterUser values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        mus.setResult(false);
        try{
            File file = new File(mus.getRuta_imagen());
            fi= new FileInputStream(file);
            pst = cone.prepareStatement(sql);
            pst.setString(1,mus.getMuEmail());
            pst.setLong(2,mus.getBussinessUnitBuBisCode());
            pst.setString(3,mus.getMuPassword());
            pst.setString(4,mus.getMuPasswordOld());
            pst.setString(5,mus.getMuName());
            pst.setInt(6,mus.getMuSectionTime());
            pst.setString(7,mus.getMuQuestion());
            pst.setString(8, mus.getMuDate());
            pst.setString(9,mus.getMuAnswer());
            pst.setString(10,mus.getMuStatus());
            pst.setString(11,mus.getMuDate());
            pst.setInt(12,mus.getMuEffectiveDays());
            pst.setString(13,mus.getMuConfirmCode());
            pst.setString(14,mus.getMuEmailConfirm());
            pst.setString(15,mus.getMuDateResetPwd());
            pst.setString(16,mus.getMuGender());
            pst.setBinaryStream(17,fi, (int) file.length());

            pst.setString(18,mus.getMuDataUser());
            pst.setString(19,mus.getMuDataPassword());
            pst.setString(20,mus.getMuExpires());
            pst.setString(21,mus.getMuDateExpires());
            pst.setInt(22,mus.getCityCiCityCode());
            pst.setInt(23,mus.getCityStatesStStateCode());
            pst.setInt(24,mus.getCityStatesCountryCoCountryCode());
            pst.executeUpdate();
            System.out.println("Operacion de Insert Exitosa.");
            //  busca para validar y mostrar en consola los datos del usuario registrado
            pst = cone.prepareStatement("SELECT mu_email, bussinessUnit_bu_bis_code FROM masterUser" +
                    " WHERE mu_email='" + mus.getMuEmail()+ "' AND bussinessUnit_bu_bis_code = " + mus.getBussinessUnitBuBisCode());
            rs = pst.executeQuery();
            if (rs.next()){
                last = rs.getString(1)+","+rs.getString(2);
                mus.setResultMsg("User registrado con exito, ID: "+last);
                System.out.println("User registrado con exito, ID: "+last);
                mus.setResult(true);
            }else{
                mus.setResultMsg("User NO registrado.");
                System.out.println("User NO registrado.");
            }
        }catch (FileNotFoundException | SQLException ex){
            mus.setResult(false);
            mus.setResultMsg("Error en la consulta: "+ex.getMessage() + sql);
            System.out.println("Error en la consulta de insert.: "+ex.getMessage());
        }
    }

    public static void updateEmailConfirmDAO(MasterUserVO muv, Connection cone) {
        String sql = "UPDATE masterUser SET " +
                "mu_status=?," +
                "mu_email_confirm=?" +
                " WHERE (mu_email=? AND bussinessUnit_bu_bis_code=?)";
        try{
            pst = cone.prepareStatement(sql);
            pst.setString(1,"T");
            pst.setString(2,"T");
            pst.setString(3,muv.getMuEmail());
            pst.setLong(4, muv.getBussinessUnitBuBisCode());
            pst.execute();
            System.out.println("Master User actualizada con exito, ID: "+muv.getMuEmail()+
                   " , "+muv.getBussinessUnitBuBisCode());
            muv.setResult(true);
        }catch (SQLException ex){
            System.out.println("Error en la actualizacion: "+ex.getMessage());
            muv.setResult(false);
        }
    }

    public static void updateIniciaResetDAO(MasterUserVO muv, Connection cone) {
        LocalDate fecha = null;
        LocalDateFunctions of = new LocalDateFunctions();
        fecha = of.obtenerFechaActual();
        String sql = "UPDATE masterUser SET " +
                "mu_date_reset_pwd=?" +
                " WHERE (mu_email=? AND bussinessUnit_bu_bis_code=?)";
        try{
            pst = cone.prepareStatement(sql);
            pst.setString(1, String.valueOf(fecha));
            pst.setString(2,muv.getMuEmail());
            pst.setLong(3, muv.getBussinessUnitBuBisCode());
            pst.execute();
            System.out.println("Master User actualizada con exito, ID: "+muv.getMuEmail()+
                    " , "+muv.getBussinessUnitBuBisCode());
            muv.setResult(true);
        }catch (SQLException ex){
            System.out.println("Error en la actualizacion: "+ex.getMessage());
            muv.setResult(false);
        }
    }

    public static void updateFinalResetDAO(MasterUserVO muv, Connection cone,String pwd) {
        String oldpasswd = muv.getMuPassword();
        String sql = "UPDATE masterUser SET " +
                "mu_password=?," +
                "mu_password_old=?" +
                " WHERE (mu_email=? AND bussinessUnit_bu_bis_code=?)";
        try{
            pst = cone.prepareStatement(sql);
            pst.setString(1,pwd);
            pst.setString(2,oldpasswd);
            pst.setString(3,muv.getMuEmail());
            pst.setLong(4, muv.getBussinessUnitBuBisCode());
            pst.execute();
            System.out.println("Master User actualizada con exito, ID: "+muv.getMuEmail()+
                    " , "+muv.getBussinessUnitBuBisCode());
            muv.setResult(true);
        }catch (SQLException ex){
            System.out.println("Error en la actualizacion: "+ex.getMessage());
            muv.setResult(false);
        }
    }

    public static void updatePasswordValidDAO(MasterUserVO muv, Connection cone,String pwd) {
        String oldpasswd = muv.getMuPassword();
        String fecha = null;
        DateFunctions of = new DateFunctions();
        fecha = of.fechaFull(9);
        //String fech= String.valueOf(fecha);
        String sql = "UPDATE masterUser SET " +
                "mu_password=?," +
                "mu_password_old=?," +
                "mu_date=?" +
                " WHERE (mu_email=? AND bussinessUnit_bu_bis_code=?)";
        try{
            pst = cone.prepareStatement(sql);
            pst.setString(1,pwd);
            pst.setString(2,oldpasswd);
            pst.setString(3,fecha);
            pst.setString(4,muv.getMuEmail());
            pst.setLong(5, muv.getBussinessUnitBuBisCode());
            pst.execute();
            System.out.println("Master User actualizada con exito, ID: "+muv.getMuEmail()+
                    " , "+muv.getBussinessUnitBuBisCode());
            muv.setResult(true);
        }catch (SQLException ex){
            System.out.println("Error en la actualizacion: "+ex.getMessage());
            muv.setResult(false);
        }
    }

    public static void updateMasterUserDAO(MasterUserVO muv, Connection cone) {
        String sql = "UPDATE masterUser SET " +
                "mu_password=?," +
                "mu_name=?," +
                "mu_section_time=?," +
                "mu_question=?," +
                "mu_answer=?," +
                "mu_status=?," +
                "mu_effective_days=?," +
                "mu_email_confirm=?," +
                "mu_gender=?," +
                "mu_expires=?," +
                "mu_date_expires=?," +
                "city_ci_city_code=?," +
                "city_states_st_state_code=?," +
                "city_states_country_co_country_code=?" +
                " WHERE (mu_email=? AND bussinessUnit_bu_bis_code=?)";
        try{
            pst = cone.prepareStatement(sql);
            pst.setString(1,muv.getMuPassword());
            pst.setString(2,muv.getMuName());
            pst.setInt(3,muv.getMuSectionTime());
            pst.setString(4,muv.getMuQuestion());
            pst.setString(5,muv.getMuAnswer());
            pst.setString(6,muv.getMuStatus());
            pst.setInt(7,muv.getMuEffectiveDays());

            pst.setString(8,muv.getMuEmailConfirm());
            pst.setString(9,muv.getMuGender());
            pst.setString(10,muv.getMuExpires());
            pst.setString(11,muv.getMuDateExpires());

            pst.setInt(12,muv.getCityCiCityCode());
            pst.setInt(13,muv.getCityStatesStStateCode());
            pst.setInt(14,muv.getCityStatesCountryCoCountryCode());

            pst.setString(15,muv.getMuEmail());
            pst.setLong(16, muv.getBussinessUnitBuBisCode());
            pst.execute();
            System.out.println("Master User actualizado con exito, ID: "+muv.getMuEmail()+
                    " , "+muv.getBussinessUnitBuBisCode());
            muv.setResult(true);
        }catch (SQLException ex){
            System.out.println("Error en la actualizacion: "+ex.getMessage());
            muv.setResult(false);
        }
    }

    public static void updateMasterUserImage(MasterUserVO muv, Connection cone) {
        FileInputStream fi = null;
        String sql = "UPDATE masterUser SET " +
                "muPhoto=?" +
                " WHERE (mu_email=? AND bussinessUnit_bu_bis_code=?)";
        try{
            File file = new File(muv.getRuta_imagen());
            fi = new FileInputStream(file);
            pst = cone.prepareStatement(sql);
            /*pst.setString(1,muv.getMuPassword());
            pst.setString(2,muv.getMuName());
            pst.setInt(3,muv.getMuSectionTime());
            pst.setString(4,muv.getMuQuestion());
            pst.setString(5,muv.getMuAnswer());
            pst.setString(6,muv.getMuStatus());
            pst.setInt(7,muv.getMuEffectiveDays());

            pst.setString(8,muv.getMuEmailConfirm());
            pst.setString(9,muv.getMuGender());
            pst.setString(10,muv.getMuExpires());
            pst.setString(11,muv.getMuDateExpires());

            pst.setInt(12,muv.getCityCiCityCode());
            pst.setInt(13,muv.getCityStatesStStateCode());
            pst.setInt(14,muv.getCityStatesCountryCoCountryCode());*/

            pst.setBinaryStream(1,fi, (int) file.length());
            pst.setString(2,muv.getMuEmail());
            pst.setLong(3, muv.getBussinessUnitBuBisCode());
            pst.execute();
            System.out.println("Image Master User actualizada con exito, ID: "+muv.getMuEmail()+
                    " , "+muv.getBussinessUnitBuBisCode());
            muv.setResult(true);
        }catch (SQLException | FileNotFoundException ex){
            System.out.println("Error en la actualizacion Imagen: "+ex.getMessage());
            muv.setResult(false);
        }
    }

    public MasterUserVO serchMasterUserDAO(String clave1,String clave2){
        MasterUserVO com = new MasterUserVO();
        cc = new OtherConexion();
        cn = cc.conectarse(dataUser,dataPassword);
        String sql = "SELECT * FROM masterUser WHERE mu_email=? AND bussinessUnit_bu_bis_code=?";
        try{
            pst = cn.prepareStatement(sql);
            pst.setString(1,clave1);
            pst.setInt(2,Integer.parseInt(clave2));
            rs = pst.executeQuery();
            com.setResult(false);
            if (rs.next()){ // valida si trae algun registro
                com.setMuEmail(rs.getString(1));
                com.setBussinessUnitBuBisCode(rs.getLong(2));
                com.setMuPassword(rs.getString(3));
                com.setMuPasswordOld(rs.getString(4));
                com.setMuName(rs.getString(5));
                com.setMuSectionTime(rs.getInt(6));
                com.setMuQuestion(rs.getString(7));
                com.setMuStartDate(rs.getString(8));
                com.setMuAnswer(rs.getString(9));
                com.setMuStatus(rs.getString(10));
                com.setMuDate(rs.getString(11));
                com.setMuEffectiveDays(rs.getInt(12));
                com.setMuConfirmCode(rs.getString(13));
                com.setMuEmailConfirm(rs.getString(14));
                com.setMuDateResetPwd(rs.getString(15));
                com.setMuGender(rs.getString(16));
                com.setMuPhoto(rs.getBlob(17));

                com.setMuDataUser(rs.getString(18));
                com.setMuDataPassword(rs.getString(19));
                com.setMuExpires(rs.getString(20));
                com.setMuDateExpires(rs.getString(21));
                com.setCityCiCityCode(rs.getInt(22));
                com.setCityStatesStStateCode(rs.getInt(23));
                com.setCityStatesCountryCoCountryCode(rs.getInt(24));

                com.setResult(true);
                // convierte campo tipo blob a byte[]
                int blobLength = (int) rs.getBlob(17).length();
                byte[] blobAsBytes = rs.getBlob(17).getBytes(1, blobLength);
                com.setMuPhotoByte(blobAsBytes);
            }
            com.setResultMsg("Busqueda exitosa");
            System.out.println("Busqueda de usuario exitosa");
        }catch (SQLException ex){
            com.setResult(false);
            com.setResultMsg("Error en la consulta: "+ex.getMessage());
            System.out.println("Error en la consulta de usuario: "+ex.getMessage());
        }finally {
            try{
                if (cn != null){
                    cn.close();
                    pst.close();
                    System.out.println("Conexion cerrada");
                }
            }catch (Exception e){
                com.setResultMsg("Error "+e);
                System.out.println("Error "+e);
            }
        }
        return com;
    }

    public static Boolean deleteMasterUserDAO(Integer clave1,String clave2,Connection cone){
        Boolean result = null;
        String sql = "DELETE FROM masterUser WHERE bussinessUnit_bu_bis_code=? AND mu_email=?";
        try{
            pst = cone.prepareStatement(sql);
            pst.setInt(1,clave1);
            pst.setString(2,clave2);
            pst.executeUpdate();
            System.out.println("Usuario "+clave1+" " + clave2+ " eliminado con exito");
            result = true;
        }catch (SQLException ex){
            result = false;
            System.out.println("Error en la eliminacion: "+ex.getMessage());
        }
        return result;
    }

    public static ArrayList<MasterUserVO> getListMasterUser(String condi){
        ArrayList<MasterUserVO> arrcom = new ArrayList<MasterUserVO>();
        cc = new OtherConexion();
        cn = cc.conectarse(dataUser,dataPassword);
        MasterUserVO com = null;
        sqls = new SqlFunctions();
        String sql = null;

        if(!condi.equals("")){
            sql = sqls.get_select("masterUser", "*",condi,"mu_email","","");
        }else{
            sql = sqls.get_select("masterUser", "*","","mu_email","","");
        }
        try{
            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
                com = new MasterUserVO();
                com.setMuEmail(rs.getString(1));
                com.setBussinessUnitBuBisCode(rs.getLong(2));
                com.setMuPassword(rs.getString(3));
                com.setMuPasswordOld(rs.getString(4));
                com.setMuName(rs.getString(5));
                com.setMuSectionTime(rs.getInt(6));
                com.setMuQuestion(rs.getString(7));
                com.setMuStartDate(rs.getString(8));
                com.setMuAnswer(rs.getString(9));
                com.setMuStatus(rs.getString(10));
                com.setMuDate(rs.getString(11));
                com.setMuEffectiveDays(rs.getInt(12));
                com.setMuConfirmCode(rs.getString(13));
                com.setMuEmailConfirm(rs.getString(14));
                com.setMuDateResetPwd(rs.getString(15));
                com.setMuGender(rs.getString(16));
                com.setMuPhoto(rs.getBlob(17));

                com.setMuDataUser(rs.getString(18));
                com.setMuDataPassword(rs.getString(19));
                com.setMuExpires(rs.getString(20));
                com.setMuDateExpires(rs.getString(21));
                com.setCityCiCityCode(rs.getInt(22));
                com.setCityStatesStStateCode(rs.getInt(23));
                com.setCityStatesCountryCoCountryCode(rs.getInt(24));

                int blobLength = (int) rs.getBlob(17).length();
                byte[] blobAsBytes = rs.getBlob(17).getBytes(1, blobLength);
                com.setMuPhotoByte(blobAsBytes);

                if (arrcom.isEmpty()){
                    arrcom.add(0,com);
                }else{
                    arrcom.add(com);
                }
            }
        }catch (SQLException ex){
            System.out.println("Error en la consulta array masterUser: "+ex.getMessage());
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