package br.com.sistema.redAmber.DAO;

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

	
	public Curso buscarCursoPorNomeESigla(String nomeCurso, String sigla) throws DAOException {
		try {
			TypedQuery<Curso> result = entityManager.createQuery("SELECT c FROM Curso c WHERE c.nome = :nomeCurso AND c.sigla = :sigla", Curso.class);
			result.setParameter("nomeCurso", nomeCurso);
			result.setParameter("sigla", sigla);

			Curso curso = result.getSingleResult();

			return curso;
		}catch (NoResultException e2) {
			return null;
		} 
		catch (Exception e) {
			throw new DAOException(Mensagens.m2);
		}
	}
	@Override
	public Curso buscarCursoPorNomeCurso(String nomeCurso) throws DAOException {
		try {
			TypedQuery<Curso> result = entityManager.createQuery("SELECT c FROM Curso c WHERE c.nome = :nomeCurso", Curso.class);
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
	

}