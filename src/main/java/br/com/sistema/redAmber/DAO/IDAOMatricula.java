package br.com.sistema.redAmber.DAO;

import java.util.List;

import br.com.sistema.redAmber.DAO.generics.IDAOGeneric;
import br.com.sistema.redAmber.basicas.Matricula;

public interface IDAOMatricula extends IDAOGeneric<Matricula>{

	public List<Matricula> listarMatriculasPorIdAluno(Long idAluno);
}