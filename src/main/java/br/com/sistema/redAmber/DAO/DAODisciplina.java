package br.com.sistema.redAmber.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.sistema.redAmber.DAO.generics.DAOGeneric;
import br.com.sistema.redAmber.basicas.Disciplina;
import br.com.sistema.redAmber.basicas.enums.StatusDisciplina;
import br.com.sistema.redAmber.exceptions.DAOException;
import br.com.sistema.redAmber.util.Mensagens;

public class DAODisciplina extends DAOGeneric<Disciplina> implements IDAODisciplina {

	public DAODisciplina(EntityManager em) {
		super(em);
	}

	@Override
	public Disciplina buscarDisciplinaPorTitulo(String titulo) throws DAOException {
		try {
			String jpql = "SELECT d FROM Disciplina d WHERE d.titulo =:titulo";
			TypedQuery<Disciplina> result = entityManager.createQuery(jpql, Disciplina.class);
			result.setParameter("titulo", titulo);
			Disciplina disciplina = result.getSingleResult();
			return disciplina;
		} catch (NoResultException e2) {
			e2.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(Mensagens.m2);
		}
	}
	
	@Override
	public Disciplina buscarDisciplinaPorTituloCurso(String titulo, Long idCurso) throws DAOException {
		try {
			String jpql = "SELECT d FROM Disciplina d WHERE d.titulo = :titulo AND d.curso.id = :idCurso";
			TypedQuery<Disciplina> result = entityManager.createQuery(jpql, Disciplina.class);
			result.setParameter("titulo", titulo);
			result.setParameter("idCurso", idCurso);
			return result.getSingleResult();
		} catch (NoResultException e2) {
			e2.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(Mensagens.m2);
		}
	}
	
	@Override
	public List<Disciplina> buscarDisciplinasPorCurso(Long idCurso) throws DAOException {
		try {
			String jpql = "SELECT d FROM Disciplina d WHERE d.status = :status";
			
			if (idCurso != null) {
				jpql += " AND d.curso.id = :idCurso";
			}
			
			TypedQuery<Disciplina> result = entityManager.createQuery(jpql, Disciplina.class);
			result.setParameter("status", StatusDisciplina.ATIVA);
			
			if (idCurso != null) {
				result.setParameter("idCurso", idCurso);
			}

			return result.getResultList();
		} catch (NoResultException e2) {
			e2.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(Mensagens.m2);
		}
	}
	
	@Override
	public List<Disciplina> buscarDisciplinasAtivas() throws DAOException {
		try {
			String jpql = "SELECT d FROM Disciplina d WHERE d.status = :status";
			TypedQuery<Disciplina> result = entityManager.createQuery(jpql, Disciplina.class);
			result.setParameter("status", StatusDisciplina.ATIVA);
				return result.getResultList();
		} catch (NoResultException e2) {
			e2.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(Mensagens.m2);
		}
	}
}