package br.com.sistema.redAmber.basicas;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class AvisoAluno {

	@Id @GeneratedValue
	private Long id;
	@ManyToOne
	private Funcionario funcionario;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataAviso;
	private String observacao;
	@ManyToOne
	private Turma turma;
	
	/*
	 * Getters and setters
	 */
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	public Calendar getDataAviso() {
		return dataAviso;
	}
	public void setDataAviso(Calendar dataAviso) {
		this.dataAviso = dataAviso;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public Turma getTurma() {
		return turma;
	}
	public void setTurma(Turma turma) {
		this.turma = turma;
	}
}