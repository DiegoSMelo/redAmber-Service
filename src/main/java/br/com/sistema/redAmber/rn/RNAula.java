package br.com.sistema.redAmber.rn;

import br.com.sistema.redAmber.DAO.IDAOAula;
import br.com.sistema.redAmber.DAO.factory.DAOFactory;

public class RNAula {
	
	private IDAOAula daoAula;
	
	public RNAula() {
		this.daoAula = DAOFactory.getDaoAula();
	}
	
	
}
