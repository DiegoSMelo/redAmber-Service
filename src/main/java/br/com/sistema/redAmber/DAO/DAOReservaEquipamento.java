package br.com.sistema.redAmber.DAO;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.sistema.redAmber.DAO.generics.DAOGeneric;
import br.com.sistema.redAmber.basicas.ReservaEquipamento;
import br.com.sistema.redAmber.basicas.enums.StatusReserva;

public class DAOReservaEquipamento extends DAOGeneric<ReservaEquipamento> 
implements IDAOReservaEquipamento {

	public DAOReservaEquipamento(EntityManager em) {
		super(em);
	}
	
	@Override
	public List<ReservaEquipamento> buscarReservasPendentes() {
		try {
			String jpql = "SELECT r FROM ReservaEquipamento r WHERE r.status = :status";
			
			TypedQuery<ReservaEquipamento> result = this.entityManager.
					createQuery(jpql, ReservaEquipamento.class);
			result.setParameter("status", StatusReserva.PENDENTE);
			return result.getResultList();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public ReservaEquipamento verificarReservasPorDataReservaHorario(Long idEquipamento, 
			Calendar dataReserva, Long idHorario) {
		
		try {
			System.err.println("DAO idEquipamento: " + idEquipamento);
			System.err.println("DAO dataReserva: " + dataReserva);
			System.err.println("DAO idHorario: " + idHorario);
			
			String jpql = "SELECT r FROM ReservaEquipamento r WHERE r.equip.id = :idEquipamento"
					+ " AND r.dataReserva = :dataReserva AND r.horarioReserva.id = :idHorario";
			TypedQuery<ReservaEquipamento> result = this.entityManager.
					createQuery(jpql, ReservaEquipamento.class);
			result.setParameter("idEquipamento", idEquipamento);
			result.setParameter("dataReserva", dataReserva);
			result.setParameter("idHorario", idHorario);
			return result.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}