package br.com.sistema.redAmber.rn;

import java.util.List;

import br.com.sistema.redAmber.DAO.IDAOCurso;
import br.com.sistema.redAmber.DAO.factory.DAOFactory;
import br.com.sistema.redAmber.basicas.Curso;
import br.com.sistema.redAmber.exceptions.DAOException;

public class RNCurso {
	private IDAOCurso daoCurso;

	public RNCurso(){
		this.daoCurso = DAOFactory.getDaoCurso();
	}
	
	public void salvar(Curso curso) throws DAOException {
		Curso cursoExistente = this.daoCurso.buscarCursoPorNomeCurso(curso.getNomeCurso());
		
		if (cursoExistente == null) {
			
			this.daoCurso.inserir(curso);
			
		}else{
			
			curso.setIdCurso(cursoExistente.getIdCurso());
			this.daoCurso.alterar(curso);
			
		}
		
	}
	
	public List<Curso> listarTodosOsCursos (){
		return this.daoCurso.consultarTodos();
	}

	public Curso buscarCursoPorID (Long idCurso){
		return this.daoCurso.consultarPorId(idCurso);
	}
	
	public Curso BuscarCursoPorNome (String nomeCurso) throws DAOException{
		return this.daoCurso.buscarCursoPorNomeCurso(nomeCurso);
	}
	
	public void RemoverCurso (Curso curso){
		
		this.daoCurso.remover(curso);
		}
}
