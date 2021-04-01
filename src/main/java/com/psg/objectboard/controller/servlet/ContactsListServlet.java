package com.psg.objectboard.controller.servlet;

import com.psg.objectboard.model.own.ownsEntity.classDAO.BussinessUnitDAO;
import com.psg.objectboard.model.own.ownsEntity.classDAO.CertificationsDAO;
import com.psg.objectboard.model.own.ownsEntity.classDAO.ContactsListHeaderDAO;
import com.psg.objectboard.model.own.ownsEntity.classVO.CertificationsVO;
import com.psg.objectboard.model.own.ownsEntity.classVO.ContactsListHeaderVO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "ContactsListServlet", value = "/contactslist")
public class ContactsListServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession objSesion = request.getSession();
        String company_name = (String)objSesion.getAttribute("companyName");
        String company_number = (String)objSesion.getAttribute("companyNumber");
        String user_name = (String)objSesion.getAttribute("userName");
        String data_user = (String)objSesion.getAttribute("dataUser");
        String data_pasword = (String)objSesion.getAttribute("dataPassword");
        String user_email = (String)objSesion.getAttribute("userEmail");
        String company_project = (String)objSesion.getAttribute("companyProject");

        String condicion = null;
        String acciones = "consult";
        if(request.getParameter("p_acciones")!=null) {
            acciones = request.getParameter("p_acciones");
        }

        if (acciones.equals("save")){
            String code = null;
            if(request.getParameter("p_id_selec")!=null) {
                code = request.getParameter("p_id_selec");
            }
            String name = null;
            if(request.getParameter("p_name_old")!=null) {
                name = request.getParameter("p_name_old");
            }

            request.setAttribute("rq_idSelec", code);
            request.setAttribute("rq_nameOld", name);

            condicion = "contacts_masterUser_bussinessUnit_bu_bis_code = " + company_number +
                        " AND contacts_masterUser_mu_email = '" + user_email + "' AND list_project = " +
                        company_project + " AND list_id NOT IN ( " + code + ")";
        }else{
            condicion = "contacts_masterUser_bussinessUnit_bu_bis_code = " + company_number +
                    " AND contacts_masterUser_mu_email = '" + user_email + "' AND list_project = " + company_project;
        }
        ContactsListHeaderDAO cod= new ContactsListHeaderDAO();
        ArrayList<ContactsListHeaderVO> lista = null;
        cod.setDataUser(data_user);
        cod.setDataPassword(data_pasword);

        lista = cod.getListContactsListHeader(condicion);

        request.setAttribute("rq_companyName", company_name);
        request.setAttribute("rq_userName", user_name);
        request.setAttribute("rq_numFilas", lista.size());
        request.setAttribute("rq_acciones", acciones);

        request.setAttribute("rq_companyNumber", company_number);
        BussinessUnitDAO bud = new BussinessUnitDAO();
        request.setAttribute("rq_format", bud.searchLogoName(company_number,data_user,data_pasword,1));

        request.setAttribute("rq_lista", lista);

        request.getRequestDispatcher("/WEB-INF/pages/jsp/customers/contactsListHeader.jsp").forward(request, response);
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
