package br.com.sistema.redAmber.DAO;

import java.util.List;

import br.com.sistema.redAmber.DAO.generics.IDAOGeneric;
import br.com.sistema.redAmber.basicas.BuscaProfessor;
import br.com.sistema.redAmber.basicas.Disciplina;
import br.com.sistema.redAmber.basicas.Professor;
import br.com.sistema.redAmber.exceptions.DAOException;

public interface IDAOProfessor extends IDAOGeneric<Professor>{

	public Professor buscarProfessorPorRg(String rg);
	public Professor buscarProfessorPorLoginSenha(String login, String senha) throws DAOException;
	public List<Professor> listarProfessoresPorDisciplina(Disciplina d);
	public List<Professor> buscarProfessoresPorNomeRG(BuscaProfessor bP) throws DAOException;
	public List<Disciplina> buscarDisciplinasPorProfessor(Long idProfessor);
	public Professor buscarProfessorPorEmail(String email);
}