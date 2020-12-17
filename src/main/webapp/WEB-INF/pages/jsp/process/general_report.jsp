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

    <script language="JavaScript" type="text/javascript">

        function Salir(valor){
            var json = ${ rq_assignmentReport };
            var o = JSON.parse(json);
            alert("paso");
            if(valor == 1 ){ // Llamo a reporte de asignaciones
                alert("paso2");
                document.forma.action="localhost:4200/users/view/" + o; // esto llama la serlet
            }
            document.forma.submit();
        }

    </script>

</head>
<%--
<jstl:choose>
    <jstl:when test="${ !rq_existeTodo }">
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
                                                    <p class="mb-4">This user is not registered for this company, please verify your details and try again.</p>
                                                </jstl:when>
                                                <jstl:otherwise>
                                                    <jstl:choose>
                                                        <jstl:when test="${ !rq_existePasswd }">
                                                            <h1 class="h4 text-gray-900 mb-2 alert-warning">Incorrect password</h1>
                                                            <p class="mb-4">The password you have provided does not match the one registered in our files for this user and company.</p>
                                                        </jstl:when>
                                                        <jstl:otherwise>
                                                            <jstl:choose>
                                                                <jstl:when test="${ !rq_existeEmailConfirm }">
                                                                    <h1 class="h4 text-gray-900 mb-2 alert-warning">Unconfirmed email address</h1>
                                                                    <p class="mb-4">The email address has not yet been confirmed, go to your mailbox and click on the link that appears there to validate the receipt of the confirmation email.</p>
                                                                </jstl:when>
                                                                <jstl:otherwise>
                                                                    <jstl:choose>
                                                                        <jstl:when test="${ !rq_existeStatus }">
                                                                            <h1 class="h4 text-gray-900 mb-2 alert-warning">Deactivated User</h1>
                                                                            <p class="mb-4">This user is disabled, please contact your system administrator to correct the situation and try again.</p>
                                                                        </jstl:when>
                                                                        <jstl:otherwise>
                                                                            <jstl:choose>
                                                                                <jstl:when test="${ !rq_pwdVigente }">
                                                                                    <h1 class="h4 text-gray-900 mb-2 alert-warning">Your password has expired</h1>
                                                                                    <p class="mb-4">Your password has expired please proceed to renew it now.</p>
                                                                                </jstl:when>
                                                                                <jstl:otherwise>
                                                                                    <jstl:choose>
                                                                                        <jstl:when test="${ !rq_userNotExpires }">
                                                                                            <h1 class="h4 text-gray-900 mb-2 alert-warning">Your Access has expired</h1>
                                                                                            <p class="mb-4">Your access has expired, please contact your system administrator.</p>
                                                                                        </jstl:when>
                                                                                        <jstl:otherwise>
                                                                                            <jstl:choose>
                                                                                                <jstl:when test="${ !rq_existeLicence }">
                                                                                                    <h1 class="h4 text-gray-900 mb-2 alert-warning">Company without active licenses</h1>
                                                                                                    <p class="mb-4">Company without active licenses for any module.</p>
                                                                                                </jstl:when>
                                                                                            </jstl:choose>
                                                                                        </jstl:otherwise>
                                                                                    </jstl:choose>
                                                                                </jstl:otherwise>
                                                                            </jstl:choose>
                                                                        </jstl:otherwise>
                                                                    </jstl:choose>
                                                                </jstl:otherwise>
                                                            </jstl:choose>
                                                        </jstl:otherwise>
                                                    </jstl:choose>
                                                </jstl:otherwise>
                                            </jstl:choose>
                                        </div>
                                        <form class="user" id="forma" name="forma" method="post" action="">
                                            <br>
                                                <jstl:choose>
                                                    <jstl:when test="${ !rq_existeUsuario || !rq_existePasswd   || !rq_existeEmailConfirm ||
                                                                        !rq_existeStatus  || !rq_userNotExpires || !rq_existeLicence }">
                                                        <a href="#" onClick="continar(1)" class="btn btn-primary btn-user btn-block">
                                                            Continue
                                                        </a>
                                                    </jstl:when>
                                                    <jstl:otherwise>
                                                        <jstl:choose>
                                                        <jstl:when test="${ !rq_pwdVigente }">
                                                            <a href="#" onClick="continar(2)" class="btn btn-primary btn-user btn-block">
                                                                Renew
                                                            </a>
                                                            <hr>
                                                            <div class="text-center">
                                                                <a class="small" href="#" onClick="continar(1)">No, I want to go to login</a>
                                                            </div>
                                                            <input type="hidden" name="p_company_name" id="company_name" value="<jstl:out value="${ rq_companyName }">Lost Value</jstl:out>">
                                                            <input type="hidden" name="p_company_number" id="company_number" value="<jstl:out value="${ rq_companyNumber }">Lost Value</jstl:out>">
                                                            <input type="hidden" name="p_email" id="email" value="<jstl:out value="${ rq_userEmail }">Lost Value</jstl:out>">
                                                        </jstl:when>
                                                        </jstl:choose>
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
    </jstl:when>
    <jstl:otherwise>--%>
        <body>
        <form name="forma" method="post" action="" target="_blank">
            <%--<input type="hidden" name="p_company_name" id="company_name1" value="<jstl:out value="${ rq_companyName }">Lost Value</jstl:out>">
            <input type="hidden" name="p_company_number" id="company_number1" value="<jstl:out value="${ rq_companyNumber }">Lost Value</jstl:out>">
            <input type="hidden" name="p_email" id="email1" value="<jstl:out value="${ rq_userEmail }">Lost Value</jstl:out>">
            <input type="hidden" name="p_name" id="nombres" value="<jstl:out value="${ rq_userName }">Lost Value</jstl:out>">
            <input type="hidden" name="p_password" id="password" value="<jstl:out value="${ rq_userPasswd }">Lost Value</jstl:out>">
            <input type="hidden" name="p_seccion_time" id="seccionTime" value="<jstl:out value="${ rq_userTimeSeccion }">Lost Value</jstl:out>">
            <input type="hidden" name="p_data_password" id="data_password" value="<jstl:out value="${ rq_dataPassword }">Lost Value</jstl:out>">
            <input type="hidden" name="p_data_user" id="data_user" value="<jstl:out value="${ rq_dataUser }">Lost Value</jstl:out>">--%>
            <!-- Posible validacion pendiente en el caso de cuando el usuario pertenezca a la la Super compania: variable existe_master -->
            <SCRIPT LANGUAGE='JavaScript' type='text/javascript'>Salir(${ rq_reportNumber })</SCRIPT>
        </form>
    <%--</jstl:otherwise>
</jstl:choose>--%>

</body>

</html>
