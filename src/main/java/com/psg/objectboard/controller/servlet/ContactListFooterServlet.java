package com.psg.objectboard.controller.servlet;

import com.psg.objectboard.model.own.ownsEntity.classDAO.BussinessUnitDAO;
import com.psg.objectboard.model.own.ownsEntity.classDAO.ContactsDAO;
import com.psg.objectboard.model.own.ownsEntity.classDAO.ContactsListFooterDAO;
import com.psg.objectboard.model.own.ownsEntity.classVO.ContactsListFooterVO;
import com.psg.objectboard.model.own.ownsEntity.classVO.ContactsVO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "ContactListFooterServlet", value = "/contactlistfooter")
public class ContactListFooterServlet extends HttpServlet {
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
        if(request.getParameter("p_name_old")!=null) {
            lista_name = request.getParameter("p_name_old");
            request.setAttribute("rq_listaName", lista_name);
        }

        if(request.getParameter("p_lista_id")!=null) {
            none = request.getParameter("p_lista_id");
            lista_id = Integer.parseInt(none);
            request.setAttribute("rq_listaId", lista_id);
        }
        if(request.getParameter("p_id_selec")!=null) {
            none = request.getParameter("p_id_selec");
            lista_id = Integer.parseInt(none);
            request.setAttribute("rq_listaId", lista_id);
        }

        ContactsListFooterDAO cod= new ContactsListFooterDAO();
        ArrayList<ContactsListFooterVO> contac = null;
        cod.setDataUser(data_user);
        cod.setDataPassword(data_pasword);
        condicion = "contacts_list_header_list_id = " + lista_id;
        contac = cod.getListContactsListFooterDAO(condicion);
        request.setAttribute("rq_contac", contac);

        //if (acciones.equals("create")){
            condicion = "masterUser_bussinessUnit_bu_bis_code = " + company_number +
                        " AND masterUser_mu_email = '" + user_email +
                        "' AND cto_project = " + company_project +
                        " AND (cto_email_message ='T' OR cto_sms_message = 'T') AND " +
                        "cto_email_direction NOT IN (SELECT list_email_direcction FROM " +
                        "contacts_list_footer WHERE contacts_list_header_list_id = " + lista_id +")";
            ContactsDAO ctdao = new ContactsDAO();
            ArrayList<ContactsVO> ctvo = null;
            ctdao.setDataUser(data_user);
            ctdao.setDataPassword(data_pasword);
            ctvo = ctdao.getListContacts(condicion);
            request.setAttribute("rq_inclus", ctvo);
        //}

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
