package br.com.sistema.redAmber.rn;

import java.util.Calendar;
import java.util.List;

import br.com.sistema.redAmber.DAO.IDAODisciplina;
import br.com.sistema.redAmber.DAO.IDAOProfessor;
import br.com.sistema.redAmber.DAO.factory.DAOFactory;
import br.com.sistema.redAmber.basicas.BuscaProfessor;
import br.com.sistema.redAmber.basicas.Disciplina;
import br.com.sistema.redAmber.basicas.Professor;
import br.com.sistema.redAmber.basicas.Usuario;
import br.com.sistema.redAmber.basicas.enums.StatusUsuario;
import br.com.sistema.redAmber.exceptions.DAOException;
import br.com.sistema.redAmber.exceptions.EmailException;
import br.com.sistema.redAmber.exceptions.RNException;
import br.com.sistema.redAmber.util.Mensagens;

public class RNProfessor {

	private IDAOProfessor daoProfessor;
	private IDAODisciplina daoDisciplina;

	public RNProfessor() {
		this.daoProfessor = DAOFactory.getDaoProfessor();
		this.daoDisciplina = DAOFactory.getDaoDisciplina();
	}

	public void salvar(Professor professor) throws DAOException, RNException, EmailException {

		Professor professorExistente = null;
		Usuario usuario = new Usuario();
		
		Calendar hoje = Calendar.getInstance();
		String emailVerificacao = professor.getEmail();
		
		if (professor.getDataNascimento().after(hoje)) {
			throw new RNException(Mensagens.m11);
		}
		
		if (professor.getId() == null) {
			if (this.daoProfessor.buscarProfessorPorEmail(emailVerificacao) != null) {
				throw new EmailException(Mensagens.m13);
			}
		} else {
			if (this.daoProfessor.buscarProfessorPorEmail(emailVerificacao) != null && 
					this.daoProfessor.buscarProfessorPorEmail(emailVerificacao).getId() != 
					professor.getId()) {
				throw new EmailException(Mensagens.m13);
			}
		}
		
		if (professor.getId() != null) {
			professorExistente = this.daoProfessor.consultarPorId(professor.getId());
		}
		if (professorExistente == null) {

			professor.setStatus(StatusUsuario.ATIVO);
			this.daoProfessor.inserir(professor);
		} else {
			professor.setId(professorExistente.getId());
			if (professor.getUsuario() != null) {
				usuario.setId(professor.getId());
				usuario.setLogin(professor.getUsuario().getLogin());
				//usuario.setSenha(Criptografia.criptografarSenhas(professor.getUsuario().getSenha()));
				usuario.setSenha(professor.getUsuario().getSenha());
				professor.setUsuario(usuario);
			}
			this.daoProfessor.alterar(professor);
		}
	}

	public Professor buscarProfessorPorLoginSenha(String login, String senha) throws DAOException {
		/*
		 * Senha já vem criptografada
		 */
		return this.daoProfessor.buscarProfessorPorLoginSenha(login, senha);
	}

	public Professor buscarPorId(Long id) {
		return this.daoProfessor.consultarPorId(id);
	}

	public List<Professor> listarTodosProfessores() {
		return this.daoProfessor.consultarTodos();
	}

	public Professor buscarProfessorPorRG(String rg) throws DAOException {
		return this.daoProfessor.buscarProfessorPorRg(rg);
	}

	public List<Professor> listarProfessoresPorDisciplina(Long idDisciplina) {
		Disciplina disciplina = this.daoDisciplina.consultarPorId(idDisciplina);
		return this.daoProfessor.listarProfessoresPorDisciplina(disciplina);
	}
	
	public List<Professor> buscarProfessoresPorNomeRG(BuscaProfessor bP) throws DAOException {
		return this.daoProfessor.buscarProfessoresPorNomeRG(bP);
	}
	
	public List<Disciplina> buscarDisciplinasPorProfessor(Long idProfessor) {
		return this.daoProfessor.buscarDisciplinasPorProfessor(idProfessor);
	}
}