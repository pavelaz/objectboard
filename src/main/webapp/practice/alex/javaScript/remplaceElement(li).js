//Reemplazar un elemento en la (li) lista.
(function(){
    'use stric';
    var newElement = document.createElement('li');
    newElement.textContent='I am a new Element';

    var list = document.getElementById('my-list');

    list.insertBefore(newElement, list.firstElementChild);//insertar antes del primero

    var replaceElement = document.createElement('li');
    replaceElement.textContent = 'i am replacing you';

    list.replaceChild(replaceElement, newElement);// Luego reemplaza

    console.log(newElement);

}());