package mundial.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

public class Partido implements Serializable{
	String cod_partido;
	String grupo;
	String fecha;
	String partido;
	String pais1;
	String pais2;
	String flag_apostar;
	Date fecha_parti;
	BigDecimal valor_soles; 	
	BigDecimal acumulado;
	BigDecimal score_pais1;
	BigDecimal score_pais2;
	
	
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
	public String getCod_partido() {
		return cod_partido;
	}
	public void setCod_partido(String cod_partido) {
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
	public String getFlag_apostar() {
		return flag_apostar;
	}
	public void setFlag_apostar(String flag_apostar) {
		this.flag_apostar = flag_apostar;
	}
	public Date getFecha_parti() {
		return fecha_parti;
	}
	public void setFecha_parti(Date fecha_parti) {
		this.fecha_parti = fecha_parti;
	}
	public BigDecimal getValor_soles() {
		return valor_soles;
	}
	public void setValor_soles(BigDecimal valor_soles) {
		this.valor_soles = valor_soles;
	}
	public BigDecimal getAcumulado() {
		return acumulado;
	}
	public void setAcumulado(BigDecimal acumulado) {
		this.acumulado = acumulado;
	}
	public BigDecimal getScore_pais1() {
		return score_pais1;
	}
	public void setScore_pais1(BigDecimal score_pais1) {
		this.score_pais1 = score_pais1;
	}
	public BigDecimal getScore_pais2() {
		return score_pais2;
	}
	public void setScore_pais2(BigDecimal score_pais2) {
		this.score_pais2 = score_pais2;
	}
	
}
