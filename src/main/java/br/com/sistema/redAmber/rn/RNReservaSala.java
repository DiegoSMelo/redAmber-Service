package br.com.sistema.redAmber.rn;

import br.com.sistema.redAmber.DAO.IDAOReservaSala;
import br.com.sistema.redAmber.DAO.factory.DAOFactory;
import br.com.sistema.redAmber.basicas.ReservaSala;

public class RNReservaSala {

	private IDAOReservaSala daoReservaSala;
	
	public RNReservaSala() {
		this.daoReservaSala = DAOFactory.getDaoReservaSala();
	}
	
	public void reservar(ReservaSala reservaSala) {
		
	}
	
	public void cancelar(ReservaSala reservaSala) {
		
	}
	
	public boolean salaJaReservada(ReservaSala reservaSala) {
		return false;
	}
}