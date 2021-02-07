<%@ page import="com.psg.objectboard.model.own.ownsEntity.classDAO.CountryDAO" %>
<%@ page import="com.psg.objectboard.model.own.ownsEntity.classVO.CountryVO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.psg.objectboard.model.own.ownsEntity.classDAO.StateDAO" %>
<%@ page import="com.psg.objectboard.model.own.ownsEntity.classVO.StateVO" %>
<%@ page import="com.psg.objectboard.model.own.ownsEntity.classDAO.CityDAO" %>
<%@ page import="com.psg.objectboard.model.own.ownsEntity.classVO.CityVO" %>
<%
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
    String user_board_role = (String)objSesion.getAttribute("userBoardRole");

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
    <title>User maintenance</title>
    <!-- Favicon icon -->
    <link rel="shortcut icon" type="image/png" href="<%= request.getContextPath() %>/complements/img/favicon1.png">
    <%@include file="../../../../complements/jsp/header.jsp"%>

    <!-- Propias -->
    <link rel=stylesheet href=https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css />
    <script src="<%= request.getContextPath() %>/complements/scripts/validaciones.js" type="text/javascript"></script>
    <script type="text/javascript">
        <% if (user_board_role.equals("1") || user_board_role.equals("2") ){ %>
            function Validaciones(){
                if (!valida_textos(document.formPhoto.p_muName.value,"Name","")||
                    !valida_largos(document.formPhoto.p_muName.value.length,"Name",3)||
                    !valida_selects(document.formPhoto.p_country_number.value,"Country","")||
                    !valida_selects(document.formPhoto.p_state_number.value,"State","")||
                    !valida_selects(document.formPhoto.p_city_number.value,"City","")||
                    !valida_textos(document.formPhoto.p_muPassword.value,"Password","*$@#")||
                    !valida_largos(document.formPhoto.p_muPassword.value.length,"Password",6)||
                    !valida_textos(document.formPhoto.p_muRepeat.value,"Repeat","*$@#")||
                    !valida_largos(document.formPhoto.p_muRepeat.value.length,"Repeat",6)||
                    !valida_password_iguales(document.formPhoto.p_muPassword.value, document.formPhoto.p_muRepeat.value)||
                    !valida_textos(document.formPhoto.p_muQuestion.value,"Recovery question","?")||
                    !valida_largos(document.formPhoto.p_muQuestion.value.length,"Recovery question",3)||
                    !valida_largos(document.formPhoto.p_muAnswer.value.length,"Answer to the question",3)||
                    !valida_textos(document.formPhoto.p_mudatauser.value,"Data User","")||
                    !valida_largos(document.formPhoto.p_mudatauser.value.length,"Data User",8)||
                    !valida_textos(document.formPhoto.p_mudatapassword.value,"Data Password","*$@#%")||
                    !valida_largos(document.formPhoto.p_mudatapassword.value.length,"Data Password",12)
                ){
                    return false;
                }
                return true;
            }
        <% } else { %>
            function Validaciones(){
                if (!valida_textos(document.formPhoto.p_muName.value,"Name","")||
                    !valida_largos(document.formPhoto.p_muName.value.length,"Name",3)||
                    !valida_textos(document.formPhoto.p_muPassword.value,"Password","*$@#")||
                    !valida_largos(document.formPhoto.p_muPassword.value.length,"Password",6)||
                    !valida_textos(document.formPhoto.p_muRepeat.value,"Repeat","*$@#")||
                    !valida_largos(document.formPhoto.p_muRepeat.value.length,"Repeat",6)||
                    !valida_password_iguales(document.formPhoto.p_muPassword.value, document.formPhoto.p_muRepeat.value)||
                    !valida_textos(document.formPhoto.p_muQuestion.value,"Recovery question","?")||
                    !valida_largos(document.formPhoto.p_muQuestion.value.length,"Recovery question",3)||
                    !valida_largos(document.formPhoto.p_muAnswer.value.length,"Answer to the question",3)
                ){
                    return false;
                }
                return true;
            }
        <% } %>
        function registro(){
            if(Validaciones()){
                //alert("paso");
                document.formPhoto.p_method.value = "1";
                //alert("paso 1");
                document.formPhoto.action="/objectboard/multipartconfigservlet";
                //alert("pasov2");
                document.formPhoto.submit();
            }
        }
        function combo_country() {
            document.formPhoto.action="/objectboard/userprofile";
            document.formPhoto.submit();
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
        <%--<jstl:set var="jsp_navBarOption" scope="page" value="${ '8' }"/>
        <%@include file="../../../../complements/jsp/navbar.jsp"%> --%>
        <%@include file="../../../../complements/jsp/head_basic_script.jsp"%>
        <!--end ############################### navbar ###############################-->

        <!--start ############################### navbar-header ###############################-->
        <jstl:set var="jsp_ShowImage" scope="page" value="${ 'NOT' }"/>
        <jstl:set var="jsp_menuOption" scope="page" value="${ '2,6' }"/>
        <jstl:set var="jsp_previousMsg" scope="page" value="Previous page"/>
        <jstl:choose>
           <jstl:when test="${  rq_companyNumber == '1'  }">
               <%--<jstl:set var="jsp_pagePrevious" scope="page" value="/objectboard/userprofileconsult"/>--%>
                <jstl:choose>
                   <jstl:when test="${ rq_viene == 'P' }">
                       <jstl:set var="jsp_pagePrevious" scope="page" value="/objectboard/mastermenusuper"/>
                   </jstl:when>
                   <jstl:otherwise>
                       <jstl:set var="jsp_pagePrevious" scope="page" value="/objectboard/userprofileconsult"/>
                   </jstl:otherwise>
               </jstl:choose>
           </jstl:when>
           <jstl:otherwise>
               <jstl:set var="jsp_pagePrevious" scope="page" value="/objectboard/dashmenusuper"/>
           </jstl:otherwise>
        </jstl:choose>
        <%@include file="../../../../complements/jsp/navbar-header.jsp"%>
        <!--end ############################### navbar-header ###############################-->

        <div class="pcoded-main-container">
            <div class="pcoded-wrapper">
                <div class="pcoded-content">

                    <!--start ############################### page-header ###############################-->
                    <jstl:set var="jsp_titleFrom" scope="page" value="User Maintenance Form"/>
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
                                            <h5>User properties</h5>
                                        </div>
                                        <div class="card-block">
                                            <h4 class="sub-title">Basic Data</h4>
                                            <!--start ############################# MAIN BODY (DIFFERENT PROJECTS)###############################-->

                                            <!--START##########################################CUERPO PRINCIPAL#####################################################-->

                                            <form name="formPhoto" id="formPhoto" method="post" action="#!" enctype="multipart/form-data">
                                                <div class="form-group row">
                                                    <label for="file" class="col-sm-2 col-form-label">Upload Photo</label>
                                                    <div class="col-sm-8" align="right" name="div-photo" id="div-photo">
                                                        <img name="oldPhoto" id="oldPhoto" src="/objectboard/showfile.html" class="img-thumbnail" alt="Photo Profile" width="100" height="120">
                                                        <input name="p_file" id="file" type="file" accept="image/png, image/jpeg" class="image-cropper-container" align="center" onchange="filePreview(this)">
                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <label for="muEmail" class="col-sm-2 colform-label">Email Address:</label>
                                                    <div class="col-sm-8">
                                                        <input name="p_muEmail" id="muEmail" type="text" maxlength="40" class="form-control" value="<jstl:out value="${ rq_masterUserDto.muEmail }">Lost Value</jstl:out>" disabled>
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
                                                        <input id="muPassword" maxlength="20" type="password" class="form-control"
                                                               placeholder="Password input" name="p_muPassword"
                                                               value="<jstl:out value="${ rq_masterUserDto.muPassword }">Lost Value</jstl:out>">
                                                    </div>
                                                    <div class="col-sm-1">
                                                        <label for="muRepeat" class="col-form-label">Repeat:</label>
                                                    </div>
                                                    <div class="input-group col-sm-2">
                                                        <input name="p_muRepeat" id="muRepeat" maxlength="20" type="password"
                                                               class="form-control" placeholder="Repeat Password"
                                                               value="<jstl:out value="${ rq_masterUserDto.muPassword }">Lost Value</jstl:out>" >
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
                                                                <label for="activeGender">&nbsp;&nbsp;&nbsp;Male</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                                <input type="radio" id="inactiveGender" name="p_muGender" value="F">
                                                                <label for="activeGender">&nbsp;&nbsp;&nbsp;Female</label>
                                                            </jstl:when>
                                                            <jstl:otherwise>
                                                                <input type="radio" id="activeGender" name="p_muGender" value="M">
                                                                <label for="activeGender">&nbsp;&nbsp;&nbsp;Male</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
                                                <% if (user_board_role.equals("1") || user_board_role.equals("2") ){ %>
                                                    <div class="card-block">
                                                        <h4 class="sub-title">Advanced configuration</h4>
                                                        <div class="form-group row">
                                                            <div class="col-sm-2">
                                                                <label for="muDate" class="col-form-label">Register Date:</label>
                                                            </div>
                                                            <div class="col-sm-3">
                                                                <input id="muDate" type="text" class="form-control" value="<jstl:out value="${ rq_masterUserDto.muStartDate }">Lost Value</jstl:out>" disabled>
                                                            </div>
                                                            <div class="col-sm-2">
                                                                <label class="col-form-label">User Status:</label>
                                                            </div>
                                                            <div class="col-sm-3">
                                                                <jstl:choose>
                                                                    <jstl:when test="${ rq_masterUserDto.muStatus =='T' }">
                                                                        <input type="radio" id="activeStatus" name="p_muStatus" value="T" checked>
                                                                        <label for="activeStatus">&nbsp;&nbsp;&nbsp;Active</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                                        <input type="radio" id="inactiveStatus" name="p_muStatus" value="F">
                                                                        <label for="activeStatus">&nbsp;&nbsp;&nbsp;Inactive</label>
                                                                    </jstl:when>
                                                                    <jstl:otherwise>
                                                                        <input type="radio" id="activeStatus" name="p_muStatus" value="T">
                                                                        <label for="activeStatus">&nbsp;&nbsp;&nbsp;Active</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                                        <input type="radio" id="inactiveStatus" name="p_muStatus" value="F" checked>
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
                                                                <input id="muStartDate" type="text" class="form-control" value="<jstl:out value="${ rq_masterUserDto.muDate }">Lost Value</jstl:out>" disabled>
                                                            </div>
                                                            <div class="col-sm-2">
                                                                <label for="controle_muSectionTime" class="col-form-label">Session time:</label>
                                                            </div>
                                                            <div class="col-sm-2">
                                                                <fmt:parseNumber var="i" integerOnly="true" type="number" value="${ rq_masterUserDto.muSectionTime }"/>
                                                                <input name="p_muSectionTime" id="controle_muSectionTime" oninput="muSectionTime.value = (this.value +' seg.')" value="<jstl:out value="${ i }">0</jstl:out>"step="60" min="60" max="3600" type="range" class="form-control">
                                                            </div>
                                                            <div class="col-sm-1">
                                                                <input name="muSectionTime" type="text" class="form-control" placeholder="<jstl:out value="${ i }">0</jstl:out> min." disabled>
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
                                                                <input name="muEffectiveDays" type="text" class="form-control" placeholder="<jstl:out value="${ rq_masterUserDto.muEffectiveDays }">0</jstl:out> Days." disabled>
                                                            </div>
                                                            <div class="col-sm-2">
                                                                <label class="col-form-label">Confirm Email:</label>
                                                            </div>
                                                            <div class="col-sm-3">
                                                                <jstl:choose>
                                                                    <jstl:when test="${ rq_masterUserDto.muEmailConfirm =='T' }">
                                                                        <input type="radio" id="activeEmail" name="p_muEmailConfirm" value="T" checked>
                                                                        <label for="activeEmail">&nbsp;&nbsp;Confirm</label>&nbsp;&nbsp;&nbsp;&nbsp;
                                                                        <input type="radio" id="inactiveEmail" name="p_muEmailConfirm" value="F">
                                                                        <label for="activeEmail">&nbsp;&nbsp;Not Confirm</label>
                                                                    </jstl:when>
                                                                    <jstl:otherwise>
                                                                        <input type="radio" id="activeEmail" name="p_muEmailConfirm" value="T">
                                                                        <label for="activeEmail">&nbsp;&nbsp;&nbsp;Confim</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                                        <input type="radio" id="inactiveEmail" name="p_muEmailConfirm" value="F" checked>
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
                                                                        <input type="radio" id="activeStatus" name="p_muexpires" value="T" checked>
                                                                        <label for="activeStatus">&nbsp;&nbsp;Yes</label>&nbsp;&nbsp;&nbsp;&nbsp;
                                                                        <input type="radio" id="inactiveStatus" name="p_muexpires" value="F">
                                                                        <label for="activeStatus">&nbsp;&nbsp;&nbsp;No</label>
                                                                    </jstl:when>
                                                                    <jstl:otherwise>
                                                                        <input type="radio" id="activeStatus" name="p_muexpires" value="T">
                                                                        <label for="activeStatus">&nbsp;&nbsp;&nbsp;Yes</label>
                                                                        <input type="radio" id="inactiveStatus" name="p_muexpires" value="F" checked>
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
                                                                <input  name="p_datexpires" id="datepicker" width="276" value="${ rq_fecha }" readonly/>
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
                                                                <input type="hidden" name="p_method" value="0">
                                                                <input name='p_pantalla' type='hidden' value='users' />
                                                                <input name='p_viene' type='hidden' value='${ rq_viene }' />
                                                            </div>
                                                        </div>
                                                    </div>
                                                <% } else { %>
                                                    <input type="hidden" name="p_datexpires" width="276" value="${ rq_fecha }">
                                                    <input type="hidden" name="p_muStatus" id="inactiveStatus" value="${ rq_masterUserDto.muStatus }">
                                                    <input type="hidden" name="p_muSectionTime" id="controle_muSectionTime"  value="${ rq_masterUserDto.muSectionTime }">
                                                    <input type="hidden" name="p_muEmailConfirm" id="inactiveEmail" value="${ rq_masterUserDto.muEmailConfirm }">
                                                    <input type="hidden" name="p_muexpires" id="inactiveStatus" value="${ rq_masterUserDto.muExpires }">
                                                    <input type="hidden" name="p_country_number"  value="<%= country_number %>">
                                                    <input type="hidden" name="p_state_number"  value="<%= state_number %>">
                                                    <input type="hidden" name="p_city_number"  value="<%= city_number %>">
                                                    <input type="hidden" name="p_muEffectiveDays" value="${ rq_masterUserDto.muEffectiveDays }">
                                                    <input type="hidden" name="p_acciones" value="${ rq_acciones }">
                                                    <input type="hidden" name="p_email" value="${ rq_masterUserDto.muEmail }">
                                                    <input type="hidden" name="p_unit" value="${ rq_companyNumber }">
                                                    <input type="hidden" value="${ rq_hora }" name="p_hora">
                                                    <input type="hidden" name="p_method" value="0">
                                                    <input name='p_pantalla' type='hidden' value='users' />
                                                    <input name='p_viene' type='hidden' value='${ rq_viene }' />
                                                <% } %>
                                                <%--</jstl:if>--%>
                                            </form>
                                            <!--END##########################################CUERPO PRINCIPAL#####################################################-->

                                            <!--end ############################### MAIN BODY (DIFFERENT PROJECTS)###############################-->
                                        </div>
                                    </div>
                                    </div>
                                    <!-- Basic Form Inputs card end -->
                                </div>
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
                                        &nbsp;
                                    </div>
                                </div>
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
<!--end ############################### footer_basic_script ###############################-->
<script type="text/javascript" src="<%= request.getContextPath() %>/complements/scripts/showPassword.js"></script>
<script type=text/javascript src="<%= request.getContextPath() %>/complements/js/bootstrap-datepicker.min.js"></script>
<script type=text/javascript src="<%= request.getContextPath() %>/complements/scripts/ImageControls.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
        var date_input=$('input[name="p_datexpires"]'); //our date input has the name "p_datexpires"
        var container=$('.bootstrap-iso form').length>0 ? $('.bootstrap-iso form').parent() : "body";
        date_input.datepicker({
            format: 'mm/dd/yyyy',
            container: container,
            todayHighlight: true,
            autoclose: true,
        })
    })
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
    /*function updateMasterUser() {
        const x = confirm("Are you sure to save the changes?")
        if (x){
            $("#formPhoto").submit();
        }
    };
    $("#formPhoto").submit(function(){
        const formData = new FormData($(this)[0]);
        $.ajax({
            //url: '/multipartconfigservlet',
            url: '/userprofile',
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
    });*/
</script>
</body>

</html>
