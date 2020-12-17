package com.psg.objectboard.model.own.ownsEntity.classDAO;

import com.psg.objectboard.model.service.Other.OtherConexion;
import com.psg.objectboard.model.service.Other.SqlFunctions;
import com.psg.objectboard.model.own.ownsEntity.classVO.BussinessTypeVO;

import java.sql.*;
import java.util.ArrayList;

public class BussinessTypeDAO {
    private static OtherConexion cc = null;
    private static BussinessTypeVO covo = null;
    private static Connection cn = null;
    private static SqlFunctions sqls = null;
    private static ResultSet rs = null;
    private static Statement cmd = null;
    private static PreparedStatement pst = null;
    private static String dataUser;
    private static String dataPassword;

    public static void setDataUser(String dataUser) {
        BussinessTypeDAO.dataUser = dataUser;
    }

    public static void setDataPassword(String dataPassword) {
        BussinessTypeDAO.dataPassword = dataPassword;
    }

    public static String insertBussinessTypeDAO(BussinessTypeVO busst){
        String result = null, last=null;
        cc = new OtherConexion();
        cn = cc.conectarse(dataUser,dataPassword);
        PreparedStatement pst = null;
        String sql = "INSERT INTO BussinessType values (null,?,?)";
        try{
            pst = cn.prepareStatement(sql);
            pst.setString(1,busst.getBtDescription());
            pst.setString(2,busst.getBtNote());
            pst.execute();
            pst = cn.prepareStatement("SELECT MAX (bt_code_type) AS id FROM bussinessType");
            ResultSet rs = pst.executeQuery();
            if (rs.next()){
                last = rs.getString(1);
            }
            result = "Bussiness Type registrado con exito, ID: "+last;
        }catch (SQLException ex){
            result = "Error en la consulta: "+ex.getMessage();
        }finally {
            try{
                if (cn != null){
                    cn.close();
                    pst.close();
                }
            }catch (Exception e){
               result = "Error "+e;
            }
        }
        return result;
    }

    public static String updateBussinessTypeDAO(BussinessTypeVO busst) {
        String result = null, last=null;
        cc = new OtherConexion();
        cn = cc.conectarse(dataUser,dataPassword);
        PreparedStatement pst = null;
        String sql = "UPDATE bussinessType SET bt_description=?,bt_note=? WHERE bt_code_type=?";
        try{
            pst = cn.prepareStatement(sql);
            pst.setString(1,busst.getBtDescription());
            pst.setString(2,busst.getBtNote());
            pst.setLong(3,busst.getBtCodeType());
            pst.execute();
            result = "Bussiness Type actualizado con exito, ID: "+busst.getBtCodeType();
        }catch (SQLException ex){
            result = "Error en la consulta: "+ex.getMessage();
        }finally {
            try{
                if (cn != null){
                    cn.close();
                    pst.close();
                }
            }catch (Exception e){
                result = "Error "+e;
            }
        }
        return result;
    }

    public static BussinessTypeVO serchBussinessTypeDAO(String clave){
        BussinessTypeVO busst = new BussinessTypeVO();
        String result = null;
        cc = new OtherConexion();
        cn = cc.conectarse(dataUser,dataPassword);
        PreparedStatement pst = null;
        String sql = "SELECT * FROM bussinessType WHERE bt_code_type=?";
        try{
            pst = cn.prepareStatement(sql);
            pst.setString(1,clave);
            ResultSet rs = pst.executeQuery();
            if (rs.next()){ // valida si trae algun registro
                busst.setBtCodeType(Integer.parseInt(rs.getString(1)));
                busst.setBtDescription(rs.getString(2));
                busst.setBtNote(rs.getString(3));
            }
            busst.setResult("Busqueda exitosa");
        }catch (SQLException ex){
            busst.setResult("Error en la consulta: "+ex.getMessage());
        }finally {
            try{
                if (cn != null){
                    cn.close();
                    pst.close();
                }
            }catch (Exception e){
               busst.setResult("Error "+e);
            }
        }
        return busst;
    }

    public static String deleteBussinessTypeDAO(String clave){
        //BussinessTypeVO busst = new BussinessTypeVO();
        String result = null;
        cc = new OtherConexion();
        cn = cc.conectarse(dataUser,dataPassword);
        PreparedStatement pst = null;
        String sql = "DELETE FROM bussinessType WHERE bt_code_type=?";
        try{
            pst = cn.prepareStatement(sql);
            pst.setString(1,clave);
            pst.executeUpdate();
            result = "Bussiness Type eliminada con exito";
        }catch (SQLException ex){
            result = "Error en la eliminacion: "+ex.getMessage();
        }finally {
            try{
                if (cn != null){
                    cn.close();
                    pst.close();
                }
            }catch (Exception e){
                result = "Error "+e;
            }
        }
        return result;
    }

    public static ArrayList<BussinessTypeVO> getListBussinessType(){
        ArrayList<BussinessTypeVO> arrbusst = new ArrayList<BussinessTypeVO>();
        cc = new OtherConexion();
        cn = cc.conectarse(dataUser,dataPassword);
        String sql = "SELECT * FROM bussinessType";
        try{
            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
                covo = new BussinessTypeVO();
                covo.setBtCodeType(Integer.parseInt(rs.getString(1)));
                covo.setBtDescription(rs.getString(2));
                covo.setBtNote(rs.getString(3));
                if (arrbusst.isEmpty()){
                    arrbusst.add(0,covo);
                }else{
                    arrbusst.add(covo);
                }
            }
        }catch (SQLException ex){
            System.out.println("Error en la consulta: "+ex.getMessage());
        }finally {
            try{
                if (cn != null){
                    cn.close();
                    pst.close();
                }
            }catch (Exception e){
                System.out.println("Error "+e);
            }
        }
        return arrbusst;
    }
}
