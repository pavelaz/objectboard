<!DOCTYPE html>
<html lang="en">
<!--start ############################### header ###############################-->
<head>
    <%@include file="../../../../complements/jsp/header.jsp"%>
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
            <!--Navegation = 1, Miscellaneous main files = 2,
             Properties Form = 8 {Save = 8.1, Plus = 8.2, Trash = 8.3, Printer = 8.4}-->
            <jstl:set var="jsp_navBarOption" scope="page" value="1,2,8,8.1,8.4"/>
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
                    <jstl:set var="jsp_titleFrom" scope="page" value="User profile form"/>
                    <jstl:set var="jsp_funtionFrom" scope="page" value="Objectboard system, update your information..."/>
                    <jstl:choose>
                        <jstl:when test="${ rq_company_number == 1 }">
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
                                                                <input id="muDate" type="text" class="form-control" value="<jstl:out value="${ rq_masterUserDto.muStartDate }">Lost Value</jstl:out>" disabled>
                                                            </div>
                                                            <div class="col-sm-2">
                                                                <label class="col-form-label">User Status:</label>
                                                            </div>
                                                            <div class="col-sm-2">
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
                                                                <label for="muStartDate">Enable Stard Day:</label>
                                                            </div>
                                                            <div class="col-sm-3">
                                                                <input id="muStartDate" type="text" class="form-control" value="<jstl:out value="${ rq_masterUserDto.muDate }">Lost Value</jstl:out>" disabled>
                                                            </div>
                                                            <div class="col-sm-2">
                                                                <label for="controle_muSectionTime" class="col-form-label">Session time:</label>
                                                            </div>
                                                            <div class="col-sm-2">
                                                                <fmt:parseNumber var="i" integerOnly="true" type="number" value="${ rq_masterUserDto.muSectionTime /60 }"/>
                                                                <input name="p_muSectionTime" id="controle_muSectionTime" oninput="muSectionTime.value = (this.value +' min.')" value="<jstl:out value="${ i }">0</jstl:out>"step="10" min="10" max="60" type="range" class="form-control">
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
                                                            <div class="col-sm-1">
                                                                <input name="muEffectiveDays" type="text" class="form-control" placeholder="<jstl:out value="${ rq_masterUserDto.muEffectiveDays }">0</jstl:out> Days." disabled>
                                                            </div>
                                                            <div class="col-sm-2">
                                                                <label class="col-form-label">Confirm Email:</label>
                                                            </div>
                                                            <div class="col-sm-2">
                                                                <jstl:choose>
                                                                    <jstl:when test="${ rq_masterUserDto.muEmailConfirm =='T' }">
                                                                        <input type="radio" id="activeEmail" name="p_muEmailConfirm" value="T" checked>
                                                                        <label for="activeEmail">&nbsp;&nbsp;&nbsp;Active</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                                        <input type="radio" id="inactiveEmail" name="p_muEmailConfirm" value="F">
                                                                        <label for="activeEmail">&nbsp;&nbsp;&nbsp;Inactive</label>
                                                                    </jstl:when>
                                                                    <jstl:otherwise>
                                                                        <input type="radio" id="activeEmail" name="p_muEmailConfirm" value="T">
                                                                        <label for="activeEmail">&nbsp;&nbsp;&nbsp;Active</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                                        <input type="radio" id="inactiveEmail" name="p_muEmailConfirm" value="F" checked>
                                                                        <label for="activeEmail">&nbsp;&nbsp;&nbsp;Inactive</label>
                                                                    </jstl:otherwise>
                                                                </jstl:choose>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </jstl:if>
                                                <div class="col-sm-2">
                                                    <input type="hidden" name="p_acciones" value="${ rq_acciones }">
                                                    <input type="hidden" name="p_email" value="${ rq_masterUserDto.muEmail }">
                                                    <input type="hidden" name="p_unit" value="${ rq_companyNumber }">
                                                    <input type="hidden" name="p_metodo" value="0">
                                                    <input type="hidden" name="p_pantalla" value="users">

                                                    <input type="hidden" name="p_country" value="${ rq_masterUserDto.cityStatesCountryCoCountryCode }">
                                                    <input type="hidden" name="p_state" value="${ rq_masterUserDto.cityStatesStStateCode }">
                                                    <input type="hidden" name="p_city" value="${  rq_masterUserDto.cityCiCityCode  }">
                                                </div>
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
        const x = confirm("Are you sure to save the changes?")
        if (x){
            $("#formPhoto").submit();
        }
    };
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
