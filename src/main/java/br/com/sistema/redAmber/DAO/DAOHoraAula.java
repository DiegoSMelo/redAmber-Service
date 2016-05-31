package br.com.sistema.redAmber.DAO;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.sistema.redAmber.DAO.factory.DAOFactory;
import br.com.sistema.redAmber.DAO.generics.DAOGeneric;
import br.com.sistema.redAmber.basicas.Aula;
import br.com.sistema.redAmber.basicas.HoraAula;
import br.com.sistema.redAmber.basicas.HoraAulaPK;
import br.com.sistema.redAmber.basicas.enums.DiasSemana;

public class DAOHoraAula extends DAOGeneric<HoraAula> implements IDAOHoraAula {

	private IDAOAula daoAula;

	public DAOHoraAula(EntityManager em) {
		super(em);

		daoAula = DAOFactory.getDaoAula();
	}

	public List<HoraAula> listaHoraAulaPorIdTurma(Long idTurma) {

		String jpql = "SELECT ha FROM HoraAula ha WHERE ha.turma.id = :idTurma ORDER BY ha.id.horaInicio ASC";
		TypedQuery<HoraAula> result = this.entityManager.createQuery(jpql, HoraAula.class);
		result.setParameter("idTurma", idTurma);

		return result.getResultList();

	}

