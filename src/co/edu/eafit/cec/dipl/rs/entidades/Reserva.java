package co.edu.eafit.cec.dipl.rs.entidades;

import java.util.UUID;

public class Reserva {
	private String id;
	private Vuelo vuelo;
	private Silla silla;
	private String pasajero;
	private int estado;
	
	public Reserva(Vuelo vuelo, Silla silla, String pasajero) {
		super();
		
		this.id = UUID.randomUUID().toString();
		
		this.vuelo = vuelo;
		this.silla = silla;
		this.pasajero = pasajero;
		this.estado = 1;
	}

	public String getId() {
		return id;
	}

	public Vuelo getVuelo() {
		return vuelo;
	}

	public Silla getSilla() {
		return silla;
	}

	public String getPasajero() {
		return pasajero;
	}

	public int getEstado() {
		return estado;
	}
	
}
