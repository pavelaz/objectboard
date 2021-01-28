<%--
  Created by IntelliJ IDEA.
  User: pavelaz
  Date: 22/4/20
  Time: 11:08 p. m.
  To change this template use File | Settings | File Templates.
  ****  barra verde delgada superior ****
--%>
<nav class="navbar header-navbar pcoded-header">
    <div class="navbar-wrapper">
        <div class="navbar-logo">
            <a class="mobile-menu waves-effect waves-light" id="mobile-collapse" href="#!">
                <i class="ti-menu"></i>
            </a>
            <!-- Aqui logo y nombre compania  izquierda-->
            <img src="/objectboard/imagenshowfile.html?p_forma=${ rq_format }&p_unit=${ rq_companyNumber }&p_archivo=1" class="img-fluid" alt="Theme-Logo" width="35" height="45">&nbsp;&nbsp;
            <!--<img class="img-fluid" src="/objectboard/complements/img/favicon2.png" alt="Theme-Logo" width="35" height="45" />&nbsp;&nbsp;-->
            <%-- <a href="<%= request.getContextPath() %>${ rq_companyLogoDirection }${ rq_companyLogoName }"> --%>
            <a href="#">
                <jstl:out value="${ rq_companyName }">Lost Value</jstl:out>
            </a>
            <a class="mobile-options waves-effect waves-light">
                &nbsp;&nbsp;<i class="ti-more"></i>
            </a>
        </div>
        <div class="navbar-container container-fluid">
            <ul class="nav-left">
                <li>
                    <div class="sidebar_toggle"><a href="javascript:void(0)"><i class="ti-menu"></i></a></div>
                </li>
                <li>
                    <a href="#!" onclick="javascript:toggleFullScreen()" class="waves-effect waves-light">
                        <i class="ti-fullscreen"></i>
                    </a>
                </li>
            </ul>
            <ul class="nav-right">
                <!-- Aqui empieza la parte de la campanita y las noticias  -->
                <li class="header-notification">
                    <a href="#!" class="waves-effect waves-light">
                        <i class="ti-bell"></i>
                        <span class="badge bg-c-red"></span>
                    </a>
                    <ul class="show-notification">
                        <li>
                            <h6>Notifications</h6>
                            <label class="label label-danger">New</label>
                        </li>
                        <li class="waves-effect waves-light">
                            <div class="media">
                                <img class="d-flex align-self-center img-radius" src="<%= request.getContextPath() %>/complements/assets/images/avatar-2.jpg" alt="Generic placeholder image">
                                <div class="media-body">
                                    <h5 class="notification-user"><jstl:out value="${ rq_userName }">Lost Value</jstl:out></h5>
                                    <p class="notification-msg">Lorem ipsum dolor sit amet, consectetuer elit.</p>
                                    <span class="notification-time">30 minutes ago</span>
                                </div>
                            </div>
                        </li>
                        <li class="waves-effect waves-light">
                            <div class="media">
                                <img class="d-flex align-self-center img-radius" src="<%= request.getContextPath() %>/complements/assets/images/avatar-4.jpg" alt="Generic placeholder image">
                                <div class="media-body">
                                    <h5 class="notification-user"><jstl:out value="${ rq_companyName }">Lost Value</jstl:out></h5>
                                    <p class="notification-msg">Lorem ipsum dolor sit amet, consectetuer elit.</p>
                                    <span class="notification-time">30 minutes ago</span>
                                </div>
                            </div>
                        </li>
                        <li class="waves-effect waves-light">
                            <div class="media">
                                <img class="d-flex align-self-center img-radius" src="<%= request.getContextPath() %>/complements/assets/images/avatar-3.jpg" alt="Generic placeholder image">
                                <div class="media-body">
                                    <h5 class="notification-user">Sara Soudein</h5>
                                    <p class="notification-msg">Lorem ipsum dolor sit amet, consectetuer elit.</p>
                                    <span class="notification-time">30 minutes ago</span>
                                </div>
                            </div>
                        </li>
                    </ul>
                </li>
                <!-- Aqui termina la parte de la campanita y las noticias  -->
                <li class="user-profile header-notification">
                    <a href="#!" class="waves-effect waves-light">
                        <!-- Aqui submenu derecho -->
                        <jstl:if test="${ jsp_ShowImage.equals('YES') }">
                            <img src="/objectboard/showfileimage.html" class="img-radius" alt="User-Profile-Image">
                        </jstl:if>
                        <span><jstl:out value="${ rq_userName }">Lost Value</jstl:out></span>
                        <i class="ti-angle-down"></i>
                    </a>
                    <ul class="show-notification profile-notification">
                        <jstl:forTokens var="i" items="${ jsp_menuOption }" delims=",">
                            <fmt:parseNumber var="j" integerOnly="true" type="number" value="${ i }" scope="request"/>
                            <jstl:if test="${ j.equals(1) }">
                                <li class="waves-effect waves-light">
                                    <a href="#!">
                                        <i class="ti-settings"></i> Settings
                                    </a>
                                </li>
                            </jstl:if>
                            <jstl:if test="${ j.equals(2) }">
                                <li class="waves-effect waves-light">
                                    <a href="/objectboard/userprofile?p_email=${ rq_userEmail }&p_unit=${ rq_companyNumber }&p_acciones=save&p_viene=P">
                                        <i class="ti-user"></i> Profile
                                    </a>
                                </li>
                            </jstl:if>
                            <jstl:if test="${ j.equals(3) }">
                                <li class="waves-effect waves-light">
                                    <a href="/objectboard/logout">
                                        <i class="ti-layout-sidebar-left"></i> Logout
                                    </a>
                                </li>
                            </jstl:if>
                        </jstl:forTokens>
                        <jstl:remove var="i" scope="request"/>
                        <jstl:remove var="j" scope="request"/>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
