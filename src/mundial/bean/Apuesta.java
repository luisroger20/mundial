package mundial.bean;

import java.io.Serializable;

public class Apuesta implements Serializable{
	String cod_partido;
	String cod_usuario;
	String score_1;
	String score_2;
	String descripcion;
	String flag_pago;
	Usuario usuario;
	String flagIngreso;
	
	public String getCod_partido() {
		return cod_partido;
	}
	public void setCod_partido(String cod_partido) {
		this.cod_partido = cod_partido;
	}
	public String getCod_usuario() {
		return cod_usuario;
	}
	public void setCod_usuario(String cod_usuario) {
		this.cod_usuario = cod_usuario;
	}
	public String getScore_1() {
		return score_1;
	}
	public void setScore_1(String score_1) {
		this.score_1 = score_1;
	}
	public String getScore_2() {
		return score_2;
	}
	public void setScore_2(String score_2) {
		this.score_2 = score_2;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getFlag_pago() {
		return flag_pago;
	}
	public void setFlag_pago(String flag_pago) {
		this.flag_pago = flag_pago;
	}
	public String getFlagIngreso() {
		return flagIngreso;
	}
	public void setFlagIngreso(String flagIngreso) {
		this.flagIngreso = flagIngreso;
	}		
}
