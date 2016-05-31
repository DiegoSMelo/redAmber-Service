package br.com.sistema.redAmber.basicas.http;

public class AvisoAlunoHTTP {

	private Long id;
	private FuncionarioHTTP funcionario;
	private String dataAviso;
	private String observacao;
	private TurmaHTTP turma;
	
	/*
	 * Getters and setters
	 */
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public FuncionarioHTTP getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(FuncionarioHTTP funcionario) {
		this.funcionario = funcionario;
	}
	public String getDataAviso() {
		return dataAviso;
	}
	public void setDataAviso(String dataAviso) {
		this.dataAviso = dataAviso;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public TurmaHTTP getTurma() {
		return turma;
	}
	public void setTurma(TurmaHTTP turma) {
		this.turma = turma;
	}
}