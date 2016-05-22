package br.com.sistema.redAmber.basicas.http;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import br.com.sistema.redAmber.basicas.Usuario;
import br.com.sistema.redAmber.basicas.enums.StatusUsuario;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown=true)
public class FuncionarioHTTP {

	private Long id;
	private String nome;
	private String rg;
	private String email;
	private String telefone;
	private String dataNascimento;
	private Usuario usuario;
	private StatusUsuario status;
	private String tipoFuncionario;
	
	public FuncionarioHTTP() {}
	
	public FuncionarioHTTP(Long id, String nome, String rg, String email, String telefone, 
			String dataNascimento, Usuario usuario, StatusUsuario status, String tipoFuncionario) {
		this.id = id;
		this.nome = nome;
		this.rg = rg;
		this.email = email;
		this.telefone = telefone;
		this.dataNascimento = dataNascimento;
		this.usuario = usuario;
		this.status = status;
		this.tipoFuncionario = tipoFuncionario;
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

	public String getTipoFuncionario() {
		return tipoFuncionario;
	}

	public void setTipoFuncionario(String tipoFuncionario) {
		this.tipoFuncionario = tipoFuncionario;
	}
}