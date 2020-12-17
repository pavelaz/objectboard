<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<jstl:if test="${ !empty foto }"><p><jstl:out value="La foto ${ foto } (${ description }) a sido enviada! "/></p></jstl:if>
<form id="formPrueba" method="post" action="/objectboard/multipartconfigservlet" enctype="multipart/form-data">
    <p>
        <label for="descripcion">Descripcion del archivo: </label>
        <input name="descripcion" id="descripcion" type="text">
    </p>

    <p>
        <label for="file">Foto a enviar: </label>
        <input name="file" id="file" type="file">
    </p>

    <input type="submit">Send</input>
</form>
<script src="<%= request.getContextPath() %>/complements/js/jquery-3.4.1.min.js" >

    $("#formPrueba").submit(function () {
            alert('paso');
        }
    );
    /*function sendForm(form){
        alert('paso pruebaServlet');
        const FormData = new FormData((form)[0]);
        alert('paso pruebaServlet');
        $.ajax({
            url: '/objectboard/multipartconfigservlet',
            type: 'post',
            data: FormData,
            async: false,
            success: function (data) { //en data se encuentra toda la informacion del html.
                alert('paso pruebaServlet');
                alert(data);
            },
            cache: false,
            contentType: false,
            processData: false
        });
        return false;
    };*/


</script>
</body>
</html>
