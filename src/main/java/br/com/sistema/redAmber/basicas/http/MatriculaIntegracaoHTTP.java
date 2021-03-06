package br.com.sistema.redAmber.basicas.http;

import br.com.sistema.redAmber.basicas.Grade;
import br.com.sistema.redAmber.basicas.enums.StatusMatricula;

public class MatriculaIntegracaoHTTP {

	private Long id;
	private String codigoMatricula;
	private Long idAluno;
	/*
	 * TIMESTAMP
	 */
	private String dataMatricula;
	private Grade grade;
	private Integer entrada;
	private TurmaHTTP turma;
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
	public Long getIdAluno() {
		return idAluno;
	}
	public void setIdAluno(Long idAluno) {
		this.idAluno = idAluno;
	}
	public String getDataMatricula() {
		return dataMatricula;
	}
	public void setDataMatricula(String dataMatricula) {
		this.dataMatricula = dataMatricula;
	}
	public Grade getGrade() {
		return grade;
	}
	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	public Integer getEntrada() {
		return entrada;
	}
	public void setEntrada(Integer entrada) {
		this.entrada = entrada;
	}
	public TurmaHTTP getTurma() {
		return turma;
	}
	public void setTurma(TurmaHTTP turma) {
		this.turma = turma;
	}
	public StatusMatricula getStatus() {
		return status;
	}
	public void setStatus(StatusMatricula status) {
		this.status = status;
	}
}