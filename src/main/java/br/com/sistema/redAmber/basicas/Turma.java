package br.com.sistema.redAmber.basicas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.sistema.redAmber.basicas.enums.StatusTurma;
import br.com.sistema.redAmber.basicas.enums.TipoTurno;

@Entity
public class Turma {
	
	@Id @GeneratedValue
	private Long id;
	
	@Column(nullable=false)
	private String nome;
	
	@ManyToOne(optional=false)
	private Curso curso;
	
	@Enumerated
	@Column(nullable=false)
	private TipoTurno turno;
	
	@Enumerated
	@Column(nullable=false)
	private StatusTurma status;

	
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

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public TipoTurno getTurno() {
		return turno;
	}

	public void setTurno(TipoTurno turno) {
		this.turno = turno;
	}
	
	public StatusTurma getStatus() {
		return status;
	}

	public void setStatus(StatusTurma status) {
		this.status = status;
	}

}
