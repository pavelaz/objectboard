<%--
  Created by IntelliJ IDEA.
  User: pavelaz
  Date: 6/4/20
  Time: 2:30 p. m.
  To change this template use File | Settings | File Templates.
  <%@ page contentType="text/html;charset=UTF-8" language="java" %>
--%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Countries Files</title>
    <!-- HTML5 Shim and Respond.js IE10 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 10]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!-- Meta -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />

    <meta name="keywords" content="flat ui, admin Admin , Responsive, Landing, Bootstrap, App, Template, Mobile, iOS, Android, apple, creative app">
    <meta name="author" content="Codedthemes" />
    <!-- Favicon icon -->
    <link rel="shortcut icon" type="image/png" href="../../../complements/img/favicon1.png">
    <!-- Google font-->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800" rel="stylesheet">
    <!-- Required Fremwork
    <link rel="stylesheet" type="text/css" href="../../../complements/assets/css/bootstrap/css/bootstrap.min.css">-->
    <!-- Extern -->
    <link href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" rel="stylesheet">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
   <!-- waves.css -->
    <link rel="stylesheet" href="../../../complements/assets/pages/waves/css/waves.min.css" type="text/css" media="all">
    <!-- themify-icons line icon -->
    <link rel="stylesheet" type="text/css" href="../../../complements/assets/icon/themify-icons/themify-icons.css">
    <!-- feather icon -->
    <link rel="stylesheet" type="text/css" href="../../../complements/assets/icon/feather/css/feather.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" type="text/css" href="../../../complements/assets/icon/font-awesome/css/font-awesome.min.css">
    <!-- Style.css -->
    <link rel="stylesheet" type="text/css" href="../../../complements/assets/css/style.css">
    <link rel="stylesheet" type="text/css" href="../../../complements/assets/css/jquery.mCustomScrollbar.css">
    <!-- Propias -->
    <link rel="stylesheet" type="text/css" href="../../../complements/css/own_style.css">
    <script src="../../../complements/scripts/validaciones.js" type="text/javascript"></script>
    <!-- relacionadas con tablas -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.16.0/bootstrap-table.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.16.0/extensions/filter-control/bootstrap-table-filter-control.min.css" rel="stylesheet">

    <script type="text/javascript">
        function desplaza(panta) {
            if (document.forma.p_lista_ant.value !== panta){
                document.forma.target = "";
                document.forma.action = '/countries';
                document.forma.p_listas.value = panta;
                document.forma.p_lista_ant.value = panta;
                document.forma.submit();
            }
        }
        function buscar() {
            if (valida_textos(document.forma.p_buscar.value,"Field to search","") &&
                valida_largos(document.forma.p_buscar.value.length,"Field to search",1))
            {
                document.forma.target = "";
                document.forma.p_listas.value = 1;
                document.forma.action = '/countries';
                document.forma.submit();
            }
        }
        function marcar(source) {
            checkboxes=document.getElementsByTagName('input'); //obtenemos todos los controles del tipo Input
            for(i=0;i<checkboxes.length;i++) //recoremos todos los controles
            {
                if(checkboxes[i].type == "checkbox") //solo si es un checkbox entramos
                {
                    checkboxes[i].checked=source.checked; //si es un checkbox le damos el valor del checkbox que lo llamó (Marcar/Desmarcar Todos)
                }
            }
        }
    </script>
    <script language="JavaScript" type="text/javascript">
        function borrar_usuario(){
            alert("Paso");
                if(validaItems(this)){
                    alert("Paso 1");
                    if ( confirm("Do You really want to delete the selected Country?")) {
                        alert("Paso 2");
                        //document.forma.target = "";
                        document.forma.action = 'countriesprocess';
                        document.forma.p_acciones.value = "delete";
                        document.forma.submit();
                    }
                }
            alert("Paso 3");
        }
        function nuevo_usuario(){
            document.forma.target = "";
            document.forma.action = '/country';
            document.forma.p_acciones.value = "create";
            document.forma.submit();
        }
        function validaItems(source) {
            var cta=0;
            var checkboxes=document.getElementsByTagName('input'); //obtenemos todos los controles del tipo Input
            for(i=0;i<checkboxes.length;i++) //recoremos todos los controles
            {
                if(checkboxes[i].checked) //solo si es un checkbox entramos y validamos si esta chequeado
                {
                    cta=1; //llevamos control de cuantos de ellos estan chequeados
                }
            }
            if(cta==0){
                alert("There are no country selected to remove.");
                return false;
            }
            return true;
        }
        function valida(valor){
            document.forma.p_pais.value = valor;
            document.forma.target = "";
            document.forma.p_acciones.value = "save";
            document.forma.action = "/country";
            document.forma.submit();
        }
        function listar(){
            document.forma.target = "_blank";
            document.forma.action = '../Pdfs/paises_pdf.php';
            document.forma.submit();
        }
    </script>
