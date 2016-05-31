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

import br.com.sistema.redAmber.basicas.DuracaoAula;
import br.com.sistema.redAmber.basicas.enums.TipoTurno;
import br.com.sistema.redAmber.basicas.http.DuracaoAulaHTTP;
import br.com.sistema.redAmber.exceptions.DAOException;
import br.com.sistema.redAmber.rn.RNDuracaoAula;

@Path("/duracaoaulaws")
public class DuracaoAulaWS {

	private RNDuracaoAula rnDuracaoAula;
	private Gson gson;
	
	public DuracaoAulaWS() {
		this.rnDuracaoAula = new RNDuracaoAula();
		this.gson = new Gson();
	}
	
	@POST
	@Path("salvar")
	@Consumes("application/json")
	@Produces("text/plain")
	public String salvarDuracaoAula(String jsonDuracaoAula) {
		DuracaoAula duracaoAula = new DuracaoAula();
		DuracaoAulaHTTP duracaoAulaHTTP = gson.fromJson(jsonDuracaoAula, DuracaoAulaHTTP.class);
		
		System.out.println(duracaoAulaHTTP.getHoraInicio());
		System.out.println(duracaoAulaHTTP.getHoraFim());
		
		duracaoAula.setId(duracaoAulaHTTP.getId());
		duracaoAula.setTurno(duracaoAulaHTTP.getTurno());
		duracaoAula.setStatus(duracaoAulaHTTP.getStatus());
		
		try {
			long ini = Long.parseLong(duracaoAulaHTTP.getHoraInicio());
			long end = Long.parseLong(duracaoAulaHTTP.getHoraFim());
			
			Date horaInicioDate = new Date();
			Date horaFimDate = new Date();
			horaInicioDate.setTime(ini);
			horaFimDate.setTime(end);
			duracaoAula.setHoraInicio(horaInicioDate);
			duracaoAula.setHoraFim(horaFimDate);
		} catch (Exception e) {
			e.printStackTrace();
			return "Error";
		}
		this.rnDuracaoAula.salvar(duracaoAula);
		return "Duração de aula salva com sucesso.";
	}
	
	@GET
	@Path("buscar-por-id/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	public String buscarDuracaoAulaPorId(@PathParam("id") String id) {
		System.out.println("!!! ID: " + id);
		if (id == null || id == "null") {
			return "null";
		}
		return this.gson.toJson(this.rnDuracaoAula.buscarPorId(Long.parseLong(id)));
	}
	
	@GET
	@Path("buscar-por-hora/{inicio}/{fim}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	public String buscarDuracaoAulaPorHoraInicioHoraFim(@PathParam("inicio") String inicio, @PathParam("fim") String fim) {
		System.out.println("INICIO: " + inicio);
		System.out.println("FIM: " + fim);
		
		long ini = Long.parseLong(inicio);
		long end = Long.parseLong(fim);
		
		try {
			Date horaInicio = new Date();
			Date horaFim = new Date();
			horaInicio.setTime(ini);
			horaFim.setTime(end);
			return this.gson.toJson(this.rnDuracaoAula.buscarPorHoraInicioHoraFim(horaInicio, horaFim));
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
	public String listarDuracaoAula() {
		List<DuracaoAula> horasAula;
		horasAula = rnDuracaoAula.buscarTodos();
		return this.gson.toJson(horasAula);
	}
	
	@GET
	@Path("buscar-por-turno/{turno}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	public String consultarPorTurno(@PathParam("turno") String jsonTurno) {
		
		try {
			List<DuracaoAula> horarios;
			TipoTurno turno = null;
			
			if (jsonTurno.equalsIgnoreCase("MANHA") || jsonTurno.equalsIgnoreCase("Manhã")) {
				turno = TipoTurno.MANHA;
			}
			if (jsonTurno.equalsIgnoreCase("TARDE") || jsonTurno.equalsIgnoreCase("Tarde")) {
				turno = TipoTurno.TARDE;
			}
			if (jsonTurno.equalsIgnoreCase("NOITE") || jsonTurno.equalsIgnoreCase("Noite")) {
				turno = TipoTurno.NOITE;
			}
			
			horarios = this.rnDuracaoAula.consultarPorTurno(turno);
			return this.gson.toJson(horarios);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}