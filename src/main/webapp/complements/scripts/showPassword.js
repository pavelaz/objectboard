const visibilityToggle = document.getElementById('ojo');

const input1 = document.getElementById('muPassword');
const input2 = document.getElementById('muPassword2');

var password = true;

visibilityToggle.addEventListener('click', function() {
    if (password) {
        input1.setAttribute('type', 'text');
        input2.setAttribute('type', 'text');
        visibilityToggle.innerHTML = 'visibility';
    } else {
        input1.setAttribute('type', 'password');
        input2.setAttribute('type', 'password');
        visibilityToggle.innerHTML = 'visibility_off';
    }
    password = !password;

});