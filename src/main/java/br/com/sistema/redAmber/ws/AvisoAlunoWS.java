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

import br.com.sistema.redAmber.basicas.AvisoAluno;
import br.com.sistema.redAmber.basicas.Funcionario;
import br.com.sistema.redAmber.basicas.Turma;
import br.com.sistema.redAmber.basicas.http.AvisoAlunoHTTP;
import br.com.sistema.redAmber.basicas.http.FuncionarioHTTP;
import br.com.sistema.redAmber.basicas.http.TurmaHTTP;
import br.com.sistema.redAmber.rn.RNAvisoAluno;
import br.com.sistema.redAmber.rn.RNFuncionario;
import br.com.sistema.redAmber.rn.RNTurma;
import br.com.sistema.redAmber.util.Datas;

@Path("/avisoalunows")
public class AvisoAlunoWS {

	private RNAvisoAluno rnAvisoAluno;
	private RNFuncionario rnFuncionario;
	private RNTurma rnTurma;
	private Gson gson;
	
	public AvisoAlunoWS() {
		rnAvisoAluno = new RNAvisoAluno();
		rnFuncionario = new RNFuncionario();
		rnTurma = new RNTurma();
		gson = new Gson();
	}
	
	@POST
	@Path("salvar")
	@Consumes("application/json")
	@Produces("text/plain")
	public String salvarAvisoAluno(String jsonAvisoAluno) {
		
		AvisoAluno avisoAluno = new AvisoAluno();
		AvisoAlunoHTTP avisoAlunoHTTP = gson.
				fromJson(jsonAvisoAluno, AvisoAlunoHTTP.class);
		FuncionarioHTTP funcionarioHTTP = avisoAlunoHTTP.getFuncionario();
		Funcionario funcionario = new Funcionario();
		funcionario = rnFuncionario.buscarPorId(funcionarioHTTP.getId());
		TurmaHTTP turmaHTTP = avisoAlunoHTTP.getTurma();
		Turma turma = rnTurma.buscarPorId(turmaHTTP.getId());

		Calendar dataAviso = Calendar.getInstance();
		Date hoje = new Date();
		if (avisoAlunoHTTP.getId() == null) {
			dataAviso.setTime(hoje);
		} else {
			dataAviso = Datas.converterDateToCalendar(new Date(Long.parseLong(avisoAlunoHTTP.
					getDataAviso())));
		}
		
		avisoAluno.setId(avisoAlunoHTTP.getId());
		avisoAluno.setObservacao(avisoAlunoHTTP.getObservacao());
		avisoAluno.setFuncionario(funcionario);
		avisoAluno.setTurma(turma);
		avisoAluno.setDataAviso(dataAviso);
		
		this.rnAvisoAluno.salvar(avisoAluno);
		return "Aviso aos alunos efetuado com sucesso!";
	}
	
	@GET
	@Path("buscar-por-id/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	public String buscarAvisoAlunoPorId(@PathParam("id") String id) {
		
		AvisoAluno avisoAluno = this.rnAvisoAluno.buscarPorId(Long.parseLong(id));
		return this.gson.toJson(avisoAluno);
	}
	
	@GET
	@Path("listar")
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	public String listarAvisosAluno() {
		
		List<AvisoAluno> avisos = new ArrayList<AvisoAluno>();
		avisos = this.rnAvisoAluno.listarTodos();
		return this.gson.toJson(avisos);
	}
	
	@GET
	@Path("buscar-por-aluno/{idAluno}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	public String listarAvisosAlunoPorAluno(@PathParam("idAluno") String idAluno) {
		
		try {
			List<AvisoAluno> lista = this.rnAvisoAluno.listarAvisosAlunoPorAluno(Long.parseLong(idAluno));
			return this.gson.toJson(lista);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}