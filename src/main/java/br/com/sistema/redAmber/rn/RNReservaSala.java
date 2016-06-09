package br.com.sistema.redAmber.rn;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.sistema.redAmber.DAO.IDAODuracaoAula;
import br.com.sistema.redAmber.DAO.IDAOReservaSala;
import br.com.sistema.redAmber.DAO.factory.DAOFactory;
import br.com.sistema.redAmber.basicas.BuscaReserva;
import br.com.sistema.redAmber.basicas.DuracaoAula;
import br.com.sistema.redAmber.basicas.ReservaSala;
import br.com.sistema.redAmber.exceptions.RNException;
import br.com.sistema.redAmber.util.Mensagens;

public class RNReservaSala {

	private IDAOReservaSala daoReservaSala;
	private IDAODuracaoAula daoDuracaoAula;
	
	public RNReservaSala() {
		daoReservaSala = DAOFactory.getDaoReservaSala();
		daoDuracaoAula = DAOFactory.getDaoDuracaoAula();
	}
	
	public void salvar(ReservaSala reservaSala) {
		ReservaSala reservaRetorno = null;
		if (reservaSala.getId() != null) {
			reservaRetorno = daoReservaSala.consultarPorId(reservaSala.getId());
		}
		if (reservaRetorno == null) {
			this.daoReservaSala.inserir(reservaSala);
		} else {
			reservaSala.setId(reservaRetorno.getId());
			this.daoReservaSala.alterar(reservaSala);
		}
	}
	
	public ReservaSala buscarReservaSalaPorId(Long id) {
		return daoReservaSala.consultarPorId(id);
	}
	
	public List<ReservaSala> listarTodos() {
		return daoReservaSala.consultarTodos();
	}
	
	public List<ReservaSala> buscarReservasPendentes() {
		return daoReservaSala.buscarReservasPendentes();
	}
	
	@SuppressWarnings("deprecation")
	public ReservaSala verificarReservasPorDataReservaHorario(Long idSala, 
			Calendar dataReserva, Long idHorario) throws RNException {
		
		System.err.println("!!! ID DO HORÁRIO: " + idHorario);
		
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
			return daoReservaSala.verificarReservasPorDataReservaHorario(idSala, dataReserva, idHorario);
		}
		if (reserva.before(hoje)) {
			throw new RNException(Mensagens.m8);
		}
		return daoReservaSala.verificarReservasPorDataReservaHorario(idSala, dataReserva, idHorario);
	}
	
	public Integer consultarQuantidadeDeHoje() {
		return daoReservaSala.consultarQuantidadeDeHoje();
	}
	
	public List<ReservaSala> buscarReservasPorProfessor(Long idProfessor) {
		return this.daoReservaSala.buscarReservasPorProfessor(idProfessor);
	}
	
	public List<ReservaSala> buscarReservasPorProfessorDataReserva(Long idProfessor, 
			Calendar dataReserva) {
		return this.daoReservaSala.buscarReservasPorProfessorDataReserva(idProfessor, dataReserva);
	}
	
	public List<ReservaSala> listarReservasPorParametros(BuscaReserva consulta) {
		return this.daoReservaSala.listarReservasPorParametros(consulta);
	}
}