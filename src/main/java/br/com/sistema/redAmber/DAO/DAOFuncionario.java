package br.com.sistema.redAmber.DAO;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.sistema.redAmber.DAO.generics.DAOGeneric;
import br.com.sistema.redAmber.basicas.Funcionario;
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

}
