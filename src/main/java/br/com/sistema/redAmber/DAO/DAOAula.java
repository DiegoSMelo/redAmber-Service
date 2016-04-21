package br.com.sistema.redAmber.DAO;

import javax.persistence.EntityManager;

import br.com.sistema.redAmber.DAO.generics.DAOGeneric;
import br.com.sistema.redAmber.basicas.Aula;

public class DAOAula extends DAOGeneric<Aula> implements IDAOAula {

	public DAOAula(EntityManager em) {
		super(em);
	}
	

}
