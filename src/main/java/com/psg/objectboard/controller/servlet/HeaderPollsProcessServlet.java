package com.psg.objectboard.controller.servlet;

import com.psg.objectboard.model.own.ownsEntity.classDAO.BodySurveyAnswersDAO;
import com.psg.objectboard.model.own.ownsEntity.classDAO.BodySurveyQuestionsDAO;
import com.psg.objectboard.model.own.ownsEntity.classDAO.HeadersSurveyDAO;
import com.psg.objectboard.model.own.ownsEntity.classVO.BodySurveyAnswersVO;
import com.psg.objectboard.model.own.ownsEntity.classVO.BodySurveyQuestionsVO;
import com.psg.objectboard.model.own.ownsEntity.classVO.HeadersSurveyVO;
import com.psg.objectboard.model.service.Other.DateFunctions;
import com.psg.objectboard.model.service.Other.OtherConexion;
import com.psg.objectboard.model.service.Other.OtherFunctions;

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

@WebServlet(name = "HeaderPollsProcessServlet", urlPatterns = "/headerpollsprocess")
public class HeaderPollsProcessServlet extends HttpServlet {
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

        String name = null,
                code = null,
               reference = null,
               level1 = null,
               level2 = null,
               level3 = null,
               typif1 = null,
               typif2 = null,
               typif3 = null,
               level4 = null;

