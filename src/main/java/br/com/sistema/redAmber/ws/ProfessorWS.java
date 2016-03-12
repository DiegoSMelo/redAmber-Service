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

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import br.com.sistema.redAmber.basicas.Professor;
import br.com.sistema.redAmber.basicas.http.ProfessorHTTP;
import br.com.sistema.redAmber.exceptions.DAOException;
import br.com.sistema.redAmber.rn.RNProfessor;
import br.com.sistema.redAmber.util.Datas;


@Path("/professorws")
public class ProfessorWS {
	
	private RNProfessor rnProfessor;
	private Gson gson;
	
	public ProfessorWS(){
		this.rnProfessor = new RNProfessor();
		this.gson = new Gson();
	}
	
	@POST
	@Path("salvar")
	@Consumes("application/json")
	@Produces("text/plain")
	public String salvar(String jsonProfessor){
		try {
			
			/*
			 * Esse será salvo
			 */
			Professor professor = new Professor();
			
			/*
			 * Esse eu to recebendo
			 */
			ProfessorHTTP professorHTTP = this.gson.fromJson(jsonProfessor, ProfessorHTTP.class);
			
			Calendar dataNascimento = Datas.converterDateToCalendar(new Date(Long.parseLong(professorHTTP.getDataNascimento())));
			
			professor.setDataNascimento(dataNascimento);
			professor.setEmail(professorHTTP.getEmail());
			professor.setNome(professorHTTP.getNome());
			professor.setRg(professorHTTP.getRg());
			professor.setStatus(professorHTTP.getStatus());
			professor.setTelefone(professorHTTP.getTelefone());
			professor.setUsuario(professorHTTP.getUsuario());
			
			this.rnProfessor.salvar(professor);
			
			return "Professor salvo com sucesso";
			
		} catch (JsonSyntaxException e) {
			return "Error";
		} catch (NumberFormatException e) {
			return "Error";
		} catch (DAOException e) {
			return "Error";
		}
	}
	
	@GET
	@Path("listar")
	@Produces("application/json")
	public String listarProfessores(){
		
		List<Professor> lista = this.rnProfessor.listarTodosProfessores();
				
		return this.gson.toJson(lista);
	}
	
	
	@GET
	@Path("buscar-por-rg/{rg}")
	@Produces("application/json")
	public String buscarProfessorPorRG(@PathParam("rg") String rg){
		
		try {
			return this.gson.toJson(this.rnProfessor.buscarProfessorPorRG(rg));
		} catch (DAOException e) {
			return null;
		}
		
	}	
}
