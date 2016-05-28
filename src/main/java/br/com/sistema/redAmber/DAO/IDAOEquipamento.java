package br.com.sistema.redAmber.DAO;

import java.util.List;

import br.com.sistema.redAmber.DAO.generics.IDAOGeneric;
import br.com.sistema.redAmber.basicas.Equipamento;
import br.com.sistema.redAmber.exceptions.DAOException;

public interface IDAOEquipamento extends IDAOGeneric<Equipamento> {
	
	public Equipamento consultarPorDescricao(String descricao) throws DAOException;
	public Equipamento consultarPorTombo(String tombo) throws DAOException;
	public List<Equipamento> listarPorDescricao(String descricao);
}