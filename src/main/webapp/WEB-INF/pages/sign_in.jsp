<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <link href="https://fonts.googleapis.com/css?family=Lato:100,300,400,700,900" rel="stylesheet">

    <link rel="stylesheet" href="<%= request.getContextPath() %>/complements/css/style.css">
    <link rel="shortcut icon" type="image/png" href="<%= request.getContextPath() %>/complements/img/favicon1.png">

    <!-- Scripts propios -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/complements/css/own_style.css">
    <script src="<%= request.getContextPath() %>/complements/scripts/validaciones.js" type="text/javascript"></script>

    <title>Sign in Gvsoft</title>

    <script type="text/javascript">

        function validaciones(){
            if (!valida_selects(document.forma.p_company_num.value,"company","user")||
                !valida_email(document.forma.p_email.value,"email")||
                !valida_textos(document.forma.p_password.value,"Password","*$@#")||
                !valida_largos(document.forma.p_password.value.length,"Password",6))
                return false;
            else
                return true;
        }

        function olvido(){
            if(valida_selects(document.forma.p_company_num.value,"company","") &&
                valida_email(document.forma.p_email.value,"email")){
                document.forma.p_company_name.value = buscar_texto_en_select("company");
                document.forma.action="/objectboard/forgotpassword";
                var cadenaADividir = document.forma.p_company_num.value;
                var arrayDeCadenas = cadenaADividir.split(",");
                document.forma.p_company_number.value = arrayDeCadenas[0];
                document.forma.p_company_logo.value = arrayDeCadenas[1];
                document.forma.submit();
            }
        }

        function registro(){
            if(valida_selects(document.forma.p_company_num.value,"company","")){
                var cadenaADividir = document.forma.p_company_num.value;
                var arrayDeCadenas = cadenaADividir.split(",");
                document.forma.p_company_name.value = buscar_texto_en_select("company");
                document.forma.action="/objectboard/register";
                document.forma.p_company_number.value = arrayDeCadenas[0];
                document.forma.p_company_logo.value = arrayDeCadenas[1];
                document.forma.submit();
            }
        }

        function valido(){
            if(validaciones()){
                var cadenaADividir = document.forma.p_company_num.value;
                var arrayDeCadenas = cadenaADividir.split(",");
                document.forma.p_company_name.value = buscar_texto_en_select("company");
                document.forma.action="/objectboard/graladmins";
                document.forma.p_company_number.value = arrayDeCadenas[0];
                document.forma.p_company_logo.value = arrayDeCadenas[1];
                document.forma.submit();
            }
        }
    </script>
</head>

<body>
<div class="header__text-box">
    <h1 class="heading-primary">
        <span class="heading-primary--main">Servicios PVSoft</span>
        <span class="heading-primary--sub">Building a world for yous</span>
    </h1>

    <a href="<%= request.getContextPath() %>/index.jsp" class="btn btn--white btn--animated">Under construction</a>
</div>
<header class="header">
    <div class="header__logo-box">
        <a href="<%= request.getContextPath() %>/index.jsp">
            <img src="<%= request.getContextPath() %>/complements/img/logo-white1.png" alt="Logo" class="header__logo" href="<%= request.getContextPath() %>/index.jsp">
        </a>
    </div>
    <div class="header__text-box">
        <h2 class="heading-primary">
            <span class="heading-primary--main">Sign In</span>
        </h2>
        <form role="form" id="forma" name="forma" method="post" action="" >

            <div class="container">
                <div class="form__group">
                    <select name="p_company_num" tabindex="2" class="form__input" id="company">
                        <option selected value='0'>Select Company</option>;
                        <jstl:forEach var="x" begin="0" end="${ rq_largo - 1 }" step="1">
                            <option value='${ rq_unit.get(x).getBuBisCode() },${ rq_unit.get(x).getBuLogoName() }'>
                                     <jstl:out value="${ rq_unit.get(x).getBuName()}"/></option>;
                        </jstl:forEach>
                    </select>
                </div>
                <div class="form__group">
                    <input type="email" class="form__input" placeholder="Email address" width="30"
                           id="email" name="p_email" maxlength="30" onfocus="selecciona_contenido(this)" required>
                    <label for="email" class="form__label propia">Email address</label>
                </div>

                <div class="form__group">
                    <input type="password" class="form__input" placeholder="Password" width="20"
                           name="p_password" id="password" maxlength="20" onfocus="selecciona_contenido(this)" required>
                    <label for="password" class="form__label propia">Password</label>
                </div>

                <div class="row">
                    <div class="col-6">
                        <a href="#" onClick="olvido()" class="propia_r1">I forgot the password</a>
                    </div>
                    <div class="col-6">
                        <a href="#" onClick="registro()" class="propia_r1">Register</a>
                    </div>
                </div>
            </div>
            <br>
            <a href="#" class="btn btn--white btn--animated" onClick="valido()">Sign in</a>
            <input type="hidden" name="p_company_name" id="tselect">
            <input type="hidden" name="p_company_logo" value="">
            <input type="hidden" name="p_company_number" value="">
            <%--<input type="hidden" name="p_super" id="super" value="<jstl:out value="${ rq_superCode }">Lost Value</jstl:out>">--%>
        </form>

    </div>
</header>

<%@ include file="../../complements/jsp/footer_building.jsp" %>

</body>

</html>