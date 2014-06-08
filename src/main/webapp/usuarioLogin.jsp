<%@ taglib uri="/WEB-INF/tld/struts/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/struts/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts/struts-logic.tld" prefix="logic" %>

<html>
<link href='common/css/estilo.css' rel="stylesheet" type="text/css">
<link href='common/css/display.css' rel="stylesheet" type="text/css">
<head>
    <title><bean:message key="usuario.menu"/></title>
</head>
<script language="JavaScript">
	
function valida(form) {
	if(form.nick.value=="") {
		alert("Debe ingresar nick")
			}
	if(form.password.value=="") {
		alert("Debe ingresar password")
	
	}
}

	
		function limpiar(form){
			form.nick.value="";
				form.password.value="";
		   
		}
		
	</script>
<body onLoad="javascript:document.forms(0).nick.focus()" >
	<center>
       <html:form action="login">
    	<html:hidden property="action" value="ACTIVO" />
        <table border="0" cellpadding="2" cellspacing="2" width="%20" bordercolor="#721B00">
        		<tr>
            	<td colspan="2" align="center"><h3><bean:message key="usuario.mundial"/></h3></td>
            </tr>
         	<tr>
            	<td colspan="2" align="center"><html:img src="images/mundial.gif" /></td>
            </tr>
            <tr>
            	<td colspan="2" align="center">
            		&nbsp;
            	</td>
            </tr>
            <tr>
            	<td colspan="2" align="center">
            		<A HREF="registro.jsp"><bean:message key="usuario.registro" /></A>
            	</td>
            </tr>
            <tr>
            	<td><bean:message key="usuario.nick" /></td>
            	<td><html:text property="nick" size="10" maxlength="10" value="" /></td>
            </tr>
            <tr>
            	<td><bean:message key="usuario.password" /></td>
            	<td><html:password property="password" size="10" maxlength="10" value="" /></td>
            </tr>
            <tr>
            	<td colspan="2">
            		<html:submit styleClass="botones" value="Acceso al Sistema" onclick="valida(this.form)" />
					<input type="button" class="botones" property="Limpiar" value="Limpiar" onclick="limpiar(this.form)" />
				</td>
            </tr>
             </table>
    </html:form>
    
    	<form name="form1" >
    	<tr>
		<td colspan="1"><A HREF="http://www.slideshare.net/pollamundial2010/las-10-reglas-de-apollame" TARGET="reglas">Reglas y Condiciones</A></td>
	</tr>
	</form>
</body>
</html>