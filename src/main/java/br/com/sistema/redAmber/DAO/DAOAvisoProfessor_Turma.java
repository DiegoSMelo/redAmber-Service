package br.com.sistema.redAmber.DAO;

import javax.persistence.EntityManager;

import br.com.sistema.redAmber.DAO.generics.DAOGeneric;
import br.com.sistema.redAmber.basicas.AvisoProfessor_Turma;

public class DAOAvisoProfessor_Turma extends DAOGeneric<AvisoProfessor_Turma> implements IDAOAvisoProfessor_Turma {
	public DAOAvisoProfessor_Turma(EntityManager em) {
		super(em);
	}
}