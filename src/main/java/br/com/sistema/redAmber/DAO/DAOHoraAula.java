package br.com.sistema.redAmber.DAO;

import javax.persistence.EntityManager;

import br.com.sistema.redAmber.DAO.generics.DAOGeneric;
import br.com.sistema.redAmber.basicas.HoraAula;

public class DAOHoraAula extends DAOGeneric<HoraAula> implements IDAOHoraAula {

	public DAOHoraAula(EntityManager em) {
		super(em);
	}
	
	
}
