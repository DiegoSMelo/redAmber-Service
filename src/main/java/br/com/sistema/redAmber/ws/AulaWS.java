package br.com.sistema.redAmber.ws;

import java.util.Calendar;
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

import br.com.sistema.redAmber.basicas.Aula;
import br.com.sistema.redAmber.basicas.AulaPK;
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
	@Path("listar")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public String listaAulas() {
		
		List<Aula> listaAulas = this.rnAula.listarAulas();

		return this.gson.toJson(listaAulas);

	}
	
	@GET
	@Path("por-pk/{jsonAulaPK}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public String buscarAulaPorPK(@PathParam("jsonAulaPK") String jsonAulaPK){
		
		AulaPK aPK = this.gson.fromJson(jsonAulaPK, AulaPK.class);
		
		return this.gson.toJson(this.rnAula.buscarAulaPorPK(aPK));
		
	}
	
	@POST
	@Path("add")
	@Consumes("application/json")
	@Produces("text/plain")
	public String addAula(String jsonAula) {
		
		Aula aula = this.gson.fromJson(jsonAula, Aula.class);
		
		this.rnAula.addAula(aula);
		
		return "Aula adicionada com sucesso!";

	}
	
	@POST
	@Path("remover")
	@Consumes("application/json")
	@Produces("text/plain")
	public String removerAulaPorId(String jsonAula) {
		
		Aula aula = this.gson.fromJson(jsonAula, Aula.class);

		this.rnAula.removerAula(aula);
		
		return "Aula removida com sucesso!";

	}
	
	
	//-------------------------------Hora aula--------------------------------------------------
	
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
		HoraAulaHTTP horaAulaHTTP = this.gson.fromJson(jsonHoraAula, HoraAulaHTTP.class);
		
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
		
		return "Horário de aula adicionado com sucesso";

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
