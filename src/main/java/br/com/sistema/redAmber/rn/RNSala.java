package br.com.sistema.redAmber.rn;

import java.util.List;

import br.com.sistema.redAmber.DAO.IDAOSala;
import br.com.sistema.redAmber.DAO.factory.DAOFactory;
import br.com.sistema.redAmber.basicas.Sala;
import br.com.sistema.redAmber.basicas.enums.StatusSala;
import br.com.sistema.redAmber.exceptions.DAOException;

public class RNSala {

	private IDAOSala daoSala;
	
	public RNSala() {
		this.daoSala = DAOFactory.getDaoSala();
	}
	
	public void salvar(Sala sala) {
		
		Sala salaExistente = null;
		if(sala.getId() != null) {
			salaExistente = this.daoSala.consultarPorId(sala.getId());
		}
		
		if(salaExistente == null) {
			sala.setStatus(StatusSala.ATIVA);
			this.daoSala.inserir(sala);
		} else {
			sala.setId(salaExistente.getId());
			this.daoSala.alterar(sala);
		}
	}
	
	public Sala buscarPorId(Long id) {
		return this.daoSala.consultarPorId(id);
	}
	
	public List<Sala> buscarTodos() {
		return this.daoSala.consultarTodos();
	}
	
	public Sala buscarPorDescricao(String descricao) throws DAOException {
		return this.daoSala.consultarPorDescricao(descricao);
	}
	
	public List<Sala> listarPorDescricao(String descricao) {
		return this.daoSala.listarPorDescricao(descricao);
	}
}