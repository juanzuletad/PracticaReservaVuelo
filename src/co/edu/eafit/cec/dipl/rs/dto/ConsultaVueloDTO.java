package co.edu.eafit.cec.dipl.rs.dto;

import java.util.Date;

public class ConsultaVueloDTO {

	public String getOrigen() {
		return origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	private String origen;
	private String destino;
	private Date fecha;
	
	

}
