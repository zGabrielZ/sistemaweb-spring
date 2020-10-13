package com.gabrielferreira.projeto.modelo.entidade.dto;

import java.io.Serializable;


import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SalaDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	private TurmaDTO turma;
	
	@JsonIgnore
	private AlunoDTO aluno;
}
