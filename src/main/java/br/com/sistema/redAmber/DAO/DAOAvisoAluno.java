package br.com.sistema.redAmber.DAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.sistema.redAmber.DAO.generics.DAOGeneric;
import br.com.sistema.redAmber.basicas.AvisoAluno;

public class DAOAvisoAluno extends DAOGeneric<AvisoAluno> implements IDAOAvisoAluno {

	public DAOAvisoAluno(EntityManager em) {
		super(em);
	}
	
	@Override
	@SuppressWarnings("deprecation")
	public List<AvisoAluno> listarAvisosAlunoPorAluno(Long idAluno) {
		
		try {
			Date data = new Date();
			int dia = data.getDay();
			int mes = data.getMonth();
			int ano = data.getYear();
			
			String jpql = "SELECT av FROM AvisoAluno av WHERE av.turma IN "
					+ "(SELECT m.turma FROM Matricula m WHERE m.aluno.id = :idAluno)";
			
			TypedQuery<AvisoAluno> result = this.entityManager.createQuery(jpql, AvisoAluno.class);
			
			result.setParameter("idAluno", idAluno);
			
			List<AvisoAluno> avisos = new ArrayList<AvisoAluno>();
			
			for (AvisoAluno aviso : result.getResultList()) {
				if (aviso.getDataAviso().getTime().getDay() == dia &&
						aviso.getDataAviso().getTime().getMonth() == mes &&
						aviso.getDataAviso().getTime().getYear() == ano) {
					avisos.add(aviso);
				}
			}
			return avisos;
		} catch (NoResultException e2) {
			e2.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}