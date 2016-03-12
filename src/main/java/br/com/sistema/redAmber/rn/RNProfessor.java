package br.com.sistema.redAmber.rn;

import java.util.List;

import br.com.sistema.redAmber.DAO.IDAOProfessor;
import br.com.sistema.redAmber.DAO.factory.DAOFactory;
import br.com.sistema.redAmber.basicas.Professor;
import br.com.sistema.redAmber.basicas.enums.StatusUsuario;
import br.com.sistema.redAmber.exceptions.DAOException;


public class RNProfessor {
	
	private IDAOProfessor daoProfessor;

	public RNProfessor(){
		this.daoProfessor = DAOFactory.getDaoProfessor();
	}
	
	public void salvar(Professor professor) throws DAOException {
		
		Professor professorExistente = this.daoProfessor.buscarProfessorPorRg(professor.getRg());
		
		if (professorExistente == null) {
			
			professor.setStatus(StatusUsuario.ATIVO);
			this.daoProfessor.inserir(professor);
			
		}else{
			
			professor.setId(professorExistente.getId());
			this.daoProfessor.alterar(professor);
			
		}
	}
	
	public Professor buscarProfessorPorLoginSenha(String login, String senha) throws DAOException {
		/*
		 * Senha já vem criptografada
		 */
		return this.daoProfessor.buscarProfessorPorLoginSenha(login, senha);
	}
	
	public Professor buscarPorId(Long id){
		return this.daoProfessor.consultarPorId(id);
	}
	
	public List<Professor> listarTodosProfessores(){
		return this.daoProfessor.consultarTodos();
	}
	
	public Professor buscarProfessorPorRG(String rg) throws DAOException{
		return this.daoProfessor.buscarProfessorPorRg(rg);
	}
}
