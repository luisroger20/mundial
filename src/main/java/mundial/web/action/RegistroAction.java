/*
 * CdAction.java
 *
 * Created on 14 de mayo de 2006, 15:34
 */

package mundial.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import mundial.web.dao.*;
import mundial.web.form.*;
/**
 *
 * @author Victor Balta
 * @version
 */

public class RegistroAction extends Action {
    
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        String accion = request.getParameter("action");
        
        if (accion == null) {
            request.getSession().setAttribute("mensaje", "lleg� nulo!");
        } else {
            if (accion.compareTo("INS") == 0) {
            	String existe = RegistroDAO.selecciona((RegistroActionForm) form);
            	if (existe=="false"){
                 String mensaje = RegistroDAO.inserta((RegistroActionForm) form);
                  if (mensaje == "true") {
                    request.getSession().setAttribute("mensaje", "Exito en grabaci�n del usuario: "+request.getParameter("nick"));
                } else {
                    request.getSession().setAttribute("mensaje", mensaje);
                }
            	}else
            		request.getSession().setAttribute("mensaje", "Ya existe el usuario: "+request.getParameter("nick"));
                  
                
            } else if (accion.compareTo("DEL") == 0) {
                
                String mensaje = RegistroDAO.elimina((RegistroActionForm) form);
                
                if (mensaje == "true") {
                    request.getSession().setAttribute("mensaje", "Exito en eliminaci�n!");
                } else {
                    request.getSession().setAttribute("mensaje", mensaje);
                }
                
            } else if (accion.compareTo("UPD") == 0) {
                
                String mensaje = RegistroDAO.modifica((RegistroActionForm) form);
                
                if (mensaje == "true") {
                    request.getSession().setAttribute("mensaje", "Exito en actualizaci�n!");
                } else {
                    request.getSession().setAttribute("mensaje", mensaje);
                }
            }else if (accion.compareTo("SEL") == 0) {
                
                String mensaje = RegistroDAO.selecciona((RegistroActionForm) form);
                
                if (mensaje == "true") {
                    request.getSession().setAttribute("mensaje", "Existe el nick!");
                } else {
                    request.getSession().setAttribute("mensaje", mensaje);
                }
            }
        }

        return mapping.findForward("msg");
    }
}
