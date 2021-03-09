<%@ page import="java.util.ArrayList" %>
<%@ page import="com.psg.objectboard.model.own.ownsEntity.classDAO.BodySurveyQuestionsDAO" %>
<%@ page import="com.psg.objectboard.model.own.ownsEntity.classVO.BodySurveyQuestionsVO" %>
<%@ page import="com.psg.objectboard.model.own.ownsEntity.classVO.BodySurveyAnswersVO" %>
<%@ page import="com.psg.objectboard.model.own.ownsEntity.classDAO.BodySurveyAnswersDAO" %>
<%@ page import="com.psg.objectboard.model.service.Other.OtherInserts" %>
<%@ page import="com.psg.objectboard.model.own.ownsEntity.classVO.OtherVO.ViewVO" %>
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

    String code = null;
    if(request.getParameter("p_code")!=null) {
        code = request.getParameter("p_code");
    }

    String nombre = null;
    if(request.getParameter("p_names")!=null) {
        nombre = request.getParameter("p_names");
    }

    Integer format = 0;
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

    OtherInserts oi = null;
    ViewVO vivo = null;
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
        function view_format(tip){
            document.forma.target = "";
            document.forma.action = '/objectboard/viewsurvey';
            document.forma.p_format.value = tip;
            document.forma.submit();
        }
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
        <jstl:set var="jsp_pagePrevious" scope="page" value="/objectboard/headerpolls"/>
        <%@include file="../../../../complements/jsp/navbar-header.jsp"%>
        <!--end ############################### navbar-header ###############################-->
        <!-- Contenedor central -->
        <div class="pcoded-main-container">
            <!--start ############################### page-header ###############################-->
            <jstl:set var="jsp_titleFrom" scope="page" value="View Survey List Form"/>
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
                        <jstl:if test="${ rq_imagen }">
                            <div class="form-group row" align="right">
                                <div class="col-sm-12" align="right">
                                    <img name="oldPhoto_<%= (ctaPregunta - 1) %>"
                                         src="/objectboard/showfile.html?p_unit=<%= company_number %>&p_survey=<%=
                                                code %>&p_archivo=3"
                                         class="img-thumbnail" alt="Question image" width="150" height="190">
                                </div>
                            </div>
                        </jstl:if>
                    </div>
                    <hr>
                    <% if (format == 0){ %>
                    <button type= "button" id="add1" title="View Survey Solution" class= "btn btn-outline-success" onClick=view_format(1) >
                        <i class='fa fa-file-o fa-fw'></i> Solutions</button>
                    <%}%>
                    <% if (format == 1){ %>
                    <button type= "button" id="add1" title="View Survey Only" class= "btn btn-outline-success" onClick=view_format(0) >
                        <i class='fa fa-file-o fa-fw'></i> Survey Only</button>
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
                                            <strong><%= ctaPregunta %> - <%= questions.get(x).getMainRequest() %></strong>
                                            <% ctaPregunta = ctaPregunta + 1; %>
                                            <% if (!questions.get(x).getQuestionImageName().equals("no_images.jpeg")) { %>
                                                <button type="button" class="btn btn-circle btn-sm" data-toggle="modal" data-target="#ModalCenter<%= ctaPregunta %>">
                                                    <img src="<%= request.getContextPath() %>/complements/img/info.gif" alt="x" width="20" height="20"/>
                                                </button>
                                            <%}%>
                                        </div>
                                        <div class="card-body">
                                            <%-- <% if (!questions.get(x).getQuestionImageName().equals("no_images.jpeg")) { %>
                                                <div class="form-group row">
                                                    <div class="col-sm-2" align="right">
                                                        <div class="card">
                                                            <div class="card-body">
                                                                <img name="oldPhoto_<%= (ctaPregunta - 1) %>"
                                                                     src='/objectboard/showfile.html?p_unit=<%= questions.get(x).getHeadersSurveyBussinessUnitBuBisCode() %>&p_survey=<%=
                                                        questions.get(x).getHeadersSurveySurveyCode() %>&p_question=<%= questions.get(x).getQuestionCode() %>&p_archivo=4'
                                                                     class='img-thumbnail' alt='Question image' width="200" height="240">
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            <%}%>
                                            <% if (!questions.get(x).getQuestionImageName().equals("no_images.jpeg")) {%>
                                                <div class="form-group row">
                                                    <div class="col-sm-12" align="right">
                                                        <img name="oldPhoto_<%= (ctaPregunta - 1) %>"
                                                        src='/objectboard/showfile.html?p_unit=<%= questions.get(x).getHeadersSurveyBussinessUnitBuBisCode() %>&p_survey=<%=
                                                        questions.get(x).getHeadersSurveySurveyCode() %>&p_question=<%= questions.get(x).getQuestionCode() %>&p_archivo=4'
                                                        class='img-thumbnail' alt='Question image' width="200" height="240">
                                                    </div>
                                                </div>
                                            <%}%> --%>
                                            <% condicion = "bodySurveyQuestions_question_code = " + questions.get(x).getQuestionCode() +
                                                    " AND bodySurveyQuestions_headersSurvey_survey_code = " + questions.get(x).getHeadersSurveySurveyCode() +
                                                    " AND bodySurveyQuestions_headersSurvey_bussinessUnit_bu_bis_code = " + Long.parseLong(company_number);
                                                answers = ado.getListBodySurveyAnswers(condicion);
                                                oi = new OtherInserts();
                                                vivo = new ViewVO(0,0,0,1);
                                                vivo.setFormat(format);
                                                vivo.setCtaLinea(ctaLinea);
                                                vivo.setCtaPregunta(ctaPregunta);
                                            %>
                                            <% if(answers.size() != 0 && questions.get(x).getTypeRequest() != 4 && questions.get(x).getTypeRequest() != 5){ %>
                                                <% for(Integer y=0; y < answers.size();y++){ %>
                                                    <% if (questions.get(x).getTypeRequest() == 1){ %>
                                                        <% out.print(oi.seccionViewPorTipo(x,y,questions,answers,vivo)); %>
                                                    <%}%>
                                                    <% if (questions.get(x).getTypeRequest() == 2){ %>
                                                        <%-- out.print(oi.seccionViewPorTipo(x,y,questions,answers,vivo)); --%>
                                                     <div class="form-group row">
                                                        <div class="col-sm-6">
                                                            <p>
                                                                &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                                                                <input type="radio" id="<%= questions.get(x).getQuestionCode()%>_<%= answers.get(y).getAnswerCode() %>"
                                                                       name="p_<%= questions.get(x).getQuestionCode()%>"
                                                                       value="<%= answers.get(y).getAnswerCode() %>"
                                                                    <% if (format == 1 && answers.get(y).getAnswerSolution().equals("T")){ %>
                                                                       checked >
                                                                <%}else{%>
                                                                        >
                                                                <%}%>
                                                                <label for="<%= questions.get(x).getQuestionCode()%>_<%= answers.get(y).getAnswerCode() %>">&nbsp; &nbsp;<%= answers.get(y).getAnswer() %></label>
                                                            </p>
                                                        </div>
                                                        <div class="col-sm-5">
                                                            <% if (answers.get(y).getAnswerSolution().equals("T")) {%>
                                                                <input type="hidden" name="p_as_<%= questions.get(x).getQuestionCode()%>" value="<%= answers.get(y).getAnswerCode() %>">
                                                            <%}%>
                                                            <%if (!questions.get(x).getAnnexType().equals("0")){
                                                                ctaLinea =  ctaLinea + 1;
                                                                if (ctaLinea == 1){
                                                                    if (questions.get(x).getAnnexType().equals("1")){%>
                                                                        <%= questions.get(x).getBodyAnnexDoc() %>
                                                                    <%}else{%>
                                                                        <%= questions.get(x).getBodyAnnexPhoto() %>
                                                                    <%}%>
                                                                    <input type="hidden" name="p_ant_<%= questions.get(x).getQuestionCode()%>" value="<%= questions.get(x).getAnnexType() %>">
                                                                <%}%>
                                                                <% if (ctaLinea == 2){ %>
                                                                    <%if (questions.get(x).getAnnexType().equals("1")){%>
                                                                        <input type="hidden" name="p_dn_<%= questions.get(x).getQuestionCode()%>" id="dn_<%= questions.get(x).getQuestionCode()%>" value="">
                                                                        <input type="file" class="custom-file-input" id="d_<%= questions.get(x).getQuestionCode()%>"
                                                                               name="p_d_<%= questions.get(x).getQuestionCode()%>" accept=".pdf,.doc,.txt,.docx"
                                                                               onchange=validar_files('d_<%= questions.get(x).getQuestionCode()%>','dn_<%= questions.get(x).getQuestionCode()%>')>
                                                                        <label class="custom-file-label" for="<%= questions.get(x).getQuestionCode()%>">Choose document file</label>
                                                                    <%}else{%>
                                                                        <input type="hidden" name="p_in_<%= questions.get(x).getQuestionCode()%>" value="" id="in_<%= questions.get(x).getQuestionCode()%>">
                                                                        <input type="file" class="custom-file-input" id="i_<%= questions.get(x).getQuestionCode()%>"
                                                                               name="p_i_<%= questions.get(x).getQuestionCode()%>" accept=".png,.jpeg,.jpg,.bmp,.gif"
                                                                               onchange=validar_files('i_<%= questions.get(x).getQuestionCode()%>','in_<%= questions.get(x).getQuestionCode()%>')>
                                                                        <label class="custom-file-label" for="<%= questions.get(x).getQuestionCode()%>">Choose image file</label>
                                                                    <%}%>
                                                                <%}%>
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
                                                                <input type="checkbox" class="form-check-input"
                                                                       name="p_<%= questions.get(x).getQuestionCode()%>"
                                                                       value="<%= answers.get(y).getAnswerCode() %>"
                                                                    <% if (format == 1 && answers.get(y).getAnswerSolution().equals("T")){ %>
                                                                       checked >
                                                                <%}else{%>
                                                                >
                                                                <%}%>
                                                                &nbsp; &nbsp;<%= answers.get(y).getAnswer() %>
                                                            </p>
                                                        </div>
                                                        <div class="col-sm-5">
                                                            <%if (!questions.get(x).getAnnexType().equals("0")){
                                                                ctaLinea =  ctaLinea + 1;
                                                                if (ctaLinea == 1){
                                                                    if (questions.get(x).getAnnexType().equals("1")){%>
                                                            <%= questions.get(x).getBodyAnnexDoc() %>
                                                            <%}else{%>
                                                            <%= questions.get(x).getBodyAnnexPhoto() %>
                                                            <%}%>
                                                            <input type="hidden" name="p_ant_<%= questions.get(x).getQuestionCode()%>" value="<%= questions.get(x).getAnnexType() %>">
                                                            <%}%>
                                                            <% if (ctaLinea == 2){ %>
                                                            <% if (questions.get(x).getAnnexType().equals("1")){%>
                                                            <input type="hidden" name="p_dn_<%= questions.get(x).getQuestionCode()%>" id="dn_<%= questions.get(x).getQuestionCode()%>" value="">
                                                            <input type="file" class="custom-file-input" id="d_<%= questions.get(x).getQuestionCode()%>"
                                                                   name="p_d_<%= questions.get(x).getQuestionCode()%>" accept=".pdf,.doc,.txt,.docx"
                                                                   onchange=validar_files('d_<%= questions.get(x).getQuestionCode()%>','dn_<%= questions.get(x).getQuestionCode()%>')>
                                                            <label class="custom-file-label" for="<%= questions.get(x).getQuestionCode()%>">Choose document file</label>
                                                            <%}else{%>
                                                            <input type="hidden" name="p_in_<%= questions.get(x).getQuestionCode()%>" value="" id="in_<%= questions.get(x).getQuestionCode()%>">
                                                            <input type="file" class="custom-file-input" id="i_<%= questions.get(x).getQuestionCode()%>"
                                                                   name="p_i_<%= questions.get(x).getQuestionCode()%>" accept=".png,.jpeg,.jpg,.bmp,.gif"
                                                                   onchange=validar_files('i_<%= questions.get(x).getQuestionCode()%>','in_<%= questions.get(x).getQuestionCode()%>')>
                                                            <label class="custom-file-label" for="<%= questions.get(x).getQuestionCode()%>">Choose image file</label>
                                                            <%}%>
                                                            <%}%>
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
                                                            <input type="text" id="<%= questions.get(x).getQuestionCode()%>_<%= answers.get(y).getAnswerCode() %>"
                                                                   name="p_<%= questions.get(x).getQuestionCode() %>" onfocus="selecciona_contenido(this)"
                                                                   maxlength="45" placeholder="Put text" class="form-control"
                                                                <% if (format == 1 && answers.get(y).getAuditableSolution().equals("F")){ %>
                                                                   value="<%= answers.get(y).getAnswerOnlyText() %>" >
                                                            <%}else{%>
                                                            >
                                                            <%}%>
                                                        </div>
                                                        <div class="col-sm-2">
                                                            <% if (answers.get(y).getAnswerSolution().equals("T")) {%>
                                                            <input type="hidden" name="p_as_<%= questions.get(x).getQuestionCode()%>" value="<%= answers.get(y).getAnswerOnlyText() %>">
                                                            <%}%>
                                                            <%if (!questions.get(x).getAnnexType().equals("0")){
                                                                if (questions.get(x).getAnnexType().equals("1")){%>
                                                            <%= questions.get(x).getBodyAnnexDoc() %>
                                                            <%}else{%>
                                                            <%= questions.get(x).getBodyAnnexPhoto() %>
                                                            <%}%>
                                                            <input type="hidden" name="p_ant_<%= questions.get(x).getQuestionCode()%>" value="<%= questions.get(x).getAnnexType() %>">
                                                            <%}%>
                                                        </div>
                                                        <div class="col-sm-3">
                                                            <%if (!questions.get(x).getAnnexType().equals("0")){
                                                                if (questions.get(x).getAnnexType().equals("1")){%>
                                                            <input type="hidden" name="p_dn_<%= questions.get(x).getQuestionCode()%>" value="" id="dn_<%= questions.get(x).getQuestionCode()%>" >
                                                            <input type="file" class="custom-file-input" id="d_<%= questions.get(x).getQuestionCode()%>"
                                                                   name="p_d_<%= questions.get(x).getQuestionCode()%>"  accept=".pdf,.doc,.txt,.docx"
                                                                   onchange=validar_files('d_<%= questions.get(x).getQuestionCode()%>','dn_<%= questions.get(x).getQuestionCode()%>')>
                                                            <label class="custom-file-label" for="<%= questions.get(x).getQuestionCode()%>">Choose document file</label>
                                                            <%}else{%>
                                                            <input type="hidden" name="p_in_<%= questions.get(x).getQuestionCode()%>" value="" id="in_<%= questions.get(x).getQuestionCode()%>">
                                                            <input type="file" class="custom-file-input" id="i_<%= questions.get(x).getQuestionCode()%>"
                                                                   name="p_i_<%= questions.get(x).getQuestionCode()%>" accept=".png,.jpeg,.jpg,.bmp,.gif"
                                                                   onchange=validar_files('i_<%= questions.get(x).getQuestionCode()%>','in_<%= questions.get(x).getQuestionCode()%>')>
                                                            <label class="custom-file-label" for="<%= questions.get(x).getQuestionCode()%>">Choose image file</label>
                                                            <%}%>
                                                            <%}%>
                                                        </div>
                                                        <div class="col-sm-1"></div>
                                                    </div>
                                                    <%}%>
                                                    <% if (questions.get(x).getTypeRequest() == 7){ %>
                                                    <div class="form-group row">
                                                        <div class="col-sm-6">
                                                            &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                                                            <p>Custom Number:</p>
                                                            <input type="text" id="<%= questions.get(x).getQuestionCode()%>_<%= answers.get(y).getAnswerCode() %>"
                                                                   name="p_<%= questions.get(x).getQuestionCode()%>" onfocus="selecciona_contenido(this)"
                                                                   maxlength="10" placeholder="Put number" class="form-control"
                                                                <% if (format == 1 && answers.get(y).getAuditableSolution().equals("F")){ %>
                                                                   value="<%= answers.get(y).getAnswerOnlyNumber() %>" >
                                                            <%}else{%>
                                                            >
                                                            <%}%>
                                                        </div>
                                                        <div class="col-sm-2">
                                                            <% if (answers.get(y).getAnswerSolution().equals("T")) {%>
                                                            <input type="hidden" name="p_as_<%= questions.get(x).getQuestionCode()%>" value="<%= answers.get(y).getAnswerOnlyNumber() %>">
                                                            <%}%>
                                                            <%if (!questions.get(x).getAnnexType().equals("0")){
                                                                if (questions.get(x).getAnnexType().equals("1")){%>
                                                            <%= questions.get(x).getBodyAnnexDoc() %>
                                                            <%}else{%>
                                                            <%= questions.get(x).getBodyAnnexPhoto() %>
                                                            <%}%>
                                                            <input type="hidden" name="p_ant_<%= questions.get(x).getQuestionCode()%>" value="<%= questions.get(x).getAnnexType() %>">
                                                            <%}%>
                                                        </div>
                                                        <div class="col-sm-3">
                                                            <%if (!questions.get(x).getAnnexType().equals("0")){
                                                                if (questions.get(x).getAnnexType().equals("1")){%>
                                                            <input type="hidden" name="p_dn_<%= questions.get(x).getQuestionCode()%>" value="" id="dn_<%= questions.get(x).getQuestionCode()%>">
                                                            <input type="file" class="custom-file-input" id="d_<%= questions.get(x).getQuestionCode()%>"
                                                                   name="p_d_<%= questions.get(x).getQuestionCode()%>" accept=".pdf,.doc,.txt,.docx"
                                                                   onchange=validar_files('d_<%= questions.get(x).getQuestionCode()%>','dn_<%= questions.get(x).getQuestionCode()%>')>
                                                            <label class="custom-file-label" for="<%= questions.get(x).getQuestionCode()%>">Choose document file</label>
                                                            <%}else{%>
                                                            <input type="hidden" name="p_in_<%= questions.get(x).getQuestionCode()%>" value="" id="in_<%= questions.get(x).getQuestionCode()%>">
                                                            <input type="file" class="custom-file-input" id="i_<%= questions.get(x).getQuestionCode()%>"
                                                                   name="p_i_<%= questions.get(x).getQuestionCode()%>" accept=".png,.jpeg,.jpg,.bmp,.gif"
                                                                   onchange=validar_files('i_<%= questions.get(x).getQuestionCode()%>','in_<%= questions.get(x).getQuestionCode()%>')>
                                                            <label class="custom-file-label" for="<%= questions.get(x).getQuestionCode()%>">Choose image file</label>
                                                            <%}%>
                                                            <%}%>
                                                        </div>
                                                        <div class="col-sm-1"></div>
                                                    </div>
                                                    <%}%>
                                                    <% if (questions.get(x).getTypeRequest() == 8){ %>
                                                    <div class="form-group row">
                                                        <div class="col-sm-6">
                                                            &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                                                            <p>Custom Time:</p>
                                                            <input type="text" id="<%= questions.get(x).getQuestionCode()%>_<%= answers.get(y).getAnswerCode() %>"
                                                                   name="p_<%= questions.get(x).getQuestionCode()%>" onfocus="selecciona_contenido(this)"
                                                                   maxlength="10" placeholder="Put time HH:mm:ss" class="form-control"
                                                                <% if (format == 1 && answers.get(y).getAuditableSolution().equals("F")){ %>
                                                                   value="<%= answers.get(y).getAnswerOnlyTime() %>" >
                                                            <%}else{%>
                                                            >
                                                            <%}%>
                                                        </div>
                                                        <div class="col-sm-2">
                                                            <% if (answers.get(y).getAnswerSolution().equals("T")) {%>
                                                            <input type="hidden" name="p_as_<%= questions.get(x).getQuestionCode()%>" value="<%= answers.get(y).getAnswerOnlyTime() %>">
                                                            <%}%>
                                                            <%if (!questions.get(x).getAnnexType().equals("0")){
                                                                if (questions.get(x).getAnnexType().equals("1")){%>
                                                            <%= questions.get(x).getBodyAnnexDoc() %>
                                                            <%}else{%>
                                                            <%= questions.get(x).getBodyAnnexPhoto() %>
                                                            <%}%>
                                                            <input type="hidden" name="p_ant_<%= questions.get(x).getQuestionCode()%>" value="<%= questions.get(x).getAnnexType() %>">
                                                            <%}%>
                                                        </div>
                                                        <div class="col-sm-3">
                                                            <%if (!questions.get(x).getAnnexType().equals("0")){
                                                                if (questions.get(x).getAnnexType().equals("1")){%>
                                                            <input type="hidden" name="p_dn_<%= questions.get(x).getQuestionCode()%>" value="" id="dn_<%= questions.get(x).getQuestionCode()%>">
                                                            <input type="file" class="custom-file-input" id="d_<%= questions.get(x).getQuestionCode()%>"
                                                                   name="p_d_<%= questions.get(x).getQuestionCode()%>" accept=".pdf,.doc,.txt,.docx"
                                                                   onchange=validar_files('d_<%= questions.get(x).getQuestionCode()%>','dn_<%= questions.get(x).getQuestionCode()%>')>
                                                            <label class="custom-file-label" for="<%= questions.get(x).getQuestionCode()%>">Choose document file</label>
                                                            <%}else{%>
                                                            <input type="hidden" name="p_in_<%= questions.get(x).getQuestionCode()%>" value="" id="in_<%= questions.get(x).getQuestionCode()%>">
                                                            <input type="file" class="custom-file-input" id="i_<%= questions.get(x).getQuestionCode()%>"
                                                                   name="p_i_<%= questions.get(x).getQuestionCode()%>" accept=".png,.jpeg,.jpg,.bmp,.gif"
                                                                   onchange=validar_files('i_<%= questions.get(x).getQuestionCode()%>','in_<%= questions.get(x).getQuestionCode()%>')>
                                                            <label class="custom-file-label" for="<%= questions.get(x).getQuestionCode()%>">Choose image file</label>
                                                            <%}%>
                                                            <%}%>
                                                        </div>
                                                        <div class="col-sm-1"></div>
                                                    </div>
                                                    <%}%>
                                                    <% if (questions.get(x).getTypeRequest() == 9){ %>
                                                    <div class="form-group row">
                                                        <div class="col-sm-6">
                                                            &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                                                            <p>Custom Date:</p>
                                                            <input type="text" id="<%= questions.get(x).getQuestionCode()%> %>"
                                                                   name="p_<%= questions.get(x).getQuestionCode()%>"
                                                                   maxlength="10" class="form-control"  width="276"
                                                                    <% if (format == 1 && answers.get(y).getAuditableSolution().equals("F")){ %>
                                                                   value="<%= answers.get(y).getAnswerOnlyDate() %>" readonly/>
                                                            <%}else{%>
                                                            readonly/>
                                                            <%}%>
                                                        </div>
                                                        <div class="col-sm-2">
                                                            <% if (answers.get(y).getAnswerSolution().equals("T")) {%>
                                                            <input type="hidden" name="p_as_<%= questions.get(x).getQuestionCode()%>" value="<%= answers.get(y).getAnswerOnlyDate() %>">
                                                            <%}%>
                                                            <%if (!questions.get(x).getAnnexType().equals("0")){
                                                                if (questions.get(x).getAnnexType().equals("1")){%>
                                                            <%= questions.get(x).getBodyAnnexDoc() %>
                                                            <%}else{%>
                                                            <%= questions.get(x).getBodyAnnexPhoto() %>
                                                            <%}%>
                                                            <input type="hidden" name="p_ant_<%= questions.get(x).getQuestionCode()%>" value="<%= questions.get(x).getAnnexType() %>">
                                                            <%}%>
                                                        </div>
                                                        <div class="col-sm-3">
                                                            <%if (!questions.get(x).getAnnexType().equals("0")){
                                                                if (questions.get(x).getAnnexType().equals("1")){%>
                                                                    <input type="hidden" name="p_dn_<%= questions.get(x).getQuestionCode()%>" value="" id="dn_<%= questions.get(x).getQuestionCode()%>">
                                                                    <input type="file" class="custom-file-input" id="d_<%= questions.get(x).getQuestionCode()%>"
                                                                           name="p_d_<%= questions.get(x).getQuestionCode()%>" accept=".pdf,.doc,.txt,.docx"
                                                                           onchange=validar_files('d_<%= questions.get(x).getQuestionCode()%>','dn_<%= questions.get(x).getQuestionCode()%>')>
                                                                    <label class="custom-file-label" for="<%= questions.get(x).getQuestionCode()%>">Choose document file</label>
                                                                <%}else{%>
                                                                    <input type="hidden" name="p_in_<%= questions.get(x).getQuestionCode()%>" value="" id="in_<%= questions.get(x).getQuestionCode()%>">
                                                                    <input type="file" class="custom-file-input" id="i_<%= questions.get(x).getQuestionCode()%>"
                                                                           name="p_i_<%= questions.get(x).getQuestionCode()%>" accept=".png,.jpeg,.jpg,.bmp,.gif"
                                                                           onchange=validar_files('i_<%= questions.get(x).getQuestionCode()%>','in_<%= questions.get(x).getQuestionCode()%>')>
                                                                    <label class="custom-file-label" for="<%= questions.get(x).getQuestionCode()%>">Choose image file</label>
                                                                <%}%>
                                                            <%}%>
                                                        </div>
                                                        <div class="col-sm-1"></div>
                                                    </div>
                                                    <%}%>

                                                    <%
                                                        format = vivo.getFormat();
                                                        ctaLinea = vivo.getCtaLinea();
                                                        ctaPregunta = vivo.getCtaPregunta();
                                                    %>

                                                <%}%>
                                            <%}else{%>
                                                <% if (questions.get(x).getTypeRequest() == 4){ %>
                                                <div class="form-group row">
                                                    <div class="col-sm-6">
                                                        &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                                                        <p><%= questions.get(x).getBodyAnnexDoc()%> :</p>
                                                        <div class="custom-file mb-3">
                                                            <input type="hidden" name="p_ant_<%= questions.get(x).getQuestionCode()%>" value="<%= questions.get(x).getAnnexType() %>">
                                                            <input type="hidden" name="p_dn_<%= questions.get(x).getQuestionCode()%>" value="" id="dn_<%= questions.get(x).getQuestionCode()%>">
                                                            <input type="file" class="custom-file-input" id="d_<%= questions.get(x).getQuestionCode()%>"
                                                                   name="p_d_<%= questions.get(x).getQuestionCode()%>" accept=".pdf,.doc,.txt,.docx"
                                                                   onchange=validar_files('d_<%= questions.get(x).getQuestionCode()%>','dn_<%= questions.get(x).getQuestionCode()%>')>
                                                            <label class="custom-file-label" for="<%= questions.get(x).getQuestionCode()%>">Choose document file</label>
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-6">
                                                    </div>
                                                </div>
                                                <%}%>
                                                <% if (questions.get(x).getTypeRequest() == 5){ %>
                                                <div class="form-group row">
                                                    <div class="col-sm-6">
                                                        &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                                                        <p><%= questions.get(x).getBodyAnnexPhoto()%> :</p>
                                                        <div class="custom-file mb-3">
                                                            <input type="hidden" name="p_ant_<%= questions.get(x).getQuestionCode()%>" value="<%= questions.get(x).getAnnexType() %>">
                                                            <input type="hidden" name="p_in_<%= questions.get(x).getQuestionCode()%>" value="" id="in_<%= questions.get(x).getQuestionCode()%>">
                                                            <input type="file" class="custom-file-input" id="i_<%= questions.get(x).getQuestionCode()%>"
                                                                   name="p_i_<%= questions.get(x).getQuestionCode()%>" accept=".png,.jpeg,.jpg,.bmp,.gif"
                                                                   onchange=validar_files('i_<%= questions.get(x).getQuestionCode()%>','in_<%= questions.get(x).getQuestionCode()%>')>
                                                            <label class="custom-file-label" for="<%= questions.get(x).getQuestionCode()%>">Choose image file</label>
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-6">
                                                    </div>
                                                </div>
                                                <%}%>

                                                <%
                                                    format = vivo.getFormat();
                                                    ctaLinea = vivo.getCtaLinea();
                                                    ctaPregunta = vivo.getCtaPregunta();
                                                %>

                                            <%}%>
                                        </div>
                                        <% if (questions.get(x).getComment().equals("T")){ %>
                                            <div class="card-footer">
                                                <div class="form-group row">
                                                <div class="col-sm-2">
                                                    <label for="co" class="col-form-label">Comment :</label>
                                                </div>
                                                <div class="col-sm-10" >
                                                     <textarea id="co" name="p_co_<%= questions.get(x).getQuestionCode() %>" rows="1" maxlength="145"
                                                               cols="80" class="form-control" placeholder="Write here the comment." onfocus="selecciona_contenido(this)"></textarea>
                                                </div>
                                            </div>
                                            </div>
                                        <%}%>
                                    </div>
                                </div>
                            </div>
                        <%}%>
                    <%}%>
                    <hr>
                    <input name='p_code' type='hidden' value='<%= code %>' />
                    <input name='p_format' type='hidden' value='<%= format %>' />
                    <input name='p_names' type='hidden' value='<%= nombre %>' />
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