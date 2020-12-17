<%@ page import="com.psg.objectboard.model.own.ownsEntity.classDAO.CountryDAO" %>
<%@ page import="com.psg.objectboard.model.own.ownsEntity.classVO.CountryVO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.psg.objectboard.model.own.ownsEntity.classDAO.StateDAO" %>
<%@ page import="com.psg.objectboard.model.own.ownsEntity.classVO.StateVO" %>
<%@ page import="com.psg.objectboard.model.own.ownsEntity.classDAO.CityDAO" %>
<%@ page import="com.psg.objectboard.model.own.ownsEntity.classVO.CityVO" %><%
    Integer country_number = 0;
    if(request.getParameter("p_country_number")!=null){
        country_number=Integer.parseInt(request.getParameter("p_country_number"));
    }
    Integer state_number = 0;
    if(request.getParameter("p_state_number")!=null){
        state_number=Integer.parseInt(request.getParameter("p_state_number"));
    }
    Integer city_number = 0;
    if(request.getParameter("p_city_number")!=null){
        city_number=Integer.parseInt(request.getParameter("p_city_number"));
    }
    HttpSession objSesion = request.getSession();
    String data_user = (String)objSesion.getAttribute("dataUser");
    String data_pasword = (String)objSesion.getAttribute("dataPassword");

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
<!--start ############################### header ###############################-->
<head>
    <%@include file="../../../../complements/jsp/header.jsp"%>
    <script type="text/javascript">
        function combo_country() {
            document.formPhoto.p_metodo.value = 1;
            document.formPhoto.action="/objectboard/masteruser";
            document.formPhoto.submit();
        }
        function other_pdf(){
            document.formPhoto.action = '/objectboard/generalpdf';
            document.formPhoto.p_acciones.value = "user";
            document.formPhoto.submit();
        }
    </script>

