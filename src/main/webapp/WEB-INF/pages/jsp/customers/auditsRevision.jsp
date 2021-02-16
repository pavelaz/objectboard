<%@ page import="java.util.ArrayList" %>
<%@ page import="com.psg.objectboard.model.own.ownsEntity.classDAO.BodySurveyQuestionsDAO" %>
<%@ page import="com.psg.objectboard.model.own.ownsEntity.classVO.BodySurveyQuestionsVO" %>
<%@ page import="com.psg.objectboard.model.own.ownsEntity.classVO.BodySurveyAnswersVO" %>
<%@ page import="com.psg.objectboard.model.own.ownsEntity.classDAO.BodySurveyAnswersDAO" %>
<%@ page import="com.psg.objectboard.model.own.ownsEntity.classDAO.BodyConductSurveyDAO" %>
<%@ page import="com.psg.objectboard.model.own.ownsEntity.classVO.BodyConductSurveyVO" %>
<%@ page import="com.psg.objectboard.model.service.Other.OtherFunctions" %>
<%@ page import="java.io.File" %>
<%@ page import="com.psg.objectboard.model.service.Other.OtherInserts" %>
<%--
  Created by IntelliJ IDEA.
  User: pavelaz
  Date: 6/4/20
  Time: 2:30 p. m.
  To change this template use File | Settings | File Templates.
  <%@ page contentType="text/html;charset=UTF-8" language="java" %>
--%>
<%
    HttpSession objSesion = request.getSession();
    String data_user = (String)objSesion.getAttribute("dataUser");
    String data_pasword = (String)objSesion.getAttribute("dataPassword");
    String company_number = (String)objSesion.getAttribute("companyNumber");
    String user_email = (String)objSesion.getAttribute("userEmail");

    String code = null;
    if(request.getParameter("p_survey")!=null) {
        code = request.getParameter("p_survey");
    }

    String nombre = null;
    if(request.getParameter("p_name")!=null) {
        nombre = request.getParameter("p_name");
    }

    int format = 0;
    if(request.getParameter("p_format")!=null) {
        format = Integer.parseInt(request.getParameter("p_format"));
    }

    BodySurveyQuestionsDAO sdo = new BodySurveyQuestionsDAO();
    sdo.setDataPassword(data_pasword);
    sdo.setDataUser(data_user);
    ArrayList<BodySurveyQuestionsVO> questions = null;
    String condicion = "headersSurvey_survey_code = " + Long.parseLong(code) + " AND headersSurvey_bussinessUnit_bu_bis_code = " + Long.parseLong(company_number);
    questions = sdo.getListBodySurveyQuestions(condicion);

    int ctaPregunta = 1,
            ctaLinea = 0;
    Long pregunta = null;

    BodySurveyAnswersDAO ado = new BodySurveyAnswersDAO();
    ado.setDataPassword(data_pasword);
    ado.setDataUser(data_user);
    ArrayList<BodySurveyAnswersVO> answers = null;

    String[] respuestas = new String[questions.size()];

    for (int x=0;x < respuestas.length;x++){
        respuestas[x]="";
    }

    for(Integer x=0; x < questions.size();x++){
        if (questions.get(x).getTypeRequest() == 3) {
            condicion = "bodySurveyQuestions_question_code = " + questions.get(x).getQuestionCode() +
                    " AND bodySurveyQuestions_headersSurvey_survey_code = " + questions.get(x).getHeadersSurveySurveyCode() +
                    " AND bodySurveyQuestions_headersSurvey_bussinessUnit_bu_bis_code = " + Long.parseLong(company_number);
            answers = ado.getListBodySurveyAnswers(condicion);
            for (Integer y = 0; y < answers.size(); y++) {
                if (answers.get(y).getAnswerSolution().equals("T")){
                    if (respuestas[x].length() == 0)
                        respuestas[x] = String.valueOf(answers.get(y).getAnswerCode());
                    else
                        respuestas[x] = respuestas[x] + "," + String.valueOf(answers.get(y).getAnswerCode());
                }
            }
        }
    }

    String assign = null;
    if(request.getParameter("p_assign")!=null) {
        assign = request.getParameter("p_assign");
    }

    OtherFunctions of = new OtherFunctions();
    OtherInserts oi = new OtherInserts();

    String dir_img = of.searchLink("4");
    String dir_doc = of.searchLink("4");
    String directorio_gral = of.searchLink("3") + "/img/";

    String conduct_id = null;
    if(request.getParameter("p_conduct_id")!=null) {
        conduct_id = request.getParameter("p_conduct_id");
    }

    BodyConductSurveyDAO cdo = new BodyConductSurveyDAO();
    cdo.setDataPassword(data_pasword);
    cdo.setDataUser(data_user);
    ArrayList<BodyConductSurveyVO> ejecuciones = null;
    condicion = "headerConductSurvey_conduct_id = " + Long.parseLong(conduct_id);
    ejecuciones = cdo.getListBodyConductSurvey(condicion);

