package br.com.sistema.redAmber.basicas;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.sistema.redAmber.basicas.enums.StatusUsuario;



@Entity
public class Curso {
	
	@Id
	@GeneratedValue
	private Long idCurso;
	
	@Column(nullable=false)
	private String  nomeCurso;
	
	@Column(nullable=false)
	@Temporal(TemporalType.DATE)
	private Calendar dataInicioCurso;
	
	@Column(nullable=false)
	@Temporal(TemporalType.DATE)
	private Calendar dataFimCurso;
	
	@Column(nullable=false)
	private String siglaCurso;
	
	@Column(nullable=true)
	private Integer cargaHorariaTotalCurso;
	
	@OneToMany
	private List<Periodo> periodos;
	
	@Enumerated
	private StatusUsuario status;

	
	
	public StatusUsuario getStatus() {
		return status;
	}

	public void setStatus(StatusUsuario status) {
		this.status = status;
	}

	public Long getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(Long idCurso) {
		this.idCurso = idCurso;
	}

	public String getNomeCurso() {
		return nomeCurso;
	}

	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}

	public Calendar getDataInicioCurso() {
		return dataInicioCurso;
	}

	public void setDataInicioCurso(Calendar dataInicioCurso) {
		this.dataInicioCurso = dataInicioCurso;
	}

	public Calendar getDataFimCurso() {
		return dataFimCurso;
	}

	public void setDataFimCurso(Calendar dataFimCurso) {
		this.dataFimCurso = dataFimCurso;
	}

	public String getSiglaCurso() {
		return siglaCurso;
	}

	public void setSiglaCurso(String siglaCurso) {
		this.siglaCurso = siglaCurso;
	}

	public Integer getCargaHorariaTotalCurso() {
		return cargaHorariaTotalCurso;
	}

	public void setCargaHorariaTotalCurso(Integer cargaHorariaTotalCurso) {
		this.cargaHorariaTotalCurso = cargaHorariaTotalCurso;
	}

	public List<Periodo> getPeriodos() {
		return periodos;
	}

	public void setPeriodos(List<Periodo> periodos) {
		this.periodos = periodos;
	}
	

}
