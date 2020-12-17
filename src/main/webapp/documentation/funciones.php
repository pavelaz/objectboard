<?php
// devuelbe nombre del mes en ingles o espano
function nombremes($mes){
	global $idioma;
	if($idioma=="E")
 		setlocale(LC_TIME, 'spanish');  
	else
		setlocale(LC_TIME, 'english');
	$nombre=strftime("%B",mktime(0, 0, 0, $mes, 1, 2000)); 
	return $nombre;
} 
//GENERAR NUMERO O ALFANUMERICO RANDOM DEL LARGO $qtd
function GeraHash($qtd){ 
//Under the string $Caracteres you write all the characters you want to be used to randomly generate the code. 
$Caracteres = 'ABCDEFGHIJKLMOPQRSTUVXWYZ0123456789'; 
$QuantidadeCaracteres = strlen($Caracteres); 
$QuantidadeCaracteres--; 

$Hash=NULL; 
    for($x=1;$x<=$qtd;$x++){ 
        $Posicao = rand(0,$QuantidadeCaracteres); 
        $Hash .= substr($Caracteres,$Posicao,1); 
    } 

return $Hash; 
} 
// REDONDEAR UN NUMERO
function redondeado ($numero, $decimales) { 
   $factor = pow(10, $decimales); 
   return (round($numero*$factor)/$factor);
}
// DROP ZISE 
function busca_drop_zise($tipo){
	$rango = 0;
	if($tipo == "E")
	   $rango = 46667;
	else
	   $rango = 20000;
	return $rango;
}
// Envio de sms por el portal 041x.com
function enviosms($numero, $mensaje){
	$cUrl = curl_init();
	curl_setopt($cUrl,CURLOPT_URL,"http://041x.com/secure/insert.php?uname=pavelaz&pass=1165abc01137777b4503213f54a68119&num=".$numero."&msg=".urlencode($mensaje));
	curl_setopt($cUrl, CURLOPT_RETURNTRANSFER, 1);
	echo curl_exec($cUrl);
	curl_close($cUrl);
}
//
// Funcion que recive variable alfanumerica y retona
// true si solo trae numeros o false si trae letra o esta vacia
function VerSiEsNumero($campo){
	$largo = strlen($campo);
	if ($largo == 0)
	    return false;
	$retorno =  true;
	for($op = 1; $op <= $largo; $op++){
		$i = $op - 1;
		$pos = mid($campo,$i,1);
		if($pos != "0" && $pos !="1" && $pos != "2" && $pos != "3" && $pos !="4" && $pos != "5" &&
		   $pos != "6" && $pos !="7" && $pos != "8" && $pos !="9")
			   $retorno = false;
	}
	return $retorno;
}
// Funciones faltantes en php
function left($str, $length) {
     return substr($str, 0, $length);
}

function right($str, $length) {
     return substr($str, -$length);
}
//RETORNA EL NOMBRE DE LA OFICINA
// recibe la letra
function  busca_oficina($oficina){
	$nombre = '';
	switch($oficina){
	case "H":	
		$nombre = "Barcelona";
		break;
	case "M":	
		$nombre = "Maturin";
		break;
	case "G":	
		$nombre = "Guayana";
		break;
	case "C":	
		$nombre = "Cumana";
		break;
	case "K":	
		$nombre = "Carupano";
		break;
	case "D":	
		$nombre = "Margarita";
		break;	
	case "U":	
		$nombre = "Maracaibo";
		break;
	case "O":	
		$nombre = "Puerto Ordaz";
		break;
	case "P":	
		$nombre = "Falcon";
		break;			
	default:
	    $nombre  = "No Existe";
	    break;
	}	
	return $nombre;
}
//FUNCIONES CON FECHAS
//Función que devuelve el número de días entre dos fechas NO IMPORTA EL ORDEN DE LAS FECHAS
//(formato aaaa-mm-dd o dd-mm-aaaa). Usando el - o el / como separador.

function dias_entre_fechas($fecha1, $fecha2, $separador){
	//divido fecha 1 	
	$dia1 = strtok($fecha1,"$separador$separador");
	$mes1 = strtok("$separador");
	$ano1 = strtok("");
	if($dia1 > 2000 && $ano1 < 32){
	  $inter = $dia1;
	  $dia1 = $ano1;
	  $ano1 = $inter;
	}
	//echo("Fecha 1:" . $ano1 . $mes1  . $dia1 . "\n");
	
	//divido fecha 2	
	$dia2 = strtok($fecha2,"$separador$separador");
	$mes2 = strtok("$separador");
	$ano2 = strtok("");
	if($dia2 > 2000 && $ano2 < 32){
	  $inter = $dia2;
	  $dia2 = $ano2;
	  $ano2 = $inter;
	}
	//echo("Fecha 2:" . $ano2 . $mes2 . $dia2 . "\n");
	
	//CONVIERTE A SEGUNDO LAS DOS FECHAS calculo timestam 
	$timestamp1 = mktime(0,0,0,$mes1,$dia1,$ano1); 
	$timestamp2 = mktime(0,0,0,$mes2,$dia2,$ano2);
	
	//echo $segundos_diferencia;
	$segundos_diferencia = 0;
	
	//resto a una fecha la otra 
	if($timestamp1 > $timestamp2)
		$segundos_diferencia = $timestamp1 - $timestamp2; 
	else
		$segundos_diferencia = $timestamp2 - $timestamp1; 
	
	//convierto segundos en días 
	$dias_diferencia = $segundos_diferencia / (60 * 60 * 24); 
	
	//obtengo el valor absoulto de los días (quito el posible signo negativo) 
	$dias_diferencia = abs($dias_diferencia); 

	//quito los decimales a los días de diferencia 
	$dias_diferencia = floor($dias_diferencia); 
	//echo("Dias :" . $dias_diferencia);
	
	return $dias_diferencia;
}

//Función que Conpara dos fechas, devuelve 1 si la fecha 1 es mayor, 2 si es la fecha 2, o 0 si son iguales
//(formato aaaa-mm-dd o dd-mm-aaaa). Usando el - o el / como separador.

function fecha_mayor($fecha1, $fecha2, $separador){
	//divido fecha 1 	
	$dia1 = strtok($fecha1,"$separador$separador");
	$mes1 = strtok("$separador");
	$ano1 = strtok("");
	if($dia1 > 2000 && $ano1 < 32){
	  $inter = $dia1;
	  $dia1 = $ano1;
	  $ano1 = $inter;
	}
	//divido fecha 2	
	$dia2 = strtok($fecha2,"$separador$separador");
	$mes2 = strtok("$separador");
	$ano2 = strtok("");
	if($dia2 > 2000 && $ano2 < 32){
	  $inter = $dia2;
	  $dia2 = $ano2;
	  $ano2 = $inter;
	}
	
	//CONVIERTE A SEGUNDO LAS DOS FECHAS calculo timestam 
	$timestamp1 = mktime(0,0,0,$mes1,$dia1,$ano1); 
	$timestamp2 = mktime(0,0,0,$mes2,$dia2,$ano2);
	
	//comparo a una fecha la otra 
	if($timestamp1 > $timestamp2)
		return 1;
	else{
		if($timestamp1 < $timestamp2)
			return 2;
		else
			return 0;
	}
}

// Fecha del sistema con guiones
function fecha_sis_fi_g(){
	$fechaSistema=getdate();	
	$fecha=$fechaSistema["year"] . "-" . $fechaSistema["mon"] . "-" . $fechaSistema["mday"];
	return $fecha;
}

// Fecha del sistema con slach
function fecha_sis_fi_e(){
	$fechaSistema=getdate();	
	$fecha=$fechaSistema["year"] . "/" . $fechaSistema["mon"] . "/" . $fechaSistema["mday"];
	return $fecha;
}
// Fecha del sistema USA con slach
function fecha_sis_fi_u(){
	$fechaSistema=getdate();	
	$fecha=$fechaSistema["year"] . "/" . $fechaSistema["mday"] . "/" . $fechaSistema["mon"];
	return $fecha;
}
// iconvierte fecha usa a lat aaaa/mm/dd a aaaa/dd/mm
// $separa_r -> separador recibido, $separa_d -> separador debuelto
function invierte_usa_lat_i($fecha, $separa_r, $separa_d){
	//divido fecha 	
	$dia = strtok($fecha,"$separa_r$separa_r");
	$mes = strtok("$separa_r");
	$ano = strtok("");
	if($dia > 2000 && $ano < 32){
	  $inter = $dia;
	  $dia = $ano;
	  $ano = $inter;
	}
	//$fecha_n = $dia . $separa_d . $mes . $separa_d . $ano;
	$fecha_n = $ano . $separa_d . $dia . $separa_d . $mes;
	return $fecha_n;
}
// invierte fecha invertida a normal, recibida y separada por - o /
// $separa_r -> separador recibido, $separa_d -> separador debuelto
function invierte_in($fecha, $separa_r, $separa_d){
	//divido fecha 	
	$dia = strtok($fecha,"$separa_r$separa_r");
	$mes = strtok("$separa_r");
	$ano = strtok("");
	if($dia > 2000 && $ano < 32){
	  $inter = $dia;
	  $dia = $ano;
	  $ano = $inter;
	}
	$fecha_n = $dia . $separa_d . $mes . $separa_d . $ano;
	return $fecha_n;
}

// invierte fecha normal a invertida, recibida y separada por - o /
// $separa_r -> separador recibido, $separa_d -> separador debuelto
function invierte_ni($fecha, $separa_r, $separa_d){
	//divido fecha 	
	$dia = strtok($fecha,"$separa_r$separa_r");
	$mes = strtok("$separa_r");
	$ano = strtok("");
	if($dia > 2000 && $ano < 32){
	  $inter = $dia;
	  $dia = $ano;
	  $ano = $inter;
	}
	$fecha_n = $ano . $separa_d . $mes . $separa_d . $dia;
	return $fecha_n;
}
// invierte fecha normal a invertida, recibida y separada por - o /
// $separa_r -> separador recibido, $separa_d -> separador debuelto
// recibe formato AAAA/MM/DD retorna formato MM/DD/AAAA
function invierte_in_usa($fecha, $separa_r, $separa_d){
	//divido fecha 	
	$dia = strtok($fecha,"$separa_r$separa_r");
	$mes = strtok("$separa_r");
	$ano = strtok("");
	if($dia > 2000 && $ano < 32){
	  $inter = $dia;
	  $dia = $ano;
	  $ano = $inter;
	}
	$fecha_n = $ano . $separa_d . $dia . $separa_d . $mes;
	return $fecha_n;
}
// recibe formato AAAA/MM/DD retorna formato MM/DD/AAAA
function invierte_in_us($fecha, $separa_r, $separa_d){
	//divido fecha 	
	$dia = strtok($fecha,"$separa_r$separa_r");
	$mes = strtok("$separa_r");
	$ano = strtok("");
	if($dia > 2000 && $ano < 32){
	  $inter = $dia;
	  $dia = $ano;
	  $ano = $inter;
	}
	$fecha_n = $mes . $separa_d . $dia . $separa_d . $ano ;
	return $fecha_n;
}
// NUEVA FECHA A PARTIR DE UNA PREVIA MAS O MENOS DIAS
// formato fecha recibida date('Y-m-j')  retorna a fecha en el mismo formato
function nueva_fecha($fecha,$operador,$dias){
	//$fecha = date('Y-m-j');  fecha del distema
	if($operador=="+")
		$nuevafecha = strtotime ( '+'.$dias.'day' , strtotime ( $fecha ) ) ;
	else
		$nuevafecha = strtotime ( '-'.$dias.'day' , strtotime ( $fecha ) ) ;
	$nuevafecha = date ( 'Y-m-j' , $nuevafecha );
    return $nuevafecha;
}
// FUNCIONES DE ACCESO A BASES DE DATOA
// Ejecuta todas las consultas a la base de datos
function consulta_sql(){
	global $sql,$IdCon,$error_sql;
	$rsult=mysql_query($sql,$IdCon);
	if (!$rsult && $error_sql == 0)
		$error_sql = mysql_errno();
	return $rsult;
}

// Ejecuta todas las consultas a la base de 
// datos cuando son varias 
function consulta_sql_varias(){
	global $sql,$IdCon,$error_sql,$result;
	$rsult=mysql_query($sql,$IdCon);
	if (!$rsult && $error_sql == 0)
		$error_sql = mysql_errno();
	if(!$rsult)
		$result=$rsult;
}

//valida el resultado de la totalidad de la transacion
function valida_transacion(){
	global $result;
	if (!$result)
		mysql_query('rollback');
	else
		mysql_query('COMMIT'); 	
}

function muestra_errse(){
	global $result;
	if (!$result){
		die(muestra_error());
		exit;
	}
}

// combertir de nuemros a letras en espanol
function valorEnLetras_hispano($x) { 
	if ($x<0) {
		$signo = "menos ";
	}else{
		$signo = "";
	} 
	$x = abs ($x); 
	$C1 = $x; 

	$G6 = floor($x/(1000000));  // 7 y mas 

	$E7 = floor($x/(100000)); 
	$G7 = $E7-$G6*10;   // 6 

	$E8 = floor($x/1000); 
	$G8 = $E8-$E7*100;   // 5 y 4 

	$E9 = floor($x/100); 
	$G9 = $E9-$E8*10;  //  3 

	$E10 = floor($x); 
	$G10 = $E10-$E9*100;  // 2 y 1 


	$G11 = round(($x-$E10)*100,0);  // Decimales 
	////////////////////// 

	$H6 = unidades_hisp($G6); 

	if($G7==1 AND $G8==0) {
		$H7 = "Cien ";
	} 
	else { 
		$H7 = decenas_hisp($G7);
	} 

	$H8 = unidades_hisp($G8); 

	if($G9==1 AND $G10==0) {
		$H9 = "Cien ";
	} 
	else { 
		$H9 = decenas_hisp($G9);
	} 

	$H10 = unidades_hisp($G10); 

	if($G11 < 10) { 
		$H11 = "0".$G11;
	} 
	else { 
		$H11 = $G11;
	} 

	///////////////////////////// 
    if($G6==0) {
		$I6=" ";
	} 
	elseif($G6==1) {
		$I6="Millón ";
	} else {
		$I6="Millones ";
	} 

	if ($G8==0 AND $G7==0) { 
		$I8=" "; 
	} else { 
		$I8="Mil ";
	} 

	$I10 = "con "; 
	$I11 = "/100 Centavos. "; 

	$C3 = $signo.$H6.$I6.$H7.$H8.$I8.$H9.$H10.$I10.$H11.$I11; 

	return $C3; //Retornar el resultado 

	} 

