package br.com.sistema.redAmber.basicas;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

/**
 * Representa o período.
 * 
 * @author Diego Melo
 *
 */

@Entity
public class Grade_Disciplina {
	
	@EmbeddedId
	private Grade_Disciplina_PK id;
	
	private Integer nPeriodo;

	public Grade_Disciplina_PK getId() {
		return id;
	}

	public void setId(Grade_Disciplina_PK id) {
		this.id = id;
	}

	public Integer getnPeriodo() {
		return nPeriodo;
	}

	public void setnPeriodo(Integer nPeriodo) {
		this.nPeriodo = nPeriodo;
	}
	
	
}
