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
    <title>Bussiness Type Files</title>
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

</head>

<style>
    ::-moz-selection { /* Code for Firefox */
        color: white;
        background: #0101cb;
    }
    ::selection {
        color: white;
        background: #0101cb;
    }
</style>

<body>
<!-- Pre-loader start x
start ############################### Pre-loader ###############################
<%@include file="../../../../complements/jsp/pre_loader.jsp"%>
-end ############################### Pre-loader ###############################-->

<!--start ############################### body ###############################-->
<div class="theme-loader">
    <div class="loader-track">
        <div class="preloader-wrapper">
            <div class="spinner-layer spinner-blue">
                <div class="circle-clipper left">
                    <div class="circle"></div>
                </div>
                <div class="gap-patch">
                    <div class="circle"></div>
                </div>
                <div class="circle-clipper right">
                    <div class="circle"></div>
                </div>
            </div>
            <div class="spinner-layer spinner-red">
                <div class="circle-clipper left">
                    <div class="circle"></div>
                </div>
                <div class="gap-patch">
                    <div class="circle"></div>
                </div>
                <div class="circle-clipper right">
                    <div class="circle"></div>
                </div>
            </div>
            <div class="spinner-layer spinner-yellow">
                <div class="circle-clipper left">
                    <div class="circle"></div>
                </div>
                <div class="gap-patch">
                    <div class="circle"></div>
                </div>
                <div class="circle-clipper right">
                    <div class="circle"></div>
                </div>
            </div>
            <div class="spinner-layer spinner-green">
                <div class="circle-clipper left">
                    <div class="circle"></div>
                </div>
                <div class="gap-patch">
                    <div class="circle"></div>
                </div>
                <div class="circle-clipper right">
                    <div class="circle"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Pre-loader end -->
