/*
 * CdActionForm.java
 *
 * Created on 14 de mayo de 2006, 15:29
 */

package mundial.web.form;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author Victor Balta
 * @version
 */

public class RegistroActionForm extends org.apache.struts.action.ActionForm {
    
    private String nick;
    private String password;
    private String nombre;
    private String apellidos;
    private String dni;
    private String correo;
    private String tipo;
    
    public String getNick() {
        return  nick;
    }
    
    public void setNick(String nick) {
        this.nick = nick;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }
    public void  setNombre(String nombre) {
    	this.nombre = nombre;
    }
    
    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    
	public String getDni() {
        return dni;
    }
    
    public void setDni(String dni) {
        this.dni = dni;
    }
    
    public String getCorreo() {
        return correo;
    }


    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    public String getTipo() {
        return tipo;
    }


    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    
    public RegistroActionForm() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        if (getNick() == null || getNick().length() < 1) {
            errors.add("nick", new ActionMessage("error.usuario.requerido"));
            // TODO: add 'error.name.required' key to your resources
        }
        return errors;
    }
}
