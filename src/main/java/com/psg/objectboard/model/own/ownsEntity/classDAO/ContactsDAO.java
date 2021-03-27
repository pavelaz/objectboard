package com.psg.objectboard.model.own.ownsEntity.classDAO;

import com.psg.objectboard.model.own.ownsEntity.classVO.CommentsVO;
import com.psg.objectboard.model.own.ownsEntity.classVO.ContactsVO;
import com.psg.objectboard.model.service.Other.OtherConexion;
import com.psg.objectboard.model.service.Other.SqlFunctions;

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
}
