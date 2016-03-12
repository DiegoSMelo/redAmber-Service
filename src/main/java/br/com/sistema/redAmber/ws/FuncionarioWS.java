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

import br.com.sistema.redAmber.basicas.Funcionario;
import br.com.sistema.redAmber.basicas.enums.TipoFuncionario;
import br.com.sistema.redAmber.basicas.http.FuncionarioHTTP;
import br.com.sistema.redAmber.exceptions.DAOException;
import br.com.sistema.redAmber.rn.RNFuncionario;
import br.com.sistema.redAmber.util.Datas;

@Path("funcionariows")
public class FuncionarioWS {

	private RNFuncionario rnFuncionario;
	private Gson gson;
	
	public FuncionarioWS() {
		this.gson = new Gson();
		this.rnFuncionario = new RNFuncionario();
	}
	
	@POST
	@Path("salvar")
	@Consumes("application/json")
	@Produces("text/plain")
	public String salvarFuncionario(String jsonFuncionario){
		try {
			Funcionario funcionario = new Funcionario();
			
			FuncionarioHTTP funcionarioHTTP = this.gson.fromJson(jsonFuncionario, FuncionarioHTTP.class);
			
			Calendar dataNascimento = Datas.converterDateToCalendar(new Date(Long.parseLong(funcionarioHTTP.getDataNascimento())));
			
			funcionario.setDataNascimento(dataNascimento);
			funcionario.setEmail(funcionarioHTTP.getEmail());
			funcionario.setNome(funcionarioHTTP.getNome());
			funcionario.setRg(funcionarioHTTP.getRg());
			funcionario.setStatus(funcionarioHTTP.getStatus());
			funcionario.setTelefone(funcionarioHTTP.getTelefone());
			
			if (funcionarioHTTP.getTipoFuncionario().equals("C")) {
				funcionario.setTipoFuncionario(TipoFuncionario.C);
			}else{
				if (funcionarioHTTP.getTipoFuncionario().equals("S")) {
					funcionario.setTipoFuncionario(TipoFuncionario.S);
				}
			}
			
			this.rnFuncionario.salvar(funcionario);
			return "Funcionário salvo com sucesso";
		} catch (DAOException e) {
			return "Error";
		}
	}
	
	@GET
	@Path("buscar-por-rg/{rg}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public String buscaFuncionarioPorRG(@PathParam("rg") String rg){
		
		try {
			return this.gson.toJson(this.rnFuncionario.buscarFuncionarioPorRG(rg));
		} catch (DAOException e) {
			return null;
		}
		
	}
	
	@GET
	@Path("funcionario/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public String buscaFuncionarioPorId(@PathParam("id") String id){
		
		return this.gson.toJson(this.rnFuncionario.buscarPorId(Long.parseLong(id)));
	}
	
	@GET
	@Path("listar")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public String listaFuncionarios() {
		
		List<Funcionario> listaFuncionarios = this.rnFuncionario.listarTodosFuncionarios();

		return this.gson.toJson(listaFuncionarios);
	}
}