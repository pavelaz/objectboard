package com.psg.objectboard.controller.servlet;

import com.psg.objectboard.model.own.ownsEntity.classDAO.BussinessUnitDAO;
import com.psg.objectboard.model.own.ownsEntity.classDAO.ContactsDAO;
import com.psg.objectboard.model.own.ownsEntity.classDAO.OrganizationDAO;
import com.psg.objectboard.model.own.ownsEntity.classVO.ContactsVO;
import com.psg.objectboard.model.own.ownsEntity.classVO.OrganizationVO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "ContactsServlet", value = "/contacts")
public class ContactsServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession objSesion = request.getSession();
        String company_name = (String)objSesion.getAttribute("companyName");
        String company_number = (String)objSesion.getAttribute("companyNumber");
        String user_name = (String)objSesion.getAttribute("userName");
        String user_email = (String)objSesion.getAttribute("userEmail");
        String data_user = (String)objSesion.getAttribute("dataUser");
        String data_pasword = (String)objSesion.getAttribute("dataPassword");
        String company_project = (String)objSesion.getAttribute("companyProject");

        String condicion = "",
               none = "",
               email_selec = "";
        ContactsDAO cod= new ContactsDAO();
        ArrayList<ContactsVO> contac = null;
        ArrayList<ContactsVO> contac_old = null;
        cod.setDataUser(data_user);
        cod.setDataPassword(data_pasword);

        String acciones = "consult";
        if(request.getParameter("p_acciones")!=null) {
            acciones = request.getParameter("p_acciones");
        }

        if (acciones.equals("consult")){
            condicion = "masterUser_bussinessUnit_bu_bis_code = " + company_number +
                    " AND masterUser_mu_email = '" + user_email +
                    "' AND cto_project = " + Integer.parseInt(company_project);
        }
        String column = "0";
        if(request.getParameter("p_column")!=null) {
            column = request.getParameter("p_column");
        }

        if (acciones.equals("save")){
            // Busca registro seleccionado
            if(request.getParameter("p_email_selec")!=null) {
                email_selec = request.getParameter("p_email_selec");
            }
            condicion = "masterUser_bussinessUnit_bu_bis_code = " + company_number +
                        " AND masterUser_mu_email = '" + user_email +
                        "' AND cto_email_direction = '" + email_selec +
                        "' AND cto_project = " + Integer.parseInt(company_project);
            contac_old = cod.getListContacts(condicion);
            if (contac_old.size() != 0){
                for (int x = 0; x < contac_old.size(); x++) {
                    none = contac_old.get(x).getCto_phone_cell().substring(0, 3) + "-" +
                            contac_old.get(x).getCto_phone_cell().substring(3, 6) + "-" +
                            contac_old.get(x).getCto_phone_cell().substring(6, 10);
                    contac_old.get(x).setCto_phone_cell(none);
                    none = contac_old.get(x).getCto_landline().substring(0, 3) + "-" +
                            contac_old.get(x).getCto_landline().substring(3, 6) + "-" +
                            contac_old.get(x).getCto_landline().substring(6, 10);
                    contac_old.get(x).setCto_landline(none);
                }
            }
            request.setAttribute("rq_contacOld", contac_old);
            request.setAttribute("rq_emailSelec", email_selec);

            // Busca registros sin incluir el seleccionado
            condicion = "masterUser_bussinessUnit_bu_bis_code = " + company_number +
                        " AND masterUser_mu_email = '" + user_email +
                        "' AND cto_project = " + Integer.parseInt(company_project) +
                        " AND cto_email_direction NOT IN ('" + email_selec + "')";
        }

        contac = cod.getListContacts(condicion);

        if (contac.size() != 0){
            for (int x = 0; x < contac.size(); x++) {
                none = contac.get(x).getCto_phone_cell().substring(0, 3) + "-" +
                        contac.get(x).getCto_phone_cell().substring(3, 6) + "-" +
                        contac.get(x).getCto_phone_cell().substring(6, 10);
                contac.get(x).setCto_phone_cell(none);
                none = contac.get(x).getCto_landline().substring(0, 3) + "-" +
                        contac.get(x).getCto_landline().substring(3, 6) + "-" +
                        contac.get(x).getCto_landline().substring(6, 10);
                contac.get(x).setCto_landline(none);
            }
        }

        request.setAttribute("rq_companyName", company_name);
        request.setAttribute("rq_companyNumber", company_number);
        request.setAttribute("rq_companyProject", company_project);
        request.setAttribute("rq_userName", user_name);
        request.setAttribute("rq_acciones", acciones);
        request.setAttribute("rq_contac", contac);
        request.setAttribute("rq_column", column);

        BussinessUnitDAO bud = new BussinessUnitDAO();
        request.setAttribute("rq_format", bud.searchLogoName(company_number,data_user,data_pasword,1));

        request.getRequestDispatcher("/WEB-INF/pages/jsp/customers/contacts.jsp").forward(request, response);
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
