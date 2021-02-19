package com.psg.objectboard.model.own.ownsEntity.classDAO;

import com.psg.objectboard.model.own.ownsEntity.classVO.MasterUserVO;
import com.psg.objectboard.model.service.Other.DateFunctions;
import com.psg.objectboard.model.service.Other.OtherConexion;
import com.psg.objectboard.model.service.Other.SqlFunctions;
import com.psg.objectboard.model.own.ownsEntity.classVO.HeadersSurveyVO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;

public class HeadersSurveyDAO {
    private static OtherConexion cc = null;
    private static HeadersSurveyVO covo = null;
    private static Connection cn = null;
    private static SqlFunctions sqls = null;
    private static ResultSet rs = null;
    private static Statement cmd = null;
    private static PreparedStatement pst = null;
    private static String dataUser;
    private static String dataPassword;

    public static void setDataUser(String dataUser) {
        HeadersSurveyDAO.dataUser = dataUser;
    }

    public static void setDataPassword(String dataPassword) {
        HeadersSurveyDAO.dataPassword = dataPassword;
    }

    public ArrayList<HeadersSurveyVO> getListHeadersSurvey(String condi){
        ArrayList<HeadersSurveyVO> arrcom = new ArrayList<HeadersSurveyVO>();
        cc = new OtherConexion();
        cn = cc.conectarse(dataUser,dataPassword);
        sqls = new SqlFunctions();
        String sql = null;
        if(!condi.equals("")){
            sql = sqls.get_select("headersSurvey", "*",condi,"","","");
        }else{
            sql = sqls.get_select("headersSurvey", "*","","","","");
        }
        try{
            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
                covo = new HeadersSurveyVO();
                covo.setSurveyCode(rs.getLong(1));
                covo.setBussinessUnitBuBisCode(rs.getLong(2));
                covo.setName(rs.getString(3));
                covo.setReferences(rs.getString(4));
                covo.setTotalQuestions(rs.getInt(5));
                covo.setSurveyStatus(rs.getString(6));
                covo.setExecutionStatus(rs.getString(7));
                covo.setVersion(rs.getInt(8));
                covo.setDateCreation(rs.getString(9));
                covo.setTypifiedBussinessUnitBuBisCode(rs.getLong(10));
                covo.setTypifiedCtypifiedCode1(rs.getString(11));
                covo.setTypifiedCtypifiedCode2(rs.getString(12));
                covo.setTypifiedCtypifiedCode3(rs.getString(13));
                covo.setOrganizationBussinessUnitBuBisCode(rs.getLong(14));
                covo.setOrganizationLevel1(rs.getString(15));
                covo.setOrganizationLevel2(rs.getString(16));
                covo.setOrganizationLevel3(rs.getString(17));
                covo.setOrganizationLevel4(rs.getString(18));
                covo.setDateLastModification(rs.getString(19));
                covo.setAudited(rs.getString(20));
                covo.setTotalPoints(rs.getDouble(21));

                covo.setSurveyImageName(rs.getString(22));
                covo.setSurveyImageFile(rs.getBlob(23));
                int blobLength = (int) rs.getBlob(23).length();
                byte[] blobAsBytes = rs.getBlob(23).getBytes(1, blobLength);
                covo.setSurveyImageFileByte(blobAsBytes);

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

    public ArrayList<HeadersSurveyVO> getListHeadersSurvey(String condi,Connection cone){
        ArrayList<HeadersSurveyVO> arrcom = new ArrayList<HeadersSurveyVO>();
        //cc = new OtherConexion();
        //cn = cc.conectarse(dataUser,dataPassword);
        sqls = new SqlFunctions();
        String sql = null;
        if(!condi.equals("")){
            sql = sqls.get_select("headersSurvey", "*",condi,"","","");
        }else{
            sql = sqls.get_select("headersSurvey", "*","","","","");
        }
        try{
            pst = cone.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
                covo = new HeadersSurveyVO();
                covo.setSurveyCode(rs.getLong(1));
                covo.setBussinessUnitBuBisCode(rs.getLong(2));
                covo.setName(rs.getString(3));
                covo.setReferences(rs.getString(4));
                covo.setTotalQuestions(rs.getInt(5));
                covo.setSurveyStatus(rs.getString(6));
                covo.setExecutionStatus(rs.getString(7));
                covo.setVersion(rs.getInt(8));
                covo.setDateCreation(rs.getString(9));
                covo.setTypifiedBussinessUnitBuBisCode(rs.getLong(10));
                covo.setTypifiedCtypifiedCode1(rs.getString(11));
                covo.setTypifiedCtypifiedCode2(rs.getString(12));
                covo.setTypifiedCtypifiedCode3(rs.getString(13));
                covo.setOrganizationBussinessUnitBuBisCode(rs.getLong(14));
                covo.setOrganizationLevel1(rs.getString(15));
                covo.setOrganizationLevel2(rs.getString(16));
                covo.setOrganizationLevel3(rs.getString(17));
                covo.setOrganizationLevel4(rs.getString(18));
                covo.setDateLastModification(rs.getString(19));
                covo.setAudited(rs.getString(20));
                covo.setTotalPoints(rs.getDouble(21));

                covo.setSurveyImageName(rs.getString(22));
                covo.setSurveyImageFile(rs.getBlob(23));
                int blobLength = (int) rs.getBlob(23).length();
                byte[] blobAsBytes = rs.getBlob(23).getBytes(1, blobLength);
                covo.setSurveyImageFileByte(blobAsBytes);

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

    public static void insertHeadersSurveyDAO(HeadersSurveyVO covo, Connection cone){
        FileInputStream fi = null;
        String sql = "INSERT INTO headersSurvey values (null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        covo.setResult(false);
        try{
            pst = cone.prepareStatement(sql);
            pst.setLong(1,covo.getBussinessUnitBuBisCode());
            pst.setString(2,covo.getName());
            pst.setString(3,covo.getReferences());
            pst.setInt(4,covo.getTotalQuestions());
            pst.setString(5,covo.getSurveyStatus());
            pst.setString(6,covo.getExecutionStatus());
            pst.setInt(7,covo.getVersion());
            pst.setString(8,covo.getDateCreation());
            pst.setLong(9,covo.getTypifiedBussinessUnitBuBisCode());
            pst.setString(10,covo.getTypifiedCtypifiedCode1());
            pst.setString(11,covo.getTypifiedCtypifiedCode2());
            pst.setString(12,covo.getTypifiedCtypifiedCode3());
            pst.setLong(13,covo.getOrganizationBussinessUnitBuBisCode());
            pst.setString(14,covo.getOrganizationLevel1());
            pst.setString(15,covo.getOrganizationLevel2());
            pst.setString(16,covo.getOrganizationLevel3());
            pst.setString(17,covo.getOrganizationLevel4());
            pst.setString(18,covo.getDateLastModification());
            pst.setString(19,covo.getAudited());
            pst.setDouble(20,covo.getTotalPoints());

            File file = new File(covo.getRuta_image());
            fi= new FileInputStream(file);
            pst.setString(21,covo.getSurveyImageName());
            pst.setBinaryStream(22,fi, (int) file.length());

            pst.executeUpdate();
            System.out.println("Operacion de Insert headersSurvey Exitosa.");
            covo.setResult(true);
        }catch (SQLException | FileNotFoundException ex){
            covo.setResult(false);
            System.out.println("Error en la consulta de insert headersSurvey: "+ex.getMessage());
        }
    }

    public static void insertHeadersSurveyCopyDAO(HeadersSurveyVO covo, Connection cone){
        //FileInputStream fi = null;
        String sql = "INSERT INTO headersSurvey values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        covo.setResult(false);
        try{
            pst = cone.prepareStatement(sql);
            pst.setLong(1,covo.getSurveyCode());
            pst.setLong(2,covo.getBussinessUnitBuBisCode());
            pst.setString(3,covo.getName());
            pst.setString(4,covo.getReferences());
            pst.setInt(5,covo.getTotalQuestions());
            pst.setString(6,covo.getSurveyStatus());
            pst.setString(7,covo.getExecutionStatus());
            pst.setInt(8,covo.getVersion());
            pst.setString(9,covo.getDateCreation());
            pst.setLong(10,covo.getTypifiedBussinessUnitBuBisCode());
            pst.setString(11,covo.getTypifiedCtypifiedCode1());
            pst.setString(12,covo.getTypifiedCtypifiedCode2());
            pst.setString(13,covo.getTypifiedCtypifiedCode3());
            pst.setLong(14,covo.getOrganizationBussinessUnitBuBisCode());
            pst.setString(15,covo.getOrganizationLevel1());
            pst.setString(16,covo.getOrganizationLevel2());
            pst.setString(17,covo.getOrganizationLevel3());
            pst.setString(18,covo.getOrganizationLevel4());
            pst.setString(19,covo.getDateLastModification());
            pst.setString(20,covo.getAudited());
            pst.setDouble(21,covo.getTotalPoints());

            //File file = new File(covo.getRuta_image());
            //fi= new FileInputStream(file);
            pst.setString(22,covo.getSurveyImageName());
            pst.setBlob(23,covo.getSurveyImageFile());
            //pst.setBinaryStream(23,fi, (int) file.length());

            pst.executeUpdate();
            System.out.println("Operacion de Insert headersSurvey Exitosa.");
            covo.setResult(true);
        }catch (SQLException ex){
            covo.setResult(false);
            System.out.println("Error en la consulta de insert headersSurvey: "+ex.getMessage());
        }
    }

    public static Boolean deleteHeadersSurveyDAO(Integer clave0, Integer clave1,Connection cone){
        Boolean result = null;
        String sql = "DELETE FROM headersSurvey WHERE " +
                "bussinessUnit_bu_bis_code=? AND survey_code=?";
        try{
            pst = cone.prepareStatement(sql);
            pst.setInt(1,clave0);
            pst.setInt(2,clave1);
            pst.executeUpdate();
            System.out.println("headersSurvey "+clave0+" "+clave1+" eliminado con exito");
            result = true;
        }catch (SQLException ex){
            result = false;
            System.out.println("Error en la eliminacion: "+ex.getMessage());
        }
        return result;
    }

    public static void updateHeadersSurveyDAO(HeadersSurveyVO cov, Connection cone) {
        String sql = "UPDATE headersSurvey SET hs_name='" +cov.getName() +
                "', hs_references='" + cov.getReferences() +
                "', typified_ctypified_code1='" + cov.getTypifiedCtypifiedCode1() +
                "', typified_ctypified_code2='" + cov.getTypifiedCtypifiedCode2() +
                "', typified_ctypified_code3='" + cov.getTypifiedCtypifiedCode3() +
                "', organization_level1='" + cov.getOrganizationLevel1() +
                "', organization_level2='" + cov.getOrganizationLevel2() +
                "', organization_level3='" + cov.getOrganizationLevel3() +
                "', organization_level4='" + cov.getOrganizationLevel4() +
                "', date_last_modification='" + cov.getDateLastModification() +
                "' WHERE (survey_code=" + cov.getSurveyCode() +
                " AND bussinessUnit_bu_bis_code=" + cov.getBussinessUnitBuBisCode() + ")";
        try{
            pst = cone.prepareStatement(sql);
            pst.execute();
            System.out.println("HeadersSurvey actualizado con exito, ID: "+ cov.getName());
            cov.setResult(true);
        }catch (SQLException ex){
            System.out.println("Error en la actualizacion: "+ex.getMessage());
            System.out.println("sentencia: "+sql);
            cov.setResult(false);
        }
    }

    public static void updateHeadersSurveyImage(HeadersSurveyVO muv, Connection cone) {
        FileInputStream fi = null;
        String sql = "UPDATE headersSurvey SET " +
                "survey_image_file=?, " +
                "survey_image_name=?" +
                " WHERE (survey_code=? AND bussinessUnit_bu_bis_code=?)";
        try{
            File file = new File(muv.getRuta_image());
            fi = new FileInputStream(file);
            pst = cone.prepareStatement(sql);
            pst.setBinaryStream(1,fi, (int) file.length());
            pst.setString(2,muv.getSurveyImageName());
            pst.setLong(3,muv.getSurveyCode());
            pst.setLong(4, muv.getBussinessUnitBuBisCode());
            pst.execute();
            System.out.println("Image Survey actualizada con exito, ID: "+muv.getSurveyCode()+
                    " , "+muv.getBussinessUnitBuBisCode());
            muv.setResult(true);
        }catch (SQLException | FileNotFoundException ex){
            System.out.println("Error en la actualizacion Imagen: "+ex.getMessage());
            muv.setResult(false);
        }
    }

    public static void updateHeadersSurveyCounterDateDAO(HeadersSurveyVO cov,Integer registros, Connection cone) {
        DateFunctions df = new DateFunctions();
        String fecha = null;
        fecha = df.fechaFull(9);

        String sql = "UPDATE headersSurvey SET total_questions=total_questions + " + registros +
                ", date_last_modification='" + fecha +
                "' WHERE (survey_code=" + cov.getSurveyCode() +
                " AND bussinessUnit_bu_bis_code=" + cov.getBussinessUnitBuBisCode() + ")";
        try{
            pst = cone.prepareStatement(sql);
            pst.execute();
            System.out.println("HeadersSurvey actualizado con exito, ID: "+ cov.getName());
            cov.setResult(true);
        }catch (SQLException ex){
            System.out.println("Error en la actualizacion: "+ex.getMessage());
            System.out.println("sentencia: "+sql);
            cov.setResult(false);
        }
    }

    public static void updateCloseOpenHeadersSurveyDAO(HeadersSurveyVO cov, Connection cone) {
        String sql = "UPDATE headersSurvey SET surveyStatus ='" +cov.getSurveyStatus() +
                "' WHERE (survey_code=" + cov.getSurveyCode() +
                " AND bussinessUnit_bu_bis_code=" + cov.getBussinessUnitBuBisCode() + ")";
        try{
            pst = cone.prepareStatement(sql);
            pst.execute();
            System.out.println("HeadersSurvey actualizado con exito, ID: "+ cov.getName());
            cov.setResult(true);
        }catch (SQLException ex){
            System.out.println("Error en la actualizacion: "+ex.getMessage());
            System.out.println("sentencia: "+sql);
            cov.setResult(false);
        }
    }

    public static void updateAuditedHeadersSurveyDAO(HeadersSurveyVO cov, Connection cone) {
        String sql = "UPDATE headersSurvey SET audited ='" + cov.getAudited() +
                "' WHERE (survey_code=" + cov.getSurveyCode() +
                " AND bussinessUnit_bu_bis_code=" + cov.getBussinessUnitBuBisCode() + ")";
        try{
            pst = cone.prepareStatement(sql);
            pst.execute();
            System.out.println("HeadersSurvey actualizado con exito, ID: " + cov.getName());
            cov.setResult(true);
        }catch (SQLException ex){
            System.out.println("Error en la actualizacion: " + ex.getMessage());
            System.out.println("sentencia: " + sql);
            cov.setResult(false);
        }
    }

    public static Long searchNextHeaderSurveyCode(Connection cone, Integer company) {
        Integer contador = 1;
        Boolean pasa = false;
        try{
            while (!pasa) {
                String sql = "SELECT * FROM headersSurvey WHERE (bussinessUnit_bu_bis_code=" + company + " AND survey_code = " + contador + ")";
                pst = cone.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs.next()) {
                    contador = contador + 1;
                }else{
                    pasa = true;
                }
            }
            System.out.println("HeadersSurvey buscado con exito, ID: "+ contador);
        }catch (SQLException ex){
            System.out.println("Error en la actualizacion: "+ex.getMessage());
        }
        return Long.parseLong(String.valueOf(contador));
    }

    public static void updateExcecutionHeadersSurveyDAO(HeadersSurveyVO cov, Connection cone) {
        String sql = "UPDATE headersSurvey SET execution_status ='" + cov.getExecutionStatus() +
                "' WHERE (survey_code=" + cov.getSurveyCode() +
                " AND bussinessUnit_bu_bis_code=" + cov.getBussinessUnitBuBisCode() + ")";
        try{
            pst = cone.prepareStatement(sql);
            pst.execute();
            System.out.println("HeadersSurvey actualizado con exito, ID: " + cov.getSurveyCode());
            cov.setResult(true);
        }catch (SQLException ex){
            System.out.println("Error en la actualizacion: " + ex.getMessage());
            System.out.println("sentencia: " + sql);
            cov.setResult(false);
        }
    }

    public void updatePointsHeadersSurveyDAO(HeadersSurveyVO cov, Connection cone,String operacion) {
        String sql = "UPDATE headersSurvey SET total_points = total_points " + operacion + " " + cov.getTotalPoints() +
                " WHERE (survey_code=" + cov.getSurveyCode() +
                " AND bussinessUnit_bu_bis_code=" + cov.getBussinessUnitBuBisCode() + ")";
        try{
            pst = cone.prepareStatement(sql);
            pst.execute();
            System.out.println("HeadersSurvey actualizado con exito, ID: "+ cov.getName());
            cov.setResult(true);
        }catch (SQLException ex){
            System.out.println("Error en la actualizacion: "+ex.getMessage());
            System.out.println("sentencia: "+sql);
            cov.setResult(false);
        }
    }

    public HeadersSurveyVO serchHeadersSurveyrDAO(String unidad, String survey){
        HeadersSurveyVO arrcom = new HeadersSurveyVO();
        cc = new OtherConexion();
        cn = cc.conectarse(dataUser,dataPassword);
        sqls = new SqlFunctions();
        String sql = null,
               condi = "survey_code =" + survey + " AND bussinessUnit_bu_bis_code =" + unidad;
        if(!condi.equals("")){
            sql = sqls.get_select("headersSurvey", "*",condi,"","","");
        }else{
            sql = sqls.get_select("headersSurvey", "*","","","","");
        }
        try{
            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
                //covo = new HeadersSurveyVO();
                arrcom.setSurveyCode(rs.getLong(1));
                arrcom.setBussinessUnitBuBisCode(rs.getLong(2));
                arrcom.setName(rs.getString(3));
                arrcom.setReferences(rs.getString(4));
                arrcom.setTotalQuestions(rs.getInt(5));
                arrcom.setSurveyStatus(rs.getString(6));
                arrcom.setExecutionStatus(rs.getString(7));
                arrcom.setVersion(rs.getInt(8));
                arrcom.setDateCreation(rs.getString(9));
                arrcom.setTypifiedBussinessUnitBuBisCode(rs.getLong(10));
                arrcom.setTypifiedCtypifiedCode1(rs.getString(11));
                arrcom.setTypifiedCtypifiedCode2(rs.getString(12));
                arrcom.setTypifiedCtypifiedCode3(rs.getString(13));
                arrcom.setOrganizationBussinessUnitBuBisCode(rs.getLong(14));
                arrcom.setOrganizationLevel1(rs.getString(15));
                arrcom.setOrganizationLevel2(rs.getString(16));
                arrcom.setOrganizationLevel3(rs.getString(17));
                arrcom.setOrganizationLevel4(rs.getString(18));
                arrcom.setDateLastModification(rs.getString(19));
                arrcom.setAudited(rs.getString(20));
                arrcom.setTotalPoints(rs.getDouble(21));

                arrcom.setSurveyImageName(rs.getString(22));
                arrcom.setSurveyImageFile(rs.getBlob(23));
                int blobLength = (int) rs.getBlob(23).length();
                byte[] blobAsBytes = rs.getBlob(23).getBytes(1, blobLength);
                arrcom.setSurveyImageFileByte(blobAsBytes);

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