%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>View Survey Files</title>
    <meta name="author" content="Paul Velazquez" />
    <!--start ############################### head_basic_script #############################-->
    <%@include file="../../../../complements/jsp/head_basic_script.jsp"%>
    <!-- Propias -->
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/complements/css/own_style.css">
    <!--end ############################### head_basic_script ###############################-->
    <!-- Bootstrap CSS
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">-->
    <!-- CSS personalizado -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/complements/bootstrap/datatable_custom/main.css">
    <!--datables CSS básico-->
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/complements/bootstrap/datatable_custom/datatables/datatables.min.css"/>
    <!--datables estilo bootstrap 4 CSS-->
    <link rel="stylesheet"  type="text/css" href="<%= request.getContextPath() %>/complements/bootstrap/datatable_custom/datatables/DataTables-1.10.18/css/dataTables.bootstrap4.min.css">
    <!--font awesome con CDN
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css" integrity="sha384-oS3vJWv+0UjzBfQzYUhtDYW+Pj2yciDJxpsK1OYPAYjqT085Qq/1cq5FLXAZQ7Ay" crossorigin="anonymous">
    -->
    <link rel=stylesheet href=https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css />
    <script src="<%= request.getContextPath() %>/complements/scripts/validaciones.js" type="text/javascript"></script>
    <!-- relacionadas con tablas -->
    <link href="<%= request.getContextPath() %>/complements/css/bootstrap-table.min.css" rel="stylesheet">
    <link href="<%= request.getContextPath() %>/complements/css/bootstrap-table-filter-control.min.css" rel="stylesheet">
    <script type="text/javascript">
        function document_view(ruta){
            document.forma.target = "_blank";
            document.forma.action = ruta;
            document.forma.submit();
            document.forma.target = "";
        }
    </script>
    <script type="text/javascript">
        function view_format(tip){
            document.forma.target = "";
            document.forma.action = '/objectboard/auditsrevision';
            document.forma.p_format.value = tip;
            document.forma.submit();
        }
        <% if (format == 2){ %>
            function auditar(){
                if(validaItems() && valida_comentarios()) {
                    //alert("paso 1");
                    document.forma.target = "";
                    document.forma.action = '/objectboard/auditsrevisionprocess';
                    document.forma.submit();
                }
            }
            function valida_comentarios(){
                <% for (Integer x=1;x<questions.size()+1;x++){%>
                    if (!valida_textos(document.forma.p_comen_<%=x%>.value,"Comment at the result No. <%=x%>",",.")||
                        !valida_largos(document.forma.p_comen_<%=x%>.value.length,"Comment at the result No. <%=x%>",1)){
                        return false;
                    }
                <%}%>
                return true;
            }
            function validaItems() {
                var cta=0;
                var checkboxes=document.getElementsByTagName('input');
                for(x=1;x<=document.forma.p_questions.value;x++) {
                    cta =0;
                    for(i=0;i<checkboxes.length;i++) {//recoremos todos los controles
                        if((checkboxes[i].id === "codep_" + x + "_1") || (checkboxes[i].id === "codep_" + x + "_2")) {
                            if (checkboxes[i].checked) {
                                cta = cta + 1;
                            }
                        }
                    }
                    if (cta === 0){
                        alert("Please mark the result of your evaluation for question No.: " + x);
                        return false;
                    }
                    cta = 0;
                }
                mueve_status();
                return true;
            }
            function mueve_status(){
                var con = 0;
                var no_con = 0;
                <% for (Integer x = 1; x < questions.size()+1;x++){ %>
                    if(document.forma.p_audi_<%=x%>.value === 'T'){
                        con++;
                        document.forma.p_status_<%=x%>.value = 'T';
                    }else{
                        no_con++;
                        document.forma.p_status_<%=x%>.value = 'F';
                    }
                <%}%>
                document.forma.p_conform.value = con;
                document.forma.p_no_conform.value = no_con;
            }
        <%}%>
    </script>
</head>

