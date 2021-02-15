<!-- Begin Script -->
// Validacion del los caracteres permitidos en los campos hora. formato HH:MM:SS ,
// 1 valor a validar
// 2 nombre del campo
function valida_hora(valor,campo){
	var checkOK = "0123456789:";
	var checkStr = valor;
	var allValid = true;
	// valida caracteres
	for (i = 0; i < checkStr.length; i++) {
		ch = checkStr.charAt(i);
		for (j = 0; j < checkOK.length; j++){
			li =checkOK.charAt(j);
			if (ch == li){
				j = checkOK.length;
			}else{
				if (j == (checkOK.length-1))
					allValid = false;
			}
		}
		if (!allValid) {
			i = checkStr.length;
		}
	}
	if(!allValid) {
		alert("Cannot use characters\n" +
			  "Specials or not valid in\n" +
			  "the field " + campo);
	}else {
		// valida el largo
		l = checkStr.length;
		if (l != 8 ) {
			allValid = false;
			alert("The correct length including\n" +
				  "separator characters is 8 for\n" +
				  " the field" + campo + ".\nex: HH:MM:SS");
		}else{
			//valida formato
			if (isNaN(checkStr.substring(0,2)) ||
				isNaN(checkStr.substring(3,5)) ||
				isNaN(checkStr.substring(6,8))) {
				allValid = false;
				alert("The value placed as hours, minutes\n" +
					  "or seconds is not numeric, according\n" +
					  "to the requested format HH:MM:SS.\n" +
					  "In " + campo + ".");
			}else{
				// valida valores
				if (parseInt(checkStr.substring(0,2)) > 24 ||
					parseInt(checkStr.substring(3,5)) > 59 ||
					parseInt(checkStr.substring(6,8)) > 59){
					allValid = false;
					alert("The value placed as hours, minutes\n" +
						  "or seconds exceed the numerical value\n" +
						  "allowed according to the requested\n" +
						  "format Max. 24:59:59." + campo);
				}else{
					// valida valores todos cero
					if (parseInt(checkStr.substring(0,2)) ==  0 &&
						parseInt(checkStr.substring(3,5)) == 0 &&
						parseInt(checkStr.substring(6,8)) == 0){
						allValid = false;
						alert("The value placed as time is not allowed,\n" +
							  "according to the requested\n" + campo +
							  "\nformat Max. 24:59:59 - Min. 00:00:01.");
					}
				}
			}
		}
	}

	return (allValid);
}

//validacion de emails
// 1 valor a validar
// 2 nombre del campo
function valida_email(valor,nombre){
	var allValid = true;
	var checkStr = valor.indexOf ('@', 0);
	if(checkStr==-1||valor.length < 5){
		alert("Enter an email address\n" +
			"valid in the field " + nombre + ".");
		allValid=false;
	}
	return (allValid); 
}

//validacion de campos tipo selects
// 1 valor del campo a validar
// 2 nombre del campo
// 3 sujeto del campo
function valida_selects(valor,nombre,sujeto){
	var allValid = true;
	var checkStr = valor;
	if(checkStr==0||valor.length ==0){
		if(sujeto.length ==0) {
			alert("You must select a " + nombre + ",\n" +
				"Correct this situation, I have tried again.");
		}else{
			alert("You must select a " + nombre + " for this " + sujeto + ",\n" +
				"Correct this situation, I have tried again.");
		}
		allValid=false;
	}
	return (allValid);
}

// Validacion del Largo de los Textos
// 1- largo del campo a validar obtenido con la propiedad (.length) del input
// 2- nombre del campo
// 3- largo minimo del campo
function valida_largos(valor,campo,largo){
	var checkVal = valor;
	var allValid = true;
	var checkLar = largo;
	if(checkLar==0){
		if(checkVal==checkLar)
			allValid = false;
	}else{
		if(checkVal < checkLar)
			allValid = false;
	}
	if(!allValid){
		if(checkLar==0)
			alert("You must fill in the field "+ campo);
		else
			alert("You must write at least " + largo + " characters\non " + campo);
	}
	return (allValid); 
}
		
// Validacion del los caracteres permitidos en los textos  valida Texto mayusculas y minusculas,
// y ademas los numeros del 0-9 mas caracteres adicionales
// 1 valor a validar
// 2 nombre del campo
// 3 caracteres adicionales que se permiten como validos dentro del text
function valida_textos(valor,campo,adicional){
	var checkOK = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +  "0123456789" +
	              "abcdefghijklmnopqrstuvwxyz " + adicional;
	var checkStr = valor;
	var allValid = true;
	for (i = 0; i < checkStr.length; i++) { 
		ch = checkStr.charAt(i); 
		for (j = 0; j < checkOK.length; j++){ 
			li =checkOK.charAt(j);
  			if (ch == li){
			    j = checkOK.length;
        	}else{
			if (j == (checkOK.length-1)) 
				allValid = false; 
			}
		} 
		if (!allValid) {
			i = checkStr.length;
		}
	} 
	if(!allValid)
	alert("Cannot use characters\n" +
		"Specials in the field "+ campo);
	return (allValid); 	
}

