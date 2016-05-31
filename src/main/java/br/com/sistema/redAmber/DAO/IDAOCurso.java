package br.com.sistema.redAmber.DAO;

import java.util.List;

import br.com.sistema.redAmber.DAO.generics.IDAOGeneric;
import br.com.sistema.redAmber.basicas.Curso;
import br.com.sistema.redAmber.basicas.enums.TipoCurso;
import br.com.sistema.redAmber.exceptions.DAOException;

public interface IDAOCurso extends IDAOGeneric<Curso> {

	public Curso buscarCursoPorNomeCurso(String nomeCurso) throws DAOException;
	public Curso buscarCursoPorNomeESigla(String nomeCurso, String sigla) throws DAOException;
	public List<Curso> listarCursosPorTipo(TipoCurso tipoCurso);
}