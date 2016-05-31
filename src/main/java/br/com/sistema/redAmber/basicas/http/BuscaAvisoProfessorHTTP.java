package br.com.sistema.redAmber.basicas.http;

import br.com.sistema.redAmber.basicas.enums.StatusAvisoProfessor;

public class BuscaAvisoProfessorHTTP {

	private Long idProfessor;
	private Long dataAviso;
	private StatusAvisoProfessor status;
	
	/**
	 * Getters and setters
	 */
	public Long getIdProfessor() {
		return idProfessor;
	}
	public void setIdProfessor(Long idProfessor) {
		this.idProfessor = idProfessor;
	}
	public Long getDataAviso() {
		return dataAviso;
	}
	public void setDataAviso(Long dataAviso) {
		this.dataAviso = dataAviso;
	}
	public StatusAvisoProfessor getStatus() {
		return status;
	}
	public void setStatus(StatusAvisoProfessor status) {
		this.status = status;
	}
}