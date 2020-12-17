package com.psg.objectboard.model.own.ownsEntity.classDAO;

import com.psg.objectboard.model.service.Other.OtherConexion;
import com.psg.objectboard.model.service.Other.SqlFunctions;
import com.psg.objectboard.model.own.ownsEntity.classVO.BodySurveyAnswersVO;

import java.sql.*;
import java.util.ArrayList;

public class BodySurveyAnswersDAO {
    private static OtherConexion cc = null;
    private static BodySurveyAnswersVO covo = null;
    private static Connection cn = null;
    private static SqlFunctions sqls = null;
    private static ResultSet rs = null;
    private static Statement cmd = null;
    private static PreparedStatement pst = null;
    private static String dataUser;
    private static String dataPassword;

    public void setDataUser(String dataUser) {
        BodySurveyAnswersDAO.dataUser = dataUser;
    }

    public void setDataPassword(String dataPassword) {
        BodySurveyAnswersDAO.dataPassword = dataPassword;
    }

    public ArrayList<BodySurveyAnswersVO> getListBodySurveyAnswers(String condi){
        ArrayList<BodySurveyAnswersVO> arrcom = new ArrayList<BodySurveyAnswersVO>();
        cc = new OtherConexion();
        cn = cc.conectarse(dataUser,dataPassword);
        sqls = new SqlFunctions();
        String sql = null;
        if(!condi.equals("")){
            sql = sqls.get_select("bodySurveyAnswers", "*",condi,"","","");
        }else{
            sql = sqls.get_select("bodySurveyAnswers", "*","","","","");
        }
        try{
            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
                covo = new BodySurveyAnswersVO();
                covo.setAnswerCode(rs.getLong(1));
                covo.setBodySurveyQuestionsQuestionCode(rs.getLong(2));
                covo.setBodySurveyQuestionsHeadersSurveySurveyCode(rs.getLong(3));
                covo.setBodySurveyQuestionsHeadersSurveyBussinessUnitBuBisCode(rs.getLong(4));
                covo.setAnswer(rs.getString(5));
                covo.setAnswerSolution(rs.getString(6));
                covo.setAuditableSolution(rs.getString(7));
                covo.setAnswerOnlyText(rs.getString(8));
                covo.setAnswerOnlyNumber(rs.getDouble(9));
                covo.setAnswerOnlyDate(rs.getString(10));
                covo.setAnswerOnlyTime(rs.getString(11));
                covo.setStatusRank(rs.getString(12));
                covo.setRankMin(rs.getDouble(13));
                covo.setRankMax(rs.getDouble(14));

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

    public static void insertBodySurveyAnswersDAO(BodySurveyAnswersVO covo, Connection cone){
        String sql = "INSERT INTO  bodySurveyAnswers values (null,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        covo.setResult(false);
        try{
            pst = cone.prepareStatement(sql);
            pst.setLong(1,covo.getBodySurveyQuestionsQuestionCode());
            pst.setLong(2,covo.getBodySurveyQuestionsHeadersSurveySurveyCode());
            pst.setLong(3,covo.getBodySurveyQuestionsHeadersSurveyBussinessUnitBuBisCode());
            pst.setString(4,covo.getAnswer());
            pst.setString(5,covo.getAnswerSolution());
            pst.setString(6,covo.getAuditableSolution());
            pst.setString(7,covo.getAnswerOnlyText());
            pst.setDouble(8,covo.getAnswerOnlyNumber());
            pst.setString(9,covo.getAnswerOnlyDate());
            pst.setString(10,covo.getAnswerOnlyTime());
            pst.setString(11,covo.getStatusRank());
            pst.setDouble(12,covo.getRankMin());
            pst.setDouble(13,covo.getRankMax());
            pst.executeUpdate();
            System.out.println("Operacion de Insert bodySurveyAnswers Exitosa.");
            covo.setResult(true);
        }catch (SQLException ex){
            covo.setResult(false);
            System.out.println("sql:"+sql);
            System.out.println("Error en la consulta de insert bodySurveyAnswers: "+ex.getMessage());
        }
    }

    public static void insertBodySurveyAnswersCopyDAO(BodySurveyAnswersVO covo, Connection cone){
        String sql = "INSERT INTO  bodySurveyAnswers values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        covo.setResult(false);
        try{
            pst = cone.prepareStatement(sql);
            pst.setLong(1,covo.getAnswerCode());
            pst.setLong(2,covo.getBodySurveyQuestionsQuestionCode());
            pst.setLong(3,covo.getBodySurveyQuestionsHeadersSurveySurveyCode());
            pst.setLong(4,covo.getBodySurveyQuestionsHeadersSurveyBussinessUnitBuBisCode());
            pst.setString(5,covo.getAnswer());
            pst.setString(6,covo.getAnswerSolution());
            pst.setString(7,covo.getAuditableSolution());
            pst.setString(8,covo.getAnswerOnlyText());
            pst.setDouble(9,covo.getAnswerOnlyNumber());
            pst.setString(10,covo.getAnswerOnlyDate());
            pst.setString(11,covo.getAnswerOnlyTime());
            pst.setString(12,covo.getStatusRank());
            pst.setDouble(13,covo.getRankMin());
            pst.setDouble(14,covo.getRankMax());
            pst.executeUpdate();
            System.out.println("Operacion de Insert bodySurveyAnswers Exitosa.");
            covo.setResult(true);
        }catch (SQLException ex){
            covo.setResult(false);
            System.out.println("sql:"+sql);
            System.out.println("Error en la consulta de insert bodySurveyAnswers: "+ex.getMessage());
        }
    }

    public static Boolean deleteBodySurveyAnswersDAO(Integer clave1, Integer clave2,Integer clave3,Integer clave4,Connection cone){
        Boolean result = null;
        String sql = "DELETE FROM bodySurveyAnswers WHERE " +
                "answer_code=? AND bodySurveyQuestions_question_code=? " +
                "AND bodySurveyQuestions_headersSurvey_survey_code=? AND bodySurveyQuestions_headersSurvey_bussinessUnit_bu_bis_code=?";
        try{
            pst = cone.prepareStatement(sql);
            pst.setInt(1,clave1);
            pst.setInt(2,clave2);
            pst.setInt(3,clave3);
            pst.setInt(4,clave4);
            pst.executeUpdate();
            System.out.println("bodySurveyAnswers "+clave1+" "+clave2+" "+clave3+" "+clave4+" eliminado con exito");
            result = true;
        }catch (SQLException ex){
            result = false;
            System.out.println("Error en la eliminacion: "+ex.getMessage());
        }
        return result;
    }

    public static void updateBodySurveyAnswersDAO(BodySurveyAnswersVO cov, Connection cone) {
        String sql = "UPDATE bodySurveyAnswers SET answer ='" +cov.getAnswer() +
                "', answer_solution='" + cov.getAnswerSolution() +
                "', auditable_solution='" + cov.getAuditableSolution() +
                "' WHERE (answer_code = " + cov.getAnswerCode() +
                " AND bodySurveyQuestions_question_code =" + cov.getBodySurveyQuestionsQuestionCode() +
                " AND bodySurveyQuestions_headersSurvey_survey_code =" + cov.getBodySurveyQuestionsHeadersSurveySurveyCode() +
                " AND bodySurveyQuestions_headersSurvey_bussinessUnit_bu_bis_code =" + cov.getBodySurveyQuestionsHeadersSurveyBussinessUnitBuBisCode() + ")";
        try{
            pst = cone.prepareStatement(sql);
            pst.execute();
            System.out.println("bodySurveyAnswers actualizado con exito, ID: "+ cov.getAnswerCode());
            cov.setResult(true);
        }catch (SQLException ex){
            System.out.println("Error en la actualizacion: "+ex.getMessage());
            System.out.println("sentencia: "+sql);
            cov.setResult(false);
        }
    }

    public static void updateBodySurveyAnswersDAO(BodySurveyAnswersVO cov,Integer type, Connection cone) {
        String sql = "UPDATE bodySurveyAnswers SET auditable_solution='" + cov.getAuditableSolution() + "'";
        if (type == 6)
            sql = sql + ", answer_only_text = '" + cov.getAnswerOnlyText() + "'";
        if (type == 7)
            sql = sql + ", answer_only_number = '" + cov.getAnswerOnlyNumber() +
                    "', status_rank = '" + cov.getStatusRank() +
                    "', rank_min = " + cov.getRankMin() +
                    ", rank_max = " + cov.getRankMax();
        if (type == 8)
            sql = sql + ", answer_only_time = '" + cov.getAnswerOnlyTime() + "'";
        if (type == 9)
            sql = sql + ", answer_only_date = '" + cov.getAnswerOnlyDate() + "'";
        sql = sql + " WHERE (answer_code = " + cov.getAnswerCode() +
                " AND bodySurveyQuestions_question_code =" + cov.getBodySurveyQuestionsQuestionCode() +
                " AND bodySurveyQuestions_headersSurvey_survey_code =" + cov.getBodySurveyQuestionsHeadersSurveySurveyCode() +
                " AND bodySurveyQuestions_headersSurvey_bussinessUnit_bu_bis_code =" + cov.getBodySurveyQuestionsHeadersSurveyBussinessUnitBuBisCode() + ")";
        try{
            pst = cone.prepareStatement(sql);
            pst.execute();
            System.out.println("bodySurveyAnswers actualizado con exito, ID: "+ cov.getAnswerCode());
            cov.setResult(true);
        }catch (SQLException ex){
            System.out.println("Error en la actualizacion: "+ex.getMessage());
            System.out.println("sentencia: "+sql);
            cov.setResult(false);
        }
    }
}
