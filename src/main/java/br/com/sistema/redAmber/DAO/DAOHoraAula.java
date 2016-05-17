package br.com.sistema.redAmber.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.sistema.redAmber.DAO.factory.DAOFactory;
import br.com.sistema.redAmber.DAO.generics.DAOGeneric;
import br.com.sistema.redAmber.basicas.HoraAula;
import br.com.sistema.redAmber.basicas.HoraAulaPK;

public class DAOHoraAula extends DAOGeneric<HoraAula> implements IDAOHoraAula {
	
	private IDAOAula daoAula;
	
	public DAOHoraAula(EntityManager em) {
		super(em);
		
		daoAula = DAOFactory.getDaoAula();
	}
	
	public List<HoraAula> listaHoraAulaPorIdTurma(Long idTurma){
		
		String jpql = "SELECT ha FROM HoraAula ha WHERE ha.id.turma.id = :idTurma ORDER BY ha.id.horaInicio ASC";
		
		TypedQuery<HoraAula> result = this.entityManager.createQuery(jpql, HoraAula.class);
		result.setParameter("idTurma", idTurma);
		
		return result.getResultList();
		
	}

	@Override
	public HoraAula consultarPorPK(HoraAulaPK pk) {
		
		String jpql = "SELECT ha FROM HoraAula ha WHERE ha.id.turma.id = :idTurma AND ha.id.aula.id.sala.id = :idSala AND ha.id.aula.id.disciplina.id = :idDisciplina AND ha.id.aula.id.professor.id = :idProfessor";
		
		TypedQuery<HoraAula> result = this.entityManager.createQuery(jpql, HoraAula.class);
		result.setParameter("idTurma", pk.getTurma().getId());
		result.setParameter("idAula", pk.getAula().getId().getSala().getId());
		result.setParameter("idDisciplina", pk.getAula().getId().getDisciplina().getId());
		result.setParameter("idProfessor", pk.getAula().getId().getProfessor().getId());
		
		return result.getSingleResult();
	}

	@Override
	public void removerPorIdTurma(Long idTurma) {
		
		List<HoraAula> lista = this.listaHoraAulaPorIdTurma(idTurma);
		
		lista.forEach(value -> this.remover(value));
		lista.forEach(value -> this.daoAula.remover(value.getId().getAula()));
		
		/*for (HoraAula horaAula : lista) {
			
			this.remover(horaAula);
			
		}	*/
		/*
		for (HoraAula horaAula : lista) {
			
			this.daoAula.remover(horaAula.getId().getAula());
			
		}*/
		
	}
}
