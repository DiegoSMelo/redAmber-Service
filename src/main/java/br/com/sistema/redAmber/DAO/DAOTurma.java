package br.com.sistema.redAmber.DAO;

import javax.persistence.EntityManager;

import br.com.sistema.redAmber.DAO.generics.DAOGeneric;
import br.com.sistema.redAmber.basicas.Turma;

public class DAOTurma extends DAOGeneric<Turma> implements IDAOTurma{

	public DAOTurma(EntityManager em) {
		super(em);
	}

}
