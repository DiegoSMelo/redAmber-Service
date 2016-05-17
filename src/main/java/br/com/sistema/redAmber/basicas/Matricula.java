package br.com.sistema.redAmber.basicas;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.sistema.redAmber.basicas.enums.StatusMatricula;

@Entity
public class Matricula {

	@Id @GeneratedValue
	private Long id;
	
	@Column(nullable=false, unique=true)
	private String codigoMatricula;
	
	@ManyToOne
	private Aluno aluno;
	
	@ManyToOne
	private Grade grade;
	
	@Temporal(TemporalType.DATE)
	private Calendar dataMatricula;
	
	private Integer entrada;
	
	@ManyToOne
	private Turma turma;
	
	@Enumerated
	private StatusMatricula status;
	
	/*
	 * Getters and setters
	 */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigoMatricula() {
		return codigoMatricula;
	}

	public void setCodigoMatricula(String codigoMatricula) {
		this.codigoMatricula = codigoMatricula;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Calendar getDataMatricula() {
		return dataMatricula;
	}

	public void setDataMatricula(Calendar dataMatricula) {
		this.dataMatricula = dataMatricula;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public StatusMatricula getStatus() {
		return status;
	}

	public void setStatus(StatusMatricula status) {
		this.status = status;
	}
	
	public Integer getEntrada() {
		 return entrada;
	}
		 
	public void setEntrada(Integer entrada) {
		 this.entrada = entrada;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}
}