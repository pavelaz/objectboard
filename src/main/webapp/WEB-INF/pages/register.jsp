<%@ page import="com.psg.objectboard.model.own.ownsEntity.classDAO.CountryDAO" %>
<%@ page import="com.psg.objectboard.model.own.ownsEntity.classVO.CountryVO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.psg.objectboard.model.own.ownsEntity.classDAO.StateDAO" %>
<%@ page import="com.psg.objectboard.model.own.ownsEntity.classVO.StateVO" %>
<%@ page import="com.psg.objectboard.model.own.ownsEntity.classDAO.CityDAO" %>
<%@ page import="com.psg.objectboard.model.own.ownsEntity.classVO.CityVO" %><%--
  Created by IntelliJ IDEA.
  User: pavelaz
  Date: 12/3/20
  Time: 9:00 p. m.
  To change this template use File | Settings | File Templates.
  <%@ page contentType="text/html;charset=UTF-8" language="java" %>
--%>
<%
    Integer country_number = 0;
    if(request.getParameter("p_country_number")!=null){
        country_number=Integer.parseInt(request.getParameter("p_country_number"));
    }
    Integer state_number = 0;
    if(request.getParameter("p_state_number")!=null){
        state_number=Integer.parseInt(request.getParameter("p_state_number"));
    }
    Integer city_number = 0;
    if(request.getParameter("p_city_number")!=null){
        city_number=Integer.parseInt(request.getParameter("p_city_number"));
    }
    String data_user = "boarduser",
           data_password = "1#Object5Board*%";

    String condicion = null;
    CountryDAO cod = new CountryDAO();
    ArrayList<CountryVO> paises;
    cod.setDataUser(data_user);
    cod.setDataPassword(data_password);
    StateDAO sod = new StateDAO();
    ArrayList<StateVO> states = new ArrayList<StateVO>();
    sod.setDataUser(data_user);
    sod.setDataPassword(data_password);
    CityDAO cid = new CityDAO();
    ArrayList<CityVO> cities = new ArrayList<CityVO>();
    cid.setDataUser(data_user);
    cid.setDataPassword(data_password);
    paises = cod.getListCountries("");

    if (country_number == 0){
        state_number=0;
        city_number=0;
        condicion = "country_co_country_code = 0";
        states = sod.getListStates(condicion);
        condicion = "states_st_state_code = 0 AND states_country_co_country_code = 0";
        cities = cid.getListCities(condicion);
    }else{
        condicion = "country_co_country_code = " + country_number;
        states = sod.getListStates(condicion);
        if (state_number==0){
            city_number=0;
            condicion = "states_st_state_code = 0 AND states_country_co_country_code = " + country_number;
            cities = cid.getListCities(condicion);
        }else{
            condicion = "states_st_state_code = " + state_number +" AND states_country_co_country_code = " + country_number;
            cities = cid.getListCities(condicion);
        }
    }
%>
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
    <link href="<%= request.getContextPath() %>/complements/bootstrap/bootstrap_4/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="<%= request.getContextPath() %>/complements/bootstrap/bootstrap_4/css/sb-admin-2.min.css" rel="stylesheet">

    <!-- Scripts propios -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/complements/css/own_style.css">
    <script src="<%= request.getContextPath() %>/complements/scripts/validaciones.js" type="text/javascript"></script>
    <script src="<%= request.getContextPath() %>/complements/scripts/Particulares.js" type="text/javascript"></script>
    <link rel="shortcut icon" type="image/png" href="<%= request.getContextPath() %>/complements/img/favicon.png">
    <!-- icono del ojo para la vista o no del password -->
    <style>
        @import url('https://fonts.googleapis.com/icon?family=Material+Icons');
    </style>
    <script type="text/javascript">
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
                !valida_cia_registro()
            ){
                return false;
            }
            return true;
        }

        function registrar(){
            if(Validaciones()){
                document.forma.action="/objectboard/registeraccess";
                document.forma.submit();
            }
        }

        function combo_country() {
            document.forma.action="/objectboard/register";
            document.forma.submit();
        }
    </script>