<div id="pcoded" class="pcoded">
    <div class="pcoded-overlay-box">

    </div>
    <div class="pcoded-container navbar-wrapper">
        <!--start ############################### navbar ###############################-->
        <nav class="pcoded-navbar">
            <!--Navegation = 1, Miscellaneous main files = 2,
             Properties Form = 8 {Save = 8.1, Plus = 8.2, Trash = 8.3, Printer = 8.4, Test =8.5}-->
            <jstl:set var="jsp_navBarOption" scope="page" value="1,8"/>
            <%@include file="../../../../complements/jsp/navbar.jsp"%>
        </nav>
        <!--end ############################### navbar ###############################-->

        <!--start ############################### navbar-header ###############################-->
        <!--Mostrar imagen = 'YES', No mostar imagen = 'NOT'-->
        <jstl:set var="jsp_ShowImage" scope="page" value="${ 'YES' }"/>
        <!--start ############## dropdown menu ##############-->
        <!--    Settings = 1, Profile = 2, My Messages = 3, Lock Screen = 4, Logout = 5.-->
        <jstl:set var="jsp_menuOption" scope="page" value="${ '3,4,5' }"/>
        <!--end ############## dropdown menu ################-->
        <%@include file="../../../../complements/jsp/navbar-header.jsp"%>
        <!--end ############################### navbar-header ###############################-->

        <div class="pcoded-main-container">
            <div class="pcoded-wrapper">
                <div class="pcoded-content">

                    <!--start ############################### page-header ###############################-->
                    <jstl:set var="jsp_titleFrom" scope="page" value="Bussiness Type form"/>
                    <jstl:set var="jsp_funtionFrom" scope="page" value="Objectboard system, update your information..."/>
                    <jstl:choose>
                        <jstl:when test="${ rq_companyNumber == 1 }">
                            <jstl:set var="jsp_pagePrevious" scope="page" value="/objectboard/mastermenusuper"/>
                        </jstl:when>
                        <jstl:otherwise>
                            <jstl:set var="jsp_pagePrevious" scope="page" value="/objectboard/dashmenusuper"/>
                        </jstl:otherwise>
                    </jstl:choose>
                    <jstl:set var="jsp_previousMsg" scope="page" value="Previous menu"/>
                    <%@include file="../../../../complements/jsp/page-header.jsp"%>
                    <!--end ############################### page-header ###############################-->

                    <div class="pcoded-inner-content">
                        <div class="main-body">
                            <div class="page-wrapper">
                                <div class="page-body">
                                    <!-- Basic Form Inputs card start -->
                                    <div class="card">
                                        <div class="card-header">
                                            <h5>Tabla de BussinessType</h5>
                                        </div>
                                        <div class="card-header">
                                            <div class="card-header-right">
                                                <ul class="list-unstyled card-option">
                                                    <li>
                                                        <i class="fa fa fa-wrench open-card-option"></i>
                                                    </li>
                                                    <li>
                                                        <i class="fa fa-window-maximize full-card"></i>
                                                    </li>
                                                    <li>
                                                        <i class="fa fa-minus minimize-card"></i>
                                                    </li>
                                                    <li>
                                                        <i class="fa fa-refresh reload-card"></i>
                                                    </li>
                                                    <li>
                                                        <i class="fa fa-trash close-card"></i>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                        <div class="card-block">
                                            <!--start ############################# MAIN BODY (DIFFERENT PROJECTS)###############################-->
                                            <!--START##########################################CUERPO PRINCIPAL#####################################################-->
                                            <div class="container mt-2 mb-2">
                                                <!--<table id="table" class="table table-striped table-bordered" cellspacing="0" width="100%"
                                                       data-toggle="table"
                                                       data-toolbar="#toolbar"
                                                       data-show-toggle="true"
                                                >-->
                                                <table id="table" class="table table-striped table-bordered mydatatable" style="width :100%">
                                                    <thead>
                                                    <tr>
                                                        <th style="display: none" data-field="checkbox" data-sortable="false">
                                                            <input id='mainCheckbox' type='checkbox'/></th>
                                                        <th style="display: none" data-field="actionsPrevious" data-sortable="true" data-switchable="true" data-filter-control="input">ActionsPrevious</th>
                                                        <th style="display: none" data-field="action" data-sortable="true" data-switchable="true" data-filter-control="input">Actions</th>
                                                        <th data-field="id" data-sortable="true" data-switchable="true" data-filter-control="input">CODE</th>
                                                        <th data-field="bussinessType" data-sortable="true" data-switchable="true" data-filter-control="input">BUSSINESS TYPE</th>
                                                        <th data-field="description" data-sortable="true" data-switchable="true" data-filter-control="input">DESCRIPTION</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody id="tbody">
                                                    <jstl:forEach varStatus="status" var="bussinessTypeDto"  items="${ rq_BussinessTypeDto.getBussinessTypeDtoSet() }">
                                                        <tr><!--#Start#######################Fila########################-->
                                                            <td style="display: none">
                                                                <input type='checkbox'/></td>
                                                            <td style="display: none"></td>
                                                            <td style="display: none"></td>
                                                            <td><jstl:out value="${ bussinessTypeDto.btCodeType }">Lost Value</jstl:out></td>
                                                            <td oninput="writeAction(this, 'up');enableButtonSave(true)" id='tdId<jstl:out value="${ status.count }"/>-1'>
                                                                <jstl:out value="${ bussinessTypeDto.btDescription }">Lost Value</jstl:out></td>
                                                            <td oninput="writeAction(this, 'up');enableButtonSave(true)" id='tdId<jstl:out value="${ status.count }"/>-2'>
                                                                <jstl:out value="${ bussinessTypeDto.btNote }">Lost Value</jstl:out></td>
                                                        </tr><!--#End#######################Fila########################-->
                                                        <jstl:if test="${ status.last }">
                                                            <input id="lastNumberCell" type="hidden" value='${ status.count }'/>
                                                        </jstl:if>
                                                    </jstl:forEach>
                                                    </tbody>
                                                    <tfoot>
                                                    <tr>
                                                        <th style="display: none" data-field="checkbox" data-sortable="true"></th>
                                                        <th style="display: none" data-field="actionsPrevious" data-sortable="true" data-switchable="true" data-filter-control="input">ActionsPrevious</th>
                                                        <th style="display: none" data-field="action" data-sortable="true" data-switchable="true" data-filter-control="input">Actions</th>
                                                        <th data-field="id" data-sortable="true" data-switchable="true" data-filter-control="input">CODE</th>
                                                        <th data-field="bussinessType" data-sortable="true" data-switchable="true" data-filter-control="input">BUSSINESS TYPE</th>
                                                        <th data-field="description" data-sortable="true" data-switchable="true" data-filter-control="input">DESCRIPTION</th>
                                                    </tr>
                                                    </tfoot>
                                                </table>
                                                <!--#Start#######################Send arrayTableData########################-->
                                                <form method="post" name="formTable" id="formTable">
                                                    <input name="arrayTable" type="hidden"/>
                                                    <input name="lengthTable" type="hidden">
                                                </form>
                                                <form method="post" name="formDeleteTable" id="formDeleteTable">
                                                    <input name="arrayDeleteTable" type="hidden"/>
                                                    <input name="lengthTable" type="hidden">
                                                </form>
                                                <!--#End#######################Send arrayTableData########################-->
                                            </div>
                                            <!--END##########################################CUERPO PRINCIPAL#####################################################-->

                                            <!--end ############################### MAIN BODY (DIFFERENT PROJECTS)###############################-->
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- Basic Form Inputs card end -->
                        </div>
                    </div>
                    <!--start ############################### footer ###############################-->
                    <!--end ############################### footer ##########################-->
                    <%@include file="../../../../complements/jsp/footer.jsp"%>
                    <!--end ############################### footer ###############################-->
                    <!--start ############################### footer_basic_script #############################-->
                    <%@include file="../../../../complements/jsp/footer_basic_script.jsp"%>
                    <!--end ############################### footer_basic_script ###############################-->
                    <!--end ############################### footer ###############################-->
                </div>
            </div>
        </div>
        <div id="styleSelector"></div>
    </div>
