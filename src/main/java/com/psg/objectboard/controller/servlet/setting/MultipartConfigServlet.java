package com.psg.objectboard.controller.servlet.setting;

import com.psg.objectboard.controller.servlet.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pantalla = " ";
        //String pantalla = request.getParameter("p_pantalla");

        if(request.getParameter("p_pantalla")!=null){
            pantalla=request.getParameter("p_pantalla");
        }
        if (pantalla.equals("users")) {
            MasterUserProfileServlet masterUserProfileServlet = new MasterUserProfileServlet();
            masterUserProfileServlet.doOptions(request, response);
        }else {
            UserProfileServlet userProfileServlet = new UserProfileServlet();
            userProfileServlet.doOptions(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pantalla = " ";
        if(request.getParameter("p_pantalla")!=null){
            pantalla=request.getParameter("p_pantalla");
        }
        if (pantalla.equals("bussinessunit")){
            BussinessUnitsProcessServlet bussinessUnitsProcessServlet = new BussinessUnitsProcessServlet();
            bussinessUnitsProcessServlet.doPost(request, response);
        }
    }
}
