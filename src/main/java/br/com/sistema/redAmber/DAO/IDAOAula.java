package br.com.sistema.redAmber.DAO;

import br.com.sistema.redAmber.DAO.generics.IDAOGeneric;
import br.com.sistema.redAmber.basicas.Aula;
import br.com.sistema.redAmber.basicas.AulaPK;

public interface IDAOAula extends IDAOGeneric<Aula> {
	
	public Aula buscarAulaPorPK(AulaPK pk);
}
