package com.psg.objectboard.model.own.ownsEntity.classDAO;

import com.psg.objectboard.model.service.Other.OtherConexion;
import com.psg.objectboard.model.service.Other.SqlFunctions;
import com.psg.objectboard.model.own.ownsEntity.classVO.ProcessVO;

import java.sql.*;
import java.util.ArrayList;

public class ProcessDAO {
    private static OtherConexion cc = null;
    private static ProcessVO covo = null;
    private static Connection cn = null;
    private static SqlFunctions sqls = null;
    private static ResultSet rs = null;
    private static Statement cmd = null;
    private static PreparedStatement pst = null;
    private static String dataUser;
    private static String dataPassword;

    public static void setDataUser(String dataUser) {
        ProcessDAO.dataUser = dataUser;
    }

    public static void setDataPassword(String dataPassword) {
        ProcessDAO.dataPassword = dataPassword;
    }

    public ArrayList<ProcessVO> getListProcess(String condi){
        ArrayList<ProcessVO> arrcom = new ArrayList<ProcessVO>();
        cc = new OtherConexion();
        cn = cc.conectarse(dataUser,dataPassword);
        sqls = new SqlFunctions();
        String sql = null;
        if(!condi.equals("")){
            sql = sqls.get_select("process", "*",condi,"","","");
        }else{
            sql = sqls.get_select("process", "*","","","","");
        }
        try{
            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
                covo = new ProcessVO();
                covo.setProcessCode(rs.getLong(1));
                covo.setCertificationsCertificationCode(rs.getLong(1));
                covo.setCertificationsBussinessUnitBuBisCode(rs.getLong(1));
                covo.setHeadersSurveySurveyCode(rs.getLong(1));
                covo.setStatus(rs.getString(5));
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
}