</head>
<!--end ############################### header ###############################-->
<body>
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
            <jstl:set var="jsp_titleFrom" scope="page" value="User profile form"/>
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
            <!--Navegation = 1, Miscellaneous main files = 2,
             Properties Form = 8 {Save = 8.1, Plus = 8.2, Trash = 8.3, Printer = 8.4}-->
            <jstl:set var="jsp_navBarOption" scope="page" value="1,8,8.1,8.4"/>
            <%@include file="../../../../complements/jsp/navbar.jsp"%>
        </nav>
        <!--end ############################### navbar ###############################-->

        <!--start ############################### navbar-header ###############################-->
        <!--Mostrar imagen = 'YES', No mostar imagen = 'NOT'-->
        <jstl:set var="jsp_ShowImage" scope="page" value="${ 'NOT' }"/>
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
                    <%@include file="../../../../complements/jsp/page-header.jsp"%>
                    <!--end ############################### page-header ###############################-->

                    <div class="pcoded-inner-content">
                        <div class="main-body">
                            <div class="page-wrapper">
                                <div class="page-body">
                                    <!-- Basic Form Inputs card start -->
                                    <div class="card">
                                        <div class="card-header">
                                            <h5>User properties</h5>
                                        </div>
                                        <div class="card-block">
                                            <h4 class="sub-title">Basic Data</h4>
                                            <!--start ############################# MAIN BODY (DIFFERENT PROJECTS)###############################-->

                                            <!--START##########################################CUERPO PRINCIPAL#####################################################-->

                                            <form name="formPhoto" id="formPhoto" method="OPTIONS" action="#!" enctype="multipart/form-data">
                                                <div class="form-group row">
                                                    <label for="file" class="col-sm-2 col-form-label">Upload Photo</label>
                                                    <div class="col-sm-8" align="right" name="div-photo" id="div-photo">
                                                        <img name="oldPhoto" id="oldPhoto" src="/objectboard/showfileimage.html" class="img-thumbnail" alt="Photo Profile" width="100" height="120">
                                                        <input name="p_file" id="file" type="file" accept="image/png, image/jpeg" class="image-cropper-container" align="center" onchange="filePreview(this)">
                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <label for="muEmail" class="col-sm-2 colform-label">Email Address:</label>
                                                    <div class="col-sm-8">
                                                        <input name="p_muEmail" id="muEmail" type="text" maxlength="40" class="form-control" value="<jstl:out value="${ rq_masterUserDto.muEmail }">Lost Value</jstl:out>" readonly>
                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <label for="muName" class="col-sm-2 col-form-label">Full Name</label>
                                                    <div class="col-sm-8">
                                                        <input name="p_muName" id="muName" maxlength="45" type="text" class="form-control" value="<jstl:out value="${ rq_masterUserDto.muName }">Lost Value</jstl:out>">
                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <div class="col-sm-2">
                                                        <label for="muPassword" class="col-form-label">Password:</label>
                                                    </div>
                                                    <div class="col-sm-2">
                                                        <input id="muPassword" maxlength="20" type="password" class="form-control" placeholder="Password input" value="<jstl:out value="${ rq_masterUserDto.muPassword }">Lost Value</jstl:out>">
                                                    </div>
                                                    <div class="col-sm-1">
                                                        <label for="muRepeat" class="col-form-label">Repeat:</label>
                                                    </div>
                                                    <div class="input-group col-sm-2">
                                                        <input name="p_muRepeat" id="muRepeat" maxlength="20" type="password" class="form-control" placeholder="Repeat Password" value="<jstl:out value="${ rq_masterUserDto.muPassword }">Lost Value</jstl:out>" >
                                                        <div class="input-group-btn">
                                                            <button id="buttonEye" class="btn-sm" type="button">
                                                                <i id="imageEye" class="ti-eye"></i>
                                                            </button>
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-1">
                                                        <label class="col-form-label">Gender:</label>
                                                    </div>
                                                    <div class="col-sm-2">
                                                        <jstl:choose>
                                                            <jstl:when test="${ rq_masterUserDto.muGender =='M' }">
                                                                <input type="radio" id="activeGender" name="p_muGender" value="M" checked>
                                                                <label for="activeGender">&nbsp;&nbsp;&nbsp;Male</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                                <input type="radio" id="inactiveGender" name="p_muGender" value="F">
                                                                <label for="activeGender">&nbsp;&nbsp;&nbsp;Female</label>
                                                            </jstl:when>
                                                            <jstl:otherwise>
                                                                <input type="radio" id="activeGender" name="p_muGender" value="M">
                                                                <label for="activeGender">&nbsp;&nbsp;&nbsp;Male</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                                <input type="radio" id="inactiveGender" name="p_muGender" value="F" checked>
                                                                <label for="activeGender">&nbsp;&nbsp;&nbsp;Female</label>
                                                            </jstl:otherwise>
                                                        </jstl:choose>
                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <div class="col-sm-2">
                                                        <label for="muQuestion" class="col-form-label">Recovery question:</label>
                                                    </div>
                                                    <div class="col-sm-3">
                                                        <input name="p_muQuestion" id="muQuestion" maxlength="60" type="text" class="form-control" value="<jstl:out value="${ rq_masterUserDto.muQuestion }">Lost Value</jstl:out>">
                                                    </div>
                                                    <div class="col-sm-2">
                                                        <label for="muAnswer" class="col-form-label">Answer to the question:</label>
                                                    </div>
                                                    <div class="col-sm-3">
                                                        <input name="p_muAnswer" id="muAnswer" maxlength="35" type="text" class="form-control" value="<jstl:out value="${ rq_masterUserDto.muAnswer }">Lost Value</jstl:out>">
                                                    </div>
                                                </div>
                                                <!--start ############## Form MAIN BODY ##############-->
                                                <!--WHEN Bussiness Unit = (1) VIEW:
                                                Effective Days, Section Time, Start Date,
                                                Status, Date, Email Confirm
                                                rq_companyNumber == 1-->
                                                <!--end ############## Form MAIN BODY ################-->
                                                <jstl:if test="${ rq_companyNumber == 1 }">
                                                    <div class="card-block">
                                                        <h4 class="sub-title">Advanced configuration</h4>
                                                        <div class="form-group row">
                                                            <div class="col-sm-2">
                                                                <label for="muDate" class="col-form-label">Register Date:</label>
                                                            </div>
                                                            <div class="col-sm-3">
                                                                <input id="muDate" type="text" class="form-control"  readonly value="<jstl:out value="${ rq_masterUserDto.muStartDate }">Lost Value</jstl:out>">
                                                            </div>
                                                            <div class="col-sm-2">
                                                                <label class="col-form-label">User Status:</label>
                                                            </div>
                                                            <div class="col-sm-3">
                                                                <jstl:choose>
                                                                    <jstl:when test="${ rq_masterUserDto.muStatus =='T' }">
                                                                        <input type="radio" id="activeStatus" name="p_muStatus" value="T" checked disabled>
                                                                        <label for="activeStatus">&nbsp;&nbsp;&nbsp;Active</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                                        <input type="radio" id="inactiveStatus" name="p_muStatus" value="F" disabled>
                                                                        <label for="activeStatus">&nbsp;&nbsp;&nbsp;Inactive</label>
                                                                    </jstl:when>
                                                                    <jstl:otherwise>
                                                                        <input type="radio" id="activeStatus" name="p_muStatus" value="T" disabled>
                                                                        <label for="activeStatus">&nbsp;&nbsp;&nbsp;Active</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                                        <input type="radio" id="inactiveStatus" name="p_muStatus" value="F" checked disabled>
                                                                        <label for="activeStatus">&nbsp;&nbsp;&nbsp;Inactive</label>
                                                                    </jstl:otherwise>
                                                                </jstl:choose>
                                                            </div>
                                                        </div>
                                                        <div class="form-group row">
                                                            <div class="col-sm-2">
                                                                <label for="muStartDate">Enable Start Day:</label>
                                                            </div>
                                                            <div class="col-sm-3">
                                                                <input id="muStartDate" type="text" class="form-control" value="<jstl:out value="${ rq_masterUserDto.muDate }">Lost Value</jstl:out>" readonly>
                                                            </div>
                                                            <div class="col-sm-2">
                                                                <label for="controle_muSectionTime" class="col-form-label">Session time:</label>
                                                            </div>
                                                            <div class="col-sm-2">
                                                                <fmt:parseNumber var="i" integerOnly="true" type="number" value="${ rq_masterUserDto.muSectionTime }"/>
                                                                <input name="p_muSectionTime" id="controle_muSectionTime" oninput="muSectionTime.value = (this.value +' seg.')" value="<jstl:out value="${ i }">0</jstl:out>"step="60" min="60" max="3600" type="range" class="form-control">
                                                            </div>
                                                            <div class="col-sm-1">
                                                                <input name="muSectionTime" type="text" class="form-control" placeholder="<jstl:out value="${ i }">0</jstl:out> min." readonly>
                                                            </div>
                                                        </div>
                                                        <div class="form-group row">
                                                            <div class="col-sm-2">
                                                                <label for="controle_muEffectiveDays" class="col-form-label">Renovation password days:</label>
                                                            </div>
                                                            <div class="col-sm-2">
                                                                <input name="p_muEffectiveDays" id="controle_muEffectiveDays" oninput="muEffectiveDays.value = (this.value +' Days.')" value="<jstl:out value="${ rq_masterUserDto.muEffectiveDays }">0</jstl:out>"step="30" min="30" max="365" type="range" class="form-control">
                                                            </div>
                                                            <div class="col-sm-2">
                                                                <input name="muEffectiveDays" type="text" class="form-control" placeholder="<jstl:out value="${ rq_masterUserDto.muEffectiveDays }">0</jstl:out> Days." readonly>
                                                            </div>
                                                            <div class="col-sm-2">
                                                                <label class="col-form-label">Confirm Email:</label>
                                                            </div>
                                                            <div class="col-sm-3">
                                                                <jstl:choose>
                                                                    <jstl:when test="${ rq_masterUserDto.muEmailConfirm =='T' }">
                                                                        <input type="radio" id="activeEmail" name="p_muEmailConfirm" value="T" checked disabled>
                                                                        <label for="activeEmail">&nbsp;&nbsp;Confirm</label>&nbsp;&nbsp;&nbsp;&nbsp;
                                                                        <input type="radio" id="inactiveEmail" name="p_muEmailConfirm" value="F" disabled>
                                                                        <label for="activeEmail">&nbsp;&nbsp;Not Confirm</label>
                                                                    </jstl:when>
                                                                    <jstl:otherwise>
                                                                        <input type="radio" id="activeEmail" name="p_muEmailConfirm" value="T" disabled>
                                                                        <label for="activeEmail">&nbsp;&nbsp;&nbsp;Confim</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                                        <input type="radio" id="inactiveEmail" name="p_muEmailConfirm" value="F" checked disabled>
                                                                        <label for="activeEmail">&nbsp;&nbsp;&nbsp;Not Confim</label>
                                                                    </jstl:otherwise>
                                                                </jstl:choose>
                                                            </div>
                                                        </div>
                                                        <div class="form-group row">
                                                            <div class="col-sm-2">
                                                                <label for="controle_muEffectiveDays" class="col-form-label">Data User:</label>
                                                            </div>
                                                            <div class="col-sm-2">
                                                                <input name="p_mudatauser" id="mudatauser"
                                                                       value="<jstl:out value="${ rq_masterUserDto.muDataUser }">Lost Value</jstl:out>"
                                                                       maxlength="12" readonly>
                                                            </div>
                                                            <div class="col-sm-2">
                                                                <label class="col-form-label">Data Password:</label>
                                                            </div>
                                                            <div class="col-sm-2">
                                                                <input name="p_mudatapassword" id="mudatapassword"
                                                                       value="<jstl:out value="${ rq_masterUserDto.muDataPassword }">Lost Value</jstl:out>"
                                                                       maxlength="12" readonly>
                                                            </div>
                                                            <div class="col-sm-1">
                                                                <label class="col-form-label">Expires:</label>
                                                            </div>
                                                            <div class="col-sm-2">
                                                                <jstl:choose>
                                                                    <jstl:when test="${ rq_masterUserDto.muExpires =='T' }">
                                                                        <input type="radio" id="activeStatus" name="p_muexpires" value="T" checked disabled>
                                                                        <label for="activeStatus">&nbsp;&nbsp;Yes</label>&nbsp;&nbsp;&nbsp;&nbsp;
                                                                        <input type="radio" id="inactiveStatus" name="p_muexpires" value="F" disabled>
                                                                        <label for="activeStatus">&nbsp;&nbsp;&nbsp;No</label>
                                                                    </jstl:when>
                                                                    <jstl:otherwise>
                                                                        <input type="radio" id="activeStatus" name="p_muexpires" value="T" disabled>
                                                                        <label for="activeStatus">&nbsp;&nbsp;&nbsp;Yes</label>
                                                                        <input type="radio" id="inactiveStatus" name="p_muexpires" value="F" checked disabled>
                                                                        <label for="activeStatus">&nbsp;&nbsp;&nbsp;No</label>
                                                                    </jstl:otherwise>
                                                                </jstl:choose>
                                                            </div>
                                                        </div>
                                                        <div class="form-group row">
                                                            <div class="col-sm-2">
                                                                <label for="controle_muEffectiveDays" class="col-form-label">Date Expires:</label>
                                                            </div>
                                                            <div class="col-sm-2">
                                                                <input  name="p_datexpires" id="datepicker" width="276" value="${ rq_masterUserDto.muDateExpires }" readonly/>
                                                                <input type="hidden" value="${ rq_hora }" name="p_hora">
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
                                                            <div class="col-sm-2">
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
                                                            <div class="col-sm-2">
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
                                                            <div class="col-sm-2">
                                                                <input type="hidden" name="p_acciones" value="${ rq_acciones }">
                                                                <input type="hidden" name="p_email" value="${ rq_masterUserDto.muEmail }">
                                                                <input type="hidden" name="p_unit" value="${ rq_companyNumber }">
                                                                <input type="hidden" name="p_metodo" value="0">
                                                                <input name='p_pantalla' type='hidden' value='users' />
                                                            </div>
                                                        </div>
                                                    </div>
                                                </jstl:if>
                                            </form>
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
                    <footer>
                        <%@include file="../../../../complements/jsp/footer.jsp"%>
                    </footer>
                    <!--end ############################### footer ###############################-->
                </div>
            </div>
        </div>
        <div id="styleSelector"></div>
    </div>
