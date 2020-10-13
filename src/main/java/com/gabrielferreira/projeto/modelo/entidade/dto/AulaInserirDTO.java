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
public class AulaInserirDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "Tem que escolher o semestre")
	private Integer semestre;
	
	@Valid
	@NotNull(message = "Disciplina não pode ser nulo")
	private Long disciplina;
	
	@Valid
	@NotNull(message = "Aluno não pode ser nulo")
	private Long aluno;
}
