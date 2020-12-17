<%--
  Created by IntelliJ IDEA.
  User: pavelaz
  Date: 22/4/20
  Time: 11:21 p. m.
  To change this template use File | Settings | File Templates.
  <!-- Empieza Page-header Segunda linea azul del tope -->
--%>
<div class="page-header">
    <div class="page-block">
        <div class="row align-items-center">
            <div class="col-md-8">
                <div class="page-header-title">
                    <h5 class="m-b-10"><jstl:out value="${ jsp_dashbName }">Lost Value</jstl:out></h5>
                    <p class="m-b-0"><jstl:out value="${ jsp_dashbMsg1 }">Lost Value</jstl:out></p>
                </div>
            </div>
            <div class="col-md-4">
                <ul class="breadcrumb">
                    <li class="breadcrumb-item">
                        <a href="${ jsp_dashbHome }"> <i class="fa fa-home"></i> </a>
                    </li>
                    <li class="breadcrumb-item"><a href="#!"><jstl:out value="${ jsp_dashbBodyMsg }">Lost Value</jstl:out></a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
