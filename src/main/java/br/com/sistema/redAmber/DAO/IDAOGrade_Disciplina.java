package br.com.sistema.redAmber.DAO;

import java.util.List;

import br.com.sistema.redAmber.DAO.generics.IDAOGeneric;
import br.com.sistema.redAmber.basicas.Grade_Disciplina;

public interface IDAOGrade_Disciplina extends IDAOGeneric<Grade_Disciplina> {

	
	public List<Grade_Disciplina> buscarPorIdGrade(Long id_grade);
	public List<Grade_Disciplina> buscarPorIdCurso(Long id_curso);
	public void removerPorGrade(Long id_grade);
}
