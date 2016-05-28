package br.com.sistema.redAmber.DAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.sistema.redAmber.DAO.generics.DAOGeneric;
import br.com.sistema.redAmber.basicas.AvisoAluno;
import br.com.sistema.redAmber.basicas.BuscaAvisoAluno;

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
	
	@Override
	@SuppressWarnings("deprecation")
	public List<AvisoAluno> listarAvisosAlunoPorParametros(BuscaAvisoAluno consulta) {
		
		try {
			String jpql = "SELECT av FROM AvisoAluno av";
			List<AvisoAluno> resultado = new ArrayList<AvisoAluno>();
			List<AvisoAluno> resposta = new ArrayList<AvisoAluno>();
			
			if (consulta.getIdFuncionario() != null && consulta.getIdTurma() == null) {
				jpql += " WHERE av.funcionario.id = :idFuncionario";
			}
			if (consulta.getIdFuncionario() == null && consulta.getIdTurma() != null) {
				jpql += " WHERE av.turma.id = :idTurma";
			}
			if (consulta.getIdFuncionario() != null && consulta.getIdTurma() != null) {
				jpql += " WHERE av.funcionario.id = :idFuncionario AND av.turma.id = :idTurma";
			}
			
			TypedQuery<AvisoAluno> query = entityManager.createQuery(jpql, AvisoAluno.class);
			
			if (consulta.getIdFuncionario() != null && consulta.getIdTurma() == null) {
				query.setParameter("idFuncionario", consulta.getIdFuncionario());
			}
			if (consulta.getIdFuncionario() == null && consulta.getIdTurma() != null) {
				query.setParameter("idTurma", consulta.getIdTurma());
			}
			if (consulta.getIdFuncionario() != null && consulta.getIdTurma() != null) {
				query.setParameter("idFuncionario", consulta.getIdFuncionario());
				query.setParameter("idTurma", consulta.getIdTurma());
			}
			
			resultado = query.getResultList();
			
			if (consulta.getDataAviso() != null) {
				int dia = consulta.getDataAviso().getDate();
				int mes = consulta.getDataAviso().getMonth();
				int ano = consulta.getDataAviso().getYear();
				
				for (AvisoAluno aviso : resultado) {
					if (aviso.getDataAviso().getTime().getDate() == dia && 
							aviso.getDataAviso().getTime().getMonth() == mes && 
							aviso.getDataAviso().getTime().getYear() == ano) {
						resposta.add(aviso);
					}
				}
				return resposta;
			}
			return resultado;
		} catch (NoResultException e2) {
			e2.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}