package br.com.sistema.redAmber.DAO;

import br.com.sistema.redAmber.DAO.generics.IDAOGeneric;
import br.com.sistema.redAmber.basicas.Curso;
import br.com.sistema.redAmber.exceptions.DAOException;

public interface IDAOCurso extends IDAOGeneric<Curso> {

	public Curso buscarCursoPorNomeCurso(String nomeCurso) throws DAOException;
	public Curso buscarCursoPorNomeESigla(String nomeCurso, String sigla) throws DAOException;
}