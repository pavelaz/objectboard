<%--
  Created by IntelliJ IDEA.
  User: alex
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
    <jstl:choose>
        <jstl:when test="${ rq_mostrarCierre }">
            <title>Logout</title>

            <!-- Custom fonts for this template-->
            <link href="<%= request.getContextPath() %>/complements/bootstrap/bootstrap_4/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
            <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

            <!-- Custom styles for this template-->
            <link href="<%= request.getContextPath() %>/complements/bootstrap/bootstrap_4/css/sb-admin-2.min.css" rel="stylesheet">

            <!-- Scripts propios -->
            <link rel="stylesheet" href="<%= request.getContextPath() %>/complements/css/own_style.css">
            <script src="<%= request.getContextPath() %>/complements/scripts/validaciones.js" type="text/javascript"></script>
            <link rel="shortcut icon" type="image/png" href="<%= request.getContextPath() %>/complements/img/favicon.png">
        </jstl:when>
    </jstl:choose>
    <script type="text/javascript">
        function Continuar(){
            <jstl:choose>
            <jstl:when test="${ rq_companyNumber != 1 && !rq_exitTotal  }">
                document.forma.action="/objectboard/graladmins";
            </jstl:when>
            <jstl:otherwise>
                document.forma.action="/objectboard/signin";
            </jstl:otherwise>
            </jstl:choose>
            document.forma.submit();
        }
    </script>
</head>
<jstl:choose>
<jstl:when test="${ rq_mostrarCierre }">
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
                            <div class="col-lg-12">
                                <div class="p-5">
                                    <form class="user" id="forma" name="forma" method="post" action="">
                                        <div class="p-5">
                                            <div class="text-center">
                                                <h1 class="h4 text-gray-900 mb-2 alert-warning">Section closure</h1>
                                                <jstl:choose>
                                                    <jstl:when test="${ rq_otraRazon == 0 }">
                                                        <p class="mb-4">The section has been closed entirely, because you have chosen to do so or because the effective time for an inactive section, assigned to your user profile, has expired.</p>
                                                    </jstl:when>
                                                    <jstl:otherwise>
                                                        <p class="mb-4">The section has been closed because the company does not have active licenses or because the user does not have active roles to enter this company.</p>
                                                    </jstl:otherwise>
                                                </jstl:choose>
                                            </div>
                                            <br>
                                            <a href="#" onClick="Continuar()" class="btn btn-primary btn-user btn-block">Continue
                                            </a>
                                        </div>
                                        <div>
                                            <jstl:choose>
                                                <jstl:when test="${ rq_companyNumber != 1 }">
                                                    <input type="hidden" name="p_company_name" id="company_name" value="<jstl:out value="${ rq_companyName }">Lost Value</jstl:out>">
                                                    <input type="hidden" name="p_company_number" id="company_number" value="<jstl:out value="${ rq_companyNumber }">Lost Value</jstl:out>">
                                                    <input type="hidden" name="p_company_logo" id="company_logo" value="<jstl:out value="${ rq_companyLogo }">Lost Value</jstl:out>">
                                                    <input type="hidden" name="p_email" id="email" value="<jstl:out value="${ rq_userEmail }">Lost Value</jstl:out>">
                                                    <input type="hidden" name="p_password" id="password" value="<jstl:out value="${ rq_userPassword }">Lost Value</jstl:out>">
                                                </jstl:when>
                                            </jstl:choose>
                                            <jstl:remove var="rq_companyName" scope="request"/>
                                            <jstl:remove var="rq_companyNumber" scope="request"/>
                                            <jstl:remove var="rq_userEmail" scope="request"/>
                                            <jstl:remove var="rq_userPassword" scope="request"/>
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
</jstl:when>
<jstl:otherwise>
    <body>
        <form name="forma" method="post" action="" >
            <jstl:choose>
                <jstl:when test="${ rq_companyNumber != 1 && !rq_exitTotal }">
                    <input type="hidden" name="p_company_name" id="company_name" value="<jstl:out value="${ rq_companyName }">Lost Value</jstl:out>">
                    <input type="hidden" name="p_company_number" id="company_number" value="<jstl:out value="${ rq_companyNumber }">Lost Value</jstl:out>">
                    <input type="hidden" name="p_company_logo" id="company_logo" value="<jstl:out value="${ rq_companyLogo }">Lost Value</jstl:out>">
                    <input type="hidden" name="p_email" id="email" value="<jstl:out value="${ rq_userEmail }">Lost Value</jstl:out>">
                    <input type="hidden" name="p_password" id="password" value="<jstl:out value="${ rq_userPassword }">Lost Value</jstl:out>">
                </jstl:when>
            </jstl:choose>
            <jstl:remove var="rq_companyName" scope="request"/>
            <jstl:remove var="rq_companyNumber" scope="request"/>
            <jstl:remove var="rq_userEmail" scope="request"/>
            <jstl:remove var="rq_userPassword" scope="request"/>
            <!-- Posible validacion pendiente en el caso de cuando el usuario pertenezca a la la Super compania: variable existe_master -->
            <SCRIPT LANGUAGE='JavaScript' type='text/javascript'>Continuar();</SCRIPT>;
        </form>
    </body>
</jstl:otherwise>
</jstl:choose>
</html>

