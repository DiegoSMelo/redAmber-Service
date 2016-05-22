package br.com.sistema.redAmber.rn;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.sistema.redAmber.DAO.IDAOReservaSala;
import br.com.sistema.redAmber.DAO.factory.DAOFactory;
import br.com.sistema.redAmber.basicas.ReservaSala;
import br.com.sistema.redAmber.exceptions.RNException;
import br.com.sistema.redAmber.util.Mensagens;

public class RNReservaSala {

	private IDAOReservaSala daoReservaSala;
	
	public RNReservaSala() {
		daoReservaSala = DAOFactory.getDaoReservaSala();
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
		
		Date reserva = dataReserva.getTime();
		Date hoje = new Date();
		if (reserva.getDate() == hoje.getDate() && reserva.getMonth() == hoje.getMonth() &&
				reserva.getYear() == hoje.getYear()) {
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
	
	public List<ReservaSala> buscarReservasPorProfessorDataReserva(Long idProfessor, 
			Calendar dataReserva) {
		return this.daoReservaSala.buscarReservasPorProfessorDataReserva(idProfessor, dataReserva);
	}
}