$("#file").change (function(){
    alert("paso");
    document.formPhoto.submit(function(e){
        alert("paso2");
        e.preventDefault();
        $.ajax({
            url: "/masteruser",
            type:"POST",
            data: "data",
            success: function(data) {
                $("#forma").html(data);
            },
            error: function (jXHR, textStatus, errorThrown){
                alert(errorThrown);
            }
        });
    });
});
document.getElementById("buttonSave").onclick = (function () {
    const x = confirm("Are you sure to save the changes?")
    if (x){
        $("#buttonSave").removeClass("btn-primary");
        document.getElementById("buttonSave").disabled = true;
    }
});

document.formPhoto.submit(function(e){
    e.preventDefault();
    $.ajax({
        url: "/masteruser",
        type:"POST",
        data: "data",
        success: function(data) {
            $("#formaPhoto").html(data);
        },
        error: function (jXHR, textStatus, errorThrown){
            alert(errorThrown);
        }
    });
});
$("#formPhoto").submit(function(){
    var formData = new FormData($(this)[0]);
    $.ajax({
        url: '/masteruser',
        type: 'POST',
        data: formData,
        async: false,
        success: function (data) {//data contiene toda la informacion de la paguina html
            document.getElementById("textSave").innerHTML = "Updated data";
        },
        cache: false,
        contentType: false,
        processData: false
    });
    return false;
});

/*<input type="hidden" name="p_updateReady_form" value="F"/>
    <!--<input type="hidden" name="p_updateReady_Photo" id="p_updateReady_Photo" value="F"/>-->
    <%--<jstl:set var="jsp_updateReadyPhoto" value="F" scope="page"/>--%>
    ;<jstl:set target='jsp_updateReadyPhoto'/>" value="T"/>


onsubmit="return updateMasterUser('F')"*/