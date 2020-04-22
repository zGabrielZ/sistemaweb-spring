package com.gabrielferreira.projeto.modelo.entidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.gabrielferreira.projeto.modelo.entidade.enums.Sexo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tab_pessoa")
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type") 
public abstract class Pessoa implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message = "Nome não pode ser vazio")
	@Column(name="nome",nullable = false,length = 80)
	private String nome;
	
	@NotEmpty(message = "Sobrenome não pode ser vazio")
	@Column(name="sobrenome",nullable = false,length = 80)
	private String sobrenome;
	
	@CPF(message = "Cpf inválido")
	@Column(name="cpf",nullable = false,length = 14)
	private String cpf;
	
	@Valid
	@NotNull(message = "Tem que selecionar o Sexo da pessoa")
	@Enumerated(EnumType.STRING)
	private Sexo sexo;
	
	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "endereco_id")
	private Endereco endereco;
	
	@Valid
	@NotNull(message = "Tem que selecionar o Curso da pessoa")
	@ManyToOne()
	@JoinColumn(name = "curso_id")
	private Curso curso;
	
	@OneToMany(mappedBy = "pessoa",cascade = CascadeType.ALL)
	private List<Telefone> telefones = new ArrayList<Telefone>();
	
	@OneToMany(mappedBy = "pessoa")
	private List<Itens> itens = new ArrayList<Itens>();
	
	
	public Pessoa() {}
	
	public Pessoa(Integer id, String nome, String sobrenome, String cpf, Sexo sexo,Curso curso) {
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.cpf = cpf;
		this.sexo = sexo;
		this.curso = curso;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}
