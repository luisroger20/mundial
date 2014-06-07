/*
 * RegistroDAO.java
 *
 * Created on 06 de junio de 2010
 *
 */

package mundial.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import mundial.util.UtilConexion;
import mundial.web.form.*;

/**
 *
 * @author Roger Martinez
 */
public class RegistroDAO {

   public static String inserta(RegistroActionForm registro)throws Exception {
		PreparedStatement ps = null;
		String sql = "";
		Connection con = null;
		ResultSet rs = null;
		String sw = "false";
		try {
			con = UtilConexion.getInstance().getConnection();
			   sql= "Insert into usuario(cod_usuario,nombre,clave,apellidos,dni,correo,tipo) " +
		                      "Values('" + registro.getNick().trim() + "','" + registro.getNombre() + "','" + 
		                      registro.getPassword().trim() + "','" + registro.getApellidos() + "','" + 
		                      registro.getDni() + "','"+registro.getCorreo()+"','"+
		                      registro.getTipo()+"')";	
			   ps = con.prepareStatement(sql);
			   
			   if (ps.executeUpdate() > 0)
				   sw = "true";
			   
				if (!con.getAutoCommit())
					con.commit();
		}catch(Exception e) {
			System.out.println(RegistroDAO.class.getName()+", Error="+e);
			throw e;
		} finally {
	  	 	  if (ps != null)
	  	 		ps.close();
	  	 	if(con != null)	{
				con.close();
			  }
		}
		return sw;    // retorna null si todo OK!
    }
	
    public static String elimina(RegistroActionForm registro) throws Exception	{
    	PreparedStatement ps = null;
		String sql = "";
		Connection con = null;
		ResultSet rs = null;
		String sw = "false";
		try {
			con = UtilConexion.getInstance().getConnection();
        sql= "Delete from usuario Where cod_usuario=" + registro.getNick();
        ps = con.prepareStatement(sql);
		   
		   if (ps.executeUpdate() > 0)
			   sw = "true";
		}catch(Exception e) {
			System.out.println(RegistroDAO.class.getName()+", Error="+e);
			throw e;
		}finally {
	  	 	  if (ps != null)
		  	 		ps.close();
	  	 	if(con != null)	{
					con.close();
				  }
			}

        return sw;    // retorna null si todo OK!
    }

    public static String modifica(RegistroActionForm registro) throws Exception {
    	PreparedStatement ps = null;
		String sql = "";
		Connection con = null;
		ResultSet rs = null;
		String sw = "false";
		try {
			con = UtilConexion.getInstance().getConnection();
         sql= "Update usuario set nombre='" + registro.getNick()+
       					"',clave='" + registro.getPassword() +
       					"',apellidos='" + registro.getApellidos() +
       					"',dni=" + registro.getDni() +
       					"',correo=" + registro.getCorreo() +
        				" Where cod_usuario=" + registro.getNick();
                 ps = con.prepareStatement(sql);
		   		   if (ps.executeUpdate() > 0)
			   sw = "true";
		}catch(Exception e) {
			System.out.println(RegistroDAO.class.getName()+", Error="+e);
			throw e;
		}finally {
	  	 	  if (ps != null)
		  	 		ps.close();
	  	 	if(con != null)	{
					con.close();
				  }
			}

        return sw;    // retorna null si todo OK!
    }
    
    public static String selecciona(RegistroActionForm registro) throws Exception {
    	PreparedStatement ps = null;
		String sql = "";
		String respuesta = "false";
		Connection con = null;
		ResultSet rs = null;
		String sw = "false";
		try {
			con = UtilConexion.getInstance().getConnection();
        sql= "Select * from usuario "+
        			" Where cod_usuario='" + registro.getNick().trim()+"'";
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        if (rs.next()) {
        	respuesta = rs.getString(1);
        }
		}catch(Exception e) {
			System.out.println(RegistroDAO.class.getName()+", Error="+e);
			throw e;
		}finally {
			if(con != null)
				try {
					con.close();
				} catch(Exception e) {
	            	e.printStackTrace();
				}
			}
        
        return respuesta;    // retorna null si todo OK!
    }
}
