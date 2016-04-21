package br.com.sistema.redAmber.DAO;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.sistema.redAmber.DAO.generics.DAOGeneric;
import br.com.sistema.redAmber.basicas.Equipamento;
import br.com.sistema.redAmber.exceptions.DAOException;
import br.com.sistema.redAmber.util.Mensagens;

public class DAOEquipamento extends DAOGeneric<Equipamento> implements IDAOEquipamento {

	public DAOEquipamento(EntityManager em) {
		super(em);
	}
	
	@Override
	public Equipamento consultarPorDescricao(String descricao) throws DAOException {
		try {
			TypedQuery<Equipamento> result = entityManager.createQuery("SELECT e FROM Equipamento e WHERE e.descricao = :descricao", Equipamento.class);
			result.setParameter("descricao", descricao);

			Equipamento equipamento = result.getSingleResult();
			return equipamento;
		} catch (NoResultException e2) {
			return null;
		} catch (Exception e) {
			throw new DAOException(Mensagens.m4);
		}
	}
	
	@Override
	public Equipamento consultarPorTombo(String tombo) throws DAOException {
		try {
			TypedQuery<Equipamento> result = entityManager.createQuery("SELECT e FROM Equipamento e WHERE e.tombo = :tombo", Equipamento.class);
			result.setParameter("tombo", tombo);
			
			Equipamento equipamento = result.getSingleResult();
			return equipamento;
		} catch (NoResultException e2) {
			return null;
		} catch (Exception e) {
			throw new DAOException(Mensagens.m4);
		}
	}
}