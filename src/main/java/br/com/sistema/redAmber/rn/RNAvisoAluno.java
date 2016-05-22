package br.com.sistema.redAmber.rn;

import java.util.List;

import br.com.sistema.redAmber.DAO.IDAOAvisoAluno;
import br.com.sistema.redAmber.DAO.factory.DAOFactory;
import br.com.sistema.redAmber.basicas.AvisoAluno;

public class RNAvisoAluno {

	private IDAOAvisoAluno daoAvisoAluno;
	
	public RNAvisoAluno() {
		this.daoAvisoAluno = DAOFactory.getDaoAvisoAluno();
	}
	
	public void salvar(AvisoAluno avisoAluno) {
		
		AvisoAluno avisoAlunoExistente = null;
		if(avisoAluno.getId() != null) {
			avisoAlunoExistente = this.daoAvisoAluno.consultarPorId(avisoAluno.getId());
		}
		
		if(avisoAlunoExistente == null) {
			this.daoAvisoAluno.inserir(avisoAluno);
		} else {
			avisoAluno.setId(avisoAlunoExistente.getId());
			this.daoAvisoAluno.alterar(avisoAluno);
		}
	}
	
	public AvisoAluno buscarPorId(Long id) {
		return daoAvisoAluno.consultarPorId(id);
	}
	
	public List<AvisoAluno> listarTodos() {
		return daoAvisoAluno.consultarTodos();
	}
	
	public List<AvisoAluno> listarAvisosAlunoPorAluno(Long idAluno) {
		return daoAvisoAluno.listarAvisosAlunoPorAluno(idAluno);
	}
}