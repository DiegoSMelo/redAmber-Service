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

import br.com.sistema.redAmber.basicas.Equipamento;
import br.com.sistema.redAmber.basicas.Sala;
import br.com.sistema.redAmber.basicas.http.EquipamentoHTTP;
import br.com.sistema.redAmber.exceptions.DAOException;
import br.com.sistema.redAmber.rn.RNEquipamento;
import br.com.sistema.redAmber.rn.RNSala;

@Path("/equipamentows")
public class EquipamentoWS {

	private RNEquipamento rnEquipamento;
	private RNSala rnSala;
	private Gson gson;
	
	public EquipamentoWS() {
		this.gson = new Gson();
		this.rnEquipamento = new RNEquipamento();
		this.rnSala = new RNSala();
	}
	
	@POST
	@Path("salvar")
	@Consumes("application/json")
	@Produces("text/plain")
	public String salvarEquipamento(String jsonEquipamento) {
		try {
			
			Equipamento equipamento = new Equipamento();
			EquipamentoHTTP equipamentoHTTP = gson.fromJson(jsonEquipamento, EquipamentoHTTP.class);
			
			equipamento.setId(equipamentoHTTP.getId());
			equipamento.setDescricao(equipamentoHTTP.getDescricao());
			equipamento.setTombo(equipamentoHTTP.getTombo());
			equipamento.setStatus(equipamentoHTTP.getStatus());
			
			Long id = null;
			try {
				id = equipamentoHTTP.getSala().getId();
			} catch (Exception e) {
				id = null;
			}
			if (id != null) {
				Sala sala = rnSala.buscarPorId(id);
				equipamento.setSala(sala);
			}
			
			this.rnEquipamento.salvar(equipamento);
			return "Equipamento salvo com sucesso.";
			
		} catch (JsonSyntaxException e) {
			return "Error";
		} catch (DAOException e) {
			return "Error";
		}
	}
	
	@GET
	@Path("buscar-por-id/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	public String buscarEquipamentoPorId(@PathParam("id") String id) {
		
		return this.gson.toJson(this.rnEquipamento.buscarPorId(Long.parseLong(id)));		
	}
	
	@GET
	@Path("buscar-por-descricao/{descricao}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	public String buscarEquipamentoPorDescricao(@PathParam("descricao") String descricao) {

		String retorno = "";
		
		try {
			retorno = this.gson.toJson(this.rnEquipamento.buscarPorDescricao(descricao));
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return retorno;
	}
	
	@GET
	@Path("buscar-por-tombo/{tombo}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	public String buscarEquipamentoPorTombo(@PathParam("tombo") String tombo) {
		
		String retorno = "";
		
		try {
			retorno = this.gson.toJson(this.rnEquipamento.buscarPorTombo(tombo));
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return retorno;
	}
	
	@GET
	@Path("listar")
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	public String listarEquipamentos() {
		
		List<Equipamento> equipamentos;
		equipamentos = rnEquipamento.buscarTodos();
		return this.gson.toJson(equipamentos);
	}
}