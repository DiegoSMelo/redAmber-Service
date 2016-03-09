package br.com.sistema.redAmber.basicas;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Periodo {
	
	@Id
	@GeneratedValue
	private Long idPeriodo;
	
	@Column(nullable=false)
	@Temporal(TemporalType.DATE)
	private Calendar dataInicioPeriodo;
	
	@Column(nullable=false)
	@Temporal(TemporalType.DATE)
	private Calendar dataFimPeriodo;
	
	@Column(nullable=true)
	private Integer cargaHorariaTotalPeriodo;
	
	@OneToMany
	private List<Disciplina> disciplinas;
	
	
	
	public Long getIdPeriodo() {
		return idPeriodo;
	}
	public void setIdPeriodo(Long idPeriodo) {
		this.idPeriodo = idPeriodo;
	}
	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}
	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}
	public Calendar getDataInicioPeriodo() {
		return dataInicioPeriodo;
	}
	public void setDataInicioPeriodo(Calendar dataInicioPeriodo) {
		this.dataInicioPeriodo = dataInicioPeriodo;
	}
	public Calendar getDataFimPeriodo() {
		return dataFimPeriodo;
	}
	public void setDataFimPeriodo(Calendar dataFimPeriodo) {
		this.dataFimPeriodo = dataFimPeriodo;
	}
	public Integer getCargaHorariaTotalPeriodo() {
		return cargaHorariaTotalPeriodo;
	}
	public void setCargaHorariaTotalPeriodo(Integer cargaHorariaTotalPeriodo) {
		this.cargaHorariaTotalPeriodo = cargaHorariaTotalPeriodo;
	}

}
