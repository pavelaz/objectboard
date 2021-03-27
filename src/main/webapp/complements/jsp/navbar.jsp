<nav class="pcoded-navbar">
    <div class="sidebar_toggle"><a href="#"><i class="icon-close icons"></i></a></div>
    <div class="pcoded-inner-navbar main-menu">
        <!--start################################# body navbar  #################################-->
        <jstl:forTokens var="i" items="${ jsp_navBarOption }" delims=",">
            <jstl:if test="${ i.equals('1') }">  <%--<Navigation--%>
                <!-- Bloque del menu con subopciones -->
                <div class="pcoded-navigation-label">Navigation</div>
                <ul class="pcoded-item pcoded-left-item">
                    <li class="">
                        <a href="${ jsp_pagePrevious }" class="waves-effect waves-dark">
                            <span class="pcoded-micon"><i class="ti-home"></i><b>D</b></span>
                            <span class="pcoded-mtext">Dashboard</span>
                            <span class="pcoded-mcaret"></span>
                        </a>
                    </li>
                </ul>
            </jstl:if>
            <jstl:if test="${ i.substring(0,1).equals('2') }"><%--<Miscellaneous main files 1--%>
                <div class="pcoded-navigation-label">Miscellaneous main files</div>
                <ul class="pcoded-item pcoded-left-item">
                    <li class="pcoded-hasmenu">
                        <a href="javascript:void(0)" class="waves-effect waves-dark">
                            <span class="pcoded-micon"><i class="ti-menu-alt"></i><b>BC</b></span>
                            <span class="pcoded-mtext">Main Files</span>
                            <span class="pcoded-mcaret"></span>
                        </a>
                        <jstl:forTokens var="x" items="${ i }" delims="." >
                            <ul class="pcoded-submenu">
                            <jstl:if test="${ x.equals('1') }">
                                <li class=" ">
                                    <a href="/objectboard/organizations" class="waves-effect waves-dark">
                                        <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                                        <span class="pcoded-mtext">Organization</span>
                                        <span class="pcoded-mcaret"></span>
                                    </a>
                                </li><%--<Miscellaneous main files 2.1--%>
                            </jstl:if>
                            <jstl:if test="${ x.equals('3') }">
                                <li class=" ">
                                    <a href="/objectboard/typifieds" class="waves-effect waves-dark">
                                        <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                                        <span class="pcoded-mtext">Typified</span>
                                        <span class="pcoded-mcaret"></span>
                                    </a>
                                </li><%--<Miscellaneous main files 2.3--%>
                            </jstl:if>
                            <jstl:if test="${ x.equals('4') }">
                                <li class="">
                                    <a href="/objectboard/certifications" class="waves-effect waves-dark">
                                        <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                                        <span class="pcoded-mtext">Certifications</span>
                                        <span class="pcoded-mcaret"></span>
                                    </a>
                                </li><%--<Miscellaneous main files 2.4--%>
                            </jstl:if>
                            <jstl:if test="${ x.equals('5') }">
                                <!--<li class="">
                                    <a href="../../WEB-INF/pages/jsp/master/prueba.jsp" class="waves-effect waves-dark">
                                        <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                                        <span class="pcoded-mtext">Comments</span>
                                        <span class="pcoded-mcaret"></span>
                                    </a>
                                </li>--><%--<Miscellaneous main files 2.5--%>
                            </jstl:if>
                            <jstl:if test="${ x.equals('6') }">
                                    <li class=" ">
                                        <a href="/objectboard/discharges" class="waves-effect waves-dark">
                                            <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                                            <span class="pcoded-mtext">Discharges</span>
                                            <span class="pcoded-mcaret"></span>
                                        </a>
                                    </li><%--<Miscellaneous main files 2.6--%>
                            </jstl:if>
                            <jstl:if test="${ x.equals('7') }">
                                    <li class=" ">
                                        <a href="/objectboard/contacts" class="waves-effect waves-dark">
                                            <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                                            <span class="pcoded-mtext">Contacts</span>
                                            <span class="pcoded-mcaret"></span>
                                        </a>
                                    </li><%--<Miscellaneous main files 2.7--%>
                            </jstl:if>
                        </ul>
                        </jstl:forTokens>
                    </li>
                </ul>
            </jstl:if>
            <jstl:if test="${ i.substring(0,1).equals('3') }"><%--<General main files 1--%>
                <div class="pcoded-navigation-label">General main files</div>
                <ul class="pcoded-item pcoded-left-item">
                    <li class="pcoded-hasmenu">
                        <a href="javascript:void(0)" class="waves-effect waves-dark">
                            <span class="pcoded-micon"><i class="ti-layout-grid2-alt"></i><b>BC</b></span>
                            <span class="pcoded-mtext">Main Process</span>
                            <span class="pcoded-mcaret"></span>
                        </a>
                        <jstl:forTokens var="x" items="${ i }" delims="." >
                            <ul class="pcoded-submenu">
                            <jstl:if test="${ x.equals('1')}">
                            <li class=" ">
                                <a href="/objectboard/headerpolls" class="waves-effect waves-dark">
                                    <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                                    <span class="pcoded-mtext">Survey</span>
                                    <span class="pcoded-mcaret"></span>
                                </a>
                            </li><%--<General main files 3.1--%>
                            </jstl:if>
                            <jstl:if test="${ x.equals('2')}">
                            <li class=" ">
                                <a href="/objectboard/assignments" class="waves-effect waves-dark">
                                    <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                                    <span class="pcoded-mtext">Assignments</span>
                                    <span class="pcoded-mcaret"></span>
                                </a>
                            </li><%--<General main files 3.2--%>
                            </jstl:if>
                            <jstl:if test="${ x.equals('4') }">
                            <li class="">
                                <a href="/objectboard/conduct" class="waves-effect waves-dark">
                                    <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                                    <span class="pcoded-mtext">Conduct Survey</span>
                                    <span class="pcoded-mcaret"></span>
                                </a>
                            </li><%--<General main files 3.4--%>
                            </jstl:if>
                            <jstl:if test="${ x.equals('5') }">
                            <li class=" ">
                                <a href="/objectboard/audits" class="waves-effect waves-dark">
                                    <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                                    <span class="pcoded-mtext">Audits</span>
                                    <span class="pcoded-mcaret"></span>
                                </a>
                            </li><%--<General main files 3.5--%>
                            </jstl:if>
                            <jstl:if test="${ x.equals('6') }">
                                    <li class=" ">
                                        <a href="/objectboard/contactslist" class="waves-effect waves-dark">
                                            <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                                            <span class="pcoded-mtext">Contacts List</span>
                                            <span class="pcoded-mcaret"></span>
                                        </a>
                                    </li><%--<General main files 3.6--%>
                            </jstl:if>
                            <jstl:if test="${ x.equals('7') }">
                                 <li class=" ">
                                        <a href="/objectboard/message" class="waves-effect waves-dark">
                                            <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                                            <span class="pcoded-mtext">Messages</span>
                                            <span class="pcoded-mcaret"></span>
                                        </a>
                                 </li><%--<General main files 3.7--%>
                            </jstl:if>
                        </ul>
                        </jstl:forTokens>
                    </li>
                </ul>
            </jstl:if>
            <jstl:if test="${ i.substring(0,1).equals('4')  }"><%--<General main files 2 (Disponible no utilizado)--%>
                <%-- <div class="pcoded-navigation-label">General main files</div>
                <ul class="pcoded-item pcoded-left-item">
                    <li class="pcoded-hasmenu">
                        <a href="javascript:void(0)" class="waves-effect waves-dark">
                            <span class="pcoded-micon"><i class="ti-layout-grid2-alt"></i><b>BC</b></span>
                            <span class="pcoded-mtext">Main Files</span>
                            <span class="pcoded-mcaret"></span>
                        </a>
                        <ul class="pcoded-submenu">
                            <li class=" ">
                                <a href="#!" class="waves-effect waves-dark">
                                    <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                                    <span class="pcoded-mtext">Bussiness Type</span>
                                    <span class="pcoded-mcaret"></span>
                                </a>
                            </li>
                            <li class=" ">
                                <a href="#!" class="waves-effect waves-dark">
                                    <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                                    <span class="pcoded-mtext">Project</span>
                                    <span class="pcoded-mcaret"></span>
                                </a>
                            </li>
                            <li class="">
                                <a href="#!" class="waves-effect waves-dark">
                                    <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                                    <span class="pcoded-mtext">Users Master</span>
                                    <span class="pcoded-mcaret"></span>
                                </a>
                            </li>
                            <li class=" ">
                                <a href="#!" class="waves-effect waves-dark">
                                    <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                                    <span class="pcoded-mtext">Users Roll</span>
                                    <span class="pcoded-mcaret"></span>
                                </a>
                            </li>
                            <li class=" ">
                                <a href="#!" class="waves-effect waves-dark">
                                    <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                                    <span class="pcoded-mtext">Bussiness Unit</span>
                                    <span class="pcoded-mcaret"></span>
                                </a>
                            </li>
                        </ul>
                    </li>
                </ul> --%>
            </jstl:if>
            <jstl:if test="${ i.substring(0,1).equals('5') }"><%-- General main Reportes --%>
                <div class="pcoded-navigation-label">Management Reports</div>
                <ul class="pcoded-item pcoded-left-item">
                    <jstl:forTokens var="j" items="${ i }" delims=".">
                        <jstl:if test="${ j.substring(0,1).equals('1') }">
                            <li class="pcoded-hasmenu">
                                <a href="javascript:void(0)" class="waves-effect waves-dark">
                                    <span class="pcoded-micon"><i class="ti-files"></i><b>BC</b></span>
                                    <span class="pcoded-mtext">Files Report</span>
                                    <span class="pcoded-mcaret"></span>
                                </a>
                                <ul class="pcoded-submenu">
                                    <jstl:forTokens var="k" items="${ jsp_reposrt1Option }" delims=".">
                                        <jstl:if test="${ k.substring(0,1).equals('1') }">
                                            <li class=" ">
                                            <a onclick="send_report(2.1)" class="waves-effect waves-dark">
                                                <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                                                <span class="pcoded-mtext">Organization</span>
                                                <span class="pcoded-mcaret"></span>
                                            </a>
                                        </li>
                                        </jstl:if>
                                        <jstl:if test="${ k.substring(0,1).equals('2') }">
                                            <li class=" ">
                                                <a onclick="send_report(2.2)" class="waves-effect waves-dark">
                                                    <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                                                    <span class="pcoded-mtext">Typified</span>
                                                    <span class="pcoded-mcaret"></span>
                                                </a>
                                            </li>
                                        </jstl:if>
                                        <jstl:if test="${ k.substring(0,1).equals('3') }">
                                            <li class=" ">
                                                <a onclick="send_report(2.3)" class="waves-effect waves-dark">
                                                    <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                                                    <span class="pcoded-mtext">Certifications</span>
                                                    <span class="pcoded-mcaret"></span>
                                                </a>
                                            </li>
                                        </jstl:if>
                                    </jstl:forTokens>
                                </ul>
                            </li>
                        </jstl:if>
                        <jstl:if test="${ j.substring(0,1).equals('2') }">
                            <li class="pcoded-hasmenu">
                                <a href="javascript:void(0)" class="waves-effect waves-dark">
                                    <span class="pcoded-micon"><i class="ti-clipboard"></i><b>BC</b></span>
                                    <span class="pcoded-mtext">Process Report</span>
                                    <span class="pcoded-mcaret"></span>
                                </a>
                                <ul class="pcoded-submenu">
                                    <jstl:forTokens var="k" items="${ jsp_reposrt2Option }" delims=".">
                                        <jstl:if test="${ k.substring(0,1).equals('1') }">
                                            <li class=" ">
                                                <a onclick="send_report(1.3)" class="waves-effect waves-dark">
                                                    <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                                                    <span class="pcoded-mtext">Survey</span>
                                                    <span class="pcoded-mcaret"></span>
                                                </a>
                                            </li>
                                        </jstl:if>
                                        <jstl:if test="${ k.substring(0,1).equals('2') }">
                                            <li class=" ">
                                            <a onclick="send_report(1.2)" class="waves-effect waves-dark">
                                                <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                                                <span class="pcoded-mtext">Assignment</span>
                                                <span class="pcoded-mcaret"></span>
                                            </a>
                                        </li>
                                        </jstl:if>
                                        <jstl:if test="${ k.substring(0,1).equals('3') }">
                                            <li class=" ">
                                            <a onclick="send_report(1.4)" class="waves-effect waves-dark">
                                                <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                                                <span class="pcoded-mtext">Consult Survey</span>
                                                <span class="pcoded-mcaret"></span>
                                            </a>
                                        </li>
                                        </jstl:if>
                                        <jstl:if test="${ k.substring(0,1).equals('4') }">
                                            <li class="">
                                            <a onclick="send_report(1.5)" class="waves-effect waves-dark">
                                                <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                                                <span class="pcoded-mtext">Audit</span>
                                                <span class="pcoded-mcaret"></span>
                                            </a>
                                        </li>
                                        </jstl:if>
                                    </jstl:forTokens>
                                </ul>
                            </li>
                        </jstl:if>
                    </jstl:forTokens>
                </ul>
            </jstl:if>
            <jstl:if test="${ i.equals('6') }"><%--< Tables --%>
                <div class="pcoded-navigation-label">Tables</div>
                <ul class="pcoded-item pcoded-left-item">
                    <li class="">
                        <a href="bs-basic-table.html" class="waves-effect waves-dark">
                            <span class="pcoded-micon"><i class="ti-receipt"></i><b>B</b></span>
                            <span class="pcoded-mtext">Table</span>
                            <span class="pcoded-mcaret"></span>
                        </a>
                    </li>
                </ul>
            </jstl:if>
            <jstl:if test="${ i.equals('7') }"><%--< Chart And Maps --%>
                <div class="pcoded-navigation-label">Chart And Maps</div>
                <ul class="pcoded-item pcoded-left-item">
                    <li class="">
                        <a href="#!" class="waves-effect waves-dark">
                            <span class="pcoded-micon"><i class="ti-bar-chart-alt"></i><b>C</b></span>
                            <span class="pcoded-mtext">Charts</span>
                            <span class="pcoded-mcaret"></span>
                        </a>
                    </li>
                    <li class="">
                        <a href="#!" class="waves-effect waves-dark">
                            <span class="pcoded-micon"><i class="ti-map-alt"></i><b>M</b></span>
                            <span class="pcoded-mtext">Maps</span>
                            <span class="pcoded-mcaret"></span>
                        </a>
                    </li>
                </ul>
            </jstl:if>
            <jstl:if test="${ i.equals('8') }"><%--<Miscellaneous main files 2 (Disponible no utilizado)--%>
                <div class="pcoded-navigation-label">Miscellaneous main files</div>
                <ul class="pcoded-item pcoded-left-item">
                    <li class="pcoded-hasmenu">
                        <a href="javascript:void(0)" class="waves-effect waves-dark">
                            <span class="pcoded-micon"><i class="ti-layout-grid2-alt"></i><b>BC</b></span>
                            <span class="pcoded-mtext">Main Files</span>
                            <span class="pcoded-mcaret"></span>
                        </a>
                        <ul class="pcoded-submenu">
                            <ul class="pcoded-item pcoded-left-item">
                                <li class=" "></br>
                                    &nbsp;&nbsp;&nbsp;&nbsp;
                                    <button id="buttonSave" type="button" class="btn-xs" disabled="false"><i class="ti-save"></i></button>
                                    &nbsp;&nbsp;&nbsp;
                                    <span id="textSave" class="pcoded-mcaret">(Off) Save</span>
                                </li>
                            </ul>
                            <ul class="pcoded-item pcoded-left-item">
                                <li class=" "></br>
                                    &nbsp;&nbsp;&nbsp;&nbsp;
                                    <button id="buttonPlus" type="button" class="btn-xs btn-primary"><i class="ti-plus"></i></button>
                                    &nbsp;&nbsp;&nbsp;
                                    <span class="pcoded-mcaret">Plus</span>
                                </li>
                            </ul>
                            <ul class="pcoded-item pcoded-left-item">
                                <li class=" "></br>
                                    &nbsp;&nbsp;&nbsp;&nbsp;
                                    <button id="buttonTrash" type="button" class="btn-xs" disabled="false"><i class="ti-trash"></i></button>
                                    &nbsp;&nbsp;&nbsp;
                                    <span id="textTrash" class="pcoded-mcaret">(Off) Trash</span>
                                </li>
                            </ul>
                            <ul class="pcoded-item pcoded-left-item">
                                <li class=" "></br>
                                    &nbsp;&nbsp;&nbsp;&nbsp;
                                    <button type="button" id="buttonpdf" class="btn-primary btn-xs" onClick="" ><i class="ti-printer"></i></button>
                                    &nbsp;&nbsp;&nbsp;
                                    <span class="pcoded-mcaret">PDF</span>
                                </li>
                            </ul>
                        </ul>
                    </li>
                </ul>
            </jstl:if>
        </jstl:forTokens>
        <a class="navbar-brand" href="#!">${ application.name }</a>
        <jstl:remove var="jsp_navBarOption" scope="page"/>
        <jstl:remove var="i" scope="request"/>
        <jstl:remove var="j" scope="request"/>
    </div>
</nav>
<%--<form id="form_send" style="display: none" method="get" action="http://localhost:4200/pdf/${ rq_dataUser }/${ rq_dataPassword }/${ rq_companyNumber }" target="_blank"/>--%>


