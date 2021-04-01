package com.psg.objectboard.model.own.ownsEntity.classDAO;

import com.psg.objectboard.model.own.ownsEntity.classVO.CommentsVO;
import com.psg.objectboard.model.own.ownsEntity.classVO.ContactsVO;
import com.psg.objectboard.model.own.ownsEntity.classVO.HeadersSurveyVO;
import com.psg.objectboard.model.service.Other.OtherConexion;
import com.psg.objectboard.model.service.Other.SqlFunctions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;

public class ContactsDAO {
    private static OtherConexion cc = null;
    private static ContactsVO covo = null;
    private static Connection cn = null;
    private static SqlFunctions sqls = null;
    private static ResultSet rs = null;
    private static Statement cmd = null;
    private static PreparedStatement pst = null;
    private static String dataUser;
    private static String dataPassword;

    public void setDataUser(String dataUser) {
        ContactsDAO.dataUser = dataUser;
    }

    public void setDataPassword(String dataPassword) {
        ContactsDAO.dataPassword = dataPassword;
    }

    public ArrayList<ContactsVO> getListContacts(String condi) {
        ArrayList<ContactsVO> arrcom = new ArrayList<>();
        cc = new OtherConexion();
        cn = cc.conectarse(dataUser,dataPassword);
        sqls = new SqlFunctions();
        String sql = null;
        if(!condi.equals("")){
            sql = sqls.get_select("contacts", "*",condi,"","","");
        }else{
            sql = sqls.get_select("contacts", "*","","","","");
        }
        try{
            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
                covo = new ContactsVO();
                covo.setMasterUser_bussinessUnit_bu_bis_code(rs.getLong(1));
                covo.setMasterUser_mu_email(rs.getString(2));
                covo.setCto_project(rs.getInt(3));
                covo.setCto_email_direction(rs.getString(4));
                covo.setCto_name(rs.getString(5));
                covo.setCto_business_name(rs.getString(6));
                covo.setCto_address_1(rs.getString(7));
                covo.setCto_address_2(rs.getString(8));
                covo.setCto_phone_cell(rs.getString(9));
                covo.setCto_landline(rs.getString(10));
                covo.setCto_email_message(rs.getString(11));
                covo.setCto_sms_message(rs.getString(12));
                covo.setCto_front_yard(rs.getString(13));
                covo.setCto_back_yard(rs.getString(14));
                covo.setCto_type(rs.getString(15));
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

    public static void insertContactsDAO(ContactsVO covo, Connection cone){
        String sql = "INSERT INTO contacts values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        covo.setResult(false);
        try{
            pst = cone.prepareStatement(sql);
            pst.setLong(1,covo.getMasterUser_bussinessUnit_bu_bis_code());
            pst.setString(2,covo.getMasterUser_mu_email());
            pst.setInt(3,covo.getCto_project());
            pst.setString(4,covo.getCto_email_direction());
            pst.setString(5,covo.getCto_name());
            pst.setString(6,covo.getCto_business_name());
            pst.setString(7,covo.getCto_address_1());
            pst.setString(8,covo.getCto_address_2());
            pst.setString(9,covo.getCto_phone_cell());
            pst.setString(10,covo.getCto_landline());
            pst.setString(11,covo.getCto_email_message());
            pst.setString(12,covo.getCto_sms_message());
            pst.setString(13,covo.getCto_front_yard());
            pst.setString(14,covo.getCto_back_yard());
            pst.setString(15,covo.getCto_type());
            pst.executeUpdate();
            System.out.println("Operacion de Insert Contacts Exitosa.");
            covo.setResult(true);
        }catch (SQLException ex){
            covo.setResult(false);
            System.out.println("Error en la consulta de insert Contacts: "+ex.getMessage());
        }
    }

    public static void updateContactsDAO(ContactsVO muv, Connection cone) {
        String sql = "UPDATE contacts SET " +
                "cto_name=?, " +
                "cto_business_name=?, " +
                "cto_address_1=?, " +
                "cto_address_2=?, " +
                "cto_phone_cell=?, " +
                "cto_landline=?, " +
                "cto_email_message=?, " +
                "cto_sms_message=?, " +
                "cto_front_yard=?, " +
                "cto_back_yard=?, " +
                "cto_type=?" +
                " WHERE (masterUser_bussinessUnit_bu_bis_code=? AND masterUser_mu_email=? AND cto_project=? AND cto_email_direction=?)";
        try{
            pst = cone.prepareStatement(sql);
            pst.setString(1,muv.getCto_name());
            pst.setString(2,muv.getCto_business_name());
            pst.setString(3,muv.getCto_address_1());
            pst.setString(4, muv.getCto_address_2());
            pst.setString(5,muv.getCto_phone_cell());
            pst.setString(6, muv.getCto_landline());
            pst.setString(7,muv.getCto_email_message());
            pst.setString(8,muv.getCto_sms_message());
            pst.setString(9, muv.getCto_front_yard());
            pst.setString(10,muv.getCto_back_yard());
            pst.setString(11, muv.getCto_type());
            pst.setLong(12, muv.getMasterUser_bussinessUnit_bu_bis_code());
            pst.setString(13, muv.getMasterUser_mu_email());
            pst.setInt(14, muv.getCto_project());
            pst.setString(15, muv.getCto_email_direction());
            pst.execute();
            System.out.println("contacts actualizada con exito, ID: "+muv.getCto_email_direction());
            muv.setResult(true);
        }catch (SQLException ex){
            System.out.println("Error en la actualizacion contacts: "+ex.getMessage());
            muv.setResult(false);
        }
    }

    public static Boolean deleteContactsDAO(ContactsVO muv,Connection cone){
        Boolean result = null;
        String sql = "DELETE FROM contacts" +
                " WHERE (masterUser_bussinessUnit_bu_bis_code=? AND masterUser_mu_email=? AND cto_project=? AND cto_email_direction=?)";
        try{
            pst = cone.prepareStatement(sql);
            pst.setLong(1, muv.getMasterUser_bussinessUnit_bu_bis_code());
            pst.setString(2, muv.getMasterUser_mu_email());
            pst.setInt(3, muv.getCto_project());
            pst.setString(4, muv.getCto_email_direction());
            pst.executeUpdate();
            System.out.println("contacts "+ muv.getCto_email_direction()+" eliminado con exito");
            result = true;
        }catch (SQLException ex){
            result = false;
            System.out.println("Error en la eliminacion: "+ex.getMessage());
        }
        return result;
    }
}
