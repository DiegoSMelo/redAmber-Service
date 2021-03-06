package br.com.sistema.redAmber.rn;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.sistema.redAmber.DAO.IDAODuracaoAula;
import br.com.sistema.redAmber.DAO.IDAOReservaEquipamento;
import br.com.sistema.redAmber.DAO.factory.DAOFactory;
import br.com.sistema.redAmber.basicas.BuscaReserva;
import br.com.sistema.redAmber.basicas.DuracaoAula;
import br.com.sistema.redAmber.basicas.ReservaEquipamento;
import br.com.sistema.redAmber.exceptions.RNException;
import br.com.sistema.redAmber.util.Mensagens;

public class RNReservaEquipamento {

	private IDAOReservaEquipamento daoReservaEquipamento;
	private IDAODuracaoAula daoDuracaoAula;
	
	public RNReservaEquipamento() {
		daoReservaEquipamento = DAOFactory.getDaoReservaEquipamento();
		daoDuracaoAula = DAOFactory.getDaoDuracaoAula();
	}
	
	public void salvar(ReservaEquipamento reservaEquipamento) {

		ReservaEquipamento reservaRetorno = null;
		if (reservaEquipamento.getId() != null) {
			reservaRetorno = daoReservaEquipamento.consultarPorId(reservaEquipamento.getId());
		}
		if (reservaRetorno == null) {
			this.daoReservaEquipamento.inserir(reservaEquipamento);
		} else {
			reservaEquipamento.setId(reservaRetorno.getId());
			this.daoReservaEquipamento.alterar(reservaEquipamento);
		}
	}
	
	public ReservaEquipamento buscarReservaEquipamentoPorId(Long id) {
		return daoReservaEquipamento.consultarPorId(id);
	}
	
	public List<ReservaEquipamento> listarTodos() {
		return daoReservaEquipamento.consultarTodos();
	}
	
	public List<ReservaEquipamento> buscarReservasPendentes() {
		return daoReservaEquipamento.buscarReservasPendentes();
	}
	
	@SuppressWarnings("deprecation")
	public ReservaEquipamento verificarReservasPorDataReservaHorario(Long idEquipamento, 
			Calendar dataReserva, Long idHorario) throws RNException {
		
		Date reserva = dataReserva.getTime();
		Date hoje = new Date();
		int horaHoje = hoje.getHours();
		int minutoHoje = hoje.getMinutes();
		
		DuracaoAula duracao = this.daoDuracaoAula.consultarPorId(idHorario);
		int horaInicio = duracao.getHoraInicio().getHours();
		int minutoInicio = duracao.getHoraInicio().getMinutes();
		
		if (reserva.getDate() == hoje.getDate() && reserva.getMonth() == hoje.getMonth() &&
				reserva.getYear() == hoje.getYear()) {
			if ((horaInicio < horaHoje) || (horaInicio == horaHoje && minutoInicio < minutoHoje)) {
				throw new RNException(Mensagens.m8);
			}
			return daoReservaEquipamento.verificarReservasPorDataReservaHorario(idEquipamento, 
					dataReserva, idHorario);
		}
		if (reserva.before(hoje)) {
			throw new RNException(Mensagens.m8);
		}
		
		return daoReservaEquipamento.verificarReservasPorDataReservaHorario(idEquipamento, 
				dataReserva, idHorario);
	}
	
	public Integer consultarQuantidadeDeHoje() {
		return daoReservaEquipamento.consultarQuantidadeDeHoje();
	}
	
	public List<ReservaEquipamento> buscarReservasPorProfessor(Long idProfessor) {
		return this.daoReservaEquipamento.buscarReservasPorProfessor(idProfessor);
	}
	
	public List<ReservaEquipamento> buscarReservasPorProfessorDataReserva(Long idProfessor, 
			Calendar dataReserva) {
		return this.daoReservaEquipamento.buscarReservasPorProfessorDataReserva(idProfessor, dataReserva);
	}
	
	public List<ReservaEquipamento> listarReservasPorParametros(BuscaReserva consulta) {
		return this.daoReservaEquipamento.listarReservasPorParametros(consulta);
	}
}