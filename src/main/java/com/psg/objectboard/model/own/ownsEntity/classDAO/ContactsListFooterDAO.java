package com.psg.objectboard.model.own.ownsEntity.classDAO;

import com.psg.objectboard.model.own.ownsEntity.classVO.ContactsListFooterVO;
import com.psg.objectboard.model.own.ownsEntity.classVO.ContactsListHeaderVO;
import com.psg.objectboard.model.service.Other.OtherConexion;
import com.psg.objectboard.model.service.Other.SqlFunctions;

import java.sql.*;
import java.util.ArrayList;

public class ContactsListFooterDAO {
    private static OtherConexion cc = null;
    private static ContactsListFooterVO covo = null;
    private static Connection cn = null;
    private static SqlFunctions sqls = null;
    private static ResultSet rs = null;
    private static Statement cmd = null;
    private static PreparedStatement pst = null;
    private static String dataUser;
    private static String dataPassword;

    public void setDataUser(String dataUser) {
        ContactsListFooterDAO.dataUser = dataUser;
    }

    public void setDataPassword(String dataPassword) {
        ContactsListFooterDAO.dataPassword = dataPassword;
    }

    public ArrayList<ContactsListFooterVO> getListContactsListFooterDAO(String condi) {
        ArrayList<ContactsListFooterVO> arrcom = new ArrayList<>();
        cc = new OtherConexion();
        cn = cc.conectarse(dataUser,dataPassword);
        sqls = new SqlFunctions();
        String sql = null;
        if(!condi.equals("")){
            sql = sqls.get_select("contacts_list_footer", "*",condi,"","","");
        }else{
            sql = sqls.get_select("contacts_list_footer", "*","","","","");
        }
        try{
            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
                covo = new ContactsListFooterVO();
                covo.setContacts_list_header_list_id(rs.getInt(1));
                covo.setList_email_direcction(rs.getString(2));
                if (arrcom.isEmpty()){
                    arrcom.add(0,covo);
                }else{
                    arrcom.add(covo);
                }
            }
            System.out.println("Consulta array contacts_list_footer exitosa: ");
        }catch (SQLException ex){
            System.out.println("Error en la consulta array contacts_list_footer: "+ex.getMessage());
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

    public Boolean insertContactsListFooterDAO(ContactsListFooterVO covo, Connection cone){
        String sql = "INSERT INTO contacts_list_footer values (?,?)";
        covo.setResult(false);
        try{
            pst = cone.prepareStatement(sql);
            pst.setLong(1,covo.getContacts_list_header_list_id());
            pst.setString(2,covo.getList_email_direcction());
            pst.executeUpdate();
            System.out.println("Operacion de Insert Contacts List Exitosa.");
            covo.setResult(true);
        }catch (SQLException ex){
            covo.setResult(false);
            System.out.println("Error en la consulta de insert Contacts List: "+ex.getMessage());
        }
        return covo.getResult();
    }

    public Boolean deleteContactsListFooterDAO(ContactsListFooterVO muv,Connection cone){
        Boolean result = null;
        String sql = "DELETE FROM contacts_list_footer" +
                " WHERE contacts_list_header_list_id = ? AND list_email_direcction = ?";
        try{
            pst = cone.prepareStatement(sql);
            pst.setLong(1, muv.getContacts_list_header_list_id());
            pst.setString(2, muv.getList_email_direcction());
            pst.executeUpdate();
            System.out.println("contacts_list_footer "+ muv.getList_email_direcction()+" eliminado con exito");
            result = true;
        }catch (SQLException ex){
            result = false;
            System.out.println("Error en la eliminacion contacts_list_footer: "+ex.getMessage());
        }
        return result;
    }
}
