package br.com.sistema.redAmber.basicas.http;

public class BuscaAvisoAlunoHTTP {

	private Long idFuncionario;
	private Long dataAviso;
	private Long idTurma;
	
	/**
	 * Getters and setters
	 */
	public Long getIdFuncionario() {
		return idFuncionario;
	}
	public void setIdFuncionario(Long idFuncionario) {
		this.idFuncionario = idFuncionario;
	}
	public Long getDataAviso() {
		return dataAviso;
	}
	public void setDataAviso(Long dataAviso) {
		this.dataAviso = dataAviso;
	}
	public Long getIdTurma() {
		return idTurma;
	}
	public void setIdTurma(Long idTurma) {
		this.idTurma = idTurma;
	}
}