<%--
  Created by IntelliJ IDEA.
  User: pavelaz
  Date: 22/4/20
  Time: 11:17 p. m.
  To change this template use File | Settings | File Templates.
  <!--  Empieza Menu del lado izquierdo -->
--%>
<nav class="pcoded-navbar">
    <div class="pcoded-inner-navbar main-menu">
        <!-- Aqui Maestros Basicos -->
        <jstl:forTokens var="i" items="${ jsp_navBarOption }" delims=",">
            <fmt:parseNumber var="j" integerOnly="true" type="number" value="${ i }" scope="request"/>
            <jstl:if test="${ j.equals(1) }">
                <!-- modulo miscelaneos -->
                <div class="pcoded-navigation-label">Miscellaneous main files</div>
                <ul class="pcoded-item pcoded-left-item">
                    <li class="pcoded-hasmenu">
                        <a href="javascript:void(0)" class="waves-effect waves-dark">
                            <span class="pcoded-micon"><i class="ti-layout-grid2-alt"></i><b>BC</b></span>
                            <span class="pcoded-mtext">Main Files</span>
                            <span class="pcoded-mcaret"></span>
                        </a>
                        <ul class="pcoded-submenu">
                            <li class=" ">
                                <a href="/objectboard/countries" class="waves-effect waves-dark">
                                    <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                                    <span class="pcoded-mtext">Countries</span>
                                    <span class="pcoded-mcaret"></span>
                                </a>
                            </li>
                            <li class=" ">
                                <a href="/objectboard/states" class="waves-effect waves-dark">
                                    <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                                    <span class="pcoded-mtext">States</span>
                                    <span class="pcoded-mcaret"></span>
                                </a>
                            </li>
                            <li class="">
                                <a href="/objectboard/cities" class="waves-effect waves-dark">
                                    <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                                    <span class="pcoded-mtext">Cities</span>
                                    <span class="pcoded-mcaret"></span>
                                </a>
                            </li>
                            <li class=" ">
                                <a href="/objectboard/bussinesstype" class="waves-effect waves-dark">
                                    <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                                    <span class="pcoded-mtext">Bussiness Type</span>
                                    <span class="pcoded-mcaret"></span>
                                </a>
                            </li>
                            <li class=" ">
                                <a href="/objectboard/project" class="waves-effect waves-dark">
                                    <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                                    <span class="pcoded-mtext">Project</span>
                                    <span class="pcoded-mcaret"></span>
                                </a>
                            </li>
                            <li class=" ">
                                <a href="/objectboard/userprofileconsult" class="waves-effect waves-dark">
                                    <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                                    <span class="pcoded-mtext">Users Master</span>
                                    <span class="pcoded-mcaret"></span>
                                </a>
                            </li>
                            <li class=" ">
                                <a href="/objectboard/userroles" class="waves-effect waves-dark">
                                    <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                                    <span class="pcoded-mtext">User Roll</span>
                                    <span class="pcoded-mcaret"></span>
                                </a>
                            </li>
                            <li class=" ">
                                <a href="/objectboard/discharges" class="waves-effect waves-dark">
                                    <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                                    <span class="pcoded-mtext">Discharge</span>
                                    <span class="pcoded-mcaret"></span>
                                </a>
                            </li>
                            <li class=" ">
                                <a href="/objectboard/bussinessunits" class="waves-effect waves-dark">
                                    <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                                    <span class="pcoded-mtext">Bussiness Unit</span>
                                    <span class="pcoded-mcaret"></span>
                                </a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </jstl:if>
            <jstl:if test="${ j.equals(2) }">
                <div class="pcoded-navigation-label">General main files</div>
                <ul class="pcoded-item pcoded-left-item">
                    <li class="pcoded-hasmenu">
                        <a href="javascript:void(0)" class="waves-effect waves-dark">
                            <span class="pcoded-micon"><i class="ti-layout-grid2-alt"></i><b>BC</b></span>
                            <span class="pcoded-mtext">Main Files</span>
                            <span class="pcoded-mcaret"></span>
                        </a>
                        <ul class="pcoded-submenu">
                            <li class=" ">
                                <a href="/objectboard/pruebaimagen" class="waves-effect waves-dark">
                                    <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                                    <span class="pcoded-mtext">Prueba imagen</span>
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
                            <li class=" ">
                                <a href="#!" class="waves-effect waves-dark">
                                    <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                                    <span class="pcoded-mtext">Color</span>
                                    <span class="pcoded-mcaret"></span>
                                </a>
                            </li>
                            <li class=" ">
                                <a href="#!" class="waves-effect waves-dark">
                                    <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                                    <span class="pcoded-mtext">Label Badge</span>
                                    <span class="pcoded-mcaret"></span>
                                </a>
                            </li>
                            <li class=" ">
                                <a href="#!" class="waves-effect waves-dark">
                                    <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                                    <span class="pcoded-mtext">Tooltip And Popover</span>
                                    <span class="pcoded-mcaret"></span>
                                </a>
                            </li>
                            <li class=" ">
                                <a href="#!" class="waves-effect waves-dark">
                                    <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                                    <span class="pcoded-mtext">Typography</span>
                                    <span class="pcoded-mcaret"></span>
                                </a>
                            </li>
                            <li class=" ">
                                <a href="#!" class="waves-effect waves-dark">
                                    <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                                    <span class="pcoded-mtext">Notifications</span>
                                    <span class="pcoded-mcaret"></span>
                                </a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </jstl:if>
            <jstl:if test="${ j.equals(3) }">
                <div class="pcoded-navigation-label">Forms</div>
                <ul class="pcoded-item pcoded-left-item">
                    <li class="">
                        <!--<a href="form-elements-component.html" class="waves-effect waves-dark">-->
                        <a href="#!" class="waves-effect waves-dark">
                            <span class="pcoded-micon"><i class="ti-layers"></i><b>FC</b></span>
                            <span class="pcoded-mtext">Form</span>
                            <span class="pcoded-mcaret"></span>
                        </a>
                    </li>
                </ul>
            </jstl:if>
            <jstl:if test="${ j.equals(4) }">
                <div class="pcoded-navigation-label">Tables</div>
                <ul class="pcoded-item pcoded-left-item">
                    <li class="">
                        <a href="#!" class="waves-effect waves-dark">
                            <span class="pcoded-micon"><i class="ti-receipt"></i><b>B</b></span>
                            <span class="pcoded-mtext">Table</span>
                            <span class="pcoded-mcaret"></span>
                        </a>
                    </li>
                </ul>
            </jstl:if>
            <jstl:if test="${ j.equals(5) }">
                <div class="pcoded-navigation-label">Chart And Maps</div>
                <ul class="pcoded-item pcoded-left-item">
                    <li class="">
                        <!--<a href="chart-morris.html" class="waves-effect waves-dark">-->
                        <a href="#!" class="waves-effect waves-dark">
                            <span class="pcoded-micon"><i class="ti-bar-chart-alt"></i><b>C</b></span>
                            <span class="pcoded-mtext">Charts</span>
                            <span class="pcoded-mcaret"></span>
                        </a>
                    </li>
                    <li class="">
                        <!--<a href="map-google.html" class="waves-effect waves-dark">-->
                        <a href="#!" class="waves-effect waves-dark">
                            <span class="pcoded-micon"><i class="ti-map-alt"></i><b>M</b></span>
                            <span class="pcoded-mtext">Maps</span>
                            <span class="pcoded-mcaret"></span>
                        </a>
                    </li>
                </ul>
            </jstl:if>
            <jstl:if test="${ j.equals(6) }">
                <div class="pcoded-navigation-label">Pages</div>
                <ul class="pcoded-item pcoded-left-item">
                    <li class="pcoded-hasmenu ">
                        <a href="javascript:void(0)" class="waves-effect waves-dark">
                            <span class="pcoded-micon"><i class="ti-id-badge"></i><b>A</b></span>
                            <span class="pcoded-mtext">Various pages</span>
                            <span class="pcoded-mcaret"></span>
                        </a>
                        <ul class="pcoded-submenu">
                            <li class="">
                                <a href="/objectboard/masteruser" class="waves-effect waves-dark">
                                    <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                                    <span class="pcoded-mtext">
                                        <i class="ti-user"></i>&nbsp;&nbsp;&nbsp;My Profile
                                    </span>
                                    <span class="pcoded-mcaret"></span>
                                </a>
                            </li>
                            <li class="">
                                <a href="/objectboard/logout" class="waves-effect waves-dark">
                                    <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                                    <span class="pcoded-mtext">
                                        <i class="ti-layout-sidebar-left"></i>&nbsp;&nbsp;&nbsp;System Logout
                                    </span>
                                    <span class="pcoded-mcaret"></span>
                                </a>
                            </li>
                            <!--<li class="">
                                <a href="sample-page.html" class="waves-effect waves-dark">
                                    <span class="pcoded-micon"><i class="ti-layout-sidebar-left"></i><b>S</b></span>
                                    <span class="pcoded-mtext">Sample Page</span>
                                    <span class="pcoded-mcaret"></span>
                                </a>
                            </li>-->
                        </ul>
                    </li>
                </ul>
            </jstl:if>
            <jstl:if test="${ j.equals(8) }">
                <!-- Bloque del menu particular de alex -->
                <div class="pcoded-navigation-label">Option Element</div>
                <ul class="pcoded-item pcoded-left-item">
                    <li class=" " name="p_btSave" id="btSave">
                        <!--<a class="waves-effect waves-dark" onclick="">
                            <span class="pcoded-micon"><i class="ti-save"></i><b>B</b></span>
                            <span class="pcoded-mtext"></span>
                            <span class="pcoded-mcaret">Save</span>
                        </a>-->
                    </li>
                </ul>
            </jstl:if>
        </jstl:forTokens>
        <jstl:remove var="i" scope="request"/>
        <jstl:remove var="j" scope="request"/>
    </div>
</nav>
