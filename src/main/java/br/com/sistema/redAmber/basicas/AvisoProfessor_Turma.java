package br.com.sistema.redAmber.basicas;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class AvisoProfessor_Turma {

	@EmbeddedId
	private AvisoProfessor_TurmaPK id;
	@MapsId("idAvisoProfessor")
	@ManyToOne
	private AvisoProfessor avisoProfessor;
	@MapsId("idTurma")
	@ManyToOne
	private Turma turma;
	
	/*
	 * Getters and setters
	 */
	public AvisoProfessor_TurmaPK getId() {
		return id;
	}
	public void setId(AvisoProfessor_TurmaPK id) {
		this.id = id;
	}
	public AvisoProfessor getAvisoProfessor() {
		return avisoProfessor;
	}
	public void setAvisoProfessor(AvisoProfessor avisoProfessor) {
		this.avisoProfessor = avisoProfessor;
	}
	public Turma getTurma() {
		return turma;
	}
	public void setTurma(Turma turma) {
		this.turma = turma;
	}
}