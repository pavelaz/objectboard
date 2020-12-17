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
    <title>cities Files</title>
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
    </script>
    <script language="JavaScript" type="text/javascript">
        function borrar_usuario(){
            if(validaItems(this)){
                if ( confirm("Do You really want to delete the selected City?")) {
                    //document.forma.target = "";
                    document.forma.action = '/objectboard/citiesprocess';
                    document.forma.p_acciones.value = "delete";
                    document.forma.submit();
                }
            }
        }
        function nuevo_usuario(){
            document.forma.target = "";
            document.forma.action = '/objectboard/city';
            document.forma.p_acciones.value = "create";
            document.forma.submit();
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
                alert("There are no cities selected to remove.");
                return false;
            }
            return true;
        }
        function valida(state,contry,city){
            document.forma.p_country.value = contry;
            document.forma.p_state.value = state;
            document.forma.p_city.value = city;
            document.forma.target = "";
            document.forma.p_acciones.value = "save";
            document.forma.action = "/objectboard/city";
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
            <jstl:set var="jsp_titleFrom" scope="page" value="Cities List Form"/>
            <jstl:set var="jsp_funtionFrom" scope="page" value="List or select your options ..."/>
            <%@include file="../../../../complements/jsp/page-header.jsp"%>
            <!--end ############################### page-header ###############################-->
            <!--<section id="content">-->
            <div class="container mt-2 mb-2">
                <form class="user" role="form" method="post" action="#!" name="forma" >
                    <table id="other" cellspacing="0" width="100%" border="0">
                        <thead>
                        <tr>
                            <button type= "button" id="add"class= "btn btn-outline-success"  onClick=nuevo_usuario() ><i class='fa fa-file-o fa-fw'></i> New</button>
                            &nbsp;
                            <button type= "button" id="remove" class= "btn btn-outline-danger" onClick=borrar_usuario()>
                                <i class='fa fa-trash-o fa-fw'></i> Trash
                            </button>
                        </tr>
                        </thead>
                        <tbody>
                        <td>

                        </td>
                        </tbody>
                    </table>
                    <!-- <div id="toolbar">
                         <button type="button" class="btn btn-primary btn-circle btn-xl"><i class="fa fa-file-o"></i>
                         </button>
                        <button type= "button" id="add"class= "btn btn-outline-success"  onClick=nuevo_usuario() ><i class='fa fa-file-o fa-fw'></i> New</button>
                        <button type= "button" class= "btn btn-outline-danger" > Danger </button>
                        <button type= "button" id="remove" class= "btn btn-outline-danger" onClick=borrar_usuario()>
                            <i class='fa fa-trash-o fa-fw'></i> Trash
                        </button>

                        <button id="add" onClick=nuevo_usuario()  class="btn-primary" ><i class='fa fa-file-o fa-fw cuadro1'></i></button>
                        <button id="remove" onClick=borrar_usuario() class="btn-info"><i class='fa fa-trash-o fa-fw cuadro1' ></i></button>
                    </div>-->
                    <table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%"
                           data-toggle="example"
                           data-toolbar="#toolbar"
                           data-show-toggle="true"
                    >
                        <thead>
                        <tr>
                            <th data-field="state" data-sortable="false">
                                <input type='checkbox' onclick='marcar(this);' />
                            </th>
                            <th data-field="id" data-sortable="false">ID CITIES</th>
                            <th data-field="city" data-sortable="true" data-switchable="true" data-filter-control="input">CITY NAME</th>
                            <th data-field="name" data-sortable="true" data-switchable="true" data-filter-control="input">STATE NAME</th>
                            <th data-field="contry" data-sortable="true" data-switchable="true"data-filter-control="select">COUNTRY NAME</th>
                            <th data-field="action" data-sortable="false">ACTION</th>
                        </tr>
                        </thead>
                        <tbody>
                        <jtsl:set var="f" value="${0}" scope="page"/>
                        <jtsl:set var="cta" value="${1}" scope="page"/>
                        <jstl:forEach var="x" begin="0" end="${ rq_numFilas -1 }" step="1">
                            <jstl:set var = "f" value = "${f + 1}" />
                            <tr>
                                <td>
                                    <input type='checkbox' id="p_select" name='p_selec_<jstl:out value="${ f }"/>' />
                                </td>
                                <td>
                                    <jstl:out value="${ rq_cities.get(x).getId_city() }">Lost Value</jstl:out>
                                    <input type='hidden' value='<jstl:out value="${ Math.toIntExact(rq_cities.get(x).getId_city()) }"/>'
                                           name='p_cual_c<jstl:out value="${ f }"/>' />&nbsp;
                                    <input type='hidden' value='<jstl:out value="${ Math.toIntExact(rq_cities.get(x).getId_state()) }"/>'
                                           name='p_cual_s<jstl:out value="${ f }"/>' />&nbsp;
                                </td>
                                <td>
                                    <jstl:out value="${ rq_cities.get(x).getCity_name() }">Lost Value</jstl:out>
                                </td>
                                <td>
                                    <jstl:out value="${ rq_cities.get(x).getState_name() }">Lost Value</jstl:out>
                                </td>
                                <td>
                                    <jstl:out value="${ rq_cities.get(x).getCountry_name() }">Lost Value</jstl:out>
                                    <input type='hidden' value='<jstl:out value="${ Math.toIntExact(rq_cities.get(x).getId_country()) }"/>'
                                           name='p_cual_p<jstl:out value="${ f }"/>' />&nbsp;
                                </td>
                                <td>
                                    <a href='#' title="Edit" onClick=valida(${ rq_cities.get(x).getId_state() },${ rq_cities.get(x).getId_country()},${ rq_cities.get(x).getId_city() })>
                                        <i class='fa fa-edit fa-fw' ></i>
                                    </a>&nbsp;
                                </td>
                            </tr>
                        </jstl:forEach>
                        </tbody>
                    </table>
                    <input name='p_cuantos' type='hidden' value='<jstl:out value="${ rq_numFilas }">Lost Value</jstl:out>' />
                    <input name='p_country' type='hidden' value='' />
                    <input name='p_state' type='hidden' value='' />
                    <input name='p_city' type='hidden' value='' />
                    <input name='p_pantalla' type='hidden' value='cities' />
                    <input name="p_acciones" type="hidden" />
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

<!-- jQuery, Popper.js, Bootstrap JS
<script src="jquery/jquery-3.3.1.min.js"></script>-->
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

</html>