package br.com.sistema.redAmber.basicas;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import br.com.sistema.redAmber.basicas.enums.StatusDisciplina;

@Entity
public class Disciplina {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable=false, unique=true)
	private String titulo;
	
	@ManyToMany(mappedBy="listaDisciplinas", fetch = FetchType.LAZY)
	private List<Professor> listaProfessores;
	
	
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

	public List<Professor> getListaProfessores() {
		return listaProfessores;
	}

	public void setListaProfessores(List<Professor> listaProfessores) {
		this.listaProfessores = listaProfessores;
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
