package com.psg.objectboard.model.own.ownsEntity.classDAO;

import com.psg.objectboard.model.service.Other.OtherConexion;
import com.psg.objectboard.model.service.Other.OtherFunctions;
import com.psg.objectboard.model.service.Other.SqlFunctions;
import com.psg.objectboard.model.own.ownsEntity.classVO.HeaderConductSurveyVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HeaderConductSurveyDAO {
    private static OtherFunctions of = null;
    private static PreparedStatement pst = null;
    private static OtherConexion cc = null;
    private static Connection cn = null;
    private static ResultSet rs = null;
    private ResultSet rs1 = null;
    private static SqlFunctions sqls = null;
    private static String dataUser;
    private static String dataPassword;

    public void setDataUser(String dataUser) {
        HeaderConductSurveyDAO.dataUser = dataUser;
    }

    public void setDataPassword(String dataPassword) {
        HeaderConductSurveyDAO.dataPassword = dataPassword;
    }

    public void insertHeaderConductSurveyDAO(HeaderConductSurveyVO mus, Connection cone){
        String sql = "INSERT INTO headerConductSurvey values (null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try{
            pst = cone.prepareStatement(sql);
            pst.setLong(1,mus.getHeadersSurvey_survey_code());
            pst.setLong(2,mus.getHeadersSurvey_bussinessUnit_bu_bis_code());
            pst.setString(3,mus.getMasterUser_mu_email());
            pst.setString(4,mus.getConduct_date());
            pst.setLong(5,mus.getMasterUser_bussinessUnit_bu_bis_code());
            pst.setString(6,mus.getAuditable());
            pst.setString(7,mus.getTo_audited());
            pst.setString(8, mus.getUser_auditor());
            pst.setString(9,mus.getUser_conduct());
            pst.setLong(10,mus.getNotes_code());
            pst.setLong(11,mus.getLegends_code());
            pst.setLong(12,mus.getExceptions_code());
            pst.setLong(13,mus.getTerms_code());
            pst.setInt(14,mus.getConformities());
            pst.setInt(15,mus.getNon_conformities());
            pst.setDouble(16,mus.getTotalPointsRaised());
            pst.setDouble(17,mus.getTotalPointsAchieved());
            pst.executeUpdate();
            System.out.println("Operacion de Insert headerConductSurvey Exitosa.");
            mus.setResult(true);
        }catch (SQLException ex){
            mus.setResult(false);
            System.out.println("Error en la consulta de insert.headerConductSurvey: "+ex.getMessage());
        }
    }

    public ArrayList<HeaderConductSurveyVO> getListHeaderConductSurvey(String condi){
        ArrayList<HeaderConductSurveyVO> arrcom = new ArrayList<HeaderConductSurveyVO>();
        cc = new OtherConexion();
        cn = cc.conectarse(dataUser,dataPassword);
        HeaderConductSurveyVO com = null;
        sqls = new SqlFunctions();
        String sql = null,
               condicion = "";
        String[] var = null;

        if(!condi.equals("")){
            sql = sqls.get_select("headerConductSurvey", "*",condi,"conduct_id","","");
        }else{
            sql = sqls.get_select("headerConductSurvey", "*","","conduct_id","","");
        }
        try{
            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
                com = new HeaderConductSurveyVO();
                com.setConduct_id(rs.getLong(1));
                com.setHeadersSurvey_survey_code(rs.getLong(2));
                com.setHeadersSurvey_bussinessUnit_bu_bis_code(rs.getLong(3));
                com.setMasterUser_mu_email(rs.getString(4));
                com.setConduct_date(rs.getString(5));
                com.setMasterUser_bussinessUnit_bu_bis_code(rs.getLong(6));
                com.setAuditable(rs.getString(7));
                com.setTo_audited(rs.getString(8));
                com.setUser_auditor(rs.getString(9));
                com.setUser_conduct(rs.getString(10));
                com.setNotes_code(rs.getLong(11));
                com.setLegends_code(rs.getLong(12));
                com.setExceptions_code(rs.getLong(13));
                com.setTerms_code(rs.getLong(14));
                com.setConformities(rs.getInt(15));
                com.setNon_conformities(rs.getInt(16));
                com.setTotalPointsRaised(rs.getDouble(17));
                com.setTotalPointsAchieved(rs.getDouble(18));

                condicion = "survey_code = " + rs.getLong(2);
                condicion = this.getSurveyInformation(condicion,cn);
                var = condicion.split(",",2);
                com.setSurveyName(var[0]);
                com.setSurveyNumberQuestions(Integer.parseInt(var[1]));

                condicion = "mu_email = '" + com.getMasterUser_mu_email() + "' AND bussinessUnit_bu_bis_code = " + com.getHeadersSurvey_bussinessUnit_bu_bis_code();
                com.setUserAssignName(this.getMasterUserInformation(condicion,cn));

                condicion = "mu_email = '" + com.getUser_auditor() + "' AND bussinessUnit_bu_bis_code = " + com.getHeadersSurvey_bussinessUnit_bu_bis_code();
                com.setUserAuditorName(this.getMasterUserInformation(condicion,cn));

                condicion = "mu_email = '" + com.getUser_conduct() + "' AND bussinessUnit_bu_bis_code = " + com.getHeadersSurvey_bussinessUnit_bu_bis_code();
                com.setUserConductName(this.getMasterUserInformation(condicion,cn));

                if (arrcom.isEmpty()){
                    arrcom.add(0,com);
                }else{
                    arrcom.add(com);
                }
            }
        }catch (SQLException ex){
            System.out.println("Error en la consulta array headerConductSurvey: "+ex.getMessage());
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

    public String getMasterUserInformation(String condi,Connection cone){
        String variable = "";
        sqls = new SqlFunctions();
        String sql = null;

        sql = sqls.get_select("masterUser", "*",condi,"","","");
        try{
            pst = cone.prepareStatement(sql);
            rs1 = pst.executeQuery();
            while (rs1.next()){
                variable = rs1.getString(5);
            }
        }catch (SQLException ex){
            System.out.println("Error en la busqueda del id: "+ex.getMessage());
        }
        return variable;
    }

    public String getSurveyInformation(String condi,Connection cone){
        String variable = "";
        sqls = new SqlFunctions();
        String sql = null;

        sql = sqls.get_select("headersSurvey", "*",condi,"","","");
        try{
            pst = cone.prepareStatement(sql);
            rs1 = pst.executeQuery();
            while (rs1.next()){
                variable = rs1.getString(3) + "," + rs1.getInt(5);
            }
        }catch (SQLException ex){
            System.out.println("Error en la busqueda del id: "+ex.getMessage());
        }
        return variable;
    }

    public Long getLastIde(String condi,Connection cone){
        long count_id = Long.parseLong("0");
        sqls = new SqlFunctions();
        String sql = null;

        sql = sqls.get_select("headerConductSurvey", "conduct_id",condi,"","","");
        try{
            pst = cone.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
                count_id = rs.getLong(1);
            }
        }catch (SQLException ex){
            System.out.println("Error en la busqueda del id: "+ex.getMessage());
        }
        return count_id;
    }

    public static void updateEvaluadionAutoHeaderConductSurveyDAO(HeaderConductSurveyVO cov, Connection cone) {
        String sql = "UPDATE headerConductSurvey SET conformities = " + cov.getConformities() +
                ", non_conformities = " + cov.getNon_conformities() +
                ", total_points_achieved = " + cov.getTotalPointsAchieved() +
                " WHERE (conduct_id = " + cov.getConduct_id() +
                " AND headersSurvey_survey_code = " + cov.getHeadersSurvey_survey_code() +
                " AND headersSurvey_bussinessUnit_bu_bis_code = " + cov.getHeadersSurvey_bussinessUnit_bu_bis_code() +
                " AND masterUser_mu_email = '" + cov.getMasterUser_mu_email() +
                "' AND conduct_date = '" + cov.getConduct_date() + "')";
        try{
            pst = cone.prepareStatement(sql);
            pst.execute();
            System.out.println("HeadersSurvey actualizado con exito, ID: " + cov.getConduct_id());
            cov.setResult(true);
        }catch (SQLException ex){
            System.out.println("Error en la actualizacion: " + ex.getMessage());
            System.out.println("sentencia: " + sql);
            cov.setResult(false);
        }
    }

    public static void updateManualEvaluadionHeaderConductSurveyDAO(HeaderConductSurveyVO cov, Connection cone) {
        String sql = "UPDATE headerConductSurvey SET " +
                "conformities = " + cov.getConformities() +
                ", non_conformities = " + cov.getNon_conformities() +
                ", to_audited= '" + cov.getTo_audited() +
                "', total_points_achieved = " + cov.getTotalPointsAchieved() +
                " WHERE (conduct_id = " + cov.getConduct_id() +
                " AND headersSurvey_survey_code = " + cov.getHeadersSurvey_survey_code() +
                " AND headersSurvey_bussinessUnit_bu_bis_code = " + cov.getHeadersSurvey_bussinessUnit_bu_bis_code() +
                " AND masterUser_mu_email = '" + cov.getMasterUser_mu_email() + "')";
        try{
            pst = cone.prepareStatement(sql);
            pst.execute();
            System.out.println("HeadersSurvey actualizado con exito, ID: " + cov.getConduct_id());
            cov.setResult(true);
        }catch (SQLException ex){
            System.out.println("Error en la actualizacion: " + ex.getMessage());
            System.out.println("sentencia: " + sql);
            cov.setResult(false);
        }
    }
}
