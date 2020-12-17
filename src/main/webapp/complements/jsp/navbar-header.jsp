<nav class="navbar header-navbar pcoded-header">
    <div class="navbar-wrapper">
        <div class="navbar-logo">
            <a class="mobile-menu waves-effect waves-light" id="mobile-collapse" href="#!">
                <i class="ti-menu"></i>
            </a>
            <div class="mobile-search waves-effect waves-light">
                <div class="header-search">
                    <div class="main-search morphsearch-search">
                        <div class="input-group">
                            <span class="input-group-prepend search-close"><i class="ti-close input-group-text"></i></span>
                            <input type="text" class="form-control" placeholder="Enter Keyword">
                            <span class="input-group-append search-btn"><i class="ti-search input-group-text"></i></span>
                        </div>
                    </div>
                </div>
            </div>
            <!--<img class="img-fluid" src="/objectboard/complements/img/logos/01/favicon2.png" alt="Theme-Logo" width="35" height="45" />&nbsp;&nbsp; -->
            <%--<img class="img-fluid" src="/objectboard/complements/img/logos/02/favicon2.png" alt="Theme-Logo" width="35" height="45" />&nbsp;&nbsp;
            <img class="img-fluid" src="${pageContext.request.contextPath}/complements/img/favicon2.png" alt="Theme-Logo" width="35" height="45" />&nbsp;&nbsp;
            <img class="img-fluid" src="/objectboard/complements/img/logos/01/favicon2.png" alt="Theme-Logo" width="35" height="45" />&nbsp;&nbsp;
            <img class="img-fluid" src="<%= request.getContextPath() %>${ rq_companyLogoDirection }${ rq_companyLogoName }" alt="Theme-Logo" width="35" height="45" />&nbsp;&nbsp;
            <img class="img-fluid" src="<%= request.getContextPath() %>/complements/img/logos/01/favicon2.png" alt="Theme-Logo" width="35" height="45" />&nbsp;&nbsp;--%>
            <jstl:choose>
                <jstl:when test="${ rq_companyNumber.equals('1')}">
                    <img class="img-fluid" src="${pageContext.request.contextPath}/complements/img/favicon2.png" alt="Theme-Logo" width="35" height="45" />&nbsp;&nbsp;
                </jstl:when>
                <jstl:otherwise>
                    <%-- <img class="img-fluid" src="/objectboard/complements/img/logos/02/logo_nasa.png" alt="Theme-Logo" width="35" height="45" />&nbsp;&nbsp; --%>
                    <img class="img-fluid" src="${ rq_companyLogoDirection }${ rq_companyLogoName }" alt="Theme-Logo" width="35" height="45">&nbsp;&nbsp;
                </jstl:otherwise>
            </jstl:choose>
            <a href="<%= request.getContextPath() %>/index.jsp">
                <jstl:out value="${ rq_companyName }">Lost Value</jstl:out>
            </a>
            <a class="mobile-options waves-effect waves-light">
                <i class="ti-more"></i>
            </a>
        </div>
        <div class="navbar-container container-fluid">
            <ul class="nav-left">
                <li>
                    <div class="sidebar_toggle"><a href="javascript:void(0)">&nbsp;&nbsp;<i class="ti-menu"></i></a></div>
                </li>
                <li>
                    <a href="#!" onclick="javascript:toggleFullScreen()" class="waves-effect waves-light">
                        <i class="ti-fullscreen"></i>
                    </a>
                </li>
            </ul>
            <ul class="nav-right">
                <li class="header-notification">
                    <!--<a href="#!" class="waves-effect waves-light">
                        <i class="ti-bell"></i>
                        <span class="badge bg-c-red"></span>
                    </a>-->
                    <ul class="show-notification">
                        <li>
                            <h6>Notifications</h6>
                            <label class="label label-danger">New</label>
                        </li>
                        <li class="waves-effect waves-light">
                            <div class="media">
                                <img class="d-flex align-self-center img-radius" src="<%= request.getContextPath() %>/complements/assets/images/avatar-2.jpg" alt="Generic placeholder image">
                                <div class="media-body">
                                    <h5 class="notification-user">John Doe</h5>
                                    <p class="notification-msg">Lorem ipsum dolor sit amet, consectetuer elit.</p>
                                    <span class="notification-time">30 minutes ago</span>
                                </div>
                            </div>
                        </li>
                        <li class="waves-effect waves-light">
                            <div class="media">
                                <img class="d-flex align-self-center img-radius" src="<%= request.getContextPath() %>/complements/assets/images/avatar-4.jpg" alt="Generic placeholder image">
                                <div class="media-body">
                                    <h5 class="notification-user">Joseph William</h5>
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
                <li class="user-profile header-notification">
                    <a href="#!" class="waves-effect waves-light">
                        <jstl:if test="${ jsp_ShowImage.equals('YES') }">
                            <img src="/objectboard/showfileimage.html" class="img-radius" alt="User-Profile-Image">
                        </jstl:if>
                        <jstl:remove var="jsp_ShowImage"/>
                        <span><jstl:out value="${ rq_userName }">Lost Value</jstl:out></span>
                        <i class="ti-angle-down"></i>
                    </a>
                    <ul class="show-notification profile-notification">
                        <!--start ############################################### menu desplegable #############################################-->
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
                                    <a href="/objectboard/masteruser">
                                        <i class="ti-user"></i> Profile
                                    </a>
                                </li>
                            </jstl:if>
                            <jstl:if test="${ j.equals(3) }">
                                <li class="waves-effect waves-light">
                                    <a href="email-inbox.html">
                                        <i class="ti-email"></i> My Messages
                                    </a>
                                </li>
                            </jstl:if>
                            <jstl:if test="${ j.equals(4) }">
                                <li class="waves-effect waves-light">
                                    <a href="auth-lock-screen.html">
                                        <i class="ti-lock"></i> Lock Screen
                                    </a>
                                </li>
                            </jstl:if>
                            <jstl:if test="${ j.equals(5) }">
                                <li class="waves-effect waves-light">
                                    <a href="/objectboard/logout">
                                        <i class="ti-layout-sidebar-left"></i> Logout
                                    </a>
                                </li>
                            </jstl:if>
                            <jstl:if test="${ j.equals(6) }">
                                <li class="waves-effect waves-light">
                                    <a href="${ jsp_pagePrevious }">
                                        <i class="ti-layout-sidebar-left"></i>
                                        <jstl:out value="${ jsp_previousMsg }">Lost Value</jstl:out>
                                    </a>
                                </li>
                            </jstl:if>
                            <jstl:if test="${ j.equals(7) }">
                                <li class="waves-effect waves-light">
                                    <a href="#" onclick='Salir()'>
                                        <i class="ti-layout-sidebar-left"></i> Logout
                                    </a>
                                </li>
                            </jstl:if>
                        </jstl:forTokens>
                        <jstl:remove var="i" scope="request"/>
                        <jstl:remove var="j" scope="request"/>
                        <jstl:remove var="jsp_menuOption" scope="page"/>
                        <!--end ############################################### menu desplegable ###############################################-->
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>