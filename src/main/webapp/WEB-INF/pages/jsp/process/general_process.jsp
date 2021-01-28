l<%--
  Created by IntelliJ IDEA.
  User: pavelaz
  Date: 17/3/20
  Time: 2:10 p. m.
  To change this template use File | Settings | File Templates.
  <%@ page contentType="text/html;charset=UTF-8" language="java" %>
--%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Paul Velazquez">
    <jstl:choose>
        <jstl:when test="${ rq_pantalla == 'paises' }"><title>Country Process</title></jstl:when>
        <jstl:when test="${ rq_pantalla == 'states' }"><title>States Process</title></jstl:when>
        <jstl:when test="${ rq_pantalla == 'cities' }"><title>Cities Process</title></jstl:when>
        <jstl:when test="${ rq_pantalla == 'roles' }"><title>User Roles Process</title></jstl:when>
        <jstl:when test="${ rq_pantalla == 'discharges' }"><title>Discharges Process</title></jstl:when>
        <jstl:when test="${ rq_pantalla == 'bussinessunit' }"><title>Bussiness Unit Process</title></jstl:when>
        <jstl:when test="${ rq_pantalla == 'userprofile' }"><title>User Profile Process</title></jstl:when>
        <jstl:when test="${ rq_pantalla == 'userprofile1' }"><title>User Profile Process</title></jstl:when>
        <jstl:when test="${ rq_pantalla == 'userprofile2' }"><title>User Profile Process</title></jstl:when>
        <jstl:when test="${ rq_pantalla == 'organization' }"><title>Organizations Process</title></jstl:when>
        <jstl:when test="${ rq_pantalla == 'typifieds' }"><title>Typifieds Process</title></jstl:when>
        <jstl:when test="${ rq_pantalla == 'certifications' }"><title>Certifications Process</title></jstl:when>
        <jstl:when test="${ rq_pantalla == 'headerpolls' }"><title>Header Polls Process</title></jstl:when>
        <jstl:when test="${ rq_pantalla == 'questionspolls' }"><title>Questions Polls Process</title></jstl:when>
        <jstl:when test="${ rq_pantalla == 'answerrequest' }"><title>Answer Request Process</title></jstl:when>
        <jstl:when test="${ rq_pantalla == 'comments' }"><title>Comments Process</title></jstl:when>
        <jstl:when test="${ rq_pantalla == 'asignments' }"><title>Assignments Process</title></jstl:when>
        <jstl:when test="${ rq_pantalla == 'conductsurvey' }"><title>Conduct Survey Process</title></jstl:when>
        <jstl:when test="${ rq_pantalla == 'auditsrevision' }"><title>Audits Conduct Process</title></jstl:when>
    </jstl:choose>

    <jstl:choose>
        <jstl:when test="${ !rq_result }">
            <!--<title>Country Process</title>-->
            <!-- Custom fonts for this template-->
            <link href="<%= request.getContextPath() %>/complements/bootstrap/bootstrap_4/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
            <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

            <!-- Custom styles for this template-->
            <link href="<%= request.getContextPath() %>/complements/bootstrap/bootstrap_4/css/sb-admin-2.min.css" rel="stylesheet">

            <!-- Scripts propios -->
            <link rel="stylesheet" href="<%= request.getContextPath() %>/complements/css/own_style.css">
            <script src="<%= request.getContextPath() %>/complements/scripts/validaciones.js" type="text/javascript"></script>
            <link rel="shortcut icon" type="image/png" href="<%= request.getContextPath() %>/complements/img/favicon.png">

            <script type="text/javascript">
                function continar(){
                    <jstl:choose>
                        <jstl:when test="${ rq_pantalla == 'headerpolls' }">
                            <jstl:choose>
                                <jstl:when test="${ rq_questionTipo == 0 }">
                                    document.forma.action="/objectboard/signin";
                                </jstl:when>
                                <jstl:otherwise>
                                    document.forma.action="/objectboard/headerpolls";
                                </jstl:otherwise>
                            </jstl:choose>
                        </jstl:when>
                        <jstl:otherwise>
                            document.forma.action="/objectboard/signin";
                        </jstl:otherwise>
                    </jstl:choose>
                    document.forma.submit();
                }
            </script>
        </jstl:when>
        <jstl:otherwise>
            <script language="JavaScript" type="text/javascript">
                function Salir(valor){
                    if(valor == 1 ) // continua a pagina que muestra modulos disponibles a ese usuario
                        <jstl:choose>
                            <jstl:when test="${ rq_pantalla == 'paises' }">document.forma.action="/objectboard/countries";</jstl:when>
                            <jstl:when test="${ rq_pantalla == 'states' }">document.forma.action="/objectboard/states";</jstl:when>
                            <jstl:when test="${ rq_pantalla == 'cities' }">document.forma.action="/objectboard/cities";</jstl:when>
                            <jstl:when test="${ rq_pantalla == 'roles' }">document.forma.action="/objectboard/userroles";</jstl:when>
                            <jstl:when test="${ rq_pantalla == 'discharges' }">document.forma.action="/objectboard/discharges";</jstl:when>
                            <jstl:when test="${ rq_pantalla == 'bussinessunit' }">document.forma.action="/objectboard/bussinessunits";</jstl:when>
                            <jstl:when test="${ rq_pantalla == 'userprofile' }">document.forma.action="/objectboard/userprofileconsult";</jstl:when>
                            <jstl:when test="${ rq_pantalla == 'userprofile1' }">document.forma.action="/objectboard/mastermenusuper";</jstl:when>
                            <jstl:when test="${ rq_pantalla == 'userprofile2' }">document.forma.action="/objectboard/dashmenusuper";</jstl:when>
                            <jstl:when test="${ rq_pantalla == 'organization' }">document.forma.action="/objectboard/organizations";</jstl:when>
                            <jstl:when test="${ rq_pantalla == 'typifieds' }">document.forma.action="/objectboard/typifieds";</jstl:when>
                            <jstl:when test="${ rq_pantalla == 'certifications' }">document.forma.action="/objectboard/certifications";</jstl:when>
                            <jstl:when test="${ rq_pantalla == 'headerpolls' }">document.forma.action="/objectboard/headerpolls";</jstl:when>
                            <jstl:when test="${ rq_pantalla == 'questionspolls' }">document.forma.action="/objectboard/bodysurveyquestions";</jstl:when>
                            <jstl:when test="${ rq_pantalla == 'answerrequest' }">document.forma.action="/objectboard/answerssurveyrequests";</jstl:when>
                            <jstl:when test="${ rq_pantalla == 'comments' }">document.forma.action="/objectboard/comment";</jstl:when>
                            <jstl:when test="${ rq_pantalla == 'asignments' }">document.forma.action="/objectboard/assignments";</jstl:when>
                            <jstl:when test="${ rq_pantalla == 'conductsurvey' }">document.forma.action="/objectboard/conduct";</jstl:when>
                            <jstl:when test="${ rq_pantalla == 'auditsrevision' }">document.forma.action="/objectboard/audits";</jstl:when>
                        </jstl:choose>
                    document.forma.submit();
                }
            </script>
        </jstl:otherwise>
    </jstl:choose>
