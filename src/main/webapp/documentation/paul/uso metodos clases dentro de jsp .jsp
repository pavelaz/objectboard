<%@ page import="com.psg.objectboard.model.own.ownsEntity.classVO.CountryVO" %>
<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: pavelaz
  Date: 12/3/20
  Time: 9:00 p. m.
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

    <title>Register</title>

    <!-- Custom fonts for this template-->
    <link href="../../complements/bootstrap/bootstrap_4/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="../../complements/bootstrap/bootstrap_4/css/sb-admin-2.min.css" rel="stylesheet">

    <!-- Scripts propios -->
    <link rel="stylesheet" href="../../complements/css/own_style.css">
    <script src="../../complements/scripts/validaciones.js" type="text/javascript"></script>
    <link rel="shortcut icon" type="image/png" href="../../complements/img/favicon.png">
    <!-- icono del ojo para la vista o no del password -->
    <style>
        @import url('https://fonts.googleapis.com/icon?family=Material+Icons');
    </style>
    <script type="text/javascript">
        function valida_cia(){
            if (document.forma.p_company_number.value == 1 && document.forma.p_supercode.value.length == 0){
                alert("To deliver to the Master company it is mandatory to provide a super code\n" +
                      "valid for this, contact the system administrator and try again.");
                return false;
            }else{
                if (document.forma.p_supercode.value != document.forma.p_super.value){
                    if (document.forma.p_company_number.value == 1) {
                        alert("The super code provided does not match the one registered in our files for the master company.");
                        return false;
                    }
                }
            }
            return true;
        }

        function Validaciones(){
            if (!valida_email(document.forma.p_email.value,"email")||
                !valida_textos(document.forma.p_name.value,"First and last name","")||
                !valida_largos(document.forma.p_name.value.length,"First and last name",3)||
                !valida_textos(document.forma.p_password.value,"Password","*$@#")||
                !valida_largos(document.forma.p_password.value.length,"Password",6)||
                !valida_textos(document.forma.p_repeat_password.value,"Repeat Password","*$@#")||
                !valida_largos(document.forma.p_repeat_password.value.length,"Repeat Password",6)||
                !valida_password_iguales(document.forma.p_password.value,document.forma.p_repeat_password.value)||
                !valida_textos(document.forma.p_question.value,"Recovery question","?")||
                !valida_largos(document.forma.p_question.value.length,"Recovery question",3)||
                !valida_textos(document.forma.p_answer.value,"Answer to the question","?")||
                !valida_largos(document.forma.p_answer.value.length,"Answer to the question",3)||
                !valida_selects(document.forma.p_country_number.value,"country","")||
                !valida_selects(document.forma.p_state_number.value,"state","")||
                !valida_selects(document.forma.p_city_number.value,"city","")||
                !valida_cia()
            ){
                return false;
            }
            return true;
        }

        function registro(){
            if(Validaciones()){
                document.forma.action="/registeraccess";
                document.forma.submit();
            }
        }

        function combo_country() {
            document.forma.action="/register";
            document.forma.submit();
        }
    </script>
</head>
<!-- Aqui se cambia el color de fondo y l clase esta en sb-admin-2.min.css -->
<body class="bg-gradient-success">
<div class="header__logo-box">
    <br>
    <a href="../../index.jsp">
        &nbsp;&nbsp;&nbsp; <img src="../../complements/img/logo-white1.png" alt="Logo" width="78" height="40" class="header__logo" href="../../index.jsp">
    </a>
