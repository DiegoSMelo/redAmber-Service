package br.com.sistema.redAmber.DAO;

import java.util.List;

import br.com.sistema.redAmber.DAO.generics.IDAOGeneric;
import br.com.sistema.redAmber.basicas.Sala;
import br.com.sistema.redAmber.exceptions.DAOException;

public interface IDAOSala extends IDAOGeneric<Sala> {
	
	public Sala consultarPorDescricao(String descricao) throws DAOException;
	public List<Sala> listarPorDescricao(String descricao);
}