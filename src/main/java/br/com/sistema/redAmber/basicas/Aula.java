package br.com.sistema.redAmber.basicas;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Aula {

	@EmbeddedId
	private AulaPK id;
}