	@Override
	public HoraAula consultarPorPK(HoraAulaPK pk) {

		try {
			String jpql = "SELECT ha FROM HoraAula ha WHERE  ha.id.aula.id.sala.id = :idSala AND ha.id.aula.id.disciplina.id = :idDisciplina AND ha.id.aula.id.professor.id = :idProfessor "
					+ "AND ha.id.dia = :dia AND ha.id.horaInicio = :horaInicio AND ha.id.horaFim = :horaFim";

			TypedQuery<HoraAula> result = this.entityManager.createQuery(jpql, HoraAula.class);
			//result.setParameter("idTurma", pk.getTurma().getId());
			result.setParameter("idSala", pk.getAula().getId().getSala().getId());
			result.setParameter("idDisciplina", pk.getAula().getId().getDisciplina().getId());
			result.setParameter("idProfessor", pk.getAula().getId().getProfessor().getId());
			result.setParameter("dia", pk.getDia());
			result.setParameter("horaInicio", pk.getHoraInicio());
			result.setParameter("horaFim", pk.getHoraFim());
			
			return result.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public void removerPorIdTurma(Long idTurma) {

		List<HoraAula> lista = this.listaHoraAulaPorIdTurma(idTurma);
		lista.forEach(value -> this.remover(value));
		lista.forEach(value -> this.daoAula.remover(value.getId().getAula()));

		/*
		 * for (HoraAula horaAula : lista) {
		 * 
		 * this.remover(horaAula);
		 * 
		 * }
		 */
		/*
		 * for (HoraAula horaAula : lista) {
		 * 
		 * this.daoAula.remover(horaAula.getId().getAula());
		 * 
		 * }
		 */
	}

	@Override
	public List<HoraAula> listarHoraAulaPorProfessor(Long idProfessor) {
		try {
			String jpql = "SELECT ha FROM HoraAula ha WHERE ha.id.aula.id.professor.id = :idProfessor";
			TypedQuery<HoraAula> result = this.entityManager.createQuery(jpql, HoraAula.class);
			result.setParameter("idProfessor", idProfessor);
			return result.getResultList();
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<HoraAula> listarHoraAulaPorProfessorTurma(Long idProfessor, Long idTurma) {
		try {
			String jpql = "SELECT ha FROM HoraAula ha WHERE ha.turma.id = :idTurma AND "
					+ "ha.id.aula.id.professor.id = :idProfessor";
			TypedQuery<HoraAula> result = this.entityManager.createQuery(jpql, HoraAula.class);
			result.setParameter("idProfessor", idProfessor);
			result.setParameter("idTurma", idTurma);
			return result.getResultList();
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<HoraAula> listarHoraAulaPorAluno(Long idAluno) {
		try {
			String jpql = "SELECT ha FROM HoraAula ha WHERE ha.id.turma IN "
					+ "(SELECT m.turma FROM Matricula m WHERE m.aluno.id = :idAluno)";
			TypedQuery<HoraAula> result = this.entityManager.createQuery(jpql, HoraAula.class);
			result.setParameter("idAluno", idAluno);
			return result.getResultList();
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@SuppressWarnings("deprecation")
	public List<Aula> listarAulaPorAlunoHoje(Long idAluno) {
		try {
			Date hoje = new Date();
			String jpql = "SELECT ha.id.aula FROM HoraAula ha WHERE ha.id.dia = :dia "
					+ "AND ha.id.turma IN (SELECT m.turma FROM Matricula m WHERE m.aluno.id = :idAluno)";
			TypedQuery<Aula> result = this.entityManager.createQuery(jpql, Aula.class);
			result.setParameter("idAluno", idAluno);
			
			if (hoje.getDay() == 0) {
				result.setParameter("dia", DiasSemana.SEGUNDA);
			}
			if (hoje.getDay() == 1) {
				result.setParameter("dia", DiasSemana.SEGUNDA);
			}
			if (hoje.getDay() == 2) {
				result.setParameter("dia", DiasSemana.TERCA);
			}
			if (hoje.getDay() == 3) {
				result.setParameter("dia", DiasSemana.QUARTA);
			}
			if (hoje.getDay() == 4) {
				result.setParameter("dia", DiasSemana.QUINTA);
			}
			if (hoje.getDay() == 5) {
				result.setParameter("dia", DiasSemana.SEXTA);
			}
			if (hoje.getDay() == 6) {
				result.setParameter("dia", DiasSemana.SABADO);
			}
			return result.getResultList();
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@SuppressWarnings("deprecation")
	public List<Aula> listarAulaPorTurmaHoje(Long idTurma) {
		
		try {
			Date hoje = new Date();
			String jpql = "SELECT ha.id.aula FROM HoraAula ha WHERE ha.id.dia = :dia "
					+ "AND ha.turma.id = :idTurma";
			TypedQuery<Aula> result = this.entityManager.createQuery(jpql, Aula.class);
			result.setParameter("idTurma", idTurma);
			
			if (hoje.getDay() == 0) {
				result.setParameter("dia", DiasSemana.SEGUNDA);
			}
			if (hoje.getDay() == 1) {
				result.setParameter("dia", DiasSemana.SEGUNDA);
			}
			if (hoje.getDay() == 2) {
				result.setParameter("dia", DiasSemana.TERCA);
			}
			if (hoje.getDay() == 3) {
				result.setParameter("dia", DiasSemana.QUARTA);
			}
			if (hoje.getDay() == 4) {
				result.setParameter("dia", DiasSemana.QUINTA);
			}
			if (hoje.getDay() == 5) {
				result.setParameter("dia", DiasSemana.SEXTA);
			}
			if (hoje.getDay() == 6) {
				result.setParameter("dia", DiasSemana.SABADO);
			}
			return result.getResultList();
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	@SuppressWarnings("deprecation")
	public List<HoraAula> listarHoraAulaPorAlunoHoje(Long idAluno) {
		try {
			Date hoje = new Date();
			String jpql = "SELECT ha FROM HoraAula ha WHERE ha.id.dia = :dia "
					+ "AND ha.turma IN (SELECT m.turma FROM Matricula m WHERE m.aluno.id = :idAluno)";
			TypedQuery<HoraAula> result = this.entityManager.createQuery(jpql, HoraAula.class);
			result.setParameter("idAluno", idAluno);
			
			if (hoje.getDay() == 0) {
				result.setParameter("dia", DiasSemana.SEGUNDA);
			}
			if (hoje.getDay() == 1) {
				result.setParameter("dia", DiasSemana.SEGUNDA);
			}
			if (hoje.getDay() == 2) {
				result.setParameter("dia", DiasSemana.TERCA);
			}
			if (hoje.getDay() == 3) {
				result.setParameter("dia", DiasSemana.QUARTA);
			}
			if (hoje.getDay() == 4) {
				result.setParameter("dia", DiasSemana.QUINTA);
			}
			if (hoje.getDay() == 5) {
				result.setParameter("dia", DiasSemana.SEXTA);
			}
			if (hoje.getDay() == 6) {
				result.setParameter("dia", DiasSemana.SABADO);
			}
			return result.getResultList();
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@SuppressWarnings("deprecation")
	public List<HoraAula> listarHoraAulaPorTurmaHoje(Long idTurma) {
		
		try {
			Date hoje = new Date();
			String jpql = "SELECT ha FROM HoraAula ha WHERE ha.id.dia = :dia "
					+ "AND ha.turma.id = :idTurma";
			TypedQuery<HoraAula> result = this.entityManager.createQuery(jpql, HoraAula.class);
			result.setParameter("idTurma", idTurma);
			
			if (hoje.getDay() == 0) {
				result.setParameter("dia", DiasSemana.SEGUNDA);
			}
			if (hoje.getDay() == 1) {
				result.setParameter("dia", DiasSemana.SEGUNDA);
			}
			if (hoje.getDay() == 2) {
				result.setParameter("dia", DiasSemana.TERCA);
			}
			if (hoje.getDay() == 3) {
				result.setParameter("dia", DiasSemana.QUARTA);
			}
			if (hoje.getDay() == 4) {
				result.setParameter("dia", DiasSemana.QUINTA);
			}
			if (hoje.getDay() == 5) {
				result.setParameter("dia", DiasSemana.SEXTA);
			}
			if (hoje.getDay() == 6) {
				result.setParameter("dia", DiasSemana.SABADO);
			}
			return result.getResultList();
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	@SuppressWarnings("deprecation")
	public List<HoraAula> listarHoraAulaPorProfessorHoje(Long idProfessor) {
		
		try {
			Date hoje = new Date();
			String jpql = "SELECT ha FROM HoraAula ha WHERE ha.id.dia = :dia "
					+ "AND ha.id.aula.id.professor.id = :idProfessor";
			TypedQuery<HoraAula> result = this.entityManager.createQuery(jpql, HoraAula.class);
			result.setParameter("idProfessor", idProfessor);
			
			if (hoje.getDay() == 0) {
				result.setParameter("dia", DiasSemana.SEGUNDA);
			}
			if (hoje.getDay() == 1) {
				result.setParameter("dia", DiasSemana.SEGUNDA);
			}
			if (hoje.getDay() == 2) {
				result.setParameter("dia", DiasSemana.TERCA);
			}
			if (hoje.getDay() == 3) {
				result.setParameter("dia", DiasSemana.QUARTA);
			}
			if (hoje.getDay() == 4) {
				result.setParameter("dia", DiasSemana.QUINTA);
			}
			if (hoje.getDay() == 5) {
				result.setParameter("dia", DiasSemana.SEXTA);
			}
			if (hoje.getDay() == 6) {
				result.setParameter("dia", DiasSemana.SABADO);
			}
			return result.getResultList();
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	@Override
	@SuppressWarnings("deprecation")
	public List<Aula> listarAulaPorProfessorHoje(Long idProfessor) {
		
		try {
			Date hoje = new Date();
			String jpql = "SELECT ha.id.aula FROM HoraAula ha WHERE ha.id.dia = :dia "
					+ "AND ha.id.aula.id.professor.id = :idProfessor";
			TypedQuery<Aula> result = this.entityManager.createQuery(jpql, Aula.class);
			result.setParameter("idProfessor", idProfessor);
			
			if (hoje.getDay() == 0) {
				result.setParameter("dia", DiasSemana.SEGUNDA);
			}
			if (hoje.getDay() == 1) {
				result.setParameter("dia", DiasSemana.SEGUNDA);
			}
			if (hoje.getDay() == 2) {
				result.setParameter("dia", DiasSemana.TERCA);
			}
			if (hoje.getDay() == 3) {
				result.setParameter("dia", DiasSemana.QUARTA);
			}
			if (hoje.getDay() == 4) {
				result.setParameter("dia", DiasSemana.QUINTA);
			}
			if (hoje.getDay() == 5) {
				result.setParameter("dia", DiasSemana.SEXTA);
			}
			if (hoje.getDay() == 6) {
				result.setParameter("dia", DiasSemana.SABADO);
			}
			return result.getResultList();
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}