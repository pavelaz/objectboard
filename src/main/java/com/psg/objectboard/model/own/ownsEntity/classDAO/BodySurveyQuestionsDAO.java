package com.psg.objectboard.model.own.ownsEntity.classDAO;

import com.psg.objectboard.model.own.ownsEntity.classVO.HeadersSurveyVO;
import com.psg.objectboard.model.service.Other.OtherConexion;
import com.psg.objectboard.model.service.Other.SqlFunctions;
import com.psg.objectboard.model.own.ownsEntity.classVO.BodySurveyQuestionsVO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;

public class BodySurveyQuestionsDAO {
    private static OtherConexion cc = null;
    private static BodySurveyQuestionsVO covo = null;
    private static Connection cn = null;
    private static SqlFunctions sqls = null;
    private static ResultSet rs = null;
    private static Statement cmd = null;
    private static PreparedStatement pst = null;
    private static String dataUser;
    private static String dataPassword;

    public void setDataUser(String dataUser) {
        BodySurveyQuestionsDAO.dataUser = dataUser;
    }

    public void setDataPassword(String dataPassword) {
        BodySurveyQuestionsDAO.dataPassword = dataPassword;
    }

    public ArrayList<BodySurveyQuestionsVO> getListBodySurveyQuestions(String condi){
        ArrayList<BodySurveyQuestionsVO> arrcom = new ArrayList<BodySurveyQuestionsVO>();
        cc = new OtherConexion();
        cn = cc.conectarse(dataUser,dataPassword);
        sqls = new SqlFunctions();
        String sql = null;
        if(!condi.equals("")){
            sql = sqls.get_select("bodySurveyQuestions", "*",condi,"","","");
        }else{
            sql = sqls.get_select("bodySurveyQuestions", "*","","","","");
        }
        try{
            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
                covo = new BodySurveyQuestionsVO();
                covo.setQuestionCode(rs.getLong(1));
                covo.setHeadersSurveySurveyCode(rs.getLong(2));
                covo.setHeadersSurveyBussinessUnitBuBisCode(rs.getLong(3));
                covo.setTypeRequest(rs.getInt(4));
                covo.setMainRequest(rs.getString(5));
                covo.setAnnexType(rs.getString(6));
                covo.setBodyAnnexDoc(rs.getString(7));
                covo.setBodyAnnexPhoto(rs.getString(8));
                covo.setComment(rs.getString(9));
                covo.setAnswerNumber(rs.getInt(10));
                covo.setSolutionNumber(rs.getInt(11));
                covo.setAuditableSolution(rs.getString(12));
                covo.setAuditableAnswerSolution(rs.getString(13));
                covo.setQuestionPoints(rs.getDouble(14));
                covo.setStatusRank(rs.getString(15));

                covo.setQuestionImageName(rs.getString(16));
                covo.setQuestionImageFile(rs.getBlob(17));

                /*int blobLength = (int) covo.getQuestionImageFile().length();
                byte[] blobAsBytes = rs.getBlob(17).getBytes(1, blobLength);
                covo.setQuestionImageFileByte(blobAsBytes);*/

                /*covo.setQuestionImageName(rs.getString(16));
                covo.setQuestionImageFile(rs.getBlob(17));
                int blobLength = (int) rs.getBlob(17).length();
                byte[] blobAsBytes = rs.getBlob(17).getBytes(1, blobLength);
                covo.setQuestionImageFileByte(blobAsBytes);*/

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

    public ArrayList<BodySurveyQuestionsVO> getListBodySurveyQuestions(String condi,Connection cone){
        ArrayList<BodySurveyQuestionsVO> arrcom = new ArrayList<BodySurveyQuestionsVO>();
        //cc = new OtherConexion();
        //cn = cc.conectarse(dataUser,dataPassword);
        sqls = new SqlFunctions();
        String sql = null;
        if(!condi.equals("")){
            sql = sqls.get_select("bodySurveyQuestions", "*",condi,"","","");
        }else{
            sql = sqls.get_select("bodySurveyQuestions", "*","","","","");
        }
        try{
            pst = cone.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
                covo = new BodySurveyQuestionsVO();
                covo.setQuestionCode(rs.getLong(1));
                covo.setHeadersSurveySurveyCode(rs.getLong(2));
                covo.setHeadersSurveyBussinessUnitBuBisCode(rs.getLong(3));
                covo.setTypeRequest(rs.getInt(4));
                covo.setMainRequest(rs.getString(5));
                covo.setAnnexType(rs.getString(6));
                covo.setBodyAnnexDoc(rs.getString(7));
                covo.setBodyAnnexPhoto(rs.getString(8));
                covo.setComment(rs.getString(9));
                covo.setAnswerNumber(rs.getInt(10));
                covo.setSolutionNumber(rs.getInt(11));
                covo.setAuditableSolution(rs.getString(12));
                covo.setAuditableAnswerSolution(rs.getString(13));
                covo.setQuestionPoints(rs.getDouble(14));
                covo.setStatusRank(rs.getString(15));

                covo.setQuestionImageName(rs.getString(16));
                covo.setQuestionImageFile(rs.getBlob(17));
                int blobLength = (int) rs.getBlob(17).length();
                byte[] blobAsBytes = rs.getBlob(17).getBytes(1, blobLength);
                covo.setQuestionImageFileByte(blobAsBytes);

                if (arrcom.isEmpty()){
                    arrcom.add(0,covo);
                }else{
                    arrcom.add(covo);
                }
            }
            System.out.println("Consulta array exitosa: ");
        }catch (SQLException ex){
            System.out.println("Error en la consulta array: "+ex.getMessage());
        }/*finally {
            try{
                if (cn != null){
                    cn.close();
                    pst.close();
                    System.out.println("Conexion cerrada");
                }
            }catch (Exception e){
                System.out.println("Error "+e);
            }
        }*/
        return arrcom;
    }

    public static void insertBodySurveyQuestionsDAO(BodySurveyQuestionsVO covo, Connection cone){
        FileInputStream fi = null;
        String sql = "INSERT INTO bodySurveyQuestions values (null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        covo.setResult(false);
        try{
            pst = cone.prepareStatement(sql);
            pst.setLong(1,covo.getHeadersSurveySurveyCode());
            pst.setLong(2,covo.getHeadersSurveyBussinessUnitBuBisCode());
            pst.setInt(3,covo.getTypeRequest());
            pst.setString(4,covo.getMainRequest());
            pst.setString(5,covo.getAnnexType());
            pst.setString(6,covo.getBodyAnnexDoc());
            pst.setString(7,covo.getBodyAnnexPhoto());
            pst.setString(8,covo.getComment());
            pst.setInt(9,covo.getAnswerNumber());
            pst.setInt(10,covo.getSolutionNumber());
            pst.setString(11,searchAuditableSolution(covo.getTypeRequest(),covo.getAnnexType()));
            pst.setString(12,"F");
            pst.setDouble(13,covo.getQuestionPoints());
            pst.setString(14,covo.getStatusRank());

            File file = new File(covo.getRuta_image());
            fi= new FileInputStream(file);
            pst.setString(15,covo.getQuestionImageName());
            pst.setBinaryStream(16,fi, (int) file.length());

            pst.executeUpdate();
            System.out.println("Operacion de Insert BodySurveyQuestions Exitosa.");
            covo.setResult(true);
        }catch (SQLException | FileNotFoundException ex){
            covo.setResult(false);
            System.out.println("sql:"+sql);
            System.out.println("Error en la consulta de insert BodySurveyQuestions: "+ex.getMessage());
        }
    }

    public static void insertBodySurveyQuestionsCopyDAO(BodySurveyQuestionsVO covo, Connection cone){
        String sql = "INSERT INTO bodySurveyQuestions values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        covo.setResult(false);
        try{
            pst = cone.prepareStatement(sql);
            pst.setLong(1,covo.getQuestionCode());
            pst.setLong(2,covo.getHeadersSurveySurveyCode());
            pst.setLong(3,covo.getHeadersSurveyBussinessUnitBuBisCode());
            pst.setInt(4,covo.getTypeRequest());
            pst.setString(5,covo.getMainRequest());
            pst.setString(6,covo.getAnnexType());
            pst.setString(7,covo.getBodyAnnexDoc());
            pst.setString(8,covo.getBodyAnnexPhoto());
            pst.setString(9,covo.getComment());
            pst.setInt(10,covo.getAnswerNumber());
            pst.setInt(11,covo.getSolutionNumber());
            pst.setString(12,searchAuditableSolution(covo.getTypeRequest(),covo.getAnnexType()));
            //pst.setString(11,covo.getAuditableSolution());
            pst.setString(13,covo.getAuditableAnswerSolution());
            pst.setDouble(14,covo.getQuestionPoints());
            pst.setString(15,covo.getStatusRank());

            pst.setString(16, covo.getQuestionImageName());
            pst.setBlob(17,covo.getQuestionImageFile());

            pst.executeUpdate();
            System.out.println("Operacion de Insert BodySurveyQuestions Exitosa.");
            covo.setResult(true);
        }catch (SQLException ex){
            covo.setResult(false);
            System.out.println("sql:"+sql);
            System.out.println("Error en la consulta de insert BodySurveyQuestions: "+ex.getMessage());
        }
    }

    public static Boolean deleteBodySurveyQuestionsDAO(Integer clave0, Integer clave1,Integer clave2,Connection cone){
        Boolean result = null;
        String sql = "DELETE FROM bodySurveyQuestions WHERE " +
                "question_code=? AND headersSurvey_survey_code=? AND headersSurvey_bussinessUnit_bu_bis_code=?";
        try{
            pst = cone.prepareStatement(sql);
            pst.setInt(1,clave0);
            pst.setInt(2,clave1);
            pst.setInt(3,clave2);
            pst.executeUpdate();
            System.out.println("bodySurveyQuestions "+clave0+" "+clave1+" "+clave2+" eliminado con exito");
            result = true;
        }catch (SQLException ex){
            result = false;
            System.out.println("Error en la eliminacion: "+ex.getMessage());
        }
        return result;
    }

    public static void updateBodySurveyQuestionsDAO(BodySurveyQuestionsVO cov, Connection cone) {
        String sql = "UPDATE bodySurveyQuestions SET main_request ='" +cov.getMainRequest() +
                "', Annex_type='" + cov.getAnnexType() +
                "', body_annex_doc='" + cov.getBodyAnnexDoc() +
                "', body_annex_photo='" + cov.getBodyAnnexPhoto() +
                "', comment ='" + cov.getComment() +
                "', auditable_solution ='" + searchAuditableSolution(cov.getTypeRequest(),cov.getAnnexType()) +
                "', question_points = " + cov.getQuestionPoints() +
                ", status_rank = '" + cov.getStatusRank() +
                "' WHERE (question_code = " + cov.getQuestionCode() +
                " AND headersSurvey_survey_code =" + cov.getHeadersSurveySurveyCode() +
                " AND headersSurvey_bussinessUnit_bu_bis_code =" +
                cov.getHeadersSurveyBussinessUnitBuBisCode() + ")";
        try{
            pst = cone.prepareStatement(sql);
            pst.execute();
            System.out.println("bodySurveyQuestions actualizado con exito, ID: "+ cov.getQuestionCode());
            cov.setResult(true);
        }catch (SQLException ex){
            System.out.println("Error en la actualizacion: "+ex.getMessage());
            System.out.println("sentencia: "+sql);
            cov.setResult(false);
        }
    }

    public static void updateHeadersSurveyCounterDAO(BodySurveyQuestionsVO cov,Integer answ, Integer solu, Connection cone) {
        String sql = "UPDATE bodySurveyQuestions SET " +
                "answer_number = answer_number + " + answ + ", solution_number = solution_number +" + solu +
                " WHERE (question_code = " + cov.getQuestionCode() +
                " AND headersSurvey_survey_code =" + cov.getHeadersSurveySurveyCode() +
                " AND headersSurvey_bussinessUnit_bu_bis_code =" + cov.getHeadersSurveyBussinessUnitBuBisCode() + ")";
        try{
            pst = cone.prepareStatement(sql);
            pst.execute();
            System.out.println("bodySurveyQuestions actualizado con exito, ID: "+ cov.getQuestionCode());
            cov.setResult(true);
        }catch (SQLException ex){
            System.out.println("Error en la actualizacion: "+ex.getMessage());
            System.out.println("sentencia: "+sql);
            cov.setResult(false);
        }
    }

    public static void updateHeadersSurveyCounterSolutionDAO(BodySurveyQuestionsVO cov,Integer solu, Connection cone) {
        String sql = "UPDATE bodySurveyQuestions SET solution_number = " + solu +
                " WHERE (question_code = " + cov.getQuestionCode() +
                " AND headersSurvey_survey_code =" + cov.getHeadersSurveySurveyCode() +
                " AND headersSurvey_bussinessUnit_bu_bis_code =" + cov.getHeadersSurveyBussinessUnitBuBisCode() + ")";
        try{
            pst = cone.prepareStatement(sql);
            pst.execute();
            System.out.println("bodySurveyQuestions actualizado con exito, ID: "+ cov.getQuestionCode());
            cov.setResult(true);
        }catch (SQLException ex){
            System.out.println("Error en la actualizacion: "+ex.getMessage());
            System.out.println("sentencia: "+sql);
            cov.setResult(false);
        }
    }

    public static void updateHeadersSurveyAuditableAnswerSolutionDAO(BodySurveyQuestionsVO cov,String solu, Connection cone) {
        String sql = "UPDATE bodySurveyQuestions SET " +
                "auditable_answer_solution = '" + solu +
                "', status_rank = '" + cov.getStatusRank() +
                "' WHERE (question_code = " + cov.getQuestionCode() +
                " AND headersSurvey_survey_code =" + cov.getHeadersSurveySurveyCode() +
                " AND headersSurvey_bussinessUnit_bu_bis_code =" + cov.getHeadersSurveyBussinessUnitBuBisCode() + ")";
        try{
            pst = cone.prepareStatement(sql);
            pst.execute();
            System.out.println("bodySurveyQuestions actualizado con exito, ID: "+ cov.getQuestionCode());
            cov.setResult(true);
        }catch (SQLException ex){
            System.out.println("Error en la actualizacion: "+ex.getMessage());
            System.out.println("sentencia: "+sql);
            cov.setResult(false);
        }
    }

    public static void updateBodySurveyQuestionsImage(BodySurveyQuestionsVO muv, Connection cone) {
        FileInputStream fi = null;
        String sql = "UPDATE bodySurveyQuestions SET " +
                "question_image_file=?, " +
                "question_image_name=?" +
                " WHERE (question_code=? AND headersSurvey_survey_code=? AND headersSurvey_bussinessUnit_bu_bis_code=?)";
        try{
            File file = new File(muv.getRuta_image());
            fi = new FileInputStream(file);
            pst = cone.prepareStatement(sql);
            pst.setBinaryStream(1,fi, (int) file.length());
            pst.setString(2,muv.getQuestionImageName());
            pst.setLong(3,muv.getQuestionCode());
            pst.setLong(4, muv.getHeadersSurveySurveyCode());
            pst.setLong(5, muv.getHeadersSurveyBussinessUnitBuBisCode());
            pst.execute();
            System.out.println("Image Question actualizada con exito, ID: "+muv.getQuestionCode()+
                    " , "+muv.getHeadersSurveySurveyCode() +
                    " , "+muv.getHeadersSurveyBussinessUnitBuBisCode());
            muv.setResult(true);
        }catch (SQLException | FileNotFoundException ex){
            System.out.println("Error en la actualizacion Imagen: "+ex.getMessage());
            muv.setResult(false);
        }
    }

    public static String searchAuditableSolution(Integer tipo,String annex){
        String as = null;
        Integer anexo = Integer.parseInt(annex);
         if (anexo != 0){
             as = "T";
         }else{
             if(tipo == 1 || tipo == 2 || tipo == 3 ||
                tipo == 6 || tipo == 7 || tipo == 8){
                as = "F";
             }else
                 as = "T";
        }
        return as;
    }

    public Long searchLastInsert(String condi,Connection cone){
        long count_id = Long.parseLong("0");
        sqls = new SqlFunctions();
        String sql = null;
        sql = sqls.get_select("bodySurveyQuestions", "question_code",condi,"","","");
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

    public BodySurveyQuestionsVO serchBodySurveyQuestionsDAO(String unidad, String survey, String question){
        BodySurveyQuestionsVO arrcom = new BodySurveyQuestionsVO();
        cc = new OtherConexion();
        cn = cc.conectarse(dataUser,dataPassword);
        sqls = new SqlFunctions();
        String sql = null,
                condi = "headersSurvey_survey_code =" + survey +
                        " AND headersSurvey_bussinessUnit_bu_bis_code = " + unidad +
                        " AND question_code = " + question;
        if(!condi.equals("")){
            sql = sqls.get_select("bodySurveyQuestions", "*",condi,"","","");
        }else{
            sql = sqls.get_select("bodySurveyQuestions", "*","","","","");
        }
        try{
            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
                arrcom.setQuestionCode(rs.getLong(1));
                arrcom.setHeadersSurveySurveyCode(rs.getLong(2));
                arrcom.setHeadersSurveyBussinessUnitBuBisCode(rs.getLong(3));
                arrcom.setTypeRequest(rs.getInt(4));
                arrcom.setMainRequest(rs.getString(5));
                arrcom.setAnnexType(rs.getString(6));
                arrcom.setBodyAnnexDoc(rs.getString(7));
                arrcom.setBodyAnnexPhoto(rs.getString(8));
                arrcom.setComment(rs.getString(9));
                arrcom.setAnswerNumber(rs.getInt(10));
                arrcom.setSolutionNumber(rs.getInt(11));
                arrcom.setAuditableSolution(rs.getString(12));
                arrcom.setAuditableAnswerSolution(rs.getString(13));
                arrcom.setQuestionPoints(rs.getDouble(14));
                arrcom.setStatusRank(rs.getString(15));

                arrcom.setQuestionImageName(rs.getString(16));
                arrcom.setQuestionImageFile(rs.getBlob(17));
                int blobLength = (int) rs.getBlob(17).length();
                byte[] blobAsBytes = rs.getBlob(17).getBytes(1, blobLength);
                arrcom.setQuestionImageFileByte(blobAsBytes);

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

    /*public static void updateHeadersSurveyImage(HeadersSurveyVO muv, Connection cone) {
        FileInputStream fi = null;
        String sql = "UPDATE masterUser SET " +
                "mu_photo=?, " +
                "mu_photo_name=?" +
                " WHERE (mu_email=? AND bussinessUnit_bu_bis_code=?)";
        try{
            File file = new File(muv.getRuta_imagen());
            fi = new FileInputStream(file);
            pst = cone.prepareStatement(sql);
            pst.setBinaryStream(1,fi, (int) file.length());
            pst.setString(2,muv.getMuPhotoName());
            pst.setString(3,muv.getMuEmail());
            pst.setLong(4, muv.getBussinessUnitBuBisCode());
            pst.execute();
            System.out.println("Image Master User actualizada con exito, ID: "+muv.getMuEmail()+
                    " , "+muv.getBussinessUnitBuBisCode());
            muv.setResult(true);
        }catch (SQLException | FileNotFoundException ex){
            System.out.println("Error en la actualizacion Imagen: "+ex.getMessage());
            muv.setResult(false);
        }
    }*/

}
