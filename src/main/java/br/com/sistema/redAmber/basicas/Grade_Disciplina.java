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
	
	

	public Grade_Disciplina_PK getId() {
		return id;
	}

	public void setId(Grade_Disciplina_PK id) {
		this.id = id;
	}

	
	
}
