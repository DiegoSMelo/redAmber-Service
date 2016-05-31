package br.com.sistema.redAmber.basicas;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.sistema.redAmber.basicas.enums.StatusAvisoProfessor;
import br.com.sistema.redAmber.basicas.enums.TipoAvisoProfessor;

@Entity
public class AvisoProfessor {

	@Id @GeneratedValue
	private Long id;
	@ManyToOne
	private Professor professor;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataAviso;
	private String observacao;
	@Enumerated
	private TipoAvisoProfessor tipoAvisoProfessor;
	@Enumerated
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
	public Professor getProfessor() {
		return professor;
	}
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	public Calendar getDataAviso() {
		return dataAviso;
	}
	public void setDataAviso(Calendar dataAviso) {
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