package br.com.sistema.redAmber.ws;

import java.util.ArrayList;
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

import br.com.sistema.redAmber.basicas.BuscaReserva;
import br.com.sistema.redAmber.basicas.DuracaoAula;
import br.com.sistema.redAmber.basicas.Professor;
import br.com.sistema.redAmber.basicas.ReservaSala;
import br.com.sistema.redAmber.basicas.Sala;
import br.com.sistema.redAmber.basicas.enums.StatusReserva;
import br.com.sistema.redAmber.basicas.http.BuscaReservaHTTP;
import br.com.sistema.redAmber.basicas.http.DuracaoAulaHTTP;
import br.com.sistema.redAmber.basicas.http.ProfessorHTTP;
import br.com.sistema.redAmber.basicas.http.ReservaSalaHTTP;
import br.com.sistema.redAmber.basicas.http.SalaHTTP;
import br.com.sistema.redAmber.exceptions.RNException;
import br.com.sistema.redAmber.rn.RNDuracaoAula;
import br.com.sistema.redAmber.rn.RNProfessor;
import br.com.sistema.redAmber.rn.RNReservaSala;
import br.com.sistema.redAmber.rn.RNSala;
import br.com.sistema.redAmber.util.Datas;

@Path("/reservasalaws")
public class ReservaSalaWS {

	private RNReservaSala rnReservaSala;
	private RNProfessor rnProfessor;
	private RNSala rnSala;
	private RNDuracaoAula rnDuracaoAula;
	private Gson gson;

	public ReservaSalaWS() {
		this.rnReservaSala = new RNReservaSala();
		this.rnProfessor = new RNProfessor();
		this.rnSala = new RNSala();
		this.rnDuracaoAula = new RNDuracaoAula();
		this.gson = new Gson();
	}

	@POST
	@Path("salvar")
	@Consumes("application/json")
	@Produces("text/plain")
	public String reservarSala(String jsonReservaSala) {

		ReservaSala reservaSala = new ReservaSala();
		Professor professor = new Professor();
		Sala sala = new Sala();
		DuracaoAula horarioReserva = new DuracaoAula();

		ReservaSalaHTTP reservaSalaHTTP = this.gson.fromJson(jsonReservaSala, ReservaSalaHTTP.class);
		ProfessorHTTP professorHTTP = reservaSalaHTTP.getProfessor();
		SalaHTTP salaHTTP = reservaSalaHTTP.getSala();
		DuracaoAulaHTTP duracaoAulaHTTP = reservaSalaHTTP.getHorarioReserva();

		professor = rnProfessor.buscarPorId(professorHTTP.getId());
		sala = rnSala.buscarPorId(salaHTTP.getId());
		horarioReserva = rnDuracaoAula.buscarPorId(duracaoAulaHTTP.getId());

		Calendar dataRequisicao = Calendar.getInstance();
		Date hoje = new Date();
		if (reservaSalaHTTP.getId() == null) {
			dataRequisicao.setTime(hoje);
		} else {
			dataRequisicao = Datas
					.converterDateToCalendar(new Date(Long.parseLong(reservaSalaHTTP.getDataRequisicao())));
		}
		Calendar dataReserva = Datas
				.converterDateToCalendar(new Date(Long.parseLong(reservaSalaHTTP.getDataReserva())));

		reservaSala.setId(reservaSalaHTTP.getId());
		reservaSala.setProfessor(professor);
		reservaSala.setSala(sala);
		reservaSala.setDataRequisicao(dataRequisicao);
		reservaSala.setDataReserva(dataReserva);
		reservaSala.setHorarioReserva(horarioReserva);
		reservaSala.setObservacao(reservaSalaHTTP.getObservacao());
		reservaSala.setResposta(reservaSalaHTTP.getResposta());

		if (reservaSalaHTTP.getId() == null) {
			reservaSala.setStatus(StatusReserva.PENDENTE);
		} else {
			reservaSala.setStatus(reservaSalaHTTP.getStatus());
		}

		this.rnReservaSala.salvar(reservaSala);
		return "Reserva de sala realizada com sucesso!";
	}

	@GET
	@Path("buscar-por-id/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public String buscarReservaSalaPorId(@PathParam("id") String id) {

		ReservaSala reservaSala = this.rnReservaSala.buscarReservaSalaPorId(Long.parseLong(id));
		return this.gson.toJson(reservaSala);
	}

	@GET
	@Path("listar")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public String listarReservasSala() {

		List<ReservaSala> reservas;
		reservas = this.rnReservaSala.listarTodos();
		return this.gson.toJson(reservas);
	}

	@GET
	@Path("listar-pendentes")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public String listarReservasSalaPendentes() {

		List<ReservaSala> pendentes;
		pendentes = this.rnReservaSala.buscarReservasPendentes();
		return this.gson.toJson(pendentes);
	}

