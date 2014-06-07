package mundial.bean;

import java.io.Serializable;

public class Partido implements Serializable{
	Integer cod_partido;
	String grupo;
	String fecha;
	String partido;
	String pais1;
	String pais2;
	
	public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getPartido() {
		return partido;
	}
	public void setPartido(String partido) {
		this.partido = partido;
	}
	public Integer getCod_partido() {
		return cod_partido;
	}
	public void setCod_partido(Integer cod_partido) {
		this.cod_partido = cod_partido;
	}
	public String getPais1() {
		return pais1;
	}
	public void setPais1(String pais1) {
		this.pais1 = pais1;
	}
	public String getPais2() {
		return pais2;
	}
	public void setPais2(String pais2) {
		this.pais2 = pais2;
	}	
	
}
