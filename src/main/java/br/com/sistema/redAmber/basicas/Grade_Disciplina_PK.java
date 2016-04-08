package br.com.sistema.redAmber.basicas;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class Grade_Disciplina_PK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	private Grade grade;
	
	@ManyToOne
	private Disciplina disciplina;
	
	private Integer nPeriodo;

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Integer getnPeriodo() {
		return nPeriodo;
	}

	public void setnPeriodo(Integer nPeriodo) {
		this.nPeriodo = nPeriodo;
	}
	
	
	
}
