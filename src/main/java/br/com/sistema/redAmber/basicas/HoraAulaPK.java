package br.com.sistema.redAmber.basicas;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.sistema.redAmber.basicas.enums.DiasSemana;

@Embeddable
public class HoraAulaPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	@ManyToOne
	private Turma turma;*/
	
	@ManyToOne
	private Aula aula;
	
	@Enumerated
	private DiasSemana dia;
	
	@Temporal(TemporalType.TIME)
	private Date horaInicio;
	
	@Temporal(TemporalType.TIME)
	private Date horaFim;
	
	


	public Aula getAula() {
		return aula;
	}

	public void setAula(Aula aula) {
		this.aula = aula;
	}

	public DiasSemana getDia() {
		return dia;
	}

	public void setDia(DiasSemana dia) {
		this.dia = dia;
	}

	public Date getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Date getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(Date horaFim) {
		this.horaFim = horaFim;
	}
	
	
	
}
