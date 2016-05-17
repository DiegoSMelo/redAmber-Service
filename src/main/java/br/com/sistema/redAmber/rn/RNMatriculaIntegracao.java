package br.com.sistema.redAmber.rn;

import java.util.List;

import br.com.sistema.redAmber.DAO.IDAOMatriculaIntegracao;
import br.com.sistema.redAmber.DAO.factory.DAOFactory;
import br.com.sistema.redAmber.basicas.integracao.MatriculaIntegracao;
import br.com.sistema.redAmber.exceptions.DAOException;

public class RNMatriculaIntegracao {

	private IDAOMatriculaIntegracao daoMatriculaIntegracao;
	
	public RNMatriculaIntegracao() {
		this.daoMatriculaIntegracao = DAOFactory.getDaoMatriculaIntegracao();
	}
	
	public void salvar(MatriculaIntegracao matriculaIntegracao) {
		
		MatriculaIntegracao matriculaRetorno = null;
		if (matriculaIntegracao.getId() != null) {
			matriculaRetorno = this.daoMatriculaIntegracao.consultarPorId(matriculaIntegracao.getId());
		}		
		if (matriculaRetorno == null) {	
			this.daoMatriculaIntegracao.inserir(matriculaIntegracao);
		} else {			
			matriculaIntegracao.setId(matriculaRetorno.getId());
			this.daoMatriculaIntegracao.alterar(matriculaIntegracao);
		}
	}
	
	public MatriculaIntegracao buscarMatriculaPorCodigoMatricula(String codigoMatricula) throws 
		DAOException {	
		return this.daoMatriculaIntegracao.buscarMatriculaPorCodigoMatricula(codigoMatricula);
	}
	
	public List<MatriculaIntegracao> listarMatriculasPorIdAluno(Long idAluno){	
		return this.daoMatriculaIntegracao.listarMatriculasPorIdAluno(idAluno);
	}
	
	public MatriculaIntegracao buscarPorId(Long id) {
		return this.daoMatriculaIntegracao.consultarPorId(id);
	}
	
	public List<MatriculaIntegracao> buscarTodos() {
		return this.daoMatriculaIntegracao.consultarTodos();
	}
	
	public MatriculaIntegracao buscarMatriculaAtivaPorCurso(Long idAluno, Long idCurso) throws 
		DAOException {	
		return this.daoMatriculaIntegracao.buscarMatriculaAtivaPorCurso(idAluno, idCurso);
	}
}