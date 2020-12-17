//Re un nuevo elemento en la (li) lista.
(function(){
    'use stric';
    var newElement = document.createElement('li');
    newElement.textContent='I am a new Element';

    var list = document.getElementById('my-list');

    list.removeChild(list.firstElementChild); // Remuevo el primero

    list.insertBefore(newElement, list.firstElementChild);//inserto el nuevo

    console.log(newElement);

}());