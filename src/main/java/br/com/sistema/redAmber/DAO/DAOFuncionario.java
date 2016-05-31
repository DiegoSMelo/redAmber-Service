package br.com.sistema.redAmber.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.sistema.redAmber.DAO.generics.DAOGeneric;
import br.com.sistema.redAmber.basicas.BuscaFuncionario;
import br.com.sistema.redAmber.basicas.Funcionario;
import br.com.sistema.redAmber.basicas.enums.StatusUsuario;
import br.com.sistema.redAmber.exceptions.DAOException;
import br.com.sistema.redAmber.util.Mensagens;

public class DAOFuncionario extends DAOGeneric<Funcionario> implements IDAOFuncionario{

	public DAOFuncionario(EntityManager em) {
		super(em);
	}

	@Override
	public Funcionario buscarFuncionarioPorRG(String rg) throws DAOException {
		try {

			TypedQuery<Funcionario> result = entityManager.createQuery("SELECT f FROM Funcionario f WHERE f.rg = :rg", Funcionario.class);
			result.setParameter("rg", rg);


			Funcionario funcionario = result.getSingleResult();

			return funcionario;
		}catch (NoResultException e2) {
			return null;
		} 
		catch (Exception e) {
			throw new DAOException(Mensagens.m1);
		} 
	}

	@Override
	public Funcionario buscarFuncionarioPorLoginSenha(String login, String senha) throws DAOException {
		try {
			TypedQuery<Funcionario> result = entityManager.createQuery("SELECT f FROM Funcionario f WHERE f.usuario.login = :login AND f.usuario.senha = :senha", Funcionario.class);
			result.setParameter("login", login);
			result.setParameter("senha", senha);

			Funcionario funcionario = result.getSingleResult();

			return funcionario;
		}catch (NoResultException e2) {
			return null;
		} 
		catch (Exception e) {
			throw new DAOException(Mensagens.m1);
		}
	}

	@Override
	public Funcionario buscarFuncionarioPorLogin(String login) throws DAOException {
		try {
			TypedQuery<Funcionario> result = entityManager.createQuery("SELECT f FROM Funcionario f WHERE f.usuario.login = :login", Funcionario.class);
			result.setParameter("login", login);
			Funcionario funcionario = result.getSingleResult();

			return funcionario;
		}catch (NoResultException e2) {
			return null;
		} 
		catch (Exception e) {
			throw new DAOException(Mensagens.m1);
		}
	}
	
	
	@Override
	public List<Funcionario> buscarFuncionariosPorNomeRG(BuscaFuncionario bF) throws DAOException {
		
		String sql = "SELECT f FROM Funcionario f WHERE f.status = :status";

		if ((bF.getNome() != null && !bF.getNome().trim().equals("") && !bF.getNome().isEmpty()) && 
				(bF.getRg() == null || bF.getRg().trim().equals("") || bF.getRg().isEmpty())) {
			sql += " AND f.nome LIKE :nome";
		}
		if ((bF.getNome() == null || bF.getNome().trim().equals("") || bF.getNome().isEmpty()) && 
				(bF.getRg() != null && !bF.getRg().trim().equals("") && !bF.getRg().isEmpty())) {
			sql += " AND f.rg = :rg";
		}
		if ((bF.getNome() != null && !bF.getNome().trim().equals("") && !bF.getNome().isEmpty()) && 
				(bF.getRg() != null && !bF.getRg().trim().equals("") && !bF.getRg().isEmpty())) {
			sql += " AND f.nome LIKE :nome AND f.rg = :rg";
		}

		TypedQuery<Funcionario> result = entityManager.createQuery(sql, Funcionario.class);
		result.setParameter("status", StatusUsuario.ATIVO);

		try {
			if ((bF.getNome() != null && !bF.getNome().trim().equals("") && !bF.getNome().isEmpty()) && 
					(bF.getRg() == null || bF.getRg().trim().equals("") || bF.getRg().isEmpty())) {
				result.setParameter("nome", "%" + bF.getNome() + "%");
			}
			if ((bF.getNome() == null || bF.getNome().trim().equals("") || bF.getNome().isEmpty()) && 
					(bF.getRg() != null && !bF.getRg().trim().equals("") && !bF.getRg().isEmpty())) {
				result.setParameter("rg", bF.getRg());
			}
			if ((bF.getNome() != null && !bF.getNome().trim().equals("") && !bF.getNome().isEmpty()) && 
					(bF.getRg() != null && !bF.getRg().trim().equals("") && !bF.getRg().isEmpty())) {
				result.setParameter("nome", "%" + bF.getNome() + "%");
				result.setParameter("rg", bF.getRg());
			}
			return result.getResultList();
		} catch (NoResultException e2) {
			e2.printStackTrace();
			return null;
		} 
		catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(Mensagens.m2);
		}
	}
}