// Validacion del los caracteres permitidos en los textos  valida solo Texto mayusculas y minusculas,
// 1 valor a validar
// 2 nombre del campo
// 3 caracteres adicionales que se permiten como validos dentro del text
function valida_solo_textos(valor,campo){
	var checkOK = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
		"abcdefghijklmnopqrstuvwxyz ";
	var checkStr = valor;
	var allValid = true;
	for (i = 0; i < checkStr.length; i++) {
		ch = checkStr.charAt(i);
		for (j = 0; j < checkOK.length; j++){
			li =checkOK.charAt(j);
			if (ch == li){
				j = checkOK.length;
			}else{
				if (j == (checkOK.length-1))
					allValid = false;
			}
		}
		if (!allValid) {
			i = checkStr.length;
		}
	}
	if(!allValid)
		alert("Cannot use characters\n" +
			"Specials in the field "+ campo);
	return (allValid);
}

//Validacion de Campos valores Numericos enteros
// 1 campo a validar
// 2 nombre del campo
// 3 valor igual y menor permitido
function valida_numeros(valor,prefi,minimo){
	var checkOK = "0123456789"; 
	var checkStr = valor;
	if(valor=="")
		var allValid = false; 
	else
		var allValid = true;
	var allNum = ""; 
	if(allValid){
		for (i = 0; i < checkStr.length; i++) { 
			ch = checkStr.charAt(i); 
			for (j = 0; j < checkOK.length; j++) 
				 if (ch == checkOK.charAt(j))
					break; 
				if (j == checkOK.length) { 
					allValid = false; 
					break; 
				} 
				allNum += ch; 
		}
	}
	if (!allValid) { 
	    alert("Type only digits in " + prefi );
		allValid = false; 
	}else{
		if(allNum.substring(0,1)=="0"){
			allNum = allNum.substring(1,allNum.length);
		}
		var chkVal = allNum; 
		var prsVal = parseInt(allNum);
		if (chkVal == "" && minimo != 0) { 
			alert("Type some value in " + prefi);
			allValid = false; 
		}else{
			if(prsVal < minimo ){
				alert("Enter a value greater than or equal to\n" +
					"what " + minimo + " in " + prefi);
				allValid = false; 
			}
		}
	} 
	return(allValid);
}

// Solo Valida que los caracteres del campo sean numericos o no.
// retorna true o false segun sea el caso
function SoloValida_numeros(valor){
	var checkOK = "0123456789"; 
	var checkStr = valor;
	if(valor=="")
		var allValid = false; 
	else
		var allValid = true;
	var allNum = ""; 
	if(allValid){
		for (i = 0; i < checkStr.length; i++) { 
			ch = checkStr.charAt(i); 
			for (j = 0; j < checkOK.length; j++) 
				 if (ch == checkOK.charAt(j))
					break; 
				if (j == checkOK.length) { 
					allValid = false; 
					break; 
				} 
				allNum += ch; 
		}
	}
	return(allValid);
}
		
