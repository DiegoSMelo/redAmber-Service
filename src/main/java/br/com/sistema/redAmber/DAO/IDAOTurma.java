package br.com.sistema.redAmber.DAO;

import java.util.List;

import br.com.sistema.redAmber.DAO.generics.IDAOGeneric;
import br.com.sistema.redAmber.basicas.Curso;
import br.com.sistema.redAmber.basicas.Turma;
import br.com.sistema.redAmber.basicas.enums.TipoTurno;

public interface IDAOTurma extends IDAOGeneric<Turma>{
	
	public List<Turma> consultarTurmasPorCursoTurno(Curso curso, TipoTurno turno);
}
