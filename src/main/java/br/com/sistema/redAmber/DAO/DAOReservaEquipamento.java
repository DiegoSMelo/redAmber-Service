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
import br.com.sistema.redAmber.basicas.ReservaEquipamento;
import br.com.sistema.redAmber.basicas.enums.StatusReserva;

public class DAOReservaEquipamento extends DAOGeneric<ReservaEquipamento> 
implements IDAOReservaEquipamento {

	public DAOReservaEquipamento(EntityManager em) {
		super(em);
	}
	
	@Override
	public List<ReservaEquipamento> buscarReservasPendentes() {
		try {
			String jpql = "SELECT r FROM ReservaEquipamento r WHERE r.status = :status";
			
			TypedQuery<ReservaEquipamento> result = this.entityManager.
					createQuery(jpql, ReservaEquipamento.class);
			result.setParameter("status", StatusReserva.PENDENTE);
			return result.getResultList();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public ReservaEquipamento verificarReservasPorDataReservaHorario(Long idEquipamento, 
			Calendar dataReserva, Long idHorario) {		
		try {
			String jpql = "SELECT r FROM ReservaEquipamento r WHERE r.equip.id = :idEquipamento"
					+ " AND r.dataReserva = :dataReserva AND r.horarioReserva.id = :idHorario";
			TypedQuery<ReservaEquipamento> result = this.entityManager.
					createQuery(jpql, ReservaEquipamento.class);
			result.setParameter("idEquipamento", idEquipamento);
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
		
		List<ReservaEquipamento> lista = new ArrayList<ReservaEquipamento>();
		Integer quantidade = 0;
		Calendar dataReserva = Calendar.getInstance();
		Date hoje = new Date();
		dataReserva.setTime(hoje);
		int paramDia = hoje.getDay();
		int paramMes = hoje.getMonth();
		int paramAno = hoje.getYear();
		
		try {
			TypedQuery<ReservaEquipamento> result = entityManager
					.createQuery("SELECT re FROM ReservaEquipamento re WHERE"
							+ " re.status = :status", 
							ReservaEquipamento.class);
			result.setParameter("status", StatusReserva.PENDENTE);
			lista = result.getResultList();
			
			for(ReservaEquipamento reserva : lista) {
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
	public List<ReservaEquipamento> buscarReservasPorProfessor(Long idProfessor) {
		
		try {
			String jpql = "SELECT re FROM ReservaEquipamento re WHERE re.professor.id = :idProfessor";
			TypedQuery<ReservaEquipamento> result = entityManager
					.createQuery(jpql, ReservaEquipamento.class);
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
	public List<ReservaEquipamento> buscarReservasPorProfessorDataReserva(Long idProfessor, 
			Calendar dataReserva) {
		
		try {
			String jpql = "SELECT re FROM ReservaEquipamento re WHERE re.professor.id = :idProfessor "
					+ "AND re.dataReserva = :dataReserva";
			TypedQuery<ReservaEquipamento> result = entityManager
					.createQuery(jpql, ReservaEquipamento.class);
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
	public List<ReservaEquipamento> listarReservasPorParametros(BuscaReserva consulta) {
		
		try {
			String sql = "SELECT re FROM ReservaEquipamento re";
			
			List<ReservaEquipamento> resultado = new ArrayList<ReservaEquipamento>();
			List<ReservaEquipamento> resposta = new ArrayList<ReservaEquipamento>();
			
			if (consulta.getIdProfessor() != null && consulta.getStatus() == null) {
				sql += " WHERE re.professor.id = :idProfessor";
			}
			if (consulta.getIdProfessor() == null && consulta.getStatus() != null) {
				sql += " WHERE re.status = :status";
			}
			if (consulta.getIdProfessor() != null && consulta.getStatus() != null) {
				sql += " WHERE re.professor.id = :idProfessor AND re.status = :status";
			}
			
			TypedQuery<ReservaEquipamento> query = entityManager.createQuery(sql, ReservaEquipamento.class);
			
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
			
			if (consulta.getDataReserva() != null && consulta.getDataRequisicao() == null) {
				int dia = consulta.getDataReserva().getDate();
				int mes = consulta.getDataReserva().getMonth();
				int ano = consulta.getDataReserva().getYear();
				
				for (ReservaEquipamento reserva : resultado) {
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
				
				for (ReservaEquipamento reserva : resultado) {
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
				
				for (ReservaEquipamento reserva : resultado) {
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