</head>
<jstl:choose>
    <jstl:when test="${ !rq_result }">
        <body class="bg-gradient-success">
        <div class="header__logo-box">
            <br>
            <a href="<%= request.getContextPath() %>/index.jsp">
                &nbsp;&nbsp;&nbsp; <img src="<%= request.getContextPath() %>/complements/img/logo-white1.png" alt="Logo" width="78" height="40" class="header__logo" href="<%= request.getContextPath() %>/index.jsp">
            </a>
        </div>
        <div class="container">

            <!-- Outer Row -->
            <div class="row justify-content-center">

                <div class="col-xl-10 col-lg-12 col-md-9">

                    <div class="card o-hidden border-0 shadow-lg my-5">
                        <div class="card-body p-0">
                            <!-- Nested Row within Card Body -->
                            <div class="row">
                                <!--<div class="col-lg-6 d-none d-lg-block bg-password-image"></div>-->
                                <div class="col-lg-12">
                                    <div class="p-5">
                                        <div class="text-center">
                                            <jstl:choose>
                                                <jstl:when test="${ !rq_result }">
                                                    <h1 class="h4 text-gray-900 mb-2 alert-warning">Process Error: </h1>
                                                    <jstl:choose>
                                                        <jstl:when test="${ rq_pantalla == 'headerpolls' }">
                                                            <jstl:choose>
                                                                <jstl:when test="${ rq_questionTipo == 0 }">
                                                                    <p class="mb-4">An error occurred during the process of inserting, updating or deleting to be executed in the database. Contact your system administrator.</p>
                                                                </jstl:when>
                                                                <jstl:when test="${ rq_questionTipo == 1 }">
                                                                    <p class="mb-4">There are (${ rq_questionCtos }) Boolean type questions without the total number of associated answers allowed, or without a solution.</p>
                                                                </jstl:when>
                                                                <jstl:when test="${ rq_questionTipo == 2 }">
                                                                    <p class="mb-4">There are (${ rq_questionCtos }) Unique Selection questions without the total number of associated answers allowed, or without solution.</p>
                                                                </jstl:when>
                                                                <jstl:when test="${ rq_questionTipo == 3 }">
                                                                    <p class="mb-4">There are (${ rq_questionCtos }) Multi Solution questions without the total number of associated answers allowed, or without the total number of solutions allowed.</p>
                                                                </jstl:when>
                                                                <jstl:when test="${ rq_questionTipo == 6 }">
                                                                    <p class="mb-4">There are (${ rq_questionCtos }) text input type questions without the total number of associated answers allowed, or without solution.</p>
                                                                </jstl:when>
                                                                <jstl:when test="${ rq_questionTipo == 7 }">
                                                                    <p class="mb-4">There are (${ rq_questionCtos }) Numeric input type questions without the total number of associated answers allowed, or without solution.</p>
                                                                </jstl:when>
                                                                <jstl:when test="${ rq_questionTipo == 8 }">
                                                                    <p class="mb-4">There are (${ rq_questionCtos }) Time input type questions without the total number of associated answers allowed, or without solution.</p>
                                                                </jstl:when>
                                                                <jstl:when test="${ rq_questionTipo == 9 }">
                                                                    <p class="mb-4">There are (${ rq_questionCtos }) Date input type questions without the total number of associated answers allowed, or without solution.</p>
                                                                </jstl:when>
                                                            </jstl:choose>
                                                        </jstl:when>
                                                        <jstl:otherwise>
                                                            <p class="mb-4">An error occurred during the process of inserting, updating or deleting to be executed in the database. Contact your system administrator.</p>
                                                        </jstl:otherwise>
                                                    </jstl:choose>
                                                </jstl:when>
                                            </jstl:choose>
                                        </div>
                                        <form class="user" id="forma" name="forma" method="post" action="">
                                            <br>
                                            <a href="#" onClick="continar()" class="btn btn-primary btn-user btn-block">
                                                Continue
                                            </a>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>

            </div>

        </div>
        <!-- Bootstrap core JavaScript-->
        <script src="<%= request.getContextPath() %>/complements/bootstrap/bootstrap_4/vendor/jquery/jquery.min.js"></script>
        <script src="<%= request.getContextPath() %>/complements/bootstrap/bootstrap_4/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

        <!-- Core plugin JavaScript-->
        <script src="<%= request.getContextPath() %>/complements/bootstrap/bootstrap_4/vendor/jquery-easing/jquery.easing.min.js"></script>

        <!-- Custom scripts for all pages-->
        <script src="<%= request.getContextPath() %>/complements/bootstrap/bootstrap_4/js/sb-admin-2.min.js"></script>
    </jstl:when>
    <jstl:otherwise>
        <body>
        <form name="forma" method="post" action="" >
            <jstl:choose>
                <jstl:when test="${ rq_pantalla == 'questionspolls' }">
                    <input name="p_code" value="${ rq_code }" type="hidden">
                    <input name="p_names" value="${ rq_names }" type="hidden">
                    <input name="p_refes" value="${ rq_refes }" type="hidden">
                </jstl:when>
                <jstl:when test="${ rq_pantalla == 'answerrequest' }">
                    <input name="p_code" value="${ rq_code }" type="hidden">
                    <input name="p_names" value="${ rq_names }" type="hidden">
                    <input name="p_refes" value="${ rq_refes }" type="hidden">
                    <input name="p_request_type" value="${ rq_requestType }" type="hidden">
                    <input name="p_request_code" value="${ rq_requestCode }" type="hidden">
                    <input name="p_request_main" value="${ rq_requestMain }" type="hidden">
                </jstl:when>
            </jstl:choose>
            <SCRIPT LANGUAGE='JavaScript' type='text/javascript'>Salir(1);</SCRIPT>;
        </form>
    </jstl:otherwise>
</jstl:choose>
</body>

</html>
