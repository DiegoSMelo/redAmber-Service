package br.com.sistema.redAmber.rn;

import java.util.List;

import br.com.sistema.redAmber.DAO.IDAOAluno;
import br.com.sistema.redAmber.DAO.factory.DAOFactory;
import br.com.sistema.redAmber.basicas.Aluno;
import br.com.sistema.redAmber.basicas.enums.StatusUsuario;
import br.com.sistema.redAmber.exceptions.DAOException;
import br.com.sistema.redAmber.util.Criptografia;

public class RNAluno {
	
	private IDAOAluno daoAluno;

	public RNAluno(){
		this.daoAluno = DAOFactory.getDaoAluno();
	}
	
	public void salvar(Aluno aluno) throws DAOException {
		aluno.setSenha(Criptografia.criptografarSenhas(aluno.getSenha()));
		Aluno alunoExistente = this.daoAluno.buscarAlunoPorRG(aluno.getRg());
		
		if (alunoExistente == null) {
			
			aluno.setStatus(StatusUsuario.ATIVO);
			this.daoAluno.inserir(aluno);
			
		}else{
			
			aluno.setId(alunoExistente.getId());
			this.daoAluno.alterar(aluno);
			
		}
		
	}
	
	public Aluno buscarAlunoPorLoginSenha(String login, String senha) throws DAOException {
		return this.daoAluno.buscarAlunoPorLoginSenha(login, Criptografia.criptografarSenhas(senha));
	}
	
	public Aluno buscarPorId(Long id){
		return this.daoAluno.consultarPorId(id);
	}
	
	public List<Aluno> listarTodosAlunos(){
		return this.daoAluno.consultarTodos();
	}
	
	
}
