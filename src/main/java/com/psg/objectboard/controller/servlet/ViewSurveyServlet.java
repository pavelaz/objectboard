package com.psg.objectboard.controller.servlet;

import com.psg.objectboard.model.own.ownsEntity.classDAO.BussinessUnitDAO;
import com.psg.objectboard.model.own.ownsEntity.classDAO.HeadersSurveyDAO;
import com.psg.objectboard.model.own.ownsEntity.classVO.HeadersSurveyVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ViewSurveyServlet", urlPatterns = "/viewsurvey")
public class ViewSurveyServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession objSesion = request.getSession();
        String company_name = (String)objSesion.getAttribute("companyName");
        String user_name = (String)objSesion.getAttribute("userName");
        String user_email = (String)objSesion.getAttribute("userEmail");
        String company_number = (String)objSesion.getAttribute("companyNumber");
        String data_user = (String)objSesion.getAttribute("dataUser");
        String data_pasword = (String)objSesion.getAttribute("dataPassword");
        //String company_logo_name = (String)objSesion.getAttribute("companyLogoName");
        //String company_logo_dir = (String)objSesion.getAttribute("companyLogoDirection");
        String code = null;
        if(request.getParameter("p_code")!=null) {
            code = request.getParameter("p_code");
        }
        HeadersSurveyDAO hsd = new HeadersSurveyDAO();
        HeadersSurveyVO hsv = hsd.serchHeadersSurveyrDAO(company_number,code);
        if (!hsv.getSurveyImageName().equals("no_images.jpeg"))
            request.setAttribute("rq_imagen", true);
        else
            request.setAttribute("rq_imagen", false);

        request.setAttribute("rq_companyName", company_name);
        request.setAttribute("rq_userName", user_name);
        request.setAttribute("rq_userEmail", user_email);
        //request.setAttribute("rq_companyLogoName", company_logo_name);
        //request.setAttribute("rq_companyLogoDirection", company_logo_dir);
        request.setAttribute("rq_companyNumber", company_number);

        BussinessUnitDAO bud = new BussinessUnitDAO();
        request.setAttribute("rq_format", bud.searchLogoName(company_number,data_user,data_pasword,1));

        request.getRequestDispatcher("/WEB-INF/pages/jsp/customers/viewSurvey.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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