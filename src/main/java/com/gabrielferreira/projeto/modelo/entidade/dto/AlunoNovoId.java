package com.gabrielferreira.projeto.modelo.entidade.dto;

import java.io.Serializable;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlunoNovoId implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "Aluno id não pode ser nulo")
	private Long id;
}
