package br.com.sistema.redAmber.basicas;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.sistema.redAmber.basicas.enums.StatusUsuario;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Usuario implements Serializable{
	


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(unique=true, nullable=false)
	private String login;
	
	@Column(nullable=false)
	private String senha;
	
	@Column(nullable=false)
	private String nome;
	
	@Column(unique=true, nullable=false)
	private String rg;
	
	@Column(unique=true, nullable=false)
	private String email;
	
	@Column(nullable=false)
	private String telefone;
	
	@Column(nullable=false)
	@Temporal(TemporalType.DATE)
	private Calendar dataNascimento;
	
	@Enumerated
	private StatusUsuario status;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
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
	public Calendar getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public StatusUsuario getStatus() {
		return status;
	}
	public void setStatus(StatusUsuario status) {
		this.status = status;
	}
	
}