</div>
</div>
</div>

<script type="text/javascript" src="<%= request.getContextPath() %>/complements/bootstrap/datatable_custom/datatables/datatables.min.js"></script>
<!-- para usar botones en datatables JS -->
<script src="<%= request.getContextPath() %>/complements/bootstrap/datatable_custom/datatables/Buttons-1.5.6/js/dataTables.buttons.min.js"></script>
<script src="<%= request.getContextPath() %>/complements/bootstrap/datatable_custom/datatables/JSZip-2.5.0/jszip.min.js"></script>
<script src="<%= request.getContextPath() %>/complements/bootstrap/datatable_custom/datatables/pdfmake-0.1.36/pdfmake.min.js"></script>
<script src="<%= request.getContextPath() %>/complements/bootstrap/datatable_custom/datatables/pdfmake-0.1.36/vfs_fonts.js"></script>
<script src="<%= request.getContextPath() %>/complements/bootstrap/datatable_custom/datatables/Buttons-1.5.6/js/buttons.html5.min.js"></script>

<!-- código JS propìo-->
<script type="text/javascript" src="<%= request.getContextPath() %>/complements/bootstrap/datatable_custom/main.js"></script>

<!--star ############################### section JavaScript ###############################-->
<script type="text/javascript">

    $(document).ready(function() {
        /*Start################### Valores por defecto para barra de comandos  ###################*/
        document.getElementById("buttonSave").setAttribute("onClick", "sendData()");
        document.getElementById("buttonTrash").setAttribute("onClick", "deleteRow()");
        <!--si refrescamos pagina desmarcamos checkbox-->
        jQuery(':checkbox').each(function () {
            this.checked = false;
            /*this.style.display = "none";*/
        });
        /*End################### Valores por defecto para barra de comandos  ###################*/
    });

    /*Start################### seccion de eventos ###################*/
    //new event
    jQuery("#mainCheckbox").click(function () {
        jQuery(':checkbox').each(function () {
            if (this.checked) {
                this.checked = false;
                document.getElementById("mainCheckbox").checked = false;
            }
            else{
                this.checked = true;
                document.getElementById("mainCheckbox").checked = true;
            }
        });
    });
    //new event
    $('input[type="checkbox"]').change( function() {
        var check = $(this).prop('checked');
        var tr = ($(this)).parent().parent().get(0);

        if (tr.rowIndex != 0) {
            if (check){
                document.getElementById("table").rows[tr.rowIndex].cells[2].innerText = "-";
                enableButtonTrash(true);
            }
            else {
                document.getElementById("table").rows[tr.rowIndex].cells[2].innerText = document.getElementById("table").rows[tr.rowIndex].cells[1].innerText;
                enableButtonTrash(false);
            }
        }
    });
    //new event
    $("#buttonPlus").click(function(){
        const x = confirm("Activate in multiple edition?");
        const firstRowAndCell = document.getElementById("table").rows[0].cells[0].style.display;
        if (x) {
            const interators = prompt("Entre number of rows: ");
            if ((interators >= 2) && (interators <= 10)) {
                if (firstRowAndCell == "none") {
                    insertNewTr(interators, true);
                } else {
                    insertNewTr(interators, false);
                }
            } else alert("The number entered is incorrect, please enter according to the valid format: [2..10]");
        }
        else { //(false) const x = confirm("Activate in multiple edition?");
            if (firstRowAndCell  == "none") {
                insertNewTr(1, true);
            } else {
                insertNewTr(1, false);
            }
        }
        /*********************/
        const checkboxs = document.querySelectorAll('input[type="checkbox"]');
        checkboxs.forEach(checkbox=>{
            checkbox.addEventListener('click', function (event) {
                var check = $(this).prop('checked');
                var tr = ($(this)).parent().parent().get(0);

                if (tr.rowIndex != 0) {
                    if (check){
                        document.getElementById("table").rows[tr.rowIndex].cells[2].innerText = "-";
                        enableButtonTrash(true);
                    }
                    else {
                        document.getElementById("table").rows[tr.rowIndex].cells[2].innerText = document.getElementById("table").rows[tr.rowIndex].cells[1].innerText;
                        enableButtonTrash(false);
                    }
                }
            });
        });
        /*********************/
    });

    //new event
    $("#buttonHideCell").click(function(){
        var nameTable = "table"
        var  x = document.getElementById(nameTable).rows[0].cells[0].style.display;
        if (x == "none")
            hideCell(nameTable, 1, false);
        else
            hideCell(nameTable, 1, true);
    });
    //new event
    $('td').dblclick(function(){
        var tdId = $(this).attr('id');
        var tr = document.getElementById(tdId).parentElement;
        document.getElementById(tdId).contentEditable = "true";

        if ((document.getElementById("table").rows[tr.rowIndex].cells[0].children[0].checked) = true){
            document.getElementById("table").rows[tr.rowIndex].cells[0].children[0].checked = false;
            enableButtonTrash(false);
        };
    });
    //new event
    $('#tdbuttonHideCell').click(function() {
        var x = document.getElementById("table").rows[0].cells[0].style.display;
        if (x == "none")
            hideCell("table", 1, false);
        else
            hideCell("table", 1, true);
    });
    //new event
    /*End################### seccion de eventos ###################*/
    /*Start################### seccion de funciones ###################*/
    //new function
    function  writeAction(element, action) {
        document.getElementById("table").rows[element.closest('tr').rowIndex].cells[1].innerText = action;
        document.getElementById("table").rows[element.closest('tr').rowIndex].cells[2].innerText = action;
    }
    //new function
    function tableReading() {
        const cells = document.querySelectorAll("td");
        const rows = document.querySelectorAll("th");
        let arrayTable = new Array();
        //var rowsToSubTract = 18+2;
        cells.forEach((cell=>{
            if (cell.cellIndex != 0 && cell.cellIndex >= 2)
                arrayTable.push((cell.innerText).replace(","," -"));
        }));
        console.log(arrayTable);
        document.formTable.arrayTable.value = arrayTable;
        //4 es el numero de filas que hacen parte del head y food de la tabla boostrap.
        //2 es son los campos de la tabla excluidos [(0)input y (1)ActionsPrevious];
        document.formTable.lengthTable.value = ((rows.length / 4) - 2);
    };
    //new function
    function deleteRow(){
        let arrayDeleteTable = new Array();
        var rows = document.querySelectorAll('th');
        var checkboxsToDelete = document.querySelectorAll('input[type="checkbox"]:checked');
        const x = confirm("Are you sure to delete the row(s)?")
        //var rowsToSubTract = 18;

        if (x){
            console.log("NumberCheckBox: "+checkboxsToDelete.length);
            console.log(checkboxsToDelete[0].attributes.item(0).ownerElement.parentNode.nodeName);

            checkboxsToDelete.forEach(function(checkbox) {
                console.log(checkbox.attributes.item(this).ownerElement.parentNode.parentNode.nodeName);

                if ((document.getElementById("table").rows[checkbox.attributes.item(this).ownerElement.parentNode.parentNode.rowIndex].cells[2].innerText)!="+"
                    && checkbox.attributes.item(this).ownerElement.parentNode.parentNode.rowIndex != 0) {
                    for (var i = 2; i < (rows.length / 4); i++) {
                        console.log(document.getElementById("table").rows[checkbox.attributes.item(this).ownerElement.parentNode.parentNode.rowIndex].cells[i].innerText);
                        arrayDeleteTable.push((document.getElementById("table").rows[checkbox.attributes.item(this).ownerElement.parentNode.parentNode.rowIndex].cells[i].innerText).replace(","," -"));
                    }
                    if ((document.getElementById("table").rows[checkbox.attributes.item(this).ownerElement.parentNode.parentNode.rowIndex].cells[3].innerText)!="0")
                    sendToDelete();
                }
                //Si btCodeType es numerico, llenamos el arreglo para eliminar en bd.
                if (checkbox.id !="mainCheckbox"){
                    checkbox.parentNode.parentNode.parentNode.removeChild(checkbox.attributes.item(this).ownerElement.parentNode.parentNode);
                }
            });
            console.log(arrayDeleteTable);
            document.getElementById("formDeleteTable").arrayDeleteTable.value = arrayDeleteTable;
            //4 es el numero de filas que hacen parte del head y food de la tabla boostrap.
            //2 es son los campos de la tabla excluidos [(0)input y (1)ActionsPrevious];
            document.getElementById("formDeleteTable").lengthTable.value = ((rows.length / 4) - 2);
            console.log("Value ArrayDeleteTable: " + document.getElementById("formDeleteTable").arrayDeleteTable.value);
            console.log("Value lengthTable: " + document.getElementById("formDeleteTable").lengthTable.value);
            enableButtonTrash(false);
        }
    };
    //new function
    function insertNewTr(iterations, hide){
        for (var i = 1; i <= iterations; i++){
            if (hide){
                document.querySelector("tbody").insertAdjacentHTML("beforeend",`
                        <tr>
                            <td style="display: none"><input type="checkbox"></td>
                            <td style="display: none">+</td>
                            <td style="display: none">+</td>
                            <td>0</td>
                            <td contentEditable >New Description</td>
                            <td contentEditable >New Note</td>
                        </tr>
                    `);
            }else{
                document.querySelector("tbody").insertAdjacentHTML("beforeend",`
                <tr>
                    <td style="display: none"><input type="checkbox"></td>
                    <td style="display: none">+</td>
                    <td style="display: none">+</td>
                    <td>0</td>
                    <td contentEditable ><input type="hidden">New Description</td>
                    <td contentEditable >New Note</td>
                </tr>
            `);
            }
        }
        enableButtonSave(true);
    };
    //new function
    function hideCell(table, cell, hide) {
        var col = cell;
        col = parseInt(col, 10);
        col = col - 1;
        var tbl = document.getElementById(table);
        if (tbl != null) {
            for (var i = 0; i < tbl.rows.length; i++) {//filas
                for (var j = 0; j < tbl.rows[i].cells.length; j++) { //columnas
                    if (j == col){
                        if (hide) {
                            tbl.rows[i].cells[j].style.display = "none";
                            document.querySelectorAll("th")[0].style.display = "none";
                            document.querySelectorAll("tfoot")[1].children[0].cells[0].style.display="none";
                        }
                        else {
                            tbl.rows[i].cells[j].style.display = "block";
                            document.querySelectorAll("th")[0].style.display = "block";
                            document.querySelectorAll("th")[0].style.width = "17.75px";
                            document.querySelectorAll("tfoot")[1].children[0].cells[0].style.display="block";
                        }
                    }
                }
            }
        }
        actionChangeImageButton('ti-check', 'ti-close')
        enableButtonTrash(false);
    };
    //new function
    function actionChangeImageButton(firstImage, secondImage){
        const image = document.getElementById('imageCheck');
        var passe = false;
        if (image.className == firstImage) {
            $('#imageCheck').removeClass(firstImage);
            $('#imageCheck').addClass(secondImage);
            $("#buttonHideCell").removeClass("btn-primary");
            passe = true;
        }
        if (image.className == secondImage && !passe ) {
            $('#imageCheck').removeClass(secondImage);
            $('#imageCheck').addClass(firstImage);
            $("#buttonHideCell").addClass("btn-primary");
        }
    };
    //new function
    function enableButtonSave(option){
        if (option){
            document.getElementById("textSave").innerHTML = "(On) Save";
            document.getElementById("buttonSave").disabled = false;
            $("#buttonSave").addClass("btn-primary");
        } else{
            document.getElementById("textSave").innerHTML = "Updated data";
            document.getElementById("buttonSave").disabled = true;
            $("#buttonSave").removeClass("btn-primary");
        }
    };
    //new function
    function enableButtonTrash(option){
        if (option){
            document.getElementById("textTrash").innerHTML = "(On) Trash";
            document.getElementById("buttonTrash").disabled = false;
            $("#buttonTrash").addClass("btn-danger");
        } else{
            document.getElementById("textTrash").innerHTML = "(Off) Trash";
            document.getElementById("buttonTrash").disabled = true;
            $("#buttonTrash").removeClass("btn-danger");
        }
    };
    //new function
    function sendData() {
        const x = confirm("Are you sure to save the changes?")
        if (x){
            tableReading();
            $("#formTable").submit();
        }
    };
    //new function
    $("#formTable").submit(function (e) {
        e.preventDefault();
        var formTable = $(this);
        $.ajax({
            url: '/objectboard/bussinesstype',
            type: 'POST',
            data: formTable.serialize(),
            success: function (data) { //en data contiene informacion del html.
                enableButtonSave(false);
            }
        });
        return false;
    });
    //new function
    function sendToDelete() {
        console.log("dentro de sendToDelete");
        $("#formDeleteTable").submit();
    };
    //new function
    $("#formDeleteTable").submit(function (e) {
        e.preventDefault();
        console.log("dentro de ajax");
        var formTable = $(this);
        $.ajax({
            url: '/objectboard/bussinesstype',
            type: 'post',
            data: formTable.serialize(),
            success: function (data) { //en data contiene informacion del html.
                enableButtonSave(false);
            }
        });
        return false;
    });
    //new function
    function listenerTable(){
        const rowsTableHead = document.querySelectorAll("th");
        const rows = document.querySelectorAll("tr");
        const cells = document.querySelectorAll("td");
        console.log("Filas Table Head (Rows)(th)");
        console.log(rowsTableHead);
        console.log("Filas (Rows)(tr)");
        console.log(rows);
        console.log("Columnas (Cells)(td)");
        console.log(cells);

    };
    //new function
    /*End################### seccion de funciones ###################*/
</script>

<!--end ############################### section JavaScript ###############################-->

</body>

</html>