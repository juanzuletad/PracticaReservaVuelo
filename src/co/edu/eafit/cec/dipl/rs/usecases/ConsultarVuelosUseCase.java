package co.edu.eafit.cec.dipl.rs.usecases;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.edu.eafit.cec.dipl.rs.dto.ConsultaVueloDTO;
import co.edu.eafit.cec.dipl.rs.entidades.Silla;
import co.edu.eafit.cec.dipl.rs.entidades.Vuelo;

public class ConsultarVuelosUseCase {

	List<Vuelo> vuelos = new ArrayList<Vuelo>();
	
	public ConsultarVuelosUseCase() {
		
		List<Silla> listaSillas = new ArrayList<Silla>();
		listaSillas.add(new Silla("A1","TURISTA"));
		listaSillas.add(new Silla("B1","TURISTA"));
		listaSillas.add(new Silla("C1","TURISTA"));
		
		vuelos.add(new Vuelo("MDE","SMR", new Date(1526706000000l), listaSillas));
		vuelos.add(new Vuelo("SMR","MDE", new Date(1526706000000l), listaSillas));
		vuelos.add(new Vuelo("BOG","RIH", new Date(1526706000000l), listaSillas));
		vuelos.add(new Vuelo("BOG","MDE", new Date(1526706000000l), listaSillas));
		vuelos.add(new Vuelo("MDE","MTR", new Date(1526706000000l), listaSillas));
		vuelos.add(new Vuelo("MDE","BOG", new Date(1526706000000l), listaSillas));
		vuelos.add(new Vuelo("BOG","MTR", new Date(1526706000000l), listaSillas));
			
	}
	
	public List <Vuelo> obtenerVuelos (ConsultaVueloDTO parametros){
		List <Vuelo> vuelosDisponibles = null;
		
		for (Vuelo vuelo : vuelos) {
			if (parametros.getOrigen().equals(vuelo.getOrigen())) {
				if (parametros.getDestino().equals(vuelo.getDestino())) {
					if (vuelo.estaDisponibleParaReservas()) {
						if (vuelosDisponibles == null) {
							vuelosDisponibles = new ArrayList<Vuelo>();
						}
						vuelosDisponibles.add(vuelo);
					}					
				}
				
			}			
		}
		
		return vuelosDisponibles;
		
	}
	
}
