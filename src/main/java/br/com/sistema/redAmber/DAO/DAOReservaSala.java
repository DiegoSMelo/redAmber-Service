package br.com.sistema.redAmber.DAO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.sistema.redAmber.DAO.generics.DAOGeneric;
import br.com.sistema.redAmber.basicas.BuscaReserva;
import br.com.sistema.redAmber.basicas.ReservaSala;
import br.com.sistema.redAmber.basicas.enums.StatusReserva;

public class DAOReservaSala extends DAOGeneric<ReservaSala> implements IDAOReservaSala {

	public DAOReservaSala(EntityManager em) {
		super(em);
	}
	
	@Override
	public List<ReservaSala> buscarReservasPendentes() {
		try {
			String jpql = "SELECT r FROM ReservaSala r WHERE r.status = :status";
			
			TypedQuery<ReservaSala> result = this.entityManager.
					createQuery(jpql, ReservaSala.class);
			result.setParameter("status", StatusReserva.PENDENTE);
			return result.getResultList();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public ReservaSala verificarReservasPorDataReservaHorario(Long idSala, 
			Calendar dataReserva, Long idHorario) {
		try {
			String jpql = "SELECT r FROM ReservaSala r WHERE r.sala.id = :idSala"
					+ " AND r.dataReserva = :dataReserva AND r.horarioReserva.id = :idHorario";
			TypedQuery<ReservaSala> result = this.entityManager.
					createQuery(jpql, ReservaSala.class);
			result.setParameter("idSala", idSala);
			result.setParameter("dataReserva", dataReserva);
			result.setParameter("idHorario", idHorario);
			return result.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@Override
	@SuppressWarnings("deprecation")
	public Integer consultarQuantidadeDeHoje() {
		
		List<ReservaSala> lista = new ArrayList<ReservaSala>();
		Integer quantidade = 0;
		Calendar dataReserva = Calendar.getInstance();
		Date hoje = new Date();
		dataReserva.setTime(hoje);
		int paramDia = hoje.getDay();
		int paramMes = hoje.getMonth();
		int paramAno = hoje.getYear();
		
		try {
			TypedQuery<ReservaSala> result = entityManager
					.createQuery("SELECT rs FROM ReservaSala rs WHERE rs.status = :status",
							ReservaSala.class);
			result.setParameter("status", StatusReserva.PENDENTE);
			lista = result.getResultList();
			
			for(ReservaSala reserva : lista) {
				if (reserva.getDataReserva().getTime().getDay() == paramDia && 
						reserva.getDataReserva().getTime().getMonth() == paramMes && 
						reserva.getDataReserva().getTime().getYear() == paramAno) {
					quantidade++;
				}
			}
			return quantidade;
		} catch (NoResultException e2) {
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public List<ReservaSala> buscarReservasPorProfessor(Long idProfessor) {
		try {
			String jpql = "SELECT rs FROM ReservaSala rs WHERE rs.professor.id = :idProfessor";
			TypedQuery<ReservaSala> result = entityManager
					.createQuery(jpql, ReservaSala.class);
			result.setParameter("idProfessor", idProfessor);
			return result.getResultList();
		} catch (NoResultException e2) {
			e2.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public List<ReservaSala> buscarReservasPorProfessorDataReserva(Long idProfessor, 
			Calendar dataReserva) {
		try {
			String jpql = "SELECT rs FROM ReservaSala rs WHERE rs.professor.id = :idProfessor AND "
					+ "rs.dataReserva = :dataReserva";
			TypedQuery<ReservaSala> result = entityManager
					.createQuery(jpql, ReservaSala.class);
			result.setParameter("idProfessor", idProfessor);
			result.setParameter("dataReserva", dataReserva);
			return result.getResultList();
		} catch (NoResultException e2) {
			e2.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	@SuppressWarnings("deprecation")
	public List<ReservaSala> listarReservasPorParametros(BuscaReserva consulta) {
		
		try {
			String sql = "SELECT rs FROM ReservaSala rs";
			
			List<ReservaSala> resultado = new ArrayList<ReservaSala>();
			List<ReservaSala> resposta = new ArrayList<ReservaSala>();
			
			if (consulta.getIdProfessor() != null && consulta.getStatus() == null) {
				sql += " WHERE rs.professor.id = :idProfessor";
			}
			if (consulta.getIdProfessor() == null && consulta.getStatus() != null) {
				sql += " WHERE rs.status = :status";
			}
			if (consulta.getIdProfessor() != null && consulta.getStatus() != null) {
				sql += " WHERE rs.professor.id = :idProfessor AND rs.status = :status";
			}
			
			TypedQuery<ReservaSala> query = entityManager.createQuery(sql, ReservaSala.class);
			
			if (consulta.getIdProfessor() != null && consulta.getStatus() == null) {
				query.setParameter("idProfessor", consulta.getIdProfessor());
			}
			if (consulta.getIdProfessor() == null && consulta.getStatus() != null) {
				query.setParameter("status", consulta.getStatus());
			}
			if (consulta.getIdProfessor() != null && consulta.getStatus() != null) {
				query.setParameter("idProfessor", consulta.getIdProfessor());
				query.setParameter("status", consulta.getStatus());
			}
			
			resultado = query.getResultList();
			
			if (consulta.getDataReserva() != null) {
				int dia = consulta.getDataReserva().getDate();
				int mes = consulta.getDataReserva().getMonth();
				int ano = consulta.getDataReserva().getYear();
				
				for (ReservaSala reserva : resultado) {
					if (reserva.getDataReserva().getTime().getDate() == dia && 
							reserva.getDataReserva().getTime().getMonth() == mes && 
							reserva.getDataReserva().getTime().getYear() == ano) {
						resposta.add(reserva);
					}
				}
				return resposta;
			}
			
			if (consulta.getDataReserva() == null && consulta.getDataRequisicao() != null) {
				int dia = consulta.getDataRequisicao().getDate();
				int mes = consulta.getDataRequisicao().getMonth();
				int ano = consulta.getDataRequisicao().getYear();
				
				for (ReservaSala reserva : resultado) {
					if (reserva.getDataRequisicao().getTime().getDate() == dia && 
							reserva.getDataRequisicao().getTime().getMonth() == mes && 
							reserva.getDataRequisicao().getTime().getYear() == ano) {
						resposta.add(reserva);
					}
				}
				return resposta;
			}
			
			if (consulta.getDataReserva() != null && consulta.getDataRequisicao() != null) {
				int diaRes = consulta.getDataReserva().getDate();
				int mesRes = consulta.getDataReserva().getMonth();
				int anoRes = consulta.getDataReserva().getYear();
				
				int diaReq = consulta.getDataRequisicao().getDate();
				int mesReq = consulta.getDataRequisicao().getMonth();
				int anoReq = consulta.getDataRequisicao().getYear();
				
				for (ReservaSala reserva : resultado) {
					if (reserva.getDataReserva().getTime().getDate() == diaRes && 
							reserva.getDataReserva().getTime().getMonth() == mesRes && 
							reserva.getDataReserva().getTime().getYear() == anoRes && 
							reserva.getDataRequisicao().getTime().getDate() == diaReq && 
							reserva.getDataRequisicao().getTime().getMonth() == mesReq && 
							reserva.getDataRequisicao().getTime().getYear() == anoReq) {
						resposta.add(reserva);
					}
				}
				return resposta;
			}
			return resultado;
		} catch (NoResultException e2) {
			e2.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}