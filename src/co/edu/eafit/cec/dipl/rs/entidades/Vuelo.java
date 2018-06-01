package co.edu.eafit.cec.dipl.rs.entidades;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Vuelo {
	
	private String id;
	private String origen;
	private String destino;
	private Date fecha;
	
	private List<Silla> sillas;
	private int estado;
	
	public Vuelo(String id) {
		this.setId(id);
	}
	
	public Vuelo (String origen, String destino, Date fecha, List<Silla> sillas) {
		this.setId(UUID.randomUUID().toString());
		this.origen = origen;
		this.destino = destino;
		this.sillas = sillas;
		this.fecha = fecha;
	}
		
	public boolean estaDisponibleParaReservas() {
		for (Silla silla : sillas) {
			if (silla.estaDisponible()) {
				return true;
			}
		}
		return false;
	}
	
	public String getOrigen() {
		return origen;
	}
	
	public String getDestino() {
		return destino;
	}
	
	public Date detFecha() {
		return fecha;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
