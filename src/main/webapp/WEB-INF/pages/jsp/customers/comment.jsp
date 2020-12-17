<%@ page import="com.psg.objectboard.model.own.ownsEntity.classVO.OtherVO.CommentsTypeVO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.psg.objectboard.model.own.ownsEntity.classDAO.OtherDAO.CommentsTypeDAO" %>
<%@ page import="com.psg.objectboard.model.service.Other.SqlFunctions" %>
<%@ page import="java.sql.*" %>
<%@ page import="com.psg.objectboard.model.service.Other.OtherConexion" %>
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
    String company_number = (String)objSesion.getAttribute("companyNumber");
    String data_user = (String)objSesion.getAttribute("dataUser");
    String data_pasword = (String)objSesion.getAttribute("dataPassword");

    String acciones = "consult";
    if(request.getParameter("p_acciones")!=null) {
        acciones = request.getParameter("p_acciones");
    }

    String code = null;
    String name = null;
    String type = "0";
    String refes = null;
    if (acciones.equals("save")){
        if(request.getParameter("p_code")!=null) {
            code = request.getParameter("p_code");
        }
        if(request.getParameter("p_names")!=null) {
            name = request.getParameter("p_names");
        }
        if(request.getParameter("p_types")!=null) {
            type = request.getParameter("p_types");
        }
        if(request.getParameter("p_refes")!=null) {
            refes = request.getParameter("p_refes");
        }
    }

    ArrayList<CommentsTypeVO> comment_type = null;
    if (acciones.equals("create") || acciones.equals("save") ) {
        CommentsTypeDAO ct = new CommentsTypeDAO();
        comment_type = ct.getCommentsType();
    }
    Integer f = 0;

    OtherConexion cc = new OtherConexion();
    System.out.println("User: " + data_user + " Password: " + data_pasword);
    Connection cn = cc.conectarse(data_user,data_pasword);
    SqlFunctions sqls = new SqlFunctions();

    String sql = null;
    sql = sqls.get_select("comments", "*","bussinessUnit_bu_bis_code = "+company_number,"","","");
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Comments Files</title>
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
            <jstl:when test="${ rq_acciones.equals('consult') }">
                function nuevo_registro(){
                    document.forma.target = "";
                    document.forma.action = '/objectboard/comment';
                    document.forma.p_acciones.value = "create";
                    document.forma.submit();
                }
            </jstl:when>
            <jstl:when test="${ rq_acciones.equals('create') }">
                function nuevo_registro(){
                    if (varias_validaciones()) {
                        document.forma.target = "";
                        document.forma.action = '/objectboard/commentprocess';
                        document.forma.p_acciones.value = "create";
                        document.forma.submit();
                    }
                }
                function cancelar(){
                    document.forma.target = "";
                    document.forma.action = '/objectboard/comment';
                    document.forma.p_acciones.value = "consult";
                    document.forma.submit();
                }
                function varias_validaciones() {
                    if (!valida_textos(document.forma.p_name.value,"Comments Description",",.") ||
                        !valida_largos(document.forma.p_name.value.length,"Comments Description",2) ||
                        !valida_textos(document.forma.p_refe.value,"Comments Reference","") ||
                        !valida_largos(document.forma.p_refe.value.length,"Comments Reference",2) ||
                        !valida_selects(document.forma.p_type.value,"Comment Type","")
                    ){
                        return false;
                    }
                    return true;
                }
            </jstl:when>
            <jstl:when test="${ rq_acciones.equals('save') }">
                function update_registro(){
                    if (varias_validaciones()) {
                        document.forma.target = "";
                        document.forma.action = '/objectboard/commentprocess';
                        document.forma.p_acciones.value = "save";
                        document.forma.submit();
                    }
                }
                function cancelar(){
                    document.forma.target = "";
                    document.forma.action = '/objectboard/comment';
                    document.forma.p_acciones.value = "consult";
                    document.forma.submit();
                }
                function varias_validaciones() {
                    if (!valida_textos(document.forma.p_name.value,"Comments Description",",.") ||
                        !valida_largos(document.forma.p_name.value.length,"Comments Description",2) ||
                        !valida_textos(document.forma.p_refe.value,"Comments Reference","") ||
                        !valida_largos(document.forma.p_refe.value.length,"Comments Reference",2) ||
                        !valida_selects(document.forma.p_type.value,"Comment Type","")
                    ){
                        return false;
                    }
                    return true;
                }
            </jstl:when>
        </jstl:choose>
        function borrar_registro(){
            if(validaItems(this)){
                if ( confirm("Do You really want to delete the selected Comments?")) {
                    document.forma.action = '/objectboard/commentprocess';
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
                alert("There are no Comments selected to remove.");
                return false;
            }
            return true;
        }
        function valida(cod,nam,tip,ref){
            document.forma.p_code.value = cod;
            document.forma.p_names.value = nam.replace("-"," ");
            document.forma.p_types.value = tip;
            document.forma.p_refes.value = ref.replace("-"," ");
            document.forma.target = "";
            document.forma.p_acciones.value = "save";
            document.forma.action = "/objectboard/comment";
            document.forma.submit();
        }
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
            <jstl:set var="jsp_titleFrom" scope="page" value="Comments List Form"/>
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
                                    <label for="type" class="col-form-label">Comment Type :</label>
                                </div>
                                <div class="col-sm-2">
                                    <select name="p_type" id="type" class="form-control">
                                        <% for(int x=0; x < comment_type.size(); x++){ %>
                                        <% if (comment_type.get(x).getCt_id() == 0){ %>
                                        <option value="<%= comment_type.get(x).getCt_id() %>" selected><%= comment_type.get(x).getCt_description() %></option>
                                        <%}else{%>
                                        <option value="<%= comment_type.get(x).getCt_id() %>"><%= comment_type.get(x).getCt_description() %></option>
                                        <%}%>
                                        <% } %>
                                    </select>
                                </div>
                                <div class="col-sm-2">
                                    <label for="refe" class="col-form-label">Reference :</label>
                                </div>
                                <div class="col-sm-2">
                                    <input name="p_refe" id="refe" maxlength="10" type="text"
                                           class="form-control" value="${ rq_refe }" onfocus="selecciona_contenido(this)"  >

                                </div>
                                <div class="col-sm-4">
                                    <label for="name">Text description :</label>
                                    <textarea id="name" name="p_name" rows="3" placeholder="Write here the text to place as description." maxlength="180"
                                              cols="35" onfocus="selecciona_contenido(this)"></textarea>
                                </div>
                            </div>
                            <hr>
                        </jstl:when>
                        <jstl:when test="${ rq_acciones.equals('save') }">
                            <div class="form-group row">
                                <div class="col-sm-2">
                                    <label for="type2" class="col-form-label">Comment Type :</label>
                                </div>
                                <div class="col-sm-2">
                                    <select name="p_type" id="type2" class="form-control">
                                        <% for(int x=0; x < comment_type.size(); x++){ %>
                                            <% assert type != null;
                                                if (comment_type.get(x).getCt_id() == Integer.parseInt(type)){ %>
                                                <option value="<%= comment_type.get(x).getCt_id() %>" selected><%= comment_type.get(x).getCt_description() %></option>
                                            <%}else{%>
                                                <option value="<%= comment_type.get(x).getCt_id() %>"><%= comment_type.get(x).getCt_description() %></option>
                                            <%}%>
                                        <% } %>
                                    </select>
                                </div>
                                <div class="col-sm-2">
                                    <label for="refe2" class="col-form-label">Reference :</label>
                                </div>
                                <div class="col-sm-2">
                                    <input name="p_refe" id="refe2" maxlength="10" type="text"
                                           class="form-control" value="<%= refes %>" onfocus="selecciona_contenido(this)"  >

                                </div>
                                <div class="col-sm-4">
                                    <label for="name2">Text description :</label>
                                    <textarea id="name2" name="p_name" rows="3" placeholder="Write here the text to place as description." maxlength="180"
                                              cols="35" onfocus="selecciona_contenido(this)" ><%= name %></textarea>
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
                                    <button type= "button" id="add" title="Create New Comment" class= "btn btn-outline-success" onClick=nuevo_registro() ><i class='fa fa-file-o fa-fw'></i> New</button>
                                    &nbsp;
                                    <button type= "button" id="remove" title="Delete Comments" class= "btn btn-outline-danger" onClick=borrar_registro() >
                                        <i class='fa fa-trash-o fa-fw'></i> Trash
                                    </button>
                                </tr>
                            </jstl:when>
                            <jstl:when test="${ rq_acciones.equals('create') }">
                                <tr>
                                    <button type= "button" id="add1" title="Create New Comment" class= "btn btn-outline-success" onClick=nuevo_registro() >
                                        <i class='fa fa-file-o fa-fw'></i> Create</button>
                                    &nbsp;
                                    <button type= "button" id="remove1" title="Delete Comments" class= "btn btn-outline-danger" onClick=borrar_registro() >
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
                                    <button type= "button" id="add2" title="Update Comment" class= "btn btn-outline-success" onClick=update_registro() >
                                        <i class='fa fa-file-o fa-fw'></i> Save
                                    </button>
                                    &nbsp;
                                    <button type= "button" id="remove2" title="Delete Comments" class= "btn btn-outline-danger" onClick=borrar_registro() >
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
                            <th data-field="state" data-valign="middle" data-align="center">
                                <input type='checkbox' onclick='marcar(this);' />
                            </th>
                            <th data-field="l1" data-sortable="true" data-switchable="false">CODE</th>
                            <th data-field="l2" data-sortable="true" data-switchable="true" data-filter-control="input">TYPE</th>
                            <th data-field="l2" data-sortable="true" data-switchable="true" data-filter-control="input">DESCRIPTIONS</th>
                            <th data-field="l2" data-sortable="true" data-switchable="true" data-filter-control="input">REFERENCE</th>
                            <th data-field="ac" data-sortable="false" data-valign="middle" data-align="center">ACTION</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                        try{
                           PreparedStatement pst = cn.prepareStatement(sql);
                           ResultSet rs = pst.executeQuery();
                                while (rs.next()){
                                   f = f + 1; %>
                                   <tr>
                                        <td>
                                            <input type='checkbox' id="p_select" name='p_selec_<%= f %>' />
                                        </td>
                                        <td>
                                            <%= rs.getLong(1) %>
                                            <input type='hidden' value='<%= rs.getLong(1) %>' name='p_cual_1<%= f %>' id="cual_1" />&nbsp;
                                        </td>
                                        <td>
                                            <% if ( rs.getString(3).equals("1")) %>Legend
                                            <% if ( rs.getString(3).equals("2")) %>Exception
                                            <% if ( rs.getString(3).equals("3")) %>Notes
                                            <% if ( rs.getString(3).equals("4")) %>Terms
                                        </td>
                                        <td>
                                            <%= rs.getString(4) %>
                                        </td>
                                        <td>
                                            <%= rs.getString(5) %>
                                        </td>
                                        <td>
                                            <a href='#' title="Edit"
                                                onClick=valida(<%= rs.getLong(1) %>,'<%= rs.getString(4).replace(" ","-") %>',<%= rs.getString(3)%>,'<%= rs.getString(5).replace(" ","-") %>') >
                                                <i class='fa fa-edit fa-fw' ></i>
                                            </a>
                                        </td>
                                   </tr>
                                <% } %>
                                <% if (f == 0){ %>
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
                                        Not data
                                    </td>
                                    <td>
                                        Not data
                                    </td>
                                    <td>
                                        Not Action
                                    </td>
                                </tr>
                                <% } %>
                        <%
                        }catch (SQLException ex){
                            System.out.println("Error en la consulta array: "+ex.getMessage());
                        }finally {
                            try{
                                if (cn != null){
                                    cn.close();
                                //pst.close();
                                    System.out.println("Conexion cerrada");
                                }
                            }catch (Exception e){
                                System.out.println("Error "+e);
                            }
                        }
                        %>
                        </tbody>
                    </table>
                    <input name='p_cuantos' type='hidden' value='<%= f %>' />
                    <input name='p_code' type='hidden' value='<%= code %>' />
                    <input name='p_names' type='hidden' value='<%= name %>' />
                    <input name='p_types' type='hidden' value='<%= type %>' />
                    <input name='p_refes' type='hidden' value='<%= refes %>' />
                    <input name='p_pantalla' type='hidden' value='comments' />
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