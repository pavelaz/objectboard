<!DOCTYPE html>
<html lang="en">
<!--start ############################### header ###############################-->
<head>
    <title>Discharge maintenance</title>
    <!-- Favicon icon -->
    <link rel="shortcut icon" type="image/png" href="<%= request.getContextPath() %>/complements/img/favicon1.png">
    <%@include file="../../../../complements/jsp/header.jsp"%>
    <link rel=stylesheet href=https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css />
    <!-- Propias -->
    <script src="<%= request.getContextPath() %>/complements/scripts/validaciones.js" type="text/javascript"></script>
    <script type="text/javascript">

        function Validaciones(){
            if (!valida_largos(document.forma.p_start_d.value.length,"Start Date",10)||
                !valida_largos(document.forma.p_end_d.value.length,"End Date",10)||
                !valida_numeros(document.forma.p_number_user.value,"User Number",1)||
                !valida_numeros(document.forma.p_sale_code.value,"Sales Number",1)||
                !valida_selects(document.forma.p_unit_number.value,"Bussiness Unit","")||
                !valida_selects(document.forma.p_proj_number.value,"Project","")
            ){
                return false;
            }
            return true;
        }

        function registro(){
            if(Validaciones() &&
               otrasValidaciones()){
                document.forma.action="/objectboard/dischargesprocess";
                document.forma.submit();
            }
        }
        function otrasValidaciones() {
            if (compara_dos_fechas(document.forma.p_start_d.value, document.forma.p_end_d.value) == 1){
                alert("The license expiration date must be greater than or equal to the beginning.");
                return false;
            }
            return true;
        }
    </script>
