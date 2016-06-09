package br.com.sistema.redAmber.rn;

import java.util.Calendar;
import java.util.List;

import br.com.sistema.redAmber.DAO.IDAOFuncionario;
import br.com.sistema.redAmber.DAO.factory.DAOFactory;
import br.com.sistema.redAmber.basicas.BuscaFuncionario;
import br.com.sistema.redAmber.basicas.Funcionario;
import br.com.sistema.redAmber.basicas.Usuario;
import br.com.sistema.redAmber.basicas.enums.StatusUsuario;
import br.com.sistema.redAmber.basicas.enums.TipoFuncionario;
import br.com.sistema.redAmber.exceptions.DAOException;
import br.com.sistema.redAmber.exceptions.EmailException;
import br.com.sistema.redAmber.exceptions.RNException;
import br.com.sistema.redAmber.util.Criptografia;
import br.com.sistema.redAmber.util.Datas;
import br.com.sistema.redAmber.util.Mensagens;

public class RNFuncionario {

	private IDAOFuncionario daoFunc;

	public RNFuncionario() {
		this.daoFunc = DAOFactory.getDaoFuncionario();
	}

	public void salvar(Funcionario funcionario) throws DAOException, RNException, EmailException {
		Funcionario funcionarioExistente = null;
		
		Calendar hoje = Calendar.getInstance();
		String emailVerificacao = funcionario.getEmail();
		
		if (funcionario.getDataNascimento().after(hoje)) {
			throw new RNException(Mensagens.m11);
		}
		
		if (funcionario.getId() == null) {
			if (this.daoFunc.buscarFuncionarioPorEmail(emailVerificacao) != null) {
				throw new EmailException(Mensagens.m14);
			}
		} else {
			if (this.daoFunc.buscarFuncionarioPorEmail(emailVerificacao) != null && 
					this.daoFunc.buscarFuncionarioPorEmail(emailVerificacao).getId() != 
					funcionario.getId()) {
				throw new EmailException(Mensagens.m14);
			}
		}
		
		if (funcionario.getId() != null) {
			funcionarioExistente = this.daoFunc.consultarPorId(funcionario.getId());
		}

		if (funcionarioExistente == null) {
			funcionario.setStatus(StatusUsuario.ATIVO);
			this.daoFunc.inserir(funcionario);
		} else {
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

	public Funcionario buscarFuncionarioPorLogin(String login) throws DAOException {
		return this.daoFunc.buscarFuncionarioPorLogin(login);
	}

	public Funcionario buscarPorId(Long id) {
		return this.daoFunc.consultarPorId(id);
	}

	public List<Funcionario> listarTodosFuncionarios() {
		return this.daoFunc.consultarTodos();
	}

	public void inserirAdmin() throws DAOException, RNException, EmailException {

		if (this.buscarFuncionarioPorLoginSenha("funcionario-admin", "funcionario-admin") == null) {
			Funcionario f = new Funcionario();
			f.setDataNascimento(Datas.converterDateToCalendar(Datas.criarData(06, 3, 2016)));
			f.setEmail("funcionario-admin@redamber.com.br");

			f.setNome("Funcionário Administrador");
			f.setRg("8888888");
			f.setTelefone("081888888888");
			f.setTipoFuncionario(TipoFuncionario.C);
			f.setStatus(StatusUsuario.ATIVO);

			this.salvar(f);

			Funcionario funcr = this.buscarFuncionarioPorRG("8888888");
			Usuario usuario = new Usuario();
			usuario.setLogin("funcionario-admin");
			usuario.setSenha(Criptografia.criptografarSenhas("funcionario-admin"));
			usuario.setId(funcr.getId());
			funcr.setUsuario(usuario);

			this.salvar(funcr);
		}
	}
	
	public List<Funcionario> buscarFuncionariosPorNomeRG(BuscaFuncionario bF) throws DAOException {
		return this.daoFunc.buscarFuncionariosPorNomeRG(bF);
	}
}