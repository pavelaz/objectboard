package com.psg.objectboard.controller.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet (name="LogoutServlet", urlPatterns = {"/logout"})
public class LogoutServlet extends HttpServlet {

    public LogoutServlet() {
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String none = "0";

        Boolean mostrar_cierre = true;
        if(request.getParameter("p_mostrar_cierre")!=null) {
            mostrar_cierre = false;
        }

        if(request.getParameter("p_exit_total")!=null){
            none=request.getParameter("p_exit_total");
        }

        Boolean exit_total = false;
        if(Integer.parseInt(none) == 1) {
                exit_total = true;
        }
        Integer otra_salida = 0;
        if(request.getParameter("p_otra_salida")!=null) {
            otra_salida = Integer.parseInt(request.getParameter("p_otra_salida"));
        }

        HttpSession objSesion = request.getSession();
        Integer company_number = 0;
        String company_name = null,
                email = null,
                logo_name = null,
                password = null;

        if ((String)objSesion.getAttribute("companyNumber") != null) {
            none = (String) objSesion.getAttribute("companyNumber");
            company_name = (String) objSesion.getAttribute("companyName");
            email = (String) objSesion.getAttribute("userEmail");
            password = (String) objSesion.getAttribute("userPassword");
            company_number = Integer.parseInt(none);
            logo_name = (String) objSesion.getAttribute("companyLogoName");
        }

        request.getSession().invalidate();

        request.setAttribute("rq_companyNumber", company_number);
        request.setAttribute("rq_companyLogo", logo_name);
        request.setAttribute("rq_companyName", company_name);
        request.setAttribute("rq_userEmail", email);
        request.setAttribute("rq_userPassword", password);
        request.setAttribute("rq_mostrarCierre", mostrar_cierre);
        request.setAttribute("rq_exitTotal", exit_total);
        request.setAttribute("rq_otraRazon", otra_salida);

        request.getRequestDispatcher("WEB-INF/pages/jsp/process/logout.jsp").forward(request, response);
    }

    /*******Metodos Get, Post, Put y Delete********/
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("LogoutServlet");
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("LogoutServlet");
        }
    }
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("LogoutServlet");
        }
    }
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("LogoutServlet");
        }
    }
}