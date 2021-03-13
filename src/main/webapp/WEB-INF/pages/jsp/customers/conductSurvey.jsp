<%@ page import="java.util.ArrayList" %>
<%@ page import="com.psg.objectboard.model.own.ownsEntity.classDAO.BodySurveyQuestionsDAO" %>
<%@ page import="com.psg.objectboard.model.own.ownsEntity.classVO.BodySurveyQuestionsVO" %>
<%@ page import="com.psg.objectboard.model.own.ownsEntity.classVO.BodySurveyAnswersVO" %>
<%@ page import="com.psg.objectboard.model.own.ownsEntity.classDAO.BodySurveyAnswersDAO" %>
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

    BodySurveyQuestionsDAO sdo = new BodySurveyQuestionsDAO();
    sdo.setDataPassword(data_pasword);
    sdo.setDataUser(data_user);
    ArrayList<BodySurveyQuestionsVO> questions = null;
    String condicion = "headersSurvey_survey_code = " + Long.parseLong(code) + " AND headersSurvey_bussinessUnit_bu_bis_code = " + Long.parseLong(company_number);
    questions = sdo.getListBodySurveyQuestions(condicion);

    Integer ctaPregunta = 1,
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
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Conduct Survey Files</title>
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
       function nuevo_registro(){
           if (varias_validaciones()) {
               document.forma.target = "";
               document.forma.action = '/objectboard/conductsurveyprocess';
               document.forma.p_acciones.value = "create";
               document.forma.submit();
           }
       }
       function cancelar(){
                    document.forma.target = "";
                    document.forma.action = '/objectboard/conduct';
                    document.forma.p_acciones.value = "consult";
                    document.forma.submit();
                }
       function varias_validaciones() {
         if (
            !valida_los_radios() ||
            !valida_los_checkboxes() ||
            !valida_los_textos() ||
            !valida_los_numeros() ||
            !valida_las_horas() ||
            !valida_las_fechas() ||
            !valida_los_comment() ||
            !valida_documentos() ||
            !valida_imagenes()){
               return false;
          }
            return true;
       }
       function valida_los_radios(){
           <% ctaLinea = 0; %>
           <% for(Integer x=0; x < questions.size();x++){ %>
               <% ctaLinea = ctaLinea + 1; %>
               <% if (questions.get(x).getTypeRequest() == 1 || questions.get(x).getTypeRequest() == 2 ){ %>
                   if (!valida_radios("p_<%= questions.get(x).getQuestionCode()%>","question No.<%= ctaLinea %>")){
                       return false;
                   }
               <%}%>
           <%}%>
           return true;
       }
       function valida_los_checkboxes(){
           <% ctaLinea = 0; %>
           <% for (Integer x=0; x < questions.size();x++){ %>
               <% ctaLinea = ctaLinea + 1; %>
               <% if (questions.get(x).getTypeRequest() == 3 ){ %>
                   if (!valida_checkboxes("p_<%= questions.get(x).getQuestionCode()%>","question No.<%= ctaLinea %>")){
                       return false;
                   }else{
                       document.forma.p_mu_<%= questions.get(x).getQuestionCode() %>.value = llena_arreglo(<%= questions.get(x).getQuestionCode()%>);
                       //alert("Pasado: " + document.forma.p_mu_<%= questions.get(x).getQuestionCode() %>.value);
                   }
               <%}%>
           <%}%>
           return true;
       }
       function llena_arreglo(nombre){
           var cta = 0;
           var arr = "";
           var basura = " ";
           var checkboxes=document.getElementsByTagName('input');
           for(i=0;i<checkboxes.length;i++) {
               if(checkboxes[i].name === "p_" + nombre) {
                   if (checkboxes[i].checked) {
                       if (cta === 0)
                           arr = checkboxes[i].value;
                       else
                           arr = arr.trim() + "," + checkboxes[i].value;
                       cta = cta + 1;
                       //alert("Part: " + arr);
                       basura = arr;
                   }
               }
           }
           //alert("Entera: " + arr);
           basura = arr;
           return arr;
       }
       function valida_los_textos(){
           <% ctaLinea = 0; %>
           <% for(Integer x=0; x < questions.size();x++){ %>
               <% ctaLinea = ctaLinea + 1; %>
               <% if (questions.get(x).getTypeRequest() == 6){ %>
                   if (!valida_textos(document.forma.p_<%= questions.get(x).getQuestionCode()%>.value,"text at the question No.<%= ctaLinea %>","")||
                       !valida_largos(document.forma.p_<%= questions.get(x).getQuestionCode()%>.value.length,"text at the question No.<%= ctaLinea %>",3)){
                       return false;
                   }
               <%}%>
           <%}%>
           return true;
       }
       function valida_los_numeros(){
           <% ctaLinea = 0; %>
           <% for(Integer x=0; x < questions.size();x++){ %>
           <% ctaLinea = ctaLinea + 1; %>
           <% if (questions.get(x).getTypeRequest() == 7){ %>
           if (!valida_montos(document.forma.p_<%= questions.get(x).getQuestionCode()%>.value,"number at the question No.<%= ctaLinea %>",0)){
               return false;
           }
           <%}%>
           <%}%>
           return true;
       }
       function valida_las_horas(){
           <% ctaLinea = 0; %>
           <% for(Integer x=0; x < questions.size();x++){ %>
           <% ctaLinea = ctaLinea + 1; %>
           <% if (questions.get(x).getTypeRequest() == 8){ %>
           if (!valida_hora(document.forma.p_<%= questions.get(x).getQuestionCode()%>.value,"time at the question No.<%= ctaLinea %>")){
               return false;
           }
           <%}%>
           <%}%>
           return true;
       }
       function valida_las_fechas(){
           <% ctaLinea = 0; %>
           <% for(Integer x=0; x < questions.size();x++){ %>
           <% ctaLinea = ctaLinea + 1; %>
           <% if (questions.get(x).getTypeRequest() == 9){ %>
           if (!valida_largos(document.forma.p_<%= questions.get(x).getQuestionCode()%>.value.length,"date at the question No.<%= ctaLinea %>",10)){
               return false;
           }
           <%}%>
           <%}%>
           return true;
       }
       function valida_los_comment(){
           <% ctaLinea = 0; %>
           <% for(Integer x=0; x < questions.size();x++){ %>
           <% ctaLinea = ctaLinea + 1; %>
           <% if (questions.get(x).getComment().equals("T")){ %>
           if (!valida_textos(document.forma.p_co_<%= questions.get(x).getQuestionCode()%>.value,"coment at the question No.<%= ctaLinea %>",".,")){
               return false;
           }
           <%}%>
           <%}%>
           return true;
       }
       function valida_imagenes(){
                   <% ctaLinea = 0; %>
                   <% for(Integer x=0; x < questions.size();x++){ %>
                        <% ctaLinea = ctaLinea + 1; %>
                        <% if (questions.get(x).getAnnexType().equals("2")){ %>
                            if (!valida_largos(document.forma.p_i_<%=questions.get(x).getQuestionCode()%>.value.length,"Image of question No.<%= ctaLinea %>",5)){
                                return false;
                            }
                        <%}%>
                    <%}%>
                    return true;
                }
       function valida_documentos(){
                    <% ctaLinea = 0; %>
                    <% for(Integer x=0; x < questions.size();x++){ %>
                    <% ctaLinea = ctaLinea + 1; %>
                    <% if (questions.get(x).getAnnexType().equals("1")){ %>
                    if (!valida_largos(document.forma.p_d_<%=questions.get(x).getQuestionCode()%>.value.length,"Document of question No.<%= ctaLinea %>",5)){
                        return false;
                    }
                    <%}%>
                    <%}%>
                    return true;
                }
       function validar_files(id,id1) {
           //alert("Paso");
           //alert(id);
           // Obtener nombre de archivo
           let archivo = document.getElementById(id).value,
           // Obtener extensión del archivo
           extension = archivo.substring(archivo.lastIndexOf('.'),archivo.length);
           // Si la extensión obtenida no está incluida en la lista de valores
           // del atributo "accept", mostrar un error.
           if(document.getElementById(id).getAttribute('accept').split(',').indexOf(extension) < 0) {
                  alert('Invalid file. Extension is not allowed ' + extension);
           }
           //alert(document.getElementById(id).value);
           //alert(extension);
           var nombre_archivo = archivo.split('\\');
           //alert(nombre_archivo[nombre_archivo.length-1]);
           document.getElementById(id1).value = nombre_archivo[nombre_archivo.length-1];
           //alert("Nombre: "+ document.getElementById(id1).value);
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
        <jstl:set var="jsp_pagePrevious" scope="page" value="/objectboard/conduct"/>
        <%@include file="../../../../complements/jsp/navbar-header.jsp"%>
        <!--end ############################### navbar-header ###############################-->
        <!-- Contenedor central -->
        <div class="pcoded-main-container">
            <!--start ############################### page-header ###############################-->
            <jstl:set var="jsp_titleFrom" scope="page" value="Conduct Survey List Form"/>
            <jstl:set var="jsp_funtionFrom" scope="page" value="List or select your options ..."/>
            <%@include file="../../../../complements/jsp/page-header.jsp"%>
            <!--end ############################### page-header ###############################-->
            <!--<section id="content">-->
            <div class="container mt-2 mb-2">
                <form class="user" role="form" method="post" action="#!" name="forma" enctype="multipart/form-data">
                    <jstl:if test="${ rq_imagen }">
                        <div class="form-group row">
                            <div class="col-sm-12" align="left">
                                <img name="oldPhoto_t"
                                     src='/objectboard/showfile.html?p_unit=<%= company_number %>&p_survey=<%= code %>&p_archivo=3'
                                     class='img-thumbnail' alt='Question image' width="100" height="120">
                            </div>
                        </div>
                    </jstl:if>
                    <div class="form-group row">
                        <div class="col-sm-12" align="center">
                            <label class="propia_r">
                                <jstl:out value="${ rq_name }">Lost Value</jstl:out>
                            </label>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-2">
                            <label for="nn" class="col-form-label">Performing user :</label>
                        </div>
                        <div class="col-sm-2" id="nn" >
                            <jstl:out value="${ rq_userName }">Lost Value</jstl:out>
                            <input name="p_user_email" value="${ rq_userEmail }" type="hidden">
                        </div>
                        <div class="col-sm-3">

                        </div>
                        <div class="col-sm-1">
                            <label  class="col-form-label">Fecha :</label>
                        </div>
                        <div class="col-sm-2">
                            <jstl:out value="${ rq_fechaHoy }">Lost Value</jstl:out>
                            <input name="p_fecha_hoy" value="<jstl:out value="${ rq_fechaArch }">Lost Value</jstl:out>" type="hidden">
                            <input name="p_hora_hoy" value="<jstl:out value="${ rq_hora }">Lost Value</jstl:out>" type="hidden">
                        </div>
                        <div class="col-sm-2">

                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-2">
                            <label for="nn1" class="col-form-label">Assignmet user :</label>
                        </div>
                        <div class="col-sm-2" id="nn1" >
                            <jstl:out value="${ rq_userAssi.get(0).getUserName()  }">Lost Value</jstl:out>
                            <input name="p_email_assign" value="${ rq_emailAssign }" type="hidden">
                            <input name="p_name_assign" value="${ rq_userAssi.get(0).getUserName() }" type="hidden">
                            <input name="p_ptos_raised" value="${ rq_userAssi.get(0).getPtosRaised() }" type="hidden">
                        </div>
                        <div class="col-sm-3">

                        </div>
                        <div class="col-sm-1">
                            <label  class="col-form-label">Auditor User :</label>
                        </div>
                        <div class="col-sm-2">
                            <jstl:out value="${ rq_auditorName }">Lost Value</jstl:out>
                            <input name="p_auditor" value="${ rq_auditor }" type="hidden">
                        </div>
                        <div class="col-sm-2">

                        </div>
                    </div>
                    <jstl:choose>
                        <jstl:when test="${ !rq_userAssi.get(0).getNotesDescription().equals('Without Notes') ||
                                            !rq_userAssi.get(0).getLegendsDescription().equals('Without Legends') }">
                            <div class="form-group row">
                                <div class="col-sm-2">
                                    <label for="nn2" class="col-form-label">Notes :</label>
                                </div>
                                <div class="col-sm-4" >
                                    <textarea id="nn2" name="p_note" rows="2" maxlength="45" readonly="" class="form-control"
                                              cols="35" ><jstl:out value="${ rq_userAssi.get(0).getNotesDescription() }">Lost Value</jstl:out></textarea>
                                    <input name="p_notes" value="${ rq_userAssi.get(0).getNotes() }" type="hidden">
                                </div>
                                <div class="col-sm-1">

                                </div>
                                <div class="col-sm-1">
                                    <label for="nn3" class="col-form-label">Legend :</label>
                                </div>
                                <div class="col-sm-4">
                               <textarea id="nn3" name="p_legend" rows="2" maxlength="45" readonly="" class="form-control"
                                         cols="35" ><jstl:out value="${ rq_userAssi.get(0).getLegendsDescription() }">Lost Value</jstl:out></textarea>
                                    <input name="p_legends" value="${ rq_userAssi.get(0).getLegends() }" type="hidden">
                                </div>
                            </div>
                        </jstl:when>
                        <jstl:otherwise>
                            <input name="p_notes" value="${ rq_userAssi.get(0).getNotes() }" type="hidden">
                            <input name="p_legends" value="${ rq_userAssi.get(0).getLegends() }" type="hidden">
                        </jstl:otherwise>
                    </jstl:choose>
                    <jstl:choose>
                        <jstl:when test="${ !rq_userAssi.get(0).getExceptionsDescription().equals('Without Exceptions') ||
                                            !rq_userAssi.get(0).getTermsDescription().equals('Without Terms') }">
                            <div class="form-group row">
                                <div class="col-sm-2">
                                    <label for="nn4" class="col-form-label">Exceptions :</label>
                                </div>
                                <div class="col-sm-4" >
                                    <textarea id="nn4" name="p_exception" rows="2" maxlength="45" readonly="" class="form-control"
                                      cols="35" ><jstl:out value="${ rq_userAssi.get(0).getExceptionsDescription() }">Lost Value</jstl:out></textarea>
                                    <input name="p_exceptions" value="${ rq_userAssi.get(0).getExceptions() }" type="hidden">
                                </div>
                                <div class="col-sm-1">

                                </div>
                                <div class="col-sm-1">
                                    <label for="nn5" class="col-form-label">Terms :</label>
                                </div>
                                <div class="col-sm-4">
                                    <textarea id="nn5" name="p_term" rows="2" maxlength="45" readonly="" class="form-control"
                                         cols="35" ><jstl:out value="${ rq_userAssi.get(0).getTermsDescription() }">Lost Value</jstl:out></textarea>
                                    <input name="p_terms" value="${ rq_userAssi.get(0).getTerms() }" type="hidden">
                                </div>
                            </div>
                        </jstl:when>
                        <jstl:otherwise>
                            <input name="p_exceptions" value="${ rq_userAssi.get(0).getExceptions() }" type="hidden">
                            <input name="p_terms" value="${ rq_userAssi.get(0).getTerms() }" type="hidden">
                        </jstl:otherwise>
                    </jstl:choose>
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
                                            <input type="hidden" name="p_ptos_question_<%= ctaPregunta %>" value="<%= questions.get(x).getQuestionPoints() %>">
                                            <%  ctaPregunta = ctaPregunta + 1; %>
                                            <% if (!questions.get(x).getQuestionImageName().equals("no_images.jpeg")) { %>
                                                <!-- Boton Modal -->
                                                <button type="button" title="Show Supporting Image" class="btn btn-circle btn-sm" data-toggle="modal" data-target="#ModalCenter<%= ctaPregunta %>">
                                                    <img src="<%= request.getContextPath() %>/complements/img/info.gif" alt="x" width="20" height="20"/>
                                                </button>
                                                <!-- Modal -->
                                                <div class="modal fade" id="ModalCenter<%= ctaPregunta %>" tabindex="-1" role="dialog"
                                                     aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                                                    <div class="modal-dialog modal-dialog-centered" role="document">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <h5 class="modal-title text-info" id="ModalLongTitle">Important supporting Image</h5>
                                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                    <span aria-hidden="true">&times;</span>
                                                                </button>
                                                            </div>
                                                            <div class="modal-body">
                                                                <img name="oldPhoto_<%= (ctaPregunta - 1) %>"
                                                                     src='/objectboard/showfile.html?p_unit=<%= questions.get(x).getHeadersSurveyBussinessUnitBuBisCode() %>&p_survey=<%=
                                                                questions.get(x).getHeadersSurveySurveyCode() %>&p_question=<%= questions.get(x).getQuestionCode() %>&p_archivo=4'
                                                                     class='img-thumbnail' alt='Question image' width="499" height="500">
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            <%}%>
                                        </div>
                                        <div class="card-body">
                                            <%-- <% if (!questions.get(x).getQuestionImageName().equals("no_images.jpeg")) {%>
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
                                            %>
                                            <% if(answers.size() != 0){%>
                                                <input type="hidden" name="p_status_rank_<%= ctaPregunta %>" value="<%= answers.get(0).getStatusRank() %>">
                                                <input type="hidden" name="p_min_rank_<%= ctaPregunta %>" value="<%= answers.get(0).getRankMin() %>">
                                                <input type="hidden" name="p_max_rank_<%= ctaPregunta %>" value="<%= answers.get(0).getRankMax() %>">
                                            <%}%>
                                            <% if(answers.size() != 0 &&
                                                  questions.get(x).getTypeRequest() != 4 &&
                                                  questions.get(x).getTypeRequest() != 5){ %>
                                                <% for(Integer y=0; y < answers.size();y++){ %>
                                                    <% if (questions.get(x).getTypeRequest() == 1){ %>
                                                        <div class="form-group row">
                                                            <div class="col-sm-6">
                                                                <p>
                                                                    &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                                                                    <input type="radio" id="<%= questions.get(x).getQuestionCode()%>_<%= answers.get(y).getAnswerCode() %>"
                                                                           name="p_<%= questions.get(x).getQuestionCode()%>"
                                                                           value="<%= answers.get(y).getAnswerCode() %>" >
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
                                                    <% if (questions.get(x).getTypeRequest() == 2){ %>
                                                        <div class="form-group row">
                                                            <div class="col-sm-6">
                                                                <p>
                                                                    &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                                                                    <input type="radio" id="<%= questions.get(x).getQuestionCode()%>_<%= answers.get(y).getAnswerCode() %>"
                                                                           name="p_<%= questions.get(x).getQuestionCode()%>"
                                                                           value="<%= answers.get(y).getAnswerCode() %>" >
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
                                                                           value="<%= answers.get(y).getAnswerCode() %>">
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
                                                    <% if (questions.get(x).getTypeRequest() == 6){ %>
                                                        <div class="form-group row">
                                                            <div class="col-sm-6">
                                                                &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                                                                <p>Custom Text:</p>
                                                                <input type="text" id="<%= questions.get(x).getQuestionCode()%>_<%= answers.get(y).getAnswerCode() %>"
                                                                       name="p_<%= questions.get(x).getQuestionCode() %>" onfocus="selecciona_contenido(this)"
                                                                       maxlength="45" placeholder="Put text" class="form-control">
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
                                                                       maxlength="10" placeholder="Put number" class="form-control">
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
                                                                       maxlength="10" placeholder="Put time HH:mm:ss" class="form-control">
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
                                                                       maxlength="10" class="form-control"  width="276" readonly/>
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
                                            <%}%>
                                        </div>
                                        <% if (questions.get(x).getComment().equals("T")){ %>
                                            <div class="card-footer">
                                                <div class="form-group row">
                                                    <div class="col-sm-2">
                                                        <label for="co" class="col-form-label">Comment :</label>
                                                    </div>
                                                    <div class="col-sm-10" >
                                                        <textarea id="co" name="p_co_<%= questions.get(x).getQuestionCode()%>" rows="1" maxlength="145"
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
                    <button type= "button" id="add1" title="Finish Survey" class= "btn btn-outline-success" onClick=nuevo_registro() >
                        <i class='fa fa-file-o fa-fw'></i> Finish</button>
                    &nbsp;
                    <button type= "button" id="cancel1" title="Reset Survey" class= "btn btn-outline-danger" onClick=cancelar() >
                        <i class='fa fa-undo fa-fw'></i> Reset
                    </button>
                    <% for(Integer x=0; x < questions.size();x++){ %>
                        <% if (questions.get(x).getTypeRequest() == 3){ %>
                            <input name='p_mu_<%= questions.get(x).getQuestionCode() %>' type='hidden' value='' />
                            <input type="hidden" name="p_as_<%= questions.get(x).getQuestionCode()%>" value="<%= respuestas[x] %>">
                        <%}%>
                    <%}%>
                    <input name='p_code' type='hidden' value='<%= code %>' />
                    <input name='p_pantalla' type='hidden' value='conductsurvey' />
                    <input name='p_audited' type='hidden' value='${ rq_audited }' />
                    <input name="p_acciones" type="hidden" value='<jstl:out value="${ rq_acciones }">Lost Value</jstl:out>'/>
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