</head>

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
    <div class="pcoded-container navbar-wrapper">
        <!--start ############################### navbar-header ###############################-->
        <%@include file="../complements/jsp/navbar-header.jsp"%>
        <!--end ############################### navbar-header ###############################-->
        <!-- Contenedor central -->
        <div class="pcoded-main-container">
                <!--start ############################### page-header ###############################-->
                <%@include file="../complements/jsp/page-header.jsp"%>
            <!--end ############################### page-header ###############################-->
        </div>
        <div class="container mt-2 mb-2">
            <div id="toolbar">
                <button id="add" class="btn btn-primary">Add New</button>
                <button id="remove" class="btn btn-danger">Remove</button>
                <button id="removeall" class="btn btn-danger">Remove all</button>
                &nbsp;&nbsp;
            </div>
            <div id="toolbar1" class="select">
                <select class="form-control">
                    <option value="">Export Basic</option>
                    <option value="all">Export All</option>
                    <option value="selected">Export Select</option>
                </select>
            </div>
            <table
                    id="table"
                    data-toggle="table"
                    data-search="false"
                    data-search-align="left"
                    data-show-columns="true"
                    data-show-toggle="true"
                    data-show-refresh="true"
                    data-show-fullscreen="true"
                    data-show-pagination-switch="true"
                    data-show-print="true"
                    data-show-export="true"
                    data-click-to-select="true"
                    data-pagination="true"
                    data-pagination-pre-text="Previous"
                    data-pagination-next-text="Next"
                    data-pagination-h-align="right"
                    data-pagination-detail-h-align="left"
                    data-toolbar="#toolbar"
                    data-advanced-search="true"
                    data-id-table="advancedTable"
                    data-filter-control="true"
                    data-filter-show-clear="true">
                    <!--data-show-footer="true"
                    data-detail-view="true"
                    data-detail-formatter="detailformatter">-->

                <thead>
                <tr>
                    <th rowspan="2" data-checkbox="true" data-valign="middle"></th>
                    <th colspan="7" data-align="center">COUNTRIES LIST</th>
                </tr>
                <tr>
                    <th data-field="state" data-checkbox="true" data-visible="fasle"></th>
                    <th data-field="id" data-sortable="true" data-switchable="fasle">ID</th>
                    <th data-field="name" data-sortable="true" data-switchable="true" data-filter-control="input">NAME</th>
                    <th data-field="position" data-sortable="true" data-filter-control="select">POSITION</th>
                    <th data-field="office" data-sortable="true">OFFICE</th>
                    <th data-field="age" data-sortable="true">AGE</th>
                    <th data-field="date" data-sortable="true">DATE</th>
                    <th data-field="salary" data-sortable="true">SALARY</th>
                </tr>
                </thead>
                <tbody>
                <jstl:forEach var="x" begin="0" end="${ num_filas -1 }" step="1">
                    <tr>
                        <td></td>
                        <td><jstl:out value="${ Math.toIntExact(paises.get(x).getCoCountryCode()) }">Lost Value</jstl:out></td>
                        <td><jstl:out value="${ paises.get(x).getCoName() }">Lost Value</jstl:out></td>
                        <td>administrador</td>
                        <td>contabilidad</td>
                        <td>45</td>
                        <td>2011/04/25</td>
                        <td>$100,000</td>
                    </tr>
                </jstl:forEach>
                </tbody>
            </table>

        </div>
</div>

<!--end ############################### page-header ##########################-->
<%@include file="../complements/jsp/footer.jsp"%>
<!--end ############################### footer ###############################-->
<!-- Required Jquery -->
<script type="text/javascript" src="../../../complements/assets/js/jquery/jquery.min.js "></script>
<script type="text/javascript" src="../../../complements/assets/js/jquery-ui/jquery-ui.min.js "></script>
<script type="text/javascript" src="../../../complements/assets/js/popper.js/popper.min.js"></script>
<script type="text/javascript" src="../../../complements/assets/js/bootstrap/js/bootstrap.min.js "></script>
<!-- waves js -->
<script src="../../../complements/assets/pages/waves/js/waves.min.js"></script>
<!-- jquery slimscroll js -->
<script type="text/javascript" src="../../../complements/assets/js/jquery-slimscroll/jquery.slimscroll.js"></script>
<script src="../../../complements/assets/js/pcoded.min.js"></script>
<script src="../../../complements/assets/js/vertical/vertical-layout.min.js"></script>
<script src="../../../complements/assets/js/jquery.mCustomScrollbar.concat.min.js"></script>
<!-- Custom js -->
<script type="text/javascript" src="../../../complements/js/script.min.js"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.3.1.min.js "></script>
<!-- Relacionadas con tablas js -->
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.16.0/bootstrap-table.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.16.0/extensions/toolbar/bootstrap-table-toolbar.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.16.0/extensions/filter-control/bootstrap-table-filter-control.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/TableExport/5.2.0/js/tableexport.min.js"></script>
<!-- Para Exportacion js -->
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.5.3/jspdf.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jspdf-autotable/3.4.3/jspdf.plugin.autotable.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jspdf-autotable/3.4.3/jspdf.plugin.autotable.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.16.0/extensions/print/bootstrap-table-print.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.16.0/extensions/export/bootstrap-table-export.min.js"></script>
    <script>
        var table = $('#table')

        $('selected').on('change',function(){
          if ($(this).val() == 'selected'){
              table.bootstrapTable('showColumn','state')
          }  else{
              table.bootstrapTable('hideColumn','state')
          }
          table.bootstrapTable({
              exportDataType: $(this).val(),
              exportTypes: ['json','xml','cvs','txt','sql','excel','pdf']
          })
        })
    </script>
</body>

</html>

