package com.gabrielferreira.projeto.modelo.entidade;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@Table(name = "tab_professor")
@JsonTypeName("Professor") 
public class Professor extends Pessoa {

	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "Disciplina não pode ser nulo")
	@NotEmpty(message = "Disciplina não pode ser vazio")
	private String disciplina;
	
	@NotNull(message = "Salário nao pode ser vazio")
	private Double salario;
	
	public Professor() {}

	public Professor(Integer id, String nome,String sobrenome,String cpf,Contato contato ,String disciplina, Double salario) {
		super(id, nome,sobrenome ,cpf,contato);
		this.disciplina = disciplina;
		this.salario = salario;
	}

	public String getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	
	
	

}
