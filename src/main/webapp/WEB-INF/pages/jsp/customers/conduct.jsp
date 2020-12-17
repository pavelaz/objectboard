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
    <title>Conduct Files</title>
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
    <script language="JavaScript" type="text/javascript">
        <jstl:choose>
            <jstl:when test="${ rq_acciones.equals('create') }">
                function nuevo_registro(){
                    if (varias_validaciones()) {
                        document.forma.target = "";
                        document.forma.action = '/objectboard/conductsurvey';
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
                    if (!valida_selects(document.forma.p_auditor.value,"Auditor","")
                    ){
                        return false;
                    }
                    return true;
                }
            </jstl:when>
        </jstl:choose>
        function valida(code,email,audi,name){
            document.forma.p_code.value = code;
            document.forma.p_email.value = email;
            document.forma.p_audited.value = audi;
            document.forma.p_name.value = replaceAllChart(name,"-"," ");
            document.forma.target = "";
            document.forma.p_acciones.value = "create";
            if (audi == "T")
                document.forma.action = "/objectboard/conduct";
            else
                document.forma.action = "/objectboard/conductsurvey";
            document.forma.submit();
        }
        function valida_columnas(){
            document.forma.action = "/objectboard/conduct";
            document.forma.submit();
        }
        function myNewFunction(sel) {
            //alert(sel.options[sel.selectedIndex].text);
            document.forma.p_auditor_name.value = sel.options[sel.selectedIndex].text;
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
            <jstl:set var="jsp_titleFrom" scope="page" value="Conduct Survey List Form"/>
            <jstl:set var="jsp_funtionFrom" scope="page" value="List or select your options ..."/>
            <%@include file="../../../../complements/jsp/page-header.jsp"%>
            <!--end ############################### page-header ###############################-->
            <!--<section id="content">-->
            <div class="container mt-2 mb-2">
                <form class="user" role="form" method="post" action="#!" name="forma" >
                    <jstl:choose>
                        <jstl:when test="${ rq_acciones.equals('create') }">
                            <div class="form-group row">
                                <div class="col-sm-2">
                                        <label for="nn" class="col-form-label">Survey description :</label>
                                </div>
                                <div class="col-sm-2">
                                    <p id="nn">
                                        <jstl:out value=" ${ rq_surveyName }">Lost Value</jstl:out>
                                    </p>
                                </div>
                                <div class="col-sm-2">
                                    <label for="auditor" class="col-form-label">Auditor :</label>
                                </div>
                                <div class="col-sm-4">
                                    <select name="p_auditor" id="auditor" class="form-control" onChange="myNewFunction(this);">
                                        <option value="0" selected>Select Auditor</option>
                                        <jstl:forEach var="x" begin="0" end="${ rq_musers.size() -1 }" step="1">
                                            <option value="${ rq_musers.get(x).getMuEmail() }" ><jstl:out value="${ rq_musers.get(x).getMuName() }">Lost Value</jstl:out></option>
                                        </jstl:forEach>
                                    </select>
                                </div>
                                <div class="col-sm-2">

                                </div>
                            </div>
                            <hr>
                        </jstl:when>
                    </jstl:choose>
                    <table id="other" cellspacing="0" width="100%" border="0">
                        <thead>
                        <jstl:choose>
                            <jstl:when test="${ rq_acciones.equals('create') }">
                                <tr>
                                    <button type= "button" id="add1" title="Run Survey" class= "btn btn-outline-success" onClick=nuevo_registro() >
                                        <i class='fa fa-file-o fa-fw'></i> Continue</button>
                                    &nbsp;
                                    <button type= "button" id="cancel1" title="Cancel operation" class= "btn btn-outline-danger" onClick=cancelar() >
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

                    <!-- Rectangular switch
                    <label class="switch">
                        <input type="checkbox">
                        <span class="slider "></span>
                    </label> -->
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
                    <!-- -->

                    <table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%"
                           data-toggle="example"
                           data-toolbar="#toolbar"
                           data-show-toggle="true"
                    >
                        <thead>
                        <tr>
                            <jstl:choose>
                                <jstl:when test="${ rq_column.equals('0') }">
                                    <th data-field="ac" data-sortable="false" data-valign="middle" data-align="center">ACTION</th>
                                    <th data-field="l1" data-sortable="true" data-switchable="true">SURVEY</th>
                                    <th data-field="l2" data-sortable="true" data-switchable="true" data-filter-control="input">FREQUENCY</th>
                                    <th data-field="l5" data-sortable="true" data-switchable="true" data-filter-control="input">AUDITED</th>
                                </jstl:when>
                                <jstl:otherwise>
                                    <th data-field="ac" data-sortable="false" data-valign="middle" data-align="center">ACTION</th>
                                    <th data-field="l1" data-sortable="true" data-switchable="true">SURVEY</th>
                                    <th data-field="l2" data-sortable="true" data-switchable="true" data-filter-control="input">FREQUENCY</th>
                                    <th data-field="l3" data-sortable="true" data-switchable="true" data-filter-control="input">TIMES FREC.</th>
                                    <th data-field="l4" data-sortable="true" data-switchable="true" data-filter-control="input">REFERENCE</th>
                                    <th data-field="l5" data-sortable="true" data-switchable="true" data-filter-control="input">AUDITED</th>
                                </jstl:otherwise>
                            </jstl:choose>
                        </tr>
                        </thead>
                        <tbody>
                        <jstl:choose>
                            <jstl:when test="${ rq_surveysAssign.size() != 0 }">
                                <jstl:set var="f" value="${0}" scope="page"/>
                                <jstl:forEach var="x" begin="0" end="${ rq_surveysAssign.size()  -1 }" step="1">
                                    <jstl:set var = "f" value = "${f + 1}" />
                                    <tr>
                                        <jstl:choose>
                                        <jstl:when test="${ rq_column.equals('0') }">
                                            <td>
                                                <a href='#' title="Run"
                                                   onClick=valida(${ rq_surveysAssign.get(x).getHeadersSurveySurveyCode() },'${ rq_surveysAssign.get(x).getMasterUserMuEmail() }','${ rq_surveysAssign.get(x).getAudited() }','${ rq_surveysAssign.get(x).getSurveyDescription().replace(" ","-") }')>
                                                    &nbsp;<i class='fa fa-gears fa-fw' ></i>
                                                </a>
                                            </td>
                                            <td>
                                                <jstl:out value="${ rq_surveysAssign.get(x).getSurveyDescription() }">Lost Value</jstl:out>
                                            </td>
                                            <td>
                                                <jstl:choose>
                                                    <jstl:when test="${ rq_surveysAssign.get(x).getFrecuency() == 1 }">daily</jstl:when>
                                                    <jstl:when test="${ rq_surveysAssign.get(x).getFrecuency() == 2 }">weekly</jstl:when>
                                                    <jstl:when test="${ rq_surveysAssign.get(x).getFrecuency() == 3 }">biweekly</jstl:when>
                                                    <jstl:when test="${ rq_surveysAssign.get(x).getFrecuency() == 4 }">monthly</jstl:when>
                                                    <jstl:when test="${ rq_surveysAssign.get(x).getFrecuency() == 5 }">yearly</jstl:when>
                                                </jstl:choose>
                                            </td>
                                            <td>
                                                <jstl:choose>
                                                    <jstl:when test="${ rq_surveysAssign.get(x).getAudited().equals('T') }">Yes</jstl:when>
                                                    <jstl:otherwise>No</jstl:otherwise>
                                                </jstl:choose>
                                            </td>
                                        </jstl:when>
                                        <jstl:otherwise>
                                            <td>
                                                <a href='#' title="Run"
                                                   onClick=valida(${ rq_surveysAssign.get(x).getHeadersSurveySurveyCode() },'${ rq_surveysAssign.get(x).getMasterUserMuEmail() }','${ rq_surveysAssign.get(x).getAudited() }','${ rq_surveysAssign.get(x).getSurveyDescription().replace(" ","-") }')>
                                                    &nbsp;<i class='fa fa-gears fa-fw' ></i>
                                                </a>
                                            </td>
                                            <td>
                                                <jstl:out value="${ rq_surveysAssign.get(x).getSurveyDescription() }">Lost Value</jstl:out>
                                            </td>
                                            <td>
                                                <jstl:choose>
                                                    <jstl:when test="${ rq_surveysAssign.get(x).getFrecuency() == 1 }">daily</jstl:when>
                                                    <jstl:when test="${ rq_surveysAssign.get(x).getFrecuency() == 2 }">weekly</jstl:when>
                                                    <jstl:when test="${ rq_surveysAssign.get(x).getFrecuency() == 3 }">biweekly</jstl:when>
                                                    <jstl:when test="${ rq_surveysAssign.get(x).getFrecuency() == 4 }">monthly</jstl:when>
                                                    <jstl:when test="${ rq_surveysAssign.get(x).getFrecuency() == 5 }">yearly</jstl:when>
                                                </jstl:choose>
                                            </td>
                                            <td>
                                                <jstl:out value="${ rq_surveysAssign.get(x).getTimeByFrecuency() }">Lost Value</jstl:out> times
                                            </td>
                                            <td>
                                                <jstl:out value="${ rq_surveysAssign.get(x).getReference() }">Lost Value</jstl:out>
                                            </td>
                                            <td>
                                                <jstl:choose>
                                                    <jstl:when test="${ rq_surveysAssign.get(x).getAudited().equals('T') }">Yes</jstl:when>
                                                    <jstl:otherwise>No</jstl:otherwise>
                                                </jstl:choose>
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
                                        </jstl:when>
                                        <jstl:otherwise>
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
                                        </jstl:otherwise>
                                    </jstl:choose>
                                </tr>
                            </jstl:otherwise>
                        </jstl:choose>
                        </tbody>
                    </table>
                    <input name='p_code' type='hidden' value='${ rq_code }' />
                    <input name='p_email' type='hidden' value='${ rq_email }' />
                    <input name='p_audited' type='hidden' value='${ rq_audited }' />
                    <input name='p_name' type='hidden' value='${ rq_surveyName }' />
                    <input name='p_auditor_name' type='hidden' value='${ rq_auditorName }' />
                    <input name='p_pantalla' type='hidden' value='conduct' />
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