package br.com.sistema.redAmber.DAO;

import java.util.List;

import br.com.sistema.redAmber.DAO.generics.IDAOGeneric;
import br.com.sistema.redAmber.basicas.HoraAula;
import br.com.sistema.redAmber.basicas.HoraAulaPK;

public interface IDAOHoraAula extends IDAOGeneric<HoraAula> {

	public List<HoraAula> listaHoraAulaPorIdTurma(Long idTurma);
	public HoraAula consultarPorPK(HoraAulaPK pk);
	public void removerPorIdTurma(Long idTurma);
	public List<HoraAula> listarHoraAulaPorProfessor(Long idProfessor);
	public List<HoraAula> listarHoraAulaPorProfessorTurma(Long idProfessor, Long idTurma);
	public List<HoraAula> listarHoraAulaPorAluno(Long idAluno);
}