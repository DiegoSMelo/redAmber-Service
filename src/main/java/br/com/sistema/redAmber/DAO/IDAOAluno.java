package br.com.sistema.redAmber.DAO;

import br.com.sistema.redAmber.DAO.generics.IDAOGeneric;
import br.com.sistema.redAmber.basicas.Aluno;
import br.com.sistema.redAmber.basicas.enums.StatusUsuario;
import br.com.sistema.redAmber.exceptions.DAOException;

public interface IDAOAluno extends IDAOGeneric<Aluno>{
		
	public Aluno buscarAlunoPorRG(String rg) throws DAOException;
	public Aluno buscarAlunoPorLoginSenha(String login, String senha) throws DAOException;
}
