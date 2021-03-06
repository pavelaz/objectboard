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
            UserProfileServlet userProfileServlet = new UserProfileServlet();
            userProfileServlet.doOptions(request, response);
        }
        if (pantalla.equals("headerpolls")){
            String target = " ";
            if(request.getParameter("p_target")!=null){
                target=request.getParameter("p_target");
            }
            if (target.equals("headerpolls")){
                HeaderPollsServlet headerPollsServlet = new HeaderPollsServlet();
                headerPollsServlet.doPost(request, response);
            }
            if (target.equals("viewsurvey")){
                ViewSurveyServlet viewSurveyServlet = new ViewSurveyServlet();
                viewSurveyServlet.doPost(request, response);
            }
            if (target.equals("headerpollsprocess")){
                HeaderPollsProcessServlet headerPollsProcessServlet = new HeaderPollsProcessServlet();
                headerPollsProcessServlet.doPost(request, response);
            }
            if (target.equals("bodysurveyquestions")){
                BodySurveyQuestionsServlet bodySurveyQuestionsServlet = new BodySurveyQuestionsServlet();
                bodySurveyQuestionsServlet.doPost(request, response);
            }
        }
        if (pantalla.equals("questionspolls")){
            String target = " ";
            if(request.getParameter("p_target")!=null){
                target=request.getParameter("p_target");
            }
            if (target.equals("bodysurveyquestions")){
                BodySurveyQuestionsServlet bodySurveyQuestionsServlet = new BodySurveyQuestionsServlet();
                bodySurveyQuestionsServlet.doPost(request, response);
            }
            if (target.equals("bodysurveyquestionsprocess")){
                BodySurveyQuestionsProcessServlet bodySurveyQuestionsProcessServlet = new BodySurveyQuestionsProcessServlet();
                bodySurveyQuestionsProcessServlet.doPost(request, response);
            }
            if (target.equals("answerssurveyrequests")){
                AnswersSurveyRequestsServlet answersSurveyRequestsServlet = new AnswersSurveyRequestsServlet();
                answersSurveyRequestsServlet.doPost(request, response);
            }
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
