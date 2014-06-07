<%@ page import="java.util.Vector, org.me.sql.*" %>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<link href='common/css/estilo.css' rel="stylesheet" type="text/css">
<link href='common/css/display.css' rel="stylesheet" type="text/css">
<html>
    <head>
        <title>Registro de Usuario</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="STYLESHEET" href="common/css/musica.css" type="text/css">

        <script language="javascript">

            function valida(form) {
            	if(form.nick.value=="") {
            		alert("Debe ingresar nick")
            	}
            	if(form.password.value=="") {
          		alert("Debe ingresar password")
            	}
				if(form.nombre.value=="") {
          		alert("Debe ingresar Nombre")
            	}
           	    if(form.apellidos.value=="") {
          		alert("Debe ingresar Apellidos")
            	}
				if(form.dni.value=="") {
          		alert("Debe ingresar DNI")
            	}
				if(form.correo.value=="") {
          		alert("Debe ingresar Correo Electronico")
            	}

            }
function jsCancelar() {
    document.location.href = '<%=request.getContextPath().trim()%>/index.jsp';
}            
        </script>
    </head>

    <body onLoad="javascript:document.forms(0).nick.focus()">
        <center>
        <br><br>
        
               
        <html:form action="logRegistro">
        <html:hidden property="action" value="INS" />
        <html:hidden property="tipo" value="0" />
        <table width="60%" border="0" cellpadding="0" cellspacing="0" >
        <tr><td>
        <table width="100%" border="0" bgcolor="">
        	<th colspan="2"><div align="center" class="tituloModulo">Registro de Participante</div></th>
        	<tr>
        	<td colspan="2">
        	<fieldset>
        	<legend>Datos generales</legend>  
        	<table width="100%" border="0" cellpadding="0" cellspacing="2">
            <tr>
                <td align="left"><span class="Estilo1">Nick</span></td>
                <td align="left">
                    <html:text property="nick" size="10" maxlength="10" />
			    </td>
            </tr>
            <tr>
                <td align="left"><span class="Estilo1">Password</span></td>
                <td align="left">
                    <html:text property="password" size="10" maxlength="10" />
                </td>
            </tr>
            <tr>
                <td align="left"><span class="Estilo1">Nombres:</span></td>
                <td align="left">
                    <html:text property="nombre" size="30" maxlength="50" />
                </td>
            </tr>
            <tr>
                <td align="left"><span class="Estilo1">Apellidos:</span></td>
                <td align="left">
                    <html:text property="apellidos" size="30" maxlength="50" />
                </td>
            </tr>
            <tr>
                <td align="left"><span class="Estilo1">Dni:</span></td>
                <td align="left">
                     <html:text property="dni" size="8" maxlength="8" />
                </td>
            </tr>
			         <tr>
                <td align="left"><span class="Estilo1">Correo Electronico:</span></td>
                <td align="left">
					 <html:text property="correo" size="30" maxlength="30" />               
			    </td>
            </tr>
            </table>
            </fieldset>
            </td></tr>
        </table>
        </td>
        </tr>
            <tr>
                <td align="center" colspan="2"><br>
                    <html:submit styleClass="botones" value="A&ntilde;adir registro" onclick="valida(this.form)" />
                    <input type="button" value="Cancelar" class="botones" onclick="javascript:jsCancelar();" />
                </td>
            </tr>
        </table>
        </html:form>
    </body>
</html>
