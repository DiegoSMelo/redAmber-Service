package br.com.sistema.redAmber.ws;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import br.com.sistema.redAmber.basicas.AvisoProfessor;
import br.com.sistema.redAmber.basicas.BuscaAvisoProfessor;
import br.com.sistema.redAmber.basicas.Professor;
import br.com.sistema.redAmber.basicas.enums.StatusAvisoProfessor;
import br.com.sistema.redAmber.basicas.enums.TipoAvisoProfessor;
import br.com.sistema.redAmber.basicas.http.AvisoProfessorHTTP;
import br.com.sistema.redAmber.basicas.http.BuscaAvisoProfessorHTTP;
import br.com.sistema.redAmber.basicas.http.ProfessorHTTP;
import br.com.sistema.redAmber.exceptions.DAOException;
import br.com.sistema.redAmber.rn.RNAvisoProfessor;
import br.com.sistema.redAmber.rn.RNProfessor;
import br.com.sistema.redAmber.util.Datas;

@Path("/avisoprofessorws")
public class AvisoProfessorWS {

	private RNAvisoProfessor rnAvisoProfessor;
	private RNProfessor rnProfessor;
	private Gson gson;
	
	public AvisoProfessorWS() {
		rnAvisoProfessor = new RNAvisoProfessor();
		rnProfessor = new RNProfessor();
		gson = new Gson();
	}
	
	@POST
	@Path("salvar")
	@Consumes("application/json")
	@Produces("text/plain")
	public String salvarAvisoProfessor(String jsonAvisoProfessor) {
		
		AvisoProfessor avisoProfessor = new AvisoProfessor();
		AvisoProfessorHTTP avisoProfessorHTTP = gson.
				fromJson(jsonAvisoProfessor, AvisoProfessorHTTP.class);
		ProfessorHTTP professorHTTP = avisoProfessorHTTP.getProfessor();
		Professor professor = new Professor();
		professor = rnProfessor.buscarPorId(professorHTTP.getId());

		Calendar dataAviso = Calendar.getInstance();
		Date hoje = new Date();
		if (avisoProfessorHTTP.getId() == null) {
			dataAviso.setTime(hoje);
			avisoProfessor.setStatusAvisoProfessor(StatusAvisoProfessor.ENVIADO);
		} else {
			dataAviso = Datas.converterDateToCalendar(new Date(Long.parseLong(avisoProfessorHTTP.
					getDataAviso())));
			avisoProfessor.setStatusAvisoProfessor(avisoProfessorHTTP.getStatusAvisoProfessor());
		}
		
		avisoProfessor.setId(avisoProfessorHTTP.getId());
		avisoProfessor.setObservacao(avisoProfessorHTTP.getObservacao());
		avisoProfessor.setTipoAvisoProfessor(avisoProfessorHTTP.getTipoAvisoProfessor());		
		avisoProfessor.setProfessor(professor);
		avisoProfessor.setDataAviso(dataAviso);
		
		this.rnAvisoProfessor.salvar(avisoProfessor);
		return "Aviso à secretaria efetuado com sucesso!";
	}
	
	@GET
	@Path("buscar-por-id/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	public String buscarAvisoProfessorPorId(@PathParam("id") String id) {
		
		AvisoProfessor avisoProfessor = this.rnAvisoProfessor.buscarPorId(Long.parseLong(id));
		return this.gson.toJson(avisoProfessor);
	}
	
	@GET
	@Path("listar")
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	public String listarAvisosProfessor() {
		
		List<AvisoProfessor> avisos;
		avisos = this.rnAvisoProfessor.listarTodos();
		return this.gson.toJson(avisos);
	}
	
