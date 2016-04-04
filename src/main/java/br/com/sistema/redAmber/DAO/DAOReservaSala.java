package br.com.sistema.redAmber.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.sistema.redAmber.DAO.generics.DAOGeneric;
import br.com.sistema.redAmber.basicas.ReservaSala;
import br.com.sistema.redAmber.exceptions.DAOException;
import br.com.sistema.redAmber.util.Mensagens;

public class DAOReservaSala extends DAOGeneric<ReservaSala> implements IDAOReservaSala {

	public DAOReservaSala(EntityManager em) {
		super(em);
	}

	@Override
	public List<ReservaSala> consultarPorDataSolicitacao(ReservaSala rS) throws DAOException {
		
		String sql = "SELECT r FROM ReservaSala r WHERE r.professor = :professor";
		
		try {
			if (rS.getDataSolicitacao() != null && rS.getDataReserva() == null) {
				sql += " AND r.dataSolicitacao = :dataSolicitacao";
			}
			if (rS.getDataSolicitacao() == null && rS.getDataReserva() != null) {
				sql += " AND r.dataReserva = :dataReserva";
			}
			if (rS.getDataSolicitacao() != null && rS.getDataReserva() != null) {
				sql += " AND r.dataSolicitacao = :dataSolicitacao AND r.dataReserva = :dataReserva";
			}
			
			TypedQuery<ReservaSala> result = entityManager.createQuery(sql, ReservaSala.class);
			result.setParameter("professor", rS.getProfessor());
			
			if (rS.getDataSolicitacao() != null && rS.getDataReserva() == null) {
				result.setParameter("dataSolicitacao", rS.getDataSolicitacao());
			}
			if (rS.getDataSolicitacao() == null && rS.getDataReserva() != null) {
				result.setParameter("dataReserva", rS.getDataReserva());
			}
			if (rS.getDataSolicitacao() != null && rS.getDataReserva() != null) {
				result.setParameter("dataSolicitacao", rS.getDataSolicitacao());
				result.setParameter("dataReserva", rS.getDataReserva());
			}
			return result.getResultList();
		} catch (Exception e) {
			throw new DAOException(Mensagens.m6);
		}
	}

	@Override
	public ReservaSala consultarPorSalaDataReserva(ReservaSala rS) throws DAOException {
		
		String sql = "SELECT r FROM ReservaSala r WHERE r.sala = :sala AND r.dataReserva = :dataReserva";
		TypedQuery<ReservaSala> result = entityManager.createQuery(sql, ReservaSala.class);
		result.setParameter("sala", rS.getSalaReserva());
		result.setParameter("dataReserva", rS.getDataReserva());
		return result.getSingleResult();
		// FALTA PENSAR NOS CHOQUES DE HORÁRIO
		// É PRECISO REPENSAR TUDO DO INÍCIO
	}
}