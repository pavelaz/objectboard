<!DOCTYPE html>
<html lang="en">

<head>
    <title>Bussiness Units</title>
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
        function borrar_usuario(){
            if(validaItems(this)){
                if ( confirm("Do You really want to delete the selected Bussines Unit?")) {
                    document.forma.action = '/objectboard/bussinessunitsprocess';
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
                alert("There are no bussines unit selected to remove.");
                return false;
            }
            return true;
        }
        function nuevo_usuario(){
            document.forma.target = "";
            //document.forma.action = '/objectboard/bussinessunit';
            document.forma.action = '/objectboard/bussinessunit';
            document.forma.p_acciones.value = "create";
            document.forma.submit();
        }
        function valida(unit,country,state,city){
            document.forma.p_unit.value = unit;
            document.forma.p_country_number.value = country;
            document.forma.p_state_number.value = state;
            document.forma.p_city_number.value = city;
            document.forma.target = "";
            document.forma.p_acciones.value = "save";
            document.forma.action = "/objectboard/bussinessunit";
            document.forma.submit();
        }
        function other_pdf(){
            alert("paso54");
            document.forma.action = '/objectboard/generalpdf';
            document.forma.p_acciones.value = "unidades";
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
        <jstl:set var="jsp_pagePrevious" scope="page" value="/objectboard/mastermenusuper"/>
        <%@include file="../../../../complements/jsp/navbar-header.jsp"%>
        <!--end ############################### navbar-header ###############################-->
        <!-- Contenedor central -->
        <div class="pcoded-main-container">
            <!--start ############################### page-header ###############################-->
            <jstl:set var="jsp_titleFrom" scope="page" value="Bussiness Unit List Form"/>
            <jstl:set var="jsp_funtionFrom" scope="page" value="List or select your options ..."/>
            <%@include file="../../../../complements/jsp/page-header.jsp"%>
            <!--end ############################### page-header ###############################-->
            <!--<section id="content">-->
            <div class="container mt-2 mb-2">
                <form class="user" role="form" method="post" action="#!" name="forma" >
                    <input name='p_cuantos' type='hidden' value='<jstl:out value="${ rq_numFilas }">Lost Value</jstl:out>' />
                    <input name='p_unit' type='hidden' value='' />
                    <input name='p_country_number' type='hidden' value='' />
                    <input name='p_state_number' type='hidden' value='' />
                    <input name='p_city_number' type='hidden' value='' />
                    <input name="p_acciones" type="hidden" />
                    <input name='p_pantalla' type='hidden' value='bussinessunit' />
                    <table id="other" cellspacing="0" width="100%" border="0">
                        <thead>
                        <tr>
                            <button type= "button" id="add"  class= "btn btn-outline-success" onClick=nuevo_usuario() ><i class='fa fa-file-o fa-fw'></i> New</button>
                            &nbsp;
                            <button type= "button" id="remove" class= "btn btn-outline-danger" onClick=borrar_usuario() >
                                <i class='fa fa-trash-o fa-fw'></i> Trash
                            </button>
                            &nbsp;
                            <button type= "button" id="pdf" title="Export to Pdf" class= "btn btn-outline-success" onClick=other_pdf() ><i class='fa fa-list-alt fa-fw'></i> Pdf</button>
                        </tr>
                        </thead>
                        <tbody>
                        <td>

                        </td>
                        </tbody>
                    </table>

                    <table id="example" class="table table-striped table-bordered display nowrap" cellspacing="0" width="100%"
                           data-toggle="example"
                           data-toolbar="#toolbar"
                           data-show-toggle="true"
                    >
                        <thead>
                        <tr>
                            <th data-field="state" data-sortable="false">
                                <input type='checkbox' onclick='marcar(this);' />
                            </th>
                            <th data-field="action" data-sortable="false">ACTION</th>
                            <th data-field="id" data-sortable="true" data-switchable="true">B.UNIT</th>
                            <th data-field="c1" data-sortable="true" data-switchable="true">UNIT NAME</th>
                            <th data-field="c2" data-sortable="true" data-switchable="true">FEDERAL No.</th>
                            <th data-field="c3" data-sortable="true" data-switchable="true">PROVINCIAL No.</th>
                            <th data-field="c4" data-sortable="true" data-switchable="true">STATUS</th>
                            <th data-field="c5" data-sortable="true" data-switchable="true">PHONE</th>
                            <th data-field="c6" data-sortable="true" data-switchable="true">SUPER CODE</th>
                            <th data-field="c7" data-sortable="true" data-switchable="true">ZIP CODE</th>
                            <th data-field="c8" data-sortable="true" data-switchable="true">ADDRESS</th>
                            <th data-field="c9" data-sortable="true" data-switchable="true">WEB PAGE</th>
                            <th data-field="c14" data-sortable="true" data-switchable="true">ADMIN CODE</th>
                            <th data-field="c15" data-sortable="true" data-switchable="true">USER 1 CODE</th>
                            <th data-field="c16" data-sortable="true" data-switchable="true">USER 2 CODE</th>
                            <th data-field="c10" data-sortable="true" data-switchable="true">COUNTRY</th>
                            <th data-field="c11" data-sortable="true" data-switchable="true">STATE</th>
                            <th data-field="c12" data-sortable="true" data-switchable="true">CITY</th>
                            <th data-field="c13" data-sortable="true" data-switchable="true">TYPE NAME</th>
                        </tr>
                        </thead>
                        <tbody>
                        <jstl:set var="f" value="${0}" scope="page"/>
                        <jstl:forEach var="x" begin="0" end="${ rq_unidad.size() -1 }" step="1">
                            <jstl:set var = "f" value = "${f + 1}" />
                            <tr>
                                <td>
                                    <input type='checkbox' id="p_select" name='p_selec_<jstl:out value="${ f }"/>' />
                                </td>
                                <td>
                                    <a href='#' title="Edit" onClick=valida(${ rq_unidad.get(x).getBuBisCode() },${ rq_unidad.get(x).getCityStatesCountryCoCountryCode() },${ rq_unidad.get(x).getCityStatesStStateCode() },${ rq_unidad.get(x).getCityCiCityCode() })>
                                        <i class='fa fa-edit fa-fw' ></i>
                                    </a>&nbsp;
                                </td>
                                <td>
                                    <jstl:out value="${ rq_unidad.get(x).getBuBisCode() }">Lost Value</jstl:out>
                                    <input type='hidden' value='<jstl:out value="${ Math.toIntExact(rq_unidad.get(x).getBuBisCode()) }"/>'
                                           name='p_cual_u<jstl:out value="${ f }"/>' />&nbsp;
                                    <input type='hidden' value='<jstl:out value="${ rq_unidad.get(x).getBuLogoName() }"/>'
                                           name='p_cual_l<jstl:out value="${ f }"/>' />&nbsp;
                                </td>
                                <td>
                                    <jstl:out value="${ rq_unidad.get(x).getBuName() }">Lost Value</jstl:out>
                                </td>
                                <td>
                                    <jstl:out value="${ rq_unidad.get(x).getBuFederalNumber() }">Lost Value</jstl:out>
                                </td>
                                <td>
                                    <jstl:out value="${ rq_unidad.get(x).getBuProvincialNumber() }">Lost Value</jstl:out>
                                </td>
                                <td>
                                    <jstl:choose>
                                        <jstl:when test="${ rq_unidad.get(x).getBuStatus() == 'T' }">
                                            Enabled
                                        </jstl:when>
                                        <jstl:otherwise>
                                            Disabled
                                        </jstl:otherwise>
                                    </jstl:choose>
                                </td>
                                <td>
                                    <jstl:out value="${ rq_unidad.get(x).getBuPhone() }">Lost Value</jstl:out>
                                </td>
                                <td>
                                    <jstl:out value="${ rq_unidad.get(x).getBuSuperCode() }">Lost Value</jstl:out>
                                </td>
                                <td>
                                    <jstl:out value="${ rq_unidad.get(x).getBuZipcode() }">Lost Value</jstl:out>
                                </td>
                                <td>
                                    <jstl:out value="${ rq_unidad.get(x).getBuAddress() }">Lost Value</jstl:out>
                                </td>
                                <td>
                                    <jstl:out value="${ rq_unidad.get(x).getBuWebpage() }">Lost Value</jstl:out>
                                </td>
                                <td>
                                    <jstl:out value="${ rq_unidad.get(x).getBuAdminCode() }">Lost Value</jstl:out>
                                </td>
                                <td>
                                    <jstl:out value="${ rq_unidad.get(x).getBuUser1Code() }">Lost Value</jstl:out>
                                </td>
                                <td>
                                    <jstl:out value="${ rq_unidad.get(x).getBuUser2Code() }">Lost Value</jstl:out>
                                </td>
                                <td>
                                    <jstl:out value="${ rq_unidad.get(x).getCountryName() }">Lost Value</jstl:out>
                                </td>
                                <td>
                                    <jstl:out value="${ rq_unidad.get(x).getStateName() }">Lost Value</jstl:out>
                                </td>
                                <td>
                                    <jstl:out value="${ rq_unidad.get(x).getCityName() }">Lost Value</jstl:out>
                                </td>
                                <td>
                                    <jstl:out value="${ rq_unidad.get(x).getTypeName() }">Lost Value</jstl:out>
                                </td>
                            </tr>
                        </jstl:forEach>
                        </tbody>
                    </table>
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
<script src="jquery/jquery-3.3.1.min.js"></script>
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
            "scrollY": 425,
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