        ArrayList<Integer> cual_0 = new ArrayList<Integer>();

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
                    }
                }
                break; // break es opcional
            case "create":
            case "save":
                if(request.getParameter("p_code")!=null){
                    code=request.getParameter("p_code");
                }
                if(request.getParameter("p_name")!=null){
                    name=request.getParameter("p_name");
                }
                if(request.getParameter("p_refe")!=null){
                    reference=request.getParameter("p_refe");
                }
                if(request.getParameter("p_level1")!=null) {
                    level1 = request.getParameter("p_level1");
                }
                if(request.getParameter("p_level2")!=null) {
                    level2 = request.getParameter("p_level2");
                }
                if(request.getParameter("p_level3")!=null) {
                    level3 = request.getParameter("p_level3");
                }
                if(request.getParameter("p_level4")!=null) {
                    level4 = request.getParameter("p_level4");
                }
                if(request.getParameter("p_typif1")!=null) {
                    typif1 = request.getParameter("p_typif1");
                }
                if(request.getParameter("p_typif2")!=null) {
                    typif2 = request.getParameter("p_typif2");
                }
                if(request.getParameter("p_typif3")!=null) {
                    typif3 = request.getParameter("p_typif3");
                }
                break;
            case "close":
            case "open":
            case "copy":
                if(request.getParameter("p_code")!=null){
                    code=request.getParameter("p_code");
                }
                break;
            default :
                System.out.println("Accion solicitada no Existe");
                break;
        }
        // Realizo las acciones solicitadas sobe la base de datos
        HeadersSurveyVO cvo = new HeadersSurveyVO();
        HeadersSurveyDAO cdo = new HeadersSurveyDAO();
        OtherConexion ocn = new OtherConexion();
        Connection con = null;
        con=ocn.conectarse(data_user,data_pasword);

        DateFunctions df = new DateFunctions();
        String fecha = null;
        fecha = df.fechaFull(9);
        Integer question_tipo = 0;

        BodySurveyQuestionsVO bsqvo= new BodySurveyQuestionsVO();
        BodySurveyQuestionsDAO bsqdo = new BodySurveyQuestionsDAO();
        ArrayList<BodySurveyQuestionsVO> questions = null;
        bsqdo.setDataUser(data_user);
        bsqdo.setDataPassword(data_pasword);

        BodySurveyAnswersVO bsavo= new BodySurveyAnswersVO();
        BodySurveyAnswersDAO bsado = new BodySurveyAnswersDAO();
        ArrayList<BodySurveyAnswersVO> answers = null;
        bsado.setDataUser(data_user);
        bsado.setDataPassword(data_pasword);

        try{
            // iniciar transacion
            ocn.init_trans(con);
            switch(acciones){
                case "create":
                    cvo.setBussinessUnitBuBisCode(Long.parseLong(company_number));
                    cvo.setName(name);
                    cvo.setReferences(reference);
                    cvo.setSurveyStatus("T");
                    cvo.setExecutionStatus("F");
                    cvo.setTotalQuestions(0);
                    cvo.setDateCreation(fecha);
                    cvo.setVersion(1);
                    cvo.setTypifiedBussinessUnitBuBisCode(Long.parseLong(company_number));
                    cvo.setTypifiedCtypifiedCode1(typif1);
                    cvo.setTypifiedCtypifiedCode2(typif2);
                    cvo.setTypifiedCtypifiedCode3(typif3);
                    cvo.setOrganizationBussinessUnitBuBisCode(Long.parseLong(company_number));
                    cvo.setOrganizationLevel1(level1);
                    cvo.setOrganizationLevel2(level2);
                    cvo.setOrganizationLevel3(level3);
                    cvo.setOrganizationLevel4(level4);
                    cvo.setDateLastModification(fecha);
                    cvo.setAudited("F");
                    cvo.setTotalPoints(Double.parseDouble("0"));
                    cdo.insertHeadersSurveyDAO(cvo,con);
                    break;
                case "delete":
                    cvo.setResult(true);
                    for(int x=0 ; x < cual_0.size();x++){
                        if (cvo.getResult()){
                            cvo.setResult(cdo.deleteHeadersSurveyDAO(Integer.parseInt(company_number),
                                    cual_0.get(x),
                                    con));
                        }
                    }
                    break;
                case "save":
                    cvo.setBussinessUnitBuBisCode(Long.parseLong(company_number));
                    cvo.setSurveyCode(Long.parseLong(code));
                    cvo.setName(name);
                    cvo.setReferences(reference);
                    cvo.setTypifiedBussinessUnitBuBisCode(Long.parseLong(company_number));
                    cvo.setTypifiedCtypifiedCode1(typif1);
                    cvo.setTypifiedCtypifiedCode2(typif2);
                    cvo.setTypifiedCtypifiedCode3(typif3);
                    cvo.setOrganizationBussinessUnitBuBisCode(Long.parseLong(company_number));
                    cvo.setOrganizationLevel1(level1);
                    cvo.setOrganizationLevel2(level2);
                    cvo.setOrganizationLevel3(level3);
                    cvo.setOrganizationLevel4(level4);
                    cvo.setDateLastModification(fecha);
                    cdo.updateHeadersSurveyDAO(cvo,con);
                    break;
                case "close":
                    Integer question_ctos = 0;
                    cvo.setResult(true);
                    // valida las preguntas tipo 1 - booleanas
                    String condicion = "headersSurvey_survey_code = " + Integer.parseInt(code) +
                           " AND headersSurvey_bussinessUnit_bu_bis_code = " + Integer.parseInt(company_number) +
                           " AND type_request = 1 AND (answer_number <> 2 OR solution_number <> 1)";
                    questions = bsqdo.getListBodySurveyQuestions(condicion);
                    if(questions.size() != 0){
                        question_tipo = 1;
                        question_ctos = questions.size();
                        cvo.setResult(false);
                    }
                    if (cvo.getResult()){
                        // valida las preguntas tipo 2 - Unica seleccion
                        condicion = "headersSurvey_survey_code = " + Integer.parseInt(code) +
                                " AND headersSurvey_bussinessUnit_bu_bis_code = " + Integer.parseInt(company_number) +
                                " AND type_request = 2 AND (answer_number < 3 OR solution_number <> 1)";
                        questions = bsqdo.getListBodySurveyQuestions(condicion);
                        if(questions.size() != 0){
                            question_tipo = 2;
                            question_ctos = questions.size();
                            cvo.setResult(false);
                        }
                    }
                    if (cvo.getResult()){
                        // valida las preguntas tipo 3 - Multi seleccion
                        condicion = "headersSurvey_survey_code = " + Integer.parseInt(code) +
                                " AND headersSurvey_bussinessUnit_bu_bis_code = " + Integer.parseInt(company_number) +
                                " AND type_request = 3 AND (answer_number < 3 OR solution_number = 1 OR" +
                                " answer_number = solution_number )";
                        questions = bsqdo.getListBodySurveyQuestions(condicion);
                        if(questions.size() != 0){
                            question_tipo = 3;
                            question_ctos = questions.size();
                            cvo.setResult(false);
                        }
                    }
                    if (cvo.getResult()){
                        // valida las preguntas tipo 6 - Input Text
                        condicion = "headersSurvey_survey_code = " + Integer.parseInt(code) +
                                " AND headersSurvey_bussinessUnit_bu_bis_code = " + Integer.parseInt(company_number) +
                                " AND type_request = 6 AND (answer_number = 0 OR solution_number = 0 )";
                        questions = bsqdo.getListBodySurveyQuestions(condicion);
                        if(questions.size() != 0){
                            question_tipo = 6;
                            question_ctos = questions.size();
                            cvo.setResult(false);
                        }
                    }
                    if (cvo.getResult()){
                        // valida las preguntas tipo 7 - Input Number
                        condicion = "headersSurvey_survey_code = " + Integer.parseInt(code) +
                                " AND headersSurvey_bussinessUnit_bu_bis_code = " + Integer.parseInt(company_number) +
                                " AND type_request = 7 AND (answer_number = 0 OR solution_number = 0 )";
                        questions = bsqdo.getListBodySurveyQuestions(condicion);
                        if(questions.size() != 0){
                            question_tipo = 7;
                            question_ctos = questions.size();
                            cvo.setResult(false);
                        }
                    }
                    if (cvo.getResult()){
                        // valida las preguntas tipo 8 - Input Time
                        condicion = "headersSurvey_survey_code = " + Integer.parseInt(code) +
                                " AND headersSurvey_bussinessUnit_bu_bis_code = " + Integer.parseInt(company_number) +
                                " AND type_request = 8 AND (answer_number = 0 OR solution_number = 0 )";
                        questions = bsqdo.getListBodySurveyQuestions(condicion);
                        if(questions.size() != 0){
                            question_tipo = 8;
                            question_ctos = questions.size();
                            cvo.setResult(false);
                        }
                    }
                    if (cvo.getResult()){
                        // valida las preguntas tipo 9 - Input Date
                        condicion = "headersSurvey_survey_code = " + Integer.parseInt(code) +
                                " AND headersSurvey_bussinessUnit_bu_bis_code = " + Integer.parseInt(company_number) +
                                " AND type_request = 9 AND (answer_number = 0 OR solution_number = 0 )";
                        questions = bsqdo.getListBodySurveyQuestions(condicion);
                        if(questions.size() != 0){
                            question_tipo = 9;
                            question_ctos = questions.size();
                            cvo.setResult(false);
                        }
                    }
                    if (cvo.getResult()){
                        cvo.setBussinessUnitBuBisCode(Long.parseLong(company_number));
                        cvo.setSurveyCode(Long.parseLong(code));
                        cvo.setSurveyStatus("F");
                        cdo.updateCloseOpenHeadersSurveyDAO(cvo, con);
                    }
                    if (cvo.getResult()){
                        condicion = "headersSurvey_survey_code = " + Integer.parseInt(code) +
                                " AND headersSurvey_bussinessUnit_bu_bis_code = " + Integer.parseInt(company_number) +
                                " AND (auditable_solution = 'T' OR auditable_answer_solution = 'T')";
                        questions = bsqdo.getListBodySurveyQuestions(condicion);
                        if (questions.size() !=0){
                            cvo.setBussinessUnitBuBisCode(Long.parseLong(company_number));
                            cvo.setSurveyCode(Long.parseLong(code));
                            cvo.setAudited("T");
                            cdo.updateAuditedHeadersSurveyDAO(cvo, con);
                        }
                    }
                    request.setAttribute("rq_questionTipo", question_tipo);
                    request.setAttribute("rq_questionCtos", question_ctos);
                    break;
                case "open":
                    cvo.setBussinessUnitBuBisCode(Long.parseLong(company_number));
                    cvo.setSurveyCode(Long.parseLong(code));
                    cvo.setSurveyStatus("T");
                    cdo.updateCloseOpenHeadersSurveyDAO(cvo, con);
                    break;
                case "copy":
                    // crear copia de encuesta
                    String new_name = null;
                    Long new_survey_code = null;
                    new_survey_code = cdo.searchNextHeaderSurveyCode(con,Integer.parseInt(company_number));
                    condicion = "survey_code = " +
                            Long.parseLong(code) +
                            " AND bussinessUnit_bu_bis_code = " +
                            Long.parseLong(company_number);
                    ArrayList<HeadersSurveyVO> survey = null;
                    survey = cdo.getListHeadersSurvey(condicion,con);
                    cvo.setSurveyCode(new_survey_code);
                    cvo.setBussinessUnitBuBisCode(survey.get(0).getBussinessUnitBuBisCode());
                    if (survey.get(0).getName().length() > 38 ){
                        new_name = survey.get(0).getName().substring(0,38) + " (COPY)";
                    }else{
                        new_name = survey.get(0).getName().substring(0,survey.get(0).getName().length()) + " (COPY)";
                    }
                    cvo.setName(new_name);
                    OtherFunctions of = new OtherFunctions();
                    String referencia = of.generateRandomString(8);
                    cvo.setReferences(referencia);
                    cvo.setTotalQuestions(survey.get(0).getTotalQuestions());
                    cvo.setSurveyStatus("T");
                    cvo.setExecutionStatus("F");
                    cvo.setVersion(survey.get(0).getVersion()+1);
                    cvo.setDateCreation(fecha);
                    cvo.setTypifiedBussinessUnitBuBisCode(survey.get(0).getTypifiedBussinessUnitBuBisCode());
                    cvo.setTypifiedCtypifiedCode1(survey.get(0).getTypifiedCtypifiedCode1());
                    cvo.setTypifiedCtypifiedCode2(survey.get(0).getTypifiedCtypifiedCode2());
                    cvo.setTypifiedCtypifiedCode3(survey.get(0).getTypifiedCtypifiedCode3());
                    cvo.setOrganizationBussinessUnitBuBisCode(survey.get(0).getOrganizationBussinessUnitBuBisCode());
                    cvo.setOrganizationLevel1(survey.get(0).getOrganizationLevel1());
                    cvo.setOrganizationLevel2(survey.get(0).getOrganizationLevel2());
                    cvo.setOrganizationLevel3(survey.get(0).getOrganizationLevel3());
                    cvo.setOrganizationLevel4(survey.get(0).getOrganizationLevel4());
                    cvo.setDateLastModification(fecha);
                    cvo.setAudited("F");
                    cvo.setTotalPoints(survey.get(0).getTotalPoints());
                    cdo.insertHeadersSurveyCopyDAO(cvo,con);

                    // crear copia de preguntas de encuesta
                    if (cvo.getResult()) {
                        // crea copia de respuestas a preguntas de la encuesta
                        condicion = "headersSurvey_survey_code = " +
                                Long.parseLong(code) +
                                " AND headersSurvey_bussinessUnit_bu_bis_code = " +
                                Long.parseLong(company_number);
                        questions = bsqdo.getListBodySurveyQuestions(condicion,con);
                        bsqvo.setResult(true);
                        Integer cta_question = 0;
                        for (Integer x = 0; x < questions.size(); x++) {
                            if (bsqvo.getResult()) {
                                cta_question = cta_question + 1;
                                bsqvo.setQuestionCode(Long.parseLong(String.valueOf(cta_question)));
                                bsqvo.setHeadersSurveySurveyCode(new_survey_code);
                                bsqvo.setHeadersSurveyBussinessUnitBuBisCode(questions.get(x).getHeadersSurveyBussinessUnitBuBisCode());
                                bsqvo.setTypeRequest(questions.get(x).getTypeRequest());
                                bsqvo.setMainRequest(questions.get(x).getMainRequest());
                                bsqvo.setAnnexType(questions.get(x).getAnnexType());
                                bsqvo.setBodyAnnexDoc(questions.get(x).getBodyAnnexDoc());
                                bsqvo.setBodyAnnexPhoto(questions.get(x).getBodyAnnexPhoto());
                                bsqvo.setComment(questions.get(x).getComment());
                                bsqvo.setAnswerNumber(questions.get(x).getAnswerNumber());
                                bsqvo.setSolutionNumber(questions.get(x).getSolutionNumber());
                                bsqvo.setAuditableSolution(questions.get(x).getAuditableSolution());
                                bsqvo.setAuditableAnswerSolution(questions.get(x).getAuditableAnswerSolution());
                                bsqvo.setQuestionPoints(questions.get(x).getQuestionPoints());
                                bsqvo.setStatusRank(questions.get(x).getStatusRank());
                                bsqdo.insertBodySurveyQuestionsCopyDAO(bsqvo, con);

                                if (bsqvo.getResult()){
                                    if (cvo.getResult()) {
                                        condicion = "bodySurveyQuestions_question_code = " +
                                                questions.get(x).getQuestionCode() +
                                                " AND bodySurveyQuestions_headersSurvey_survey_code = " +
                                                Long.parseLong(code) +
                                                " AND bodySurveyQuestions_headersSurvey_bussinessUnit_bu_bis_code = " +
                                                Long.parseLong(company_number);
                                        answers = bsado.getListBodySurveyAnswers(condicion);
                                        bsavo.setResult(true);
                                        Integer cta_answer = 0;
                                        for (Integer y = 0; y < answers.size(); y++) {
                                            if (bsavo.getResult()) {
                                                cta_answer = cta_answer + 1;
                                                bsavo.setAnswerCode(Long.parseLong(String.valueOf(cta_answer)));
                                                bsavo.setBodySurveyQuestionsQuestionCode(Long.parseLong(String.valueOf(cta_question)));
                                                bsavo.setBodySurveyQuestionsHeadersSurveySurveyCode(new_survey_code);
                                                bsavo.setBodySurveyQuestionsHeadersSurveyBussinessUnitBuBisCode(answers.get(y).getBodySurveyQuestionsHeadersSurveyBussinessUnitBuBisCode());
                                                bsavo.setAnswer(answers.get(y).getAnswer());
                                                bsavo.setAnswerSolution(answers.get(y).getAnswerSolution());
                                                bsavo.setAuditableSolution(answers.get(y).getAuditableSolution());
                                                bsavo.setAnswerOnlyNumber(answers.get(y).getAnswerOnlyNumber());
                                                bsavo.setAnswerOnlyTime(answers.get(y).getAnswerOnlyTime());
                                                bsavo.setAnswerOnlyText(answers.get(y).getAnswerOnlyText());
                                                bsavo.setAnswerOnlyDate(answers.get(y).getAnswerOnlyDate());
                                                bsavo.setStatusRank(answers.get(y).getStatusRank());
                                                bsavo.setRankMin(answers.get(y).getRankMin());
                                                bsavo.setRankMax(answers.get(y).getRankMax());

                                                bsado.insertBodySurveyAnswersCopyDAO(bsavo, con);
                                            }
                                        }
                                        bsqvo.setResult(bsavo.getResult());
                                    }
                                }
                            }
                        }
                        cvo.setResult(bsqvo.getResult());
                    }
                    break;
                default :
                    System.out.println("proceso solicitado o Existe");
                    break;
            }

            // valida status de la transacion
            ocn.valida_trans(con,cvo.getResult());
            //
            ocn.cierra_coneccion(con);
        }catch (Exception es){
            System.out.println(es.getMessage());
        }

        request.setAttribute("rq_questionTipo", question_tipo);
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
