package br.com.sistema.redAmber.DAO;

import java.util.Date;
import java.util.List;

import br.com.sistema.redAmber.DAO.generics.IDAOGeneric;
import br.com.sistema.redAmber.basicas.DuracaoAula;
import br.com.sistema.redAmber.basicas.enums.TipoTurno;
import br.com.sistema.redAmber.exceptions.DAOException;

public interface IDAODuracaoAula extends IDAOGeneric<DuracaoAula> {
	
	public DuracaoAula consultarPorHoraInicioHoraFim(Date horaInicio, Date horaFim) throws DAOException;
	public List<DuracaoAula> consultarPorTurno(TipoTurno turno);
}