</head>
<!-- Aqui se cambia el color de fondo y la clase esta en sb-admin-2.min.css -->
<body class="bg-gradient-success">
<div class="header__logo-box">
    <br>
    <a href="<%= request.getContextPath() %>/index.jsp">
        &nbsp;&nbsp;&nbsp; <img src="<%= request.getContextPath() %>/complements/img/logo-white1.png" alt="Logo" width="78" height="40" class="header__logo" href="<%= request.getContextPath() %>/index.jsp">
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
                                    <jstl:choose>
                                        <jstl:when test="${ rq_userEmail !=null }">
                                            <input type="email" name="p_email" class="form-control form-control-user"
                                                   id="InputEmail" placeholder="Email Address" maxlength="40"
                                                   value="<jstl:out value="${ rq_userEmail }">Lost Value</jstl:out>"
                                                   onfocus="selecciona_contenido(this)">
                                        </jstl:when>
                                        <jstl:otherwise>
                                            <input type="email" name="p_email" class="form-control form-control-user" id="InputEmail"
                                                   placeholder="Email Address" maxlength="40" onfocus="selecciona_contenido(this)">
                                        </jstl:otherwise>
                                    </jstl:choose>
                                </div>
                                <div class="col-sm-6">
                                    <input type="text" name="p_company" class="form-control form-control-user"
                                           id="company" value="<jstl:out value="${ rq_companyName }">Lost Value</jstl:out>" readonly>
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class="col-sm-6 mb-3 mb-sm-0">
                                    <jstl:choose>
                                        <jstl:when test="${ rq_userName != null }">
                                            <input type="text" name="p_name" class="form-control form-control-user"
                                                   id="name" placeholder="First and Last Name" maxlength="45"
                                                   value="<jstl:out value="${ rq_userName }">Lost Value</jstl:out>"
                                                   onfocus="selecciona_contenido(this)">
                                        </jstl:when>
                                        <jstl:otherwise>
                                            <input type="text" name="p_name" class="form-control form-control-user" id="Name"
                                                   placeholder="First and Last Name" maxlength="45" onfocus="selecciona_contenido(this)">
                                        </jstl:otherwise>
                                    </jstl:choose>
                                    <input type="hidden" name="p_company_name" id="company_name" value="<jstl:out value="${ rq_companyName }">Lost Value</jstl:out>">
                                    <input type="hidden" name="p_company_number" id="company_number" value="<jstl:out value="${ rq_companyNumber }">Lost Value</jstl:out>">
                                    <input type="hidden" name="p_super" id="super" value="<jstl:out value="${ rq_userSuperC }">Lost Value</jstl:out>" readonly>
                                    <input type="hidden" name="p_admin" id="adminc" value="<jstl:out value="${ rq_userAdminC }">Lost Value</jstl:out>" readonly>
                                    <input type="hidden" name="p_user1" id="user1" value="<jstl:out value="${ rq_userUser1C }">Lost Value</jstl:out>" readonly>
                                    <input type="hidden" name="p_user2" id="user2" value="<jstl:out value="${ rq_userUser2C }">Lost Value</jstl:out>" readonly>
                                    <input type="hidden" name="p_role" id="role" value="0" readonly>
                                </div>
                                <div class="col-sm-6">
                                    <div class="table-responsive-sm">
                                        <table border="0">
                                            <tbody>
                                            <tr>
                                                <td width="90%">
                                                    <jstl:choose>
                                                         <jstl:when test="${ rq_superCode != null }">
                                                           <input type="password" name="p_supercode" class="form-control form-control-user"
                                                           id="scode" placeholder="Super code" maxlength="12"
                                                           value="<jstl:out value="${ rq_superCode }">Lost Value</jstl:out>"
                                                                  onfocus="selecciona_contenido(this)">
                                                    </jstl:when>
                                                    <jstl:otherwise>
                                                        <input type="password" name="p_supercode" class="form-control form-control-user"
                                                        id="scode" placeholder="Super code" maxlength="12" onfocus="selecciona_contenido(this)">
                                                    </jstl:otherwise>
                                                    </jstl:choose>
                                                </td>
                                                <td align="left" width="10%">
                                                    <!-- Button trigger modal -->
                                                   <button type="button" class="btn btn-circle btn-sm" data-toggle="modal" data-target="#ModalCenter">
                                                        <img src="<%= request.getContextPath() %>/complements/img/info.gif" alt="x" width="20" height="20"/>
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
                                    <jstl:choose>
                                        <jstl:when test="${ rq_userPassword != null }">
                                            <input type="password" name="p_password" class="form-control form-control-user"
                                                   id="muPassword" placeholder="Password" maxlength="20"
                                                   value="<jstl:out value="${ rq_userPassword }">Lost Value</jstl:out>"
                                                   onfocus="selecciona_contenido(this)">
                                        </jstl:when>
                                        <jstl:otherwise>
                                            <input type="password" name="p_password" class="form-control form-control-user"
                                                   id="muPassword" placeholder="Password" maxlength="20"
                                                   onfocus="selecciona_contenido(this)">
                                        </jstl:otherwise>
                                    </jstl:choose>
                                </div>
                                <div class="col-sm-5">
                                    <jstl:choose>
                                        <jstl:when test="${ rq_repeatPassword != null }">
                                            <input type="password" name="p_repeat_password" class="form-control form-control-user"
                                                   id="muPassword2" placeholder="Repeat Password" maxlength="20"
                                                   value="<jstl:out value="${ rq_repeatPassword }">Lost Value</jstl:out>"
                                                   onfocus="selecciona_contenido(this)">
                                        </jstl:when>
                                        <jstl:otherwise>
                                            <input type="password" name="p_repeat_password" class="form-control form-control-user"
                                                   id="muPassword2" placeholder="Repeat Password" maxlength="20"
                                                   onfocus="selecciona_contenido(this)">
                                        </jstl:otherwise>
                                    </jstl:choose>
                                </div>
                                <div class="col-sm-1">
                                    <a href="#"> <i id="ojo" class="material-icons visibility">visibility_off</i></a>
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class="col-sm-6 mb-3 mb-sm-0">
                                    <jstl:choose>
                                        <jstl:when test="${ rq_userQuestion != null }">
                                            <input type="text" class="form-control form-control-user" id="question"
                                                   placeholder="Recovery question" name="p_question" maxlength="60"
                                                   value="<jstl:out value="${ rq_userQuestion }">Lost Value</jstl:out>">
                                        </jstl:when>
                                        <jstl:otherwise>
                                            <input type="text" class="form-control form-control-user" id="question" placeholder="Recovery question" name="p_question" maxlength="60">
                                        </jstl:otherwise>
                                    </jstl:choose>
                                </div>
                                <div class="col-sm-6">
                                    <jstl:choose>
                                        <jstl:when test="${ rq_userAnswer != null }">
                                            <input type="text" class="form-control form-control-user" id="answer" placeholder="Answer to the question"
                                                   name="p_answer" maxlength="35" value="<jstl:out value="${ rq_userAnswer }">Lost Value</jstl:out>">
                                        </jstl:when>
                                        <jstl:otherwise>
                                            <input type="text" class="form-control form-control-user" id="answer" placeholder="Answer to the question" name="p_answer" maxlength="35">
                                        </jstl:otherwise>
                                    </jstl:choose>
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class="col-sm-3">
                                    <jstl:choose>
                                        <jstl:when test="${ rq_userSexo == 'M' }">
                                            <input type="radio" id="huey" name="p_sexo" value="M" checked>
                                            <label for="huey">Male</label>&nbsp;&nbsp;
                                            <input type="radio" id="dewey" name="p_sexo" value="F">
                                            <label for="dewey">Female</label>
                                        </jstl:when>
                                        <jstl:otherwise>
                                            <input type="radio" id="huey" name="p_sexo" value="M">
                                            <label for="huey">Male</label>&nbsp;&nbsp;
                                            <input type="radio" id="dewey" name="p_sexo" value="F" checked>
                                            <label for="dewey">Female</label>
                                        </jstl:otherwise>
                                    </jstl:choose>
                                </div>
                                <div class="col-sm-3 mb-3 mb-sm-0">
                                    <select name="p_country_number" id="i_country" class="form-control" onchange="combo_country()">
                                        <% if(country_number == 0 ){ %>
                                            <option value="0" selected>Select Country</option>
                                            <% for(int x=0; x < paises.size(); x++){ %>
                                                <option value="<%= paises.get(x).getCoCountryCode()%>"><%=paises.get(x).getCoName() %></option>
                                           <% } %>
                                        <%}else{%>
                                            <option value="0">Select Country</option>
                                            <% for(int x=0; x < paises.size(); x++){ %>
                                                <% if (paises.get(x).getCoCountryCode() == country_number){ %>
                                                    <option value="<%=paises.get(x).getCoCountryCode()%>" selected><%=paises.get(x).getCoName() %></option>
                                                <%}else{%>
                                                    <option value="<%=paises.get(x).getCoCountryCode()%>"><%=paises.get(x).getCoName() %></option>
                                                <%}%>
                                            <% } %>
                                        <%}%>
                                    </select>
                                </div>
                                <div class="col-sm-3">
                                    <select name="p_state_number" id="i_state" class="form-control" onchange="combo_country()">
                                        <% if(state_number == 0 ){ %>
                                            <option value="0" selected>Select State</option>
                                            <% for(int x=0; x < states.size(); x++){ %>
                                                <option value="<%= states.get(x).getStStateCode()%>"><%=states.get(x).getStName() %></option>
                                            <% } %>
                                        <%}else{%>
                                            <option value="0">Select State</option>
                                            <% for(int x=0; x < states.size(); x++){ %>
                                                <% if (states.get(x).getStStateCode() == state_number){ %>
                                                    <option value="<%=states.get(x).getStStateCode()%>" selected><%=states.get(x).getStName() %></option>
                                                <%}else{%>
                                                    <option value="<%=states.get(x).getStStateCode()%>"><%=states.get(x).getStName() %></option>
                                                <%}%>
                                            <% } %>
                                        <%}%>
                                    </select>
                                </div>
                                <div class="col-sm-3">
                                    <select name="p_city_number" id="i_city" class="form-control">
                                        <% if(city_number == 0 ){ %>
                                            <option value="0" selected>Select City</option>
                                            <% for(int x=0; x < cities.size(); x++){ %>
                                                <option value="<%= cities.get(x).getCiCityCode()%>"><%=cities.get(x).getCiName() %></option>
                                            <% } %>
                                        <%}else{%>
                                            <option value="0">Select City</option>
                                            <% for(int x=0; x < cities.size(); x++){ %>
                                                <% if (cities.get(x).getCiCityCode() == city_number){ %>
                                                    <option value="<%=cities.get(x).getCiCityCode()%>" selected><%=cities.get(x).getCiName() %></option>
                                                <%}else{%>
                                                    <option value="<%=cities.get(x).getCiCityCode()%>"><%=cities.get(x).getCiName() %></option>
                                                <%}%>
                                            <% } %>
                                        <%}%>
                                    </select>
                                </div>
                            </div>
                            <a href="#" onClick="registrar()" class="btn btn-primary btn-user btn-block">
                                Register Account
                            </a>
                        </form>
                        <hr>
                        <div class="text-center">
                            <a class="small" href="/objectboard/signin">Already have an account? Login!</a>
                        </div>
                        <div class="text-center">
                            <a class="small" href="<%= request.getContextPath() %>/index.jsp">Home</a>
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
<!-- propios -->
<script type="text/javascript" src="<%= request.getContextPath() %>/complements/scripts/showPassword.js"></script>
<script type="text/javascript">
String.prototype.hexEncode = function(){
var hex, i;

var result = "";
for (i=0; i<this.length; i++) {
hex = this.charCodeAt(i).toString(16);
result += ("000"+hex).slice(-4);
}

return result
}

String.prototype.hexDecode = function(){
    var j;
    var hexes = this.match(/.{1,4}/g) || [];
    var back = "";
    for(j = 0; j<hexes.length; j++) {
        back += String.fromCharCode(parseInt(hexes[j], 16));
    }

    return back;
}
</script>
</body>

</html>
