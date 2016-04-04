package br.com.sistema.redAmber.basicas;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.sistema.redAmber.basicas.enums.StatusReservaSala;

@Entity
public class ReservaSala {

	@Id @GeneratedValue
	private Long id;
	private String solicitacao;
	@Temporal(TemporalType.DATE)
	private Calendar dataSolicitacao;
	@Temporal(TemporalType.DATE)
	private Calendar dataReserva;
	@OneToMany
	private List<HoraAula> horasReserva;
	@ManyToOne
	private Sala salaReserva;
	@ManyToOne
	private Professor professor;
	private String resposta;
	private StatusReservaSala statusReserva;
	
	/*
	 * Construtor padrão
	 */
	public ReservaSala(){}
	
	/*
	 * Construtor com parâmetros
	 */
	public ReservaSala(Long id, String solicitacao, Calendar dataSolicitacao, Calendar dataReserva,
			List<HoraAula> horasReserva, Sala salaReserva, Professor professor,
			StatusReservaSala statusReserva) {
		this.id = id;
		this.solicitacao = solicitacao;
		this.dataSolicitacao = dataSolicitacao;
		this.dataReserva = dataReserva;
		this.horasReserva = horasReserva;
		this.salaReserva = salaReserva;
		this.professor = professor;
		this.statusReserva = statusReserva;
	}
	
	/*
	 * Construtor com parâmetros (completo)
	 */
	public ReservaSala(Long id, String solicitacao, Calendar dataSolicitacao, Calendar dataReserva,
			List<HoraAula> horasReserva, Sala salaReserva, Professor professor, String resposta,
			StatusReservaSala statusReserva) {
		this.id = id;
		this.solicitacao = solicitacao;
		this.dataSolicitacao = dataSolicitacao;
		this.dataReserva = dataReserva;
		this.horasReserva = horasReserva;
		this.salaReserva = salaReserva;
		this.professor = professor;
		this.resposta = resposta;
		this.statusReserva = statusReserva;
	}

	/*
	 * Getters and Setters
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

	public Calendar getDataSolicitacao() {
		return dataSolicitacao;
	}

	public void setDataSolicitacao(Calendar dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}

	public Calendar getDataReserva() {
		return dataReserva;
	}

	public void setDataReserva(Calendar dataReserva) {
		this.dataReserva = dataReserva;
	}

	public List<HoraAula> getHorasReserva() {
		return horasReserva;
	}

	public void setHorasReserva(List<HoraAula> horasReserva) {
		this.horasReserva = horasReserva;
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

	/*
	 * Método que verifica se dois objetos do tipo ReservaSala são iguais
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReservaSala other = (ReservaSala) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}