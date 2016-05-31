package br.com.sistema.redAmber.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.sistema.redAmber.DAO.generics.DAOGeneric;
import br.com.sistema.redAmber.basicas.Equipamento;
import br.com.sistema.redAmber.basicas.enums.StatusEquipamento;
import br.com.sistema.redAmber.exceptions.DAOException;
import br.com.sistema.redAmber.util.Mensagens;

public class DAOEquipamento extends DAOGeneric<Equipamento> implements IDAOEquipamento {

	public DAOEquipamento(EntityManager em) {
		super(em);
	}

	@Override
	public Equipamento consultarPorDescricao(String descricao) throws DAOException {
		try {
			String sql = "SELECT e FROM Equipamento e WHERE e.descricao = :descricao";
			TypedQuery<Equipamento> result = entityManager.createQuery(sql, Equipamento.class);
			result.setParameter("descricao", descricao);

			Equipamento equipamento = result.getSingleResult();
			return equipamento;
		} catch (NoResultException e2) {
			e2.printStackTrace();
			return null;
		} catch (Exception e) {
			throw new DAOException(Mensagens.m9);
		}
	}

	@Override
	public Equipamento consultarPorTombo(String tombo) throws DAOException {
		try {
			String sql = "SELECT e FROM Equipamento e WHERE e.tombo = :tombo";
			TypedQuery<Equipamento> result = entityManager.createQuery(sql, Equipamento.class);
			result.setParameter("tombo", tombo);

			Equipamento equipamento = result.getSingleResult();
			return equipamento;
		} catch (NoResultException e2) {
			e2.printStackTrace();
			return null;
		} catch (Exception e) {
			throw new DAOException(Mensagens.m9);
		}
	}

	@Override
	public List<Equipamento> listarPorDescricao(String descricao) {

		try {
			String sql = "SELECT e FROM Equipamento e WHERE e.status = :status";

			if (descricao != null && !descricao.trim().equalsIgnoreCase("")
					&& !descricao.trim().equalsIgnoreCase("null") && !descricao.isEmpty()) {
				sql += " AND e.descricao LIKE :descricao";
			}

			TypedQuery<Equipamento> result = entityManager.createQuery(sql, Equipamento.class);
			result.setParameter("status", StatusEquipamento.ATIVO);

			if (descricao != null && !descricao.trim().equalsIgnoreCase("")
					&& !descricao.trim().equalsIgnoreCase("null") && !descricao.isEmpty()) {
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