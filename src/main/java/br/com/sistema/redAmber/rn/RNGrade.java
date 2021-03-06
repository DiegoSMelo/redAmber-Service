package br.com.sistema.redAmber.rn;

import java.util.List;

import br.com.sistema.redAmber.DAO.IDAOGrade;
import br.com.sistema.redAmber.DAO.factory.DAOFactory;
import br.com.sistema.redAmber.basicas.Grade;
import br.com.sistema.redAmber.basicas.enums.StatusGrade;

public class RNGrade {
	
	private IDAOGrade daoGrade;
	
	public RNGrade() {
		this.daoGrade = DAOFactory.getDaoGrade();
	}
	
	public void salvar(Grade grade){
		
		Grade gradeExistente = null;
		
		if (grade.getId() != null) {
			gradeExistente = this.daoGrade.consultarPorId(grade.getId());
		}
		
		
		if (gradeExistente == null) {
			
			grade.setStatus(StatusGrade.PENDENTE);
			this.daoGrade.inserir(grade);
			
		}else{
			
			grade.setId(gradeExistente.getId());
			this.daoGrade.alterar(grade);
			
		}
		
	}
	
	public Grade buscarGradePorId(Long id) {
		return this.daoGrade.consultarPorId(id);
	}
	
	public List<Grade> listarGradesPorCurso(Long idCurso){
		return this.daoGrade.listarGradesPorCurso(idCurso);
	}
	
	public List<Grade> listarGrades() {
		return this.daoGrade.consultarTodos();
	}
}
