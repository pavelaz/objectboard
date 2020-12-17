<!-- Start js -->
// Validaciones Particulares
// esta pertenece a la pagina de register.jsp
function valida_cia_registro(){
    if (document.forma.p_company_number.value == 1 && document.forma.p_supercode.value.length == 0){
        alert("To deliver to the Master company it is mandatory to provide a super code\n" +
            "valid for this, contact the system administrator and try again.");
        return false;
    }else{
        if (utf8ToHex(document.forma.p_supercode.value) != document.forma.p_super.value &&
            utf8ToHex(document.forma.p_supercode.value) != document.forma.p_admin.value &&
            utf8ToHex(document.forma.p_supercode.value) != document.forma.p_user1.value &&
            utf8ToHex(document.forma.p_supercode.value) != document.forma.p_user2.value){
            alert("The super code provided does not match the one registered in our files for the company.");
            return false;
        }else{
            if(utf8ToHex(document.forma.p_supercode.value) == document.forma.p_super.value)
                document.forma.p_role.value = 1;
            if(utf8ToHex(document.forma.p_supercode.value) == document.forma.p_admin.value)
                document.forma.p_role.value = 2
            if(utf8ToHex(document.forma.p_supercode.value) == document.forma.p_user1.value)
                document.forma.p_role.value = 3;
            if(utf8ToHex(document.forma.p_supercode.value) == document.forma.p_user2.value)
                document.forma.p_role.value = 4;
        }
    }
    return true;
}

// esta pertenece a forgot_password.jsp
function verify_password(){
    // if (document.forma.p_answer.value.toUpperCase() === document.forma.p_answer_t.value.toUpperCase()){
    if (utf8ToHex(document.forma.p_answer.value) === document.forma.p_answer_t.value){
        return true;
    }else {
        alert("The answer to the security question provided does not match the one registered in our files, please verify I have tried again.");
        return false;
    }
}

function verify_password_renew(){
    if(
        !valida_textos(document.forma.p_answer.value,"Answer to the question","?")||
        !valida_largos(document.forma.p_answer.value.length,"Answer to the question",3))
        return false;
    if (utf8ToHex(document.forma.p_answer.value) != document.forma.p_answer_t.value){
        alert("The answer to the security question provided does not match the one registered in our files, please verify I have tried again.");
        return false;
    }
    if(
        !valida_textos(document.forma.p_new_password.value,"Password","*$@#")||
        !valida_largos(document.forma.p_new_password.value.length,"Password",6)||
        !valida_password_iguales(document.forma.p_new_password.value,document.forma.p_rep_password.value))
        return false;
    if (utf8ToHex(document.forma.p_new_password.value) == document.forma.p_pwd_actual.value){
        alert("The new password must be a different one from the current one used by you.");
        return false;
    }
    if (utf8ToHex(document.forma.p_new_password.value) == document.forma.p_pwd_ant.value){
        alert("The new password must be a different one from the one previously used by you.");
        return false;
    }
    return true;
}
<!-- End js -->
