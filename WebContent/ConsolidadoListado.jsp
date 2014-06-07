<%@ page contentType="text/html; charset=Cp1252" %>
<%@ taglib uri="/WEB-INF/tld/displaytag/displaytag-11.tld" prefix="display" %>
<link href='common/css/estilo.css' rel="stylesheet" type="text/css">
<link href='common/css/display.css' rel="stylesheet" type="text/css">
<%@ taglib uri="/WEB-INF/tld/struts/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tld/struts/struts-bean.tld" prefix="bean" %>
<link rel="stylesheet" type="text/css" media="screen" href="../../seguridad/a/css/menu/menu.css">    
<script>
function jsCancelar() {
    document.location.href = '<%=request.getContextPath().trim()%>/PartidosListado.jsp';
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
					  		<th><div align="center" class="tituloModulo">Consolidado Score</div></th>
					  		<td></td>
						</tr>
						<tr height="5">
							<td colspan="3"></td>
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
															<th width="15%" align="center" rowspan="2">Participante</th>	
															<logic:equal name="usuario" property="tipo" value="1">
																<th width="5%" align="center" rowspan="2">Cancelo</th>
															</logic:equal>
															<th  width="10%" align="center" colspan="2">Score</th>															
															<th  width="30%" align="center" rowspan="2">Comentarios</th>
														</tr>						   				
														<tr >
															<th width="10%" align="center" ><bean:write name="partidoBean" property="pais1"/></th>															
															<th width="10%"  align="center" ><bean:write name="partidoBean" property="pais2"/></th>
														</tr>						   				
										   			</thead>
										   			<%int i=0; %>
										   			<logic:notEmpty name="ListaApuesta"> 
											   			<logic:iterate id="fila" name="ListaApuesta" >
															<tbody>
																<tr class="<%=(i % 2 == 0)?"odd":"even"%>">
																	<td align="center">
																		<bean:write name="fila" property="cod_usuario"/> - <bean:write name="fila" property="usuario.nombre"/>
																	</td>
																	<logic:equal name="usuario" property="tipo" value="1">
																		<td align="center">
																			<input type="checkbox" <logic:equal name="fila" property="flag_pago" value="1">checked</logic:equal> >
																		</td>
																	</logic:equal>
																	<td align="center">
																		<bean:write name="fila" property="score_1"/>
																	</td>
																	<td align="center">
																		<bean:write name="fila" property="score_2"/>
																	</td>
																	<td align="center">
																		<bean:write name="fila" property="descripcion"/>
																	</td>		
																</tr>
															</tbody>		
															<%i++; %>					   			
											   			</logic:iterate>
										   			</logic:notEmpty>
						         					<logic:empty name="ListaApuesta" >
						         						<table class='tabla_normal' width="100%" align="center">
						         							<tr>
						         								<td colspan="4">No existen registros</td>
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
				  		<tr>
				  			<td>&nbsp;</td>
				  			<td align="center">
				  				<logic:equal name="usuario" property="tipo" value="1">
				  					<input type="button" name="btnGrabar" value="Grabar" class="botones" onclick="javascript:jsCancelar();">
				  				</logic:equal>
								<input type="button" name="btnCancelar" value="Regresar" class="botones" onclick="javascript:jsCancelar();">				  				
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