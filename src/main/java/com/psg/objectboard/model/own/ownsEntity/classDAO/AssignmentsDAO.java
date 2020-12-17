package com.psg.objectboard.model.own.ownsEntity.classDAO;

import com.psg.objectboard.model.service.Other.OtherConexion;
import com.psg.objectboard.model.service.Other.SqlFunctions;
import com.psg.objectboard.model.own.ownsEntity.classVO.AssignmentsVO;
import com.psg.objectboard.model.own.ownsEntity.classVO.OtherVO.AssignmentsConsultVO;

import java.sql.*;
import java.util.ArrayList;

public class AssignmentsDAO {
    private static OtherConexion cc = null;
    private static AssignmentsVO covo = null;
    private static Connection cn = null;
    private static SqlFunctions sqls = null;
    private static ResultSet rs = null;
    private static Statement cmd = null;
    private static PreparedStatement pst = null;
    private static String dataUser;
    private static String dataPassword;

    public void setDataUser(String dataUser) {
        AssignmentsDAO.dataUser = dataUser;
    }

    public  void setDataPassword(String dataPassword) {
        AssignmentsDAO.dataPassword = dataPassword;
    }

    public ArrayList<AssignmentsVO> getListAssignments(String condi){
        ArrayList<AssignmentsVO> arrcom = new ArrayList<>();
        cc = new OtherConexion();
        cn = cc.conectarse(dataUser,dataPassword);
        sqls = new SqlFunctions();
        String sql = null;
        if(!condi.equals("")){
            sql = sqls.get_select("assignments", "*",condi,"","","");
        }else{
            sql = sqls.get_select("assignments", "*","","","","");
        }
        try{
            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
                covo = new AssignmentsVO();
                covo.setMasterUserMuEmail(rs.getString(1));
                covo.setMasterUserBussinessUnitBuBisCode(rs.getLong(2));
                covo.setHeadersSurveySurveyCode(rs.getLong(3));
                covo.setDateStart(rs.getString(4));
                covo.setFrecuency(rs.getInt(5));
                covo.setTimeByFrecuency(rs.getInt(6));
                covo.setNotes(rs.getLong(7));
                covo.setLegends(rs.getLong(8));
                covo.setExceptions(rs.getLong(9));
                covo.setTerms(rs.getLong(10));
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

    public static void insertAssignmentsDAO(AssignmentsVO covo, Connection cone){
        String sql = "INSERT INTO assignments values (?,?,?,?,?,?,?,?,?,?)";
        covo.setResult(false);
        try{
            pst = cone.prepareStatement(sql);
            pst.setString(1,covo.getMasterUserMuEmail());
            pst.setLong(2,covo.getMasterUserBussinessUnitBuBisCode());
            pst.setLong(3,covo.getHeadersSurveySurveyCode());
            pst.setString(4,covo.getDateStart());
            pst.setInt(5,covo.getFrecuency());
            pst.setInt(6,covo.getTimeByFrecuency());
            pst.setLong(7,covo.getNotes());
            pst.setLong(8,covo.getLegends());
            pst.setLong(9,covo.getExceptions());
            pst.setLong(10,covo.getTerms());
            pst.executeUpdate();
            System.out.println("Operacion de Insert Assignments Exitosa.");
            covo.setResult(true);
        }catch (SQLException ex){
            covo.setResult(false);
            System.out.println("Error en la consulta de insert Assignments: "+ex.getMessage());
        }
    }

    public String getCommentsDescriptions(Long code,Connection cone){
        sqls = new SqlFunctions();
        ResultSet res = null;
        PreparedStatement pest = null;
        String sql = null;
        String retorno = "Not Selected";
        String condicion = "comment_code = " + code ;
        sql = sqls.get_select("comments", "*",condicion,"","","");
        try{
            pest = cone.prepareStatement(sql);
            res = pest.executeQuery();
            if (res.next()){
               retorno = res.getString(4);
            }
            System.out.println("Consulta array exitosa: ");
        }catch (SQLException ex){
            System.out.println("Error en la consulta array: "+ex.getMessage());
        }
        return retorno;
    }

    public String getSurveyVarios(Long code,Connection cone){
        sqls = new SqlFunctions();
        ResultSet res = null;
        PreparedStatement pest = null;
        String sql = null;
        String retorno = "Not Selected";
        String condicion = "survey_code = " + code ;
        sql = sqls.get_select("headersSurvey", "*",condicion,"","","");
        try{
            pest = cone.prepareStatement(sql);
            res = pest.executeQuery();
            if (res.next()){
                retorno = res.getString(3) + "-" +
                          res.getString(4) + "-" +
                          res.getString(20) + "-" +
                          res.getDouble(21);
            }
            System.out.println("Consulta array exitosa: ");
        }catch (SQLException ex){
            System.out.println("Error en la consulta array: "+ex.getMessage());
        }
        return retorno;
    }

    public String getMasterUserName(Long code,String email,Connection cone){
        sqls = new SqlFunctions();
        ResultSet res = null;
        PreparedStatement pest = null;
        String sql = null;
        String retorno = "Not Selected";
        String condicion = "bussinessUnit_bu_bis_code = " + code + " AND mu_email = '" + email + "'";
        sql = sqls.get_select("masterUser", "*",condicion,"","","");
        try{
            pest = cone.prepareStatement(sql);
            res = pest.executeQuery();
            if (res.next()){
                retorno = res.getString(5);
            }
            System.out.println("Consulta array exitosa: ");
        }catch (SQLException ex){
            System.out.println("Error en la consulta array: "+ex.getMessage());
        }
        return retorno;
    }

    public ArrayList<AssignmentsConsultVO> getListAssignmentsConsult(String condi){
        ArrayList<AssignmentsConsultVO> arrcom = new ArrayList<AssignmentsConsultVO>();
        AssignmentsConsultVO sovo = null;
        cc = new OtherConexion();
        cn = cc.conectarse(dataUser,dataPassword);
        sqls = new SqlFunctions();
        String sql = null;
        String varios = null;

        if(!condi.equals("")){
            sql = sqls.get_select("assignments", "*",condi,"","","");
        }else{
            sql = sqls.get_select("assignments", "*","","","","");
        }
        try{
            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
                sovo = new AssignmentsConsultVO();
                sovo.setMasterUserMuEmail(rs.getString(1));
                sovo.setMasterUserBussinessUnitBuBisCode(rs.getLong(2));
                sovo.setHeadersSurveySurveyCode(rs.getLong(3));
                sovo.setDateStart(rs.getString(4));
                sovo.setFrecuency(rs.getInt(5));
                sovo.setTimeByFrecuency(rs.getInt(6));
                sovo.setNotes(rs.getLong(7));
                sovo.setLegends(rs.getLong(8));
                sovo.setExceptions(rs.getLong(9));
                sovo.setTerms(rs.getLong(10));
                sovo.setNotesDescription(getCommentsDescriptions(sovo.getNotes(),cn));
                sovo.setLegendsDescription(getCommentsDescriptions(sovo.getLegends(),cn));
                sovo.setExceptionsDescription(getCommentsDescriptions(sovo.getExceptions(),cn));
                sovo.setTermsDescription(getCommentsDescriptions(sovo.getTerms(),cn));
                varios = getSurveyVarios(sovo.getHeadersSurveySurveyCode(),cn);
                String[] parts = varios.split("-");
                String part1 = parts[0]; // 1234
                String part2 = parts[1];
                String part3 = parts[2];
                String part4 = parts[3];
                sovo.setSurveyDescription(part1);
                sovo.setReference(part2);
                sovo.setAudited(part3);
                sovo.setPtosRaised(Double.parseDouble(part4));
                sovo.setUserName(getMasterUserName(sovo.getMasterUserBussinessUnitBuBisCode(),sovo.getMasterUserMuEmail(),cn));
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

    public static Boolean deleteAssignmentsDAO(Integer unid,String email, Integer survey,Connection cone){
        Boolean result = null;
        String sql = "DELETE FROM assignments WHERE " +
                "masterUser_mu_email=? AND masterUser_bussinessUnit_bu_bis_code=? AND headersSurvey_survey_code=?";
        try{
            pst = cone.prepareStatement(sql);
            pst.setString(1,email);
            pst.setInt(2,unid);
            pst.setInt(3,survey);
            pst.executeUpdate();
            System.out.println("assignments "+email+" "+survey+" eliminado con exito");
            result = true;
        }catch (SQLException ex){
            result = false;
            System.out.println("Error en la eliminacion: "+ex.getMessage());
        }
        return result;
    }

    public static void updateAssignmentsDAO(AssignmentsVO cov,Connection cone) {
        String sql = "UPDATE assignments SET " +
                "frecuency=?, " +
                "timeByFrecuency=?, " +
                "notes=?, " +
                "legends=?, " +
                "exceptions=?, " +
                "terms=? " +
                " WHERE (masterUser_bussinessUnit_bu_bis_code=? AND masterUser_mu_email=? AND headersSurvey_survey_code=?)";
        try{
            pst = cone.prepareStatement(sql);
            pst.setInt(1,cov.getFrecuency());
            pst.setInt(2,cov.getTimeByFrecuency());
            pst.setLong(3,cov.getNotes());
            pst.setLong(4,cov.getLegends());
            pst.setLong(5,cov.getExceptions());
            pst.setLong(6,cov.getTerms());
            pst.setLong(7,cov.getMasterUserBussinessUnitBuBisCode());
            pst.setString(8,cov.getMasterUserMuEmail());
            pst.setLong(9,cov.getHeadersSurveySurveyCode());
            pst.execute();
            System.out.println("assignments actualizado con exito, ID: "+
                    cov.getMasterUserBussinessUnitBuBisCode()+" "+
                    cov.getMasterUserMuEmail()+" "+cov.getHeadersSurveySurveyCode());
            cov.setResult(true);
        }catch (SQLException ex){
            System.out.println("Error en sql: "+sql);
            System.out.println("Error en la actualizacion: "+ex.getMessage());
            cov.setResult(false);
        }
    }

}
