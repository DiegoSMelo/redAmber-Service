package br.com.sistema.redAmber.rn;

import java.util.List;

import br.com.sistema.redAmber.DAO.IDAOAvisoProfessor_Turma;
import br.com.sistema.redAmber.DAO.factory.DAOFactory;
import br.com.sistema.redAmber.basicas.AvisoProfessor_Turma;

public class RNAvisoProfessor_Turma {

	private IDAOAvisoProfessor_Turma daoAvisoProfessor_Turma;
	
	public RNAvisoProfessor_Turma() {
		this.daoAvisoProfessor_Turma = DAOFactory.getDaoAvisoProfessor_Turma();
	}
	
	public void adicionar(AvisoProfessor_Turma avisoProfessor_Turma) {
		this.daoAvisoProfessor_Turma.inserir(avisoProfessor_Turma);
	}
	
	public AvisoProfessor_Turma buscarPorId(Long id) {
		return daoAvisoProfessor_Turma.consultarPorId(id);
	}
	
	public List<AvisoProfessor_Turma> listarTodos() {
		return daoAvisoProfessor_Turma.consultarTodos();
	}
}