package br.com.sistema.redAmber.basicas;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;

import br.com.sistema.redAmber.basicas.enums.TipoFuncionario;

@Entity
public class Funcionario extends GeralUsuario{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Enumerated
	@Column(nullable=false)
	private TipoFuncionario tipoFuncionario;
	

	public TipoFuncionario getTipoFuncionario() {
		return tipoFuncionario;
	}
	public void setTipoFuncionario(TipoFuncionario tipoFuncionario) {
		this.tipoFuncionario = tipoFuncionario;
	}
	
}
