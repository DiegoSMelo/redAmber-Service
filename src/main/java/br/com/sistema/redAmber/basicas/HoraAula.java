package br.com.sistema.redAmber.basicas;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.sistema.redAmber.basicas.enums.StatusHoraAula;
import br.com.sistema.redAmber.basicas.enums.TipoTurno;

@Entity
public class HoraAula {

	@Id @GeneratedValue
	private Long id;
	@Enumerated(EnumType.STRING)
	private TipoTurno turno;
	@Temporal(TemporalType.TIMESTAMP)
	private Date horaInicio;
	@Temporal(TemporalType.TIMESTAMP)
	private Date horaFim;
	@Enumerated
	private StatusHoraAula status;
	
	/*
	 * Construtor padr�o
	 */
	public HoraAula(){}
	
	/*
	 * Construtor com par�metros
	 */
	public HoraAula(TipoTurno turno, Date horaInicio, Date horaFim) {
		this.turno = turno;
		this.horaInicio = horaInicio;
		this.horaFim = horaFim;
	}
	
	/*
	 * Construtor com par�metros (completo)
	 */
	public HoraAula(Long id, TipoTurno turno, Date horaInicio, Date horaFim,
			StatusHoraAula status) {
		this.id = id;
		this.turno = turno;
		this.horaInicio = horaInicio;
		this.horaFim = horaFim;
		this.status = status;
	}

	/*
	 * M�todo que compara se dois objetos do tipo HoraAula s�o iguais
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HoraAula other = (HoraAula) obj;
		if (horaFim == null) {
			if (other.horaFim != null)
				return false;
		} else if (!horaFim.equals(other.horaFim))
			return false;
		if (horaInicio == null) {
			if (other.horaInicio != null)
				return false;
		} else if (!horaInicio.equals(other.horaInicio))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (turno != other.turno)
			return false;
		return true;
	}

	/*
	 * Getters and setters
	 */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoTurno getTurno() {
		return turno;
	}

	public void setTurno(TipoTurno turno) {
		this.turno = turno;
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

	public StatusHoraAula getStatus() {
		return status;
	}

	public void setStatus(StatusHoraAula status) {
		this.status = status;
	}
}