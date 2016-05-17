package br.com.sistema.redAmber.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.sistema.redAmber.DAO.generics.DAOGeneric;
import br.com.sistema.redAmber.basicas.Matricula;
import br.com.sistema.redAmber.basicas.enums.StatusMatricula;
import br.com.sistema.redAmber.exceptions.DAOException;
import br.com.sistema.redAmber.util.Mensagens;

public class DAOMatricula extends DAOGeneric<Matricula> implements IDAOMatricula{

	public DAOMatricula(EntityManager em) {
		super(em);
	}
	
	
	public List<Matricula> listarMatriculasPorIdAluno(Long idAluno){
		String jpql = "SELECT m FROM Matricula m WHERE m.aluno.id = :idAluno";
		
		TypedQuery<Matricula> result = this.entityManager.createQuery(jpql, Matricula.class);
		result.setParameter("idAluno", idAluno);
		
		return result.getResultList();
	}


	@Override
	public Matricula buscarMatriculaPorCodigoMatricula(String codigoMatricula) throws DAOException {
		try {
			String jpql = "SELECT m FROM Matricula m WHERE m.codigoMatricula = :codigoMatricula";
			
			TypedQuery<Matricula> result = this.entityManager.createQuery(jpql, Matricula.class);
			result.setParameter("codigoMatricula", codigoMatricula);
			
			return result.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(Mensagens.m7);
		}
	}
	
	@Override
	public Matricula buscarMatriculaAtivaPorCurso(Long idAluno, Long idCurso) throws DAOException {
		try {
			String jpql = "SELECT m FROM Matricula m WHERE m.aluno.id = :idAluno AND "
					+ "m.grade.curso.id = :idCurso AND m.status = :status";
			
			TypedQuery<Matricula> result = this.entityManager.createQuery(jpql, Matricula.class);
			result.setParameter("idAluno", idAluno);
			result.setParameter("idCurso", idCurso);
			result.setParameter("status", StatusMatricula.ATIVA);
			
			return result.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(Mensagens.m7);
		}
	}
}
