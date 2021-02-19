<%@ page import="com.psg.objectboard.model.own.ownsEntity.classDAO.OtherDAO.RequestTypeDAO" %>
<%@ page import="com.psg.objectboard.model.own.ownsEntity.classVO.OtherVO.RequestTypeVO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.psg.objectboard.model.own.ownsEntity.classDAO.OtherDAO.AnnexTypeDAO" %>
<%@ page import="com.psg.objectboard.model.own.ownsEntity.classVO.OtherVO.AnnexTypeVO" %>
<%@ page import="com.psg.objectboard.model.own.ownsEntity.classVO.BodySurveyQuestionsVO" %>
<%@ page import="com.psg.objectboard.model.own.ownsEntity.classDAO.BodySurveyQuestionsDAO" %>
<%@ page import="com.psg.objectboard.model.service.Other.OtherFunctions" %>
<%--
  Created by IntelliJ IDEA.
  User: pavelaz
  Date: 6/4/20
  Time: 2:30 p. m.
  To change this template use File | Settings | File Templates.
  <%@ page contentType="text/html;charset=UTF-8" language="java" %>
--%>
<%
    String acciones = "consult";
    if(request.getParameter("p_acciones")!=null) {
        acciones = request.getParameter("p_acciones");
    }

    ArrayList<RequestTypeVO> request_type = null;
    ArrayList<AnnexTypeVO> annex_type = null;
    if (acciones.equals("create") || acciones.equals("save") ) {
        RequestTypeDAO rt = new RequestTypeDAO();
        request_type = rt.getRequestType();

        AnnexTypeDAO at = new AnnexTypeDAO();
        annex_type = at.getAnnexType();
    }

    String request_code = null;
    ArrayList<BodySurveyQuestionsVO> recuest_puntual = null;
    if (acciones.equals("save")) {
        if(request.getParameter("p_request_code")!=null) {
            request_code = request.getParameter("p_request_code");
        }
        String code_poll = null;
        if(request.getParameter("p_code")!=null) {
            code_poll = request.getParameter("p_code");
        }
        HttpSession objSesion = request.getSession();
        String company_number = (String)objSesion.getAttribute("companyNumber");
        String condicion = null;

        BodySurveyQuestionsDAO cod= new BodySurveyQuestionsDAO();

        condicion = "headersSurvey_bussinessUnit_bu_bis_code = " +
                Integer.parseInt(company_number) +
                " AND headersSurvey_survey_code = " +
                Integer.parseInt(code_poll) +
                " AND question_code = " +
                Integer.parseInt(request_code);
        recuest_puntual = cod.getListBodySurveyQuestions(condicion);
    }
    OtherFunctions of = new OtherFunctions();
    String idselect = "select";
    String idcual = "cual_";
    String idptos = "p_ptos_";
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Requests Polls Files</title>
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
    <link rel="stylesheet" type="text/css" href=".<%= request.getContextPath() %>/complements/bootstrap/datatable_custom/datatables/datatables.min.css"/>
    <!--datables estilo bootstrap 4 CSS-->
    <link rel="stylesheet"  type="text/css" href="<%= request.getContextPath() %>/complements/bootstrap/datatable_custom/datatables/DataTables-1.10.18/css/dataTables.bootstrap4.min.css">
    <!--font awesome con CDN
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css" integrity="sha384-oS3vJWv+0UjzBfQzYUhtDYW+Pj2yciDJxpsK1OYPAYjqT085Qq/1cq5FLXAZQ7Ay" crossorigin="anonymous">
    -->
    <script src="<%= request.getContextPath() %>/complements/scripts/validaciones.js" type="text/javascript"></script>
    <!-- relacionadas con tablas -->
    <link href="<%= request.getContextPath() %>/complements/css/bootstrap-table.min.css" rel="stylesheet">
    <link href="<%= request.getContextPath() %>/complements/css/bootstrap-table-filter-control.min.css" rel="stylesheet">
    <script language="JavaScript" type="text/javascript">
        <jstl:choose>
        <jstl:when test="${ rq_acciones.equals('consult') }">
        function nuevo_registro(){
            document.forma.target = "";
            document.forma.action = '/objectboard/multipartconfigservlet';
            document.forma.p_target.value = "bodysurveyquestions";
            document.forma.p_acciones.value = "create";
            document.forma.submit();
        }
        </jstl:when>
        <jstl:when test="${ rq_acciones.equals('create') }">
        function nuevo_registro(){
            if (varias_validaciones() &&
                valida_annex_type()
            ) {
                document.forma.target = "";
                document.forma.action = '/objectboard/multipartconfigservlet';
                document.forma.p_target.value = "bodysurveyquestionsprocess";
                document.forma.p_acciones.value = "create";
                document.forma.submit();
            }
        }
        function cancelar(){
            document.forma.target = "";
            document.forma.action = '/objectboard/multipartconfigservlet';
            document.forma.p_target.value = "bodysurveyquestions";
            document.forma.p_acciones.value = "consult";
            document.forma.submit();
        }
        function valida_annex_type() {
            if (document.forma.p_annex_type.value === "0"){
                document.forma.p_bdoc.value = "";
                document.forma.p_bimg.value = "";
            }
            if (document.forma.p_annex_type.value === "1"){
                if (!valida_textos(document.forma.p_bdoc.value,"Text document request",".,:")||
                    !valida_largos(document.forma.p_bdoc.value.length,"Text document request",2)){
                    document.forma.p_bimg.value = "";
                    return false;
                }
            }
            if (document.forma.p_annex_type.value === "2"){
                if (!valida_textos(document.forma.p_bimg.value,"Text image request",".:,")||
                    !valida_largos(document.forma.p_bimg.value.length,"Text image request",2)){
                    document.forma.p_bdoc.value = "";
                    return false;
                }
            }
            return true;
        }
        function varias_validaciones() {
            if (!valida_selects(document.forma.p_type.value,"Type Request","")){
                return false;
            }
            if (!valida_montos(document.forma.p_puntos.value,"Value or points assigned",1)){
                return false;
            }
            if (document.forma.p_type.value == 1 ||
                document.forma.p_type.value == 2 ||
                document.forma.p_type.value == 3 ||
                document.forma.p_type.value == 6 ||
                document.forma.p_type.value == 7 ||
                document.forma.p_type.value == 8
            ){
                if (!valida_textos(document.forma.p_main.value,"Text main request","?,.")||
                    !valida_largos(document.forma.p_main.value.length,"Text main request",2)){
                    return false;
                }
            }
            if (document.forma.p_type.value == 4){
                if (!valida_textos(document.forma.p_main.value,"Text main request","?,.")){
                    return false;
                }else
                    document.forma.p_annex_type.value = "1";
            }
            if (document.forma.p_type.value == 5){
                if (!valida_textos(document.forma.p_main.value,"Text main request","?,.")){
                    return false;
                }else
                    document.forma.p_annex_type.value = "2";
            }
            return true;
        }
        </jstl:when>
        <jstl:when test="${ rq_acciones.equals('save') }">
        function update_registro(){
            if (varias_validaciones() &&
                valida_annex_type() &&
                valida_cambios()

            ) {
                document.forma.target = "";
                document.forma.action = '/objectboard/multipartconfigservlet';
                document.forma.p_target.value = "bodysurveyquestionsprocess";
                document.forma.p_acciones.value = "save";
                document.forma.submit();
            }
        }
        function valida_cambios() {
            if (document.forma.p_annex_type.value === document.forma.p_annex_type_old.value &&
                document.forma.p_bdoc.value === document.forma.p_bdoc_old.value &&
                document.forma.p_bimg.value === document.forma.p_bimg_old.value &&
                document.forma.p_main.value === document.forma.p_main_old.value &&
                document.forma.p_comment.value === document.forma.p_comment_old.value &&
                document.forma.p_puntos.value === document.forma.p_puntos_old.value &&
                document.forma.p_iname_ac.value === document.forma.p_iname_an.value
            ){
                alert("No changes have been made to any of the fields, so there is nothing to save.");
                return false;
            }
            return true;
        }
        function cancelar(){
            document.forma.target = "";
            document.forma.action = '/objectboard/multipartconfigservlet';
            document.forma.p_target.value = "bodysurveyquestions";
            document.forma.p_acciones.value = "consult";
            document.forma.submit();
        }
        function valida_annex_type() {
            if (document.forma.p_annex_type.value === "0"){
                document.forma.p_bdoc.value = "";
                document.forma.p_bimg.value = "";
            }
            if (document.forma.p_annex_type.value === "1"){
                if (!valida_textos(document.forma.p_bdoc.value,"Text document request",".:,")||
                    !valida_largos(document.forma.p_bdoc.value.length,"Text document request",2)){
                    document.forma.p_bimg.value = "";
                    return false;
                }else
                    document.forma.p_bimg.value = "";
            }
            if (document.forma.p_annex_type.value === "2"){
                if (!valida_textos(document.forma.p_bimg.value,"Text image request",".:,")||
                    !valida_largos(document.forma.p_bimg.value.length,"Text image request",2)){
                    document.forma.p_bdoc.value = "";
                    return false;
                }else
                    document.forma.p_bdoc.value = "";
            }
            return true;
        }
        function varias_validaciones() {
            if (!valida_montos(document.forma.p_puntos.value,"Value or points assigned",1)){
                return false;
            }
            if (document.forma.p_type.value == 1 ||
                document.forma.p_type.value == 2 ||
                document.forma.p_type.value == 3 ||
                document.forma.p_type.value == 6 ||
                document.forma.p_type.value == 7 ||
                document.forma.p_type.value == 8
            ){
                if (!valida_textos(document.forma.p_main.value,"Text main request","?,.")||
                    !valida_largos(document.forma.p_main.value.length,"Text main request",2)){
                    return false;
                }
            }
            if (document.forma.p_type.value == 4){
                if (!valida_textos(document.forma.p_main.value,"Text main request","?,.")){
                    return false;
                }else
                    document.forma.p_annex_type.value = "1";
            }
            if (document.forma.p_type.value == 5){
                if (!valida_textos(document.forma.p_main.value,"Text main request","?,.")){
                    return false;
                }else
                    document.forma.p_annex_type.value = "2";
            }
            return true;
        }
        </jstl:when>
        </jstl:choose>
        function marcar(source) {
            checkboxes=document.getElementsByTagName('input'); //obtenemos todos los controles del tipo Input
            for(i=0;i<checkboxes.length;i++) //recoremos todos los controles
            {
                if(checkboxes[i].type == "checkbox") //solo si es un checkbox entramos
                {
                    checkboxes[i].checked=source.checked; //si es un checkbox le damos el valor del checkbox que lo llamó (Marcar/Desmarcar Todos)
                }
            }
        }
        function borrar_registro(){
            if(validaItems(this)){
                if ( confirm("Do You really want to delete the selected Request?")) {
                    document.forma.action = '/objectboard/multipartconfigservlet';
                    document.forma.p_target.value = "bodysurveyquestionsprocess";
                    document.forma.p_acciones.value = "delete";
                    document.forma.submit();
                }
            }
        }
        function validaItems(source) {
            var cta=0;
            var checkboxes=document.getElementsByTagName('input'); //obtenemos todos los controles del tipo Input
            for(i=0;i<checkboxes.length;i++) {//recoremos todos los controles
                if(checkboxes[i].id == "p_select") {
                    if (checkboxes[i].checked) {//solo si es un checkbox entramos y validamos si esta chequeado
                        cta = cta + 1; //llevamos control de cuantos de ellos estan chequeados
                    }
                }
            }
            if(cta==0){
                alert("There are no Request selected to remove.");
                return false;
            }
            return true;
        }
        function valida(valor0){
            document.forma.p_request_code.value = valor0;
            document.forma.target = "";
            document.forma.p_acciones.value = "save";
            document.forma.action = "/objectboard/multipartconfigservlet";
            document.forma.p_target.value = "bodysurveyquestions";
            document.forma.submit();
        }
        function create_answers(valor0,valor1,valor2) {
            document.forma.target = "";
            document.forma.p_request_code.value = valor0;
            document.forma.p_request_type.value = valor1;
            document.forma.p_request_main.value = valor2.replace("-"," ");
            document.forma.p_acciones.value = "consult";
            document.forma.action = "/objectboard/multipartconfigservlet";
            document.forma.p_target.value = "answerssurveyrequests";
            document.forma.submit();
        }
        function valida_columnas(){
            document.forma.action = "/objectboard/multipartconfigservlet";
            document.forma.p_target.value = "bodysurveyquestions";
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
            <jstl:set var="jsp_titleFrom" scope="page" value="Requests Polls List Form"/>
            <jstl:set var="jsp_funtionFrom" scope="page" value="List or select your options ..."/>
            <%@include file="../../../../complements/jsp/page-header.jsp"%>
            <!--end ############################### page-header ###############################-->
            <!--<section id="content">-->
            <div class="container mt-2 mb-2">
                <form class="user" role="form" method="post" action="#!" name="forma" id="formPhoto" enctype="multipart/form-data" >
                    <jstl:choose>
                        <jstl:when test="${ rq_acciones.equals('create') }">
                            <div class="form-group row">
                                <div class="col-sm-2">
                                    <label for="type1">Request Type :</label>
                                </div>
                                <div class="col-sm-3">
                                    <select name="p_type" id="type1" class="form-control">
                                        <% for(int x=0; x < request_type.size(); x++){ %>
                                        <% if (request_type.get(x).getRt_id() == 0){ %>
                                        <option value="<%= request_type.get(x).getRt_id() %>" selected><%= request_type.get(x).getRt_description() %></option>
                                        <%}else{%>
                                        <option value="<%= request_type.get(x).getRt_id() %>"><%= request_type.get(x).getRt_description() %></option>
                                        <%}%>
                                        <% } %>
                                    </select>
                                    <input name="p_status_rank" type="hidden" value="F" >
                                </div>
                                <div class="col-sm-1">
                                    Annex :
                                </div>
                                <div class="col-sm-2">
                                    <select name="p_annex_type" id="atype" class="form-control">
                                        <% for(int x=0; x < annex_type.size(); x++){ %>
                                        <% if (annex_type.get(x).getAt_id() == 0){ %>
                                        <option value="<%= annex_type.get(x).getAt_id() %>" selected><%= annex_type.get(x).getAt_description() %></option>
                                        <%}else{%>
                                        <option value="<%= annex_type.get(x).getAt_id() %>"><%= annex_type.get(x).getAt_description() %></option>
                                        <%}%>
                                        <% } %>
                                    </select>
                                </div>
                                <div class="col-sm-2" align="center">
                                    <label>Request comment :</label>&nbsp;
                                </div>
                                <div class="col-sm-2" align="right">
                                    <input type="radio" id="commenta" name="p_comment" value="T">
                                    <label for="commenta">&nbsp;&nbsp;Yes</label>&nbsp;&nbsp;&nbsp;&nbsp;
                                    <input type="radio" id="commenta1" name="p_comment" value="F" checked>
                                    <label for="commenta1">&nbsp;&nbsp;&nbsp;No</label>
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class="col-sm-4">
                                    <label for="main">Text main request :</label>
                                    <textarea id="main" name="p_main" rows="3" placeholder="Write here the text to place as request for the main request." maxlength="160"
                                              cols="35" onfocus="selecciona_contenido(this)"></textarea>
                                </div>
                                <div class="col-sm-3">
                                    <label for="bdoc">Text request document :</label>
                                    <textarea id="bdoc" name="p_bdoc" rows="3" placeholder="Write here the text to place as request of the document." maxlength="45"
                                              cols="25" onfocus="selecciona_contenido(this)"></textarea>
                                </div>
                                <div class="col-sm-3">
                                    <label for="bimg">Text request image :</label>
                                    <textarea id="bimg" name="p_bimg" rows="3" placeholder="Write here the text to place as request for the Image." maxlength="45"
                                              cols="25" onfocus="selecciona_contenido(this)"></textarea>
                                </div>
                                <div class="col-sm-2">
                                    <label for="valor">Assigned value:</label>
                                    <input id="valor" name="p_puntos" placeholder="value" maxlength="5" value="1" width="8" onfocus="selecciona_contenido(this)"/>
                                </div>
                            </div>
                            <hr>
                            <div class="form-group row">
                                <div class="col-sm-4">
                                    <label for="file" class="col-sm-4 col-form-label">Upload Image:</label>
                                </div>
                                <div class="col-sm-8" align="right" name="div-photo" id="div-photo">
                                        <%--<img name="oldPhoto" id="oldPhoto" src="/objectboard/showfile.html?p_unit=${ rq_polls.get(x).getBussinessUnitBuBisCode() }&p_survey=${ rq_polls.get(x).getSurveyCode() }&p_archivo=3" class="img-thumbnail" alt="Survey image" width="100" height="120">
                                        <img name="oldPhoto" id="oldPhoto" src="/objectboard/showfile.html?p_unit=${ rq_polls.get(x).getBussinessUnitBuBisCode() }&p_survey=${ rq_polls.get(x).getSurveyCode() }&p_archivo=3" class="img-thumbnail" alt="Survey image" width="100" height="120">--%>
                                    <img name="oldPhoto" id="oldPhoto" src="/objectboard/<%= of.searchLink("3") %>/img/no_images.jpeg" class="img-thumbnail" alt="Survey image" width="100" height="120">
                                    <input name="p_file" id="file" type="file" accept="image/png, image/jpeg, image/jpg" class="image-cropper-container" align="center" onchange="filePreview(this)">
                                </div>
                            </div>
                            <hr>
                        </jstl:when>
                        <jstl:when test="${ rq_acciones.equals('save') }">
                            <div class="form-group row">
                                <div class="col-sm-2">
                                    <label for="type1">Request Code :</label>
                                </div>
                                <div class="col-sm-2">
                                    <input name='p_request_code' type='text' readonly value='<%= request_code %>' />
                                </div>
                                <div class="col-sm-8">

                                </div>
                            </div>
                            <div class="form-group row">
                                <div class="col-sm-2">
                                    <label for="type2">Request Type :</label>
                                </div>
                                <div class="col-sm-3">
                                    <input name="p_type" type="hidden" id="type2" value="<%= recuest_puntual.get(0).getTypeRequest() %>" >
                                    <input name="p_status_rank" type="hidden" value="<%= recuest_puntual.get(0).getStatusRank() %>" >
                                    <select name="p_type_nulo" id="typenulo" class="form-control" disabled>
                                        <% for(int x=0; x < request_type.size(); x++){ %>
                                        <% if (request_type.get(x).getRt_id() == recuest_puntual.get(0).getTypeRequest()){ %>
                                        <option value="<%= request_type.get(x).getRt_id() %>" selected><%= request_type.get(x).getRt_description() %></option>
                                        <%}else{%>
                                        <option value="<%= request_type.get(x).getRt_id() %>"><%= request_type.get(x).getRt_description() %></option>
                                        <%}%>
                                        <% } %>
                                    </select>
                                </div>
                                <div class="col-sm-1">
                                    Annex :
                                </div>
                                <div class="col-sm-2">
                                    <select name="p_annex_type" id="atype1" class="form-control">
                                        <% for(int x=0; x < annex_type.size(); x++){ %>
                                        <% if (annex_type.get(x).getAt_id() == Integer.parseInt(recuest_puntual.get(0).getAnnexType())){ %>
                                        <option value="<%= annex_type.get(x).getAt_id() %>" selected><%= annex_type.get(x).getAt_description() %></option>
                                        <%}else{%>
                                        <option value="<%= annex_type.get(x).getAt_id() %>"><%= annex_type.get(x).getAt_description() %></option>
                                        <%}%>
                                        <% } %>
                                    </select>
                                </div>
                                <div class="col-sm-2" align="center">
                                    <label>Request comment :</label>&nbsp;
                                </div>
                                <div class="col-sm-2" align="right">
                                    <% if (recuest_puntual.get(0).getComment().equals("T")){ %>
                                    <input type="radio" id="commenta2" name="p_comment" value="T" checked>
                                    <label for="commenta">&nbsp;&nbsp;Yes</label>&nbsp;&nbsp;&nbsp;&nbsp;
                                    <input type="radio" id="commenta3" name="p_comment" value="F" >
                                    <label for="commenta1">&nbsp;&nbsp;&nbsp;No</label>
                                    <%}else{%>
                                    <input type="radio" id="commenta2" name="p_comment" value="T">
                                    <label for="commenta">&nbsp;&nbsp;Yes</label>&nbsp;&nbsp;&nbsp;&nbsp;
                                    <input type="radio" id="commenta3" name="p_comment" value="F" checked>
                                    <label for="commenta1">&nbsp;&nbsp;&nbsp;No</label>
                                    <%}%>
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class="col-sm-4">
                                    <label for="main">Text main request :</label>
                                    <textarea id="main1" name="p_main" rows="3" placeholder="Write here the text to place as request for the main request."
                                              maxlength="160" cols="35" onfocus="selecciona_contenido(this)"><%= recuest_puntual.get(0).getMainRequest() %></textarea>
                                </div>
                                <div class="col-sm-3">
                                    <label for="bdoc">Text request document :</label>
                                    <textarea id="bdoc1" name="p_bdoc" rows="3" placeholder="Write here the text to place as request of the document." maxlength="45"
                                              cols="25" onfocus="selecciona_contenido(this)"><%= recuest_puntual.get(0).getBodyAnnexDoc() %></textarea>
                                </div>
                                <div class="col-sm-3">
                                    <label for="bimg">Text request image :</label>
                                    <textarea id="bimg1" name="p_bimg" rows="3" placeholder="Write here the text to place as request for the Image." maxlength="45"
                                              cols="25" onfocus="selecciona_contenido(this)"><%= recuest_puntual.get(0).getBodyAnnexPhoto() %></textarea>
                                </div>
                                <div class="col-sm-2">
                                    <label for="valor1">Assigned value:</label>
                                    <input id="valor1" name="p_puntos" placeholder="value" maxlength="5" value="<%= recuest_puntual.get(0).getQuestionPoints() %>" width="8" onfocus="selecciona_contenido(this)"/>
                                </div>
                            </div>
                            <hr>
                            <div class="form-group row">
                                <div class="col-sm-4">
                                    <label for="file" class="col-sm-4 col-form-label">Upload Image:</label>
                                </div>
                                <div class="col-sm-8" align="right" name="div-photo" id="div-photo1">
                                        <%--<img name="oldPhoto" id="oldPhoto" src="/objectboard/showfile.html?p_unit=${ rq_polls.get(x).getBussinessUnitBuBisCode() }&p_survey=${ rq_polls.get(x).getSurveyCode() }&p_archivo=3" class="img-thumbnail" alt="Survey image" width="100" height="120">--%>
                                    <img name="oldPhoto" id="oldPhoto" src="/objectboard/showfile.html?p_unit=${ rq_companyNumber }&p_survey=${ rq_code }&p_question=<%= request_code %>&p_archivo=4" class="img-thumbnail" alt="Survey image" width="100" height="120">
                                    <input name="p_file" id="file" type="file" accept="image/png, image/jpeg, image/jpg" class="image-cropper-container" align="center" onchange="filePreview(this)">
                                </div>
                            </div>
                            <hr>
                        </jstl:when>
                    </jstl:choose>
                    <!-- Rounded switch -->
                    <label class="switch" title="Show more columns: On / Off">
                        <jstl:choose>
                            <jstl:when test="${rq_column.equals('0')}">
                                <input type="checkbox" name="p_column" value="1" onClick=valida_columnas() >
                            </jstl:when>
                            <jstl:otherwise>
                                <input type="checkbox" name="p_column" value="0" onClick=valida_columnas() checked>
                            </jstl:otherwise>
                        </jstl:choose>
                        <span class="slider round"></span>
                    </label>
                    <p></p>
                    <!-- -->
                    <table id="other" cellspacing="0" width="100%" border="0">
                        <thead>
                        <jstl:choose>
                            <jstl:when test="${ rq_acciones.equals('consult') }">
                                <tr>
                                    <button type= "button" id="add" title="Create New Request" class= "btn btn-outline-success" onClick=nuevo_registro() ><i class='fa fa-file-o fa-fw'></i> New</button>
                                    &nbsp;
                                    <button type= "button" id="remove" title="Delete Request" class= "btn btn-outline-danger" onClick=borrar_registro() >
                                        <i class='fa fa-trash-o fa-fw'></i> Trash
                                    </button>
                                </tr>
                            </jstl:when>
                            <jstl:when test="${ rq_acciones.equals('create') }">
                                <tr>
                                    <button type= "button" id="add1" title="Create New Request" class= "btn btn-outline-success" onClick=nuevo_registro() >
                                        <i class='fa fa-file-o fa-fw'></i> Create</button>
                                    &nbsp;
                                    <button type= "button" id="remove1" title="Delete Request" class= "btn btn-outline-danger" onClick=borrar_registro() >
                                        <i class='fa fa-trash-o fa-fw'></i> Trash
                                    </button>
                                    &nbsp;
                                    <button type= "button" id="cancel1" title="Cancel operation" class= "btn btn-outline-success" onClick=cancelar() >
                                        <i class='fa fa-undo fa-fw'></i> Cancel
                                    </button>
                                </tr>
                            </jstl:when>
                            <jstl:when test="${ rq_acciones.equals('save') }">
                                <tr>
                                    <button type= "button" id="add2" title="Update Request" class= "btn btn-outline-success" onClick=update_registro() >
                                        <i class='fa fa-file-o fa-fw'></i> Save
                                    </button>
                                    &nbsp;
                                    <button type= "button" id="remove2" title="Delete Request" class= "btn btn-outline-danger" onClick=borrar_registro() >
                                        <i class='fa fa-trash-o fa-fw'></i> Trash
                                    </button>
                                    &nbsp;
                                    <button type= "button" id="cancel2" title="Cancel operation" class= "btn btn-outline-success" onClick=cancelar() >
                                        <i class='fa fa-undo fa-fw'></i> Cancel
                                    </button>
                                </tr>
                            </jstl:when>
                        </jstl:choose>
                        </thead>
                        <tbody>
                        <td>

                        </td>
                        </tbody>
                    </table>
                    <table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%"
                           data-toggle="example"
                           data-toolbar="#toolbar"
                           data-show-toggle="true"
                    >
                        <thead>
                        <jstl:choose>
                            <jstl:when test="${ rq_column.equals('0') }">
                                <tr>
                                    <th  colspan="2">
                                        Poll Name :
                                    </th>
                                    <th colspan="3">
                                        <jstl:out value="${ rq_names }">Lost Value</jstl:out>
                                    </th>
                                    <th colspan="2">
                                        Poll Reference :
                                    </th>
                                    <th colspan="4">
                                        <jstl:out value="${ rq_refes }">Lost Value</jstl:out>
                                    </th>
                                </tr>
                                <tr>
                                    <th data-field="state" data-sortable="false" >
                                        <input type='checkbox' onclick='marcar(this);' />
                                    </th>
                                    <th data-field="ac" data-sortable="false" data-valign="middle" data-align="center">ACTION</th>
                                    <th data-field="l2" data-sortable="true" data-switchable="true">TYPE</th>
                                    <th data-field="l6" data-sortable="true" data-switchable="true">MAIN REQUEST</th>
                                    <th data-field="l7" data-sortable="true" data-switchable="true">ANSWERS</th>
                                    <th data-field="l8" data-sortable="true" data-switchable="true">SOLUTIONS</th>
                                    <th data-field="l9" data-sortable="true" data-switchable="true">AUDITABLE</th>
                                    <th data-field="l10" data-sortable="true" data-switchable="true">REF</th>
                                    <th data-field="l9" data-sortable="true" data-switchable="true">POINTS</th>
                                </tr>
                            </jstl:when>
                            <jstl:otherwise>
                                <tr>
                                    <th  colspan="2">
                                        Poll Name :
                                    </th>
                                    <th colspan="5">
                                        <jstl:out value="${ rq_names }">Lost Value</jstl:out>
                                    </th>
                                    <th colspan="2">
                                        Poll Reference :
                                    </th>
                                    <th colspan="4">
                                        <jstl:out value="${ rq_refes }">Lost Value</jstl:out>
                                    </th>
                                </tr> <!-- aqui menos -->
                                <tr>
                                    <th data-field="state" data-sortable="false" >
                                        <input type='checkbox' onclick='marcar(this);' />
                                    </th>
                                    <th data-field="ac" data-sortable="false" data-valign="middle" data-align="center">ACTION</th>
                                    <th data-field="l2" data-sortable="true" data-switchable="true">TYPE</th>
                                    <th data-field="l6" data-sortable="true" data-switchable="true">MAIN REQUEST</th>
                                    <th data-field="l7" data-sortable="true" data-switchable="true">ANSWERS</th>
                                    <th data-field="l8" data-sortable="true" data-switchable="true">SOLUTIONS</th>
                                    <th data-field="l9" data-sortable="true" data-switchable="true">AUDITABLE</th>
                                    <th data-field="l10" data-sortable="true" data-switchable="true">REF</th>
                                    <th data-field="l9" data-sortable="true" data-switchable="true">POINTS</th>
                                    <th data-field="l3" data-sortable="true" data-switchable="true">ANNEX TYPE</th>
                                    <th data-field="l5" data-sortable="true" data-switchable="true">COMMENT</th>
                                    <th data-field="l4" data-sortable="true" data-switchable="true">TEXT ANNEX DOC</th>
                                    <th data-field="l4" data-sortable="true" data-switchable="true">TEXT ANNEX IMG</th>
                                </tr>
                            </jstl:otherwise>
                        </jstl:choose>
                        </thead>
                        <tbody>
                        <jstl:choose>
                            <jstl:when test="${ rq_numFilas != 0 }">
                                <jstl:set var="f" value="${0}" scope="page"/>
                                <jstl:forEach var="x" begin="0" end="${ rq_numFilas -1 }" step="1">
                                    <jstl:set var = "f" value = "${f + 1}" />
                                    <tr>
                                        <jstl:choose>
                                            <jstl:when test="${ rq_column.equals('0') }">
                                                <td>
                                                    <input type='checkbox' id="p_<%=idselect%>" name='p_selec_<jstl:out value="${ f }"/>' />
                                                    <input type='hidden' value='<jstl:out value="${ rq_request.get(x).getQuestionCode() }"/>'
                                                           name='p_cual_0<jstl:out value="${ f }"/>' id="<%=idcual%>_0" />
                                                    <input type='hidden' value='<jstl:out value="${ rq_request.get(x).getQuestionPoints() }"/>'
                                                           name='p_ptos_<jstl:out value="${ f }"/>' id="<%=idptos%>>" />
                                                </td>
                                                <td>
                                                    <jstl:choose>
                                                        <jstl:when test="${ rq_request.get(x).getTypeRequest() > 5 }">
                                                            <a href='#' title="Edit Request" onClick=valida('${ rq_request.get(x).getQuestionCode() }')>
                                                                <i class='fa fa-edit fa-fw' ></i>
                                                            </a>
                                                            &nbsp;
                                                        </jstl:when>
                                                        <jstl:when test="${ rq_request.get(x).getTypeRequest() < 6 }">
                                                            <a href='#' title="Edit Request" onClick=valida('${ rq_request.get(x).getQuestionCode() }')>
                                                                <i class='fa fa-edit fa-fw' ></i>
                                                            </a>
                                                            &nbsp;
                                                        </jstl:when>
                                                    </jstl:choose>
                                                    <jstl:choose>
                                                        <jstl:when test="${ rq_acciones.equals('consult') &&
                                                                        rq_request.get(x).getTypeRequest() != 4 &&
                                                                        rq_request.get(x).getTypeRequest() != 5 }">
                                                            <a href='#' title="Add / Edit Answer to Request"
                                                               onClick=create_answers('${ rq_request.get(x).getQuestionCode() }','${ rq_request.get(x).getTypeRequest() }','${ rq_request.get(x).getMainRequest().replace(" ","-") }')>
                                                                <i class='fa fa-cogs fa-fw' ></i>
                                                            </a>
                                                        </jstl:when>
                                                    </jstl:choose>
                                                    &nbsp;
                                                </td>
                                                <td>
                                                    <jstl:choose>
                                                        <jstl:when test="${ rq_request.get(x).getTypeRequest() == 1 }">Boolean (Yes or Not)</jstl:when>
                                                        <jstl:when test="${ rq_request.get(x).getTypeRequest() == 2 }">Unique selection</jstl:when>
                                                        <jstl:when test="${ rq_request.get(x).getTypeRequest() == 3 }">Multi selection</jstl:when>
                                                        <jstl:when test="${ rq_request.get(x).getTypeRequest() == 4 }">Only document</jstl:when>
                                                        <jstl:when test="${ rq_request.get(x).getTypeRequest() == 5 }">Only image</jstl:when>
                                                        <jstl:when test="${ rq_request.get(x).getTypeRequest() == 6 }">Input text</jstl:when>
                                                        <jstl:when test="${ rq_request.get(x).getTypeRequest() == 7 }">Input number</jstl:when>
                                                        <jstl:when test="${ rq_request.get(x).getTypeRequest() == 8 }">Input time</jstl:when>
                                                        <jstl:when test="${ rq_request.get(x).getTypeRequest() == 9 }">Input date</jstl:when>
                                                    </jstl:choose>
                                                </td>
                                                <td>
                                                    <jstl:out value="${ rq_request.get(x).getMainRequest() }">Lost Value</jstl:out>
                                                </td>
                                                <td>
                                                    <jstl:out value="${ rq_request.get(x).getAnswerNumber() }">Lost Value</jstl:out>
                                                </td>
                                                <td>
                                                    <jstl:out value="${ rq_request.get(x).getSolutionNumber() }">Lost Value</jstl:out>
                                                </td>
                                                <td>
                                                    <jstl:choose>
                                                        <jstl:when test="${ rq_request.get(x).getTypeRequest() == 6 ||
                                                                            rq_request.get(x).getTypeRequest() == 8 ||
                                                                            rq_request.get(x).getTypeRequest() == 9}">
                                                            <jstl:choose>
                                                                <jstl:when test="${ rq_request.get(x).getAuditableSolution().equals('T') }">
                                                                    Yes
                                                                </jstl:when>
                                                                <jstl:otherwise>
                                                                    No
                                                                </jstl:otherwise>
                                                            </jstl:choose>
                                                        </jstl:when>
                                                        <jstl:otherwise>
                                                            <jstl:choose>
                                                                <jstl:when test="${ rq_request.get(x).getAuditableSolution().equals('T') }">
                                                                    Yes
                                                                </jstl:when>
                                                                <jstl:otherwise>
                                                                    No
                                                                </jstl:otherwise>
                                                            </jstl:choose>
                                                        </jstl:otherwise>
                                                    </jstl:choose>
                                                </td>
                                                <td>
                                                    <jstl:choose>
                                                        <jstl:when test="${ rq_request.get(x).getTypeRequest() == 6 ||
                                                                            rq_request.get(x).getTypeRequest() == 8 ||
                                                                            rq_request.get(x).getTypeRequest() == 9}">
                                                            <jstl:choose>
                                                                <jstl:when test="${ rq_request.get(x).getAuditableAnswerSolution().equals('T') }">
                                                                    <a href='#' title="Auditable response">
                                                                        <i class='fa fa-star fa-fw' ></i>
                                                                    </a>
                                                                </jstl:when>
                                                                <jstl:otherwise>
                                                                    <a href='#!' title="Not Auditable response">
                                                                        <i class='fa fa-star-o fa-fw' ></i>
                                                                    </a>
                                                                </jstl:otherwise>

                                                            </jstl:choose>
                                                        </jstl:when>
                                                        <jstl:otherwise>
                                                            <jstl:if test="${ rq_request.get(x).getTypeRequest() == 7 &&
                                                                              rq_request.get(x).getAuditableAnswerSolution().equals('F') &&
                                                                              rq_request.get(x).getStatusRank().equals('T') }">
                                                                <a href='#' title="Not Auditable response with rank">
                                                                    <i class='fa fa-circle fa-fw' ></i>
                                                                </a>
                                                            </jstl:if>
                                                            <jstl:if test="${ rq_request.get(x).getTypeRequest() == 7 &&
                                                                              rq_request.get(x).getAuditableAnswerSolution().equals('F') &&
                                                                              rq_request.get(x).getStatusRank().equals('F') }">
                                                                <a href='#!' title="Not Auditable response without rank">
                                                                    <i class='fa fa-circle-o fa-fw' ></i>
                                                                </a>
                                                            </jstl:if>
                                                            <jstl:if test="${ rq_request.get(x).getTypeRequest() == 7 &&
                                                                              rq_request.get(x).getAuditableAnswerSolution().equals('T') &&
                                                                              rq_request.get(x).getAuditableSolution().equals('F') }">
                                                                <a href='#' title="With Auditable response">
                                                                    <i class='fa fa-dot-circle-o fa-fw' ></i>
                                                                </a>
                                                            </jstl:if>
                                                        </jstl:otherwise>
                                                    </jstl:choose>
                                                </td>
                                                <td>
                                                    <jstl:out value="${ rq_request.get(x).getQuestionPoints() }">Lost Value</jstl:out>
                                                </td>
                                            </jstl:when>
                                            <jstl:otherwise>
                                                <td>
                                                    <input type='checkbox' id="p_select" name='p_selec_<jstl:out value="${ f }"/>' />
                                                    <input type='hidden' value='<jstl:out value="${ rq_request.get(x).getQuestionCode() }"/>'
                                                           name='p_cual_0<jstl:out value="${ f }"/>' id="cual_0" />
                                                    <input type='hidden' value='<jstl:out value="${ rq_request.get(x).getQuestionPoints() }"/>'
                                                           name='p_ptos_<jstl:out value="${ f }"/>' id="p_ptos_" />
                                                </td>
                                                <td>
                                                    <jstl:choose>
                                                        <jstl:when test="${ rq_request.get(x).getTypeRequest() > 5 }">
                                                            <a href='#' title="Edit Request" onClick=valida('${ rq_request.get(x).getQuestionCode() }')>
                                                                <i class='fa fa-edit fa-fw' ></i>
                                                            </a>
                                                            &nbsp;
                                                        </jstl:when>
                                                        <jstl:when test="${ rq_request.get(x).getTypeRequest() < 6 }">
                                                            <a href='#' title="Edit Request" onClick=valida('${ rq_request.get(x).getQuestionCode() }')>
                                                                <i class='fa fa-edit fa-fw' ></i>
                                                            </a>
                                                            &nbsp;
                                                        </jstl:when>
                                                    </jstl:choose>
                                                    <jstl:choose>
                                                        <jstl:when test="${ rq_acciones.equals('consult') &&
                                                                        rq_request.get(x).getTypeRequest() != 4 &&
                                                                        rq_request.get(x).getTypeRequest() != 5 }">
                                                            <a href='#' title="Add / Edit Answer to Request"
                                                               onClick=create_answers('${ rq_request.get(x).getQuestionCode() }','${ rq_request.get(x).getTypeRequest() }','${ rq_request.get(x).getMainRequest().replace(" ","-") }')>
                                                                <i class='fa fa-cogs fa-fw' ></i>
                                                            </a>
                                                        </jstl:when>
                                                    </jstl:choose>
                                                    &nbsp;
                                                </td>
                                                <td>
                                                    <jstl:choose>
                                                        <jstl:when test="${ rq_request.get(x).getTypeRequest() == 1 }">Boolean (Yes or Not)</jstl:when>
                                                        <jstl:when test="${ rq_request.get(x).getTypeRequest() == 2 }">Unique selection</jstl:when>
                                                        <jstl:when test="${ rq_request.get(x).getTypeRequest() == 3 }">Multi selection</jstl:when>
                                                        <jstl:when test="${ rq_request.get(x).getTypeRequest() == 4 }">Only document</jstl:when>
                                                        <jstl:when test="${ rq_request.get(x).getTypeRequest() == 5 }">Only image</jstl:when>
                                                        <jstl:when test="${ rq_request.get(x).getTypeRequest() == 6 }">Input text</jstl:when>
                                                        <jstl:when test="${ rq_request.get(x).getTypeRequest() == 7 }">Input number</jstl:when>
                                                        <jstl:when test="${ rq_request.get(x).getTypeRequest() == 8 }">Input time</jstl:when>
                                                        <jstl:when test="${ rq_request.get(x).getTypeRequest() == 9 }">Input date</jstl:when>
                                                    </jstl:choose>
                                                </td>
                                                <td>
                                                    <jstl:out value="${ rq_request.get(x).getMainRequest() }">Lost Value</jstl:out>
                                                </td>
                                                <td>
                                                    <jstl:out value="${ rq_request.get(x).getAnswerNumber() }">Lost Value</jstl:out>
                                                </td>
                                                <td>
                                                    <jstl:out value="${ rq_request.get(x).getSolutionNumber() }">Lost Value</jstl:out>
                                                </td>
                                                <td>
                                                    <jstl:choose>
                                                        <jstl:when test="${ rq_request.get(x).getTypeRequest() == 6 ||
                                                                            rq_request.get(x).getTypeRequest() == 8 ||
                                                                            rq_request.get(x).getTypeRequest() == 9}">
                                                            <jstl:choose>
                                                                <jstl:when test="${ rq_request.get(x).getAuditableSolution().equals('T') }">
                                                                    Yes
                                                                </jstl:when>
                                                                <jstl:otherwise>
                                                                    No
                                                                </jstl:otherwise>
                                                            </jstl:choose>
                                                        </jstl:when>
                                                        <jstl:otherwise>
                                                            <jstl:choose>
                                                                <jstl:when test="${ rq_request.get(x).getAuditableSolution().equals('T') }">
                                                                    Yes
                                                                </jstl:when>
                                                                <jstl:otherwise>
                                                                    No
                                                                </jstl:otherwise>
                                                            </jstl:choose>
                                                        </jstl:otherwise>
                                                    </jstl:choose>
                                                </td>
                                                <td>
                                                    <jstl:choose>
                                                        <jstl:when test="${ rq_request.get(x).getTypeRequest() == 6 ||
                                                                            rq_request.get(x).getTypeRequest() == 8 ||
                                                                            rq_request.get(x).getTypeRequest() == 9}">
                                                            <jstl:choose>
                                                                <jstl:when test="${ rq_request.get(x).getAuditableAnswerSolution().equals('T') }">
                                                                    <a href='#' title="Auditable response">
                                                                        <i class='fa fa-star fa-fw' ></i>
                                                                    </a>
                                                                </jstl:when>
                                                                <jstl:otherwise>
                                                                    <a href='#!' title="Not Auditable response">
                                                                        <i class='fa fa-star-o fa-fw' ></i>
                                                                    </a>
                                                                </jstl:otherwise>

                                                            </jstl:choose>
                                                        </jstl:when>
                                                        <jstl:otherwise>
                                                            <jstl:if test="${ rq_request.get(x).getTypeRequest() == 7 &&
                                                                              rq_request.get(x).getAuditableAnswerSolution().equals('F') &&
                                                                              rq_request.get(x).getStatusRank().equals('T') }">
                                                                <a href='#' title="Not Auditable response with rank">
                                                                    <i class='fa fa-circle fa-fw' ></i>
                                                                </a>
                                                            </jstl:if>
                                                            <jstl:if test="${ rq_request.get(x).getTypeRequest() == 7 &&
                                                                              rq_request.get(x).getAuditableAnswerSolution().equals('F') &&
                                                                              rq_request.get(x).getStatusRank().equals('F') }">
                                                                <a href='#!' title="Not Auditable response without rank">
                                                                    <i class='fa fa-circle-o fa-fw' ></i>
                                                                </a>
                                                            </jstl:if>
                                                            <jstl:if test="${ rq_request.get(x).getTypeRequest() == 7 &&
                                                                              rq_request.get(x).getAuditableAnswerSolution().equals('T') &&
                                                                              rq_request.get(x).getAuditableSolution().equals('F') }">
                                                                <a href='#' title="With Auditable response">
                                                                    <i class='fa fa-dot-circle-o fa-fw' ></i>
                                                                </a>
                                                            </jstl:if>
                                                        </jstl:otherwise>
                                                    </jstl:choose>
                                                </td>
                                                <td>
                                                    <jstl:out value="${ rq_request.get(x).getQuestionPoints() }">Lost Value</jstl:out>
                                                </td>
                                                <td>
                                                    <jstl:choose>
                                                        <jstl:when test="${ rq_request.get(x).getAnnexType().equals('0') }">Not Annex</jstl:when>
                                                        <jstl:when test="${ rq_request.get(x).getAnnexType().equals('1') }">Document</jstl:when>
                                                        <jstl:when test="${ rq_request.get(x).getAnnexType().equals('2') }">Image</jstl:when>
                                                    </jstl:choose>
                                                </td>
                                                <td>
                                                    <jstl:choose>
                                                        <jstl:when test="${ rq_request.get(x).getComment().equals('T') }">
                                                            Yes
                                                        </jstl:when>
                                                        <jstl:otherwise>
                                                            No
                                                        </jstl:otherwise>
                                                    </jstl:choose>
                                                </td>
                                                <td>
                                                    <jstl:out value="${ rq_request.get(x).getBodyAnnexDoc() }">Lost Value</jstl:out>
                                                </td>
                                                <td>
                                                    <jstl:out value="${ rq_request.get(x).getBodyAnnexPhoto() }">Lost Value</jstl:out>
                                                </td>
                                            </jstl:otherwise>
                                        </jstl:choose>
                                    </tr>
                                </jstl:forEach>
                            </jstl:when>
                            <jstl:otherwise>
                                <tr>
                                    <jstl:choose>
                                        <jstl:when test="${ rq_column.equals('0') }">
                                            <td>
                                                &nbsp;
                                            </td>
                                            <td>
                                                Not Action
                                            </td>
                                            <td>
                                                Not data
                                            </td>
                                            <td>
                                                Not data
                                            </td>
                                            <td>
                                                Not data
                                            </td>
                                            <td>
                                                Not data
                                            </td>
                                            <td>
                                                Not data
                                            </td>
                                            <td>
                                                Not data
                                            </td>
                                            <td>
                                                Not data
                                            </td>
                                        </jstl:when>
                                        <jstl:otherwise>
                                            <td>
                                                &nbsp;
                                            </td>
                                            <td>
                                                Not Action
                                            </td>
                                            <td>
                                                Not data
                                            </td>
                                            <td>
                                                Not data
                                            </td>
                                            <td>
                                                Not data
                                            </td>
                                            <td>
                                                Not data
                                            </td>
                                            <td>
                                                Not data
                                            </td>
                                            <td>
                                                Not data
                                            </td>
                                            <td>
                                                Not data
                                            </td>
                                            <td>
                                                Not data
                                            </td>
                                            <td>
                                                Not data
                                            </td>
                                            <td>
                                                Not data
                                            </td>
                                            <td>
                                                Not data
                                            </td>
                                        </jstl:otherwise>
                                    </jstl:choose>
                                </tr>
                            </jstl:otherwise>
                        </jstl:choose>
                        </tbody>
                    </table>
                    <input name='p_cuantos' type='hidden' value='<jstl:out value="${ rq_numFilas }">Lost Value</jstl:out>' />
                    <input name='p_code' type='hidden' value='${ rq_code }' />
                    <input name='p_names' type='hidden' value='${ rq_names}' />
                    <input name='p_refes' type='hidden' value='${ rq_refes}' />
                    <input name='p_pantalla' type='hidden' value='questionspolls' />
                    <input name='p_target' type='hidden' value='' />
                    <input name='p_iname_ac' type='hidden' value='${ rq_iname_an }' />
                    <input name="p_acciones" type="hidden" value='<jstl:out value="${ rq_acciones }">Lost Value</jstl:out>'/>
                    <jstl:choose>
                        <jstl:when test="${ rq_acciones.equals('consult') }">
                            <input name='p_request_code' type='hidden' value='' />
                            <input name='p_request_type' type='hidden' value='' />
                            <input name='p_request_main' type='hidden' value='' />
                        </jstl:when>
                        <jstl:when test="${ rq_acciones.equals('save') }">
                            <input name='p_comment_old' type='hidden' value='<%= recuest_puntual.get(0).getComment() %>' />
                            <input name='p_annex_type_old' type='hidden' value='<%= recuest_puntual.get(0).getAnnexType() %>' />
                            <input name='p_bdoc_old' type='hidden' value='<%= recuest_puntual.get(0).getBodyAnnexDoc() %>' />
                            <input name='p_bimg_old' type='hidden' value='<%= recuest_puntual.get(0).getBodyAnnexPhoto() %>' />
                            <input name='p_main_old' type='hidden' value='<%= recuest_puntual.get(0).getMainRequest() %>' />
                            <input name='p_puntos_old' type='hidden' value='<%= recuest_puntual.get(0).getQuestionPoints() %>' />
                            <input name='p_iname_an' type='hidden' value='${ rq_iname_an }' />
                        </jstl:when>
                    </jstl:choose>
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
<script type="text/javascript" src="<%= request.getContextPath() %>/complements/bootstrap/datatable_custom/datatables/datatables.min.js"></script>
<!-- para usar botones en datatables JS -->
<script src="<%= request.getContextPath() %>/complements/bootstrap/datatable_custom/datatables/Buttons-1.5.6/js/dataTables.buttons.min.js"></script>
<script src="<%= request.getContextPath() %>/complements/bootstrap/datatable_custom/datatables/JSZip-2.5.0/jszip.min.js"></script>
<script src="<%= request.getContextPath() %>/complements/bootstrap/datatable_custom/datatables/pdfmake-0.1.36/pdfmake.min.js"></script>
<script src="<%= request.getContextPath() %>/complements/bootstrap/datatable_custom/datatables/pdfmake-0.1.36/vfs_fonts.js"></script>
<script src="<%= request.getContextPath() %>/complements/bootstrap/datatable_custom/datatables/Buttons-1.5.6/js/buttons.html5.min.js"></script>