function unidades_hisp($u) { 
    if ($u==0)  {$ru = " ";} 
	elseif ($u==1)  {$ru = "Un ";} 
	elseif ($u==2)  {$ru = "Dos ";} 
	elseif ($u==3)  {$ru = "Tres ";} 
	elseif ($u==4)  {$ru = "Cuatro ";} 
	elseif ($u==5)  {$ru = "Cinco ";} 
	elseif ($u==6)  {$ru = "Seis ";} 
	elseif ($u==7)  {$ru = "Siete ";} 
	elseif ($u==8)  {$ru = "Ocho ";} 
	elseif ($u==9)  {$ru = "Nueve ";} 
	elseif ($u==10) {$ru = "Diez ";} 

	elseif ($u==11) {$ru = "Once ";} 
	elseif ($u==12) {$ru = "Doce ";} 
	elseif ($u==13) {$ru = "Trece ";} 
	elseif ($u==14) {$ru = "Catorce ";} 
	elseif ($u==15) {$ru = "Quince ";} 
	elseif ($u==16) {$ru = "Dieciseis ";} 
	elseif ($u==17) {$ru = "Decisiete ";} 
	elseif ($u==18) {$ru = "Dieciocho ";} 
	elseif ($u==19) {$ru = "Diecinueve ";} 
	elseif ($u==20) {$ru = "Veinte ";} 

	elseif ($u==21) {$ru = "Veintiun ";} 
	elseif ($u==22) {$ru = "Veintidos ";} 
	elseif ($u==23) {$ru = "Veintitres ";} 
	elseif ($u==24) {$ru = "Veinticuatro ";} 
	elseif ($u==25) {$ru = "Veinticinco ";} 
	elseif ($u==26) {$ru = "Veintiseis ";} 
	elseif ($u==27) {$ru = "Veintisiente ";} 
	elseif ($u==28) {$ru = "Veintiocho ";} 
	elseif ($u==29) {$ru = "Veintinueve ";} 
	elseif ($u==30) {$ru = "Treinta ";} 

	elseif ($u==31) {$ru = "Treinta y uno ";} 
	elseif ($u==32) {$ru = "Treinta y dos ";} 
	elseif ($u==33) {$ru = "Treinta y tres ";} 
	elseif ($u==34) {$ru = "Treinta y cuatro ";} 
	elseif ($u==35) {$ru = "Treinta y cinco ";} 
	elseif ($u==36) {$ru = "Treinta y seis ";} 
	elseif ($u==37) {$ru = "Treinta y siete ";} 
	elseif ($u==38) {$ru = "Treinta y ocho ";} 
	elseif ($u==39) {$ru = "Treinta y nueve ";} 
	elseif ($u==40) {$ru = "Cuarenta ";} 

	elseif ($u==41) {$ru = "Cuarenta y uno ";} 
	elseif ($u==42) {$ru = "Cuarenta y dos ";} 
	elseif ($u==43) {$ru = "Cuarenta y tres ";} 
	elseif ($u==44) {$ru = "Cuarenta y cuatro ";} 
	elseif ($u==45) {$ru = "Cuarenta y cinco ";} 
	elseif ($u==46) {$ru = "Cuarenta y seis ";} 
	elseif ($u==47) {$ru = "Cuarenta y siete ";} 
	elseif ($u==48) {$ru = "Cuarenta y ocho ";} 
	elseif ($u==49) {$ru = "Cuarenta y nueve ";} 
	elseif ($u==50) {$ru = "Cincuenta ";} 

	elseif ($u==51) {$ru = "Cincuenta y uno ";} 
	elseif ($u==52) {$ru = "Cincuenta y dos ";} 
	elseif ($u==53) {$ru = "Cincuenta y tres ";} 
	elseif ($u==54) {$ru = "Cincuenta y cuatro ";} 
	elseif ($u==55) {$ru = "Cincuenta y cinco ";} 
	elseif ($u==56) {$ru = "Cincuenta y seis ";} 
	elseif ($u==57) {$ru = "Cincuenta y siete ";} 
	elseif ($u==58) {$ru = "Cincuenta y ocho ";} 
	elseif ($u==59) {$ru = "Cincuenta y nueve ";} 
	elseif ($u==60) {$ru = "Sesenta ";} 

	elseif ($u==61) {$ru = "Sesenta y uno ";} 
	elseif ($u==62) {$ru = "Sesenta y dos ";} 
	elseif ($u==63) {$ru = "Sesenta y tres ";} 
	elseif ($u==64) {$ru = "Sesenta y cuatro ";} 
	elseif ($u==65) {$ru = "Sesenta y cinco ";} 
	elseif ($u==66) {$ru = "Sesenta y seis ";} 
	elseif ($u==67) {$ru = "Sesenta y siete ";} 
	elseif ($u==68) {$ru = "Sesenta y ocho ";} 
	elseif ($u==69) {$ru = "Sesenta y nueve ";} 
	elseif ($u==70) {$ru = "Setenta ";} 

	elseif ($u==71) {$ru = "Setenta y uno ";} 
	elseif ($u==72) {$ru = "Setenta y dos ";} 
	elseif ($u==73) {$ru = "Setenta y tres ";} 
	elseif ($u==74) {$ru = "Setenta y cuatro ";} 
	elseif ($u==75) {$ru = "Setenta y cinco ";} 
	elseif ($u==76) {$ru = "Setenta y seis ";} 
	elseif ($u==77) {$ru = "Setenta y siete ";} 
	elseif ($u==78) {$ru = "Setenta y ocho ";} 
	elseif ($u==79) {$ru = "Setenta y nueve ";} 
	elseif ($u==80) {$ru = "Ochenta ";} 

	elseif ($u==81) {$ru = "Ochenta y uno ";} 
	elseif ($u==82) {$ru = "Ochenta y dos ";} 
	elseif ($u==83) {$ru = "Ochenta y tres ";} 
	elseif ($u==84) {$ru = "Ochenta y cuatro ";} 
	elseif ($u==85) {$ru = "Ochenta y cinco ";} 
	elseif ($u==86) {$ru = "Ochenta y seis ";} 
	elseif ($u==87) {$ru = "Ochenta y siete ";} 
	elseif ($u==88) {$ru = "Ochenta y ocho ";} 
	elseif ($u==89) {$ru = "Ochenta y nueve ";} 
	elseif ($u==90) {$ru = "Noventa ";} 

	elseif ($u==91) {$ru = "Noventa y uno ";} 
	elseif ($u==92) {$ru = "Noventa y dos ";} 
	elseif ($u==93) {$ru = "Noventa y tres ";} 
	elseif ($u==94) {$ru = "Noventa y cuatro ";} 
	elseif ($u==95) {$ru = "Noventa y cinco ";} 
	elseif ($u==96) {$ru = "Noventa y seis ";} 
	elseif ($u==97) {$ru = "Noventa y siete ";} 
	elseif ($u==98) {$ru = "Noventa y ocho ";} 
	else            {$ru = "Noventa y nueve ";} 
	return $ru; //Retornar el resultado 
	} 

function decenas_hisp($d)
	{ 
		if ($d==0)  {$rd = "";} 
	elseif ($d==1)  {$rd = "Ciento ";} 
	elseif ($d==2)  {$rd = "Doscientos ";} 
	elseif ($d==3)  {$rd = "Trescientos ";} 
	elseif ($d==4)  {$rd = "Cuatrocientos ";} 
	elseif ($d==5)  {$rd = "Quinientos ";} 
	elseif ($d==6)  {$rd = "Seiscientos ";} 
	elseif ($d==7)  {$rd = "Setecientos ";} 
	elseif ($d==8)  {$rd = "Ochocientos ";} 
	else            {$rd = "Novecientos ";} 
	return $rd; //Retornar el resultado 
	} 
// combertir de nuemros a letras en ingles
function valorEnLetras_ingles($x) { 
	if ($x<0) {
		$signo = "less ";
	}else{
		$signo = "";
	} 
	$x = abs ($x); 
	$C1 = $x; 

	$G6 = floor($x/(1000000));  // 7 y mas 

	$E7 = floor($x/(100000)); 
	$G7 = $E7-$G6*10;   // 6 

	$E8 = floor($x/1000); 
	$G8 = $E8-$E7*100;   // 5 y 4 

	$E9 = floor($x/100); 
	$G9 = $E9-$E8*10;  //  3 

	$E10 = floor($x); 
	$G10 = $E10-$E9*100;  // 2 y 1 


	$G11 = round(($x-$E10)*100,0);  // Decimales 
	////////////////////// 

	$H6 = unidades($G6); 

	if($G7==1 AND $G8==0) {
		$H7 = "hundred ";
	} 
	else { 
		$H7 = decenas($G7);
	} 

	$H8 = unidades($G8); 

	if($G9==1 AND $G10==0) {
		$H9 = "hundred ";
	} 
	else { 
		$H9 = decenas($G9);
	} 

	$H10 = unidades($G10); 

	if($G11 < 10) { 
		$H11 = "0".$G11;
	} 
	else { 
		$H11 = $G11;
	} 

	///////////////////////////// 
    if($G6==0) {
		$I6=" ";
	} 
	elseif($G6==1) {
		$I6="Million ";
	} else {
		$I6="Millions ";
	} 

	if ($G8==0 AND $G7==0) { 
		$I8=" "; 
	} else { 
		$I8="thousand ";
	} 

	$I10 = "Dollars with "; 
	$I11 = "/100 Cents. "; 

	$C3 = $signo.$H6.$I6.$H7.$H8.$I8.$H9.$H10.$I10.$H11.$I11; 

	return $C3; //Retornar el resultado 

	} 

function unidades($u) { 
    if ($u==0)  {$ru = " ";} 
	elseif ($u==1)  {$ru = "One ";} 
	elseif ($u==2)  {$ru = "Two ";} 
	elseif ($u==3)  {$ru = "Three ";} 
	elseif ($u==4)  {$ru = "Four ";} 
	elseif ($u==5)  {$ru = "Five ";} 
	elseif ($u==6)  {$ru = "Six ";} 
	elseif ($u==7)  {$ru = "Seven ";} 
	elseif ($u==8)  {$ru = "Eight ";} 
	elseif ($u==9)  {$ru = "Nine ";} 
	elseif ($u==10) {$ru = "Ten ";} 

	elseif ($u==11) {$ru = "Eleven ";} 
	elseif ($u==12) {$ru = "Twelve ";} 
	elseif ($u==13) {$ru = "Thirteen ";} 
	elseif ($u==14) {$ru = "Fourteen ";} 
	elseif ($u==15) {$ru = "Fifteen ";} 
	elseif ($u==16) {$ru = "Sixteen ";} 
	elseif ($u==17) {$ru = "Seventeen ";} 
	elseif ($u==18) {$ru = "Eighteen ";} 
	elseif ($u==19) {$ru = "Nineteen ";} 
	elseif ($u==20) {$ru = "Twenty ";} 

	elseif ($u==21) {$ru = "Twenty-one ";} 
	elseif ($u==22) {$ru = "Twenty-Two ";} 
	elseif ($u==23) {$ru = "Twenty-three ";} 
	elseif ($u==24) {$ru = "Twenty-four ";} 
	elseif ($u==25) {$ru = "Twenty-five ";} 
	elseif ($u==26) {$ru = "Twenty-six ";} 
	elseif ($u==27) {$ru = "Twenty-Seven ";} 
	elseif ($u==28) {$ru = "Twenty-eigh ";} 
	elseif ($u==29) {$ru = "Twenty-Nine ";} 
	elseif ($u==30) {$ru = "Thirty ";} 

	elseif ($u==31) {$ru = "Thirty-one ";} 
	elseif ($u==32) {$ru = "Thirty-two ";} 
	elseif ($u==33) {$ru = "Thirty-three ";} 
	elseif ($u==34) {$ru = "Thirty-four ";} 
	elseif ($u==35) {$ru = "Thirty-five ";} 
	elseif ($u==36) {$ru = "Thirty-six ";} 
	elseif ($u==37) {$ru = "Thirty-seven ";} 
	elseif ($u==38) {$ru = "Thirty-eight ";} 
	elseif ($u==39) {$ru = "Thirty-nine ";} 
	elseif ($u==40) {$ru = "Forty ";} 

	elseif ($u==41) {$ru = "Forty-one ";} 
	elseif ($u==42) {$ru = "Forty-two ";} 
	elseif ($u==43) {$ru = "Forty-three ";} 
	elseif ($u==44) {$ru = "Forty-four ";} 
	elseif ($u==45) {$ru = "Forty-five ";} 
	elseif ($u==46) {$ru = "Forty-six ";} 
	elseif ($u==47) {$ru = "Forty-seven ";} 
	elseif ($u==48) {$ru = "Forty-eight ";} 
	elseif ($u==49) {$ru = "Forty-nine ";} 
	elseif ($u==50) {$ru = "Fifty ";} 

	elseif ($u==51) {$ru = "Fifty-one ";} 
	elseif ($u==52) {$ru = "Fifty-two ";} 
	elseif ($u==53) {$ru = "Fifty-three ";} 
	elseif ($u==54) {$ru = "Fifty-four ";} 
	elseif ($u==55) {$ru = "Fifty-five ";} 
	elseif ($u==56) {$ru = "Fifty-six ";} 
	elseif ($u==57) {$ru = "Fifty-seven ";} 
	elseif ($u==58) {$ru = "Fifty-eight ";} 
	elseif ($u==59) {$ru = "Fifty-nine ";} 
	elseif ($u==60) {$ru = "Sixty ";} 

	elseif ($u==61) {$ru = "Sixty-one ";} 
	elseif ($u==62) {$ru = "Sixty-two ";} 
	elseif ($u==63) {$ru = "Sixty-three ";} 
	elseif ($u==64) {$ru = "Sixty-four ";} 
	elseif ($u==65) {$ru = "Sixty-five ";} 
	elseif ($u==66) {$ru = "Sixty-six ";} 
	elseif ($u==67) {$ru = "Sixty-seven ";} 
	elseif ($u==68) {$ru = "Sixty-eight ";} 
	elseif ($u==69) {$ru = "Sixty-nine ";} 
	elseif ($u==70) {$ru = "Seventy ";} 

	elseif ($u==71) {$ru = "Seventy-one ";} 
	elseif ($u==72) {$ru = "Seventy-two ";} 
	elseif ($u==73) {$ru = "Seventy-three ";} 
	elseif ($u==74) {$ru = "Seventy-four ";} 
	elseif ($u==75) {$ru = "Seventy-five ";} 
	elseif ($u==76) {$ru = "Seventy-six ";} 
	elseif ($u==77) {$ru = "Seventy-seven ";} 
	elseif ($u==78) {$ru = "Seventy-eight ";} 
	elseif ($u==79) {$ru = "Seventy-nine ";} 
	elseif ($u==80) {$ru = "Eighty ";} 

	elseif ($u==81) {$ru = "Eighty-one ";} 
	elseif ($u==82) {$ru = "Eighty-two ";} 
	elseif ($u==83) {$ru = "Eighty-three ";} 
	elseif ($u==84) {$ru = "Eighty-four ";} 
	elseif ($u==85) {$ru = "Eighty-five ";} 
	elseif ($u==86) {$ru = "Eighty-six ";} 
	elseif ($u==87) {$ru = "Eighty-seven ";} 
	elseif ($u==88) {$ru = "Eighty-eight ";} 
	elseif ($u==89) {$ru = "Eighty-nine ";} 
	elseif ($u==90) {$ru = "Ninety ";} 

	elseif ($u==91) {$ru = "Ninety-one ";} 
	elseif ($u==92) {$ru = "Ninety-two ";} 
	elseif ($u==93) {$ru = "Ninety-three ";} 
	elseif ($u==94) {$ru = "Ninety-four ";} 
	elseif ($u==95) {$ru = "Ninety-five ";} 
	elseif ($u==96) {$ru = "Ninety-six ";} 
	elseif ($u==97) {$ru = "Ninety-seven ";} 
	elseif ($u==98) {$ru = "Ninety-eight ";} 
	else            {$ru = "Ninety-nine ";} 
	return $ru; //Retornar el resultado 
	} 

