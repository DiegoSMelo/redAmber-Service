package br.com.sistema.redAmber.DAO;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.sistema.redAmber.DAO.generics.DAOGeneric;
import br.com.sistema.redAmber.basicas.Disciplina;
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
			
		}catch (NoResultException e2) {
			return null;
		} 
		catch (Exception e) {
			throw new DAOException(Mensagens.m2);
		} 
		
	}

	

}