<!-- código JS propìo-->
<script type="text/javascript" src="<%= request.getContextPath() %>/complements/bootstrap/datatable_custom/main.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $('#example').DataTable({
            "scrollY": 675,
            "scrollX": true,
            //para usar los botones
            responsive: "true",
            dom: 'Bfrtilp',
            buttons:[
                {
                    extend:    'excelHtml5',
                    text:      '<i class="fa fa-table"></i> ',
                    titleAttr: 'Export to Excel',
                    className: 'btn btn-success'
                },
                {
                    extend:    'print',
                    text:      '<i class="fa fa-print"></i> ',
                    titleAttr: 'Print',
                    className: 'btn btn-info'
                },
                {
                    extend:    'pdfHtml5',
                    text:      '<i class="fa fa-list-alt"></i> ',
                    titleAttr: 'Export to PDF',
                    className: 'btn btn-primary'
                },
            ]
        });
    });
</script>
<script type="text/javascript">
    function filePreview(input) {
        var oldPhoto = document.getElementById('oldPhoto');
        var filePath = input.value;
        var allowedExtensions = /(\.jpg|\.png|\.jpeg)$/i;
        if (!allowedExtensions.exec(filePath)) {
            alert("Please upload file having extensions .jpg/ .png/ .jpeg/ only.");
            input.value = "";
            return false;
        }else{
            if (input.files[0].size > (1024 * 1024 * 1)){ // 1024 * 1024 * 1,= 1 MB)
                alert("Please upload a photo not exceeding 1 Mb.");
                input.value = "";
                return false;
            }
            else{
                if (input.files && input.files[0]) {
                    var reader = new FileReader();
                    var image = new Image();
                    document.forma.p_iname_ac.value = input.files[0].name;
                    reader.onload = function (e) {
                        image.src = e.target.result;
                        //Validate the File Height and Width.
                        image.onload = function (){
                            var height = this.height;
                            var width = this.width;
                            //if ( 600 > height && 600 > width){
                            oldPhoto.remove();
                            //alert("Height and Width must not exceed a Photo the."+height+" "+width);
                            $('#file').before('<img id="oldPhoto" name="oldPhoto" class="img-thumbnail" alt="Photo Profile" src="' + e.target.result + '" width="100" height="120" /> &nbsp');
                            // return false;
                            //}
                            alert("The uploaded image has a valid height and width of 500 x 499,\n" +
                                "and if it exceeds these dimensions, it will be readjusted upon storage. ");
                            return true;
                        }
                    }
                    reader.readAsDataURL(input.files[0]);
                }
            }
        }
    }

</script>
</body>



