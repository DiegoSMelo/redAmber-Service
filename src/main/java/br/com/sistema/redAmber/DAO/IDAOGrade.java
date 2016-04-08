package br.com.sistema.redAmber.DAO;

import java.util.List;

import br.com.sistema.redAmber.DAO.generics.IDAOGeneric;
import br.com.sistema.redAmber.basicas.Grade;

public interface IDAOGrade extends IDAOGeneric<Grade>{
	
	public List<Grade> listarGradesPorCurso(Long idCurso);
}
