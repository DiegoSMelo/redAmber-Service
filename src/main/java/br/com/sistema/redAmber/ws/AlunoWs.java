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
import com.google.gson.JsonSyntaxException;

import br.com.sistema.redAmber.basicas.Aluno;
import br.com.sistema.redAmber.basicas.BuscaAluno;
import br.com.sistema.redAmber.basicas.Usuario;
import br.com.sistema.redAmber.basicas.http.AlunoHTTP;
import br.com.sistema.redAmber.basicas.http.LoginHTTP;
import br.com.sistema.redAmber.exceptions.DAOException;
import br.com.sistema.redAmber.rn.RNAluno;
import br.com.sistema.redAmber.util.Datas;

@Path("/alunows")
public class AlunoWs {

	private RNAluno rnAluno;
	private Gson gson;

	public AlunoWs() {
		this.gson = new Gson();
		this.rnAluno = new RNAluno();

		/*
		 * try { this.rnAluno.inserirAlunoAdmin(); } catch (DAOException e) {
		 * 
		 * e.printStackTrace();
		 * 
		 * }
		 */
	}

	@GET
	@Path("login/{login}/{senha}")
	@Produces("application/json")
	public String buscarAlunoPorLoginSenha(@PathParam("login") String login, @PathParam("senha") String senha) {
		try {
			LoginHTTP loginHTTP = new LoginHTTP(login, senha);
			Aluno aluno = this.rnAluno.buscarAlunoPorLoginSenha(loginHTTP.getLogin(), loginHTTP.getSenha());
			return this.gson.toJson(aluno);
		} catch (JsonSyntaxException e) {
			return "Error";
		} catch (DAOException e) {
			return "Error";
		}
	}

	@POST
	@Path("login")
	@Consumes("application/json")
	@Produces("application/json")
	public String buscarAlunoPorLoginSenha(String jsonLogin) {
		try {
			LoginHTTP loginHTTP = this.gson.fromJson(jsonLogin, LoginHTTP.class);
			Aluno aluno = this.rnAluno.buscarAlunoPorLoginSenha(loginHTTP.getLogin(), loginHTTP.getSenha());
			return this.gson.toJson(aluno);
		} catch (JsonSyntaxException e) {
			return "Error";
		} catch (DAOException e) {
			return "Error";
		}
	}

	@POST
	@Path("salvar")
	@Consumes("application/json")
	@Produces("text/plain")
	public String salvarAluno(String jsonAluno) {
		try {
			Aluno aluno = new Aluno();
			AlunoHTTP alunoHTTP = this.gson.fromJson(jsonAluno, AlunoHTTP.class);
			Usuario usuario = new Usuario();
			usuario = alunoHTTP.getUsuario();
			
			if (usuario != null) {
				aluno.setUsuario(usuario);
			}
			
			Calendar dataNascimento = Datas
					.converterDateToCalendar(new Date(Long.parseLong(alunoHTTP.getDataNascimento())));

			aluno.setId(alunoHTTP.getId());
			aluno.setDataNascimento(dataNascimento);
			aluno.setEmail(alunoHTTP.getEmail());
			aluno.setNome(alunoHTTP.getNome());
			aluno.setRg(alunoHTTP.getRg());
			aluno.setStatus(alunoHTTP.getStatus());
			aluno.setTelefone(alunoHTTP.getTelefone());

			this.rnAluno.salvar(aluno);
			return "Aluno salvo com sucesso";
		} catch (DAOException e) {
			return "Error";
		}
	}

	@GET
	@Path("rg/{rg}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public String buscaAlunoPorRG(@PathParam("rg") String rg) {
		try {
			return this.gson.toJson(this.rnAluno.buscarAlunoPorRG(rg));
		} catch (DAOException e) {
			return null;
		}
	}

	@GET
	@Path("id/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public String buscaAlunoPorId(@PathParam("id") String id) {
		return this.gson.toJson(this.rnAluno.buscarPorId(Long.parseLong(id)));
	}

	@GET
	@Path("listar")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public String listaAlunos() {
		List<Aluno> listaAlunos = this.rnAluno.listarTodosAlunos();
		return this.gson.toJson(listaAlunos);
	}
	
	@POST
	@Path("buscar-por-nome-rg")
	@Consumes("application/json")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public String buscarAlunoPorNomeRG(String buscaAluno) {
		BuscaAluno consulta = new BuscaAluno();
		consulta = this.gson.fromJson(buscaAluno, BuscaAluno.class);
		List<Aluno> listaAlunos = new ArrayList<Aluno>();
		try {
			listaAlunos = this.rnAluno.buscarAlunosPorNomeRG(consulta);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return this.gson.toJson(listaAlunos);
	}
}