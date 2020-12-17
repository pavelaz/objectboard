//Crear un nuevo elemento en la (li) lista.
(function(){
    'use stric';
    var newElement = document.createElement('li');
    newElement.textContent='I am a new Element';

    var list = document.getElementById('my-list');
    list.insertBefore(newElement, list.firstElementChild);//insertar antes del primero
    console.log(newElement);

}());