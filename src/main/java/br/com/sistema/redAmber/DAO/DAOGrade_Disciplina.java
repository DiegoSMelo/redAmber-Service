package br.com.sistema.redAmber.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.sistema.redAmber.DAO.generics.DAOGeneric;
import br.com.sistema.redAmber.basicas.Grade_Disciplina;

public class DAOGrade_Disciplina extends DAOGeneric<Grade_Disciplina> implements IDAOGrade_Disciplina{

	public DAOGrade_Disciplina(EntityManager em) {
		super(em);
	}

	@Override
	public List<Grade_Disciplina> buscarPorIdGrade(Long id_grade) {
		
		String jpql = "SELECT gd FROM Grade_Disciplina gd WHERE gd.id.grade.id = :idGrade";
		
		TypedQuery<Grade_Disciplina> result = this.entityManager.createQuery(jpql, Grade_Disciplina.class);
		result.setParameter("idGrade", id_grade);
		
		return result.getResultList();
		
	}

	@Override
	public List<Grade_Disciplina> buscarPorIdCurso(Long id_curso) {
		
		String jpql = "SELECT gd FROM Grade_Disciplina gd WHERE gd.id.grade.curso.id = :idCurso";
		
		TypedQuery<Grade_Disciplina> result = this.entityManager.createQuery(jpql, Grade_Disciplina.class);
		result.setParameter("idCurso", id_curso);
		
		return result.getResultList();
		
	}

	@Override
	public void removerPorGrade(Long id_grade) {

		String jpql = "SELECT gd FROM Grade_Disciplina gd WHERE gd.id.grade.id = :idGrade";
		
		TypedQuery<Grade_Disciplina> query = this.entityManager.createQuery(jpql, Grade_Disciplina.class);
		query.setParameter("idGrade", id_grade);
		
		for (Grade_Disciplina gd : query.getResultList()) {
			this.remover(gd);
		}
		
	}
	
	
}
