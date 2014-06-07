 package mundial.dao;
 import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import mundial.servlet.InicioServlet;
import mundial.util.UtilConexion;
import mundial.web.form.*;

//import com.sql.Sql;	// biblioteca implementada en el futuro

public class UsuarioDAO {
	public String insertar(UsuarioActionForm usuarioForm) {
			String sql= "Insert into usuario(...";

		return sql;
	}
	
	public String eliminar(UsuarioActionForm usuarioForm)	{
		String sql= "Delete from usuario Where idUsuario=..";

		return sql;
	}
	
	public String modificar(UsuarioActionForm usuarioForm) {
		String sql= "Update usuario set ...";

		return sql;
	}
	
	public String getUsuario(UsuarioActionForm usuarioForm)throws Exception {
		//String[] fila= Sql.getFila("Select * From usuario Where idUsuario= ...");
		  		PreparedStatement ps = null;
				String sql = "";
				String respuesta = "false";
				Connection con = null;
				ResultSet rs = null;
				
				try {
					con = UtilConexion.getInstance().getConnection();
		        sql= "Select * from usuario "+
		        			" Where cod_usuario='" + usuarioForm.getNick()+
		                    "' And clave='"+usuarioForm.getPassword()+"'";
		        ps = con.prepareStatement(sql);
		        rs = ps.executeQuery();
		        if (rs.next()) {
		        	respuesta = "success";
		        }	
				}catch(Exception e) {
					System.out.println(UsuarioDAO.class.getName()+", Error="+e);
					throw e;
				}finally {
					if(con != null)
						try {
							con.close();
						} catch(Exception e) {
			            	e.printStackTrace();
						}
					}
       
		
		return respuesta;
	}
	
	public String getUsuario(String nick, String clave)throws Exception {
		//String[] fila= Sql.getFila("Select * From usuario Where idUsuario= ...");
		  		PreparedStatement ps = null;
				String sql = "";
				String respuesta = "false";
				Connection con = null;
				ResultSet rs = null;
				
				try {
					con = UtilConexion.getInstance().getConnection();
		        sql= "Select * from usuario "+
		        			" Where cod_usuario='" + nick+
		                    "' And clave='"+clave+"'";
		        ps = con.prepareStatement(sql);
		        rs = ps.executeQuery();
		        if (rs.next()) {
		        	respuesta = "success";
		        }	
				}catch(Exception e) {
					System.out.println(UsuarioDAO.class.getName()+", Error="+e);
					throw e;
				}finally {
					if(con != null)
						try {
							con.close();
						} catch(Exception e) {
			            	e.printStackTrace();
						}
					}
       
		
		return respuesta;
	}	
}
