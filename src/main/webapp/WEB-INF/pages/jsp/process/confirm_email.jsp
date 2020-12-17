<%--
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

    <title>Forgot Process</title>

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
        function continar(opcion){
            if (opcion == 1 || opcion == 2)
                document.forma.action="<%= request.getContextPath() %>/index.jsp";
            else
                document.forma.action="/objectboard/signin";
            document.forma.submit();
        }
    </script>

</head>

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
                                        <jstl:when test="${!rq_existePrevio}">
                                            <jstl:choose>
                                                <jstl:when test="${rq_result}">
                                                    <jstl:choose>
                                                        <jstl:when test="${!rq_existeUsuario}">
                                                            <h1 class="h4 text-gray-900 mb-2 alert-warning">Unregistered user</h1>
                                                            <p class="mb-4">There is no user with the provided email address registered for this company in our files, verify the information and try to register again.</p>
                                                        </jstl:when>
                                                        <jstl:otherwise>
                                                            <jstl:choose>
                                                                <jstl:when test="${!rq_existeSerial}">
                                                                    <h1 class="h4 text-gray-900 mb-2 alert-warning">Unrecognized serial</h1>
                                                                    <p class="mb-4">The serial with which you intend to activate the account does not match the one stored in our files. Please contact your system administrator for help.</p>
                                                                </jstl:when>
                                                                <jstl:otherwise>
                                                                    <h1 class="h4 text-gray-900 mb-2 alert-info">Satisfactory Email Confirmation</h1>
                                                                    <p class="mb-4">Your email address has been successfully confirmed and your account activated.</p>
                                                                </jstl:otherwise>
                                                            </jstl:choose>
                                                        </jstl:otherwise>
                                                    </jstl:choose>
                                                </jstl:when>
                                                <jstl:otherwise>
                                                    <h1 class="h4 text-gray-900 mb-2 alert-danger">Execution error in the update</h1>
                                                    <p class="mb-4">Please contact the system administrator for help.</p>
                                                </jstl:otherwise>
                                            </jstl:choose>
                                        </jstl:when>
                                        <jstl:otherwise>
                                            <h1 class="h4 text-gray-900 mb-2 alert-danger">Prior confirmation</h1>
                                            <p class="mb-4">Previously confirmed email address, This process is unnecessary.</p>
                                        </jstl:otherwise>
                                    </jstl:choose>
                                </div>
                                <form class="user" id="forma" name="forma" method="post" action="">
                                    <br>
                                    <jstl:choose>
                                        <jstl:when test="${!rq_existePrevio}">
                                            <jstl:choose>
                                                <jstl:when test="${rq_result}">
                                                    <jstl:choose>
                                                        <jstl:when test="${!rq_existeUsuario}">
                                                            <a href="#" onClick="continar(1)" class="btn btn-primary btn-user btn-block">Continue</a>
                                                        </jstl:when>
                                                        <jstl:otherwise>
                                                            <jstl:choose>
                                                                <jstl:when test="${!rq_existeSerial}">
                                                                    <a href="#" onClick="continar(2)" class="btn btn-primary btn-user btn-block">Continue</a>
                                                                </jstl:when>
                                                                <jstl:otherwise>
                                                                    <a href="#" onClick="continar(3)" class="btn btn-primary btn-user btn-block">Sign In</a>
                                                                </jstl:otherwise>
                                                            </jstl:choose>
                                                        </jstl:otherwise>
                                                    </jstl:choose>
                                                </jstl:when>
                                                <jstl:otherwise>
                                                    <a href="#" onClick="continar(4)" class="btn btn-primary btn-user btn-block">Sign In</a>
                                                </jstl:otherwise>
                                            </jstl:choose>
                                        </jstl:when>
                                        <jstl:otherwise>
                                            <a href="#" onClick="continar(5)" class="btn btn-primary btn-user btn-block">Sign In</a>
                                        </jstl:otherwise>
                                    </jstl:choose>
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

</body>

</html>
