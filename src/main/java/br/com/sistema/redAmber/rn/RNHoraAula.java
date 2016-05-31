package br.com.sistema.redAmber.rn;

import java.util.List;

import br.com.sistema.redAmber.DAO.IDAOHoraAula;
import br.com.sistema.redAmber.DAO.factory.DAOFactory;
import br.com.sistema.redAmber.basicas.Aula;
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
    
	public List<HoraAula> listarHoraAulaPorProfessor(Long idProfessor) {
		return this.daoHoraAula.listarHoraAulaPorProfessor(idProfessor);
	}
	
	public List<HoraAula> listarHoraAulaPorProfessorTurma(Long idProfessor, Long idTurma) {
		return this.daoHoraAula.listarHoraAulaPorProfessorTurma(idProfessor, idTurma);
	}
	
	public List<HoraAula> listarHoraAulaPorAluno(Long idAluno) {
		return this.daoHoraAula.listarHoraAulaPorAluno(idAluno);
	}
	
	public List<HoraAula> listarHoraAulaPorAlunoHoje(Long idAluno) {
		return this.daoHoraAula.listarHoraAulaPorAlunoHoje(idAluno);
	}
	
	public List<HoraAula> listarHoraAulaPorTurmaHoje(Long idTurma) {
		return this.daoHoraAula.listarHoraAulaPorTurmaHoje(idTurma);
	}
	
	public List<Aula> listarAulaPorAlunoHoje(Long idAluno) {
		return this.daoHoraAula.listarAulaPorAlunoHoje(idAluno);
	}
	
	public List<Aula> listarAulaPorTurmaHoje(Long idTurma) {
		return this.daoHoraAula.listarAulaPorTurmaHoje(idTurma);
	}
	
	public List<HoraAula> listarHoraAulaPorProfessorHoje(Long idProfessor) {
		return this.daoHoraAula.listarHoraAulaPorProfessorHoje(idProfessor);
	}
	public List<Aula> listarAulaPorProfessorHoje(Long idProfessor) {
		return this.daoHoraAula.listarAulaPorProfessorHoje(idProfessor);
	}
}