package br.com.sistema.redAmber.basicas;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.sistema.redAmber.basicas.enums.StatusCurso;
import br.com.sistema.redAmber.basicas.enums.TipoCurso;



@Entity
public class Curso {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable=false,unique=true)
	private String  nome;
	
	@Column(nullable=false)
	@Temporal(TemporalType.DATE)
	private Calendar dataInicio;
	
	@Column(nullable=false)
	@Temporal(TemporalType.DATE)
	private Calendar dataFim;
	
	@Column(nullable=false, unique = true)
	private String sigla;
	
	@Column(nullable=true)
	private Integer cargaHorariaTotal;
	
	@Enumerated
	private StatusCurso status;
	
	@Enumerated
	private TipoCurso tipoCurso;
		

	
	
	public TipoCurso getTipoCurso() {
		return tipoCurso;
	}

	public void setTipoCurso(TipoCurso tipoCurso) {
		this.tipoCurso = tipoCurso;
	}

	public StatusCurso getStatus() {
		return status;
	}

	public void setStatus(StatusCurso status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Calendar getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Calendar dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Calendar getDataFim() {
		return dataFim;
	}

	public void setDataFim(Calendar dataFim) {
		this.dataFim = dataFim;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public Integer getCargaHorariaTotal() {
		return cargaHorariaTotal;
	}

	public void setCargaHorariaTotal(Integer cargaHorariaTotal) {
		this.cargaHorariaTotal = cargaHorariaTotal;
	}

	public Boolean equals(Curso curso){
	 if(curso.getNome().equals(this.getNome()) || curso.getSigla().equals(this.getSigla())){
		 return true;
	 }
	 return false;
	}
	

}
