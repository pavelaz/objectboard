var texto = document.getElementById('capture');
texto.addEventListener('keydown', function(keyboardEvent) {
    let code = String.fromCharCode(keyboardEvent.keyCode).toLowerCase().charCodeAt();
    console.log(code);
});

<!--
<input type="text" id="capture">
 -->