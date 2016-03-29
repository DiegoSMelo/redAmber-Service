package br.com.sistema.redAmber.DAO;

import javax.persistence.EntityManager;

import br.com.sistema.redAmber.DAO.generics.DAOGeneric;
import br.com.sistema.redAmber.basicas.Grade;

public class DAOGrade extends DAOGeneric<Grade> implements IDAOGrade{

	public DAOGrade(EntityManager em) {
		super(em);
	}
	
	
	
}
