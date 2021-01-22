package com.psg.objectboard.controller.servlet;

import com.psg.objectboard.model.own.ownsEntity.classDAO.CertificationsDAO;
import com.psg.objectboard.model.own.ownsEntity.classVO.CertificationsVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "CertificationsServlet", urlPatterns = "/certifications")
public class CertificationsServlet extends HttpServlet {
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
            String code = null;
            if(request.getParameter("p_code")!=null) {
                code = request.getParameter("p_code");
            }
            String name = null;
            if(request.getParameter("p_names")!=null) {
                name = request.getParameter("p_names");
            }

            request.setAttribute("rq_code", code);
            request.setAttribute("rq_name", name);
        }
        CertificationsDAO cod= new CertificationsDAO();
        ArrayList<CertificationsVO> cert = null;
        cod.setDataUser(data_user);
        cod.setDataPassword(data_pasword);

        cert = cod.getListCertifications("bussinessUnit_bu_bis_code = "+company_number);

        request.setAttribute("rq_companyName", company_name);
        request.setAttribute("rq_userName", user_name);
        request.setAttribute("rq_numFilas", cert.size());
        request.setAttribute("rq_acciones", acciones);
        //request.setAttribute("rq_companyLogoName", company_logo_name);
        //request.setAttribute("rq_companyLogoDirection", company_logo_dir);

        request.setAttribute("rq_orga", cert);

        request.getRequestDispatcher("/WEB-INF/pages/jsp/customers/certifications.jsp").forward(request, response);
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