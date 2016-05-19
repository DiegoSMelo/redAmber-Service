package br.com.sistema.redAmber.ws;

import java.util.ArrayList;
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

import br.com.sistema.redAmber.basicas.Curso;
import br.com.sistema.redAmber.basicas.Disciplina;
import br.com.sistema.redAmber.basicas.http.CursoHTTP;
import br.com.sistema.redAmber.basicas.http.DisciplinaHTTP;
import br.com.sistema.redAmber.exceptions.DAOException;
import br.com.sistema.redAmber.rn.RNCurso;
import br.com.sistema.redAmber.rn.RNDisciplina;

@Path("/disciplinaws")
public class DisciplinaWS {

	private RNDisciplina rnDisciplina;
	private RNCurso rnCurso;
	private Gson gson;

	public DisciplinaWS() {
		this.rnDisciplina = new RNDisciplina();
		this.gson = new Gson();
		this.rnCurso = new RNCurso();
	}

	@GET
	@Path("id/{id}")
	@Produces("application/json")
	public String buscarDisciplinaPorId(@PathParam("id") String id) {
		try {
			Disciplina disciplina = this.rnDisciplina.buscarPorId(Long.parseLong(id));
			return this.gson.toJson(disciplina);
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
			return "Error";
		}
	}

	@GET
	@Path("titulo/{titulo}")
	@Produces("application/json")
	public String buscarDisciplinaPorTitulo(@PathParam("titulo") String titulo) {
		try {
			Disciplina disciplina = this.rnDisciplina.buscarDisciplinaPorTitulo(titulo);
			return this.gson.toJson(disciplina);
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
			return "Error";
		} catch (DAOException e) {
			e.printStackTrace();
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
	public String salvarDisciplina(String jsonDisciplina) {
		try {
			Disciplina disciplina = new Disciplina();
			Curso curso = new Curso();

			DisciplinaHTTP disciplinaHTTP = this.gson.fromJson(jsonDisciplina, DisciplinaHTTP.class);
			CursoHTTP cursoHTTP = disciplinaHTTP.getCurso();
			curso = this.rnCurso.buscarCursoPorID(cursoHTTP.getId());
			
			disciplina.setId(disciplinaHTTP.getId());
			disciplina.setTitulo(disciplinaHTTP.getTitulo());
			disciplina.setDescricao(disciplinaHTTP.getDescricao());
			disciplina.setConteudoProgramatico(disciplinaHTTP.getConteudoProgramatico());
			disciplina.setCurso(curso);
			disciplina.setStatus(disciplinaHTTP.getStatus());

			this.rnDisciplina.salvar(disciplina);
			return "Disciplina inserida com sucesso";
		} catch (DAOException e) {
			e.printStackTrace();
			return "Error";
		}
	}
	
	@GET
	@Path("buscar-por-titulo-curso/{titulo}/{idCurso}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public String buscarDisciplinaPorTituloCurso(@PathParam("titulo") String titulo, 
			@PathParam("idCurso") String idCurso) {
		try {
			Disciplina disciplina = this.rnDisciplina
					.buscarDisciplinaPorTituloCurso(titulo, Long.parseLong(idCurso));
			return this.gson.toJson(disciplina);
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
			return "Error";
		} catch (DAOException e) {
			e.printStackTrace();
			return "Error";
		}
	}
	
	@GET
	@Path("buscar-por-curso/{idCurso}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public String buscarDisciplinasPorCurso(@PathParam("idCurso") String idCurso) {
		try {
			List<Disciplina> lista = this.rnDisciplina.buscarDisciplinasPorCurso(Long.parseLong(idCurso));
			return this.gson.toJson(lista);
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
			return "Error";
		} catch (DAOException e) {
			e.printStackTrace();
			return "Error";
		}
	}
	
	@GET
	@Path("listar-ativas")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public String listarDisciplinasAtivas() {
		List<Disciplina> lista = new ArrayList<Disciplina>();
		try {
			lista = this.rnDisciplina.buscarDisciplinasAtivas();
		} catch (DAOException e) {
			e.printStackTrace();
			return null;
		}
		return this.gson.toJson(lista);
	}
}