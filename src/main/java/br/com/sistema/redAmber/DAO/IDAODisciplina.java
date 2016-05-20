package br.com.sistema.redAmber.DAO;

import java.util.List;

import br.com.sistema.redAmber.DAO.generics.IDAOGeneric;
import br.com.sistema.redAmber.basicas.Disciplina;
import br.com.sistema.redAmber.exceptions.DAOException;

public interface IDAODisciplina extends IDAOGeneric<Disciplina> {
	
	public Disciplina buscarDisciplinaPorTitulo(String titulo) throws DAOException;
	public Disciplina buscarDisciplinaPorTituloCurso(String titulo, Long idCurso) throws DAOException;
	public List<Disciplina> buscarDisciplinasPorCurso(Long idCurso) throws DAOException;
	public List<Disciplina> buscarDisciplinasAtivas() throws DAOException;
}