package br.com.sistema.redAmber.basicas;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Professor extends GeralUsuario{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Column(nullable=true)
	@ManyToMany
	@JoinTable(name="professor_disciplina", joinColumns={@JoinColumn(name="professor_id")}, inverseJoinColumns={@JoinColumn(name="disciplina_id")})
	private List<Disciplina> listaDisciplinas;


	public List<Disciplina> getListDisciplinas() {
		return listaDisciplinas;
	}


	public void setListDisciplinas(List<Disciplina> listaDisciplinas) {
		this.listaDisciplinas = listaDisciplinas;
	}
	
	
	
}
