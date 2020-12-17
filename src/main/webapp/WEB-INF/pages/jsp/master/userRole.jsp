<%@ page import="java.util.ArrayList" %>
<%@ page import="com.psg.objectboard.model.own.ownsEntity.classDAO.MasterUserDAO" %>
<%@ page import="com.psg.objectboard.model.own.ownsEntity.classVO.MasterUserVO" %>
<%@ page import="com.psg.objectboard.model.own.ownsEntity.classDAO.ProjectDAO" %>
<%@ page import="com.psg.objectboard.model.own.ownsEntity.classVO.ProjectVO" %><%
    HttpSession objSesion = request.getSession();
    String data_user = (String)objSesion.getAttribute("dataUser");
    String data_pasword = (String)objSesion.getAttribute("dataPassword");

    String acciones = null;
    if(request.getParameter("p_acciones")!=null){
        acciones=request.getParameter("p_acciones");
    }
    String user_email = null;
    if(request.getParameter("p_email")!=null){
        user_email=request.getParameter("p_email");
    }
    String user_unit = null;
    if(request.getParameter("p_unit")!=null){
        user_unit=request.getParameter("p_unit");
    }
    String user_proj = null;
    if(request.getParameter("p_proj")!=null){
        user_proj=request.getParameter("p_proj");
    }

    String condicion= null;

    MasterUserDAO sod = new MasterUserDAO();
    ArrayList<MasterUserVO> muser = new ArrayList<MasterUserVO>();
    sod.setDataUser(data_user);
    sod.setDataPassword(data_pasword);

    if (user_email == null || user_email == "") {
        muser = sod.getListMasterUser("bussinessUnit_bu_bis_code = 0");
    }else{
        condicion = "bussinessUnit_bu_bis_code = '" + user_unit +"'";
        muser = sod.getListMasterUser(condicion);
    }

    ProjectDAO pro = new ProjectDAO();
    ArrayList<ProjectVO> projectos;
    pro.setDataUser(data_user);
    pro.setDataPassword(data_pasword);
    projectos = pro.getListProjects("");
%>
<!DOCTYPE html>
<html lang="en">
<!--start ############################### header ###############################-->
<head>
    <title>Role maintenance</title>
    <!-- Favicon icon -->
    <link rel="shortcut icon" type="image/png" href="<%= request.getContextPath() %>/complements/img/favicon1.png">
    <%@include file="../../../../complements/jsp/header.jsp"%>
    <!-- Propias -->
    <script src="<%= request.getContextPath() %>/complements/scripts/validaciones.js" type="text/javascript"></script>
    <script type="text/javascript">

        function Validaciones(){
            if (!valida_selects(document.forma.p_unit.value,"Bussiness Unit","")||
                !valida_selects(document.forma.p_email.value,"User Email","")||
                !valida_selects(document.forma.p_proj.value,"Project","")||
                !valida_selects(document.forma.p_role.value,"Role","")
            ){
                return false;
            }
            return true;
        }

        function registro(){
            if(Validaciones()){
                document.forma.action="/objectboard/userrolesprocess";
                document.forma.submit();
            }
        }

        function combo_country() {
            document.forma.action="/objectboard/userrole";
            document.forma.submit();
        }
    </script>
</head>
<!--end ############################### header ###############################-->
<!--ul-->
<body>
<!-- Pre-loader start -->
<div class="theme-loader">
    <div class="loader-track">
        <div class="preloader-wrapper">
            <div class="spinner-layer spinner-blue">
                <div class="circle-clipper left">
                    <div class="circle"></div>
                </div>
                <div class="gap-patch">
                    <div class="circle"></div>
                </div>
                <div class="circle-clipper right">
                    <div class="circle"></div>
                </div>
            </div>
            <div class="spinner-layer spinner-red">
                <div class="circle-clipper left">
                    <div class="circle"></div>
                </div>
                <div class="gap-patch">
                    <div class="circle"></div>
                </div>
                <div class="circle-clipper right">
                    <div class="circle"></div>
                </div>
            </div>
            <div class="spinner-layer spinner-yellow">
                <div class="circle-clipper left">
                    <div class="circle"></div>
                </div>
                <div class="gap-patch">
                    <div class="circle"></div>
                </div>
                <div class="circle-clipper right">
                    <div class="circle"></div>
                </div>
            </div>
            <div class="spinner-layer spinner-green">
                <div class="circle-clipper left">
                    <div class="circle"></div>
                </div>
                <div class="gap-patch">
                    <div class="circle"></div>
                </div>
                <div class="circle-clipper right">
                    <div class="circle"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Pre-loader end -->
