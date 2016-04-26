package br.com.sistema.redAmber.basicas;

import java.util.Calendar;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.sistema.redAmber.basicas.enums.DiasSemana;
import br.com.sistema.redAmber.basicas.enums.StatusHoraAula;

@Entity
public class HoraAula {

	@EmbeddedId
	private HoraAulaPK id;
	
	@Enumerated
	private DiasSemana dia;
	
	@Temporal(TemporalType.TIME)
	private Calendar horaInicio;
	
	@Temporal(TemporalType.TIME)
	private Calendar horaFim;
	
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

	public Calendar getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Calendar horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Calendar getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(Calendar horaFim) {
		this.horaFim = horaFim;
	}

	public StatusHoraAula getStatus() {
		return status;
	}

	public void setStatus(StatusHoraAula status) {
		this.status = status;
	}

	public DiasSemana getDia() {
		return dia;
	}

	public void setDia(DiasSemana dia) {
		this.dia = dia;
	}
}