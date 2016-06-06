package br.com.sistema.redAmber.DAO;

import java.util.Date;
import java.util.List;

import br.com.sistema.redAmber.DAO.generics.IDAOGeneric;
import br.com.sistema.redAmber.basicas.Aula;
import br.com.sistema.redAmber.basicas.HoraAula;
import br.com.sistema.redAmber.basicas.HoraAulaPK;
import br.com.sistema.redAmber.basicas.enums.DiasSemana;

public interface IDAOHoraAula extends IDAOGeneric<HoraAula> {

	public List<HoraAula> listaHoraAulaPorIdTurma(Long idTurma);
	public HoraAula consultarPorPK(HoraAulaPK pk);
	public void removerPorIdTurma(Long idTurma);
	public List<HoraAula> listarHoraAulaPorProfessor(Long idProfessor);
	public List<HoraAula> listarHoraAulaPorProfessorTurma(Long idProfessor, Long idTurma);
	public List<HoraAula> listarHoraAulaPorAluno(Long idAluno);
	
	public List<HoraAula> listarHoraAulaPorAlunoHoje(Long idAluno);
	public List<HoraAula> listarHoraAulaPorTurmaHoje(Long idTurma);
	public List<Aula> listarAulaPorAlunoHoje(Long idAluno);
	public List<Aula> listarAulaPorTurmaHoje(Long idTurma);
	
	public List<HoraAula> listarHoraAulaPorProfessorHoje(Long idProfessor);
	public List<Aula> listarAulaPorProfessorHoje(Long idProfessor);
	public void removerHoraAula(Date HoraInicio, Date HoraFim, DiasSemana diaSemana, Long idTurma);
}