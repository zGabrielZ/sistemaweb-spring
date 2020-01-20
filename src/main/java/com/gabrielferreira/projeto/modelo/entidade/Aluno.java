package com.gabrielferreira.projeto.modelo.entidade;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@Table(name = "tab_aluno")
@JsonTypeName("Aluno") 
public class Aluno extends Pessoa {
	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "Ra não pode ser nulo")
	@NotEmpty(message = "Ra não pode ser vazio")
	private String ra;
	
	@NotNull(message = "Número da matricula nao pode ser vazio")
	private Integer numeroDaMatricula;
	
	@NotNull(message = "Curso não pode ser nulo")
	@NotEmpty(message = "Curso não pode ser vazio")
	private String curso;
	
	public Aluno() {}
	
	public Aluno(Integer id, String nome,String sobrenome,String cpf,Contato contato ,String ra, Integer numeroDaMatricula, String curso) {
		super(id, nome,sobrenome,cpf,contato);
		this.ra = ra;
		this.numeroDaMatricula = numeroDaMatricula;
		this.curso = curso;
	}

	public String getRa() {
		return ra;
	}

	public void setRa(String ra) {
		this.ra = ra;
	}

	public Integer getNumeroDaMatricula() {
		return numeroDaMatricula;
	}

	public void setNumeroDaMatricula(Integer numeroDaMatricula) {
		this.numeroDaMatricula = numeroDaMatricula;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}
	
	
	
	
}
