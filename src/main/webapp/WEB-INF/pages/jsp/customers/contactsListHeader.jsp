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
    <title>Contacts List Files</title>
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
        <% out.print(oi.pone_funcionesContactsList("consult")); %>
        </jstl:when>
        <jstl:when test="${ rq_acciones.equals('create') }">
        <% out.print(oi.pone_funcionesContactsList("create")); %>
        </jstl:when>
        <jstl:when test="${ rq_acciones.equals('save') }">
        <% out.print(oi.pone_funcionesContactsList("save")); %>
        </jstl:when>
        </jstl:choose>
        <% out.print(oi.pone_funcionesContactsList("comun")); %>
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
                                <div class="col-sm-2">
                                    <label for="email" class="col-form-label">List Name:</label>
                                </div>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control form-control-user" id="email"
                                           placeholder="List Name" name="p_name" maxlength="45" value="">
                                </div>
                            </div>
                            <hr>
                        </jstl:when>
                        <jstl:when test="${ rq_acciones.equals('save') }">
                            <div class="form-group row">
                                <div class="col-sm-2">
                                    <label for="email1" class="col-form-label">List Name:</label>
                                </div>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control form-control-user" id="email1"
                                           placeholder="Email direction" name="p_name" maxlength="45"
                                           value="${ rq_nameOld }">
                                </div>
                            </div>
                            <hr>
                        </jstl:when>
                    </jstl:choose>
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
                             <% out.print(oi.pone_encabezadoContactsList()); %>
                        </tr>
                        </thead>
                        <tbody>
                        <jstl:choose>
                            <jstl:when test="${ rq_lista.size() != 0 }">
                                <jstl:set var="f" value="${0}" scope="page"/>
                                <jstl:forEach var="x" begin="0" end="${ rq_lista.size() -1 }" step="1">
                                    <jstl:set var = "f" value = "${f + 1}" />
                                    <tr>
                                        <td>
                                            <input type='checkbox' id="<%=idselect%>" name='p_selec_${ f }' />
                                            <input type='hidden' value='${ rq_lista.get(x).getList_name() }' name='p_cual_0${ f }' id="<%=idcual0%>" />
                                            <input type='hidden' value='${ rq_lista.get(x).getList_id() }' name='p_cual_1${ f }' />
                                        </td>
                                        <td>
                                            <a href='#' title="Edit" onClick=valida(${ rq_lista.get(x).getList_id() },'${ rq_lista.get(x).getList_name().replace(" ","-") }') >
                                                        &nbsp;<i class='fa fa-edit fa-fw' ></i>
                                            </a>
                                            <jstl:if test="${ rq_acciones.equals('consult') }">
                                                <a href='#' title="Add Contacts"
                                                   onClick=add_contact(${ rq_lista.get(x).getList_id() })>
                                                    &nbsp;<i class='fa fa-cogs fa-fw' ></i>
                                                </a>
                                            </jstl:if>
                                        </td>
                                        <td>
                                            <jstl:out value="${ rq_lista.get(x).getList_name() }">Lost Value</jstl:out>
                                        </td>
                                        <td>
                                            <jstl:out value="${ rq_lista.get(x).getList_count_directions() }">Lost Value</jstl:out>
                                        </td>
                                    </tr>
                                </jstl:forEach>
                            </jstl:when>
                            <jstl:otherwise>
                                <tr>
                                     <% out.print(oi.pone_pieContactsList()); %>
                                </tr>
                            </jstl:otherwise>
                        </jstl:choose>
                        </tbody>
                    </table>
                    <input name='p_cuantos' type='hidden' value='${ rq_lista.size() }' />
                    <input name='p_pantalla' type='hidden' value='contactsListHeader' />
                    <input name="p_acciones" type="hidden" value='<jstl:out value="${ rq_acciones }">Lost Value</jstl:out>'/>
                    <input name='p_id_selec' type='hidden' value='${ rq_idSelec }' />
                    <input name='p_name_old' type='hidden' value='${ rq_nameOld }' />
                    <%--<jstl:choose>
                        <jstl:when test="${ rq_acciones.equals('save') }">
                            <input name='p_id_selec' type='hidden' value='${ rq_idSelec }' />
                            <input name='p_name_old' type='hidden' value='${ rq_nameOld }' />
                        </jstl:when>
                    </jstl:choose>--%>
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



