package com.psg.objectboard.model.own.ownsEntity.classDAO;

import com.psg.objectboard.model.service.Other.OtherConexion;
import com.psg.objectboard.model.service.Other.SqlFunctions;
import com.psg.objectboard.model.own.ownsEntity.classVO.TypifiedVO;

import java.sql.*;
import java.util.ArrayList;

public class TypifiedDAO {
    private static OtherConexion cc = null;
    private static TypifiedVO covo = null;
    private static Connection cn = null;
    private static SqlFunctions sqls = null;
    private static ResultSet rs = null;
    private static Statement cmd = null;
    private static PreparedStatement pst = null;
    private static String dataUser;
    private static String dataPassword;

    public static void setDataUser(String dataUser) {
        TypifiedDAO.dataUser = dataUser;
    }

    public static void setDataPassword(String dataPassword) {
        TypifiedDAO.dataPassword = dataPassword;
    }

    public ArrayList<TypifiedVO> getListTypified(String condi){
        ArrayList<TypifiedVO> arrcom = new ArrayList<TypifiedVO>();
        cc = new OtherConexion();
        cn = cc.conectarse(dataUser,dataPassword);
        sqls = new SqlFunctions();
        String sql = null;
        if(!condi.equals("")){
            sql = sqls.get_select("typified", "*",condi,"ctypified_code1,ctypified_code2,ctypified_code3","","");
        }else{
            sql = sqls.get_select("typified", "*","","ctypified_code1,ctypified_code2,ctypified_code3","","");
        }
        try{
            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
                covo = new TypifiedVO();
                covo.setBussinessUnitBuBbisCode(rs.getLong(1));
                covo.setCtypifiedCode1(rs.getString(2));
                covo.setCtypifiedCode2(rs.getString(3));
                covo.setCtypifiedCode3(rs.getString(4));
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

    public static void insertTypifiedDAO(TypifiedVO covo, Connection cone){
        String sql = "INSERT INTO typified values (?,?,?,?)";
        covo.setResult(false);
        try{
            pst = cone.prepareStatement(sql);
            pst.setLong(1,covo.getBussinessUnitBuBbisCode());
            pst.setString(2,covo.getCtypifiedCode1());
            pst.setString(3,covo.getCtypifiedCode2());
            pst.setString(4,covo.getCtypifiedCode3());
            pst.executeUpdate();
            System.out.println("Operacion de Insert typifieds Exitosa.");
            covo.setResult(true);
        }catch (SQLException ex){
            covo.setResult(false);
            System.out.println("Error en la consulta de insert typifieds: "+ex.getMessage());
        }
    }

    public static Boolean deleteTypifiedDAO(Integer clave0, String clave1, String clave2,
                                                String clave3, Connection cone){
        Boolean result = null;
        String sql = "DELETE FROM typified WHERE " +
                "bussinessUnit_bu_bis_code=? AND ctypified_code1=? AND ctypified_code2=? AND ctypified_code3=?";
        try{
            pst = cone.prepareStatement(sql);
            pst.setInt(1,clave0);
            pst.setString(2,clave1);
            pst.setString(3,clave2);
            pst.setString(4,clave3);
            pst.executeUpdate();
            System.out.println("typified "+clave0+" "+clave1+" "+clave2+
                    " "+clave3+" eliminado con exito");
            result = true;
        }catch (SQLException ex){
            result = false;
            System.out.println("Error en la eliminacion: "+ex.getMessage());
        }
        return result;
    }

    public static void updateTypifiedDAO(TypifiedVO cov, TypifiedVO old, Connection cone) {
        String sql = "UPDATE typified SET " +
                "ctypified_code1=?, " +
                "ctypified_code2=?, " +
                "ctypified_code3=? " +
                " WHERE (bussinessUnit_bu_bis_code=? AND ctypified_code1=? AND ctypified_code2=? AND ctypified_code3=?)";
        try{
            pst = cone.prepareStatement(sql);
            pst.setString(1,cov.getCtypifiedCode1());
            pst.setString(2,cov.getCtypifiedCode2());
            pst.setString(3,cov.getCtypifiedCode3());
            pst.setLong(4,cov.getBussinessUnitBuBbisCode());
            pst.setString(5,old.getCtypifiedCode1());
            pst.setString(6,old.getCtypifiedCode2());
            pst.setString(7,old.getCtypifiedCode3());
            pst.execute();
            System.out.println("typified actualizado con exito, ID: "+
                    cov.getBussinessUnitBuBbisCode()+" "+
                    old.getCtypifiedCode1()+" "+old.getCtypifiedCode2()+" "+
                    old.getCtypifiedCode3());
            cov.setResult(true);
        }catch (SQLException ex){
            System.out.println("Error en la actualizacion: "+ex.getMessage());
            cov.setResult(false);
        }
    }

    public ArrayList<String> getListTypifiedLevels(String campo,String condi,String orden){
        ArrayList<String> arrcom = new ArrayList<String>();
        cc = new OtherConexion();
        cn = cc.conectarse(dataUser,dataPassword);
        sqls = new SqlFunctions();
        String sql = null;
        sql = sqls.get_select("typified", campo,condi,orden,"","");
        try{
            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
                sql = new String();
                sql = rs.getString(1);
                if (arrcom.isEmpty()){
                    arrcom.add(0,sql);
                }else{
                    arrcom.add(sql);
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
