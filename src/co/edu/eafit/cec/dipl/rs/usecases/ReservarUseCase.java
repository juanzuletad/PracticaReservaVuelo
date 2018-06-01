package co.edu.eafit.cec.dipl.rs.usecases;

import java.util.ArrayList;
import java.util.List;

import co.edu.eafit.cec.dipl.rs.dto.ReservaDTO;
import co.edu.eafit.cec.dipl.rs.entidades.Reserva;
import co.edu.eafit.cec.dipl.rs.entidades.Silla;
import co.edu.eafit.cec.dipl.rs.entidades.Vuelo;

public class ReservarUseCase {
	
	static List<Reserva> reservas = new ArrayList<Reserva>();
	
	public String ejecutar(ReservaDTO parametros) {
		//TODO: Buscar el vuelo
		//TODO: Buscar si el vuelo existe
		//TODO: Buscar si la silla continua dispoible
		
		Vuelo vuelo = new Vuelo(parametros.getVuelo());
		Silla silla = new Silla(parametros.getSilla(),"TURISTA");
		
		Reserva reserva = new Reserva(vuelo, silla, parametros.getPasajero());
		
		reservas.add(reserva);
		
		return reserva.getId();
		
	}
	
	public boolean cancelar(String idReserva) {
		Reserva reserva = getReserva(idReserva);
		if (reserva == null) {
			return false;
		} else {
			reservas.remove(reserva);
			reserva = null;
			return true;
		}
	}
	
	public boolean cancelar(ReservaDTO parametros) {
		boolean retorno;
		retorno = false;
		for (Reserva reserva : reservas) {
			if (reserva.getVuelo().getId().equals(parametros.getVuelo()) && 
				reserva.getSilla().getId().equals(parametros.getSilla())) {
				reservas.remove(reserva);
				retorno = true;
			}
		}
		return retorno;
	}
	
	public static Reserva getReserva(String idReserva) {
		Reserva reservaEncontrada = null;
		for (Reserva reserva : reservas) {
			System.out.println(reserva.getId());
			if (reserva.getId().equals(idReserva)) {
				reservaEncontrada = reserva;
				return reservaEncontrada;
			}
		}
		return reservaEncontrada;
	}
	
}
