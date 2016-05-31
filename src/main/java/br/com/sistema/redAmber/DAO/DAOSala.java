package br.com.sistema.redAmber.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.sistema.redAmber.DAO.generics.DAOGeneric;
import br.com.sistema.redAmber.basicas.Sala;
import br.com.sistema.redAmber.basicas.enums.StatusSala;
import br.com.sistema.redAmber.exceptions.DAOException;
import br.com.sistema.redAmber.util.Mensagens;

public class DAOSala extends DAOGeneric<Sala> implements IDAOSala {

	public DAOSala(EntityManager em) {
		super(em);
	}

	@Override
	public Sala consultarPorDescricao(String descricao) throws DAOException {
		try {
			TypedQuery<Sala> result = entityManager.createQuery("SELECT s FROM Sala s WHERE s.descricao = :descricao",
					Sala.class);
			result.setParameter("descricao", descricao);

			Sala sala = result.getSingleResult();
			return sala;
		} catch (NoResultException e2) {
			e2.printStackTrace();
			return null;
		} catch (Exception e) {
			throw new DAOException(Mensagens.m5);
		}
	}
	
	@Override
	public List<Sala> listarPorDescricao(String descricao) {
		
		try {
			String sql = "SELECT s FROM Sala s WHERE s.status = :status";
			
			if (descricao != null && !descricao.trim().equalsIgnoreCase("") && 
					!descricao.trim().equalsIgnoreCase("null") && !descricao.isEmpty()) {
				sql += " AND s.descricao LIKE :descricao";
			}
			
			TypedQuery<Sala> result = entityManager.createQuery(sql, Sala.class);
			result.setParameter("status", StatusSala.ATIVA);
			
			if (descricao != null && !descricao.trim().equalsIgnoreCase("") && 
					!descricao.trim().equalsIgnoreCase("null") && !descricao.isEmpty()) {
				result.setParameter("descricao", "%" + descricao + "%");
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