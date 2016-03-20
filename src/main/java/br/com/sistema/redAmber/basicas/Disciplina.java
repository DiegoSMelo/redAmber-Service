package br.com.sistema.redAmber.basicas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import br.com.sistema.redAmber.basicas.enums.StatusDisciplina;

@Entity
public class Disciplina {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable=false, unique=true)
	private String titulo;
	
	
	private String descricao;
	
	@Enumerated
	private StatusDisciplina status;

	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public StatusDisciplina getStatus() {
		return status;
	}

	public void setStatus(StatusDisciplina status) {
		this.status = status;
	}	
}