<div id="pcoded" class="pcoded">
    <div class="pcoded-overlay-box"></div>
    <div class="pcoded-container navbar-wrapper">

        <!--start ############################### navbar ###############################-->
        <jstl:set var="jsp_navBarOption" scope="page" value="${ '8' }"/>
        <%@include file="../../../../complements/jsp/navbar.jsp"%>
        <!--end ############################### navbar ###############################-->

        <!--start ############################### navbar-header ###############################-->
        <jstl:set var="jsp_ShowImage" scope="page" value="${ 'NOT' }"/>
        <!-- Settings = 1, Profile = 2, My Messages = 3, Lock Screen = 4, Logout = 5, pegina anterior 6  -->
        <jstl:set var="jsp_menuOption" scope="page" value="${ '2,6' }"/>
        <jstl:set var="jsp_previousMsg" scope="page" value="Previous page"/>
        <jstl:set var="jsp_pagePrevious" scope="page" value="/objectboard/userroles"/>
        <%@include file="../../../../complements/jsp/navbar-header.jsp"%>
        <!--end ############################### navbar-header ###############################-->

        <div class="pcoded-main-container">
            <div class="pcoded-wrapper">
                <div class="pcoded-content">

                    <!--start ############################### page-header ###############################-->
                    <jstl:set var="jsp_titleFrom" scope="page" value="Role Maintenance Form"/>
                    <jstl:set var="jsp_funtionFrom" scope="page" value="Complete your options ..."/>
                    <%@include file="../../../../complements/jsp/page-header.jsp"%>
                    <!--end ############################### page-header ###############################-->

                    <div class="pcoded-inner-content">
                        <div class="main-body">
                            <div class="page-wrapper">
                                <div class="page-body">
                                    <!-- Basic Form Inputs card start -->
                                    <div class="card">
                                        <div class="card-header">
                                            <h5>Role properties</h5>
                                        </div>
                                        <div class="card-block">
                                            <h4 class="sub-title">Basic Data</h4>
                                            <form method="post" name="forma" id="uploadFrom" >
                                                <div class="form-group row">
                                                    <div class="col-sm-2">
                                                        <input name="p_acciones" value="${ rq_acciones }" type="hidden">
                                                        <input name='p_pantalla' type='hidden' value='roles' />
                                                       Bussiness Unit:
                                                    </div>
                                                    <div class="col-sm-2">
                                                       <jstl:choose>
                                                            <jstl:when test="${ rq_acciones == 'save' }">
                                                                <input type="hidden" name="p_unit" class="form-control form-control-user"
                                                                       value='<jstl:out value="${ rq_unidad }">Lost Value</jstl:out>' >
                                                                <input type="text" name="p_unit_name" class="form-control form-control-user"
                                                                       value='<jstl:out value="${ rq_unitName }">Lost Value</jstl:out>' readonly>
                                                            </jstl:when>
                                                            <jstl:otherwise>
                                                                <select name="p_unit" id="unit" class="form-control"  onchange="combo_country()">
                                                                    <jstl:choose>
                                                                        <jstl:when test="${ rq_unidad == 0 }">
                                                                            <option value="0" selected>Select Unit</option>
                                                                            <jstl:forEach var="x" begin="0" end="${ rq_unidades.size() - 1}" step="1">
                                                                                <option value='<jstl:out value="${ rq_unidades.get(x).getBuBisCode() }">Lost Value</jstl:out>'>${ rq_unidades.get(x).getBuName() }</option>
                                                                            </jstl:forEach>
                                                                        </jstl:when>
                                                                        <jstl:otherwise>
                                                                            <option value="0">Select Unit</option>
                                                                            <jstl:forEach var="x" begin="0" end="${ rq_unidades.size() - 1}" step="1">
                                                                                <jstl:choose>
                                                                                    <jstl:when test="${ rq_unidades.get(x).getBuBisCode() == rq_unidad }">
                                                                                        <option selected value='<jstl:out value="${ rq_unidades.get(x).getBuBisCode() }">Lost Value</jstl:out>'>${ rq_unidades.get(x).getBuName() }</option>
                                                                                    </jstl:when>
                                                                                    <jstl:otherwise>
                                                                                        <option value='<jstl:out value="${ rq_unidades.get(x).getBuBisCode() }">Lost Value</jstl:out>'>${ rq_unidades.get(x).getBuName() }</option>
                                                                                    </jstl:otherwise>
                                                                                </jstl:choose>
                                                                            </jstl:forEach>
                                                                        </jstl:otherwise>
                                                                    </jstl:choose>
                                                                </select>
                                                            </jstl:otherwise>
                                                        </jstl:choose>
                                                    </div>
                                                    <div class="col-sm-1">
                                                        User email:
                                                    </div>
                                                    <div class="col-sm-3">
                                                        <% if(acciones == "save"){ %>
                                                            <input type="text" name="p_email" class="form-control form-control-user" value="${ rq_email }" readonly>
                                                        <%}else{%>
                                                            <% if(user_email == null){ %>
                                                                <select name="p_email" id="email" class="form-control"  onchange="combo_country()">
                                                                    <option value="0" selected>Select Email</option>
                                                                    <% for(int x=0; x < muser.size(); x++){ %>
                                                                        <option value="<%= muser.get(x).getMuEmail() %>"><%= muser.get(x).getMuEmail() %></option>
                                                                    <% } %>
                                                                </select>
                                                            <%}else{%>
                                                                <select name="p_email" id="email" class="form-control" onchange="combo_country()">
                                                                    <option value="0">Select Email</option>
                                                                    <% for(int x=0; x < muser.size(); x++){ %>
                                                                        <% if(muser.get(x).getMuEmail().equals(user_email)){ %>
                                                                            <option selected value="<%= muser.get(x).getMuEmail() %>"><%= muser.get(x).getMuEmail() %></option>
                                                                        <%}else{%>
                                                                            <option value="<%= muser.get(x).getMuEmail() %>"><%= muser.get(x).getMuEmail() %></option>
                                                                        <% } %>
                                                                    <% } %>
                                                                </select>
                                                            <% } %>
                                                        <% } %>
                                                    </div>
                                                    <div class="col-sm-1">
                                                        User Name:
                                                    </div>
                                                    <div class="col-sm-3">
                                                        <jstl:choose>
                                                            <jstl:when test="${ rq_acciones == 'save' }">
                                                                <input type="text" name="p_email_name" class="form-control form-control-user" value="${ rq_emailName }" readonly>
                                                            </jstl:when>
                                                            <jstl:otherwise>
                                                                <input type="text" name="p_email_name" class="form-control form-control-user" value="${ rq_emailName }" readonly>
                                                            </jstl:otherwise>
                                                        </jstl:choose>
                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <div class="col-sm-2">
                                                        Project:
                                                    </div>
                                                    <div class="col-sm-2">
                                                        <jstl:choose>
                                                            <jstl:when test="${ rq_acciones == 'save' }">
                                                                <input type="hidden" name="p_proj" class="form-control form-control-user"
                                                                       value='<jstl:out value="${ rq_project }">Lost Value</jstl:out>' >
                                                                <input type="text" name="p_proj_name" class="form-control form-control-user"
                                                                       value='<jstl:out value="${ rq_projName }">Lost Value</jstl:out>' readonly>
                                                            </jstl:when>
                                                            <jstl:otherwise>
                                                                <select name="p_proj" id="proj" class="form-control">
                                                                    <option value="0" selected>Select Project</option>
                                                                    <% for(int x=0; x < projectos.size(); x++){ %>
                                                                    <option value="<%= projectos.get(x).getPrIdProject() %>"><%= projectos.get(x).getPrName() %></option>
                                                                    <% } %>
                                                                </select>
                                                            </jstl:otherwise>
                                                        </jstl:choose>
                                                    </div>
                                                    <div class="col-sm-1">
                                                        Role:
                                                    </div>
                                                    <div class="col-sm-3">
                                                        <jstl:choose>
                                                            <jstl:when test="${ rq_acciones == 'save' }">
                                                                <select name="p_role" id="role" class="form-control">
                                                                    <jstl:forEach var="x" begin="0" end="${ 4 }" step="1">
                                                                        <jstl:choose>
                                                                            <jstl:when test="${ rq_rolNum[x] == rq_rol }">
                                                                                <option value="${ rq_rolNum[x] }" selected>${ rq_rolNom[x] }</option>
                                                                            </jstl:when>
                                                                            <jstl:otherwise>
                                                                                <option value="${ rq_rolNum[x] }" >${ rq_rolNom[x] }</option>
                                                                            </jstl:otherwise>
                                                                        </jstl:choose>
                                                                    </jstl:forEach>
                                                                </select>
                                                            </jstl:when>
                                                            <jstl:otherwise>
                                                                <select name="p_role" id="role" class="form-control">
                                                                    <jstl:forEach var="x" begin="0" end="${ 4 }" step="1">
                                                                        <jstl:choose>
                                                                            <jstl:when test="${ rq_rolNum[x] == rq_rol }">
                                                                                <option value="${ rq_rolNum[x] }" selected>${ rq_rolNom[x] }</option>
                                                                            </jstl:when>
                                                                            <jstl:otherwise>
                                                                                <option value="${ rq_rolNum[x] }" >${ rq_rolNom[x] }</option>
                                                                            </jstl:otherwise>
                                                                        </jstl:choose>
                                                                    </jstl:forEach>
                                                                </select>
                                                            </jstl:otherwise>
                                                        </jstl:choose>
                                                    </div>
                                                    <div class="col-sm-1">
                                                        Status:
                                                    </div>
                                                    <div class="col-sm-3">
                                                        <jstl:choose>
                                                            <jstl:when test="${ rq_acciones == 'save' }">
                                                                <jstl:choose>
                                                                    <jstl:when test="${ rq_sta == 'T' }">
                                                                        <input type="radio" id="huey" name="p_status" value="T" checked>
                                                                        <label for="huey">Enabled</label>&nbsp;&nbsp;
                                                                        <input type="radio" id="dewey" name="p_status" value="F">
                                                                        <label for="dewey">Disabled</label>
                                                                    </jstl:when>
                                                                    <jstl:otherwise>
                                                                        <input type="radio" id="huey" name="p_status" value="T">
                                                                        <label for="huey">Enabled</label>&nbsp;&nbsp;
                                                                        <input type="radio" id="dewey" name="p_status" value="F" checked>
                                                                        <label for="dewey">Disabled</label>
                                                                    </jstl:otherwise>
                                                                </jstl:choose>
                                                            </jstl:when>
                                                            <jstl:otherwise>
                                                                <jstl:choose>
                                                                    <jstl:when test="${ rq_sta == 'T' }">
                                                                        <input type="radio" id="huey" name="p_status" value="T" checked>
                                                                        <label for="huey">Enabled</label>&nbsp;&nbsp;
                                                                        <input type="radio" id="dewey" name="p_status" value="F">
                                                                        <label for="dewey">Disabled</label>
                                                                    </jstl:when>
                                                                    <jstl:otherwise>
                                                                        <input type="radio" id="huey" name="p_status" value="T">
                                                                        <label for="huey">Enabled</label>&nbsp;&nbsp;
                                                                        <input type="radio" id="dewey" name="p_status" value="F" checked>
                                                                        <label for="dewey">Disabled</label>
                                                                    </jstl:otherwise>
                                                                </jstl:choose>
                                                            </jstl:otherwise>
                                                        </jstl:choose>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                    <!-- Basic Form Inputs card end -->
                                </div>
                            </div>
                            <div class="container">
                                <div class="form-group row">
                                    <div class="col-lg-4">
                                        &nbsp;&nbsp;
                                    </div>
                                    <div class="col-lg-4">
                                        <a href="#" onClick="registro()" class="btn btn-primary btn-user btn-block">
                                            <jstl:choose>
                                                <jstl:when test="${ rq_acciones =='create' }">
                                                    Create
                                                </jstl:when>
                                                <jstl:otherwise>
                                                    Save
                                                </jstl:otherwise>
                                            </jstl:choose>
                                        </a>
                                    </div>
                                    <div class="col-lg-4">
                                        &nbsp;    &nbsp;
                                    </div>
                                </div>
                                <jstl:forEach var="x" begin="1" end="${ 5 }" step="1">
                                    <div class="form-group row">
                                        <div class="col-sm-12 mb-6 mb-sm-0">
                                            &nbsp;
                                        </div>
                                    </div>
                                </jstl:forEach>
                            </div>
                            <!--start ############################### footer ###############################-->
                            <%@include file="../../../../complements/jsp/footer.jsp"%>
                            <!--end ############################### footer ###############################-->
                        </div>
                    </div>
                </div>
                <div id="styleSelector"></div>
            </div>
        </div>
    </div>
</div>
<!-- Warning Section Starts -->
<%@include file="../../../../complements/jsp/warning_section.jsp"%>
<!-- Warning Section Ends -->
<!--start ############################### footer_basic_script #############################-->
<%@include file="../../../../complements/jsp/footer_basic_script.jsp"%>
<!--end ############################### footer_basic_script ###############################-->
</body>

</html>
