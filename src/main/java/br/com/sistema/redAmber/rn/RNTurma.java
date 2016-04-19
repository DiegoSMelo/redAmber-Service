package br.com.sistema.redAmber.rn;

import java.util.List;

import br.com.sistema.redAmber.DAO.IDAOTurma;
import br.com.sistema.redAmber.DAO.factory.DAOFactory;
import br.com.sistema.redAmber.basicas.Curso;
import br.com.sistema.redAmber.basicas.Turma;
import br.com.sistema.redAmber.basicas.enums.StatusTurma;
import br.com.sistema.redAmber.basicas.enums.TipoTurno;
import br.com.sistema.redAmber.exceptions.DAOException;

public class RNTurma {
	
	private IDAOTurma daoTurma;
	
	public RNTurma() {
		this.daoTurma = DAOFactory.getDaoTurma();
	}
	
	public void salvar(Turma turma) throws DAOException {

		Turma turmaExistente = null;
		
		if (turma.getId() != null) {
			turmaExistente = this.daoTurma.consultarPorId(turma.getId());
		}
		
		
		if (turmaExistente == null) {
			
			turma.setStatus(StatusTurma.PENDENTE);
			this.daoTurma.inserir(turma);
			
		}else{
			
			turma.setId(turmaExistente.getId());
			this.daoTurma.alterar(turma);
			
		}
		
	}
	
	public List<Turma> listarTurmas(){
		return this.daoTurma.consultarTodos();
	}
	
	public Turma buscarPorId(Long id){
		return this.daoTurma.consultarPorId(id);
	}
	
	public List<Turma> buscarTurmasPorCursoTurno(Curso curso, TipoTurno turno) {
		return this.daoTurma.consultarTurmasPorCursoTurno(curso, turno);
	}
	
}
