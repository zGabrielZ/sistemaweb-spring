package com.gabrielferreira.projeto.modelo.entidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@Entity
@Table(name = "tab_pessoa")
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type") 
public abstract class Pessoa implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message = "Nome não pode ser nulo")
	@NotEmpty(message = "Nome não pode ser vazio")
	private String nome;
	
	@NotNull(message = "Sobrenome não pode ser nulo")
	@NotEmpty(message = "Sobrenome não pode ser vazio")
	private String sobrenome;
	
	@NotNull(message = "Cpf não pode ser nulo")
	@NotEmpty(message = "Cpf não pode ser vazio")
	private String cpf;
	
	@Valid
	@NotNull(message = "Tem que selecionar o Sexo da pessoa")
	@ManyToOne()
	@JoinColumn(name = "sexo_id")
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
	
	@Valid
	@NotNull(message = "Tem que selecionar a Escola da pessoa")
	@ManyToOne()
	@JoinColumn(name = "escola_id")
	private Escola escola;
	
	@ManyToMany()
	@JoinTable(name = "tab_pessoa_disciplina",
	joinColumns = @JoinColumn(name = "disciplina_id"),
	inverseJoinColumns = @JoinColumn(name = "pessoa_id"))
	private List<Disciplina> disciplinas = new ArrayList<Disciplina>();
	
	public Pessoa() {}
	
	public Pessoa(Integer id, String nome, String sobrenome, String cpf, Sexo sexo,Curso curso,
			Escola escola) {
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.cpf = cpf;
		this.sexo = sexo;
		this.curso = curso;
		this.escola = escola;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	
	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	public Escola getEscola() {
		return escola;
	}

	public void setEscola(Escola escola) {
		this.escola = escola;
	}
	
	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
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
