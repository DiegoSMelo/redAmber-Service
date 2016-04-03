package br.com.sistema.redAmber.rn;

import java.util.List;

import br.com.sistema.redAmber.DAO.IDAOGrade_Disciplina;
import br.com.sistema.redAmber.DAO.factory.DAOFactory;
import br.com.sistema.redAmber.basicas.Grade_Disciplina;

public class RNGrade_Disciplina {

	private IDAOGrade_Disciplina daoGrade_Disciplina;
	
	public RNGrade_Disciplina() {
		this.daoGrade_Disciplina = DAOFactory.getDaoGrade_Disciplina();
	}
	
	public void adicionar(Grade_Disciplina gd){
		
		this.daoGrade_Disciplina.inserir(gd);
		
	}
	
	public void removerPorGrade(Long id_grade){
		
		this.daoGrade_Disciplina.removerPorGrade(id_grade);
		
	}
	
	public Grade_Disciplina buscarPorId(Long id){
		
		return this.daoGrade_Disciplina.consultarPorId(id);
		
	}
	
	public List<Grade_Disciplina> buscarPorIdGrade(Long id_grade){
		
		return this.daoGrade_Disciplina.buscarPorIdGrade(id_grade);
		
	}
	
	public List<Grade_Disciplina> buscarPorIdCurso(Long id_curso) {
		
		return this.daoGrade_Disciplina.buscarPorIdCurso(id_curso);
		
	}

	
}
