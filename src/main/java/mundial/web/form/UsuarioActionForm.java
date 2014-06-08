package mundial.web.form;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.*;

public class UsuarioActionForm extends ActionForm {

    private String nick;
    private String password;

	public String getNick() {
        return nick;
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
}

