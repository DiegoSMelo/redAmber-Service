package br.com.sistema.redAmber.DAO;

import javax.persistence.EntityManager;

import br.com.sistema.redAmber.DAO.generics.DAOGeneric;
import br.com.sistema.redAmber.basicas.AvisoProfessor;

public class DAOAvisoProfessor extends DAOGeneric<AvisoProfessor> implements IDAOAvisoProfessor {
	public DAOAvisoProfessor(EntityManager em) {
		super(em);
	}
}