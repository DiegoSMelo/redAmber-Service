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
import br.com.sistema.redAmber.basicas.Equipamento;
import br.com.sistema.redAmber.basicas.Professor;
import br.com.sistema.redAmber.basicas.ReservaEquipamento;
import br.com.sistema.redAmber.basicas.enums.StatusReserva;
import br.com.sistema.redAmber.basicas.http.BuscaReservaHTTP;
import br.com.sistema.redAmber.basicas.http.DuracaoAulaHTTP;
import br.com.sistema.redAmber.basicas.http.EquipamentoHTTP;
import br.com.sistema.redAmber.basicas.http.ProfessorHTTP;
import br.com.sistema.redAmber.basicas.http.ReservaEquipamentoHTTP;
import br.com.sistema.redAmber.exceptions.RNException;
import br.com.sistema.redAmber.rn.RNDuracaoAula;
import br.com.sistema.redAmber.rn.RNEquipamento;
import br.com.sistema.redAmber.rn.RNProfessor;
import br.com.sistema.redAmber.rn.RNReservaEquipamento;
import br.com.sistema.redAmber.util.Datas;

@Path("/reservaequipamentows")
public class ReservaEquipamentoWS {

	private RNReservaEquipamento rnReservaEquipamento;
	private RNProfessor rnProfessor;
	private RNEquipamento rnEquipamento;
	private RNDuracaoAula rnDuracaoAula;
	private Gson gson;
	
	public ReservaEquipamentoWS() {
		this.rnReservaEquipamento = new RNReservaEquipamento();
		this.rnProfessor = new RNProfessor();
		this.rnEquipamento = new RNEquipamento();
		this.rnDuracaoAula = new RNDuracaoAula();
		this.gson = new Gson();
	}
	
	@POST
	@Path("salvar")
	@Consumes("application/json")
	@Produces("text/plain")
	public String reservarEquipamento(String jsonReservaEquipamento) {
		
		ReservaEquipamento reservaEquipamento = new ReservaEquipamento();
		Professor professor = new Professor();
		Equipamento equipamento = new Equipamento();
		DuracaoAula horarioReserva = new DuracaoAula();
		
		ReservaEquipamentoHTTP reservaEquipamentoHTTP = this.gson.
				fromJson(jsonReservaEquipamento, ReservaEquipamentoHTTP.class);
		ProfessorHTTP professorHTTP = reservaEquipamentoHTTP.getProfessor();
		EquipamentoHTTP equipamentoHTTP = reservaEquipamentoHTTP.getEquip();
		DuracaoAulaHTTP duracaoAulaHTTP = reservaEquipamentoHTTP.getHorarioReserva();
		
		professor = rnProfessor.buscarPorId(professorHTTP.getId());
		equipamento = rnEquipamento.buscarPorId(equipamentoHTTP.getId());
		horarioReserva = rnDuracaoAula.buscarPorId(duracaoAulaHTTP.getId());
		
		Calendar dataRequisicao = Calendar.getInstance();
		Date hoje = new Date();
		if (reservaEquipamentoHTTP.getId() == null) {
			dataRequisicao.setTime(hoje);
		} else {
			dataRequisicao = Datas.converterDateToCalendar(new Date(Long.parseLong(reservaEquipamentoHTTP.getDataRequisicao())));
		}		
		Calendar dataReserva = Datas.converterDateToCalendar(new Date(Long.parseLong(reservaEquipamentoHTTP.getDataReserva())));
		
		reservaEquipamento.setId(reservaEquipamentoHTTP.getId());
		reservaEquipamento.setProfessor(professor);
		reservaEquipamento.setEquip(equipamento);
		reservaEquipamento.setDataRequisicao(dataRequisicao);
		reservaEquipamento.setDataReserva(dataReserva);
		reservaEquipamento.setHorarioReserva(horarioReserva);
		reservaEquipamento.setObservacao(reservaEquipamentoHTTP.getObservacao());
		reservaEquipamento.setResposta(reservaEquipamentoHTTP.getResposta());
		
		if (reservaEquipamentoHTTP.getId() == null) {
			reservaEquipamento.setStatus(StatusReserva.PENDENTE);
		} else {
			reservaEquipamento.setStatus(reservaEquipamentoHTTP.getStatus());
		}
		
		this.rnReservaEquipamento.salvar(reservaEquipamento);
		return "Reserva de equipamento realizada com sucesso!";
	}
	
	@GET
	@Path("buscar-por-id/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	public String buscarReservaEquipamentoPorId(@PathParam("id") String id) {
		
		ReservaEquipamento reservaEquipamento = this.rnReservaEquipamento.
				buscarReservaEquipamentoPorId(Long.parseLong(id));
		return this.gson.toJson(reservaEquipamento);
		
	}
	
