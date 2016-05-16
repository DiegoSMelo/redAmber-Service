package br.com.sistema.redAmber.DAO;

import java.util.List;

import br.com.sistema.redAmber.DAO.generics.IDAOGeneric;
import br.com.sistema.redAmber.basicas.integracao.MatriculaIntegracao;
import br.com.sistema.redAmber.exceptions.DAOException;

public interface IDAOMatriculaIntegracao extends IDAOGeneric<MatriculaIntegracao> {
	
	public List<MatriculaIntegracao> listarMatriculasPorIdAluno(Long idAluno);
	public MatriculaIntegracao buscarMatriculaPorCodigoMatricula(String codigoMatricula) throws 
		DAOException;
	public MatriculaIntegracao buscarMatriculaAtivaPorCurso(Long idAluno, Long idCurso) throws 
		DAOException;
}