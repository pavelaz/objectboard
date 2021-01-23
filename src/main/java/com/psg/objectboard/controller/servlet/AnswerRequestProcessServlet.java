package com.psg.objectboard.controller.servlet;

import com.psg.objectboard.model.own.ownsEntity.classDAO.BodySurveyAnswersDAO;
import com.psg.objectboard.model.own.ownsEntity.classDAO.BodySurveyQuestionsDAO;
import com.psg.objectboard.model.own.ownsEntity.classVO.BodySurveyAnswersVO;
import com.psg.objectboard.model.own.ownsEntity.classVO.BodySurveyQuestionsVO;
import com.psg.objectboard.model.service.Other.OtherConexion;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "AnswerRequestProcessServlet", urlPatterns = "/answerrequestprocess")
public class AnswerRequestProcessServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession objSesion = request.getSession();
        String data_user = (String)objSesion.getAttribute("dataUser");
        String data_pasword = (String)objSesion.getAttribute("dataPassword");
        String company_number = (String)objSesion.getAttribute("companyNumber");
        //String company_logo_name = (String)objSesion.getAttribute("companyLogoName");
        //String company_logo_dir = (String)objSesion.getAttribute("companyLogoDirection");

        String acciones = " ";
        if(request.getParameter("p_acciones")!=null){
            acciones=request.getParameter("p_acciones");
        }
        String pantalla = " ";
        if(request.getParameter("p_pantalla")!=null){
            pantalla=request.getParameter("p_pantalla");
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

        String request_main = null;
        if(request.getParameter("p_request_main")!=null) {
            request_main = request.getParameter("p_request_main");
        }
        request.setAttribute("rq_requestMain", request_main);

        ArrayList<Integer> cual_0 = new ArrayList<Integer>();
        ArrayList<String> cual_1 = new ArrayList<String>();

        String answer = null,
               audi_solu = null,
               answer_code = null,
               answ_solu = null,
               only_text =  null,
               only_time = null,
               only_date = null,
               only_number = "0",
               status_rank = "F",
               min_rank = "0",
               max_rank = "0";

        // Recupero los valores necesarios para la accion
        switch(acciones){
            case "delete":
                Integer num_filas = 0;
                String none= null;
                if(request.getParameter("p_cuantos")!=null) {
                    none = request.getParameter("p_cuantos");
                    num_filas = Integer.parseInt(none);
                }
                for(int f=1 ; f < (num_filas + 1);f++){
                    if(request.getParameter("p_selec_"+f)!=null) {
                        none = request.getParameter("p_cual_0"+f);
                        if (cual_0.isEmpty()){
                            cual_0.add(0,Integer.parseInt(none));
                        }else{
                            cual_0.add(Integer.parseInt(none));
                        }
                        none = request.getParameter("p_cual_1"+f);
                        if (cual_1.isEmpty()){
                            cual_1.add(0,none);
                        }else{
                            cual_1.add(none);
                        }
                    }
                }
                break; // break es opcional
            case "create":
            case "save":
                if(request.getParameter("p_answer")!=null){
                    answer=request.getParameter("p_answer");
                }
                if(request.getParameter("p_answ_solu")!=null){
                    answ_solu=request.getParameter("p_answ_solu");
                }
                if(request.getParameter("p_audi_solu")!=null){
                    audi_solu=request.getParameter("p_audi_solu");
                }
                if(request.getParameter("p_answer_code")!=null) {
                    answer_code = request.getParameter("p_answer_code");
                }
                if(request.getParameter("p_answer_only_text")!=null) {
                    only_text = request.getParameter("p_answer_only_text");
                }
                if(request.getParameter("p_answer_only_number")!=null) {
                    only_number = request.getParameter("p_answer_only_number");
                }
                if(request.getParameter("p_answer_only_time")!=null) {
                    only_time = request.getParameter("p_answer_only_time");
                }
                if(request.getParameter("p_answer_only_date")!=null) {
                    only_date = request.getParameter("p_answer_only_date");
                }
                if (audi_solu.equals("T")){
                    status_rank = "F";
                    min_rank = "0";
                    max_rank = "0";
                }else {
                    if (request.getParameter("p_status_rank") != null) {
                        status_rank = request.getParameter("p_status_rank");
                    }
                    if (request.getParameter("p_min") != null) {
                        min_rank = request.getParameter("p_min");
                    }
                    if (request.getParameter("p_max") != null) {
                        max_rank = request.getParameter("p_max");
                    }
                }
                break;
            default :
                System.out.println("Accion solicitada no Existe");
                break;
        }
        // Realizo las acciones solicitadas sobe la base de datos
        BodySurveyAnswersVO cvo = new BodySurveyAnswersVO();
        BodySurveyAnswersDAO cdo = null;
        OtherConexion ocn = new OtherConexion();
        Connection con = null;
        con=ocn.conectarse(data_user,data_pasword);

        try{
            // iniciar transacion
            ocn.init_trans(con);
            BodySurveyQuestionsVO hvo = new BodySurveyQuestionsVO();
            BodySurveyQuestionsDAO hdo = null;
            if (acciones.equals("create")){
                if (request_type.equals("6") || request_type.equals("7") || request_type.equals("8") ||
                    request_type.equals("9")
                ){
                    answ_solu = "T";
                    answer = "";
                }
                cvo.setBodySurveyQuestionsQuestionCode(Long.parseLong(request_code));
                cvo.setBodySurveyQuestionsHeadersSurveySurveyCode(Long.parseLong(code_poll));
                cvo.setBodySurveyQuestionsHeadersSurveyBussinessUnitBuBisCode(Long.parseLong(company_number));
                cvo.setAnswer(answer);
                cvo.setAnswerSolution(answ_solu);
                cvo.setAuditableSolution(audi_solu);
                cvo.setAnswerOnlyText(only_text);
                cvo.setAnswerOnlyNumber(Double.parseDouble(only_number));
                cvo.setAnswerOnlyTime(only_time);
                cvo.setAnswerOnlyDate(only_date);
                cvo.setStatusRank(status_rank);
                cvo.setRankMin(Double.parseDouble(min_rank));
                cvo.setRankMax(Double.parseDouble(max_rank));
                cdo.insertBodySurveyAnswersDAO(cvo,con);
                if (cvo.getResult()){
                    hvo.setQuestionCode(Long.parseLong(request_code));
                    hvo.setHeadersSurveySurveyCode(Long.parseLong(code_poll));
                    hvo.setHeadersSurveyBussinessUnitBuBisCode(Long.parseLong(company_number));
                    if (answ_solu.equals("T"))
                        hdo.updateHeadersSurveyCounterDAO(hvo,1,1,con);
                    else
                        hdo.updateHeadersSurveyCounterDAO(hvo,1,0,con);
                    cvo.setResult(hvo.getResult());
                }
                if (cvo.getResult() && cvo.getAuditableSolution().equals("T") && Integer.parseInt(request_type) > 5
                ){
                    hvo.setStatusRank(status_rank);
                    hdo.updateHeadersSurveyAuditableAnswerSolutionDAO(hvo,"T",con);
                    cvo.setResult(hvo.getResult());
                }
                if (cvo.getResult() && cvo.getStatusRank().equals("T") && Integer.parseInt(request_type) == 7
                ){
                    hvo.setStatusRank(status_rank);
                    hdo.updateHeadersSurveyAuditableAnswerSolutionDAO(hvo,"F",con);
                    cvo.setResult(hvo.getResult());
                }
            }else{
                if (acciones.equals("delete")){
                    cvo.setResult(true);
                    hvo.setQuestionCode(Long.parseLong(request_code));
                    hvo.setHeadersSurveySurveyCode(Long.parseLong(code_poll));
                    hvo.setHeadersSurveyBussinessUnitBuBisCode(Long.parseLong(company_number));
                    for(int x=0 ; x < cual_0.size();x++){
                        if (cvo.getResult()){
                            cvo.setResult(cdo.deleteBodySurveyAnswersDAO(
                                    cual_0.get(x), Integer.parseInt(request_code), Integer.parseInt(code_poll),
                                    Integer.parseInt(company_number),con));
                            if (cvo.getResult()){
                                if (cual_1.get(x).equals("T"))
                                    hdo.updateHeadersSurveyCounterDAO(hvo, -1,-1,con);
                                else
                                    hdo.updateHeadersSurveyCounterDAO(hvo, -1,0,con);
                                cvo.setResult(hvo.getResult());
                            }
                            if (cvo.getResult() &&
                                    Integer.parseInt(request_type) > 5
                            ){
                                hvo.setStatusRank(status_rank);
                                hdo.updateHeadersSurveyAuditableAnswerSolutionDAO(hvo,"F",con);
                            }
                        }
                    }
                }else{
                    cvo.setAnswerCode(Long.parseLong(answer_code));
                    cvo.setBodySurveyQuestionsQuestionCode(Long.parseLong(request_code));
                    cvo.setBodySurveyQuestionsHeadersSurveySurveyCode(Long.parseLong(code_poll));
                    cvo.setBodySurveyQuestionsHeadersSurveyBussinessUnitBuBisCode(Long.parseLong(company_number));
                    cvo.setAnswer(answer);
                    cvo.setAnswerSolution(answ_solu);
                    cvo.setAuditableSolution(audi_solu);
                    cvo.setAnswerOnlyText(only_text);
                    cvo.setAnswerOnlyNumber(Double.parseDouble(only_number));
                    cvo.setAnswerOnlyTime(only_time);
                    cvo.setAnswerOnlyDate(only_date);
                    cvo.setStatusRank(status_rank);
                    cvo.setRankMin(Double.parseDouble(min_rank));
                    cvo.setRankMax(Double.parseDouble(max_rank));
                    if (request_type.equals("6") ||
                        request_type.equals("7") ||
                        request_type.equals("8") ||
                        request_type.equals("9")
                    )
                        cdo.updateBodySurveyAnswersDAO(cvo,Integer.parseInt(request_type),con);
                    else
                        cdo.updateBodySurveyAnswersDAO(cvo,con);
                    if (cvo.getResult()){
                        hvo.setQuestionCode(Long.parseLong(request_code));
                        hvo.setHeadersSurveySurveyCode(Long.parseLong(code_poll));
                        hvo.setHeadersSurveyBussinessUnitBuBisCode(Long.parseLong(company_number));
                        String none = "0";
                        if(request.getParameter("p_change")!=null) {
                            none = request.getParameter("p_change");
                        }
                        hdo.updateHeadersSurveyCounterDAO(hvo,0,Integer.parseInt(none),con);
                        cvo.setResult(hvo.getResult());
                    }
                    if (cvo.getResult() && Integer.parseInt(request_type) > 5
                    ){
                        hvo.setStatusRank(status_rank);
                        if(cvo.getAuditableSolution().equals("T"))
                            hdo.updateHeadersSurveyAuditableAnswerSolutionDAO(hvo,"T",con);
                        else
                            hdo.updateHeadersSurveyAuditableAnswerSolutionDAO(hvo,"F",con);
                    }
                }
            }
            // valida status de la transacion
            ocn.valida_trans(con,cvo.getResult());
            //
            ocn.cierra_coneccion(con);
        }catch (Exception es){
            System.out.println(es.getMessage());
        }

        request.setAttribute("rq_result", cvo.getResult());
        request.setAttribute("rq_pantalla", pantalla);
        //request.setAttribute("rq_companyLogoName", company_logo_name);
        //request.setAttribute("rq_companyLogoDirection", company_logo_dir);

        request.getRequestDispatcher("/WEB-INF/pages/jsp/process/general_process.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("LogoutServlet");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("LogoutServlet");
        }
    }
}
