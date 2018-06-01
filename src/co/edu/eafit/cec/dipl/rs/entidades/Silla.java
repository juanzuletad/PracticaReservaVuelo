package co.edu.eafit.cec.dipl.rs.entidades;

public class Silla {
	private String id;
	// DISPONIBLE = 1
	// NO DISPONIBLE = 0
	private int estado;
	private String categoria;
	
	public Silla(String id, String categoria) {
		super();
		this.id = id;
		this.estado = 1;
		this.categoria = categoria;
	}

	public boolean estaDisponible() {
		return estado == 1;
	}

	public String getId() {
		return id;
	}

}
