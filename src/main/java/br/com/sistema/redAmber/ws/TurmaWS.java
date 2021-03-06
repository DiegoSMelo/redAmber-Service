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

import br.com.sistema.redAmber.basicas.Curso;
import br.com.sistema.redAmber.basicas.Turma;
import br.com.sistema.redAmber.basicas.enums.TipoTurno;
import br.com.sistema.redAmber.basicas.http.CursoHTTP;
import br.com.sistema.redAmber.basicas.http.TurmaHTTP;
import br.com.sistema.redAmber.exceptions.DAOException;
import br.com.sistema.redAmber.rn.RNCurso;
import br.com.sistema.redAmber.rn.RNTurma;

@Path("/turmaws")
public class TurmaWS {

	private RNTurma rnTurma;
	private RNCurso rnCurso;
	private Gson gson;

	public TurmaWS() {
		this.rnTurma = new RNTurma();
		this.rnCurso = new RNCurso();
		this.gson = new Gson();
	}

	@POST
	@Path("salvar")
	@Consumes("application/json")
	@Produces("text/plain")
	public String salvarTurma(String jsonTurmaHTTP) {
		try {
			Turma turma = new Turma();
			Curso curso = new Curso();
			
			TurmaHTTP turmaHTTP = this.gson.fromJson(jsonTurmaHTTP, TurmaHTTP.class);
			CursoHTTP cursoHTTP = turmaHTTP.getCurso();
			curso = this.rnCurso.buscarCursoPorID(cursoHTTP.getId());
			
			turma.setId(turmaHTTP.getId());
			turma.setNome(turmaHTTP.getNome());
			turma.setCurso(curso);
			turma.setTurno(turmaHTTP.getTurno());
			turma.setStatus(turmaHTTP.getStatus());

//			for (int i = 0; i < TipoTurno.values().length; i++) {
//				if (TipoTurno.values()[i].toString().equalsIgnoreCase(turmaHTTP.getTurno().toString())) {
//					turma.setTurno(TipoTurno.values()[i]);
//				}
//			}
//			turma.setStatus(turmaHTTP.getStatus());
			this.rnTurma.salvar(turma);
			return "Turma salva com sucesso.";
		} catch (JsonSyntaxException e) {
			return null;
		} catch (DAOException e) {
			return null;
		}
	}

	@GET
	@Path("listar")
	@Produces("application/json")
	public String listarTurmas() {
		List<Turma> lista = this.rnTurma.listarTurmas();
		return this.gson.toJson(lista);
	}

	@GET
	@Path("buscar-por-id/{id}")
	@Produces("application/json")
	public String buscarTurmaPorId(@PathParam("id") String id) {
		return this.gson.toJson(this.rnTurma.buscarPorId(Long.parseLong(id)));
	}

	@GET
	@Path("buscar-por-curso-turno/{curso}/{turno}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public String buscarTurmasPorCurso(@PathParam("curso") String jsonCurso, 
			@PathParam("turno") String jsonTurno) {
		
		Curso curso = new Curso();
		TipoTurno turno = null;
		Long idCurso = null;
		
		if (jsonCurso == null || jsonCurso.trim().equalsIgnoreCase("null") || 
				jsonCurso.trim().equalsIgnoreCase("") || jsonCurso.isEmpty()) {
			idCurso = 0L;
		} else {
			idCurso = this.gson.fromJson(jsonCurso, Long.class);	
		}
		curso = rnCurso.buscarCursoPorID(idCurso);

		if (jsonTurno.equalsIgnoreCase("MANHA") || jsonTurno.equalsIgnoreCase("Manh�")) {
			turno = TipoTurno.MANHA;
		}
		if (jsonTurno.equalsIgnoreCase("TARDE") || jsonTurno.equalsIgnoreCase("Tarde")) {
			turno = TipoTurno.TARDE;
		}
		if (jsonTurno.equalsIgnoreCase("NOITE") || jsonTurno.equalsIgnoreCase("Noite")) {
			turno = TipoTurno.NOITE;
		}

		List<Turma> lista = rnTurma.buscarTurmasPorCursoTurno(curso, turno);
		return gson.toJson(lista);
	}
}