//Validacion de Montos (Positivos) Con o Sin decimales usando el . como separador decimal
// 1 valor a validar
// 2 nombre del campo
//3 valor minimo permitido en el campo
function valida_montos(valor,prefi,minimo){
	var checkOK = "0123456789."; 
	var checkStr = valor;
	//var checkMin = minimo.toString();
	if(valor=="")
		var allValid = false; 
	else
		var allValid = true;
	var allNum = ""; 
	var NumDec = 0; 			
	if(allValid){
		for (i = 0; i < checkStr.length; i++) { 
			ch = checkStr.charAt(i); 
			if (ch == ".")
				NumDec++;
			if (NumDec > 1){
				allValid = false;
				break;
			}
			for (j = 0; j < checkOK.length; j++) 
				 if (ch == checkOK.charAt(j))
					break; 
				if (j == checkOK.length) { 
					allValid = false; 
					break; 
				} 
				allNum += ch; 
		}
	}
	if (!allValid) { 
	    alert("Type only digits in " + prefi + " and\n use the point as the only decimal separator");
	}else{
		if(allNum.substring(0,1)=="0"){
			allNum = allNum.substring(1,allNum.length);
		}
		var chkVal = allNum; 
		var prsVal = parseInt(allNum);
		if (chkVal == "") {
			if(minimo != 0){
				alert("Type some value in " + prefi);
				allValid = false; 
			}else
				allValid = true;
		}else{
			if(prsVal < minimo ){
				alert("Enter a value greater than or equal to\n" +
					"what " + minimo + " in " + prefi);
				allValid = false; 
			}
			/*if (allValid){
				var objNumerovalor = new Number( valor );
				var objNumeromini = new Number( checkMin );
				if (objNumerovalor < objNumeromini){
					alert("Enter a value greater than or equal to\n" +
						"what " + minimo + " in " + prefi);
					allValid = false;
				}
				var decvalor = valor.split(".",2);
				var decmini = checkMin.split(".",2);
				alert("DecValor: " + decvalor);
				alert("DecValor: " + decvalor[0]);
				alert("DecValor: " + decvalor[1]);
				var objNumerovalor = new Number( "0."+decvalor[1] );
				alert("DecMinimo: " + decmini[0]);
				alert("DecMinimo: " + decmini[1]);
				var objNumeromini = new Number( "0."+decmini[1] );
				alert(objNumerovalor);
				alert(objNumeromini);
			}*/
		}
	} 
	return(allValid);
}		

//Validacion de Montos (Negativos) Con o Sin decimales usando el . como separador decimal
// 1 valor a validar
// 2 nombre del campo
// valor minimo del campo
function valida_montosNegativos(valor,prefi,minimo){
	var checkOK = "0123456789.-"; 
	var checkStr = valor;
	if(valor === "")
		var allValid = false; 
	else
		var allValid = true;
	var chrNoPer = 0;
	var allChr = true;
	var chrNeg = true;
	var NumDec = 0; 
	var NumSig = 0;
	// Proceso de chequeo de los factores de la cadena
	if(allValid){
		for (i = 0; i < checkStr.length; i++) { 
			ch = checkStr.charAt(i);
			if (ch === ".")   // cuenta la cantidad de puntos en la cadena
				NumDec++;
			if (ch === "-")   // cuenta la cantidade de signos negativos en la cadena
				NumSig++;
			if (i === 0){     // valida se el primer caracter de la cdena es un signo negativo
				if(ch === "-")
					chrNeg = true;
				else
					chrNeg = false;
			}
			for (j = 0; j < checkOK.length; j++) {
				if (ch === checkOK.charAt(j)){
					allChr = true;
					break;
				}else{
					allChr = false;
				}
			}
			if (!allChr){
				chrNoPer++;
			}
		}
	}
	// validaciones
	// se sale sila cadena no trae valor
	if (!allValid){
		alert("Type some value in " + prefi);
	}
	// se sale si existen caracteres no permitidos dentro de la cadena
	if (allValid) {
		if (chrNoPer > 0) {
			alert("Type <strong>ONLY</strong> characters allowed for " + prefi);
			allValid = false;
		}
	}
	// se sale si existe mas de un punto decimal
	if (allValid) {
		if (NumDec > 1) {
			alert("Type only <strong>ONE</strong> point as a decimal point in " + prefi);
			allValid = false;
		}
	}
	// se sale si existe mas de un signo negativo
	if (allValid) {
		if (NumSig > 1) {
			alert("Type only <strong>ONE</strong> the negative symbol (-)." + prefi);
			allValid = false;
		}
	}
	// se sale si el primer caracter no es un signo negativo
	if (allValid) {
		if (!chrNeg) {
			alert("Type only digits in " + prefi + " and\n" +
				"and the negative symbol (-) as the <strong>FIRST</strong> character");
			allValid = false;
		}
	}
	// se sale si no cumple con la validacion del minimo
	if (allValid) { // se sale si no cumple con la validacion del minimo
		var prsVal = parseFloat(valor);
		if (valor === "") {
			if(minimo !== 0){
				alert("Type some value in " + prefi + "with minimun");
				allValid = false;
			}else
				allValid = true;
		}else{
			if(prsVal > minimo ){
				alert("Enter a value less than or equal\n" +
					"what " + minimo + " in " + prefi);
				allValid = false;
			}
		}
	}
    //
	return(allValid);
}