<body>
<!-- Pre-loader start x
start ############################### Pre-loader ###############################
<%@include file="../../../../complements/jsp/pre_loader.jsp"%>
-end ############################### Pre-loader ###############################-->
<div id="pcoded" class="pcoded">
    <div class="pcoded-overlay-box"></div>
    <div class="pcoded-container navbar-wrapper">
        <!--start ############################### navbar-header ###############################-->
        <jstl:set var="jsp_ShowImage" scope="page" value="${ 'NOT' }"/>
        <jstl:set var="jsp_menuOption" scope="page" value="${ '2,6' }"/>
        <jstl:set var="jsp_previousMsg" scope="page" value="Previous page"/>
        <jstl:set var="jsp_pagePrevious" scope="page" value="/objectboard/audits"/>
        <%@include file="../../../../complements/jsp/navbar-header.jsp"%>
        <!--end ############################### navbar-header ###############################-->
        <!-- Contenedor central -->
        <div class="pcoded-main-container">
            <!--start ############################### page-header ###############################-->
            <jstl:set var="jsp_titleFrom" scope="page" value="Audit Conduct Survey List Form"/>
            <jstl:set var="jsp_funtionFrom" scope="page" value="List or select your options ..."/>
            <%@include file="../../../../complements/jsp/page-header.jsp"%>
            <!--end ############################### page-header ###############################-->
            <!--<section id="content">-->
            <div class="container mt-2 mb-2">
                <form class="user" role="form" method="post" action="#!" name="forma">
                    <div class="form-group row">
                        <div class="col-sm-12" align="center">
                            <label class="propia_r">
                                <%= nombre %>
                            </label>
                        </div>
                    </div>
                    <hr>
                    <% if (format == 0){ %>
                    <button type= "button" id="add1" title="View Survey Original Solution" class= "btn btn-outline-success" onClick=view_format(1) >
                        <i class='fa fa-file-o fa-fw'></i> Solutions</button>
                    <p></p>
                    <p>
                        <label class="notamini_1">Note: This is the original structure of the survey without the responses to it.</label>
                    </p>
                    <%}%>
                    <% if (format == 1){ %>
                    <button type= "button" id="add1" title="View responses to audit" class= "btn btn-outline-success" onClick=view_format(2) >
                        <i class='fa fa-file-o fa-fw'></i> Responses to audit</button>
                    <p></p>
                    <p>
                        <label class="notamini_1">Note: This is the original survey with the responses to it placed as a solution to each question at the time of its design.</label>
                    </p>
                    <%}%>
                    <% if (format == 2){ %>
                    <button type= "button" id="add1" title="View Survey Only" class= "btn btn-outline-success" onClick=view_format(0) >
                        <i class='fa fa-file-o fa-fw'></i>Survey Only</button>
                    <button type= "button" id="add1" title="Audit Process" class= "btn btn-outline-warning" onClick=auditar() >
                        <i class='fa fa-cogs fa-fw'></i>Audit Process</button>
                    <p></p>
                    <p>
                        <label class="notamini_1">Note: This is the survey with the answers placed as a solution to each question at the time of this execution.</label>
                    </p>
                    <%}%>
                    <hr>
                    <% for(Integer x=0; x < questions.size();x++){ %>
                    <% ctaLinea = 0; %>
                    <% if (pregunta != questions.get(x).getQuestionCode()){ %>
                    <% pregunta = questions.get(x).getQuestionCode(); %>
                    <div class="form-group row">
                        <div class="col-sm-12">
                            <div class="card">
                                <div class="card-header">
                                    <%= ctaPregunta %> - <%= questions.get(x).getMainRequest() %>
                                    <% if (format == 2){%>
                                          <input name='p_ptos_<%=x+1%>' type='hidden' value='<%= questions.get(x).getQuestionPoints() %>' />
                                    <%}%>
                                    <%  ctaPregunta = ctaPregunta + 1; %>
                                </div>
                                <div class="card-body">
                                    <% condicion = "bodySurveyQuestions_question_code = " + questions.get(x).getQuestionCode() +
                                            " AND bodySurveyQuestions_headersSurvey_survey_code = " + questions.get(x).getHeadersSurveySurveyCode() +
                                            " AND bodySurveyQuestions_headersSurvey_bussinessUnit_bu_bis_code = " + Long.parseLong(company_number);
                                        answers = ado.getListBodySurveyAnswers(condicion);
                                    %>
                                    <% if(answers.size() != 0 &&
                                            questions.get(x).getTypeRequest() != 4 &&
                                            questions.get(x).getTypeRequest() != 5){ %>
                                    <% for(Integer y=0; y < answers.size();y++){ %>
                                    <% if (questions.get(x).getTypeRequest() == 1){ %>
                                    <div class="form-group row">
                                        <div class="col-sm-6">
                                            <p>
                                                &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                                                <% if (format == 1){ %>
                                                <input type="radio" id="<%= questions.get(x).getQuestionCode()%>_<%= answers.get(y).getAnswerCode() %>"
                                                       name="p_<%= questions.get(x).getQuestionCode()%>"
                                                       value="<%= answers.get(y).getAnswerCode() %>"
                                                    <% if (answers.get(y).getAnswerSolution().equals("T")){ %>
                                                       checked disabled>
                                                <%}else{%>
                                                disabled>
                                                <%}%>
                                                <%}%>
                                                <% if (format == 2){ %>
                                                <input type="radio" id="<%= questions.get(x).getQuestionCode()%>_<%= answers.get(y).getAnswerCode() %>"
                                                       name="p_<%= questions.get(x).getQuestionCode()%>"
                                                       value="<%= answers.get(y).getAnswerCode() %>"
                                                    <% if (String.valueOf(answers.get(y).getAnswerCode()).equals(ejecuciones.get(x).getBcsAnswer())){ %>
                                                       checked disabled>
                                                <%}else{%>
                                                disabled>
                                                <%}%>
                                                <%}%>
                                                <% if (format == 0){ %>
                                                <input type="radio" id="<%= questions.get(x).getQuestionCode()%>_<%= answers.get(y).getAnswerCode() %>"
                                                       name="p_<%= questions.get(x).getQuestionCode()%>"
                                                       value="<%= answers.get(y).getAnswerCode() %>" disabled>
                                                <%}%>
                                                <label for="<%= questions.get(x).getQuestionCode()%>_<%= answers.get(y).getAnswerCode() %>">&nbsp; &nbsp;<%= answers.get(y).getAnswer() %></label>
                                            </p>
                                            <input type="hidden" name="p_question_n<%= ctaPregunta - 1%>" value="<%= questions.get(x).getQuestionCode()%>">
                                        </div>
                                        <div class="col-sm-5">
                                            <%if (!questions.get(x).getAnnexType().equals("0")){
                                                ctaLinea =  ctaLinea + 1; %>
                                            <%--<% out.print(of.valida_tipoArchivo(ctaLinea,questions.get(x).getAnnexType(),ejecuciones.get(x).getBcsNameAnnexFile(),
                                                    dir_doc,dir_img,directorio_gral,format,company_number,ejecuciones.get(x).getBcsAnnexFileByte())); %>--%>
                                            <% out.print(oi.valida_tipoArchivo(ctaLinea,questions.get(x).getAnnexType(), directorio_gral,format,company_number,
                                                    ejecuciones.get(x).getHeaderConductSurveyConductId(),ejecuciones.get(x).getBsaBodySurveyQuestionsQuestionCode(),
                                                    ejecuciones.get(x).getBsaBodySurveyQuestionsHeadersSurveySurveyCode(),user_email,questions.get(x).getBodyAnnexPhoto())); %>
                                            <%}%>
                                        </div>
                                        <div class="col-sm-1"></div>
                                    </div>
                                    <%}%>
                                    <% if (questions.get(x).getTypeRequest() == 2){ %>
                                    <div class="form-group row">
                                        <div class="col-sm-6">
                                            <p>
                                                &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                                                <% if (format == 1){ %>
                                                <input type="radio" id="<%= questions.get(x).getQuestionCode()%>_<%= answers.get(y).getAnswerCode() %>"
                                                       name="p_<%= questions.get(x).getQuestionCode()%>"
                                                       value="<%= answers.get(y).getAnswerCode() %>"
                                                    <% if (answers.get(y).getAnswerSolution().equals("T")){ %>
                                                       checked disabled>
                                                <%}else{%>
                                                disabled>
                                                <%}%>
                                                <%}%>
                                                <% if (format == 2){ %>
                                                <input type="radio" id="<%= questions.get(x).getQuestionCode()%>_<%= answers.get(y).getAnswerCode() %>"
                                                       name="p_<%= questions.get(x).getQuestionCode()%>"
                                                       value="<%= answers.get(y).getAnswerCode() %>"
                                                    <% if (String.valueOf(answers.get(y).getAnswerCode()).equals(ejecuciones.get(x).getBcsAnswer())){ %>
                                                       checked disabled>
                                                <%}else{%>
                                                disabled>
                                                <%}%>
                                                <%}%>
                                                <% if (format == 0){ %>
                                                <input type="radio" id="<%= questions.get(x).getQuestionCode()%>_<%= answers.get(y).getAnswerCode() %>"
                                                       name="p_<%= questions.get(x).getQuestionCode()%>"
                                                       value="<%= answers.get(y).getAnswerCode() %>" disabled>
                                                <%}%>
                                                <label for="<%= questions.get(x).getQuestionCode()%>_<%= answers.get(y).getAnswerCode() %>">&nbsp; &nbsp;<%= answers.get(y).getAnswer() %></label>
                                            </p>
                                            <input type="hidden" name="p_question_n<%= ctaPregunta - 1%>" value="<%= questions.get(x).getQuestionCode()%>">
                                        </div>
                                        <div class="col-sm-5">
                                            <%if (!questions.get(x).getAnnexType().equals("0")){
                                                ctaLinea =  ctaLinea + 1; %>
                                            <%--%><% out.print(of.valida_tipoArchivo(ctaLinea,questions.get(x).getAnnexType(),ejecuciones.get(x).getBcsNameAnnexFile(),
                                                    dir_doc,dir_img,directorio_gral,format,company_number,ejecuciones.get(x).getBcsAnnexFileByte())); %> --%>
                                            <% out.print(oi.valida_tipoArchivo(ctaLinea,questions.get(x).getAnnexType(), directorio_gral,format,company_number,
                                                    ejecuciones.get(x).getHeaderConductSurveyConductId(),ejecuciones.get(x).getBsaBodySurveyQuestionsQuestionCode(),
                                                    ejecuciones.get(x).getBsaBodySurveyQuestionsHeadersSurveySurveyCode(),user_email,questions.get(x).getBodyAnnexPhoto())); %>
                                            <%}%>
                                        </div>
                                        <div class="col-sm-1"></div>
                                    </div>
                                    <%}%>
                                    <% if (questions.get(x).getTypeRequest() == 3){ %>
                                    <div class="form-group row">
                                        <div class="col-sm-6">
                                            <p>
                                                &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                                                <% if (format == 1){ %>
                                                <input type="checkbox" class="form-check-input"
                                                       name="p_<%= questions.get(x).getQuestionCode()%>"
                                                       value="<%= answers.get(y).getAnswerCode() %>"
                                                    <% if (answers.get(y).getAnswerSolution().equals("T")){ %>
                                                       checked disabled>
                                                <%}else{%>
                                                disabled >
                                                <%}%>
                                                <%}%>
                                                <% if (format == 2){ %>
                                                <input type="checkbox" class="form-check-input"
                                                       name="p_<%= questions.get(x).getQuestionCode()%>"
                                                       value="<%= answers.get(y).getAnswerCode() %>"
                                                <%-- Buscar dentro de una variable (1ra), una cadena de caracteres (2da), devolviendo true or false --%>
                                                    <% if (ejecuciones.get(x).getBcsAnswer().contains(String.valueOf(answers.get(y).getAnswerCode()))){ %>
                                                       checked disabled>
                                                <%}else{%>
                                                disabled >
                                                <%}%>
                                                <%}%>
                                                <% if (format == 0){ %>
                                                <input type="checkbox" class="form-check-input"
                                                       name="p_<%= questions.get(x).getQuestionCode()%>"
                                                       value="<%= answers.get(y).getAnswerCode() %>" disabled>
                                                <%}%>
                                                &nbsp; &nbsp;<%= answers.get(y).getAnswer() %>
                                            </p>
                                            <input type="hidden" name="p_question_n<%= ctaPregunta - 1%>" value="<%= questions.get(x).getQuestionCode()%>">
                                        </div>
                                        <div class="col-sm-5">
                                            <%if (!questions.get(x).getAnnexType().equals("0")){
                                                ctaLinea =  ctaLinea + 1; %>
                                            <%-- <% out.print(of.valida_tipoArchivo(ctaLinea,questions.get(x).getAnnexType(),ejecuciones.get(x).getBcsNameAnnexFile(),
                                                    dir_doc,dir_img,directorio_gral,format,company_number,ejecuciones.get(x).getBcsAnnexFileByte())); %> --%>
                                            <% out.print(oi.valida_tipoArchivo(ctaLinea,questions.get(x).getAnnexType(), directorio_gral,format,company_number,
                                                    ejecuciones.get(x).getHeaderConductSurveyConductId(),ejecuciones.get(x).getBsaBodySurveyQuestionsQuestionCode(),
                                                    ejecuciones.get(x).getBsaBodySurveyQuestionsHeadersSurveySurveyCode(),user_email,questions.get(x).getBodyAnnexPhoto())); %>
                                            <%}%>
                                        </div>
                                        <div class="col-sm-1"></div>
                                    </div>
                                    <%}%>
                                    <% if (questions.get(x).getTypeRequest() == 6){ %>
                                    <div class="form-group row">
                                        <div class="col-sm-6">
                                            &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                                            <p>Custom Text:</p>
                                            <% if (format == 1){ %>
                                            <input type="text" id="<%= questions.get(x).getQuestionCode()%>_<%= answers.get(y).getAnswerCode() %>"
                                                   name="p_<%= questions.get(x).getQuestionCode() %>" onfocus="selecciona_contenido(this)"
                                                   maxlength="45" placeholder="Put text" class="form-control"
                                                <% if (answers.get(y).getAuditableSolution().equals("F")){ %>
                                                   value="<%= answers.get(y).getAnswerOnlyText() %>" disabled>
                                            <%}else{%>
                                            disabled>
                                            <%}%>
                                            <%}%>
                                            <% if (format == 2){ %>
                                            <input type="text" id="<%= questions.get(x).getQuestionCode()%>_<%= answers.get(y).getAnswerCode() %>"
                                                   name="p_<%= questions.get(x).getQuestionCode() %>" onfocus="selecciona_contenido(this)"
                                                   maxlength="45" placeholder="Put text" class="form-control"
                                                   value="<%= ejecuciones.get(x).getBcsAnswer() %>" disabled>
                                            <%}%>
                                            <% if (format == 0){ %>
                                            <input type="text" id="<%= questions.get(x).getQuestionCode()%>_<%= answers.get(y).getAnswerCode() %>"
                                                   name="p_<%= questions.get(x).getQuestionCode() %>" onfocus="selecciona_contenido(this)"
                                                   maxlength="45" placeholder="Put text" class="form-control" disabled>
                                            <%}%>
                                            <input type="hidden" name="p_question_n<%= ctaPregunta - 1%>" value="<%= questions.get(x).getQuestionCode()%>">
                                        </div>
                                        <div class="col-sm-2">
                                            <% out.print(oi.valida_mensaFiletype(questions.get(x).getAnnexType())); %>
                                        </div>
                                        <div class="col-sm-3">
                                            <% out.print(oi.valida_mensaFile(questions.get(x).getAnnexType(),dir_doc,ejecuciones.get(x).getBcsNameAnnexFile(),directorio_gral,dir_img,format)); %>
                                        </div>
                                        <div class="col-sm-1"></div>
                                    </div>
                                    <%}%>
                                    <% if (questions.get(x).getTypeRequest() == 7){ %>
                                    <div class="form-group row">
                                        <div class="col-sm-6">
                                            &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                                            <p>Custom Number:</p>
                                            <% if (format == 1){ %>
                                            <input type="text" id="<%= questions.get(x).getQuestionCode()%>_<%= answers.get(y).getAnswerCode() %>"
                                                   name="p_<%= questions.get(x).getQuestionCode()%>" onfocus="selecciona_contenido(this)"
                                                   maxlength="10" placeholder="Put number" class="form-control"
                                                <% if (answers.get(y).getAuditableSolution().equals("F")){ %>
                                                   value="<%= answers.get(y).getAnswerOnlyNumber() %>" disabled>
                                            <%}else{%>
                                            disabled>
                                            <%}%>
                                            <%}%>
                                            <% if (format == 2){ %>
                                            <input type="text" id="<%= questions.get(x).getQuestionCode()%>_<%= answers.get(y).getAnswerCode() %>"
                                                   name="p_<%= questions.get(x).getQuestionCode()%>" onfocus="selecciona_contenido(this)"
                                                   maxlength="10" placeholder="Put number" class="form-control"
                                                   value="<%= ejecuciones.get(x).getBcsAnswer() %>" disabled>
                                            <%}%>
                                            <% if (format == 0){ %>
                                            <input type="text" id="<%= questions.get(x).getQuestionCode()%>_<%= answers.get(y).getAnswerCode() %>"
                                                   name="p_<%= questions.get(x).getQuestionCode()%>" onfocus="selecciona_contenido(this)"
                                                   maxlength="10" placeholder="Put number" class="form-control" disabled>
                                            <%}%>
                                            <input type="hidden" name="p_question_n<%= ctaPregunta - 1%>" value="<%= questions.get(x).getQuestionCode()%>">
                                        </div>
                                        <div class="col-sm-2">
                                            <% out.print(oi.valida_mensaFiletype(questions.get(x).getAnnexType())); %>
                                        </div>
                                        <div class="col-sm-3">
                                            <% out.print(oi.valida_mensaFile(questions.get(x).getAnnexType(),dir_doc,ejecuciones.get(x).getBcsNameAnnexFile(),directorio_gral,dir_img,format)); %>
                                        </div>
                                        <div class="col-sm-1"></div>
                                    </div>
                                    <%}%>
                                    <% if (questions.get(x).getTypeRequest() == 8){ %>
                                    <div class="form-group row">
                                        <div class="col-sm-6">
                                            &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                                            <p>Custom Time:</p>
                                            <% if (format == 1){ %>
                                            <input type="text" id="<%= questions.get(x).getQuestionCode()%>_<%= answers.get(y).getAnswerCode() %>"
                                                   name="p_<%= questions.get(x).getQuestionCode()%>" onfocus="selecciona_contenido(this)"
                                                   maxlength="10" placeholder="Put time HH:mm:ss" class="form-control"
                                                <% if (answers.get(y).getAuditableSolution().equals("F")){ %>
                                                   value="<%= answers.get(y).getAnswerOnlyTime() %>" disabled>
                                            <%}else{%>
                                            disabled>
                                            <%}%>
                                            <%}%>
                                            <% if (format == 2){ %>
                                            <input type="text" id="<%= questions.get(x).getQuestionCode()%>_<%= answers.get(y).getAnswerCode() %>"
                                                   name="p_<%= questions.get(x).getQuestionCode()%>" onfocus="selecciona_contenido(this)"
                                                   maxlength="10" placeholder="Put time HH:mm:ss" class="form-control"
                                                   value="<%= ejecuciones.get(x).getBcsAnswer() %>" disabled>
                                            <%}%>
                                            <% if (format == 0){ %>
                                            <input type="text" id="<%= questions.get(x).getQuestionCode()%>_<%= answers.get(y).getAnswerCode() %>"
                                                   name="p_<%= questions.get(x).getQuestionCode()%>" onfocus="selecciona_contenido(this)"
                                                   maxlength="10" placeholder="Put time HH:mm:ss" class="form-control" disabled>
                                            <%}%>
                                            <input type="hidden" name="p_question_n<%= ctaPregunta - 1%>" value="<%= questions.get(x).getQuestionCode()%>">
                                        </div>
                                        <div class="col-sm-2">
                                            <% out.print(oi.valida_mensaFiletype(questions.get(x).getAnnexType())); %>
                                        </div>
                                        <div class="col-sm-3">
                                            <% out.print(oi.valida_mensaFile(questions.get(x).getAnnexType(),dir_doc,ejecuciones.get(x).getBcsNameAnnexFile(),directorio_gral,dir_img,format)); %>
                                        </div>
                                        <div class="col-sm-1"></div>
                                    </div>
                                    <%}%>
                                    <% if (questions.get(x).getTypeRequest() == 9){ %>
                                    <div class="form-group row">
                                        <div class="col-sm-6">
                                            &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                                            <p>Custom Date:</p>
                                            <% if (format == 1){ %>
                                            <input type="text" id="<%= questions.get(x).getQuestionCode()%> %>"
                                                   name="p_<%= questions.get(x).getQuestionCode()%>"
                                                   maxlength="10" class="form-control"  width="276"
                                                    <% if (answers.get(y).getAuditableSolution().equals("F")){ %>
                                                   value="<%= answers.get(y).getAnswerOnlyDate() %>" disabled/>
                                            <%}else{%>
                                            disabled/>
                                            <%}%>
                                            <%}%>
                                            <% if (format == 2){ %>
                                            <input type="text" id="<%= questions.get(x).getQuestionCode()%> %>"
                                                   name="p_<%= questions.get(x).getQuestionCode()%>"
                                                   maxlength="10" class="form-control"  width="276"
                                                   value="<%= ejecuciones.get(x).getBcsAnswer() %>" disabled/>
                                            <%}%>
                                            <% if (format == 0){ %>
                                            <input type="text" id="<%= questions.get(x).getQuestionCode()%> %>"
                                                   name="p_<%= questions.get(x).getQuestionCode()%>"
                                                   maxlength="10" class="form-control"  width="276" disabled/>
                                            <%}%>
                                            <input type="hidden" name="p_question_n<%= ctaPregunta - 1%>" value="<%= questions.get(x).getQuestionCode()%>">
                                        </div>
                                        <div class="col-sm-2">
                                            <% out.print(oi.valida_mensaFiletype(questions.get(x).getAnnexType())); %>
                                        </div>
                                        <div class="col-sm-3">
                                            <% out.print(oi.valida_mensaFile(questions.get(x).getAnnexType(),dir_doc,ejecuciones.get(x).getBcsNameAnnexFile(),directorio_gral,dir_img,format)); %>
                                        </div>
                                        <div class="col-sm-1"></div>
                                    </div>
                                    <%}%>
                                    <%}%>
                                    <%}else{%>
                                    <% if (questions.get(x).getTypeRequest() == 4){ %>
                                    <div class="form-group row">
                                        <div class="col-sm-6">
                                            &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                                            <div class="custom-file mb-3">
                                                Validate document file:
                                                <% out.print(oi.validacionGral_tipoArchivo(questions.get(x).getTypeRequest(),directorio_gral,format,company_number,
                                                        ejecuciones.get(x).getHeaderConductSurveyConductId(),ejecuciones.get(x).getBsaBodySurveyQuestionsQuestionCode(),
                                                        ejecuciones.get(x).getBsaBodySurveyQuestionsHeadersSurveySurveyCode(),user_email,questions.get(x).getBodyAnnexPhoto())); %>
                                            </div>
                                        </div>
                                        <div class="col-sm-6">
                                            <input type="hidden" name="p_question_n<%= ctaPregunta - 1%>" value="<%= questions.get(x).getQuestionCode()%>">
                                        </div>
                                    </div>
                                    <%}%>
                                    <% if (questions.get(x).getTypeRequest() == 5){ %>
                                    <div class="form-group row">
                                        <div class="col-sm-6">
                                            &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                                            <div class="custom-file mb-3">
                                                Validate image file:
                                                <% out.print(oi.validacionGral_tipoArchivo(questions.get(x).getTypeRequest(),directorio_gral,format,company_number,
                                                        ejecuciones.get(x).getHeaderConductSurveyConductId(),ejecuciones.get(x).getBsaBodySurveyQuestionsQuestionCode(),
                                                        ejecuciones.get(x).getBsaBodySurveyQuestionsHeadersSurveySurveyCode(),user_email,questions.get(x).getBodyAnnexPhoto())); %>
                                            </div>
                                        </div>
                                        <div class="col-sm-6">
                                            <input type="hidden" name="p_question_n<%= ctaPregunta - 1%>" value="<%= questions.get(x).getQuestionCode()%>">
                                        </div>
                                    </div>
                                    <%}%>
                                    <%}%>
                                </div>
                                <% if (questions.get(x).getComment().equals("T")){ %>
                                <div class="card-footer">
                                    <% out.print(oi.valida_comentario(format,questions.get(x).getQuestionCode(),ejecuciones.get(x).getBcsComment())); %>
                                    <% if (format==2) {%>
                                    <% out.print(oi.valida_result(questions.get(x).getQuestionCode(),0,questions.get(x).getTypeRequest(),
                                            ejecuciones.get(x).getBcsAnswer(),ejecuciones.get(x).getBcsAnswerSolution(),questions.get(x).getAnnexType(),
                                            ejecuciones.get(x).getBcsAnswerOnlyText(),ejecuciones.get(x).getBcsAnswerOnlyNumber(),
                                            ejecuciones.get(x).getBcsAnswerOnlyTime(),ejecuciones.get(x).getBcsAnswerOnlyDate(),ctaPregunta - 1)); %>
                                    <%}%>
                                </div>
                                <%}else{%>
                                <% if (format==2) {%>
                                <% out.print(oi.valida_result(questions.get(x).getQuestionCode(),1,questions.get(x).getTypeRequest(),
                                        ejecuciones.get(x).getBcsAnswer(),ejecuciones.get(x).getBcsAnswerSolution(),questions.get(x).getAnnexType(),
                                        ejecuciones.get(x).getBcsAnswerOnlyText(),ejecuciones.get(x).getBcsAnswerOnlyNumber(),
                                        ejecuciones.get(x).getBcsAnswerOnlyTime(),ejecuciones.get(x).getBcsAnswerOnlyDate(),ctaPregunta - 1)); %>
                                <%}%>
                                <%}%>
                            </div>
                        </div>
                    </div>
                    <%}%>
                    <%}%>
                    <hr>
                    <input name='p_survey' type='hidden' value='<%= code %>' />
                    <input name='p_format' type='hidden' value='<%= format %>' />
                    <input name='p_name' type='hidden' value='<%= nombre %>' />
                    <input name='p_assign' type='hidden' value='<%= assign %>' />
                    <input name='p_conduct_id' type='hidden' value='<%= conduct_id %>' />
                    <input name='p_questions' type='hidden' value='<%= ctaPregunta - 1 %>' />
                    <input name='p_pantalla' type='hidden' value='auditsrevision' />
                    <input name='p_conform' type='hidden' value='' />
                    <input name='p_no_conform' type='hidden' value='' />
                    <% if (format == 2){%>
                        <% for (Integer x = 1; x < questions.size()+1;x++){ %>
                            <input name='p_status_<%=x%>' type='hidden' value='' />
                        <%}%>
                    <%}%>
                </form>
            </div>
            <!--</section>-->
        </div>
    </div>
