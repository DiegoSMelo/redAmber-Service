package br.com.sistema.redAmber.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import br.com.sistema.redAmber.basicas.Funcionario;
import br.com.sistema.redAmber.basicas.Usuario;
import br.com.sistema.redAmber.basicas.enums.StatusUsuario;
import br.com.sistema.redAmber.basicas.enums.TipoFuncionario;
import br.com.sistema.redAmber.basicas.http.LoginHTTP;
import br.com.sistema.redAmber.exceptions.DAOException;
import br.com.sistema.redAmber.rn.RNFuncionario;
import br.com.sistema.redAmber.util.Criptografia;
import br.com.sistema.redAmber.util.Datas;


/**
 * Ainda estão aqui os métodos do funcionario
 * @author Diego Melo
 *
 */
@Path("/redamberws")
public class RedAmberWS {
	
	private RNFuncionario rnFuncionario;
	
	private Gson gson;
	
	public RedAmberWS() {
		this.gson = new Gson();
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
	
	
	
	
	
	
	
}
