package com.gabrielferreira.projeto.modelo.entidade;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.gabrielferreira.projeto.modelo.entidade.enums.Sexo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tab_professor")
@JsonTypeName("professor") 
@NoArgsConstructor
public class Professor extends Pessoa{
	
	private static final long serialVersionUID = 1L;
	
	private Double salario;
	
	private String email;
	
	private String senha;

	public Professor(Long id, String nomeCompleto, String cpf, Sexo sexo, Double salario, String email, String senha) {
		super(id, nomeCompleto, cpf, sexo);
		this.salario = salario;
		this.email = email;
		this.senha = senha;
	}


	

}
