package br.com.sistema.redAmber.DAO;

import java.util.List;

import br.com.sistema.redAmber.DAO.generics.IDAOGeneric;
import br.com.sistema.redAmber.basicas.AvisoAluno;

public interface IDAOAvisoAluno extends IDAOGeneric<AvisoAluno> {
	
	public List<AvisoAluno> listarAvisosAlunoPorAluno(Long idAluno);
}