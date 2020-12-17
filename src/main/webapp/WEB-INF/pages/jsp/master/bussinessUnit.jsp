<%@ page import="com.psg.objectboard.model.own.ownsEntity.classDAO.CountryDAO" %>
<%@ page import="com.psg.objectboard.model.own.ownsEntity.classVO.CountryVO" %>
<%@ page import="com.psg.objectboard.model.own.ownsEntity.classDAO.StateDAO" %>
<%@ page import="com.psg.objectboard.model.own.ownsEntity.classVO.StateVO" %>
<%@ page import="com.psg.objectboard.model.own.ownsEntity.classDAO.CityDAO" %>
<%@ page import="com.psg.objectboard.model.own.ownsEntity.classVO.CityVO" %>
<%@ page import="java.util.ArrayList" %><%--
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

    Integer country_number = 0;
    if(request.getParameter("p_country_number") != null &&
            request.getParameter("p_country_number") != ""){
        country_number=Integer.parseInt(request.getParameter("p_country_number"));
    }
    Integer state_number = 0;
    if(request.getParameter("p_state_number") != null &&
            request.getParameter("p_state_number") != ""){
        state_number=Integer.parseInt(request.getParameter("p_state_number"));
    }
    Integer city_number = 0;
    if(request.getParameter("p_city_number") != null &&
            request.getParameter("p_city_number") != ""){
        city_number=Integer.parseInt(request.getParameter("p_city_number"));
    }

    String condicion = null;
    CountryDAO cod = new CountryDAO();
    ArrayList<CountryVO> paises;
    cod.setDataUser(data_user);
    cod.setDataPassword(data_pasword);
    StateDAO sod = new StateDAO();
    ArrayList<StateVO> states = new ArrayList<StateVO>();
    sod.setDataUser(data_user);
    sod.setDataPassword(data_pasword);
    CityDAO cid = new CityDAO();
    ArrayList<CityVO> cities = new ArrayList<CityVO>();
    cid.setDataUser(data_user);
    cid.setDataPassword(data_pasword);
    paises = cod.getListCountries("");

    if (country_number == 0){
        state_number=0;
        city_number=0;
        condicion = "country_co_country_code = 0";
        states = sod.getListStates(condicion);
        condicion = "states_st_state_code = 0 AND states_country_co_country_code = 0";
        cities = cid.getListCities(condicion);
    }else{
        condicion = "country_co_country_code = " + country_number;
        states = sod.getListStates(condicion);
        if (state_number==0){
            city_number=0;
            condicion = "states_st_state_code = 0 AND states_country_co_country_code = " + country_number;
            cities = cid.getListCities(condicion);
        }else{
            condicion = "states_st_state_code = " + state_number +" AND states_country_co_country_code = " + country_number;
            cities = cid.getListCities(condicion);
        }
    }
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Conduct Survey Files</title>
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
    <link rel=stylesheet href=https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css />
    <script src="<%= request.getContextPath() %>/complements/scripts/validaciones.js" type="text/javascript"></script>
    <!-- relacionadas con tablas -->
    <link href="<%= request.getContextPath() %>/complements/css/bootstrap-table.min.css" rel="stylesheet">
    <link href="<%= request.getContextPath() %>/complements/css/bootstrap-table-filter-control.min.css" rel="stylesheet">
    <script type="text/javascript">

        function Validaciones(){
            if (!valida_textos(document.forma.p_unit_name.value,"Bussines Unit name","")||
                !valida_largos(document.forma.p_unit_name.value.length,"Bussines Unit name",3)||
                !valida_textos(document.forma.p_fed_number.value,"Federal Number","")||
                !valida_largos(document.forma.p_fed_number.value.length,"Federal Number",3)||
                !valida_textos(document.forma.p_pro_number.value,"Provincial Number","")||
                !valida_largos(document.forma.p_pro_number.value.length,"Provincial Number",3)||
                !valida_email(document.forma.p_email.value,"email")||
                !valida_numeros(document.forma.p_phone.value,"Phone Number",1)||
                !valida_largos(document.forma.p_zipcode.value.length,"ZipCode",6)||
                !valida_textos(document.forma.p_zipcode.value,"ZipCode","")||
                !valida_largos(document.forma.p_supercode.value.length,"Super Code",6)||
                !valida_textos(document.forma.p_supercode.value,"Super Code","")||
                !valida_largos(document.forma.p_address.value.length,"Address",3)||
                !valida_textos(document.forma.p_address.value,"Address","")||
                !valida_largos(document.forma.p_webpage.value.length,"Web page",3)||
                !valida_textos(document.forma.p_webpage.value,"Web page",".")||
                !valida_selects(document.forma.p_type.value,"type","")||
                !valida_selects(document.forma.p_country_number.value,"country","")||
                !valida_selects(document.forma.p_state_number.value,"state","")||
                !valida_selects(document.forma.p_city_number.value,"city","")||
                !valida_imagen()
            ){
                return false;
            }
            return true;
        }
        function valida_imagen(){
            if(document.forma.p_file_sta.value === "F"){
                alert("The image exceeds the allowed size parameters.");
                return false;
            }
            return true;
        }
        function combo_country() {
            document.forma.action="/objectboard/bussinessunit";
            document.forma.submit();
        }
        function registro(){
            //alert("Paso :");
            if(Validaciones()){
                //document.forma.action="/objectboard/bussinessunitsprocess";
                document.forma.action="/objectboard/multipartconfigservlet";
                document.forma.submit();
            }
        }
        function filePreview(input) {
            var oldPhoto = document.getElementById('oldPhoto');
            //var filePath = input.value;
            //var allowedExtensions = /(\.jpg|\.png)$/i;
            /*if (!allowedExtensions.exec(filePath)) {
                alert("Please upload file having extensions .jpg/ .png/ only.");
                input.value = "";
                return false;
            }else{*/
                if (input.files[0].size > (1024 * 1024 * 10)){ // 1024 * 1024 * 10,= 10 MB)
                    alert("Please upload a photo not exceeding 10 Mb.");
                    input.value = "";
                    return false;
                }
                else{
                    if (input.files && input.files[0]) {
                        var reader = new FileReader();
                        var image = new Image();
                        reader.onload = function (e) {
                            image.src = e.target.result;
                            //Validate the File Height and Width.
                            image.onload = function (){
                                var height = this.height;
                                var width = this.width;
                                if ( 432 > height &&  432 > width){
                                    oldPhoto.remove();
                                    $('#file').before('<img id="oldPhoto" name="oldPhoto" class="img-thumbnail" alt="Photo Profile" src="' + e.target.result + '" width="120" height="160" /> &nbsp');
                                    document.forma.p_file_sta.value = "T";
                                    return false;
                                }
                                alert("The uploaded image is taller or wider than allowed.\nThe maximum valid values are 432 x 432 px.");
                                oldPhoto.remove();
                                $('#file').before('<img id="oldPhoto" name="oldPhoto" class="img-thumbnail" alt="Photo Profile" src="' + e.target.result + '" width="120" height="160" /> &nbsp');
                                document.forma.p_file_sta.value = "F";
                                return true;
                            }
                        }
                        reader.readAsDataURL(input.files[0]);
                    }
                }
            //}
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
        <!-- Settings = 1, Profile = 2, My Messages = 3, Lock Screen = 4, Logout = 5, pegina anterior 6  -->
        <jstl:set var="jsp_menuOption" scope="page" value="${ '2,6' }"/>
        <jstl:set var="jsp_previousMsg" scope="page" value="Previous page"/>
        <jstl:set var="jsp_pagePrevious" scope="page" value="/objectboard/bussinessunits"/>
        <%@include file="../../../../complements/jsp/navbar-header.jsp"%>
        <!--end ############################### navbar-header ###############################-->
        <!-- Contenedor central -->
        <div class="pcoded-main-container">
            <!--start ############################### page-header ###############################-->
            <jstl:set var="jsp_titleFrom" scope="page" value="Bussiness Unit Maintenance Form"/>
            <jstl:set var="jsp_funtionFrom" scope="page" value="Complete your options ..."/>
            <%@include file="../../../../complements/jsp/page-header.jsp"%>
            <!--end ############################### page-header ###############################-->
            <!--<section id="content">-->
            <div class="container mt-2 mb-2">
                <form class="user" role="form" method="post" action="#!" name="forma" enctype="multipart/form-data">
                    <div class="form-group row">
                        <label for="file" class="col-sm-2 col-form-label">Upload Logo Image:</label>
                        <div class="col-sm-8" align="right" name="div-photo" id="div-photo">
                            <jstl:choose>
                                <jstl:when test="${ rq_unitLogoName != null }">
                                    <img name="oldPhoto" title="preferably 216 x 216 pixels" id="oldPhoto"
                                         src="<%= request.getContextPath() %>${ rq_unitLogoDir }${ rq_unitLogoName }"
                                         class="img-thumbnail" alt="Logo Profile" width="120" height="160">
                                    <input type="file" name="p_file" id="file" accept=".png,.jpeg,.jpg,.bmp,.gif"
                                           class="image-cropper-container" onchange="filePreview(this)">
                                    <input type="hidden" name="p_file_sta" value="T" disabled>
                                    <input type="hidden" name="p_file_ant" value="${ rq_unitLogoNameAnt }" >
                                </jstl:when>
                                <jstl:otherwise>
                                    <img name="oldPhoto" title="preferably 216 x 216 pixels" id="oldPhoto"
                                         src="<%= request.getContextPath() %>${ rq_unitLogoDir }/complements/img/no_logos.png"
                                         class="img-thumbnail" alt="Logo Profile" width="120" height="160">
                                    <input type="file" name="p_file" id="file" accept=".png,.jpeg,.jpg,.bmp,.gif"
                                           class="image-cropper-container" onchange="filePreview(this)">
                                    <input type="hidden" name="p_file_sta" value="T" disabled>
                                </jstl:otherwise>
                            </jstl:choose>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-1">
                            <label class="col-form-label">Unit Name:</label>
                        </div>
                        <div class="col-sm-3">
                            <jstl:choose>
                                <jstl:when test="${ rq_unitName != null}">
                                    <input type="text" name="p_unit_name" class="form-control form-control-user"
                                           value='<jstl:out value="${ rq_unitName }">Lost Value</jstl:out>'>
                                </jstl:when>
                                <jstl:otherwise>
                                    <input type="text" name="p_unit_name" class="form-control form-control-user">
                                </jstl:otherwise>
                            </jstl:choose>
                            <input type="hidden" name="p_unit"
                                   value='<jstl:out value="${ rq_unitNumber }">Lost Value</jstl:out>'>
                        </div>
                        <div class="col-1">
                            <label class="col-form-label">Federal No.</label>
                        </div>
                        <div class="col-2">
                            <jstl:choose>
                                <jstl:when test="${ rq_fedNumber != null}">
                                    <input type="text" name="p_fed_number" class="form-control form-control-user" maxlength="10"
                                           value='<jstl:out value="${ rq_fedNumber }">Lost Value</jstl:out>'>
                                </jstl:when>
                                <jstl:otherwise>
                                    <input type="text" name="p_fed_number" class="form-control form-control-user" maxlength="10">
                                </jstl:otherwise>
                            </jstl:choose>
                        </div>
                        <div class="col-2">
                            <label class="col-form-label">Provincial No.</label>
                        </div>
                        <div class="col-3">
                            <jstl:choose>
                                <jstl:when test="${ rq_proNumber != null }">
                                    <input type="text" name="p_pro_number" class="form-control form-control-user" maxlength="16"
                                           value='<jstl:out value="${ rq_proNumber }">Lost Value</jstl:out>'>
                                </jstl:when>
                                <jstl:otherwise>
                                    <input type="text" name="p_pro_number" class="form-control form-control-user" maxlength="16">
                                </jstl:otherwise>
                            </jstl:choose>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-1">
                            <label class="col-form-label">Email:</label>
                        </div>
                        <div class="col-sm-3">
                            <jstl:choose>
                                <jstl:when test="${ rq_unitEmail != null }">
                                    <input type="email" name="p_email" class="form-control form-control-user" maxlength="60"
                                           value='<jstl:out value="${ rq_unitEmail }">Lost Value</jstl:out>'>
                                </jstl:when>
                                <jstl:otherwise>
                                    <input type="email" name="p_email" class="form-control form-control-user" maxlength="60">
                                </jstl:otherwise>
                            </jstl:choose>
                        </div>
                        <div class="col-1">
                            <label class="col-form-label">Status:</label>
                        </div>
                        <div class="col-3">
                            <jstl:choose>
                                <jstl:when test="${ rq_unitStatus == 'T' }">
                                    <input type="radio" id="activeStatus" name="p_status" value="T" checked>
                                    <label for="activeStatus">&nbsp;&nbsp;&nbsp;Active</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <input type="radio" id="inactiveStatus" name="p_status" value="F">
                                    <label for="activeStatus">&nbsp;&nbsp;&nbsp;Inactive</label>
                                </jstl:when>
                                <jstl:otherwise>
                                    <input type="radio" id="activeStatus" name="p_status" value="T">
                                    <label for="activeStatus">&nbsp;&nbsp;&nbsp;Active</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <input type="radio" id="inactiveStatus" name="p_status" value="F" checked>
                                    <label for="activeStatus">&nbsp;&nbsp;&nbsp;Inactive</label>
                                </jstl:otherwise>
                            </jstl:choose>
                        </div>
                        <div class="col-1">
                            <label class="col-form-label">Phone:</label>
                        </div>
                        <div class="col-sm-3">
                            <jstl:choose>
                                <jstl:when test="${ rq_unitPhone != null }">
                                    <input type="text" name="p_phone" class="form-control form-control-user" maxlength="13"
                                           value='<jstl:out value="${ rq_unitPhone }">Lost Value</jstl:out>'>
                                </jstl:when>
                                <jstl:otherwise>
                                    <input type="text" name="p_phone" class="form-control form-control-user" maxlength="13">
                                </jstl:otherwise>
                            </jstl:choose>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-1">
                            <label class="col-form-label">Zip Code:</label>
                        </div>
                        <div class="col-sm-3">
                            <jstl:choose>
                                <jstl:when test="${ rq_unitZipcode != null }">
                                    <input type="text" name="p_zipcode" class="form-control form-control-user" maxlength="6"
                                           value='<jstl:out value="${ rq_unitZipcode }">Lost Value</jstl:out>'>
                                </jstl:when>
                                <jstl:otherwise>
                                    <input type="text" name="p_zipcode" class="form-control form-control-user" maxlength="6">
                                </jstl:otherwise>
                            </jstl:choose>
                        </div>
                        <div class="col-1">
                            <label class="col-form-label">Super Code:</label>
                        </div>
                        <div class="col-3">
                            <input type="text" name="p_supercode" class="form-control form-control-user" maxlength="12"
                                   value='<jstl:out value="${ rq_unitSupercode }">Lost Value</jstl:out>' readonly>
                        </div>
                        <div class="col-1">
                            <label class="col-form-label">Type:</label>
                        </div>
                        <div class="col-sm-3">
                            <select name="p_type" id="type" class="form-control">
                                <jstl:choose>
                                    <jstl:when test="${ rq_unitType != null }">
                                        <option selected value='0'>Select Type</option>;
                                        <jstl:forEach var="x" begin="0" end="${ rq_tipos.size() - 1 }" step="1">
                                            <jstl:choose>
                                                <jstl:when test="${ rq_tipos.get(x).getBtCodeType() == rq_unitType }">
                                                    <option value='<jstl:out value="${ rq_tipos.get(x).getBtCodeType() }"/>' selected>
                                                        <jstl:out value="${ rq_tipos.get(x).getBtDescription()}"/></option>;
                                                </jstl:when>
                                                <jstl:otherwise>
                                                    <option value='<jstl:out value="${ rq_tipos.get(x).getBtCodeType() }"/>'>
                                                        <jstl:out value="${ rq_tipos.get(x).getBtDescription()}"/></option>;
                                                </jstl:otherwise>
                                            </jstl:choose>
                                        </jstl:forEach>
                                    </jstl:when>
                                    <jstl:otherwise>
                                        <option selected value='0'>Select Type</option>;
                                        <jstl:forEach var="x" begin="0" end="${ rq_tipos.size() - 1 }" step="1">
                                            <option value='<jstl:out value="${ rq_tipos.get(x).getBtCodeType() }"/>'>
                                                <jstl:out value="${ rq_tipos.get(x).getBtDescription()}"/></option>;
                                        </jstl:forEach>
                                    </jstl:otherwise>
                                </jstl:choose>
                            </select>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-1">
                            <label class="col-form-label">Web Page:</label>
                        </div>
                        <div class="col-sm-3">
                            <jstl:choose>
                                <jstl:when test="${ rq_unitWebpage != null }">
                                    <input type="text" name="p_webpage" class="form-control form-control-user" maxlength="50"
                                           value='<jstl:out value="${ rq_unitWebpage }">Lost Value</jstl:out>'>
                                </jstl:when>
                                <jstl:otherwise>
                                    <input type="text" name="p_webpage" class="form-control form-control-user" maxlength="50">
                                </jstl:otherwise>
                            </jstl:choose>
                        </div>
                        <div class="col-sm-2">
                            <select name="p_country_number" id="i_country" class="form-control" onchange="combo_country()">
                                <% if(country_number == 0 ){ %>
                                <option value="0" selected>Select Country</option>
                                <% for(int x=0; x < paises.size(); x++){ %>
                                <option value="<%= paises.get(x).getCoCountryCode()%>"><%=paises.get(x).getCoName() %></option>
                                <% } %>
                                <%}else{%>
                                <option value="0">Select Country</option>
                                <% for(int x=0; x < paises.size(); x++){ %>
                                <% if (paises.get(x).getCoCountryCode() == country_number){ %>
                                <option value="<%=paises.get(x).getCoCountryCode()%>" selected><%=paises.get(x).getCoName() %></option>
                                <%}else{%>
                                <option value="<%=paises.get(x).getCoCountryCode()%>"><%=paises.get(x).getCoName() %></option>
                                <%}%>
                                <% } %>
                                <%}%>
                            </select>
                        </div>
                        <div class="col-sm-3">
                            <select name="p_state_number" id="i_state" class="form-control" onchange="combo_country()">
                                <% if(state_number == 0 ){ %>
                                <option value="0" selected>Select State</option>
                                <% for(int x=0; x < states.size(); x++){ %>
                                <option value="<%= states.get(x).getStStateCode()%>"><%=states.get(x).getStName() %></option>
                                <% } %>
                                <%}else{%>
                                <option value="0">Select State</option>
                                <% for(int x=0; x < states.size(); x++){ %>
                                <% if (states.get(x).getStStateCode() == state_number){ %>
                                <option value="<%=states.get(x).getStStateCode()%>" selected><%=states.get(x).getStName() %></option>
                                <%}else{%>
                                <option value="<%=states.get(x).getStStateCode()%>"><%=states.get(x).getStName() %></option>
                                <%}%>
                                <% } %>
                                <%}%>
                            </select>
                        </div>
                        <div class="col-sm-3">
                            <select name="p_city_number" id="i_city" class="form-control">
                                <% if(city_number == 0 ){ %>
                                <option value="0" selected>Select City</option>
                                <% for(int x=0; x < cities.size(); x++){ %>
                                <option value="<%= cities.get(x).getCiCityCode()%>"><%=cities.get(x).getCiName() %></option>
                                <% } %>
                                <%}else{%>
                                <option value="0">Select City</option>
                                <% for(int x=0; x < cities.size(); x++){ %>
                                <% if (cities.get(x).getCiCityCode() == city_number){ %>
                                <option value="<%=cities.get(x).getCiCityCode()%>" selected><%=cities.get(x).getCiName() %></option>
                                <%}else{%>
                                <option value="<%=cities.get(x).getCiCityCode()%>"><%=cities.get(x).getCiName() %></option>
                                <%}%>
                                <% } %>
                                <%}%>
                            </select>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-1">
                            <label class="col-form-label">Address:</label>
                        </div>
                        <div class="col-sm-4">
                            <jstl:choose>
                                <jstl:when test="${ rq_unitAddress != null }">
                                    <input type="text" name="p_address" class="form-control form-control-user" maxlength="45"
                                           value='<jstl:out value="${ rq_unitAddress }">Lost Value</jstl:out>'>
                                </jstl:when>
                                <jstl:otherwise>
                                    <input type="text" name="p_address" class="form-control form-control-user" maxlength="45">
                                </jstl:otherwise>
                            </jstl:choose>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-1">
                            <label class="col-form-label">Admin Code:</label>
                            <input name="p_acciones" type="hidden" value="${ rq_acciones }"/>
                            <input name='p_pantalla' type='hidden' value='bussinessunit' />
                        </div>
                        <div class="col-sm-2">
                            <input type="text" name="p_admincode" class="form-control form-control-user" maxlength="12"
                                   value='<jstl:out value="${ rq_unitAdminCode }">Lost Value</jstl:out>' readonly>
                        </div>
                        <div class="col-sm-2">
                            <label class="col-form-label">User 1 Code:</label>
                        </div>
                        <div class="col-sm-2">
                            <input type="text" name="p_user1code" class="form-control form-control-user" maxlength="12"
                                   value='<jstl:out value="${ rq_unitUser1Code }">Lost Value</jstl:out>' readonly>
                        </div>
                        <div class="col-sm-2">
                            <label class="col-form-label">User 2 Code:</label>
                        </div>
                        <div class="col-sm-2">
                            <input type="text" name="p_user2code" class="form-control form-control-user" maxlength="12"
                                   value='<jstl:out value="${ rq_unitUser2Code }">Lost Value</jstl:out>' readonly>
                        </div>
                    </div>
                </form>
            </div>
            <div class="container">
                <div class="form-group row">
                    <div class="col-lg-4">
                        &nbsp; &nbsp;
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
                <jstl:forEach var="x" begin="1" end="${ 2 }" step="1">
                    <div class="form-group row">
                        <div class="col-sm-12 mb-6 mb-sm-0">
                            &nbsp;
                        </div>
                    </div>
                </jstl:forEach>
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
<script type=text/javascript src="<%= request.getContextPath() %>/complements/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/complements/bootstrap/datatable_custom/datatables/datatables.min.js"></script>
<!-- para usar botones en datatables JS -->
<script src="<%= request.getContextPath() %>/complements/bootstrap/datatable_custom/datatables/Buttons-1.5.6/js/dataTables.buttons.min.js"></script>
<script src="<%= request.getContextPath() %>/complements/bootstrap/datatable_custom/datatables/JSZip-2.5.0/jszip.min.js"></script>
<script src="<%= request.getContextPath() %>/complements/bootstrap/datatable_custom/datatables/pdfmake-0.1.36/pdfmake.min.js"></script>
<script src="<%= request.getContextPath() %>/complements/bootstrap/datatable_custom/datatables/pdfmake-0.1.36/vfs_fonts.js"></script>
<script src="<%= request.getContextPath() %>/complements/bootstrap/datatable_custom/datatables/Buttons-1.5.6/js/buttons.html5.min.js"></script>

<!-- código JS propìo-->
<script type="text/javascript" src="../../../../complements/bootstrap/datatable_custom/main.js"></script>
</body>