	@GET
	@Path("buscar-por-data/{dataAviso}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	public String consultarPorData(@PathParam("dataAviso") String dataAviso) {
		
		List<AvisoProfessor> avisos = new ArrayList<AvisoProfessor>();
		try {
			Calendar data = Datas.converterDateToCalendar(new Date(Long.parseLong(dataAviso)));
			avisos = this.rnAvisoProfessor.consultarPorData(data);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return this.gson.toJson(avisos);
	}
	
	@GET
	@Path("listar-pendentes")
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	public String consultarPendentes() {
		List<AvisoProfessor> avisos = new ArrayList<AvisoProfessor>();
		try {
			avisos = this.rnAvisoProfessor.consultarPendentes();
		} catch(DAOException e) {
			e.printStackTrace();
		}
		return this.gson.toJson(avisos);
	}
	
	@GET
	@Path("buscar-por-professor-data/{idProfessor}/{dataAviso}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	public String consultarPorProfessorData(@PathParam("idProfessor") String idProfessor,
			@PathParam("dataAviso") String dataAviso) {
		List<AvisoProfessor> avisos = new ArrayList<AvisoProfessor>();
		try {
			Calendar data = Datas.converterDateToCalendar(new Date(Long.parseLong(dataAviso)));
			Long id = Long.parseLong(idProfessor);
			avisos = this.rnAvisoProfessor.consultarPorProfessorData(id, data);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (DAOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.gson.toJson(avisos);
	}
	
	@GET
	@Path("quantidade-do-dia")
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	public String consultarQuantidadeDeHoje() {
		
		Integer retorno;
		retorno = this.rnAvisoProfessor.consultarQuantidadeDeHoje();
		return this.gson.toJson(retorno);
	}
	
	@GET
	@Path("salvar-get/{idProfessor}/{tipoAviso}/{observacao}")
	@Produces("text/plain")
	public String salvarAvisoProfessorGet(@PathParam("idProfessor") String idProfessor,
			@PathParam("tipoAviso") String tipoAviso, @PathParam("observacao") String observacao) {
		
		try {
			AvisoProfessor aviso = new AvisoProfessor();
			Professor professor = new Professor();
			int tipo = Integer.parseInt(tipoAviso);
			Calendar dataAviso = Calendar.getInstance();
			dataAviso.setTime(new Date());
			
			professor = this.rnProfessor.buscarPorId(Long.parseLong(idProfessor));
			
			aviso.setProfessor(professor);
			aviso.setDataAviso(dataAviso);
			aviso.setStatusAvisoProfessor(StatusAvisoProfessor.ENVIADO);
			aviso.setObservacao(observacao);
			
			if (tipo == 0) {
				aviso.setTipoAvisoProfessor(TipoAvisoProfessor.ATRASO);
			}
			if (tipo == 1) {
				aviso.setTipoAvisoProfessor(TipoAvisoProfessor.AUSENCIA);
			}
			
			this.rnAvisoProfessor.salvar(aviso);
			return "sucesso!";
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return "falha!";
		} catch (Exception e) {
			e.printStackTrace();
			return "falha!";
		}
	}
	
	@POST
	@Path("buscar-por-parametros")
	@Consumes("application/json")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public String listarAvisosProfessorPorParametros(String pesquisa) {
		BuscaAvisoProfessor consulta = new BuscaAvisoProfessor();
		BuscaAvisoProfessorHTTP consultaHTTP = this.gson.fromJson(pesquisa, BuscaAvisoProfessorHTTP.class);
		
		if (consultaHTTP.getIdProfessor() != null) {
			consulta.setIdProfessor(consultaHTTP.getIdProfessor());
		}
		if (consultaHTTP.getStatus() != null) {
			consulta.setStatus(consultaHTTP.getStatus());
		}
		if (consultaHTTP.getDataAviso() != null) {
			Date data = new Date(consultaHTTP.getDataAviso());
			consulta.setDataAviso(data);
		}
		
		List<AvisoProfessor> listaAvisos = new ArrayList<AvisoProfessor>();
		try {
			listaAvisos = this.rnAvisoProfessor.listarAvisosProfessorPorParametros(consulta);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.gson.toJson(listaAvisos);
	}
}