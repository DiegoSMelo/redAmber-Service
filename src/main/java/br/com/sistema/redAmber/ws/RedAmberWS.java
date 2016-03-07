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
import com.google.gson.JsonSyntaxException;

import br.com.sistema.redAmber.basicas.Aluno;
import br.com.sistema.redAmber.basicas.Usuario;
import br.com.sistema.redAmber.basicas.enums.StatusUsuario;
import br.com.sistema.redAmber.basicas.http.AlunoHTTP;
import br.com.sistema.redAmber.basicas.http.LoginHTTP;
import br.com.sistema.redAmber.exceptions.DAOException;
import br.com.sistema.redAmber.rn.RNAluno;
import br.com.sistema.redAmber.util.Criptografia;
import br.com.sistema.redAmber.util.Datas;

@Path("/redamberws")
public class RedAmberWS {
	
	private RNAluno rnAluno;
	private Gson gson;
	
	public RedAmberWS() {
		this.gson = new Gson();
		this.rnAluno = new RNAluno();
		
		try {
			
			if (this.rnAluno.buscarAlunoPorLoginSenha("admin", "admin") == null) {
				Aluno a = new Aluno();
				a.setDataNascimento(Datas.converterDateToCalendar(Datas.criarData(06, 3, 2016)));
				a.setEmail("admin@redamber.com.br");
				
				Usuario usuario = new Usuario();
				usuario.setLogin("admin");
				usuario.setSenha(Criptografia.criptografarSenhas("admin"));
				a.setUsuario(usuario);
				
				a.setNome("Administrador");
				a.setRg("9999999");
				a.setTelefone("08199999999");
				a.setStatus(StatusUsuario.ATIVO);
				RNAluno rna = new RNAluno();
				rna.salvar(a);
			}
			
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	
	@GET
	@Path("login/{login}/{senha}")
	@Produces("application/json")
	public String buscarPorLoginSenha(@PathParam("login") String login, @PathParam("senha") String senha){
		
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
	public String buscarPorLoginSenha(String jsonLogin){
		
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
	@Path("aluno/salvar")
	@Consumes("application/json")
	@Produces("text/plain")
	public String salvarAluno(String jsonAluno){
		try {
			Aluno aluno = new Aluno();
			
			AlunoHTTP alunoHTTP = this.gson.fromJson(jsonAluno, AlunoHTTP.class);
			
			
			Calendar dataNascimento = Datas.converterDateToCalendar(new Date(Long.parseLong(alunoHTTP.getDataNascimento())));
			
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
	@Path("aluno/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public String buscaAlunoPorId(@PathParam("id") String id){
		
		return this.gson.toJson(this.rnAluno.buscarPorId(Long.parseLong(id)));
	}
	
	@GET
	@Path("aluno/listar")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public String listaAlunos() {
		
		List<Aluno> listaAlunos = this.rnAluno.listarTodosAlunos();

		return this.gson.toJson(listaAlunos);

	}
	
	
}
