package br.com.sistema.redAmber.basicas;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class AvisoProfessor_TurmaPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long idAvisoProfessor;
	private Long idTurma;
	
	/*
	 * Getters and setters
	 */
	public Long getIdAvisoProfessor() {
		return idAvisoProfessor;
	}
	public void setIdAvisoProfessor(Long idAvisoProfessor) {
		this.idAvisoProfessor = idAvisoProfessor;
	}
	public Long getIdTurma() {
		return idTurma;
	}
	public void setIdTurma(Long idTurma) {
		this.idTurma = idTurma;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idAvisoProfessor == null) ? 0 : idAvisoProfessor.hashCode());
		result = prime * result + ((idTurma == null) ? 0 : idTurma.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AvisoProfessor_TurmaPK other = (AvisoProfessor_TurmaPK) obj;
		if (idAvisoProfessor == null) {
			if (other.idAvisoProfessor != null)
				return false;
		} else if (!idAvisoProfessor.equals(other.idAvisoProfessor))
			return false;
		if (idTurma == null) {
			if (other.idTurma != null)
				return false;
		} else if (!idTurma.equals(other.idTurma))
			return false;
		return true;
	}
}