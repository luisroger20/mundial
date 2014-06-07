function jsDeshabilitaObjetos(form, valor){
	var fields = form.elements;
	
	for(i=0;i<fields.length;i++){
		var field = fields[i];
		if(field.type != 'hidden')
			field.disabled = valor;
	}
}
function jsSubmit(formx, method){
	formx.action.value = method;	
	jsDeshabilitaObjetos(formx, false);	
	formx.submit();
	jsDeshabilitaObjetos(formx, true);		
}
var popUpWindow=0;
function jsPopUpWindow(URLStr, left, top, width, height){ 
	if(popUpWindow){
  		if(!popUpWindow.closed) popUpWindow.close();
  	} 
  	popUpWindow = open(URLStr, 'popUpWindow', 'toolbar=no,location=no,directories=no,status=yes,menubar=no,scrollbar=no,resizable=no,copyhistory=yes,dependent=yes,width='+width+',height='+height+',left='+left+', top='+top+',screenX='+left+',screenY='+top+'');
}
function jsToProperCase(str) { 
	return str.toLowerCase().replace( 
	/\w+/g,function(s){ return s.charAt(0).toUpperCase() + s.substr(1); } 
	) 
} 
function jsValidaTamanio(obj, max){
	var num_caracteres = obj.value.length;

	if (num_caracteres <= max){
		contenido_text = obj.value;
	}else{
		if(contenido_text != '')
			obj.value = contenido_text;
		else
			obj.value = obj.value.substring(0,max);
	}
}
function jsIndiceRadioSeleccion(indiceRadio){
	if (indiceRadio != undefined){
		if (indiceRadio.length != undefined){
			for(i=0; i<indiceRadio.length; i++){
				if (indiceRadio[i].checked ){
					return i;
				}
			}
		}else if(indiceRadio.checked){
			return 0;
		}
	}
	return -1;
}
function jsValorIndiceRadio(indiceRadio){
	if (indiceRadio != undefined){
		if (indiceRadio.length != undefined){
			for(i=0; i<indiceRadio.length; i++){
				if (indiceRadio[i].checked ){
					return indiceRadio[i].value;
				}
			}
		}else if(indiceRadio.checked){
			return indiceRadio.value;
		}
	}
	return -1;
}
function jsAccionBoton(formx, campoClave, method){
	var valorIndice = 0;
	valorIndice = jsValorIndiceRadio(formx.indiceRadio);
	if (valorIndice != -1){
		campoClave.value = valorIndice;
		jsSubmit(formx, method);
	}else{
		alert("Debe Seleccionar un Registro1"); 
	}
}
function jsAccionBotonIframe(formx, indiceRadio, campoClave, method){
	var valorIndice = 0;
	valorIndice = jsValorIndiceRadio(indiceRadio);
	if (valorIndice != -1){
		campoClave.value = valorIndice;
		jsSubmit(formx, method);
	}else{
		alert("Debe Seleccionar un Registro"); 
	}
}
function jsIndiceRadio(formx){
	if(formx.indiceRadio != undefined){	
		if (formx.valorIndice.value != ''){
			if(formx.indiceRadio != undefined){
				if (formx.indiceRadio.length != undefined){
					formx.indiceRadio[0].checked = true;
					for(i=0; i<formx.indiceRadio.length; i++){
						if(formx.indiceRadio[i].value == formx.valorIndice.value){
							formx.indiceRadio[i].checked = true;
						}
					}
				}else{
					formx.indiceRadio.checked = true;
				}
			}
		}else{
			if(formx.indiceRadio != undefined){
				if (formx.indiceRadio.length != undefined){
					formx.indiceRadio[0].checked = true;
				}else{
					formx.indiceRadio.checked = true;
				}
			}
		}
	}
	
	if(formx.indiceRadio == undefined){
		var fields = formx.elements;
		for(i=0;i<fields.length;i++){
			var field = fields[i];
			if(field.type == 'button' && (field.name != 'btnNuevo' && field.name != 'btnBuscar' && field.name != 'btnCancelar'))
				field.disabled = true;
		}	
	}
}
function Trim(s){
 
    if (s!=null){
	    while ((s.substring(0,1) == ' ') || (s.substring(0,1) == '\n') || (s.substring(0,1) == '\r')){
			s = s.substring(1,s.length);
		}
		while ((s.substring(s.length-1,s.length) == ' ') || (s.substring(s.length-1,s.length) == '\n') || (s.substring(s.length-1,s.length) == '\r')){
			s = s.substring(0,s.length-1);
		}
    } 
	
	return s;
}
function jsNumeroEntero(object,campo,bool){
	object.value=Trim(object.value);
	cad=object.value;
	if (cad==''){
	  if(bool){
	  	alert(campo+' es obligatorio');
	  	if(!object.disabled){
	  		object.focus();
	  	}
		return false;
	  }
	  else
	  	return true;
	}
	strValorOk=/^[0-9]+$/
	if(!strValorOk.test(cad)){
		alert('Sólo se permite valores Numéricos en '+campo);
		if(!object.disabled){
		    object.select();
	  	 	object.focus();		
  	 	}
		return false;
	}
	else 
		return true;
}
function jsFecha(object,campo,bool){
	object.value=Trim(object.value);
	caja = object.value;
	if (caja==''){
	  if(bool){
	  	alert('Fecha de '+campo+' es obligatorio');
	  	object.focus();
		return false;
	  }
	  else
	  	return true;
	}
	if(caja){
		borrar = caja;
		if((caja.substr(2,1) == "/") && (caja.substr(5,1) == "/")){
			for(i=0; i<10; i++){
				if(((caja.substr(i,1)<"0") || (caja.substr(i,1)>"9")) && (i != 2) && (i != 5)){
					borrar = '';
					break;
				}
			}
			if(borrar){
				a = caja.substr(6,4);
				m = caja.substr(3,2);
				d = caja.substr(0,2);
				if((a < 1900) || (a > 2050) || (m < 1) || (m > 12) || (d < 1) || (d > 31))
					borrar = '';
				else{
					if((a%4 != 0) && (m == 2) && (d > 28))
						borrar = ''; // Año no viciesto y es febrero y el dia es mayor a 28
					else{
						if ((((m == 4) || (m == 6) || (m == 9) || (m==11)) && (d>30)) || ((m==2) && (d>29)))
							borrar = '';
					}
				}
			}
		}
		else
			borrar = '';
		if (borrar == ''){
			alert('Formato de Fecha de '+campo+' no válido');
		    object.select();
	  	 	object.focus();
			return false;
		}
		else{
			return true;
		}
	}
}
function jsTexto(object,campo,bool){
	object.value=Trim(object.value);
	cad=object.value;
	if (cad==''){
	  if(bool){
	  	alert(campo+' es obligatorio');
	  	object.focus();
		return false;
	  }
	  else
	  	return true;
	}
	strValorOk=/^([a-z]|[A-Z]|á|é|í|ó|ú|ñ|Ñ|[.]|[ ]|[-]|["'"])+$/
	if(!strValorOk.test(cad)){
		alert('Sólo se permite valores Textuales en '+campo);
	    object.select();
  	 	object.focus();
		return false;
	}
	return true;
}
function jsTextoNumero(object,campo,bool){
	object.value=Trim(object.value);
	cad=object.value;
	if (cad==''){
	  if(bool){
	  	alert(campo+' es obligatorio');
	  	if(!object.disabled){
	  		object.focus();
	  	}
		return false;
	  }
	  else
	  	return true;
	}
	strValorOk=/^([a-z]|[A-Z]|á|é|í|ó|ú|ñ|Ñ|@|[.]|[,]|[ ]|[/]|[(]|[)]|[0-9]|[-]|["'"]|[&])+$/
	if(!strValorOk.test(cad)){
		alert('Sólo se permite valores Alfanuméricos en '+campo);
		if(!object.disabled){
		    object.select();
	  	 	object.focus();
  	 	}
		return false;
	}
	
	return true;
}
function jsSelect(object,campo,bool){
	cad=object.value;
	if (cad==''){
		if(bool){
	  		alert(campo+' es obligatorio');
	  		if(!object.disabled){
	  			object.focus();
	  		}
			return false;
	  	}else{
			return true;
		}
	}else{
		return true;
	}
}
function jsValidaHora(strHora){
	if (jsLongitudCorrecta(strHora, 5)) {
		strHora += ":00";
	}
	return !(!(/[0-2][0-9]:[0-5][0-9]:[0-5][0-9]/.test(strHora)) || (strHora.substring(0,2)<0 || strHora.substring(0,2)>23));
}
function jsLongitudCorrecta( campo, len ){
  if ( campo != null ) return ( campo.length == len );
  else return false;
}
function jsDescargaArchivo(contexto, archivo) {
	var URLStr = contexto +  '/descargaAlias' + '?archivo=' + archivo;
	popUpWindow = open(URLStr, 'popUpWindow', 'toolbar=yes,location=no,directories=no,status=yes,menubar=yes,scrollbar=no,resizable=yes,copyhistory=yes,dependent=yes,width='+'800'+',height='+'500'+',left='+'100'+', top='+'20'+',screenX='+'100'+',screenY='+'100'+'');	
}
function jsCamposFormulario(form){
	var fields = form.elements;
	var campos = '';
	for(i=0;i<fields.length;i++){
		var field = fields[i];
		if((field.type == 'text' || field.type == 'select-one' || field.type == 'hidden') && field.name != 'method'){
			campos = campos + '&' + field.name + '=' + field.value;
		}
	}
	
	return campos;
}
function jsCargarReporte(action, formx, method) {
	if(action == ''){
		formx.method.value = method;
		formx.target = '_blanck';
		formx.submit();
	}else{	
		if(popUpWindow){
	  		if(!popUpWindow.closed) popUpWindow.close();
	  	} 	
		action = action + '?method=' + method + jsCamposFormulario(formx);
		open(action, 'popUpWindow', 'toolbar=yes,location=no,directories=no,status=yes,menubar=yes,scrollbar=no,resizable=yes,copyhistory=yes,dependent=yes,width='+'800'+',height='+'500'+',left='+'100'+', top='+'20'+',screenX='+'100'+',screenY='+'100'+'');	
	}
}

function jsValorDecimalSinComas(control){
	control.value = control.value.replace(/,/g,"");
	return;
}