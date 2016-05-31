package br.com.sistema.redAmber.basicas;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import br.com.sistema.redAmber.basicas.enums.StatusHoraAula;

@Entity
public class HoraAula {

	@EmbeddedId
	private HoraAulaPK id;
	
	@ManyToOne
	private Turma turma;
	
	@Enumerated
	private StatusHoraAula status;
	

	/*
	 * Getters and setters
	 */
	public HoraAulaPK getId() {
		return id;
	}

	public void setId(HoraAulaPK id) {
		this.id = id;
	}

	

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public StatusHoraAula getStatus() {
		return status;
	}

	public void setStatus(StatusHoraAula status) {
		this.status = status;
	}

	
}