package br.com.sistema.redAmber.DAO;

import java.util.Calendar;
import java.util.List;

import br.com.sistema.redAmber.DAO.generics.IDAOGeneric;
import br.com.sistema.redAmber.basicas.BuscaReserva;
import br.com.sistema.redAmber.basicas.ReservaSala;

public interface IDAOReservaSala extends IDAOGeneric<ReservaSala> {
	
	public List<ReservaSala> buscarReservasPendentes();
	public ReservaSala verificarReservasPorDataReservaHorario(Long idSala, 
			Calendar dataReserva, Long idHorario);
	public Integer consultarQuantidadeDeHoje();
	public List<ReservaSala> buscarReservasPorProfessor(Long idProfessor);
	public List<ReservaSala> buscarReservasPorProfessorDataReserva(Long idProfessor, 
			Calendar dataReserva);
	public List<ReservaSala> listarReservasPorParametros(BuscaReserva consulta);
}