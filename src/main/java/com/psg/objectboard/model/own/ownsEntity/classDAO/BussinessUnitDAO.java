package com.psg.objectboard.model.own.ownsEntity.classDAO;

import com.psg.objectboard.model.service.Other.OtherConexion;
import com.psg.objectboard.model.service.Other.SqlFunctions;
import com.psg.objectboard.model.own.ownsEntity.classVO.BussinessUnitVO;

import java.sql.*;
import java.util.ArrayList;

public class BussinessUnitDAO {
    private static OtherConexion cc = null;
    private static BussinessUnitVO covo = null;
    private static Connection cn = null;
    private static SqlFunctions sqls = null;
    private static ResultSet rs = null;
    private static Statement cmd = null;
    private static PreparedStatement pst = null;
    private static String dataUser;
    private static String dataPassword;

    public static void setDataUser(String dataUser) {
        BussinessUnitDAO.dataUser = dataUser;
    }
    //
    public static void setDataPassword(String dataPassword) {
        BussinessUnitDAO.dataPassword = dataPassword;
    }
    //
    public void insertBussinessUnitDAO(BussinessUnitVO sovo, Connection cone){
        String sql = "INSERT INTO bussinessUnit values (null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        sovo.setResult(false);
        try{
            pst = cone.prepareStatement(sql);
            pst.setString(1,sovo.getBuFederalNumber());
            pst.setString(2,sovo.getBuProvincialNumber());
            pst.setString(3,sovo.getBuEmail());
            pst.setString(4,sovo.getBuStatus());
            pst.setString(5,sovo.getBuSuperCode());
            pst.setString(6,sovo.getBuPhone());
            pst.setString(7,sovo.getBuZipCode());
            pst.setString(8,sovo.getBuName());
            pst.setString(9,sovo.getBuAddress());
            pst.setString(10,sovo.getBuWebPage());
            pst.setString(11,sovo.getBuAdminCode());
            pst.setString(12,sovo.getBuUser1Code());
            pst.setString(13,sovo.getBuUser2Code());
            pst.setLong(14,sovo.getCityCiCityCode());
            pst.setLong(15,sovo.getCityStatesStStateCode());
            pst.setLong(16,sovo.getCityStatesCountryCoCountryCode());
            pst.setLong(17,sovo.getBussinessTypeBtCodeType());
            pst.setString(18,sovo.getBuLogoName());
            pst.executeUpdate();
            System.out.println("Operacion de Insert bussinessUnit Exitosa.");
            sovo.setResult(true);
        }catch (SQLException ex){
            sovo.setResult(false);
            System.out.println("Error en la consulta de insert bussinessUnit: "+ex.getMessage());
        }
    }
    //
    public static Boolean deleteBussinessUnitDAO(Integer unit,Connection cone){
        Boolean result = null;
        String sql = "DELETE FROM bussinessUnit WHERE bu_bis_code=?";
        try{
            pst = cone.prepareStatement(sql);
            pst.setInt(1,unit);
            pst.executeUpdate();
            System.out.println("Bussines Unit "+ unit +" eliminada con exito");
            result = true;
        }catch (SQLException ex){
            result = false;
            System.out.println("Error en la eliminacion: "+ex.getMessage());
        }
        return result;
    }
    //
    public static void updateBussinessUnittDAO(BussinessUnitVO cov, Connection cone) {
        String sql = "UPDATE bussinessUnit SET " +
                "bu_federal_number=?," +
                "bu_provincial_number=?," +
                "bu_email=?," +
                "bu_status=?," +
                "bu_super_code=?," +
                "bu_phone=?," +
                "bu_zipcode=?," +
                "bu_name=?," +
                "bu_address=?," +
                "bu_webpage=?," +
                "bu_admin_code=?," +
                "bu_user1_code=?," +
                "bu_user2_code=?," +
                "city_ci_city_code=?," +
                "city_states_st_state_code=?," +
                "city_states_country_co_country_code=?," +
                "bussinessType_bt_code_type=?," +
                "bu_logo_name=?" +
                " WHERE (bu_bis_code=?)";
        try{
            pst = cone.prepareStatement(sql);
            pst.setString(1, cov.getBuFederalNumber());
            pst.setString(2, cov.getBuProvincialNumber());
            pst.setString(3, cov.getBuEmail());
            pst.setString(4, cov.getBuStatus());
            pst.setString(5, cov.getBuSuperCode());
            pst.setString(6, cov.getBuPhone());
            pst.setString(7, cov.getBuZipCode());
            pst.setString(8, cov.getBuName());
            pst.setString(9, cov.getBuAddress());
            pst.setString(10, cov.getBuWebPage());
            pst.setString(11, cov.getBuAdminCode());
            pst.setString(12, cov.getBuUser1Code());
            pst.setString(13, cov.getBuUser2Code());
            pst.setLong(14, cov.getCityCiCityCode());
            pst.setLong(15, cov.getCityStatesStStateCode());
            pst.setLong(16, cov.getCityStatesCountryCoCountryCode());
            pst.setLong(17, cov.getBussinessTypeBtCodeType());
            pst.setString(18, cov.getBuLogoName());
            pst.setLong(19, cov.getBuBisCode());
            pst.execute();
            System.out.println("Bussiness Unit actualizado con exito, ID: "+ cov.getBuName());
            cov.setResult(true);
        }catch (SQLException ex){
            System.out.println("Error en la actualizacion: "+ex.getMessage());
            System.out.println("sentencia: "+sql);
            cov.setResult(false);
        }
    }
    // Consutas simples y mixtas varias
    public static BussinessUnitVO serchBussinessUnitDAO(String clave){
        covo = new BussinessUnitVO();
        cc = new OtherConexion();
        cn = cc.conectarse(dataUser,dataPassword);
        String sql = "SELECT * FROM bussinessUnit WHERE bu_bis_code=?";
        try{
            pst = cn.prepareStatement(sql);
            pst.setString(1,clave);
            rs = pst.executeQuery();
            if (rs.next()){ // valida si trae algun registro
                covo.setBuBisCode(Integer.parseInt(rs.getString(1)));
                covo.setBuFederalNumber(rs.getString(2));
                covo.setBuProvincialNumber(rs.getString(3));
                covo.setBuEmail(rs.getString(4));
                covo.setBuStatus(rs.getString(5));
                covo.setBuSuperCode(rs.getString(6));
                covo.setBuPhone(rs.getString(7));
                covo.setBuZipCode(rs.getString(8));
                covo.setBuName(rs.getString(9));
                covo.setBuAddress(rs.getString(10));
                covo.setBuWebPage(rs.getString(11));
                covo.setBuAdminCode(rs.getString(12));
                covo.setBuUser1Code(rs.getString(13));
                covo.setBuUser2Code(rs.getString(14));
                covo.setCityCiCityCode(Integer.parseInt(rs.getString(15)));
                covo.setCityStatesStStateCode(Integer.parseInt(rs.getString(16)));
                covo.setCityStatesCountryCoCountryCode(Integer.parseInt(rs.getString(17)));
                covo.setBussinessTypeBtCodeType(Integer.parseInt(rs.getString(18)));
                covo.setBuLogoName(rs.getString(19));
            }
            covo.setResult(true);
            System.out.println("Busqueda exitosa");
        }catch (SQLException ex){
            covo.setResult(false);
            System.out.println("Error en la consulta: "+ex.getMessage());
        }finally {
            try{
                if (cn != null){
                    cn.close();
                    pst.close();
                    System.out.println("Conexion cerrada");
                }
            }catch (Exception e){
                covo.setResult(false);
                System.out.println("Error "+e);
            }
        }
        return covo;
    }
    //
    public ArrayList<BussinessUnitVO> getListBussinessUnit(String condi){
        ArrayList<BussinessUnitVO> arrcom = new ArrayList<BussinessUnitVO>();
        cc = new OtherConexion();
        cn = cc.conectarse(dataUser,dataPassword);
        sqls = new SqlFunctions();
        String sql = null;

        if(!condi.equals("")){
            sql = sqls.get_select("bussinessUnit", "*",condi,"bu_bis_code","","");
        }else{
            sql = sqls.get_select("bussinessUnit", "*","","bu_bis_code","","");
        }

        try{
            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
                covo = new BussinessUnitVO();
                covo.setBuBisCode(rs.getLong(1));
                covo.setBuFederalNumber(rs.getString(2));
                covo.setBuProvincialNumber(rs.getString(3));
                covo.setBuEmail(rs.getString(4));
                covo.setBuStatus(rs.getString(5));
                covo.setBuSuperCode(rs.getString(6));
                covo.setBuPhone(rs.getString(7));
                covo.setBuZipCode(rs.getString(8));
                covo.setBuName(rs.getString(9));
                covo.setBuAddress(rs.getString(10));
                covo.setBuWebPage(rs.getString(11));
                covo.setBuAdminCode(rs.getString(12));
                covo.setBuUser1Code(rs.getString(13));
                covo.setBuUser2Code(rs.getString(14));
                covo.setCityCiCityCode(rs.getLong(15));
                covo.setCityStatesStStateCode(rs.getLong(16));
                covo.setCityStatesCountryCoCountryCode(rs.getLong(17));
                covo.setBussinessTypeBtCodeType(rs.getLong(18));
                covo.setBuLogoName(rs.getString(19));
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

    public String getLastBussinessUnitCreate(String condi,Connection cone){
        //cc = new OtherConexion();
        //cn = cc.conectarse(dataUser,dataPassword);
        sqls = new SqlFunctions();
        Long contador = Long.parseLong("0");

        String sql = sqls.get_select("bussinessUnit", "*",condi,"bu_bis_code","","");

        try{
            pst = cone.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
                contador = rs.getLong(1);
            }
            System.out.println("Consulta contador exitosa: ");
        }catch (SQLException ex){
            System.out.println("Error en la consulta contador: "+ex.getMessage());
        }
        return String.valueOf(contador);
    }
}
