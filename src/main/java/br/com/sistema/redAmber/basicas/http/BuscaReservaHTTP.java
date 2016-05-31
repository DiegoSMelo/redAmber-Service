package br.com.sistema.redAmber.basicas.http;

import br.com.sistema.redAmber.basicas.enums.StatusReserva;

public class BuscaReservaHTTP {

	private Long idProfessor;
	private Long dataReserva;
	private Long dataRequisicao;
	private StatusReserva status;
	
	/**
	 * Getters and setters
	 */
	public Long getIdProfessor() {
		return idProfessor;
	}
	public void setIdProfessor(Long idProfessor) {
		this.idProfessor = idProfessor;
	}
	public Long getDataReserva() {
		return dataReserva;
	}
	public void setDataReserva(Long dataReserva) {
		this.dataReserva = dataReserva;
	}
	public Long getDataRequisicao() {
		return dataRequisicao;
	}
	public void setDataRequisicao(Long dataRequisicao) {
		this.dataRequisicao = dataRequisicao;
	}
	public StatusReserva getStatus() {
		return status;
	}
	public void setStatus(StatusReserva status) {
		this.status = status;
	}
}