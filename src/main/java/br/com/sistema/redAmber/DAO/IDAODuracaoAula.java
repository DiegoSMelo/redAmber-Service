package br.com.sistema.redAmber.DAO;

import java.util.Date;

import br.com.sistema.redAmber.DAO.generics.IDAOGeneric;
import br.com.sistema.redAmber.basicas.DuracaoAula;
import br.com.sistema.redAmber.exceptions.DAOException;

public interface IDAODuracaoAula extends IDAOGeneric<DuracaoAula> {
	
	public DuracaoAula consultarPorHoraInicioHoraFim(Date horaInicio, Date horaFim) throws DAOException;
}