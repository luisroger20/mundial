package mundial.web.action;

import mundial.web.dao.*;
import mundial.web.form.*;
import javax.servlet.http.*;
import org.apache.struts.action.*;

public class UsuarioAction extends Action {
    
    /* forward name="success" path="" */
    private  String SUCCESS = "success";
    
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        String accion = request.getParameter("action");
        //if(accion == null)
        	//accion = "ACTIVO";
        
        if (accion.compareTo("ACTIVO") == 0 ) {
        	
        	UsuarioDAO usuarioDAO= new UsuarioDAO();
        	
        	SUCCESS = usuarioDAO.getUsuario((UsuarioActionForm) form); // asumiendo que tiene ID==10
        	
        	if (SUCCESS== "false") {
                    request.getSession().setAttribute("mensaje", "Clave Incorrecta o no Existe el Usuario");
                    		
        	}
        } else if (accion.compareTo("INS") == 0) {
        	
        	// sentencias para insertar un nuevo usuario	
        	
        } else if (accion.compareTo("DEL") == 0) {
        	
        	// sentencias para dar de baja a un usuario
        	
        } else if (accion.compareTo("UPD") == 0) {
        	
        	// sentencias para actualizar datos de usuario
        }
        
        return mapping.findForward(SUCCESS);
    }
}
