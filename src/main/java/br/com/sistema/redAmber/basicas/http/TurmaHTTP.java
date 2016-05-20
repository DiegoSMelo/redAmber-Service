package br.com.sistema.redAmber.basicas.http;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import br.com.sistema.redAmber.basicas.enums.StatusTurma;
import br.com.sistema.redAmber.basicas.enums.TipoTurno;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown=true)
public class TurmaHTTP {
	
	private Long id;
	private String nome;
	private CursoHTTP curso;
	private TipoTurno turno;
	private StatusTurma status;

	public TurmaHTTP() {}
	
	public TurmaHTTP(Long id, String nome, CursoHTTP curso, TipoTurno turno, StatusTurma status) {
		this.id = id;
		this.nome = nome;
		this.curso = curso;
		this.turno = turno;
		this.status = status;
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public CursoHTTP getCurso() {
		return curso;
	}

	public void setCurso(CursoHTTP curso) {
		this.curso = curso;
	}
}