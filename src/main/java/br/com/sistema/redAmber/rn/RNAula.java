package br.com.sistema.redAmber.rn;

import java.util.List;

import br.com.sistema.redAmber.DAO.IDAOAula;
import br.com.sistema.redAmber.DAO.factory.DAOFactory;
import br.com.sistema.redAmber.basicas.Aula;
import br.com.sistema.redAmber.basicas.AulaPK;

public class RNAula {
	
	private IDAOAula daoAula;
	
	public RNAula() {
		this.daoAula = DAOFactory.getDaoAula();
	}
	
	
	public List<Aula> listarAulas(){
		return this.daoAula.consultarTodos();
	}
	
	public Aula buscarAulaPorPK(AulaPK pk){
		return this.daoAula.buscarAulaPorPK(pk);
	}
	
	public void addAula(Aula aula){
		this.daoAula.inserir(aula);
	}
	
	public void alterarAula(Aula aula){
		this.daoAula.alterar(aula);
	}
	
	public void removerAula(Aula aula){
		this.daoAula.remover(aula);
	}
	
}
