package br.com.sistema.redAmber.rn;

import br.com.sistema.redAmber.DAO.IDAOHoraAula;
import br.com.sistema.redAmber.DAO.factory.DAOFactory;

public class RNHoraAula {
	
	private IDAOHoraAula daoHoraAula;
	
	public RNHoraAula() {
		this.daoHoraAula = DAOFactory.getDaoHoraAula();
	}
	
	
}