</div>
</div>
</div>

<!--end ############################### warnig section ###############################-->
<%@include file="../../../../complements/jsp/warning_section.jsp"%>
<!--end ############################### warnig section ###############################-->

<!--star ############################### section JavaScript ###############################-->
<%@include file="../../../../complements/jsp/script.jsp"%>

<script type="text/javascript">
    /*Start################### Valores por defecto para barra de comandos  ###################*/
    $(document).ready(function() {
        document.getElementById("buttonSave").setAttribute("onClick", "updateMasterUser()");
        document.getElementById("buttonpdf").setAttribute("onClick", "other_pdf()");
    });
    /*End################### Valores por defecto para barra de comandos  ###################*/
    $("#muName").focusout(function(){
        valida_solo_textos(document.getElementById("muName").value,"Full Name:");
        valida_largos(document.getElementById("muName").value.length,"Full Name",3);
    });
    $("#muPassword").focusout(function(){
        valida_textos(document.getElementById("muPassword").value,"Password","*$@#");
        valida_largos(document.getElementById("muPassword").value.length,"Password",6);
    });
    $("#muRepeat").focusout(function(){
        valida_textos(document.getElementById("muRepeat").value,"Repeat","*$@#");
        valida_largos(document.getElementById("muRepeat").value.length,"Repeat",6);
        valida_password_iguales(document.getElementById("muPassword").value, document.getElementById("muRepeat").value);
    });
    $("#muQuestion").focusout(function(){
        valida_textos(document.getElementById("muQuestion").value,"Recovery question","?");
        valida_largos(document.getElementById("muQuestion").value.length,"Recovery question",3);
    });
    $("#muAnswer").focusout(function(){
        valida_largos(document.getElementById("muAnswer").value.length,"Answer to the question",3);
    });

    $("input").change (function(){
        document.getElementById("textSave").innerHTML = "(On) Save";
        document.getElementById("buttonSave").disabled = false;
        $("#buttonSave").addClass("btn-primary");
    });


    document.getElementById("buttonEye").onclick = (function () {
        const image = document.getElementById('imageEye');
        var passe = false;
        if (image.className == 'ti-eye') {
            document.getElementById('muPassword').setAttribute('type', 'text');
            document.getElementById('muRepeat').setAttribute('type', 'text');
            $("#imageEye").removeClass("ti-eye");
            $("#imageEye").addClass("ti-close");
            passe = true;
        }
        if (image.className == 'ti-close' && !passe ) {
            document.getElementById('muPassword').setAttribute('type', 'password');
            document.getElementById('muRepeat').setAttribute('type', 'password');
            $("#imageEye").removeClass("ti-close");
            $("#imageEye").addClass("ti-eye");
        }
    });
    function updateMasterUser() {
        const x = confirm("Are you sure to save the changes?");
        if (x){
            $("#formPhoto").submit();
        }
    }
    $("#formPhoto").submit(function(){
        const formData = new FormData($(this)[0]);
        $.ajax({
            url: '/objectboard/multipartconfigservlet',
            type: 'OPTIONS',
            data: formData,
            async: false,
            success: function (data) { //en data se encuentra toda la informacion del html.
                $("#buttonSave").removeClass("btn-primary");
                document.getElementById("buttonSave").disabled = true;
                document.getElementById("textSave").innerHTML = "Updated data";
                //alert(data);
            },
            cache: false,
            contentType: false,
            processData: false
        });
        return false;
    });
</script>

<!--end ############################### section JavaScript ###############################-->
<!--end ############################### body ###############################-->
</body>

</html>
