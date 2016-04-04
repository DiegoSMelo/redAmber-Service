package br.com.sistema.redAmber.DAO;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.sistema.redAmber.DAO.generics.DAOGeneric;
import br.com.sistema.redAmber.basicas.HoraAula;
import br.com.sistema.redAmber.basicas.enums.StatusHoraAula;
import br.com.sistema.redAmber.exceptions.DAOException;
import br.com.sistema.redAmber.util.Mensagens;

public class DAOHoraAula extends DAOGeneric<HoraAula> implements IDAOHoraAula {

	public DAOHoraAula(EntityManager em) {
		super(em);
	}

	@Override
	public HoraAula consultarPorHoraInicioHoraFim(Date horaInicio, Date horaFim) throws DAOException {
		
		try {
			String sql = "SELECT h FROM HoraAula h WHERE h.status = :status";
			
			if (horaInicio != null && horaFim == null) {
				sql += " AND h.horaInicio = :horaInicio";
			}
			if (horaInicio == null && horaFim != null) {
				sql += " AND h.horaFim = :horaFim";
			}
			if (horaInicio != null && horaFim != null) {
				sql += " AND (h.horaInicio = :horaInicio OR h.horaFim = :horaFim)";
			}
			
			TypedQuery<HoraAula> result = entityManager.createQuery(sql, HoraAula.class);
			result.setParameter("status", StatusHoraAula.ATIVA);
			
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
			HoraAula resultado = result.getSingleResult();
			return resultado;
		} catch (NoResultException e2) {
			return null;
		} catch (Exception e) {
			throw new DAOException(Mensagens.m7);
		}
	}
}