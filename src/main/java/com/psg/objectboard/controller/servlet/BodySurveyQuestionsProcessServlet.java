package com.psg.objectboard.controller.servlet;

import com.psg.objectboard.model.own.ownsEntity.classDAO.BodySurveyAnswersDAO;
import com.psg.objectboard.model.own.ownsEntity.classDAO.BodySurveyQuestionsDAO;
import com.psg.objectboard.model.own.ownsEntity.classDAO.HeadersSurveyDAO;
import com.psg.objectboard.model.own.ownsEntity.classVO.BodySurveyAnswersVO;
import com.psg.objectboard.model.own.ownsEntity.classVO.BodySurveyQuestionsVO;
import com.psg.objectboard.model.own.ownsEntity.classVO.HeadersSurveyVO;
import com.psg.objectboard.model.service.Other.OtherConexion;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "BodySurveyQuestionsProcessServlet", urlPatterns = "/bodysurveyquestionsprocess")
@MultipartConfig(maxFileSize = 16177215)    // upload file's size up to 16MB
public class BodySurveyQuestionsProcessServlet extends HttpServlet {
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

        String type = null,
                bdoc = null,
                bimg = null,
                annex_type = null,
                comment = null,
                request_code = null,
                main = null,
                puntos = null,
                puntos_old = null,
                rank = null;

        ArrayList<Integer> cual_0 = new ArrayList<Integer>();
        Double total_ptos =null;
        total_ptos = Double.parseDouble("0");

