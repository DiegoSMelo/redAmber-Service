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
import br.com.sistema.redAmber.basicas.Funcionario;
import br.com.sistema.redAmber.basicas.Usuario;
import br.com.sistema.redAmber.basicas.enums.StatusUsuario;
import br.com.sistema.redAmber.basicas.enums.TipoFuncionario;
import br.com.sistema.redAmber.basicas.http.AlunoHTTP;
import br.com.sistema.redAmber.basicas.http.LoginHTTP;
import br.com.sistema.redAmber.exceptions.DAOException;
import br.com.sistema.redAmber.rn.RNAluno;
import br.com.sistema.redAmber.rn.RNFuncionario;
import br.com.sistema.redAmber.util.Criptografia;
import br.com.sistema.redAmber.util.Datas;

@Path("/redamberws")
public class RedAmberWS {
	
	private RNAluno rnAluno;
	private RNFuncionario rnFuncionario;
	private Gson gson;
	
	public RedAmberWS() {
		this.gson = new Gson();
		this.rnAluno = new RNAluno();
		this.rnFuncionario = new RNFuncionario();
		
		try {
			if (this.rnFuncionario.buscarFuncionarioPorLoginSenha("funcionario-admin", "funcionario-admin") == null) {
				Funcionario f = new Funcionario();
				f.setDataNascimento(Datas.converterDateToCalendar(Datas.criarData(06, 3, 2016)));
				f.setEmail("funcionario-admin@redamber.com.br");
				
				f.setNome("Funcionário Administrador");
				f.setRg("8888888");
				f.setTelefone("081888888888");
				f.setTipoFuncionario(TipoFuncionario.C);
				f.setStatus(StatusUsuario.ATIVO);
				
				rnFuncionario.salvar(f);
				
				
				Funcionario funcr = rnFuncionario.buscarFuncionarioPorRG("8888888");
				Usuario usuario = new Usuario();
				usuario.setLogin("funcionario-admin");
				usuario.setSenha(Criptografia.criptografarSenhas("funcionario-admin"));
				usuario.setId(funcr.getId());
				funcr.setUsuario(usuario);
				
				rnFuncionario.salvar(funcr);
			}
			
			if (this.rnAluno.buscarAlunoPorLoginSenha("aluno-admin", "aluno-admin") == null) {
				Aluno a = new Aluno();
				a.setDataNascimento(Datas.converterDateToCalendar(Datas.criarData(06, 3, 2016)));
				a.setEmail("aluno-admin@redamber.com.br");
				
				
				
				a.setNome("Aluno Administrador");
				a.setRg("9999999");
				a.setTelefone("08199999999");
				a.setStatus(StatusUsuario.ATIVO);
				rnAluno.salvar(a);
				
				Aluno alunor = rnAluno.buscarAlunoPorRG("9999999");
				Usuario usuario = new Usuario();
				usuario.setLogin("aluno-admin");
				usuario.setSenha(Criptografia.criptografarSenhas("aluno-admin"));
				usuario.setId(alunor.getId());
				alunor.setUsuario(usuario);
				
				rnAluno.salvar(alunor);
			}
			
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	@GET
	@Path("funcionario-login/{login}/{senha}")
	@Produces("application/json")
	public String buscarFuncionarioPorLoginSenha(@PathParam("login") String login, @PathParam("senha") String senha){
		
		try {
			
			LoginHTTP loginHTTP = new LoginHTTP(login, senha);
			Funcionario funcionario = this.rnFuncionario.buscarFuncionarioPorLoginSenha(loginHTTP.getLogin(), loginHTTP.getSenha());
			
			return this.gson.toJson(funcionario);
			
		} catch (JsonSyntaxException e) {
			
			return "Error";
			
		} catch (DAOException e) {
			
			return "Error";
			
		}
		
	}
	
	
	@GET
	@Path("aluno-login/{login}/{senha}")
	@Produces("application/json")
	public String buscarAlunoPorLoginSenha(@PathParam("login") String login, @PathParam("senha") String senha){
		
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
	@Path("login/funcionario")
	@Consumes("application/json")
	@Produces("application/json")
	public String buscarFuncionarioPorLoginSenha(String jsonLogin){
		
		try {
			
			LoginHTTP loginHTTP = this.gson.fromJson(jsonLogin, LoginHTTP.class);
			Funcionario funcionario = this.rnFuncionario.buscarFuncionarioPorLoginSenha(loginHTTP.getLogin(), loginHTTP.getSenha());
			
			return this.gson.toJson(funcionario);
						
		} catch (JsonSyntaxException e) {
			
			return "Error";
			
		} catch (DAOException e) {
			
			return "Error";
			
		}
		
	}
	
	@POST
	@Path("login/aluno")
	@Consumes("application/json")
	@Produces("application/json")
	public String buscarAlunoPorLoginSenha(String jsonLogin){
		
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
	
	@GET
	@Path("funcionario/por-login/{login}")
	@Produces("application/json")
	public String buscarFuncionarioPorLogin(@PathParam("login") String login) {
		try {

			LoginHTTP loginHTTP = new LoginHTTP();
			loginHTTP.setLogin(login);
			
			Funcionario funcionario = this.rnFuncionario.buscarFuncionarioPorLogin(loginHTTP.getLogin());

			return this.gson.toJson(funcionario);

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
	@Path("aluno/rg/{rg}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public String buscaAlunoPorRG(@PathParam("rg") String rg){
		
		try {
			return this.gson.toJson(this.rnAluno.buscarAlunoPorRG(rg));
		} catch (DAOException e) {
			return null;
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
