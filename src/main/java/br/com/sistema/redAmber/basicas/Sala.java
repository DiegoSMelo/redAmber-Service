package br.com.sistema.redAmber.basicas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import br.com.sistema.redAmber.basicas.enums.StatusSala;
import br.com.sistema.redAmber.basicas.enums.TipoSala;

@Entity
public class Sala {

	@Id
	@GeneratedValue
	private Long id;
	@Column(unique=true)
	private String descricao;
	private Integer capacidadeAlunos;
	@Enumerated
	private TipoSala tipoSala;
	@Enumerated
	private StatusSala status;
	
	/*
	 * Construtor padrão
	 */
	public Sala() {}

	/*
	 * Construtor com parâmetros
	 */
	public Sala(Long id, String descricao, Integer capacidadeAlunos, TipoSala tipoSala,
			StatusSala status) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.capacidadeAlunos = capacidadeAlunos;
		this.tipoSala = tipoSala;
		this.status = status;
	}

	/*
	 * Método equals sobrescrito. Ele serve para definir os critérios que definem se um objeto é ou
	 * não igual a outro.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || this == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		Sala other = (Sala) obj;
		if (other.getId().intValue() == this.getId().intValue()) {
			return true;
		}
		if (other.getDescricao().equalsIgnoreCase(this.getDescricao())){
			return true;
		}
		return false;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getCapacidadeAlunos() {
		return capacidadeAlunos;
	}

	public void setCapacidadeAlunos(Integer capacidadeAlunos) {
		this.capacidadeAlunos = capacidadeAlunos;
	}

	public TipoSala getTipoSala() {
		return tipoSala;
	}
	
	public void setTipoSala(TipoSala tipoSala) {
		this.tipoSala = tipoSala;
	}
	
	public StatusSala getStatus() {
		return status;
	}

	public void setStatus(StatusSala status) {
		this.status = status;
	}
}