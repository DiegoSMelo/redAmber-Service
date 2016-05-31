package br.com.sistema.redAmber.DAO;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.sistema.redAmber.DAO.generics.DAOGeneric;
import br.com.sistema.redAmber.basicas.DuracaoAula;
import br.com.sistema.redAmber.basicas.enums.StatusDuracaoAula;
import br.com.sistema.redAmber.basicas.enums.TipoTurno;
import br.com.sistema.redAmber.exceptions.DAOException;
import br.com.sistema.redAmber.util.Mensagens;

public class DAODuracaoAula extends DAOGeneric<DuracaoAula> implements IDAODuracaoAula {

	public DAODuracaoAula(EntityManager em) {
		super(em);
	}

	@Override
	public DuracaoAula consultarPorHoraInicioHoraFim(Date horaInicio, Date horaFim) throws DAOException {
		
		try {
			String sql = "SELECT h FROM DuracaoAula h WHERE h.status = :status";
			
			if (horaInicio != null && horaFim == null) {
				sql += " AND h.horaInicio = :horaInicio";
			}
			if (horaInicio == null && horaFim != null) {
				sql += " AND h.horaFim = :horaFim";
			}
			if (horaInicio != null && horaFim != null) {
				sql += " AND (h.horaInicio = :horaInicio OR h.horaFim = :horaFim)";
			}
			
			TypedQuery<DuracaoAula> result = entityManager.createQuery(sql, DuracaoAula.class);
			result.setParameter("status", StatusDuracaoAula.ATIVA);
			
			if (horaInicio != null && horaFim == null) {
				result.setParameter("horaInicio", horaInicio);
			}
			if (horaInicio == null && horaFim != null) {
				result.setParameter("horaFim", horaFim);
			}
			if (horaInicio != null && horaFim != null) {
				result.setParameter("horaInicio", horaInicio);
				result.setParameter("horaFim", horaFim);
			}
			DuracaoAula resultado = result.getSingleResult();
			return resultado;
		} catch (NoResultException e2) {
			return null;
		} catch (Exception e) {
			throw new DAOException(Mensagens.m6);
		}
	}
	
	@Override
	public List<DuracaoAula> consultarPorTurno(TipoTurno turno) {
		
		try {
			String sql = "SELECT h FROM DuracaoAula h WHERE h.status = :status";
			
			if (turno != null) {
				sql += " AND h.turno = :turno";
			}
			
			TypedQuery<DuracaoAula> result = entityManager.createQuery(sql, DuracaoAula.class);
			result.setParameter("status", StatusDuracaoAula.ATIVA);
			
			if (turno != null) {
				result.setParameter("turno", turno);
			}
			return result.getResultList();
		} catch (NoResultException e2) {
			e2.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}