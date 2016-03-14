package br.com.sistema.redAmber.ws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
 
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import br.com.sistema.redAmber.basicas.Disciplina;
import br.com.sistema.redAmber.basicas.http.DisciplinaHTTP;
import br.com.sistema.redAmber.exceptions.DAOException;
import br.com.sistema.redAmber.rn.RNDisciplina;


@Path("/disciplinaws")
public class DisciplinaWS {
	
	private RNDisciplina rnDisciplina;
	private Gson gson;
	
	public DisciplinaWS(){
		
		this.gson = new Gson();
		this.rnDisciplina = new RNDisciplina();
		
	}
	
	@GET
	@Path("titulo/{titulo}")
	@Produces("application/json")
	public String buscarDisciplinaPorTitulo(@PathParam("titulo") String titulo){
		
		try {
			
			Disciplina disciplina = this.rnDisciplina.buscarDisciplinaPorTitulo(titulo);
			
			return this.gson.toJson(disciplina);
			
		} catch (JsonSyntaxException e) {
			
			return "Error";
			
		} catch (DAOException e) {
			
			return "Error";
			
		}
		
	}
	
	
	@GET
	@Path("listar")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public String listaDisciplina() {
		
		List<Disciplina> listaDisciplina = this.rnDisciplina.listarTodosDiscipina();
		return this.gson.toJson(listaDisciplina);

	}
	

	@POST
	@Path("salvar")
	@Consumes("application/json")
	@Produces("text/plain")
	public String salvarDisciplina(String jsonDisciplina){
		try {
			
			Disciplina disciplina = new Disciplina();
			
			DisciplinaHTTP disciplinaHTTP = this.gson.fromJson(jsonDisciplina, DisciplinaHTTP.class);
			
			disciplina.setTitulo(disciplinaHTTP.getTitulo());
			disciplina.setDescricao(disciplinaHTTP.getDescricao());
			disciplina.setStatus(disciplinaHTTP.getStatus());

			this.rnDisciplina.salvar(disciplina);
			
			return "Disciplina inserida com sucesso";
			
		} catch (DAOException e) {
			
			return "Error";
			
		}
	}

}
