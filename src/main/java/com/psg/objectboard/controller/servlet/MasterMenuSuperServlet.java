package com.psg.objectboard.controller.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "MasterMenuSuperServlet", urlPatterns = "/mastermenusuper")
public class MasterMenuSuperServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession objSesion = request.getSession();

        String company_number = (String)objSesion.getAttribute("companyNumber");
        String company_name = (String)objSesion.getAttribute("companyName");
        String user_email = (String)objSesion.getAttribute("userEmail");
        String user_name = (String)objSesion.getAttribute("userName");

        System.out.println("Usuario en sesion: " + user_email);

        request.setAttribute("rq_companyName", company_name);
        request.setAttribute("rq_companyNumber", company_number);
        request.setAttribute("rq_userName", user_name);

        request.getRequestDispatcher("/WEB-INF/pages/jsp/master/masterMenu_super.jsp").forward(request, response);
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
