package com.gabrielferreira.projeto.modelo.entidade;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@Table(name = "tab_professor")
@JsonTypeName("Professor") 
public class Professor extends Pessoa{
	
	private static final long serialVersionUID = 1L;
	
	private Double salario;
	
	public Professor() {}

	public Professor(Integer id, String nome, String sobrenome, String cpf, Sexo sexo,
			Curso curso,Escola escola,Double salario) {
		super(id, nome, sobrenome, cpf, sexo,curso,escola);
		this.salario = salario;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}
	
	

}