</div>
<!--end ############################### footer ##########################-->
<%@include file="../../../../complements/jsp/footer.jsp"%>
<!--end ############################### footer ###############################-->
<!--start ############################### footer_basic_script #############################-->
<%@include file="../../../../complements/jsp/footer_basic_script.jsp"%>
<!--end ############################### footer_basic_script ###############################-->
<!-- datatables JS -->
<script type=text/javascript src="<%= request.getContextPath() %>/complements/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/complements/bootstrap/datatable_custom/datatables/datatables.min.js"></script>
<!-- para usar botones en datatables JS -->
<script src="<%= request.getContextPath() %>/complements/bootstrap/datatable_custom/datatables/Buttons-1.5.6/js/dataTables.buttons.min.js"></script>
<script src="<%= request.getContextPath() %>/complements/bootstrap/datatable_custom/datatables/JSZip-2.5.0/jszip.min.js"></script>
<script src="<%= request.getContextPath() %>/complements/bootstrap/datatable_custom/datatables/pdfmake-0.1.36/pdfmake.min.js"></script>
<script src="<%= request.getContextPath() %>/complements/bootstrap/datatable_custom/datatables/pdfmake-0.1.36/vfs_fonts.js"></script>
<script src="<%= request.getContextPath() %>/complements/bootstrap/datatable_custom/datatables/Buttons-1.5.6/js/buttons.html5.min.js"></script>

<!-- código JS propìo-->
<script type="text/javascript" src="../../../../complements/bootstrap/datatable_custom/main.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        <% for(Integer x=0; x < questions.size();x++){ %>
        <% if (questions.get(x).getTypeRequest() == 9){ %>
        var date_input=$('input[name="p_<%= questions.get(x).getQuestionCode()%>"]'); //our date input has the name "p_datexpires"
        <%}%>
        <%}%>
        // var date_input=$('input[name="p_datepicker"]'); //our date input has the name "p_datexpires"
        var container=$('.bootstrap-iso form').length>0 ? $('.bootstrap-iso form').parent() : "body";
        date_input.datepicker({
            format: 'mm/dd/yyyy',
            container: container,
            todayHighlight: true,
            autoclose: true,
        })
    });
</script>
<script>
    // Add the following code if you want the name of the file appear on select
    $(".custom-file-input").on("change", function() {
        var fileName = $(this).val().split("\\").pop();
        $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
    });
</script>
</body>