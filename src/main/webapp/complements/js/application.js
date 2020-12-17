$(document).ready(function(){
    $('input').last().on('click',function(){
        if ($("input[name=format]").prop( "checked")===true){
            $("form").submit();
        }
        else{
            $.ajax({
                url: "rest/countryunit",
                data: $("form").serialize(),
                success: function(resultObject) {
                    $("p").remove()
                    /*var message="<br/><p>La somme des 2 nombres fournis est <strong>" + resultObject. + "-" + resultObject.somme.texte + "</strong></p>";
                    message=message.concat("<br/><p>Le produit des 2 nombres fournis est <strong>"+ resultObject.produit.numerique + "-" + resultObject.produit.texte + "</strong></p>");
                    $("form").after(message);*/
                }
            });
        }
    });
});


