package com.psg.objectboard.controller.servlet;

import com.psg.objectboard.model.own.ownsEntity.classDAO.BussinessUnitDAO;
import com.psg.objectboard.model.own.ownsEntity.classDAO.ContactsDAO;
import com.psg.objectboard.model.own.ownsEntity.classDAO.ContactsListHeaderDAO;
import com.psg.objectboard.model.own.ownsEntity.classVO.ContactsListHeaderVO;
import com.psg.objectboard.model.own.ownsEntity.classVO.ContactsVO;
import com.psg.objectboard.model.service.Other.OtherConexion;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "ContactsProcessServlet", urlPatterns = "/contactslistprocess")
public class ContactsListProcessServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession objSesion = request.getSession();
        String user_email = (String)objSesion.getAttribute("userEmail");
        String user_project = (String)objSesion.getAttribute("companyProject");
        String data_user = (String)objSesion.getAttribute("dataUser");
        String data_pasword = (String)objSesion.getAttribute("dataPassword");
        String company_number = (String)objSesion.getAttribute("companyNumber");

        String pantalla = " ";
        if(request.getParameter("p_pantalla")!=null){
            pantalla = request.getParameter("p_pantalla");
        }

        String acciones = " ";
        if(request.getParameter("p_acciones")!=null){
            acciones = request.getParameter("p_acciones");
        }

        ArrayList<Integer> cual_0 = new ArrayList<Integer>();
        ContactsListHeaderVO cvo = new ContactsListHeaderVO();
        String none = null;

        // Recupero los valores necesarios para la accion
        switch(acciones){
            case "delete":
                Integer num_filas = 0;
                if(request.getParameter("p_cuantos")!=null) {
                    none = request.getParameter("p_cuantos");
                    num_filas = Integer.parseInt(none);
                }
                for(int f=1 ; f < (num_filas + 1);f++){
                    if(request.getParameter("p_selec_"+f)!=null) {
                        none = request.getParameter("p_cual_1"+f);
                        if (cual_0.isEmpty()){
                            cual_0.add(0,Integer.parseInt(none));
                        }else{
                            cual_0.add(Integer.parseInt(none));
                        }
                    }
                }
                break; // break es opcional
            case "create":
            case "save":
                if(request.getParameter("p_name")!=null) {
                    none = request.getParameter("p_name");
                    cvo.setList_name(none.trim());
                }
                if(request.getParameter("p_id_selec")!=null) {
                    none = request.getParameter("p_id_selec");
                    cvo.setList_id(Integer.parseInt(none));
                }
                /*if(request.getParameter("p_name")!=null) {
                    none = request.getParameter("p_name");
                    cvo.setCto_name(none);
                }
                if(request.getParameter("p_bname")!=null) {
                    none = request.getParameter("p_bname");
                    cvo.setCto_business_name(none);
                }
                if(request.getParameter("p_ad1")!=null) {
                    none = request.getParameter("p_ad1");
                    cvo.setCto_address_1(none);
                }
                if(request.getParameter("p_ad2")!=null) {
                    none = request.getParameter("p_ad2");
                    cvo.setCto_address_2(none);
                }
                if(request.getParameter("p_cell")!=null) {
                    none = request.getParameter("p_cell");
                    none = none.replace("-","");
                    cvo.setCto_phone_cell(none);
                }
                if(request.getParameter("p_land")!=null) {
                    none = request.getParameter("p_land");
                    none = none.replace("-","");
                    cvo.setCto_landline(none);
                }
                none = "F";
                if(request.getParameter("p_email")!=null) {
                    none = request.getParameter("p_email");
                    cvo.setCto_email_message(none);
                }
                none = "F";
                if(request.getParameter("p_sms")!=null) {
                    none = request.getParameter("p_sms");
                    cvo.setCto_sms_message(none);
                }
                none = null;
                if(request.getParameter("p_type")!=null) {
                    none = request.getParameter("p_type");
                    cvo.setCto_type(none);
                }
                none = "F";
                if(request.getParameter("p_from")!=null) {
                    none = request.getParameter("p_from");
                    cvo.setCto_front_yard(none);
                }
                none = "F";
                if(request.getParameter("p_back")!=null) {
                    none = request.getParameter("p_back");
                    cvo.setCto_back_yard(none);
                }*/
                break;
            default :
                System.out.println("Accion solicitada no Existe");
                break;
        }
        // Realizo las acciones solicitadas sobe la base de datos

        ContactsListHeaderDAO cdo = new ContactsListHeaderDAO();
        OtherConexion ocn = new OtherConexion();
        Connection con = null;
        con=ocn.conectarse(data_user,data_pasword);

        try{
            // iniciar transacion
            ocn.init_trans(con);
            cvo.setContacts_masterUser_bussinessUnit_bu_bis_code(Long.parseLong(company_number));
            cvo.setContacts_masterUser_mu_email(user_email);
            cvo.setList_project(Long.parseLong(user_project));
            if (acciones.equals("create")){
                cvo.setList_count_directions(0);
                cdo.insertContactsListHeaderDAO(cvo,con);
            }else{
                if (acciones.equals("delete")){
                    cvo.setResult(true);
                    for(int x=0 ; x < cual_0.size();x++){
                        if (cvo.getResult()){
                            cvo.setList_id(cual_0.get(x));
                            cvo.setResult(cdo.deleteContactsListHeaderDAO(cvo,con));
                        }
                    }
                }else{
                    cdo.updateContactsListHeaderDAO(cvo,con);
                }
            }
            // valida status de la transacion
            ocn.valida_trans(con,cvo.getResult());
            //
            ocn.cierra_coneccion(con);
        }catch (Exception es){
            System.out.println(es.getMessage());
        }

        request.setAttribute("rq_result", cvo.getResult());
        request.setAttribute("rq_pantalla", pantalla);
        request.setAttribute("rq_companyNumber", company_number);

        BussinessUnitDAO bud = new BussinessUnitDAO();
        request.setAttribute("rq_format", bud.searchLogoName(company_number,data_user,data_pasword,1));

        request.getRequestDispatcher("/WEB-INF/pages/jsp/process/general_process.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error en el Module User Servlet");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error en el Module User Servlet");
        }
    }
}
