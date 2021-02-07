package com.psg.objectboard.model.own.ownsEntity.classDAO;

import com.psg.objectboard.model.own.ownsEntity.classVO.BussinessUnitVO;
import com.psg.objectboard.model.service.Other.OtherConexion;
import com.psg.objectboard.model.service.Other.OtherFunctions;
import com.psg.objectboard.model.service.Other.SqlFunctions;
import com.psg.objectboard.model.own.ownsEntity.classVO.BodyConductSurveyVO;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BodyConductSurveyDAO {
    private static OtherFunctions of = null;
    private static PreparedStatement pst = null;
    private static OtherConexion cc = null;
    private static Connection cn = null;
    private static ResultSet rs = null;
    private static SqlFunctions sqls = null;
    private static String dataUser;
    private static String dataPassword;

    public static void setDataUser(String dataUser) {
        BodyConductSurveyDAO.dataUser = dataUser;
    }

    public static void setDataPassword(String dataPassword) {
        BodyConductSurveyDAO.dataPassword = dataPassword;
    }

    public static void insertBodyConductSurveyDAO(BodyConductSurveyVO mus, Connection cone){

        String sql = "INSERT INTO bodyConductSurvey " +
                     "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        mus.setResult(false);
        try{
            pst = cone.prepareStatement(sql);
            pst.setLong(1,mus.getHeaderConductSurveyConductId());
            pst.setLong(2,mus.getBsaBodySurveyQuestionsQuestionCode());
            pst.setLong(3,mus.getBsaBodySurveyQuestionsHeadersSurveySurveyCode());
            pst.setLong(4,mus.getBsaBodySurveyQuestionsHeadersSurveyBussinessUnitBuBisCode());
            pst.setString(5,mus.getBcsAnswerOnlyText());
            pst.setDouble(6,mus.getBcsAnswerOnlyNumber());
            pst.setString(7,mus.getBcsAnswerOnlyDate());
            pst.setString(8,mus.getBcsAnswerOnlyTime());
            pst.setString(9,mus.getBcsComment());
            pst.setString(10,mus.getBcsAnswer());
            pst.setInt(11,mus.getBcsTypeRequest());
            pst.setString(12,mus.getBcsNameAnnexFile());
            pst.setString(13,mus.getBcsAnnexType());
            pst.setString(14,mus.getBcsAnswerSolution());
            pst.setString(15,mus.getStatusAudit());
            pst.setString(16,mus.getResultAudit());
            pst.setDouble(17,mus.getPoints());
            pst.setString(18,mus.getAuditNote());
            pst.setString(19,mus.getStatusRank());
            pst.setDouble(20,mus.getRankMin());
            pst.setDouble(21,mus.getRankMax());

            File file = new File(mus.getRutaAnnex().replace(" ","_"));
            FileInputStream fileInputStream = new FileInputStream(file);
            pst.setBinaryStream(22,fileInputStream);
            /*System.out.println("\n Reading input file " + file.getAbsolutePath());
                System.out.println("\n Storing resumen in database " + file);
                System.out.println(sql);

                 * BLOB Types Object type Value length that the object can hold TINYBLOB from 0 to 255 bytes BLOB from 0 to 65535 bytes MEDIUMBLOB from 0 to 16 777 215 bytes LONGBLOB from 0 to 4 294 967 295 bytes
                 * */

            pst.executeUpdate();
            System.out.println("Operacion de Insert Exitosa.");
            mus.setResult(true);
        }catch (SQLException | FileNotFoundException ex){
            mus.setResult(false);
            System.out.println("Error en la consulta de insert.: "+ex.getMessage());
        }
    }

    public static ArrayList<BodyConductSurveyVO> getListBodyConductSurvey(String condi){
        ArrayList<BodyConductSurveyVO> arrcom = new ArrayList<BodyConductSurveyVO>();
        cc = new OtherConexion();
        cn = cc.conectarse(dataUser,dataPassword);
        BodyConductSurveyVO com = null;
        sqls = new SqlFunctions();
        String sql = null;

        if(!condi.equals("")){
            sql = sqls.get_select("bodyConductSurvey", "*",condi,"","","");
        }else{
            sql = sqls.get_select("bodyConductSurvey", "*","","","","");
        }
        try{
            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
                com = new BodyConductSurveyVO();
                com.setHeaderConductSurveyConductId(rs.getLong(1));
                com.setBsaBodySurveyQuestionsQuestionCode(rs.getLong(2));
                com.setBsaBodySurveyQuestionsHeadersSurveySurveyCode(rs.getLong(3));
                com.setBsaBodySurveyQuestionsHeadersSurveyBussinessUnitBuBisCode(rs.getLong(4));
                com.setBcsAnswerOnlyText(rs.getString(5));
                com.setBcsAnswerOnlyNumber(rs.getDouble(6));
                com.setBcsAnswerOnlyDate(rs.getString(7));
                com.setBcsAnswerOnlyTime(rs.getString(8));

                com.setBcsComment(rs.getString(9));
                com.setBcsAnswer(rs.getString(10));
                com.setBcsTypeRequest(rs.getInt(11));
                com.setBcsNameAnnexFile(rs.getString(12));
                com.setBcsAnnexType(rs.getString(13));
                com.setBcsAnswerSolution(rs.getString(14));
                com.setStatusAudit(rs.getString(15));
                com.setResultAudit(rs.getString(16));
                com.setPoints(rs.getDouble(17));
                com.setAuditNote(rs.getString(18));
                com.setStatusRank(rs.getString(19));
                com.setRankMin(rs.getDouble(20));
                com.setRankMax(rs.getDouble(21));
                com.setBcsAnnexFile(rs.getBlob(22));
                int blobLength = (int) rs.getBlob(22).length();
                byte[] blobAsBytes = rs.getBlob(22).getBytes(1, blobLength);
                com.setBcsAnnexFileByte(blobAsBytes);

                if (arrcom.isEmpty()){
                    arrcom.add(0,com);
                }else{
                    arrcom.add(com);
                }
            }
        }catch (SQLException ex){
            System.out.println("Error en la consulta array bodyConductSurvey: "+ex.getMessage());
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

    public static BodyConductSurveyVO serchBodyConductSurveyDAO(String unidad,String conduct,String question, String survey){
        BodyConductSurveyVO covo = new BodyConductSurveyVO();
        cc = new OtherConexion();
        cn = cc.conectarse(dataUser,dataPassword);
        String sql = "SELECT * FROM bodyConductSurvey WHERE " +
                "headerConductSurvey_conduct_id=? AND bsa_bodySurveyQuestions_question_code=? AND " +
                "bsa_bodySurveyQuestions_headersSurvey_survey_code=? AND " +
                "bsa_bodySurveyQuestions_headersSurvey_bussinessUnit_bu_bis_code=?";
        try{
            pst = cn.prepareStatement(sql);
            pst.setLong(1,Long.parseLong(conduct));
            pst.setLong(2,Long.parseLong(question));
            pst.setLong(3,Long.parseLong(survey));
            pst.setLong(4,Long.parseLong(unidad));
            rs = pst.executeQuery();
            if (rs.next()){ // valida si trae algun registro
                covo.setHeaderConductSurveyConductId(rs.getLong(1));
                covo.setBsaBodySurveyQuestionsQuestionCode(rs.getLong(2));
                covo.setBsaBodySurveyQuestionsHeadersSurveySurveyCode(rs.getLong(3));
                covo.setBsaBodySurveyQuestionsHeadersSurveyBussinessUnitBuBisCode(rs.getLong(4));
                covo.setBcsAnswerOnlyText(rs.getString(5));
                covo.setBcsAnswerOnlyNumber(rs.getDouble(6));
                covo.setBcsAnswerOnlyDate(rs.getString(7));
                covo.setBcsAnswerOnlyTime(rs.getString(8));
                covo.setBcsComment(rs.getString(9));
                covo.setBcsAnswer(rs.getString(10));
                covo.setBcsTypeRequest(rs.getInt(11));
                covo.setBcsNameAnnexFile(rs.getString(12));
                covo.setBcsAnnexType(rs.getString(13));
                covo.setBcsAnswerSolution(rs.getString(14));
                covo.setStatusAudit(rs.getString(15));
                covo.setResultAudit(rs.getString(16));
                covo.setPoints(rs.getDouble(17));
                covo.setAuditNote(rs.getString(18));
                covo.setStatusRank(rs.getString(19));
                covo.setRankMin(rs.getDouble(20));
                covo.setRankMax(rs.getDouble(21));

                covo.setBcsAnnexFile(rs.getBlob(22));
                if (!(covo.getBcsAnnexFile() == null)){
                    int blobLength = (int) rs.getBlob(22).length();
                    byte[] blobAsBytes = rs.getBlob(22).getBytes(1, blobLength);
                    covo.setBcsAnnexFileByte(blobAsBytes);
                }
            }
            covo.setResult(true);
            System.out.println("Busqueda exitosa");
        }catch (SQLException ex){
            covo.setResult(false);
            System.out.println("Error en la consulta: " + ex.getMessage());
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

    /*public static void updateManualEvaluationBodyConductSurvey(BodyConductSurveyVO cov, Connection cone) {
        String sql = "UPDATE  bodyConductSurvey SET " +
                "status_audit = '" + cov.getStatusAudit() +
                "', result_audit = '" + cov.getResultAudit() +
                "', audit_note = '" + cov.getAuditNote() +
                "' WHERE (headerConductSurvey_conduct_id = " + cov.getHeaderConductSurveyConductId() +
                " AND bsa_bodySurveyQuestions_headersSurvey_survey_code = " + cov.getBsaBodySurveyQuestionsHeadersSurveySurveyCode() +
                " AND bsa_bodySurveyQuestions_headersSurvey_bussinessUnit_bu_bis_code = " + cov.getBsaBodySurveyQuestionsHeadersSurveyBussinessUnitBuBisCode() +
                " AND bsa_bodySurveyQuestions_question_code = " + cov.getBsaBodySurveyQuestionsQuestionCode() + ")";
        try{
            pst = cone.prepareStatement(sql);
            pst.execute();
            System.out.println("BodyConductSurvey actualizado con exito, ID: " + cov.getHeaderConductSurveyConductId());
            cov.setResult(true);
        }catch (SQLException ex){
            System.out.println("Error en la actualizacion: " + ex.getMessage());
            System.out.println("sentencia: " + sql);
            cov.setResult(false);
        }
    } */

}
