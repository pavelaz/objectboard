package com.psg.objectboard.controller.servlet;

import com.psg.objectboard.model.own.ownsEntity.classDAO.BussinessUnitDAO;
import com.psg.objectboard.model.own.ownsEntity.classDAO.ContactsListFooterDAO;
import com.psg.objectboard.model.own.ownsEntity.classDAO.ContactsListHeaderDAO;
import com.psg.objectboard.model.own.ownsEntity.classVO.ContactsListFooterVO;
import com.psg.objectboard.model.own.ownsEntity.classVO.ContactsListHeaderVO;
import com.psg.objectboard.model.service.Other.OtherConexion;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "ContactListFooterProcessServlet", value = "/contactlistfooterprocess")
public class ContactListFooterProcessServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession objSesion = request.getSession();
        String company_name = (String)objSesion.getAttribute("companyName");
        String company_number = (String)objSesion.getAttribute("companyNumber");
        String user_name = (String)objSesion.getAttribute("userName");
        String data_user = (String)objSesion.getAttribute("dataUser");
        String data_pasword = (String)objSesion.getAttribute("dataPassword");
        String user_email = (String)objSesion.getAttribute("userEmail");
        String company_project = (String)objSesion.getAttribute("companyProject");

        String condicion = null,
                none = null,
                lista_name = "";
        Integer lista_id = 0;

        String acciones = "create";
        if(request.getParameter("p_acciones")!=null) {
            acciones = request.getParameter("p_acciones");
        }

        if(request.getParameter("p_lista_name")!=null) {
            lista_name = request.getParameter("p_lista_name");
            request.setAttribute("rq_listaName", lista_name);
        }

        if(request.getParameter("p_lista_id")!=null) {
            none = request.getParameter("p_lista_id");
            lista_id = Integer.parseInt(none);
            request.setAttribute("rq_listaId", lista_id);
        }

        ArrayList<String> inclus = new ArrayList<String>();
        ArrayList<String> elimin = new ArrayList<String>();

        switch(acciones){
            case "delete":
                Integer num_filas_to_delete = 0;
                if(request.getParameter("p_cuantos_to_delete")!=null) {
                    none = request.getParameter("p_cuantos_to_delete");
                    num_filas_to_delete = Integer.parseInt(none);
                }
                for(int f=1 ; f < (num_filas_to_delete + 1);f++){
                    if(request.getParameter("p_selec_"+f)!=null) {
                        none = request.getParameter("p_cual_0"+f);
                        if (elimin.isEmpty()){
                            elimin.add(0,none);
                        }else{
                            elimin.add(none);
                        }
                    }
                }
                break; // break es opcional
            case "create":
                Integer num_filas_to_include = 0;
                if(request.getParameter("p_cuantos_to_include")!=null) {
                    none = request.getParameter("p_cuantos_to_include");
                    num_filas_to_include = Integer.parseInt(none);
                }
                for(int f=1 ; f < (num_filas_to_include + 1);f++){
                    if(request.getParameter("p_include_"+f)!=null) {
                        none = request.getParameter("p_inclu_0"+f);
                        if (inclus.isEmpty()){
                            inclus.add(0,none);
                        }else{
                            inclus.add(none);
                        }
                    }
                }
                break;
            default :
                System.out.println("Accion solicitada no Existe");
                break;
        }

        ContactsListFooterDAO cdo = new ContactsListFooterDAO();
        OtherConexion ocn = new OtherConexion();
        Connection con = null;
        con=ocn.conectarse(data_user,data_pasword);
        ContactsListFooterVO cvo = new ContactsListFooterVO();

        try{
            // iniciar transacion
            ocn.init_trans(con);
            /*cvo.setContacts_masterUser_bussinessUnit_bu_bis_code(Long.parseLong(company_number));
            cvo.setContacts_masterUser_mu_email(user_email);
            cvo.setList_project(Long.parseLong(user_project));
            if (acciones.equals("create")){
                cvo.setList_count_directions(0);
                cdo.insertContactsListHeaderDAO(cvo,con);
            }else{*/
                if (acciones.equals("delete")){
                    cvo.setResult(true);
                    cvo.setContacts_list_header_list_id(lista_id);
                    for(int x=0 ; x < elimin.size();x++){
                        if (cvo.getResult()){
                            cvo.setList_email_direcction(elimin.get(x));
                            cvo.setResult(cdo.deleteContactsListFooterDAO(cvo,con));
                        }
                    }
                }else{
                    cvo.setResult(true);
                    cvo.setContacts_list_header_list_id(lista_id);
                    for(int x=0 ; x < inclus.size();x++){
                        if (cvo.getResult()){
                            cvo.setList_email_direcction(inclus.get(x));
                            cvo.setResult(cdo.insertContactsListFooterDAO(cvo,con));
                        }
                    }
                }
            //}
            // valida status de la transacion
            ocn.valida_trans(con,cvo.getResult());
            //
            ocn.cierra_coneccion(con);
        }catch (Exception es){
            System.out.println(es.getMessage());
        }

        request.setAttribute("rq_result", cvo.getResult());
        request.setAttribute("rq_companyName", company_name);
        request.setAttribute("rq_userName", user_name);
        request.setAttribute("rq_acciones", acciones);

        request.setAttribute("rq_companyNumber", company_number);
        BussinessUnitDAO bud = new BussinessUnitDAO();
        request.setAttribute("rq_format", bud.searchLogoName(company_number,data_user,data_pasword,1));

        request.getRequestDispatcher("/WEB-INF/pages/jsp/customers/contactsListFooter.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
