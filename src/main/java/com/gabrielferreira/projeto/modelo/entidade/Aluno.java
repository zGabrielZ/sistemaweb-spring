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
@Table(name = "tab_aluno")
@JsonTypeName("aluno") 
@NoArgsConstructor
public class Aluno extends Pessoa {

	private static final long serialVersionUID = 1L;
	
	private String ra;
	
	private String email;
	
	private String senha;

	public Aluno(Long id, String nomeCompleto, String cpf, Sexo sexo, String ra, String email, String senha) {
		super(id, nomeCompleto, cpf, sexo);
		this.ra = ra;
		this.email = email;
		this.senha = senha;
	}
	
}
