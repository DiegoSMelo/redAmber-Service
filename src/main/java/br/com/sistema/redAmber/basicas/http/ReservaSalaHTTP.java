package br.com.sistema.redAmber.basicas.http;

import br.com.sistema.redAmber.basicas.Professor;
import br.com.sistema.redAmber.basicas.Sala;
import br.com.sistema.redAmber.basicas.enums.StatusReservaSala;

public class ReservaSalaHTTP {

	private Long id;
	private String solicitacao;
	private String dataSolicitacao;
	private String dataReserva;
	private String horaReserva;
	private Sala salaReserva;
	private Professor professor;
	private String resposta;
	private StatusReservaSala statusReserva;
	
	/*
	 * Construtor padrão
	 */
	public ReservaSalaHTTP(){}
	
	/*
	 * Construtor com parâmetros
	 */
	public ReservaSalaHTTP(Long id, String solicitacao, String dataSolicitacao, String dataReserva,
			String horaReserva, Sala salaReserva, Professor professor, StatusReservaSala statusReserva){
		this.id = id;
		this.solicitacao = solicitacao;
		this.dataSolicitacao = dataSolicitacao;
		this.dataReserva = dataReserva;
		this.horaReserva = horaReserva;
		this.salaReserva = salaReserva;
		this.professor = professor;
		this.statusReserva = statusReserva;
	}
	
	/*
	 * Construtor com parâmetros (completo)
	 */
	public ReservaSalaHTTP(Long id, String solicitacao, String dataSolicitacao, String dataReserva,
			String horaReserva, Sala salaReserva, Professor professor, String resposta,
			StatusReservaSala statusReserva){
		this.id = id;
		this.solicitacao = solicitacao;
		this.dataSolicitacao = dataSolicitacao;
		this.dataReserva = dataReserva;
		this.horaReserva = horaReserva;
		this.salaReserva = salaReserva;
		this.professor = professor;
		this.resposta = resposta;
		this.statusReserva = statusReserva;
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
	public String getSolicitacao() {
		return solicitacao;
	}
	public void setSolicitacao(String solicitacao) {
		this.solicitacao = solicitacao;
	}
	public String getDataSolicitacao() {
		return dataSolicitacao;
	}
	public void setDataSolicitacao(String dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}
	public String getDataReserva() {
		return dataReserva;
	}
	public void setDataReserva(String dataReserva) {
		this.dataReserva = dataReserva;
	}
	public String getHoraReserva() {
		return horaReserva;
	}
	public void setHoraReserva(String horaReserva) {
		this.horaReserva = horaReserva;
	}
	public Sala getSalaReserva() {
		return salaReserva;
	}
	public void setSalaReserva(Sala salaReserva) {
		this.salaReserva = salaReserva;
	}
	public Professor getProfessor() {
		return professor;
	}
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	public String getResposta() {
		return resposta;
	}
	public void setResposta(String resposta) {
		this.resposta = resposta;
	}
	public StatusReservaSala getStatusReserva() {
		return statusReserva;
	}
	public void setStatusReserva(StatusReservaSala statusReserva) {
		this.statusReserva = statusReserva;
	}
}