<%--
  Created by IntelliJ IDEA.
  User: pavelaz
  Date: 12/3/20
  Time: 11:20 a. m.
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

    <title>Forgot Password</title>

    <!-- Custom fonts for this template-->
    <link href="<%= request.getContextPath() %>/complements/bootstrap/bootstrap_4/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="<%= request.getContextPath() %>/complements/bootstrap/bootstrap_4/css/sb-admin-2.min.css" rel="stylesheet">

    <!-- Scripts propios -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/complements/css/own_style.css">
    <script src="<%= request.getContextPath() %>/complements/scripts/validaciones.js" type="text/javascript"></script>
    <script src="<%= request.getContextPath() %>/complements/scripts/Particulares.js" type="text/javascript"></script>
    <link rel="shortcut icon" type="image/png" href="<%= request.getContextPath() %>/complements/img/favicon.png">

    <script type="text/javascript">
        <jstl:choose>
            <jstl:when test="${ rq_existeUsuario }">
                function valido(){
                    if (verify_password()){
                        document.forma.action="/objectboard/forgotprocess";
                        document.forma.submit();
                    }
                }
            </jstl:when>
        </jstl:choose>
        function registro(){
            document.forma.action="/objectboard/register";
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
                            `<div class="p-5">
                                <form class="user" id="forma" name="forma" method="post" action="">
                                <jstl:choose>
                                    <jstl:when test="${ rq_existeUsuario }">
                                        <div class="text-center">
                                            <h1 class="h4 text-gray-900 mb-2">Forgot Your Password?</h1>
                                            <p class="mb-4">We get it, stuff happens. Just enter your email address below and we'll send you a link to reset your password!</p>
                                        </div>

                                            <div class="form-group row">
                                                <div class="col-sm-6 mb-3 mb-sm-0">
                                                    <input type="text" name="p_company" class="form-control form-control-user"  id="comps" value="<jstl:out value="${ rq_companyName }">Lost Value</jstl:out>" readonly>
                                                </div>
                                                <div class="col-sm-6">
                                                    <input type="email" name="p_email" class="form-control form-control-user"  value="<jstl:out value="${ rq_userEmail }">Lost Value</jstl:out>" readonly>
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <div class="col-sm-6 mb-3 mb-sm-0">
                                                    <input type="text" name="p_question" class="form-control form-control-user" id="Inputquestion"  maxlength="40" value="<jstl:out value="${ rq_question }">Lost Value</jstl:out>" readonly>
                                                </div>
                                                <div class="col-sm-6">
                                                    <input type="password" name="p_answer" class="form-control form-control-user"  id="answer" placeholder="Enter the answer to the question">
                                                </div>
                                            </div>
                                            <a href="#" onClick="valido()" class="btn btn-primary btn-user btn-block">
                                                Reset Password
                                            </a>
                                            <input type="hidden" name="p_company_name" id="companyname" value="<jstl:out value="${ rq_companyName }">Lost Value</jstl:out>">
                                            <input type="hidden" name="p_company_number" id="companynumber" value="<jstl:out value="${ rq_companyNumber }">Lost Value</jstl:out>">
                                            <input type="hidden" name="p_answer_t" id="answer_t" value="<jstl:out value="${ rq_answer }">Lost Value</jstl:out>" readonly>
                                        <hr>
                                        <p class="mb-4">* The information sent must match our records for the reset email to be sent.</p>
                                    </jstl:when>
                                    <jstl:otherwise>
                                        <div class="text-center">
                                            <h1 class="h4 text-gray-900 mb-2">Forgot Your Password?</h1>
                                            <p class="mb-4">We get it, stuff happens. Just enter your email address below and we'll send you a link to reset your password!</p>
                                        </div>
                                        <div class="form-group row">
                                            <div class="col-sm-6 mb-3 mb-sm-0">
                                                <input type="text" name="p_company" class="form-control form-control-user"
                                                       id="company" value="<jstl:out value="${ rq_companyName }">Lost Value</jstl:out>" readonly>
                                            </div>
                                            <div class="col-sm-6">
                                                <input type="email" name="p_email" class="form-control form-control-user"
                                                       id="InputEmail" value="<jstl:out value="${ rq_userEmail }">Lost Value</jstl:out>" readonly>
                                            </div>
                                        </div>
                                        <div class="text-center">
                                            <h1 class="h4 text-gray-900 mb-2 alert-warning">Unregistered user</h1>
                                            <p class="mb-4">There is no user with the email address provided, registered for this company in our files, please verify the information and try again.</p>
                                        </div>
                                    </jstl:otherwise>
                                </jstl:choose>
                                </form>
                                <div class="text-center">
                                    <a class="small" href="#" onClick="registro()">Create an Account!</a>
                                </div>
                                <div class="text-center">
                                    <a class="small" href="/objectboard/signin">Already have an account? Login!</a>
                                </div>
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
