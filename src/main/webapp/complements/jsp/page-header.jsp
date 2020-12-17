<!-- Page-header start -->
<div class="page-header">
    <div class="page-block">
        <div class="row align-items-center">
            <div class="col-md-8">
                <div class="page-header-title">
                    <h5 class="m-b-10"><jstl:out value="${ jsp_titleFrom }">Lost Value</jstl:out>&nbsp;</h5>
                    <p class="m-b-0"><jstl:out value="${ jsp_funtionFrom }">Lost Value</jstl:out></p>
                </div>
            </div>
            <div class="col-md-4">
                <ul class="breadcrumb">
                    <li class="breadcrumb-item">
                        <a href="/signin"> <i class="fa fa-home"></i> </a>
                    </li>
                    <li class="breadcrumb-item">
                        <jstl:choose>
                            <jstl:when test="${ jsp_pagePrevious=='/logout' }">
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
        </div>
    </div>
</div>
<jstl:remove var="jsp_titleFrom" scope="page"/>
<jstl:remove var="jsp_funtionFrom" scope="page"/>
<!-- Page-header end -->