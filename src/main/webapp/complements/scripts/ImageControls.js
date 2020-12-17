/*
Esta funcion elimina primero una etiqueta <img > con id = "oldPhoto"con la imagen que trae y luego crea otra
etiqueta <img> con una nueva, Todo esto en funcion de si una etiqueta input tipo file cambia o no
 */
function filePreview(input) {
    var oldPhoto = document.getElementById('oldPhoto');
    var filePath = input.value;
    var allowedExtensions = /(\.jpg|\.png)$/i;
    if (!allowedExtensions.exec(filePath)) {
        alert("Please upload file having extensions .jpg/ .png/ only.");
        input.value = "";
        return false;
    }else{
        if (input.files[0].size > (1024 * 1024 * 1)){ // 1024 * 1024 * 1,= 1 MB)
            alert("Please upload a photo not exceeding 1 Mb.");
            input.value = "";
            return false;
        }
        else{
            if (input.files && input.files[0]) {
                var reader = new FileReader();
                var image = new Image();
                reader.onload = function (e) {
                    image.src = e.target.result;
                    //Validate the File Height and Width.
                    image.onload = function (){
                        var height = this.height;
                        var width = this.width;
                        if ( 500 > height &&  500 > width){
                            oldPhoto.remove();
                            //alert("Height and Width must not exceed a Photo the."+height+" "+width);
                            $('#file').before('<img id="oldPhoto" name="oldPhoto" class="img-thumbnail" alt="Photo Profile" src="' + e.target.result + '" width="100" height="120" /> &nbsp');
                            return false;
                        }
                        alert("Uploaded image has valid Height and Width 500x500");
                        return true;
                    }
                }
                reader.readAsDataURL(input.files[0]);
            }
        }
    }
}