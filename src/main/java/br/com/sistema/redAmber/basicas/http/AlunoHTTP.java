package br.com.sistema.redAmber.basicas.http;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import br.com.sistema.redAmber.basicas.Usuario;
import br.com.sistema.redAmber.basicas.enums.StatusUsuario;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown=true)
public class AlunoHTTP {
	
	private Long id;
	
	private String nome;
	
	private String rg;
	
	private String email;
	
	private String telefone;
	
	/*
	 * TIMESTAMP
	 */
	private String dataNascimento;
	
	private Usuario usuario;
	
	private StatusUsuario status;
	
	public AlunoHTTP(){}
	
	/**
	 * 
	 * @param id
	 * @param login
	 * @param senha
	 * @param nome
	 * @param rg
	 * @param email
	 * @param telefone
	 * @param dataNascimento
	 * @param status
	 */
	public AlunoHTTP(Long id, String nome, String rg, String email, String telefone, String dataNascimento, StatusUsuario status){
		setId(id);
		setNome(nome);
		setRg(rg);
		setEmail(email);
		setTelefone(telefone);
		setDataNascimento(dataNascimento);
		setStatus(status);
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public StatusUsuario getStatus() {
		return status;
	}
	public void setStatus(StatusUsuario status) {
		this.status = status;
	}
}