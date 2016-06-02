package br.com.sistema.redAmber.rn;

import java.util.Calendar;
import java.util.List;

import br.com.sistema.redAmber.DAO.IDAOAluno;
import br.com.sistema.redAmber.DAO.factory.DAOFactory;
import br.com.sistema.redAmber.basicas.Aluno;
import br.com.sistema.redAmber.basicas.BuscaAluno;
import br.com.sistema.redAmber.basicas.Disciplina;
import br.com.sistema.redAmber.basicas.GeralUsuario;
import br.com.sistema.redAmber.basicas.Usuario;
import br.com.sistema.redAmber.basicas.enums.StatusUsuario;
import br.com.sistema.redAmber.exceptions.DAOException;
import br.com.sistema.redAmber.exceptions.EmailException;
import br.com.sistema.redAmber.exceptions.RNException;
import br.com.sistema.redAmber.util.Criptografia;
import br.com.sistema.redAmber.util.Datas;
import br.com.sistema.redAmber.util.Mensagens;

public class RNAluno {

	private IDAOAluno daoAluno;

	public RNAluno() {
		this.daoAluno = DAOFactory.getDaoAluno();
	}

	public void salvar(Aluno aluno) throws DAOException, RNException, EmailException {
		// Aluno alunoExistente = this.daoAluno.buscarAlunoPorRG(aluno.getRg());
		Aluno alunoExistente = null;
		Usuario usuario = new Usuario();
		Calendar hoje = Calendar.getInstance();
		String emailVerificacao = aluno.getEmail();
		
		if (aluno.getDataNascimento().after(hoje)) {
			throw new RNException(Mensagens.m11);
		}
		
		if (aluno.getId() == null) {
			if (this.daoAluno.buscarAlunoPorEmail(emailVerificacao) != null) {
				throw new EmailException(Mensagens.m12);
			}
		} else {
			if (this.daoAluno.buscarAlunoPorEmail(emailVerificacao) != null && 
					this.daoAluno.buscarAlunoPorEmail(emailVerificacao).getId() != aluno.getId()) {
				throw new EmailException(Mensagens.m12);
			}
		}
		
		if (aluno.getId() != null) {
			alunoExistente = this.daoAluno.consultarPorId(aluno.getId());
		}
		
		if (alunoExistente == null) {		
			aluno.setStatus(StatusUsuario.ATIVO);
			this.daoAluno.inserir(aluno);
		} else {
			aluno.setId(alunoExistente.getId());
			if (aluno.getUsuario() != null) {
				usuario.setId(aluno.getId());
				usuario.setLogin(aluno.getUsuario().getLogin());
				//usuario.setSenha(Criptografia.criptografarSenhas(aluno.getUsuario().getSenha()));
				usuario.setSenha(aluno.getUsuario().getSenha());
				aluno.setUsuario(usuario);
			}
			this.daoAluno.alterar(aluno);
		}
	}

	public Aluno buscarAlunoPorLoginSenha(String login, String senha) throws DAOException {
		/*
		 * Senha já vem criptografada
		 */
		return this.daoAluno.buscarAlunoPorLoginSenha(login, senha);
	}

	public Aluno buscarPorId(Long id) {
		return this.daoAluno.consultarPorId(id);
	}

	public List<Aluno> listarTodosAlunos() {
		return this.daoAluno.consultarTodos();
	}

	public Aluno buscarAlunoPorRG(String rg) throws DAOException {
		return this.daoAluno.buscarAlunoPorRG(rg);
	}

	public void inserirAlunoAdmin() throws DAOException, RNException, EmailException {

		if (this.buscarAlunoPorLoginSenha("aluno-admin", "aluno-admin") == null) {
			Aluno a = new Aluno();
			a.setDataNascimento(Datas.converterDateToCalendar(Datas.criarData(06, 3, 2016)));
			a.setEmail("aluno-admin@redamber.com.br");

			a.setNome("Aluno Administrador");
			a.setRg("9999999");
			a.setTelefone("08199999999");
			a.setStatus(StatusUsuario.ATIVO);
			this.salvar(a);

			Aluno alunor = this.buscarAlunoPorRG("9999999");
			Usuario usuario = new Usuario();
			usuario.setLogin("aluno-admin");
			usuario.setSenha(Criptografia.criptografarSenhas("aluno-admin"));
			usuario.setId(alunor.getId());
			alunor.setUsuario(usuario);

			this.salvar(alunor);
		}
	}
	
	public List<Aluno> buscarAlunosPorNomeRG(BuscaAluno buscaAluno) throws DAOException {
		return daoAluno.buscarAlunosPorNomeRG(buscaAluno);
	}
	
	public GeralUsuario buscarGeralUsuarioPorLoginSenha(String login, String senha) throws DAOException {
		return daoAluno.buscarGeralUsuarioPorLoginSenha(login, senha);
	}
	
	public List<Disciplina> buscarDisciplinasPorAluno(Long idAluno) {
		return this.daoAluno.buscarDisciplinasPorAluno(idAluno);
	}
}