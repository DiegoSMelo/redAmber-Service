package br.com.sistema.redAmber.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.sistema.redAmber.DAO.generics.DAOGeneric;
import br.com.sistema.redAmber.basicas.Matricula;

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
}
