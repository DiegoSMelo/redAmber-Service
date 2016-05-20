package br.com.sistema.redAmber.basicas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import br.com.sistema.redAmber.basicas.enums.StatusDisciplina;

@Entity
public class Disciplina {
	
	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable=false, unique=false)
	private String titulo;
	private String descricao;
	@Lob
	private String conteudoProgramatico;
	@ManyToOne
	private Curso curso;
	@Enumerated
	private StatusDisciplina status;
	
	public Disciplina() {}
	
	public Disciplina(Long id, String titulo, String descricao, StatusDisciplina status) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
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
		try {			
			Disciplina other = (Disciplina) obj;
			if (other.getId() != null && other.getId().intValue() == this.getId().intValue()) {
				return true;
			}
		} catch (NullPointerException e) {
			return false;
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

	public String getConteudoProgramatico() {
		return conteudoProgramatico;
	}

	public void setConteudoProgramatico(String conteudoProgramatico) {
		this.conteudoProgramatico = conteudoProgramatico;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}


	public StatusDisciplina getStatus() {
		return status;
	}

	public void setStatus(StatusDisciplina status) {
		this.status = status;
	}
}