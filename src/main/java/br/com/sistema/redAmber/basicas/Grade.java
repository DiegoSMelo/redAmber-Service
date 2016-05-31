package br.com.sistema.redAmber.basicas;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.sistema.redAmber.basicas.enums.StatusGrade;

@Entity
public class Grade {
	
	@Id @GeneratedValue
	private Long id;
	@ManyToOne
	private Curso curso;
	private String titulo;
	private boolean vigente;
	@Enumerated
	private StatusGrade status;
	
	/*
	 * Getters and setters
	 */
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public boolean isVigente() {
		return vigente;
	}
	public void setVigente(boolean vigente) {
		this.vigente = vigente;
	}
	public StatusGrade getStatus() {
		return status;
	}
	public void setStatus(StatusGrade status) {
		this.status = status;
	}
}