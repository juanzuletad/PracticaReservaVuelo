package co.edu.eafit.cec.dipl.rs;

import java.net.URI;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import co.edu.eafit.cec.dipl.rs.dto.ConsultaVueloDTO;
import co.edu.eafit.cec.dipl.rs.dto.ReservaDTO;
import co.edu.eafit.cec.dipl.rs.entidades.Reserva;
import co.edu.eafit.cec.dipl.rs.entidades.Vuelo;
import co.edu.eafit.cec.dipl.rs.usecases.ConsultarVuelosUseCase;
import co.edu.eafit.cec.dipl.rs.usecases.ReservarUseCase;
import sun.misc.BASE64Decoder;

@Path("/rest")
public class ReservaService {

	@Path("/vuelos")
	@GET
	@Produces("co.edu.eafit.reservas.vuelos.v1+application/json")

	public List<Vuelo> getVuelos(@Context UriInfo info) {

		MultivaluedMap<String, String> queryString = info.getQueryParameters();

		String origen = (String) queryString.getFirst("origen");
		String destino = (String) queryString.getFirst("destino");
		String fecha = (String) queryString.getFirst("fecha");

		System.out.println("Versión 1");
		System.out.println("Buscando origen " + origen);
		System.out.println("Buscando destino " + destino);
		System.out.println("Buscando fecha " + fecha);

		ConsultaVueloDTO parametros = new ConsultaVueloDTO();
		parametros.setDestino(destino);
		parametros.setOrigen(origen);
		parametros.setFecha(new Date(1526706000000l));

		ConsultarVuelosUseCase casoUso = new ConsultarVuelosUseCase();
		List<Vuelo> resultado = casoUso.obtenerVuelos(parametros);

		return resultado;
	}

	@Path("/vuelos")
	@GET
	@Produces("co.edu.eafit.reservas.vuelos.v2+application/json")

	public List<Vuelo> getVuelosTexto(@Context UriInfo info) {

		MultivaluedMap<String, String> queryString = info.getQueryParameters();

		System.out.println("Versión 2");
		String origen = (String) queryString.getFirst("origen");
		String destino = (String) queryString.getFirst("destino");
		String fecha = (String) queryString.getFirst("fecha");

		System.out.println("Buscando origen " + origen);
		System.out.println("Buscando destino " + destino);
		System.out.println("Buscando fecha " + fecha);

		ConsultaVueloDTO parametros = new ConsultaVueloDTO();
		parametros.setDestino(origen);
		parametros.setOrigen(destino);
		parametros.setFecha(new Date(1526706000000l));

		ConsultarVuelosUseCase casoUso = new ConsultarVuelosUseCase();
		List<Vuelo> resultado = casoUso.obtenerVuelos(parametros);

		return resultado;
	}

	@POST
	@Path("/reservas")
	@Consumes("co.edu.eafit.reservas.vuelos.v1+application/json")
	@Produces("co.edu.eafit.reservas.vuelos.v1+application/json")
	public Response crearReserva(ReservaDTO datos) {
		ReservarUseCase casoUso = new ReservarUseCase();
		String idTransaccion = casoUso.ejecutar(datos);
		URI uri = null;
		try {
			uri = new URI("http://localhost:8080/ReservaVuelo/api/rest/reservas/" + idTransaccion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.created(uri).build();
	}

	@DELETE
	@Path("/reservas/{reservaId}")
	@Consumes("co.edu.eafit.reservas.vuelos.v1+application/json")
	@Produces("co.edu.eafit.reservas.vuelos.v1+application/json")
	public Response cancelarReserva(@PathParam("reservaId") String id) {
		ReservarUseCase casoUso = new ReservarUseCase();
		if (casoUso.cancelar(id)) {
			return Response.ok().build();
		} else {
			return Response.notModified().build();
		}
	}
	
	@PATCH
	@Path("/reservas")
	@Consumes("co.edu.eafit.reservas.vuelos.v1+application/json")
	@Produces("co.edu.eafit.reservas.vuelos.v1+application/json")
	public Response cancelarReserva(ReservaDTO datos) {
		ReservarUseCase casoUso = new ReservarUseCase();
		if (casoUso.cancelar(datos)) {
			return Response.ok().build();
		} else {
			return Response.notModified().build();
		}
	}

	@GET
	@Path("/autenticacion")
	@Produces("application/json")
	public Response obtenerReservas(@HeaderParam("Authorization") String authorization) {

		System.out.println("Prueba 1");

		if (authorization == null) {

			Response.status(401).header("WWW-Authenticate", "Basic EAFIT").build();
		}

		System.out.println("Prueba 2");

		String[] authData = authorization.split(" ");
		System.out.println("Prueba 3");
		String base64 = authData[1];
		try {
			byte[] bytes = new BASE64Decoder().decodeBuffer(base64);
			String datos = new String(bytes);
			String[] datosPartidos = datos.split(":");

			if (datosPartidos[0].equals("jzuletad") && datosPartidos[1].equals("jzuletad")) {
				// TODO ejecutar
			} else {
				Response.status(401).build();
			}

		} catch (Exception e) {
			return Response.status(404).build();
		}

		return Response.ok().build();
		// return Response.status(401).header("WWW-Authenticate", "Basic
		// \"EAFIT\"").build();
	}

	@GET
	@Path("/reservas/{reservaId}")
	@Produces("co.edu.eafit.reservas.vuelos.v1+application/json")
	public Reserva getReserva(@PathParam("reservaId") String id) {
		return ReservarUseCase.getReserva(id);
	}

}
