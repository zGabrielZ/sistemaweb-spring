package com.gabrielferreira.projeto.modelo.entidade;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@Table(name = "tab_aluno")
@JsonTypeName("Aluno") 
public class Aluno extends Pessoa {

	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "Número da matricula não pode ser nulo")
	private Integer numeroDamatricula;
	
	@NotEmpty(message = "Ra não pode ser vazio")
	@NotNull(message = "Ra não pode ser nulo")
	@Length(max = 10,message = "Tamanho do ra deve ser até 10 caracteres")
	private String ra;
	
	public Aluno() {}
	
	public Aluno(Integer id, String nome, String sobrenome, String cpf, Sexo sexo,
		Curso curso,Escola escola,Integer numeroDamatricula, String ra) {
		super(id, nome, sobrenome, cpf, sexo,curso,escola);
		this.numeroDamatricula = numeroDamatricula;
		this.ra = ra;
	}

	public Integer getNumeroDamatricula() {
		return numeroDamatricula;
	}

	public void setNumeroDamatricula(Integer numeroDamatricula) {
		this.numeroDamatricula = numeroDamatricula;
	}

	public String getRa() {
		return ra;
	}

	public void setRa(String ra) {
		this.ra = ra;
	}
	
	
	
	
	
	
}
