<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Paul Velazquez">

    <jstl:choose>
        <jstl:when test="${ !rq_existeMaster }">
            <title>Selection of Modules</title>

            <!-- Custom fonts for this template-->
            <link href="<%= request.getContextPath() %>/complements/bootstrap/bootstrap_4/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
            <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

            <!-- Custom styles for this template-->
            <link href="<%= request.getContextPath() %>/complements/bootstrap/bootstrap_4/css/sb-admin-2.min.css" rel="stylesheet">

            <!-- Scripts propios -->
            <link rel="stylesheet" href="<%= request.getContextPath() %>/complements/css/own_style.css">
            <script src="<%= request.getContextPath() %>/complements/scripts/validaciones.js" type="text/javascript"></script>
            <link rel="shortcut icon" type="image/png" href="<%= request.getContextPath() %>/complements/img/favicon.png">

            <!--  Seccion adicionada para los botones con imagenes -->
                <!-- Bootstrap Core CSS -->
                <link href="<%= request.getContextPath() %>/complements/bootstrap/bootstrap_2/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

                <!-- MetisMenu CSS -->
                <link href="<%= request.getContextPath() %>/complements/bootstrap/bootstrap_2/bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

                <!-- Social Buttons CSS -->
                <link href="<%= request.getContextPath() %>/complements/bootstrap/bootstrap_2/bower_components/bootstrap-social/bootstrap-social.css" rel="stylesheet">

                <!-- Custom CSS -->
                <link href="<%= request.getContextPath() %>/complements/bootstrap/bootstrap_2/dist/css/sb-admin-2.css" rel="stylesheet">

                <!-- Custom Fonts -->
                <link href="<%= request.getContextPath() %>/complements/bootstrap/bootstrap_2/bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
            <!--     Fin de seccion -->

            <script type="text/javascript">
                function Continar(valor){
                    if (valor == 1)
                        document.forma.action = "/objectboard/dashmenusuper";
                    if (valor == 2) {
                        document.forma.action = "/objectboard/logout";
                        document.forma.p_exit_total.value = 1;
                    }
                    document.forma.submit();
                }
                function alertas(valor) {
                    if (valor == 1) {
                        alert("This company does not have active licenses for the use of this\n" +
                              "system module.  Please check with your administrator.");
                    }
                    if (valor == 2) {
                        alert("This user does not have active use roles, which allow you to access\n" +
                        "this module.  Please check with your administrator.");
                    }
                }
            </script>
        </jstl:when>
        <jstl:otherwise>
            <script language="JavaScript" type="text/javascript">
                function Salir(valor){
                    if(valor == 1 ) // continua a pagina qu mustra modulos disponibles a ese usuario
                        document.forma.action="/objectboard/mastermenusuper";
                    if(valor == 2 ) // sale a logout porque el usuario no es super y (no hay licencias o roles activos),
                        document.forma.action="/objectboard/logout";
                    document.forma.submit();
                }
            </script>
        </jstl:otherwise>
    </jstl:choose>
</head>

<jstl:choose>
    <jstl:when test="${ !rq_existeMaster }">
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
                                            <div class="text-center">
                                                   <div class="form-group row">
                                                       <div class="col-sm-6 mb-3 mb-sm-0">
                                                           <jstl:choose>
                                                               <jstl:when test="${ rq_moduloBoardCia && rq_userRoleStatusBoard }">
                                                                   <a href="#" onClick="Continar(1)"  class="btn btn-block btn-social btn-success">
                                                                       <i class="fa fa-th-list"></i> Dashboard Menu
                                                                   </a>
                                                               </jstl:when>
                                                               <jstl:otherwise>
                                                                   <jstl:choose>
                                                                       <jstl:when test="${ !rq_moduloBoardCia }">
                                                                           <a href="#" onClick="alertas(1)" class="btn btn-block btn-social btn-success">
                                                                               <i class="fa fa-th-list"></i> Dashboard Menu
                                                                           </a>
                                                                       </jstl:when>
                                                                       <jstl:when test="${ !rq_userRoleStatusBoard }">
                                                                           <a href="#" onClick="alertas(2)" class="btn btn-block btn-social btn-success">
                                                                               <i class="fa fa-th-list"></i> Dashboard Menu
                                                                           </a>
                                                                       </jstl:when>
                                                                   </jstl:choose>
                                                               </jstl:otherwise>
                                                           </jstl:choose>
                                                       </div>
                                                       <div class="col-sm-6">
                                                           <a href="#" onClick="Continar(2)"  class="btn btn-block btn-social btn-pinterest">
                                                               <i class="fa fa-sign-out"></i> Sign Out
                                                           </a>
                                                       </div>
                                                   </div>
                                            </div>
                                            <input type="hidden" name="p_exit_total" value="0">
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
        <!-- seccion adicionada para los botone con imagenes -->
            <!-- jQuery -->
            <script src="<%= request.getContextPath() %>/complements/bootstrap/bootstrap_2/bower_components/jquery/dist/jquery.min.js"></script>

            <!-- Bootstrap Core JavaScript -->
            <script src="<%= request.getContextPath() %>/complements/bootstrap/bootstrap_2/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

            <!-- Metis Menu Plugin JavaScript -->
            <script src="<%= request.getContextPath() %>/complements/bootstrap/bootstrap_2/bower_components/metisMenu/dist/metisMenu.min.js"></script>

            <!-- Custom Theme JavaScript -->
            <script src="<%= request.getContextPath() %>/complements/bootstrap/bootstrap_2/dist/js/sb-admin-2.js"></script>
        <!--     fin de seccion  -->
    </jstl:when>
    <jstl:otherwise>
        <body>
        <form name="forma" method="post" action="" >
            <jstl:choose>
                <jstl:when test="${ rq_otraRazon == 0 }">
                    <!-- Posible validacion pendiente en el caso de cuando el usuario pertenezca a la la Super compania: variable existe_master -->
                    <SCRIPT LANGUAGE='JavaScript' type='text/javascript'>Salir(1);</SCRIPT>;
                </jstl:when>
                <jstl:otherwise>
                    <!--<input name="p_mostrar_cierre">-->
                    <input name="p_exit_total" value="1">
                    <input name="p_otra_salida" value="${rq_otraRazon}">
                    <!-- Posible validacion pendiente en el caso de cuando el usuario pertenezca a la la Super compania: variable existe_master -->
                    <SCRIPT LANGUAGE='JavaScript' type='text/javascript'>Salir(2);</SCRIPT>;
                </jstl:otherwise>
            </jstl:choose>
        </form>
    </jstl:otherwise>
</jstl:choose>

</body>

</html>
