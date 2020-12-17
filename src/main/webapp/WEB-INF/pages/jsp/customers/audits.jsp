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
    <title>Audits Files</title>
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
        function auditar_registro(conduct,survey,name,assign){
            document.forma.target = "";
            document.forma.action = '/objectboard/auditsrevision';
            document.forma.p_survey.value = survey;
            document.forma.p_conduct_id.value = conduct;
            document.forma.p_name.value = replaceAllChart(name,"-"," ");
            document.forma.p_assign.value = assign;
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
            <jstl:set var="jsp_titleFrom" scope="page" value="Audits Conduct List Form"/>
            <jstl:set var="jsp_funtionFrom" scope="page" value="List or select your options ..."/>
            <%@include file="../../../../complements/jsp/page-header.jsp"%>
            <!--end ############################### page-header ###############################-->
            <!--<section id="content">-->
            <div class="container mt-2 mb-2">
                <form class="user" role="form" method="post" action="#!" name="forma" >
                    <table id="other" cellspacing="0" width="100%" border="0">
                        <thead>
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
                            <th data-field="ac" data-sortable="false" data-valign="middle" data-align="center">ACTION</th>
                            <th data-field="l1" data-sortable="true" data-switchable="false">USER ASSIGN</th>
                            <th data-field="l2" data-sortable="true" data-switchable="true">SURVEY NAME</th>
                            <th data-field="l3" data-sortable="true" data-switchable="true">CONDUCT DATE</th>
                            <th data-field="l5" data-sortable="true" data-switchable="true">CONDUCT NAME</th>
                            <th data-field="l6" data-sortable="true" data-switchable="true">No.REQUEST</th>
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
                                                <a href='#' title="Audict"
                                                   onClick=auditar_registro(${ rq_polls.get(x).getConduct_id() },${ rq_polls.get(x).getHeadersSurvey_survey_code()},'${ rq_polls.get(x).getSurveyName().replace(" ","-")}','${rq_polls.get(x).getMasterUser_mu_email()}')>
                                                    &nbsp;<i class='fa fa-cogs fa-fw' ></i>
                                        </a>
                                        </td>
                                        <td>
                                            <jstl:out value="${ rq_polls.get(x).getUserAssignName() }">Lost Value</jstl:out>
                                        </td>
                                        <td>
                                            <jstl:out value="${ rq_polls.get(x).getSurveyName() }">Lost Value</jstl:out>
                                        </td>
                                        <td>
                                            <jstl:out value="${ rq_polls.get(x).getConduct_date() }">Lost Value</jstl:out>
                                        </td>
                                        <td>
                                            <jstl:out value="${ rq_polls.get(x).getUserConductName() }">Lost Value</jstl:out>
                                        </td>
                                        <td>
                                            <jstl:out value="${ rq_polls.get(x).getSurveyNumberQuestions() }">Lost Value</jstl:out>
                                        </td>
                                    </tr>
                                </jstl:forEach>
                            </jstl:when>
                            <jstl:otherwise>
                                <tr>
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
                                </tr>
                            </jstl:otherwise>
                        </jstl:choose>
                        </tbody>
                    </table>
                    <input name='p_survey' type='hidden' value='' />
                    <input name='p_conduct_id' type='hidden' value='' />
                    <input name='p_name' type='hidden' value='' />
                    <input name='p_assign' type='hidden' value='' />
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



