package com.psg.objectboard.controller.servlet;

import com.psg.objectboard.model.own.ownsEntity.classDAO.AssignmentsDAO;
import com.psg.objectboard.model.own.ownsEntity.classVO.OtherVO.AssignmentsConsultVO;
import com.psg.objectboard.model.service.Other.DateFunctions;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "ConductSurveyServlet", urlPatterns = "/conductsurvey")
public class ConductSurveyServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession objSesion = request.getSession();
        String company_name = (String)objSesion.getAttribute("companyName");
        String user_name = (String)objSesion.getAttribute("userName");
        String user_email = (String)objSesion.getAttribute("userEmail");
        String data_user = (String)objSesion.getAttribute("dataUser");
        String data_pasword = (String)objSesion.getAttribute("dataPassword");
        String company_number = (String)objSesion.getAttribute("companyNumber");
        //String company_logo_name = (String)objSesion.getAttribute("companyLogoName");
        //String company_logo_dir = (String)objSesion.getAttribute("companyLogoDirection");

        String email_assign = null;
        if(request.getParameter("p_email")!=null) {
            email_assign = request.getParameter("p_email");
        }
        request.setAttribute("rq_emailAssign", email_assign);

        String audited = null;
        if(request.getParameter("p_audited")!=null) {
            audited = request.getParameter("p_audited");
        }
        request.setAttribute("rq_audited", audited);

        String auditor = null;
        if(request.getParameter("p_auditor")!=null) {
            auditor = request.getParameter("p_auditor");
        }
        request.setAttribute("rq_auditor", auditor);

        String code = null;
        if(request.getParameter("p_code")!=null) {
            code = request.getParameter("p_code");
        }
        request.setAttribute("rq_code", code);

        String name = null;
        if(request.getParameter("p_name")!=null) {
            name = request.getParameter("p_name");
        }
        request.setAttribute("rq_name", name);

        String auditor_name = null;
        if(request.getParameter("p_auditor_name")!=null) {
            auditor_name = request.getParameter("p_auditor_name");
        }
        request.setAttribute("rq_auditorName", auditor_name);

        DateFunctions df = new DateFunctions();
        String none = null, hora = null, fecha = null;
        none = df.fechaFull(9);
        hora = none.substring(11,19);
        fecha = none.substring(0,10);
        none = df.parseFecha_2(none);

        request.setAttribute("rq_hora", hora);
        request.setAttribute("rq_fechaHoy", none);
        request.setAttribute("rq_fechaArch", fecha);

        AssignmentsDAO ado = new AssignmentsDAO();
        ado.setDataPassword(data_pasword);
        ado.setDataUser(data_user);
        ArrayList<AssignmentsConsultVO> userAssi = null;
        String condicion = "masterUser_mu_email = '" + email_assign +"' AND masterUser_bussinessUnit_bu_bis_code = " + Long.parseLong(company_number) +
                " AND headersSurvey_survey_code = " + Long.parseLong(code);
        userAssi = ado.getListAssignmentsConsult(condicion);
        request.setAttribute("rq_userAssi", userAssi);

        request.setAttribute("rq_companyName", company_name);
        request.setAttribute("rq_userName", user_name);
        request.setAttribute("rq_userEmail", user_email);
        //request.setAttribute("rq_companyLogoName", company_logo_name);
        //request.setAttribute("rq_companyLogoDirection", company_logo_dir);

        request.getRequestDispatcher("/WEB-INF/pages/jsp/customers/conductSurvey.jsp").forward(request, response);
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