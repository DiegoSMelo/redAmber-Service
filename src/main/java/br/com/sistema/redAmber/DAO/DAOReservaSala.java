package br.com.sistema.redAmber.DAO;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.sistema.redAmber.DAO.generics.DAOGeneric;
import br.com.sistema.redAmber.basicas.ReservaSala;
import br.com.sistema.redAmber.basicas.enums.StatusReserva;

public class DAOReservaSala extends DAOGeneric<ReservaSala> implements IDAOReservaSala {

	public DAOReservaSala(EntityManager em) {
		super(em);
	}
	
	@Override
	public List<ReservaSala> buscarReservasPendentes() {
		try {
			String jpql = "SELECT r FROM ReservaSala r WHERE r.status = :status";
			
			TypedQuery<ReservaSala> result = this.entityManager.
					createQuery(jpql, ReservaSala.class);
			result.setParameter("status", StatusReserva.PENDENTE);
			return result.getResultList();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public ReservaSala verificarReservasPorDataReservaHorario(Long idSala, 
			Calendar dataReserva, Long idHorario) {
		try {
			String jpql = "SELECT r FROM ReservaSala r WHERE r.sala.id = :idSala"
					+ " AND r.dataReserva = :dataReserva AND r.horarioReserva.id = :idHorario";
			TypedQuery<ReservaSala> result = this.entityManager.
					createQuery(jpql, ReservaSala.class);
			result.setParameter("idSala", idSala);
			result.setParameter("dataReserva", dataReserva);
			result.setParameter("idHorario", idHorario);
			return result.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}