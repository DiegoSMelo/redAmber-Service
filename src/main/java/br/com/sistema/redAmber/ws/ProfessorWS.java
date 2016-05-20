package br.com.sistema.redAmber.ws;

import java.io.IOException;
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

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import br.com.sistema.redAmber.basicas.BuscaProfessor;
import br.com.sistema.redAmber.basicas.Disciplina;
import br.com.sistema.redAmber.basicas.Professor;
import br.com.sistema.redAmber.basicas.Usuario;
import br.com.sistema.redAmber.basicas.http.ProfessorHTTP;
import br.com.sistema.redAmber.exceptions.DAOException;
import br.com.sistema.redAmber.rn.RNProfessor;
import br.com.sistema.redAmber.util.Datas;

@Path("/professorws")
public class ProfessorWS {

	private RNProfessor rnProfessor;
	private Gson gson;

	public ProfessorWS() {
		this.rnProfessor = new RNProfessor();
		this.gson = new Gson();
	}

	@POST
	@Path("salvar")
	@Consumes("application/json")
	@Produces("text/plain")
	public String salvar(String jsonProfessor) {
		try {
			JsonFactory factory = new JsonFactory();
			JsonParser parser = factory.createJsonParser(jsonProfessor);

			// avança o stream até chegar no array
			while (parser.nextToken() != JsonToken.START_ARRAY) {
				parser.nextToken();
			}

			ObjectMapper objectMapper = new ObjectMapper();
			List<Disciplina> listaDisciplinas = objectMapper.readValue(parser, new TypeReference<List<Disciplina>>() {
			});

			/*
			 * Esse será salvo
			 */
			Professor professor = new Professor();

			/*
			 * Esse eu to recebendo
			 */
			ProfessorHTTP professorHTTP = this.gson.fromJson(jsonProfessor, ProfessorHTTP.class);

			Usuario usuario = new Usuario();
			usuario = professorHTTP.getUsuario();
			
			if (usuario != null) {
				professor.setUsuario(usuario);
			}
			
			Calendar dataNascimento = Datas
					.converterDateToCalendar(new Date(Long.parseLong(professorHTTP.getDataNascimento())));

			professor.setId(professorHTTP.getId());
			professor.setDataNascimento(dataNascimento);
			professor.setEmail(professorHTTP.getEmail());
			professor.setNome(professorHTTP.getNome());
			professor.setRg(professorHTTP.getRg());
			professor.setStatus(professorHTTP.getStatus());
			professor.setTelefone(professorHTTP.getTelefone());
			professor.setUsuario(professorHTTP.getUsuario());
			professor.setListDisciplinas(listaDisciplinas);

			this.rnProfessor.salvar(professor);
			return "Professor salvo com sucesso";
		} catch (JsonSyntaxException e) {
			return "Error";
		} catch (NumberFormatException e) {
			return "Error";
		} catch (DAOException e) {
			return "Error";
		} catch (JsonParseException e) {
			return "Error";
		} catch (IOException e) {
			return "Error";
		}
	}

	@GET
	@Path("listar")
	@Produces("application/json")
	public String listarProfessores() {
		List<Professor> lista = this.rnProfessor.listarTodosProfessores();
		return this.gson.toJson(lista);
	}

	@GET
	@Path("buscar-por-id/{id}")
	@Produces("application/json")
	public String buscarProfessorPorID(@PathParam("id") String id) {
		return this.gson.toJson(this.rnProfessor.buscarPorId(Long.parseLong(id)));
	}

	@GET
	@Path("buscar-por-rg/{rg}")
	@Produces("application/json")
	public String buscarProfessorPorRG(@PathParam("rg") String rg) {
		try {
			return this.gson.toJson(this.rnProfessor.buscarProfessorPorRG(rg));
		} catch (DAOException e) {
			return null;
		}
	}

	@GET
	@Path("listar-por-disciplina/{idDisciplina}")
	@Produces("application/json")
	public String listarProfessoresPorDisciplina(@PathParam("idDisciplina") Long idDisciplina) {
		List<Professor> lista = this.rnProfessor.listarProfessoresPorDisciplina(idDisciplina);
		return this.gson.toJson(lista);
	}
	
	@POST
	@Path("buscar-por-nome-rg")
	@Consumes("application/json")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public String buscarProfessoresPorNomeRG(String buscaProfessor) {
		BuscaProfessor consulta = new BuscaProfessor();
		consulta = this.gson.fromJson(buscaProfessor, BuscaProfessor.class);
		List<Professor> listaProfessores = new ArrayList<Professor>();
		try {
			listaProfessores = this.rnProfessor.buscarProfessoresPorNomeRG(consulta);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return this.gson.toJson(listaProfessores);
	}
	
	@GET
	@Path("login/{login}/{senha}")
	@Produces("application/json")
	public String buscarProfessorPorLoginSenha(@PathParam("login") String login, @PathParam("senha") String senha) {
		
		Professor professor = new Professor();
		try {
			professor = this.rnProfessor.buscarProfessorPorLoginSenha(login, senha);
		} catch (DAOException e) {
			e.printStackTrace();
			return null;
		}
		return this.gson.toJson(professor);
	}
}