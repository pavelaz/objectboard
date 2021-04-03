<%--
  Created by IntelliJ IDEA.
  User: pavelaz
  Date: 6/4/20
  Time: 2:30 p. m.
  To change this template use File | Settings | File Templates.
  <%@ page contentType="text/html;charset=UTF-8" language="java" %>
--%>
<%
    String idselect = "select";
    String inclselect = "include";
    String idcual = "cual_";
    String idptos = "p_ptos_";
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Contact Include Files</title>
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
    <link rel="stylesheet" type="text/css" href=".<%= request.getContextPath() %>/complements/bootstrap/datatable_custom/datatables/datatables.min.css"/>
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
        function marcar_include(source) {
            checkboxes=document.getElementsByTagName('input'); //obtenemos todos los controles del tipo Input
            for(i=0;i<checkboxes.length;i++) //recoremos todos los controles
            {
                if(checkboxes[i].type == "checkbox" && checkboxes[i].id == "include") //solo si es un checkbox entramos
                {
                    checkboxes[i].checked=source.checked; //si es un checkbox le damos el valor del checkbox que lo llamó (Marcar/Desmarcar Todos)
                }
            }
        }
        function nuevos_registros(){
            if (validaItems_incluir
            ) {
                document.forma.target = "";
                document.forma.action = '/objectboard/multipartconfigservlet';
                document.forma.p_acciones.value = "create";
                document.forma.submit();
            }
        }
        function validaItems_incluir() {
            var cta=0;
            var checkboxes=document.getElementsByTagName('input'); //obtenemos todos los controles del tipo Input
            for(i=0;i<checkboxes.length;i++) {//recoremos todos los controles
                if(checkboxes[i].id == "include") {
                    if (checkboxes[i].checked) {//solo si es un checkbox entramos y validamos si esta chequeado
                        cta = cta + 1; //llevamos control de cuantos de ellos estan chequeados
                    }
                }
            }
            if(cta==0){
                alert("There are no Contact selected to include to the list.");
                return false;
            }
            return true;
        }
        /*function valida_annex_type() {
            if (document.forma.p_annex_type.value === "0"){
                document.forma.p_bdoc.value = "";
                document.forma.p_bimg.value = "";
            }
            if (document.forma.p_annex_type.value === "1"){
                if (!valida_textos(document.forma.p_bdoc.value,"Text document request",".,:")||
                    !valida_largos(document.forma.p_bdoc.value.length,"Text document request",2)){
                    document.forma.p_bimg.value = "";
                    return false;
                }
            }
            if (document.forma.p_annex_type.value === "2"){
                if (!valida_textos(document.forma.p_bimg.value,"Text image request",".:,")||
                    !valida_largos(document.forma.p_bimg.value.length,"Text image request",2)){
                    document.forma.p_bdoc.value = "";
                    return false;
                }
            }
            return true;
        }*/
        /*function varias_validaciones() {
            if (!valida_selects(document.forma.p_type.value,"Type Request","")){
                return false;
            }
            if (!valida_montos(document.forma.p_puntos.value,"Value or points assigned",1)){
                return false;
            }
            if (document.forma.p_type.value == 1 ||
                document.forma.p_type.value == 2 ||
                document.forma.p_type.value == 3 ||
                document.forma.p_type.value == 6 ||
                document.forma.p_type.value == 7 ||
                document.forma.p_type.value == 8
            ){
                if (!valida_textos(document.forma.p_main.value,"Text main request","?,.")||
                    !valida_largos(document.forma.p_main.value.length,"Text main request",2)){
                    return false;
                }
            }
            if (document.forma.p_type.value == 4){
                if (!valida_textos(document.forma.p_main.value,"Text main request","?,.")){
                    return false;
                }else
                    document.forma.p_annex_type.value = "1";
            }
            if (document.forma.p_type.value == 5){
                if (!valida_textos(document.forma.p_main.value,"Text main request","?,.")){
                    return false;
                }else
                    document.forma.p_annex_type.value = "2";
            }
            return true;
        } */
        <%--<jstl:when test="${ rq_acciones.equals('save') }">
        function update_registro(){
            if (varias_validaciones() &&
                valida_annex_type() &&
                valida_cambios()

            ) {
                document.forma.target = "";
                document.forma.action = '/objectboard/multipartconfigservlet';
                //document.forma.p_target.value = "bodysurveyquestionsprocess";
                document.forma.p_acciones.value = "save";
                document.forma.submit();
            }
        }
        function valida_cambios() {
            if (document.forma.p_annex_type.value === document.forma.p_annex_type_old.value &&
                document.forma.p_bdoc.value === document.forma.p_bdoc_old.value &&
                document.forma.p_bimg.value === document.forma.p_bimg_old.value &&
                document.forma.p_main.value === document.forma.p_main_old.value &&
                document.forma.p_comment.value === document.forma.p_comment_old.value &&
                document.forma.p_puntos.value === document.forma.p_puntos_old.value &&
                document.forma.p_iname_ac.value === document.forma.p_iname_an.value
            ){
                alert("No changes have been made to any of the fields, so there is nothing to save.");
                return false;
            }
            return true;
        }
        function cancelar(){
            document.forma.target = "";
            document.forma.action = '/objectboard/multipartconfigservlet';
            //document.forma.p_target.value = "bodysurveyquestions";
            document.forma.p_acciones.value = "consult";
            document.forma.submit();
        }
        function valida_annex_type() {
            if (document.forma.p_annex_type.value === "0"){
                document.forma.p_bdoc.value = "";
                document.forma.p_bimg.value = "";
            }
            if (document.forma.p_annex_type.value === "1"){
                if (!valida_textos(document.forma.p_bdoc.value,"Text document request",".:,")||
                    !valida_largos(document.forma.p_bdoc.value.length,"Text document request",2)){
                    document.forma.p_bimg.value = "";
                    return false;
                }else
                    document.forma.p_bimg.value = "";
            }
            if (document.forma.p_annex_type.value === "2"){
                if (!valida_textos(document.forma.p_bimg.value,"Text image request",".:,")||
                    !valida_largos(document.forma.p_bimg.value.length,"Text image request",2)){
                    document.forma.p_bdoc.value = "";
                    return false;
                }else
                    document.forma.p_bdoc.value = "";
            }
            return true;
        }
        function varias_validaciones() {
            if (!valida_montos(document.forma.p_puntos.value,"Value or points assigned",1)){
                return false;
            }
            if (document.forma.p_type.value == 1 ||
                document.forma.p_type.value == 2 ||
                document.forma.p_type.value == 3 ||
                document.forma.p_type.value == 6 ||
                document.forma.p_type.value == 7 ||
                document.forma.p_type.value == 8
            ){
                if (!valida_textos(document.forma.p_main.value,"Text main request","?,.")||
                    !valida_largos(document.forma.p_main.value.length,"Text main request",2)){
                    return false;
                }
            }
            if (document.forma.p_type.value == 4){
                if (!valida_textos(document.forma.p_main.value,"Text main request","?,.")){
                    return false;
                }else
                    document.forma.p_annex_type.value = "1";
            }
            if (document.forma.p_type.value == 5){
                if (!valida_textos(document.forma.p_main.value,"Text main request","?,.")){
                    return false;
                }else
                    document.forma.p_annex_type.value = "2";
            }
            return true;
        }
        </jstl:when> --%>
        function marcar(source) {
            checkboxes=document.getElementsByTagName('input'); //obtenemos todos los controles del tipo Input
            for(i=0;i<checkboxes.length;i++) //recoremos todos los controles
            {
                if(checkboxes[i].type == "checkbox" && checkboxes[i].id == "select" ) //solo si es un checkbox entramos
                {
                    checkboxes[i].checked=source.checked; //si es un checkbox le damos el valor del checkbox que lo llamó (Marcar/Desmarcar Todos)
                }
            }
        }
        function borrar_registro(){
            if(validaItems_eliminar()){
                if ( confirm("Do You really want to delete the selected Request?")) {
                    document.forma.action = '/objectboard/multipartconfigservlet';
                    //document.forma.p_target.value = "bodysurveyquestionsprocess";
                    document.forma.p_acciones.value = "delete";
                    document.forma.submit();
                }
            }
        }
        function validaItems_eliminar() {
            var cta=0;
            var checkboxes=document.getElementsByTagName('input'); //obtenemos todos los controles del tipo Input
            for(i=0;i<checkboxes.length;i++) {//recoremos todos los controles
                if(checkboxes[i].id == "select") {
                    if (checkboxes[i].checked) {//solo si es un checkbox entramos y validamos si esta chequeado
                        cta = cta + 1; //llevamos control de cuantos de ellos estan chequeados
                    }
                }
            }
            if(cta==0){
                alert("There are no Contact selected to remove from list.");
                return false;
            }
            return true;
        }
        /*function valida(valor0){
            document.forma.p_request_code.value = valor0;
            document.forma.target = "";
            document.forma.p_acciones.value = "save";
            document.forma.action = "/objectboard/multipartconfigservlet";
            //document.forma.p_target.value = "bodysurveyquestions";
            document.forma.submit();
        }
        function create_answers(valor0,valor1,valor2) {
            document.forma.target = "";
            document.forma.p_request_code.value = valor0;
            document.forma.p_request_type.value = valor1;
            document.forma.p_request_main.value = valor2.replace("-"," ");
            document.forma.p_acciones.value = "consult";
            document.forma.action = "/objectboard/multipartconfigservlet";
            document.forma.p_target.value = "answerssurveyrequests";
            document.forma.submit();
        }*/
        /*function valida_columnas(){
            document.forma.action = "/objectboard/multipartconfigservlet";
            document.forma.p_target.value = "bodysurveyquestions";
            document.forma.submit();
        }*/
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
        <jstl:set var="jsp_pagePrevious" scope="page" value="/objectboard/contactslist"/>
        <%@include file="../../../../complements/jsp/navbar-header.jsp"%>
        <!--end ############################### navbar-header ###############################-->
        <!-- Contenedor central -->
        <div class="pcoded-main-container">
            <!--start ############################### page-header ###############################-->
            <jstl:set var="jsp_titleFrom" scope="page" value="Contacts Include List Form"/>
            <jstl:set var="jsp_funtionFrom" scope="page" value="List or select your options ..."/>
            <%@include file="../../../../complements/jsp/page-header.jsp"%>
            <!--end ############################### page-header ###############################-->
            <!--<section id="content">-->
            <div class="container mt-2 mb-2">
                <form class="user" role="form" method="post" action="#!" name="forma" id="formPhoto" >
                            <div class="form-group row">
                                <div class="col-sm-6">
                                    <!-- Tabla de contactos a incluir en la lista -->
                                    <table id="adicion" class="table table-striped table-bordered" cellspacing="0" width="100%"
                                           data-toggle="example"
                                           data-toolbar="#toolbar"
                                           data-show-toggle="true"
                                    >
                                        <thead>
                                        <tr>
                                            <th data-field="state" data-sortable="false" >
                                                <input type='checkbox' onclick='marcar_include(this);' />&nbsp;&nbsp;&nbsp;TO INCLUDE
                                            </th>
                                            <th data-field="l2" data-sortable="true" data-switchable="true">EMAIL DIRECTIONS</th>
                                            <th data-field="l3" data-sortable="true" data-switchable="true">NAME</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <jstl:choose>
                                            <jstl:when test="${ rq_inclus.size() != 0 }">
                                                <jstl:set var="f" value="${0}" scope="page"/>
                                                <jstl:forEach var="x" begin="0" end="${ rq_inclus.size() -1 }" step="1">
                                                    <jstl:set var = "f" value = "${f + 1}" />
                                                    <tr>
                                                        <td>
                                                            <input type='checkbox' id="<%=inclselect%>" name='p_include_<jstl:out value="${ f }"/>' />
                                                            <input type='hidden' value='<jstl:out value="${ rq_inclus.get(x).getCto_email_direction() }"/>'
                                                                   name='p_inclu_0<jstl:out value="${ f }"/>' id="<%=idcual%>i_0" />
                                                        </td>
                                                        <td>
                                                            <jstl:out value="${ rq_inclus.get(x).getCto_email_direction() }">Lost Value</jstl:out>
                                                        </td>
                                                        <td>
                                                            <jstl:out value="${ rq_inclus.get(x).getCto_name() }">Lost Value</jstl:out>
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
                                                </tr>
                                            </jstl:otherwise>
                                        </jstl:choose>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="col-sm-1">
                                    <!-- Tabla de botones centrales disponibles -->
                                    <table id="other" cellspacing="0" width="100%" border="0">
                                        <thead>
                                                <tr>
                                                    <button type= "button" id="add1" title="Include New contact" class= "btn btn-outline-success" onClick=nuevos_registros() >
                                                        <i class='fa fa-file-o fa-fw'></i></button>
                                                    &nbsp;
                                                    <button type= "button" id="remove1" title="Delete contact" class= "btn btn-outline-danger" onClick=borrar_registros() >
                                                        <i class='fa fa-trash-o fa-fw'></i>
                                                    </button>
                                        </thead>
                                        <tbody>
                                        <td>

                                        </td>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="col-sm-5">
                                    <!-- Tabla de contactos de la lista -->
                                    <table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%"
                                           data-toggle="example"
                                           data-toolbar="#toolbar"
                                           data-show-toggle="true"
                                    >
                                        <thead>
                                        <tr>
                                            <th >
                                                Contacts List Name :
                                            </th>
                                            <th>
                                                <jstl:out value="${ rq_listaName }">Lost Value</jstl:out>
                                            </th>
                                        </tr>
                                        <tr>
                                            <th data-field="state" data-sortable="false" >
                                                <input type='checkbox' onclick='marcar(this);' />&nbsp;&nbsp;&nbspTO DELETE
                                            </th>
                                            <%--<th data-field="ac" data-sortable="false" data-valign="middle" data-align="center">ACTION</th> --%>
                                            <th data-field="l2" data-sortable="true" data-switchable="true">EMAIL DIRECTIONS</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <jstl:choose>
                                            <jstl:when test="${ rq_contac.size() != 0 }">
                                                <jstl:set var="f" value="${0}" scope="page"/>
                                                <jstl:forEach var="x" begin="0" end="${ rq_contac.size() -1 }" step="1">
                                                    <jstl:set var = "f" value = "${f + 1}" />
                                                    <tr>
                                                        <td>
                                                            <input type='checkbox' id="p_<%=idselect%>" name='p_selec_<jstl:out value="${ f }"/>' />
                                                            <input type='hidden' value='<jstl:out value="${ rq_contac.get(x).getList_name() }"/>'
                                                                   name='p_cual_0<jstl:out value="${ f }"/>' id="<%=idcual%>_0" />
                                                        </td>
                                                        <td>
                                                            <jstl:out value="${ rq_contac.get(x).getList_name() }">Lost Value</jstl:out>
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
                                                        Not Action
                                                    </td>
                                                </tr>
                                            </jstl:otherwise>
                                        </jstl:choose>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <hr>
                    <input name='p_cuantos_to_delete' type='hidden' value='<jstl:out value="${ rq_contac.size() }">Lost Value</jstl:out>' />
                    <input name='p_cuantos_to_include' type='hidden' value='<jstl:out value="${ rq_inclus.size() }">Lost Value</jstl:out>' />
                    <input name="p_acciones" type="hidden" value='<jstl:out value="${ rq_acciones }">Lost Value</jstl:out>'/>
                    <input name='p_lista_name' type='hidden' value='${ rq_listaName }' />
                    <input name='p_lista_id' type='hidden' value='${ rq_listaId}' />
                    <input name='p_pantalla' type='hidden' value='contactsListFooter' />
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
            "scrollY": 375,
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
        $('#adicion').DataTable({
            "scrollY": 375,
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
<script type="text/javascript">
    function filePreview(input) {
        var oldPhoto = document.getElementById('oldPhoto');
        var filePath = input.value;
        var allowedExtensions = /(\.jpg|\.png|\.jpeg)$/i;
        if (!allowedExtensions.exec(filePath)) {
            alert("Please upload file having extensions .jpg/ .png/ .jpeg/ only.");
            input.value = "";
            return false;
        }else{
            if (input.files[0].size > (1024 * 1024 * 1)){ // 1024 * 1024 * 1,= 1 MB)
                alert("Please upload a photo not exceeding 1 Mb.");
                input.value = "";
                return false;
            }
            else{
                if (input.files && input.files[0]) {
                    var reader = new FileReader();
                    var image = new Image();
                    document.forma.p_iname_ac.value = input.files[0].name;
                    reader.onload = function (e) {
                        image.src = e.target.result;
                        //Validate the File Height and Width.
                        image.onload = function (){
                            var height = this.height;
                            var width = this.width;
                            //if ( 600 > height && 600 > width){
                            oldPhoto.remove();
                            //alert("Height and Width must not exceed a Photo the."+height+" "+width);
                            $('#file').before('<img id="oldPhoto" name="oldPhoto" class="img-thumbnail" alt="Photo Profile" src="' + e.target.result + '" width="100" height="120" /> &nbsp');
                            // return false;
                            //}
                            alert("The uploaded image has a valid height and width of 500 x 499,\n" +
                                "and if it exceeds these dimensions, it will be readjusted upon storage. ");
                            return true;
                        }
                    }
                    reader.readAsDataURL(input.files[0]);
                }
            }
        }
    }

</script>
</body>



