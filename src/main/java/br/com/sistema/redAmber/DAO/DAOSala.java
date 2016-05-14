package br.com.sistema.redAmber.DAO;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.sistema.redAmber.DAO.generics.DAOGeneric;
import br.com.sistema.redAmber.basicas.Sala;
import br.com.sistema.redAmber.exceptions.DAOException;
import br.com.sistema.redAmber.util.Mensagens;

public class DAOSala extends DAOGeneric<Sala> implements IDAOSala {

	public DAOSala(EntityManager em) {
		super(em);
	}
	
	@Override
	public Sala consultarPorDescricao(String descricao) throws DAOException {
		try {
			TypedQuery<Sala> result = entityManager.createQuery("SELECT s FROM Sala s WHERE s.descricao = :descricao", Sala.class);
			result.setParameter("descricao", descricao);

			Sala sala = result.getSingleResult();
			return sala;
		} catch (NoResultException e2) {
			return null;
		} catch (Exception e) {
			throw new DAOException(Mensagens.m5);
		}
	}
}