package com.psg.objectboard.model.own.ownsEntity.classDAO;

import com.psg.objectboard.model.own.ownsEntity.classVO.ProjectVO;
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
            busst.setResult(true);
        }catch (SQLException ex){
            System.out.println("Error en la consulta: "+ex.getMessage());
            busst.setResult(false);
        }finally {
            try{
                if (cn != null){
                    cn.close();
                    pst.close();
                }
            }catch (Exception e){
                System.out.println("Error : "+e);
                busst.setResult(false);
            }
        }
        return busst;
    }

    public ArrayList<BussinessTypeVO> getListBussinessTypeDAO(String condi){
        ArrayList<BussinessTypeVO> arrcom = new ArrayList<BussinessTypeVO>();
        cc = new OtherConexion();
        cn = cc.conectarse(dataUser,dataPassword);
        sqls = new SqlFunctions();
        String sql = null;
        if(!condi.equals("")){
            sql = sqls.get_select("bussinessType", "*",condi,"","","");
        }else{
            sql = sqls.get_select("bussinessType", "*","","","","");
        }
        try{
            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
                covo = new BussinessTypeVO();
                covo.setBtCodeType(rs.getLong(1));
                covo.setBtDescription(rs.getString(2));
                covo.setBtNote(rs.getString(3));
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

    public static void insertBussinessTypeDAO(BussinessTypeVO covo, Connection cone){
        String sql = "INSERT INTO bussinessType values (null,?,?)";
        covo.setResult(false);
        try{
            pst = cone.prepareStatement(sql);
            pst.setString(1,covo.getBtDescription());
            pst.setString(2,covo.getBtNote());
            pst.executeUpdate();
            System.out.println("Operacion de Insert bussinessType Exitosa.");
            covo.setResult(true);
        }catch (SQLException ex){
            covo.setResult(false);
            System.out.println("Error en la consulta de insert bussinessType: "+ex.getMessage());
        }
    }

    public static Boolean deleteBussinessTypeDAO(Integer clave,Connection cone){
        Boolean result = null;
        String sql = "DELETE FROM bussinessType WHERE bt_code_type=?";
        try{
            pst = cone.prepareStatement(sql);
            pst.setInt(1,clave);
            pst.executeUpdate();
            System.out.println("bussinessType "+clave+" "+ "eliminado con exito");
            result = true;
        }catch (SQLException ex){
            result = false;
            System.out.println("Error en la eliminacion: "+ex.getMessage());
        }
        return result;
    }

    public static void updateBussinessTypeDAO(BussinessTypeVO cov, Connection cone) {
        String sql = "UPDATE bussinessType SET " +
                "bt_description=?, " +
                "bt_note=? " +
                " WHERE bt_code_type=?";
        try{
            pst = cone.prepareStatement(sql);
            pst.setString(1,cov.getBtDescription());
            pst.setString(2,cov.getBtNote());
            pst.setLong(3,cov.getBtCodeType());
            pst.execute();
            System.out.println("bussinessType actualizado con exito, ID: "+
                    cov.getBtCodeType());
            cov.setResult(true);
        }catch (SQLException ex){
            System.out.println("Error en la actualizacion: "+ex.getMessage());
            cov.setResult(false);
        }
    }
}
