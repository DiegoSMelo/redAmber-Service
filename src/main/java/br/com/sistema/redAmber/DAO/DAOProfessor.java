package br.com.sistema.redAmber.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.sistema.redAmber.DAO.generics.DAOGeneric;
import br.com.sistema.redAmber.basicas.Disciplina;
import br.com.sistema.redAmber.basicas.Professor;
import br.com.sistema.redAmber.exceptions.DAOException;
import br.com.sistema.redAmber.util.Mensagens;

public class DAOProfessor extends DAOGeneric<Professor> implements IDAOProfessor{

	public DAOProfessor(EntityManager em) {
		super(em);
	}



	@Override
	public Professor buscarProfessorPorLoginSenha(String login, String senha) throws DAOException {
		try {
			TypedQuery<Professor> result = entityManager.createQuery("SELECT p FROM Professor p WHERE p.usuario.login = :login AND p.usuario.senha = :senha", Professor.class);
			result.setParameter("login", login);
			result.setParameter("senha", senha);

			Professor professor = result.getSingleResult();

			return professor;
		}catch (NoResultException e2) {
			return null;
		} 
		catch (Exception e) {
			throw new DAOException(Mensagens.m1);
		}
	}


	@Override
	public Professor buscarProfessorPorRg(String rg) {
		
		try {
			
			String jpql = "SELECT p FROM Professor p WHERE p.rg = :rg";
			TypedQuery<Professor> retorno = this.entityManager.createQuery(jpql, Professor.class);
			retorno.setParameter("rg", rg);
			Professor professor = retorno.getSingleResult();
			
			return professor;
			
		} catch (NoResultException e) {
			
			return null;
			
		} catch (Exception e) {
			
			return null;
			
		}
		
	}



	@Override
	public List<Professor> listarProfessoresPorDisciplina(Disciplina d) {

		String jpql = "SELECT p FROM Professor p WHERE p.listaDisciplinas IS NOT EMPTY AND :disciplina MEMBER OF p.listaDisciplinas";
		
		TypedQuery<Professor> result = this.entityManager.createQuery(jpql, Professor.class);
		result.setParameter("disciplina", d);
		
		return result.getResultList();
		
	}
	
	

}
