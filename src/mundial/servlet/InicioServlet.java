package mundial.servlet;

import mundial.util.UtilConexion;
import mundial.web.form.UsuarioActionForm;
import mundial.dao.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import mundial.bean.Apuesta;
import mundial.bean.Partido;
import mundial.bean.Usuario;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
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
		HttpSession session = request.getSession(true);		
		this.redirect(request, response, "/usuarioLogin.jsp");
		//doPost(request,response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String action = request.getParameter("action");
		HttpSession session = request.getSession(false);
		try{
			if (action.equals("ACTIVO")){
				validar(request,response, session);
			}
			
			if(action.equals("apostar")){
				apostar(request, response, session);
			}else if(action.equals("consolidado")){
				consolidado(request, response, session);
			}else if(action.equals("grabarApuesta")){
				grabarApuesta(request, response, session);
			}else if(action.equals("actualizarLista")){
				actualizarLista(request, response, session);
			}else if(action.equals("validaUsuario")){
				validaUsuario(request, response, session);
			}
		}catch (Exception e) {
			System.out.println(InicioServlet.class.getName()+", Error="+e);
		}
	}	
		
	private void apostar(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		String cod_partido = "";
		String cod_usuario = "";
		List listaPartidos = null;
		List listaApuesta = null;
		Usuario usuario = null;
		Apuesta apuesta = null;
		try{
			cod_partido = request.getParameter("cod_partido");
			usuario = (Usuario)session.getAttribute("usuario");
			
			cod_usuario = usuario.getCod_usuario();
			//Consulta registro
			listaPartidos = this.consultarListaPartido(cod_partido);
			listaApuesta = this.consultarApuesta(cod_partido, cod_usuario);
			
			if(listaApuesta != null && listaApuesta.size() > 0){
				apuesta = (Apuesta)listaApuesta.get(0);
				apuesta.setFlagIngreso("1");
				session.setAttribute("apuestaBean", listaApuesta.get(0));
			}else{				
				session.setAttribute("apuestaBean", new Apuesta());
			}
			
			List listaUsuario = this.consultarUsuario(usuario);
			session.setAttribute("listaUsuario", listaUsuario);
			session.setAttribute("partidoBean", listaPartidos.get(0));
			
			this.redirect(request, response, "/Apuesta.jsp");
		}catch (Exception e) {
			System.out.println(InicioServlet.class.getName()+", Error="+e);
			throw e;
		}
	}
	
	private void grabarApuesta(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		PreparedStatement ps = null;
		String sql = "";
		Connection con = null;
		ResultSet rs = null;
		List listaPartidos = null;
		try{
			con = UtilConexion.getInstance().getConnection();
						
			//Verifica si existe registro
			sql = "select count(*) as count from apuesta where cod_partido = ? and cod_usuario = ? ";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Integer.valueOf(request.getParameter("cod_partido")).intValue());
			ps.setString(2, request.getParameter("cod_usuario"));
			rs = ps.executeQuery();
			rs.next();

			if(rs.getInt("count") > 0){
				sql = "update APUESTA set SCORE_1 = ?, SCORE_2 = ?, DESCRIPCION = ?, FECHA_MOD = now() where cod_partido = ? and cod_usuario = ? ";

				ps = con.prepareStatement(sql);
				ps.setInt(1, Integer.valueOf(request.getParameter("score_1")).intValue());
				ps.setInt(2, Integer.valueOf(request.getParameter("score_2")).intValue());
				ps.setString(3, request.getParameter("descripcion"));
				ps.setInt(4, Integer.valueOf(request.getParameter("cod_partido")).intValue());
				ps.setString(5, request.getParameter("cod_usuario"));
			}else{
				sql = "insert into APUESTA(COD_PARTIDO, COD_USUARIO, SCORE_1, SCORE_2, DESCRIPCION, FLAG_PAGO, FECHA ) "+
				  "values(?, ?, ?, ?, ?, ?, now())";

				ps = con.prepareStatement(sql);
					
					
				ps.setInt(1, Integer.valueOf(request.getParameter("cod_partido")).intValue());
				ps.setString(2, request.getParameter("cod_usuario"));
				ps.setInt(3, Integer.valueOf(request.getParameter("score_1")).intValue());
				ps.setInt(4, Integer.valueOf(request.getParameter("score_2")).intValue());
				ps.setString(5, request.getParameter("descripcion"));
				ps.setString(6, "0");
				
			}
			ps.executeUpdate();		
			if (!con.getAutoCommit())
				con.commit();
			
			listaPartidos = consultarListaPartido(null);			
			session.setAttribute("ListaPartido", listaPartidos);
			
			
			this.redirect(request, response, "/PartidosListado.jsp");
		}catch (Exception e) {
			System.out.println(InicioServlet.class.getName()+", Error="+e);
			throw e;
		}
		
		finally{
			con.close();
			ps.close();		
			rs.close();
		}
	}	
	
	private List consultarListaPartido(String cod_partido) throws Exception{
		List lista = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT P.COD_PARTIDO, F.NOMBRE AS GRUPO, P1.NOMBRE AS PAIS1, P2.NOMBRE AS PAIS2, P.FECHA, P.FLAG_APOSTAR, P.SCORE_PAIS1,P.SCORE_PAIS2, " +
				     " P.FECHA_PARTI, P.VALOR_SOLES " +
					 ", (SELECT COUNT(1) FROM APUESTA AX WHERE AX.COD_PARTIDO = P.COD_PARTIDO AND AX.FLAG_PAGO = '1') * P.VALOR_SOLES as acumulado " +
					 "FROM   PARTIDO P " +
					 "INNER JOIN FASE F ON (F.COD_FASE = P.COD_FASE) " +
					 "INNER JOIN PAIS P1 ON (P1.COD_PAIS = P.COD_PAIS1) " +
					 "INNER JOIN PAIS P2 ON (P2.COD_PAIS = P.COD_PAIS2)" +
					 "WHERE P.FLAG_MOSTRAR = '1'  ";
		try{
			if(cod_partido != null){
				sql = sql.concat(" AND P.COD_PARTIDO = ").concat(cod_partido);
			}
			sql = sql.concat(" ORDER BY P.COD_PARTIDO ASC ");
			
			con = UtilConexion.getInstance().getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				Partido partido = new Partido();

				partido.setCod_partido(rs.getString("cod_partido"));
				partido.setGrupo(rs.getString("grupo"));
				partido.setFecha(rs.getString("fecha"));
				partido.setPais1(rs.getString("pais1"));
				partido.setPais2(rs.getString("pais2"));
				partido.setFlag_apostar(rs.getString("flag_apostar"));				
				partido.setFecha_parti(rs.getDate("fecha_parti"));
				partido.setValor_soles(rs.getBigDecimal("valor_soles"));
				partido.setAcumulado(rs.getBigDecimal("acumulado"));
				partido.setScore_pais1(rs.getBigDecimal("score_pais1"));
				partido.setScore_pais2(rs.getBigDecimal("score_pais2"));
				Time a = rs.getTime("fecha_parti");
				
				lista.add(partido);
			}
		}catch (Exception e) {
			System.out.println(InicioServlet.class.getName()+", Error="+e);
			e.printStackTrace();
			throw e;
		}
				
		finally{
			con.close();
			ps.close();				
			rs.close();			
			
		}
		return lista;
	}
	
	private List consultarUsuario(Usuario usuar) throws Exception{
		List lista = new ArrayList();
		Connection con = UtilConexion.getInstance().getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT COD_USUARIO, NOMBRE, TIPO " +
					 "FROM   USUARIO WHERE COD_USUARIO='"+usuar.getCod_usuario()+"'";
		try{
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				Usuario usuario = new Usuario();

				usuario.setCod_usuario(rs.getString("cod_usuario"));
				usuario.setNombre(rs.getString("nombre"));
				usuario.setTipo(rs.getString("tipo"));
				
				lista.add(usuario);
			}
		}catch (Exception e) {
			//System.out.println(InicioServlet.class.getName()+", Error="+e);
			e.printStackTrace();
			throw e;
		}
		
		finally{
			con.close();
			ps.close();				
			rs.close();			
		}		
		return lista;
	}
	
	private List consultarApuesta(String cod_partido, String cod_usuario) throws Exception{
		List lista = new ArrayList();
		Connection con = UtilConexion.getInstance().getConnection();
		PreparedStatement ps = null;
		String sql = "";
		ResultSet rs = null;
		try{
			sql = "SELECT a.COD_USUARIO, a.COD_PARTIDO, a.SCORE_1, a.SCORE_2, a.DESCRIPCION, u.nombre, a.flag_pago " +
				  "FROM   apuesta a " +
				  "inner join usuario u on (u.cod_usuario = a.cod_usuario) " +
				  "WHERE a.COD_PARTIDO = ?  ";
			
			if(cod_usuario != null && !cod_usuario.equals("")){
				sql = sql.concat(" and a.COD_USUARIO = ? ");
				sql = sql.concat(" ORDER BY a.FECHA ASC");
				ps = con.prepareStatement(sql);
				ps.setInt(1, Integer.valueOf(cod_partido));
				ps.setString(2, cod_usuario);
			}else{
				sql = sql.concat(" ORDER BY a.FECHA ASC");
				ps = con.prepareStatement(sql);
				ps.setInt(1, Integer.valueOf(cod_partido));
			}
			
			rs = ps.executeQuery();
			while(rs.next()){
				Apuesta apuesta = new Apuesta();

				apuesta.setCod_usuario(rs.getString("cod_usuario"));
				apuesta.setCod_partido(rs.getString("cod_partido"));
				apuesta.setScore_1(rs.getString("score_1"));
				apuesta.setScore_2(rs.getString("score_2"));
				apuesta.setDescripcion(rs.getString("descripcion"));
				apuesta.setFlag_pago(rs.getString("flag_pago"));
				apuesta.setUsuario(new Usuario());
				apuesta.getUsuario().setNombre(rs.getString("nombre"));
				
				lista.add(apuesta);
			}

		}catch (Exception e) {
			System.out.println(InicioServlet.class.getName()+", Error="+e);
			throw e;
		}
		
		finally{
			con.close();
			ps.close();				
			rs.close();			
		}			
		return lista;
	}	
	
	private void consolidado(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		String cod_partido = "";
		List listaApuesta = null;
		List listaPartidos = null;
		try{
			cod_partido = request.getParameter("cod_partido");
			//Consulta registro
			listaApuesta = this.consultarApuesta(cod_partido, null);
			listaPartidos = this.consultarListaPartido(cod_partido);
			
			session.setAttribute("ListaApuesta", listaApuesta);
			session.setAttribute("partidoBean", listaPartidos.get(0));
			
			this.redirect(request, response, "/ConsolidadoListado.jsp");
		}catch (Exception e) {
			System.out.println(InicioServlet.class.getName()+", Error="+e);
			throw e;
		}
	}	
	
	public void redirect(HttpServletRequest request, HttpServletResponse response, String pagina) {
		try {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pagina);
			dispatcher.forward(request, response);
			return;
		}
		catch(ServletException e) {
			e.printStackTrace();
			System.out.println("Error " + e);
		}
		catch(IOException e) {
			e.printStackTrace();
			System.out.println("Error " + e);
		}
	}	
	private void validar(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		//Variables de identificación de usuario
		String login = null;
		String password = null;
		String nombre = null;
		String tipo = null;
		String tipoUsuario = null;
		String codigoUsuarioModulo = null;
		List listaPartidos = new ArrayList();
		List listaUsuario = null;
		Usuario us = new Usuario();
		try{
			//Obtiene los parámetros de usuario
			login = request.getParameter("nick");
			password = request.getParameter("password");
			tipo = request.getParameter("tipo");
			nombre = "Usuario de Prueba";

			Usuario usuario = new Usuario();
			usuario.setCod_usuario(login);
			usuario.setTipo("0");//0 = Participante, 1 = Administrador
			
			usuario.setCod_usuario(login);
			listaUsuario = this.consultarUsuario(usuario);
			us = (Usuario)listaUsuario.get(0);
			usuario.setTipo(us.getTipo());
			usuario.setCod_usuario(us.getCod_usuario());
			
			  		  
			session.setAttribute("usuario", usuario);
			
			listaPartidos = consultarListaPartido(null);			
			session.setAttribute("ListaPartido", listaPartidos);
			
			this.redirect(request, response, "/PartidosListado.jsp");
			
		}catch(Exception e) {
			e.printStackTrace();
			 System.out.println(InicioServlet.class.getName()+", Error="+e);
			 session.setAttribute("mensaje", e);
			 this.redirect(request, response, "/mensaje.jsp");
		}
	}

	private void actualizarLista(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		//Variables de identificación de usuario
		List listaPartidos = new ArrayList();
		try{
			//Obtiene los parámetros de usuario

			listaPartidos = consultarListaPartido(null);			
			session.setAttribute("ListaPartido", listaPartidos);
			
			this.redirect(request, response, "/PartidosListado.jsp");
			
		}catch(Exception e) {
			e.printStackTrace();
			 System.out.println(InicioServlet.class.getName()+", Error="+e);
			 session.setAttribute("mensaje", e);
			 this.redirect(request, response, "/mensaje.jsp");
		}
	}
	
	private void validaUsuario(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		//Variables de identificación de usuario
		List listaPartidos = new ArrayList();
		UsuarioDAO usuarioDAO= new UsuarioDAO();
		String usuario = "";
		String password = "";
		String SUCCESS = "";
		List listaUsuario = null;
		Usuario us = new Usuario();
		try{
			//Obtiene los parámetros de usuario
			usuario = request.getParameter("nick");
			password = request.getParameter("password");
			
			SUCCESS = usuarioDAO.getUsuario(usuario, password);			
			if (SUCCESS== "false") {
				session.setAttribute("mensaje", "Clave Incorrecta o no Existe el Usuario");
                this.redirect(request, response, "/mensaje.jsp");
			}else{
				//login = request.getParameter("nick");
				
				Usuario usuarioB = new Usuario();
				usuarioB.setCod_usuario(usuario);
				usuarioB.setTipo("0");//0 = Participante, 1 = Administrador
				
				usuarioB.setCod_usuario(usuario);
				listaUsuario = this.consultarUsuario(usuarioB);
				us = (Usuario)listaUsuario.get(0);
				usuarioB.setTipo(us.getTipo());
				usuarioB.setCod_usuario(us.getCod_usuario());
				  		  
				session.setAttribute("usuario", usuario);
				
				listaPartidos = consultarListaPartido(null);			
				session.setAttribute("ListaPartido", listaPartidos);
				
				this.redirect(request, response, "/PartidosListado.jsp");
			}
		}catch(Exception e) {
			e.printStackTrace();
			 System.out.println(InicioServlet.class.getName()+", Error="+e);
			 session.setAttribute("mensaje", e);
			 this.redirect(request, response, "/mensaje.jsp");
		}
	}
	
	
	private void updateFlagApos() throws Exception{
		
		Connection con = UtilConexion.getInstance().getConnection();
		PreparedStatement ps = null;
		String sql = "";
		ResultSet rs = null;
		try{
			sql = "UPDATE partido set flag_apostar=0 "+
                  " where FECHA_PARTI<now() and flag_apostar=1  ";
			ps = con.prepareStatement(sql);
			ps.executeUpdate();		
			if (!con.getAutoCommit())
				con.commit();

		}catch (Exception e) {
			System.out.println(InicioServlet.class.getName()+", Error="+e);
			throw e;
		}
		
		finally{
			con.close();
			ps.close();				
			rs.close();			
		}			
	}	
	