</div>
<div class="container">
    <div class="card o-hidden border-0 shadow-lg my-5">
        <div class="card-body p-0">
            <!-- Nested Row within Card Body -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="p-5">
                        <div class="text-center">
                            <h1 class="h4 text-gray-900 mb-4">Create an Account!</h1>
                        </div>
                        <form class="user" id="forma" name="forma" method="post" action="">
                            <div class="form-group row">
                                <div class="col-sm-6 mb-3 mb-sm-0">
                                    <input type="email" name="p_email" class="form-control form-control-user" id="InputEmail" placeholder="Email Address" maxlength="40">
                                </div>
                                <div class="col-sm-6">
                                    <input type="text" name="p_company" class="form-control form-control-user"
                                           id="company" value="<jstl:out value="${ rq_companyName }">Lost Value</jstl:out>" readonly>
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class="col-sm-6 mb-3 mb-sm-0">
                                    <input type="text" name="p_name" class="form-control form-control-user" id="Name" placeholder="First and Last Name" maxlength="45">
                                </div>
                                <div class="col-sm-6">
                                    <div class="table-responsive-sm">
                                        <table border="0">
                                            <tbody>
                                            <tr>
                                                <td width="90%">
                                                    <input type="password" name="p_supercode" class="form-control form-control-user"
                                                           id="scode" placeholder="Super code" maxlength="12">
                                                </td>
                                                <td align="left" width="10%">
                                                    <!-- Button trigger modal -->
                                                   <button type="button" class="btn btn-circle btn-sm" data-toggle="modal" data-target="#ModalCenter">
                                                        <img src="../../complements/img/info.gif" alt="x" width="20" height="20"/>
                                                    </button>
                                                    <!-- Modal -->
                                                   <div class="modal fade" id="ModalCenter" tabindex="-1" role="dialog"
                                                         aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                                                        <div class="modal-dialog modal-dialog-centered" role="document">
                                                            <div class="modal-content">
                                                                <div class="modal-header">
                                                                    <h5 class="modal-title text-info" id="ModalLongTitle">Important information</h5>
                                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                        <span aria-hidden="true">&times;</span>
                                                                    </button>
                                                                </div>
                                                                <div class="modal-body">
                                                                    The information requested as super code, if necessary in your case, will be delivered to
                                                                    you by the system administrator prior to registering as a user. This field is not
                                                                    obilatory filling.
                                                                </div>
                                                                <div class="modal-footer">
                                                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                                                    <!--<button type="button" class="btn btn-primary">Save changes</button>-->
                                                                </div>
                                                            </div>
                                                        </div>
                                                   </div>
                                                </td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class="col-sm-6 mb-3 mb-sm-0">
                                    <input type="password" name="p_password" class="form-control form-control-user" id="muPassword" placeholder="Password" maxlength="20">
                                </div>
                                <div class="col-sm-5">
                                    <input type="password" name="p_repeat_password" class="form-control form-control-user" id="muPassword2" placeholder="Repeat Password" maxlength="20">
                                </div>
                                <div class="col-sm-1">
                                    <a href="#"> <i id="ojo" class="material-icons visibility">visibility_off</i></a>
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class="col-sm-6 mb-3 mb-sm-0">
                                    <input type="text" class="form-control form-control-user" id="question" placeholder="Recovery question" name="p_question" maxlength="60">
                                </div>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control form-control-user" id="answer" placeholder="Answer to the question" name="p_answer" maxlength="35">
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class="col-sm-3">
                                    <input type="radio" id="huey" name="p_sexo" value="M" checked>
                                    <label for="huey">Male</label>&nbsp;&nbsp;
                                    <input type="radio" id="dewey" name="p_sexo" value="F">
                                    <label for="dewey">Female</label>
                                </div>
                                <div class="col-sm-3 mb-3 mb-sm-0">
                                    <jsp:useBean id="jsp_co" class="com.psg.objectboard.model.own.ownsEntity.classDAO.CountryDAO" scope="page"></jsp:useBean>
                                    <select name="p_country_number" id="i_country" class="form-control" onchange="combo_country()">
                                    <jstl:choose>
                                        <jstl:when test="${ rq_countryNumber == 0 }">
                                            <% ArrayList<CountryVO> countries = jsp_co.getListCountries(""); %>
                                            <option value="0" selected>Select Country</option>
                                            <% for(int x=0; x< countries.size();x++){ %>
                                                <option value="<%= countries.get(x).getCoCountryCode() %>"><%= countries.get(x).getCoName() %></option>
                                            <%}%>
                                        </jstl:when>
                                        <jstl:otherwise>
                                            <% ArrayList<CountryVO> countries = jsp_co.getListCountries(""); %>
                                            <option value="0">Select Country</option>
                                            <% for(int x=0; x< countries.size();x++){ %>
                                                <jstl:choose>
                                                    <jstl:when test="${ countries.get(x).getCoCountryCode() == rq_countryNumber }">
                                                        <option value="<%= countries.get(x).getCoCountryCode() %>" selected><%= countries.get(x).getCoName() %></option>
                                                    </jstl:when>
                                                    <jstl:otherwise>
                                                        <option value="<%= countries.get(x).getCoCountryCode() %>"><%= countries.get(x).getCoName() %></option>
                                                    </jstl:otherwise>
                                                </jstl:choose>
                                            <%}%>
                                        </jstl:otherwise>
                                    </jstl:choose>
                                    </select>
                                </div>
                                <div class="col-sm-3">
                                    <input type="hidden" name="p_opc" id="i_opc">
                                    <select  name="p_state_number" id="i_state" class="form-control" >
                                        <option value="0" selected>Select State</option>
                                    </select>
                                </div>
                                <div class="col-sm-3">
                                    <select name="p_city_number" id="moneda" value='0' class="form-control">
                                        <option value="0" selected>Select City</option>
                                    </select>
                                </div>
                            </div>
                            <a href="#" onClick="registro()" class="btn btn-primary btn-user btn-block">
                                Register Account
                            </a>
                            <input type="hidden" name="p_company_name" id="company_name" value="<jstl:out value="${ rq_companyName }">Lost Value</jstl:out>">
                            <input type="hidden" name="p_company_number" id="company_number" value="<jstl:out value="${ rq_companyNumber }">Lost Value</jstl:out>">
                            <input type="hidden" name="p_super" id="super" value="<jstl:out value="${ rq_superCode }">Lost Value</jstl:out>" readonly>
                        </form>
                        <hr>
                        <div class="text-center">
                            <a class="small" href="/signin">Already have an account? Login!</a>
                        </div>
                        <div class="text-center">
                            <a class="small" href="../../index.jsp">Home</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>

<!-- Bootstrap core JavaScript-->
<script src="../../complements/bootstrap/bootstrap_4/vendor/jquery/jquery.min.js"></script>
<script src="../../complements/bootstrap/bootstrap_4/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- Core plugin JavaScript-->
<script src="../../complements/bootstrap/bootstrap_4/vendor/jquery-easing/jquery.easing.min.js"></script>
<!-- Custom scripts for all pages-->
<script src="../../complements/bootstrap/bootstrap_4/js/sb-admin-2.min.js"></script>
<!-- propios -->
<script type="text/javascript" src="../../complements/scripts/showPassword.js"></script>
</body>

</html>