</head>
<!--end ############################### header ###############################-->
<!--ul-->
<body>
<!-- Pre-loader start -->
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
    <div class="pcoded-overlay-box"></div>
    <div class="pcoded-container navbar-wrapper">

        <!--start ############################### navbar ###############################-->
        <jstl:set var="jsp_navBarOption" scope="page" value="${ '8' }"/>
        <%@include file="../../../../complements/jsp/navbar.jsp"%>
        <!--end ############################### navbar ###############################-->

        <!--start ############################### navbar-header ###############################-->
        <jstl:set var="jsp_ShowImage" scope="page" value="${ 'NOT' }"/>
        <!-- Settings = 1, Profile = 2, My Messages = 3, Lock Screen = 4, Logout = 5, pegina anterior 6  -->
        <jstl:set var="jsp_menuOption" scope="page" value="${ '2,6' }"/>
        <jstl:set var="jsp_previousMsg" scope="page" value="Previous page"/>
        <jstl:set var="jsp_pagePrevious" scope="page" value="/objectboard/discharges"/>
        <%@include file="../../../../complements/jsp/navbar-header.jsp"%>
        <!--end ############################### navbar-header ###############################-->

        <div class="pcoded-main-container">
            <div class="pcoded-wrapper">
                <div class="pcoded-content">

                    <!--start ############################### page-header ###############################-->
                    <jstl:set var="jsp_titleFrom" scope="page" value="Discharge Maintenance Form"/>
                    <jstl:set var="jsp_funtionFrom" scope="page" value="Complete your options ..."/>
                    <%@include file="../../../../complements/jsp/page-header.jsp"%>
                    <!--end ############################### page-header ###############################-->

                    <div class="pcoded-inner-content">
                        <div class="main-body">
                            <div class="page-wrapper">
                                <div class="page-body">
                                    <!-- Basic Form Inputs card start -->
                                    <div class="card">
                                        <div class="card-header">
                                            <h5>Discharge properties</h5>
                                        </div>
                                        <div class="card-block">
                                            <h4 class="sub-title">Basic Data</h4>
                                            <form method="post" name="forma" id="uploadFrom" >
                                                <div class="form-group row">
                                                    <div class="col-sm-2">
                                                        <input name="p_acciones" value="${ rq_acciones }" type="hidden">
                                                        <input name='p_pantalla' type='hidden' value='discharges' />
                                                        Discharge :
                                                    </div>
                                                    <div class="col-sm-2">
                                                       <jstl:choose>
                                                            <jstl:when test="${ rq_acciones == 'save' }">
                                                                <input type="text" name="p_license_number" class="form-control form-control-user"
                                                                       value='<jstl:out value="${ rq_dischar }">Lost Value</jstl:out>' readonly>
                                                            </jstl:when>
                                                            <jstl:otherwise>
                                                                <input type="text" name="p_license_number" class="form-control form-control-user" readonly>
                                                            </jstl:otherwise>
                                                        </jstl:choose>
                                                    </div>
                                                    <div class="col-sm-2">
                                                        Start Date:
                                                    </div>
                                                    <div class="col-sm-2">
                                                        <jstl:choose>
                                                            <jstl:when test="${ rq_acciones == 'save' }">
                                                                <input  name="p_start_d" id="datepicker" width="276" value="${ rq_fechaDesde }" readonly/>
                                                            </jstl:when>
                                                            <jstl:otherwise>
                                                                <input  name="p_start_d" id="datepicker" width="276" value="${ rq_fechaHoy }" readonly/>
                                                            </jstl:otherwise>
                                                        </jstl:choose>
                                                    </div>
                                                    <div class="col-sm-1">
                                                        End Date:
                                                    </div>
                                                    <div class="col-sm-2">
                                                        <jstl:choose>
                                                            <jstl:when test="${ rq_acciones == 'save' }">
                                                                <input  name="p_end_d" id="datepicker" width="276" value="${ rq_fechaHasta }" readonly/>
                                                            </jstl:when>
                                                            <jstl:otherwise>
                                                                <input  name="p_end_d" id="datepicker" width="276" value="${ rq_fechaHoy }" readonly/>
                                                            </jstl:otherwise>
                                                        </jstl:choose>
                                                    </div>
                                                    <div class="col-sm-1">

                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <div class="col-sm-2">
                                                        Number Users :
                                                    </div>
                                                    <div class="col-sm-2">
                                                        <jstl:choose>
                                                            <jstl:when test="${ rq_acciones == 'save' }">
                                                                <input type="text" name="p_number_user" class="form-control form-control-user"
                                                                       value='<jstl:out value="${ rq_discharge.get(0).getDiNumberUser() }">Lost Value</jstl:out>' maxlength="3">
                                                            </jstl:when>
                                                            <jstl:otherwise>
                                                                <input type="text" name="p_number_user" class="form-control form-control-user" maxlength="3">
                                                            </jstl:otherwise>
                                                        </jstl:choose>
                                                    </div>
                                                    <div class="col-sm-2">
                                                        Permanent:
                                                    </div>
                                                    <div class="col-sm-2">
                                                        <jstl:choose>
                                                        <jstl:when test="${ rq_acciones == 'save' }">
                                                        <jstl:choose>
                                                        <jstl:when test="${ rq_discharge.get(0).getDiPermanent() =='T' }">
                                                        <input type="radio" id="activeStatus" name="p_permanent" value="T" checked>
                                                        <label for="activeStatus">&nbsp;&nbsp;&nbsp;Yes<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                            <input type="radio" name="p_permanent" value="F">
                                                            <label for="activeStatus">&nbsp;&nbsp;&nbsp;No<abel>
                                                                </jstl:when>
                                                                <jstl:otherwise>
                                                                <input type="radio" id="activeStatus" name="p_permanent" value="T">
                                                                <label for="activeStatus">&nbsp;&nbsp;&nbsp;Yes</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                                <input type="radio" id="inactiveStatus" name="p_permanent" value="F" checked>
                                                                <label for="activeStatus">&nbsp;&nbsp;&nbsp;No</label>
                                                                </jstl:otherwise>
                                                                </jstl:choose>
                                                                </jstl:when>
                                                                <jstl:otherwise>
                                                                <input type="radio" id="activeStatus" name="p_permanent" value="T">
                                                                <label for="activeStatus">&nbsp;&nbsp;&nbsp;Yes</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                                <input type="radio" id="inactiveStatus" name="p_permanent" value="F" checked>
                                                                <label for="activeStatus">&nbsp;&nbsp;&nbsp;No</label>
                                                                </jstl:otherwise>
                                                                </jstl:choose>
                                                    </div>
                                                    <div class="col-sm-1">
                                                        Sale Code:
                                                    </div>
                                                    <div class="col-sm-2">
                                                        <jstl:choose>
                                                         <jstl:when test="${ rq_acciones == 'save' }">
                                                             <input  name="p_sale_code" id="sale_code" width="276" value="${ rq_discharge.get(0).getDiSalesCode() }" maxlength="3"/>
                                                         </jstl:when>
                                                         <jstl:otherwise>
                                                             <input  name="p_sale_code" id="sale_code" width="276" maxlength="3"/>
                                                         </jstl:otherwise>
                                                         </jstl:choose>
                                                    </div>
                                                    <div class="col-sm-1">

                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <div class="col-sm-2">
                                                        License Code :
                                                    </div>
                                                    <div class="col-sm-2">
                                                        <jstl:choose>
                                                            <jstl:when test="${ rq_acciones == 'save' }">
                                                                <input type="text" name="p_license_code" class="form-control form-control-user"
                                                                       value='<jstl:out value="${ rq_discharge.get(0).getDiLicenseCode() }">Lost Value</jstl:out>' readonly>
                                                            </jstl:when>
                                                            <jstl:otherwise>
                                                                <input type="text" name="p_license_code" class="form-control form-control-user"
                                                                       value="${ rq_licenseCode }" readonly>
                                                            </jstl:otherwise>
                                                        </jstl:choose>
                                                    </div>
                                                    <div class="col-sm-2">
                                                        Bussiness Unit:
                                                    </div>
                                                    <div class="col-sm-2">
                                                         <jstl:choose>
                                                                <jstl:when test="${ rq_acciones == 'save' }">
                                                                    <select name="p_unit_number" id="unit_number" class="form-control" >
                                                                        <option value="0" selected>Select Unit</option>
                                                                        <jstl:forEach var="x" begin="0" end="${ rq_units.size() - 1 }" step="1">
                                                                            <jstl:choose>
                                                                            <jstl:when test="${ rq_units.get(x).getBuBisCode() == rq_unidad }">
                                                                                <option selected value='<jstl:out value="${ rq_units.get(x).getBuBisCode()}"/>'>
                                                                                    <jstl:out value="${ rq_units.get(x).getBuName()}"/>
                                                                                </option>
                                                                            </jstl:when>
                                                                            <jstl:otherwise>
                                                                                <option value='<jstl:out value="${ rq_units.get(x).getBuBisCode()}"/>'>
                                                                                    <jstl:out value="${ rq_units.get(x).getBuName()}"/>
                                                                                </option>
                                                                            </jstl:otherwise>
                                                                            </jstl:choose>
                                                                        </jstl:forEach>
                                                                    </select>
                                                                </jstl:when>
                                                                <jstl:otherwise>
                                                                    <select name="p_unit_number" id="unit_number" class="form-control" >
                                                                        <option value="0" selected>Select Unit</option>
                                                                        <jstl:forEach var="x" begin="0" end="${ rq_units.size() - 1 }" step="1">
                                                                            <option value='<jstl:out value="${ rq_units.get(x).getBuBisCode()}"/>'>
                                                                                <jstl:out value="${ rq_units.get(x).getBuName()}"/>
                                                                            </option>
                                                                        </jstl:forEach>
                                                                    </select>
                                                                </jstl:otherwise>
                                                            </jstl:choose>
                                                    </div>
                                                    <div class="col-sm-1">
                                                        Project:
                                                    </div>
                                                    <div class="col-sm-2">
                                                        <jstl:choose>
                                                            <jstl:when test="${ rq_acciones == 'save' }">
                                                                <select name="p_proj_number" id="proj_number" class="form-control" >
                                                                    <option value="0" selected>Select Project</option>
                                                                    <jstl:forEach var="x" begin="0" end="${ rq_projects.size() - 1 }" step="1">
                                                                        <jstl:choose>
                                                                            <jstl:when test="${ rq_projects.get(x).getPrIdProject() == rq_project }">
                                                                                <option selected value='<jstl:out value="${ rq_projects.get(x).getPrIdProject() }"/>'>
                                                                                    <jstl:out value="${ rq_projects.get(x).getPrName() }"/>
                                                                                </option>
                                                                            </jstl:when>
                                                                            <jstl:otherwise>
                                                                                <option value='<jstl:out value="${ rq_projects.get(x).getPrIdProject() }"/>'>
                                                                                    <jstl:out value="${ rq_projects.get(x).getPrName() }"/>
                                                                                </option>
                                                                            </jstl:otherwise>
                                                                        </jstl:choose>
                                                                    </jstl:forEach>
                                                                </select>
                                                            </jstl:when>
                                                            <jstl:otherwise>
                                                                <select name="p_proj_number" id="proj_number" class="form-control" >
                                                                <option value="0" selected>Select Project</option>
                                                                <jstl:forEach var="x" begin="0" end="${ rq_projects.size() - 1 }" step="1">
                                                                    <option value='<jstl:out value="${ rq_projects.get(x).getPrIdProject()}"/>'>
                                                                        <jstl:out value="${ rq_projects.get(x).getPrName()}"/>
                                                                    </option>
                                                                </jstl:forEach>
                                                                </select>
                                                            </jstl:otherwise>
                                                        </jstl:choose>
                                                    </div>
                                                    <div class="col-sm-1">

                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                    <!-- Basic Form Inputs card end -->
                                </div>
                            </div>
                            <div class="container">
                                <div class="form-group row">
                                    <div class="col-lg-4">
                                        &nbsp;&nbsp;
                                    </div>
                                    <div class="col-lg-4">
                                        <a href="#" onClick="registro()" class="btn btn-primary btn-user btn-block">
                                            <jstl:choose>
                                                <jstl:when test="${ rq_acciones =='create' }">
                                                    Create
                                                </jstl:when>
                                                <jstl:otherwise>
                                                    Save
                                                </jstl:otherwise>
                                            </jstl:choose>
                                        </a>
                                    </div>
                                    <div class="col-lg-4">
                                        &nbsp;    &nbsp;
                                    </div>
                                </div>

                                <jstl:forEach var="x" begin="1" end="${ 5 }" step="1">
                                    <div class="form-group row">
                                        <div class="col-sm-12 mb-6 mb-sm-0">
                                            &nbsp;
                                        </div>
                                    </div>
                                </jstl:forEach>
                            </div>
                            <!--start ############################### footer ###############################-->
                            <%@include file="../../../../complements/jsp/footer.jsp"%>
                            <!--end ############################### footer ###############################-->
                        </div>
                    </div>
                </div>
                <div id="styleSelector"></div>
            </div>
        </div>
    </div>
</div>
<!-- Warning Section Starts -->
<%@include file="../../../../complements/jsp/warning_section.jsp"%>
<!-- Warning Section Ends -->
<!--start ############################### footer_basic_script #############################-->
<%@include file="../../../../complements/jsp/footer_basic_script.jsp"%>
<script type=text/javascript src="<%= request.getContextPath() %>/complements/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
        var date_input=$('input[name="p_start_d"]'); //our date input has the name "p_datexpires"
        var container=$('.bootstrap-iso form').length>0 ? $('.bootstrap-iso form').parent() : "body";
        date_input.datepicker({
            format: 'mm/dd/yyyy',
            container: container,
            todayHighlight: true,
            autoclose: true,
        })

        var date_input=$('input[name="p_end_d"]'); //our date input has the name "p_datexpires"
        var container=$('.bootstrap-iso form').length>0 ? $('.bootstrap-iso form').parent() : "body";
        date_input.datepicker({
            format: 'mm/dd/yyyy',
            container: container,
            todayHighlight: true,
            autoclose: true,
        })
    })
</script>
<!--end ############################### footer_basic_script ###############################-->
</body>

</html>
