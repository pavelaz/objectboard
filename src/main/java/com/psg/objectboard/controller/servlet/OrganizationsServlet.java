package com.psg.objectboard.controller.servlet;

import com.psg.objectboard.model.own.ownsEntity.classDAO.OrganizationDAO;
import com.psg.objectboard.model.own.ownsEntity.classVO.OrganizationVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "OrganizationsServlet", urlPatterns = "/organizations")
public class OrganizationsServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession objSesion = request.getSession();
        String company_name = (String)objSesion.getAttribute("companyName");
        String company_number = (String)objSesion.getAttribute("companyNumber");
        String user_name = (String)objSesion.getAttribute("userName");
        String data_user = (String)objSesion.getAttribute("dataUser");
        String data_pasword = (String)objSesion.getAttribute("dataPassword");
        //String company_logo_name = (String)objSesion.getAttribute("companyLogoName");
        //String company_logo_dir = (String)objSesion.getAttribute("companyLogoDirection");

        String acciones = "consult";
        if(request.getParameter("p_acciones")!=null) {
            acciones = request.getParameter("p_acciones");
        }

        if (acciones.equals("save")){
            String level1 = null;
            if(request.getParameter("p_levels1")!=null) {
                level1 = request.getParameter("p_levels1");
            }
            String level2 = null;
            if(request.getParameter("p_levels2")!=null) {
                level2 = request.getParameter("p_levels2");
            }
            String level3 = null;
            if(request.getParameter("p_levels3")!=null) {
                level3 = request.getParameter("p_levels3");
            }
            String level4 = null;
            if(request.getParameter("p_levels4")!=null) {
                level4 = request.getParameter("p_levels4");
            }
            request.setAttribute("rq_level1", level1);
            request.setAttribute("rq_level2", level2);
            request.setAttribute("rq_level3", level3);
            request.setAttribute("rq_level4", level4);
        }
        OrganizationDAO cod= new OrganizationDAO();
        ArrayList<OrganizationVO> orga = null;
        cod.setDataUser(data_user);
        cod.setDataPassword(data_pasword);

        orga = cod.getListOrganization("bussinessUnit_bu_bis_code = "+company_number);

        request.setAttribute("rq_companyName", company_name);
        request.setAttribute("rq_userName", user_name);
        request.setAttribute("rq_numFilas", orga.size());
        request.setAttribute("rq_acciones", acciones);
        //request.setAttribute("rq_companyLogoName", company_logo_name);
        //request.setAttribute("rq_companyLogoDirection", company_logo_dir);

        request.setAttribute("rq_orga", orga);

        request.getRequestDispatcher("/WEB-INF/pages/jsp/customers/organizations.jsp").forward(request, response);
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
