<%--
  Created by IntelliJ IDEA.
  User: pavelaz
  Date: 2/15/21
  Time: 2:30 p. m.
  To change this template use File | Settings | File Templates.
  <%@ page contentType="text/html;charset=UTF-8" language="java" %>
--%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Bussiness Types Files</title>
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
                document.forma.action = '/objectboard/bussinesstypes';
                document.forma.p_acciones.value = "create";
                document.forma.submit();
            }
        </jstl:when>
        <jstl:when test="${ rq_acciones.equals('create') }">
            function nuevo_registro(){
                if (varias_validaciones() &&
                    valida_duplicados(this)) {
                    document.forma.target = "";
                    document.forma.action = '/objectboard/bussinesstypesprocess';
                    document.forma.p_acciones.value = "create";
                    document.forma.submit();
                }
            }
            function cancelar(){
                document.forma.target = "";
                document.forma.action = '/objectboard/bussinesstypes';
                document.forma.p_acciones.value = "consult";
                document.forma.submit();
            }
            function varias_validaciones() {
                if (!valida_textos(document.forma.p_name.value,"Bussiness Type Descripcion","")||
                    !valida_largos(document.forma.p_name.value.length,"Bussiness Type Descripcion",2)||
                    !valida_textos(document.forma.p_note.value,"Bussiness Type Note",",.")||
                    !valida_largos(document.forma.p_note.value.length,"Bussiness Type Note",2)
                ){
                    return false;
                }
                return true;
            }
            function valida_duplicados(source) {
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
                    alert("The bussinesstypes structure that you want to create or modify \nalready exists previously, so the requested changes will NOT be made.");
                    return false;
                }
                return true;
            }
        </jstl:when>
        <jstl:when test="${ rq_acciones.equals('save') }">
            function update_registro(){
                //alert("paso");
                if (varias_validaciones() &&
                    otras_validaciones()  &&
                    valida_duplicados(this)) {
                    document.forma.target = "";
                    document.forma.action = '/objectboard/bussinesstypesprocess';
                    document.forma.p_acciones.value = "save";
                    document.forma.submit();
                }
            }
            function cancelar(){
                document.forma.target = "";
                document.forma.action = '/objectboard/bussinesstypes';
                document.forma.p_acciones.value = "consult";
                document.forma.submit();
            }
            function varias_validaciones() {
                if (!valida_textos(document.forma.p_name.value,"Bussiness Type Name","")||
                    !valida_largos(document.forma.p_name.value.length,"Bussiness Type Name",2)||
                    !valida_textos(document.forma.p_note.value,"Bussiness Type Note",".,")||
                    !valida_largos(document.forma.p_note.value.length,"Bussiness Type Note",2)
                ){
                    return false;
                }
                return true;
            }
            function otras_validaciones() {
                if (document.forma.p_name.value === document.forma.p_name_1.value &&
                    document.forma.p_note.value === document.forma.p_note_1.value
                ){
                    alert("No changes have been made to the fields of the selected record, so \nthere is no need to save.");
                    return false;
                }
                return true;
            }
            function valida_duplicados(source) {
                var cta1=0;
                var checkboxes=document.getElementsByTagName('input'); //obtenemos todos los controles del tipo Input los llamamboxesos check
                for(i=0;i<checkboxes.length;i++) {//recoremos todos los controles
                    if(checkboxes[i].id == "cual_1") {
                        if (checkboxes[i].value == document.forma.p_name.value) {//solo si es un id particular entramos y validamos si esta chequeado
                            cta1 = cta1 + 1; //llevamos control de cuantos de ellos son iguales
                        }
                    }
                }
                if(cta1!=0 ){
                    alert("The Bussiness Type structure that you want to create or modify \nalready exists previously, so the requested changes will NOT be made.");
                    return false;
                }
                return true;
            }
        </jstl:when>
        </jstl:choose>
        function borrar_registro(){
            if(validaItems(this)){
                if ( confirm("Do You really want to delete the selected Bussiness Type?")) {
                    document.forma.action = '/objectboard/bussinesstypesprocess';
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
                alert("There are no Bussiness Types selected to remove.");
                return false;
            }
            return true;
        }
        function valida(id){
            //alert("paulxxx");
            document.forma.p_id_1.value = id;
            document.forma.target = "";
            document.forma.p_acciones.value = "save";
            document.forma.action = "/objectboard/bussinesstypes";
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
            <jstl:set var="jsp_titleFrom" scope="page" value="Bussiness Type List Form"/>
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
                                    <label for="name" class="col-form-label">Bussiness Type:</label>
                                </div>
                                <div class="col-sm-3">
                                    <input name="p_name" id="name" maxlength="45" type="text" class="form-control"
                                           onfocus="selecciona_contenido(this)">
                                </div>
                                <div class="col-sm-1">

                                </div>
                                <div class="col-sm-2">
                                    <label for="note" class="col-form-label">Bussiness Description:</label>
                                </div>
                                <div class="col-sm-4">
                                    <input name="p_note" id="note" maxlength="85" type="text" class="form-control"
                                           onfocus="selecciona_contenido(this)">
                                </div>
                            </div>
                            <hr>
                        </jstl:when>
                        <jstl:when test="${ rq_acciones.equals('save') }">
                            <div class="form-group row">
                                <div class="col-sm-2">
                                    <label for="name1" class="col-form-label">Bussiness Type Name:</label>
                                </div>
                                <div class="col-sm-3">
                                    <input name="p_name" id="name1" maxlength="45" type="text" class="form-control"
                                           onfocus="selecciona_contenido(this)" value='${rq_name}'>
                                    <input name="p_id" type="hidden" class="form-control" value='${ rq_id }'>
                                </div>
                                <div class="col-sm-1">

                                </div>
                                <div class="col-sm-2">
                                    <label for="note1" class="col-form-label">Bussiness Type Descripcion:</label>
                                </div>
                                <div class="col-sm-4">
                                    <input name="p_note" id="note1" maxlength="85" type="text" class="form-control"
                                           onfocus="selecciona_contenido(this)" value='${rq_note}'>
                                </div>
                            </div>
                            <hr>
                        </jstl:when>
                    </jstl:choose>
                    <table id="other" cellspacing="0" width="100%" border="0">
                        <thead>
                        <jstl:choose>
                            <jstl:when test="${ rq_acciones.equals('consult') }">
                                <tr>
                                    <button type= "button" id="add" title="Create New Bussiness Type" class= "btn btn-outline-success" onClick=nuevo_registro() ><i class='fa fa-file-o fa-fw'></i> New</button>
                                    &nbsp;
                                    <button type= "button" id="remove" title="Delete Bussiness Type" class= "btn btn-outline-danger" onClick=borrar_registro() >
                                        <i class='fa fa-trash-o fa-fw'></i> Trash
                                    </button>
                                </tr>
                            </jstl:when>
                            <jstl:when test="${ rq_acciones.equals('create') }">
                                <tr>
                                    <button type= "button" id="add1" title="Create New Bussiness Type" class= "btn btn-outline-success" onClick=nuevo_registro() >
                                        <i class='fa fa-file-o fa-fw'></i> Create</button>
                                    &nbsp;
                                    <button type= "button" id="remove1" title="Delete Bussiness Type" class= "btn btn-outline-danger" onClick=borrar_registro() >
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
                                    <button type= "button" id="add2" title="Update Bussiness Type" class= "btn btn-outline-success" onClick=update_registro() >
                                        <i class='fa fa-file-o fa-fw'></i> Save
                                    </button>
                                    &nbsp;
                                    <button type= "button" id="remove2" title="Delete Bussiness Type" class= "btn btn-outline-danger" onClick=borrar_registro() >
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
                            <th data-field="state" data-sortable="false">
                                <input type='checkbox' onclick='marcar(this);' />
                            </th>
                            <th data-field="name" data-sortable="true" data-switchable="true" data-filter-control="input">TYPE</th>
                            <th data-field="st" data-sortable="true" data-switchable="true" data-filter-control="input">DESCRIPTION</th>
                            <th data-field="action" data-sortable="false">ACTION</th>
                        </tr>
                        </thead>
                        <tbody>
                        <jstl:choose>
                            <jstl:when test="${ rq_numFilas != 0 }">
                                <jstl:set var="f" value="${0}" scope="page"/>
                                <jstl:forEach var="x" begin="0" end="${ rq_provo.size() -1 }" step="1">
                                    <jstl:set var = "f" value = "${f + 1}" />
                                    <tr>
                                        <td>
                                            <input type='checkbox' id="p_select" name='p_selec_<jstl:out value="${ f }"/>' />
                                        </td>
                                        <td>
                                            <jstl:out value="${ rq_provo.get(x).getBtDescription() }">Lost Value</jstl:out>
                                            <input type='hidden' value='<jstl:out value="${ rq_provo.get(x).getBtDescription() }"/>'
                                                   name='p_cual_1<jstl:out value="${ f }"/>' id="cual_1" />&nbsp;
                                            <input type='hidden' value='<jstl:out value="${ rq_provo.get(x).getBtCodeType() }"/>'
                                                   name='p_cual_2<jstl:out value="${ f }"/>' id="cual_2" />&nbsp;
                                        </td>
                                        <td>
                                            <jstl:out value="${ rq_provo.get(x).getBtNote() }">Lost Value</jstl:out>
                                        </td>
                                        <td>
                                            <a href='#' title="Edit" onClick=valida(${ rq_provo.get(x).getBtCodeType() })>
                                                <i class='fa fa-edit fa-fw' ></i>
                                            </a>&nbsp;
                                        </td>
                                    </tr>
                                </jstl:forEach>
                            </jstl:when>
                            <jstl:otherwise>
                                <tr>
                                    <td>
                                        &nbsp;
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
                    <input name='p_cuantos' type='hidden' value='<jstl:out value="${ rq_provo.size() }">Lost Value</jstl:out>' />
                    <input name='p_id_1' type='hidden' value='${ rq_id }' />
                    <input name='p_note_1' type='hidden' value='${ rq_note }' />
                    <input name='p_name_1' type='hidden' value='${ rq_name }' />
                    <input name='p_pantalla' type='hidden' value='bussinestypes' />
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