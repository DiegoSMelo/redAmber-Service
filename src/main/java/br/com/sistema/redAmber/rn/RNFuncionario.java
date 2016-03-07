package br.com.sistema.redAmber.rn;

import java.util.List;

import br.com.sistema.redAmber.DAO.IDAOFuncionario;
import br.com.sistema.redAmber.DAO.factory.DAOFactory;
import br.com.sistema.redAmber.basicas.Funcionario;
import br.com.sistema.redAmber.basicas.enums.StatusUsuario;
import br.com.sistema.redAmber.exceptions.DAOException;

public class RNFuncionario {
	private IDAOFuncionario daoFunc;

	public RNFuncionario(){
		this.daoFunc = DAOFactory.getDaoFuncionario();
	}
	
	public void salvar(Funcionario funcionario) throws DAOException {
		Funcionario funcionarioExistente = this.daoFunc.buscarFuncionarioPorRG(funcionario.getRg());
		
		if (funcionarioExistente == null) {
			
			funcionario.setStatus(StatusUsuario.ATIVO);
			this.daoFunc.inserir(funcionario);
			
		}else{
			
			funcionario.setId(funcionarioExistente.getId());
			this.daoFunc.alterar(funcionario);
			
		}
		
	}
	
	public Funcionario buscarFuncionarioPorRG(String rg) throws DAOException {
		return this.daoFunc.buscarFuncionarioPorRG(rg);
	}
	
	public Funcionario buscarFuncionarioPorLoginSenha(String login, String senha) throws DAOException {
		/*
		 * Senha já vem criptografada
		 */
		return this.daoFunc.buscarFuncionarioPorLoginSenha(login, senha);
	}
	
	public Funcionario buscarPorId(Long id){
		return this.daoFunc.consultarPorId(id);
	}

	public List<Funcionario> listarTodosAlunos(){
		return this.daoFunc.consultarTodos();
	}
}
