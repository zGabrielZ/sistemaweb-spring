package com.gabrielferreira.projeto.modelo.entidade;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "tab_endereco")
public class Endereco implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message = "Logradouro não pode ser vazio")
	@Column(name="logradouro",nullable = false,length = 150)
	private String logradouro;
	
	@NotEmpty(message = "Número da casa ou apartamento não pode ser vazio")
	@Column(name="numero",nullable = false,length = 80)
	private String numero;
	
	@NotEmpty(message = "Bairro não pode ser vazio")
	@Column(name="bairro",nullable = false,length = 150)
	private String bairro;
	
	@NotEmpty(message = "Cep não pode ser vazio")
	@Column(name="cep",nullable = false)
	private String cep;
	
	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cidade_id")
	private Cidade cidade;
	
	@OneToOne(mappedBy = "endereco")
	private Pessoa pessoa;
	
	public Endereco() {}

	public Endereco(Integer id, String logradouro, String numero, String bairro, String cep,
			Cidade cidade) {
		this.id = id;
		this.logradouro = logradouro;
		this.numero = numero;
		this.bairro = bairro;
		this.cep = cep;
		this.cidade = cidade;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	
	
	
}
