function filePreview(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            //('#uploadFrom + img').remove();
            $('#file').before('<img id="oldPhoto" name="oldPhoto" class="img-thumbnail" alt="Photo Profile" src="' + e.target.result + '" width="100" height="120" /> &nbsp');
        }
        reader.readAsDataURL(input.files[0]);
    }
}
$("#file").change (function(){
    filePreview(this);
});