package br.com.sistema.redAmber.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.sistema.redAmber.DAO.generics.DAOGeneric;
import br.com.sistema.redAmber.basicas.Curso;
import br.com.sistema.redAmber.basicas.Turma;
import br.com.sistema.redAmber.basicas.enums.TipoTurno;

public class DAOTurma extends DAOGeneric<Turma> implements IDAOTurma{

	public DAOTurma(EntityManager em) {
		super(em);
	}
	
	
	@Override
	public List<Turma> consultarTurmasPorCursoTurno(Curso curso, TipoTurno turno) {
		
		try {
			String jpql = "SELECT t FROM Turma t";
			
			if ((curso != null && curso.getId() != null) && turno == null) {
				jpql += " WHERE t.curso = :curso";
			}
			if ((curso == null || curso.getId() == null) && turno != null) {
				jpql += " WHERE t.turno = :turno";
			}
			if ((curso != null && curso.getId() != null) && turno != null) {
				jpql += " WHERE t.curso = :curso AND t.turno = :turno";
			}
			
			TypedQuery<Turma> result = this.entityManager.createQuery(jpql, Turma.class);
			
			if ((curso != null && curso.getId() != null) && turno == null) {
				result.setParameter("curso", curso);
			}
			if ((curso == null || curso.getId() == null) && turno != null) {
				result.setParameter("turno", turno);
			}
			if ((curso != null && curso.getId() != null) && turno != null) {
				result.setParameter("curso", curso);
				result.setParameter("turno", turno);
			}
			return result.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
