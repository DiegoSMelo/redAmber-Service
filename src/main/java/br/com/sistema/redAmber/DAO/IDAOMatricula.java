package br.com.sistema.redAmber.DAO;

import java.util.List;

import br.com.sistema.redAmber.DAO.generics.IDAOGeneric;
import br.com.sistema.redAmber.basicas.Matricula;
import br.com.sistema.redAmber.exceptions.DAOException;

public interface IDAOMatricula extends IDAOGeneric<Matricula>{

	public List<Matricula> listarMatriculasPorIdAluno(Long idAluno);
	public Matricula buscarMatriculaPorCodigoMatricula(String codigoMatricula) throws DAOException;
	public Matricula buscarMatriculaAtivaPorCurso(Long idAluno, Long idCurso) throws DAOException;
}