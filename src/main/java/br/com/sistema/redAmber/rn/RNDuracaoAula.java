package br.com.sistema.redAmber.rn;

import java.util.Date;
import java.util.List;

import br.com.sistema.redAmber.DAO.IDAODuracaoAula;
import br.com.sistema.redAmber.DAO.factory.DAOFactory;
import br.com.sistema.redAmber.basicas.DuracaoAula;
import br.com.sistema.redAmber.basicas.enums.StatusDuracaoAula;
import br.com.sistema.redAmber.basicas.enums.TipoTurno;
import br.com.sistema.redAmber.exceptions.DAOException;

public class RNDuracaoAula {

	private IDAODuracaoAula daoDuracaoAula;
	
	public RNDuracaoAula() {
		this.daoDuracaoAula = DAOFactory.getDaoDuracaoAula();
	}
	
	public void salvar(DuracaoAula duracaoAula) {
		DuracaoAula duracaoAulaExistente = null;
		if (duracaoAula.getId() != null) {
			duracaoAulaExistente = this.daoDuracaoAula.consultarPorId(duracaoAula.getId());
		}
		if (duracaoAulaExistente == null) {
			duracaoAula.setStatus(StatusDuracaoAula.ATIVA);
			this.daoDuracaoAula.inserir(duracaoAula);
		} else {
			duracaoAula.setId(duracaoAulaExistente.getId());
			this.daoDuracaoAula.alterar(duracaoAula);
		}
	}
	
	public DuracaoAula buscarPorId(Long id) {
		return this.daoDuracaoAula.consultarPorId(id);
	}
	
	public DuracaoAula buscarPorHoraInicioHoraFim(Date horaInicio, Date horaFim) throws DAOException {
		return this.daoDuracaoAula.consultarPorHoraInicioHoraFim(horaInicio, horaFim);
	}
	
	public List<DuracaoAula> buscarTodos() {
		return this.daoDuracaoAula.consultarTodos();
	}
	
	public List<DuracaoAula> consultarPorTurno(TipoTurno turno) {
		return this.daoDuracaoAula.consultarPorTurno(turno);
	}
}