package br.com.sistema.redAmber.rn;

import java.util.List;

import br.com.sistema.redAmber.DAO.IDAOEquipamento;
import br.com.sistema.redAmber.DAO.factory.DAOFactory;
import br.com.sistema.redAmber.basicas.Equipamento;
import br.com.sistema.redAmber.basicas.enums.StatusEquipamento;
import br.com.sistema.redAmber.exceptions.DAOException;

public class RNEquipamento {

	private IDAOEquipamento daoEquipamento;
	
	public RNEquipamento() {
		this.daoEquipamento = DAOFactory.getDaoEquipamento();
	}
	
	public void salvar(Equipamento equipamento) throws DAOException {
		
		Equipamento equipamentoExistente = null;
		if(equipamento.getId() != null) {
			equipamentoExistente = this.daoEquipamento.consultarPorId(equipamento.getId());
		}
		
		if(equipamentoExistente == null) {
			equipamento.setStatus(StatusEquipamento.ATIVO);
			this.daoEquipamento.inserir(equipamento);
		} else {
			equipamento.setId(equipamentoExistente.getId());
			this.daoEquipamento.alterar(equipamento);
		}
	}
	
	public Equipamento buscarPorId(Long id) {
		return this.daoEquipamento.consultarPorId(id);
	}
	
	public List<Equipamento> buscarTodos() {
		return this.daoEquipamento.consultarTodos();
	}
	
	public Equipamento buscarPorDescricao(String descricao) throws DAOException {
		return this.daoEquipamento.consultarPorDescricao(descricao);
	}
	
	public Equipamento buscarPorTombo(String tombo) throws DAOException {
		return this.daoEquipamento.consultarPorTombo(tombo);
	}
	
	public List<Equipamento> listarPorDescricao(String descricao) {
		return this.daoEquipamento.listarPorDescricao(descricao);
	}
}