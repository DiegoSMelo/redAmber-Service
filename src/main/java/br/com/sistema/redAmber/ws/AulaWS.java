package br.com.sistema.redAmber.ws;

import java.util.Calendar;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import br.com.sistema.redAmber.basicas.HoraAula;
import br.com.sistema.redAmber.basicas.HoraAulaPK;
import br.com.sistema.redAmber.basicas.http.HoraAulaHTTP;
import br.com.sistema.redAmber.rn.RNAula;
import br.com.sistema.redAmber.rn.RNHoraAula;
import br.com.sistema.redAmber.util.Datas;

@Path("/aulaws")
public class AulaWS {

	private RNAula rnAula;
	private RNHoraAula rnHoraAula;
	private Gson gson;
	
	public AulaWS() {
		
		this.rnAula = new RNAula();
		this.rnHoraAula = new RNHoraAula();
		this.gson = new Gson();
		
	}
	
	@GET
	@Path("hora-aula/por-turma/{idTurma}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public String listaHoraAulaPorIdTurma(@PathParam("idTurma") String idTurma){
		
		Long id_turma = Long.parseLong(idTurma);
		
		return this.gson.toJson(this.rnHoraAula.listaHoraAulaPorIdTurma(id_turma));
		
	}
	
	@GET
	@Path("hora-aula/{jsonHoraAulaPK}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public String buscarHoraAulaPorPK(@PathParam("jsonHoraAulaPK") String jsonHoraAulaPK){
		
		HoraAulaPK haPK = this.gson.fromJson(jsonHoraAulaPK, HoraAulaPK.class);
		
		return this.gson.toJson(this.rnHoraAula.buscarHoraAulaPorId(haPK));
		
	}
	
	@POST
	@Path("hora-aula/add")
	@Consumes("application/json")
	@Produces("text/plain")
	public String addHoraAula(String jsonHoraAula) {

		HoraAula horaAula = new HoraAula();
		HoraAulaHTTP horaAulaHTTP = new HoraAulaHTTP();
		
		/*
		 * TIMESTAMP DAS HORAS
		 */
		Calendar horaInicio = Datas.converterDateToCalendar(new Date(Long.parseLong(horaAulaHTTP.getHoraInicio())));
		Calendar horaFim = Datas.converterDateToCalendar(new Date(Long.parseLong(horaAulaHTTP.getHoraFim())));
		
		horaAula.setDia(horaAulaHTTP.getDia());
		horaAula.setHoraFim(horaFim);
		horaAula.setHoraInicio(horaInicio);
		horaAula.setId(horaAulaHTTP.getId());
		horaAula.setStatus(horaAulaHTTP.getStatus());
		
		this.rnHoraAula.adicionarHoraAula(horaAula);
		
		return "Horário de aula salvo com sucesso";

	}
	
	
	@POST
	@Path("hora-aula/remover")
	@Consumes("application/json")
	@Produces("text/plain")
	public String removerHoraAulaPorId(String jsonHoraAula) {
		
		HoraAulaHTTP haHTTP = this.gson.fromJson(jsonHoraAula, HoraAulaHTTP.class);
		HoraAula ha = this.rnHoraAula.buscarHoraAulaPorId(haHTTP.getId());
		this.rnHoraAula.removerHoraAula(ha);
		
		return "O horário de aula foi removida da sua grade!";

	}
}
