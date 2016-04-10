package br.com.sistema.redAmber.DAO;

import java.util.Date;

import br.com.sistema.redAmber.DAO.generics.IDAOGeneric;
import br.com.sistema.redAmber.basicas.HoraAula;
import br.com.sistema.redAmber.exceptions.DAOException;

public interface IDAOHoraAula extends IDAOGeneric<HoraAula> {
	
	public HoraAula consultarPorHoraInicioHoraFim(Date horaInicio, Date horaFim) throws DAOException;
}