function decenas($d)
	{ 
		if ($d==0)  {$rd = "";} 
	elseif ($d==1)  {$rd = "One Hundred ";} 
	elseif ($d==2)  {$rd = "Two Hundred ";} 
	elseif ($d==3)  {$rd = "Three Hundred ";} 
	elseif ($d==4)  {$rd = "Four Hundred ";} 
	elseif ($d==5)  {$rd = "Five Hundred ";} 
	elseif ($d==6)  {$rd = "Six Hundred ";} 
	elseif ($d==7)  {$rd = "Seven Hundred ";} 
	elseif ($d==8)  {$rd = "Eight Hundred ";} 
	else            {$rd = "Nine Hundred ";} 
	return $rd; //Retornar el resultado 
	} 
//DECLARATIVA DE ERRORES
function muestra_error(){
  global $error_sql;
  switch($error_sql){
  case 115:
	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> SMTP Error: No ha podido autenticarse para emision de correo.."
	    . "</div>");	  
  	break;
  case 1004:
	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> No se ha podido Crear Archivo."
	    . "</div>");	  
  	break;
  case 1005:
	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> No se ha podido Crear Tabla."
	    . "</div>");	  
  	break;	
  case 1006:
	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> No se ha podido Crear Base de Datos."
	    . "</div>");  
  	break;	
  case 1007:
	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> No se ha podido Crear Base de Datos."
		. "La base de datos ya existe."		
	    . "</div>");  
  	break;	
  case 1008:
	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> No se ha podido Eliminar Base de Datos; "
		. "la base de datos no existe."
	    . "</div>");  
  	break;
  case 1009:
	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Error eliminando la base de datos; "
		. "no puedo borrar."
	    . "</div>");   
  	break;
  case 1010:
	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Error eliminando la base de datos; "
		. "No puedo borrar directorio."
	    . "</div>");   
  	break;	
  case 1011:
  	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Error en el borrado."
	    . "</div>"); 
  	break;
  case 1015:
  	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> No puedo bloquear archivo."
	    . "</div>"); 
  	break;
  case 1016:
  	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> No puedo abrir archivo."
	    . "</div>");   
  	break;
  case 1017:
  	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> No puedo encontrar archivo."
	    . "</div>");   
  	break;	
  case 1018:
  	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> No puedo leer el directorio."
	    . "</div>");   
  	break;	
  case 1019:
  	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> No puedo cambiar al directorio."
	    . "</div>");   ;
  	break;	
  case 1020:
  	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> El registro ha cambiado desde la "
		. "ultima lectura de la tabla."
	    . "</div>");   
  	break;	
  case 1021:
  	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Disco lleno.  Esperando para que se "
		. "libere algo de espacio...."
	    . "</div>");    
  	break;
  case 1022:
  	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> No puedo escribir, clave duplicada "
		. "en la tabla."
	    . "</div>");    
  	break;	
  case 1026:
  	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Error escribiendo en el archivo."
	    . "</div>");    
  	break;
  case 1027:
  	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Archivo esta bloqueado contra cambios."
	    . "</div>");    
  	break;	
  case 1028:
  	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Ordenamiento cancelado."
	    . "</div>");    
  	break;	
  case 1032:
  	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> No puedo encontrar el registro en el indice."
	    . "</div>");   
  	break;
  case 1033:
  	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Informacion erronea en el archivo."
	    . "</div>");   
  	break;
  case 1034:
  	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Clave de archivo erronea para la tabla "
		. "intente repararlo."
	    . "</div>");   
  	break;
  case 1035:
  	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Clave de archivo antigua para la tabla "
		. "reparelo!"
	    . "</div>");   
  	break;
  case 1036:
  	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Archivo es de solo lectura."
	    . "</div>");   
  	break;	
  case 1037:
  	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Memoria insuficiente. Reinicie el demonio he"
		. "intentelo otra vez."
	    . "</div>");   
  	break;
  case 1038:
  	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Memoria de ordenacion insuficiente. Incremente "
		. "el tamano del buffer de ordenacion."
	    . "</div>");   
  	break;	
  case 1039:
  	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Inesperado fin de fichero mientras "
		. "leiamos el archivo."
	    . "</div>");   
  	break;
  case 1040:
  	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Demasiadas conexiones."
	    . "</div>");   
  	break;
  case 1044:
  	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Acceso negado para su usuario "
		. "para la base de datos."
	    . "</div>");   
  	break;
  case 1045:
  	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Acceso negado para su usuario "
		. "Usando clave actual."
	    . "</div>");   
  	break;
  case 1046:
  	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Base de datos no seleccionada."
	    . "</div>");   
  	break;	
 case 1047:
  	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Comando desconocido."
	    . "</div>");  
  	break;
 case 1048:
  	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> La columna seleccionada no puede ser nula."
	    . "</div>");  
  	break;	
 case 1049:
  	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Base de datos desconocida."
	    . "</div>");  
  	break;	
 case 1050:
  	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Memoria de ordenacion insuficiente. Incremente "
		. "el tamano del buffer de ordenacion."
	    . "</div>");  
  	print("Error: $error_sql \n--> La tabla ya existe.");
  	break;	
 case 1051:
  	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> La tabla ya existe."
	    . "</div>");  
  	break;	
 case 1052:
  	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> La columna buscada en la consulta es ambigua."
	    . "</div>");   
  	break;	
 case 1053:
  	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Desconexion de servidor en proceso."
	    . "</div>");   
  	break;
 case 1054:
  	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> La columna pedida en la consulta es\ndesconocida."
	    . "</div>");   
  	break;
 case 1056:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> No puedo agrupar por el campo indicado."
	    . "</div>");  
  	break;	
 case 1057:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> La Consulta tiene funciones de suma "
		. "y columnas en el mismo estamento."
	    . "</div>");  
  	break;
 case 1058:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> La columna con count no tiene valores "
		. "para contar."
	    . "</div>");  
  	break;	
 case 1059:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> El nombre del identificador es "
		. "demasiado grande."
	    . "</div>");  
  	break;	
 case 1060:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Nombre de columna duplicado."
	    . "</div>");  
  	break;	
 case 1061:
  	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Nombre de clave duplicado."
	    . "</div>");   
  	#print("Error: $error_sql \n--> Nombre de clave duplicado.");
  	break;	
 case 1062:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Entrada duplicada para la clave."
	    . "</div>");  
  	#print("Error: $error_sql \n--> Entrada duplicada para la clave.");
  	break;
 case 1063:
  	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n-->  Especificador de columna erroneo "
		. "para la columna."
	    . "</div>");   
  	#print("Error: $error_sql \n-->  Especificador de columna erroneo\n"
	#	. "para la columna.");
  	break;
 case 1065:
  	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> La query estaba vacia."
	    . "</div>");    
  	#print("Error: $error_sql \n--> La query estaba vacia.");
  	break;
 case 1066:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Tabla/alias es no unica."
	    . "</div>");   
  	#print("Error: $error_sql \n--> Tabla/alias es no unica.");
  	break;	
 case 1067:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Valor por defecto invalido."
		. "para la columna."
	    . "</div>");   
  	#print("Error: $error_sql \n--> Valor por defecto invalido.");
  	break;
 case 1068:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Multiples claves primarias definidas."
	    . "</div>");   
  	#print("Error: $error_sql \n--> Multiples claves primarias definidas.");
  	break;	
 case 1069:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Demasiadas claves primarias declaradas.  "
		. "Un maximo de claves son permitidas."
	    . "</div>");   
  	#print("Error: $error_sql \n--> Demasiadas claves primarias declaradas.\n"
	#	. "Un maximo de claves son permitidas.");
  	break;
 case 1070:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Demasiadas partes de clave declaradas.  "
		. "Un maximo de partes son permitidas."
	    . "</div>");   
  	#print("Error: $error_sql \n--> Demasiadas partes de clave declaradas.\n"
	#	. "Un maximo de partes son permitidas.");
  	break;	
 case 1071:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Declaracion de clave demasiado larga.\n"
		. "Existe una longitud maxima par la clave."
	    . "</div>");   
  	#print("Error: $error_sql \n--> Declaracion de clave demasiado larga.\n"
	#	. "Existe una longitud maxima par la clave.");
  	break;	
 case 1072:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> La columna clave no existe en la tabla."
	    . "</div>");   
  	#print("Error: $error_sql \n--> La columna clave no existe en la tabla.");
  	break;
 case 1074:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Longitud de la columna es demasiado grande\n"
		. "para la columna.  Usar BLOB en su lugar."
	    . "</div>");   
  	#print("Error: $error_sql \n--> Longitud de la columna es demasiado grande\n"
	#	. "para la columna.  Usar BLOB en su lugar.");
  	break;
 case 1075:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Puede ser solamente un campo automatico\n"
		. "y este debe ser definido como una clave."
	    . "</div>"); 
  	#print("Error: $error_sql \n--> Puede ser solamente un campo automatico\n"
	#	. "y este debe ser definido como una clave.");
  	break;	
 case 1082:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> La tabla no tiene indice como el usado\n"
		. "en CREATE INDEX. Crea de nuevo la tabla."
	    . "</div>");  
  	#print("Error: $error_sql \n--> La tabla no tiene indice como el usado\n"
	#	. "en CREATE INDEX. Crea de nuevo la tabla.");
  	break;	
 case 1083:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Los separadores de argumentos del campo no\n"
		. "son los especificados. Comprueba el manual."
	    . "</div>");  
  	#print("Error: $error_sql \n--> Los separadores de argumentos del campo no\n"
	#	. "son los especificados. Comprueba el manual.");
  	break;	
 case 1085:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> El archivo debe estar en el directorio de la\n"
		. "base de datos o ser de lectura por todos."
	    . "</div>");  
  	#print("Error: $error_sql \n--> El archivo debe estar en el directorio de la\n"
	#	. "base de datos o ser de lectura por todos.");
  	break;	
 case 1086:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> El archivo ya existe."
	    . "</div>");  
  	#print("Error: $error_sql \n--> El archivo ya existe.");
  	break;
 case 1088:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Registros Duplicados."
	    . "</div>");  
  	#print("Error: $error_sql \n--> Registros Duplicados.");
  	break;	
 case 1089:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Parte de la clave es erronea. Una parte de la\n"
		. "clave no es una cadena o la longitud usada es\n"
		. "tan grande como la parte de la clave."
	    . "</div>");  
  	#print("Error: $error_sql \n--> Parte de la clave es erronea. Una parte de la\n"
	#	. "clave no es una cadena o la longitud usada es\n"
	#	. "tan grande como la parte de la clave.");
  	break;
 case 1090:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> No puede borrar todos los campos con\n"
		. "ALTER TABLE. Usa DROP TABLE para hacerlo."
	    . "</div>");  
  	#print("Error: $error_sql \n--> No puede borrar todos los campos con\n"
	#	. "ALTER TABLE. Usa DROP TABLE para hacerlo.");
  	break;	
 case 1091:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> No puedo ELIMINAR. compuebe que el campo\n"
		. "clave existe ."
	    . "</div>"); 
  	#print("Error: $error_sql \n--> No puedo ELIMINAR. compuebe que el campo\n"
	#	. "clave existe .");
  	break;	
 case 1092:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Registros Duplicados."
	    . "</div>");   
  	#print("Error: $error_sql \n--> Registros Duplicados.");
  	break;
 case 1096:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> No hay tablas usadas."
	    . "</div>");   
  	#print("Error: $error_sql \n--> No hay tablas usadas.");
  	break;	
 case 1097:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Muchas strings para columnas y SET."
	    . "</div>");   
  	#print("Error: $error_sql \n--> Muchas strings para columnas y SET.");
  	break;
 case 1098:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> No puede crear un unico archivo logs."
	    . "</div>");   
  	#print("Error: $error_sql \n--> No puede crear un unico archivo logs.");
  	break;
 case 1099:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Tabla fue trabada con un READ lock y\n"
		. "no puede ser actualizada."
	    . "</div>");   
  	#print("Error: $error_sql \n--> Tabla fue trabada con un READ lock y\n"
	#	. "no puede ser actualizada.");
  	break;
 case 1100:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Tabla no fue trabada con LOCK TABLES."
	    . "</div>");   
  	#print("Error: $error_sql \n--> Tabla no fue trabada con LOCK TABLES.");
  	break;
 case 1102:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Nombre de base de datos ilegal."
	    . "</div>");   
  	#print("Error: $error_sql \n--> Nombre de base de datos ilegal.");
  	break;	
 case 1103:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Nombre de tabla ilegal."
	    . "</div>");   
  	#print("Error: $error_sql \n--> Nombre de tabla ilegal.");
  	break;
 case 1104:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> El SELECT puede examinar muchos registros y\n"
		. "probablemente con mucho tiempo. Verifique tu WHERE y\n"
		. "usa SET SQL_BIG_SELECTS=1 si el SELECT esta correcto ."
	    . "</div>");   
  	#print("Error: $error_sql \n--> El SELECT puede examinar muchos registros y\n"
	#	. "probablemente con mucho tiempo. Verifique tu WHERE y\n"
	#	. "usa SET SQL_BIG_SELECTS=1 si el SELECT esta correcto .");
  	break;
 case 1105:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Error desconocido."
	    . "</div>");   
  	#print("Error: $error_sql \n--> Error desconocido.");
  	break;	
 case 1106:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Procedimiento desconocido."
	    . "</div>");   
  	#print("Error: $error_sql \n--> Procedimiento desconocido.");
  	break;
 case 1107:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Equivocado parametro count para procedimiento."
	    . "</div>");   
  	#print("Error: $error_sql \n--> Equivocado parametro count para procedimiento.");
  	break;
 case 1108:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Equivocados parametros para procedimiento."
	    . "</div>");   
  	#print("Error: $error_sql \n--> Equivocados parametros para procedimiento.");
  	break;	
 case 1109:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Tabla desconocida."
	    . "</div>");   
  	#print("Error: $error_sql \n--> Tabla desconocida.");
  	break;	
 case 1110:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Campo especificado dos veces."
	    . "</div>");   
  	#print("Error: $error_sql \n--> Campo especificado dos veces.");
  	break;	
 case 1111:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Invalido uso de función en grupo."
	    . "</div>");   
  	#print("Error: $error_sql \n--> Invalido uso de función en grupo.");
  	break;	
 case 1112:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Tabla usa una extensión que no existe\n"
		. "en esta MySQL versión."
	    . "</div>");   
  	#print("Error: $error_sql \n--> Tabla usa una extensión que no existe\n"
	#	. "en esta MySQL versión.");
  	break;
 case 1113:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Una tabla debe tener al menos 1 columna."
	    . "</div>");   
  	#print("Error: $error_sql \n--> Una tabla debe tener al menos 1 columna.");
  	break;
 case 1114:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> La tabla está llena."
	    . "</div>");   
  	#print("Error: $error_sql \n--> La tabla está llena.");
  	break;
 case 1115:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Juego de caracteres desconocido."
	    . "</div>");   
  	#print("Error: $error_sql \n--> Juego de caracteres desconocido.");
  	break;
 case 1116:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Muchas tablas. MySQL solamente puede\n"
		. "usar tablas en un join."
	    . "</div>");   
  	#print("Error: $error_sql \n--> Muchas tablas. MySQL solamente puede\n"
	#	. "usar tablas en un join.");
  	break;	
 case 1117:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Muchos campos."
	    . "</div>");   
  	#print("Error: $error_sql \n--> Muchos campos.");
  	break;	
 case 1118:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Tamaño de línea muy grande. Máximo tamaño de línea,\n"
		. "no contando blob, es X. Tu tienes que cambiar\n"
		. "algunos campos para blob."
	    . "</div>");   
  	#print("Error: $error_sql \n--> Tamaño de línea muy grande. Máximo tamaño de línea,\n"
	#	. "no contando blob, es X. Tu tienes que cambiar\n"
	#	. "algunos campos para blob.");
  	break;	
 case 1120:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Dependencia cruzada encontrada en OUTER JOIN.\n"
		. "Examine su condición ON."
	    . "</div>");   
  	#print("Error: $error_sql \n--> Dependencia cruzada encontrada en OUTER JOIN.\n"
	#	. "Examine su condición ON.");
  	break;
 case 1121:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Columna es usada con UNIQUE o INDEX pero\n"
		. "no está definida como NOT NULL."
	    . "</div>");   
  	#print("Error: $error_sql \n--> Columna es usada con UNIQUE o INDEX pero\n"
	#	. "no está definida como NOT NULL.");
  	break;	
 case 1122:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> No puedo cargar función."
	    . "</div>");   
  	#print("Error: $error_sql \n--> No puedo cargar función.");
  	break;	
 case 1123:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> No puedo inicializar función."
	    . "</div>");   
  	#print("Error: $error_sql \n--> No puedo inicializar función.");
  	break;	
 case 1124:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> No pasos permitidos para librarias conjugadas."
	    . "</div>");   
  	#print("Error: $error_sql \n--> No pasos permitidos para librarias conjugadas.");
  	break;
 case 1125:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Función ya existe."
	    . "</div>");   
  	#print("Error: $error_sql \n--> Función ya existe.");
  	break;	
 case 1126:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> No puedo abrir libraria conjugada."
	    . "</div>");   
  	#print("Error: $error_sql \n--> No puedo abrir libraria conjugada.");
  	break;	
 case 1127:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> No puedo encontrar función en libraria."
	    . "</div>");   
  	#print("Error: $error_sql \n--> No puedo encontrar función en libraria.");
  	break;	
 case 1128:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Función no está definida."
	    . "</div>");   
  	#print("Error: $error_sql \n--> Función no está definida.");
  	break;		
 case 1129:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Servidor está bloqueado por muchos errores de\n"
		. "conexión. Desbloquear con mysqladmin flush-hosts."
	    . "</div>");   
  	#print("Error: $error_sql \n--> Servidor está bloqueado por muchos errores de\n"
	#	. "conexión. Desbloquear con mysqladmin flush-hosts.");
  	break;	
 case 1130:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Servidor no está permitido para conectar con este\n"
		. "servidor MySQL."
	    . "</div>");   
  	#print("Error: $error_sql \n--> Servidor no está permitido para conectar con este\n"
	#	. "servidor MySQL.");
	break;
 case 1131:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Tu estás usando MySQL como un usuario anonimo y usuarios anonimos\n"
		. "no tienen permiso para cambiar las claves."
	    . "</div>");   
  	#print("Error: $error_sql \n--> Tu estás usando MySQL como un usuario anonimo y usuarios anonimos\n"
	#	. "no tienen permiso para cambiar las claves.");
	break;
 case 1132:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Tu debes de tener permiso para actualizar tablas en la base\n"
		. "de datos mysql para cambiar las claves para otros"
	    . "</div>");   
  	#print("Error: $error_sql \n--> Tu debes de tener permiso para actualizar tablas en la base\n"
	#	. "de datos mysql para cambiar las claves para otros");	
  	break;
 case 1133:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> No puedo encontrar una línea correponsdiente en la tabla user."
	    . "</div>");   
  	#print("Error: $error_sql \n--> No puedo encontrar una línea correponsdiente en la tabla user.");	
  	break;	
 case 1136:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Sentencia no ejecutada por diferencia entre numero de columnas y valores recibidos."
	    . "</div>");   
  	#print("Error: $error_sql \n--> No puedo encontrar una línea correponsdiente en la tabla user.");	
  	break;	
 case 1137:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> No puedo reabrir tabla."
	    . "</div>");   
  	#print("Error: $error_sql \n--> No puedo reabrir tabla.");	
  	break;	
 case 1138:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Invalido uso de valor NULL."
	    . "</div>");   
  	#print("Error: $error_sql \n--> Invalido uso de valor NULL.");	
  	break;	
 case 1139:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Obtenido error de regexp."
	    . "</div>");   
  	#print("Error: $error_sql \n--> Obtenido error de regexp.");	
  	break;
 case 1140:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Mezcla de columnas GROUP (MIN(),MAX(),COUNT()...) con no GROUP\n"
		. "columnas es ilegal si no hat la clausula GROUP BY."
	    . "</div>");   
  	#print("Error: $error_sql \n--> Mezcla de columnas GROUP (MIN(),MAX(),COUNT()...) con no GROUP\n"
	#	. "columnas es ilegal si no hat la clausula GROUP BY.");	
  	break;
 case 1141:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> No existe permiso definido para usuario en el servidor."
	    . "</div>");   
  	#print("Error: $error_sql \n--> No existe permiso definido para usuario en el servidor.");	
  	break;
 case 1142:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> comando negado al usuario\n"
		. "para tabla, por falta de permisos de Insercion,\n"
		. "Actualizacion, Consulta o Eliminacion."
	    . "</div>");   
  	#print("Error: $error_sql \n--> comando negado al usuario\n"
	#	. "para tabla, por falta de permisos de Insercion,\n"
	#	. "Actualizacion, Consulta o Eliminacion.");	
  	break;
 case 1143:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> comando negado al usuario para columna en la tabla."
	    . "</div>");   
  	#print("Error: $error_sql \n--> comando negado al usuario para columna en la tabla.");	
  	break;	
 case 1144:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Ilegal comando GRANT/REVOKE. Por favor consulte el manual\n"
		. "para cuales permisos pueden ser usados."
	    . "</div>");   
  	#print("Error: $error_sql \n--> Ilegal comando GRANT/REVOKE. Por favor consulte el manual\n"
	#	. "para cuales permisos pueden ser usados.");	
  	break;
 case 1145:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> rror: $error_sql \n--> El argumento para servidor o usuario para GRANT es demasiado grande."
	    . "</div>");   
  	#print("Error: $error_sql \n--> El argumento para servidor o usuario para GRANT es demasiado grande.");	
  	break;	
 case 1146:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Tabla no existe."
	    . "</div>");   
  	#print("Error: $error_sql \n--> Tabla no existe.");	
  	break;	
  case 1147:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> No existe tal permiso definido para usuario en el\n"
		. "servidor en la tabla."
	    . "</div>");    
  	#print("Error: $error_sql \n--> No existe tal permiso definido para usuario en el\n"
	#	. "servidor en la tabla.");	
  	break;
 case 1148:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> El comando usado no es permitido con esta\n"
		. "versión de MySQL."
	    . "</div>"); 
  	#print("Error: $error_sql \n--> El comando usado no es permitido con esta\n"
	#	. "versión de MySQL.");	
  	break;	
 case 1149:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Algo está equivocado en su sintax."
	    . "</div>");  
  	#print("Error: $error_sql \n--> Algo está equivocado en su sintax.");	
  	break;	
 case 1163:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> El tipo de tabla usada no permite\n"
		. "soporte para columnas BLOB/TEXT."
	    . "</div>");  
  	#print("Error: $error_sql \n--> El tipo de tabla usada no permite\n"
	#	. "soporte para columnas BLOB/TEXT.");	
  	break;
 case 1164:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> El tipo de tabla usada no permite soporte\n"
		. "para columnas AUTO_INCREMENT."
	    . "</div>");  
  	#print("Error: $error_sql \n--> El tipo de tabla usada no permite soporte\n"
	#	. "para columnas AUTO_INCREMENT.");	
  	break;	
 case 1165:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> INSERT DELAYED no puede ser usado con tablas,\n"
		. "porque esta bloqueada con LOCK TABLES."
	    . "</div>");  
  	#print("Error: $error_sql \n--> INSERT DELAYED no puede ser usado con tablas,\n"
	#	. "porque esta bloqueada con LOCK TABLES.");	
  	break;
 case 1166:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Incorrecto nombre de columna."
	    . "</div>");  
  	#print("Error: $error_sql \n--> Incorrecto nombre de columna.");	
  	break;
 case 1167:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> El manipulador de tabla usado no puede\n"
		. "indexar columna."
	    . "</div>");  
  	#print("Error: $error_sql \n--> El manipulador de tabla usado no puede\n"
	#	. "indexar columna.");	
  	break;	
