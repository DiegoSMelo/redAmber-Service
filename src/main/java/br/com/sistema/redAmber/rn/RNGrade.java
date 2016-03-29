package br.com.sistema.redAmber.rn;

import br.com.sistema.redAmber.DAO.IDAOGrade;
import br.com.sistema.redAmber.DAO.factory.DAOFactory;

public class RNGrade {
	
	private IDAOGrade daoGrade;
	
	public RNGrade() {
		this.daoGrade = DAOFactory.getDaoGrade();
	}
	
	
}
