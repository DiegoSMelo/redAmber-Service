package br.com.sistema.redAmber.DAO;

import java.util.Calendar;
import java.util.List;

import br.com.sistema.redAmber.DAO.generics.IDAOGeneric;
import br.com.sistema.redAmber.basicas.ReservaEquipamento;

public interface IDAOReservaEquipamento extends IDAOGeneric<ReservaEquipamento> {
	
	public List<ReservaEquipamento> buscarReservasPendentes();
	
	public ReservaEquipamento verificarReservasPorDataReservaHorario(Long idEquipamento, 
			Calendar dataReserva, Long idHorario);
}