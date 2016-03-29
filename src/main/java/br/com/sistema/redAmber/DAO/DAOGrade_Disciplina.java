package br.com.sistema.redAmber.DAO;

import javax.persistence.EntityManager;

import br.com.sistema.redAmber.DAO.generics.DAOGeneric;
import br.com.sistema.redAmber.basicas.Grade_Disciplina;

public class DAOGrade_Disciplina extends DAOGeneric<Grade_Disciplina> implements IDAOGrade_Disciplina{

	public DAOGrade_Disciplina(EntityManager em) {
		super(em);
	}

}
