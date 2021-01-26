package com.psg.objectboard.controller.servlet.setting;

import com.psg.objectboard.controller.servlet.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@MultipartConfig(
        fileSizeThreshold   = 1024 * 1024 * 10,  // 10 MB
        maxFileSize         = 1024 * 1024 * 100, // 100 MB
        maxRequestSize      = 1024 * 1024 * 150, // 150 MB
        location            = "/web/temporaryuploadfiles/"
)
@WebServlet (name="MultipartConfigServlet", urlPatterns = "/multipartconfigservlet")
public class MultipartConfigServlet extends HttpServlet {

    public MultipartConfigServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException {
        String pantalla = " ";
        if(request.getParameter("p_pantalla")!=null){
            pantalla=request.getParameter("p_pantalla");
        }
        if (pantalla.equals("bussinessunit")){
            BussinessUnitsProcessServlet bussinessUnitsProcessServlet = new BussinessUnitsProcessServlet();
            bussinessUnitsProcessServlet.doPost(request, response);
        }
        if (pantalla.equals("users")){
            /*BussinessUnitsProcessServlet bussinessUnitsProcessServlet = new BussinessUnitsProcessServlet();
            bussinessUnitsProcessServlet.doPost(request, response);*/
            UserProfileServlet userProfileServlet = new UserProfileServlet();
            userProfileServlet.doOptions(request, response);
        }
    }

    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
