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

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Funcionario other = (Funcionario) obj;
		if (this.getId().intValue() == other.getId().intValue()) {
			return true;
		}
		return false;
	}

	public TipoFuncionario getTipoFuncionario() {
		return tipoFuncionario;
	}
	public void setTipoFuncionario(TipoFuncionario tipoFuncionario) {
		this.tipoFuncionario = tipoFuncionario;
	}
	
	
	
	
}
