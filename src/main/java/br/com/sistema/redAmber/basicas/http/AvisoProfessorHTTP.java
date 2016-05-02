package br.com.sistema.redAmber.basicas.http;

import br.com.sistema.redAmber.basicas.enums.StatusAvisoProfessor;
import br.com.sistema.redAmber.basicas.enums.TipoAvisoProfessor;

public class AvisoProfessorHTTP {

	private Long id;
	private ProfessorHTTP professor;
	private String dataAviso;
	private String observacao;
	private TipoAvisoProfessor tipoAvisoProfessor;
	private StatusAvisoProfessor statusAvisoProfessor;

	/*
	 * Getters and setters
	 */
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ProfessorHTTP getProfessor() {
		return professor;
	}
	public void setProfessor(ProfessorHTTP professor) {
		this.professor = professor;
	}
	public String getDataAviso() {
		return dataAviso;
	}
	public void setDataAviso(String dataAviso) {
		this.dataAviso = dataAviso;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public TipoAvisoProfessor getTipoAvisoProfessor() {
		return tipoAvisoProfessor;
	}
	public void setTipoAvisoProfessor(TipoAvisoProfessor tipoAvisoProfessor) {
		this.tipoAvisoProfessor = tipoAvisoProfessor;
	}
	public StatusAvisoProfessor getStatusAvisoProfessor() {
		return statusAvisoProfessor;
	}
	public void setStatusAvisoProfessor(StatusAvisoProfessor statusAvisoProfessor) {
		this.statusAvisoProfessor = statusAvisoProfessor;
	}
}