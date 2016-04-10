package br.com.sistema.redAmber.ws;

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

import br.com.sistema.redAmber.basicas.Grade;
import br.com.sistema.redAmber.basicas.Matricula;
import br.com.sistema.redAmber.basicas.http.MatriculaHTTP;
import br.com.sistema.redAmber.rn.RNGrade;
import br.com.sistema.redAmber.rn.RNMatricula;
import br.com.sistema.redAmber.util.Datas;

@Path("/matriculaws")
public class MatriculaWS {
	
	private RNMatricula rnMatricula;
	private RNGrade rnGrade;
	private Gson gson;
	
	public MatriculaWS() {
		
		this.rnMatricula = new RNMatricula();
		this.rnGrade = new RNGrade();
		this.gson = new Gson();
	}

	@POST
	@Path("salvar")
	@Consumes("application/json")
	@Produces("text/plain")
	public String salvarMatricula(String jsonMatricula){
	
		Matricula matricula = new Matricula();
		
		MatriculaHTTP matriculaHTTP = this.gson.fromJson(jsonMatricula, Matricula.class);
		
		Calendar dataMatricula = Datas.converterDateToCalendar(new Date(Long.parseLong(matriculaHTTP.getDataMatricula())));
		
		matricula.setId(matriculaHTTP.getId());
		matricula.setCodigoMatricula(matriculaHTTP.getCodigoMatricula());
		matricula.setDataMatricula(dataMatricula);
		matricula.setAluno(matriculaHTTP.getAluno());
		matricula.setStatus(matriculaHTTP.getStatus());
		
		Long id = null;
		try {
			id = matriculaHTTP.getGrade().getId();
		} catch (Exception e) {
			id = null;
		}
		if (id != null) {
			Grade grade = rnGrade.buscarGradePorId(id);
			matricula.setGrade(grade);
		}
		
		this.rnMatricula.salvar(matricula);
		
		return "Matrícula salva com sucesso.";
	}
	
	@GET
	@Path("aluno/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public String listarMatriculasPorIdAluno(@PathParam("id") String id){
		
		List<Matricula> lista =  this.rnMatricula.listarMatriculasPorIdAluno(Long.parseLong(id));
		return this.gson.toJson(lista);
	}
	
}
