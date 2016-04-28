package br.com.sistema.redAmber.DAO;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.sistema.redAmber.DAO.generics.DAOGeneric;
import br.com.sistema.redAmber.basicas.Aula;
import br.com.sistema.redAmber.basicas.AulaPK;

public class DAOAula extends DAOGeneric<Aula> implements IDAOAula {

	public DAOAula(EntityManager em) {
		super(em);
	}

	@Override
	public Aula buscarAulaPorPK(AulaPK pk) {

		String jpql = "SELECT aula FROM Aula aula WHERE aula.sala.id = :idSala AND aula.disciplina.id = :idDisciplina AND aula.professor.id = :idProfessor";
		
		TypedQuery<Aula> result = this.entityManager.createQuery(jpql, Aula.class);
		result.setParameter("idSala", pk.getSala().getId());
		result.setParameter("idDisciplina", pk.getDisciplina().getId());
		result.setParameter("idProfessor", pk.getProfessor().getId());
		
		return result.getSingleResult();
		
	}
	

}
