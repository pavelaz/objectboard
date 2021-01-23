package com.psg.objectboard.controller.servlet;

import com.psg.objectboard.model.own.ownsEntity.classDAO.BussinessUnitDAO;
import com.psg.objectboard.model.service.Other.DashboardFunctions;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DashMenuSuperServlet", urlPatterns = "/dashmenusuper")
public class DashMenuSuperServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession objSesion = request.getSession();

        String company_number = (String)objSesion.getAttribute("companyNumber");
        String company_name = (String)objSesion.getAttribute("companyName");
        String user_name = (String)objSesion.getAttribute("userName");
        String user_email = (String)objSesion.getAttribute("userEmail");
        String user_boardrole = (String) objSesion.getAttribute("userBoardRole");
        String data_user = (String)objSesion.getAttribute("dataUser");
        String data_pasword = (String)objSesion.getAttribute("dataPassword");
        //String company_logo_name = (String)objSesion.getAttribute("companyLogoName");
        //String company_logo_dir = (String)objSesion.getAttribute("companyLogoDirection");

        System.out.println("DashMenuSuperServlet Usuario en sesion: " + user_email);

        // Secccion del dashboard
        // Primer Bloque
        DashboardFunctions dbf = new DashboardFunctions();
        String varios = dbf.BuscaValoresPrimerBloque(data_pasword,data_user,company_number);
        String[] valores = varios.split(",");
        request.setAttribute("rq_porcLast24", valores[0]);
        request.setAttribute("rq_totalSurvey", valores[1]);
        request.setAttribute("rq_auditManual", valores[2]);
        request.setAttribute("rq_porcManual", valores[3]);
        request.setAttribute("rq_auditAuto", valores[4]);
        request.setAttribute("rq_porcAuto", valores[5]);
        request.setAttribute("rq_auditPend", valores[6]);
        request.setAttribute("rq_porcPend", valores[7]);
        request.setAttribute("rq_auditAudit", valores[8]);
        request.setAttribute("rq_porcAudit", valores[9]);
        request.setAttribute("rq_porcLast3", valores[10]);
        request.setAttribute("rq_totalPolls", valores[11]);
        // fin dashboard

        request.setAttribute("rq_companyNumber", company_number);
        request.setAttribute("rq_companyName", company_name);
        request.setAttribute("rq_userName", user_name);
        request.setAttribute("rq_userEmail", user_email);
        request.setAttribute("rq_userBoardRole", Integer.parseInt(user_boardrole));
        request.setAttribute("rq_dataUser", data_user);
        request.setAttribute("rq_dataPassword", data_pasword);
        //request.setAttribute("rq_companyLogoName", company_logo_name);
        //request.setAttribute("rq_companyLogoDirection", company_logo_dir);
        //String  none = company_logo_dir + company_logo_name;
        //request.setAttribute("rq_data_logo", none.replace("/","*"));
        BussinessUnitDAO bud = new BussinessUnitDAO();
        request.setAttribute("rq_format", bud.searchLogoName(company_number,data_user,data_pasword,1));

        request.getRequestDispatcher("/WEB-INF/pages/jsp/customers/dashMenu_super.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            processRequest(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