        // Recupero los valores necesarios para la accion
        switch(acciones){
            case "delete":
                int num_filas = 0;
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
                        none = request.getParameter("p_ptos_"+f);
                        total_ptos = total_ptos + Double.parseDouble(none);
                    }
                }
                break; // break es opcional
            case "create":
            case "save":
                if(request.getParameter("p_type")!=null){
                    type=request.getParameter("p_type");
                }
                if(request.getParameter("p_bdoc")!=null){
                    bdoc=request.getParameter("p_bdoc");
                }
                if(request.getParameter("p_bimg")!=null){
                    bimg=request.getParameter("p_bimg");
                }
                if(request.getParameter("p_annex_type")!=null){
                    annex_type=request.getParameter("p_annex_type");
                }
                if(request.getParameter("p_comment")!=null){
                    comment=request.getParameter("p_comment");
                }
                if(request.getParameter("p_main")!=null){
                    main=request.getParameter("p_main");
                }
                if(request.getParameter("p_request_code")!=null){
                    request_code=request.getParameter("p_request_code");
                }
                if(request.getParameter("p_puntos")!=null){
                    puntos=request.getParameter("p_puntos");
                }
                if(request.getParameter("p_puntos_old")!=null){
                    puntos_old=request.getParameter("p_puntos_old");
                }
                if(request.getParameter("p_status_rank")!=null){
                    rank =request.getParameter("p_status_rank");
                }
                break;
            default :
                System.out.println("Accion solicitada no Existe");
                break;
        }
        // Realizo las acciones solicitadas sobe la base de datos
        BodySurveyQuestionsVO cvo = new BodySurveyQuestionsVO();
        BodySurveyQuestionsDAO cdo = new BodySurveyQuestionsDAO();
        OtherConexion ocn = new OtherConexion();
        Connection con = null;
        con=ocn.conectarse(data_user,data_pasword);

        try{
            // iniciar transacion
            ocn.init_trans(con);
            if (acciones.equals("create")){
                int tipo = Integer.parseInt(type);
                cvo.setHeadersSurveySurveyCode(Long.parseLong(code_poll));
                cvo.setHeadersSurveyBussinessUnitBuBisCode(Long.parseLong(company_number));
                cvo.setTypeRequest(Integer.parseInt(type));
                cvo.setMainRequest(main);
                cvo.setAnnexType(annex_type);
                cvo.setBodyAnnexDoc(bdoc);
                cvo.setBodyAnnexPhoto(bimg);
                cvo.setComment(comment);
                if(tipo == 4 || tipo == 5){
                    cvo.setAnswerNumber(1);
                    cvo.setSolutionNumber(1);
                }else {
                    cvo.setAnswerNumber(0);
                    cvo.setSolutionNumber(0);
                }
                cvo.setQuestionPoints(Double.parseDouble(puntos));
                cvo.setStatusRank(rank);

                cdo.insertBodySurveyQuestionsDAO(cvo,con);

                if (cvo.getResult()){ // suma nuevos puntos
                    HeadersSurveyVO hvo = new HeadersSurveyVO();
                    HeadersSurveyDAO hdo = new HeadersSurveyDAO();
                    hvo.setTotalPoints(cvo.getQuestionPoints());
                    hvo.setSurveyCode(cvo.getHeadersSurveySurveyCode());
                    hvo.setBussinessUnitBuBisCode(cvo.getHeadersSurveyBussinessUnitBuBisCode());
                    hdo.updatePointsHeadersSurveyDAO(hvo,con,"+");
                    cvo.setResult(hvo.getResult());
                }

                if (cvo.getResult()) {
                    if (tipo == 4 || tipo == 5) {
                        // BUSCAR RECIEN INSERTADO
                        String condicion = "headersSurvey_survey_code = " + Long.parseLong(code_poll) +
                                " AND headersSurvey_bussinessUnit_bu_bis_code = " + Long.parseLong(company_number) +
                                " AND type_request = " + Integer.parseInt(type) +
                                " AND main_request = '" + main + "' AND Annex_type = '" + annex_type + "'" +
                                " AND comment = '" + comment + "'";
                        //assert cdo != null;
                        Long id_newQuestion = null;
                        id_newQuestion = cdo.searchLastInsert(condicion,con);
                        // insertar registro de respuesta automatica para caso 4 y 5
                        BodySurveyAnswersVO bvo = new BodySurveyAnswersVO();
                        BodySurveyAnswersDAO bdo = new BodySurveyAnswersDAO();
                        // mover datos
                        bvo.setBodySurveyQuestionsQuestionCode(id_newQuestion);
                        bvo.setBodySurveyQuestionsHeadersSurveySurveyCode(Long.parseLong(code_poll));
                        bvo.setBodySurveyQuestionsHeadersSurveyBussinessUnitBuBisCode(Long.parseLong(company_number));
                        bvo.setAnswer("NA");
                        bvo.setAnswerSolution("T");
                        bvo.setAuditableSolution("T");
                        bvo.setAnswerOnlyText("");
                        bvo.setAnswerOnlyNumber(Double.parseDouble("0"));
                        bvo.setAnswerOnlyTime("");
                        bvo.setAnswerOnlyDate("");
                        bvo.setStatusRank("F");
                        bvo.setRankMin(Double.parseDouble("0"));
                        bvo.setRankMax(Double.parseDouble("0"));
                        //
                        bdo.insertBodySurveyAnswersDAO(bvo,con);
                        cvo.setResult(bvo.getResult());
                    }
                }
                if (cvo.getResult()){
                    HeadersSurveyVO hvo = new HeadersSurveyVO();
                    HeadersSurveyDAO hdo = null;
                    hvo.setSurveyCode(Long.parseLong(code_poll));
                    hvo.setBussinessUnitBuBisCode(Long.parseLong(company_number));
                    hdo.updateHeadersSurveyCounterDateDAO(hvo,1,con);
                    cvo.setResult(hvo.getResult());
                }
            }else{
                if (acciones.equals("delete")){
                    cvo.setResult(true);
                    int cta_reg = 0;
                    for(int x=0 ; x < cual_0.size();x++){
                        if (cvo.getResult()){
                            cvo.setResult(cdo.deleteBodySurveyQuestionsDAO(
                                    cual_0.get(x), Integer.parseInt(code_poll),
                                    Integer.parseInt(company_number),con));
                            cta_reg++;
                        }
                    }
                    HeadersSurveyVO hvo = new HeadersSurveyVO();
                    HeadersSurveyDAO hdo = new HeadersSurveyDAO();
                    if (cvo.getResult()){
                        hvo.setSurveyCode(Long.parseLong(code_poll));
                        hvo.setBussinessUnitBuBisCode(Long.parseLong(company_number));
                        hdo.updateHeadersSurveyCounterDateDAO(hvo,cta_reg * -1,con);
                        cvo.setResult(hvo.getResult());
                    }
                    if (cvo.getResult()){ // resta puntos
                        cvo.setQuestionPoints(total_ptos);
                        hvo.setTotalPoints(cvo.getQuestionPoints());
                        hdo.updatePointsHeadersSurveyDAO(hvo,con,"-");
                        cvo.setResult(hvo.getResult());
                    }
                }else{
                    cvo.setQuestionCode(Long.parseLong(request_code));
                    cvo.setHeadersSurveySurveyCode(Long.parseLong(code_poll));
                    cvo.setHeadersSurveyBussinessUnitBuBisCode(Long.parseLong(company_number));
                    cvo.setTypeRequest(Integer.parseInt(type));
                    cvo.setMainRequest(main);
                    cvo.setAnnexType(annex_type);
                    cvo.setBodyAnnexDoc(bdoc);
                    cvo.setBodyAnnexPhoto(bimg);
                    cvo.setComment(comment);
                    cvo.setQuestionPoints(Double.parseDouble(puntos));
                    cvo.setStatusRank(rank);
                    // cvo.setAuditableSolution("T"); se define en el DAO
                    cdo.updateBodySurveyQuestionsDAO(cvo,con);
                    HeadersSurveyVO hvo = new HeadersSurveyVO();
                    HeadersSurveyDAO hdo = new HeadersSurveyDAO();
                    if (cvo.getResult()){
                        hvo.setSurveyCode(Long.parseLong(code_poll));
                        hvo.setBussinessUnitBuBisCode(Long.parseLong(company_number));
                        hdo.updateHeadersSurveyCounterDateDAO(hvo,0,con);
                        cvo.setResult(hvo.getResult());
                    }
                    if (cvo.getResult()){ // suma nuevos
                        cvo.setQuestionPoints(Double.parseDouble(puntos));
                        hvo.setTotalPoints(cvo.getQuestionPoints());
                        hdo.updatePointsHeadersSurveyDAO(hvo,con,"+");
                        cvo.setResult(hvo.getResult());
                    }
                    if (cvo.getResult()){ // resta los anteriores
                        cvo.setQuestionPoints(Double.parseDouble(puntos_old));
                        hvo.setTotalPoints(cvo.getQuestionPoints());
                        hvo.setSurveyCode(cvo.getHeadersSurveySurveyCode());
                        hvo.setBussinessUnitBuBisCode(cvo.getHeadersSurveyBussinessUnitBuBisCode());
                        hdo.updatePointsHeadersSurveyDAO(hvo,con,"-");
                        cvo.setResult(hvo.getResult());
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