case 1168:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Todas las tablas en la MERGE tabla no estan\n"
		. "definidas identicamente."
	    . "</div>"); 
  	#print("Error: $error_sql \n--> Todas las tablas en la MERGE tabla no estan\n"
	#	. "definidas identicamente.");	
  	break;	
 case 1169:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> No puedo escribir, debido al único constraint,\n"
		. "para tabla."
	    . "</div>");  
  	#print("Error: $error_sql \n--> No puedo escribir, debido al único constraint,\n"
	#	. "para tabla.");	
  	break;	
 case 1170:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Columna BLOB column usada en especificación de\n"
		. "clave sin tamaño de la clave."
	    . "</div>");  
  	#print("Error: $error_sql \n--> Columna BLOB column usada en especificación de\n"
	#	. "clave sin tamaño de la clave.");	
  	break;
 case 1171:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Todas las partes de un PRIMARY KEY deben ser NOT NULL;\n"
		. "Si necesitas NULL en una clave, use UNIQUE."
	    . "</div>");  
  	#print("Error: $error_sql \n--> Todas las partes de un PRIMARY KEY deben ser NOT NULL;\n"
	#	. "Si necesitas NULL en una clave, use UNIQUE.");	
  	break;	
 case 1172:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Resultado compuesto de mas que una línea."
	    . "</div>");  
  	#print("Error: $error_sql \n--> Resultado compuesto de mas que una línea.");	
  	break;	
 case 1173:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Este tipo de tabla necesita de una primary key."
	    . "</div>"); 
  	#print("Error: $error_sql \n--> Este tipo de tabla necesita de una primary key.");	
  	break;
 case 1175:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Tu estás usando modo de actualización segura y tentado\n"
		. "actualizar una tabla sin un WHERE que usa una KEY columna."
	    . "</div>");  
  	#print("Error: $error_sql \n--> Tu estás usando modo de actualización segura y tentado\n"
	#	. "actualizar una tabla sin un WHERE que usa una KEY columna.");	
  	break;	
 case 1176:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Clave no existe en la tabla."
	    . "</div>");  
  	#print("Error: $error_sql \n--> Clave no existe en la tabla.");	
  	break;	
 case 1177:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> No puedo abrir tabla."
	    . "</div>");  
  	#print("Error: $error_sql \n--> No puedo abrir tabla.");	
  	break;
 case 1179:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> No tienes el permiso para ejecutar este\n"
		. "comando en una transición."
	    . "</div>");  
  	#print("Error: $error_sql \n--> No tienes el permiso para ejecutar este\n"
	#	. "comando en una transición.");	
  	break;	
 case 1180:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Obtenido error %d durante COMMIT."
	    . "</div>");  
  	#print("Error: $error_sql \n--> Obtenido error %d durante COMMIT.");	
  	break;
 case 1181:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Obtenido error %d durante ROLLBACK."
	    . "</div>");  
  	#print("Error: $error_sql \n--> Obtenido error %d durante ROLLBACK.");	
  	break;
 case 1182:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> rror: $error_sql \n--> Obtenido error durante FLUSH_LOGS."
	    . "</div>");  
  	#print("Error: $error_sql \n--> Obtenido error durante FLUSH_LOGS.");	
  	break;	
 case 1183:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Obtenido error durante CHECKPOINT."
	    . "</div>");  
  	#print("Error: $error_sql \n--> Obtenido error durante CHECKPOINT.");	
  	break;	
 case 1184:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong>Error: $error_sql \n--> Abortada conexión para db del usuario yservidor."
	    . "</div>");  
  	#print("Error: $error_sql \n--> Abortada conexión para db del usuario yservidor.");	
  	break;	
 case 1185:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> El manipulador de tabla no soporta dump para\n"
		. "tabla binaria."
	    . "</div>");  
  	#print("Error: $error_sql \n--> El manipulador de tabla no soporta dump para\n"
	#	. "tabla binaria.");	
  	break;
 case 1187:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Falla reconstruyendo el indice de la tabla dumped."
	    . "</div>");  
  	#print("Error: $error_sql \n--> Falla reconstruyendo el indice de la tabla dumped.");	
  	break;	
 case 1192:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> No puedo ejecutar el comando dado porque tienes\n"
		. "tablas bloqueadas o una transición activa."
	    . "</div>");  
  	#print("Error: $error_sql \n--> No puedo ejecutar el comando dado porque tienes\n"
	#	. "tablas bloqueadas o una transición activa.");	
  	break;	
 case 1364:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> No puedo ejecutar la sentencia, por valor no nombrado\n"
		. "sin valor por defecto."
	    . "</div>");  
  	#print("Error: $error_sql \n--> No puedo ejecutar el comando dado porque tienes\n"
	#	. "tablas bloqueadas o una transición activa.");	
  	break;
  case 1292:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Incorrect datetime value:."
	    . "</div>");  
  	#print("Error: $error_sql \n--> No puedo ejecutar el comando dado porque tienes\n"
	#	. "tablas bloqueadas o una transición activa.");	
  	break;
 case 1451:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> NO pudo borrar o actualizar porque existe(n)\n"
		. "claves alternas activas con esta claven en archivo(s) relacionados."
	    . "</div>");  
  	#print("Error: $error_sql \n--> NO pudo borrar o actualizar porque existe(n)\n"
	#	. "claves alternas activas con esta claven en archivo(s) relacionados.");	
  	break;	
 case 1406:
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> Informacion muy larga para la columna que lo recibe."
	    . "</div>");  
  	#print("Error: $error_sql \n--> NO pudo borrar o actualizar porque existe(n)\n"
	#	. "claves alternas activas con esta claven en archivo(s) relacionados.");	
  	break;		
 case 3000: // desde aqui empiezan errores propios
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql \n--> NO se pudo escribir en archivo nuevo registro\n"
		. "en el archivo texto"
	    . "</div>"); 
  	#print("Error: $error_sql \n--> NO se pudo escribir en archivo nuevo registro\n"
	#	. "en el archivo texto");	
  	break;		
  default:	
   	print("<div class='alert alert-warning'>"
	    . "<strong>Alerta!</strong> Error: $error_sql --> Sentencia <strong><em>NO</em></strong>"
		. " ejecutada, por error no definido."
	    . "</div>");    
	#print("Error: $error_sql --> Sentencia <strong><em>NO</em></strong>"
	#	. " ejecutada, por error no definido.");
	break; 
  }
}
/*Error: 1197 SQLSTATE: HY000 (ER_TRANS_CACHE_FULL) 
Mensaje: Multipla transición necesita mas que 'max_binlog_cache_size' bytes de almacenamiento. Aumente esta variable mysqld y tente de nuevo 
Error: 1198 SQLSTATE: HY000 (ER_SLAVE_MUST_STOP) 
Mensaje: Esta operación no puede ser hecha con el esclavo funcionando, primero use STOP SLAVE 
Error: 1199 SQLSTATE: HY000 (ER_SLAVE_NOT_RUNNING) 
Mensaje: Esta operación necesita el esclavo funcionando, configure esclavo y haga el START SLAVE 
Error: 1200 SQLSTATE: HY000 (ER_BAD_SLAVE) 
Mensaje: El servidor no está configurado como esclavo, edite el archivo config file o con CHANGE MASTER TO 
Error: 1203 SQLSTATE: 42000 (ER_TOO_MANY_USER_CONNECTIONS) 
Mensaje: Usario %s ya tiene mas que 'max_user_connections' conexiones activas 
Error: 1204 SQLSTATE: HY000 (ER_SET_CONSTANTS_ONLY) 
Mensaje: Tu solo debes usar expresiones constantes con SET 
Error: 1205 SQLSTATE: HY000 (ER_LOCK_WAIT_TIMEOUT) 
Mensaje: Tiempo de bloqueo de espera excedido 
Error: 1206 SQLSTATE: HY000 (ER_LOCK_TABLE_FULL) 
Mensaje: El número total de bloqueos excede el tamaño de bloqueo de la tabla 
Error: 1207 SQLSTATE: 25000 (ER_READ_ONLY_TRANSACTION) 
Mensaje: Bloqueos de actualización no pueden ser adqueridos durante una transición READ UNCOMMITTED 
Error: 1208 SQLSTATE: HY000 (ER_DROP_DB_WITH_READ_LOCK) 
Mensaje: DROP DATABASE no permitido mientras un thread está ejerciendo un bloqueo de lectura global 
Error: 1209 SQLSTATE: HY000 (ER_CREATE_DB_WITH_READ_LOCK) 
Mensaje: CREATE DATABASE no permitido mientras un thread está ejerciendo un bloqueo de lectura global 
Error: 1211 SQLSTATE: 42000 (ER_NO_PERMISSION_TO_CREATE_USER) 
Mensaje:  no es permitido para crear nuevos usuarios 
Error: 1212 SQLSTATE: HY000 (ER_UNION_TABLES_IN_DIFFERENT_DIR) 
Mensaje: Incorrecta definición de la tabla; Todas las tablas MERGE deben estar en el mismo banco de datos 
Error: 1213 SQLSTATE: 40001 (ER_LOCK_DEADLOCK) 
Mensaje: Encontrado deadlock cuando tentando obtener el bloqueo; Tente recomenzar la transición 
Error: 1214 SQLSTATE: HY000 (ER_TABLE_CANT_HANDLE_FT) 
Mensaje: El tipo de tabla usada no soporta índices FULLTEXT 
Error: 1215 SQLSTATE: HY000 (ER_CANNOT_ADD_FOREIGN) 
Mensaje: No puede adicionar clave extranjera constraint 
Error: 1216 SQLSTATE: 23000 (ER_NO_REFERENCED_ROW) 
Mensaje: No puede adicionar una línea hijo: falla de clave extranjera constraint 
Error: 1217 SQLSTATE: 23000 (ER_ROW_IS_REFERENCED) 
Mensaje: No puede deletar una línea padre: falla de clave extranjera constraint 
Error: 1219 SQLSTATE: HY000 (ER_QUERY_ON_MASTER) 
Mensaje: Error executando el query en master: %s 
Error: 1222 SQLSTATE: 21000 (ER_WRONG_NUMBER_OF_COLUMNS_IN_SELECT) 
Mensaje: El comando SELECT usado tiene diferente número de columnas 
Error: 1223 SQLSTATE: HY000 (ER_CANT_UPDATE_WITH_READLOCK) 
Mensaje: No puedo ejecutar el query porque usted tiene conflicto de traba de lectura 
Error: 1224 SQLSTATE: HY000 (ER_MIXING_NOT_ALLOWED) 
Mensaje: Mezla de transancional y no-transancional tablas está deshabilitada 
Error: 1226 SQLSTATE: 42000 (ER_USER_LIMIT_REACHED) 
Mensaje: Usuario '%s' ha excedido el recurso '%s' (actual valor: %ld) 
Error: 1227 SQLSTATE: 42000 (ER_SPECIFIC_ACCESS_DENIED_ERROR) 
Mensaje: Acceso negado. Usted necesita el privilegio %s para esta operación 
Error: 1232 SQLSTATE: 42000 (ER_WRONG_TYPE_FOR_VAR) 
Mensaje: Tipo de argumento equivocado para variable '%s'  
Error: 1237 SQLSTATE: HY000 (ER_SLAVE_IGNORED_TABLE) 
Mensaje: Slave SQL thread ignorado el query debido a las reglas de replicación-*-tabla 
Error: 1239 SQLSTATE: 42000 (ER_WRONG_FK_DEF) 
Mensaje: Equivocada definición de llave extranjera para '%s': %s 
Error: 1240 SQLSTATE: HY000 (ER_KEY_REF_DO_NOT_MATCH_TABLE_REF) 
Mensaje: Referencia de llave y referencia de tabla no coinciden 
Error: 1242 SQLSTATE: 21000 (ER_SUBQUERY_NO_1_ROW) 
Mensaje: Subconsulta retorna mas que 1 línea 
Error: 1243 SQLSTATE: HY000 (ER_UNKNOWN_STMT_HANDLER) 
Mensaje: Desconocido preparado comando handler (%.*s) dado para %s 
Error: 1244 SQLSTATE: HY000 (ER_CORRUPT_HELP_DB) 
Mensaje: Base de datos Help está corrupto o no existe 
Error: 1245 SQLSTATE: HY000 (ER_CYCLIC_REFERENCE) 
Mensaje: Cíclica referencia en subconsultas 
Error: 1247 SQLSTATE: 42S22 (ER_ILLEGAL_REFERENCE) 
Mensaje: Referencia '%s' no soportada (%s) 
Error: 1248 SQLSTATE: 42000 (ER_DERIVED_MUST_HAVE_ALIAS) 
Mensaje: Cada tabla derivada debe tener su propio alias 
Error: 1249 SQLSTATE: 01000 (ER_SELECT_REDUCED) 
Mensaje: Select %u fué reducido durante optimización 
Error: 1250 SQLSTATE: 42000 (ER_TABLENAME_NOT_ALLOWED_HERE) 
Mensaje: Tabla '%s' de uno de los SELECT no puede ser usada en %s 
Error: 1251 SQLSTATE: 08004 (ER_NOT_SUPPORTED_AUTH_MODE) 
Mensaje: Cliente no soporta protocolo de autenticación solicitado por el servidor; considere actualizar el cliente MySQL 
Error: 1253 SQLSTATE: 42000 (ER_COLLATION_CHARSET_MISMATCH) 
Mensaje: COLLATION '%s' no es válido para CHARACTER SET '%s' 
Error: 1254 SQLSTATE: HY000 (ER_SLAVE_WAS_RUNNING) 
Mensaje: Slave ya está funcionando 
Error: 1255 SQLSTATE: HY000 (ER_SLAVE_WAS_NOT_RUNNING) 
Mensaje: Slave ya fué parado 
Error: 1256 SQLSTATE: HY000 (ER_TOO_BIG_FOR_UNCOMPRESS) 
Mensaje: Tamaño demasiado grande para datos descomprimidos. El máximo tamaño es %d. (probablemente, extensión de datos descomprimidos fué corrompida) 
Error: 1260 SQLSTATE: HY000 (ER_CUT_VALUE_GROUP_CONCAT) 
Mensaje: %d línea(s) fue(fueron) cortadas por group_concat() 
Error: 1261 SQLSTATE: 01000 (ER_WARN_TOO_FEW_RECORDS) 
Mensaje: Línea %ld no contiene datos para todas las columnas 
Error: 1262 SQLSTATE: 01000 (ER_WARN_TOO_MANY_RECORDS) 
Mensaje: Línea %ld fué truncada; La misma contine mas datos que las que existen en las columnas de entrada 
Error: 1263 SQLSTATE: 22004 (ER_WARN_NULL_TO_NOTNULL) 
Mensaje: Datos truncado, NULL suministrado para NOT NULL columna '%s' en la línea %ld 
Error: 1264 SQLSTATE: 22003 (ER_WARN_DATA_OUT_OF_RANGE) 
Mensaje: Datos truncados, fuera de gama para columna '%s' en la línea %ld 
Error: 1265 SQLSTATE: 01000 (WARN_DATA_TRUNCATED) 
Mensaje: Datos truncados para columna '%s' en la línea %ld 
Error: 1266 SQLSTATE: HY000 (ER_WARN_USING_OTHER_HANDLER) 
Mensaje: Usando motor de almacenamiento %s para tabla '%s' 
Error: 1269 SQLSTATE: HY000 (ER_REVOKE_GRANTS) 
Mensaje: No puede revocar todos los privilegios, derecho para uno o mas de los usuarios solicitados 
Error: 1271 SQLSTATE: HY000 (ER_CANT_AGGREGATE_NCOLLATIONS) 
Mensaje: Ilegal mezcla de collations para operación '%s' 
Error: 1272 SQLSTATE: HY000 (ER_VARIABLE_IS_NOT_STRUCT) 
Mensaje: Variable '%s' no es una variable componente (No puede ser usada como XXXX.variable_name) 
Error: 1273 SQLSTATE: HY000 (ER_UNKNOWN_COLLATION) 
Mensaje: Collation desconocida: '%s' 
Error: 1277 SQLSTATE: HY000 (ER_BAD_SLAVE_UNTIL_COND) 
Mensaje: Parametro equivocado o combinación de parametros para START SLAVE UNTIL 
Error: 1280 SQLSTATE: 42000 (ER_WRONG_NAME_FOR_INDEX) 
Mensaje: Nombre de índice incorrecto '%s' 
Error: 1281 SQLSTATE: 42000 (ER_WRONG_NAME_FOR_CATALOG) 
Mensaje: Nombre de catalog incorrecto '%s' 
Error: 1282 SQLSTATE: HY000 (ER_WARN_QC_RESIZE) 
Mensaje: Query cache fallada para configurar tamaño %lu, nuevo tamaño de query cache es %lu 
Error: 1283 SQLSTATE: HY000 (ER_BAD_FT_COLUMN) 
Mensaje: Columna '%s' no puede ser parte de FULLTEXT index 
Error: 1284 SQLSTATE: HY000 (ER_UNKNOWN_KEY_CACHE) 
Mensaje: Desconocida key cache '%s' 
Error: 1285 SQLSTATE: HY000 (ER_WARN_HOSTNAME_WONT_WORK) 
Mensaje: MySQL esta inicializado en modo --skip-name-resolve. Usted necesita reinicializarlo sin esta opción para este derecho funcionar 
Error: 1286 SQLSTATE: 42000 (ER_UNKNOWN_STORAGE_ENGINE) 
Mensaje: Desconocido motor de tabla '%s' 

Error: 1288 SQLSTATE: HY000 (ER_NON_UPDATABLE_TABLE) 
Mensaje: La tabla destino %s del %s no es actualizable 
Error: 1289 SQLSTATE: HY000 (ER_FEATURE_DISABLED) 
Mensaje: El recurso '%s' fue deshabilitado; usted necesita construir MySQL con '%s' para tener eso funcionando 
Error: 1290 SQLSTATE: HY000 (ER_OPTION_PREVENTS_STATEMENT) 
Mensaje: El servidor MySQL está rodando con la opción %s tal que no puede ejecutar este comando 
Error: 1291 SQLSTATE: HY000 (ER_DUPLICATED_VALUE_IN_TYPE) 
Mensaje: Columna '%s' tiene valor doblado '%s' en %s 
Error: 1292 SQLSTATE: 22007 (ER_TRUNCATED_WRONG_VALUE) 
Mensaje: Equivocado truncado %s valor: '%s' 
Error: 1293 SQLSTATE: HY000 (ER_TOO_MUCH_AUTO_TIMESTAMP_COLS) 
Mensaje: Incorrecta definición de tabla; Solamente debe haber una columna TIMESTAMP con CURRENT_TIMESTAMP en DEFAULT o ON UPDATE cláusula 
Error: 1294 SQLSTATE: HY000 (ER_INVALID_ON_UPDATE) 
Mensaje: Inválido ON UPDATE cláusula para campo '%s' 
Error: 1295 SQLSTATE: HY000 (ER_UNSUPPORTED_PS) 
Mensaje: This command is not supported in the prepared statement protocol yet 
Error: 1296 SQLSTATE: HY000 (ER_GET_ERRMSG) 
Mensaje: Got error %d '%s' from %s 
Error: 1297 SQLSTATE: HY000 (ER_GET_TEMPORARY_ERRMSG) 
Mensaje: Got temporary error %d '%s' from %s 
Error: 1298 SQLSTATE: HY000 (ER_UNKNOWN_TIME_ZONE) 
Mensaje: Unknown or incorrect time zone: '%s' 
Error: 1299 SQLSTATE: HY000 (ER_WARN_INVALID_TIMESTAMP) 
Mensaje: Invalid TIMESTAMP value in column '%s' at row %ld 
Error: 1300 SQLSTATE: HY000 (ER_INVALID_CHARACTER_STRING) 
Mensaje: Invalid %s character string: '%s' 
Error: 1301 SQLSTATE: HY000 (ER_WARN_ALLOWED_PACKET_OVERFLOWED) 
Mensaje: Result of %s() was larger than max_allowed_packet (%ld) - truncated 
Error: 1302 SQLSTATE: HY000 (ER_CONFLICTING_DECLARATIONS) 
Mensaje: Conflicting declarations: '%s%s' and '%s%s' 
Error: 1303 SQLSTATE: 2F003 (ER_SP_NO_RECURSIVE_CREATE) 
Mensaje: Can't create a %s from within another stored routine 
Error: 1304 SQLSTATE: 42000 (ER_SP_ALREADY_EXISTS) 
Mensaje: %s %s already exists 
Error: 1305 SQLSTATE: 42000 (ER_SP_DOES_NOT_EXIST) 
Mensaje: %s %s does not exist 
Error: 1306 SQLSTATE: HY000 (ER_SP_DROP_FAILED) 
Mensaje: Failed to DROP %s %s 
Error: 1307 SQLSTATE: HY000 (ER_SP_STORE_FAILED) 
Mensaje: Failed to CREATE %s %s 
Error: 1308 SQLSTATE: 42000 (ER_SP_LILABEL_MISMATCH) 
Mensaje: %s with no matching label: %s 
Error: 1309 SQLSTATE: 42000 (ER_SP_LABEL_REDEFINE) 
Mensaje: Redefining label %s 
Error: 1310 SQLSTATE: 42000 (ER_SP_LABEL_MISMATCH) 
Mensaje: End-label %s without match 
Error: 1311 SQLSTATE: 01000 (ER_SP_UNINIT_VAR) 
Mensaje: Referring to uninitialized variable %s 
Error: 1312 SQLSTATE: 0A000 (ER_SP_BADSELECT) 
Mensaje: PROCEDURE %s can't return a result set in the given context 
Error: 1313 SQLSTATE: 42000 (ER_SP_BADRETURN) 
Mensaje: RETURN is only allowed in a FUNCTION 
Error: 1314 SQLSTATE: 0A000 (ER_SP_BADSTATEMENT) 
Mensaje: %s is not allowed in stored procedures 
Error: 1315 SQLSTATE: 42000 (ER_UPDATE_LOG_DEPRECATED_IGNORED) 
Mensaje: The update log is deprecated and replaced by the binary log; SET SQL_LOG_UPDATE has been ignored 
Error: 1316 SQLSTATE: 42000 (ER_UPDATE_LOG_DEPRECATED_TRANSLATED) 
Mensaje: The update log is deprecated and replaced by the binary log; SET SQL_LOG_UPDATE has been translated to SET SQL_LOG_BIN 
Error: 1317 SQLSTATE: 70100 (ER_QUERY_INTERRUPTED) 
Mensaje: Query execution was interrupted 
Error: 1318 SQLSTATE: 42000 (ER_SP_WRONG_NO_OF_ARGS) 
Mensaje: Incorrect number of arguments for %s %s; expected %u, got %u 
Error: 1319 SQLSTATE: 42000 (ER_SP_COND_MISMATCH) 
Mensaje: Undefined CONDITION: %s 
Error: 1320 SQLSTATE: 42000 (ER_SP_NORETURN) 
Mensaje: No RETURN found in FUNCTION %s 
Error: 1321 SQLSTATE: 2F005 (ER_SP_NORETURNEND) 
Mensaje: FUNCTION %s ended without RETURN 
Error: 1322 SQLSTATE: 42000 (ER_SP_BAD_CURSOR_QUERY) 
Mensaje: Cursor statement must be a SELECT 
Error: 1323 SQLSTATE: 42000 (ER_SP_BAD_CURSOR_SELECT) 
Mensaje: Cursor SELECT must not have INTO 
Error: 1324 SQLSTATE: 42000 (ER_SP_CURSOR_MISMATCH) 
Mensaje: Undefined CURSOR: %s 
Error: 1325 SQLSTATE: 24000 (ER_SP_CURSOR_ALREADY_OPEN) 
Mensaje: Cursor is already open 
Error: 1326 SQLSTATE: 24000 (ER_SP_CURSOR_NOT_OPEN) 
Mensaje: Cursor is not open 
Error: 1327 SQLSTATE: 42000 (ER_SP_UNDECLARED_VAR) 
Mensaje: Undeclared variable: %s 
Error: 1328 SQLSTATE: HY000 (ER_SP_WRONG_NO_OF_FETCH_ARGS) 
Mensaje: Incorrect number of FETCH variables 
Error: 1329 SQLSTATE: 02000 (ER_SP_FETCH_NO_DATA) 
Mensaje: No data - zero rows fetched, selected, or processed 
Error: 1330 SQLSTATE: 42000 (ER_SP_DUP_PARAM) 
Mensaje: Duplicate parameter: %s 
Error: 1331 SQLSTATE: 42000 (ER_SP_DUP_VAR) 
Mensaje: Duplicate variable: %s 
Error: 1332 SQLSTATE: 42000 (ER_SP_DUP_COND) 
Mensaje: Duplicate condition: %s 
Error: 1333 SQLSTATE: 42000 (ER_SP_DUP_CURS) 
Mensaje: Duplicate cursor: %s 
Error: 1334 SQLSTATE: HY000 (ER_SP_CANT_ALTER) 
Mensaje: Failed to ALTER %s %s 
Error: 1335 SQLSTATE: 0A000 (ER_SP_SUBSELECT_NYI) 
Mensaje: Subselect value not supported 
Error: 1336 SQLSTATE: 0A000 (ER_STMT_NOT_ALLOWED_IN_SF_OR_TRG) 
Mensaje: %s is not allowed in stored function or trigger 
Error: 1337 SQLSTATE: 42000 (ER_SP_VARCOND_AFTER_CURSHNDLR) 
Mensaje: Variable or condition declaration after cursor or handler declaration 
Error: 1338 SQLSTATE: 42000 (ER_SP_CURSOR_AFTER_HANDLER) 
Mensaje: Cursor declaration after handler declaration 
Error: 1339 SQLSTATE: 20000 (ER_SP_CASE_NOT_FOUND) 
Mensaje: Case not found for CASE statement 
Error: 1340 SQLSTATE: HY000 (ER_FPARSER_TOO_BIG_FILE) 
Mensaje: Configuration file '%s' is too big 
Error: 1341 SQLSTATE: HY000 (ER_FPARSER_BAD_HEADER) 
Mensaje: Malformed file type header in file '%s' 
Error: 1342 SQLSTATE: HY000 (ER_FPARSER_EOF_IN_COMMENT) 
Mensaje: Unexpected end of file while parsing comment '%s' 
Error: 1343 SQLSTATE: HY000 (ER_FPARSER_ERROR_IN_PARAMETER) 
Mensaje: Error while parsing parameter '%s' (line: '%s') 
Error: 1344 SQLSTATE: HY000 (ER_FPARSER_EOF_IN_UNKNOWN_PARAMETER) 
Mensaje: Unexpected end of file while skipping unknown parameter '%s' 
Error: 1345 SQLSTATE: HY000 (ER_VIEW_NO_EXPLAIN) 
Mensaje: EXPLAIN/SHOW can not be issued; lacking privileges for underlying table 
Error: 1346 SQLSTATE: HY000 (ER_FRM_UNKNOWN_TYPE) 
Mensaje: File '%s' has unknown type '%s' in its header 
Error: 1347 SQLSTATE: HY000 (ER_WRONG_OBJECT) 
Mensaje: '%s.%s' is not %s 
Error: 1348 SQLSTATE: HY000 (ER_NONUPDATEABLE_COLUMN) 
Mensaje: Column '%s' is not updatable 
Error: 1349 SQLSTATE: HY000 (ER_VIEW_SELECT_DERIVED) 
Mensaje: View's SELECT contains a subquery in the FROM clause 
Error: 1350 SQLSTATE: HY000 (ER_VIEW_SELECT_CLAUSE) 
Mensaje: View's SELECT contains a '%s' clause 
Error: 1351 SQLSTATE: HY000 (ER_VIEW_SELECT_VARIABLE) 
Mensaje: View's SELECT contains a variable or parameter 
Error: 1352 SQLSTATE: HY000 (ER_VIEW_SELECT_TMPTABLE) 
Mensaje: View's SELECT refers to a temporary table '%s' 
Error: 1353 SQLSTATE: HY000 (ER_VIEW_WRONG_LIST) 
Mensaje: View's SELECT and view's field list have different column counts 
Error: 1354 SQLSTATE: HY000 (ER_WARN_VIEW_MERGE) 
Mensaje: View merge algorithm can't be used here for now (assumed undefined algorithm) 
Error: 1355 SQLSTATE: HY000 (ER_WARN_VIEW_WITHOUT_KEY) 
Mensaje: View being updated does not have complete key of underlying table in it 
Error: 1356 SQLSTATE: HY000 (ER_VIEW_INVALID) 
Mensaje: View '%s.%s' references invalid table(s) or column(s) or function(s) or definer/invoker of view lack rights to use them 
Error: 1357 SQLSTATE: HY000 (ER_SP_NO_DROP_SP) 
Mensaje: Can't drop or alter a %s from within another stored routine 
Error: 1358 SQLSTATE: HY000 (ER_SP_GOTO_IN_HNDLR) 
Mensaje: GOTO is not allowed in a stored procedure handler 
Error: 1359 SQLSTATE: HY000 (ER_TRG_ALREADY_EXISTS) 
Mensaje: Trigger already exists 
Error: 1360 SQLSTATE: HY000 (ER_TRG_DOES_NOT_EXIST) 
Mensaje: Trigger does not exist 
Error: 1361 SQLSTATE: HY000 (ER_TRG_ON_VIEW_OR_TEMP_TABLE) 
Mensaje: Trigger's '%s' is view or temporary table 
Error: 1362 SQLSTATE: HY000 (ER_TRG_CANT_CHANGE_ROW) 
Mensaje: Updating of %s row is not allowed in %strigger 
Error: 1363 SQLSTATE: HY000 (ER_TRG_NO_SUCH_ROW_IN_TRG) 
Mensaje: There is no %s row in %s trigger 
Error: 1364 SQLSTATE: HY000 (ER_NO_DEFAULT_FOR_FIELD) 
Mensaje: Field '%s' doesn't have a default value 
Error: 1365 SQLSTATE: 22012 (ER_DIVISION_BY_ZERO) 
Mensaje: Division by 0 
Error: 1366 SQLSTATE: HY000 (ER_TRUNCATED_WRONG_VALUE_FOR_FIELD) 
Mensaje: Incorrect %s value: '%s' for column '%s' at row %ld 
Error: 1367 SQLSTATE: 22007 (ER_ILLEGAL_VALUE_FOR_TYPE) 
Mensaje: Illegal %s '%s' value found during parsing 
Error: 1368 SQLSTATE: HY000 (ER_VIEW_NONUPD_CHECK) 
Mensaje: CHECK OPTION on non-updatable view '%s.%s' 
Error: 1369 SQLSTATE: HY000 (ER_VIEW_CHECK_FAILED) 
Mensaje: CHECK OPTION failed '%s.%s' 
Error: 1370 SQLSTATE: 42000 (ER_PROCACCESS_DENIED_ERROR) 
Mensaje: %s command denied to user '%s'@'%s' for routine '%s' 
Error: 1371 SQLSTATE: HY000 (ER_RELAY_LOG_FAIL) 
Mensaje: Failed purging old relay logs: %s 
Error: 1372 SQLSTATE: HY000 (ER_PASSWD_LENGTH) 
Mensaje: Password hash should be a %d-digit hexadecimal number 
Error: 1373 SQLSTATE: HY000 (ER_UNKNOWN_TARGET_BINLOG) 
Mensaje: Target log not found in binlog index 
Error: 1374 SQLSTATE: HY000 (ER_IO_ERR_LOG_INDEX_READ) 
Mensaje: I/O error reading log index file 
Error: 1375 SQLSTATE: HY000 (ER_BINLOG_PURGE_PROHIBITED) 
Mensaje: Server configuration does not permit binlog purge 
Error: 1376 SQLSTATE: HY000 (ER_FSEEK_FAIL) 
Mensaje: Failed on fseek() 
Error: 1377 SQLSTATE: HY000 (ER_BINLOG_PURGE_FATAL_ERR) 
Mensaje: Fatal error during log purge 
Error: 1378 SQLSTATE: HY000 (ER_LOG_IN_USE) 
Mensaje: A purgeable log is in use, will not purge 
Error: 1379 SQLSTATE: HY000 (ER_LOG_PURGE_UNKNOWN_ERR) 
Mensaje: Unknown error during log purge 
Error: 1380 SQLSTATE: HY000 (ER_RELAY_LOG_INIT) 
Mensaje: Failed initializing relay log position: %s 
Error: 1381 SQLSTATE: HY000 (ER_NO_BINARY_LOGGING) 
Mensaje: You are not using binary logging 
Error: 1382 SQLSTATE: HY000 (ER_RESERVED_SYNTAX) 
Mensaje: The '%s' syntax is reserved for purposes internal to the MySQL server 
Error: 1383 SQLSTATE: HY000 (ER_WSAS_FAILED) 
Mensaje: WSAStartup Failed 
Error: 1384 SQLSTATE: HY000 (ER_DIFF_GROUPS_PROC) 
Mensaje: Can't handle procedures with different groups yet 
Error: 1385 SQLSTATE: HY000 (ER_NO_GROUP_FOR_PROC) 
Mensaje: Select must have a group with this procedure 
Error: 1386 SQLSTATE: HY000 (ER_ORDER_WITH_PROC) 
Mensaje: Can't use ORDER clause with this procedure 
Error: 1387 SQLSTATE: HY000 (ER_LOGGING_PROHIBIT_CHANGING_OF) 
Mensaje: Binary logging and replication forbid changing the global server %s 
Error: 1388 SQLSTATE: HY000 (ER_NO_FILE_MAPPING) 
Mensaje: Can't map file: %s, errno: %d 
Error: 1389 SQLSTATE: HY000 (ER_WRONG_MAGIC) 
Mensaje: Wrong magic in %s 
Error: 1390 SQLSTATE: HY000 (ER_PS_MANY_PARAM) 
Mensaje: Prepared statement contains too many placeholders 
Error: 1391 SQLSTATE: HY000 (ER_KEY_PART_0) 
Mensaje: Key part '%s' length cannot be 0 
Error: 1392 SQLSTATE: HY000 (ER_VIEW_CHECKSUM) 
Mensaje: View text checksum failed 
Error: 1393 SQLSTATE: HY000 (ER_VIEW_MULTIUPDATE) 
Mensaje: Can not modify more than one base table through a join view '%s.%s' 
Error: 1394 SQLSTATE: HY000 (ER_VIEW_NO_INSERT_FIELD_LIST) 
Mensaje: Can not insert into join view '%s.%s' without fields list 
Error: 1395 SQLSTATE: HY000 (ER_VIEW_DELETE_MERGE_VIEW) 
Mensaje: Can not delete from join view '%s.%s' 
Error: 1396 SQLSTATE: HY000 (ER_CANNOT_USER) 
Mensaje: Operation %s failed for %s 
Error: 1397 SQLSTATE: XAE04 (ER_XAER_NOTA) 
Mensaje: XAER_NOTA: Unknown XID 
Error: 1398 SQLSTATE: XAE05 (ER_XAER_INVAL) 
Mensaje: XAER_INVAL: Invalid arguments (or unsupported command) 
Error: 1399 SQLSTATE: XAE07 (ER_XAER_RMFAIL) 
Mensaje: XAER_RMFAIL: The command cannot be executed when global transaction is in the %s state 
Error: 1400 SQLSTATE: XAE09 (ER_XAER_OUTSIDE) 
Mensaje: XAER_OUTSIDE: Some work is done outside global transaction 
Error: 1401 SQLSTATE: XAE03 (ER_XAER_RMERR) 
Mensaje: XAER_RMERR: Fatal error occurred in the transaction branch - check your data for consistency 
Error: 1402 SQLSTATE: XA100 (ER_XA_RBROLLBACK) 
Mensaje: XA_RBROLLBACK: Transaction branch was rolled back 
Error: 1403 SQLSTATE: 42000 (ER_NONEXISTING_PROC_GRANT) 
Mensaje: There is no such grant defined for user '%s' on host '%s' on routine '%s' 
Error: 1404 SQLSTATE: HY000 (ER_PROC_AUTO_GRANT_FAIL) 
Mensaje: Failed to grant EXECUTE and ALTER ROUTINE privileges 
Error: 1405 SQLSTATE: HY000 (ER_PROC_AUTO_REVOKE_FAIL) 
Mensaje: Failed to revoke all privileges to dropped routine 
Error: 1406 SQLSTATE: 22001 (ER_DATA_TOO_LONG) 
Mensaje: Data too long for column '%s' at row %ld 
Error: 1407 SQLSTATE: 42000 (ER_SP_BAD_SQLSTATE) 
Mensaje: Bad SQLSTATE: '%s' 
Error: 1408 SQLSTATE: HY000 (ER_STARTUP) 
Mensaje: %s: ready for connections. Version: '%s' socket: '%s' port: %d %s 
Error: 1409 SQLSTATE: HY000 (ER_LOAD_FROM_FIXED_SIZE_ROWS_TO_VAR) 
Mensaje: Can't load value from file with fixed size rows to variable 
Error: 1410 SQLSTATE: 42000 (ER_CANT_CREATE_USER_WITH_GRANT) 
Mensaje: You are not allowed to create a user with GRANT 
Error: 1411 SQLSTATE: HY000 (ER_WRONG_VALUE_FOR_TYPE) 
Mensaje: Incorrect %s value: '%s' for function %s 
Error: 1412 SQLSTATE: HY000 (ER_TABLE_DEF_CHANGED) 
Mensaje: Table definition has changed, please retry transaction 
Error: 1413 SQLSTATE: 42000 (ER_SP_DUP_HANDLER) 
Mensaje: Duplicate handler declared in the same block 
Error: 1414 SQLSTATE: 42000 (ER_SP_NOT_VAR_ARG) 
Mensaje: OUT or INOUT argument %d for routine %s is not a variable or NEW pseudo-variable in BEFORE trigger 
Error: 1415 SQLSTATE: 0A000 (ER_SP_NO_RETSET) 
Mensaje: Not allowed to return a result set from a %s 
Error: 1416 SQLSTATE: 22003 (ER_CANT_CREATE_GEOMETRY_OBJECT) 
Mensaje: Cannot get geometry object from data you send to the GEOMETRY field 
Error: 1417 SQLSTATE: HY000 (ER_FAILED_ROUTINE_BREAK_BINLOG) 
Mensaje: A routine failed and has neither NO SQL nor READS SQL DATA in its declaration and binary logging is enabled; if non-transactional tables were updated, the binary log will miss their changes 
Error: 1418 SQLSTATE: HY000 (ER_BINLOG_UNSAFE_ROUTINE) 
Mensaje: This function has none of DETERMINISTIC, NO SQL, or READS SQL DATA in its declaration and binary logging is enabled (you *might* want to use the less safe log_bin_trust_function_creators variable) 
Error: 1419 SQLSTATE: HY000 (ER_BINLOG_CREATE_ROUTINE_NEED_SUPER) 
Mensaje: You do not have the SUPER privilege and binary logging is enabled (you *might* want to use the less safe log_bin_trust_function_creators variable) 
Error: 1420 SQLSTATE: HY000 (ER_EXEC_STMT_WITH_OPEN_CURSOR) 
Mensaje: You can't execute a prepared statement which has an open cursor associated with it. Reset the statement to re-execute it. 
Error: 1421 SQLSTATE: HY000 (ER_STMT_HAS_NO_OPEN_CURSOR) 
Mensaje: The statement (%lu) has no open cursor. 
Error: 1422 SQLSTATE: HY000 (ER_COMMIT_NOT_ALLOWED_IN_SF_OR_TRG) 
Mensaje: Explicit or implicit commit is not allowed in stored function or trigger. 
Error: 1423 SQLSTATE: HY000 (ER_NO_DEFAULT_FOR_VIEW_FIELD) 
Mensaje: Field of view '%s.%s' underlying table doesn't have a default value 
Error: 1424 SQLSTATE: HY000 (ER_SP_NO_RECURSION) 
Mensaje: Recursive stored functions and triggers are not allowed. 
Error: 1425 SQLSTATE: 42000 (ER_TOO_BIG_SCALE) 
Mensaje: Too big scale %lu specified for column '%s'. Maximum is %d. 
Error: 1426 SQLSTATE: 42000 (ER_TOO_BIG_PRECISION) 
Mensaje: Too big precision %lu specified for column '%s'. Maximum is %lu. 
Error: 1427 SQLSTATE: 42000 (ER_M_BIGGER_THAN_D) 
Mensaje: For float(M,D), double(M,D) or decimal(M,D), M must be >= D (column '%s'). 
Error: 1428 SQLSTATE: HY000 (ER_WRONG_LOCK_OF_SYSTEM_TABLE) 
Mensaje: You can't combine write-locking of system '%s.%s' table with other tables 
Error: 1429 SQLSTATE: HY000 (ER_CONNECT_TO_FOREIGN_DATA_SOURCE) 
Mensaje: Unable to connect to foreign data source: %s 
Error: 1430 SQLSTATE: HY000 (ER_QUERY_ON_FOREIGN_DATA_SOURCE) 
Mensaje: There was a problem processing the query on the foreign data source. Data source error: %s 
Error: 1431 SQLSTATE: HY000 (ER_FOREIGN_DATA_SOURCE_DOESNT_EXIST) 
Mensaje: The foreign data source you are trying to reference does not exist. Data source error: %s 
Error: 1432 SQLSTATE: HY000 (ER_FOREIGN_DATA_STRING_INVALID_CANT_CREATE) 
Mensaje: Can't create federated table. The data source connection string '%s' is not in the correct format 
Error: 1433 SQLSTATE: HY000 (ER_FOREIGN_DATA_STRING_INVALID) 
Mensaje: The data source connection string '%s' is not in the correct format 
Error: 1434 SQLSTATE: HY000 (ER_CANT_CREATE_FEDERATED_TABLE) 
Mensaje: Can't create federated table. Foreign data src error: %s 
Error: 1435 SQLSTATE: HY000 (ER_TRG_IN_WRONG_SCHEMA) 
Mensaje: Trigger in wrong schema 
Error: 1436 SQLSTATE: HY000 (ER_STACK_OVERRUN_NEED_MORE) 
Mensaje: Thread stack overrun: %ld bytes used of a %ld byte stack, and %ld bytes needed. Use 'mysqld -O thread_stack=#' to specify a bigger stack. 
Error: 1437 SQLSTATE: 42000 (ER_TOO_LONG_BODY) 
Mensaje: Routine body for '%s' is too long 
Error: 1438 SQLSTATE: HY000 (ER_WARN_CANT_DROP_DEFAULT_KEYCACHE) 
Mensaje: Cannot drop default keycache 
Error: 1439 SQLSTATE: 42000 (ER_TOO_BIG_DISPLAYWIDTH) 
Mensaje: Display width out of range for column '%s' (max = %lu) 
Error: 1440 SQLSTATE: XAE08 (ER_XAER_DUPID) 
Mensaje: XAER_DUPID: The XID already exists 
Error: 1441 SQLSTATE: 22008 (ER_DATETIME_FUNCTION_OVERFLOW) 
Mensaje: Datetime function: %s field overflow 
Error: 1442 SQLSTATE: HY000 (ER_CANT_UPDATE_USED_TABLE_IN_SF_OR_TRG) 
Mensaje: Can't update table '%s' in stored function/trigger because it is already used by statement which invoked this stored function/trigger. 
Error: 1443 SQLSTATE: HY000 (ER_VIEW_PREVENT_UPDATE) 
Mensaje: The definition of table '%s' prevents operation %s on table '%s'. 
Error: 1444 SQLSTATE: HY000 (ER_PS_NO_RECURSION) 
Mensaje: The prepared statement contains a stored routine call that refers to that same statement. It's not allowed to execute a prepared statement in such a recursive manner 

Error: 1445 SQLSTATE: HY000 (ER_SP_CANT_SET_AUTOCOMMIT) 
Mensaje: Not allowed to set autocommit from a stored function or trigger 
Error: 1446 SQLSTATE: HY000 (ER_MALFORMED_DEFINER) 
Mensaje: Definer is not fully qualified 
Error: 1447 SQLSTATE: HY000 (ER_VIEW_FRM_NO_USER) 
Mensaje: View '%s'.'%s' has no definer information (old table format). Current user is used as definer. Please recreate the view! 
Error: 1448 SQLSTATE: HY000 (ER_VIEW_OTHER_USER) 
Mensaje: You need the SUPER privilege for creation view with '%s'@'%s' definer 
Error: 1449 SQLSTATE: HY000 (ER_NO_SUCH_USER) 
Mensaje: There is no '%s'@'%s' registered 
Error: 1450 SQLSTATE: HY000 (ER_FORBID_SCHEMA_CHANGE) 
Mensaje: Changing schema from '%s' to '%s' is not allowed. 
Error: 1452 SQLSTATE: 23000 (ER_NO_REFERENCED_ROW_2) 
Mensaje: Cannot add or update a child row: a foreign key constraint fails (%s) 
Error: 1453 SQLSTATE: 42000 (ER_SP_BAD_VAR_SHADOW) 
Mensaje: Variable '%s' must be quoted with `...`, or renamed 
Error: 1454 SQLSTATE: HY000 (ER_TRG_NO_DEFINER) 
Mensaje: No definer attribute for trigger '%s'.'%s'. The trigger will be activated under the authorization of the invoker, which may have insufficient privileges. Please recreate the trigger. 
Error: 1455 SQLSTATE: HY000 (ER_OLD_FILE_FORMAT) 
Mensaje: '%s' has an old format, you should re-create the '%s' object(s) 
Error: 1456 SQLSTATE: HY000 (ER_SP_RECURSION_LIMIT) 
Mensaje: Recursive limit %d (as set by the max_sp_recursion_depth variable) was exceeded for routine %s 
Error: 1457 SQLSTATE: HY000 (ER_SP_PROC_TABLE_CORRUPT) 
Mensaje: Failed to load routine %s. The table mysql.proc is missing, corrupt, or contains bad data (internal code %d) 
Error: 1458 SQLSTATE: 42000 (ER_SP_WRONG_NAME) 
Mensaje: Incorrect routine name '%s' 
Error: 1459 SQLSTATE: HY000 (ER_TABLE_NEEDS_UPGRADE) 
Mensaje: Table upgrade required. Please do "REPAIR TABLE `%s`" to fix it! 
Error: 1460 SQLSTATE: 42000 (ER_SP_NO_AGGREGATE) 
Mensaje: AGGREGATE is not supported for stored functions 
Error: 1461 SQLSTATE: 42000 (ER_MAX_PREPARED_STMT_COUNT_REACHED) 
Mensaje: Can't create more than max_prepared_stmt_count statements (current value: %lu) 
Error: 1462 SQLSTATE: HY000 (ER_VIEW_RECURSIVE) 
Mensaje: `%s`.`%s` contains view recursion 
Error: 1463 SQLSTATE: 42000 (ER_NON_GROUPING_FIELD_USED) 
Mensaje: non-grouping field '%s' is used in %s clause 
Error: 1464 SQLSTATE: HY000 (ER_TABLE_CANT_HANDLE_SPKEYS) 
Mensaje: The used table type doesn't support SPATIAL indexes 
Error: 1465 SQLSTATE: HY000 (ER_NO_TRIGGERS_ON_SYSTEM_SCHEMA) 
Mensaje: Triggers can not be created on system tables 
Error: 1466 SQLSTATE: HY000 (ER_REMOVED_SPACES) 
Mensaje: Leading spaces are removed from name '%s' 
Error: 1467 SQLSTATE: HY000 (ER_AUTOINC_READ_FAILED) 
Mensaje: Failed to read auto-increment value from storage engine 
Error: 1468 SQLSTATE: HY000 (ER_USERNAME) 
Mensaje: user name 
Error: 1469 SQLSTATE: HY000 (ER_HOSTNAME) 
Mensaje: host name 
Error: 1470 SQLSTATE: HY000 (ER_WRONG_STRING_LENGTH) 
Mensaje: String '%s' is too long for %s (should be no longer than %d) 
Error: 1471 SQLSTATE: HY000 (ER_NON_INSERTABLE_TABLE) 
Mensaje: The target table %s of the %s is not insertable-into 
Error: 1472 SQLSTATE: HY000 (ER_ADMIN_WRONG_MRG_TABLE) 
Mensaje: Table '%s' is differently defined or of non-MyISAM type or doesn't exist 
Error: 1473 SQLSTATE: HY000 (ER_TOO_HIGH_LEVEL_OF_NESTING_FOR_SELECT) 
Mensaje: Too high level of nesting for select 
Error: 1474 SQLSTATE: HY000 (ER_NAME_BECOMES_EMPTY) 
Mensaje: Name '%s' has become '' 
Error: 1475 SQLSTATE: HY000 (ER_AMBIGUOUS_FIELD_TERM) 
Mensaje: First character of the FIELDS TERMINATED string is ambiguous; please use non-optional and non-empty FIELDS ENCLOSED BY 
Error: 1476 SQLSTATE: HY000 (ER_LOAD_DATA_INVALID_COLUMN) 
Mensaje: Invalid column reference (%s) in LOAD DATA 
Error: 1477 SQLSTATE: HY000 (ER_LOG_PURGE_NO_FILE) 

Mensaje: Being purged log %s was not found 
Error: 1478 SQLSTATE: XA106 (ER_XA_RBTIMEOUT) 
Mensaje: XA_RBTIMEOUT: Transaction branch was rolled back: took too long 
Error: 1479 SQLSTATE: XA102 (ER_XA_RBDEADLOCK) 
Mensaje: XA_RBDEADLOCK: Transaction branch was rolled back: deadlock was detected 
Error: 1480 SQLSTATE: HY000 (ER_TOO_MANY_CONCURRENT_TRXS) 
Mensaje: Too many active concurrent transactions 
La información de error de cliente proviene de los siguientes ficheros: 
Los valores de Error y los símbolos en paréntesis se corresponden a las definiciones en el fichero fuente MySQL include/errmsg.h . 
Los valores de Mensaje se corresponden con los mensajes de error que se listan en el fichero libmysql/errmsg.c . %d y %s representan números y cadenas de caracteres, respectivamente, que se substituyen en los mensajes cuando se muestran. 
Como las actualizaciones son frecuentes, es posible que estos ficheros contengan información de error adicional que no está listada aquí. 
Error: 2000 (CR_UNKNOWN_ERROR) 
Mensaje: Unknown MySQL error 
Error: 2001 (CR_SOCKET_CREATE_ERROR) 
Mensaje: Can't create UNIX socket (%d) 
Error: 2002 (CR_CONNECTION_ERROR) 
Mensaje: Can't connect to local MySQL server through socket '%s' (%d) 
Error: 2003 (CR_CONN_HOST_ERROR) 
Mensaje: Can't connect to MySQL server on '%s' (%d) 
Error: 2004 (CR_IPSOCK_ERROR) 
Mensaje: Can't create TCP/IP socket (%d) 
Error: 2005 (CR_UNKNOWN_HOST) 
Mensaje: Unknown MySQL server host '%s' (%d) 
Error: 2006 (CR_SERVER_GONE_ERROR) 
Mensaje: MySQL server has gone away 
Error: 2007 (CR_VERSION_ERROR) 
Mensaje: Protocol mismatch; server version = %d, client version = %d 
Error: 2008 (CR_OUT_OF_MEMORY) 
Mensaje: MySQL client ran out of memory 
Error: 2009 (CR_WRONG_HOST_INFO) 
Mensaje: Wrong host info 
Error: 2010 (CR_LOCALHOST_CONNECTION) 
Mensaje: Localhost via UNIX socket 
Error: 2011 (CR_TCP_CONNECTION) 
Mensaje: %s via TCP/IP 
Error: 2012 (CR_SERVER_HANDSHAKE_ERR) 
Mensaje: Error in server handshake 
Error: 2013 (CR_SERVER_LOST) 
Mensaje: Lost connection to MySQL server during query 
Error: 2014 (CR_COMMANDS_OUT_OF_SYNC) 
Mensaje: Commands out of sync; you can't run this command now 
Error: 2015 (CR_NAMEDPIPE_CONNECTION) 
Mensaje: Named pipe: %s 
Error: 2016 (CR_NAMEDPIPEWAIT_ERROR) 
Mensaje: Can't wait for named pipe to host: %s pipe: %s (%lu) 
Error: 2017 (CR_NAMEDPIPEOPEN_ERROR) 
Mensaje: Can't open named pipe to host: %s pipe: %s (%lu) 
Error: 2018 (CR_NAMEDPIPESETSTATE_ERROR) 
Mensaje: Can't set state of named pipe to host: %s pipe: %s (%lu) 
Error: 2019 (CR_CANT_READ_CHARSET) 
Mensaje: Can't initialize character set %s (path: %s) 
Error: 2020 (CR_NET_PACKET_TOO_LARGE) 
Mensaje: Got packet bigger than 'max_allowed_packet' bytes 
Error: 2021 (CR_EMBEDDED_CONNECTION) 
Mensaje: Embedded server 
Error: 2022 (CR_PROBE_SLAVE_STATUS) 
Mensaje: Error on SHOW SLAVE STATUS: 
Error: 2023 (CR_PROBE_SLAVE_HOSTS) 
Mensaje: Error on SHOW SLAVE HOSTS: 
Error: 2024 (CR_PROBE_SLAVE_CONNECT) 
Mensaje: Error connecting to slave: 
Error: 2025 (CR_PROBE_MASTER_CONNECT) 
Mensaje: Error connecting to master: 
Error: 2026 (CR_SSL_CONNECTION_ERROR) 
Mensaje: SSL connection error 
Error: 2027 (CR_MALFORMED_PACKET) 
Mensaje: Malformed packet 
Error: 2028 (CR_WRONG_LICENSE) 
Mensaje: This client library is licensed only for use with MySQL servers having '%s' license 
Error: 2029 (CR_NULL_POINTER) 
Mensaje: Invalid use of null pointer 
Error: 2030 (CR_NO_PREPARE_STMT) 
Mensaje: Statement not prepared 
Error: 2031 (CR_PARAMS_NOT_BOUND) 
Mensaje: No data supplied for parameters in prepared statement 
Error: 2032 (CR_DATA_TRUNCATED) 
Mensaje: Data truncated 
Error: 2033 (CR_NO_PARAMETERS_EXISTS) 
Mensaje: No parameters exist in the statement 
Error: 2034 (CR_INVALID_PARAMETER_NO) 
Mensaje: Invalid parameter number 
Error: 2035 (CR_INVALID_BUFFER_USE) 
Mensaje: Can't send long data for non-string/non-binary data types (parameter: %d) 
Error: 2036 (CR_UNSUPPORTED_PARAM_TYPE) 
Mensaje: Using unsupported buffer type: %d (parameter: %d) 
Error: 2037 (CR_SHARED_MEMORY_CONNECTION) 
Mensaje: Shared memory: %s 
Error: 2038 (CR_SHARED_MEMORY_CONNECT_REQUEST_ERROR) 
Mensaje: Can't open shared memory; client could not create request event (%lu) 
Error: 2039 (CR_SHARED_MEMORY_CONNECT_ANSWER_ERROR) 
Mensaje: Can't open shared memory; no answer event received from server (%lu) 
Error: 2040 (CR_SHARED_MEMORY_CONNECT_FILE_MAP_ERROR) 
Mensaje: Can't open shared memory; server could not allocate file mapping (%lu) 
Error: 2041 (CR_SHARED_MEMORY_CONNECT_MAP_ERROR) 
Mensaje: Can't open shared memory; server could not get pointer to file mapping (%lu) 
Error: 2042 (CR_SHARED_MEMORY_FILE_MAP_ERROR) 
Mensaje: Can't open shared memory; client could not allocate file mapping (%lu) 
Error: 2043 (CR_SHARED_MEMORY_MAP_ERROR) 
Mensaje: Can't open shared memory; client could not get pointer to file mapping (%lu) 
Error: 2044 (CR_SHARED_MEMORY_EVENT_ERROR) 
Mensaje: Can't open shared memory; client could not create %s event (%lu) 
Error: 2045 (CR_SHARED_MEMORY_CONNECT_ABANDONED_ERROR) 
Mensaje: Can't open shared memory; no answer from server (%lu) 
Error: 2046 (CR_SHARED_MEMORY_CONNECT_SET_ERROR) 
Mensaje: Can't open shared memory; cannot send request event to server (%lu) 
Error: 2047 (CR_CONN_UNKNOW_PROTOCOL) 
Mensaje: Wrong or unknown protocol 
Error: 2048 (CR_INVALID_CONN_HANDLE) 
Mensaje: Invalid connection handle 
Error: 2049 (CR_SECURE_AUTH) 
Mensaje: Connection using old (pre-4.1.1) authentication protocol refused (client option 'secure_auth' enabled) 
Error: 2050 (CR_FETCH_CANCELED) 
Mensaje: Row retrieval was canceled by mysql_stmt_close() call 
Error: 2051 (CR_NO_DATA) 
Mensaje: Attempt to read column without prior row fetch 
Error: 2052 (CR_NO_STMT_METADATA) 
Mensaje: Prepared statement contains no metadata 
Error: 2053 (CR_NO_RESULT_SET) 
Mensaje: Attempt to read a row while there is no result set associated with the statement 
Error: 2054 (CR_NOT_IMPLEMENTED) 
Mensaje: This feature is not implemented yet 
Error: 2055 (CR_SERVER_LOST_EXTENDED) 
Mensaje: Lost connection to MySQL server at '%s', system error*/
?>