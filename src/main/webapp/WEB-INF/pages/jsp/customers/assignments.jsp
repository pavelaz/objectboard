<%--
  Created by IntelliJ IDEA.
  User: pavelaz
  Date: 6/4/20
  Time: 2:30 p. m.
  To change this template use File | Settings | File Templates.
  <%@ page contentType="text/html;charset=UTF-8" language="java" %>
--%>
<%
    String idselect = "p_select";
    String idcual0 = "cual_0";
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Assignments Files</title>
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
                if(checkboxes[i].type === "checkbox" && checkboxes[i].id == "p_select") //solo si es un checkbox entramos
                {
                    checkboxes[i].checked=source.checked; //si es un checkbox le damos el valor del checkbox que lo llamó (Marcar/Desmarcar Todos)
                }
            }
        }
        <jstl:choose>
            <jstl:when test="${ rq_acciones.equals('consult') }">
                function nuevo_registro(){
                    document.forma.target = "";
                    document.forma.action = '/objectboard/assignments';
                    document.forma.p_acciones.value = "create";
                    document.forma.submit();
                }
            </jstl:when>
            <jstl:when test="${ rq_acciones.equals('create') }">
                function nuevo_registro(){
                    if (varias_validaciones() &&
                        valida_duplicados(this)
                    ) {
                        document.forma.target = "";
                        document.forma.action = '/objectboard/assignmentsprocess';
                        document.forma.p_acciones.value = "create";
                        document.forma.submit();
                    }
                }
                function cancelar(){
                    document.forma.target = "";
                    document.forma.action = '/objectboard/assignments';
                    document.forma.p_acciones.value = "consult";
                    document.forma.submit();
                }
                function varias_validaciones() {
                    if (!valida_selects(document.forma.p_user.value,"User","")||
                        !valida_selects(document.forma.p_poll.value,"Survey","")||
                        !valida_selects(document.forma.p_frequency.value,"Frequency","")||
                        !valida_numeros(document.forma.p_timef.value,"Time in frequency",1)||
                        !valida_selects(document.forma.p_note.value,"Notes","")||
                        !valida_selects(document.forma.p_legend.value,"Legends","")||
                        !valida_selects(document.forma.p_exception.value,"Exceptions","")||
                        !valida_selects(document.forma.p_term.value,"Terms","")
                    ){
                        return false;
                    }
                    return true;
                }
                function valida_duplicados(source) {
                    var cta0=0;
                    var cta1=0;
                    var checkboxes=document.getElementsByTagName('input'); //obtenemos todos los controles del tipo Input los llamamboxesos check
                    for(i=0;i<checkboxes.length;i++) {//recoremos todos los controles
                        if(checkboxes[i].id === "cual_0") {
                            if (checkboxes[i].value === document.forma.p_user.value) {//solo si es un id particular entramos y validamos si esta chequeado
                                cta0 = cta0 + 1; //llevamos control de cuantos de ellos son iguales
                            }
                        }
                        if(checkboxes[i].id === "cual_1") {
                            if (checkboxes[i].value === document.forma.p_poll.value) {//solo si es un id particular entramos y validamos si esta chequeado
                                cta1 = cta1 + 1; //llevamos control de cuantos de ellos son iguales
                            }
                        }
                    }
                    if(cta1!==0 && cta0 !==0){
                        alert("The assignments and survey that you want to create or modify \nalready exists previously, so the requested changes will NOT be made.");
                        return false;
                    }
                    return true;
                }
            </jstl:when>
            <jstl:when test="${ rq_acciones.equals('save') }">
                function update_registro(){
                    if (varias_validaciones() &&
                        valida_cambios() &&
                        valida_duplicados(this)
                    ) {
                        document.forma.target = "";
                        document.forma.action = '/objectboard/assignmentsprocess';
                        document.forma.p_acciones.value = "save";
                        document.forma.submit();
                    }
                }
                function cancelar(){
                    document.forma.target = "";
                    document.forma.action = '/objectboard/assignments';
                    document.forma.p_acciones.value = "consult";
                    document.forma.submit();
                }
                function valida_cambios(){
                    if(document.forma.p_frequency.value === document.forma.p_frecu_old.value &&
                       document.forma.p_timef.value === document.forma.p_timef_old.value &&
                       document.forma.p_note.value === document.forma.p_note_old.value &&
                       document.forma.p_legend.value === document.forma.p_legend_old.value &&
                       document.forma.p_exception.value === document.forma.p_exception_old.value &&
                       document.forma.p_term.value === document.forma.p_term_old.value
                    ){
                        alert("No changes have been made to the registry, so there is nothing to save.");
                        return false;
                    }
                    return true;
                }
                function varias_validaciones() {
                    if (!valida_selects(document.forma.p_user.value,"User","")||
                        !valida_selects(document.forma.p_poll.value,"Survey","")||
                        !valida_selects(document.forma.p_frequency.value,"Frequency","")||
                        !valida_numeros(document.forma.p_timef.value,"Time in frequency",1)||
                        !valida_selects(document.forma.p_note.value,"Notes","")||
                        !valida_selects(document.forma.p_legend.value,"Legends","")||
                        !valida_selects(document.forma.p_exception.value,"Exceptions","")||
                        !valida_selects(document.forma.p_term.value,"Terms","")
                    ){
                        return false;
                    }
                    return true;
                }
                function valida_duplicados(source) {
                    var cta0=0;
                    var cta1=0;
                    var checkboxes=document.getElementsByTagName('input'); //obtenemos todos los controles del tipo Input los llamamboxesos check
                    for(i=0;i<checkboxes.length;i++) {//recoremos todos los controles
                        if(checkboxes[i].id === "cual_0") {
                            if (checkboxes[i].value === document.forma.p_user.value) {//solo si es un id particular entramos y validamos si esta chequeado
                                cta0 = cta0 + 1; //llevamos control de cuantos de ellos son iguales
                            }
                        }
                        if(checkboxes[i].id === "cual_1") {
                            if (checkboxes[i].value === document.forma.p_poll.value) {//solo si es un id particular entramos y validamos si esta chequeado
                                cta1 = cta1 + 1; //llevamos control de cuantos de ellos son iguales
                            }
                        }
                    }
                    if(cta1!==0 && cta0 !==0){
                        alert("The assignments and survey that you want to create or modify \nalready exists previously, so the requested changes will NOT be made.");
                        return false;
                    }
                    return true;
                }
            </jstl:when>
        </jstl:choose>
        function borrar_registro(){
            if(validaItems(this)){
                if ( confirm("Do You really want to delete the selected Assignments?")) {
                    document.forma.action = '/objectboard/assignmentsprocess';
                    document.forma.p_acciones.value = "delete";
                    document.forma.submit();
                }
            }
        }
        function validaItems(source) {
            var cta=0;
            var checkboxes=document.getElementsByTagName('input'); //obtenemos todos los controles del tipo Input
            for(i=0;i<checkboxes.length;i++) {//recoremos todos los controles
                if(checkboxes[i].id === "p_select") {
                    if (checkboxes[i].checked) {//solo si es un checkbox entramos y validamos si esta chequeado
                        cta = cta + 1; //llevamos control de cuantos de ellos estan chequeados
                    }
                }
            }
            if(cta===0){
                alert("There are no Assignments selected to remove.");
                return false;
            }
            return true;
        }
        function valida(email,survey){
            document.forma.p_email.value = email;
            document.forma.p_survey.value = survey;
            document.forma.target = "";
            document.forma.p_acciones.value = "save";
            document.forma.action = "/objectboard/assignments";
            document.forma.submit();
        }
        function valida_columnas(){
            document.forma.action = "/objectboard/assignments";
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
            <jstl:set var="jsp_titleFrom" scope="page" value="Assignments List Form"/>
            <jstl:set var="jsp_funtionFrom" scope="page" value="List or select your options ..."/>
            <%@include file="../../../../complements/jsp/page-header.jsp"%>
            <!--end ############################### page-header ###############################-->
            <!--<section id="content">-->
            <div class="container mt-2 mb-2">
                <form class="user" role="form" method="post" name="forma" >
                    <jstl:choose>
                        <jstl:when test="${ rq_acciones.equals('create') }">
                            <div class="form-group row">
                                <div class="col-sm-1">
                                    <label for="user" class="col-form-label">User Name:</label>
                                </div>
                                <div class="col-sm-3">
                                    <select name="p_user" id="user" class="form-control">
                                        <option value="0" selected>Select User</option>
                                        <jstl:forEach var="x" begin="0" end="${ rq_users.size() -1 }" step="1">
                                            <option value="${rq_users.get(x).getMuEmail()}" ><jstl:out value="${ rq_users.get(x).getMuName() }">Lost Value</jstl:out></option>
                                        </jstl:forEach>
                                    </select>
                                </div>
                                <div class="col-sm-1">
                                    <label for="polls" class="col-form-label">Survey :</label>
                                </div>
                                <div class="col-sm-3">
                                    <select name="p_poll" id="polls" class="form-control">
                                        <option value="0" selected>Select Poll</option>
                                        <jstl:forEach var="x" begin="0" end="${ rq_survs.size() -1 }" step="1">
                                            <option value="${ rq_survs.get(x).getSurveyCode() }" ><jstl:out value="${ rq_survs.get(x).getName() }">Lost Value</jstl:out></option>
                                        </jstl:forEach>
                                    </select>
                                </div>
                                <div class="col-sm-2">
                                    <label for="frecuency" class="col-form-label">Frequency :</label>
                                </div>
                                <div class="col-sm-2">
                                    <select name="p_frequency" id="frecuency" class="form-control">
                                        <jstl:forEach var="x" begin="0" end="${ rq_frecu.size() -1 }" step="1">
                                            <option value="${ rq_frecu.get(x).getFr_id() }" ><jstl:out value="${ rq_frecu.get(x).getFr_description() }">Lost Value</jstl:out></option>
                                        </jstl:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class="col-sm-1">
                                    <label for="timef" class="col-form-label">Time on Frequency:</label>
                                </div>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control form-control-user" id="timef"
                                           placeholder="Number of times in frequency." name="p_timef" maxlength="3"
                                           value="">
                                </div>
                                <div class="col-sm-1">
                                    <label for="nota" class="col-form-label">Nota:</label>
                                </div>
                                <div class="col-sm-3">
                                    <select name="p_note" id="nota" class="form-control" >
                                        <option value="0" selected>Select Note</option>
                                        <jstl:forEach var="x" begin="0" end="${ rq_notes.size() -1 }" step="1">
                                            <jstl:choose>
                                                <jstl:when test="${ rq_notes.get(x).getCommentDescription().length() < 15 }">
                                                    <option value="${ rq_notes.get(x).getCommentCode() }" >
                                                            ${  rq_notes.get(x).getCommentDescription() } - ${  rq_notes.get(x).getCommentReference()}
                                                    </option>
                                                </jstl:when>
                                                <jstl:otherwise>
                                                    <option value="${ rq_notes.get(x).getCommentCode() }" >
                                                        ${ rq_notes.get(x).getCommentDescription().substring(0,15) } - ${ rq_notes.get(x).getCommentReference() }
                                                    </option>
                                                </jstl:otherwise>
                                            </jstl:choose>
                                        </jstl:forEach>
                                    </select>
                                </div>
                                <div class="col-sm-1">
                                    <label for="legend" class="col-form-label">Legend:</label>
                                </div>
                                <div class="col-sm-3">
                                    <select name="p_legend" id="legend" class="form-control">
                                        <option value="0" selected>Select Legend</option>
                                        <jstl:forEach var="x" begin="0" end="${ rq_legends.size() -1 }" step="1">
                                            <jstl:choose>
                                                <jstl:when test="${ rq_legends.get(x).getCommentDescription().length() < 15 }">
                                                    <option value="${ rq_legends.get(x).getCommentCode() }" >
                                                            ${  rq_legends.get(x).getCommentDescription() } - ${  rq_legends.get(x).getCommentReference()}
                                                    </option>
                                                </jstl:when>
                                                <jstl:otherwise>
                                                    <option value="${ rq_legends.get(x).getCommentCode() }" >
                                                            ${ rq_legends.get(x).getCommentDescription().substring(0,15) } - ${ rq_legends.get(x).getCommentReference() }
                                                    </option>
                                                </jstl:otherwise>
                                            </jstl:choose>
                                        </jstl:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class="col-sm-1">
                                    <label for="exception" class="col-form-label">Exception:</label>
                                </div>
                                <div class="col-sm-3">
                                    <select name="p_exception" id="exception" class="form-control">
                                        <option value="0" selected>Select Exception</option>
                                        <jstl:forEach var="x" begin="0" end="${ rq_exceptions.size() -1 }" step="1">
                                            <jstl:choose>
                                                <jstl:when test="${ rq_exceptions.get(x).getCommentDescription().length() < 15 }">
                                                    <option value="${ rq_exceptions.get(x).getCommentCode() }" >
                                                            ${  rq_exceptions.get(x).getCommentDescription() } - ${  rq_exceptions.get(x).getCommentReference()}
                                                    </option>
                                                </jstl:when>
                                                <jstl:otherwise>
                                                    <option value="${ rq_exceptions.get(x).getCommentCode() }" >
                                                            ${ rq_exceptions.get(x).getCommentDescription().substring(0,15) } - ${ rq_exceptions.get(x).getCommentReference() }
                                                    </option>
                                                </jstl:otherwise>
                                            </jstl:choose>
                                        </jstl:forEach>
                                    </select>
                                </div>
                                <div class="col-sm-1">
                                    <label for="term" class="col-form-label">Term:</label>
                                </div>
                                <div class="col-sm-3">
                                    <select name="p_term" id="term" class="form-control">
                                        <option value="0" selected>Select Term</option>
                                        <jstl:forEach var="x" begin="0" end="${ rq_terms.size() -1 }" step="1">
                                            <jstl:choose>
                                                <jstl:when test="${ rq_terms.get(x).getCommentDescription().length() < 15 }">
                                                    <option value="${ rq_terms.get(x).getCommentCode() }" >
                                                            ${  rq_terms.get(x).getCommentDescription() } - ${  rq_terms.get(x).getCommentReference()}
                                                    </option>
                                                </jstl:when>
                                                <jstl:otherwise>
                                                    <option value="${ rq_terms.get(x).getCommentCode() }" >
                                                            ${ rq_terms.get(x).getCommentDescription().substring(0,15) } - ${ rq_terms.get(x).getCommentReference() }
                                                    </option>
                                                </jstl:otherwise>
                                            </jstl:choose>
                                        </jstl:forEach>
                                    </select>
                                </div>
                                <div class="col-sm-1">

                                </div>
                                <div class="col-sm-3">

                                </div>
                            </div>
                            <hr>
                        </jstl:when>
                        <jstl:when test="${ rq_acciones.equals('save') }">
                            <div class="form-group row">
                                <div class="col-sm-1">
                                    <label for="users" class="col-form-label">User Name:</label>
                                </div>
                                <div class="col-sm-3">
                                    <select name="p_use" id="users" class="form-control" disabled>
                                        <option value="0" selected>Select User</option>
                                        <jstl:forEach var="x" begin="0" end="${ rq_users.size() -1 }" step="1">
                                            <jstl:choose>
                                                <jstl:when test="${ rq_users.get(x).getMuEmail() == rq_puntual.get(0).getMasterUserMuEmail() }">
                                                    <option value="${rq_users.get(x).getMuEmail()}" selected><jstl:out value="${ rq_users.get(x).getMuName() }">Lost Value</jstl:out></option>
                                                </jstl:when>
                                                <jstl:otherwise>
                                                    <option value="${rq_users.get(x).getMuEmail()}" ><jstl:out value="${ rq_users.get(x).getMuName() }">Lost Value</jstl:out></option>
                                                </jstl:otherwise>
                                            </jstl:choose>
                                        </jstl:forEach>
                                    </select>
                                    <input type="hidden" name="p_user" value="${rq_puntual.get(0).getMasterUserMuEmail()} ">
                                </div>
                                <div class="col-sm-1">
                                    <label for="pollss" class="col-form-label">Survey :</label>
                                </div>
                                <div class="col-sm-3">
                                    <select name="p_pol" id="pollss" class="form-control" disabled>
                                        <option value="0" selected>Select Poll</option>
                                        <jstl:forEach var="x" begin="0" end="${ rq_survs.size() -1 }" step="1">
                                            <jstl:choose>
                                                <jstl:when test="${ rq_survs.get(x).getSurveyCode() == rq_puntual.get(0).getHeadersSurveySurveyCode() }">
                                                    <option value="${rq_survs.get(x).getSurveyCode()}" selected><jstl:out value="${ rq_survs.get(x).getName() }">Lost Value</jstl:out></option>
                                                </jstl:when>
                                                <jstl:otherwise>
                                                    <option value="${rq_survs.get(x).getSurveyCode()}" ><jstl:out value="${ rq_survs.get(x).getName() }">Lost Value</jstl:out></option>
                                                </jstl:otherwise>
                                            </jstl:choose>
                                        </jstl:forEach>
                                    </select>
                                    <input type="hidden" name="p_poll" value="${rq_puntual.get(0).getHeadersSurveySurveyCode()}">
                                </div>
                                <div class="col-sm-2">
                                    <label for="frecuencys" class="col-form-label">Frequency :</label>
                                </div>
                                <div class="col-sm-2">
                                    <select name="p_frequency" id="frecuencys" class="form-control">
                                        <jstl:forEach var="x" begin="0" end="${ rq_frecu.size() -1 }" step="1">
                                            <jstl:choose>
                                                <jstl:when test="${ rq_frecu.get(x).getFr_id() == rq_puntual.get(0).getFrecuency() }">
                                                    <option value="${rq_frecu.get(x).getFr_id()}" selected><jstl:out value="${ rq_frecu.get(x).getFr_description() }">Lost Value</jstl:out></option>
                                                </jstl:when>
                                                <jstl:otherwise>
                                                    <option value="${rq_frecu.get(x).getFr_id()}" ><jstl:out value="${ rq_frecu.get(x).getFr_description() }">Lost Value</jstl:out></option>
                                                </jstl:otherwise>
                                            </jstl:choose>
                                        </jstl:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class="col-sm-1">
                                    <label for="timefs" class="col-form-label">Time on Frequency:</label>
                                </div>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control form-control-user" id="timefs"
                                           placeholder="Number of times in frequency." name="p_timef" maxlength="3"
                                           value="${rq_puntual.get(0).getTimeByFrecuency()}">
                                </div>
                                <div class="col-sm-1">
                                    <label for="notas" class="col-form-label">Nota:</label>
                                </div>
                                <div class="col-sm-3">
                                    <select name="p_note" id="notas" class="form-control" >
                                        <option value="0" >Select Note</option>
                                        <jstl:forEach var="x" begin="0" end="${ rq_notes.size() -1 }" step="1">
                                            <jstl:choose>
                                                <jstl:when test="${ rq_notes.get(x).getCommentCode() == rq_puntual.get(0).getNotes() }">
                                                    <jstl:choose>
                                                        <jstl:when test="${ rq_notes.get(x).getCommentDescription().length() < 15 }">
                                                            <option value="${ rq_notes.get(x).getCommentCode() }"  selected>
                                                                    ${  rq_notes.get(x).getCommentDescription() } - ${  rq_notes.get(x).getCommentReference()}
                                                            </option>
                                                        </jstl:when>
                                                        <jstl:otherwise>
                                                            <option value="${ rq_notes.get(x).getCommentCode() }"  selected>
                                                                    ${ rq_notes.get(x).getCommentDescription().substring(0,15) } - ${ rq_notes.get(x).getCommentReference() }
                                                            </option>
                                                        </jstl:otherwise>
                                                    </jstl:choose>
                                                </jstl:when>
                                                <jstl:otherwise>
                                                    <jstl:choose>
                                                        <jstl:when test="${ rq_notes.get(x).getCommentDescription().length() < 15 }">
                                                            <option value="${ rq_notes.get(x).getCommentCode() }" >
                                                                    ${  rq_notes.get(x).getCommentDescription() } - ${  rq_notes.get(x).getCommentReference()}
                                                            </option>
                                                        </jstl:when>
                                                        <jstl:otherwise>
                                                            <option value="${ rq_notes.get(x).getCommentCode() }" >
                                                                    ${ rq_notes.get(x).getCommentDescription().substring(0,15) } - ${ rq_notes.get(x).getCommentReference() }
                                                            </option>
                                                        </jstl:otherwise>
                                                    </jstl:choose>
                                                </jstl:otherwise>
                                            </jstl:choose>
                                        </jstl:forEach>
                                    </select>
                                </div>
                                <div class="col-sm-1">
                                    <label for="legends" class="col-form-label">Legend:</label>
                                </div>
                                <div class="col-sm-3">
                                    <select name="p_legend" id="legends" class="form-control">
                                        <option value="0">Select Legend</option>
                                        <jstl:forEach var="x" begin="0" end="${ rq_legends.size() -1 }" step="1">
                                            <jstl:choose>
                                                <jstl:when test="${ rq_legends.get(x).getCommentCode() == rq_puntual.get(0).getLegends() }">
                                                    <jstl:choose>
                                                        <jstl:when test="${ rq_legends.get(x).getCommentDescription().length() < 15 }">
                                                            <option value="${ rq_legends.get(x).getCommentCode() }" selected>
                                                                    ${  rq_legends.get(x).getCommentDescription() } - ${  rq_legends.get(x).getCommentReference()}
                                                            </option>
                                                        </jstl:when>
                                                        <jstl:otherwise>
                                                            <option value="${ rq_legends.get(x).getCommentCode() }" selected>
                                                                    ${ rq_legends.get(x).getCommentDescription().substring(0,15) } - ${ rq_legends.get(x).getCommentReference() }
                                                            </option>
                                                        </jstl:otherwise>
                                                    </jstl:choose>
                                                </jstl:when>
                                                <jstl:otherwise>
                                                    <jstl:choose>
                                                        <jstl:when test="${ rq_legends.get(x).getCommentDescription().length() < 15 }">
                                                            <option value="${ rq_legends.get(x).getCommentCode() }" >
                                                                    ${  rq_legends.get(x).getCommentDescription() } - ${  rq_legends.get(x).getCommentReference()}
                                                            </option>
                                                        </jstl:when>
                                                        <jstl:otherwise>
                                                            <option value="${ rq_legends.get(x).getCommentCode() }" >
                                                                    ${ rq_legends.get(x).getCommentDescription().substring(0,15) } - ${ rq_legends.get(x).getCommentReference() }
                                                            </option>
                                                        </jstl:otherwise>
                                                    </jstl:choose>
                                                </jstl:otherwise>
                                            </jstl:choose>
                                        </jstl:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class="col-sm-1">
                                    <label for="exceptions" class="col-form-label">Exception:</label>
                                </div>
                                <div class="col-sm-3">
                                    <select name="p_exception" id="exceptions" class="form-control">
                                        <option value="0">Select Exception</option>
                                        <jstl:forEach var="x" begin="0" end="${ rq_exceptions.size() -1 }" step="1">
                                            <jstl:choose>
                                                <jstl:when test="${ rq_exceptions.get(x).getCommentCode() == rq_puntual.get(0).getExceptions() }">
                                                    <jstl:choose>
                                                        <jstl:when test="${ rq_exceptions.get(x).getCommentDescription().length() < 15 }">
                                                            <option value="${ rq_exceptions.get(x).getCommentCode() }" selected>
                                                                    ${  rq_exceptions.get(x).getCommentDescription() } - ${  rq_exceptions.get(x).getCommentReference()}
                                                            </option>
                                                        </jstl:when>
                                                        <jstl:otherwise>
                                                            <option value="${ rq_exceptions.get(x).getCommentCode() }" selected>
                                                                    ${ rq_exceptions.get(x).getCommentDescription().substring(0,15) } - ${ rq_exceptions.get(x).getCommentReference() }
                                                            </option>
                                                        </jstl:otherwise>
                                                    </jstl:choose>
                                                </jstl:when>
                                                <jstl:otherwise>
                                                    <jstl:choose>
                                                        <jstl:when test="${ rq_exceptions.get(x).getCommentDescription().length() < 15 }">
                                                            <option value="${ rq_exceptions.get(x).getCommentCode() }" >
                                                                    ${  rq_exceptions.get(x).getCommentDescription() } - ${  rq_exceptions.get(x).getCommentReference()}
                                                            </option>
                                                        </jstl:when>
                                                        <jstl:otherwise>
                                                            <option value="${ rq_exceptions.get(x).getCommentCode() }" >
                                                                    ${ rq_exceptions.get(x).getCommentDescription().substring(0,15) } - ${ rq_exceptions.get(x).getCommentReference() }
                                                            </option>
                                                        </jstl:otherwise>
                                                    </jstl:choose>
                                                </jstl:otherwise>
                                            </jstl:choose>
                                        </jstl:forEach>
                                    </select>
                                </div>
                                <div class="col-sm-1">
                                    <label for="terms" class="col-form-label">Term:</label>
                                </div>
                                <div class="col-sm-3">
                                    <select name="p_term" id="terms" class="form-control">
                                        <option value="0">Select Term</option>
                                        <jstl:forEach var="x" begin="0" end="${ rq_terms.size() -1 }" step="1">
                                            <jstl:choose>
                                                <jstl:when test="${ rq_terms.get(x).getCommentCode() == rq_puntual.get(0).getTerms() }">
                                                    <jstl:choose>
                                                        <jstl:when test="${ rq_terms.get(x).getCommentDescription().length() < 15 }">
                                                            <option value="${ rq_terms.get(x).getCommentCode() }" selected>
                                                                    ${  rq_terms.get(x).getCommentDescription() } - ${  rq_terms.get(x).getCommentReference()}
                                                            </option>
                                                        </jstl:when>
                                                        <jstl:otherwise>
                                                            <option value="${ rq_terms.get(x).getCommentCode() }" selected>
                                                                    ${ rq_terms.get(x).getCommentDescription().substring(0,15) } - ${ rq_terms.get(x).getCommentReference() }
                                                            </option>
                                                        </jstl:otherwise>
                                                    </jstl:choose>
                                                </jstl:when>
                                                <jstl:otherwise>
                                                    <jstl:choose>
                                                        <jstl:when test="${ rq_terms.get(x).getCommentDescription().length() < 15 }">
                                                            <option value="${ rq_terms.get(x).getCommentCode() }" >
                                                                    ${  rq_terms.get(x).getCommentDescription() } - ${  rq_terms.get(x).getCommentReference()}
                                                            </option>
                                                        </jstl:when>
                                                        <jstl:otherwise>
                                                            <option value="${ rq_terms.get(x).getCommentCode() }" >
                                                                    ${ rq_terms.get(x).getCommentDescription().substring(0,15) } - ${ rq_terms.get(x).getCommentReference() }
                                                            </option>
                                                        </jstl:otherwise>
                                                    </jstl:choose>
                                                </jstl:otherwise>
                                            </jstl:choose>
                                        </jstl:forEach>
                                    </select>
                                </div>
                                <div class="col-sm-1">

                                </div>
                                <div class="col-sm-3">

                                </div>
                            </div>
                            <hr>
                        </jstl:when>
                    </jstl:choose>
                    <!-- Rounded switch -->
                    <label class="switch" title="Show more columns: On / Off">
                        <jstl:choose>
                            <jstl:when test="${ rq_column.equals('0') }">
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
                                    <button type= "button" id="add" title="Create New Assignment" class= "btn btn-outline-success" onClick=nuevo_registro() >
                                        <i class='fa fa-file-o fa-fw'></i> New</button>
                                    &nbsp;
                                    <button type= "button" id="remove" title="Delete Assignments" class= "btn btn-outline-danger" onClick=borrar_registro() >
                                        <i class='fa fa-trash-o fa-fw'></i> Trash
                                    </button>
                                </tr>
                            </jstl:when>
                            <jstl:when test="${ rq_acciones.equals('create') }">
                                <tr>
                                    <button type= "button" id="add1" title="Create New Assignment" class= "btn btn-outline-success" onClick=nuevo_registro() >
                                        <i class='fa fa-file-o fa-fw'></i> Create</button>
                                    &nbsp;
                                    <button type= "button" id="remove1" title="Delete Assignments" class= "btn btn-outline-danger" onClick=borrar_registro() >
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
                                    <button type= "button" id="add2" title="Update Assignment" class= "btn btn-outline-success" onClick=update_registro() >
                                        <i class='fa fa-file-o fa-fw'></i> Save
                                    </button>
                                    &nbsp;
                                    <button type= "button" id="remove2" title="Delete Assignments" class= "btn btn-outline-danger" onClick=borrar_registro() >
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
                            <jstl:choose>
                                <jstl:when test="${ rq_column.equals('0') }">
                                    <th data-field="state" data-valign="middle" data-align="center" data-sortable="false" >
                                        <input type='checkbox' onclick='marcar(this);' />
                                    </th>
                                    <th data-field="ac" data-sortable="false" data-valign="middle" data-align="center">ACTION</th>
                                    <th data-field="l1" data-sortable="true" data-switchable="false">USER EMAIL</th>
                                    <th data-field="l2" data-sortable="true" data-switchable="true">SURVEY</th>
                                    <th data-field="l3" data-sortable="true" data-switchable="true">DATE START</th>
                                    <th data-field="l5" data-sortable="true" data-switchable="true">FRECUENCY</th>
                                    <th data-field="l6" data-sortable="true" data-switchable="true">TIMES FREC.</th>
                                </jstl:when>
                                <jstl:otherwise>
                                    <th data-field="state" data-valign="middle" data-align="center" data-sortable="false" >
                                        <input type='checkbox' onclick='marcar(this);' />
                                    </th>
                                    <th data-field="ac" data-sortable="false" data-valign="middle" data-align="center">ACTION</th>
                                    <th data-field="l1" data-sortable="true" data-switchable="false">USER EMAIL</th>
                                    <th data-field="l2" data-sortable="true" data-switchable="true">SURVEY</th>
                                    <th data-field="l3" data-sortable="true" data-switchable="true">DATE START</th>
                                    <th data-field="l5" data-sortable="true" data-switchable="true">FRECUENCY</th>
                                    <th data-field="l6" data-sortable="true" data-switchable="true">TIMES FREC.</th>
                                    <th data-field="l6" data-sortable="true" data-switchable="true">NOTES</th>
                                    <th data-field="l7" data-sortable="true" data-switchable="false">LEGENDS</th>
                                    <th data-field="l9" data-sortable="true" data-switchable="true">EXCEPTIONS</th>
                                    <th data-field="l10" data-sortable="true" data-switchable="true">TERMS</th>
                                </jstl:otherwise>
                            </jstl:choose>
                        </tr>
                        </thead>
                        <tbody>
                        <jstl:choose>
                            <jstl:when test="${ rq_asigna.size() != 0 }">
                                <jstl:set var="f" value="${0}" scope="page"/>
                                <jstl:forEach var="x" begin="0" end="${ rq_asigna.size() -1 }" step="1">
                                    <jstl:set var = "f" value = "${f + 1}" />
                                    <tr>
                                        <jstl:choose>
                                            <jstl:when test="${ rq_column.equals('0') }">
                                                <td>
                                                    <input type='checkbox' id="<%=idselect%>" name='p_selec_${ f }' />
                                                    <input type='hidden' value='${ rq_asigna.get(x).getMasterUserMuEmail() }'
                                                           name='p_cual_0${ f }' id="<%=idcual0%>" />
                                                </td>
                                                <td>
                                                    <a onClick=valida('${ rq_asigna.get(x).getMasterUserMuEmail() }',${ rq_asigna.get(x).getHeadersSurveySurveyCode() })
                                                       title="Edit" >
                                                        &nbsp;<i class='fa fa-edit fa-fw' ></i>
                                                    </a>
                                                </td>
                                                <td>
                                                    <jstl:out value="${ rq_asigna.get(x).getMasterUserMuEmail() }">Lost Value</jstl:out>
                                                </td>
                                                <td>
                                                    <jstl:out value="${ rq_asigna.get(x).getSurveyDescription() }">Lost Value</jstl:out>
                                                    <input type='hidden' value='${ rq_asigna.get(x).getHeadersSurveySurveyCode() }'
                                                           name='p_cual_1${ f }' id="cual_1" />
                                                </td>
                                                <td>
                                                    <jstl:out value="${ rq_asigna.get(x).getDateStart() }">Lost Value</jstl:out>
                                                </td>
                                                <td>
                                                    <jstl:choose>
                                                        <jstl:when test="${ rq_asigna.get(x).getFrecuency() == 1 }">daily</jstl:when>
                                                        <jstl:when test="${ rq_asigna.get(x).getFrecuency() == 2 }">weekly</jstl:when>
                                                        <jstl:when test="${ rq_asigna.get(x).getFrecuency() == 3 }">biweekly</jstl:when>
                                                        <jstl:when test="${ rq_asigna.get(x).getFrecuency() == 4 }">monthly</jstl:when>
                                                        <jstl:when test="${ rq_asigna.get(x).getFrecuency() == 5 }">yearly</jstl:when>
                                                    </jstl:choose>
                                                </td>
                                                <td>
                                                    <jstl:out value="${ rq_asigna.get(x).getTimeByFrecuency() }">Lost Value</jstl:out> times
                                                </td>
                                            </jstl:when>
                                            <jstl:otherwise>
                                                <td>
                                                    <input type='checkbox' id="p_select" name='p_selec_${ f }' />
                                                    <input type='hidden' value='${ rq_asigna.get(x).getMasterUserMuEmail() }'
                                                           name='p_cual_0${ f }' id="cual_0" />
                                                </td>
                                                <td>
                                                    <a onClick=valida('${ rq_asigna.get(x).getMasterUserMuEmail() }',${ rq_asigna.get(x).getHeadersSurveySurveyCode() })
                                                       title="Edit" >
                                                        &nbsp;<i class='fa fa-edit fa-fw' ></i>
                                                    </a>
                                                </td>
                                                <td>
                                                    <jstl:out value="${ rq_asigna.get(x).getMasterUserMuEmail() }">Lost Value</jstl:out>
                                                </td>
                                                <td>
                                                    <jstl:out value="${ rq_asigna.get(x).getSurveyDescription() }">Lost Value</jstl:out>
                                                    <input type='hidden' value='${ rq_asigna.get(x).getHeadersSurveySurveyCode() }'
                                                           name='p_cual_1${ f }' id="cual_1" />
                                                </td>
                                                <td>
                                                    <jstl:out value="${ rq_asigna.get(x).getDateStart() }">Lost Value</jstl:out>
                                                </td>
                                                <td>
                                                    <jstl:choose>
                                                        <jstl:when test="${ rq_asigna.get(x).getFrecuency() == 1 }">daily</jstl:when>
                                                        <jstl:when test="${ rq_asigna.get(x).getFrecuency() == 2 }">weekly</jstl:when>
                                                        <jstl:when test="${ rq_asigna.get(x).getFrecuency() == 3 }">biweekly</jstl:when>
                                                        <jstl:when test="${ rq_asigna.get(x).getFrecuency() == 4 }">monthly</jstl:when>
                                                        <jstl:when test="${ rq_asigna.get(x).getFrecuency() == 5 }">yearly</jstl:when>
                                                    </jstl:choose>
                                                </td>
                                                <td>
                                                    <jstl:out value="${ rq_asigna.get(x).getTimeByFrecuency() }">Lost Value</jstl:out> times
                                                </td>
                                                <td>
                                                    <jstl:out value="${ rq_asigna.get(x).getNotesDescription() }">Lost Value</jstl:out>
                                                </td>
                                                <td>
                                                    <jstl:out value="${ rq_asigna.get(x).getLegendsDescription() }">Lost Value</jstl:out>
                                                </td>
                                                <td>
                                                    <jstl:out value="${ rq_asigna.get(x).getExceptionsDescription() }">Lost Value</jstl:out>
                                                </td>
                                                <td>
                                                    <jstl:out value="${ rq_asigna.get(x).getTermsDescription() }">Lost Value</jstl:out>
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
                                        </jstl:otherwise>
                                    </jstl:choose>
                                </tr>
                            </jstl:otherwise>
                        </jstl:choose>
                        </tbody>
                    </table>
                    <input name='p_cuantos' type='hidden' value='${ rq_asigna.size() }' />
                    <input name='p_email' type='hidden' value='${ rq_email }' />
                    <input name='p_survey' type='hidden' value='${ rq_survey }' />
                    <input name='p_pantalla' type='hidden' value='asignments' />
                    <input name="p_acciones" type="hidden" value='<jstl:out value="${ rq_acciones }">Lost Value</jstl:out>'/>
                    <jstl:choose>
                        <jstl:when test="${ rq_acciones.equals('save') }">
                            <input name='p_frecu_old' type='hidden' value='${ rq_puntual.get(0).getFrecuency() }' />
                            <input name='p_timef_old' type='hidden' value='${ rq_puntual.get(0).getTimeByFrecuency() }' />
                            <input name='p_note_old' type='hidden' value='${ rq_puntual.get(0).getNotes() }' />
                            <input name='p_legend_old' type='hidden' value='${ rq_puntual.get(0).getLegends() }' />
                            <input name='p_exception_old' type='hidden' value='${ rq_puntual.get(0).getExceptions() }' />
                            <input name='p_term_old' type='hidden' value='${ rq_puntual.get(0).getTerms() }' />
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
</body>