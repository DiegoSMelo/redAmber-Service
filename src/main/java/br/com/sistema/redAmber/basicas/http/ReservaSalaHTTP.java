package br.com.sistema.redAmber.basicas.http;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import br.com.sistema.redAmber.basicas.enums.StatusReserva;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown=true)
public class ReservaSalaHTTP {

	private Long id;
	private ProfessorHTTP professor;
	private SalaHTTP sala;
	private String dataRequisicao;
	private String dataReserva;
	private DuracaoAulaHTTP horarioReserva;
	private String observacao;
	private String resposta;
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
	public ProfessorHTTP getProfessor() {
		return professor;
	}
	public void setProfessor(ProfessorHTTP professor) {
		this.professor = professor;
	}
	public SalaHTTP getSala() {
		return sala;
	}
	public void setSala(SalaHTTP sala) {
		this.sala = sala;
	}
	public String getDataRequisicao() {
		return dataRequisicao;
	}
	public void setDataRequisicao(String dataRequisicao) {
		this.dataRequisicao = dataRequisicao;
	}
	public String getDataReserva() {
		return dataReserva;
	}
	public void setDataReserva(String dataReserva) {
		this.dataReserva = dataReserva;
	}
	public DuracaoAulaHTTP getHorarioReserva() {
		return horarioReserva;
	}
	public void setHorarioReserva(DuracaoAulaHTTP horarioReserva) {
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