package br.com.sistema.redAmber.DAO;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.sistema.redAmber.DAO.generics.DAOGeneric;
import br.com.sistema.redAmber.basicas.Aluno;
import br.com.sistema.redAmber.exceptions.DAOException;
import br.com.sistema.redAmber.util.Criptografia;
import br.com.sistema.redAmber.util.Mensagens;

public class DAOAluno extends DAOGeneric<Aluno> implements IDAOAluno{

	public DAOAluno(EntityManager em) {
		super(em);
	}

	@Override
	public Aluno buscarAlunoPorRG(String rg) throws DAOException {
		try {

			TypedQuery<Aluno> result = entityManager.createQuery("SELECT a FROM Aluno a WHERE a.rg = :rg", Aluno.class);
			result.setParameter("rg", rg);


			Aluno aluno = result.getSingleResult();

			return aluno;
		}catch (NoResultException e2) {
			return null;
		} 
		catch (Exception e) {
			throw new DAOException(Mensagens.m1);
		} 
	}

	@Override
	public Aluno buscarAlunoPorLoginSenha(String login, String senha) throws DAOException {
		try {
			TypedQuery<Aluno> result = entityManager.createQuery("SELECT a FROM Aluno a WHERE a.usuario.login = :login AND a.usuario.senha = :senha", Aluno.class);
			result.setParameter("login", login);
			result.setParameter("senha", senha);

			Aluno aluno = result.getSingleResult();

			return aluno;
		}catch (NoResultException e2) {
			return null;
		} 
		catch (Exception e) {
			throw new DAOException(Mensagens.m1);
		}
	}

	

}
