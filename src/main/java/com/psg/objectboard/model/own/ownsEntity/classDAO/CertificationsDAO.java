package com.psg.objectboard.model.own.ownsEntity.classDAO;

import com.psg.objectboard.model.service.Other.OtherConexion;
import com.psg.objectboard.model.service.Other.SqlFunctions;
import com.psg.objectboard.model.own.ownsEntity.classVO.CertificationsVO;

import java.sql.*;
import java.util.ArrayList;

public class CertificationsDAO {
    private static OtherConexion cc = null;
    private static CertificationsVO covo = null;
    private static Connection cn = null;
    private static SqlFunctions sqls = null;
    private static ResultSet rs = null;
    private static Statement cmd = null;
    private static PreparedStatement pst = null;
    private static String dataUser;
    private static String dataPassword;

    public static void setDataUser(String dataUser) {
        CertificationsDAO.dataUser = dataUser;
    }

    public static void setDataPassword(String dataPassword) {
        CertificationsDAO.dataPassword = dataPassword;
    }

    public ArrayList<CertificationsVO> getListCertifications(String condi){
        ArrayList<CertificationsVO> arrcom = new ArrayList<CertificationsVO>();
        cc = new OtherConexion();
        cn = cc.conectarse(dataUser,dataPassword);
        sqls = new SqlFunctions();
        String sql = null;
        if(!condi.equals("")){
            sql = sqls.get_select("certifications", "*",condi,"name","","");
        }else{
            sql = sqls.get_select("certifications", "*","","name","","");
        }
        try{
            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
                covo = new CertificationsVO();
                covo.setCertificationCode(rs.getLong(1));
                covo.setBussinessUnitBuBisCode(rs.getLong(2));
                covo.setName(rs.getString(3));
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

    public static void insertCertificationsDAO(CertificationsVO covo, Connection cone){
        String sql = "INSERT INTO certifications values (null,?,?)";
        covo.setResult(false);
        try{
            pst = cone.prepareStatement(sql);
            pst.setLong(1,covo.getBussinessUnitBuBisCode());
            pst.setString(2,covo.getName());
            pst.executeUpdate();
            System.out.println("Operacion de Insert Certifications Exitosa.");
            covo.setResult(true);
        }catch (SQLException ex){
            covo.setResult(false);
            System.out.println("Error en la consulta de insert Certifications: "+ex.getMessage());
        }
    }

    public static Boolean deleteCertificationsDAO(Integer clave0,Integer clave1,Connection cone){
        Boolean result = null;
        String sql = "DELETE FROM certifications WHERE " +
                "bussinessUnit_bu_bis_code=? AND certification_code=?";
        try{
            pst = cone.prepareStatement(sql);
            pst.setInt(1,clave0);
            pst.setInt(2,clave1);
            pst.executeUpdate();
            System.out.println("certifications "+clave0+" "+clave1+" eliminado con exito");
            result = true;
        }catch (SQLException ex){
            result = false;
            System.out.println("Error en la eliminacion: "+ex.getMessage());
        }
        return result;
    }

    public static void updateCertificationsDAO(CertificationsVO cov, Connection cone) {
        String sql = "UPDATE certifications SET " +
                "name=? " +
                " WHERE (bussinessUnit_bu_bis_code=? AND certification_code=?)";
        try{
            pst = cone.prepareStatement(sql);
            pst.setString(1,cov.getName());
            pst.setLong(2,cov.getBussinessUnitBuBisCode());
            pst.setLong(3,cov.getCertificationCode());
            pst.execute();
            System.out.println("certifications actualizado con exito, ID: "+
                    cov.getBussinessUnitBuBisCode()+" "+
                    cov.getCertificationCode()+" "+cov.getCertificationCode());
            cov.setResult(true);
        }catch (SQLException ex){
            System.out.println("Error en la actualizacion: "+ex.getMessage());
            cov.setResult(false);
        }
    }
}