	@GET
	@Path("listar")
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	public String listarReservasEquipamento() {
		
		List<ReservaEquipamento> reservas;
		reservas = this.rnReservaEquipamento.listarTodos();
		return this.gson.toJson(reservas);
	}

	@GET
	@Path("listar-pendentes")
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	public String listarReservasEquipamentoPendentes() {
		
		List<ReservaEquipamento> pendentes;
		pendentes = this.rnReservaEquipamento.buscarReservasPendentes();
		return this.gson.toJson(pendentes);
	}
	
	@GET
	@Path("verificar-por-data-horario/{idEquipamento}/{dataReserva}/{idHorario}")
	@Produces("application/json")
	public String verificarReservasPorDataReservaHorario(@PathParam("idEquipamento") String idEquipamento, 
			@PathParam("dataReserva") String dataReserva, 
			@PathParam("idHorario") String idHorario) {
		
		ReservaEquipamento resultado;
		Long idEquip = Long.parseLong(idEquipamento);
		Calendar data = Datas.converterDateToCalendar(new Date(Long.parseLong(dataReserva)));
		Long horario = Long.parseLong(idHorario);
		
		try {
			resultado = this.rnReservaEquipamento.verificarReservasPorDataReservaHorario(idEquip, 
					data, horario);
		} catch (RNException e) {
			e.getMessage();
			String retorno = "data anterior";
			return this.gson.toJson(retorno);
		}
		return this.gson.toJson(resultado);
	}
	
	@GET
	@Path("quantidade-do-dia")
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	public String consultarQuantidadeDeHoje() {
		
		Integer retorno;
		retorno = this.rnReservaEquipamento.consultarQuantidadeDeHoje();
		return this.gson.toJson(retorno);
	}
	
	@GET
	@Path("buscar-por-professor/{idProfessor}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	public String buscarReservasPorProfessor(@PathParam("idProfessor") String idProfessor) {
		try {
			List<ReservaEquipamento> lista = this.rnReservaEquipamento.
					buscarReservasPorProfessor(Long.parseLong(idProfessor));
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
			List<ReservaEquipamento> lista = this.rnReservaEquipamento.
					buscarReservasPorProfessorDataReserva(Long.parseLong(idProfessor), data);
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
	@Path("salvar-get/{idProfessor}/{idEquipamento}/{idDuracao}/{dataReserva}/{observacao}")
	@Produces("text/plain")
	public String reservarEquipamentoGet(@PathParam("idProfessor") String idProfessor,
			@PathParam("idEquipamento") String idEquipamento, @PathParam("idDuracao") String idDuracao,
			@PathParam("dataReserva") String dataReserva, @PathParam("observacao") String observacao) {

		ReservaEquipamento reservaJaExiste = null;
		Long equipId = Long.parseLong(idEquipamento);
		Calendar dataRes = Datas.converterDateToCalendar(new Date(Long.parseLong(dataReserva)));
		Long horario = Long.parseLong(idDuracao);
		
		try {
			reservaJaExiste = this.rnReservaEquipamento.verificarReservasPorDataReservaHorario(equipId, 
					dataRes, horario);
		} catch (RNException e) {
			e.getMessage();
			return "data anterior";
		}
		
		if (reservaJaExiste == null) {
			ReservaEquipamento reserva = new ReservaEquipamento();
			Professor professor = new Professor();
			Equipamento equipamento = new Equipamento();
			DuracaoAula duracao = new DuracaoAula();
			
			professor = this.rnProfessor.buscarPorId(Long.parseLong(idProfessor));
			equipamento = this.rnEquipamento.buscarPorId(Long.parseLong(idEquipamento));
			duracao = this.rnDuracaoAula.buscarPorId(Long.parseLong(idDuracao));
			Calendar data = Datas.converterDateToCalendar(new Date(Long.parseLong(dataReserva)));
			Calendar requisicao = Datas.converterDateToCalendar(new Date());
			
			reserva.setProfessor(professor);
			reserva.setEquip(equipamento);
			reserva.setHorarioReserva(duracao);
			reserva.setDataReserva(data);
			reserva.setDataRequisicao(requisicao);
			reserva.setObservacao(observacao);
			reserva.setStatus(StatusReserva.PENDENTE);
			
			this.rnReservaEquipamento.salvar(reserva);
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
		
		List<ReservaEquipamento> listaReservas = new ArrayList<ReservaEquipamento>();
		try {
			listaReservas = this.rnReservaEquipamento.listarReservasPorParametros(consulta);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.gson.toJson(listaReservas);
	}
}