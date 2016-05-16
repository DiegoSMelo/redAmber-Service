package br.com.sistema.redAmber.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.sistema.redAmber.DAO.generics.DAOGeneric;
import br.com.sistema.redAmber.basicas.enums.StatusMatricula;
import br.com.sistema.redAmber.basicas.integracao.MatriculaIntegracao;
import br.com.sistema.redAmber.exceptions.DAOException;
import br.com.sistema.redAmber.util.Mensagens;

public class DAOMatriculaIntegracao extends DAOGeneric<MatriculaIntegracao> implements 
	IDAOMatriculaIntegracao {

	public DAOMatriculaIntegracao(EntityManager em) {
		super(em);
	}
	
	public List<MatriculaIntegracao> listarMatriculasPorIdAluno(Long idAluno) {
		
		String jpql = "SELECT m FROM MatriculaIntegracao m WHERE m.idAluno = :idAluno";
		
		TypedQuery<MatriculaIntegracao> result = this.entityManager.createQuery(jpql, 
				MatriculaIntegracao.class);
		result.setParameter("idAluno", idAluno);
		
		return result.getResultList();
	}
	
	public MatriculaIntegracao buscarMatriculaPorCodigoMatricula(String codigoMatricula) throws 
		DAOException {
		try {
			String jpql = "SELECT m FROM MatriculaIntegracao m WHERE m.codigoMatricula = "
					+ ":codigoMatricula";
			
			TypedQuery<MatriculaIntegracao> result = this.entityManager.createQuery(jpql, 
					MatriculaIntegracao.class);
			result.setParameter("codigoMatricula", codigoMatricula);
			
			return result.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(Mensagens.m7);
		}
	}
	
	public MatriculaIntegracao buscarMatriculaAtivaPorCurso(Long idAluno, Long idCurso) throws 
		DAOException {
		try {
			String jpql = "SELECT m FROM MatriculaIntegracao m WHERE m.idAluno = :idAluno AND "
					+ "m.grade.curso.id = :idCurso AND m.status = :status";
			
			TypedQuery<MatriculaIntegracao> result = this.entityManager.createQuery(jpql, 
					MatriculaIntegracao.class);
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