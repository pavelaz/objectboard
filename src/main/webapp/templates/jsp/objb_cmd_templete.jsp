<%@ page import="com.psg.objectboard.model.own.ownsEntity.classDAO.OtherDAO.MultiUsoDAO" %>
<%
    MultiUsoDAO varios = new MultiUsoDAO();
    String answer=null;
%>
<!DOCTYPE html>
<html lang="en">
<!--start ############################### header ###############################-->
        <%@include file="../../complements/jsp/header.jsp"%>
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
        <%@include file="../../complements/jsp/navbar.jsp"%>
<!--end ############################### navbar ###############################-->

<!--start ############################### navbar-header ###############################-->
        <%@include file="../../complements/jsp/navbar-header.jsp"%>
<!--end ############################### navbar-header ###############################-->

        <div class="pcoded-main-container">
            <div class="pcoded-wrapper">
                <div class="pcoded-content">

<!--start ############################### page-header ###############################-->
        <%@include file="../../complements/jsp/page-header.jsp"%>
<!--end ############################### page-header ###############################-->

<div class="pcoded-inner-content">
    <div class="main-body">
        <div class="page-wrapper">
            <div class="page-body">
                <!-- Basic Form Inputs card start -->
                <div class="card">
                    <div class="card-header">
                        <h5>User properties form</h5>
                    </div>
                    <div class="card-block">
                        <h4 class="sub-title">Basic Data</h4>
                        <form method="post" enctype="multipart/form-data" name="forma" id="uploadFrom" action="#!">
                                <div class="form-group row">
                                    <label class="col-sm-2 col-form-label">Upload Photo</label>
                                    <div class="col-sm-8" align="right" name="div-photo" id="div-photo">
                                            <img name="oldPhoto" id="oldPhoto" src="/imagen.html" class="img-thumbnail" alt="Photo Profile" width="100" height="120">
                                            <input type="file" id="file" class="image-cropper-container" align="center" onchange="filePreview(this)">
                                    </div>
                                </div>

                                    <div class="form-group row">
                                        <label class="col-sm-2 col-form-label">Email Address</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" value="<jstl:out value="${ masterUserDto.muEmail }">Lost Value</jstl:out>" disabled>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-sm-2 col-form-label">First and Last Name</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" value="<jstl:out value="${ masterUserDto.muName }">Lost Value</jstl:out>">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-sm-2 col-form-label">Password</label>

                                        <div class="col-sm-3">
                                            <input id="idpwd" type="password" class="form-control" placeholder="Password input" value="<jstl:out value="${ masterUserDto.muPassword }">Lost Value</jstl:out>">

                                        </div>

                                        <div class="col-sm-3">
                                            <input id="idpwd2" type="password" class="form-control" placeholder="Repeat Password" value="<jstl:out value="${ masterUserDto.muPassword }">Lost Value</jstl:out>">
                                        </div>
                                        <i id="ojo" class="material-icons visibility">visibility_off</i>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-sm-2 col-form-label">Recovery question</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" value="<jstl:out value="${ masterUserDto.muQuestion }">Lost Value</jstl:out>">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-sm-2 col-form-label">Answer to the question</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" value="<jstl:out value="${ masterUserDto.muAnswer }">Lost Value</jstl:out>">
                                        </div>
                                    </div>

                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Basic Form Inputs card end -->
            </div>
        </div>
<!--start ############################### footer ###############################-->
        <%@include file="../../complements/jsp/footer.jsp"%>
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
<!-- Older IE warning message -->

<!--no es necesario este codigo-->
<!--[if lt IE 10]>
<div class="ie-warning">
    <h1>Warning!!</h1>
    <p>You are using an outdated version of Internet Explorer, please upgrade <br/>to any of the following web
        browsers to access this website.</p>
    <div class="iew-container">
        <ul class="iew-download">
            <li>
                <a href="http://www.google.com/chrome/">
                    <img src="../../../../complements/assets/images/browser/chrome.png" alt="Chrome">
                    <div>Chrome</div>
                </a>
            </li>
            <li>
                <a href="https://www.mozilla.org/en-US/firefox/new/">
                    <img src="../../../../complements/assets/images/browser/firefox.png" alt="Firefox">
                    <div>Firefox</div>
                </a>
            </li>
            <li>
                <a href="http://www.opera.com">
                    <img src="../../../../complements/assets/images/browser/opera.png" alt="Opera">
                    <div>Opera</div>
                </a>
            </li>
            <li>
                <a href="https://www.apple.com/safari/">
                    <img src="../../../../complements/assets/images/browser/safari.png" alt="Safari">
                    <div>Safari</div>
                </a>
            </li>
            <li>
                <a href="http://windows.microsoft.com/en-us/internet-explorer/download-ie">
                    <img src="../../../../complements/assets/images/browser/ie.png" alt="">
                    <div>IE (9 & above)</div>
                </a>
            </li>
        </ul>
    </div>
    <p>Sorry for the inconvenience!</p>
</div>
<![endif]-->
<!-- Warning Section Ends -->
<!-- Required Jquery -->
<script type="text/javascript" src="../../../../complements/assets/js/jquery/jquery.min.js "></script>
<script type="text/javascript" src="../../../../complements/assets/js/jquery-ui/jquery-ui.min.js "></script>
<script type="text/javascript" src="../../../../complements/assets/js/popper.js/popper.min.js"></script>
<script type="text/javascript" src="../../../../complements/assets/js/bootstrap/js/bootstrap.min.js "></script>
<!-- waves js -->
<script src="../../../../complements/assets/pages/waves/js/waves.min.js"></script>
<!-- jquery slimscroll js -->
<script type="text/javascript" src="../../../../complements/assets/js/jquery-slimscroll/jquery.slimscroll.js"></script>
<script src="../../../../complements/assets/js/pcoded.min.js"></script>
<script src="../../../../complements/assets/js/vertical/vertical-layout.min.js"></script>
<script src="../../../../complements/assets/js/jquery.mCustomScrollbar.concat.min.js"></script>
<!-- Custom js -->
<script type="text/javascript" src="../../../../complements/assets/js/script.min.js"></script>
<!--<script type="text/javascript" src="../../../../complements/js/ImageControls.js"></script>-->
<script type="text/javascript" src="../../../../complements/scripts/showPassword.js"></script>

</body>

</html>
