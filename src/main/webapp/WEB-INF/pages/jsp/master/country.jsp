<!DOCTYPE html>
<html lang="en">
<!--start ############################### header ###############################-->
<head>
    <title>Country maintenance</title>
    <!-- Favicon icon -->
    <link rel="icon" href="<%= request.getContextPath() %>/complements/assets/images/favicon.ico" type="image/x-icon">
    <%@include file="../../../../complements/jsp/header.jsp"%>

    <!-- Propias -->
    <script src="<%= request.getContextPath() %>/complements/scripts/validaciones.js" type="text/javascript"></script>
    <script type="text/javascript">

        function Validaciones(){
            if (!valida_textos(document.forma.p_name.value,"Country's name","")||
                !valida_largos(document.forma.p_name.value.length,"Country's name",3)
            ){
                return false;
            }
            return true;
        }

        function registro(){
            if(Validaciones()){
                document.forma.action="/objectboard/countriesprocess";
                document.forma.submit();
            }
        }
    </script>
</head>
<!--end ############################### header ###############################-->
<!--ul-->
<body>
<!-- Pre-loader start
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
Pre-loader end -->
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
        <jstl:set var="jsp_pagePrevious" scope="page" value="/objectboard/countries"/>
        <%@include file="../../../../complements/jsp/navbar-header.jsp"%>
<!--end ############################### navbar-header ###############################-->

        <div class="pcoded-main-container">
            <div class="pcoded-wrapper">
                <div class="pcoded-content">

<!--start ############################### page-header ###############################-->
        <jstl:set var="jsp_titleFrom" scope="page" value="Country Maintenance Form"/>
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
                        <h5>Country properties</h5>
                    </div>
                    <div class="card-block">
                        <h4 class="sub-title">Basic Data</h4>
                        <form method="post" name="forma" id="uploadFrom" action="#!">
                            <div class="form-group row">
                                <div class="col-sm-4 mb-2 mb-sm-0">
                                    <label class="col-sm-4 col-form-label">Country Name</label>
                                </div>
                                <div class="col-sm-4">
                                    <jstl:choose>
                                        <jstl:when test="${ rq_acciones == 'save' }">
                                            <input type="text" name="p_name" class="form-control form-control-user" id="InputName"
                                                   placeholder="Country Name" maxlength="40" value='<jstl:out value="${ rq_countryName }">Lost Value</jstl:out>'>
                                            <input type="hidden" name="p_pais" id="pais" value="<jstl:out value="${ rq_countryNumber }">Lost Value</jstl:out>">
                                        </jstl:when>
                                        <jstl:otherwise>
                                            <input type="text" name="p_name" class="form-control form-control-user" id="InputName" placeholder="Country Name" maxlength="40">
                                        </jstl:otherwise>
                                    </jstl:choose>
                                    <input type="hidden" name="p_acciones" id="acciones" value="<jstl:out value="${ rq_acciones }">Lost Value</jstl:out>">
                                    <input name='p_pantalla' type='hidden' value='paises' />
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
                            &nbsp;    &nbsp;
                        </div>
                    </div>

            <jstl:forEach var="x" begin="1" end="${ 6 }" step="1">
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