	@GET
	@Path("verificar-por-data-horario/{idSala}/{dataReserva}/{idHorario}")
	@Produces("application/json")
	public String verificarReservasPorDataReservaHorario(@PathParam("idSala") String idSala,
			@PathParam("dataReserva") String dataReserva, @PathParam("idHorario") String idHorario) {

		ReservaSala resultado;
		Long salaId = Long.parseLong(idSala);
		Calendar data = Datas.converterDateToCalendar(new Date(Long.parseLong(dataReserva)));
		Long horario = Long.parseLong(idHorario);

		try {
			resultado = this.rnReservaSala.verificarReservasPorDataReservaHorario(salaId, data, horario);
		} catch (RNException e) {
			e.getMessage();
			String retorno = "data anterior";
			return this.gson.toJson(retorno);
		}
		return this.gson.toJson(resultado);
	}

	@GET
	@Path("quantidade-do-dia")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public String consultarQuantidadeDeHoje() {

		Integer retorno;
		retorno = this.rnReservaSala.consultarQuantidadeDeHoje();
		return this.gson.toJson(retorno);
	}

	@GET
	@Path("buscar-por-professor/{idProfessor}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public String buscarReservasPorProfessor(@PathParam("idProfessor") String idProfessor) {
		try {
			List<ReservaSala> lista = this.rnReservaSala
					.buscarReservasPorProfessor(Long.parseLong(idProfessor));
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
	@Path("buscar-por-professor-data-reserva/{idProfessor}/{dataReserva}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public String buscarReservasPorProfessorDataReserva(@PathParam("idProfessor") String idProfessor,
			@PathParam("dataReserva") String dataReserva) {

		try {
			Calendar data = Datas.converterDateToCalendar(new Date(Long.parseLong(dataReserva)));
			List<ReservaSala> lista = this.rnReservaSala
					.buscarReservasPorProfessorDataReserva(Long.parseLong(idProfessor), data);
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
	@Path("salvar-get/{idProfessor}/{idSala}/{idDuracao}/{dataReserva}/{observacao}")
	@Produces("text/plain")
	public String reservarSalaGet(@PathParam("idProfessor") String idProfessor, @PathParam("idSala") String idSala,
			@PathParam("idDuracao") String idDuracao, @PathParam("dataReserva") String dataReserva,
			@PathParam("observacao") String observacao) {

		ReservaSala reservaJaExiste = null;
		Long salaId = Long.parseLong(idSala);
		Calendar dataRes = Datas.converterDateToCalendar(new Date(Long.parseLong(dataReserva)));
		Long horario = Long.parseLong(idDuracao);

		try {
			reservaJaExiste = this.rnReservaSala.verificarReservasPorDataReservaHorario(salaId, dataRes, horario);
		} catch (RNException e) {
			e.getMessage();
			return "data anterior";
		}

		if (reservaJaExiste == null) {
			ReservaSala reserva = new ReservaSala();
			Professor professor = new Professor();
			Sala sala = new Sala();
			DuracaoAula duracao = new DuracaoAula();

			professor = this.rnProfessor.buscarPorId(Long.parseLong(idProfessor));
			sala = this.rnSala.buscarPorId(Long.parseLong(idSala));
			duracao = this.rnDuracaoAula.buscarPorId(Long.parseLong(idDuracao));
			Calendar data = Datas.converterDateToCalendar(new Date(Long.parseLong(dataReserva)));
			Calendar requisicao = Datas.converterDateToCalendar(new Date());

			reserva.setProfessor(professor);
			reserva.setSala(sala);
			reserva.setHorarioReserva(duracao);
			reserva.setDataReserva(data);
			reserva.setDataRequisicao(requisicao);
			reserva.setObservacao(observacao);
			reserva.setStatus(StatusReserva.PENDENTE);

			this.rnReservaSala.salvar(reserva);
			return "sucesso!";
		} else {
			return "ja reservado!";
		}
	}
	
	@POST
	@Path("buscar-por-parametros")
	@Consumes("application/json")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public String listarReservasPorParametros(String pesquisa) {
				
		BuscaReserva consulta = new BuscaReserva();
		BuscaReservaHTTP consultaHTTP = this.gson.fromJson(pesquisa, BuscaReservaHTTP.class);
		
		if (consultaHTTP.getIdProfessor() != null) {
			consulta.setIdProfessor(consultaHTTP.getIdProfessor());
		}
		if (consultaHTTP.getStatus() != null) {
			consulta.setStatus(consultaHTTP.getStatus());
		}
		if (consultaHTTP.getDataReserva() != null) {
			Date data = new Date(consultaHTTP.getDataReserva());
			consulta.setDataReserva(data);
		}
		if (consultaHTTP.getDataRequisicao() != null) {
			Date data = new Date(consultaHTTP.getDataRequisicao());
			consulta.setDataRequisicao(data);
		}
		
		List<ReservaSala> listaReservas = new ArrayList<ReservaSala>();
		try {
			listaReservas = this.rnReservaSala.listarReservasPorParametros(consulta);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.gson.toJson(listaReservas);
	}
}