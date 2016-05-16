package br.com.sistema.redAmber.DAO;

import javax.persistence.EntityManager;

import br.com.sistema.redAmber.DAO.generics.DAOGeneric;
import br.com.sistema.redAmber.basicas.AvisoAluno;

public class DAOAvisoAluno extends DAOGeneric<AvisoAluno> implements IDAOAvisoAluno {

	public DAOAvisoAluno(EntityManager em) {
		super(em);
	}
}