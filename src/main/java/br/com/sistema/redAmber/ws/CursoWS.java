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

import br.com.sistema.redAmber.basicas.Curso;
import br.com.sistema.redAmber.basicas.Grade;
import br.com.sistema.redAmber.basicas.Grade_Disciplina;
import br.com.sistema.redAmber.basicas.http.CursoHTTP;
import br.com.sistema.redAmber.basicas.http.GradeHTTP;
import br.com.sistema.redAmber.exceptions.DAOException;
import br.com.sistema.redAmber.rn.RNCurso;
import br.com.sistema.redAmber.rn.RNGrade;
import br.com.sistema.redAmber.rn.RNGrade_Disciplina;

@Path("/cursows")
public class CursoWS {
	
	private RNCurso rnCurso;
	private RNGrade rnGrade;
	private RNGrade_Disciplina rnGrade_Disciplina;
	private Gson gson;
	
	public CursoWS() {
		
		this.gson = new Gson();
		this.rnCurso = new RNCurso();
		this.rnGrade = new RNGrade();
		this.rnGrade_Disciplina = new RNGrade_Disciplina();
		
	}
	

	@POST
	@Path("salvar")
	@Consumes("application/json")
	@Produces("text/plain")
	public String salvarCurso(String jsonCurso){
		
		try {
			
			CursoHTTP cursoHTTP = new CursoHTTP();
			
			cursoHTTP = this.gson.fromJson(jsonCurso, CursoHTTP.class);
			
			Curso curso = new Curso();
			
			
			curso.setId(cursoHTTP.getId());
			curso.setNome(cursoHTTP.getNome());
			curso.setTipoCurso(cursoHTTP.getTipoCurso());
			curso.setSigla(cursoHTTP.getSigla());
			curso.setCargaHorariaTotal(cursoHTTP.getCargaHorariaTotal());
			curso.setStatus(cursoHTTP.getStatus());
			
			
			this.rnCurso.salvar(curso);
			return "Curso salvo com sucesso";
			
		} catch (DAOException e) {
			return "Error";
		}
		
	}
	
	@GET
	@Path("listar")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public String listarCursos() {
		
		List<Curso> listaCursos = this.rnCurso.listarTodosOsCursos();

		return this.gson.toJson(listaCursos);

	}
	
	@GET
	@Path("buscarPorNomeSigla/{nomeCurso}/{siglaCurso}")
	@Produces("application/json")
	public String buscarCursoPorNomeESigla(@PathParam("nomeCurso")String nomeCurso,@PathParam("siglaCurso") String siglaCurso) {
		
		try {
			Curso curso = this.rnCurso.buscarCursoPorNomeESigla(nomeCurso, siglaCurso);

			return this.gson.toJson(curso);
		} catch (DAOException e) {
			return null;
		}

	}
	
	@GET
	@Path("buscar-por-id/{id}")
	@Produces("application/json")
	public String buscarCursoPorId(@PathParam("id") String id) {

		Curso curso = this.rnCurso.buscarCursoPorID(Long.parseLong(id));

		return this.gson.toJson(curso);

	}
	
//----------------------------------------------GRADE-------------------------------------------------------------
	
	@POST
	@Path("salvarGrade")
	@Consumes("application/json")
	@Produces("text/plain")
	public String salvarGrade(String jsonGrade) {

		GradeHTTP gradeHTTP = new GradeHTTP();

		gradeHTTP = this.gson.fromJson(jsonGrade, GradeHTTP.class);

		Grade grade = new Grade();

		grade.setId(gradeHTTP.getId());
		grade.setCurso(gradeHTTP.getCurso());
		grade.setTitulo(gradeHTTP.getTitulo());
		grade.setStatus(gradeHTTP.getStatus());

		this.rnGrade.salvar(grade);
		return "Grade salva com sucesso!";

	}
	
	@GET
	@Path("listar/grades/por-curso/{id_curso}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public String listarGradesPorCurso(@PathParam("id_curso") String id_curso){
		
		return this.gson.toJson(this.rnGrade.listarGradesPorCurso(Long.parseLong(id_curso)));
		
	}
	
	
	
	@POST
	@Path("addGrade-disciplina")
	@Consumes("application/json")
	@Produces("text/plain")
	public String salvarGrade_Disciplina(String jsonGrade_Disciplina) {

		Grade_Disciplina gd = this.gson.fromJson(jsonGrade_Disciplina, Grade_Disciplina.class);
		
		this.rnGrade_Disciplina.adicionar(gd);
		
		return "A disciplina foi adicionada a sua grade!";

	}
	
	@POST
	@Path("removeGrade-disciplina")
	@Consumes("application/json")
	@Produces("text/plain")
	public String removerGrade_DisciplinaPorGrade(String json_grade) {
		
		GradeHTTP grade = this.gson.fromJson(json_grade, GradeHTTP.class);
		
		this.rnGrade_Disciplina.removerPorGrade(grade.getId());
		
		return "A disciplina foi removida da sua grade!";

	}
	
	@GET
	@Path("listar/grade-disciplina/por-grade/{id_grade}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public String listarGrade_DisciplinaPorGrade(@PathParam("id_grade") String id_grade){
		
		return this.gson.toJson(this.rnGrade_Disciplina.buscarPorIdGrade(Long.parseLong(id_grade)));
		
	}
	
	@GET
	@Path("listar/grade-disciplina/por-id/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public String listarGrade_DisciplinaPorId(@PathParam("id") String id){
		
		return this.gson.toJson(this.rnGrade_Disciplina.buscarPorId(Long.parseLong(id)));
		
	}
}