private String updateFechaParti(String fecha) throws Exception{
		
		Connection con = UtilConexion.getInstance().getConnection();
		PreparedStatement ps = null;
		String sql = "";
		ResultSet rs = null;
		try{
			sql = "UPDATE partido set flag_apostar=0 "+
                  " where FECHA_PARTI<now() and flag_apostar=1  ";
			ps = con.prepareStatement(sql);
			ps.executeUpdate();		
			if (!con.getAutoCommit())
				con.commit();

		}catch (Exception e) {
			System.out.println(InicioServlet.class.getName()+", Error="+e);
			throw e;
		}
		
		finally{
			con.close();
			ps.close();				
			rs.close();			
		}
		return fecha;
	}	


// FECHA EN YYYYMMDD   Y HORA EN H24:MI:SS
//UPDATE PARTIDO SET FECHA_PARTI='2010-06-14 00:00:00'
//WHERE COD_PARTIDO=1

private String updatefechparti(String fecha, String hora, int cod_partido) throws Exception{
	
	Connection con = UtilConexion.getInstance().getConnection();
	PreparedStatement ps = null;
	String sql = "";
	ResultSet rs = null;
	String fechmysql=fecha.substring(7, 8)+fecha.substring(2,6)+fecha.substring(2,6); 
	try{
		sql = "UPDATE partido set FECHA_PARTI="+
              " where FECHA_PARTI<now() and flag_apostar=1  ";
		ps = con.prepareStatement(sql);
		ps.executeUpdate();		
		if (!con.getAutoCommit())
			con.commit();

	}catch (Exception e) {
		System.out.println(InicioServlet.class.getName()+", Error="+e);
		throw e;
	}
	
	finally{
		con.close();
		ps.close();				
		rs.close();			
	}
	return fecha;
}


private String calcularPuntos(String cod_usuario,  String cod_partido) throws Exception{
	List listaPartidos= null;
	List listaApuesta = null;
	Partido partido;
	BigDecimal score1;
	BigDecimal score2;
	String acumulado="";
	//Consulta registro
	listaApuesta = this.consultarApuesta(cod_partido, cod_usuario);
    listaPartidos = this.consultarListaPartido(cod_partido);
	 
	if(listaPartidos != null && listaPartidos.size() > 0){
		if(listaApuesta != null && listaApuesta.size() > 0){
			for (int i=0; i<listaApuesta.size(); i++){
				for (int y=0; y<listaPartidos.size(); y++){
					partido = (Partido)listaPartidos.get(i);
					score1 = partido.getScore_pais1();
					score2 = partido.getScore_pais2();
					
				}
				
				
			}
		}
	}
	return acumulado;
}

}
	
