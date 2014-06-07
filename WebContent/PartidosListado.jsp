<%@ page contentType="text/html; charset=Cp1252" %>
<%@ taglib uri="/WEB-INF/tld/displaytag/displaytag-11.tld" prefix="display" %>
<link href='common/css/estilo.css' rel="stylesheet" type="text/css">
<link href='common/css/display.css' rel="stylesheet" type="text/css">
<%@ taglib uri="/WEB-INF/tld/struts/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tld/struts/struts-bean.tld" prefix="bean" %>
<link rel="stylesheet" type="text/css" media="screen" href="../../seguridad/a/css/menu/menu.css">    
<script>
function jsApostar(cod_partido)  {
	var formx = document.forms[0];
	formx.action.value = 'apostar';
	formx.cod_partido.value = cod_partido;
	formx.submit();		
}

function jsConsolidado(cod_partido)  {
	var formx = document.forms[0];
	formx.action.value = 'consolidado';
	formx.cod_partido.value = cod_partido;
	formx.submit();		
}

function jsActualizar()  {
	var formx = document.forms[0];
	formx.action.value = 'actualizarLista';
	formx.submit();		
}
</script>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=Cp1252"/>
		<title></title>
	</head>
	<body  >
		<div class="form-text">
		<table width="100%" height="430" border="0" cellpadding="0" cellspacing="0" >
			<tr>
				<td width="0.5%" height="0" ></td>
				<td width="99%" height="0" ></td>
				<td width="0.5%" height="0" ></td>
			</tr>
		  	<tr>
		    	<td height="400" ></td>
			 	<td valign="top">
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="EdicionTabla">
		  				<tr height="5">
					  		<td width="1%"></td>
					  		<td width="98%"></td>
					  		<td width="1%"></td>
						</tr>
		  				<tr>
					  		<td></td>
					  		<th><div align="center" class="tituloModulo">Listado de Partidos</div></th>
					  		<td></td>
						</tr>
					</table>
					<form method="POST" action="<%=request.getContextPath().trim()%>/inicioServlet" name="form1">					
					<input type="hidden" name="action">
					<input type="hidden" name="valorIndice" value="">					
					<input type="hidden" name="cod_partido">				
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="EdicionTabla">					 
					  	<!-- RESULTADO DE BUSQUEDA -->	
					  	<tr>
					  		<td width="1%"></td>
					  		<td width="98%"></td>
					  		<td width="1%"></td>					  	
				  		</tr>
		  				<tr>
					  		<td></td>
					  		<th><div align="center" class="tituloModulo">&nbsp;</div></th>
					  		<td></td>
						</tr>
						<tr height="5">
							<td colspan="3" align="center">
								<input type="button" name="btnActualizar" value="Actualizar" class="botones" onclick="javascript:jsActualizar();">							
							</td>
						</tr>				  		
					 	<tr>
					    	<td >&nbsp;</td>
						  	<td valign="top" align="center">	
								<fieldset>
								<legend align="left">Resultado de la Busqueda</legend>  						  	
									<table width="100%" border="0" cellspacing="0" cellpadding="0" >
							        	<tr>
									       	<td width="0.5%" class="busqueda">&nbsp;</td>
									       	<td width="99%" class="busqueda"></td>
									       	<td width="0.5%" class="busqueda">&nbsp;</td>
									   	</tr>
									   	<tr>
								        	<td height="250" class="busqueda">&nbsp;</td>
								        	<td valign="top" class="normal">
										   		<table width="100%" border="0" cellspacing="1" cellpadding="0" class="stgrid">
										   			<thead>
														<tr >
															<th width="15%" align="center" rowspan="2">Grupo / Fase</th>	
															<th  width="10%" align="center" colspan="2">Partido</th>															
															<th  width="10%" align="center" rowspan="2">Fecha</th>
															<th  width="10%" align="center" colspan="2">Apuesta (S/.)</th>
															<th  width="10%" align="center" rowspan="2">&nbsp;</th>
															<th  width="10%" align="center" rowspan="2">&nbsp;</th>
														</tr>						   				
														<tr >
															<th  width="10%" align="center">&nbsp;</th>
															<th  width="10%" align="center">&nbsp;</th>
															<th  width="10%" align="center">Valor</th>
															<th  width="10%" align="center">Acumulado</th>															
														</tr>						   				
										   			</thead>
										   			<%int i=0; %>
										   			<logic:notEmpty name="ListaPartido"> 
											   			<logic:iterate id="fila" name="ListaPartido" >
															<tbody>
																<tr class="<%=(i % 2 == 0)?"odd":"even"%>">
																	<td align="center">
																		<bean:write name="fila" property="grupo"/>
																	</td>
																	<td align="center">
																		<bean:write name="fila" property="pais1"/>
																
																		
																															
																	</td>
																	<td align="center">
																		<bean:write name="fila" property="pais2"/>
																		
																	</td>
																	<td align="center">
																		<bean:write name="fila" property="fecha_parti" format="dd/MM/yyyy HH:mm"/>
																	</td>		
																	<td align="center">
																		<bean:write name="fila" property="valor_soles" format="###"/>
																	</td>
																	<td align="center">
																		<bean:write name="fila" property="acumulado" format="###"/>
																	</td>
																	<td align="center">		
																		<input type="button" name="btnApostar" value="Incluir Score" class="botones" <logic:equal name="fila" property="flag_apostar" value="0">disabled</logic:equal> onclick="javascript:jsApostar('<bean:write name="fila" property="cod_partido"/>');">
																	</td>
																	<td align="center">
																		<input type="button" name="btnConsolidado" value="Consolidado" class="botones" onclick="javascript:jsConsolidado('<bean:write name="fila" property="cod_partido"/>');">
																	</td>	
																</tr>
															</tbody>		
															<%i++; %>					   			
											   			</logic:iterate>
										   			</logic:notEmpty>
						         					<logic:empty name="ListaPartido" >
						         						<table class='tabla_normal' width="100%" align="center">
						         							<tr>
						         								<td>No existen registros</td>
						         							</tr>
								    					</table>
								    				</logic:empty>  											   			
										   		</table>	
						       		 		</td>
	       									<td ></td> 
						      			</tr>
							   		</table>	
					   			</fieldset>				  	
				  	  		</td>
					      	<td>&nbsp;</td>
				  		</tr>  
			  			<!-- FINAL DE LISTADO -->		
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