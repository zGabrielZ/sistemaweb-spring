package com.gabrielferreira.projeto.modelo.entidade.dto;

import java.io.Serializable;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gabrielferreira.projeto.modelo.entidade.enums.Semestre;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AulaDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	
	private Long id;
	private DisciplinaDTO disciplina;
	
	@JsonIgnore
	private AlunoDTO aluno;
	
	@Enumerated(EnumType.ORDINAL)
	private Semestre semestre;
}
