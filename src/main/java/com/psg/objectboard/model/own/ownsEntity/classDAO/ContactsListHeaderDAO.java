package com.psg.objectboard.model.own.ownsEntity.classDAO;

import com.psg.objectboard.model.own.ownsEntity.classVO.ContactsListHeaderVO;
import com.psg.objectboard.model.own.ownsEntity.classVO.ContactsVO;
import com.psg.objectboard.model.service.Other.OtherConexion;
import com.psg.objectboard.model.service.Other.SqlFunctions;

import java.sql.*;
import java.util.ArrayList;

public class ContactsListHeaderDAO {
    private static OtherConexion cc = null;
    private static ContactsListHeaderVO covo = null;
    private static Connection cn = null;
    private static SqlFunctions sqls = null;
    private static ResultSet rs = null;
    private static Statement cmd = null;
    private static PreparedStatement pst = null;
    private static String dataUser;
    private static String dataPassword;

    public void setDataUser(String dataUser) {
        ContactsListHeaderDAO.dataUser = dataUser;
    }

    public void setDataPassword(String dataPassword) {
        ContactsListHeaderDAO.dataPassword = dataPassword;
    }

    public ArrayList<ContactsListHeaderVO> getListContactsListHeader(String condi) {
        ArrayList<ContactsListHeaderVO> arrcom = new ArrayList<>();
        cc = new OtherConexion();
        cn = cc.conectarse(dataUser,dataPassword);
        sqls = new SqlFunctions();
        String sql = null;
        if(!condi.equals("")){
            sql = sqls.get_select("contacts_list_header", "*",condi,"","","");
        }else{
            sql = sqls.get_select("contacts_list_header", "*","","","","");
        }
        try{
            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
                covo = new ContactsListHeaderVO();
                covo.setList_id(rs.getInt(1));
                covo.setContacts_masterUser_bussinessUnit_bu_bis_code(rs.getLong(2));
                covo.setContacts_masterUser_mu_email(rs.getString(3));
                covo.setList_project(rs.getLong(4));
                covo.setList_name(rs.getString(5));
                covo.setList_count_directions(rs.getInt(6));
                if (arrcom.isEmpty()){
                    arrcom.add(0,covo);
                }else{
                    arrcom.add(covo);
                }
            }
            System.out.println("Consulta array contacts_list_header exitosa: ");
        }catch (SQLException ex){
            System.out.println("Error en la consulta array contacts_list_header: "+ex.getMessage());
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

    public void insertContactsListHeaderDAO(ContactsListHeaderVO covo, Connection cone){
        String sql = "INSERT INTO contacts_list_header values (null,?,?,?,?,?)";
        covo.setResult(false);
        try{
            pst = cone.prepareStatement(sql);
            pst.setLong(1,covo.getContacts_masterUser_bussinessUnit_bu_bis_code());
            pst.setString(2,covo.getContacts_masterUser_mu_email());
            pst.setLong(3,covo.getList_project());
            pst.setString(4,covo.getList_name());
            pst.setInt(5,covo.getList_count_directions());
            pst.executeUpdate();
            System.out.println("Operacion de Insert Contacts List Exitosa.");
            covo.setResult(true);
        }catch (SQLException ex){
            covo.setResult(false);
            System.out.println("Error en la consulta de insert Contacts List: "+ex.getMessage());
        }
    }

    public void updateContactsListHeaderDAO(ContactsListHeaderVO muv, Connection cone) {
        String sql = "UPDATE contacts_list_header SET " +
                "list_name = ? " +
                " WHERE list_id=?";
        try{
            pst = cone.prepareStatement(sql);
            pst.setString(1,muv.getList_name());
            pst.setLong(2,muv.getList_id());
            pst.execute();
            System.out.println("contacts list actualizada con exito, ID: "+muv.getList_name());
            muv.setResult(true);
        }catch (SQLException ex){
            System.out.println("Error en la actualizacion contacts list: "+ex.getMessage());
            muv.setResult(false);
        }
    }

    public void updateContactsListHeaderCountDAO(ContactsListHeaderVO muv, Connection cone) {
        String sql = "UPDATE contacts_list_header SET " +
                "list_name = ? " +
                " WHERE (list_id=?)";
        try{
            pst = cone.prepareStatement(sql);
            pst.setString(1,muv.getList_name());
            pst.setLong(2,muv.getList_id());
            pst.execute();
            System.out.println("contacts list actualizada con exito, ID: "+muv.getList_name());
            muv.setResult(true);
        }catch (SQLException ex){
            System.out.println("Error en la actualizacion contacts list: "+ex.getMessage());
            muv.setResult(false);
        }
    }

    public Boolean deleteContactsListHeaderDAO(ContactsListHeaderVO muv,Connection cone){
        Boolean result = null;
        String sql = "DELETE FROM contacts_list_header" +
                " WHERE list_id=?";
        try{
            pst = cone.prepareStatement(sql);
            pst.setLong(1, muv.getList_id());
            pst.executeUpdate();
            System.out.println("contacts List "+ muv.getList_id()+" eliminado con exito");
            result = true;
        }catch (SQLException ex){
            result = false;
            System.out.println("Error en la eliminacion: "+ex.getMessage());
        }
        return result;
    }
}
