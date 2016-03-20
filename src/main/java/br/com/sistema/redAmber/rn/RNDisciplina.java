package br.com.sistema.redAmber.rn;

import java.util.List;

import br.com.sistema.redAmber.DAO.IDAODisciplina;
import br.com.sistema.redAmber.DAO.factory.DAOFactory;
import br.com.sistema.redAmber.basicas.Disciplina;
import br.com.sistema.redAmber.basicas.enums.StatusDisciplina;
import br.com.sistema.redAmber.exceptions.DAOException;

public class RNDisciplina {
	
	private IDAODisciplina daoDisciplina;

	public RNDisciplina(){
		this.daoDisciplina = DAOFactory.getDaoDisciplina();
	}
	
	public void salvar(Disciplina disciplina) throws DAOException {
		//Disciplina disciplinaExistente = this.daoDisciplina.buscarDisciplinaPorTitulo(disciplina.getTitulo());
		Disciplina disciplinaExistente = null;
		if (disciplina.getId() != null) {
			disciplinaExistente = this.daoDisciplina.consultarPorId(disciplina.getId());
		}
		
		
		if (disciplinaExistente == null) {	
			disciplina.setStatus(StatusDisciplina.ATIVO);
			this.daoDisciplina.inserir(disciplina);
			
		}else{
			
			disciplina.setId(disciplinaExistente.getId());
			this.daoDisciplina.alterar(disciplina);
			
		}
		
	}
			
	public Disciplina buscarPorId(Long id){
		return this.daoDisciplina.consultarPorId(id);
	}
	
	public List<Disciplina> listarTodosDiscipina(){
		return this.daoDisciplina.consultarTodos();
	}
	
	public Disciplina buscarDisciplinaPorTitulo(String titulo) throws DAOException{
		return this.daoDisciplina.buscarDisciplinaPorTitulo(titulo);
	}

}
