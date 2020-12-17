<%--
  Created by IntelliJ IDEA.
  User: paul
  Date: 2020-04-08
  Time: 4:34 p.m.
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
     <title>Renew Password Process</title>

            <!-- Custom fonts for this template-->
            <link href="<%= request.getContextPath() %>/complements/bootstrap/bootstrap_4/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
            <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

            <!-- Custom styles for this template-->
            <link href="<%= request.getContextPath() %>/complements/bootstrap/bootstrap_4/css/sb-admin-2.min.css" rel="stylesheet">

            <!-- Scripts propios -->
            <link rel="stylesheet" href="<%= request.getContextPath() %>/complements/css/own_style.css">
    <script type="text/javascript">
        function Continuar(){
            document.forma.action="/objectboard/signin";
            document.forma.submit();
        }
    </script>
</head>

    <body class="bg-gradient-success">
    <div class="header__logo-box">
        <br>
        <a href="<%= request.getContextPath() %>/index.jsp">
            &nbsp;&nbsp;&nbsp; <img src="<%= request.getContextPath() %>/complements/img/logo-white1.png" alt="Logo" width="78" height="40" class="header__logo" href="../../index.jsp">
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
                            <div class="col-lg-12">
                                <div class="p-5">
                                    <form class="user" id="forma" name="forma" method="post" action="">
                                        <div class="p-5">
                                            <jstl:choose>
                                                <jstl:when test="${ rq_result }">
                                                    <div class="text-center">
                                                        <h1 class="h4 text-gray-900 mb-2 alert-info">Updated user password</h1>
                                                        <p class="mb-4">User password has been renew successfully.</p>
                                                    </div>
                                                    <br>
                                                </jstl:when>
                                                <jstl:otherwise>
                                                    <div class="text-center">
                                                        <h1 class="h4 text-gray-900 mb-2 alert-warning">Update failure</h1>
                                                        <p class="mb-4">The password renewal process has failed, please contact the system administrator.</p>
                                                    </div>
                                                </jstl:otherwise>
                                            </jstl:choose>
                                            <a href="#" onClick="Continuar()" class="btn btn-primary btn-user btn-block">Continue</a>
                                        </div>
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
