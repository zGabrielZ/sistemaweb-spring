package com.gabrielferreira.projeto.modelo.entidade;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@Table(name = "tab_professor")
@JsonTypeName("Professor") 
public class Professor extends Pessoa{
	
	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "Salário não pode ser nulo")
	@NotEmpty(message = "Salário não pode ser vazio")
	private String salario;
	
	public Professor() {}

	public Professor(Integer id, String nome, String sobrenome, String cpf, Sexo sexo,
			Curso curso,Escola escola,String salario) {
		super(id, nome, sobrenome, cpf, sexo,curso,escola);
		this.salario = salario;
	}

	public String getSalario() {
		return salario;
	}

	public void setSalario(String salario) {
		this.salario = salario;
	}

}
