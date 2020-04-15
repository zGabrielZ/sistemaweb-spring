package com.gabrielferreira.projeto.modelo.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.gabrielferreira.projeto.modelo.entidade.enums.Sexo;

@Entity
@Table(name = "tab_aluno")
@JsonTypeName("Aluno") 
public class Aluno extends Pessoa {

	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "Ra não pode ser vazio")
	@Column(name="ra",nullable = false,length = 10)
	private String ra;
	
	public Aluno() {}
	
	public Aluno(Integer id, String nome, String sobrenome, String cpf, Sexo sexo,
		Curso curso,Integer numeroDamatricula, String ra) {
		super(id, nome, sobrenome, cpf, sexo,curso);
		this.ra = ra;
	}

	public String getRa() {
		return ra;
	}

	public void setRa(String ra) {
		this.ra = ra;
	}
	
	
	
	
	
	
}
