package com.psg.objectboard.controller.servlet;

import com.psg.objectboard.model.own.ownsEntity.classDAO.BodySurveyQuestionsDAO;
import com.psg.objectboard.model.own.ownsEntity.classVO.BodySurveyQuestionsVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "BodySurveyQuestionsServlet", urlPatterns = "/bodysurveyquestions")
public class BodySurveyQuestionsServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession objSesion = request.getSession();
        String company_name = (String)objSesion.getAttribute("companyName");
        String company_number = (String)objSesion.getAttribute("companyNumber");
        String user_name = (String)objSesion.getAttribute("userName");
        String data_user = (String)objSesion.getAttribute("dataUser");
        String data_pasword = (String)objSesion.getAttribute("dataPassword");
        String company_logo_name = (String)objSesion.getAttribute("companyLogoName");
        String company_logo_dir = (String)objSesion.getAttribute("companyLogoDirection");

        String acciones = "consult";
        if(request.getParameter("p_acciones")!=null) {
            acciones = request.getParameter("p_acciones");
        }
        String code_poll = null;
        if(request.getParameter("p_code")!=null) {
            code_poll = request.getParameter("p_code");
        }
        request.setAttribute("rq_code", code_poll);

        String name_poll = null;
        if(request.getParameter("p_names")!=null) {
            name_poll = request.getParameter("p_names");
        }
        request.setAttribute("rq_names", name_poll);

        String refe_poll = null;
        if(request.getParameter("p_refes")!=null) {
            refe_poll = request.getParameter("p_refes");
        }
        request.setAttribute("rq_refes", refe_poll);
        String column = "0";
        if(request.getParameter("p_column")!=null) {
            column = request.getParameter("p_column");
        }
        request.setAttribute("rq_column", column);

        BodySurveyQuestionsDAO cod= new BodySurveyQuestionsDAO();
        ArrayList<BodySurveyQuestionsVO> recuest = null;
        cod.setDataUser(data_user);
        cod.setDataPassword(data_pasword);
        String condicion = null;

        condicion = "headersSurvey_bussinessUnit_bu_bis_code = " +
                Integer.parseInt(company_number) +
                " AND headersSurvey_survey_code = " +
                Integer.parseInt(code_poll);

        recuest = cod.getListBodySurveyQuestions(condicion);

        request.setAttribute("rq_companyName", company_name);
        request.setAttribute("rq_userName", user_name);
        request.setAttribute("rq_numFilas", recuest.size());
        request.setAttribute("rq_acciones", acciones);
        request.setAttribute("rq_companyLogoName", company_logo_name);
        request.setAttribute("rq_companyLogoDirection", company_logo_dir);

        request.setAttribute("rq_request", recuest);

        request.getRequestDispatcher("/WEB-INF/pages/jsp/customers/questions_polls.jsp").forward(request, response);
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