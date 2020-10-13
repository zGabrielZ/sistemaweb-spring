package com.gabrielferreira.projeto.modelo.entidade.dto;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SalaInserirDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	@Valid
	@NotNull(message = "Turma não pode ser nulo")
	private TurmaNovoId turma;
	
	@Valid
	@NotNull(message = "Aluno não pode ser nulo")
	private AlunoNovoId aluno;
}
