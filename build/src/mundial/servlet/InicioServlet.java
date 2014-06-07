package mundial.servlet;

import mundial.util.UtilConexion;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import mundial.bean.Partido;
import mundial.bean.Usuario;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InicioServlet extends HttpServlet implements Servlet{
	
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
	}	
	
	/**
	 * Standard servlet doGet method.
	 * 
	 * @param req the servlet request
	 * @param res the servlet response
	 * @throws ServletException
	 * @throws IOException
	 * @throws BusinessException 
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Variables de identificación de usuario
		String login = null;
		String nombre = null;
		String tipoUsuario = null;
		String codigoUsuarioModulo = null;
		List listaPartidos = new ArrayList();
		try{
			//Obtiene los parámetros de usuario
			login = "0100";
			nombre = "Usuario de Prueba";

			Usuario usuario = new Usuario();

			request.getSession().setAttribute("USER", usuario);
			
			listaPartidos = consultarListaPartido();			
			request.getSession().setAttribute("ListaPartido", listaPartidos);
			
			response.sendRedirect("PartidosListado.jsp");
			
		}catch(Exception e) {
			 System.out.println(InicioServlet.class.getName()+", Error="+e);
		}
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		String action = req.getParameter("action");
		try{
			if(action.equals("apostar")){
				apostar(req, resp);
			}else if(action.equals("consolidado")){
				
			}
		}catch (Exception e) {
			System.out.println(InicioServlet.class.getName()+", Error="+e);
		}
	}	
		
	private void apostar(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			List listaUsuario = this.consultarUsuario();
			request.getSession().setAttribute("listaUsuario", listaUsuario);
			response.sendRedirect("Apuesta.jsp");
		}catch (Exception e) {
			System.out.println(InicioServlet.class.getName()+", Error="+e);
			throw e;
		}
	}
	
	private List consultarListaPartido() throws Exception{
		List lista = new ArrayList();
		Connection con = UtilConexion.getInstance().getConnection();
		PreparedStatement ps = null;
		String sql = "SELECT P.COD_PARTIDO, F.NOMBRE AS GRUPO, P1.NOMBRE AS PAIS1, P2.NOMBRE AS PAIS2, P.FECHA " +
					 "FROM    PARTIDO P " +
					 "INNER JOIN FASE F ON (F.COD_FASE = P.COD_FASE) " +
					 "INNER JOIN PAIS P1 ON (P1.COD_PAIS = P.COD_PAIS1) " +
					 "INNER JOIN PAIS P2 ON (P2.COD_PAIS = P.COD_PAIS2)";
		try{
			ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Partido partido = new Partido();

				partido.setCod_partido(Integer.valueOf(rs.getString("cod_partido")));
				partido.setGrupo(rs.getString("grupo"));
				partido.setFecha(rs.getString("fecha"));
				partido.setPais1(rs.getString("pais1"));
				partido.setPais2(rs.getString("pais2"));
				
				lista.add(partido);
			}

			con.close();
			ps.close();				
			rs.close();
		}catch (Exception e) {
			System.out.println(InicioServlet.class.getName()+", Error="+e);
			throw e;
		}
		
		return lista;
	}
	
	private List consultarUsuario() throws Exception{
		List lista = new ArrayList();
		Connection con = UtilConexion.getInstance().getConnection();
		PreparedStatement ps = null;
		String sql = "SELECT COD_USUARIO, NOMBRE " +
					 "FROM   USUARIO ";
		try{
			ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Usuario usuario = new Usuario();

				usuario.setCod_usuario(rs.getString("cod_usuario"));
				usuario.setNombre(rs.getString("nombre"));
				
				lista.add(usuario);
			}

			con.close();
			ps.close();				
			rs.close();
		}catch (Exception e) {
			System.out.println(InicioServlet.class.getName()+", Error="+e);
			throw e;
		}
		
		return lista;
	}
}