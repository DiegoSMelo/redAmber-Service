package br.com.sistema.redAmber.basicas;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Aula {

	@EmbeddedId
	private AulaPK id;

	public AulaPK getId() {
		return id;
	}

	public void setId(AulaPK id) {
		this.id = id;
	}
	
	
}
