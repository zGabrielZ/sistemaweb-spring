package com.gabrielferreira.projeto.modelo.entidade;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.gabrielferreira.projeto.modelo.entidade.enums.Sexo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tab_professor")
@JsonTypeName("Professor") 
public class Professor extends Pessoa{
	
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "Salário não pode ser vazio")
	@Column(name="salario",nullable = false)
	private String salario;
	
	public Professor() {}

	public Professor(Integer id, String nome, String sobrenome, String cpf, Sexo sexo,
			Curso curso,String salario) {
		super(id, nome, sobrenome, cpf, sexo,curso);
		this.salario = salario;
	}

}
