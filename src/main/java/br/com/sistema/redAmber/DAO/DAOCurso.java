package br.com.sistema.redAmber.DAO;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.sistema.redAmber.DAO.generics.DAOGeneric;
import br.com.sistema.redAmber.basicas.Curso;
import br.com.sistema.redAmber.exceptions.DAOException;
import br.com.sistema.redAmber.util.Mensagens;

public class DAOCurso extends DAOGeneric<Curso> implements IDAOCurso {

	public DAOCurso(EntityManager em) {
		super(em);
		
	}

	@Override
	public Curso buscarCursoPorNomeCurso(String nomeCurso) throws DAOException {
		try {
			TypedQuery<Curso> result = entityManager.createQuery("SELECT c FROM Curso c WHERE c.nomeCurso = :nomeCurso", Curso.class);
			result.setParameter("nomeCurso", nomeCurso);
			

			Curso curso = result.getSingleResult();

			return curso;
		}catch (NoResultException e2) {
			return null;
		} 
		catch (Exception e) {
			throw new DAOException(Mensagens.m2);
		}
	}
	
	public List<Curso> buscarCursosPorDatas(Calendar dataInicial, Calendar dataFinal) throws DAOException{
		try {
			TypedQuery<Curso> result = entityManager.createQuery("SELECT c FROM Curso c WHERE c.dataInicial >= :dataInicial and c.dataFinal <= :dataFinal", Curso.class);
			result.setParameter("dataInicial", dataInicial);
			result.setParameter("dataFinal", dataFinal);

			return result.getResultList();

			
		}catch (NoResultException e2) {
			return null;
		} 
		catch (Exception e) {
			throw new DAOException(Mensagens.m3);
		}
	}
	
	
	
}