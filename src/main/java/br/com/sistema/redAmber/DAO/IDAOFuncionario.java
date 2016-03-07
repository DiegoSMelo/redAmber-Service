package br.com.sistema.redAmber.DAO;

import br.com.sistema.redAmber.DAO.generics.IDAOGeneric;
import br.com.sistema.redAmber.basicas.Funcionario;
import br.com.sistema.redAmber.exceptions.DAOException;

public interface IDAOFuncionario extends IDAOGeneric<Funcionario>{
	
	public Funcionario buscarFuncionarioPorRG(String rg) throws DAOException;
	public Funcionario buscarFuncionarioPorLoginSenha(String login, String senha) throws DAOException;
}
