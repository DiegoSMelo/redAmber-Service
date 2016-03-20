package br.com.sistema.redAmber.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.sistema.redAmber.DAO.generics.DAOGeneric;
import br.com.sistema.redAmber.basicas.Funcionario;
import br.com.sistema.redAmber.exceptions.DAOException;
import br.com.sistema.redAmber.util.Mensagens;

public class DAOFuncionario extends DAOGeneric<Funcionario> implements IDAOFuncionario{

	public DAOFuncionario(EntityManager em) {
		super(em);
	}

	@Override
	public Funcionario buscarFuncionarioPorRG(String rg) throws DAOException {
		try {

			TypedQuery<Funcionario> result = entityManager.createQuery("SELECT f FROM Funcionario f WHERE f.rg = :rg", Funcionario.class);
			result.setParameter("rg", rg);


			Funcionario funcionario = result.getSingleResult();

			return funcionario;
		}catch (NoResultException e2) {
			return null;
		} 
		catch (Exception e) {
			throw new DAOException(Mensagens.m1);
		} 
	}

	@Override
	public Funcionario buscarFuncionarioPorLoginSenha(String login, String senha) throws DAOException {
		try {
			TypedQuery<Funcionario> result = entityManager.createQuery("SELECT f FROM Funcionario f WHERE f.usuario.login = :login AND f.usuario.senha = :senha", Funcionario.class);
			result.setParameter("login", login);
			result.setParameter("senha", senha);

			Funcionario funcionario = result.getSingleResult();

			return funcionario;
		}catch (NoResultException e2) {
			return null;
		} 
		catch (Exception e) {
			throw new DAOException(Mensagens.m1);
		}
	}

	@Override
	public Funcionario buscarFuncionarioPorLogin(String login) throws DAOException {
		try {
			TypedQuery<Funcionario> result = entityManager.createQuery("SELECT f FROM Funcionario f WHERE f.usuario.login = :login", Funcionario.class);
			result.setParameter("login", login);
			Funcionario funcionario = result.getSingleResult();

			return funcionario;
		}catch (NoResultException e2) {
			return null;
		} 
		catch (Exception e) {
			throw new DAOException(Mensagens.m1);
		}
	}
	
	
	@Override
	public List<Funcionario> buscarFuncionariosPorParametros(Funcionario f) throws DAOException {

		String sql = "SELECT f FROM Funcionario f WHERE f.status = :status";
		try {
			if (f.getNome() != null && !f.getNome().equals("") && !f.getNome().isEmpty() && f.getStatus() == null
					&& f.getTipoFuncionario() == null) {
				sql += " AND f.nome LIKE :nome";
			}
			if ((f.getNome() == null || f.getNome().equals("") || f.getNome().isEmpty()) && f.getStatus() != null
					&& f.getTipoFuncionario() == null) {
				sql += " AND f.status = :status";
			}
			if ((f.getNome() == null || f.getNome().equals("") || f.getNome().isEmpty()) && f.getStatus() == null
					&& f.getTipoFuncionario() != null) {
				sql += " AND f.tipoFuncionario = :tipoFuncionario";
			}
			if (f.getNome() != null && !f.getNome().equals("") && !f.getNome().isEmpty() && f.getStatus() != null
					&& f.getTipoFuncionario() == null) {
				sql += " AND f.nome LIKE :nome AND f.status = :status";
			}
			if (f.getNome() != null && !f.getNome().equals("") && !f.getNome().isEmpty() && f.getStatus() == null
					&& f.getTipoFuncionario() != null) {
				sql += " AND f.nome LIKE :nome AND f.tipoFuncionario = :tipoFuncionario";
			}
			if (f.getNome() != null && !f.getNome().equals("") && !f.getNome().isEmpty() && f.getStatus() != null
					&& f.getTipoFuncionario() != null) {
				sql += " AND f.nome LIKE :nome AND f.status = :status AND f.tipoFuncionario = :tipoFuncionario";
			}
			if ((f.getNome() == null || f.getNome().equals("") || f.getNome().isEmpty()) && f.getStatus() != null
					&& f.getTipoFuncionario() != null) {
				sql += " AND f.status = :status AND f.tipoFuncionario = :tipoFuncionario";
			}

			TypedQuery<Funcionario> result = entityManager.createQuery(sql, Funcionario.class);

			if (f.getNome() != null && !f.getNome().equals("") && !f.getNome().isEmpty() && f.getStatus() == null
					&& f.getTipoFuncionario() == null) {
				result.setParameter("nome", f.getNome());
			}
			if ((f.getNome() == null || f.getNome().equals("") || f.getNome().isEmpty()) && f.getStatus() != null
					&& f.getTipoFuncionario() == null) {
				result.setParameter("status", f.getStatus());
			}
			if ((f.getNome() == null || f.getNome().equals("") || f.getNome().isEmpty()) && f.getStatus() == null
					&& f.getTipoFuncionario() != null) {
				result.setParameter("tipoFuncionario", f.getTipoFuncionario());
			}
			if (f.getNome() != null && !f.getNome().equals("") && !f.getNome().isEmpty() && f.getStatus() != null
					&& f.getTipoFuncionario() == null) {
				result.setParameter("nome", f.getNome());
				result.setParameter("status", f.getStatus());
			}
			if (f.getNome() != null && !f.getNome().equals("") && !f.getNome().isEmpty() && f.getStatus() == null
					&& f.getTipoFuncionario() != null) {
				result.setParameter("nome", f.getNome());
				result.setParameter("tipoFuncionario", f.getTipoFuncionario());
			}
			if (f.getNome() != null && !f.getNome().equals("") && !f.getNome().isEmpty() && f.getStatus() != null
					&& f.getTipoFuncionario() != null) {
				result.setParameter("nome", f.getNome());
				result.setParameter("status", f.getStatus());
				result.setParameter("tipoFuncionario", f.getTipoFuncionario());
			}
			if ((f.getNome() == null || f.getNome().equals("") || f.getNome().isEmpty()) && f.getStatus() != null
					&& f.getTipoFuncionario() != null) {
				result.setParameter("status", f.getStatus());
				result.setParameter("tipoFuncionario", f.getTipoFuncionario());
			}
			return result.getResultList();
		} catch (Exception e) {
			throw new DAOException(Mensagens.m2);
		}
	}

}
