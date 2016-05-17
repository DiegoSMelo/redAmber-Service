package br.com.sistema.redAmber.DAO;

import br.com.sistema.redAmber.DAO.generics.IDAOGeneric;
import br.com.sistema.redAmber.basicas.Disciplina;
import br.com.sistema.redAmber.exceptions.DAOException;

public interface IDAODisciplina extends IDAOGeneric<Disciplina> {
	
	public Disciplina buscarDisciplinaPorTitulo(String titulo) throws DAOException;
}