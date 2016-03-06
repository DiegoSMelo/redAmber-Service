package br.com.sistema.redAmber.DAO;

import javax.persistence.EntityManager;

import br.com.sistema.redAmber.DAO.generics.DAOGeneric;
import br.com.sistema.redAmber.basicas.Funcionario;

public class DAOFuncionario extends DAOGeneric<Funcionario> implements IDAOFuncionario{

	public DAOFuncionario(EntityManager em) {
		super(em);
	}

}
