<%@ page contentType="text/html; charset=Cp1252" %>
<%@ taglib uri="/WEB-INF/tld/struts/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tld/struts/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/displaytag/displaytag-11.tld" prefix="display" %>
<link href='common/css/estilo.css' rel="stylesheet" type="text/css">
<link href='common/css/display.css' rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" media="screen" href="../../seguridad/a/css/menu/menu.css">    
<script language="JavaScript" src='common/js/util.js' ></script>
<script>
function jsGrabar()  {
	if(!jsValidar()){
		return;
	}
	
	if (confirm("Confirme que desea registrar los datos")) {
		var formx = document.forms[0];
		jsSubmit(formx, 'grabarApuesta');		
	}
}
function jsValidar(){
	var formx = document.forms[0];

	if(!jsSelect(formx.cod_usuario, 'Participante', true)){
		return false;
	}
	
	if(!jsNumeroEntero(formx.score_1, 'Score', true)){
		return false;
	}	
	
	if(!jsNumeroEntero(formx.score_2, 'Score', true)){
		return false;		
	}
	
	return true;
}
function jsCancelar() {
    document.location.href = '<%=request.getContextPath().trim()%>/PartidosListado.jsp';
}
function jsInicio(){
	var formx = document.forms[0];
	
	<logic:equal name="usuario" property="tipo" value="0">
		formx.cod_usuario.value = '<bean:write name="usuario" property="cod_usuario"/>';
		//formx.cod_usuario.disabled = true;
	</logic:equal>
}
</script>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=Cp1252"/>
		<title></title>
	</head>
	<body onload="jsInicio();" >
		<div class="form-text">
		<table width="100%" height="430" border="0" cellpadding="0" cellspacing="0" >
			<tr>
				<td width="0.5%" height="0" ></td>
				<td width="99%" height="0" ></td>
				<td width="0.5%" height="0" ></td>
			</tr>
		  	<tr>
		    	<td ></td>
			 	<td valign="top">
					<form method="POST" action="<%=request.getContextPath().trim()%>/inicioServlet" styleId="form1">
					<input type="hidden" name="action">
					<input type="hidden" name="cod_partido" value="<bean:write name="partidoBean" property="cod_partido"/>">
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="EdicionTabla">
		  				<tr height="5">
					  		<td width="1%"></td>
					  		<td width="98%"></td>
					  		<td width="1%"></td>
						</tr>
		  				<tr>
					  		<td></td>
					  		<th><div align="center" class="tituloModulo">Registro de Score</div></th>
					  		<td></td>
						</tr>
						<tr height="5">
							<td colspan="3"></td>
						</tr>
						<tr>
							<td></td>
				      		<td >
  							    <!--  Comienza  Cuerpo Zona de busqueda-->
								<fieldset>
									<legend>Datos generales</legend>  		
									<table width="100%" border="0" cellpadding="0" cellspacing="2">
						  				<tr>
									  		<td width="0.5%"></td>
									  		<td width="99%"></td>
									  		<td width="0.5%"></td>
										</tr>
										<tr>
											<td></td>
											<td>
												<table width="100%" border="0" cellpadding="2" cellspacing="2" class="EdicionTabla" id="filtros">
						        					<tr>
						        						<td width="15%" height="0"></td>
						        						<td width="35%" height="0"></td>
						        						<td width="15%" height="0"></td>
						        						<td width="35%" height="0"></td>
						        					</tr>
										        	<tr>
				                                       	<td class="normal"><b>Participante</b></td>
				                                       	<td colspan="3" class="normal">
															<select name='cod_usuario'>
																<option value="">--Seleccionar--</option>
																<logic:iterate name="listaUsuario" id="usuario">
																	<option value="<bean:write name="usuario" property="cod_usuario"/>" >
																		<bean:write name="usuario" property="nombre"/>
																	</option>
																</logic:iterate>
															</select>
				                                       	</td>
									              	</tr>
										        	<tr>
				                                       	<td class="normal"><b>Score</b></td>
				                                       	<td colspan="3" class="normal">
				                                       		<b><bean:write name="partidoBean" property="pais1"/></b>&nbsp;
				                                       		<input name="score_1" size="2" maxlength="2" value="<bean:write name="apuestaBean" property="score_1"/>" <logic:equal name="apuestaBean" property="flagIngreso" value="1"><logic:equal name="usuario" property="tipo" value="0">disabled</logic:equal></logic:equal> >
				                                       		&nbsp;&nbsp;-&nbsp;&nbsp;
				                                       		<b><bean:write name="partidoBean" property="pais2"/></b>&nbsp;
				                                       		<input name="score_2" size="2" maxlength="2" value="<bean:write name="apuestaBean" property="score_2"/>" <logic:equal name="apuestaBean" property="flagIngreso" value="1"><logic:equal name="usuario" property="tipo" value="0">disabled</logic:equal></logic:equal> >
				                                       	</td>
									              	</tr>										              											        	
										        	<tr>
				                                       	<td class="normal"><b>Comentarios</b></td>
				                                       	<td colspan="3" class="normal">
															<textarea rows="4" cols="50" name="descripcion" onkeypress="jsValidaTamanio(this,200);" onkeyup="jsValidaTamanio(this,200);"><bean:write name="apuestaBean" property="descripcion"/></textarea>
				                                       	</td>
									              	</tr>
												</table>											
											</td>
											<td></td>
										</tr>
									</table>					    
								</fieldset>
								<!--  Termina Cuerpo -->						   
						   	</td>
			  		 		<td>&nbsp;</td>
			  			</tr>
			  			<!--  Botonería -->
			  		 	<tr>
			  		 		<td></td>
					    	<td align="center" height="25">
					    		<input type="button" name="btnGrabar" value="Grabar" class="botones" onclick="javascript:jsGrabar();">
					    		<input type="button" name="btnCancelar" value="Cancelar" class="botones" onclick="javascript:jsCancelar();">
					    	</td>
					    	<td></td>
					 	</tr> 
					</table>
					</form>
		   		</td>
				<td width="0.5%" ></td>
		 	</tr>
		 	<tr><!-- marco horizontal abajo -->
      			<td colspan="3" height="5" ></td>
	    	</tr>
	  	</table>
	  	</div>
	</body>
</html>