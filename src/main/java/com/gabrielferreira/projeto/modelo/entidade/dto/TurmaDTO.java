package com.gabrielferreira.projeto.modelo.entidade.dto;

import java.io.Serializable;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import com.gabrielferreira.projeto.modelo.entidade.enums.Turno;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TurmaDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nomeTurma;
	private String numeroTurma;
	
	@Enumerated(EnumType.ORDINAL)
	private Turno turno;
	
	private Integer vagas;
	
	private ProfessorDTO professor;
}
