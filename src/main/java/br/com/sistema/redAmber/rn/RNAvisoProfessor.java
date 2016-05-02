package br.com.sistema.redAmber.rn;

import java.util.List;

import br.com.sistema.redAmber.DAO.IDAOAvisoProfessor;
import br.com.sistema.redAmber.DAO.factory.DAOFactory;
import br.com.sistema.redAmber.basicas.AvisoProfessor;
import br.com.sistema.redAmber.basicas.enums.StatusAvisoProfessor;

public class RNAvisoProfessor {

	private IDAOAvisoProfessor daoAvisoProfessor;
	
	public RNAvisoProfessor() {
		this.daoAvisoProfessor = DAOFactory.getDaoAvisoProfessor();
	}
	
	public void salvar(AvisoProfessor avisoProfessor) {
		
		AvisoProfessor avisoProfessorExistente = null;
		if(avisoProfessor.getId() != null) {
			avisoProfessorExistente = this.daoAvisoProfessor.consultarPorId(avisoProfessor.getId());
		}
		
		if(avisoProfessorExistente == null) {
			avisoProfessor.setStatusAvisoProfessor(StatusAvisoProfessor.ATIVO);
			this.daoAvisoProfessor.inserir(avisoProfessor);
		} else {
			avisoProfessor.setId(avisoProfessorExistente.getId());
			this.daoAvisoProfessor.alterar(avisoProfessor);
		}
	}
	
	public AvisoProfessor buscarPorId(Long id) {
		return daoAvisoProfessor.consultarPorId(id);
	}
	
	public List<AvisoProfessor> listarTodos() {
		return daoAvisoProfessor.consultarTodos();
	}
}