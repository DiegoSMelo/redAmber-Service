package br.com.sistema.redAmber.basicas;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

@Entity
public class Professor extends GeralUsuario{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Column(nullable=true)
	@ManyToMany(fetch=FetchType.LAZY)
	private List<Disciplina> listaDisciplinas;


	public List<Disciplina> getListDisciplinas() {
		return listaDisciplinas;
	}


	public void setListDisciplinas(List<Disciplina> listaDisciplinas) {
		this.listaDisciplinas = listaDisciplinas;
	}
	
	
	
}
