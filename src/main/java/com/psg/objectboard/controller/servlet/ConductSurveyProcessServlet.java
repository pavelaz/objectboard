package com.psg.objectboard.controller.servlet;

import com.psg.objectboard.model.own.ownsEntity.classDAO.*;
import com.psg.objectboard.model.own.ownsEntity.classVO.BodyConductSurveyVO;
import com.psg.objectboard.model.own.ownsEntity.classVO.BodySurveyQuestionsVO;
import com.psg.objectboard.model.own.ownsEntity.classVO.HeadersSurveyVO;
import com.psg.objectboard.model.own.ownsEntity.classVO.HeaderConductSurveyVO;
import com.psg.objectboard.model.service.Other.DateFunctions;
import com.psg.objectboard.model.service.Other.OtherConexion;
import com.psg.objectboard.model.service.Other.OtherFunctions;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "ConductSurveyProcessServlet", urlPatterns = "/conductsurveyprocess")
@MultipartConfig
public class ConductSurveyProcessServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession objSesion = request.getSession();
        //String user_name = (String)objSesion.getAttribute("userName");
        String data_user = (String)objSesion.getAttribute("dataUser");
        String data_pasword = (String)objSesion.getAttribute("dataPassword");
        String company_number = (String)objSesion.getAttribute("companyNumber");
        //String company_logo_name = (String)objSesion.getAttribute("companyLogoName");
        //String company_logo_dir = (String)objSesion.getAttribute("companyLogoDirection");

        String pantalla = " ";
        if(request.getParameter("p_pantalla")!=null){
            pantalla = request.getParameter("p_pantalla");
        }
        String poll_code = null;
        if(request.getParameter("p_code")!=null){
            poll_code = request.getParameter("p_code");
        }
        String audited = null;
        if(request.getParameter("p_audited")!=null){
            audited = request.getParameter("p_audited");
        }
        String user_email = null;
        if(request.getParameter("p_user_email")!=null){
            user_email = request.getParameter("p_user_email");
        }
        String fecha_hoy = null;
        if(request.getParameter("p_fecha_hoy")!=null){
            fecha_hoy = request.getParameter("p_fecha_hoy");
        }
        String hora_hoy = null;
        if(request.getParameter("p_hora_hoy")!=null){
            hora_hoy = request.getParameter("p_hora_hoy");
        }
        String email_assign = null;
        if(request.getParameter("p_email_assign")!=null){
            email_assign = request.getParameter("p_email_assign");
        }
        String name_assign = null;
        if(request.getParameter("p_name_assign")!=null){
            name_assign = request.getParameter("p_name_assign");
        }
        String auditor = null;
        if(request.getParameter("p_auditor")!=null){
            auditor = request.getParameter("p_auditor");
        }
        String note = "0";
        if(request.getParameter("p_notes")!=null){
            note = request.getParameter("p_notes");
        }
        String legend = "0";
        if(request.getParameter("p_legends")!=null){
            legend = request.getParameter("p_legends");
        }
        String exception = "0";
        if(request.getParameter("p_exceptions")!=null){
            exception = request.getParameter("p_exceptions");
        }
        String term = "0";
        if(request.getParameter("p_terms")!=null){
            term = request.getParameter("p_terms");
        }

        String ptos_raised = "0";
        if(request.getParameter("p_ptos_raised")!=null){
            ptos_raised = request.getParameter("p_ptos_raised");
        }

        BodySurveyQuestionsDAO sdo = new BodySurveyQuestionsDAO();
        sdo.setDataPassword(data_pasword);
        sdo.setDataUser(data_user);
        ArrayList<BodySurveyQuestionsVO> questions;
        String condicion = "headersSurvey_survey_code = " + Long.parseLong(poll_code) +
                           " AND headersSurvey_bussinessUnit_bu_bis_code = " + Long.parseLong(company_number);
        questions = sdo.getListBodySurveyQuestions(condicion);

        OtherFunctions of = new OtherFunctions();
        String none = null;
        String[] response_question_status_rank = new String[questions.size()];
        String[] response_question_min_rank = new String[questions.size()];
        String[] response_question_max_rank = new String[questions.size()];
        Double[] response_question_ptos = new Double[questions.size()];
        Long[] response_question_number = new Long[questions.size()];
        String[] response_question_answer = new String[questions.size()];
        String[] response_question_file_name = new String[questions.size()];
        String[] response_question_comment = new String[questions.size()];
        Part[] response_question_document = new Part[questions.size()];
        Part[] response_question_imagen = new Part[questions.size()];
        Integer[] response_question_type = new Integer[questions.size()];
        String[] response_question_annex_type = new String[questions.size()];
        String[] response_question_solution = new String[questions.size()];
        String[] response_datetime_img = new String[questions.size()];
        DateFunctions df = new DateFunctions();
        //String path = System.getProperty("user.home");
        //String primaryDirectory = "/IdeaProjects/objectboard/src/main/webapp/complements/temporaryfiles/";
        //String secundaryDirectory = "/IdeaProjects/objectboard/src/main/webapp/complements/files/";

        for(int x = 0; x < questions.size(); x++) {
            response_question_status_rank[x] = "F";
            response_question_min_rank[x] = "0";
            response_question_max_rank[x] = "0";
            none = request.getParameter("p_ptos_question_" + (x+1));
            response_question_ptos[x] = Double.parseDouble(none);
            response_question_type[x] = 0;
            response_question_number[x] = Long.parseLong("0");
            response_question_answer[x] = "";
            response_question_comment[x] = "";
            response_question_file_name[x] = "";
            response_question_solution[x] = "";
            response_question_document[x] = null;
            response_question_imagen[x] = null;
            response_question_annex_type[x] = "0";
            if (questions.get(x).getTypeRequest() == 1 ||
                questions.get(x).getTypeRequest() == 2 ||
                questions.get(x).getTypeRequest() == 3 ||
                questions.get(x).getTypeRequest() == 6 ||
                questions.get(x).getTypeRequest() == 7 ||
                questions.get(x).getTypeRequest() == 8 ||
                questions.get(x).getTypeRequest() == 9) {
                if(request.getParameter("p_" + questions.get(x).getQuestionCode())!=null){
                    response_question_type[x] = questions.get(x).getTypeRequest();
                    response_question_number[x] = questions.get(x).getQuestionCode();
                    if (questions.get(x).getTypeRequest() == 3){
                        response_question_answer[x] = request.getParameter("p_mu_" + questions.get(x).getQuestionCode());
                    }else {
                        response_question_answer[x] = request.getParameter("p_" + questions.get(x).getQuestionCode());
                    }
                }
                if(request.getParameter("p_status_rank_" + x+1)!=null){
                    response_question_status_rank[x] = request.getParameter("p_status_rank_" + x+1);
                }
                if(request.getParameter("p_min_rank_" + x+1)!=null){
                    response_question_min_rank[x] = request.getParameter("p_min_rank_" + x+1);
                }
                if(request.getParameter("p_max_rank_" + x+1)!=null){
                    response_question_max_rank[x] = request.getParameter("p_max_rank_" + x+1);
                }
                if(request.getParameter("p_co_" + questions.get(x).getQuestionCode())!=null){
                    response_question_comment[x] = request.getParameter("p_co_" + questions.get(x).getQuestionCode());
                }
                if(request.getParameter("p_ant_" + questions.get(x).getQuestionCode())!=null){
                    response_question_annex_type[x] = request.getParameter("p_ant_" + questions.get(x).getQuestionCode());
                }
                if(request.getParameter("p_as_" + questions.get(x).getQuestionCode())!=null){
                    response_question_solution[x] = request.getParameter("p_as_" + questions.get(x).getQuestionCode());
                }
                if(request.getPart("p_d_" + questions.get(x).getQuestionCode())!=null){
                    response_datetime_img[x] = of.buscaPrefijoToFiles(company_number);
                    response_question_file_name[x]= request.getParameter("p_dn_" + questions.get(x).getQuestionCode());
                    response_question_document[x] = request.getPart("p_d_" + questions.get(x).getQuestionCode());
                    InputStream is = response_question_document[x].getInputStream();
                    File f = new File(of.searchLink("4") + response_datetime_img[x] + response_question_file_name[x]);
                    OtherFunctions.subirArchivos(is, f);
                }
                if(request.getParameter("p_in_" + questions.get(x).getQuestionCode())!=null){
                    response_datetime_img[x] = of.buscaPrefijoToFiles(company_number);
                    response_question_file_name[x] = request.getParameter("p_in_" + questions.get(x).getQuestionCode());
                    response_question_imagen[x] = request.getPart("p_i_" + questions.get(x).getQuestionCode());
                    InputStream is = response_question_imagen[x].getInputStream();
                    File f = new File(of.searchLink("4") + response_datetime_img[x] + response_question_file_name[x]);
                    OtherFunctions.subirArchivos(is, f);
                }
            }
            if (questions.get(x).getTypeRequest() == 4 || questions.get(x).getTypeRequest() == 5) {
                response_question_type[x] = questions.get(x).getTypeRequest();
                response_question_number[x] = questions.get(x).getQuestionCode();
                if(request.getParameter("p_co_" + questions.get(x).getQuestionCode())!=null){
                    response_question_comment[x] = request.getParameter("p_co_" + questions.get(x).getQuestionCode());
                }
                if(request.getParameter("p_ant_" + questions.get(x).getQuestionCode())!=null){
                    response_question_annex_type[x] = request.getParameter("p_ant_" + questions.get(x).getQuestionCode());
                }
                if(request.getParameter("p_as_" + questions.get(x).getQuestionCode())!=null){
                    response_question_solution[x] = request.getParameter("p_as_" + questions.get(x).getQuestionCode());
                }
                if(request.getParameter("p_d_" + questions.get(x).getQuestionCode())!=null){
                    response_datetime_img[x] = of.buscaPrefijoToFiles(company_number);
                    response_question_file_name[x] = request.getParameter("p_dn_" + questions.get(x).getQuestionCode());
                    response_question_document[x] = request.getPart("p_d_" + questions.get(x).getQuestionCode());
                    InputStream is = response_question_document[x].getInputStream();
                    File f = new File(of.searchLink("4") + response_datetime_img[x] + response_question_file_name[x]);
                    OtherFunctions.subirArchivos(is, f);
                }
                if(request.getParameter("p_in_" + questions.get(x).getQuestionCode())!=null){
                    response_datetime_img[x] = of.buscaPrefijoToFiles(company_number);
                    response_question_file_name[x] = request.getParameter("p_in_" + questions.get(x).getQuestionCode());
                    response_question_imagen[x] = request.getPart("p_i_" + questions.get(x).getQuestionCode());
                    InputStream is = response_question_imagen[x].getInputStream();
                    File f = new File(of.searchLink("4") + response_datetime_img[x] + response_question_file_name[x]);
                    OtherFunctions.subirArchivos(is, f);
                }
            }
        }

        // Realizo las acciones solicitadas sobe la base de datos
        HeaderConductSurveyVO cvo = new HeaderConductSurveyVO();
        HeaderConductSurveyDAO cdo = new HeaderConductSurveyDAO();
        OtherConexion ocn = new OtherConexion();
        Connection con = null;
        con=ocn.conectarse(data_user,data_pasword);

        try{
           int conformidades = 0,
                   disconformidades = 0;
           Double ptos_acu = Double.parseDouble("0");
            // iniciar transacion
            ocn.init_trans(con);
            // crear header
            cvo.setHeadersSurvey_survey_code(Long.parseLong(poll_code));
            cvo.setHeadersSurvey_bussinessUnit_bu_bis_code(Long.parseLong(company_number));
            cvo.setMasterUser_mu_email(email_assign);
            cvo.setConduct_date(fecha_hoy + " " + hora_hoy);
            cvo.setMasterUser_bussinessUnit_bu_bis_code(Long.parseLong(company_number));
            cvo.setAuditable(audited);
            assert audited != null;
            if (audited.equals("T"))
                cvo.setTo_audited("T");
            else
                cvo.setTo_audited("F");
            cvo.setUser_auditor(auditor);
            cvo.setUser_conduct(user_email);
            cvo.setNotes_code(Long.parseLong(note));
            cvo.setLegends_code(Long.parseLong(legend));
            cvo.setExceptions_code(Long.parseLong(exception));
            cvo.setTerms_code(Long.parseLong(term));
            cvo.setConformities(conformidades);
            cvo.setNon_conformities(disconformidades);
            cvo.setTotalPointsRaised(Double.parseDouble(ptos_raised));
            cvo.setTotalPointsAchieved(Double.parseDouble("0"));

            cdo.insertHeaderConductSurveyDAO(cvo,con);

            condicion = "headersSurvey_survey_code = " + Long.parseLong(poll_code) +
                        " AND headersSurvey_bussinessUnit_bu_bis_code = " + Long.parseLong(company_number) +
                        " AND masterUser_mu_email = '" + email_assign +
                        "' AND conduct_date = '" + cvo.getConduct_date() + "'";
            Long conduc_number = null;
            conduc_number = cdo.getLastIde(condicion,con);

            BodyConductSurveyVO bvo = new BodyConductSurveyVO();
            BodyConductSurveyDAO bdo = null;
            //OtherFunctions of = new OtherFunctions();
            //String directorio = email_assign.replace("@","_");
            //       directorio = directorio.replace(".","_") + "_" + Long.parseLong(company_number);
            //of.CrearDirectorio (path + secundaryDirectory + directorio);
            //of.CrearDirectorio (path + secundaryDirectory + directorio + "/document");
            //of.CrearDirectorio (path + secundaryDirectory + directorio + "/image");

            if (cvo.getResult()){
                bvo.setResult(true);
                for (int x = 0; x< response_question_type.length; x++){
                    if (bvo.getResult()) {
                        bvo.setHeaderConductSurveyConductId(conduc_number);
                        bvo.setBsaBodySurveyQuestionsQuestionCode(response_question_number[x]);
                        bvo.setBsaBodySurveyQuestionsHeadersSurveySurveyCode(Long.parseLong(poll_code));
                        bvo.setBsaBodySurveyQuestionsHeadersSurveyBussinessUnitBuBisCode(Long.parseLong(company_number));
                        bvo.setBcsTypeRequest(response_question_type[x]);
                        if (bvo.getBcsTypeRequest() == 6)
                            bvo.setBcsAnswerOnlyText(response_question_answer[x]);
                        else
                            bvo.setBcsAnswerOnlyText("");
                        if (bvo.getBcsTypeRequest() == 7)
                            bvo.setBcsAnswerOnlyNumber(Double.parseDouble(response_question_answer[x]));
                        else
                            bvo.setBcsAnswerOnlyNumber(Double.parseDouble("0"));
                        if (bvo.getBcsTypeRequest() == 8)
                            bvo.setBcsAnswerOnlyTime(response_question_answer[x]);
                        else
                            bvo.setBcsAnswerOnlyTime("");
                        if (bvo.getBcsTypeRequest() == 9)
                            bvo.setBcsAnswerOnlyDate(response_question_answer[x]);
                        else
                            bvo.setBcsAnswerOnlyDate("");

                        bvo.setBcsComment(response_question_comment[x]);
                        bvo.setBcsAnswer(response_question_answer[x]);
                        bvo.setBcsAnnexType(response_question_annex_type[x]);

                        if (!bvo.getBcsAnnexType().equals("0")) {
                            bvo.setBcsNameAnnexFile(response_question_file_name[x]);
                            bvo.setRutaAnnex(of.searchLink("4") + response_datetime_img[x] + bvo.getBcsNameAnnexFile());
                        }else {
                            bvo.setBcsNameAnnexFile("no_image.jpeg");
                            bvo.setRutaAnnex(of.searchLink("0") + "img/" + bvo.getBcsNameAnnexFile());
                        }

                        bvo.setBcsAnswerSolution(response_question_solution[x]);
                        bvo.setStatusRank(response_question_status_rank[x]);
                        bvo.setRankMin(Double.parseDouble(response_question_min_rank[x]));
                        bvo.setRankMax(Double.parseDouble(response_question_max_rank[x]));
                        // aqui quede
                        // Auditoria automatica
                        if (!cvo.getAuditable().equals("T")){
                            if (bvo.getBcsTypeRequest() == 7){
                                if (bvo.getStatusRank().equals("F")){
                                    if(bvo.getBcsAnswer().equals(bvo.getBcsAnswerSolution())){
                                        conformidades = conformidades + 1;
                                        ptos_acu = ptos_acu + response_question_ptos[x];
                                        bvo.setStatusAudit("T");
                                        bvo.setResultAudit("T");
                                    }else{
                                        disconformidades = disconformidades + 1;
                                        bvo.setStatusAudit("T");
                                        bvo.setResultAudit("F");
                                    }
                                }else{
                                    if (Double.parseDouble(bvo.getBcsAnswer()) >= bvo.getRankMin() &&
                                        Double.parseDouble(bvo.getBcsAnswer()) <= bvo.getRankMax()){
                                            conformidades = conformidades + 1;
                                            ptos_acu = ptos_acu + response_question_ptos[x];
                                            bvo.setStatusAudit("T");
                                            bvo.setResultAudit("T");
                                    }else{
                                            disconformidades = disconformidades + 1;
                                            bvo.setStatusAudit("T");
                                            bvo.setResultAudit("F");
                                    }
                                }
                            }else{
                                if(bvo.getBcsAnswer().equals(bvo.getBcsAnswerSolution())){
                                    conformidades = conformidades + 1;
                                    ptos_acu = ptos_acu + response_question_ptos[x];
                                    bvo.setStatusAudit("T");
                                    bvo.setResultAudit("T");
                                }else{
                                    disconformidades = disconformidades + 1;
                                    bvo.setStatusAudit("T");
                                    bvo.setResultAudit("F");
                                }
                            }
                        }else{
                            bvo.setStatusAudit("F"); //*
                            bvo.setResultAudit("F"); //*
                        }
                        bvo.setPoints(response_question_ptos[x]);

                        bvo.setAuditNote("");

                        bdo.insertBodyConductSurveyDAO(bvo, con);

                    }
                }
                cvo.setResult(bvo.getResult());
            }

            if (cvo.getResult()){
                //String none = null;
                for (int x = 0; x< response_question_type.length; x++) {
                    if (!response_question_file_name[x].equals("")) {
                       /*if (response_question_annex_type[x].equals("1")){
                          of.copiarArchivos(path + primaryDirectory,
                                  path + secundaryDirectory + directorio + "/document/",
                                   response_question_file_name[x]);
                       }
                       if (response_question_annex_type[x].equals("2")){
                            of.copiarArchivos(path + primaryDirectory,
                                    path + secundaryDirectory + directorio + "/image/",
                                    response_question_file_name[x]);
                       }*/
                       none = of.searchLink("4") + response_datetime_img[x] + response_question_file_name[x];
                       File fichero = new File(none);
                       bvo.setResult(of.eliminarFichero(fichero)); // en este caso no hago nada si ha sido borrado previamente o no existe.
                    }
                }
            }

            if (cvo.getResult()){
                HeadersSurveyDAO hdo = new HeadersSurveyDAO();
                HeadersSurveyVO  hvo = new HeadersSurveyVO();
                hvo.setSurveyCode(Long.parseLong(poll_code));
                hvo.setBussinessUnitBuBisCode(Long.parseLong(company_number));
                hvo.setExecutionStatus("T");
                hdo.updateExcecutionHeadersSurveyDAO(hvo,con);
                cvo.setResult(hvo.getResult());
            }

            if (cvo.getResult()) {
                if (!cvo.getAuditable().equals("T")) {
                    cvo.setConformities(conformidades);
                    cvo.setNon_conformities(disconformidades);
                    cvo.setConduct_id(conduc_number);
                    cvo.setTotalPointsAchieved(ptos_acu);
                    cdo.updateEvaluadionAutoHeaderConductSurveyDAO(cvo,con);
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
        request.setAttribute("rq_companyNumber", company_number);
        BussinessUnitDAO bud = new BussinessUnitDAO();
        request.setAttribute("rq_format", bud.searchLogoName(company_number,data_user,data_pasword,1));

        request.getRequestDispatcher("/WEB-INF/pages/jsp/process/general_process.jsp").forward(request, response);
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
