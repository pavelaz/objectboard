package com.psg.objectboard.model.own.ownsEntity.classDAO;

import com.psg.objectboard.model.own.ownsEntity.classVO.ContactusVO;
import com.psg.objectboard.model.service.Other.OtherConexion;
import com.psg.objectboard.model.service.Other.SqlFunctions;

import java.sql.*;

public class ContactusDAO {
    //private static OtherConexion cc = null;
    private static ContactusVO sovo = null;
    //private static Connection cn = null;
    //private static SqlFunctions sqls = null;
    //private static ResultSet rs = null;
    //private static Statement cmd = null;
    private static PreparedStatement pst = null;
    //private static String dataUser;
    //private static String dataPassword;

    /*public static void setDataUser(String dataUser) {
        ContactusDAO.dataUser = dataUser;
    }

    public static void setDataPassword(String dataPassword) {
        ContactusDAO.dataPassword = dataPassword;
    }*/

    public void insertContactusDAO(ContactusVO sovo, Connection cone){
        String sql = "INSERT INTO contactus values (null,?,?,?,?,?)";
        sovo.setResult(false);
        try{
            pst = cone.prepareStatement(sql);
            pst.setString(1,sovo.getCtNombre());
            pst.setString(2,sovo.getCtPhone());
            pst.setString(3,sovo.getCtDate());
            pst.setString(4,sovo.getCtMessage());
            pst.setString(5,sovo.getCtEmail());
            pst.executeUpdate();
            System.out.println("Operacion de Insert contactus Exitosa.");
            sovo.setResult(true);
        }catch (SQLException ex){
            sovo.setResult(false);
            System.out.println("Error en la consulta de insert contactus: "+ex.getMessage());
        }
    }
}
