package br.com.sistema.redAmber.rn;

import java.util.Date;
import java.util.List;

import br.com.sistema.redAmber.DAO.IDAOHoraAula;
import br.com.sistema.redAmber.DAO.factory.DAOFactory;
import br.com.sistema.redAmber.basicas.HoraAula;
import br.com.sistema.redAmber.basicas.enums.StatusHoraAula;
import br.com.sistema.redAmber.exceptions.DAOException;

public class RNHoraAula {

	private IDAOHoraAula daoHoraAula;
	
	public RNHoraAula() {
		this.daoHoraAula = DAOFactory.getDaoHoraAula();
	}
	
	public void salvar(HoraAula horaAula) {
		HoraAula horaAulaExistente = null;
		if (horaAula.getId() != null) {
			horaAulaExistente = this.daoHoraAula.consultarPorId(horaAula.getId());
		}
		if (horaAulaExistente == null) {
			horaAula.setStatus(StatusHoraAula.ATIVA);
			this.daoHoraAula.inserir(horaAula);
		} else {
			horaAula.setId(horaAulaExistente.getId());
			this.daoHoraAula.alterar(horaAula);
		}
	}
	
	public HoraAula buscarPorId(Long id) {
		return this.daoHoraAula.consultarPorId(id);
	}
	
	public HoraAula buscarPorHoraInicioHoraFim(Date horaInicio, Date horaFim) throws DAOException {
		return this.daoHoraAula.consultarPorHoraInicioHoraFim(horaInicio, horaFim);
	}
	
	public List<HoraAula> buscarTodos() {
		return this.daoHoraAula.consultarTodos();
	}
}