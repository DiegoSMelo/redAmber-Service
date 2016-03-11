package br.com.sistema.redAmber.rn;

import java.util.List;

import br.com.sistema.redAmber.DAO.IDAOMatricula;
import br.com.sistema.redAmber.DAO.factory.DAOFactory;
import br.com.sistema.redAmber.basicas.Matricula;

public class RNMatricula {
	
	private IDAOMatricula daoMatricula;
	
	public RNMatricula(){
		this.daoMatricula = DAOFactory.getDaoMatricula();
	}
	
	public List<Matricula> listarMatriculasPorIdAluno(Long idAluno){
		return this.daoMatricula.listarMatriculasPorIdAluno(idAluno);
	}
}
