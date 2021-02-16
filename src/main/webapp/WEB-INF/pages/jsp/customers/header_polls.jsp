<%@ page import="com.psg.objectboard.model.own.ownsEntity.classDAO.OrganizationDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.psg.objectboard.model.own.ownsEntity.classDAO.TypifiedDAO" %>
<%@ page import="com.psg.objectboard.model.service.Other.OtherFunctions" %>
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
    String company_number = (String)objSesion.getAttribute("companyNumber");
    String data_user = (String)objSesion.getAttribute("dataUser");
    String data_pasword = (String)objSesion.getAttribute("dataPassword");

    String acciones = "consult";
    if(request.getParameter("p_acciones")!=null) {
        acciones = request.getParameter("p_acciones");
    }
    String column = "0";
    if(request.getParameter("p_column")!=null) {
        column = request.getParameter("p_column");
    }
    String level1 = "";
    String level2 = "";
    String level3 = "";
    String level4 = "";
    ArrayList<String> organ_1 = null;
    ArrayList<String> organ_2 = null;
    ArrayList<String> organ_3 = null;
    ArrayList<String> organ_4 = null;

    String typif1 = "";
    String typif2 = "";
    String typif3 = "";
    ArrayList<String> typif_1 = null;
    ArrayList<String> typif_2 = null;
    ArrayList<String> typif_3 = null;

    //DateFunctions dafu = new DateFunctions();

    if (acciones.equals("create") || acciones.equals("save")){

        if(request.getParameter("p_level1")!=null) {
            level1 = request.getParameter("p_level1");
        }

        OrganizationDAO org= new OrganizationDAO();
        org.setDataUser(data_user);
        org.setDataPassword(data_pasword);

        String none = "bussinessUnit_bu_bis_code = "+company_number;
        organ_1 = org.getListOrganizationLevels("DISTINCT level1",none,"level1");

        if(request.getParameter("p_level2")!=null &&
                !request.getParameter("p_level2").equals("0")) {
            level2 = request.getParameter("p_level2");
        }

        none = "bussinessUnit_bu_bis_code = "+company_number + " AND level1 = '" + level1 + "'";
        organ_2 = org.getListOrganizationLevels("DISTINCT level2",none,"level2");

        if(request.getParameter("p_level3")!=null && !request.getParameter("p_level3").equals("0")) {
            level3 = request.getParameter("p_level3");
        }

        none = "bussinessUnit_bu_bis_code = "+company_number + " AND level1 = '" + level1 + "' AND level2 = '" + level2 + "'";
        organ_3 = org.getListOrganizationLevels("DISTINCT level3",none,"level3");

        if(request.getParameter("p_level4")!=null && !request.getParameter("p_level4").equals("0")) {
            level4 = request.getParameter("p_level4");
        }

        none = "bussinessUnit_bu_bis_code = "+company_number + " AND level1 = '" + level1 +
               "' AND level2 = '" + level2 + "' AND level3 = '" + level3 + "'";
        organ_4 = org.getListOrganizationLevels("DISTINCT level4",none,"level4");

        // Ty
        if(request.getParameter("p_typif1")!=null &&
                !request.getParameter("p_typif1").equals("0")) {
            typif1 = request.getParameter("p_typif1");
        }

        TypifiedDAO typ= new TypifiedDAO();
        typ.setDataUser(data_user);
        typ.setDataPassword(data_pasword);

        none = "bussinessUnit_bu_bis_code = "+company_number;
        typif_1 = typ.getListTypifiedLevels("DISTINCT ctypified_code1",none,"ctypified_code1");

        if(request.getParameter("p_typif2")!=null &&
                !request.getParameter("p_typif2").equals("0")) {
            typif2 = request.getParameter("p_typif2");
        }

        none = "bussinessUnit_bu_bis_code = "+company_number + " AND ctypified_code1 = '" + typif1 + "'";
        typif_2 = typ.getListTypifiedLevels("DISTINCT ctypified_code2",none,"ctypified_code2");

        if(request.getParameter("p_typif3")!=null &&
                !request.getParameter("p_typif3").equals("0")) {
            typif3 = request.getParameter("p_typif3");
        }

        none = "bussinessUnit_bu_bis_code = "+company_number + " AND ctypified_code1 = '" + typif1 + "' AND ctypified_code2 = '" + typif2 + "'";
        typif_3 = typ.getListTypifiedLevels("DISTINCT ctypified_code3",none,"ctypified_code3");
    }
    OtherInserts oi = new OtherInserts();
    String idselect = "p_select";
    String idcual0 = "cual_0";
    String idcual1= "cual_01";
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Header Polls Files</title>
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
    <script src="<%= request.getContextPath() %>/complements/scripts/validaciones.js" type="text/javascript"></script>
    <!-- relacionadas con tablas -->
    <link href="<%= request.getContextPath() %>/complements/css/bootstrap-table.min.css" rel="stylesheet">
    <link href="<%= request.getContextPath() %>/complements/css/bootstrap-table-filter-control.min.css" rel="stylesheet">
    <script type="text/javascript">
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
    </script>
    <script language="JavaScript" type="text/javascript">
        <jstl:choose>
            <jstl:when test="${ rq_acciones.equals('consult') }">
                function nuevo_registro(){
                    document.forma.target = "";
                    document.forma.action = '/objectboard/headerpolls';
                    document.forma.p_acciones.value = "create";
                    document.forma.submit();
                }
            </jstl:when>
            <jstl:when test="${ rq_acciones.equals('create') }">
                function recarga_registro(){
                    document.forma.target = "";
                    document.forma.action = '/objectboard/headerpolls';
                    document.forma.p_acciones.value = "create";
                    document.forma.submit();
                }
                function nuevo_registro(){
                    if (varias_validaciones() &&
                        valida_duplicados_name(this) &&
                        valida_duplicados_reference(this)
                    ) {
                        document.forma.target = "";
                        document.forma.action = '/objectboard/headerpollsprocess';
                        document.forma.p_acciones.value = "create";
                        document.forma.submit();
                    }
                }
                function cancelar(){
                    document.forma.target = "";
                    document.forma.action = '/objectboard/headerpolls';
                    document.forma.p_acciones.value = "consult";
                    document.forma.submit();
                }
                function varias_validaciones() {
                    if (!valida_textos(document.forma.p_name.value,"Poll Name","")||
                        !valida_largos(document.forma.p_name.value.length,"Poll Name",2)||
                        !valida_textos(document.forma.p_refe.value,"Poll Reference","")||
                        !valida_largos(document.forma.p_refe.value.length,"Poll Reference",2)||
                        !valida_selects(document.forma.p_level1.value,"Organization Level 1","")||
                        !valida_selects(document.forma.p_level2.value,"Organization Level 2","")||
                        !valida_selects(document.forma.p_level3.value,"Organization Level 3","")||
                        !valida_selects(document.forma.p_level4.value,"Organization Level 4","")||
                        !valida_selects(document.forma.p_typif1.value,"Typified Level 1","")||
                        !valida_selects(document.forma.p_typif2.value,"Typified Level 2","")||
                        !valida_selects(document.forma.p_typif3.value,"Typified Level 3","")
                    ){
                        return false;
                    }
                    return true;
                }
                function valida_duplicados_name(source) {
                    var cta1=0;
                    var checkboxes=document.getElementsByTagName('input'); //obtenemos todos los controles del tipo Input los llamamboxesos check
                    for(i=0;i<checkboxes.length;i++) {//recoremos todos los controles
                        if(checkboxes[i].id == "cual_1") {
                            if (checkboxes[i].value == document.forma.p_name.value) {//solo si es un id particular entramos y validamos si esta chequeado
                                cta1 = cta1 + 1; //llevamos control de cuantos de ellos son iguales
                            }
                        }
                    }
                    if(cta1!=0){
                        alert("The Poll Name that you want to create or modify \nalready exists previously, so the requested changes will NOT be made.");
                        return false;
                    }
                    return true;
                }
                function valida_duplicados_reference(source) {
                    var cta1=0;
                    var checkboxes=document.getElementsByTagName('input'); //obtenemos todos los controles del tipo Input los llamamboxesos check
                    for(i=0;i<checkboxes.length;i++) {//recoremos todos los controles
                        if(checkboxes[i].id == "cual_2") {
                            if (checkboxes[i].value == document.forma.p_refe.value) {//solo si es un id particular entramos y validamos si esta chequeado
                                cta1 = cta1 + 1; //llevamos control de cuantos de ellos son iguales
                            }
                        }
                    }
                    if(cta1!=0){
                        alert("The Poll Reference that you want to create or modify \nalready exists previously, so the requested changes will NOT be made.");
                        return false;
                    }
                    return true;
                }
            </jstl:when>
            <jstl:when test="${ rq_acciones.equals('save') }">
                function update_registro(){
                    if (varias_validaciones() &&
                        otras_validaciones()  &&
                        valida_duplicados(this)
                    ) {
                        document.forma.target = "";
                        document.forma.action = '/objectboard/headerpollsprocess';
                        document.forma.p_acciones.value = "save";
                        document.forma.submit();
                    }
                }
                function cancelar(){
                    document.forma.target = "";
                    document.forma.action = '/objectboard/headerpolls';
                    document.forma.p_acciones.value = "consult";
                    document.forma.submit();
                }
                function varias_validaciones() {
                    if (!valida_textos(document.forma.p_name.value,"Poll Name","")||
                        !valida_largos(document.forma.p_name.value.length,"Poll Name",2)||
                        !valida_textos(document.forma.p_refe.value,"Poll Reference","")||
                        !valida_largos(document.forma.p_refe.value.length,"Poll Reference",2)||
                        !valida_selects(document.forma.p_level1.value,"Organization Level 1","")||
                        !valida_selects(document.forma.p_level2.value,"Organization Level 2","")||
                        !valida_selects(document.forma.p_level3.value,"Organization Level 3","")||
                        !valida_selects(document.forma.p_level4.value,"Organization Level 4","")||
                        !valida_selects(document.forma.p_typif1.value,"Typified Level 1","")||
                        !valida_selects(document.forma.p_typif2.value,"Typified Level 2","")||
                        !valida_selects(document.forma.p_typif3.value,"Typified Level 3","")
                    ){
                        return false;
                    }
                    return true;
                }
                function otras_validaciones() {
                    if (document.forma.p_name.value == document.forma.p_names.value &&
                        document.forma.p_refe.value == document.forma.p_refes.value &&
                        document.forma.p_level1.value == document.forma.p_levels1.value &&
                        document.forma.p_level2.value == document.forma.p_levels2.value &&
                        document.forma.p_level3.value == document.forma.p_levels3.value &&
                        document.forma.p_level4.value == document.forma.p_levels4.value &&
                        document.forma.p_typif1.value == document.forma.p_typifs1.value &&
                        document.forma.p_typif2.value == document.forma.p_typifs2.value &&
                        document.forma.p_typif3.value == document.forma.p_typifs3.value
                    ){
                        alert("No changes have been made to the fields of the selected record, so \nthere is no need to save.");
                        return false;
                    }
                    return true;
                }
                function valida_duplicados(source) {
                    var cta1=0;
                    var cta2=0;
                    var checkboxes=document.getElementsByTagName('input'); //obtenemos todos los controles del tipo Input los llamamboxesos check
                    for(i=0;i<checkboxes.length;i++) {//recoremos todos los controles
                        if(checkboxes[i].id == "cual_1") {
                            if (checkboxes[i].value == document.forma.p_name.value) {//solo si es un id particular entramos y validamos si esta chequeado
                                cta1 = cta1 + 1; //llevamos control de cuantos de ellos son iguales
                            }
                        }
                        if(checkboxes[i].id == "cual_2") {
                            if (checkboxes[i].value == document.forma.p_refe.value) {//solo si es un id particular entramos y validamos si esta chequeado
                                cta2 = cta2 + 1; //llevamos control de cuantos de ellos son iguales
                            }
                        }
                    }
                    if(cta1!=0 || cta2!=0){
                        alert("The Poll Name or references that you want to create or modify \nalready exists previously, so the requested changes will NOT be made.");
                        return false;
                    }
                    return true;
                }
            </jstl:when>
        </jstl:choose>
        function borrar_registro(){
            if(validaItems(this)){
                if ( confirm("Do You really want to delete the selected Polls?")) {
                    document.forma.action = '/objectboard/headerpollsprocess';
                    document.forma.p_acciones.value = "delete";
                    document.forma.submit();
                }
            }
        }
        function validaItems(source) {
            var cta0=0;
            var cta1=0;
            var checkboxes=document.getElementsByTagName('input'); //obtenemos todos los controles del tipo Input
            for(i=0;i<checkboxes.length;i++) {//recoremos todos los controles
                if(checkboxes[i].id === "p_select") {
                    if (checkboxes[i].checked) {
                        cta0 = cta0 + 1;
                        if(checkboxes[i].value == "T"){
                            cta1 = cta1 + 1;
                        }
                    }
                }
            }
            if(cta0===0){
                alert("There are no Polls selected to remove.");
                return false;
            }
            if(cta1 !==0 ){
                alert("There are selected surveys that have already been run, so they cannot\nbe deleted, please correct this situation and try again..");
                return false;
            }
            return true;
        }
        function valida(valor0,valor1,valor2,valor3,valor4,valor5,valor6,valor7,valor8,valor9){
            document.forma.p_code.value = valor0;

            document.forma.p_level1.value = replaceAllChart(valor1,"-"," ");
            document.forma.p_level2.value = replaceAllChart(valor2,"-"," ");
            document.forma.p_level3.value = replaceAllChart(valor3,"-"," ");
            document.forma.p_level4.value = replaceAllChart(valor4,"-"," ");
            document.forma.p_typif1.value = replaceAllChart(valor5,"-"," ");
            document.forma.p_typif2.value = replaceAllChart(valor6,"-"," ");
            document.forma.p_typif3.value = replaceAllChart(valor7,"-"," ");
            document.forma.p_names.value = replaceAllChart(valor8,"-"," ");
            document.forma.p_refes.value = replaceAllChart(valor9,"-"," ");
            document.forma.target = "";
            document.forma.p_acciones.value = "save";
            document.forma.action = "/objectboard/headerpolls";
            document.forma.submit();
        }
        function create_request(valor0,valor1,valor2,valor3) {
            if (valor0 == "T") {
                document.forma.target = "";
                document.forma.p_code.value = valor1;
                document.forma.p_names.value = replaceAllChart(valor2,"-"," ");
                document.forma.p_refes.value = replaceAllChart(valor3,"-"," ");
                document.forma.p_acciones.value = "consult";
                document.forma.action = "/objectboard/bodysurveyquestions";
                document.forma.submit();
            }else{
                alert("The process of creating requirements for this survey has been closed, \nwhich is why it is no longer possible to add more.");
            }
        }
        function close_poll(code){
            if ( confirm("When closing this survey, you will no longer be\nable to add new questions to it, without previously carrying out\nthe process of reopening it, a process that can only be carried out\nif after the survey is closed, it has NOT been executed.\nDo you really want to continue?")) {
                document.forma.p_code.value = code;
                document.forma.action = '/objectboard/headerpollsprocess';
                document.forma.p_acciones.value = "close";
                document.forma.submit();
            }
        }
        function open_poll(code){
                document.forma.p_code.value = code;
                document.forma.action = '/objectboard/headerpollsprocess';
                document.forma.p_acciones.value = "open";
                document.forma.submit();
        }
        function copy_poll(code){
            if ( confirm("Please confirm that you want to make a copy of this survey.")) {
                document.forma.p_code.value = code;
                document.forma.action = '/objectboard/headerpollsprocess';
                document.forma.p_acciones.value = "copy";
                document.forma.submit();
            }
        }
        function view_poll(code,name){
            if ( confirm("Please confirm that you want to view this survey.")) {
                document.forma.p_code.value = code;
                document.forma.p_names.value = replaceAllChart(name,"-"," ");
                document.forma.action = '/objectboard/viewsurvey';
                document.forma.p_acciones.value = "view";
                document.forma.submit();
            }
        }
        function valida_columnas(){
            document.forma.action = "/objectboard/headerpolls";
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
        <jstl:set var="jsp_previousMsg" scope="page" value="Previous menu"/>
        <jstl:set var="jsp_pagePrevious" scope="page" value="/objectboard/dashmenusuper"/>
        <%@include file="../../../../complements/jsp/navbar-header.jsp"%>
        <!--end ############################### navbar-header ###############################-->
        <!-- Contenedor central -->
        <div class="pcoded-main-container">
            <!--start ############################### page-header ###############################-->
            <jstl:set var="jsp_titleFrom" scope="page" value="Headers Polls List Form"/>
            <jstl:set var="jsp_funtionFrom" scope="page" value="List or select your options ..."/>
            <%@include file="../../../../complements/jsp/page-header.jsp"%>
            <!--end ############################### page-header ###############################-->
            <!--<section id="content">-->
            <div class="container mt-2 mb-2">
                <form class="user" role="form" method="post" action="#!" name="forma" >
                    <jstl:choose>
                        <jstl:when test="${ rq_acciones.equals('create') }">
                            <div class="form-group row">
                                <div class="col-sm-1">
                                    <label for="name" class="col-form-label">Polls Name:</label>
                                </div>
                                <div class="col-sm-3">
                                    <input name="p_name" id="name" maxlength="45" type="text" class="form-control"
                                           onfocus="selecciona_contenido(this)" value="${ rq_name }">
                                </div>
                                <div class="col-sm-1">
                                    <label for="refe" class="col-form-label">Polls Reference:</label>
                                </div>
                                <div class="col-sm-3">
                                    <input name="p_refe" id="refe" maxlength="8" type="text" class="form-control"
                                           onfocus="selecciona_contenido(this)" value="${ rq_refe }">
                                </div>
                                <div class="col-sm-1">
                                    <label for="level1" class="col-form-label">Organization L1:</label>
                                </div>
                                <div class="col-sm-3">
                                    <select name="p_level1" id="level1" class="form-control" onchange="recarga_registro()">
                                        <% if( level1.equals("") ){ %>
                                            <option value="0" selected>Select Level1</option>
                                            <% for(int x=0; x < organ_1.size(); x++){ %>
                                                <option value="<%= organ_1.get(x) %>"><%= organ_1.get(x) %></option>
                                            <% } %>
                                        <%}else{%>
                                            <option value="0">Select Level1</option>
                                            <% for(int x=0; x < organ_1.size(); x++){ %>
                                                <% if (organ_1.get(x).equals(level1)){ %>
                                                    <option value="<%= organ_1.get(x) %>" selected><%=organ_1.get(x) %></option>
                                                <%}else{%>
                                                    <option value="<%= organ_1.get(x) %>"><%= organ_1.get(x) %></option>
                                                <%}%>
                                            <% } %>
                                        <%}%>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class="col-sm-1">
                                    <label for="level2" class="col-form-label">Organization L2:</label>
                                </div>
                                <div class="col-sm-3">
                                    <select name="p_level2" id="level2" class="form-control" onchange="recarga_registro()">
                                        <% if( level2.equals("") ){ %>
                                        <option value="0" selected>Select Level2</option>
                                        <% for(int x=0; x < organ_2.size(); x++){ %>
                                        <option value="<%= organ_2.get(x) %>"><%= organ_2.get(x) %></option>
                                        <% } %>
                                        <%}else{%>
                                        <option value="0">Select Level2</option>
                                        <% for(int x=0; x < organ_2.size(); x++){ %>
                                        <% if (organ_2.get(x).equals(level2)){ %>
                                        <option value="<%= organ_2.get(x) %>" selected><%=organ_2.get(x) %></option>
                                        <%}else{%>
                                        <option value="<%= organ_2.get(x) %>"><%= organ_2.get(x) %></option>
                                        <%}%>
                                        <% } %>
                                        <%}%>
                                    </select>
                                </div>
                                <div class="col-sm-1">
                                    <label for="level3" class="col-form-label">Organization L3:</label>
                                </div>
                                <div class="col-sm-3">
                                    <select name="p_level3" id="level3" class="form-control" onchange="recarga_registro()">
                                        <% if( level3.equals("") ){ %>
                                        <option value="0" selected>Select Level3</option>
                                        <% for(int x=0; x < organ_3.size(); x++){ %>
                                        <option value="<%= organ_3.get(x) %>"><%= organ_3.get(x) %></option>
                                        <% } %>
                                        <%}else{%>
                                        <option value="0">Select Level3</option>
                                        <% for(int x=0; x < organ_3.size(); x++){ %>
                                        <% if (organ_3.get(x).equals(level3)){ %>
                                        <option value="<%= organ_3.get(x) %>" selected><%=organ_3.get(x) %></option>
                                        <%}else{%>
                                        <option value="<%= organ_3.get(x) %>"><%= organ_3.get(x) %></option>
                                        <%}%>
                                        <% } %>
                                        <%}%>
                                    </select>
                                </div>
                                <div class="col-sm-1">
                                    <label for="level4" class="col-form-label">Organization L4:</label>
                                </div>
                                <div class="col-sm-3">
                                    <select name="p_level4" id="level4" class="form-control" onchange="recarga_registro()">
                                        <% if( level4.equals("") ){ %>
                                        <option value="0" selected>Select Level4</option>
                                        <% for(int x=0; x < organ_4.size(); x++){ %>
                                        <option value="<%= organ_4.get(x) %>"><%= organ_4.get(x) %></option>
                                        <% } %>
                                        <%}else{%>
                                        <option value="0">Select Level4</option>
                                        <% for(int x=0; x < organ_4.size(); x++){ %>
                                        <% if (organ_4.get(x).equals(level4)){ %>
                                        <option value="<%= organ_4.get(x) %>" selected><%=organ_4.get(x) %></option>
                                        <%}else{%>
                                        <option value="<%= organ_4.get(x) %>"><%= organ_4.get(x) %></option>
                                        <%}%>
                                        <% } %>
                                        <%}%>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class="col-sm-1">
                                    <label for="typif1" class="col-form-label">Typified L1:</label>
                                </div>
                                <div class="col-sm-3">
                                    <select name="p_typif1" id="typif1" class="form-control" onchange="recarga_registro()">
                                        <% if( typif1.equals("") ){ %>
                                        <option value="0" selected>Select Level1</option>
                                        <% for(int x=0; x < typif_1.size(); x++){ %>
                                        <option value="<%= typif_1.get(x) %>"><%= typif_1.get(x) %></option>
                                        <% } %>
                                        <%}else{%>
                                        <option value="0">Select Level1</option>
                                        <% for(int x=0; x < typif_1.size(); x++){ %>
                                        <% if (typif_1.get(x).equals(typif1)){ %>
                                        <option value="<%= typif_1.get(x) %>" selected><%=typif_1.get(x) %></option>
                                        <%}else{%>
                                        <option value="<%= typif_1.get(x) %>"><%= typif_1.get(x) %></option>
                                        <%}%>
                                        <% } %>
                                        <%}%>
                                    </select>
                                </div>
                                <div class="col-sm-1">
                                    <label for="typif2" class="col-form-label">Typified L2:</label>
                                </div>
                                <div class="col-sm-3">
                                    <select name="p_typif2" id="typif2" escapeXml="false" class="form-control" onchange="recarga_registro()">
                                        <% if( typif2.equals("") ){ %>
                                                <option value="0" selected>Select Level2</option>
                                                <% for(int x=0; x < typif_2.size(); x++){ %>
                                                    <option value="<%= typif_2.get(x) %>"><%= typif_2.get(x) %></option>
                                                <% } %>
                                            <%}else{%>
                                                <option value="0">Select Level2</option>
                                                <% for(int x=0; x < typif_2.size(); x++){ %>
                                                    <% if (typif_2.get(x).equals(typif2)){ %>
                                                        <option value="<%= typif_2.get(x) %>" selected><%=typif_2.get(x) %></option>
                                                    <%}else{%>
                                                        <option value="<%= typif_2.get(x) %>"><%= typif_2.get(x) %></option>
                                                    <%}%>
                                                <% } %>
                                            <%}%>
                                    </select>
                                </div>
                                <div class="col-sm-1">
                                    <label for="typif3" class="col-form-label">Typified L3:</label>
                                </div>
                                <div class="col-sm-3">
                                    <select name="p_typif3" id="typif3" class="form-control" onchange="recarga_registro()">
                                        <% if( typif3.equals("") ){ %>
                                        <option value="0" selected>Select Level3</option>
                                        <% for(int x=0; x < typif_3.size(); x++){ %>
                                        <option value="<%= typif_3.get(x) %>"><%= typif_3.get(x) %></option>
                                        <% } %>
                                        <%}else{%>
                                        <option value="0">Select Level3</option>
                                        <% for(int x=0; x < typif_3.size(); x++){ %>
                                        <% if (typif_3.get(x).equals(typif3)){ %>
                                        <option value="<%= typif_3.get(x) %>" selected><%=typif_3.get(x) %></option>
                                        <%}else{%>
                                        <option value="<%= typif_3.get(x) %>"><%= typif_3.get(x) %></option>
                                        <%}%>
                                        <% } %>
                                        <%}%>
                                    </select>
                                </div>
                            </div>
                            <hr>
                        </jstl:when>
                        <jstl:when test="${ rq_acciones.equals('save') }">
                            <div class="form-group row">
                                <div class="col-sm-1">
                                    <label for="name1" class="col-form-label">Polls Name:</label>
                                </div>
                                <div class="col-sm-3">
                                    <input name="p_name" id="name1" maxlength="45" type="text" class="form-control"
                                           onfocus="selecciona_contenido(this)" value="${ rq_name }">
                                </div>
                                <div class="col-sm-1">
                                    <label for="refe1" class="col-form-label">Polls Reference:</label>
                                </div>
                                <div class="col-sm-3">
                                    <input name="p_refe" id="refe1" maxlength="8" type="text" class="form-control"
                                           onfocus="selecciona_contenido(this)" value="${ rq_refe }">
                                </div>
                                <div class="col-sm-1">
                                    <label for="level1s" class="col-form-label">Organization L1:</label>
                                </div>
                                <div class="col-sm-3">
                                    <select name="p_level1" id="level1s" class="form-control" onchange="recarga_registro()">
                                        <% if( level1.equals("") ){ %>
                                        <option value="0" selected>Select Level1</option>
                                        <% for(int x=0; x < organ_1.size(); x++){ %>
                                        <option value="<%= organ_1.get(x) %>"><%= organ_1.get(x) %></option>
                                        <% } %>
                                        <%}else{%>
                                        <option value="0">Select Level1</option>
                                        <% for(int x=0; x < organ_1.size(); x++){ %>
                                        <% if (organ_1.get(x).equals(level1)){ %>
                                        <option value="<%= organ_1.get(x) %>" selected><%=organ_1.get(x) %></option>
                                        <%}else{%>
                                        <option value="<%= organ_1.get(x) %>"><%= organ_1.get(x) %></option>
                                        <%}%>
                                        <% } %>
                                        <%}%>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class="col-sm-1">
                                    <label for="level2s" class="col-form-label">Organization L2:</label>
                                </div>
                                <div class="col-sm-3">
                                    <select name="p_level2" id="level2s" class="form-control" onchange="recarga_registro()">
                                        <% if( level2.equals("") ){ %>
                                        <option value="0" selected>Select Level2</option>
                                        <% for(int x=0; x < organ_2.size(); x++){ %>
                                        <option value="<%= organ_2.get(x) %>"><%= organ_2.get(x) %></option>
                                        <% } %>
                                        <%}else{%>
                                        <option value="0">Select Level2</option>
                                        <% for(int x=0; x < organ_2.size(); x++){ %>
                                        <% if (organ_2.get(x).equals(level2)){ %>
                                        <option value="<%= organ_2.get(x) %>" selected><%=organ_2.get(x) %></option>
                                        <%}else{%>
                                        <option value="<%= organ_2.get(x) %>"><%= organ_2.get(x) %></option>
                                        <%}%>
                                        <% } %>
                                        <%}%>
                                    </select>
                                </div>
                                <div class="col-sm-1">
                                    <label for="level3s" class="col-form-label">Organization L3:</label>
                                </div>
                                <div class="col-sm-3">
                                    <select name="p_level3" id="level3s" class="form-control" onchange="recarga_registro()">
                                        <% if( level3.equals("") ){ %>
                                        <option value="0" selected>Select Level3</option>
                                        <% for(int x=0; x < organ_3.size(); x++){ %>
                                        <option value="<%= organ_3.get(x) %>"><%= organ_3.get(x) %></option>
                                        <% } %>
                                        <%}else{%>
                                        <option value="0">Select Level3</option>
                                        <% for(int x=0; x < organ_3.size(); x++){ %>
                                        <% if (organ_3.get(x).equals(level3)){ %>
                                        <option value="<%= organ_3.get(x) %>" selected><%=organ_3.get(x) %></option>
                                        <%}else{%>
                                        <option value="<%= organ_3.get(x) %>"><%= organ_3.get(x) %></option>
                                        <%}%>
                                        <% } %>
                                        <%}%>
                                    </select>
                                </div>
                                <div class="col-sm-1">
                                    <label for="level4s" class="col-form-label">Organization L4:</label>
                                </div>
                                <div class="col-sm-3">
                                    <select name="p_level4" id="level4s" class="form-control" onchange="recarga_registro()">
                                        <% if( level4.equals("") ){ %>
                                        <option value="0" selected>Select Level4</option>
                                        <% for(int x=0; x < organ_4.size(); x++){ %>
                                        <option value="<%= organ_4.get(x) %>"><%= organ_4.get(x) %></option>
                                        <% } %>
                                        <%}else{%>
                                        <option value="0">Select Level4</option>
                                        <% for(int x=0; x < organ_4.size(); x++){ %>
                                        <% if (organ_4.get(x).equals(level4)){ %>
                                        <option value="<%= organ_4.get(x) %>" selected><%=organ_4.get(x) %></option>
                                        <%}else{%>
                                        <option value="<%= organ_4.get(x) %>"><%= organ_4.get(x) %></option>
                                        <%}%>
                                        <% } %>
                                        <%}%>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class="col-sm-1">
                                    <label for="typif1s" class="col-form-label">Typified L1:</label>
                                </div>
                                <div class="col-sm-3">
                                    <select name="p_typif1" id="typif1s" class="form-control" onchange="recarga_registro()">
                                        <% if( typif1.equals("") ){ %>
                                        <option value="0" selected>Select Level1</option>
                                        <% for(int x=0; x < typif_1.size(); x++){ %>
                                        <option value="<%= typif_1.get(x) %>"><%= typif_1.get(x) %></option>
                                        <% } %>
                                        <%}else{%>
                                        <option value="0">Select Level1</option>
                                        <% for(int x=0; x < typif_1.size(); x++){ %>
                                        <% if (typif_1.get(x).equals(typif1)){ %>
                                        <option value="<%= typif_1.get(x) %>" selected><%=typif_1.get(x) %></option>
                                        <%}else{%>
                                        <option value="<%= typif_1.get(x) %>"><%= typif_1.get(x) %></option>
                                        <%}%>
                                        <% } %>
                                        <%}%>
                                    </select>
                                </div>
                                <div class="col-sm-1">
                                    <label for="typif2s" class="col-form-label">Typified L2:</label>
                                </div>
                                <div class="col-sm-3">
                                    <select name="p_typif2" id="typif2s" class="form-control" onchange="recarga_registro()">
                                        <% if( typif2.equals("") ){ %>
                                        <option value="0" selected>Select Level2</option>
                                        <% for(int x=0; x < typif_2.size(); x++){ %>
                                        <option value="<%= typif_2.get(x) %>"><%= typif_2.get(x) %></option>
                                        <% } %>
                                        <%}else{%>
                                        <option value="0">Select Level2</option>
                                        <% for(int x=0; x < typif_2.size(); x++){ %>
                                        <% if (typif_2.get(x).equals(typif2)){ %>
                                        <option value="<%= typif_2.get(x) %>" selected><%=typif_2.get(x) %></option>
                                        <%}else{%>
                                        <option value="<%= typif_2.get(x) %>"><%= typif_2.get(x) %></option>
                                        <%}%>
                                        <% } %>
                                        <%}%>
                                    </select>
                                </div>
                                <div class="col-sm-1">
                                    <label for="typif3s" class="col-form-label">Typified L3:</label>
                                </div>
                                <div class="col-sm-3">
                                    <select name="p_typif3" id="typif3s" class="form-control" onchange="recarga_registro()">
                                        <% if( typif3.equals("") ){ %>
                                        <option value="0" selected>Select Level3</option>
                                        <% for(int x=0; x < typif_3.size(); x++){ %>
                                        <option value="<%= typif_3.get(x) %>"><%= typif_3.get(x) %></option>
                                        <% } %>
                                        <%}else{%>
                                        <option value="0">Select Level3</option>
                                        <% for(int x=0; x < typif_3.size(); x++){ %>
                                        <% if (typif_3.get(x).equals(typif3)){ %>
                                        <option value="<%= typif_3.get(x) %>" selected><%=typif_3.get(x) %></option>
                                        <%}else{%>
                                        <option value="<%= typif_3.get(x) %>"><%= typif_3.get(x) %></option>
                                        <%}%>
                                        <% } %>
                                        <%}%>
                                    </select>
                                </div>
                            </div>
                            <hr>
                        </jstl:when>
                    </jstl:choose>
                    <!-- Rounded switch -->
                    <label class="switch" title="Show more columns: On / Off">
                        <% if (column.equals("0")){%>
                        <input type="checkbox" name="p_column" value="1" onClick=valida_columnas() >
                        <%}else{%>
                        <input type="checkbox" name="p_column" value="0" onClick=valida_columnas() checked>
                        <%}%>
                        <span class="slider round"></span>
                    </label>
                    <p></p>
                    <!-- -->
                    <table id="other" cellspacing="0" width="100%" border="0">
                        <thead>
                        <jstl:choose>
                            <jstl:when test="${ rq_acciones.equals('consult') }">
                                <tr>
                                    <button type= "button" id="add" title="Create New Polls" class= "btn btn-outline-success" onClick=nuevo_registro() ><i class='fa fa-file-o fa-fw'></i> New</button>
                                    &nbsp;
                                    <button type= "button" id="remove" title="Delete Polls" class= "btn btn-outline-danger" onClick=borrar_registro() >
                                        <i class='fa fa-trash-o fa-fw'></i> Trash
                                    </button>
                                </tr>
                            </jstl:when>
                            <jstl:when test="${ rq_acciones.equals('create') }">
                                <tr>
                                    <button type= "button" id="add1" title="Create New Polls" class= "btn btn-outline-success" onClick=nuevo_registro() >
                                        <i class='fa fa-file-o fa-fw'></i> Create</button>
                                    &nbsp;
                                    <button type= "button" id="remove1" title="Delete Polls" class= "btn btn-outline-danger" onClick=borrar_registro() >
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
                                    <button type= "button" id="add2" title="Update Polls" class= "btn btn-outline-success" onClick=update_registro() >
                                        <i class='fa fa-file-o fa-fw'></i> Save
                                    </button>
                                    &nbsp;
                                    <button type= "button" id="remove2" title="Delete Polls" class= "btn btn-outline-danger" onClick=borrar_registro() >
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
                        <tr>
                            <% out.print(oi.pone_encabezadoHeaderPolls(column)); %>
                        </tr>
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
                                                    <input type='checkbox' id="<%=idselect%>" name='p_selec_<jstl:out value="${ f }"/>'
                                                           value='<jstl:out value="${ rq_polls.get(x).getExecutionStatus() }"/>' />
                                                    <input type='hidden' value='<jstl:out value="${ rq_polls.get(x).getSurveyCode() }"/>'
                                                           name='p_cual_0<jstl:out value="${ f }"/>' id="<%=idcual0%>" />&nbsp;
                                                </td>
                                                <td>
                                                    <a href='#' title="Edit"
                                                       onClick=valida('${ rq_polls.get(x).getSurveyCode() }','${ rq_polls.get(x).getOrganizationLevel1().replace(" ","-") }','${ rq_polls.get(x).getOrganizationLevel2().replace(" ","-")  }','${ rq_polls.get(x).getOrganizationLevel3().replace(" ","-")  }','${ rq_polls.get(x).getOrganizationLevel4().replace(" ","-")  }','${ rq_polls.get(x).getTypifiedCtypifiedCode1().replace(" ","-")  }','${ rq_polls.get(x).getTypifiedCtypifiedCode2().replace(" ","-")  }','${ rq_polls.get(x).getTypifiedCtypifiedCode3().replace(" ","-")  }','${ rq_polls.get(x).getName().replace(" ","-")  }','${ rq_polls.get(x).getReferences().replace(" ","-")  }')>
                                                        &nbsp;<i class='fa fa-edit fa-fw' ></i>
                                                    </a>
                                                    <a href='#' title="Add Request"
                                                       onClick=create_request('${ rq_polls.get(x).getSurveyStatus() }',${ rq_polls.get(x).getSurveyCode() },'${ rq_polls.get(x).getName().replace(" ","-") }','${ rq_polls.get(x).getReferences().replace(" ","-") }')>
                                                        &nbsp;<i class='fa fa-cogs fa-fw' ></i>
                                                    </a>
                                                    <jstl:choose>
                                                        <jstl:when test="${ rq_polls.get(x).getSurveyStatus().equals('T') &&
                                                                    rq_polls.get(x).getTotalQuestions() != 0}">
                                                            <a href='#' title="Close Poll"
                                                               onClick=close_poll(${ rq_polls.get(x).getSurveyCode() })>
                                                                &nbsp;&nbsp;<i class='fa fa-lock fa-fw' ></i>
                                                            </a>
                                                        </jstl:when>
                                                        <jstl:when test="${ rq_polls.get(x).getSurveyStatus().equals('F') &&
                                                                    rq_polls.get(x).getExecutionStatus().equals('F') }">
                                                            <a href='#' title="Open Poll"
                                                               onClick=open_poll(${ rq_polls.get(x).getSurveyCode() })>
                                                                &nbsp;<i class='fa fa-unlock fa-fw' ></i>
                                                            </a>
                                                        </jstl:when>
                                                    </jstl:choose>
                                                    <jstl:choose>
                                                        <jstl:when test="${ rq_polls.get(x).getSurveyStatus().equals('F') }">
                                                            <a href='#' title="Copy Poll"
                                                               onClick=copy_poll(${ rq_polls.get(x).getSurveyCode() })>
                                                                &nbsp;<i class='fa fa-copy fa-fw' ></i>
                                                            </a>
                                                            <a href='#' title="View Poll"
                                                               onClick=view_poll(${ rq_polls.get(x).getSurveyCode() },'${rq_polls.get(x).getName().replace(" ","-") }')>
                                                                &nbsp;<i class='fa fa-desktop fa-fw' ></i>
                                                            </a>
                                                        </jstl:when>
                                                    </jstl:choose>
                                                </td>
                                                <td>
                                                    <jstl:out value="${ rq_polls.get(x).getName() }">Lost Value</jstl:out>
                                                    <input type='hidden' value='<jstl:out value="${ rq_polls.get(x).getName() }"/>'
                                                           name='p_cual_1<jstl:out value="${ f }"/>' id="cual_1" />&nbsp;
                                                    <input type='hidden' value='<jstl:out value="${ rq_polls.get(x).getReferences() }"/>'
                                                           name='p_cual_2<jstl:out value="${ f }"/>' id="cual_2" />&nbsp;
                                                </td>
                                                <td>
                                                    <jstl:choose>
                                                        <jstl:when test="${ rq_polls.get(x).getSurveyStatus().equals('T') }">
                                                            Open
                                                        </jstl:when>
                                                        <jstl:otherwise>
                                                            Close
                                                        </jstl:otherwise>
                                                    </jstl:choose>
                                                </td>
                                                <td>
                                                    <jstl:out value="${ rq_polls.get(x).getDateCreation() }">Lost Value</jstl:out>
                                                </td>
                                                <td>
                                                    <jstl:out value="${ rq_polls.get(x).getTotalQuestions() }">Lost Value</jstl:out>
                                                </td>
                                                <td>
                                                    <jstl:choose>
                                                        <jstl:when test="${ rq_polls.get(x).getAudited().equals('T') }">
                                                            Yes
                                                        </jstl:when>
                                                        <jstl:otherwise>
                                                            No
                                                        </jstl:otherwise>
                                                    </jstl:choose>
                                                </td>
                                                <td>
                                                    <jstl:out value="${ rq_polls.get(x).getTotalPoints() }">Lost Value</jstl:out>
                                                </td>
                                            </jstl:when>
                                            <jstl:otherwise>
                                                <td>
                                                    <input type='checkbox' id="p_select" name='p_selec_<jstl:out value="${ f }"/>' />
                                                    <input type='hidden' value='<jstl:out value="${ rq_polls.get(x).getSurveyCode() }"/>'
                                                           name='p_cual_0<jstl:out value="${ f }"/>' id="cual_0" />&nbsp;
                                                    <input type='hidden' value='<jstl:out value="${ rq_polls.get(x).getExecutionStatus() }"/>'
                                                           name='p_cual_1<jstl:out value="${ f }"/>' id="cual_01" />&nbsp;
                                                </td>
                                                <td>
                                                    <a href='#' title="Edit"
                                                       onClick=valida('${ rq_polls.get(x).getSurveyCode() }','${ rq_polls.get(x).getOrganizationLevel1().replace(" ","-") }','${ rq_polls.get(x).getOrganizationLevel2().replace(" ","-")  }','${ rq_polls.get(x).getOrganizationLevel3().replace(" ","-")  }','${ rq_polls.get(x).getOrganizationLevel4().replace(" ","-")  }','${ rq_polls.get(x).getTypifiedCtypifiedCode1().replace(" ","-")  }','${ rq_polls.get(x).getTypifiedCtypifiedCode2().replace(" ","-")  }','${ rq_polls.get(x).getTypifiedCtypifiedCode3().replace(" ","-")  }','${ rq_polls.get(x).getName().replace(" ","-")  }','${ rq_polls.get(x).getReferences().replace(" ","-")  }')>
                                                        &nbsp;<i class='fa fa-edit fa-fw' ></i>
                                                    </a>
                                                    <a href='#' title="Add Request"
                                                       onClick=create_request('${ rq_polls.get(x).getSurveyStatus() }',${ rq_polls.get(x).getSurveyCode() },'${ rq_polls.get(x).getName().replace(" ","-") }','${ rq_polls.get(x).getReferences().replace(" ","-") }')>
                                                        &nbsp;<i class='fa fa-cogs fa-fw' ></i>
                                                    </a>
                                                    <jstl:choose>
                                                        <jstl:when test="${ rq_polls.get(x).getSurveyStatus().equals('T') &&
                                                                    rq_polls.get(x).getTotalQuestions() != 0}">
                                                            <a href='#' title="Close Poll"
                                                               onClick=close_poll(${ rq_polls.get(x).getSurveyCode() })>
                                                                &nbsp;&nbsp;<i class='fa fa-folder fa-fw' ></i>
                                                            </a>
                                                        </jstl:when>
                                                        <jstl:when test="${ rq_polls.get(x).getSurveyStatus().equals('F') &&
                                                                    rq_polls.get(x).getExecutionStatus().equals('F') }">
                                                            <a href='#' title="Open Poll"
                                                               onClick=open_poll(${ rq_polls.get(x).getSurveyCode() })>
                                                                &nbsp;<i class='fa fa-unlock-alt fa-fw' ></i>
                                                            </a>
                                                        </jstl:when>
                                                    </jstl:choose>
                                                    <jstl:choose>
                                                        <jstl:when test="${ rq_polls.get(x).getSurveyStatus().equals('F') }">
                                                            <a href='#' title="Copy Poll"
                                                               onClick=copy_poll(${ rq_polls.get(x).getSurveyCode() })>
                                                                &nbsp;<i class='fa fa-copy fa-fw' ></i>
                                                            </a>
                                                            <a href='#' title="View Poll"
                                                               onClick=view_poll(${ rq_polls.get(x).getSurveyCode() },'${rq_polls.get(x).getName().replace(" ","-") }')>
                                                                &nbsp;<i class='fa fa-desktop fa-fw' ></i>
                                                            </a>
                                                        </jstl:when>
                                                    </jstl:choose>
                                                </td>
                                                <td>
                                                    <jstl:out value="${ rq_polls.get(x).getName() }">Lost Value</jstl:out>
                                                    <input type='hidden' value='<jstl:out value="${ rq_polls.get(x).getName() }"/>'
                                                           name='p_cual_1<jstl:out value="${ f }"/>' id="cual_1" />&nbsp;
                                                </td>
                                                <td>
                                                    <jstl:out value="${ rq_polls.get(x).getReferences() }">Lost Value</jstl:out>
                                                    <input type='hidden' value='<jstl:out value="${ rq_polls.get(x).getReferences() }"/>'
                                                           name='p_cual_2<jstl:out value="${ f }"/>' id="cual_2" />&nbsp;
                                                </td>
                                                <td>
                                                    <jstl:choose>
                                                        <jstl:when test="${ rq_polls.get(x).getSurveyStatus().equals('T') }">
                                                            Open
                                                        </jstl:when>
                                                        <jstl:otherwise>
                                                            Close
                                                        </jstl:otherwise>
                                                    </jstl:choose>
                                                </td>
                                                <td>
                                                    <jstl:out value="${ rq_polls.get(x).getVersion() }">Lost Value</jstl:out>
                                                </td>
                                                <td>
                                                    <jstl:out value="${ rq_polls.get(x).getDateCreation() }">Lost Value</jstl:out>
                                                </td>
                                                <td>
                                                    <jstl:out value="${ rq_polls.get(x).getTotalQuestions() }">Lost Value</jstl:out>
                                                </td>
                                                <td>
                                                    <jstl:choose>
                                                        <jstl:when test="${ rq_polls.get(x).getAudited().equals('T') }">
                                                            Yes
                                                        </jstl:when>
                                                        <jstl:otherwise>
                                                            No
                                                        </jstl:otherwise>
                                                    </jstl:choose>
                                                </td>
                                                <td>
                                                    <jstl:out value="${ rq_polls.get(x).getOrganizationLevel1() }">Lost Value</jstl:out>
                                                </td>
                                                <td>
                                                    <jstl:out value="${ rq_polls.get(x).getOrganizationLevel2() }">Lost Value</jstl:out>
                                                </td>
                                                <td>
                                                    <jstl:out value="${ rq_polls.get(x).getOrganizationLevel3() }">Lost Value</jstl:out>
                                                </td>
                                                <td>
                                                    <jstl:out value="${ rq_polls.get(x).getOrganizationLevel4() }">Lost Value</jstl:out>
                                                </td>
                                                <td>
                                                    <jstl:out value="${ rq_polls.get(x).getTypifiedCtypifiedCode1() }">Lost Value</jstl:out>
                                                </td>
                                                <td>
                                                    <jstl:out value="${ rq_polls.get(x).getTypifiedCtypifiedCode2() }">Lost Value</jstl:out>
                                                </td>
                                                <td>
                                                    <jstl:out value="${ rq_polls.get(x).getTypifiedCtypifiedCode3() }">Lost Value</jstl:out>
                                                </td>
                                                <td>
                                                    <jstl:out value="${ rq_polls.get(x).getDateLastModification() }">Lost Value</jstl:out>
                                                </td>
                                                <td>
                                                    <jstl:out value="${ rq_polls.get(x).getTotalPoints() }">Lost Value</jstl:out>
                                                </td>
                                            </jstl:otherwise>
                                        </jstl:choose>
                                    </tr>
                                </jstl:forEach>
                            </jstl:when>
                            <jstl:otherwise>
                                <tr>
                                    <% out.print(oi.pone_pieHeaderPolls(column)); %>
                                </tr>
                            </jstl:otherwise>
                        </jstl:choose>
                        </tbody>
                    </table>
                    <input name='p_cuantos' type='hidden' value='<jstl:out value="${ rq_numFilas }">Lost Value</jstl:out>' />
                    <input name='p_code' type='hidden' value='${ rq_code }' />
                    <input name='p_names' type='hidden' value='${ rq_name}' />
                    <input name='p_refes' type='hidden' value='${ rq_refe}' />
                    <input name='p_pantalla' type='hidden' value='headerpolls' />
                    <input name="p_acciones" type="hidden" value='<jstl:out value="${ rq_acciones }">Lost Value</jstl:out>'/>
                    <jstl:choose>
                        <jstl:when test="${ rq_acciones.equals('consult') }">
                            <input name='p_level1' type='hidden' value='' />
                            <input name='p_level2' type='hidden' value='' />
                            <input name='p_level3' type='hidden' value='' />
                            <input name='p_level4' type='hidden' value='' />
                            <input name='p_typif1' type='hidden' value='' />
                            <input name='p_typif2' type='hidden' value='' />
                            <input name='p_typif3' type='hidden' value='' />
                        </jstl:when>
                        <jstl:when test="${ rq_acciones.equals('save') }">
                            <input name='p_levels1' type='hidden' value='<%= level1 %>' />
                            <input name='p_levels2' type='hidden' value='<%= level2 %>' />
                            <input name='p_levels3' type='hidden' value='<%= level3 %>' />
                            <input name='p_levels4' type='hidden' value='<%= level4 %>' />
                            <input name='p_typifs1' type='hidden' value='<%= typif1 %>' />
                            <input name='p_typifs2' type='hidden' value='<%= typif2 %>' />
                            <input name='p_typifs3' type='hidden' value='<%= typif3 %>' />
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
<!-- Relacionadas con tablas js
<script type="text/javascript" src="../../../../complements/js/bootstrap-table.min.js"></script>
<script type="text/javascript" src="../../../../complements/js/bootstrap-table-toolbar.min.js"></script>
<script type="text/javascript" src="../../../../complements/js/bootstrap-table-filter-control.min.js"></script>-->
<!-- Para Exportacion js
<script type="text/javascript" src="../../../../complements/js/bootstrap-table-print.min.js"></script>-->

<!-- jQuery, Popper.js, Bootstrap JS -->
<!--<script src="jquery/jquery-3.3.1.min.js"></script>-->
<!--<script src="popper/popper.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>-->
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
</body>



