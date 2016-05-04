package br.com.sistema.redAmber.rn;

import java.util.List;

import br.com.sistema.redAmber.DAO.IDAOHoraAula;
import br.com.sistema.redAmber.DAO.factory.DAOFactory;
import br.com.sistema.redAmber.basicas.HoraAula;
import br.com.sistema.redAmber.basicas.HoraAulaPK;

public class RNHoraAula {
	
	private IDAOHoraAula daoHoraAula;
	
	public RNHoraAula() {
		this.daoHoraAula = DAOFactory.getDaoHoraAula();
	}
	
	public List<HoraAula> listaHoraAulaPorIdTurma(Long idTurma){
		return this.daoHoraAula.listaHoraAulaPorIdTurma(idTurma);
	}
	
	public HoraAula buscarHoraAulaPorId(HoraAulaPK id){
		return this.daoHoraAula.consultarPorPK(id);
	}
	
	public void adicionarHoraAula(HoraAula horaAula){
		this.daoHoraAula.inserir(horaAula);
	}
	
	public void alterarHoraAula(HoraAula horaAula){
		this.daoHoraAula.alterar(horaAula);
	}
	
	public void removerHoraAula(HoraAula horaAula){
		this.daoHoraAula.remover(horaAula);
	}
	
    public void removerPorIdTurma(Long idTurma){
    	this.daoHoraAula.removerPorIdTurma(idTurma);
    }
}
