package br.com.sistema.redAmber.rn;

import java.util.Calendar;
import java.util.List;

import br.com.sistema.redAmber.DAO.IDAOAvisoProfessor;
import br.com.sistema.redAmber.DAO.factory.DAOFactory;
import br.com.sistema.redAmber.basicas.AvisoProfessor;
import br.com.sistema.redAmber.basicas.BuscaAvisoProfessor;
import br.com.sistema.redAmber.exceptions.DAOException;

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
	
	public List<AvisoProfessor> consultarPorData(Calendar data) throws DAOException {
		return daoAvisoProfessor.consultarPorData(data);
	}
	
	public List<AvisoProfessor> consultarPendentes() throws DAOException {
		return daoAvisoProfessor.consultarPendentes();
	}
	
	public List<AvisoProfessor> consultarPorProfessorData(Long idProfessor, 
			Calendar dataAviso) throws DAOException {
		return daoAvisoProfessor.consultarPorProfessorData(idProfessor, dataAviso);
	}
	
	public Integer consultarQuantidadeDeHoje() {
		return daoAvisoProfessor.consultarQuantidadeDeHoje();
	}
	
	public List<AvisoProfessor> listarAvisosProfessorPorParametros(BuscaAvisoProfessor consulta) {
		return daoAvisoProfessor.listarAvisosProfessorPorParametros(consulta);
	}
}