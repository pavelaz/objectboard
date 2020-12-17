    <div class="card borderless-card" >
        <div class="card-block primary-breadcrumb">
            <div class="breadcrumb-header">
                <h5>Servicios PVSoft LLC</h5>
                <span>Making history with effort, always based on our systematic mentality of difficult time !! AR.</span>
            </div>
            <div class="page-header-breadcrumb">
                    <div class="col-md-4">
                        <ul class="breadcrumb">
                            <li class="breadcrumb-item">
                                <a href="/signin"> <i class="fa fa-home"></i> </a>
                            </li>
                            <li class="breadcrumb-item">
                                <jstl:choose>
                                    <jstl:when test="${ jsp_pagePrevious=='/objectboard/logout' }">
                                        <a href="#" onclick="Salir()">
                                            <jstl:out value="${ jsp_previousMsg }">Lost Value</jstl:out>
                                        </a>
                                    </jstl:when>
                                    <jstl:otherwise>
                                        <a href="${ jsp_pagePrevious }">
                                            <jstl:out value="${ jsp_previousMsg }">Lost Value</jstl:out>
                                        </a>
                                    </jstl:otherwise>
                                </jstl:choose>
                            </li>
                        </ul>
                    </div>
                </ul>
            </div>
        </div>
    </div>
    <jstl:remove var="jsp_pagePrevious" scope="page"/>
    <jstl:remove var="jsp_previousMsg" scope="page"/>
    <!-- Primary-color Breadcrumb card end -->
</footer>