//Validacion de Montos (Negativos o Positivos) Con o Sin decimales usando el . como separador decimal
// y el signo - para los montosl negativos en la primera positicion
// 1 valor a validar
// 2 nombre del campo
// valor minimo del campo
function valida_montosPosOrNeg(valor,prefi,minimo){
	var checkStr = valor;
	var allValid = true;
	var NumSig = 0;
	for (i = 0; i < checkStr.length; i++) {
		ch = checkStr.charAt(i);
		if (ch === "-")
			NumSig++;
	}
	if(NumSig === 0)
		allValid = valida_montos(valor,prefi,minimo);
	else
		allValid = valida_montosNegativos(valor,prefi,minimo);

	return(allValid);
}

// OBTENER EL VALOR SELECCIONADO DE UN GRUPO RATIO BOTON
// Valor de entrada document.cliente.objeto
// siendo cliente el nombre del formulario y objeto el nombre del campo
function getRadioButtonSelectedValue(ctrl){
	for(i=0;i<ctrl.length;i++)
		if(ctrl[i].checked)
			return ctrl[i].value;
}

// VALIDAR SI SE HA SELECCIONADO O NO UNA OPCION DE UN GRUPO RADIOBUTTON
// se recibe el nombre del campo y el texto de la etiqueta
function valida_radios(name,campo) {
	var radio_check_val = "";
	for (i = 0; i < document.getElementsByName(name).length; i++) {
		if (document.getElementsByName(name)[i].checked) {
			// alert("this radio button was clicked: " + document.getElementsByName(name)[i].value);
			radio_check_val = document.getElementsByName(name)[i].value;
		}
	}
	if (radio_check_val === ""){
		alert("please select option on " + campo);
		return false;
	}
	return  true;
}

function valida_checkboxes(name,campo) {
	var checkbox_check_val = "";
	for (i = 0; i < document.getElementsByName(name).length; i++) {
		if (document.getElementsByName(name)[i].checked) {
			// alert("this radio button was clicked: " + document.getElementsByName(name)[i].value);
			checkbox_check_val = document.getElementsByName(name)[i].value;
		}
	}
	if (checkbox_check_val === ""){
		alert("please select option on " + campo);
		return false;
	}
	return  true;
}

// COMPARA DOS FECHAS Y VERIFICA CUAL DE LAS DOS FECHAS ES MAYOR
// FORMATO MM/DD/AAAA.  RETORNA 0 SI SON IGUALES, 1 SI ES MAYOR LA 1
// Y 2 SI ES MAYOR LA 2
function compara_dos_fechas(fecha, fecha2){
	var valor1 = fecha.substring(6,10)+fecha.substring(0,2)+fecha.substring(3,5);
	var valor2 = fecha2.substring(6,10)+fecha2.substring(0,2)+fecha2.substring(3,5);

	if (valor1 == valor2)
		return 0;
	else {
		if (valor1 > valor2)
			return 1;
		else
			return 2;
	}
}

// PARA VALIDAR QUE DOS CAMPOS PASSWORD SEA IGUALES
// 1 PRIMER CAMPO PASSWORD
// 2 SEGUNDO CAMPO PASSWORD
function valida_password_iguales(password1,password2){
	if(password1 == password2){
		return true;
	}
	alert("The password and repeat password fields are different\n" +
		" when they must be the same. correct the situation and try again.");
	return false;
}

// PARA OBTENER EL TEXTO DE LA OPCION SELECCIONADA EN UN LISTBOX (SELECT)
function buscar_texto_en_select(id){
	var select = document. getElementById(id), //El <select>
		value = select. value, //El valor seleccionado.
		text = select. options[select. selectedIndex]. innerText; //El texto de la opción seleccionada.
	return text;
}

// SELECCIONAR EL TEXTO DENTRO DE UN INPUT TIPO TEXT, EMAIL, PASSWORD AL HACER FOCUS
// EJ: <input type="text" onfocus="selecciona_texto_dentro(this)" value="soy todo el texto" />
// funciona en Mozilla, EI
function selecciona_contenido(objInput) {
		objInput.select();
}

// convertir de un string a hexagecimal EN BASE UTF8
function utf8ToHex(str) {
	return Array.from(str).map(c =>
		c.charCodeAt(0) < 128 ? c.charCodeAt(0).toString(16) :
			encodeURIComponent(c).replace(/\%/g,'').toLowerCase()
	).join('');
}

// convertir de hex a string EN BASE UTF8
function hexToUtf8(hex) {
	return decodeURIComponent('%' + hex.match(/.{1,2}/g).join('%'));
}

// eliminar un caracter tanta veces como aparezca dentro de una cadena
//función indexOf. Devuelve el valor -1 sino lo que busca no se encuentra, y devuelve un entero positivo si la encuentra
function replaceAllChart(cadena,char,by){
	while (cadena.indexOf(char) !== -1){
		cadena = cadena.replace(char,by);
	}
    return cadena;
}
//  End Script -->