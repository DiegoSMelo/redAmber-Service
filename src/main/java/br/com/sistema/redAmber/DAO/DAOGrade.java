package br.com.sistema.redAmber.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.sistema.redAmber.DAO.generics.DAOGeneric;
import br.com.sistema.redAmber.basicas.Grade;

public class DAOGrade extends DAOGeneric<Grade> implements IDAOGrade{

	public DAOGrade(EntityManager em) {
		super(em);
	}

	@Override
	public List<Grade> listarGradesPorCurso(Long idCurso) {
		String jpql = "SELECT g FROM Grade g WHERE g.curso.id = :idCurso";
		
		TypedQuery<Grade> result = this.entityManager.createQuery(jpql, Grade.class);
		result.setParameter("idCurso", idCurso);
		
		return result.getResultList();
	}
	
	
	
}
