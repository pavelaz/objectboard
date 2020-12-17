<!DOCTYPE html>
<html lang="en">
<!--start ############################### header ###############################-->
<head>
    <title>City maintenance</title>
    <!-- Favicon icon -->
    <link rel="shortcut icon" type="image/png" href="<%= request.getContextPath() %>/complements/img/favicon1.png">
    <%@include file="../../../../complements/jsp/header.jsp"%>

    <!-- Propias -->
    <script src="<%= request.getContextPath() %>/complements/scripts/validaciones.js" type="text/javascript"></script>
    <script type="text/javascript">
        function Validaciones(){
            <jstl:choose>
                <jstl:when test="${ rq_acciones != 'save' }">
                    if (!valida_textos(document.form.p_name.value,"State's name","")||
                        !valida_largos(document.form.p_name.value.length,"State's name",3)||
                        !valida_selects(document.form.p_country_number.value,"country","")||
                        !valida_selects(document.form.p_state_number.value,"State","")
                    ){
                        return false;
                    }
                </jstl:when>
                <jstl:otherwise>
                    if (!valida_textos(document.form.p_name.value,"State's name","")||
                        !valida_largos(document.form.p_name.value.length,"State's name",3)
                    ){
                        return false;
                    }
                </jstl:otherwise>
            </jstl:choose>
            return true;
        }
        function registro(){
            if(Validaciones()){
                document.form.action="/objectboard/citiesprocess";
                document.form.submit();
            }
        }
    </script>
    <jstl:choose>
        <jstl:when test="${ rq_acciones != 'save' }">
            <script language="JavaScript" type="text/javascript">
        function agregarOpciones(form){
            var selec = form.p_country_number.options;
            var combo = form.p_state_number.options;
            combo.length = null;

            if (selec[0].selected == true){
                var seleccionar = new Option('Choose State');
                combo[0] = seleccionar;
            }
            <jstl:forEach var="p" begin="0" end="${ rq_largoPaises - 1 }" step="1">
                <jstl:set var="se" scope="page" value="${ p + 1 }"/>
                if (selec[${ se }].selected == true){

                    <jstl:set var="cu" scope="page" value="${ 1 }"/>
                    <jstl:forEach var="s" begin="0" end="${ rq_largos_states[p] -1 }" step="1">
                        var popular_${ cu } = new
                            Option("<jstl:out value="${ rq_staNom[p][s] }">Lost Value</jstl:out>",
                                   "<jstl:out value="${ rq_staCod[p][s] }">Lost Value</jstl:out>");
                        combo[${ s }] = popular_${ cu };
                        <jstl:set var = "cu" value = "${cu + 1}" />
                    </jstl:forEach>
                }
            </jstl:forEach>
        }
    </script>
        </jstl:when>
    </jstl:choose>
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
        <jstl:set var="jsp_menuOption" scope="page" value="${ '2,6' }"/>
        <jstl:set var="jsp_previousMsg" scope="page" value="Previous page"/>
        <jstl:set var="jsp_pagePrevious" scope="page" value="/objectboard/cities"/>
        <%@include file="../../../../complements/jsp/navbar-header.jsp"%>
        <!--end ############################### navbar-header ###############################-->

        <div class="pcoded-main-container">
            <div class="pcoded-wrapper">
                <div class="pcoded-content">

                    <!--start ############################### page-header ###############################-->
                    <jstl:set var="jsp_titleFrom" scope="page" value="City Maintenance Form"/>
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
                                            <h5>City properties</h5>
                                        </div>
                                        <div class="card-block">
                                            <h4 class="sub-title">Basic Data</h4>
                                            <form method="post" name="form" id="uploadFrom" action="#!">
                                                <div class="form-group row">
                                                    <div class="col-sm-4 mb-2 mb-sm-0">
                                                        <label class="col-sm-4 col-form-label">Country :</label>
                                                    </div>
                                                    <div class="col-sm-4">
                                                        <jstl:choose>
                                                            <jstl:when test="${ rq_acciones == 'save' }">
                                                                <input type="text" name="p_country_name" class="form-control form-control-user"
                                                                       value='<jstl:out value="${ rq_countryName }">Lost Value</jstl:out>' disabled>
                                                                <input type="hidden" name="p_country_number" value='<jstl:out value="${ rq_countryNumber }">Lost Value</jstl:out>'>
                                                            </jstl:when>
                                                            <jstl:otherwise>
                                                                <select name="p_country_number" tabindex="2" class="form-control" id="country" onChange="agregarOpciones(this.form)" >
                                                                    <option selected value='0'>Select Country</option>
                                                                    <jstl:forEach var="x" begin="0" end="${ rq_largoPaises - 1 }" step="1">
                                                                        <option value='<jstl:out value="${ rq_paises.get(x).getCoCountryCode()}"/>'>
                                                                            <jstl:out value="${ rq_paises.get(x).getCoName()}"/>
                                                                        </option>
                                                                    </jstl:forEach>
                                                                </select>
                                                            </jstl:otherwise>
                                                        </jstl:choose>
                                                    </div>
                                                    <div class="col-sm-4 mb-2 mb-sm-0">&nbsp;&nbsp;</div>
                                                </div>
                                                <div class="form-group row">
                                                    <div class="col-sm-4 mb-2 mb-sm-0">
                                                        <label class="col-sm-4 col-form-label">State :</label>
                                                    </div>
                                                    <div class="col-sm-4">
                                                        <jstl:choose>
                                                            <jstl:when test="${ rq_acciones == 'save' }">
                                                                <input type="text" name="p_state_name" class="form-control form-control-user"
                                                                       value='<jstl:out value="${ rq_stateName }">Lost Value</jstl:out>' disabled>
                                                                <input type="hidden" name="p_state_number" value='<jstl:out value="${ rq_stateNumber }">Lost Value</jstl:out>'>
                                                            </jstl:when>
                                                            <jstl:otherwise>
                                                                <select name="p_state_number" tabindex="2" class="form-control" id="state">
                                                                    <option selected value='0'>Select State</option>
                                                                </select>
                                                            </jstl:otherwise>
                                                        </jstl:choose>
                                                    </div>
                                                    <div class="col-sm-4 mb-2 mb-sm-0">&nbsp;&nbsp;</div>
                                                </div>
                                                <div class="form-group row">
                                                    <div class="col-sm-4 mb-2 mb-sm-0">
                                                        <label class="col-sm-4 col-form-label">City Name</label>
                                                    </div>
                                                    <div class="col-sm-4">
                                                        <jstl:choose>
                                                            <jstl:when test="${ rq_acciones == 'save' }">
                                                                <input type="text" name="p_name" class="form-control form-control-user" id="InputName"
                                                                       placeholder="City Name" maxlength="40" value='<jstl:out value="${ rq_cityName }">Lost Value</jstl:out>'>
                                                                <input type="hidden" name="p_city_number" value='<jstl:out value="${ rq_cityNumber }">Lost Value</jstl:out>'>
                                                            </jstl:when>
                                                            <jstl:otherwise>
                                                                <input type="text" name="p_name" class="form-control form-control-user" id="InputName" placeholder="City Name" maxlength="40">
                                                            </jstl:otherwise>
                                                        </jstl:choose>
                                                        <input type="hidden" name="p_acciones" id="acciones" value="<jstl:out value="${ rq_acciones }">Lost Value</jstl:out>">
                                                        <input name='p_pantalla' type='hidden' value='cities' />
                                                    </div>
                                                    <div class="col-sm-4 mb-2 mb-sm-0">
                                                        &nbsp;&nbsp;
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
<!--end ############################### footer_basic_script ###############################-->
</body>

</html>
