package br.com.sistema.redAmber.DAO;

import javax.persistence.EntityManager;

import br.com.sistema.redAmber.DAO.generics.DAOGeneric;
import br.com.sistema.redAmber.basicas.Professor;

public class DAOProfessor extends DAOGeneric<Professor> implements IDAOProfessor{

	public DAOProfessor(EntityManager em) {
		super(em);
	}

}
