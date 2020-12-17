package com.psg.objectboard.model.own.ownsEntity.classDAO.OtherDAO;

import com.psg.objectboard.model.service.Other.OtherConexion;
import com.psg.objectboard.model.service.Other.SqlFunctions;
import com.psg.objectboard.model.own.ownsEntity.classVO.OtherVO.SurveyTotalViewVO;

import java.sql.*;
import java.util.ArrayList;

public class SurveyTotalViewDAO {
    private static OtherConexion cc = null;
    private static SurveyTotalViewVO covo = null;
    private static Connection cn = null;
    private static SqlFunctions sqls = null;
    private static ResultSet rs = null;
    private static Statement cmd = null;
    private static PreparedStatement pst = null;
    private static String dataUser;
    private static String dataPassword;

    public void setDataUser(String dataUser) {
        SurveyTotalViewDAO.dataUser = dataUser;
    }

    public  void setDataPassword(String dataPassword) {
        SurveyTotalViewDAO.dataPassword = dataPassword;
    }

    public ArrayList<SurveyTotalViewVO> getListSurveyTotalView(String condi){
        ArrayList<SurveyTotalViewVO> arrcom = new ArrayList<>();
        cc = new OtherConexion();
        cn = cc.conectarse(dataUser,dataPassword);
        sqls = new SqlFunctions();
        String sql = null;
        if(!condi.equals("")){
            sql = sqls.get_select("SurveyTotalView", "*",condi,"","","");
        }else{
            sql = sqls.get_select("SurveyTotalView", "*","","","","");
        }
        try{
            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
                covo = new SurveyTotalViewVO();
                covo.setSurvey_code(rs.getLong(1));
                covo.setBussinessUnit_code(rs.getLong(2));
                covo.setSurvey_name(rs.getString(3));
                covo.setHs_references(rs.getString(4));
                covo.setTotal_questions(rs.getInt(5));
                covo.setSurveyStatus(rs.getString(6));
                covo.setExecution_status(rs.getString(7));
                covo.setVersion(rs.getInt(8));
                covo.setDate_creation(rs.getString(9));
                covo.setTypified_ctypified_code1(rs.getString(10));
                covo.setTypified_ctypified_code2(rs.getString(11));
                covo.setTypified_ctypified_code3(rs.getString(12));
                covo.setOrganization_level1(rs.getString(13));
                covo.setOrganization_level2(rs.getString(14));
                covo.setOrganization_level3(rs.getString(15));
                covo.setOrganization_level4(rs.getString(16));
                covo.setDate_last_modification(rs.getString(17));
                covo.setAudited(rs.getString(18));
                covo.setQuestion_code(rs.getLong(19));
                covo.setType_request(rs.getInt(20));
                covo.setMain_request(rs.getString(21));
                covo.setAnnex_type(rs.getString(22));
                covo.setBody_annex_doc(rs.getString(23));
                covo.setBody_annex_photo(rs.getString(24));
                covo.setComment(rs.getString(25));
                covo.setAnswer_number(rs.getInt(26));
                covo.setSolution_number(rs.getInt(27));
                covo.setAuditable_question_solution(rs.getString(28));
                covo.setAuditable_inquestion_answer_solution(rs.getString(29));
                covo.setAnswer_code(rs.getLong(30));
                covo.setAnswer(rs.getString(31));
                covo.setAnswer_solution(rs.getString(32));
                covo.setAuditable_answer_solution(rs.getString(33));
                covo.setAnswer_only_text(rs.getString(34));
                covo.setAnswer_only_number(rs.getDouble(35));
                covo.setAnswer_only_date(rs.getString(36));
                covo.setAnswer_only_time(rs.getString(37));
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
