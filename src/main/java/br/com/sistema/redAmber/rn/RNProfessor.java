package br.com.sistema.redAmber.rn;

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

public class RNProfessor {

	private IDAOProfessor daoProfessor;
	private IDAODisciplina daoDisciplina;

	public RNProfessor() {
		this.daoProfessor = DAOFactory.getDaoProfessor();
		this.daoDisciplina = DAOFactory.getDaoDisciplina();
	}

	public void salvar(Professor professor) throws DAOException {

		Professor professorExistente = null;
		Usuario usuario = new Usuario();
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
}