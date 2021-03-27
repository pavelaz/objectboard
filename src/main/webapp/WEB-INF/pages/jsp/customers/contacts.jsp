<%@ page import="com.psg.objectboard.model.service.Other.OtherInserts" %><%--
  Created by IntelliJ IDEA.
  User: pavelaz
  Date: 6/4/20
  Time: 2:30 p. m.
  To change this template use File | Settings | File Templates.
  <%@ page contentType="text/html;charset=UTF-8" language="java" %>
--%>
<%
    HttpSession objSesion = request.getSession();
    String user_project = (String)objSesion.getAttribute("companyProject");

    String idselect = "p_select";
    String idcual0 = "cual_0";
    OtherInserts oi = new OtherInserts();
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Contacts Files</title>
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
        <jstl:choose>
            <jstl:when test="${ rq_acciones.equals('consult') }">
                <% out.print(oi.pone_funcionesContacts("consult",user_project)); %>
            </jstl:when>
            <jstl:when test="${ rq_acciones.equals('create') }">
                <% out.print(oi.pone_funcionesContacts("create",user_project)); %>
            </jstl:when>
            <jstl:when test="${ rq_acciones.equals('save') }">
                <% out.print(oi.pone_funcionesContacts("save",user_project)); %>
            </jstl:when>
        </jstl:choose>
        // dactivar solo el si no es consult el marcado automotico para borrado, en este c
        // el segundo parametro es usado para eso
        <jstl:choose>
            <jstl:when test="${ rq_acciones.equals('consult') }">
                <% out.print(oi.pone_funcionesContacts("comun","0")); %>
            </jstl:when>
            <jstl:otherwise>
                <% out.print(oi.pone_funcionesContacts("comun","1")); %>
            </jstl:otherwise>
        </jstl:choose>
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
            <jstl:set var="jsp_titleFrom" scope="page" value="Contacts List Form"/>
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
                                    <label for="email" class="col-form-label">User Email:</label>
                                </div>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control form-control-user" id="email"
                                           placeholder="Email direction" name="p_user_email" maxlength="45"
                                           value="">
                                </div>
                                <div class="col-sm-1">
                                    <label for="name" class="col-form-label">User Name :</label>
                                </div>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control form-control-user" id="name"
                                           placeholder="User name" name="p_name" maxlength="45"
                                           value="">
                                </div>
                                <div class="col-sm-2">
                                    <label for="bname" class="col-form-label">Bussiness Name :</label>
                                </div>
                                <div class="col-sm-2">
                                    <input type="text" class="form-control form-control-user" id="bname"
                                           placeholder="bussiness name" name="p_bname" maxlength="45"
                                           value="">
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class="col-sm-1">
                                    <label for="ad1" class="col-form-label">Address :</label>
                                </div>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control form-control-user" id="ad1"
                                           placeholder="Address line 1" name="p_ad1" maxlength="45"
                                           value="">
                                </div>
                                <div class="col-sm-1">
                                    <label for="ad2" class="col-form-label">Address :</label>
                                </div>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control form-control-user" id="ad2"
                                           placeholder="Address line 2" name="p_ad2" maxlength="45"
                                           value="">
                                </div>
                                <div class="col-sm-1">
                                    <label for="cell" class="col-form-label">Cell Phone:</label>
                                </div>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control form-control-user" id="cell"
                                           placeholder="###-###-####" name="p_cell" maxlength="12"
                                           value="">
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class="col-sm-1">
                                    <label for="land" class="col-form-label">Landline:</label>
                                </div>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control form-control-user" id="land"
                                           placeholder="###-###-####" name="p_land" maxlength="12"
                                           value="">
                                </div>
                                <div class="col-sm-4">
                                    <input type="checkbox" id="sms_envios" name="p_sms" value="T">
                                    <label for="sms_envios">&nbsp;&nbsp;&nbsp;&nbsp;SMS Accept:</label><br>
                                </div>
                                <div class="col-sm-4">
                                    <input type="checkbox" id="email_envios" name="p_email" value="T">
                                    <label for="email_envios">&nbsp;&nbsp;&nbsp;&nbsp;Email Accept:</label><br>
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class="col-sm-1">
                                    <label for="type" class="col-form-label">User Type:</label>
                                </div>
                                <div class="col-sm-3">
                                    <select name="p_type" id="type" class="form-control">
                                        <option value="0" selected>Select Type</option>
                                        <option value="I" >Internal type</option>
                                        <option value="E" >External type</option>
                                    </select>
                                </div>
                                <jstl:if test="${ rq_companyProject.equals('2') }">
                                    <div class="col-sm-4">
                                        <input type="checkbox" id="from" name="p_from" value="T">
                                        <label for="from">&nbsp;&nbsp;&nbsp;&nbsp;From Yard:</label><br>
                                    </div>
                                    <div class="col-sm-4">
                                        <input type="checkbox" id="back" name="p_back" value="T">
                                        <label for="back">&nbsp;&nbsp;&nbsp;&nbsp;Back Yard:</label><br>
                                    </div>
                                </jstl:if>
                            </div>
                            <hr>
                        </jstl:when>
                        <jstl:when test="${ rq_acciones.equals('save') }">
                            <div class="form-group row">
                                <div class="col-sm-1">
                                    <label for="email1" class="col-form-label">User Email:</label>
                                </div>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control form-control-user" id="email1"
                                           placeholder="Email direction" name="p_user_email" maxlength="45"
                                           value="${ rq_contacOld.get(0).getCto_email_direction() }" disabled>
                                </div>
                                <div class="col-sm-1">
                                    <label for="name1" class="col-form-label">User Name :</label>
                                </div>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control form-control-user" id="name1"
                                           placeholder="User name" name="p_name" maxlength="45"
                                           value="${ rq_contacOld.get(0).getCto_name() }">
                                </div>
                                <div class="col-sm-2">
                                    <label for="bname1" class="col-form-label">Bussiness Name :</label>
                                </div>
                                <div class="col-sm-2">
                                    <input type="text" class="form-control form-control-user" id="bname1"
                                           placeholder="bussiness name" name="p_bname" maxlength="45"
                                           value="${ rq_contacOld.get(0).getCto_business_name() }">
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class="col-sm-1">
                                    <label for="ad11" class="col-form-label">Address :</label>
                                </div>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control form-control-user" id="ad11"
                                           placeholder="Address line 1" name="p_ad1" maxlength="45"
                                           value="${ rq_contacOld.get(0).getCto_address_1() }">
                                </div>
                                <div class="col-sm-1">
                                    <label for="ad21" class="col-form-label">Address :</label>
                                </div>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control form-control-user" id="ad21"
                                           placeholder="Address line 2" name="p_ad2" maxlength="45"
                                           value="${ rq_contacOld.get(0).getCto_address_2() }">
                                </div>
                                <div class="col-sm-1">
                                    <label for="cell1" class="col-form-label">Cell Phone:</label>
                                </div>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control form-control-user" id="cell1"
                                           placeholder="###-###-####" name="p_cell" maxlength="12"
                                           value="${ rq_contacOld.get(0).getCto_phone_cell() }">
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class="col-sm-1">
                                    <label for="land1" class="col-form-label">Landline:</label>
                                </div>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control form-control-user" id="land1"
                                           placeholder="###-###-####" name="p_land" maxlength="12"
                                           value="${ rq_contacOld.get(0).getCto_landline() }">
                                </div>
                                <div class="col-sm-4">
                                    <jstl:choose>
                                        <jstl:when test="${ rq_contacOld.get(0).getCto_sms_message().equals('T') }">
                                            <input type="checkbox" id="sms_envios1" name="p_sms" value="T" checked>
                                        </jstl:when>
                                        <jstl:otherwise>
                                            <input type="checkbox" id="sms_envios1" name="p_sms" value="F" >
                                        </jstl:otherwise>
                                    </jstl:choose>
                                    <label for="sms_envios">&nbsp;&nbsp;&nbsp;&nbsp;SMS Accept:</label><br>
                                </div>
                                <div class="col-sm-4">
                                    <jstl:choose>
                                        <jstl:when test="${ rq_contacOld.get(0).getCto_email_message().equals('T') }">
                                            <input type="checkbox" id="email_envios1" name="p_email" value="T" checked>
                                        </jstl:when>
                                        <jstl:otherwise>
                                            <input type="checkbox" id="email_envios1" name="p_email" value="F" >
                                        </jstl:otherwise>
                                    </jstl:choose>
                                    <label for="email_envios1">&nbsp;&nbsp;&nbsp;&nbsp;Email Accept:</label><br>
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class="col-sm-1">
                                    <label for="type1" class="col-form-label">User Type:</label>
                                </div>
                                <div class="col-sm-3">
                                    <jstl:choose>
                                        <jstl:when test="${ rq_contacOld.get(0).getCto_type().equals('E') }">
                                            <select name="p_type" id="type1" class="form-control">
                                                <option value="0" >Select Type</option>
                                                <option value="I" >Internal type</option>
                                                <option value="E" selected>External type</option>
                                            </select>
                                        </jstl:when>
                                        <jstl:otherwise>
                                            <select name="p_type" id="type1" class="form-control">
                                                <option value="0" >Select Type</option>
                                                <option value="I" selected>Internal type</option>
                                                <option value="E" >External type</option>
                                            </select>
                                        </jstl:otherwise>
                                    </jstl:choose>
                                </div>
                                <jstl:if test="${ rq_companyProject.equals('2') }">
                                    <div class="col-sm-4">
                                        <jstl:choose>
                                            <jstl:when test="${ rq_contacOld.get(0).getCto_front_yard().equals('T') }">
                                                <input type="checkbox" id="from1" name="p_from" value="T" checked>
                                            </jstl:when>
                                            <jstl:otherwise>
                                                <input type="checkbox" id="from1" name="p_from" value="F" >
                                            </jstl:otherwise>
                                        </jstl:choose>
                                        <label for="from1">&nbsp;&nbsp;&nbsp;&nbsp;From Yard:</label><br>
                                    </div>
                                    <div class="col-sm-4">
                                        <jstl:choose>
                                            <jstl:when test="${ rq_contacOld.get(0).getCto_back_yard().equals('T') }">
                                                <input type="checkbox" id="back1" name="p_back" value="T" checked>
                                            </jstl:when>
                                            <jstl:otherwise>
                                                <input type="checkbox" id="back1" name="p_back" value="F" >
                                            </jstl:otherwise>
                                        </jstl:choose>
                                        <label for="back">&nbsp;&nbsp;&nbsp;&nbsp;Back Yard:</label><br>
                                    </div>
                                </jstl:if>
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
                                <% out.print(oi.pone_encabezadoContactsButtons("consult")); %>
                            </jstl:when>
                            <jstl:when test="${ rq_acciones.equals('create') }">
                                <% out.print(oi.pone_encabezadoContactsButtons("create")); %>
                            </jstl:when>
                            <jstl:when test="${ rq_acciones.equals('save') }">
                                <% out.print(oi.pone_encabezadoContactsButtons("save")); %>
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
                                    <% out.print(oi.pone_encabezadoContacts("0")); %>
                                </jstl:when>
                                <jstl:otherwise>
                                    <% out.print(oi.pone_encabezadoContacts("1")); %>
                                </jstl:otherwise>
                            </jstl:choose>
                        </tr>
                        </thead>
                        <tbody>
                        <jstl:choose>
                            <jstl:when test="${ rq_contac.size() != 0 }">
                                <jstl:set var="f" value="${0}" scope="page"/>
                                <jstl:forEach var="x" begin="0" end="${ rq_contac.size() -1 }" step="1">
                                    <jstl:set var = "f" value = "${f + 1}" />
                                    <tr>
                                        <jstl:choose>
                                            <jstl:when test="${ rq_column.equals('0') }">
                                                <td>
                                                    <input type='checkbox' id="<%=idselect%>" name='p_selec_${ f }' />
                                                    <input type='hidden' value='${ rq_contac.get(x).getCto_email_direction() }'
                                                           name='p_cual_0${ f }' id="<%=idcual0%>" />
                                                </td>
                                                <td>
                                                    <a href='#' title="Edit" onClick=valida('${ rq_contac.get(x).getCto_email_direction() }') >
                                                        &nbsp;<i class='fa fa-edit fa-fw' ></i>
                                                    </a>
                                                </td>
                                                <td>
                                                    <jstl:out value="${ rq_contac.get(x).getCto_email_direction() }">Lost Value</jstl:out>
                                                </td>
                                                <td>
                                                    <jstl:out value="${ rq_contac.get(x).getCto_name() }">Lost Value</jstl:out>
                                                    <%--<input type='hidden' value='${ rq_contac.get(x).getHeadersSurveySurveyCode() }'
                                                           name='p_cual_1${ f }' id="cual_1" /> --%>
                                                </td>
                                                <td>
                                                    <jstl:out value="${ rq_contac.get(x).getCto_phone_cell() }">Lost Value</jstl:out>
                                                </td>
                                                <td>
                                                    <jstl:choose>
                                                        <jstl:when test="${ rq_contac.get(x).getCto_sms_message().equals('F') }">
                                                            False
                                                        </jstl:when>
                                                        <jstl:otherwise>
                                                            True
                                                        </jstl:otherwise>
                                                    </jstl:choose>
                                                </td>
                                                <td>
                                                    <jstl:choose>
                                                        <jstl:when test="${ rq_contac.get(x).getCto_email_message().equals('F') }">
                                                            False
                                                        </jstl:when>
                                                        <jstl:otherwise>
                                                            True
                                                        </jstl:otherwise>
                                                    </jstl:choose>
                                                </td>
                                            </jstl:when>
                                            <jstl:otherwise>
                                                <td>
                                                    <input type='checkbox' id="<%=idselect%>" name='p_selec_${ f }' />
                                                    <input type='hidden' value='${ rq_contac.get(x).getCto_email_direction() }'
                                                           name='p_cual_0${ f }' id="<%=idcual0%>" />
                                                </td>
                                                <td>
                                                    <a href='#' title="Edit" onClick=valida('${ rq_contac.get(x).getCto_email_direction() }') >
                                                        &nbsp;<i class='fa fa-edit fa-fw' ></i>
                                                    </a>
                                                </td>
                                                <td>
                                                    <jstl:out value="${ rq_contac.get(x).getCto_email_direction() }">Lost Value</jstl:out>
                                                </td>
                                                <td>
                                                    <jstl:out value="${ rq_contac.get(x).getCto_name() }">Lost Value</jstl:out>
                                                        <%--<input type='hidden' value='${ rq_contac.get(x).getHeadersSurveySurveyCode() }'
                                                               name='p_cual_1${ f }' id="cual_1" /> --%>
                                                </td>
                                                <td>
                                                    <jstl:out value="${ rq_contac.get(x).getCto_phone_cell() }">Lost Value</jstl:out>
                                                </td>
                                                <td>
                                                    <jstl:choose>
                                                        <jstl:when test="${ rq_contac.get(x).getCto_sms_message().equals('F') }">
                                                            False
                                                        </jstl:when>
                                                        <jstl:otherwise>
                                                            True
                                                        </jstl:otherwise>
                                                    </jstl:choose>
                                                </td>
                                                <td>
                                                    <jstl:choose>
                                                        <jstl:when test="${ rq_contac.get(x).getCto_email_message().equals('F') }">
                                                            False
                                                        </jstl:when>
                                                        <jstl:otherwise>
                                                            True
                                                        </jstl:otherwise>
                                                    </jstl:choose>
                                                </td>
                                                <td>
                                                    <jstl:out value="${ rq_contac.get(x).getCto_business_name() }">Lost Value</jstl:out>
                                                </td>
                                                <td>
                                                    <jstl:choose>
                                                        <jstl:when test="${rq_contac.get(x).getCto_type().equals('E')}">
                                                            External
                                                        </jstl:when>
                                                        <jstl:otherwise>
                                                            Internal
                                                        </jstl:otherwise>
                                                    </jstl:choose>
                                                </td>
                                                <td>
                                                    <jstl:out value="${ rq_contac.get(x).getCto_landline() }">Lost Value</jstl:out>
                                                </td>
                                                <td>
                                                    <jstl:out value="${ rq_contac.get(x).getCto_address_1() }">Lost Value</jstl:out>
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
                                            <% out.print(oi.pone_pieContacts("0")); %>
                                        </jstl:when>
                                        <jstl:otherwise>
                                            <% out.print(oi.pone_pieContacts("1")); %>
                                        </jstl:otherwise>
                                    </jstl:choose>
                                </tr>
                            </jstl:otherwise>
                        </jstl:choose>
                        </tbody>
                    </table>
                    <input name='p_cuantos' type='hidden' value='${ rq_contac.size() }' />
                    <input name='p_email_selec' type='hidden' value='${ rq_emailSelec }' />
                    <input name='p_pantalla' type='hidden' value='contacts' />
                    <input name="p_acciones" type="hidden" value='<jstl:out value="${ rq_acciones }">Lost Value</jstl:out>'/>
                    <jstl:choose>
                        <jstl:when test="${ rq_acciones.equals('save') }">
                            <input name='p_name_old' type='hidden' value='${ contac_old.get(0).getCto_name() }' />
                            <input name='p_bname_old' type='hidden' value='${ contac_old.get(0).getCto_business_name() }' />
                            <input name='p_ad1_old' type='hidden' value='${ contac_old.get(0).getCto_address_1() }' />
                            <input name='p_ad2_old' type='hidden' value='${ contac_old.get(0).getCto_address_2() }' />
                            <input name='p_cell_old' type='hidden' value='${ contac_old.get(0).getCto_phone_cell() }' />
                            <input name='p_land_old' type='hidden' value='${ contac_old.get(0).getCto_landline() }' />
                            <input name='p_type_old' type='hidden' value='${ contac_old.get(0).getCto_type() }' />
                            <input name='p_email_msg_old' type='hidden' value='${ contac_old.get(0).getCto_email_message() }' />
                            <input name='p_sms_msg_old' type='hidden' value='${ contac_old.get(0).getCto_sms_message() }' />
                            <input name='p_from_old' type='hidden' value='${ contac_old.get(0).getCto_front_yard() }' />
                            <input name='p_back_old' type='hidden' value='${ contac_old.get(0).getCto_back_yard() }' />
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



