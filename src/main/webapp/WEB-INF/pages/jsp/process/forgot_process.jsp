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
        function continar(){
            <jstl:choose>
                <jstl:when test="${ !rq_existeUsuario }">
                    document.forma.action="/objectboar/forgotpassword";
                </jstl:when>
                <jstl:otherwise>
                     document.forma.action="/objectboard/signin";
                </jstl:otherwise>
            </jstl:choose>
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
                                        <jstl:when test="${ !rq_existeUsuario }">
                                            <h1 class="h4 text-gray-900 mb-2 alert-warning">Unregistered user</h1>
                                            <p class="mb-4">There is no user with the email address provided, registered for this company in our files, please verify the information and try again.</p>
                                        </jstl:when>
                                        <jstl:otherwise>
                                            <h1 class="h4 text-gray-900 mb-2 alert-warning">Password reset process executed</h1>
                                            <p class="mb-4">An email has been successfully sent to your email account with the information necessary to complete the password reset process. This information will ONLY be valid during the validity date that appears in the mail, after this time you must repeat the process.</p>
                                        </jstl:otherwise>
                                    </jstl:choose>
                                </div>
                                <form class="user" id="forma" name="forma" method="post" action="">
                                    <br>
                                    <a href="#" onClick="continar()" class="btn btn-primary btn-user btn-block">
                                        <jstl:choose>
                                            <jstl:when test="${ !rq_existeUsuario }">
                                                Continue
                                            </jstl:when>
                                            <jstl:otherwise>
                                                Go to Sign In
                                            </jstl:otherwise>
                                        </jstl:choose>
                                    </a>
                                    <input type="hidden" name="p_company_name" id="company_name" value="<jstl:out value="${ rq_companyName }">Lost Value</jstl:out>">
                                    <input type="hidden" name="p_company_number" id="company_number" value="<jstl:out value="${ rq_companyNumber }">Lost Value</jstl:out>">
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
