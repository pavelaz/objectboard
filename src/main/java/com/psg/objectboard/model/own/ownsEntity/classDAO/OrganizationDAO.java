package com.psg.objectboard.model.own.ownsEntity.classDAO;

import com.psg.objectboard.model.service.Other.OtherConexion;
import com.psg.objectboard.model.service.Other.SqlFunctions;
import com.psg.objectboard.model.own.ownsEntity.classVO.OrganizationVO;

import java.sql.*;
import java.util.ArrayList;

public class OrganizationDAO {
    private static OtherConexion cc = null;
    private static OrganizationVO covo = null;
    private static Connection cn = null;
    private static SqlFunctions sqls = null;
    private static ResultSet rs = null;
    private static Statement cmd = null;
    private static PreparedStatement pst = null;
    private static String dataUser;
    private static String dataPassword;

    public static void setDataUser(String dataUser) {
        OrganizationDAO.dataUser = dataUser;
    }

    public static void setDataPassword(String dataPassword) {
        OrganizationDAO.dataPassword = dataPassword;
    }

    public ArrayList<OrganizationVO> getListOrganization(String condi){
        ArrayList<OrganizationVO> arrcom = new ArrayList<OrganizationVO>();
        cc = new OtherConexion();
        cn = cc.conectarse(dataUser,dataPassword);
        sqls = new SqlFunctions();
        String sql = null;
        if(!condi.equals("")){
            sql = sqls.get_select("organization", "*",condi,"level1,level2,level3,level4","","");
        }else{
            sql = sqls.get_select("organization", "*","","level1,level2,level3,level4","","");
        }
        try{
            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
                covo = new OrganizationVO();
                covo.setBussinessUnitBuBbisCode(rs.getLong(1));
                covo.setLevel1(rs.getString(2));
                covo.setLevel2(rs.getString(3));
                covo.setLevel3(rs.getString(4));
                covo.setLevel4(rs.getString(5));
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

    public static void insertOrganizationDAO(OrganizationVO covo, Connection cone){
        String sql = "INSERT INTO organization values (?,?,?,?,?)";
        covo.setResult(false);
        try{
            pst = cone.prepareStatement(sql);
            pst.setLong(1,covo.getBussinessUnitBuBbisCode());
            pst.setString(2,covo.getLevel1());
            pst.setString(3,covo.getLevel2());
            pst.setString(4,covo.getLevel3());
            pst.setString(5,covo.getLevel4());
            pst.executeUpdate();
            System.out.println("Operacion de Insert organization Exitosa.");
            covo.setResult(true);
        }catch (SQLException ex){
            covo.setResult(false);
            System.out.println("Error en la consulta de insert organization: "+ex.getMessage());
        }
    }

    public static Boolean deleteOrganizationDAO(Integer clave0, String clave1, String clave2,
                                                String clave3, String clave4,Connection cone){
        Boolean result = null;
        String sql = "DELETE FROM organization WHERE " +
                "bussinessUnit_bu_bis_code=? AND level1=? AND level2=? AND level3=? AND level4=?";
        try{
            pst = cone.prepareStatement(sql);
            pst.setInt(1,clave0);
            pst.setString(2,clave1);
            pst.setString(3,clave2);
            pst.setString(4,clave3);
            pst.setString(5,clave4);
            pst.executeUpdate();
            System.out.println("Organization "+clave0+" "+clave1+" "+clave2+
                            " "+clave3+" "+clave4+" eliminado con exito");
            result = true;
        }catch (SQLException ex){
            result = false;
            System.out.println("Error en la eliminacion: "+ex.getMessage());
        }
        return result;
    }

    public static void updateOrganizationDAO(OrganizationVO cov, OrganizationVO old, Connection cone) {
        String sql = "UPDATE organization SET " +
                "level1=?, " +
                "level2=?, " +
                "level3=?, " +
                "level4=? " +
                " WHERE (bussinessUnit_bu_bis_code=? AND level1=? AND level2=? AND level3=? AND level4=?)";
        try{
            pst = cone.prepareStatement(sql);
            pst.setString(1,cov.getLevel1());
            pst.setString(2,cov.getLevel2());
            pst.setString(3,cov.getLevel3());
            pst.setString(4,cov.getLevel4());
            pst.setLong(5,cov.getBussinessUnitBuBbisCode());
            pst.setString(6,old.getLevel1());
            pst.setString(7,old.getLevel2());
            pst.setString(8,old.getLevel3());
            pst.setString(9,old.getLevel4());
            pst.execute();
            System.out.println("Organization actualizado con exito, ID: "+
                               cov.getBussinessUnitBuBbisCode()+" "+
                               old.getLevel1()+" "+old.getLevel2()+" "+
                               old.getLevel3()+" "+old.getLevel4());
            cov.setResult(true);
        }catch (SQLException ex){
            System.out.println("Error en la actualizacion: "+ex.getMessage());
            cov.setResult(false);
        }
    }

    public ArrayList<String> getListOrganizationLevels(String campo,String condi,String orden){
        ArrayList<String> arrcom = new ArrayList<String>();
        cc = new OtherConexion();
        cn = cc.conectarse(dataUser,dataPassword);
        sqls = new SqlFunctions();
        String sql = null;
        sql = sqls.get_select("organization", campo,condi,orden,"","");
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
