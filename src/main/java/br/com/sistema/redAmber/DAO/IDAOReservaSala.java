package br.com.sistema.redAmber.DAO;

import java.util.List;

import br.com.sistema.redAmber.DAO.generics.IDAOGeneric;
import br.com.sistema.redAmber.basicas.ReservaSala;
import br.com.sistema.redAmber.exceptions.DAOException;

public interface IDAOReservaSala extends IDAOGeneric<ReservaSala> {
	
	public List<ReservaSala> consultarPorDataSolicitacao(ReservaSala reservaSala) throws DAOException;
	
	public ReservaSala consultarPorSalaDataReserva(ReservaSala reservaSala) throws DAOException;
}