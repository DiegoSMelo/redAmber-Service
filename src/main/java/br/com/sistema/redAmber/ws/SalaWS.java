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

import br.com.sistema.redAmber.basicas.Sala;
import br.com.sistema.redAmber.basicas.http.SalaHTTP;
import br.com.sistema.redAmber.exceptions.DAOException;
import br.com.sistema.redAmber.rn.RNSala;

@Path("/salaws")
public class SalaWS {

	private RNSala rnSala;
	private Gson gson;
	
	public SalaWS() {
		this.rnSala = new RNSala();
		this.gson = new Gson();
	}
	
	@POST
	@Path("salvar")
	@Consumes("application/json")
	@Produces("text/plain")
	public String salvarSala(String jsonSala) {
		
		try {
			Sala sala = new Sala();
			SalaHTTP salaHTTP = gson.fromJson(jsonSala, SalaHTTP.class);
			
			sala.setId(salaHTTP.getId());
			sala.setDescricao(salaHTTP.getDescricao());
			sala.setCapacidadeAlunos(salaHTTP.getCapacidadeAlunos());
			sala.setTipoSala(salaHTTP.getTipoSala());
			sala.setStatus(salaHTTP.getStatus());
			
			this.rnSala.salvar(sala);
			return "Sala salva com sucesso.";
		} catch (JsonSyntaxException e) {
			return "Error";
		}
	}
	
	@GET
	@Path("buscar-por-id/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	public String buscarSalaPorId(@PathParam("id") String id) {
		return this.gson.toJson(this.rnSala.buscarPorId(Long.parseLong(id)));
	}
	
	@GET
	@Path("buscar-por-descricao/{descricao}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	public String buscarSalaPorDescricao(@PathParam("descricao") String descricao) {

		String retorno = "";

		try {
			retorno = this.gson.toJson(this.rnSala.buscarPorDescricao(descricao));
		} catch (DAOException e) {
			e.printStackTrace();
			return "Error";
		}
		return retorno;
	}
	
	@GET
	@Path("listar")
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	public String listarSalas() {
		
		List<Sala> salas;
		salas = this.rnSala.buscarTodos();
		return this.gson.toJson(salas);
	}
}