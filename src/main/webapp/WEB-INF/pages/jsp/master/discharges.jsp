<%@ page import="com.psg.objectboard.model.own.ownsEntity.classViewDAO.DischargeViewDAO" %>
<%@ page import="com.psg.objectboard.model.own.ownsEntity.classViewVO.DischargeViewVO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.*" %>
<%@ page import="com.psg.objectboard.model.service.Other.OtherConexion" %>
<%@ page import="com.psg.objectboard.model.service.Other.DateFunctions" %>
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
    String data_user = (String)objSesion.getAttribute("dataUser");
    String data_pasword = (String)objSesion.getAttribute("dataPassword");
    String company_number = (String) objSesion.getAttribute("companyNumber");

    DischargeViewDAO cod = new DischargeViewDAO();
    ArrayList<DischargeViewVO> dischar = new ArrayList<DischargeViewVO>();

    OtherConexion cc = null;
    DischargeViewVO sovo = null;
    Connection cn = null;
    ResultSet rs = null;
    Statement cmd = null;
    PreparedStatement pst = null;
    DateFunctions df = new DateFunctions();

    cc = new OtherConexion();
    cn = cc.conectarse(data_user,data_pasword);
    String sql = null;

    if (company_number.equals("1"))
        sql = "SELECT * FROM DischargeView;";
    else
        sql = "SELECT * FROM DischargeView WHERE bussinessUnit_bu_bis_code = " + Long.parseLong(company_number) + ";";

    try{
        pst = cn.prepareStatement(sql);
        rs = pst.executeQuery();
        while (rs.next()){
            sovo = new DischargeViewVO();
            sovo.setDiLicenseNum(rs.getLong(1));
            sovo.setDiStartDate(rs.getString(2));
            sovo.setDiEndDate(rs.getString(3));
            sovo.setDiNumberUser(rs.getInt(4));
            sovo.setDiPermanent(rs.getString(5));
            sovo.setDiSalesCode(rs.getInt(6));
            sovo.setDiLicenseCode(rs.getString(7));
            sovo.setBussinessUnitBuBisCode(rs.getLong(8));
            sovo.setProjectPrIdProject(rs.getLong(9));
            sovo.setBuName(rs.getString(10));
            sovo.setPrName(rs.getString(11));

            if (dischar.isEmpty()){
                dischar.add(0,sovo);
            }else{
                dischar.add(sovo);
            }
        }
        System.out.println("Consulta array exitosa: ");
    }catch (SQLException ex){
        System.out.println("Error en la consulta array: "+ex.getMessage());
    }finally {
        try{
            if (cn != null){
                cn.close();
                pst.close();
                System.out.println("Coneccion cerrada");
            }
        }catch (Exception e){
            System.out.println("Error "+e);
        }
    }
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Discharges</title>
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
                if ( confirm("Do You really want to delete the selected Discharge? ")) {
                    document.forma.action = '/objectboard/dischargesprocess';
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
                alert("There are no discharge selected to remove.");
                return false;
            }
            return true;
        }
        function nuevo_usuario(){
            document.forma.target = "";
            document.forma.action = '/objectboard/discharge';
            document.forma.p_acciones.value = "create";
            document.forma.submit();
        }
        function valida(disch,unid,proj){
            document.forma.p_discharge.value = disch;
            document.forma.p_unidad.value = unid;
            document.forma.p_project.value = proj;
            document.forma.target = "";
            document.forma.p_acciones.value = "save";
            document.forma.action = "/objectboard/discharge";
            document.forma.submit();
        }
        function descargar(disch,ciaNumber){
            if ( confirm("Do You really want to download the selected License Discharge? ")) {
                document.forma.p_discharge.value = disch;
                document.forma.p_cia_number.value = ciaNumber;
                document.forma.target = "";
                document.forma.p_acciones.value = "licence";
                document.forma.action = "/objectboard/generalpdf";
                document.forma.submit();
            }
        }
        function other_pdf(){
            document.forma.action = '/objectboard/generalpdf';
            document.forma.p_acciones.value = "licences";
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
        <jstl:choose>
            <jstl:when test="${ rq_companyNumber.equals('1') }">
                <jstl:set var="jsp_pagePrevious" scope="page" value="/objectboard/mastermenusuper"/>
            </jstl:when>
            <jstl:otherwise>
                <jstl:set var="jsp_pagePrevious" scope="page" value="/objectboard/dashmenusuper"/>
            </jstl:otherwise>
        </jstl:choose>

        <%@include file="../../../../complements/jsp/navbar-header.jsp"%>
        <!--end ############################### navbar-header ###############################-->
        <!-- Contenedor central -->
        <div class="pcoded-main-container">
            <!--start ############################### page-header ###############################-->
            <jstl:set var="jsp_titleFrom" scope="page" value="Discharges List Form"/>
            <jstl:set var="jsp_funtionFrom" scope="page" value="List or select your options ..."/>
            <%@include file="../../../../complements/jsp/page-header.jsp"%>
            <!--end ############################### page-header ###############################-->
            <!--<section id="content">-->
            <div class="container mt-2 mb-2">
                <form class="user" role="form" method="post" action="#!" name="forma" >
                    <input name='p_cuantos' type='hidden' value="<%= dischar.size() %>"/>
                    <input name='p_discharge' type='hidden' value='' />
                    <input name='p_cia_number' type='hidden' value='' />
                    <input name='p_unidad' type='hidden' value='' />
                    <input name='p_project' type='hidden' value='' />
                    <input name="p_acciones" type="hidden" />
                    <input name='p_pantalla' type='hidden' value='discharges' />
                    <table id="other" cellspacing="0" width="100%" border="0">
                        <thead>
                        <tr>
                            <jstl:if test="${ rq_companyNumber.equals('1')}">
                                <button type= "button" id="add"  class= "btn btn-outline-success" onClick=nuevo_usuario() ><i class='fa fa-file-o fa-fw'></i> New</button>
                                &nbsp;
                                <button type= "button" id="remove" class= "btn btn-outline-danger" onClick=borrar_usuario() >
                                    <i class='fa fa-trash-o fa-fw'></i> Trash
                                </button>
                                &nbsp;
                            </jstl:if>
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
                            <jstl:if test="${ rq_companyNumber.equals('1')}">
                                <th data-field="state" data-sortable="false">
                                    <input type='checkbox' onclick='marcar(this);' />
                                </th>
                            </jstl:if>
                            <th data-field="id" data-sortable="true" data-switchable="true">LICENSE No.</th>
                            <th data-field="c1" data-sortable="true" data-switchable="true">START DATE</th>
                            <th data-field="c2" data-sortable="true" data-switchable="true">END DATE</th>
                            <th data-field="c3" data-sortable="true" data-switchable="true">NUMBER USER</th>
                            <th data-field="c4" data-sortable="true" data-switchable="true">PERMANENT</th>
                            <th data-field="c5" data-sortable="true" data-switchable="true">SALES CODE</th>
                            <th data-field="c6" data-sortable="true" data-switchable="true">LICENSE CODE</th>
                            <th data-field="c9" data-sortable="true" data-switchable="true">STATUS</th>
                            <th data-field="action" data-sortable="false">ACTION</th>
                            <th data-field="c8" data-sortable="true" data-switchable="true">PROJECT NAME</th>
                            <th data-field="c7" data-sortable="true" data-switchable="true">UNIT NAME</th>
                        </tr>
                        </thead>
                        <tbody>
                        <jstl:set var="f" value="${0}" scope="page"/>
                        <% for(int x=0; x < dischar.size(); x++){ %>
                        <jstl:set var = "f" value = "${f + 1}" />
                        <tr>
                            <jstl:if test="${ rq_companyNumber.equals('1')}">
                                <td>
                                    <input type='checkbox' id="p_select" name='p_selec_<jstl:out value="${ f }"/>' />
                                </td>
                            </jstl:if>
                            <td>
                                <%=dischar.get(x).getDiLicenseNum()%>
                                <input type='hidden' value='<%=dischar.get(x).getDiLicenseNum()%>'
                                       name='p_cual_l<jstl:out value="${ f }"/>' />&nbsp;
                            </td>
                            <td>
                                <%=df.parseFecha_2(dischar.get(x).getDiStartDate())%>
                            </td>
                            <td>
                                <%=df.parseFecha_2(dischar.get(x).getDiEndDate())%>
                            </td>
                            <td>
                                <%=dischar.get(x).getDiNumberUser()%>
                            </td>
                            <td>
                                <% if ( dischar.get(x).getDiPermanent().equals("T")){ %>
                                Yes
                                <%}else{%>
                                No
                                <%}%>
                            </td>
                            <td>
                                <%=dischar.get(x).getDiSalesCode()%>
                            </td>
                            <td>
                                <%=dischar.get(x).getDiLicenseCode()%>
                            </td>
                            <td>
                                <% if ( df.ComparaFechas(df.fechaFull(9),dischar.get(x).getDiEndDate()) == 1){ %>
                                Invalid
                                <%}else{%>
                                Valid
                                <%}%>
                            </td>
                            <td>
                                <jstl:if test="${ rq_companyNumber.equals('1')}">
                                    <a href='#' title="Edit" onClick=valida(<%=dischar.get(x).getDiLicenseNum()%>,<%=dischar.get(x).getBussinessUnitBuBisCode()%>,<%=dischar.get(x).getProjectPrIdProject()%>)>
                                        <i class='fa fa-edit fa-fw' ></i>
                                    </a>
                                    &nbsp;
                                </jstl:if>
                                <a href='#' title="Download License PDF" onClick=descargar(<%=dischar.get(x).getDiLicenseNum()%>,<%=dischar.get(x).getBussinessUnitBuBisCode() %>)>
                                    <i class='fa fa-cloud-download fa-fw' ></i>
                                </a>
                            </td>
                            <td>
                                <%=dischar.get(x).getPrName()%>
                            </td>
                            <td>
                                <%=dischar.get(x).getBuName()%>
                            </td>
                        </tr>
                        <% } %>
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
<!-- <script type="text/javascript" src="../../../../complements/bootstrap/datatable_custom/main.js"></script> -->
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
                /*{
                    extend:    'pdfHtml5',
                    text:      '<i class="fa fa-list-alt"></i> ',
                    titleAttr: 'Export to PDF',
                    className: 'btn btn-primary'
                },*/
            ]
        });
    });
</script>
</body>

</html>