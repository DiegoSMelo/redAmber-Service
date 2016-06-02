package br.com.sistema.redAmber.DAO;

import java.util.List;

import br.com.sistema.redAmber.DAO.generics.IDAOGeneric;
import br.com.sistema.redAmber.basicas.Aluno;
import br.com.sistema.redAmber.basicas.BuscaAluno;
import br.com.sistema.redAmber.basicas.Disciplina;
import br.com.sistema.redAmber.basicas.GeralUsuario;
import br.com.sistema.redAmber.exceptions.DAOException;

public interface IDAOAluno extends IDAOGeneric<Aluno>{
		
	public Aluno buscarAlunoPorRG(String rg) throws DAOException;
	public Aluno buscarAlunoPorLoginSenha(String login, String senha) throws DAOException;
	public List<Aluno> buscarAlunosPorNomeRG(BuscaAluno aluno) throws DAOException;
	public GeralUsuario buscarGeralUsuarioPorLoginSenha(String login, String senha) throws DAOException;
	public List<Disciplina> buscarDisciplinasPorAluno(Long idAluno);
	public Aluno buscarAlunoPorEmail(String email);
}