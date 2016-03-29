package br.com.sistema.redAmber.rn;

import br.com.sistema.redAmber.DAO.IDAOGrade_Disciplina;
import br.com.sistema.redAmber.DAO.factory.DAOFactory;

public class RNGrade_Disciplina {

	private IDAOGrade_Disciplina daoGrade_Disciplina;
	
	public RNGrade_Disciplina() {
		this.daoGrade_Disciplina = DAOFactory.getDaoGrade_Disciplina();
	}
}
