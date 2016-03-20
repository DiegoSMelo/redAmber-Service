package br.com.sistema.redAmber.basicas.http;

import br.com.sistema.redAmber.basicas.Aluno;
import br.com.sistema.redAmber.basicas.enums.StatusMatricula;

public class MatriculaHTTP {
	
	private Long id;
	
	private String codigoMatricula;
	
	private Aluno aluno;
	
	/*
	 * TIMESTAMP
	 */
	private String dataMatricula;
	
	private StatusMatricula status;
	

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

	public String getDataMatricula() {
		return dataMatricula;
	}

	public void setDataMatricula(String dataMatricula) {
		this.dataMatricula = dataMatricula;
	}

	public StatusMatricula getStatus() {
		return status;
	}

	public void setStatus(StatusMatricula status) {
		this.status = status;
	}
	
	
}
