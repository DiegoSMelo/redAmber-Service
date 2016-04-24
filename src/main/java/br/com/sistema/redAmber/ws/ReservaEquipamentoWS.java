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

import br.com.sistema.redAmber.basicas.Equipamento;
import br.com.sistema.redAmber.basicas.HoraAula;
import br.com.sistema.redAmber.basicas.Professor;
import br.com.sistema.redAmber.basicas.ReservaEquipamento;
import br.com.sistema.redAmber.basicas.enums.StatusReserva;
import br.com.sistema.redAmber.basicas.http.EquipamentoHTTP;
import br.com.sistema.redAmber.basicas.http.HoraAulaHTTP;
import br.com.sistema.redAmber.basicas.http.ProfessorHTTP;
import br.com.sistema.redAmber.basicas.http.ReservaEquipamentoHTTP;
import br.com.sistema.redAmber.exceptions.RNException;
import br.com.sistema.redAmber.rn.RNEquipamento;
import br.com.sistema.redAmber.rn.RNHoraAula;
import br.com.sistema.redAmber.rn.RNProfessor;
import br.com.sistema.redAmber.rn.RNReservaEquipamento;
import br.com.sistema.redAmber.util.Datas;

@Path("/reservaequipamentows")
public class ReservaEquipamentoWS {

	private RNReservaEquipamento rnReservaEquipamento;
	private RNProfessor rnProfessor;
	private RNEquipamento rnEquipamento;
	private RNHoraAula rnHoraAula;
	private Gson gson;
	
	public ReservaEquipamentoWS() {
		this.rnReservaEquipamento = new RNReservaEquipamento();
		this.rnProfessor = new RNProfessor();
		this.rnEquipamento = new RNEquipamento();
		this.rnHoraAula = new RNHoraAula();
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
		HoraAula horarioReserva = new HoraAula();
		
		ReservaEquipamentoHTTP reservaEquipamentoHTTP = this.gson.
				fromJson(jsonReservaEquipamento, ReservaEquipamentoHTTP.class);
		ProfessorHTTP professorHTTP = reservaEquipamentoHTTP.getProfessor();
		EquipamentoHTTP equipamentoHTTP = reservaEquipamentoHTTP.getEquip();
		HoraAulaHTTP horaAulaHTTP = reservaEquipamentoHTTP.getHorarioReserva();
		
		professor = rnProfessor.buscarPorId(professorHTTP.getId());
		equipamento = rnEquipamento.buscarPorId(equipamentoHTTP.getId());
		horarioReserva = rnHoraAula.buscarPorId(horaAulaHTTP.getId());
		
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
}