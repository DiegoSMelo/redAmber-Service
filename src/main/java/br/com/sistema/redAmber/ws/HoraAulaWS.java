package br.com.sistema.redAmber.ws;

import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import br.com.sistema.redAmber.basicas.HoraAula;
import br.com.sistema.redAmber.basicas.http.HoraAulaHTTP;
import br.com.sistema.redAmber.exceptions.DAOException;
import br.com.sistema.redAmber.rn.RNHoraAula;

@Path("/horaaulaws")
public class HoraAulaWS {

	private RNHoraAula rnHoraAula;
	private Gson gson;
	
	public HoraAulaWS() {
		this.rnHoraAula = new RNHoraAula();
		this.gson = new Gson();
	}
	
	@POST
	@Path("salvar")
	@Consumes("application/json")
	@Produces("text/plain")
	public String salvarHoraAula(String jsonHoraAula) {
		HoraAula horaAula = new HoraAula();
		HoraAulaHTTP horaAulaHTTP = gson.fromJson(jsonHoraAula, HoraAulaHTTP.class);
		
		System.out.println(horaAulaHTTP.getHoraInicio());
		System.out.println(horaAulaHTTP.getHoraFim());
		
		horaAula.setId(horaAulaHTTP.getId());
		horaAula.setTurno(horaAulaHTTP.getTurno());
		horaAula.setStatus(horaAulaHTTP.getStatus());
		
		try {
			long ini = Long.parseLong(horaAulaHTTP.getHoraInicio());
			long end = Long.parseLong(horaAulaHTTP.getHoraFim());
			
			Date horaInicioDate = new Date();
			Date horaFimDate = new Date();
			horaInicioDate.setTime(ini);
			horaFimDate.setTime(end);
			horaAula.setHoraInicio(horaInicioDate);
			horaAula.setHoraFim(horaFimDate);
		} catch (Exception e) {
			e.printStackTrace();
			return "Error";
		}
		this.rnHoraAula.salvar(horaAula);
		return "Horário de aula salvo com sucesso.";
	}
	
	@GET
	@Path("buscar-por-id/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	public String buscarHoraAulaPorId(@PathParam("id") String id) {
		System.out.println("!!! ID: " + id);
		if (id == null || id == "null") {
			return "null";
		}
		return this.gson.toJson(this.rnHoraAula.buscarPorId(Long.parseLong(id)));
	}
	
	@GET
	@Path("buscar-por-hora/{inicio}/{fim}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	public String buscarHoraAulaPorHoraInicioHoraFim(@PathParam("inicio") String inicio, @PathParam("fim") String fim) {
		System.out.println("INICIO: " + inicio);
		System.out.println("FIM: " + fim);
		
		long ini = Long.parseLong(inicio);
		long end = Long.parseLong(fim);
		
		try {
			Date horaInicio = new Date();
			Date horaFim = new Date();
			horaInicio.setTime(ini);
			horaFim.setTime(end);
			return this.gson.toJson(this.rnHoraAula.buscarPorHoraInicioHoraFim(horaInicio, horaFim));
		} catch (DAOException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GET
	@Path("listar")
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	public String listarHoraAula() {
		List<HoraAula> horasAula;
		horasAula = rnHoraAula.buscarTodos();
		return this.gson.toJson(horasAula);
	}
}