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

import br.com.sistema.redAmber.basicas.Curso;
import br.com.sistema.redAmber.basicas.http.CursoHTTP;
import br.com.sistema.redAmber.exceptions.DAOException;
import br.com.sistema.redAmber.rn.RNCurso;
import br.com.sistema.redAmber.util.Datas;

@Path("/cursows")
public class CursoWS {
	
	private RNCurso rnCurso;
	private Gson gson;
	
	public CursoWS() {
		this.gson = new Gson();
		this.rnCurso = new RNCurso();
		
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
			
			Calendar dataInicioCurso = Datas.converterDateToCalendar(new Date(Long.parseLong(cursoHTTP.getDataInicio())));
			Calendar dataFimCurso = Datas.converterDateToCalendar(new Date(Long.parseLong(cursoHTTP.getDataFim())));
			
			curso.setNome(cursoHTTP.getNome());
			curso.setDataInicio(dataInicioCurso);
			curso.setDataFim(dataFimCurso);
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
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public String buscarCursoPorNomeESigla(@PathParam("nomeCurso")String nomeCurso,@PathParam("siglaCurso") String siglaCurso) {
		
		try {
			Curso curso = this.rnCurso.buscarCursoPorNomeESigla(nomeCurso, siglaCurso);

			return this.gson.toJson(curso);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			return "Error";
		}

	}
	
}
