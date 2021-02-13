<%--
  Created by IntelliJ IDEA.
  User: pavelaz
  Date: 6/4/20
  Time: 2:30 p. m.
  To change this template use File | Settings | File Templates.
  <%@ page contentType="text/html;charset=UTF-8" language="java" %>
--%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Answers Requests Polls Files</title>
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
    <link rel=stylesheet href=https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css />
    <!-- relacionadas con tablas -->
    <link href="<%= request.getContextPath() %>/complements/css/bootstrap-table.min.css" rel="stylesheet">
    <link href="<%= request.getContextPath() %>/complements/css/bootstrap-table-filter-control.min.css" rel="stylesheet">
    <script language="JavaScript" type="text/javascript">
        <jstl:choose>
            <jstl:when test="${ rq_acciones.equals('consult') }">
                function nuevo_registro(){
                    if (document.forma.p_cuantos.value == 2 && document.forma.p_request_type.value == 1){
                        alert("This type of question cannot include more than two answers.");
                    }else {
                        if (document.forma.p_cuantos.value == 1 && (document.forma.p_request_type.value == 6 ||
                            document.forma.p_request_type.value == 7 || document.forma.p_request_type.value == 8 ||
                            document.forma.p_request_type.value == 9)){
                            alert("This type of question cannot include more than one answers.");
                        }else {
                            document.forma.target = "";
                            document.forma.action = '/objectboard/answerssurveyrequests';
                            document.forma.p_acciones.value = "create";
                            document.forma.submit();
                        }
                    }
                }
            </jstl:when>
            <jstl:when test="${ rq_acciones.equals('create') }">
                function nuevo_registro(){
                    if (varias_validaciones()) {
                        document.forma.target = "";
                        document.forma.action = '/objectboard/answerrequestprocess';
                        document.forma.p_acciones.value = "create";
                        document.forma.submit();
                    }
                }
                function cancelar(){
                    document.forma.target = "";
                    document.forma.action = '/objectboard/answerssurveyrequests';
                    document.forma.p_acciones.value = "consult";
                    document.forma.submit();
                }
                function varias_validaciones() {
                    if (document.forma.p_request_type.value != 6 && document.forma.p_request_type.value != 7 &&
                        document.forma.p_request_type.value != 8 && document.forma.p_request_type.value != 9 ) {
                        if (!valida_textos(document.forma.p_answer.value, "Text answer request", "?,.") ||
                            !valida_largos(document.forma.p_answer.value.length, "Text answer request", 2)) {
                            return false;
                        }
                    }
                    if (document.forma.p_request_type.value == 1){
                        if (document.forma.p_cuantos.value == 2 ){
                            alert("With this type of question, no more than two answers are allowed.");
                            return  false;
                        }
                        if (document.forma.p_audi_solu.value === "T"){
                            //alert("With this type of question, the answer cannot be auditable.");
                            document.forma.p_audi_solu.value = "F";
                            //return  false;
                        }
                        if (document.forma.p_cuantos.value != 0) {
                            var ctos = validaItems_t1(this);
                            if (ctos == 1 && document.forma.p_answ_solu.value == "T") {
                                alert("With this type of question, there can only be one option as a solution,\ndelete or change the previous one to be able to include this\nas a solution to the question.");
                                return false;
                            }
                            if (ctos == 0 && document.forma.p_answ_solu.value == "F") {
                                alert("With this type of question, there must be an option as a solution,\nchange the previous one or be able to include this as a solution\nto the question.");
                                return false;
                            }
                        }
                    }
                    if (document.forma.p_request_type.value == 2){
                        if (document.forma.p_audi_solu.value === "T"){
                            //alert("With this type of question, the answer cannot be auditable.");
                            document.forma.p_audi_solu.value = "F";
                            //return  false;
                        }
                        if (document.forma.p_cuantos.value != 0) {
                            var ctos = validaItems_t1(this);
                            if (ctos == 1 && document.forma.p_answ_solu.value == "T") {
                                alert("With this type of question, there can only be one option as a solution,\ndelete or change the previous one to be able to include this\nas a solution to the question.");
                                return false;
                            }
                        }
                    }
                    if (document.forma.p_request_type.value == 3){
                        if (document.forma.p_audi_solu.value === "T"){
                            //alert("With this type of question, the answer cannot be auditable.");
                            document.forma.p_audi_solu.value = "F";
                            //return  false;
                        }
                    }
                    if (document.forma.p_request_type.value == 6){
                        if (document.forma.p_audi_solu.value === "F"){
                            if (!valida_textos(document.forma.p_answer_only_text.value, "Text answer request", "") ||
                                !valida_largos(document.forma.p_answer_only_text.value.length, "Text answer request", 2)) {
                                return false;
                            }
                        }else
                            document.forma.p_answer_only_text.value = "";
                    }
                    if (document.forma.p_request_type.value == 7){
                        if (document.forma.p_audi_solu.value === "F"){
                            if (document.forma.p_status_rank.value === "T") {
                                if (!valida_montos(document.forma.p_min.value, "Minimum range value", 0) ||
                                    !valida_montos(document.forma.p_max.value, "Maximum range value", 0)) {
                                    return false;
                                }

                                var maximo = parseFloat(document.forma.p_max.value);
                                var minimo  = parseFloat(document.forma.p_min.value);

                                alert(maximo);
                                alert(minimo);

                                // Ambos igual a cero
                                if (maximo == 0 && minimo == 0){
                                    alert("Under this figure, the maximum and minimum range cannot both be equal to zero.");
                                    return false;
                                }

                                // Ambos positivos, diferentes de cero, iguales entre ellos
                                if (maximo > 0 && minimo > 0 &&  maximo == minimo){
                                    alert("In the case of a totally positive range, both values cannot be equal.");
                                    return false;
                                }

                                // Ambos negativos, diferentes de cero, iguales entre ellos
                                if (maximo < 0 && minimo < 0 &&  maximo == minimo){
                                    alert("In the case of a totally negative range, both values cannot be equal.");
                                    return false;
                                }

                                // Ambos positivos, diferentes de cero, diferentes entre ellos
                                if (maximo > 0 && minimo > 0 &&  maximo < minimo){
                                    alert("In the case of a totally positive range, the maximum value cannot be less than the minimum.");
                                    return false;
                                }

                                // Ambos negativos, diferentes de cero, diferentes entre ellos
                                if (maximo < 0 && minimo < 0 &&  maximo > minimo){
                                    alert("In the case of a totally negative range, the minimum value cannot be less than the maximum.");
                                    return false;
                                }

                                // en caso de un rango mixto, ambos diferentes de cero, maximo < 0
                                if (maximo !=0 && minimo != 0 &&  maximo < 0){
                                    alert("In the case of a mixed range, that is (between positive and negative), where both are different\n" +
                                        "from zero, the maximum value cannot be negative. ");
                                    return false;
                                }

                                // maximo igual a cero y minimo es mayor que cero
                                if (maximo == 0 && minimo > 0){
                                    alert("If the maximum is equal to zero, the minimum cannot be greater than zero.");
                                    return false;
                                }

                                // minimo igual a cero y maximo es menor que cero
                                if (maximo == 0 && minimo > 0){
                                    alert("If the minimum is equal to zero, the maximum cannot be less than zero. ");
                                    return false;
                                }

                                document.forma.p_answer_only_number.value = 0;
                            }else{
                                if (!valida_montos(document.forma.p_answer_only_number.value,"Answer Solution Number",parseFloat(1))) {
                                    return false;
                                }
                                document.forma.p_min.value = 0;
                                document.forma.p_max.value = 0;
                            }
                        }else
                            document.forma.p_answer_only_number.value = 0;
                    }
                    if (document.forma.p_request_type.value == 8){
                        if (document.forma.p_audi_solu.value === "F"){
                           if (!valida_hora(document.forma.p_answer_only_time.value, "Time answer request")) {
                                    return false;
                           }
                        }else
                           document.forma.p_answer_only_time.value = "";
                    }
                    if (document.forma.p_request_type.value == 9){
                        if (document.forma.p_audi_solu.value === "F"){
                            if (!valida_largos(document.forma.p_answer_only_date.value.length, "Date answer request", 10)) {
                                    return false;
                                }
                        }else
                            document.forma.p_answer_only_date.value = "";
                    }
                    return true;
                }
                function validaItems_t1(source) {
                    var cta=0;
                    var element=document.getElementsByTagName('input'); //obtenemos todos los controles del tipo Input
                    for(i=0;i<element.length;i++) {//recoremos todos los controles
                        if(element[i].id == "cual_1") {
                            if (element[i].value == ("T")) {
                                cta = cta + 1; //llevamos control de cuantos de ellos estan chequeados
                            }
                        }
                    }
                    return cta;
                }
            </jstl:when>
            <jstl:when test="${ rq_acciones.equals('save') }">
                function cancelar(){
                    document.forma.target = "";
                    document.forma.action = '/objectboard/answerssurveyrequests';
                    document.forma.p_acciones.value = "consult";
                    document.forma.submit();
                }
                function varias_validaciones() {
                    if (document.forma.p_request_type.value != 6 && document.forma.p_request_type.value != 7 &&
                        document.forma.p_request_type.value != 8 && document.forma.p_request_type.value != 9 ) {
                        if (!valida_textos(document.forma.p_answer.value, "Text answer request", "?,.") ||
                            !valida_largos(document.forma.p_answer.value.length, "Text answer request", 2)) {
                            return false;
                        }
                        if (document.forma.p_answer.value == document.forma.p_answer_old.value &&
                            //document.forma.p_audi_solu.value == document.forma.p_audi_solu_old.value &&
                            document.forma.p_answ_solu.value == document.forma.p_answ_solu_old.value) {
                            alert("No changes have been made, so there is nothing to save");
                            return false;
                        }
                    }
                    if (document.forma.p_request_type.value == 1){
                        if (document.forma.p_cuantos.value == 2 ){
                            alert("With this type of question, no more than two answers are allowed.");
                            return  false;
                        }
                        if (document.forma.p_audi_solu.value === "T"){
                            //alert("With this type of question, the answer cannot be auditable.");
                            document.forma.p_audi_solu.value = "F";
                            //return  false;
                        }
                        if (document.forma.p_cuantos.value != 0) {
                            var ctos = validaItems_t1(this);
                            if (ctos == 1 && document.forma.p_answ_solu.value == "T") {
                                alert("With this type of question, there can only be one option as a solution,\ndelete or change the previous one to be able to include this\nas a solution to the question.");
                                return false;
                            }
                            if (ctos == 0 && document.forma.p_answ_solu.value == "F") {
                                alert("With this type of question, there must be an option as a solution,\nchange the previous one or be able to include this as a solution\nto the question.");
                                return false;
                            }
                        }
                        if(document.forma.p_answ_solu.value != document.forma.p_answ_solu_old.value){
                            if(document.forma.p_answ_solu.value == "T")
                                document.forma.p_change.value = 1;
                            else
                                document.forma.p_change.value = -1;
                        }else
                            document.forma.p_change.value = 0;
                    }
                    if (document.forma.p_request_type.value == 2){
                        if (document.forma.p_audi_solu.value === "T"){
                            //alert("With this type of question, the answer cannot be auditable.");
                            document.forma.p_audi_solu.value = "F";
                            //return  false;
                        }
                        if (document.forma.p_cuantos.value != 0) {
                            var ctos = validaItems_t1(this);
                            if (ctos == 1 && document.forma.p_answ_solu.value == "T") {
                                alert("With this type of question, there can only be one option as a solution,\ndelete or change the previous one to be able to include this\nas a solution to the question.");
                                return false;
                            }
                        }
                        if(document.forma.p_answ_solu.value != document.forma.p_answ_solu_old.value){
                            if(document.forma.p_answ_solu.value == "T")
                                document.forma.p_change.value = 1;
                            else
                                document.forma.p_change.value = -1;
                        }else
                            document.forma.p_change.value = 0;
                    }
                    if (document.forma.p_request_type.value == 3){
                        if (document.forma.p_audi_solu.value === "T"){
                            //alert("With this type of question, the answer cannot be auditable.");
                            document.forma.p_audi_solu.value = "F";
                            //return  false;
                        }
                        if(document.forma.p_answ_solu.value != document.forma.p_answ_solu_old.value){
                            if(document.forma.p_answ_solu.value == "T")
                                document.forma.p_change.value = 1;
                            else
                                document.forma.p_change.value = -1;
                        }else
                            document.forma.p_change.value = 0;
                    }
                    if (document.forma.p_request_type.value == 6){
                        if (document.forma.p_audi_solu.value === "F"){
                            if (!valida_textos(document.forma.p_answer_only_text.value, "Text answer request", "") ||
                                !valida_largos(document.forma.p_answer_only_text.value.length, "Text answer request", 2)) {
                                return false;
                            }
                        }else
                            document.forma.p_answer_only_text.value = "";
                    }
                    if (document.forma.p_request_type.value == 7){
                        if (document.forma.p_audi_solu.value === "F"){
                            if (document.forma.p_status_rank.value === "T") {
                                if (!valida_montos(document.forma.p_min.value, "Minimum range value", 0) ||
                                    !valida_montos(document.forma.p_max.value, "Maximum range value", 0)) {
                                    return false;
                                }

                                var maximo = parseFloat(document.forma.p_max.value);
                                var minimo  = parseFloat(document.forma.p_min.value);

                                alert(maximo);
                                alert(minimo);

                                // Ambos igual a cero
                                if (maximo == 0 && minimo == 0){
                                    alert("Under this figure, the maximum and minimum range cannot both be equal to zero.");
                                    return false;
                                }

                                // Ambos positivos, diferentes de cero, iguales entre ellos
                                if (maximo > 0 && minimo > 0 &&  maximo == minimo){
                                    alert("In the case of a totally positive range, both values cannot be equal.");
                                    return false;
                                }

                                // Ambos negativos, diferentes de cero, iguales entre ellos
                                if (maximo < 0 && minimo < 0 &&  maximo == minimo){
                                    alert("In the case of a totally negative range, both values cannot be equal.");
                                    return false;
                                }

                                // Ambos positivos, diferentes de cero, diferentes entre ellos
                                if (maximo > 0 && minimo > 0 &&  maximo < minimo){
                                    alert("In the case of a totally positive range, the maximum value cannot be less than the minimum.");
                                    return false;
                                }

                                // Ambos negativos, diferentes de cero, diferentes entre ellos
                                if (maximo < 0 && minimo < 0 &&  maximo > minimo){
                                    alert("In the case of a totally negative range, the minimum value cannot be less than the maximum.");
                                    return false;
                                }

                                // en caso de un rango mixto, ambos diferentes de cero, maximo < 0
                                if (maximo !=0 && minimo != 0 &&  maximo < 0){
                                    alert("In the case of a mixed range, that is (between positive and negative), where both are different\n" +
                                        "from zero, the maximum value cannot be negative. ");
                                    return false;
                                }

                                // maximo igual a cero y minimo es mayor que cero
                                if (maximo == 0 && minimo > 0){
                                    alert("If the maximum is equal to zero, the minimum cannot be greater than zero.");
                                    return false;
                                }

                                // minimo igual a cero y maximo es menor que cero
                                if (maximo == 0 && minimo > 0){
                                    alert("If the minimum is equal to zero, the maximum cannot be less than zero. ");
                                    return false;
                                }

                                document.forma.p_answer_only_number.value = 0;
                            }else{
                                if (!valida_montos(document.forma.p_answer_only_number.value,"Answer Solution Number",parseFloat(1))) {
                                    return false;
                                }
                                document.forma.p_min.value = 0;
                                document.forma.p_max.value = 0;
                            }
                        }else
                            document.forma.p_answer_only_number.value = 0;
                    }
                    if (document.forma.p_request_type.value == 8){
                        if (document.forma.p_audi_solu.value === "F"){
                            if (!valida_hora(document.forma.p_answer_only_time.value, "Time answer request")) {
                                return false;
                            }
                        }else
                            document.forma.p_answer_only_time.value = "";
                    }
                    if (document.forma.p_request_type.value == 9){
                        if (document.forma.p_audi_solu.value === "F"){
                            if (!valida_largos(document.forma.p_answer_only_date.value.length, "Date answer request", 10)) {
                                return false;
                            }
                        }else
                            document.forma.p_answer_only_date.value = "";
                    }
                    return true;
                }
                function validaItems_t1(source) {
                    var cta=0;
                    var element=document.getElementsByTagName('input'); //obtenemos todos los controles del tipo Input
                    for(i=0;i<element.length;i++) {//recoremos todos los controles
                        if(element[i].id == "cual_1") {
                            if (element[i].value == ("T")) {
                                cta = cta + 1; //llevamos control de cuantos de ellos estan chequeados
                            }
                        }
                    }
                    return cta;
                }
                function update_registro(){
                    if (varias_validaciones()) {
                        document.forma.target = "";
                        document.forma.action = '/objectboard/answerrequestprocess';
                        document.forma.p_acciones.value = "save";
                        document.forma.submit();
                    }
                }
            </jstl:when>
        </jstl:choose>
    </script>
    <script language="JavaScript" type="text/javascript">
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
                if ( confirm("Do You really want to delete the selected Answers?")) {
                    document.forma.action = '/objectboard/answerrequestprocess';
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
                alert("There are no Answer selected to remove.");
                return false;
            }
            return true;
        }
        function valida(valor0){
            document.forma.p_answer_code.value = valor0;
            document.forma.target = "";
            document.forma.p_acciones.value = "save";
            document.forma.action = "/objectboard/answerssurveyrequests";
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
        <jstl:set var="jsp_pagePrevious" scope="page" value="/objectboard/bodysurveyquestions?p_code=${rq_code}&p_refes=${rq_refes}&p_names=${rq_names}"/>
        <%@include file="../../../../complements/jsp/navbar-header.jsp"%>
        <!--end ############################### navbar-header ###############################-->
        <!-- Contenedor central -->
        <div class="pcoded-main-container">
            <!--start ############################### page-header ###############################-->
            <jstl:set var="jsp_titleFrom" scope="page" value="Answers Requests Polls List Form"/>
            <jstl:set var="jsp_funtionFrom" scope="page" value="List or select your options ..."/>
            <%@include file="../../../../complements/jsp/page-header.jsp"%>
            <!--end ############################### page-header ###############################-->
            <!--<section id="content">-->
            <div class="container mt-2 mb-2">
                <form class="user" role="form" method="post" action="#!" name="forma" >
                    <jstl:choose>
                        <jstl:when test="${ rq_acciones.equals('create') }">
                            <jstl:choose>
                                <jstl:when test="${ rq_requestType.equals('1') ||
                                                    rq_requestType.equals('2') ||
                                                    rq_requestType.equals('3') }">
                                    <div class="form-group row">
                                        <div class="col-sm-2">
                                            <label for="answ1">Answer :</label>
                                        </div>
                                        <div class="col-sm-4">
                                            <input name="p_answer" id="answ1" maxlength="65" class="form-control"
                                                   width="40" placeholder="Write here the text to responce." >
                                        </div>
                                        <div class="col-sm-2">
                                            <label for="answ_solu">Answer solution :</label>
                                        </div>
                                        <div class="col-sm-4">
                                            <input type="radio" id="answ_solu" name="p_answ_solu" value="T" >
                                            <label for="answ_solu">&nbsp;&nbsp;Yes</label>&nbsp;&nbsp;&nbsp;&nbsp;
                                            <input type="radio" id="answ_solu1" name="p_answ_solu" value="F" checked>
                                            <label for="answ_solu1">&nbsp;&nbsp;&nbsp;No</label>
                                            <input type="hidden" id="audi_solu" name="p_audi_solu" value="" >
                                        </div>
                                    </div>
                                    <hr>
                                </jstl:when>
                                <jstl:when test="${ rq_requestType.equals('6') }">
                                    <div class="form-group row">
                                        <div class="col-sm-2">
                                            <label for="answot">Answer Solution:</label>
                                        </div>
                                        <div class="col-sm-3">
                                            <input name="p_answer_only_text" id="answot" maxlength="45" class="form-control"
                                                   width="40" placeholder="Write text that is the solution to the question." >
                                        </div>
                                        <div class="col-sm-1">

                                        </div>
                                        <div class="col-sm-2">
                                            <label>Auditable solution :</label>
                                        </div>
                                        <div class="col-sm-4">
                                            <input type="radio" name="p_audi_solu" value="T" >
                                            <label>&nbsp;&nbsp;Yes</label>&nbsp;&nbsp;&nbsp;&nbsp;
                                            <input type="radio" name="p_audi_solu" value="F" checked>
                                            <label>&nbsp;&nbsp;&nbsp;No</label>
                                        </div>
                                    </div>
                                    <hr>
                                </jstl:when>
                                <jstl:when test="${ rq_requestType.equals('7') }">
                                    <div class="form-group row">
                                        <div class="col-sm-2">
                                            <label for="answon">Answer Solution:</label>
                                        </div>
                                        <div class="col-sm-3">
                                            <input name="p_answer_only_number" id="answon" maxlength="10" class="form-control"
                                                   width="40" placeholder="Write number solution to the question." >
                                        </div>
                                        <div class="col-sm-1">

                                        </div>
                                        <div class="col-sm-2">
                                            <label>Auditable solution :</label>
                                        </div>
                                        <div class="col-sm-4">
                                            <input type="radio" name="p_audi_solu" value="T" >
                                            <label>&nbsp;&nbsp;Yes</label>&nbsp;&nbsp;&nbsp;&nbsp;
                                            <input type="radio" name="p_audi_solu" value="F" checked>
                                            <label>&nbsp;&nbsp;&nbsp;No</label>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <div class="col-sm-2">
                                            <label>Is it a range?</label>
                                        </div>
                                        <div class="col-sm-2">
                                            <input type="radio" name="p_status_rank" value="T">&nbsp;&nbsp;
                                            <label>Yes&nbsp;&nbsp;</label>
                                            <input type="radio" name="p_status_rank" value="F" checked>&nbsp;&nbsp;
                                            <label>No</label>
                                        </div>
                                        <div class="col-sm-2" align="right">
                                            <label>Min. value:</label>
                                        </div>
                                        <div class="col-sm-2">
                                            <input type="text" name="p_min" maxlength="7" class="form-control"
                                                   placeholder="Mimimun value">
                                        </div>
                                        <div class="col-sm-2" align="right">
                                            <label>Max. value:</label>
                                        </div>
                                        <div class="col-sm-2">
                                            <input type="text" name="p_max" maxlength="7" class="form-control"
                                                   placeholder="Maximun value">
                                        </div>
                                    </div>
                                    <hr>
                                </jstl:when>
                                <jstl:when test="${ rq_requestType.equals('8') }">
                                    <div class="form-group row">
                                        <div class="col-sm-2">
                                            <label for="answott">Answer Solution:</label>
                                        </div>
                                        <div class="col-sm-3">
                                            <input name="p_answer_only_time" id="answott" maxlength="45" class="form-control"
                                                   width="40" placeholder="Write time solution to the question HH:mm:ss." >
                                        </div>
                                        <div class="col-sm-1">

                                        </div>
                                        <div class="col-sm-2">
                                            <label>Auditable solution :</label>
                                        </div>
                                        <div class="col-sm-4">
                                            <input type="radio" name="p_audi_solu" value="T" >
                                            <label>&nbsp;&nbsp;Yes</label>&nbsp;&nbsp;&nbsp;&nbsp;
                                            <input type="radio" name="p_audi_solu" value="F" checked>
                                            <label>&nbsp;&nbsp;&nbsp;No</label>
                                        </div>
                                    </div>
                                    <hr>
                                </jstl:when>
                                <jstl:when test="${ rq_requestType.equals('9') }">
                                    <div class="form-group row">
                                        <div class="col-sm-2">
                                            <label for="datepicker">Answer Solution:</label>
                                        </div>
                                        <div class="col-sm-3">
                                            <input  name="p_answer_only_date" id="datepicker" width="276" readonly/>
                                        </div>
                                        <div class="col-sm-1">

                                        </div>
                                        <div class="col-sm-2">
                                            <label>Auditable solution :</label>
                                        </div>
                                        <div class="col-sm-4">
                                            <input type="radio" name="p_audi_solu" value="T" >
                                            <label>&nbsp;&nbsp;Yes</label>&nbsp;&nbsp;&nbsp;&nbsp;
                                            <input type="radio" name="p_audi_solu" value="F" checked>
                                            <label>&nbsp;&nbsp;&nbsp;No</label>
                                        </div>
                                    </div>
                                    <hr>
                                </jstl:when>
                            </jstl:choose>
                        </jstl:when>
                        <jstl:when test="${ rq_acciones.equals('save') }">
                            <jstl:choose>
                                <jstl:when test="${ rq_requestType.equals('1') ||
                                                    rq_requestType.equals('2') ||
                                                    rq_requestType.equals('3') }">
                                    <div class="form-group row">
                                        <div class="col-sm-2">
                                            <label for="answ2">Answer :</label>
                                        </div>
                                        <div class="col-sm-4">
                                            <input name="p_answer" id="answ2" maxlength="65" class="form-control"
                                                   width="40" placeholder="Write here the text to responce." value="${ rq_puntual.get(0).getAnswer() }">
                                        </div>
                                        <div class="col-sm-2">
                                            <label >Answer solution :</label>
                                        </div>
                                        <div class="col-sm-4">
                                            <jstl:choose>
                                                <jstl:when test="${ rq_puntual.get(0).getAnswerSolution().equals('T') }">
                                                    <input type="radio" id="answ_sol" name="p_answ_solu" value="T" checked>
                                                    <label for="answ_sol">&nbsp;&nbsp;Yes</label>&nbsp;&nbsp;&nbsp;&nbsp;
                                                    <input type="radio" id="answ_sol" name="p_answ_solu" value="F" >
                                                    <label for="answ_sol">&nbsp;&nbsp;&nbsp;No</label>
                                                </jstl:when>
                                                <jstl:otherwise>
                                                    <input type="radio" id="answ_solu2" name="p_answ_solu" value="T" >
                                                    <label for="answ_solu2">&nbsp;&nbsp;Yes</label>&nbsp;&nbsp;&nbsp;&nbsp;
                                                    <input type="radio" id="answ_solu2" name="p_answ_solu" value="F" checked>
                                                    <label for="answ_solu2">&nbsp;&nbsp;&nbsp;No</label>
                                                </jstl:otherwise>
                                            </jstl:choose>
                                            <input type="hidden" id="audi_solum" name="p_audi_solu" value="T" >
                                        </div>
                                    </div>
                                    <hr>
                                </jstl:when>
                                <jstl:when test="${ rq_requestType.equals('6') }">
                                    <div class="form-group row">
                                        <div class="col-sm-2">
                                            <label for="answot1">Answer Solution:</label>
                                        </div>
                                        <div class="col-sm-3">
                                            <input name="p_answer_only_text" id="answot1" maxlength="45" class="form-control"
                                                   width="40" placeholder="Write text that is the solution to the question."
                                                   value="${ rq_puntual.get(0).getAnswerOnlyText() }">
                                        </div>
                                        <div class="col-sm-1">

                                        </div>
                                        <div class="col-sm-2">
                                            <label>Auditable solution :</label>
                                        </div>
                                        <div class="col-sm-4">
                                            <jstl:choose>
                                                <jstl:when test="${ rq_puntual.get(0).getAuditableSolution().equals('T') }">
                                                    <input type="radio" name="p_audi_solu" value="T" checked>
                                                    <label>&nbsp;&nbsp;Yes</label>&nbsp;&nbsp;&nbsp;&nbsp;
                                                    <input type="radio" name="p_audi_solu" value="F" >
                                                    <label>&nbsp;&nbsp;&nbsp;No</label>
                                                </jstl:when>
                                                <jstl:otherwise>
                                                    <input type="radio" name="p_audi_solu" value="T" >
                                                    <label>&nbsp;&nbsp;Yes</label>&nbsp;&nbsp;&nbsp;&nbsp;
                                                    <input type="radio" name="p_audi_solu" value="F" checked>
                                                    <label>&nbsp;&nbsp;&nbsp;No</label>
                                                </jstl:otherwise>
                                            </jstl:choose>
                                        </div>
                                    </div>
                                    <hr>
                                </jstl:when>
                                <jstl:when test="${ rq_requestType.equals('7') }">
                                    <div class="form-group row">
                                        <div class="col-sm-2">
                                            <label for="answon7">Answer Solution:</label>
                                        </div>
                                        <div class="col-sm-3">
                                            <input name="p_answer_only_number" id="answon7" maxlength="10" class="form-control"
                                                   width="40" placeholder="Write number solution to the question."
                                                   value="${ rq_puntual.get(0).getAnswerOnlyNumber() }">
                                        </div>
                                        <div class="col-sm-1">

                                        </div>
                                        <div class="col-sm-2">
                                            <label>Auditable solution :</label>
                                        </div>
                                        <div class="col-sm-4">
                                            <jstl:choose>
                                                <jstl:when test="${ rq_puntual.get(0).getAuditableSolution().equals('T') }">
                                                    <input type="radio" name="p_audi_solu" value="T" checked>
                                                    <label>&nbsp;&nbsp;Yes</label>&nbsp;&nbsp;&nbsp;&nbsp;
                                                    <input type="radio" name="p_audi_solu" value="F" >
                                                    <label>&nbsp;&nbsp;&nbsp;No</label>
                                                </jstl:when>
                                                <jstl:otherwise>
                                                    <input type="radio" name="p_audi_solu" value="T" >
                                                    <label>&nbsp;&nbsp;Yes</label>&nbsp;&nbsp;&nbsp;&nbsp;
                                                    <input type="radio" name="p_audi_solu" value="F" checked>
                                                    <label>&nbsp;&nbsp;&nbsp;No</label>
                                                </jstl:otherwise>
                                            </jstl:choose>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <div class="col-sm-2">
                                            <label>Is it a range?</label>
                                        </div>
                                        <div class="col-sm-2">
                                            <jstl:choose>
                                                <jstl:when test="${ rq_puntual.get(0).getStatusRank().equals('F') }">
                                                    <input type="radio" name="p_status_rank" value="T" >
                                                    <label>&nbsp;&nbsp;Yes</label>&nbsp;&nbsp;&nbsp;&nbsp;
                                                    <input type="radio" name="p_status_rank" value="F" checked>
                                                    <label>&nbsp;&nbsp;&nbsp;No</label>
                                                </jstl:when>
                                                <jstl:otherwise>
                                                    <input type="radio" name="p_status_rank" value="T" checked>
                                                    <label>&nbsp;&nbsp;Yes</label>&nbsp;&nbsp;&nbsp;&nbsp;
                                                    <input type="radio" name="p_status_rank" value="F" >
                                                    <label>&nbsp;&nbsp;&nbsp;No</label>
                                                </jstl:otherwise>
                                            </jstl:choose>
                                        </div>
                                        <div class="col-sm-2" align="right">
                                            <label>Min. value:</label>
                                        </div>
                                        <div class="col-sm-2">
                                            <input name="p_min" maxlength="7" class="form-control"
                                                   placeholder="Mimimun value"
                                                   value="${ rq_puntual.get(0).getRankMin() }">
                                        </div>
                                        <div class="col-sm-2" align="right">
                                            <label>Max. value:</label>
                                        </div>
                                        <div class="col-sm-2">
                                            <input name="p_max" maxlength="7" class="form-control"
                                                   placeholder="Maximun value"
                                                   value="${ rq_puntual.get(0).getRankMax() }">
                                        </div>
                                    </div>
                                    <hr>
                                </jstl:when>
                                <jstl:when test="${ rq_requestType.equals('8') }">
                                    <div class="form-group row">
                                        <div class="col-sm-2">
                                            <label for="answott8">Answer Solution:</label>
                                        </div>
                                        <div class="col-sm-3">
                                            <input name="p_answer_only_time" id="answott8" maxlength="45" class="form-control"
                                                   width="40" placeholder="Write time solution to the question HH:mm:ss."
                                                   value="${ rq_puntual.get(0).getAnswerOnlyTime() }">
                                        </div>
                                        <div class="col-sm-1">

                                        </div>
                                        <div class="col-sm-2">
                                            <label>Auditable solution :</label>
                                        </div>
                                        <div class="col-sm-4">
                                            <jstl:choose>
                                                <jstl:when test="${ rq_puntual.get(0).getAuditableSolution().equals('T') }">
                                                    <input type="radio" name="p_audi_solu" value="T" checked>
                                                    <label>&nbsp;&nbsp;Yes</label>&nbsp;&nbsp;&nbsp;&nbsp;
                                                    <input type="radio" name="p_audi_solu" value="F" >
                                                    <label>&nbsp;&nbsp;&nbsp;No</label>
                                                </jstl:when>
                                                <jstl:otherwise>
                                                    <input type="radio" name="p_audi_solu" value="T" >
                                                    <label>&nbsp;&nbsp;Yes</label>&nbsp;&nbsp;&nbsp;&nbsp;
                                                    <input type="radio" name="p_audi_solu" value="F" checked>
                                                    <label>&nbsp;&nbsp;&nbsp;No</label>
                                                </jstl:otherwise>
                                            </jstl:choose>
                                        </div>
                                    </div>
                                    <hr>
                                </jstl:when>
                                <jstl:when test="${ rq_requestType.equals('9') }">
                                    <div class="form-group row">
                                        <div class="col-sm-2">
                                            <label for="datepicker9">Answer Solution:</label>
                                        </div>
                                        <div class="col-sm-3">
                                            <input  name="p_answer_only_date" id="datepicker9" width="276"
                                                    value="${ rq_puntual.get(0).getAnswerOnlyDate() }" readonly/>
                                        </div>
                                        <div class="col-sm-1">

                                        </div>
                                        <div class="col-sm-2">
                                            <label>Auditable solution :</label>
                                        </div>
                                        <div class="col-sm-4">
                                            <jstl:choose>
                                                <jstl:when test="${ rq_puntual.get(0).getAuditableSolution().equals('T') }">
                                                    <input type="radio" name="p_audi_solu" value="T" checked>
                                                    <label>&nbsp;&nbsp;Yes</label>&nbsp;&nbsp;&nbsp;&nbsp;
                                                    <input type="radio" name="p_audi_solu" value="F" >
                                                    <label>&nbsp;&nbsp;&nbsp;No</label>
                                                </jstl:when>
                                                <jstl:otherwise>
                                                    <input type="radio" name="p_audi_solu" value="T" >
                                                    <label>&nbsp;&nbsp;Yes</label>&nbsp;&nbsp;&nbsp;&nbsp;
                                                    <input type="radio" name="p_audi_solu" value="F" checked>
                                                    <label>&nbsp;&nbsp;&nbsp;No</label>
                                                </jstl:otherwise>
                                            </jstl:choose>
                                        </div>
                                    </div>
                                    <hr>
                                </jstl:when>
                            </jstl:choose>
                        </jstl:when>
                    </jstl:choose>
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
                        <tr>
                            <th>
                                Poll Name :
                            </th>
                            <th colspan="2">
                                <jstl:out value="${ rq_names }">Lost Value</jstl:out>
                            </th>
                            <th colspan="2">

                            </th>
                        </tr>
                        <tr>
                            <th>
                                Poll Reference :
                            </th>
                            <th>
                                <jstl:out value="${ rq_refes }">Lost Value</jstl:out>
                            </th>
                            <th>
                                Answer Type :
                            </th>
                            <th colspan="2">
                                <jstl:choose>
                                    <jstl:when test="${ rq_requestType == 1 }">Boolean (Yes or Not)</jstl:when>
                                    <jstl:when test="${ rq_requestType == 2 }">Unique selection</jstl:when>
                                    <jstl:when test="${ rq_requestType == 3 }">Multi selection</jstl:when>
                                    <jstl:when test="${ rq_requestType == 4 }">Only document</jstl:when>
                                    <jstl:when test="${ rq_requestType == 5 }">Only image</jstl:when>
                                    <jstl:when test="${ rq_requestType == 6 }">Input text</jstl:when>
                                    <jstl:when test="${ rq_requestType == 7 }">Input number</jstl:when>
                                    <jstl:when test="${ rq_requestType == 8 }">Input time</jstl:when>
                                    <jstl:when test="${ rq_requestType == 9 }">Input date</jstl:when>
                                </jstl:choose>
                            </th>
                        </tr>
                        <tr>
                            <th>
                                Main Request :
                            </th>
                            <th colspan="4">
                                <jstl:out value="${ rq_requestMain }">Lost Value</jstl:out>
                            </th>
                        </tr>
                        <tr>
                            <th data-field="state" data-sortable="false" >
                                <input type='checkbox' onclick='marcar(this);' />
                            </th>
                            <th data-field="l1" data-sortable="true" data-switchable="false">ANSWER</th>
                            <th data-field="l2" data-sortable="true" data-switchable="true">SOLUCCION PART</th>
                            <!--<th data-field="l3" data-sortable="true" data-switchable="true">AUDITABLE</th>-->
                            <th data-field="ac" data-sortable="false" data-valign="middle" data-align="center">ACTION</th>
                        </tr>
                        </thead>
                        <tbody>
                        <jstl:choose>
                            <jstl:when test="${ rq_numFilas != 0 }">
                                <jstl:set var="f" value="${0}" scope="page"/>
                                <jstl:forEach var="x" begin="0" end="${ rq_numFilas -1 }" step="1">
                                    <jstl:set var = "f" value = "${f + 1}" />
                                    <tr>
                                        <td>
                                            <input type='checkbox' id="p_select" name='p_selec_<jstl:out value="${ f }"/>' />
                                            <input type='hidden' value='<jstl:out value="${ rq_answer.get(x).getAnswerCode() }"/>'
                                                   name='p_cual_0<jstl:out value="${ f }"/>' id="cual_0" />
                                        </td>
                                        <td>
                                            <jstl:choose>
                                                <jstl:when test="${ rq_requestType == 1 || rq_requestType == 2 || rq_requestType == 3 }">
                                                    <jstl:out value="${ rq_answer.get(x).getAnswer() }">Lost Value</jstl:out>
                                                </jstl:when>
                                                <jstl:when test="${ rq_requestType == 6 }">
                                                    <jstl:out value="${ rq_answer.get(x).getAnswerOnlyText() }">Lost Value</jstl:out>
                                                </jstl:when>
                                                <jstl:when test="${ rq_requestType == 7 }">
                                                    <jstl:out value="${ rq_answer.get(x).getAnswerOnlyNumber() }">Lost Value</jstl:out>
                                                </jstl:when>
                                                <jstl:when test="${ rq_requestType == 8 }">
                                                    <jstl:out value="${ rq_answer.get(x).getAnswerOnlyTime() }">Lost Value</jstl:out>
                                                </jstl:when>
                                                <jstl:when test="${ rq_requestType == 9 }">
                                                    <jstl:out value="${ rq_answer.get(x).getAnswerOnlyDate() }">Lost Value</jstl:out>
                                                </jstl:when>
                                            </jstl:choose>
                                        </td>
                                        <td>
                                            <jstl:choose>
                                                <jstl:when test="${ rq_answer.get(x).getAnswerSolution().equals('T') }">
                                                        True
                                                </jstl:when>
                                                <jstl:otherwise>
                                                        false
                                                </jstl:otherwise>
                                            </jstl:choose>
                                            <input type='hidden' value='<jstl:out value="${ rq_answer.get(x).getAnswerSolution() }"/>'
                                                   name='p_cual_1<jstl:out value="${ f }"/>' id="cual_1"/>
                                        </td>
                                            <%--<td>
                                                <jstl:choose>
                                                    <jstl:when test="${ rq_answer.get(x).getAuditableSolution().equals('T') }">
                                                        True
                                                    </jstl:when>
                                                    <jstl:otherwise>
                                                        false
                                                    </jstl:otherwise>
                                                </jstl:choose>
                                                <input type='hidden' value='<jstl:out value="${ rq_answer.get(x).getAuditableSolution() }"/>'
                                                       name='p_cual_2<jstl:out value="${ f }"/>' id="cual_2" disabled/>
                                            </td>--%>
                                        <td>
                                            <a href='#' title="Edit"
                                               onClick=valida('${ rq_answer.get(x).getAnswerCode() }')>
                                               <i class='fa fa-edit fa-fw' ></i>
                                            </a>&nbsp;
                                        </td>
                                    </tr>
                                </jstl:forEach>
                            </jstl:when>
                            <jstl:otherwise>
                                <tr>
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
                                        Not Action
                                    </td>
                                </tr>
                            </jstl:otherwise>
                        </jstl:choose>
                        </tbody>
                    </table>
                    <input name='p_cuantos' type='hidden' value='<jstl:out value="${ rq_numFilas }">Lost Value</jstl:out>' />
                    <input name='p_code' type='hidden' value='${ rq_code }' />
                    <input name='p_names' type='hidden' value='${ rq_names}' />
                    <input name='p_refes' type='hidden' value='${ rq_refes}' />
                    <input name='p_pantalla' type='hidden' value='answerrequest' />
                    <input name="p_acciones" type="hidden" value='<jstl:out value="${ rq_acciones }">Lost Value</jstl:out>'/>
                    <input name='p_request_code' type='hidden' value='${ rq_requestCode }' />
                    <input name='p_answer_code' type='hidden' value='${ rq_answerCode }' />
                    <input name='p_request_type' type='hidden' value='${ rq_requestType }' />
                    <input name='p_request_main' type='hidden' value='${ rq_requestMain }' />
                    <jstl:choose>
                        <jstl:when test="${ rq_acciones.equals('save') }">
                            <input name='p_answer_old' type='hidden' value='${ rq_puntual.get(0).getAnswer() }' />
                            <input name='p_change' type='hidden' value='0' />
                            <input name='p_answ_solu_old' type='hidden' value='${ rq_puntual.get(0).getAnswerSolution() }' />
                            <%--<input name='p_audi_solu_old' type='hidden' value='${ rq_puntual.get(0).getAuditableSolution() }' />--%>
                        </jstl:when>
                    </jstl:choose>
                </form>
            </div>
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
<script type=text/javascript src="<%= request.getContextPath() %>/complements/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        var date_input=$('input[name="p_answer_only_date"]'); //our date input has the name "p_datexpires"
        var container=$('.bootstrap-iso form').length>0 ? $('.bootstrap-iso form').parent() : "body";
        date_input.datepicker({
            format: 'mm/dd/yyyy',
            container: container,
            todayHighlight: true,
            autoclose: true,
        })
        $('#example').DataTable({
            "scrollY": 500,
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



