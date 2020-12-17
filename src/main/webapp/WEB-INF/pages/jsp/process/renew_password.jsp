<%--
  Created by IntelliJ IDEA.
  User: pavelaz
  Date: 24/4/20
  Time: 10:11 a. m.
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Paul Velazquez">

    <title>Renew Password</title>

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

    <style>
        @import url('https://fonts.googleapis.com/icon?family=Material+Icons');
    </style>

    <script type="text/javascript">
        function valido(){
            if (verify_password_renew()){
                document.forma.action="/objectboard/renewpasswordprocess";
                document.forma.submit();
            }
        }
        function salir(){
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
                            `<div class="p-5">
                            <form class="user" id="forma" name="forma" method="post" action="">
                                        <div class="text-center">
                                            <h1 class="h4 text-gray-900 mb-2">Renew Your Password?</h1>
                                            <p class="mb-4">The new password must be a different one from the current one and the last one used by you!</p>
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
                                        <div class="form-group row">
                                            <div class="col-sm-6 mb-3 mb-sm-0">
                                                <input type="password" name="p_new_password" class="form-control form-control-user" id="muPassword"  maxlength="20"  placeholder="New password">
                                            </div>
                                            <div class="col-sm-5">
                                                <input type="password" name="p_rep_password" class="form-control form-control-user"  id="muPassword2" maxlength="20" placeholder="Repeat new password">
                                            </div>
                                            <div clase="col-sm-1">
                                                <a href="#"> <i id="ojo" class="material-icons visibility">visibility_off</i></a>
                                            </div>
                                        </div>
                                        <a href="#" onClick="valido()" class="btn btn-primary btn-user btn-block">
                                            Renew Password
                                        </a>
                                        <hr>
                                        <div class="text-center">
                                            <a class="small" href="#" onClick="salir()">No, I want to go to login</a>
                                        </div>
                                        <!--<input type="hidden" name="p_company_name" id="company_name" value="<jstl:out value="${ rq_companyName }">Lost Value</jstl:out>">-->
                                        <input type="hidden" name="p_company_number" id="company_number" value="<jstl:out value="${ rq_companyNumber }">Lost Value</jstl:out>">
                                        <input type="hidden" name="p_answer_t" id="answer_t" value="<jstl:out value="${ rq_answer }">Lost Value</jstl:out>" readonly>
                                        <input type="hidden" name="p_pwd_actual" id="pwd_actual" value="<jstl:out value="${ rq_passwordActual }">Lost Value</jstl:out>" readonly>
                                        <input type="hidden" name="p_pwd_ant" id="pwd_ant" value="<jstl:out value="${ rq_passwordAnt }">Lost Value</jstl:out>" readonly>
                                        <hr>
                                        <!-- <p class="mb-4">* The information sent must match our records for the reset email to be sent.</p>  -->
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

<script src="<%= request.getContextPath() %>/complements/scripts/showPassword.js"></script>

</body>

</html>

