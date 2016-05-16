package br.com.sistema.redAmber.basicas;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.sistema.redAmber.basicas.enums.StatusReserva;

@Entity
public class ReservaEquipamento {

	@Id @GeneratedValue
	private Long id;
	@ManyToOne
	private Professor professor;
	@ManyToOne
	private Equipamento equip;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataRequisicao;
	@Temporal(TemporalType.DATE)
	private Calendar dataReserva;
	@ManyToOne
	private DuracaoAula horarioReserva;
	@Lob
	private String observacao;
	@Lob
	private String resposta;
	@Enumerated
	private StatusReserva status;
	
	/*
	 * Getters and setters
	 */
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Professor getProfessor() {
		return professor;
	}
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	public Equipamento getEquip() {
		return equip;
	}
	public void setEquip(Equipamento equip) {
		this.equip = equip;
	}
	public Calendar getDataRequisicao() {
		return dataRequisicao;
	}
	public void setDataRequisicao(Calendar dataRequisicao) {
		this.dataRequisicao = dataRequisicao;
	}
	public Calendar getDataReserva() {
		return dataReserva;
	}
	public void setDataReserva(Calendar dataReserva) {
		this.dataReserva = dataReserva;
	}
	public DuracaoAula getHorarioReserva() {
		return horarioReserva;
	}
	public void setHorarioReserva(DuracaoAula horarioReserva) {
		this.horarioReserva = horarioReserva;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public String getResposta() {
		return resposta;
	}
	public void setResposta(String resposta) {
		this.resposta = resposta;
	}
	public StatusReserva getStatus() {
		return status;
	}
	public void setStatus(StatusReserva status) {
		this.status = status;
	}
}