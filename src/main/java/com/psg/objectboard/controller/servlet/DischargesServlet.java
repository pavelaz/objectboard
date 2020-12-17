package com.psg.objectboard.controller.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DischargesServlet", urlPatterns = "/discharges")
public class DischargesServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession objSesion = request.getSession();
        String company_name = (String)objSesion.getAttribute("companyName");
        String company_number = (String) objSesion.getAttribute("companyNumber");
        String user_name = (String)objSesion.getAttribute("userName");
        String company_logo_name = (String)objSesion.getAttribute("companyLogoName");
        String company_logo_dir = (String)objSesion.getAttribute("companyLogoDirection");

        request.setAttribute("rq_companyName", company_name);
        request.setAttribute("rq_companyNumber",company_number);
        request.setAttribute("rq_userName", user_name);
        request.setAttribute("rq_companyLogoName", company_logo_name);
        request.setAttribute("rq_companyLogoDirection", company_logo_dir);

        request.getRequestDispatcher("/WEB-INF/pages/jsp/master/discharges.jsp").forward(request, response);
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
