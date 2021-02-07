<!DOCTYPE html>
<html lang="en">
<!--start ############################### header ###############################-->
<head>
    <%@include file="../../../../complements/jsp/head_menBasic_script.jsp"%>
    <script language="JavaScript" type="text/javascript">
        function Salir(){
            //alert("paso");
            document.forma.action="/objectboard/logout"; // esto llama la serlet
            document.forma.submit();
        }
        function send_report(num){
            //alert("paso");
            // server_angular = "http://192.168.1.245:4200";
            var server_angular = "http://192.168.1.245:4300";

            var datos ="<jstl:out value='${ rq_dataUser }'>Lost Value</jstl:out>" + "/" +
                "<jstl:out value='${ rq_dataPassword }'>Lost Value</jstl:out>" + "/" +
                "<jstl:out value='${ rq_companyNumber }'>Lost Value</jstl:out>" + "/" +
                "<jstl:out value='${ rq_companyName }'>Lost Value</jstl:out>" + "/" +
                "<jstl:out value='${ rq_userName }'>Lost Value</jstl:out>" +"/" +
                "<jstl:out value='${ rq_userEmail }'>Lost Value</jstl:out>";

                if (num == 1.1){
                    var title_report = "Pdf Report";
                    var caption_table = "Information";
                    var footer_table = "Pdf processes pending to be executed.";

                    document.form_send.action = server_angular + "/pdf/" + datos + "/" +
                        title_report + "/" + caption_table + "/" + footer_table;
                }
                if (num == 1.2){
                    var title_report = "Assignments Report";
                    var caption_table = "Information";
                    var footer_table = "Assignment processes pending to be executed.";

                    document.form_send.action = server_angular + "/assignmentsReport/" + datos + "/" +
                        title_report + "/" + caption_table + "/" + footer_table;
                }
                if (num == 1.3){
                    var title_report = "Surveys Report";
                    var caption_table = "Information";
                    var footer_table = "Survey processes pending to be executed.";

                    document.form_send.action = server_angular + "/headersPollsReport/" + datos + "/" +
                        title_report + "/" + caption_table + "/" + footer_table;
                }
                if (num == 1.4){
                    var title_report = "Conducts Report";
                    var caption_table = "Information";
                    var footer_table = "Conduct processes pending to be executed.";

                    document.form_send.action = server_angular + "/conductsReport/" + datos + "/" +
                        title_report + "/" + caption_table + "/" + footer_table;
                }
                if (num == 1.5){
                    var title_report = "Audits Report";
                    var caption_table = "Information";
                    var footer_table = "Audit processes pending to be executed.";

                    document.form_send.action = server_angular + "/auditsReport/" + datos + "/" +
                        title_report + "/" + caption_table + "/" + footer_table;
                                    }

                if (num == 2.1){
                    var title_report = "Organizations Report";
                    var caption_table = "Information";
                    var footer_table = "Organizations processes pending to be executed.";

                    document.form_send.action = server_angular + "/organizationsReport/" + datos + "/" +
                        title_report + "/" + caption_table + "/" + footer_table;
                }
                if (num == 2.2){
                    var title_report = "Typifieds Report";
                    var caption_table = "Information";
                    var footer_table = "Typified processes pending to be executed.";

                    document.form_send.action = server_angular + "/typifiedsReport/" + datos + "/" +
                        title_report + "/" + caption_table + "/" + footer_table;
                }
                if (num == 2.3){
                    var title_report = "Certifications Report";
                    var caption_table = "Information";
                    var footer_table = "Certification processes pending to be executed.";

                    document.form_send.action = server_angular + "/certificationsReport/" + datos + "/" +
                        title_report + "/" + caption_table + "/" + footer_table;
                }
            document.form_send.submit();
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
    <div class="pcoded-overlay-box"></div>
    <div class="pcoded-container navbar-wrapper">

    <!--start ############################### navbar ###############################-->
    <nav class="pcoded-navbar">
    <!--  Navegacion = 1, Miscelaneos Main Files = 2, General Main Files = 3, .-->
    <!--  No repetir la opcion del menu si hay submenu 5-1.2.3.4-2.2.3.4.5.-->
    <jstl:choose>
         <jstl:when test="${rq_userBoardRole == 1}">
                <jstl:set var="jsp_navBarOption" scope="page" value="${ '1,2.1.3.4.5.6,3.1.2.4.5,5.1.2' }"/>
                <jstl:set var="jsp_reposrt1Option" scope="page" value="${ '1.2.3' }"/>
                <jstl:set var="jsp_reposrt2Option" scope="page" value="${ '1.2.3.4' }"/>
            </jstl:when>
         <jstl:when test="${rq_userBoardRole == 2}">
                <jstl:set var="jsp_navBarOption" scope="page"
                          value="${ '1,2.1.3.4.5.6,3.1.2.5' }"/>
            </jstl:when>
         <jstl:when test="${rq_userBoardRole == 3}">
                <jstl:set var="jsp_navBarOption" scope="page" value="${ '1,3.2.4.5' }"/>
            </jstl:when>
         <jstl:when test="${rq_userBoardRole == 4}">
                <jstl:set var="jsp_navBarOption" scope="page" value="${ '1,3.4' }"/>
            </jstl:when>
    </jstl:choose>
    <%@include file="../../../../complements/jsp/navbar.jsp"%>
    </nav>
    <!--end ############################### navbar ###############################-->

    <!--start ############################### navbar-header ###############################-->
    <nav class="navbar header-navbar pcoded-header">
    <!--Mostrar imagen = 'YES', No mostar imagen = 'NOT'-->
    <jstl:set var="jsp_ShowImage" scope="page" value="${ 'YES' }"/>
    <!--start ############## dropdown menu barra berde superior derecha ##############-->
    <!--    Settings = 1, Profile = 2, My Messages = 3,
            Lock Screen = 4, Logout = 5.-->
    <jstl:choose>
         <jstl:when test="${rq_userBoardRole == 1}">
             <jstl:set var="jsp_menuOption" scope="page" value="${ '1,2,3,4,7' }"/>
         </jstl:when>
         <jstl:when test="${rq_userBoardRole == 2}">
             <jstl:set var="jsp_menuOption" scope="page" value="${ '2,7' }"/>
         </jstl:when>
         <jstl:when test="${rq_userBoardRole == 3}">
             <jstl:set var="jsp_menuOption" scope="page" value="${ '2,7' }"/>
         </jstl:when>
         <jstl:when test="${rq_userBoardRole == 4}">
             <jstl:set var="jsp_menuOption" scope="page" value="${ '7' }"/>
         </jstl:when>
     </jstl:choose>
    <%-- <jstl:set var="jsp_menuOption" scope="page" value="${ '1,2,3,4,7' }"/> --%>
    <!--end ############## dropdown menu ################-->
    <%@include file="../../../../complements/jsp/navbar-header.jsp"%>
    </nav>
    <!--end ############################### navbar-header ###############################-->

        <div class="pcoded-main-container">
            <div class="pcoded-wrapper">
                <div class="pcoded-content">

                <!--start ############################### page-header ###############################-->
                <jstl:set var="jsp_titleFrom" scope="page" value="Dash menu super"/>
                <jstl:set var="jsp_funtionFrom" scope="page" value="${ 'Welcome Alex' }"/>
                <!--<jstl:set var="jsp_funtionFrom" scope="page" value="${application.name}"/>-->
                <!--<jstl:set var="jsp_funtionFrom" scope="page" value="Objectboard system."/>-->
                <jstl:set var="jsp_pagePrevious" scope="page" value="/objectboard/logout"/>
                <jstl:set var="jsp_previousMsg" scope="page" value="Previous menu"/>
                <%@include file="../../../../complements/jsp/page-header.jsp"%>
                <!--end ############################### page-header ###############################-->

                    <div class="pcoded-inner-content">
                        <div class="main-body">
                            <div class="page-wrapper">
                                <div class="page-body">
                                    <!-- Basic Form Inputs card start -->
                                    <div class="card">
                                        <div class="card-block"> <!--pp -->
<!--start ############################# MAIN BODY (DIFFERENT PROJECTS)###############################-->

    <!--START##########################################CUERPO PRINCIPAL#####################################################-->
        <div class="page-body">
            <div class="row">
                <!-- Material statustic card start -->
                <div class="col-xl-4 col-md-12">
                    <div class="card mat-stat-card">
                        <div class="card-block">
                            <div class="row align-items-center b-b-default">
                                <div class="col-sm-6 b-r-default p-b-20 p-t-20">
                                    <div class="row align-items-center text-center">
                                        <div class="col-4 p-r-0">
                                            <i class="far fa-user text-c-purple f-24"></i>
                                        </div>
                                        <div class="col-8 p-l-0">
                                            <h5>10K</h5>
                                            <p class="text-muted m-b-0">Visitors</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-6 p-b-20 p-t-20">
                                    <div class="row align-items-center text-center">
                                        <div class="col-4 p-r-0">
                                            <i class="fas fa-volume-down text-c-green f-24"></i>
                                        </div>
                                        <div class="col-8 p-l-0">
                                            <h5>100%</h5>
                                            <p class="text-muted m-b-0">Volume</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row align-items-center">
                                <div class="col-sm-6 p-b-20 p-t-20 b-r-default">
                                    <div class="row align-items-center text-center">
                                        <div class="col-4 p-r-0">
                                            <i class="far fa-file-alt text-c-red f-24"></i>
                                        </div>
                                        <div class="col-8 p-l-0">
                                            <h5>2000+</h5>
                                            <p class="text-muted m-b-0">Files</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-6 p-b-20 p-t-20">
                                    <div class="row align-items-center text-center">
                                        <div class="col-4 p-r-0">
                                            <i class="far fa-envelope-open text-c-blue f-24"></i>
                                        </div>
                                        <div class="col-8 p-l-0">
                                            <h5>120</h5>
                                            <p class="text-muted m-b-0">Mails</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-xl-4 col-md-12">
                    <div class="card mat-stat-card">
                        <div class="card-block">
                            <div class="row align-items-center b-b-default">
                                <div class="col-sm-6 b-r-default p-b-20 p-t-20">
                                    <div class="row align-items-center text-center">
                                        <div class="col-4 p-r-0">
                                            <i class="fas fa-share-alt text-c-purple f-24"></i>
                                        </div>
                                        <div class="col-8 p-l-0">
                                            <h5>1000</h5>
                                            <p class="text-muted m-b-0">Share</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-6 p-b-20 p-t-20">
                                    <div class="row align-items-center text-center">
                                        <div class="col-4 p-r-0">
                                            <i class="fas fa-sitemap text-c-green f-24"></i>
                                        </div>
                                        <div class="col-8 p-l-0">
                                            <h5>600</h5>
                                            <p class="text-muted m-b-0">Network</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row align-items-center">
                                <div class="col-sm-6 p-b-20 p-t-20 b-r-default">
                                    <div class="row align-items-center text-center">
                                        <div class="col-4 p-r-0">
                                            <i class="fas fa-signal text-c-red f-24"></i>
                                        </div>
                                        <div class="col-8 p-l-0">
                                            <h5>350</h5>
                                            <p class="text-muted m-b-0">Returns</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-6 p-b-20 p-t-20">
                                    <div class="row align-items-center text-center">
                                        <div class="col-4 p-r-0">
                                            <i class="fas fa-wifi text-c-blue f-24"></i>
                                        </div>
                                        <div class="col-8 p-l-0">
                                            <h5>100%</h5>
                                            <p class="text-muted m-b-0">Connections</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-xl-4 col-md-12">
                    <div class="card mat-clr-stat-card text-white green ">
                        <div class="card-block">
                            <div class="row">
                                <div class="col-3 text-center bg-c-green">
                                    <i class="fas fa-star mat-icon f-24"></i>
                                </div>
                                <div class="col-9 cst-cont">
                                    <h5>4000+</h5>
                                    <p class="m-b-0">Ratings Received</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="card mat-clr-stat-card text-white blue">
                        <div class="card-block">
                            <div class="row">
                                <div class="col-3 text-center bg-c-blue">
                                    <i class="fas fa-trophy mat-icon f-24"></i>
                                </div>
                                <div class="col-9 cst-cont">
                                    <h5>17</h5>
                                    <p class="m-b-0">Achievements</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Material statustic card end -->
                <!-- order-visitor start -->


                <!-- order-visitor end -->

                <!--  sale analytics start -->
                <div class="col-xl-6 col-md-12">
                    <div class="card table-card">
                        <div class="card-header">
                            <h5>Member’s performance</h5>
                            <div class="card-header-right">
                                <ul class="list-unstyled card-option">
                                    <li><i class="fa fa fa-wrench open-card-option"></i></li>
                                    <li><i class="fa fa-window-maximize full-card"></i></li>
                                    <li><i class="fa fa-minus minimize-card"></i></li>
                                    <li><i class="fa fa-refresh reload-card"></i></li>
                                    <li><i class="fa fa-trash close-card"></i></li>
                                </ul>
                            </div>
                        </div>
                        <div class="card-block">
                            <div class="table-responsive">
                                <table class="table table-hover m-b-0 without-header">
                                    <tbody>
                                    <tr>
                                        <td>
                                            <div class="d-inline-block align-middle">
                                                <img src="<%= request.getContextPath() %>/complements/assets/images/avatar-4.jpg" alt="user image" class="img-radius img-40 align-top m-r-15">
                                                <div class="d-inline-block">
                                                    <h6>Shirley Hoe</h6>
                                                    <p class="text-muted m-b-0">Sales executive , NY</p>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="text-right">
                                            <h6 class="f-w-700">$78.001<i class="fas fa-level-down-alt text-c-red m-l-10"></i></h6>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <div class="d-inline-block align-middle">
                                                <img src="<%= request.getContextPath() %>/complements/assets/images/avatar-2.jpg" alt="user image" class="img-radius img-40 align-top m-r-15">
                                                <div class="d-inline-block">
                                                    <h6>James Alexander</h6>
                                                    <p class="text-muted m-b-0">Sales executive , EL</p>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="text-right">
                                            <h6 class="f-w-700">$89.051<i class="fas fa-level-up-alt text-c-green m-l-10"></i></h6>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <div class="d-inline-block align-middle">
                                                <img src="<%= request.getContextPath() %>/complements/assets/images/avatar-4.jpg" alt="user image" class="img-radius img-40 align-top m-r-15">
                                                <div class="d-inline-block">
                                                    <h6>Shirley Hoe</h6>
                                                    <p class="text-muted m-b-0">Sales executive , NY</p>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="text-right">
                                            <h6 class="f-w-700">$89.051<i class="fas fa-level-up-alt text-c-green m-l-10"></i></h6>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <div class="d-inline-block align-middle">
                                                <img src="<%= request.getContextPath() %>/complements/assets/images/avatar-2.jpg" alt="user image" class="img-radius img-40 align-top m-r-15">
                                                <div class="d-inline-block">
                                                    <h6>Nick Xander</h6>
                                                    <p class="text-muted m-b-0">Sales executive , EL</p>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="text-right">
                                            <h6 class="f-w-700">$89.051<i class="fas fa-level-up-alt text-c-green m-l-10"></i></h6>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>

                            </div>
                        </div>
                    </div>
                </div>
                <!-- Primer Bloque  Inicio -->
                <div class="col-xl-6 col-md-12">
                    <div class="row">
                        <!-- sale card start -->
                        <!-- Objeto Uno Inicio -->
                        <div class="col-md-6">
                            <div class="card text-center order-visitor-card">
                                <div class="card-block">
                                    <h6 class="m-b-0">Total Survey Executions</h6>
                                    <h4 class="m-t-15 m-b-15"><i class="fa fa-arrow-down m-r-15 text-c-red"></i><jstl:out value="${ rq_totalSurvey }">Lost Value</jstl:out></h4>
                                    <p class="m-b-0"><jstl:out value="${ rq_porcLast24 }">Lost Value</jstl:out> % From Last 24 Hours</p>
                                </div>
                            </div>
                        </div>
                        <!-- Objeto Uno Final -->
                        <!-- Objeto Seis Inicio -->
                        <div class="col-md-6">
                            <div class="card text-center order-visitor-card">
                                <div class="card-block">
                                    <h6 class="m-b-0">Total Polls Created</h6>
                                    <h4 class="m-t-15 m-b-15"><i class="fa fa-arrow-up m-r-15 text-c-green"></i><jstl:out value="${ rq_totalPolls }">Lost Value</jstl:out></h4>
                                    <p class="m-b-0"><jstl:out value="${ rq_porcLast3 }">Lost Value</jstl:out> % From Last 3 Months</p>
                                </div>
                            </div>
                        </div>
                        <!-- Objeto Seis Final -->
                        <!-- Objeto Dos Inicio -->
                        <div class="col-md-6">
                            <div class="card bg-c-red total-card">
                                <div class="card-block">
                                    <div class="text-left">
                                        <h4><jstl:out value="${ rq_auditManual }">Lost Value</jstl:out></h4>
                                        <p class="m-0">Total Manual Audits</p>
                                    </div>
                                    <span class="label bg-c-red value-badges"><jstl:out value="${ rq_porcManual }">Lost Value</jstl:out> %</span>
                                </div>
                            </div>
                        </div>
                        <!-- Objeto Dos Final -->
                        <!-- Objeto Tres Inicio -->
                        <div class="col-md-6">
                            <div class="card bg-c-green total-card">
                                <div class="card-block">
                                    <div class="text-left">
                                        <h4><jstl:out value="${ rq_auditAuto }">Lost Value</jstl:out></h4>
                                        <p class="m-0">Total Auto Audits</p>
                                    </div>
                                    <span class="label bg-c-green value-badges"><jstl:out value="${ rq_porcAuto }">Lost Value</jstl:out> %</span>
                                </div>
                            </div>
                        </div>
                        <!-- Objeto Tres Final -->
                        <!-- Objeto Cuatro Inicial -->
                        <div class="col-md-6">
                            <div class="card text-center order-visitor-card">
                                <div class="card-block">
                                    <h6 class="m-b-0">Pending Audits</h6>
                                    <h4 class="m-t-15 m-b-15"><i class="fa fa-arrow-down m-r-15 text-c-red"></i><jstl:out value="${ rq_auditPend }">Lost Value</jstl:out></h4>
                                    <p class="m-b-0"><jstl:out value="${ rq_porcPend }">Lost Value</jstl:out> % From the <jstl:out value="${ rq_totalSurvey }">Lost Value</jstl:out> total</p>
                                </div>
                            </div>
                        </div>
                        <!-- Objeto Cuatro Final -->
                        <!-- Objeto Quinto Inicial -->
                        <div class="col-md-6">
                            <div class="card text-center order-visitor-card">
                                <div class="card-block">
                                    <h6 class="m-b-0">Audits Performed</h6>
                                    <h4 class="m-t-15 m-b-15"><i class="fa fa-arrow-up m-r-15 text-c-green"></i><jstl:out value="${ rq_auditAudit }">Lost Value</jstl:out></h4>
                                    <p class="m-b-0"><jstl:out value="${ rq_porcAudit }">Lost Value</jstl:out> % From the <jstl:out value="${ rq_totalSurvey }">Lost Value</jstl:out> total</p>
                                </div>
                            </div>
                        </div>
                        <!-- Objeto Quinto Final -->
                        <!-- sale card end -->
                    </div>
                </div>
                <!-- Primer Bloque  Final -->
                <!--  sale analytics end -->
                <form name="forma" method="post" action="">
                    <input type="hidden" name="p_company_number" value="<jstl:out value="${ rq_companyNumber }">Lost Value</jstl:out>">
                    <input type="hidden" name="p_company_name" value="<jstl:out value="${ rq_companyName }">Lost Value</jstl:out>">
                    <input type="hidden" name="p_company_logo" value="<jstl:out value="${ rq_companyLogoName }">Lost Value</jstl:out>">
                    <input type="hidden" name="p_email" value="<jstl:out value="${ rq_userEmail }">Lost Value</jstl:out>">
                    <input type="hidden" name="p_name" value="<jstl:out value="${ rq_userName }">Lost Value</jstl:out>">
                    <input type="hidden" name="p_mostrar_cierre" value="F">
                </form>
                <form name="form_send" style="display: none" method="get" target="_blank"
                      <%--action="http://localhost:4200/pdf/${ rq_dataUser }/${ rq_dataPassword }/${ rq_companyNumber }/${ rq_companyName }/${ rq_userName }/${ rq_userEmail }/${ rq_title_report }/${ rq_caption_table }/${ rq_footer_table }/${ rq_data_logo }"--%>>
                </form>
                <!-- Project statustic start -->
                <div class="col-xl-12">
                    <div class="card proj-progress-card">
                        <div class="card-block">
                            <div class="row">
                                <div class="col-xl-3 col-md-6">
                                    <h6>Published Project</h6>
                                    <h5 class="m-b-30 f-w-700">532<span class="text-c-green m-l-10">+1.69%</span></h5>
                                    <div class="progress">
                                        <div class="progress-bar bg-c-red" style="width:25%"></div>
                                    </div>
                                </div>
                                <div class="col-xl-3 col-md-6">
                                    <h6>Completed Task</h6>
                                    <h5 class="m-b-30 f-w-700">4,569<span class="text-c-red m-l-10">-0.5%</span></h5>
                                    <div class="progress">
                                        <div class="progress-bar bg-c-blue" style="width:65%"></div>
                                    </div>
                                </div>
                                <div class="col-xl-3 col-md-6">
                                    <h6>Successfull Task</h6>
                                    <h5 class="m-b-30 f-w-700">89%<span class="text-c-green m-l-10">+0.99%</span></h5>
                                    <div class="progress">
                                        <div class="progress-bar bg-c-green" style="width:85%"></div>
                                    </div>
                                </div>
                                <div class="col-xl-3 col-md-6">
                                    <h6>Ongoing Project</h6>
                                    <h5 class="m-b-30 f-w-700">365<span class="text-c-green m-l-10">+0.35%</span></h5>
                                    <div class="progress">
                                        <div class="progress-bar bg-c-yellow" style="width:45%"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Project statustic end -->
            </div>
        </div>

    <!--END##########################################CUERPO PRINCIPAL#####################################################-->

<!--end ############################### MAIN BODY (DIFFERENT PROJECTS)###############################-->
                                        </div> <!-- pp -->
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
<!--end ############################### section JavaScript ###############################-->
<!--end ############################### body ###############################-->
</body>
<jstl:remove var="CommentsList"/>
<jstl:remove var="CompanyNumber"/>
</html>
