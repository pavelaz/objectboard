package com.psg.objectboard.controller.servlet;

import com.psg.objectboard.model.own.ownsEntity.classDAO.BodySurveyAnswersDAO;
import com.psg.objectboard.model.own.ownsEntity.classVO.BodySurveyAnswersVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "AnswersSurveyRequestsServlet", urlPatterns = "/answerssurveyrequests")
public class AnswersSurveyRequestsServlet extends HttpServlet {
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

        String request_type = null;
        if(request.getParameter("p_request_type")!=null) {
            request_type = request.getParameter("p_request_type");
        }
        request.setAttribute("rq_requestType", request_type);

        String request_code = null;
        if(request.getParameter("p_request_code")!=null) {
            request_code = request.getParameter("p_request_code");
        }
        request.setAttribute("rq_requestCode", request_code);

        String answer_code = null;
        if(request.getParameter("p_answer_code")!=null) {
            answer_code = request.getParameter("p_answer_code");
        }
        request.setAttribute("rq_answerCode", answer_code);

        String request_main = null;
        String condicion = null;
        if(request.getParameter("p_request_main")!=null) {
            condicion = request.getParameter("p_request_main");
            request_main = condicion.replace("-"," ");
        }
        request.setAttribute("rq_requestMain", request_main);

        BodySurveyAnswersDAO cod= new BodySurveyAnswersDAO();
        ArrayList<BodySurveyAnswersVO> answer = null;
        cod.setDataUser(data_user);
        cod.setDataPassword(data_pasword);

        if (acciones.equals("save")){
            ArrayList<BodySurveyAnswersVO> puntual = null;
            condicion = "answer_code = " +
                    Integer.parseInt(answer_code) +
                    " AND bodySurveyQuestions_question_code = " +
                    Integer.parseInt(request_code) +
                    " AND bodySurveyQuestions_headersSurvey_survey_code = " +
                    Integer.parseInt(code_poll) +
                    " AND bodySurveyQuestions_headersSurvey_bussinessUnit_bu_bis_code = " +
                    Integer.parseInt(company_number);
            puntual = cod.getListBodySurveyAnswers(condicion);
            request.setAttribute("rq_puntual", puntual);

            condicion = "bodySurveyQuestions_question_code = " +
                    Integer.parseInt(request_code) +
                    " AND bodySurveyQuestions_headersSurvey_survey_code = " +
                    Integer.parseInt(code_poll) +
                    " AND bodySurveyQuestions_headersSurvey_bussinessUnit_bu_bis_code = " +
                    Integer.parseInt(company_number) +
                    " AND answer_code  NOT IN (" +
                    Integer.parseInt(answer_code) +")";
        }else {

            condicion = "bodySurveyQuestions_question_code = " +
                    Integer.parseInt(request_code) +
                    " AND bodySurveyQuestions_headersSurvey_survey_code = " +
                    Integer.parseInt(code_poll) +
                    " AND bodySurveyQuestions_headersSurvey_bussinessUnit_bu_bis_code = " +
                    Integer.parseInt(company_number);
        }

        answer = cod.getListBodySurveyAnswers(condicion);

        request.setAttribute("rq_companyName", company_name);
        request.setAttribute("rq_userName", user_name);
        request.setAttribute("rq_numFilas", answer.size());
        request.setAttribute("rq_acciones", acciones);
        //request.setAttribute("rq_companyLogoName", company_logo_name);
        //request.setAttribute("rq_companyLogoDirection", company_logo_dir);

        request.setAttribute("rq_answer", answer);

        request.getRequestDispatcher("/WEB-INF/pages/jsp/customers/answerQuestions_polls.jsp").forward(request, response);
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