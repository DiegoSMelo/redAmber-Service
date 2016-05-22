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

import br.com.sistema.redAmber.basicas.Aula;
import br.com.sistema.redAmber.basicas.AulaPK;
import br.com.sistema.redAmber.basicas.HoraAula;
import br.com.sistema.redAmber.basicas.HoraAulaPK;
import br.com.sistema.redAmber.basicas.Professor;
import br.com.sistema.redAmber.basicas.http.AulaHTTP;
import br.com.sistema.redAmber.basicas.http.HoraAulaHTTP;
import br.com.sistema.redAmber.basicas.http.TurmaHTTP;
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
	public String buscarAulaPorPK(@PathParam("jsonAulaPK") String jsonAulaPK) {

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

	// -------------------------------Hora
	// aula--------------------------------------------------

	@GET
	@Path("hora-aula/por-turma/{idTurma}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public String listaHoraAulaPorIdTurma(@PathParam("idTurma") String idTurma) {

		Long id_turma = Long.parseLong(idTurma);

		return this.gson.toJson(this.rnHoraAula.listaHoraAulaPorIdTurma(id_turma));
		// return this.rnHoraAula.listaHoraAulaPorIdTurma(id_turma);
	}

	@GET
	@Path("hora-aula/{jsonHoraAulaPK}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public String buscarHoraAulaPorPK(@PathParam("jsonHoraAulaPK") String jsonHoraAulaPK) {

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
		 * Add aula
		 */
		AulaHTTP aulaHTTP = horaAulaHTTP.getId().getAula();

		Professor professor = new Professor();
		professor.setEmail(aulaHTTP.getId().getProfessor().getEmail());
		professor.setId(aulaHTTP.getId().getProfessor().getId());
		professor.setListDisciplinas(aulaHTTP.getId().getProfessor().getListDisciplinas());
		professor.setNome(aulaHTTP.getId().getProfessor().getNome());
		professor.setRg(aulaHTTP.getId().getProfessor().getRg());
		professor.setStatus(aulaHTTP.getId().getProfessor().getStatus());
		professor.setTelefone(aulaHTTP.getId().getProfessor().getTelefone());
		professor.setUsuario(aulaHTTP.getId().getProfessor().getUsuario());

		/*
		 * TIMESTAMP DAS HORAS
		 */
		professor.setDataNascimento(Datas.converterDateToCalendar(
				new Date(Long.parseLong(aulaHTTP.getId().getProfessor().getDataNascimento()))));

		Aula aula = new Aula();
		AulaPK aulaPK = new AulaPK();

		aulaPK.setProfessor(professor);
		aulaPK.setDisciplina(aulaHTTP.getId().getDisciplina());
		aulaPK.setSala(aulaHTTP.getId().getSala());

		aula.setId(aulaPK);

		if (this.rnAula.buscarAulaPorPK(aulaPK) == null) {

			// add Aula
			this.rnAula.addAula(aula);

		}

		/*
		 * TIMESTAMP DAS HORAS
		 */
		Date horaInicio = new Date(Long.parseLong(horaAulaHTTP.getId().getHoraInicio()));
		Date horaFim = new Date(Long.parseLong(horaAulaHTTP.getId().getHoraFim()));

		HoraAulaPK haPK = new HoraAulaPK();
		haPK.setTurma(horaAulaHTTP.getId().getTurma());
		haPK.setAula(aula);

		haPK.setDia(horaAulaHTTP.getId().getDia());
		haPK.setHoraFim(horaFim);
		haPK.setHoraInicio(horaInicio);

		horaAula.setId(haPK);
		horaAula.setStatus(horaAulaHTTP.getStatus());

		// add HoraAula
		this.rnHoraAula.adicionarHoraAula(horaAula);

		return "Horário de aula adicionado com sucesso";

	}

	@POST
	@Path("hora-aula/remover")
	@Consumes("application/json")
	@Produces("text/plain")
	public String removerHoraAulaPorId(String jsonHoraAula) {

		HoraAulaHTTP haHTTP = this.gson.fromJson(jsonHoraAula, HoraAulaHTTP.class);

		HoraAulaPK haPK = new HoraAulaPK();
		haPK.setTurma(haHTTP.getId().getTurma());

		AulaHTTP aulaHTTP = haHTTP.getId().getAula();

		Professor professor = new Professor();
		professor.setEmail(aulaHTTP.getId().getProfessor().getEmail());
		professor.setId(aulaHTTP.getId().getProfessor().getId());
		professor.setListDisciplinas(aulaHTTP.getId().getProfessor().getListDisciplinas());
		professor.setNome(aulaHTTP.getId().getProfessor().getNome());
		professor.setRg(aulaHTTP.getId().getProfessor().getRg());
		professor.setStatus(aulaHTTP.getId().getProfessor().getStatus());
		professor.setTelefone(aulaHTTP.getId().getProfessor().getTelefone());
		professor.setUsuario(aulaHTTP.getId().getProfessor().getUsuario());
		professor.setDataNascimento(Datas.converterDateToCalendar(
				new Date(Long.parseLong(aulaHTTP.getId().getProfessor().getDataNascimento()))));

		Aula aula = new Aula();
		aula.getId().setProfessor(professor);
		aula.getId().setDisciplina(aulaHTTP.getId().getDisciplina());
		aula.getId().setSala(aulaHTTP.getId().getSala());

		haPK.setAula(aula);

		HoraAula ha = this.rnHoraAula.buscarHoraAulaPorId(haPK);
		this.rnHoraAula.removerHoraAula(ha);

		return "O horário de aula foi removida da sua grade!";

	}

	@POST
	@Path("removeGradeAula/por-turma")
	@Consumes("application/json")
	@Produces("text/plain")
	public String removerGradeAula_PorTurma(String jsonTurmaHTTP) {

		TurmaHTTP turmaHTTP = this.gson.fromJson(jsonTurmaHTTP, TurmaHTTP.class);

		this.rnHoraAula.removerPorIdTurma(turmaHTTP.getId());

		return "Removido com sucesso";

	}

	@GET
	@Path("listar-horarios-por-professor/{idProfessor}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public String listarHoraAulaPorProfessor(@PathParam("idProfessor") String jsonProfessor) {

		try {
			List<HoraAula> lista = this.rnHoraAula.listarHoraAulaPorProfessor(Long.parseLong(jsonProfessor));
			return this.gson.toJson(lista);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GET
	@Path("listar-horarios-por-professor-e-turma/{idProfessor}/{idTurma}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public String listarHoraAulaPorProfessorTurma(@PathParam("idProfessor") String jsonProfessor,
			@PathParam("idTurma") String jsonTurma) {

		try {
			List<HoraAula> lista = this.rnHoraAula.listarHoraAulaPorProfessorTurma(Long.parseLong(jsonProfessor),
					Long.parseLong(jsonTurma));
			return this.gson.toJson(lista);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GET
	@Path("listar-horarios-por-aluno/{idAluno}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public String listarHoraAulaPorAluno(@PathParam("idAluno") String idAluno) {
		
		try {
			List<HoraAula> lista = this.rnHoraAula.listarHoraAulaPorAluno(Long.parseLong(idAluno));
			return this.gson.toJson(lista);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}