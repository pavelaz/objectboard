package com.psg.objectboard.model.own.ownsEntity.classDAO;

import com.psg.objectboard.model.service.Other.DateFunctions;
import com.psg.objectboard.model.service.Other.OtherConexion;
import com.psg.objectboard.model.service.Other.SqlFunctions;
import com.psg.objectboard.model.own.ownsEntity.classVO.DischargeVO;

import java.sql.*;
import java.util.ArrayList;

public class DischargeDAO {
    private static OtherConexion cc = null;
    private static DischargeVO covo = null;
    private static Connection cn = null;
    private static SqlFunctions sqls = null;
    private static ResultSet rs = null;
    private static Statement cmd = null;
    private static PreparedStatement pst = null;
    private static String dataUser;
    private static String dataPassword;

    public static void setDataUser(String dataUser) {
        DischargeDAO.dataUser = dataUser;
    }

    public static void setDataPassword(String dataPassword) {
        DischargeDAO.dataPassword = dataPassword;
    }

    public ArrayList<DischargeVO> getListDischarge(String condi){
        ArrayList<DischargeVO> arrcom = new ArrayList<DischargeVO>();
        cc = new OtherConexion();
        cn = cc.conectarse(dataUser,dataPassword);
        sqls = new SqlFunctions();
        String sql = null;
        if(!condi.equals("")){
            sql = sqls.get_select("discharge", "*",condi,"di_license_num","","");
        }else{
            sql = sqls.get_select("discharge", "*","","di_license_num","","");
        }
        try{
            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
                covo = new DischargeVO();
                covo.setDiLicenseNum(rs.getLong(1));
                covo.setDiStartDate(rs.getString(2));
                covo.setDiEndDate(rs.getString(3));
                covo.setDiNumberUser(rs.getInt(4));
                covo.setDiPermanent(rs.getString(5));
                covo.setDiSalesCode(rs.getInt(6));
                covo.setDiLicenseCode(rs.getString(7));
                covo.setBussinessUnitBuBisCode(rs.getLong(8));
                covo.setProjectPrIdProject(rs.getLong(9));
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

    public ArrayList<DischargeVO> getListDischargeActive(String condi){
        ArrayList<DischargeVO> arrcom = new ArrayList<DischargeVO>();
        DateFunctions df = new DateFunctions();
        String fecha_hoy = df.fechaFull(9);

        cc = new OtherConexion();
        cn = cc.conectarse(dataUser,dataPassword);
        sqls = new SqlFunctions();
        String sql = null;
        if(!condi.equals("")){
            sql = sqls.get_select("discharge", "*",condi,"di_license_num","","");
        }else{
            sql = sqls.get_select("discharge", "*","","di_license_num","","");
        }
        try{
            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
                covo = new DischargeVO();
                if (df.ComparaFechas(fecha_hoy,rs.getString(3)) != 1) {
                    covo.setDiLicenseNum(rs.getLong(1));
                    covo.setDiStartDate(rs.getString(2));
                    covo.setDiEndDate(rs.getString(3));
                    covo.setDiNumberUser(rs.getInt(4));
                    covo.setDiPermanent(rs.getString(5));
                    covo.setDiSalesCode(rs.getInt(6));
                    covo.setDiLicenseCode(rs.getString(7));
                    covo.setBussinessUnitBuBisCode(rs.getLong(8));
                    covo.setProjectPrIdProject(rs.getLong(9));
                    if (arrcom.isEmpty()) {
                        arrcom.add(0, covo);
                    } else {
                        arrcom.add(covo);
                    }
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

    public static void insertDischargeDAO(DischargeVO covo, Connection cone){
        String sql = "INSERT INTO discharge values (null,?,?,?,?,?,?,?,?)";
        covo.setResult(false);
        try{
            pst = cone.prepareStatement(sql);
            pst.setString(1,covo.getDiStartDate());
            pst.setString(2,covo.getDiEndDate());
            pst.setInt(3,covo.getDiNumberUser());
            pst.setString(4,covo.getDiPermanent());
            pst.setInt(5,covo.getDiSalesCode());
            pst.setString(6,covo.getDiLicenseCode());
            pst.setLong(7,covo.getBussinessUnitBuBisCode());
            pst.setLong(8,covo.getProjectPrIdProject());
            pst.executeUpdate();
            System.out.println("Operacion de Insert discharge Exitosa.");
            covo.setResult(true);
        }catch (SQLException ex){
            covo.setResult(false);
            System.out.println("Error en la consulta de insert discharge: "+ex.getMessage());
        }
    }

    public static Boolean deleteDischargeDAO(Integer clave1,Connection cone){
        Boolean result = null;
        String sql = "DELETE FROM discharge WHERE di_license_num=?";
        try{
            pst = cone.prepareStatement(sql);
            pst.setInt(1,clave1);
            pst.executeUpdate();
            System.out.println("Discharge "+clave1+" eliminado con exito");
            result = true;
        }catch (SQLException ex){
            result = false;
            System.out.println("Error en la eliminacion: "+ex.getMessage());
        }
        return result;
    }

    public static void updateDischargeDAO(DischargeVO cov, Connection cone) {
        String sql = "UPDATE discharge SET " +
                "di_start_date=?, " +
                "di_end_date=?, " +
                "di_number_user=?, " +
                "di_permanent=?, " +
                "di_sales_code=?, " +
                "di_license_code=?, " +
                "bussinessUnit_bu_bis_code=?, " +
                "project_pr_id_project=?" +
                " WHERE (di_license_num=?)";
        try{
            pst = cone.prepareStatement(sql);
            pst.setString(1,cov.getDiStartDate());
            pst.setString(2,cov.getDiEndDate());
            pst.setInt(3,cov.getDiNumberUser());
            pst.setString(4,cov.getDiPermanent());
            pst.setInt(5,cov.getDiSalesCode());
            pst.setString(6,cov.getDiLicenseCode());
            pst.setLong(7,cov.getBussinessUnitBuBisCode());
            pst.setLong(8,cov.getProjectPrIdProject());
            pst.setLong(9,cov.getDiLicenseNum());
            pst.execute();
            System.out.println("Discharge actualizado con exito, ID: "+ cov.getDiLicenseCode());
            cov.setResult(true);
        }catch (SQLException ex){
            System.out.println("Error en la actualizacion: "+ex.getMessage());
            cov.setResult(false);
        }
    }
}
