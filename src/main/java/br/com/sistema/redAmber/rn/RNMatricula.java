package br.com.sistema.redAmber.rn;

import java.util.List;

import br.com.sistema.redAmber.DAO.IDAOMatricula;
import br.com.sistema.redAmber.DAO.factory.DAOFactory;
import br.com.sistema.redAmber.basicas.Matricula;
import br.com.sistema.redAmber.exceptions.DAOException;

public class RNMatricula {
	
	private IDAOMatricula daoMatricula;
	
	public RNMatricula(){
		
		this.daoMatricula = DAOFactory.getDaoMatricula();
		
	}
	
	
	public void salvar(Matricula matricula){
		
		Matricula matriculaRetorno = null;
		if (matricula.getId() != null) {
			matriculaRetorno = this.daoMatricula.consultarPorId(matricula.getId());
		}
		
		
		if (matriculaRetorno == null) {
			
			this.daoMatricula.inserir(matricula);
			
		}else{
			
			matricula.setId(matriculaRetorno.getId());
			
			this.daoMatricula.alterar(matricula);
			
		}
		
	}
	
	public Matricula buscarMatriculaPorCodigoMatricula(String codigoMatricula) throws DAOException{
		
		return this.daoMatricula.buscarMatriculaPorCodigoMatricula(codigoMatricula);
		
	}
	
	public List<Matricula> listarMatriculasPorIdAluno(Long idAluno){
		
		return this.daoMatricula.listarMatriculasPorIdAluno(idAluno);
		
	}
	
	public Matricula buscarMatriculaPorId(Long id) {
		
		return this.daoMatricula.consultarPorId(id);
		
	}
	
	public Matricula buscarMatriculaAtivaPorCurso(Long idAluno, Long idCurso) throws DAOException {
		
		return this.daoMatricula.buscarMatriculaAtivaPorCurso(idAluno, idCurso);
		
	}
}