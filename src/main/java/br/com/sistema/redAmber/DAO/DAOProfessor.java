package br.com.sistema.redAmber.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.sistema.redAmber.DAO.generics.DAOGeneric;
import br.com.sistema.redAmber.basicas.BuscaProfessor;
import br.com.sistema.redAmber.basicas.Disciplina;
import br.com.sistema.redAmber.basicas.Professor;
import br.com.sistema.redAmber.basicas.enums.StatusUsuario;
import br.com.sistema.redAmber.exceptions.DAOException;
import br.com.sistema.redAmber.util.Mensagens;

public class DAOProfessor extends DAOGeneric<Professor> implements IDAOProfessor {

	public DAOProfessor(EntityManager em) {
		super(em);
	}

	@Override
	public Professor buscarProfessorPorLoginSenha(String login, String senha) throws DAOException {
		try {
			TypedQuery<Professor> result = entityManager.createQuery(
					"SELECT p FROM Professor p WHERE p.usuario.login = :login AND p.usuario.senha = :senha",
					Professor.class);
			result.setParameter("login", login);
			result.setParameter("senha", senha);

			Professor professor = result.getSingleResult();

			return professor;
		} catch (NoResultException e2) {
			return null;
		} catch (Exception e) {
			throw new DAOException(Mensagens.m1);
		}
	}

	@Override
	public Professor buscarProfessorPorRg(String rg) {
		try {

			String jpql = "SELECT p FROM Professor p WHERE p.rg = :rg";
			TypedQuery<Professor> retorno = this.entityManager.createQuery(jpql, Professor.class);
			retorno.setParameter("rg", rg);
			Professor professor = retorno.getSingleResult();

			return professor;

		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Professor> listarProfessoresPorDisciplina(Disciplina d) {

		String jpql = "SELECT p FROM Professor p WHERE p.listaDisciplinas IS NOT EMPTY AND :disciplina MEMBER OF p.listaDisciplinas";

		TypedQuery<Professor> result = this.entityManager.createQuery(jpql, Professor.class);
		result.setParameter("disciplina", d);

		return result.getResultList();
	}
	
	@Override
	public List<Professor> buscarProfessoresPorNomeRG(BuscaProfessor bP) throws DAOException {
		
		String sql = "SELECT p FROM Professor p WHERE p.status = :status";

		if ((bP.getNome() != null && !bP.getNome().trim().equals("") && !bP.getNome().isEmpty()) && 
				(bP.getRg() == null || bP.getRg().trim().equals("") || bP.getRg().isEmpty())) {
			sql += " AND p.nome LIKE :nome";
		}
		if ((bP.getNome() == null || bP.getNome().trim().equals("") || bP.getNome().isEmpty()) && 
				(bP.getRg() != null && !bP.getRg().trim().equals("") && !bP.getRg().isEmpty())) {
			sql += " AND p.rg = :rg";
		}
		if ((bP.getNome() != null && !bP.getNome().trim().equals("") && !bP.getNome().isEmpty()) && 
				(bP.getRg() != null && !bP.getRg().trim().equals("") && !bP.getRg().isEmpty())) {
			sql += " AND p.nome LIKE :nome AND p.rg = :rg";
		}

		TypedQuery<Professor> result = entityManager.createQuery(sql, Professor.class);
		result.setParameter("status", StatusUsuario.ATIVO);

		try {
			if ((bP.getNome() != null && !bP.getNome().trim().equals("") && !bP.getNome().isEmpty()) && 
					(bP.getRg() == null || bP.getRg().trim().equals("") || bP.getRg().isEmpty())) {
				result.setParameter("nome", "%" + bP.getNome() + "%");
			}
			if ((bP.getNome() == null || bP.getNome().trim().equals("") || bP.getNome().isEmpty()) && 
					(bP.getRg() != null && !bP.getRg().trim().equals("") && !bP.getRg().isEmpty())) {
				result.setParameter("rg", bP.getRg());
			}
			if ((bP.getNome() != null && !bP.getNome().trim().equals("") && !bP.getNome().isEmpty()) && 
					(bP.getRg() != null && !bP.getRg().trim().equals("") && !bP.getRg().isEmpty())) {
				result.setParameter("nome", "%" + bP.getNome() + "%");
				result.setParameter("rg", bP.getRg());
			}
			return result.getResultList();
		} catch (NoResultException e2) {
			e2.printStackTrace();
			return null;
		} 
		catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(Mensagens.m2);
		}
	}
	
	@Override
	public List<Disciplina> buscarDisciplinasPorProfessor(Long idProfessor) {
		
		try {
			String jpql = "SELECT p FROM Professor p WHERE p.id = :idProfessor";
			TypedQuery<Professor> result = entityManager.createQuery(jpql, Professor.class);
			result.setParameter("idProfessor", idProfessor);
			Professor professor = result.getSingleResult();
			return professor.getListDisciplinas();
		} catch (NoResultException e2) {
			e2.printStackTrace();
			return null; 
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public Professor buscarProfessorPorEmail(String email) {

		try {
			String jpql = "SELECT p FROM Professor p WHERE p.email = :email";
			TypedQuery<Professor> result = entityManager.createQuery(jpql, Professor.class);
			result.setParameter("email", email);
			return result.getSingleResult();
		} catch (NoResultException e2) {
			e2.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}