package br.com.sistema.redAmber.DAO;

import javax.persistence.EntityManager;

import br.com.sistema.redAmber.DAO.generics.DAOGeneric;
import br.com.sistema.redAmber.basicas.Periodo;

public class DAOPeriodo extends DAOGeneric<Periodo> implements IDAOPeriodo{

	public DAOPeriodo(EntityManager em) {
		super(em);
		// TODO Auto-generated constructor stub
	}

}
