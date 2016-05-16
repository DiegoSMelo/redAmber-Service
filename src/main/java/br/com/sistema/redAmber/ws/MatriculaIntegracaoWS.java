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

import br.com.sistema.redAmber.basicas.Curso;
import br.com.sistema.redAmber.basicas.Grade;
import br.com.sistema.redAmber.basicas.Turma;
import br.com.sistema.redAmber.basicas.enums.StatusMatricula;
import br.com.sistema.redAmber.basicas.http.MatriculaIntegracaoHTTP;
import br.com.sistema.redAmber.basicas.http.TurmaHTTP;
import br.com.sistema.redAmber.basicas.integracao.MatriculaIntegracao;
import br.com.sistema.redAmber.rn.RNCurso;
import br.com.sistema.redAmber.rn.RNGrade;
import br.com.sistema.redAmber.rn.RNMatriculaIntegracao;
import br.com.sistema.redAmber.util.Datas;

@Path("/matriculaintegracaows")
public class MatriculaIntegracaoWS {

	private RNMatriculaIntegracao rnMatriculaIntegracao;
	private RNGrade rnGrade;
	private Gson gson;
	private RNCurso rnCurso;
	
	public MatriculaIntegracaoWS() {
		this.rnMatriculaIntegracao = new RNMatriculaIntegracao();
		this.rnGrade = new RNGrade();
		this.rnCurso = new RNCurso();
		this.gson = new Gson();
	}
	
	/*
	 * Método que gera um código de matrícula de 12 dígitos para o aluno baseado no seguinte critério:
	 * Código de matrícula = ANO DA MATRÍCULA + ENTRADA + CÓDIGO DO CURSO + ID DO ALUNO COM 6 DÍGITOS
	 */
	public String gerarCodigoMatricula(MatriculaIntegracaoHTTP matricula) {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    
		String paramAno = Integer.toString(cal.get(Calendar.YEAR));
		String paramAluno = String.format("%06d",matricula.getIdAluno());
		
		String codigoMatricula = paramAno + matricula.getEntrada() + 
				matricula.getGrade().getCurso().getId() + paramAluno;
		
	    return  codigoMatricula;
	}
	
	/*
	 * Método que atualiza o código de uma matrícula previamente efetuada
	 */
	public String modificarCodigoMatricula(MatriculaIntegracaoHTTP matricula) {
	    
		String paramAno = matricula.getCodigoMatricula().substring(0, 4);
		String paramAluno = String.format("%06d",matricula.getIdAluno());
		
		String codigoMatricula = paramAno + matricula.getEntrada() + 
				matricula.getGrade().getCurso().getId() + paramAluno;
		
	    return  codigoMatricula;
	}
	
	@POST
	@Path("salvar")
	@Consumes("application/json")
	@Produces("text/plain")
	public String salvarMatricula(String jsonMatricula) {
		MatriculaIntegracao matricula = new MatriculaIntegracao();
		Turma turma = new Turma();
		MatriculaIntegracaoHTTP matriculaHTTP = this.gson.fromJson(jsonMatricula, 
				MatriculaIntegracaoHTTP.class);
		TurmaHTTP turmaHTTP = matriculaHTTP.getTurma();
		
		turma.setId(turmaHTTP.getId());
		turma.setNome(turmaHTTP.getNome());
		turma.setTurno(turmaHTTP.getTurno());
		turma.setStatus(turmaHTTP.getStatus());
		
		Curso curso = rnCurso.buscarCursoPorID(turmaHTTP.getIdCurso());
		
		turma.setCurso(curso);
		
		if (matriculaHTTP.getId() == null) {
			Date date = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			matricula.setDataMatricula(cal);
			matricula.setStatus(StatusMatricula.ATIVA);
			matricula.setCodigoMatricula(gerarCodigoMatricula(matriculaHTTP));
		} else {
			matricula.setId(matriculaHTTP.getId());
			
			Calendar dataMatricula = Datas.converterDateToCalendar(new Date(Long.parseLong(matriculaHTTP.
					getDataMatricula())));
			
			matricula.setDataMatricula(dataMatricula);
			matricula.setStatus(matriculaHTTP.getStatus());
			matricula.setCodigoMatricula(modificarCodigoMatricula(matriculaHTTP));
		}
		matricula.setIdAluno(matriculaHTTP.getIdAluno());
		matricula.setEntrada(matriculaHTTP.getEntrada());
		matricula.setTurma(turma);
		
		Long id = null;
		try {
			id = matriculaHTTP.getGrade().getId();
		} catch (Exception e) {
			id = null;
		}
		if (id != null) {
			Grade grade = rnGrade.buscarGradePorId(id);
			matricula.setGrade(grade);
		}
		
		this.rnMatriculaIntegracao.salvar(matricula);
		
		return "Matrícula salva com sucesso.";
	}
	
	@GET
	@Path("aluno/{idAluno}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public String listarMatriculasPorIdAluno(@PathParam("idAluno") String idAluno) {
		List<MatriculaIntegracao> lista =  this.rnMatriculaIntegracao.
				listarMatriculasPorIdAluno(Long.parseLong(idAluno));
		return this.gson.toJson(lista);
	}
	
	@GET
	@Path("buscar-por-codigo/{codigo}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public String buscarMatriculaPorCodigoMatricula(@PathParam("codigo") String codigo) {
		String retorno = null;
		try {
			MatriculaIntegracao matricula = this.rnMatriculaIntegracao.
					buscarMatriculaPorCodigoMatricula(codigo);
			retorno = this.gson.toJson(matricula);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}
	
	@GET
	@Path("buscar-por-id/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public String buscarMatriculaPorId(@PathParam("id") String id) {
		String retorno = null;
		try {
			MatriculaIntegracao matricula = this.rnMatriculaIntegracao.buscarPorId(Long.parseLong(id));
			retorno = this.gson.toJson(matricula);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}
	
	@GET
	@Path("buscar-por-aluno-curso/{idAluno}/{idCurso}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public String buscarMatriculaAtivaPorCurso(@PathParam("idAluno") String idAluno, 
			@PathParam("idCurso") String idCurso) {
		String retorno = null;
		try {
			MatriculaIntegracao matricula = this.rnMatriculaIntegracao.
					buscarMatriculaAtivaPorCurso(Long.parseLong(idAluno), 
					Long.parseLong(idCurso));
			retorno = this.gson.toJson(matricula);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}
}