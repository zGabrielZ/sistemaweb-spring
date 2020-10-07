package com.gabrielferreira.projeto.modelo.entidade;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	
	@ManyToOne
	@JoinColumn(name = "curso_id")
	private Curso curso;
	
	public Aluno(Long id, String nomeCompleto, String cpf, Sexo sexo, String ra) {
		super(id, nomeCompleto, cpf, sexo);
		this.ra = ra;
	}
	
}
