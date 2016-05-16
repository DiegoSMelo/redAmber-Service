package br.com.sistema.redAmber.basicas;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.sistema.redAmber.basicas.enums.StatusDuracaoAula;
import br.com.sistema.redAmber.basicas.enums.TipoTurno;

@Entity
public class DuracaoAula {

	@Id @GeneratedValue
	private Long id;
	@Enumerated(EnumType.STRING)
	private TipoTurno turno;
	@Temporal(TemporalType.TIMESTAMP)
	private Date horaInicio;
	@Temporal(TemporalType.TIMESTAMP)
	private Date horaFim;
	@Enumerated
	private StatusDuracaoAula status;
	
	/*
	 * Construtor padrão
	 */
	public DuracaoAula(){}
	
	/*
	 * Construtor com parâmetros
	 */
	public DuracaoAula(TipoTurno turno, Date horaInicio, Date horaFim) {
		this.turno = turno;
		this.horaInicio = horaInicio;
		this.horaFim = horaFim;
	}
	
	/*
	 * Construtor com parâmetros (completo)
	 */
	public DuracaoAula(Long id, TipoTurno turno, Date horaInicio, Date horaFim,
			StatusDuracaoAula status) {
		this.id = id;
		this.turno = turno;
		this.horaInicio = horaInicio;
		this.horaFim = horaFim;
		this.status = status;
	}

	/*
	 * Método que compara se dois objetos do tipo DuracaoAula são iguais
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DuracaoAula other = (DuracaoAula) obj;
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

	public StatusDuracaoAula getStatus() {
		return status;
	}

	public void setStatus(StatusDuracaoAula status) {
		this.status = status;
	}
}